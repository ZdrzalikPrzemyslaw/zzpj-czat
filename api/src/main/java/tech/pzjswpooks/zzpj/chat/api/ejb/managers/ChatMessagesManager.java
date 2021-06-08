package tech.pzjswpooks.zzpj.chat.api.ejb.managers;

import tech.pzjswpooks.zzpj.chat.api.entities.AccountsEntity;
import tech.pzjswpooks.zzpj.chat.api.entities.ChatMessagesEntity;

import javax.ejb.Local;

@Local
public interface ChatMessagesManager {

    void sendMessage(Long id, String text);

}
