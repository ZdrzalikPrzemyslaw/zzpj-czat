package tech.pzjswpooks.zzpj.chat.api.ejb.managers;

import tech.pzjswpooks.zzpj.chat.api.ejb.facades.ChatUsersFacade;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

@Stateful
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ChatUsersManagerImplementation implements ChatUsersManager {

    private ChatUsersFacade chatUsersFacade;

    @Inject
    public ChatUsersManagerImplementation(ChatUsersFacade chatUsersFacade) {
        this.chatUsersFacade = chatUsersFacade;
    }

    public ChatUsersManagerImplementation() {
    }
}
