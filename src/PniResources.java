import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import jaxb.Connections;
import jaxb.Host;
import jaxb.Hosts;
import jaxb.PNI;

@Path("/pni")
@Api(value = "/pni")
public class PniResources {

    private PniServices service = new PniServices();

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
    @Path("/{id}")
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

    @GET
    @Path("/connection")
    @ApiOperation(value = "getConnections", notes = "Read all the connections inside the PNI")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Connections getConnections() {
        return service.getConnections();
    }

}