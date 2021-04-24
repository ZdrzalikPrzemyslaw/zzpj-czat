package tech.pzjswpooks.zzpj.chat.api.cdi.endpoints;

import tech.pzjswpooks.zzpj.chat.api.ejb.managers.AccountsManager;
import tech.pzjswpooks.zzpj.chat.api.payloads.response.LockAccountResponseDto;
import tech.pzjswpooks.zzpj.chat.api.payloads.response.MessageResponseDto;

import javax.inject.Inject;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("account")
public class AccountEndpoint {
    @Inject
    private AccountsManager accountsManager;

    /**
     * Metoda służąca do blokowania konta przez administratora.
     *
     * @param id id blokowanego konta
     * @return Response
     */
    // TODO: 24.04.2021 Zabezpieczyć, tylko dla admina
    @PUT
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
}
