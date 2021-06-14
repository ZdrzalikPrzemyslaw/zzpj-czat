package tech.pzjswpooks.zzpj.chat.api.payloads.request;

import tech.pzjswpooks.zzpj.chat.api.common.I18n;
import tech.pzjswpooks.zzpj.chat.api.security.Login;

import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AuthenticationRequestDTO {
    @NotNull(message = I18n.LOGIN_NULL)
    @Login
    private String username;
    @Size(min = 8, message = I18n.PASSWORD_INVALID_SIZE)
    @NotNull(message = I18n.PASSWORD_NULL)
    private String password;

    public AuthenticationRequestDTO() {
    }

    public AuthenticationRequestDTO(String username, String password) {
        this.username = username;
        this.password = password;
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

    public UsernamePasswordCredential toCredential() {
        return new UsernamePasswordCredential(username, password);
    }
}
