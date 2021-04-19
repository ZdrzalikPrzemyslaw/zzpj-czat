package tech.pzjswpooks.zzpj.chat.api.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

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

    @Column(name = "owner_id", nullable = false)
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false, updatable = false)
    @ManyToOne
    private AccountsEntity ownerId;

    @Basic
    @Column(name = "name", nullable = true, length = 30)
    private String name;

    @Basic(optional = false)
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Basic(optional = false)
    @Column(name = "version", nullable = false)
    private Long version;
    /*
    private Collection<ChatMessagesEntity> chatMessagesById;
    private Collection<ChatUsersEntity> chatUsersById;
    private AccountsEntity accountsByOwnerId;
     */

    /**
     * Initializes createdAt with current time.
     */
    @PreUpdate
    public void initCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getOwnerId() {
        return ownerId.getId();
    }

    //public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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

    /*
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

     */
}
