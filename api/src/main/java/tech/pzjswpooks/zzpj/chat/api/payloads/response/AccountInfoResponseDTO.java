package tech.pzjswpooks.zzpj.chat.api.payloads.response;

import tech.pzjswpooks.zzpj.chat.api.entities.AccessLevelsEntity;
import tech.pzjswpooks.zzpj.chat.api.entities.AccountsEntity;

import java.util.Set;
import java.util.function.Consumer;

public class AccountInfoResponseDTO {

    private Long id;
    private String username;
    private Boolean enabled;
    private UsersResponseDTO userData;
    private Set<String> roles;

    public AccountInfoResponseDTO() {
    }

    public AccountInfoResponseDTO(AccountsEntity accountsEntity) {
        accountsEntity.getAccessLevels().forEach(accessLevelsEntity -> roles.add(accessLevelsEntity.getLevel()));
        userData = new UsersResponseDTO(accountsEntity.getUserId());
        id = accountsEntity.getId();
        username = accountsEntity.getUsername();
        enabled = accountsEntity.getEnabled();
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
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

}
