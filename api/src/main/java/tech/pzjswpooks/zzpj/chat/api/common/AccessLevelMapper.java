package tech.pzjswpooks.zzpj.chat.api.common;

import org.apache.commons.lang3.NotImplementedException;
import tech.pzjswpooks.zzpj.chat.api.entities.AccessLevelsEntity;
import tech.pzjswpooks.zzpj.chat.api.entities.AdminData;
import tech.pzjswpooks.zzpj.chat.api.entities.UserData;

public class AccessLevelMapper {
    /**
     * Metoda przekształca nazwę poziomu dostępu na jego obiekt.
     *
     * @param level poziom dostępu do zamiany na obiekt
     * @return obiekt poziomu dostępu
     * @throws NotImplementedException gdy poziom dostepu nie istnieje
     */
    public static AccessLevelsEntity mapLevelNameToAccessLevel(String level) throws NotImplementedException {
        if (AccessLevels.ADMIN.getLevel().equals(level)) {
            return new AdminData();
        } else if (AccessLevels.USER.getLevel().equals(level)) {
            return new UserData();
        }
        // TODO: 24.04.2021 Zmienic na nasz wyjatek
        throw new NotImplementedException();
    }
}
