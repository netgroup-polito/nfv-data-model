package it.polito.dp2.rest.nfv.resources;

import it.polito.dp2.rest.nfv.jaxb.Graph;
import it.polito.dp2.rest.nfv.jaxb.Node;
import it.polito.dp2.rest.nfv.jaxb.VNFDependency;
import it.polito.dp2.rest.nfv.services.VnfdependencyServices;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.net.URI;

@Path("ns/nsd/{nsdID}/vnfdependency")
@Api(value = "ns/nsd/{nsdID}/vnfdependency")
public class VnfdependencyResources {
    public UriInfo uriInfo;

    private VnfdependencyServices service = new VnfdependencyServices();

    public VnfdependencyResources(@Context UriInfo uriInfo) {
        this.uriInfo = uriInfo;
    }

    @GET
    @ApiOperation(value = "getVNFDependency", notes = "Read the VNFDependency data")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public VNFDependency getVNFDependency(@PathParam("nsdID") String nsdID) {
        return service.getVNFDependency(nsdID);
    }

    @POST
    @ApiOperation(value = "addVNFDependency", notes = "Add a new VNFDependency")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 500, message = "Internal Error")})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response addVNFDependency(@PathParam("nsdID") String nsdID, VNFDependency vnfDependency) {
        //Set self URI
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(nsdID);
        URI self = builder.build();

        try{
            if(service.addVNFDependency(nsdID, vnfDependency) == null)
                throw new InternalServerErrorException();
        }catch (Exception e) {
            throw e;
        }

        return Response.created(self).entity(vnfDependency).build();
    }

    @DELETE
    @ApiOperation(value = "deleteVNFDependency", notes = "Clear the VNFDependency")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal Error"),})
    public void deleteVNFDependency(@PathParam("nsdID") String nsdID) {
        try{
            if(service.deleteVNFDependency(nsdID) == null)
                throw new InternalServerErrorException();
        }catch (Exception e) {
            throw e;
        }
    }

    @GET
    @Path("/graph/{graphID}")
    @ApiOperation(value = "getGraph", notes = "Read a certain Graph")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
    		@ApiResponse(code = 404, message = "Graph not Found")})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Graph getGraph(@PathParam("nsdID") String nsdID, @PathParam("graphID") Long graphID) {
    	Graph graph = new Graph();
        try{
        	if((graph=service.getGraph(nsdID, graphID))==null)
        		throw new NotFoundException();
        }catch(Exception e){
        	throw e;
        }
        
        return graph;
    }

    @POST
    @Path("/graph")
    @ApiOperation(value = "addGraph", notes = "Add a new Graph")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created"),
    		@ApiResponse(code = 403, message = "Forbidden: graph already exist")})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response addGraph(@PathParam("nsdID") String nsdID, Graph graph) {
        //Set self URI
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(graph.getId().toString());
        URI self = builder.build();

        try{
            if(service.addGraph(nsdID, graph) == null)
                throw new ForbiddenException();
        }catch (Exception e) {
            throw e;
        }

        return Response.created(self).entity(graph).build();
    }

    @DELETE
    @Path("/graph/{graphID}")
    @ApiOperation(value = "deleteGraph", notes = "Remove a certain graph")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
    		@ApiResponse(code = 404, message = "Graph not Found")})
    public void deleteGraph(@PathParam("nsdID") String nsdID, @PathParam("graphID") Long graphID) {
        try{
            if(service.deleteGraph(nsdID, graphID) == null)
                throw new NotFoundException();
        }catch (Exception e) {
            throw e;
        }
    }

    @GET
    @Path("/graph/{graphID}/node/{nodeID}")
    @ApiOperation(value = "getNode", notes = "Read a node of a certain Graph")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Node getNode(@PathParam("nsdID") String nsdID, @PathParam("graphID") Long graphID, @PathParam("nodeID") String nodeName) {
        Node node = new Node();
        	
    	try{
        	if((node = service.getNode(nsdID, graphID, nodeName)) == null)
        		throw new NotFoundException();
        }catch (Exception e) {
            throw e;
        }
        return node;
    }

    @POST
    @Path("/graph/{graphID}/node")
    @ApiOperation(value = "addNode", notes = "Add a new Node")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Error")})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response addNode(@PathParam("nsdID") String nsdID, @PathParam("graphID") Long graphID, Node node) {
        //Set self URI
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(node.getName());
        URI self = builder.build();

        try{
            if(service.addNode(nsdID, graphID, node) == null)
                throw new ForbiddenException();
        }catch (Exception e) {
            throw e;
        }

        return Response.created(self).entity(node).build();
    }

    @DELETE
    @Path("/graph/{graphID}/node/{nodeID}")
    @ApiOperation(value = "deleteVNFDependency", notes = "Clear the VNFDependency")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Internal Error"),})
    public void deleteNode(@PathParam("nsdID") String nsdID, @PathParam("graphID") Long graphID, @PathParam("nodeID") String nodeName) {
        try{
            if(service.deleteNode(nsdID, graphID, nodeName) == null)
                throw new NotFoundException();
        }catch (Exception e) {
            throw e;
        }
    }

    @PUT
    @Path("/graph/{graphID}/node")
    @ApiOperation(value = "modifyNode", notes = "Modify a node of a certain graph")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Node not found"),
            @ApiResponse(code = 500, message = "Internal Error")})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Node modifyNode(@PathParam("nsdID") String nsdID, @PathParam("graphID") Long graphID, Node nodeMod) {
        Node node = null;
        try{
            node = service.modifyNode(nsdID, graphID, nodeMod);
            if(node == null)
                throw new NotFoundException();
            if(node == nodeMod)
                throw new InternalServerErrorException();
        }
        catch (Exception e){
            throw e;
        }

        return node;
    }
}