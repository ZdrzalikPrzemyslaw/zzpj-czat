package tech.pzjswpooks.zzpj.chat.api.auth;

import tech.pzjswpooks.zzpj.chat.api.ejb.managers.AccessLevelsManager;
import tech.pzjswpooks.zzpj.chat.api.ejb.managers.AccountsManager;
import tech.pzjswpooks.zzpj.chat.api.entities.AccessLevelsEntity;
import tech.pzjswpooks.zzpj.chat.api.entities.AccountsEntity;
import tech.pzjswpooks.zzpj.chat.api.security.JwtUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.security.enterprise.identitystore.IdentityStoreHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.ext.Provider;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequestScoped
@Default
public class JWTHttpAuthMechanism implements HttpAuthenticationMechanism {

    @Inject
    private JwtUtils jwtUtils;

    @Inject
    private AccountsManager accountsManager;

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest req, HttpServletResponse res, HttpMessageContext msgContext) {
        try {
            String jwt = parseJwt(req);
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String username = jwtUtils.getUserNameFromJwtToken(jwt);
                AccountsEntity accountsEntity = accountsManager.getAccountByUsername(username);
                if (accountsEntity.getEnabled()) {
                    Set<AccessLevelsEntity> levelsForUsername = accountsEntity.getAccessLevels();
                    Set<String> levels = new HashSet<>();
                    for (var i : levelsForUsername) {
                        if (i.getEnabled()) {
                            levels.add(i.getLevel());
                        }
                    }
                    return msgContext.notifyContainerAboutLogin(username, levels);
                }
            } if (!msgContext.isProtected()) {
                return msgContext.doNothing();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msgContext.responseUnauthorized();
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (headerAuth != null && headerAuth.length() > 0 && !headerAuth.isBlank() && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }

        return null;
    }
}