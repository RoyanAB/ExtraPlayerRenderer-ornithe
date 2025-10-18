package github.io.lucunji.explayerenderer.client.render.screen;

import github.io.lucunji.explayerenderer.Main;
import malilib.gui.config.BaseConfigScreen;
import malilib.gui.config.ConfigTab;
import malilib.gui.tab.ScreenTab;
import malilib.registry.Registry;
import malilib.util.ListUtils;
import malilib.util.data.ModInfo;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BaseScreenWithEPR extends BaseConfigScreen {
    public BaseScreenWithEPR(ModInfo modInfo, List<? extends ScreenTab> configTabs, ConfigTab defaultTab, String titleKey, Object... args) {
        super(modInfo, configTabs, defaultTab, titleKey, args);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        Main.playerHUDRenderer.doRender(partialTicks);
        super.drawScreen(mouseX,mouseY,partialTicks);
    }

    public static BaseScreenWithEPR withExtensionModTabs(ModInfo modInfo,
                                                        List<? extends ScreenTab> configTabs,
                                                        @Nullable ConfigTab defaultTab,
                                                        String titleKey, Object... args)
    {
        List<? extends ScreenTab> extraTabs = Registry.CONFIG_TAB.getExtraConfigScreenTabsFor(modInfo);
        List<ScreenTab> allTabs = ListUtils.getAppendedList(configTabs, extraTabs);
        return new BaseScreenWithEPR(modInfo, allTabs, defaultTab, titleKey, args);
    }
}
