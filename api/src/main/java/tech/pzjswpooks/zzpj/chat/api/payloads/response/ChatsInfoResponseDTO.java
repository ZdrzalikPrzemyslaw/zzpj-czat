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
        private final ChatUsersResponseDTO chatUsersResponseDTO;
        private final String name;
        private final UsersResponseDTO ownerData;

        public ChatInfoResponseDTO(ChatsEntity chatsEntity) {
            chatUsersResponseDTO = new ChatUsersResponseDTO(chatsEntity);
            name = chatsEntity.getName();
            ownerData = new UsersResponseDTO(chatsEntity.getOwner().getUserId());
        }

        public ChatUsersResponseDTO getChatUsersResponseDTO() {
            return chatUsersResponseDTO;
        }

        public String getName() {
            return name;
        }

        public UsersResponseDTO getOwnerData() {
            return ownerData;
        }

        public static class ChatUsersResponseDTO {
            private final List<UsersResponseDTO> usersResponseDTO;

            public ChatUsersResponseDTO(ChatsEntity chatsEntity) {
                usersResponseDTO = new ArrayList<>();
                chatsEntity.getChatUsers().forEach(chatUsersEntity -> usersResponseDTO.add(new UsersResponseDTO(chatUsersEntity.getAccountId().getUserId())));
            }

            public List<UsersResponseDTO> getUsersResponseDTO() {
                return usersResponseDTO;
            }
        }
    }
}
