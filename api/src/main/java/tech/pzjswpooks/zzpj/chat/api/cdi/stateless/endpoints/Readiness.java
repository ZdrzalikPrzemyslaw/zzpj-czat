package tech.pzjswpooks.zzpj.chat.api.cdi.stateless.endpoints;

import tech.pzjswpooks.zzpj.chat.api.payloads.response.MessageResponseDto;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateful;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("readiness")
@Stateful
@PermitAll
public class Readiness {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response isReady() {
        return Response.status(Response.Status.OK).entity(new MessageResponseDto("ready")).build();
    }

}
