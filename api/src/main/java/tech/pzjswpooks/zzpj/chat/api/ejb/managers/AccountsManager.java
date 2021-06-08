package tech.pzjswpooks.zzpj.chat.api.ejb.managers;

import tech.pzjswpooks.zzpj.chat.api.entities.AccountsEntity;

import javax.ejb.Local;

@Local
public interface AccountsManager {
    void lockAccount(String username);

    void lockAccount(Long id);

    void unlockAccount(Long id);

    void unlockAccount(String username);

    void registerAccount(AccountsEntity accountsEntity);

    AccountsEntity getAccountByUsername(String username);
}
