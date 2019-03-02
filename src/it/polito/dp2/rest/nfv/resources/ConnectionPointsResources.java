package it.polito.dp2.rest.nfv.resources;

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

@Path("ns/nsd/{nsdID}/cps")
@Api(value = "/ns/{nsdID}/cps")
public class ConnectionPointsResources {
    public UriInfo uriInfo;

    private ConnectionPointsServices service = new ConnectionPointsServices();

    public ConnectionPointsResources(@Context UriInfo uriInfo) {
        this.uriInfo = uriInfo;
    }

    @GET
    @ApiOperation(value = "getConnectionPoints", notes = "Read the ConnectionPoints data")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ConnectionPoints getConnectionPoints(@PathParam("nsdID") String nsdID) {
        return service.getConnectionPoints(nsdID);
    }

    @POST
    @ApiOperation(value = "addConnectionPoints", notes = "Add a new ConnectionPoints")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Error")})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response addConnectionPoints(@PathParam("nsdID") String nsdID, ConnectionPoints cps) {
        //Set self URI
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(nsdID);
        URI self = builder.build();

        try{
            if(service.addConnectionPoints(nsdID, cps) == null)
                throw new InternalServerErrorException();
        }catch (Exception e) {
            throw new InternalServerErrorException();
        }

        return Response.created(self).entity(cps).build();
    }

    @DELETE
    @ApiOperation(value = "deleteConnectionPoints", notes = "Clear the ConnectionPoints")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Internal Error"),})
    public void deleteConnectionPoints(@PathParam("nsdID") String nsdID) {
        try{
            if(service.deleteConnectionPoints(nsdID) == null)
                throw new InternalServerErrorException();
        }catch (Exception e) {
            throw e;
        }
    }

    @GET
    @Path("/cp/{cpID}")
    @ApiOperation(value = "getServiceConnectionPoint", notes = "Read a certain ConnectionPoint")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ConnectionPoint getVNFInfo(@PathParam("nsdID") String nsdID, @PathParam("cpID") String cpID) {
        return service.getConnectionPoint(nsdID, cpID);
    }

    @POST
    @Path("/cp")
    @ApiOperation(value = "addConnectionPoint", notes = "Add a new ConnectionPoint")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Error")})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response addConnectionPoint(@PathParam("nsdID") String nsdID, ConnectionPoint cp) {
        //Set self URI
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(cp.getId());
        URI self = builder.build();

        try{
            if(service.addConnectionPoint(nsdID, cp) == null)
                throw new ForbiddenException();
        }catch (Exception e) {
            throw new InternalServerErrorException();
        }

        return Response.created(self).entity(cp).build();
    }

    @DELETE
    @Path("/cp/{cpID}")
    @ApiOperation(value = "deleteConnectionPoint", notes = "Remove a certain ConnectionPoint")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Internal Error"),})
    public void deleteConnectionPoint(@PathParam("nsdID") String nsdID, @PathParam("cpID") String cpID) {
        try{
            if(service.deleteConnectionPoint(nsdID, cpID) == null)
                throw new NotFoundException();
        }catch (Exception e) {
            throw e;
        }
    }

}
