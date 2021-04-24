package tech.pzjswpooks.zzpj.chat.api.ejb.managers;

import javax.ejb.Local;

@Local
public interface AccountsManager {
    void lockAccount(String username);

    void lockAccount(Long id);
}
