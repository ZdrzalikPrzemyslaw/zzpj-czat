package tech.pzjswpooks.zzpj.chat.api.ejb.managers;

import tech.pzjswpooks.zzpj.chat.api.ejb.facades.ChatUsersFacade;
import tech.pzjswpooks.zzpj.chat.api.utils.LogInterceptor;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

@Stateful
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors(LogInterceptor.class)
public class ChatUsersManagerImplementation extends AbstractManager implements ChatUsersManager {

    private ChatUsersFacade chatUsersFacade;

    @Inject
    public ChatUsersManagerImplementation(ChatUsersFacade chatUsersFacade) {
        this.chatUsersFacade = chatUsersFacade;
    }

    public ChatUsersManagerImplementation() {
    }
}
