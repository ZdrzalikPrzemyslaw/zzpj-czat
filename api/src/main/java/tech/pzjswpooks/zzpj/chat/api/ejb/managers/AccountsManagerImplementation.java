package tech.pzjswpooks.zzpj.chat.api.ejb.managers;

import tech.pzjswpooks.zzpj.chat.api.ejb.facades.AccountEntityFacade;
import tech.pzjswpooks.zzpj.chat.api.entities.AccountsEntity;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

@Stateful
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class AccountsManagerImplementation implements AccountsManager {

    private AccountEntityFacade accountEntityFacade;

    @Inject
    public AccountsManagerImplementation(AccountEntityFacade accountEntityFacade) {
        this.accountEntityFacade = accountEntityFacade;
    }

    public AccountsManagerImplementation() {
    }

    @Override
    public void lockAccount(String username) {
        AccountsEntity account = accountEntityFacade.findByUsername(username);
        account.setEnabled(false);
    }

    @Override
    public void lockAccount(Long id) {
        AccountsEntity account = accountEntityFacade.find(id);
        account.setEnabled(false);
    }
}
