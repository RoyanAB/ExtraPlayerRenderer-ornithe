package github.io.lucunji.explayerenderer.config;

import com.google.common.collect.ImmutableList;
import malilib.config.option.ConfigOption;

import java.util.ArrayList;
import java.util.List;

public enum Category {
    GENERAL("explayerenderer.gui.settings.general"),
    DETAILS("explayerenderer.gui.settings.details");

    private final String key;
    private final List<ConfigOption<?>> configs;

    Category(String key) {
        this.key = key;
        configs = new ArrayList<>();
    }

    protected <T extends ConfigOption<?>> T add(T config) {
        this.configs.add(config);
        return config;
    }

    public List<ConfigOption<?>> getConfigs() {
        return ImmutableList.copyOf(this.configs);
    }

    public String getKey() {
        return this.key;
    }
}
