package tech.pzjswpooks.zzpj.chat.api.ejb.managers;

import tech.pzjswpooks.zzpj.chat.api.entities.ChatsEntity;
import tech.pzjswpooks.zzpj.chat.api.payloads.request.CreateChatRequestDto;

import javax.ejb.Local;


@Local
public interface ChatsManager {

    void createChat(CreateChatRequestDto createChatRequestDto);
}
