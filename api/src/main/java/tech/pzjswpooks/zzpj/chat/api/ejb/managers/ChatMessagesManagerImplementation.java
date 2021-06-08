package tech.pzjswpooks.zzpj.chat.api.ejb.managers;

import tech.pzjswpooks.zzpj.chat.api.ejb.facades.ChatMessagesEntityFacade;
import tech.pzjswpooks.zzpj.chat.api.ejb.facades.ChatsEntityFacade;
import tech.pzjswpooks.zzpj.chat.api.entities.ChatMessagesEntity;
import tech.pzjswpooks.zzpj.chat.api.entities.ChatsEntity;
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
public class ChatMessagesManagerImplementation extends AbstractManager implements ChatMessagesManager {

    private ChatMessagesEntityFacade chatMessagesEntityFacade;
    private ChatsEntityFacade chatsEntityFacade;
    private LoggedInAccountUtil loggedInAccountUtil;
    private AccountsManager accountsManager;

    @Inject
    public ChatMessagesManagerImplementation(ChatMessagesEntityFacade chatMessagesEntityFacade, ChatsEntityFacade chatsEntityFacade,
                                             LoggedInAccountUtil loggedInAccountUtil, AccountsManager accountsManager) {
        this.chatMessagesEntityFacade = chatMessagesEntityFacade;
        this.chatsEntityFacade = chatsEntityFacade;
        this.loggedInAccountUtil = loggedInAccountUtil;
        this.accountsManager = accountsManager;
    }

    public ChatMessagesManagerImplementation() {
    }


    @Override
    public void sendMessage(Long id, String text) {
        ChatsEntity chatsEntity = chatsEntityFacade.find(id);
        var accountByUsername = accountsManager.getAccountByUsername(loggedInAccountUtil.getLoggedInAccountLogin());
        ChatMessagesEntity chatMessagesEntity = new ChatMessagesEntity(chatsEntity,text,accountByUsername);
        chatMessagesEntityFacade.create(chatMessagesEntity);
    }
}
