package resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jaxb.VNFFGD;
import jaxb.NetworkForwardingPaths;
import services.VnffgdServices;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;

@Path("ns/nsd/{nsdID}/vnffgd")
@Api(value = "/ns/{nsdID}/vnffgd")
public class VnffgdResources {
    public UriInfo uriInfo;

    private VnffgdServices service = new VnffgdServices();

    public VnffgdResources(@Context UriInfo uriInfo) {
        this.uriInfo = uriInfo;
    }

    @GET
    @ApiOperation(value = "getVNFFGD", notes = "Read the VNFFGD data")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public VNFFGD getVNFFGD(@PathParam("nsdID") String nsdID) {
        return service.getVNFFGD(nsdID);
    }

    @POST
    @ApiOperation(value = "addVNFFGD", notes = "Add a new VNFFGD")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Error")})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response addVNFFGD(@PathParam("nsdID") String nsdID, VNFFGD vnffgd) {
        //Set self URI
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(nsdID);
        URI self = builder.build();

        try{
            if(service.addVNFFGD(nsdID, vnffgd) == null)
                throw new InternalServerErrorException();
        }catch (Exception e) {
            throw new InternalServerErrorException();
        }

        return Response.created(self).entity(vnffgd).build();
    }

    @DELETE
    @ApiOperation(value = "deleteVNFFGD", notes = "Clear the VNFFGD")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Internal Error"),})
    public void deleteVNFFGD(@PathParam("nsdID") String nsdID) {
        try{
            if(service.deletevVNFFGD(nsdID) == null)
                throw new InternalServerErrorException();
        }catch (Exception e) {
            throw e;
        }
    }

    @GET
    @Path("/nfp/{pathID}")
    @ApiOperation(value = "getVNFFGD", notes = "Read a certain VNFFGD")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public NetworkForwardingPaths getNetworkForwardingPathsInfo(@PathParam("nsdID") String nsdID, @PathParam("pathID") String pathID) {
        return service.getNetworkForwardingPathsInfo(nsdID, pathID);
    }

    @POST
    @Path("/nfp")
    @ApiOperation(value = "addNetworkForwardingPaths", notes = "Add a new NetworkForwardingPaths")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Error")})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response addNetworkForwardingPaths(@PathParam("nsdID") String nsdID, NetworkForwardingPaths nfp) {
        //Set self URI
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(nfp.getId());
        URI self = builder.build();

        try{
            if(service.addNetworkForwardingPaths(nsdID, nfp) == null)
                throw new ForbiddenException();
        }catch (Exception e) {
            throw new InternalServerErrorException();
        }

        return Response.created(self).entity(nfp).build();
    }

    @DELETE
    @Path("/nfp/{pathID}")
    @ApiOperation(value = "deleteNetworkForwardingPaths", notes = "Remove a certain NetworkForwardingPaths")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Internal Error"),})
    public void deleteNetworkForwardingPaths(@PathParam("nsdID") String nsdID, @PathParam("pathID") String pathID) {
        try{
            if(service.deleteNetworkForwardingPaths(nsdID, pathID) == null)
                throw new NotFoundException();
        }catch (Exception e) {
            throw e;
        }
    }

    @PUT
    @Path("/nfp")
    @ApiOperation(value = "deleteNetworkForwardingPaths", notes = "Remove a certain NetworkForwardingPaths")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Internal Error"),})
    public void MODIFYVNFD(@PathParam("nsdID") String nsdID, NetworkForwardingPaths nfp) {
        try{
            if(service.modifyNetworkForwardingPaths(nsdID, nfp) == null)
                throw new NotFoundException();
        }catch (Exception e) {
            throw e;
        }
    }

}
