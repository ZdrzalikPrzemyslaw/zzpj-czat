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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "chat_users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"chat_id", "account_id"})
})
@NamedQueries({
        @NamedQuery(name = "ChatUsersEntity.findAll", query = "SELECT a FROM ChatUsersEntity a"),
        @NamedQuery(name = "ChatUsersEntity.findById", query = "SELECT a FROM ChatUsersEntity a WHERE a.id = :id"),
        @NamedQuery(name = "ChatUsersEntity.findUserInChat", query = "SELECT cue FROM ChatUsersEntity cue, AccountsEntity ae, ChatsEntity ce " +
                "WHERE ae.id = cue.accountId.id AND ce.id = cue.chatId.id AND ae.username = :username AND ce.id = :id")
})
public class ChatUsersEntity {
    public ChatUsersEntity() {

    }

    public ChatUsersEntity(ChatsEntity chatId, AccountsEntity accountId) {
        this.chatId = chatId;
        this.accountId = accountId;
    }

    @Id
    @SequenceGenerator(name = "chat_users_generator", sequenceName = "chat_users_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chat_users_generator")
    @Basic(optional = false)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @JoinColumn(name = "chat_id", referencedColumnName = "id", nullable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ChatsEntity chatId;

    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private AccountsEntity accountId;

    public Long getId() {
        return id;
    }

    public ChatsEntity getChatId() {
        return chatId;
    }

    public AccountsEntity getAccountId() {
        return accountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ChatUsersEntity that = (ChatUsersEntity) o;

        return new EqualsBuilder().append(id, that.id).append(chatId, that.chatId).append(accountId, that.accountId).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(chatId).append(accountId).toHashCode();
    }


}
