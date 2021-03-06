package tech.pzjswpooks.zzpj.chat.api.ejb.managers;

import tech.pzjswpooks.zzpj.chat.api.entities.ChatsEntity;
import tech.pzjswpooks.zzpj.chat.api.exceptions.AppBaseException;
import tech.pzjswpooks.zzpj.chat.api.payloads.request.ChangeChatNameRequestDTO;
import tech.pzjswpooks.zzpj.chat.api.payloads.request.ChangeChatOwnerRequestDTO;
import tech.pzjswpooks.zzpj.chat.api.payloads.request.CreateChatRequestDto;

import javax.ejb.Local;
import java.util.Collection;


@Local
public interface ChatsManager {

    void createChat(CreateChatRequestDto createChatRequestDto);

    ChatsEntity findById(Long id);

    Collection<ChatsEntity> getChatsUserBelongsTo(String username);

    void changeOwner(ChangeChatOwnerRequestDTO changeChatOwnerRequestDTO, Long id) throws AppBaseException;

    void changeName(ChangeChatNameRequestDTO changeChatNameRequestDTO, Long id) throws AppBaseException;
}
