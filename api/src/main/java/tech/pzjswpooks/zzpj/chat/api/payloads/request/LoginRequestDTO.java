package tech.pzjswpooks.zzpj.chat.api.payloads.request;

import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.validation.constraints.NotNull;

public class LoginRequestDTO {
    // TODO: 09.05.2021 Ograniczenia
    @NotNull
    private String username;
    @NotNull
    private String password;

    public LoginRequestDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginRequestDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UsernamePasswordCredential toCredential() {
        return new UsernamePasswordCredential(username, password);
    }
}
