package github.io.lucunji.explayerenderer.client.render;

import github.io.lucunji.explayerenderer.config.Configs;
import github.io.lucunji.explayerenderer.mixin.EntityAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public class PlayerHUDRenderer {
    public void onRenderGameOverlayPost(float tickDelta) {
        if (Minecraft.getMinecraft().skipRenderWorld || Minecraft.getMinecraft().currentScreen != null) return;
        this.doRender(tickDelta);
    }

    public void doRender(float tickDelta) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || !Configs.ENABLED.getBooleanValue()) return;
        EntityLivingBase targetEntity = Minecraft.getMinecraft().world.playerEntities.stream().filter(p -> p.getName().equals(Configs.PLAYER_NAME.getValue())).findFirst().orElse(Minecraft.getMinecraft().player);
        if (Configs.SPECTATOR_AUTO_SWITCH.getBooleanValue() && Minecraft.getMinecraft().player.isSpectator()) {
            Entity cameraEntity = Minecraft.getMinecraft().getRenderViewEntity();
            if (cameraEntity instanceof EntityLivingBase) {
                targetEntity = (EntityLivingBase) cameraEntity;
            } else if (cameraEntity != null) {
                return;
            }
        }
        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        int scaledWidth = scaledResolution.getScaledWidth();
        int scaledHeight = scaledResolution.getScaledHeight();
        double posX = Configs.OFFSET_X.getDoubleValue() * scaledWidth;
        double posY = Configs.OFFSET_Y.getDoubleValue() * scaledHeight;
        if (((EntityAccessor)targetEntity).epr_getFlag(7))
            posY += Configs.ELYTRA_OFFSET_Y.getDoubleValue();
        else if (targetEntity.isSneaking())
            posY += Configs.SNEAKING_OFFSET_Y.getDoubleValue();
        double size = Configs.SIZE.getDoubleValue() * scaledHeight;
        boolean mirror = Configs.MIRROR.getBooleanValue();
        float lightDegree = Configs.LIGHT_DEGREE.getFloatValue();

        GlStateManager.enableColorMaterial();
        {
            GlStateManager.pushMatrix();
            GlStateManager.translate(posX, posY, -500.0F);
            GlStateManager.scale(size * (mirror ? 1 : -1), size, size);
            GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);

            /* *************** data storing *************** */
            float bodyYaw = targetEntity.renderYawOffset;
            float pitch = targetEntity.rotationPitch;
            float prevBodyYaw = targetEntity.prevRenderYawOffset;
            float prevHeadYaw = targetEntity.prevRotationYawHead;
            float prevPitch = targetEntity.prevRotationPitch;
            float headYaw = targetEntity.rotationYawHead;
            float handSwingProgress = targetEntity.swingProgress;
            int hurtTime = targetEntity.hurtResistantTime;

            GlStateManager.rotate(lightDegree, 0.0F, 1.0F, 0.0F);
            RenderHelper.enableStandardItemLighting();
            GlStateManager.rotate(-lightDegree, 0.0F, 1.0F, 0.0F);

            targetEntity.prevRenderYawOffset = targetEntity.renderYawOffset = (float) MathHelper.clamp(targetEntity.renderYawOffset, Configs.BODY_YAW_MIN.getDoubleValue(), Configs.BODY_YAW_MAX.getDoubleValue());
            targetEntity.prevRotationYawHead = targetEntity.rotationYawHead = (float) MathHelper.clamp(targetEntity.rotationYawHead, Configs.HEAD_YAW_MIN.getDoubleValue(), Configs.HEAD_YAW_MAX.getDoubleValue());
            targetEntity.prevRotationPitch = targetEntity.rotationPitch = (float) (MathHelper.clamp(targetEntity.rotationPitch, Configs.PITCH_MIN.getDoubleValue(), Configs.PITCH_MAX.getDoubleValue()) + Configs.PITCH_OFFSET.getDoubleValue());

            if (!Configs.SWING_HANDS.getBooleanValue())
                targetEntity.swingProgress = 0;

            if (!Configs.HURT_FLASH.getBooleanValue()) {
                targetEntity.hurtTime = 0;
            }

            GlStateManager.rotate(((float) Configs.ROTATION_X.getDoubleValue()), 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(((float) Configs.ROTATION_Y.getDoubleValue()), 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(((float) Configs.ROTATION_Z.getDoubleValue()), 0.0F, 0.0F, 1.0F);

            GlStateManager.translate(0.0F, 0.0F, 0.0F);
            RenderManager entityRenderDispatcher = Minecraft.getMinecraft().getRenderManager();
            entityRenderDispatcher.setPlayerViewY(180.0F);

            boolean renderShadow = entityRenderDispatcher.isRenderShadow();
            entityRenderDispatcher.setRenderShadow(renderShadow);
            entityRenderDispatcher.renderEntity(targetEntity, 0.0D, 0.0D, 0.0D, 0.0F, tickDelta, true);
            entityRenderDispatcher.setRenderShadow(renderShadow);

            /* *************** data restoring *************** */
            targetEntity.renderYawOffset = bodyYaw;
            targetEntity.rotationPitch = pitch;
            targetEntity.prevRenderYawOffset = prevBodyYaw;
            targetEntity.prevRotationYawHead = prevHeadYaw;
            targetEntity.prevRotationPitch = prevPitch;
            targetEntity.rotationYawHead = headYaw;
            targetEntity.swingProgress = handSwingProgress;
            targetEntity.hurtTime = hurtTime;
        }

        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }
}
