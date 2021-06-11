package tech.pzjswpooks.zzpj.chat.api.cdi.stateless.endpoints;

import tech.pzjswpooks.zzpj.chat.api.common.AccountEntityMapper;
import tech.pzjswpooks.zzpj.chat.api.common.I18n;
import tech.pzjswpooks.zzpj.chat.api.ejb.managers.AccountsManager;
import tech.pzjswpooks.zzpj.chat.api.entities.AccountsEntity;
import tech.pzjswpooks.zzpj.chat.api.payloads.request.AccessRequestDto;
import tech.pzjswpooks.zzpj.chat.api.payloads.request.RegistrationRequestDto;
import tech.pzjswpooks.zzpj.chat.api.payloads.response.AccountInfoResponseDTO;
import tech.pzjswpooks.zzpj.chat.api.payloads.response.MessageResponseDto;
import tech.pzjswpooks.zzpj.chat.api.utils.HashGenerator;
import tech.pzjswpooks.zzpj.chat.api.utils.LogInterceptor;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Interceptors(LogInterceptor.class)
@Path("account")
@Stateful
@DenyAll
public class AccountEndpoint {

    private final AccountsManager accountsManager;
    private final HashGenerator hashGenerator;

    @Inject
    public AccountEndpoint(AccountsManager accountsManager, HashGenerator hashGenerator) {
        this.accountsManager = accountsManager;
        this.hashGenerator = hashGenerator;
    }


    /**
     * Metoda służąca do blokowania konta przez administratora.
     *
     * @param username username blokowanego konta
     * @return Response
     */
    // TODO: 24.04.2021 Zabezpieczyć, tylko dla admina
    @POST
    @Path("/lock/{username}")
    @RolesAllowed(I18n.ADMIN)
    @Produces({MediaType.APPLICATION_JSON})
    public Response lockAccount(@Valid @NotNull @PathParam("username") String username) {
        try {
            accountsManager.lockAccount(username);
        } catch (Throwable e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new MessageResponseDto(I18n.ACCOUNT_LOCKED_SUCCESSFULLY)).build();
        }
        return Response.status(Response.Status.OK).entity(new MessageResponseDto(I18n.ACCOUNT_LOCKED_SUCCESSFULLY)).build();
    }

    /**
     * Metoda służąca do blokowania konta przez administratora.
     *
     * @param username username blokowanego konta
     * @return Response
     */
    // TODO: 24.04.2021 Zabezpieczyć, tylko dla admina
    @GET
    @Path("/get-all/")
    @RolesAllowed(I18n.ADMIN)
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllAccounts() {
        try {
            var allAccounts = accountsManager.getAllAccounts();
            List<AccountInfoResponseDTO> accountInfoResponseDTOS = new ArrayList<>();
            allAccounts.forEach(accountsEntity -> accountInfoResponseDTOS.add(new AccountInfoResponseDTO(accountsEntity)));
            return Response.status(Response.Status.OK).entity(accountInfoResponseDTOS).build();
        } catch (Throwable e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new MessageResponseDto(I18n.ACCOUNT_GET_ALL_ACCOUNTS_FAILED)).build();
        }
    }

    @POST
    @Path("/unlock/{username}")
    @RolesAllowed(I18n.ADMIN)
    @Produces({MediaType.APPLICATION_JSON})
    public Response unlockAccount(@Valid @NotNull @PathParam("username") String username) {
        try {
            accountsManager.unlockAccount(username);
        } catch (Throwable e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new MessageResponseDto(I18n.ACCOUNT_UNLOCKED_FAILED)).build();
        }
        return Response.status(Response.Status.OK).entity(new MessageResponseDto(I18n.ACCOUNT_UNLOCKED_SUCCESSFULLY)).build();
    }

    @PUT
    @Path("/register")
    @PermitAll
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response registerAccount(@Valid @NotNull RegistrationRequestDto registrationRequestDto) {
        try {
            accountsManager.registerAccount(AccountEntityMapper.mapRegistrationDtoToAccount(registrationRequestDto, hashGenerator));
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new MessageResponseDto(I18n.ACCOUNT_CREATION_FAILED)).build();
        }
        return Response.status(Response.Status.OK).entity(new MessageResponseDto(I18n.ACCOUNT_CREATED_SUCCESSFULLY)).build();
    }

    @POST
    @Path("/add-access-level")
    @RolesAllowed("level.admin")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response addAccessLevel(@Valid @NotNull AccessRequestDto accessRequestDto) {
        try {
            accountsManager.addAccessLevel(accessRequestDto.getUsername(), accessRequestDto.getAccessLevel());
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new MessageResponseDto(I18n.ACCESS_LEVEL_ADD_FAILED)).build();
        }
        return Response.status(Response.Status.OK).entity(new MessageResponseDto(I18n.ACCESS_LEVEL_ADDED_SUCCESSFULLY)).build();
    }

    @POST
    @Path("/revoke-access-level")
    @RolesAllowed("level.admin")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response revokeAccessLevel(@Valid @NotNull AccessRequestDto accessRequestDto) {
        try {
            accountsManager.revokeAccessLevel(accessRequestDto.getUsername(), accessRequestDto.getAccessLevel());
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new MessageResponseDto(I18n.ACCESS_LEVEL_REMOVE_FAILED)).build();
        }
        return Response.status(Response.Status.OK).entity(new MessageResponseDto(I18n.ACCESS_LEVEL_REMOVED_SUCCESSFULLY)).build();
    }

}
