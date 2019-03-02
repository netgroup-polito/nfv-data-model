package it.polito.dp2.rest.nfv.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.polito.dp2.rest.nfv.jaxb.PNF;
import it.polito.dp2.rest.nfv.jaxb.PNFD;
import it.polito.dp2.rest.nfv.services.PnfdServices;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;

@Path("ns/nsd/{nsdID}/pnf")
@Api(value = "/ns/{nsdID}/vnfdependency")
public class PnfdResources {
    public UriInfo uriInfo;

    private PnfdServices service = new PnfdServices();

    public PnfdResources(@Context UriInfo uriInfo) {
        this.uriInfo = uriInfo;
    }

    @GET
    @ApiOperation(value = "getPNF", notes = "Read the PNF data")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public PNF getPNF(@PathParam("nsdID") String nsdID) {
        return service.getPNF(nsdID);
    }

    @POST
    @ApiOperation(value = "addPNF", notes = "Add a new PNF")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Error")})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response addPNF(@PathParam("nsdID") String nsdID, PNF pnf) {
        //Set self URI
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(nsdID);
        URI self = builder.build();

        try{
            if(service.addPNF(nsdID, pnf) == null)
                throw new InternalServerErrorException();
        }catch (Exception e) {
            throw new InternalServerErrorException();
        }

        return Response.created(self).entity(pnf).build();
    }

    @DELETE
    @ApiOperation(value = "deletePNF", notes = "Clear the PNF")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Internal Error"),})
    public void deletePNF(@PathParam("nsdID") String nsdID) {
        try{
            if(service.deletePNF(nsdID) == null)
                throw new InternalServerErrorException();
        }catch (Exception e) {
            throw e;
        }
    }

    @GET
    @Path("/pnfd/{pnfdID}")
    @ApiOperation(value = "getPNFD", notes = "Read a certain PNFD")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public PNFD getVNFInfo(@PathParam("nsdID") String nsdID, @PathParam("pnfdID") String pnfdID) {
        return service.getPNFDInfo(nsdID, pnfdID);
    }

    @POST
    @Path("/pnfd")
    @ApiOperation(value = "addPNFD", notes = "Add a new PNFD")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Error")})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response addVnfd(@PathParam("nsdID") String nsdID, PNFD pnfd) {
        //Set self URI
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(pnfd.getId());
        URI self = builder.build();

        try{
            if(service.addPNFD(nsdID, pnfd) == null)
                throw new ForbiddenException();
        }catch (Exception e) {
            throw new InternalServerErrorException();
        }

        return Response.created(self).entity(pnfd).build();
    }

    @DELETE
    @Path("/pnfd/{pnfID}")
    @ApiOperation(value = "deletePNFD", notes = "Remove a certain PNFD")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Internal Error"),})
    public void deleteVNFD(@PathParam("nsdID") String nsdID, @PathParam("pnfID") String pnfID) {
        try{
            if(service.deletePNFD(nsdID, pnfID) == null)
                throw new NotFoundException();
        }catch (Exception e) {
            throw e;
        }
    }


    @PUT
    @Path("/pnfd")
    @ApiOperation(value = "deletePNFD", notes = "Remove a certain PNFD")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Internal Error"),})
    public void MODIFYVNFD(@PathParam("nsdID") String nsdID, PNFD pnfd) {
        try{
            if(service.modifyPNFD(nsdID, pnfd) == null)
                throw new NotFoundException();
        }catch (Exception e) {
            throw e;
        }
    }

}
