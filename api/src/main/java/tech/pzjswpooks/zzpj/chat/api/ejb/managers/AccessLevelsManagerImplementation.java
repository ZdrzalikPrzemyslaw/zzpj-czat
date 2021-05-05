package tech.pzjswpooks.zzpj.chat.api.ejb.managers;

import tech.pzjswpooks.zzpj.chat.api.ejb.facades.AccessLevelsFacade;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

@Stateful
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class AccessLevelsManagerImplementation implements AccessLevelsManager {
    private AccessLevelsFacade accessLevelsFacade;

    @Inject
    public AccessLevelsManagerImplementation(AccessLevelsFacade accessLevelsFacade) {
        this.accessLevelsFacade = accessLevelsFacade;
    }

    public AccessLevelsManagerImplementation() {
    }
}
