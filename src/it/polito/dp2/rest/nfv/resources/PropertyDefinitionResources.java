package it.polito.dp2.rest.nfv.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.polito.dp2.rest.nfv.jaxb.Property;
import it.polito.dp2.rest.nfv.jaxb.PropertyDefinition;
import it.polito.dp2.rest.nfv.services.PropertyDefinitionServices;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;

@Path("ns/nsd/{nsdID}/propertyDefinition")
@Api(value = "/ns/{nsdID}/propertyDefinition")
public class PropertyDefinitionResources {
    public UriInfo uriInfo;

    private PropertyDefinitionServices service = new PropertyDefinitionServices();

    public PropertyDefinitionResources(@Context UriInfo uriInfo) {
        this.uriInfo = uriInfo;
    }

    @GET
    @ApiOperation(value = "getPropertyDefinition", notes = "Read the PropertyDefinition")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public PropertyDefinition getPropertyDefinition(@PathParam("nsdID") String nsdID) {
        return service.getPropertyDefinition(nsdID);
    }

    @GET
    @Path("/{graphID}")
    @ApiOperation(value = "getProperty", notes = "Read the Property")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Property getPropertyDefinition(@PathParam("nsdID") String nsdID, @PathParam("graphID") Long graphID) {
        return service.getProperty(nsdID, graphID);
    }

    @POST
    @ApiOperation(value = "addProperty", notes = "Add a new Property")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Error")})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response addPNF(@PathParam("nsdID") String nsdID, Property property) {
        //Set self URI
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(nsdID);
        URI self = builder.build();

        try{
            if(service.addProperty(nsdID, property) == null)
                throw new InternalServerErrorException();
        }catch (Exception e) {
            throw new InternalServerErrorException();
        }

        return Response.created(self).entity(property).build();
    }

    @DELETE
    @Path("/{graphID}")
    @ApiOperation(value = "deleteProperty", notes = "Delete a Property")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Internal Error"),})
    public void deletePNF(@PathParam("nsdID") String nsdID, @PathParam("graphID") Long graphID) {
        try{
            if(service.deleteProperty(nsdID, graphID) == null)
                throw new InternalServerErrorException();
        }catch (Exception e) {
            throw e;
        }
    }

    @PUT
    @ApiOperation(value = "modifyProperty", notes = "Modify a certain Property")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Internal Error"),})
    public void modifyVNFD(@PathParam("nsdID") String nsdID, Property property) {
        try{
            if(service.modifyProperty(nsdID, property) == null)
                throw new NotFoundException();
        }catch (Exception e) {
            throw e;
        }
    }
}
