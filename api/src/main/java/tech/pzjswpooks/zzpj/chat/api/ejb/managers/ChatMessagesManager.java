package tech.pzjswpooks.zzpj.chat.api.ejb.managers;

import com.mashape.unirest.http.exceptions.UnirestException;
import tech.pzjswpooks.zzpj.chat.api.entities.AccountsEntity;
import tech.pzjswpooks.zzpj.chat.api.entities.ChatMessagesEntity;
import tech.pzjswpooks.zzpj.chat.api.payloads.response.MessageWithDataResponseDTO;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ChatMessagesManager {

    void sendMessage(Long id, String text);

    void sendJoke(Long id) throws UnirestException;

    List<MessageWithDataResponseDTO> getAll(Long id);

}
