package tech.pzjswpooks.zzpj.chat.api.payloads.response;

import tech.pzjswpooks.zzpj.chat.api.entities.ChatMessagesEntity;

import javax.validation.constraints.Null;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MessagesWithDataResponseDTO {

    private final List<MessageWithDataResponseDTO> messages;

    public MessagesWithDataResponseDTO(List<ChatMessagesEntity> chatMessagesEntities) {
        messages = new ArrayList<>();
        chatMessagesEntities.forEach(message -> messages.add(new MessageWithDataResponseDTO(message)));
    }

    public List<MessageWithDataResponseDTO> getMessages() {
        return messages;
    }

    public static class MessageWithDataResponseDTO {
        @Null
        private final ChatUserResponseDTO userData;
        private final String text;
        private final LocalDateTime createdAt;
        private final Long messageId;
        private final Long chatId;

        public MessageWithDataResponseDTO(ChatMessagesEntity chatMessagesEntity) {
            this.userData = new ChatUserResponseDTO(chatMessagesEntity);
            this.text = chatMessagesEntity.getText();
            this.createdAt = chatMessagesEntity.getCreatedAt();
            this.messageId = chatMessagesEntity.getId();
            this.chatId = chatMessagesEntity.getChatId();
        }

        public ChatUserResponseDTO getUserData() {
            return userData;
        }

        public String getText() {
            return text;
        }

        public LocalDateTime getCreatedAt() {
            return createdAt;
        }

        public Long getMessageId() {
            return messageId;
        }

        public Long getChatId() {
            return chatId;
        }

        public static class ChatUserResponseDTO {
            private final UsersResponseDTO user;

            public ChatUserResponseDTO(ChatMessagesEntity chatMessagesEntity) {
                user = new UsersResponseDTO(chatMessagesEntity.getAccount().getUserId());
            }

            public UsersResponseDTO getUser() {
                return user;
            }
        }
    }
}
