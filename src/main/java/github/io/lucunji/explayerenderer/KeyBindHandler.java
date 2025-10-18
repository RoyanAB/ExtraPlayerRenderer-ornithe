package github.io.lucunji.explayerenderer;

import github.io.lucunji.explayerenderer.client.render.screen.GuiConfig;
import malilib.input.ActionResult;
import malilib.input.KeyAction;
import malilib.input.KeyBind;
import malilib.input.callback.HotkeyCallback;

public class KeyBindHandler implements HotkeyCallback {

    @Override
    public ActionResult onKeyAction(KeyAction action, KeyBind key) {
        GuiConfig.open();
        return ActionResult.SUCCESS;
    }
}
