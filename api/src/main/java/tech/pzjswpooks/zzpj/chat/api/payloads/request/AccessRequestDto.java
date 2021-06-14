package tech.pzjswpooks.zzpj.chat.api.payloads.request;

import tech.pzjswpooks.zzpj.chat.api.common.I18n;
import tech.pzjswpooks.zzpj.chat.api.security.Login;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AccessRequestDto {
    @NotNull(message = I18n.LOGIN_NULL)
    @Login
    private String username;
    @NotNull(message = I18n.ACCESS_LEVEL_NULL)
    private String accessLevel;

    public AccessRequestDto() {
    }

    public AccessRequestDto(@NotEmpty @Login String username,
                            @NotEmpty String accessLevel) {
        this.username = username;
        this.accessLevel = accessLevel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }
}
