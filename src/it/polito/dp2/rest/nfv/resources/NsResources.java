package it.polito.dp2.rest.nfv.resources;

import it.polito.dp2.rest.nfv.jaxb.*;
import it.polito.dp2.rest.nfv.services.NsServices;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.net.URI;

@Path("/ns")
@Api(value = "/ns")
public class NsResources {
    public UriInfo uriInfo;

    private NsServices service = new NsServices();

    public NsResources(@Context UriInfo uriInfo) {
        this.uriInfo = uriInfo;
    }

    @GET
    @ApiOperation(value = "getNS", notes = "Read the NS data")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public NS getNS(@QueryParam("page") int page) {
        return service.getNS(uriInfo.getBaseUri().toString(), page);
    }

    @POST
    @ApiOperation(value = "addNS", notes = "Add a list of NSD")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 500, message = "Internal Error")})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response addNS(NS ns) {
        //Set self URI
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path("ns");
        URI self = builder.build();

        try{
            service.addNS(ns);
        }catch (Exception e) {
            throw new InternalServerErrorException();
        }

        return Response.created(self).entity(ns).build();
    }

    @DELETE
    @ApiOperation(value = "deleteNS", notes = "Delete all NSD")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal Error")})
    public void deleteVNFDependency() {
        try{
            service.deleteNS();
        }catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }

    @GET
    @Path("/nsd/{nsdID}")
    @ApiOperation(value = "getNSD", notes = "Read the NSD data")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "NSD Not Found")})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public NSD getNSDInfo(@QueryParam("page") int page, @PathParam("nsdID") String nsdID) {
    	NSD nsd;

        try{
        	if((nsd = service.getNSDInfo(nsdID, uriInfo.getBaseUri().toString(), page)) == null)
        		throw new NotFoundException();
        }catch(Exception e){
        	throw e;
        }
        
        return nsd; 
    }

    @POST
    @Path("/nsd")
    @ApiOperation(value = "addNSD", notes = "Add a new NSD")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 403, message = "NSD just exist: ID must be unique")})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response addNSD(NSD nsd) {
        //Set self URI
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(nsd.getId());
        URI self = builder.build();

        try{
            if(service.addNSD(nsd) != null)
                throw new ForbiddenException();
        }catch (Exception e) {
            throw e;
        }

        return Response.created(self).entity(nsd).build();
    }

    @DELETE
    @Path("/nsd/{nsdID}")
    @ApiOperation(value = "deleteNSD", notes = "Delete a NSD")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "NSD Not Found")})
    public void deleteVNFDependency(@PathParam("nsdID") String nsdID) {
        try{
            if(service.deleteNSD(nsdID) == null)
                throw new NotFoundException();
        }catch (Exception e) {
            throw e;
        }
    }
}
