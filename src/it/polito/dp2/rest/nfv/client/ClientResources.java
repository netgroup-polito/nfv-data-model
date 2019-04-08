package it.polito.dp2.rest.nfv.client;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

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
    public Response test() {
        service.test();
        return  Response.status(Response.Status.OK).build();
    }
}