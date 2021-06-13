package tech.pzjswpooks.zzpj.chat.api.payloads.response;

import tech.pzjswpooks.zzpj.chat.api.entities.ChatMessagesEntity;

import java.time.LocalDateTime;

public class MessageWithDataResponseDTO {

    private String text;
    private Long accountId;
    private LocalDateTime createdAt;

    public MessageWithDataResponseDTO(ChatMessagesEntity chatMessagesEntity) {
        this.text = chatMessagesEntity.getText();
        this.accountId = chatMessagesEntity.getAccountId();
        this.createdAt = chatMessagesEntity.getCreatedAt();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
