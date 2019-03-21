package it.polito.dp2.rest.nfv.client;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.polito.dp2.rest.nfv.jaxb.*;
import it.polito.dp2.rest.nfv.jaxb.Dependency.Relation;

public class Client {
	
	private String baseUrl;
	private static final Logger LOGGER = Logger.getLogger(Client.class.getName());
	
	public Client() {
		Handler handlerObj = new ConsoleHandler();
		handlerObj.setLevel(Level.ALL);
		LOGGER.addHandler(handlerObj);
		LOGGER.setLevel(Level.ALL);
		LOGGER.setUseParentHandlers(false);
		baseUrl = System.getProperty("it.polito.dp2.rest.nfv.URL");
		if(baseUrl == null)
			//make sure that XXXXXXX will be the name of your project
			//or client will not work properly
			baseUrl = "http://localhost:8080/XXXXXXX/nfv";	
	}
	
	private void enterHost(String hostId, TypeOfHost type, String fixEnd, int maxVnf,
							int cores, int cpu, int nOfOp,
							int mem, int diskSt, int virtMem,
							int bandWidth, 
							List<VNodeRefType> sup_vnfd, PNodeRefType sup_pnfd) 
									throws ForbiddenException {
		
		/*Creation of Hosts*/
		Host host = new Host();
		host.setId(hostId);
		host.setType(type);
		host.setFixedEndpoint(fixEnd);
		host.setMaxVNF(maxVnf);
		ComputationalPropertiesType cp = new ComputationalPropertiesType();
		cp.setCores(cores);
		cp.setCpu(cpu);
		cp.setNOfOperations(nOfOp);
		host.setComputationalProperties(cp);
		MemoryPropertiesType mp = new MemoryPropertiesType();
		mp.setDiskStorage(mem);
		mp.setMemory(diskSt);
		mp.setVirtualMemRes(virtMem);
		host.setMemoryProperties(mp);
		NetworkPropertiesType np = new NetworkPropertiesType();
		np.setBandwidth(bandWidth);
		host.setNetworkProperties(np);
		if (sup_pnfd != null)
			host.getPNodeRef().add(sup_pnfd);
		else if(sup_vnfd != null)
			host.getVNodeRef().addAll(sup_vnfd);
		
		String path = "pni/hosts/host"; 
		javax.ws.rs.client.Client client = ClientBuilder.newClient();
		WebTarget enterHost = client.target(baseUrl).path(path);
		Response responseEntered = enterHost.request(MediaType.APPLICATION_XML)
				  .accept(MediaType.APPLICATION_XML)
				  .post(Entity.entity(host, MediaType.APPLICATION_XML));
		
		if(responseEntered.getStatus() == 201){
			Host returnedHost = responseEntered.readEntity(Host.class);
			LOGGER.log(Level.FINEST, "Successful: Inserted Host {".concat(returnedHost.toString()+"}"));
		}else if(responseEntered.getStatus() == 403){
			client.close();
			throw new ForbiddenException();
		}
		
		client.close();
	}

	private void enterConnection(String hostSrc, String hostDest, int avgLat){
		
		/*Creation of Hosts*/
		Connection connection = new Connection();
		connection.setSourceHost(hostSrc);
		connection.setDestHost(hostDest);
		connection.setAvgLatency(avgLat);
		
		String path = "pni/connections/connection"; 
		javax.ws.rs.client.Client client = ClientBuilder.newClient();
		WebTarget enterHost = client.target(baseUrl).path(path);
		Response responseEntered = enterHost.request(MediaType.APPLICATION_XML)
		  .accept(MediaType.APPLICATION_XML)
		  .post(Entity.entity(connection, MediaType.APPLICATION_XML));
		
		if(responseEntered.getStatus() == 201){
			Connection returnedConn = responseEntered.readEntity(Connection.class);
			LOGGER.log(Level.FINEST, "Succesful: Inserted Connection ".concat(returnedConn.toString()+"}"));
		}else if(responseEntered.getStatus() == 403){
			client.close();
				throw new ForbiddenException();
		}
		
		client.close();
	}

	private void enterNS(){
		
		NS ns = new NS();
		
		String path = "/ns"; 
		javax.ws.rs.client.Client client = ClientBuilder.newClient();
		WebTarget enterHost = client.target(baseUrl).path(path);
		Response responseEntered = enterHost.request(MediaType.APPLICATION_XML)
		  .accept(MediaType.APPLICATION_XML)
		  .post(Entity.entity(ns, MediaType.APPLICATION_XML));
		
		if(responseEntered.getStatus() == 201){
			NS returnedNS = responseEntered.readEntity(NS.class);
			LOGGER.log(Level.FINEST, "Succesful: Inserted NS ".concat(returnedNS.toString()+"}"));
		}else if(responseEntered.getStatus() == 403){
			client.close();
				throw new ForbiddenException();
		}
		
		client.close();
	}

	private void enterNSD(NSD nsdToAdd){
		
		NSD nsd = new NSD();
		
		nsd.setId(nsdToAdd.getId());
		nsd.setVNFDependency(nsdToAdd.getVNFDependency());
		nsd.setPropertyDefinition(nsdToAdd.getPropertyDefinition());
		nsd.setVNF(nsdToAdd.getVNF());
		nsd.setVNFFGD(nsdToAdd.getVNFFGD());
		nsd.setPNF(nsdToAdd.getPNF());
		nsd.setFlavours(nsdToAdd.getFlavours());
		nsd.setConnectionPoints(nsdToAdd.getConnectionPoints());
		nsd.setParsingString(nsdToAdd.getParsingString());
		
		String path = "/ns/nsd"; 
		javax.ws.rs.client.Client client = ClientBuilder.newClient();
		WebTarget enterHost = client.target(baseUrl).path(path);
		Response responseEntered = enterHost.request(MediaType.APPLICATION_XML)
		  .accept(MediaType.APPLICATION_XML)
		  .post(Entity.entity(nsd, MediaType.APPLICATION_XML));
		
		if(responseEntered.getStatus() == 201){
			NSD returnedNSD = responseEntered.readEntity(NSD.class);
			LOGGER.log(Level.FINEST, "Succesful: Inserted NSD ".concat(returnedNSD.toString()+"}"));
		}else if(responseEntered.getStatus() == 403){
			client.close();
				throw new ForbiddenException();
		}
		
		client.close();
	}

	private void enterVNFD(String nsdID, String id, String vendor, String version,
						List<VDU> vdus, Dependency vduDep, List<VirtualLink> vlinks){
			
		VNFD vnfd = new VNFD();
		vnfd.setId(id);
		vnfd.setVendor(vendor);
		vnfd.setVersion(version);
		vnfd.getVDU().addAll(vdus);
		vnfd.setDependency(vduDep);
		vnfd.getVirtualLink().addAll(vlinks);
		
		String path = "ns/nsd/".concat(nsdID+"/vnf/vnfd"); 
		javax.ws.rs.client.Client client = ClientBuilder.newClient();
		WebTarget enterHost = client.target(baseUrl).path(path);
		Response responseEntered = enterHost.request(MediaType.APPLICATION_XML)
		  .accept(MediaType.APPLICATION_XML)
		  .post(Entity.entity(vnfd, MediaType.APPLICATION_XML));
		
		if(responseEntered.getStatus() == 201){
			VNFD returnedVNFD = responseEntered.readEntity(VNFD.class);
			LOGGER.log(Level.FINEST, "Succesful: Inserted VNDFD ".concat(returnedVNFD.toString()+"}"));
		}else if(responseEntered.getStatus() == 403){
			client.close();
				throw new ForbiddenException();
		}
		
		client.close();
	}

	private void enterPNFD(String nsdID, String id, String vendor, String version,
			String description, ConnectionPoint cp){

		PNFD pnfd = new PNFD();
		pnfd.setId(id);
		pnfd.setVendor(vendor);
		pnfd.setVersion(version);
		pnfd.setDescription(description);
		pnfd.setConnectionPoint(cp);
		
		String path = "ns/nsd/".concat(nsdID+"/pnf/pnfd"); 
		javax.ws.rs.client.Client client = ClientBuilder.newClient();
		WebTarget enterHost = client.target(baseUrl).path(path);
		Response responseEntered = enterHost.request(MediaType.APPLICATION_XML)
		  .accept(MediaType.APPLICATION_XML)
		  .post(Entity.entity(pnfd, MediaType.APPLICATION_XML));
		
		if(responseEntered.getStatus() == 201){
			PNFD returnedPNFD = responseEntered.readEntity(PNFD.class);
			LOGGER.log(Level.FINEST, "Succesful: Inserted PNFD ".concat(returnedPNFD.toString()+"}"));
		}else if(responseEntered.getStatus() == 403){
			client.close();
				throw new ForbiddenException();
		}
		
		client.close();
	}

	private void enterVNFFGD(String nsdID, List<NetworkForwardingPaths> nfps){
		
		VNFFGD vnffgd = new VNFFGD();
		vnffgd.getNetworkForwardingPaths().addAll(nfps);
		
		
		String path = "ns/nsd/".concat(nsdID+"/vnffgd"); 
		javax.ws.rs.client.Client client = ClientBuilder.newClient();
		WebTarget enterHost = client.target(baseUrl).path(path);
		Response responseEntered = enterHost.request(MediaType.APPLICATION_XML)
		  .accept(MediaType.APPLICATION_XML)
		  .post(Entity.entity(vnffgd, MediaType.APPLICATION_XML));
		
		if(responseEntered.getStatus() == 201){
			VNFFGD returnedVNFFGD = responseEntered.readEntity(VNFFGD.class);
			LOGGER.log(Level.FINEST, "Succesful: Inserted VNFFGD ".concat(returnedVNFFGD.toString()+"}"));
		}else if(responseEntered.getStatus() == 403){
			client.close();
				throw new ForbiddenException();
		}
		
		client.close();
	}

	private void enterVNF_dependency(String nsdID, List<Graph> dependecies ){

		VNFDependency vnf_dep = new VNFDependency();
		vnf_dep.getGraph().addAll(dependecies);
		
		String path = "ns/nsd/".concat(nsdID+"/vnfdependency"); 
		javax.ws.rs.client.Client client = ClientBuilder.newClient();
		WebTarget enterHost = client.target(baseUrl).path(path);
		Response responseEntered = enterHost.request(MediaType.APPLICATION_XML)
			.accept(MediaType.APPLICATION_XML)
			.post(Entity.entity(vnf_dep, MediaType.APPLICATION_XML));
		
		if(responseEntered.getStatus() == 201){
			VNFDependency returnedVNF_dep = responseEntered.readEntity(VNFDependency.class);
			LOGGER.log(Level.FINEST, "Succesful: Inserted VNF_Dependency ".concat(returnedVNF_dep.toString()+"}"));
		}else if(responseEntered.getStatus() == 403){
			client.close();
				throw new ForbiddenException();
		}
		
		client.close();
	}
	
	public void test (){
		/* ENTER A PNI COMPRENSIVE OF SEVERAL HOSTS AND CONNECTIONS */
		enterHost("host1", TypeOfHost.CLIENT, "nodeA", 0, 1000, 2, 10, 4, 10, 0, 20, null,null);
		enterHost("host2", TypeOfHost.SERVER, "nodeB", 0, 1000, 2, 10, 4, 10, 0, 20, null,null);
		enterHost("hostA", TypeOfHost.MIDDLEBOX, "nodeA", 0, 1000, 2, 10, 4, 10, 0, 20, null,null);
		enterHost("hostB", TypeOfHost.MIDDLEBOX, "nodeB", 0, 1000, 2, 10, 4, 10, 0, 20, null,null);
		enterHost("hostC", TypeOfHost.MIDDLEBOX, null, 1, 3000, 16, 16, 50, 10, 0, 20, null,null);
		enterHost("hostD", TypeOfHost.MIDDLEBOX, null, 2, 4000, 4, 16, 20, 10, 0, 20, null,null);
		enterHost("hostE", TypeOfHost.MIDDLEBOX, null, 3, 3000, 8, 16, 10, 10, 0, 20, null,null);
		
		enterConnection("hostA", "hostB", 0);
		enterConnection("hostC", "hostD", 0);
		enterConnection("hostB", "hostC", 0);
		enterConnection("hostD", "hostE", 0);
		
		enterNS();
		
		
		NSD nsd = new NSD();
		nsd.setId("0");
		
		// ENTRY A SOME VNF COMPRENSIVE OF EACH FIELD
		VNF vnf = new VNF();
		
		VNFD vnfd1 = new VNFD();
		vnfd1.setId("0");
		vnfd1.setVendor("POLITO");
		vnfd1.setVersion("1.0");
		VirtualLink vl1 = new VirtualLink();
		vl1.setSrc("0");
		vl1.setDst("1");
		vl1.setTestAccess(TestAccessType.PASSIVE);
		vnfd1.getVirtualLink().add(vl1);
		
		VDU vdu1 = new VDU();
		vdu1.setId("0");
		vdu1.setVmImage("Linux_Server");
		ComputationalPropertiesType cp1 = new ComputationalPropertiesType();
		cp1.setCores(1000);
		cp1.setCpu(1);
		cp1.setNOfOperations(1000);
		vdu1.setComputationalRequirements(cp1);
		MemoryPropertiesType mp1 = new MemoryPropertiesType();
		mp1.setDiskStorage(30);
		mp1.setMemory(100);
		mp1.setVirtualMemRes(10);
		vdu1.setMemoryRequirements(mp1);
		NetworkPropertiesType np1 = new NetworkPropertiesType();
		np1.setBandwidth(100);
		vdu1.setNetworkRequirements(np1);
		vnfd1.getVDU().add(vdu1);
		
		VDU vdu2 = new VDU();
		vdu2.setId("1");
		vdu2.setVmImage("Linux_Server");
		ComputationalPropertiesType cp2 = new ComputationalPropertiesType();
		cp2.setCores(500);
		cp2.setCpu(2);
		cp2.setNOfOperations(500);
		vdu2.setComputationalRequirements(cp2);
		MemoryPropertiesType mp2 = new MemoryPropertiesType();
		mp2.setDiskStorage(30);
		mp2.setMemory(100);
		mp2.setVirtualMemRes(10);
		vdu2.setMemoryRequirements(mp2);
		NetworkPropertiesType np2 = new NetworkPropertiesType();
		np2.setBandwidth(100);
		vdu2.setNetworkRequirements(np2);
		vnfd1.getVDU().add(vdu2);
		
		Dependency dep = new Dependency();
		Relation rel = new Relation();
		rel.setSrc("0");
		rel.setTarget("1");
		dep.getRelation().add(rel);
		vnfd1.setDependency(dep);
		
		vnf.getVNFD().add(vnfd1);
		
		VNFD vnfd2 = new VNFD();
		vnfd2.setId("1");
		vnfd2.setVendor("POLIMI");
		vnfd2.setVersion("0.5");
		VirtualLink vl2 = new VirtualLink();
		vl2.setSrc("0");
		vl2.setDst("1");
		vl2.setTestAccess(TestAccessType.PASSIVE);
		vnfd2.getVirtualLink().add(vl2);
		
		VDU vdu3 = new VDU();
		vdu3.setId("2");
		vdu3.setVmImage("Linux_Server");
		ComputationalPropertiesType cp3 = new ComputationalPropertiesType();
		cp3.setCores(500);
		cp3.setCpu(2);
		cp3.setNOfOperations(500);
		vdu3.setComputationalRequirements(cp3);
		MemoryPropertiesType mp3 = new MemoryPropertiesType();
		mp3.setDiskStorage(30);
		mp3.setMemory(100);
		mp3.setVirtualMemRes(10);
		vdu3.setMemoryRequirements(mp3);
		NetworkPropertiesType np3 = new NetworkPropertiesType();
		np1.setBandwidth(100);
		vdu3.setNetworkRequirements(np3);
		vnfd2.getVDU().add(vdu3);
		
		vnf.getVNFD().add(vnfd2);
		nsd.setVNF(vnf);
		
		// ENTRY A VNF DEPENDENCY
		VNFDependency vnfdep = new VNFDependency();
	    Graph g1 = new Graph();
	    g1.setId((long) 0);
	    
	    Node n1 = new Node();
	    n1.setId((long) 0);
	    n1.setName("nodeA");
	    n1.setFunctionalType(FunctionalTypes.WEBSERVER);
	    Configuration conf1 = new Configuration();
	    conf1.setName("confA");
	    conf1.setDescription("A simple description");
	    Webclient web1 = new Webclient();
	    web1.setNameWebServer("nodeB");
	    conf1.setWebclient(web1);
	    n1.setConfiguration(conf1);
	    g1.getNode().add(n1);
	    
	    Node n2 = new Node();
	    n2.setId((long) 1);
	    n2.setName("nodeB");
	    n2.setFunctionalType(FunctionalTypes.WEBCLIENT);
	    Configuration conf2 = new Configuration();
	    conf2.setName("confB");
	    conf2.setDescription("A simple description");
	    Webserver web2 = new Webserver();
	    web2.setName("nodeB");
	    conf2.setWebserver(web2);
	    n2.setConfiguration(conf2);
	    g1.getNode().add(n2);
	    
	    Node n3= new Node();
	    n3.setId((long) 2);
	    n3.setName("nodeC");
	    n3.setFunctionalType(FunctionalTypes.FIREWALL);
	    Configuration conf3 = new Configuration();
	    conf3.setName("confC");
	    conf3.setDescription("A simple description");
	    Firewall f1 = new Firewall();
	    Elements el1 = new Elements();
	    el1.setSource("nodeC");
	    el1.setDestination("nodeC");
	    f1.getElements().add(el1);
	    conf3.setFirewall(f1);
	    n3.setConfiguration(conf3);
	    g1.getNode().add(n3);
	    
	    Node n4 = new Node();
	    n4.setId((long) 3);
	    n4.setName("nodeD");
	    n4.setFunctionalType(FunctionalTypes.CACHE);
	    Configuration conf4 = new Configuration();
	    conf4.setName("confD");
	    conf4.setDescription("A simple description");
	    Cache c1 = new Cache();
	    c1.getResource().add("nodeA");
	    c1.getResource().add("nodeB");
	    conf4.setCache(c1);
	    n4.setConfiguration(conf4);
	    g1.getNode().add(n4);
	    
	    Node n5 = new Node();
	    n5.setId((long) 4);
	    n5.setName("nodeE");
	    n5.setFunctionalType(FunctionalTypes.FIELDMODIFIER);
	    Configuration conf5 = new Configuration();
	    conf5.setName("confE");
	    conf5.setDescription("A simple description");
	    Fieldmodifier fm1 = new Fieldmodifier();
	    fm1.setName("name");
	    conf5.setFieldmodifier(fm1);
	    n5.setConfiguration(conf5);
	    g1.getNode().add(n5);
	    
	    vnfdep.getGraph().add(g1);
	    nsd.setVNFDependency(vnfdep);
	    
	    // ENTRY A PROPERTY_DEFINITION
	    PropertyDefinition pd = new PropertyDefinition();
	    
	    Property prop1 = new Property();
	    prop1.setGraph(g1.getId());
	    prop1.setName(PName.ISOLATION_PROPERTY);
	    prop1.setSrc("nodeA");
	    prop1.setDst("nodeB");
	    prop1.setSrcPort("5000");
	    prop1.setDstPort("80");
	    HTTPDefinition http = new HTTPDefinition();
	    http.setBody("weapons");
	    prop1.setHTTPDefinition(http);
	    pd.getProperty().add(prop1);
	    
	    Property prop2 = new Property();
	    prop2.setGraph(g1.getId());
	    prop2.setName(PName.ISOLATION_PROPERTY);
	    prop2.setSrc("nodeC");
	    prop2.setDst("nodeD");
	    prop2.setSrcPort("3000");
	    prop2.setDstPort("110");
	    POP3Definition pop3 = new POP3Definition();
	    pop3.setEmailFrom("polito");
	    pop3.setBody("weapons");
	    prop2.setPOP3Definition(pop3);
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
	    
	    PNFD pnfd1 = new PNFD();
	    pnfd1.setId("0");
	    pnfd1.setVendor("fastweb");
	    pnfd1.setVersion("1.2");
	    pnfd1.setDescription("DHCP");
	    ConnectionPoint cp = new ConnectionPoint();
	    cp.setId("0");
	    cp.setType(ConnectionPointType.PHYSICAL_PORT);
	    pnfd1.setConnectionPoint(cp);
	    pnf.getPNFD().add(pnfd1);
	    
	    PNFD pnfd2 = new PNFD();
	    pnfd2.setId("1");
	    pnfd2.setVendor("fastweb");
	    pnfd2.setVersion("1.2");
	    pnfd2.setDescription("DHCP");
	    cp.setId("1");
	    cp.setType(ConnectionPointType.ENDPOINT);
	    pnfd1.setConnectionPoint(cp);
	    pnf.getPNFD().add(pnfd2);
	    
	    nsd.setPNF(pnf);
	    
	    // ENTRY A Flavours 
	    Flavours flavours = new Flavours();
	    
	    ServiceDeploymentFlavour sdf1 = new ServiceDeploymentFlavour();
	    sdf1.setId("0");
	    sdf1.setFlavourKey("call_per_second");
	    sdf1.setFlavourValue(300);
	    flavours.getServiceDeploymentFlavour().add(sdf1);
	    
	    nsd.setFlavours(flavours);
	    
	    // ENTRY A ConnectionPoints 
	    ConnectionPoints nsdCps = new ConnectionPoints();
	    
	    ConnectionPoint nsdCp = new ConnectionPoint();
	    nsdCp.setId("0");
	    nsdCp.setType(ConnectionPointType.ENDPOINT);
	    nsdCps.getConnectionPoint().add(nsdCp);
	    
	    nsd.setConnectionPoints(nsdCps);
	    
	    // ENTRY A ParsingString 
	    nsd.setParsingString("30L?");
	    
	    // ENTRY THE NSD CREATED PREVIOUSLY
		enterNSD(nsd);
		
	}
}
