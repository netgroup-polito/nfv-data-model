package it.polito.dp2.rest.nfv.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.polito.dp2.rest.nfv.jaxb.Flavours;
import it.polito.dp2.rest.nfv.jaxb.ServiceDeploymentFlavour;
import it.polito.dp2.rest.nfv.services.FlavoursServices;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;

@Path("ns/nsd/{nsdID}/flavours")
@Api(value = "ns/nsd/{nsdID}/flavours")
public class FlavoursResources {
    public UriInfo uriInfo;

    private FlavoursServices service = new FlavoursServices();

    public FlavoursResources(@Context UriInfo uriInfo) {
        this.uriInfo = uriInfo;
    }

    @GET
    @ApiOperation(value = "getFlavours", notes = "Read the Flavours data")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Flavours getFlavours(@PathParam("nsdID") String nsdID) {
        return service.getFlavours(nsdID);
    }

    @POST
    @ApiOperation(value = "addFlavours", notes = "Add a new Flavours")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 500, message = "Internal Error")})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response addFlavours(@PathParam("nsdID") String nsdID, Flavours flavours) {
        //Set self URI
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(nsdID);
        URI self = builder.build();

        try{
            if(service.addFlavours(nsdID, flavours) == null)
                throw new InternalServerErrorException();
        }catch (Exception e) {
            throw e;
        }

        return Response.created(self).entity(flavours).build();
    }

    @DELETE
    @ApiOperation(value = "deleteFlavours", notes = "Clear the Flavours")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal Error")})
    public void deleteFlavours(@PathParam("nsdID") String nsdID) {
        try{
            if(service.deleteFlavours(nsdID) == null)
                throw new InternalServerErrorException();
        }catch (Exception e) {
            throw e;
        }
    }

    @GET
    @Path("/flavour/{sdfID}")
    @ApiOperation(value = "getServiceDeploymentFlavour", notes = "Read a certain ServiceDeploymentFlavour")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
    		@ApiResponse(code = 200, message = "Flavour Not Found")})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ServiceDeploymentFlavour getFlavourInfo(@PathParam("nsdID") String nsdID, @PathParam("sdfID") String sdfID) {
    	ServiceDeploymentFlavour sdf = new ServiceDeploymentFlavour();
    	
    	try{
        	if((sdf = service.getServiceDeploymentFlavour(nsdID, sdfID)) == null)
        		throw new NotFoundException();
        }
        catch(Exception e){
        	throw e;
        }
    	
    	return sdf;
    }

    @POST
    @Path("/flavour")
    @ApiOperation(value = "addServiceDeploymentFlavour", notes = "Add a new ServiceDeploymentFlavour")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Forbidden: Flavour already exists")})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response addServiceDeploymentFlavour(@PathParam("nsdID") String nsdID, ServiceDeploymentFlavour sdf) {
        //Set self URI
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(sdf.getId());
        URI self = builder.build();

        try{
            if(service.addServiceDeploymentFlavour(nsdID, sdf) == null)
                throw new ForbiddenException();
        }catch (Exception e) {
            throw e;
        }

        return Response.created(self).entity(sdf).build();
    }

    @DELETE
    @Path("/flavour/{sdfID}")
    @ApiOperation(value = "deleteServiceDeploymentFlavour", notes = "Remove a certain ServiceDeploymentFlavour")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Flavour Not Found")})
    public void deleteServiceDeploymentFlavour(@PathParam("nsdID") String nsdID, @PathParam("sdfID") String sdfID) {
        try{
            if(service.deleteServiceDeploymentFlavour(nsdID, sdfID) == null)
                throw new NotFoundException();
        }catch (Exception e) {
            throw e;
        }
    }

}
