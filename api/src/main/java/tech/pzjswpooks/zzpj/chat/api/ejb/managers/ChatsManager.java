package tech.pzjswpooks.zzpj.chat.api.ejb.managers;

import tech.pzjswpooks.zzpj.chat.api.entities.AccountsEntity;
import tech.pzjswpooks.zzpj.chat.api.entities.ChatsEntity;
import tech.pzjswpooks.zzpj.chat.api.exceptions.AppBaseException;
import tech.pzjswpooks.zzpj.chat.api.payloads.request.ChangeChatOwnerRequestDTO;
import tech.pzjswpooks.zzpj.chat.api.payloads.request.CreateChatRequestDto;

import javax.ejb.Local;
import java.util.Collection;
import java.util.List;


@Local
public interface ChatsManager {

    void createChat(CreateChatRequestDto createChatRequestDto);

    Collection<ChatsEntity> getChatsUserBelongsTo(String username);

    void changeOwner(ChangeChatOwnerRequestDTO changeChatOwnerRequestDTO, Long id) throws AppBaseException;
}
