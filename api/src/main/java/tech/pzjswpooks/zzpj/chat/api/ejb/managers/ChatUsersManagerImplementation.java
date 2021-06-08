package tech.pzjswpooks.zzpj.chat.api.ejb.managers;

import tech.pzjswpooks.zzpj.chat.api.ejb.facades.ChatUsersFacade;
import tech.pzjswpooks.zzpj.chat.api.ejb.facades.ChatsEntityFacade;
import tech.pzjswpooks.zzpj.chat.api.entities.ChatUsersEntity;
import tech.pzjswpooks.zzpj.chat.api.entities.ChatsEntity;
import tech.pzjswpooks.zzpj.chat.api.exceptions.AppBaseException;
import tech.pzjswpooks.zzpj.chat.api.payloads.request.AddUserToChatRequestDTO;
import tech.pzjswpooks.zzpj.chat.api.payloads.request.DeleteUserFromChatRequestDTO;
import tech.pzjswpooks.zzpj.chat.api.utils.LogInterceptor;
import tech.pzjswpooks.zzpj.chat.api.utils.LoggedInAccountUtil;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

@Stateful
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors(LogInterceptor.class)
public class ChatUsersManagerImplementation extends AbstractManager implements ChatUsersManager {

    private ChatsEntityFacade chatsEntityFacade;

    private LoggedInAccountUtil loggedInAccountUtil;

    private ChatUsersFacade chatsUsersFacade;

    private AccountsManager accountsManager;

    @Inject
    public ChatUsersManagerImplementation(ChatsEntityFacade chatsEntityFacade, LoggedInAccountUtil loggedInAccountUtil, ChatUsersFacade chatsUsersFacade, AccountsManager accountsManager) {
        this.chatsEntityFacade = chatsEntityFacade;
        this.loggedInAccountUtil = loggedInAccountUtil;
        this.chatsUsersFacade = chatsUsersFacade;
        this.accountsManager = accountsManager;
    }

    public ChatUsersManagerImplementation() {
    }

    @Override
    public void addUser(AddUserToChatRequestDTO addUserToChatRequestDTO, Long id) throws AppBaseException {
        var accountByUsername = accountsManager.getAccountByUsername(loggedInAccountUtil.getLoggedInAccountLogin());
        var newUser = accountsManager.getAccountByUsername(addUserToChatRequestDTO.getUsername());
        try {
            ChatsEntity chatsEntity = chatsEntityFacade.getChatByOwnerAndId(accountByUsername.getUsername(), id);
            ChatUsersEntity chatUsersEntity = new ChatUsersEntity(chatsEntity, newUser);
            chatsUsersFacade.create(chatUsersEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw AppBaseException.noResultsError();
        }
    }

    @Override
    public void deleteUser(DeleteUserFromChatRequestDTO deleteUserFromChatRequestDTO, Long id) throws AppBaseException {
        var accountByUsername = accountsManager.getAccountByUsername(loggedInAccountUtil.getLoggedInAccountLogin());
        var user = accountsManager.getAccountByUsername(deleteUserFromChatRequestDTO.getUsername());
        try {
            ChatsEntity chatsEntity = chatsEntityFacade.getChatByOwnerAndId(accountByUsername.getUsername(), id);
            ChatUsersEntity chatUsersEntity = new ChatUsersEntity(chatsEntity, user);
            chatsUsersFacade.remove(chatUsersEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw AppBaseException.noResultsError();
        }
    }

}
