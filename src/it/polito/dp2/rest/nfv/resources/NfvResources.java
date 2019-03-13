package it.polito.dp2.rest.nfv.resources;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.polito.dp2.rest.nfv.services.NfvServices;

import it.polito.dp2.rest.nfv.jaxb.NFV;

@Path("/")
@Api(value = "./")
public class NfvResources {
	
	public UriInfo uriInfo;

    private NfvServices service = new NfvServices();

    public NfvResources(@Context UriInfo uriInfo) {
        this.uriInfo = uriInfo;
    }

    @GET
    @ApiOperation(value = "getNFV", notes = "Read the NFV data")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public NFV getNFV() {
        return service.getNFV();
    }

    @POST
    @ApiOperation(value = "addNFV", notes = "Add an NFV")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 500, message = "Internal Error")})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response addNFV(NFV nfv) {
        //Set self URI
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path("nfv");
        URI self = builder.build();

        try{
            service.addNFV(nfv);
        }catch (Exception e) {
            throw new InternalServerErrorException();
        }

        return Response.created(self).entity(nfv).build();
    }

    @DELETE
    @ApiOperation(value = "deleteNFV", notes = "Delete all NFV")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal Error")})
    public void deleteNFV() {
        try{
            service.deleteNFV();
        }catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }
}
