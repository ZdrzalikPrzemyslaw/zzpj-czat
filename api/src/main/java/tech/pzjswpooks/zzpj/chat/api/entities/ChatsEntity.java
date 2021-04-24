package tech.pzjswpooks.zzpj.chat.api.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "chats")
@NamedQueries({
        @NamedQuery(name = "ChatsEntity.findAll", query = "SELECT a FROM ChatsEntity a"),
        @NamedQuery(name = "ChatsEntity.findById", query = "SELECT a FROM ChatsEntity a WHERE a.id = :id")
})
public class ChatsEntity {

    @Id
    @SequenceGenerator(name = "chats_generator", sequenceName = "chats_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chats_generator")
    @Basic(optional = false)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false, updatable = true)
    @ManyToOne(optional = false)
    private AccountsEntity ownerId;
    @Basic(optional = true)
    @Column(name = "name", nullable = true, length = 30)
    private String name;
    @Basic(optional = false)
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Basic(optional = false)
    @Column(name = "version", nullable = false)
    private Long version = 0L;
    @JoinColumn(name = "chat_id")
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH})
    private final List<ChatMessagesEntity> chatMessages = new ArrayList<>();
    @JoinColumn(name = "chat_id")
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH})
    private final Set<ChatUsersEntity> chatUsers = new HashSet<>();

    public ChatsEntity() {

    }

    public ChatsEntity(AccountsEntity ownerId, String name) {
        this.ownerId = ownerId;
        this.name = name;
    }

    @PrePersist
    private void init() {
        createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public AccountsEntity getOwner() {
        return ownerId;
    }

    public void setOwner(AccountsEntity ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ChatsEntity that = (ChatsEntity) o;

        return new EqualsBuilder().append(id, that.id).append(ownerId, that.ownerId).append(name, that.name).append(createdAt, that.createdAt).append(version, that.version).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(ownerId).append(name).append(createdAt).append(version).toHashCode();
    }

    public List<ChatMessagesEntity> getChatMessages() {
        return chatMessages;
    }

    public Set<ChatUsersEntity> getChatUsers() {
        return chatUsers;
    }
}
