package tech.pzjswpooks.zzpj.chat.api.payloads.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AccessRequestDto {
    @NotEmpty
    @Size(min = 6, max = 32, message = "Invalid size of username")
    private String username;
    @NotNull
    private String accessLevel;


    public AccessRequestDto(@NotEmpty @Size(min = 6, max = 32, message = "Invalid size of username") String username,
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
