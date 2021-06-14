package tech.pzjswpooks.zzpj.chat.api.ejb.managers;

import tech.pzjswpooks.zzpj.chat.api.ejb.facades.AccessLevelsFacade;
import tech.pzjswpooks.zzpj.chat.api.entities.AccessLevelsEntity;
import tech.pzjswpooks.zzpj.chat.api.utils.LogInterceptor;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import java.util.List;

@Stateful
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors(LogInterceptor.class)
public class AccessLevelsManagerImplementation extends AbstractManager implements AccessLevelsManager {

    private AccessLevelsFacade accessLevelsFacade;

    @Inject
    public AccessLevelsManagerImplementation(AccessLevelsFacade accessLevelsFacade) {
        this.accessLevelsFacade = accessLevelsFacade;
    }

    @Override
    public List<AccessLevelsEntity> getLevelsForUsername(String username) {
        return accessLevelsFacade.findByUsername(username);
    }
}
