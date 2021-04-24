package tech.pzjswpooks.zzpj.chat.api.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Basic;
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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "chat_messages")
@NamedQueries({
        @NamedQuery(name = "ChatMessagesEntity.findAll", query = "SELECT a FROM ChatMessagesEntity a"),
        @NamedQuery(name = "ChatMessagesEntity.findById", query = "SELECT a FROM ChatMessagesEntity a WHERE a.id = :id")
})
public class ChatMessagesEntity {

    @Id
    @SequenceGenerator(name = "chat_messages_generator", sequenceName = "chat_messages_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chat_messages_generator")
    @Basic(optional = false)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    @JoinColumn(name = "chat_id", referencedColumnName = "id", nullable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ChatsEntity chatId;
    @Basic
    @Column(name = "text", nullable = true, length = 4096)
    private String text;
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AccountsEntity accountId;
    @Basic
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public ChatMessagesEntity() {

    }

    @PrePersist
    private void init() {
        createdAt = LocalDateTime.now();
    }


    public Long getId() {
        return id;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
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

}
