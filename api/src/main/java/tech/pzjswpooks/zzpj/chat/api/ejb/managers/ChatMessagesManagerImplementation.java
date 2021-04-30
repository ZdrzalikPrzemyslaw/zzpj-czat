package tech.pzjswpooks.zzpj.chat.api.ejb.managers;

import tech.pzjswpooks.zzpj.chat.api.ejb.facades.ChatMessagesEntityFacade;
import tech.pzjswpooks.zzpj.chat.api.entities.ChatMessagesEntity;
import tech.pzjswpooks.zzpj.chat.api.entities.UserData;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

@Stateful
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ChatMessagesManagerImplementation implements ChatMessagesManager {

    private ChatMessagesEntityFacade chatMessagesEntityFacade;

    @Inject
    public ChatMessagesManagerImplementation(ChatMessagesEntityFacade chatMessagesEntityFacade) {
        this.chatMessagesEntityFacade = chatMessagesEntityFacade;
    }

    public ChatMessagesManagerImplementation() {
    }

}
