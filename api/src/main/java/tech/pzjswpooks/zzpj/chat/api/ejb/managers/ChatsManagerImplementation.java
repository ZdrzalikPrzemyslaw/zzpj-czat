package tech.pzjswpooks.zzpj.chat.api.ejb.managers;

import tech.pzjswpooks.zzpj.chat.api.ejb.facades.ChatUsersFacade;
import tech.pzjswpooks.zzpj.chat.api.ejb.facades.ChatsEntityFacade;
import tech.pzjswpooks.zzpj.chat.api.entities.ChatUsersEntity;
import tech.pzjswpooks.zzpj.chat.api.entities.ChatsEntity;
import tech.pzjswpooks.zzpj.chat.api.exceptions.AppBaseException;
import tech.pzjswpooks.zzpj.chat.api.payloads.request.ChangeChatNameRequestDTO;
import tech.pzjswpooks.zzpj.chat.api.payloads.request.ChangeChatOwnerRequestDTO;
import tech.pzjswpooks.zzpj.chat.api.payloads.request.CreateChatRequestDto;
import tech.pzjswpooks.zzpj.chat.api.utils.LogInterceptor;
import tech.pzjswpooks.zzpj.chat.api.utils.LoggedInAccountUtil;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import java.util.Collection;

@Stateful
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors(LogInterceptor.class)
public class ChatsManagerImplementation extends AbstractManager implements ChatsManager {

    private ChatsEntityFacade chatsEntityFacade;

    private LoggedInAccountUtil loggedInAccountUtil;

    private ChatUsersFacade chatsUsersFacade;

    private AccountsManager accountsManager;

    @Inject
    public ChatsManagerImplementation(ChatsEntityFacade chatsEntityFacade,
                                      AccountsManager accountsManager,
                                      LoggedInAccountUtil loggedInAccountUtil,
                                      ChatUsersFacade chatsUsersFacade) {
        this.chatsEntityFacade = chatsEntityFacade;
        this.accountsManager = accountsManager;
        this.loggedInAccountUtil = loggedInAccountUtil;
        this.chatsUsersFacade = chatsUsersFacade;
    }


    @Override
    public void createChat(CreateChatRequestDto createChatRequestDto) {
        var accountByUsername = accountsManager.getAccountByUsername(loggedInAccountUtil.getLoggedInAccountLogin());
        ChatsEntity chatsEntity = new ChatsEntity(accountByUsername, createChatRequestDto.getName());
        ChatUsersEntity chatUsersEntity = new ChatUsersEntity(chatsEntity, accountByUsername);
        chatsEntityFacade.create(chatsEntity);
        chatsUsersFacade.create(chatUsersEntity);
    }

    @Override
    public ChatsEntity findById(Long id) {
        return chatsEntityFacade.find(id);
    }

    @Override
    public Collection<ChatsEntity> getChatsUserBelongsTo(String username) {
        return chatsEntityFacade.getChatsByUsernameBelongsTo(username);
    }

    @Override
    public void changeOwner(ChangeChatOwnerRequestDTO changeChatOwnerRequestDTO, Long id) throws AppBaseException {
        var accountByUsername = accountsManager.getAccountByUsername(loggedInAccountUtil.getLoggedInAccountLogin());
        var newOwner = accountsManager.getAccountByUsername(changeChatOwnerRequestDTO.getUsername());
        try {
            ChatsEntity chatsEntity = chatsEntityFacade.getChatByOwnerAndId(accountByUsername.getUsername(), id);
            chatsEntity.setOwner(newOwner);
            chatsEntityFacade.edit(chatsEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw AppBaseException.noResultsError();
        }


    }

    @Override
    public void changeName(ChangeChatNameRequestDTO changeChatNameRequestDTO, Long id) throws AppBaseException {
        var accountByUsername = accountsManager.getAccountByUsername(loggedInAccountUtil.getLoggedInAccountLogin());
        try {
            ChatsEntity chatsEntity = chatsEntityFacade.getChatByOwnerAndId(accountByUsername.getUsername(), id);
            chatsEntity.setName(changeChatNameRequestDTO.getName());
            chatsEntityFacade.edit(chatsEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw AppBaseException.noResultsError();
        }


    }

}
