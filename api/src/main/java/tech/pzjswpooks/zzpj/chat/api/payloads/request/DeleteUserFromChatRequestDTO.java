package tech.pzjswpooks.zzpj.chat.api.payloads.request;

import tech.pzjswpooks.zzpj.chat.api.security.Login;

import javax.validation.constraints.NotEmpty;

public class DeleteUserFromChatRequestDTO {
    @NotEmpty
    @Login
    private String username;

    public DeleteUserFromChatRequestDTO(@NotEmpty String username) {
        this.username = username;
    }

    public DeleteUserFromChatRequestDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
