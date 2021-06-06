package tech.pzjswpooks.zzpj.chat.api.cdi.stateless.endpoints;

import tech.pzjswpooks.zzpj.chat.api.common.UsersEntityToDtoMapper;
import tech.pzjswpooks.zzpj.chat.api.ejb.managers.AccountsManager;
import tech.pzjswpooks.zzpj.chat.api.ejb.managers.UsersManager;
import tech.pzjswpooks.zzpj.chat.api.entities.AccountsEntity;
import tech.pzjswpooks.zzpj.chat.api.entities.UsersEntity;
import tech.pzjswpooks.zzpj.chat.api.payloads.request.ChangeUserRequestDto;
import tech.pzjswpooks.zzpj.chat.api.payloads.request.SearchUserRequestDto;
import tech.pzjswpooks.zzpj.chat.api.payloads.response.ChangeUserResponseDto;
import tech.pzjswpooks.zzpj.chat.api.payloads.response.MessageResponseDto;
import tech.pzjswpooks.zzpj.chat.api.payloads.response.SearchUserResponseDto;
import tech.pzjswpooks.zzpj.chat.api.payloads.response.UsersResponseDTO;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response changeUser(ChangeUserRequestDto changeUserRequestDto) {
        AccountsEntity loggedInAccount = accountsManager.getLoggedInAccount();
        if (Objects.isNull(loggedInAccount)) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(new ChangeUserResponseDto(null, false)).build();
        }
        try {
            usersManager.changeUserDetails(loggedInAccount.getUserId(), changeUserRequestDto);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(new ChangeUserResponseDto(new MessageResponseDto(e.getMessage()), false)).build();
        }
        return Response.status(Response.Status.OK).entity(new ChangeUserResponseDto(null, true)).build();
    }

    @GET
    @Path("/search_username")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response searchUserByUsername(SearchUserRequestDto searchUserRequestDto) {
        List<UsersResponseDTO> users;
        try {
            users = usersManager.searchUserByUsernameRegex(searchUserRequestDto)
                    .stream()
                    .map(UsersEntityToDtoMapper::mapUsersEntityToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(new SearchUserResponseDto(null)).build();
        }
        return Response.status(Response.Status.OK).entity(new SearchUserResponseDto(users)).build();
    }

    @GET
    @Path("/search_email")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response searchUserByEmail(SearchUserRequestDto searchUserRequestDto) {
        List<UsersResponseDTO> users;
        try {
            users = usersManager.searchUserByEmailRegex(searchUserRequestDto)
                    .stream()
                    .map(UsersEntityToDtoMapper::mapUsersEntityToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(new SearchUserResponseDto(null)).build();
        }
        return Response.status(Response.Status.OK).entity(new SearchUserResponseDto(users)).build();
    }

    @GET
    @Path("/search_username")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response searchUserByFirstOrLastName(SearchUserRequestDto searchUserRequestDto) {
        List<UsersResponseDTO> users;
        try {
            users = usersManager.searchUserByFirstOrLastNameRegex(searchUserRequestDto)
                    .stream()
                    .map(UsersEntityToDtoMapper::mapUsersEntityToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(new SearchUserResponseDto(null)).build();
        }
        return Response.status(Response.Status.OK).entity(new SearchUserResponseDto(users)).build();
    }

}
