package tech.pzjswpooks.zzpj.chat.api.cdi.stateless.endpoints;

import tech.pzjswpooks.zzpj.chat.api.common.UserEntityMapper;
import tech.pzjswpooks.zzpj.chat.api.ejb.managers.UsersManager;
import tech.pzjswpooks.zzpj.chat.api.payloads.request.ChangeUserRequestDto;
import tech.pzjswpooks.zzpj.chat.api.payloads.response.ChangeUserResponseDto;
import tech.pzjswpooks.zzpj.chat.api.payloads.response.MessageResponseDto;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("user")
public class UserEndpoint {

    private final UsersManager usersManager;

    @Inject
    public UserEndpoint(UsersManager usersManager) {
        this.usersManager = usersManager;
    }

    /**
     * Metoda służąca do zmiany danych użytkownika
     *
     * @return Response
     */
    // TODO: 24.04.2021 Zabezpieczyć, tylko dla admina
    @POST
    @Path("/change")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response changeUser(ChangeUserRequestDto changeUserRequestDto) {
        try {
            usersManager.changeUser(UserEntityMapper.mapChangeUserDtoToUser(changeUserRequestDto));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(new ChangeUserResponseDto(new MessageResponseDto(e.getMessage()), false)).build();
        }
        return Response.status(Response.Status.OK).entity(new ChangeUserResponseDto(null, true)).build();
    }

}
