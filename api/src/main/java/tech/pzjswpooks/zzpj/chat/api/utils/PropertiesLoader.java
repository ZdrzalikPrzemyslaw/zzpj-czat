package tech.pzjswpooks.zzpj.chat.api.utils;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Typ Properties loader.
 */
@Startup
@Singleton
public class PropertiesLoader {

    private String confirmationJwtSecret;
    private String anonymousUserName;
    private Long confirmationJwtExpiration;

    public PropertiesLoader() {
    }

    /**
     * Pobiera pole confirmation jwt secret.
     *
     * @return confirmation jwt secret
     */
    public String getConfirmationJwtSecret() {
        return confirmationJwtSecret;
    }

    /**
     * Pobiera pole confirmation jwt expiration.
     *
     * @return confirmation jwt expiration
     */
    public Long getConfirmationJwtExpiration() {
        return confirmationJwtExpiration;
    }

    public String getAnonymousUserName() {
        return anonymousUserName;
    }


    @PostConstruct
    public void loadProperties() {
        Properties prop = null;
        try {
            prop = new Properties();
            InputStream inputStream = PropertiesLoader.class.getClassLoader()
                    .getResourceAsStream("application.properties");
            if (inputStream != null) {
                prop.load(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        confirmationJwtSecret = prop.getProperty("confirmation.jwt.secret");
        confirmationJwtExpiration = Long.valueOf(prop.getProperty("confirmation.jwt.expirationMs"));
        anonymousUserName = prop.getProperty("anonymous.user.name");
    }
}
