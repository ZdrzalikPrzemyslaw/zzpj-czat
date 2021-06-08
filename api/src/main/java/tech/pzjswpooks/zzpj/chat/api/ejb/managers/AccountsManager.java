package tech.pzjswpooks.zzpj.chat.api.ejb.managers;

import tech.pzjswpooks.zzpj.chat.api.entities.AccountsEntity;
import tech.pzjswpooks.zzpj.chat.api.exceptions.AppBaseException;

import javax.ejb.Local;

@Local
public interface AccountsManager {
    void lockAccount(String username);

    void lockAccount(Long id);

    void unlockAccount(Long id);

    void unlockAccount(String username);

    void registerAccount(AccountsEntity accountsEntity);

    void addAccessLevel(String username, String accessLevel) throws AppBaseException;

    AccountsEntity getAccountByUsername(String username);
}
