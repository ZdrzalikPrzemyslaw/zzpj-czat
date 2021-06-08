package tech.pzjswpooks.zzpj.chat.api.payloads.request;

import tech.pzjswpooks.zzpj.chat.api.security.Login;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ChangeChatOwnerRequestDTO {
    @NotEmpty
    @Login
    private String username;


    public ChangeChatOwnerRequestDTO(@NotEmpty String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ChangeChatOwnerRequestDTO() {
    }
}
