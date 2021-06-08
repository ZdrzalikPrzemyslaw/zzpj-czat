package tech.pzjswpooks.zzpj.chat.api.cdi.stateless.endpoints;

import tech.pzjswpooks.zzpj.chat.api.common.I18n;
import tech.pzjswpooks.zzpj.chat.api.ejb.managers.AccountsManager;
import tech.pzjswpooks.zzpj.chat.api.ejb.managers.ChatsManager;
import tech.pzjswpooks.zzpj.chat.api.entities.AccountsEntity;
import tech.pzjswpooks.zzpj.chat.api.payloads.request.CreateChatRequestDto;
import tech.pzjswpooks.zzpj.chat.api.payloads.response.CreateChatResponseDto;
import tech.pzjswpooks.zzpj.chat.api.payloads.response.MessageResponseDto;

import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Objects;

@Stateful
@Path("chat")
@DenyAll
public class ChatEndpoint {

    private final AccountsManager accountsManager;
    private final ChatsManager chatsManager;

    @Inject
    public ChatEndpoint(AccountsManager accountsManager, ChatsManager chatsManager) {
        this.accountsManager = accountsManager;
        this.chatsManager = chatsManager;
    }

    @POST
    @RolesAllowed({I18n.USER, I18n.ADMIN})
    @Path("/create")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response createChat(@NotNull @Valid CreateChatRequestDto createChatRequestDto) {
        try {
            chatsManager.createChat(createChatRequestDto);
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new MessageResponseDto(I18n.CHAT_CREATION_FAILED)).build();
        }
        return Response.status(Response.Status.OK).entity(new MessageResponseDto(I18n.CHAT_CREATION_SUCCESSFUL)).build();
    }

}
