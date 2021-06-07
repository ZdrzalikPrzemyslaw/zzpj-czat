package tech.pzjswpooks.zzpj.chat.api.cdi.stateless.endpoints;

import tech.pzjswpooks.zzpj.chat.api.ejb.managers.AccountsManager;
import tech.pzjswpooks.zzpj.chat.api.ejb.managers.UsersManager;
import tech.pzjswpooks.zzpj.chat.api.entities.AccountsEntity;
import tech.pzjswpooks.zzpj.chat.api.payloads.request.ChangeUserRequestDto;
import tech.pzjswpooks.zzpj.chat.api.payloads.response.ChangeUserResponseDto;
import tech.pzjswpooks.zzpj.chat.api.payloads.response.MessageResponseDto;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Objects;

@Path("user")
public class UserEndpoint {

    private final UsersManager usersManager;
    private final AccountsManager accountsManager;

    @Inject
    public UserEndpoint(UsersManager usersManager, AccountsManager accountsManager) {
        this.usersManager = usersManager;
        this.accountsManager = accountsManager;
    }

    @POST
    @Path("/change")
    @RolesAllowed({"level.user", "level.admin"})
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response changeUser(ChangeUserRequestDto changeUserRequestDto) {
        try {
            AccountsEntity loggedInAccount = accountsManager.getLoggedInAccount();
            if (Objects.isNull(loggedInAccount)) {
                return Response.status(Response.Status.UNAUTHORIZED).entity(new ChangeUserResponseDto(null, false)).build();
            }
            usersManager.changeUserDetails(loggedInAccount.getUserId(), changeUserRequestDto);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(new ChangeUserResponseDto(new MessageResponseDto(e.getMessage()), false)).build();
        }
        return Response.status(Response.Status.OK).entity(new ChangeUserResponseDto(null, true)).build();
    }

}
