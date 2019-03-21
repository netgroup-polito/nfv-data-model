package it.polito.dp2.rest.nfv.client;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.polito.dp2.rest.nfv.services.ConnectionPointsServices;
import it.polito.dp2.rest.nfv.jaxb.ConnectionPoints;
import it.polito.dp2.rest.nfv.jaxb.ConnectionPoint;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;

@Path("/test")
@Api(value = "/test")
public class ClientResources {
    public UriInfo uriInfo;

    private Client service = new Client();

    public ClientResources(@Context UriInfo uriInfo) {
        this.uriInfo = uriInfo;
    }

    @GET
    @ApiOperation(value = "testClient", notes = "Run Client perform different tests")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void test() {
        service.test();
    }
}
