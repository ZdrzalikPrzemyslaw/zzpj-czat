package tech.pzjswpooks.zzpj.chat.api.ejb.managers;

import com.mashape.unirest.http.exceptions.UnirestException;
import tech.pzjswpooks.zzpj.chat.api.entities.AccountsEntity;
import tech.pzjswpooks.zzpj.chat.api.entities.ChatMessagesEntity;

import javax.ejb.Local;

@Local
public interface ChatMessagesManager {

    void sendMessage(Long id, String text);

    void sendJoke(Long id) throws UnirestException;

}
