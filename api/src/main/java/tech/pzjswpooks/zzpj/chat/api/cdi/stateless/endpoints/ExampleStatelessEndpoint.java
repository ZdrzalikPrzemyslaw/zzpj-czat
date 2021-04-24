package tech.pzjswpooks.zzpj.chat.api.cdi.stateless.endpoints;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("example")
public class ExampleStatelessEndpoint {
    @GET
    @Path("/example_get")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response exampleGet() {
        return Response.status(Response.Status.OK).entity("Example").build();
    }


}
