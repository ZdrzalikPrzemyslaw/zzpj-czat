package tech.pzjswpooks.zzpj.chat.api.ejb.managers;

import tech.pzjswpooks.zzpj.chat.api.ejb.facades.UsersEntityFacade;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

@Stateful
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class UsersManagerImplementation implements ChatMessagesManager {

    private UsersEntityFacade usersEntityFacade;

    @Inject
    public UsersManagerImplementation(UsersEntityFacade usersEntityFacade) {
        this.usersEntityFacade = usersEntityFacade;
    }

    public UsersManagerImplementation() {
    }

}
