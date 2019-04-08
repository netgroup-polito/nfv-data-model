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

@Path("ns/nsd/{nsdID}/propertydefinition")
@Api(value = "/ns/nsd/{nsdID}/propertydefinition")
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
    public PropertyDefinition getPropertyDefinition(@QueryParam("page") int page, @PathParam("nsdID") String nsdID) {
        return service.getPropertyDefinition(nsdID, uriInfo.getBaseUri().toString(), page);
    }
    
    @POST
    @ApiOperation(value = "addPropertyDefinition", notes = "Add a new PropertyDefinition")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "NSD Not Found")})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response addPropertyDefinition(@PathParam("nsdID") String nsdID, PropertyDefinition propertyDefinition) {
        //Set self URI
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(nsdID);
        URI self = builder.build();

        try{
            if(service.addPropertyDefinition(nsdID, propertyDefinition) == null)
                throw new NotFoundException();
        }catch (Exception e) {
            throw e;
        }

        return Response.created(self).entity(propertyDefinition).build();
    }

    @DELETE
    @ApiOperation(value = "deletePropertyDefinition", notes = "Delete a PropertyDefinition")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "NSD Not Found")})
    public void deletePropertyDefinition(@PathParam("nsdID") String nsdID) {
        try{
            if(service.deletePropertyDefinition(nsdID) == null)
                throw new NotFoundException();
        }catch (Exception e) {
            throw e;
        }
    }

    @GET
    @Path("/property/{graphID}")
    @ApiOperation(value = "getProperty", notes = "Read the Property")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
    		@ApiResponse(code = 404, message = "NSD does not exist or Property of that graph Not Found")})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Property getPropertyDefinition(@PathParam("nsdID") String nsdID, @PathParam("graphID") Long graphID) {
        Property property;

    	try{
        	if((property = service.getProperty(nsdID, graphID)) == null)
        		throw new NotFoundException();
        }catch (Exception e) {
            throw e;
        }

    	return property;
    }

    @POST
    @Path("/property")
    @ApiOperation(value = "addProperty", notes = "Add a new Property")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 403, message = "NSD does not exist or Property already exists")})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response addProperty(@PathParam("nsdID") String nsdID, Property property) {
        //Set self URI
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(nsdID);
        URI self = builder.build();

        try{
            if(service.addProperty(nsdID, property) == null)
                throw new ForbiddenException();
        }catch (Exception e) {
            throw e;
        }

        return Response.created(self).entity(property).build();
    }

    @DELETE
    @Path("/property/{graphID}")
    @ApiOperation(value = "deleteProperty", notes = "Delete a Property")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "NSD does not exist or Property Not Found")})
    public void deleteProperty(@PathParam("nsdID") String nsdID, @PathParam("graphID") Long graphID) {
        try{
            if(service.deleteProperty(nsdID, graphID) == null)
                throw new NotFoundException();
        }catch (Exception e) {
            throw e;
        }
    }

    @PUT
    @Path("/property")
    @ApiOperation(value = "modifyProperty", notes = "Modify a certain Property")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
    		@ApiResponse(code = 404, message = "NSD does not exist or Property of that graph Not Found")})
    public Property modifyProperty(@PathParam("nsdID") String nsdID, Property property) {
        try{
            if(service.modifyProperty(nsdID, property) == null)
                throw new NotFoundException();
        }catch (Exception e) {
            throw e;
        }
        
        return property;
    }
}
