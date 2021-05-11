package tech.pzjswpooks.zzpj.chat.api.ejb.managers;

import tech.pzjswpooks.zzpj.chat.api.entities.AccessLevelsEntity;

import java.util.List;

public interface AccessLevelsManager {

    List<AccessLevelsEntity> getLevelsForUsername(String username);
}
