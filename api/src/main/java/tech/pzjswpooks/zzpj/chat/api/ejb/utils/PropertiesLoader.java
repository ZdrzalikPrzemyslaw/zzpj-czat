package tech.pzjswpooks.zzpj.chat.api.ejb.utils;

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

    private final String jwtSecret;
    private final Long jwtExpiration;

    private PropertiesLoader() {
        Properties prop = null;
        try {
            prop = new Properties();
            InputStream inputStream = PropertiesLoader.class.getClassLoader()
                    .getResourceAsStream("application.properties");
            if (inputStream != null) {
                prop.load(inputStream);
            }
        } catch (IOException e) {
            // TODO: 18.04.2021
        }
        jwtSecret = prop.getProperty("jwt.secret");
        jwtExpiration = Long.valueOf(prop.getProperty("jwt.expirationMs"));
    }

    /**
     * Pobiera pole confirmation jwt secret.
     *
     * @return confirmation jwt secret
     */
    public String getJwtSecret() {
        return jwtSecret;
    }

    /**
     * Pobiera pole confirmation jwt expiration.
     *
     * @return confirmation jwt expiration
     */
    public Long getJwtExpiration() {
        return jwtExpiration;
    }

}
