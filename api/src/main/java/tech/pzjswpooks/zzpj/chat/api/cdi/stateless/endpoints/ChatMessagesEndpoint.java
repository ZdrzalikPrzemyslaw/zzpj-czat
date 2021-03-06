package tech.pzjswpooks.zzpj.chat.api.cdi.stateless.endpoints;

import tech.pzjswpooks.zzpj.chat.api.common.I18n;
import tech.pzjswpooks.zzpj.chat.api.ejb.managers.ChatMessagesManager;
import tech.pzjswpooks.zzpj.chat.api.payloads.request.SendChatMessageRequestDTO;
import tech.pzjswpooks.zzpj.chat.api.payloads.response.MessageResponseDto;
import tech.pzjswpooks.zzpj.chat.api.utils.LogInterceptor;

import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Interceptors(LogInterceptor.class)
@Path("messages")
@Stateful
@DenyAll
public class ChatMessagesEndpoint {

    private final ChatMessagesManager chatMessagesManager;

    @Inject
    public ChatMessagesEndpoint(ChatMessagesManager chatMessagesManager) {
        this.chatMessagesManager = chatMessagesManager;
    }


    /**
     * Metoda służąca do wysyłania wiadomości.
     *
     * @param id   id czatu na który ma zostać wysłana wiadomość
     * @param text tekst wiadomości
     * @return Response
     */
    @PUT
    @Path("/send/{id}")
    @RolesAllowed({I18n.ADMIN, I18n.USER})
    @Produces({MediaType.APPLICATION_JSON})
    public Response sendMessage(@PathParam("id") Long id, SendChatMessageRequestDTO text) {
        try {
            chatMessagesManager.sendMessage(id, text.getMessage());
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new MessageResponseDto(I18n.MESSAGE_SEND_FAILED)).build();
        }
        return Response.status(Response.Status.OK).entity(new MessageResponseDto(I18n.MESSAGE_SEND_SUCCESSFULLY)).build();
    }

    @PUT
    @Path("/send_joke/{id}")
    @RolesAllowed({I18n.ADMIN, I18n.USER})
    @Produces({MediaType.APPLICATION_JSON})
    public Response sendJoke(@PathParam("id") Long id) {
        try {
            chatMessagesManager.sendJoke(id);
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new MessageResponseDto(I18n.MESSAGE_SEND_FAILED)).build();
        }
        return Response.status(Response.Status.OK).entity(new MessageResponseDto(I18n.MESSAGE_SEND_SUCCESSFULLY)).build();
    }

    @GET
    @Path("/get-all/{id}")
    @RolesAllowed({I18n.ADMIN, I18n.USER})
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll(@PathParam("id") Long id) {
        try {
            return Response.status(Response.Status.OK).entity(chatMessagesManager.getAll(id)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new MessageResponseDto(I18n.MESSAGE_GET_ALL_FAILED)).build();
        }
    }

}
