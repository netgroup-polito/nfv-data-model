package it.polito.dp2.rest.nfv.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import it.polito.dp2.rest.nfv.jaxb.*;
import it.polito.dp2.rest.nfv.services.VnfServices;

import java.net.URI;

@Path("ns/nsd/{nsdID}/vnf")
@Api(value = "ns/nsd/{nsdID}/vnf")
public class VnfResources {
    public UriInfo uriInfo;

    private VnfServices service = new VnfServices();

    public VnfResources(@Context UriInfo uriInfo) {
        this.uriInfo = uriInfo;
    }

    @GET
    @ApiOperation(value = "getVNF", notes = "Read the VNF data")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public VNF getVNF(@QueryParam("page") int page, @PathParam("nsdID") String nsdID) {
        return service.getVNF(nsdID, uriInfo.getBaseUri().toString(), page);
    }

    @POST
    @ApiOperation(value = "addVNF", notes = "Add a new VNF")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 500, message = "Internal Error")})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response addVNF(@PathParam("nsdID") String nsdID, VNF vnf) {
        //Set self URI
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(nsdID);
        URI self = builder.build();

        try{
            if(service.addVNF(nsdID, vnf) == null)
                throw new InternalServerErrorException();
        }catch (Exception e) {
            throw e;
        }

        return Response.created(self).entity(vnf).build();
    }

    @DELETE
    @ApiOperation(value = "deleteVNF", notes = "Clear the VNF")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal Error")})
    public void deleteVNF(@PathParam("nsdID") String nsdID) {
        try{
            if(service.deleteVNF(nsdID) == null)
                throw new InternalServerErrorException();
        }catch (Exception e) {
            throw e;
        }
    }

    @GET
    @Path("/vnfd/{vnfdID}")
    @ApiOperation(value = "getVNFD", notes = "Read a certain VNFD")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
    		@ApiResponse(code = 404, message = "vnfd not Found")})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public VNFD getVNFInfo(@PathParam("nsdID") String nsdID, @PathParam("vnfdID") String vnfdID) {
        VNFD vnfd;
        
    	try{
    		if((vnfd = service.getVNFDInfo(nsdID, vnfdID)) == null)
    			throw new NotFoundException();
    	}
    	catch(Exception e){
    		throw e;
    	}
    	
    	return vnfd;
    }

    @POST
    @Path("/vnfd")
    @ApiOperation(value = "addVNFD", notes = "Add a new VNFD")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created"),
    		@ApiResponse(code = 403, message = "Forbidden")})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response addVnfd(@PathParam("nsdID") String nsdID, VNFD vnfd) {
        //Set self URI
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(vnfd.getId());
        URI self = builder.build();

        try{
            if(service.addVNFD(nsdID, vnfd) == null)
                throw new ForbiddenException();
        }catch (Exception e) {
            throw e;
        }

        return Response.created(self).entity(vnfd).build();
    }

    @DELETE
    @Path("/vnfd/{vnfdID}")
    @ApiOperation(value = "deleteVNFD", notes = "Remove a certain VNFD")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "VNFD Not Found")})
    public void deleteVNFD(@PathParam("nsdID") String nsdID, @PathParam("vnfdID") String vnfdID) {
        try{
            if(service.deleteVNFD(nsdID, vnfdID) == null)
                throw new NotFoundException();
        }catch (Exception e) {
            throw e;
        }
    }

    @PUT
    @Path("/vnfd")
    @ApiOperation(value = "deleteVNFD", notes = "Remove a certain VNFD")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "VNFD Not Found")})
    public VNFD modifyVNFD(@PathParam("nsdID") String nsdID, VNFD vnfd) {
        try{
            if(service.modifyVNFD(nsdID, vnfd) == null)
                throw new NotFoundException();
        }catch (Exception e) {
            throw e;
        }
        
        return vnfd;
    }
}
