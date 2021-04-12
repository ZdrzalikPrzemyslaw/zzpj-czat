package tech.pzjswpooks.zzpj.chat.api.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "chat_messages", schema = "public", catalog = "chatdb")
public class ChatMessagesEntity {
    private Long id;
    private Long chatId;
    private Long accountId;
    private String text;
    private Object createdAt;
    private ChatsEntity chatsByChatId;
    private AccountsEntity accountsByAccountId;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "chat_id", nullable = false)
    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    @Basic
    @Column(name = "account_id", nullable = false)
    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    @Basic
    @Column(name = "text", nullable = true, length = 4096)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Basic
    @Column(name = "created_at", nullable = false)
    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ChatMessagesEntity that = (ChatMessagesEntity) o;

        return new EqualsBuilder().append(id, that.id).append(chatId, that.chatId).append(accountId, that.accountId).append(text, that.text).append(createdAt, that.createdAt).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(chatId).append(accountId).append(text).append(createdAt).toHashCode();
    }

    @ManyToOne
    @JoinColumn(name = "chat_id", referencedColumnName = "id", nullable = false)
    public ChatsEntity getChatsByChatId() {
        return chatsByChatId;
    }

    public void setChatsByChatId(ChatsEntity chatsByChatId) {
        this.chatsByChatId = chatsByChatId;
    }

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    public AccountsEntity getAccountsByAccountId() {
        return accountsByAccountId;
    }

    public void setAccountsByAccountId(AccountsEntity accountsByAccountId) {
        this.accountsByAccountId = accountsByAccountId;
    }
}
