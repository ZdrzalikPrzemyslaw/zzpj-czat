package tech.pzjswpooks.zzpj.chat.api.ejb.managers;

import tech.pzjswpooks.zzpj.chat.api.ejb.facades.AccessLevelsFacade;
import tech.pzjswpooks.zzpj.chat.api.entities.AccessLevelsEntity;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import java.util.List;

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

    @Override
    public List<AccessLevelsEntity> getLevelsForUsername(String username) {
        return accessLevelsFacade.findByUsername(username);
    }
}
