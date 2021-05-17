package tech.pzjswpooks.zzpj.chat.api.ejb.managers;

import tech.pzjswpooks.zzpj.chat.api.ejb.facades.AccountEntityFacade;
import tech.pzjswpooks.zzpj.chat.api.entities.ChatsEntity;
import tech.pzjswpooks.zzpj.chat.api.ejb.facades.ChatsEntityFacade;
import tech.pzjswpooks.zzpj.chat.api.utils.LogInterceptor;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

@Stateful
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors(LogInterceptor.class)
public class ChatsManagerImplementation extends AbstractManager implements ChatsManager {

    private ChatsEntityFacade chatsEntityFacade;

    @Inject
    public ChatsManagerImplementation(ChatsEntityFacade chatsEntityFacade) {
        this.chatsEntityFacade = chatsEntityFacade;
    }

    public ChatsManagerImplementation() {

    }
}
