package github.io.lucunji.explayerenderer.mixin;

import github.io.lucunji.explayerenderer.Main;
import github.io.lucunji.explayerenderer.client.render.PlayerHUDRenderer;
import malilib.event.dispatch.RenderEventDispatcherImpl;
import malilib.registry.Registry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public abstract class EntityRendererMixin {
    @Shadow @Final private Minecraft mc;

    @Inject(method = "updateCameraAndRender(FJ)V", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/GuiIngame;renderGameOverlay(F)V",
            shift = Shift.AFTER))
    private void onRenderGameOverlayPost(float tickDelta, long nanoTime, CallbackInfo ci)
    {
        if (this.mc.world != null && this.mc.player != null)
        {
            Main.playerHUDRenderer.onRenderGameOverlayPost(tickDelta);
        }
    }



}
