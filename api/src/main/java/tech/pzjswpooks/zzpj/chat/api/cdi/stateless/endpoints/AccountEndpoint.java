package tech.pzjswpooks.zzpj.chat.api.cdi.stateless.endpoints;

import tech.pzjswpooks.zzpj.chat.api.common.AccountEntityMapper;
import tech.pzjswpooks.zzpj.chat.api.ejb.managers.AccountsManager;
import tech.pzjswpooks.zzpj.chat.api.payloads.request.RegistrationRequestDto;
import tech.pzjswpooks.zzpj.chat.api.payloads.response.LockAccountResponseDto;
import tech.pzjswpooks.zzpj.chat.api.payloads.response.MessageResponseDto;
import tech.pzjswpooks.zzpj.chat.api.payloads.response.RegistrationResponseDto;
import tech.pzjswpooks.zzpj.chat.api.utils.HashGenerator;
import tech.pzjswpooks.zzpj.chat.api.utils.SHA256HashGenerator;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("account")
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
     * @param id id blokowanego konta
     * @return Response
     */
    // TODO: 24.04.2021 Zabezpieczyć, tylko dla admina
    @POST
    @Path("/lock/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response lockAccount(@PathParam("id") Long id) {
        try {
            accountsManager.lockAccount(id);
            // TODO: 24.04.2021 Fix catch block
        } catch (Throwable e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(new LockAccountResponseDto(new MessageResponseDto(e.getMessage()), false)).build();
            // TODO: 20.04.2021 add application exception
        }
        return Response.status(Response.Status.OK).entity(new LockAccountResponseDto(null, true)).build();
    }

    @PUT
    @Path("/register")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response registerAccount(RegistrationRequestDto registrationRequestDto) {
        try {
            accountsManager.registerAccount(AccountEntityMapper.mapRegistrationDtoToAccount(registrationRequestDto, hashGenerator));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(new RegistrationResponseDto(new MessageResponseDto(e.getMessage()), false)).build();
        }
        return Response.status(Response.Status.OK).entity(new RegistrationResponseDto(null, true)).build();
    }

}
