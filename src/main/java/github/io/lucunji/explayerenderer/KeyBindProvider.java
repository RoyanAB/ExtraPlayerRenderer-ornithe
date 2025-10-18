package github.io.lucunji.explayerenderer;

import com.google.common.collect.ImmutableList;
import github.io.lucunji.explayerenderer.config.Configs;
import malilib.input.Hotkey;
import malilib.input.HotkeyCategory;
import malilib.input.HotkeyProvider;

import java.util.ArrayList;
import java.util.List;

public class KeyBindProvider implements HotkeyProvider {

    public static final KeyBindProvider INSTANCE = new KeyBindProvider();

    @Override
    public List<? extends Hotkey> getAllHotkeys()
    {
        List<Hotkey> list = new ArrayList<>();
        list.add(Configs.MENU_OPEN_KEY);
        return list;
    }

    @Override
    public List<HotkeyCategory> getHotkeysByCategories()
    {
        List<Hotkey> list = new ArrayList<>();
        list.add(Configs.MENU_OPEN_KEY);
        return ImmutableList.of(new HotkeyCategory(Reference.MOD_INFO, "explayerenderer.config.tab.generic", list));
    }
}