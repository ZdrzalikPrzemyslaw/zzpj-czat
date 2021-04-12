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
@Table(name = "chats", schema = "public", catalog = "chatdb")
public class ChatsEntity {
    private Long id;
    private Long ownerId;
    private String name;
    private Object createdAt;
    private Long version;
    private Collection<ChatMessagesEntity> chatMessagesById;
    private Collection<ChatUsersEntity> chatUsersById;
    private AccountsEntity accountsByOwnerId;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "owner_id", nullable = false)
    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "created_at", nullable = false)
    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "version", nullable = true)
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatsEntity that = (ChatsEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(ownerId, that.ownerId) && Objects.equals(name, that.name) && Objects.equals(createdAt, that.createdAt) && Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ownerId, name, createdAt, version);
    }

    @OneToMany(mappedBy = "chatsByChatId")
    public Collection<ChatMessagesEntity> getChatMessagesById() {
        return chatMessagesById;
    }

    public void setChatMessagesById(Collection<ChatMessagesEntity> chatMessagesById) {
        this.chatMessagesById = chatMessagesById;
    }

    @OneToMany(mappedBy = "chatsByChatId")
    public Collection<ChatUsersEntity> getChatUsersById() {
        return chatUsersById;
    }

    public void setChatUsersById(Collection<ChatUsersEntity> chatUsersById) {
        this.chatUsersById = chatUsersById;
    }

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
    public AccountsEntity getAccountsByOwnerId() {
        return accountsByOwnerId;
    }

    public void setAccountsByOwnerId(AccountsEntity accountsByOwnerId) {
        this.accountsByOwnerId = accountsByOwnerId;
    }
}
