package tech.pzjswpooks.zzpj.chat.api.payloads.response;

import tech.pzjswpooks.zzpj.chat.api.entities.AccountsEntity;

import java.util.HashSet;
import java.util.Set;

public class AccountInfoResponseDTO {

    private Long id;
    private String username;
    private Boolean enabled;
    private UsersResponseDTO userData;
    private Set<AccessLevelDTO> roles;

    public AccountInfoResponseDTO() {
    }

    public AccountInfoResponseDTO(AccountsEntity accountsEntity) {
        roles = new HashSet<>();
        accountsEntity.getAccessLevels().forEach(accessLevelsEntity -> roles.add(new AccessLevelDTO(accessLevelsEntity.getLevel(), accessLevelsEntity.getEnabled())));
        userData = new UsersResponseDTO(accountsEntity.getUserId());
        id = accountsEntity.getId();
        username = accountsEntity.getUsername();
        enabled = accountsEntity.getEnabled();
    }

    public Set<AccessLevelDTO> getRoles() {
        return roles;
    }

    public void setRoles(Set<AccessLevelDTO> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public UsersResponseDTO getUserData() {
        return userData;
    }

    public static class AccessLevelDTO {
        private String role;
        private Boolean enabled;

        public AccessLevelDTO(String role, Boolean enabled) {
            this.role = role;
            this.enabled = enabled;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }
    }

}
