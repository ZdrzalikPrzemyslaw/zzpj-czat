package tech.pzjswpooks.zzpj.chat.api.common;

import tech.pzjswpooks.zzpj.chat.api.entities.AccessLevelsEntity;
import tech.pzjswpooks.zzpj.chat.api.entities.AdminData;
import tech.pzjswpooks.zzpj.chat.api.entities.UserData;
import tech.pzjswpooks.zzpj.chat.api.exceptions.AppBaseException;

public class AccessLevelMapper {
    /**
     * Metoda przekształca nazwę poziomu dostępu na jego obiekt.
     *
     * @param level poziom dostępu do zamiany na obiekt
     * @return obiekt poziomu dostępu
     * @throws AppBaseException {@link AppBaseException#invalidAccessLevel()} gdy poziom dostepu nie istnieje
     */
    public static AccessLevelsEntity mapLevelNameToAccessLevel(String level) throws AppBaseException {
        if (AccessLevels.ADMIN.getLevel().equals(level)) {
            return new AdminData();
        } else if (AccessLevels.USER.getLevel().equals(level)) {
            return new UserData();
        }
        throw AppBaseException.invalidAccessLevel();
    }
}
