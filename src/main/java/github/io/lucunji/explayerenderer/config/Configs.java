package github.io.lucunji.explayerenderer.config;


import com.google.common.collect.ImmutableList;
import github.io.lucunji.explayerenderer.Reference;
import malilib.config.category.BaseConfigOptionCategory;
import malilib.config.category.ConfigOptionCategory;
import malilib.config.option.*;
import malilib.input.KeyBindSettings;

import java.util.List;

public class Configs {
    public static final HotkeyConfig MENU_OPEN_KEY;
    public static final BooleanConfig ENABLED;
    public static final BooleanConfig SPECTATOR_AUTO_SWITCH;
    public static final StringConfig PLAYER_NAME;

    public static final DoubleConfig OFFSET_X;
    public static final DoubleConfig OFFSET_Y;
    public static final DoubleConfig ROTATION_X;
    public static final DoubleConfig ROTATION_Y;
    public static final DoubleConfig ROTATION_Z;
    public static final DoubleConfig SIZE;
    public static final BooleanConfig MIRROR;

    public static final DoubleConfig PITCH_MIN;
    public static final DoubleConfig PITCH_MAX;
    public static final DoubleConfig PITCH_OFFSET;
    public static final DoubleConfig HEAD_YAW_MIN;
    public static final DoubleConfig HEAD_YAW_MAX;
    public static final DoubleConfig BODY_YAW_MIN;
    public static final DoubleConfig BODY_YAW_MAX;
    public static final DoubleConfig SNEAKING_OFFSET_Y;
    public static final DoubleConfig ELYTRA_OFFSET_Y;

    public static final BooleanConfig SWING_HANDS;
    public static final DoubleConfig LIGHT_DEGREE;

    public static final List<ConfigOptionCategory> CATEGORIES;

    static {
        MENU_OPEN_KEY = Category.GENERAL.add(new HotkeyConfig("openMenuKey", "F8", KeyBindSettings.INGAME_RELEASE_EXCLUSIVE));
        ENABLED = Category.GENERAL.add(new BooleanConfig("enabled", true, "explayerenderer.gui.settings.enabled.desc"));
        SPECTATOR_AUTO_SWITCH = Category.GENERAL.add(new BooleanConfig("spectatorAutoSwitch", true, "explayerenderer.gui.settings.spectator_auto_switch.desc"));
        PLAYER_NAME = Category.GENERAL.add(new StringConfig("playerName", "", "explayerenderer.gui.settings.player_name.desc"));

        OFFSET_X = Category.GENERAL.add(new DoubleConfig("offsetX", 0.12, -0.5, 1.5, "explayerenderer.gui.settings.offset_x.desc"));
        OFFSET_Y = Category.GENERAL.add(new DoubleConfig("offsetY", 1.5, -0.5, 2.5, "explayerenderer.gui.settings.offset_y.desc"));
        ROTATION_X = Category.GENERAL.add(new DoubleConfig("rotationX", 0, -180, 180, "explayerenderer.gui.settings.rotation_x.desc"));
        ROTATION_Y = Category.GENERAL.add(new DoubleConfig("rotationY", 0, -180, 180, "explayerenderer.gui.settings.rotation_y.desc"));
        ROTATION_Z = Category.GENERAL.add(new DoubleConfig("rotationZ", 0, -180, 180, "explayerenderer.gui.settings.rotation_z.desc"));
        SIZE = Category.GENERAL.add(new DoubleConfig("size", 0.5, 0, 2, "explayerenderer.gui.settings.size.desc"));
        MIRROR = Category.GENERAL.add(new BooleanConfig("mirror", false, "explayerenderer.gui.settings.mirror.desc"));

        PITCH_MIN = Category.DETAILS.add(new DoubleConfig("pitchMin", -20, -180, 180, "explayerenderer.gui.settings.pitch_min.desc"));
        PITCH_MAX = Category.DETAILS.add(new DoubleConfig("pitchMax", 20, -180, 180, "explayerenderer.gui.settings.pitch_max.desc"));
        PITCH_OFFSET = Category.DETAILS.add(new DoubleConfig("pitchOffset", 0, -90, 90, "explayerenderer.gui.settings.pitch_offset.desc"));
        HEAD_YAW_MIN = Category.DETAILS.add(new DoubleConfig("headYawMin", -15, -180, 180, "explayerenderer.gui.settings.head_yaw_min.desc"));
        HEAD_YAW_MAX = Category.DETAILS.add(new DoubleConfig("headYawMax", -15, -180, 180, "explayerenderer.gui.settings.head_yaw_max.desc"));
        BODY_YAW_MIN = Category.DETAILS.add(new DoubleConfig("bodyYawMin", 0, -180, 180, "explayerenderer.gui.settings.body_yaw_min.desc"));
        BODY_YAW_MAX = Category.DETAILS.add(new DoubleConfig("bodyYawMax", 0, -180, 180, "explayerenderer.gui.settings.body_yaw_max.desc"));
        SNEAKING_OFFSET_Y = Category.DETAILS.add(new DoubleConfig("sneakingYOffset", -30, -100, 100, "explayerenderer.gui.settings.sneaking_y_offset.desc"));
        ELYTRA_OFFSET_Y = Category.DETAILS.add(new DoubleConfig("elytraYOffset", -120, -300, 300, "explayerenderer.gui.settings.elytra_y_offset.desc"));

        SWING_HANDS = Category.DETAILS.add(new BooleanConfig("swingHands", true, "explayerenderer.gui.settings.swing_hands.desc"));
        LIGHT_DEGREE = Category.DETAILS.add(new DoubleConfig("lightDegree", 0, -180, 180, "explayerenderer.gui.settings.light_degree.desc"));

        CATEGORIES = ImmutableList.of(
                BaseConfigOptionCategory.normal(Reference.MOD_INFO, "Generic",          Category.GENERAL.getConfigs()),
                BaseConfigOptionCategory.normal(Reference.MOD_INFO, "Detail",          Category.DETAILS.getConfigs())
        );
    }
}
