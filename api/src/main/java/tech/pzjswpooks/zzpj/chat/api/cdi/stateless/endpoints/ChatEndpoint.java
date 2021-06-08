package tech.pzjswpooks.zzpj.chat.api.cdi.stateless.endpoints;

import tech.pzjswpooks.zzpj.chat.api.common.I18n;
import tech.pzjswpooks.zzpj.chat.api.ejb.managers.AccountsManager;
import tech.pzjswpooks.zzpj.chat.api.ejb.managers.ChatUsersManager;
import tech.pzjswpooks.zzpj.chat.api.ejb.managers.ChatUsersManagerImplementation;
import tech.pzjswpooks.zzpj.chat.api.ejb.managers.ChatsManager;
import tech.pzjswpooks.zzpj.chat.api.entities.AccountsEntity;
import tech.pzjswpooks.zzpj.chat.api.payloads.request.ChangeChatNameRequestDTO;
import tech.pzjswpooks.zzpj.chat.api.payloads.request.ChangeChatOwnerRequestDTO;
import tech.pzjswpooks.zzpj.chat.api.payloads.request.CreateChatRequestDto;
import tech.pzjswpooks.zzpj.chat.api.payloads.response.ChatsInfoResponseDTO;
import tech.pzjswpooks.zzpj.chat.api.payloads.response.CreateChatResponseDto;
import tech.pzjswpooks.zzpj.chat.api.payloads.response.MessageResponseDto;
import tech.pzjswpooks.zzpj.chat.api.utils.LoggedInAccountUtil;

import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Objects;

@Stateful
@Path("chat")
@DenyAll
public class ChatEndpoint {

    private final AccountsManager accountsManager;
    private final ChatsManager chatsManager;
    private final LoggedInAccountUtil loggedInAccountUtil;

    @Inject
    public ChatEndpoint(AccountsManager accountsManager,
                        ChatsManager chatsManager,
                        LoggedInAccountUtil loggedInAccountUtil) {
        this.accountsManager = accountsManager;
        this.chatsManager = chatsManager;
        this.loggedInAccountUtil = loggedInAccountUtil;
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

    @GET
    @RolesAllowed({I18n.USER, I18n.ADMIN})
    @Path("/get-all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllChatsCurrentUserBelongsTo() {
        try {
            return Response.status(Response.Status.OK).entity(new ChatsInfoResponseDTO(chatsManager.getChatsUserBelongsTo(loggedInAccountUtil.getLoggedInAccountLogin()))).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new MessageResponseDto(I18n.CHATS_DATA_FETCH_FAILED)).build();
        }

    }

    @PUT
    @RolesAllowed({I18n.USER, I18n.ADMIN})
    @Path("/change-owner/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response changeChatOwner(@NotNull @Valid ChangeChatOwnerRequestDTO changeChatOwnerRequestDTO, @PathParam("id") Long id) {
        try {
            chatsManager.changeOwner(changeChatOwnerRequestDTO, id);
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new MessageResponseDto(I18n.CHAT_OWNER_CHANGE_FAILED)).build();
        }
        return Response.status(Response.Status.OK).entity(new MessageResponseDto(I18n.CHAT_OWNER_CHANGE_SUCCESSFUL)).build();
    }


    @PUT
    @RolesAllowed({I18n.USER, I18n.ADMIN})
    @Path("/change-name/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response changeChatName(@NotNull @Valid ChangeChatNameRequestDTO changeChatNameRequestDTO, @PathParam("id") Long id) {
        try {
            chatsManager.changeName(changeChatNameRequestDTO, id);
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new MessageResponseDto(I18n.CHAT_OWNER_CHANGE_FAILED)).build();
        }
        return Response.status(Response.Status.OK).entity(new MessageResponseDto(I18n.CHAT_OWNER_CHANGE_SUCCESSFUL)).build();
    }


}
