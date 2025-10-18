package github.io.lucunji.explayerenderer.compat.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import github.io.lucunji.explayerenderer.client.render.screen.GuiConfig;
import malilib.gui.BaseScreen;

public class ModMenuApiImpl implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory()
    {
        return (currentScreen) -> {
            BaseScreen screen = GuiConfig.create();
            screen.setParent(currentScreen);
            return screen;
        };
    }
}