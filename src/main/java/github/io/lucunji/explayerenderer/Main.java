package github.io.lucunji.explayerenderer;

import github.io.lucunji.explayerenderer.client.render.PlayerHUDRenderer;
import github.io.lucunji.explayerenderer.config.ConfigHandler;
import github.io.lucunji.explayerenderer.config.Configs;
import malilib.event.InitializationHandler;
import malilib.registry.Registry;
import net.ornithemc.osl.entrypoints.api.client.ClientModInitializer;

public class Main implements ClientModInitializer, InitializationHandler {

    public static final String MOD_ID = "explayerenderer";

    public static PlayerHUDRenderer playerHUDRenderer;

    @Override
    public void initClient() {
        Registry.INITIALIZATION_DISPATCHER.registerInitializationHandler(new Main());

        playerHUDRenderer = new PlayerHUDRenderer();
    }

    @Override
    public void registerModHandlers() {
        Registry.CONFIG_MANAGER.registerConfigHandler(new ConfigHandler());
        new Configs();  // just load the class and run static code block
        Registry.HOTKEY_MANAGER.registerHotkeyProvider(KeyBindProvider.INSTANCE);
        Configs.MENU_OPEN_KEY.getKeyBind().setCallback(new KeyBindHandler());
    }
}
