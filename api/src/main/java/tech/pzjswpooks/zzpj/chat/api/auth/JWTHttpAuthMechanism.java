package tech.pzjswpooks.zzpj.chat.api.auth;

import tech.pzjswpooks.zzpj.chat.api.ejb.managers.AccountsManager;
import tech.pzjswpooks.zzpj.chat.api.entities.AccessLevelsEntity;
import tech.pzjswpooks.zzpj.chat.api.entities.AccountsEntity;
import tech.pzjswpooks.zzpj.chat.api.security.JwtUtils;
import tech.pzjswpooks.zzpj.chat.api.utils.PropertiesLoader;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

@RequestScoped
@Default
public class JWTHttpAuthMechanism implements HttpAuthenticationMechanism {

    @Inject
    private JwtUtils jwtUtils;

    @Inject
    private AccountsManager accountsManager;

    @Inject
    private PropertiesLoader propertiesLoader;

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
            }
            if (!msgContext.isProtected()) {
                return msgContext.doNothing();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Unauthenticated shows as anon
        return msgContext.notifyContainerAboutLogin(propertiesLoader.getAnonymousUserName(), new HashSet<>());
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (headerAuth != null && headerAuth.length() > 0 && !headerAuth.isBlank() && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }

        return null;
    }
}