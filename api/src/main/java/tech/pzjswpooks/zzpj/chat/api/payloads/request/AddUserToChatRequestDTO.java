package tech.pzjswpooks.zzpj.chat.api.payloads.request;

import tech.pzjswpooks.zzpj.chat.api.common.I18n;
import tech.pzjswpooks.zzpj.chat.api.security.Login;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AddUserToChatRequestDTO {
    @NotNull(message = I18n.LOGIN_NULL)
    @Login
    private String username;

    public AddUserToChatRequestDTO(@NotEmpty String username) {
        this.username = username;
    }

    public AddUserToChatRequestDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
