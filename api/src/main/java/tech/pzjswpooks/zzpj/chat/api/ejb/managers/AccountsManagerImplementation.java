package tech.pzjswpooks.zzpj.chat.api.ejb.managers;

import tech.pzjswpooks.zzpj.chat.api.common.AccessLevelMapper;
import tech.pzjswpooks.zzpj.chat.api.ejb.facades.AccountEntityFacade;
import tech.pzjswpooks.zzpj.chat.api.entities.AccessLevelsEntity;
import tech.pzjswpooks.zzpj.chat.api.entities.AccountsEntity;
import tech.pzjswpooks.zzpj.chat.api.entities.UserData;
import tech.pzjswpooks.zzpj.chat.api.exceptions.AppBaseException;
import tech.pzjswpooks.zzpj.chat.api.utils.LogInterceptor;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.core.Context;
import java.util.function.Consumer;

@Stateful
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors(LogInterceptor.class)
public class AccountsManagerImplementation extends AbstractManager implements AccountsManager {

    private AccountEntityFacade accountEntityFacade;
    private SecurityContext securityContext;

    @Inject
    public AccountsManagerImplementation(AccountEntityFacade accountEntityFacade, SecurityContext securityContext) {
        this.accountEntityFacade = accountEntityFacade;
        this.securityContext = securityContext;
    }

    public AccountsManagerImplementation() {
    }

    @Override
    public void lockAccount(String username) {
        AccountsEntity account = accountEntityFacade.findByUsername(username);
        account.setEnabled(false);
        accountEntityFacade.edit(account);
    }

    @Override
    public void lockAccount(Long id) {
        AccountsEntity account = accountEntityFacade.find(id);
        account.setEnabled(false);
        accountEntityFacade.edit(account);
    }

    @Override
    public void unlockAccount(Long id) {
        AccountsEntity account = accountEntityFacade.find(id);
        account.setEnabled(true);
        accountEntityFacade.edit(account);
    }

    @Override
    public void unlockAccount(String username) {
        AccountsEntity account = accountEntityFacade.findByUsername(username);
        account.setEnabled(true);
        accountEntityFacade.edit(account);
    }

    @Override
    public void registerAccount(AccountsEntity accountsEntity) {
        UserData userData = new UserData();
        userData.setAccountId(accountsEntity);
        accountsEntity.addAccessLevels(userData);
        accountEntityFacade.create(accountsEntity);
    }

    @Override
    public void addAccessLevel(String username, String accessLevel) throws AppBaseException {
        AccountsEntity accountsEntity = getAccountByUsername(username);
        accountsEntity.getAccessLevels().forEach(accessLevelsEntity -> {
            if (accessLevelsEntity.getLevel().equals(accessLevel)) {
                accessLevelsEntity.setEnabled(true);
                accountEntityFacade.edit(accountsEntity);
                return;
            }
        });
        AccessLevelsEntity accessLevelsEntity = AccessLevelMapper.mapLevelNameToAccessLevel(accessLevel);
        accessLevelsEntity.setAccountId(accountsEntity);
        accountsEntity.addAccessLevels(accessLevelsEntity);
        accountEntityFacade.edit(accountsEntity);
    }

    // TODO: 10.05.2021 Wy jąt ki
    @Override
    public AccountsEntity getAccountByUsername(String username) {
        return accountEntityFacade.findByUsername(username);
    }
}
