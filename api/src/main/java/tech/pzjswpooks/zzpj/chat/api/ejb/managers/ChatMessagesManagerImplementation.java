package tech.pzjswpooks.zzpj.chat.api.ejb.managers;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import tech.pzjswpooks.zzpj.chat.api.ejb.facades.ChatMessagesEntityFacade;
import tech.pzjswpooks.zzpj.chat.api.ejb.facades.ChatsEntityFacade;
import tech.pzjswpooks.zzpj.chat.api.entities.ChatMessagesEntity;
import tech.pzjswpooks.zzpj.chat.api.entities.ChatsEntity;
import tech.pzjswpooks.zzpj.chat.api.payloads.response.MessagesWithDataResponseDTO;
import tech.pzjswpooks.zzpj.chat.api.utils.LogInterceptor;
import tech.pzjswpooks.zzpj.chat.api.utils.LoggedInAccountUtil;
import tech.pzjswpooks.zzpj.chat.api.utils.PropertiesLoader;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

@Stateful
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors(LogInterceptor.class)
public class ChatMessagesManagerImplementation extends AbstractManager implements ChatMessagesManager {

    private final ChatMessagesEntityFacade chatMessagesEntityFacade;
    private final ChatsEntityFacade chatsEntityFacade;
    private final LoggedInAccountUtil loggedInAccountUtil;
    private final AccountsManager accountsManager;
    private final PropertiesLoader propertiesLoader;

    @Inject
    public ChatMessagesManagerImplementation(ChatMessagesEntityFacade chatMessagesEntityFacade, ChatsEntityFacade chatsEntityFacade,
                                             LoggedInAccountUtil loggedInAccountUtil, AccountsManager accountsManager, PropertiesLoader propertiesLoader) {
        this.chatMessagesEntityFacade = chatMessagesEntityFacade;
        this.chatsEntityFacade = chatsEntityFacade;
        this.loggedInAccountUtil = loggedInAccountUtil;
        this.accountsManager = accountsManager;
        this.propertiesLoader = propertiesLoader;
    }

    @Override
    public void sendMessage(Long id, String text) {
        ChatsEntity chatsEntity = chatsEntityFacade.find(id);
        var accountByUsername = accountsManager.getAccountByUsername(loggedInAccountUtil.getLoggedInAccountLogin());
        ChatMessagesEntity chatMessagesEntity = new ChatMessagesEntity(chatsEntity, text, accountByUsername);
        chatMessagesEntityFacade.create(chatMessagesEntity);
    }

    @Override
    public void sendJoke(Long id) throws UnirestException {
        ChatsEntity chatsEntity = chatsEntityFacade.find(id);
        var accountByUsername = accountsManager.getAccountByUsername(loggedInAccountUtil.getLoggedInAccountLogin());

        HttpResponse<String> response = Unirest.get("https://matchilling-chuck-norris-jokes-v1.p.rapidapi.com/jokes/random")
                .header("accept", "application/json")
                .header("x-rapidapi-key", propertiesLoader.getApiKey())
                .header("x-rapidapi-host", "matchilling-chuck-norris-jokes-v1.p.rapidapi.com")
                .asString();

        JSONObject obj = new JSONObject(response.getBody());
        String joke = obj.getString("value");

        ChatMessagesEntity chatMessagesEntity = new ChatMessagesEntity(chatsEntity, joke, accountByUsername);
        chatMessagesEntityFacade.create(chatMessagesEntity);
    }

    @Override
    public MessagesWithDataResponseDTO getAll(Long id) {
        return new MessagesWithDataResponseDTO(chatMessagesEntityFacade.getAllForChat(chatsEntityFacade.find(id)));
    }
}
