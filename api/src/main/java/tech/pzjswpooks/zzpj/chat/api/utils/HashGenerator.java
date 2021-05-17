package tech.pzjswpooks.zzpj.chat.api.utils;

import javax.ejb.Local;

@Local
public interface HashGenerator {
    /**
     * Generator skrótu hash.
     *
     * @param input dane wejściowe
     * @return string
     */
    public String generateHash(String input);
}
