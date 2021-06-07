package tech.pzjswpooks.zzpj.chat.api.cdi.stateless.endpoints;

import tech.pzjswpooks.zzpj.chat.api.ejb.managers.AccountsManager;

import javax.inject.Inject;
import javax.ws.rs.Path;

@Path("chat")
public class ChatEndpoint {

    private final AccountsManager accountsManager;

    @Inject
    public ChatEndpoint(AccountsManager accountsManager) {
        this.accountsManager = accountsManager;
    }

}
