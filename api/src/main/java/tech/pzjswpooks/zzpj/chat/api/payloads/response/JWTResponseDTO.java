package tech.pzjswpooks.zzpj.chat.api.payloads.response;

import java.util.Set;

public class JWTResponseDTO {
    private final String username;
    private final Set<String> roles;
    private final String token;

    public JWTResponseDTO(String username, Set<String> roles, String token) {
        this.username = username;
        this.roles = roles;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public String getToken() {
        return token;
    }
}
