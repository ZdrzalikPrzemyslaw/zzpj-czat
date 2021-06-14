package tech.pzjswpooks.zzpj.chat.api.auth;

import tech.pzjswpooks.zzpj.chat.api.ejb.managers.AccountsManager;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class ContainerRequestFilterImpl implements ContainerRequestFilter {

    private final AccountsManager accountsManager;

    @Inject
    public ContainerRequestFilterImpl(AccountsManager accountsManager) {
        this.accountsManager = accountsManager;
    }

    @Override
    public void filter(ContainerRequestContext requestContext) {
    }
}