package tech.pzjswpooks.zzpj.chat.api.common;

public enum AccessLevels {
    ADMIN("level.admin"),
    USER("level.user");

    private final String level;

    AccessLevels(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }
}
