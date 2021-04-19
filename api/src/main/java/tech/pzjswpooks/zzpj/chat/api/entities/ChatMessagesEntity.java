package tech.pzjswpooks.zzpj.chat.api.entities;

import javax.persistence.*;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.LocalDateTime;

@Entity
@Table(name = "chat_messages")
@NamedQueries({
        @NamedQuery(name = "ChatMessages.findAll", query = "SELECT a FROM ChatMessagesEntity a"),
        @NamedQuery(name = "ChatMessages.findById", query = "SELECT a FROM ChatMessagesEntity a WHERE a.id = :id")
})
public class ChatMessagesEntity {

    @Id
    @SequenceGenerator(name = "chat_messages_generator", sequenceName = "chat_messages_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chat_messages_generator")
    @Basic(optional = false)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "chat_id", nullable = false)
    @JoinColumn(name = "chat_id", referencedColumnName = "id", nullable = false, updatable = false)
    @ManyToOne
    private ChatsEntity chatId;

    @Basic
    @Column(name = "text", nullable = true, length = 4096)
    private String text;

    @Column(name = "account_id", nullable = false)
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false, updatable = false)
    @ManyToOne
    private AccountsEntity accountId;

    @Basic
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

//    private ChatsEntity chatsByChatId;
//    private AccountsEntity accountsByAccountId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChatId() {
        return chatId.getId();
    }


    public Long getAccountId() {
        return accountId.getId();
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @PreUpdate
    public void initCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ChatMessagesEntity that = (ChatMessagesEntity) o;

        return new EqualsBuilder().append(id, that.id).append(chatId, that.chatId).append(accountId, that.accountId).append(text, that.text).append(createdAt, that.createdAt).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(chatId).append(accountId).append(text).append(createdAt).toHashCode();
    }

//    @ManyToOne
//    @JoinColumn(name = "chat_id", referencedColumnName = "id", nullable = false)
//    public ChatsEntity getChatsByChatId() {
//        return chatsByChatId;
//    }
//
//    public void setChatsByChatId(ChatsEntity chatsByChatId) {
//        this.chatsByChatId = chatsByChatId;
//    }
//
//    @ManyToOne
//    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
//    public AccountsEntity getAccountsByAccountId() {
//        return accountsByAccountId;
//    }
//
//    public void setAccountsByAccountId(AccountsEntity accountsByAccountId) {
//        this.accountsByAccountId = accountsByAccountId;
//    }
}
