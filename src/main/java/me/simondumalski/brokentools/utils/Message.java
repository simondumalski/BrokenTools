package me.simondumalski.brokentools.utils;

public enum Message {

    TOOL_BREAK("messages.tool-break"),
    ARMOR_BREAK("messages.armor-break");

    private final String configValue;

    Message(String configValue) {
        this.configValue = configValue;
    }

    public String getConfigValue() {
        return configValue;
    }

}
