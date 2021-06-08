package tech.pzjswpooks.zzpj.chat.api.payloads.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ChangeChatOwnerRequestDTO {
    @NotEmpty
    @Size(min = 6, max = 32, message = "Invalid size of username")
    private String username;

    public ChangeChatOwnerRequestDTO(@NotEmpty @Size(min = 6, max = 32, message = "Invalid size of username") String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
