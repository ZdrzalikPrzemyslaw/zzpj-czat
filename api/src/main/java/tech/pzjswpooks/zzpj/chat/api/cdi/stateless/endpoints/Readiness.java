package tech.pzjswpooks.zzpj.chat.api.cdi.stateless.endpoints;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("readiness")
public class Readiness {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String isReady() {
        return "ready";
    }
}
