package tech.pzjswpooks.zzpj.chat.api.ejb.managers;

import tech.pzjswpooks.zzpj.chat.api.entities.ChatsEntity;

import javax.ejb.Local;


@Local
public interface ChatsManager {

    void createChat(ChatsEntity chatsEntity);
}
