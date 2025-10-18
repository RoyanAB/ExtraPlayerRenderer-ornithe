package github.io.lucunji.explayerenderer.client.render.screen;

import com.google.common.collect.ImmutableList;
import github.io.lucunji.explayerenderer.Reference;
import github.io.lucunji.explayerenderer.config.Category;
import malilib.gui.BaseScreen;
import malilib.gui.config.BaseConfigTab;
import malilib.gui.config.ConfigTab;
import malilib.gui.tab.ScreenTab;
import malilib.util.data.ModInfo;

public class GuiConfig {
    public static final ModInfo MOD_INFO = Reference.MOD_INFO;

    private static final BaseConfigTab GENERIC              = new BaseConfigTab(MOD_INFO, "generic",    160, Category.GENERAL.getConfigs(),       GuiConfig::create);
    private static final BaseConfigTab DETAILS               = new BaseConfigTab(MOD_INFO, "detail", 100, Category.DETAILS.getConfigs(), GuiConfig::create);

    public static final ImmutableList<ConfigTab> CONFIG_TABS = ImmutableList.of(
            GENERIC,
            DETAILS
    );

    public static final ImmutableList<ScreenTab> ALL_TABS = ImmutableList.of(
            GENERIC,
            DETAILS
    );

    public static void open()
    {
        BaseScreen.openScreen(create());
    }

    public static BaseScreen create()
    {
        // The parent screen should not be set here, to prevent infinite recursion via
        // the call to the parent's setWorldAndResolution -> initScreen -> switch tab -> etc.
        return BaseScreenWithEPR.withExtensionModTabs(MOD_INFO, ALL_TABS, GENERIC,
                "explayerenderer.title.screen.configs", Reference.MOD_ID);
    }

    public static ImmutableList<ConfigTab> getConfigTabs()
    {
        return CONFIG_TABS;
    }
}
