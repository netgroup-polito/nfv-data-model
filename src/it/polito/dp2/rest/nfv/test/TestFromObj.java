package it.polito.dp2.rest.nfv.test;

import org.junit.Assert;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import it.polito.dp2.rest.nfv.test.DataToObj;
import it.polito.dp2.rest.nfv.jaxb.Cache;
import it.polito.dp2.rest.nfv.jaxb.Connection;
import it.polito.dp2.rest.nfv.jaxb.ConnectionPoint;
import it.polito.dp2.rest.nfv.jaxb.ConnectionPointType;
import it.polito.dp2.rest.nfv.jaxb.ConnectionPoints;
import it.polito.dp2.rest.nfv.jaxb.Connections;
import it.polito.dp2.rest.nfv.jaxb.Dependency;
import it.polito.dp2.rest.nfv.jaxb.Elements;
import it.polito.dp2.rest.nfv.jaxb.Fieldmodifier;
import it.polito.dp2.rest.nfv.jaxb.Firewall;
import it.polito.dp2.rest.nfv.jaxb.Flavours;
import it.polito.dp2.rest.nfv.jaxb.FunctionalTypes;
import it.polito.dp2.rest.nfv.jaxb.Graph;
import it.polito.dp2.rest.nfv.jaxb.Host;
import it.polito.dp2.rest.nfv.jaxb.Hosts;
import it.polito.dp2.rest.nfv.jaxb.NFV;
import it.polito.dp2.rest.nfv.jaxb.NS;
import it.polito.dp2.rest.nfv.jaxb.NSD;
import it.polito.dp2.rest.nfv.jaxb.NetworkForwardingPaths;
import it.polito.dp2.rest.nfv.jaxb.Node;
import it.polito.dp2.rest.nfv.jaxb.NodeConnection;
import it.polito.dp2.rest.nfv.jaxb.PNF;
import it.polito.dp2.rest.nfv.jaxb.PNFD;
import it.polito.dp2.rest.nfv.jaxb.PNI;
import it.polito.dp2.rest.nfv.jaxb.PName;
import it.polito.dp2.rest.nfv.jaxb.Property;
import it.polito.dp2.rest.nfv.jaxb.PropertyDefinition;
import it.polito.dp2.rest.nfv.jaxb.ServiceDeploymentFlavour;
import it.polito.dp2.rest.nfv.jaxb.TestAccessType;
import it.polito.dp2.rest.nfv.jaxb.TypeOfHost;
import it.polito.dp2.rest.nfv.jaxb.VDU;
import it.polito.dp2.rest.nfv.jaxb.VNF;
import it.polito.dp2.rest.nfv.jaxb.VNFD;
import it.polito.dp2.rest.nfv.jaxb.VNFDependency;
import it.polito.dp2.rest.nfv.jaxb.VNFFGD;
import it.polito.dp2.rest.nfv.jaxb.Webclient;
import it.polito.dp2.rest.nfv.jaxb.Webserver;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestFromObj {

    private String baseUrl = "http://localhost:8080/Rest-nfv";
    private static final Logger LOGGER = Logger.getLogger(TestFromObj.class.getName());
    private DataToObj util = new DataToObj();
    
    private NFV getTestSample(){
    	/* Create the Test sample Manually */
    	NFV nfv = new NFV();
    	PNI pni = new PNI();
    	NS ns = new NS();
    	
    	/* Entry a PNI */
    	pni.setHosts(new Hosts());
    	pni.setConnections(new Connections());
    	/*Creation of Hosts*/
    	Host h1 = util.getHostFromData("host1", TypeOfHost.CLIENT, "nodeA", 0, 1000, 2, 10, 4, 10, 0, 20, null,null);
    	Host h2 = util.getHostFromData("host2", TypeOfHost.SERVER, "nodeB", 0, 1000, 2, 10, 4, 10, 0, 20, null,null);
    	Host h3 = util.getHostFromData("hostA", TypeOfHost.MIDDLEBOX, "nodeA", 0, 1000, 2, 10, 4, 10, 0, 20, null,null);
    	Host h4 = util.getHostFromData("hostB", TypeOfHost.MIDDLEBOX, "nodeB", 0, 1000, 2, 10, 4, 10, 0, 20, null,null);
    	Host h5 = util.getHostFromData("hostC", TypeOfHost.MIDDLEBOX, null, 1, 3000, 16, 16, 50, 10, 0, 20, null,null);
    	Host h6 = util.getHostFromData("hostD", TypeOfHost.MIDDLEBOX, null, 2, 4000, 4, 16, 20, 10, 0, 20, null,null);
    	Host h7 = util.getHostFromData("hostE", TypeOfHost.MIDDLEBOX, null, 3, 3000, 8, 16, 10, 10, 0, 20, null,null);
    	pni.getHosts().getHost().add(h1);
    	pni.getHosts().getHost().add(h2);
    	pni.getHosts().getHost().add(h3);
    	pni.getHosts().getHost().add(h4);
    	pni.getHosts().getHost().add(h5);
    	pni.getHosts().getHost().add(h6);
    	pni.getHosts().getHost().add(h7);
    	/*Creation of Connections*/
    	Connection cn1 = util.getConnectionFromData("hostA", "hostB", 0);
    	Connection cn2 = util.getConnectionFromData("hostC", "hostD", 0);
    	Connection cn3 = util.getConnectionFromData("hostB", "hostC", 0);
    	Connection cn4 = util.getConnectionFromData("hostD", "hostE", 0);
    	pni.getConnections().getConnection().add(cn1);
    	pni.getConnections().getConnection().add(cn2);
    	pni.getConnections().getConnection().add(cn3);
    	pni.getConnections().getConnection().add(cn4);
    	
    	nfv.setPNI(pni);
    	
    	/* Entry a NSD */
    	NSD nsd = new NSD();
		nsd.setId("0");
		
		// ENTRY A SOME VNF COMPRENSIVE OF EACH FIELD
		VNF vnf = new VNF();
		VNFD vnfd1 = util.getVnfdFromData("0","POLITO","1.0","0","1",TestAccessType.PASSIVE);
		VDU vdu1 = util.getVduFromData("0","Linux_Server",1000,1,1000,30,100,10,100);
		VDU vdu2 = util.getVduFromData("1","Linux_Server",500,2,500,30,100,10,100);
		vnfd1.getVDU().add(vdu1);
		vnfd1.getVDU().add(vdu2);
		Dependency dep = util.getDependencyFromData("0","1");
		vnfd1.setDependency(dep);
		vnf.getVNFD().add(vnfd1);
		VNFD vnfd2 = util.getVnfdFromData("1","POLIMI","0.5","0","1",TestAccessType.PASSIVE);
		VDU vdu3 = util.getVduFromData("2","Linux_Server",500,2,500,30,100,10,100);
		vnfd2.getVDU().add(vdu3);
		vnf.getVNFD().add(vnfd2);
		nsd.setVNF(vnf);
		
		// ENTRY A VNF DEPENDENCY
		VNFDependency vnfdep = new VNFDependency();
	    Graph g1 = new Graph();
	    g1.setId((long) 0);
	    
	    Webclient web1 = new Webclient();
	    web1.setNameWebServer("nodeB");
	    Node n1 = util.getNodeClientFromData("0","nodeA",FunctionalTypes.WEBCLIENT,"confA","A simple description",web1);

	    Webserver web2 = new Webserver();
	    web2.setName("nodeB");
	    Node n2 = util.getNodeServerFromData("1","nodeB",FunctionalTypes.WEBSERVER,"confB","A simple description",web2);
	    
	    Firewall f1 = new Firewall();
	    Elements el1 = new Elements();
	    el1.setSource("nodeC");
	    el1.setDestination("nodeC");
	    f1.getElements().add(el1);
	    Node n3= util.getNodeFirewallFromData("2","nodeC",FunctionalTypes.FIREWALL,"confC","A simple description",f1);
	    
	    Cache c1 = new Cache();
	    c1.getResource().add("nodeA");
	    c1.getResource().add("nodeB");
	    Node n4 = util.getNodeCacheFromData("3","nodeD",FunctionalTypes.CACHE,"confD","A simple description",c1);
	
	    Fieldmodifier fm1 = new Fieldmodifier();
	    fm1.setName("name");
	    Node n5 = util.getNodeCacheFromData("4","nodeE",FunctionalTypes.FIELDMODIFIER,"confE","A simple description",c1);
	    
	    g1.getNode().add(n1);
	    g1.getNode().add(n2);
	    g1.getNode().add(n3);
	    g1.getNode().add(n4);
	    g1.getNode().add(n5);
	    vnfdep.getGraph().add(g1);
	    nsd.setVNFDependency(vnfdep);
	    
	    // ENTRY A PROPERTY_DEFINITION
	    PropertyDefinition pd = new PropertyDefinition();
	    Property prop1 = util.getNodePropertyHTTPFromData(g1.getId(),PName.ISOLATION_PROPERTY,"nodeA","nodeB","5000","80","weapons");	    
	    Property prop2 = util.getNodePropertyPOP3FromData(g1.getId(),PName.REACHABILITY_PROPERTY,"nodeC","nodeD","3000","110","polito","weapons");
	    pd.getProperty().add(prop1);
	    pd.getProperty().add(prop2);
	    nsd.setPropertyDefinition(pd);
	    
	    // ENTRY A VNFFGD 
	    VNFFGD vnffgd = new VNFFGD();
	  
	    NetworkForwardingPaths nfp1 = new NetworkForwardingPaths();
	    nfp1.setId("0");
	    nfp1.setNEndpoint(4);
	    nfp1.setNVl(2);
	    nfp1.setVnffgdSecurity("SHA-256");
	    NodeConnection nc11 = new NodeConnection();
	    nc11.setNodeRef("nodeA");
	    nfp1.getNodeConnection().add(nc11);
	    NodeConnection nc12 = new NodeConnection();
	    nc12.setNodeRef("nodeB");
	    nfp1.getNodeConnection().add(nc12);
	    NodeConnection nc13 = new NodeConnection();
	    nc13.setNodeRef("nodeC");
	    nfp1.getNodeConnection().add(nc13);
	    vnffgd.getNetworkForwardingPaths().add(nfp1);
	    
	    NetworkForwardingPaths nfp2 = new NetworkForwardingPaths();
	    nfp2.setId("1");
	    NodeConnection nc21 = new NodeConnection();
	    nc21.setNodeRef("nodeE");
	    nfp2.getNodeConnection().add(nc21);
	    NodeConnection nc22 = new NodeConnection();
	    nc22.setNodeRef("nodeD");
	    nfp2.getNodeConnection().add(nc22);
	    vnffgd.getNetworkForwardingPaths().add(nfp2);
	    
	    NetworkForwardingPaths nfp3 = new NetworkForwardingPaths();
	    nfp3.setId("2");
	    NodeConnection nc31 = new NodeConnection();
	    nc31.setNodeRef("nodeC");
	    nfp3.getNodeConnection().add(nc31);
	    NodeConnection nc32 = new NodeConnection();
	    nc32.setNodeRef("nodeE");
	    nfp3.getNodeConnection().add(nc32);
	    vnffgd.getNetworkForwardingPaths().add(nfp3);
	    
	    nsd.setVNFFGD(vnffgd);
	    
	    // ENTRY A PNFD 
	    PNF pnf = new PNF();
	    PNFD pnfd1 = util.getPnfdFromData("0","fastWeb","1.2","DHCP","0",ConnectionPointType.PHYSICAL_PORT);
	    PNFD pnfd2 = util.getPnfdFromData("1","fastWeb","1.2","DHCP","1",ConnectionPointType.ENDPOINT);
	    pnf.getPNFD().add(pnfd1);
	    pnf.getPNFD().add(pnfd2);
	    nsd.setPNF(pnf);
	    
	    // ENTRY A Flavours 
	    Flavours flavours = new Flavours();
	    ServiceDeploymentFlavour sdf1 = util.getServDeplFlavFromData("0", "call_per_second", 300);
	    flavours.getServiceDeploymentFlavour().add(sdf1);
	    nsd.setFlavours(flavours);
	    
	    // ENTRY A ConnectionPoints 
	    ConnectionPoints nsdCps = new ConnectionPoints();
	    ConnectionPoint nsdCp = util.getConnectionPointFromData("0",ConnectionPointType.ENDPOINT);
	    nsdCps.getConnectionPoint().add(nsdCp);
	    nsd.setConnectionPoints(nsdCps);
	    
	    // ENTRY A ParsingString 
	    nsd.setParsingString("30L?");
	    
	    // ENTRY THE NSD CREATED PREVIOUSLY
	    ns.getNSD().add(nsd);
		nfv.setNS(ns);
		
		return nfv;
    }

    @Test
    public void post_wrong_pni() {
        String path = "/nfv/pni";
        
        NFV nfv = getTestSample();
        /* insert error in PNI */
        //duplicate host 1
        Host h1 = util.getHostFromData("host1", TypeOfHost.CLIENT, "nodeA", 0, 1000, 2, 10, 4, 10, 0, 20, null,null);
        nfv.getPNI().getHosts().getHost().add(h1);

        LOGGER.log(Level.FINEST, "[!] NFV{post}(wrong_pni) test [:Started]");

        WebResource resource  = Client.create().resource(baseUrl.concat(path));
        

        ClientResponse clientResponse = resource.accept(MediaType.APPLICATION_XML_TYPE).
        		entity(nfv.getPNI()).post(ClientResponse.class);

        int statusCode = clientResponse.getStatus();
        // Assert that correct status code is returned.
        Assert.assertEquals(400, statusCode);

        LOGGER.log(Level.FINEST, "[!] NFV{post}(wrong_pni) test [:Ended]");
    }

    @Test
    public void post_fine_pni() {
        String path = "/nfv/pni";

        NFV nfv = getTestSample();
        
        LOGGER.log(Level.FINEST, "[!] NFV{post}(pni) test [:Started]");

        WebResource resource  = Client.create().resource(baseUrl.concat(path));
        

        ClientResponse clientResponse = resource.accept(MediaType.APPLICATION_XML_TYPE).
        		entity(nfv.getPNI()).post(ClientResponse.class);

        int statusCode = clientResponse.getStatus();
        // Assert that correct status code is returned.
        Assert.assertEquals(201, statusCode);

        LOGGER.log(Level.FINEST, "[!] NFV{post}(pni) test [:Ended]");
    }

    @Test
    public void post_wrong_ns() {
        String path = "/nfv/ns";
        
        NFV nfv = getTestSample();
        /* insert error in PNI */
        // duplication of node1[NSD/vnfDependency]
        Webclient web1 = new Webclient();
	    web1.setNameWebServer("nodeB");
        Node n1 = util.getNodeClientFromData("0","nodeA",FunctionalTypes.WEBCLIENT,"confA","A simple description",web1);
	    nfv.getNS().getNSD().get(0).getVNFDependency().getGraph().get(0).getNode().add(n1);

        LOGGER.log(Level.FINEST, "[!] NFV{post}(wrong_ns) test [:Started]");

        WebResource resource  = Client.create().resource(baseUrl.concat(path));
        

        ClientResponse clientResponse = resource.accept(MediaType.APPLICATION_XML_TYPE).
        		entity(nfv.getNS()).post(ClientResponse.class);

        int statusCode = clientResponse.getStatus();
        // Assert that correct status code is returned.
        Assert.assertEquals(400, statusCode);

        LOGGER.log(Level.FINEST, "[!] NFV{post}(wrong_ns) test [:Ended]");
    }

    @Test
    public void post_fine_ns() {
        String path = "/nfv/ns";

        NFV nfv = getTestSample();
        
        LOGGER.log(Level.FINEST, "[!] NFV{post}(ns) test [:Started]");

        WebResource resource  = Client.create().resource(baseUrl.concat(path));
        

        ClientResponse clientResponse = resource.accept(MediaType.APPLICATION_XML_TYPE).
        		entity(nfv.getNS()).post(ClientResponse.class);

        int statusCode = clientResponse.getStatus();
        // Assert that correct status code is returned.
        Assert.assertEquals(201, statusCode);

        LOGGER.log(Level.FINEST, "[!] NFV{post}(ns) test [:Ended]");

    }

    @Test
    public void delete_pni() {
        String path = "/nfv/pni";

        LOGGER.log(Level.FINEST, "[!] NFV{delete}(pni) test [:Started]");

        javax.ws.rs.client.Client client = ClientBuilder.newClient();

        WebTarget enterNS = client.target(baseUrl).path(path);
        LOGGER.log(Level.FINEST,"url:"+enterNS.toString());

        Response response = enterNS.request(MediaType.APPLICATION_XML)
                .delete();

        int statusCode = response.getStatus();
        // Assert that correct status code is returned.
        Assert.assertEquals(204, statusCode);

        LOGGER.log(Level.FINEST, "[!] NFV{delete}(pni) test [:Ended]");

        client.close();
    }

    @Test
    public void delete_ns() {
        String path = "/nfv/ns";

        LOGGER.log(Level.FINEST, "[!] NFV{delete}(ns) test [:Started]");

        javax.ws.rs.client.Client client = ClientBuilder.newClient();

        WebTarget enterNS = client.target(baseUrl).path(path);
        LOGGER.log(Level.FINEST,"url:"+enterNS.toString());

        Response response = enterNS.request(MediaType.APPLICATION_XML)
                .delete();

        int statusCode = response.getStatus();
        // Assert that correct status code is returned.
        Assert.assertEquals(204, statusCode);

        LOGGER.log(Level.FINEST, "[!] NFV{delete}(ns) test [:Ended]");

        client.close();
    }
   

}