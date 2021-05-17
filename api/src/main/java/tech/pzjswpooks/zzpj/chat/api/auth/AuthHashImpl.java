package tech.pzjswpooks.zzpj.chat.api.auth;

import tech.pzjswpooks.zzpj.chat.api.utils.HashGenerator;

import javax.inject.Inject;
import javax.security.enterprise.identitystore.PasswordHash;
import java.util.Map;
import java.util.Objects;

public class AuthHashImpl implements PasswordHash {
    @Inject
    private HashGenerator hashGenerator;

    @Override
    public void initialize(Map<String, String> parameters) {
    }

    @Override
    public String generate(char[] password) {
        return hashGenerator.generateHash(new String(password));
    }

    @Override
    public boolean verify(char[] password, String hashedPassword) {
        return Objects.equals(hashGenerator.generateHash(new String(password)), hashedPassword);
    }
}
