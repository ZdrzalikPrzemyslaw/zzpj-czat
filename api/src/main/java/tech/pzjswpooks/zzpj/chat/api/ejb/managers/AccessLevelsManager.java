package tech.pzjswpooks.zzpj.chat.api.ejb.managers;

import tech.pzjswpooks.zzpj.chat.api.entities.AccessLevelsEntity;

import javax.ejb.Local;
import java.util.List;

@Local
public interface AccessLevelsManager {

    List<AccessLevelsEntity> getLevelsForUsername(String username);
}
