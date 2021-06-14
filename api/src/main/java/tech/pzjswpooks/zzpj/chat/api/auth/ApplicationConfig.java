package tech.pzjswpooks.zzpj.chat.api.auth;

import tech.pzjswpooks.zzpj.chat.api.common.I18n;

import javax.annotation.security.DeclareRoles;
import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;

@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "java:app/jdbc/zzpjadmin",
        callerQuery = "#{'select password from accounts where username = ? and enabled = true'}",
        groupsQuery = "select al.level from access_levels al, accounts a  where a.username = ? and al.account_id = a.id and a.enabled = true and al.enabled = true",
        hashAlgorithm = AuthHashImpl.class
)
@DeclareRoles({I18n.USER, I18n.ADMIN})
@ApplicationScoped
public class ApplicationConfig {
}