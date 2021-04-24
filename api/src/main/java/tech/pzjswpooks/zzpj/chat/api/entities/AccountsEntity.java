package tech.pzjswpooks.zzpj.chat.api.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "accounts", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"user_id"})})

@NamedQueries({
        @NamedQuery(name = "AccountsEntity.findAll", query = "SELECT a FROM AccountsEntity a"),
        @NamedQuery(name = "AccountsEntity.findById", query = "SELECT a FROM AccountsEntity a WHERE a.id = :id")
})
public class AccountsEntity {

    @JoinColumn(name = "owner_id")
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH})
    private final Set<ChatsEntity> ownedChats = new HashSet<>();
    @JoinColumn(name = "account_id")
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH})
    private final Set<AccessLevelsEntity> accessLevels = new HashSet<>();
    @JoinColumn(name = "account_id")
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH})
    private final List<ChatMessagesEntity> chatMessages = new ArrayList<>();
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
    private Boolean enabled = true;
    @Basic(optional = false)
    @Column(name = "version", nullable = false)
    private Long version = 0L;
    @JoinColumn(name = "user_Id", referencedColumnName = "id", nullable = false, updatable = false)
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    private UsersEntity userId;
    @JoinColumn(name = "account_id")
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH})
    private final Set<ChatUsersEntity> accountId = new HashSet<>();

    // Konstruktor tworzy też instancje tabeli users
    public AccountsEntity(String username, String password, String email, String firstName, String language, String lastName, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.userId = new UsersEntity(email, firstName, language, lastName, phoneNumber);
    }

    public AccountsEntity() {

    }

    public Set<ChatsEntity> getOwnedChats() {
        return ownedChats;
    }

    public Set<AccessLevelsEntity> getAccessLevels() {
        return accessLevels;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
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

    public List<ChatMessagesEntity> getChatMessages() {
        return chatMessages;
    }

    public Set<ChatUsersEntity> getAccountId() {
        return accountId;
    }
}
