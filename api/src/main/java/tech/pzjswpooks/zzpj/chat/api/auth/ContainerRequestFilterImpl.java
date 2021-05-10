package tech.pzjswpooks.zzpj.chat.api.auth;

import tech.pzjswpooks.zzpj.chat.api.ejb.managers.AccountsManager;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class ContainerRequestFilterImpl implements ContainerRequestFilter {

    private final AccountsManager accountsManager;

    @Inject
    public ContainerRequestFilterImpl(AccountsManager accountsManager) {
        this.accountsManager = accountsManager;
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

    //        SecurityContext securityContext = requestContext.getSecurityContext();
    //        String username = securityContext.getUserPrincipal().getName();

    }
}