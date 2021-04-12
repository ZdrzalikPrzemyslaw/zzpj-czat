package tech.pzjswpooks.zzpj.chat.api.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "accounts", schema = "public", catalog = "chatdb")
public class AccountsEntity {
    private Long id;
    private String username;
    private String password;
    private Boolean enabled;
    private Long version;
    private Long userId;
    private Collection<AccessLevelsEntity> accessLevelsById;
    private UsersEntity usersByUserId;
    private Collection<ChatMessagesEntity> chatMessagesById;
    private Collection<ChatUsersEntity> chatUsersById;
    private Collection<ChatsEntity> chatsById;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 32)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 64)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "enabled", nullable = false)
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Basic
    @Column(name = "version", nullable = false)
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountsEntity that = (AccountsEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(enabled, that.enabled) && Objects.equals(version, that.version) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, enabled, version, userId);
    }

    @OneToMany(mappedBy = "accountsByAccountId")
    public Collection<AccessLevelsEntity> getAccessLevelsById() {
        return accessLevelsById;
    }

    public void setAccessLevelsById(Collection<AccessLevelsEntity> accessLevelsById) {
        this.accessLevelsById = accessLevelsById;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public UsersEntity getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(UsersEntity usersByUserId) {
        this.usersByUserId = usersByUserId;
    }

    @OneToMany(mappedBy = "accountsByAccountId")
    public Collection<ChatMessagesEntity> getChatMessagesById() {
        return chatMessagesById;
    }

    public void setChatMessagesById(Collection<ChatMessagesEntity> chatMessagesById) {
        this.chatMessagesById = chatMessagesById;
    }

    @OneToMany(mappedBy = "accountsByAccountId")
    public Collection<ChatUsersEntity> getChatUsersById() {
        return chatUsersById;
    }

    public void setChatUsersById(Collection<ChatUsersEntity> chatUsersById) {
        this.chatUsersById = chatUsersById;
    }

    @OneToMany(mappedBy = "accountsByOwnerId")
    public Collection<ChatsEntity> getChatsById() {
        return chatsById;
    }

    public void setChatsById(Collection<ChatsEntity> chatsById) {
        this.chatsById = chatsById;
    }
}
