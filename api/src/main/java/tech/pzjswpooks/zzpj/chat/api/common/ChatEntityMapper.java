package tech.pzjswpooks.zzpj.chat.api.common;

import tech.pzjswpooks.zzpj.chat.api.entities.ChatsEntity;
import tech.pzjswpooks.zzpj.chat.api.entities.AccountsEntity;
import tech.pzjswpooks.zzpj.chat.api.payloads.request.CreateChatRequestDto;
import tech.pzjswpooks.zzpj.chat.api.payloads.request.RegistrationRequestDto;
import tech.pzjswpooks.zzpj.chat.api.utils.HashGenerator;

public class ChatEntityMapper {

    public static ChatsEntity mapCreationDtoToChat(CreateChatRequestDto r, AccountsEntity accountEntity) {
        return new ChatsEntity(accountEntity, r.getName());
    }
}
