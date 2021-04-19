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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "accounts", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"user_id"})})

@NamedQueries({
        @NamedQuery(name = "AccountsEntity.findAll", query = "SELECT a FROM AccountsEntity a"),
        @NamedQuery(name = "AccountsEntity.findById", query = "SELECT a FROM AccountsEntity a WHERE a.id = :id")
})
public class AccountsEntity {

    @Id
    @SequenceGenerator(name = "accounts_generator", sequenceName = "accounts_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accounts_generator")
    @Basic(optional = false)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Basic(optional = false)
    @Column(name = "username", nullable = false, length = 32)
    private String username;

    @Basic(optional = false)
    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @Basic(optional = false)
    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    @Basic(optional = false)
    @Column(name = "version", nullable = false)
    private Long version;

    @Column(name = "user_id", nullable = false)
    @JoinColumn(name = "user_Id", referencedColumnName = "id", nullable = false, updatable = false)
    @ManyToOne
    private UsersEntity userId;
    /*
    private Collection<AccessLevelsEntity> accessLevelsById;
    private UsersEntity usersByUserId;
    private Collection<ChatMessagesEntity> chatMessagesById;
    private Collection<ChatUsersEntity> chatUsersById;
    private Collection<ChatsEntity> chatsById;
     */


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public UsersEntity getUserId() {
        return userId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AccountsEntity that = (AccountsEntity) o;

        return new EqualsBuilder().append(id, that.id).append(username, that.username).append(password, that.password).append(enabled, that.enabled)
                .append(version, that.version).append(userId, that.userId).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(username).append(password).append(enabled).append(version).append(userId).toHashCode();
    }
    /*
    @OneToMany(mappedBy = "accountsByAccountId")
    public Collection<AccessLevelsEntity> getAccessLevelsById() {
        return accessLevelsById;
    }

    public void setAccessLevelsById(Collection<AccessLevelsEntity> accessLevelsById) {
        this.accessLevelsById = accessLevelsById;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, updatable = false)
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

     */
}
