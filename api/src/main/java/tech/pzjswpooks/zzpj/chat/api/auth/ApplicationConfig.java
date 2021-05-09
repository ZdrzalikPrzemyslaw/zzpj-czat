package tech.pzjswpooks.zzpj.chat.api.auth;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;

@DatabaseIdentityStoreDefinition(
        callerQuery = "#{'select password from accounts where username = ?'}",
        groupsQuery = "select al.level from access_levels al, accounts a  where a.username = ? and al.account_id = a.id",
        hashAlgorithm = AuthHashImpl.class
)

@ApplicationScoped
@Named
public class ApplicationConfig {



}