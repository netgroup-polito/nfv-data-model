package resources;
import services.PniServices;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import jaxb.*;

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

    @GET
    @Path("/host")
    @ApiOperation(value = "getHosts", notes = "Read all the hosts inside the PNI")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Hosts getHosts() {
        return service.getHosts();
    }

    @GET
    @Path("/host/{id}")
    @ApiOperation(value = "getHostInfo", notes = "Read a host's information")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Host not Found")})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response getHostInfo(@PathParam("id") String hostID) {
        Host host = service.getHostInfo(hostID);
        if(host == null)
            throw new NotFoundException("Host not found");
        return Response.status(Status.OK).entity(host).build();
    }

    @POST
    @Path("/host")
    @ApiOperation(value = "addHost", notes = "Add a new host inside the PNI")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "Forbidden: host already exist"),
            @ApiResponse(code = 500, message = "Internal Error")})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response addHost(Host hostAdd){
        Host host = new Host();
        host.setId(hostAdd.getId());
        //Set self URI
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(hostAdd.getId());
        URI self = builder.build();

        try{
            if(service.addHost(hostAdd) == null)
                throw new ForbiddenException("Host just exist");
        }catch (Exception e) {
            throw new InternalServerErrorException();
        }

        return Response.created(self).entity(host).build();
    }

    @DELETE
    @Path("/host/{id}")
    @ApiOperation(value = "deleteHost", notes = "Delete a host inside the PNI")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Host not Found"),
            @ApiResponse(code = 500, message = "Internal Error"),})
    public void deleteVehicle(@PathParam("id") String hostID) {
        try{
            if(service.deleteHost(hostID) == null)
                throw new NotFoundException("Host not found");
        }catch (ForbiddenException | BadRequestException e) {
            throw e;
        }
    }

    @PUT
    @Path("/host")
    @ApiOperation(value = "modifyHost", notes = "Modify attribute of host inside the PNI")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "No content"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "Forbidden: wrong host"),
            @ApiResponse(code = 404, message = "Host not found"),
            @ApiResponse(code = 500, message = "Internal Error")})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Host modifyHost(Host hostMod){
        Host host = null;

        try{
            host = service.modifyHost(hostMod);
            if(host == null)
                throw new NotFoundException("Host not found");
            if(host == hostMod)
                throw new InternalServerErrorException();
        }
        catch (Exception e){
            throw e;
        }

        return host;
    }

    @GET
    @Path("/connection")
    @ApiOperation(value = "getConnections", notes = "Read all the connections inside the PNI")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Connections getConnections() {
        return service.getConnections();
    }

    @GET
    @Path("/connection/{src}/{dst}")
    @ApiOperation(value = "getConnectionInfo", notes = "Read a connection's information")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Connection not Found")})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response getConnectionInfo(@PathParam("src") String connectionSrc, @PathParam("dst") String connectionDst) {
        Connection connection = service.getConnectionInfo(connectionSrc, connectionDst);
        if(connection == null)
            throw new NotFoundException("Connection not found");
        return Response.status(Status.OK).entity(connection).build();
    }

    @POST
    @Path("/connection")
    @ApiOperation(value = "addConnection", notes = "Add a new connection inside the PNI")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "Forbidden: connection already exist"),
            @ApiResponse(code = 500, message = "Internal Error")})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response addConnection(Connection connectionAdd){
        Connection connection = new Connection();
        connection.setSourceHost(connectionAdd.getSourceHost());
        connection.setDestHost(connectionAdd.getDestHost());
        //Set self URI
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(connectionAdd.getSourceHost().concat(connectionAdd.getDestHost()));
        URI self = builder.build();

        try{
            if(service.addConnection(connection) == null)
                throw new ForbiddenException("Connection just exists");
        }catch (Exception e) {
            throw new InternalServerErrorException();
        }

        return Response.created(self).entity(connection).build();
    }

    @DELETE
    @Path("/connection/{src}/{dst}")
    @ApiOperation(value = "deleteConnection", notes = "Delete a connection inside the PNI")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Connection not Found"),
            @ApiResponse(code = 500, message = "Internal Error"),})
    public void deleteVehicle(@PathParam("src") String connectionSrc, @PathParam("dst") String connectionDst) {
        try{
            if(service.deleteConnection(connectionSrc, connectionDst) == null)
                throw new NotFoundException("Host not found");
        }catch (ForbiddenException | BadRequestException e) {
            throw e;
        }
    }

    @PUT
    @Path("/connection")
    @ApiOperation(value = "cmodifyConnection", notes = "Modify connection between host inside the PNI")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "No content"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "Forbidden: wrong connection"),
            @ApiResponse(code = 404, message = "Connection not found"),
            @ApiResponse(code = 500, message = "Internal Error")})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Connection modifyConnection(Connection connectionMod){
        Connection connection = null;
        try{
            connection = service.modifyConnection(connectionMod);
            if(connection == null)
                throw new NotFoundException("Connection not found");
            if(connection == connectionMod)
                throw new InternalServerErrorException();
        }
        catch (Exception e){
            throw e;
        }

        return connection;
    }
}