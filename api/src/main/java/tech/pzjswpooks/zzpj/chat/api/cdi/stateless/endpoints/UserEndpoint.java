package tech.pzjswpooks.zzpj.chat.api.cdi.stateless.endpoints;

import tech.pzjswpooks.zzpj.chat.api.common.I18n;
import tech.pzjswpooks.zzpj.chat.api.ejb.managers.AccountsManager;
import tech.pzjswpooks.zzpj.chat.api.ejb.managers.UsersManager;
import tech.pzjswpooks.zzpj.chat.api.payloads.request.EditAccountRequestDTO;
import tech.pzjswpooks.zzpj.chat.api.payloads.response.MessageResponseDto;
import tech.pzjswpooks.zzpj.chat.api.utils.LogInterceptor;
import tech.pzjswpooks.zzpj.chat.api.utils.LoggedInAccountUtil;

import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Interceptors(LogInterceptor.class)
@DenyAll
@Stateful
@Path("user")
public class UserEndpoint {

    private final UsersManager usersManager;
    private final AccountsManager accountsManager;
    private final LoggedInAccountUtil loggedInAccountUtil;

    @Inject
    public UserEndpoint(UsersManager usersManager, AccountsManager accountsManager, LoggedInAccountUtil loggedInAccountUtil) {
        this.usersManager = usersManager;
        this.accountsManager = accountsManager;
        this.loggedInAccountUtil = loggedInAccountUtil;
    }

    @POST
    @Path("/change")
    @RolesAllowed({I18n.ADMIN, I18n.USER})
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response changeUser(@NotNull @Valid EditAccountRequestDTO editAccountRequestDTO) {
        try {
            usersManager.changeUserDetails(loggedInAccountUtil.getLoggedInAccountLogin(), editAccountRequestDTO);
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new MessageResponseDto(I18n.ACCOUNT_EDIT_FAILED)).build();
        }
        return Response.status(Response.Status.OK).entity(new MessageResponseDto(I18n.ACCOUNT_EDITED_SUCCESSFULLY)).build();
    }

}
