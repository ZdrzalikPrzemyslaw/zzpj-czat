package tech.pzjswpooks.zzpj.chat.api.cdi.stateless.endpoints;

import tech.pzjswpooks.zzpj.chat.api.ejb.managers.AccountsManager;
import tech.pzjswpooks.zzpj.chat.api.entities.AccountsEntity;
import tech.pzjswpooks.zzpj.chat.api.payloads.request.CreateChatRequestDto;
import tech.pzjswpooks.zzpj.chat.api.payloads.response.ChangeUserResponseDto;
import tech.pzjswpooks.zzpj.chat.api.payloads.response.CreateChatResponseDto;
import tech.pzjswpooks.zzpj.chat.api.payloads.response.MessageResponseDto;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Objects;

@Path("chat")
public class ChatEndpoint {

    private final AccountsManager accountsManager;

    @Inject
    public ChatEndpoint(AccountsManager accountsManager) {
        this.accountsManager = accountsManager;
    }

    @POST
    @Path("/create")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response registerAccount(CreateChatRequestDto createChatRequestDto) {
        AccountsEntity loggedInAccount = accountsManager.getLoggedInAccount();
        if (Objects.isNull(loggedInAccount)) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(new CreateChatResponseDto(null, false)).build();
        }
        try {
//            accountsManager.registerAccount(AccountEntityMapper.mapRegistrationDtoToAccount(createChatRequestDto, hashGenerator));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(new CreateChatResponseDto(new MessageResponseDto(e.getMessage()), false)).build();
        }
        return Response.status(Response.Status.OK).entity(new CreateChatResponseDto(null, true)).build();
    }

}
