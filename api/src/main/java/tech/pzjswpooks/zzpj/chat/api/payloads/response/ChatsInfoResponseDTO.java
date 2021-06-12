package tech.pzjswpooks.zzpj.chat.api.payloads.response;


import tech.pzjswpooks.zzpj.chat.api.entities.ChatsEntity;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ChatsInfoResponseDTO {
    private final List<ChatInfoResponseDTO> chats;

    public ChatsInfoResponseDTO(Collection<ChatsEntity> chatsEntities) {
        chats = new ArrayList<>();
        chatsEntities.forEach(chatsEntity -> chats.add(new ChatInfoResponseDTO(chatsEntity)));
    }

    public List<ChatInfoResponseDTO> getChats() {
        return chats;
    }

    public static class ChatInfoResponseDTO {
        @Null
        private final ChatUsersResponseDTO usersData;
        private final String name;
        private final UsersResponseDTO ownerData;
        private final Long chatId;

        public ChatInfoResponseDTO(ChatsEntity chatsEntity) {
            usersData = new ChatUsersResponseDTO(chatsEntity);
            name = chatsEntity.getName();
            ownerData = new UsersResponseDTO(chatsEntity.getOwner().getUserId());
            chatId = chatsEntity.getId();
        }

        public Long getChatId() {
            return chatId;
        }

        public ChatUsersResponseDTO getUsersData() {
            return usersData;
        }

        public String getName() {
            return name;
        }

        public UsersResponseDTO getOwnerData() {
            return ownerData;
        }

        public static class ChatUsersResponseDTO {
            private final List<UsersResponseDTO> user;

            public ChatUsersResponseDTO(ChatsEntity chatsEntity) {
                user = new ArrayList<>();
                chatsEntity.getChatUsers().forEach(chatUsersEntity -> user.add(new UsersResponseDTO(chatUsersEntity.getAccountId().getUserId())));
            }

            public List<UsersResponseDTO> getUser() {
                return user;
            }
        }
    }
}
