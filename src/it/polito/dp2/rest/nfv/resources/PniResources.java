package it.polito.dp2.rest.nfv.resources;
import it.polito.dp2.rest.nfv.services.PniServices;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import it.polito.dp2.rest.nfv.jaxb.*;

import java.net.URI;

@Path("/pni")
@Api(value = "/pni")
public class PniResources {

    public UriInfo uriInfo;

    private PniServices service = new PniServices();

    public PniResources(@Context UriInfo uriInfo) {
        this.uriInfo = uriInfo;
    }

    @GET
    @ApiOperation(value = "getPni", notes = "Read the PNI data")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public PNI getPNI() {
        return service.getPNI();
    }

    @POST
    @ApiOperation(value = "addPNI", notes = "Add a new PNI")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 500, message = "Internal Error")})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response addPNI(PNI pniAdd){
        //Set self URI
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path("pni");
        URI self = builder.build();

        try{
            service.addPNI(pniAdd);
        }catch (Exception e) {
            throw new InternalServerErrorException();
        }

        return Response.created(self).entity(pniAdd).build();
    }

    @DELETE
    @ApiOperation(value = "deletePNI", notes = "Clear the PNI")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal Error"),})
    public void deletePNI() {
        try{
            if(service.deletePNI() == null)
                throw new InternalServerErrorException();
        }catch (Exception e) {
            throw e;
        }
    }

    @GET
    @Path("/hosts")
    @ApiOperation(value = "getHosts", notes = "Read all the hosts inside the PNI")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Hosts getHosts() {
        return service.getHosts();
    }
    
    @POST
    @Path("/hosts")
    @ApiOperation(value = "addHosts", notes = "Add a list of host")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 500, message = "Internal Error")})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response addHosts(Hosts hosts){
        //Set self URI
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path("hosts");
        URI self = builder.build();

        try{
            service.addHosts(hosts);
        }catch (Exception e) {
            throw new InternalServerErrorException();
        }

        return Response.created(self).entity(hosts).build();
    }

    @GET
    @Path("/hosts/host/{id}")
    @ApiOperation(value = "getHostInfo", notes = "Read a host's information")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Host not Found")})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response getHostInfo(@PathParam("id") String hostID) {
        Host host = service.getHostInfo(hostID);
        try{
        	if(host == null)
                throw new NotFoundException();
        }catch(Exception e){
        	throw e;
        }
        
        return Response.status(Status.OK).entity(host).build();
    }

	@POST
	@Path("/hosts/host")
	@ApiOperation(value = "addHost", notes = "Add a new host inside the PNI")
			@ApiResponses(value = {@ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 403, message = "Forbidden: host already exist")})
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Response addHost(Host hostAdd){
        //Set self URI
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(hostAdd.getId());
        URI self = builder.build();
        try{
        	if(service.addHost(hostAdd) == null)
                throw new ForbiddenException();
        }catch(Exception e){
        	throw e;
        }

        return Response.created(self).entity(hostAdd).build();
	}

    @DELETE
    @Path("/hosts/host/{id}")
    @ApiOperation(value = "deleteHost", notes = "Delete a host inside the PNI")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Host not Found")})
    public void deleteHost(@PathParam("id") String hostID) {
        try{
            if(service.deleteHost(hostID) == null)
                throw new NotFoundException();
        }catch (Exception e) {
            throw e;
        }
    }

    @PUT
    @Path("/hosts/host")
    @ApiOperation(value = "modifyHost", notes = "Modify attribute of host inside the PNI")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Host not found"),
            @ApiResponse(code = 500, message = "Internal Error")})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Host modifyHost(Host hostMod){
        Host host = null;

        try{
            host = service.modifyHost(hostMod);
            if(host == null)
                throw new NotFoundException();
            if(host == hostMod)
                throw new InternalServerErrorException();
        }
        catch (Exception e){
            throw e;
        }

        return host;
    }

    @GET
    @Path("/connections")
    @ApiOperation(value = "getConnections", notes = "Read all the connections inside the PNI")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Connections getConnections() {
        return service.getConnections();
    }
    
    @POST
    @Path("/connections")
    @ApiOperation(value = "addConnections", notes = "Add a list of connections inside the PNI")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 403, message = "Forbidden: connection already exist")})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response addConnections(Connections connectionsAdd){
        //Set self URI
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path("connections");
        URI self = builder.build();

        try{
            if(service.addConnections(connectionsAdd) == null)
                throw new ForbiddenException();
        }catch (Exception e) {
            throw e;
        }

        return Response.created(self).entity(connectionsAdd).build();
    }

    @GET
    @Path("/connections/connection/{src}&{dst}")
    @ApiOperation(value = "getConnectionInfo", notes = "Read a connection's information")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Connection not Found")})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response getConnectionInfo(@PathParam("src") String connectionSrc, @PathParam("dst") String connectionDst) {
        Connection connection = service.getConnectionInfo(connectionSrc, connectionDst);
        try{
        	if(connection == null)
                throw new NotFoundException();
        }catch(Exception e){
        	throw e;
        }
      
        return Response.status(Status.OK).entity(connection).build();
    }

    @POST
    @Path("/connections/connection")
    @ApiOperation(value = "addConnection", notes = "Add a new connection inside the PNI")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 403, message = "Forbidden: connection already exist")})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response addConnection(Connection connectionAdd){
        //Set self URI
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(connectionAdd.getSourceHost().concat(connectionAdd.getDestHost()));
        URI self = builder.build();

        try{
            if(service.addConnection(connectionAdd) == null)
                throw new ForbiddenException();
        }catch (Exception e) {
            throw e;
        }

        return Response.created(self).entity(connectionAdd).build();
    }

    @DELETE
    @Path("/connections/connection/{src}&{dst}")
    @ApiOperation(value = "deleteConnection", notes = "Delete a connection inside the PNI")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Connection not Found")})
    public void deleteConnection(@PathParam("src") String connectionSrc, @PathParam("dst") String connectionDst) {
        try{
            if(service.deleteConnection(connectionSrc, connectionDst) == null)
                throw new NotFoundException();
        }catch (Exception e) {
            throw e;
        }
    }

    @PUT
    @Path("/connections/connection")
    @ApiOperation(value = "modifyConnection", notes = "Modify connection between host inside the PNI")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Connection not found"),
            @ApiResponse(code = 500, message = "Internal Error")})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Connection modifyConnection(Connection connectionMod){
        Connection connection = null;
        try{
            connection = service.modifyConnection(connectionMod);
            if(connection == null)
                throw new NotFoundException();
            if(connection == connectionMod)
                throw new InternalServerErrorException();
        }
        catch (Exception e){
            throw e;
        }

        return connection;
    }
}