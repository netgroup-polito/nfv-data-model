package it.polito.dp2.rest.nfv.test;

import java.util.List;

import it.polito.dp2.rest.nfv.jaxb.Cache;
import it.polito.dp2.rest.nfv.jaxb.ComputationalPropertiesType;
import it.polito.dp2.rest.nfv.jaxb.Configuration;
import it.polito.dp2.rest.nfv.jaxb.Connection;
import it.polito.dp2.rest.nfv.jaxb.ConnectionPoint;
import it.polito.dp2.rest.nfv.jaxb.ConnectionPointType;
import it.polito.dp2.rest.nfv.jaxb.Dependency;
import it.polito.dp2.rest.nfv.jaxb.FunctionalTypes;
import it.polito.dp2.rest.nfv.jaxb.HTTPDefinition;
import it.polito.dp2.rest.nfv.jaxb.Host;
import it.polito.dp2.rest.nfv.jaxb.MemoryPropertiesType;
import it.polito.dp2.rest.nfv.jaxb.NetworkPropertiesType;
import it.polito.dp2.rest.nfv.jaxb.Node;
import it.polito.dp2.rest.nfv.jaxb.PNFD;
import it.polito.dp2.rest.nfv.jaxb.PName;
import it.polito.dp2.rest.nfv.jaxb.PNodeRefType;
import it.polito.dp2.rest.nfv.jaxb.POP3Definition;
import it.polito.dp2.rest.nfv.jaxb.Property;
import it.polito.dp2.rest.nfv.jaxb.ServiceDeploymentFlavour;
import it.polito.dp2.rest.nfv.jaxb.TestAccessType;
import it.polito.dp2.rest.nfv.jaxb.TypeOfHost;
import it.polito.dp2.rest.nfv.jaxb.VDU;
import it.polito.dp2.rest.nfv.jaxb.VNFD;
import it.polito.dp2.rest.nfv.jaxb.VNodeRefType;
import it.polito.dp2.rest.nfv.jaxb.VirtualLink;
import it.polito.dp2.rest.nfv.jaxb.Webclient;
import it.polito.dp2.rest.nfv.jaxb.Webserver;
import it.polito.dp2.rest.nfv.jaxb.Dependency.Relation;
import it.polito.dp2.rest.nfv.jaxb.Fieldmodifier;
import it.polito.dp2.rest.nfv.jaxb.Firewall;

public class DataToObj {
	
	public Host getHostFromData(String hostId, TypeOfHost type, String fixEnd, int maxVnf,
								int cores, int cpu, int nOfOp,
								int mem, int diskSt, int virtMem,
								int bandWidth, 
								List<VNodeRefType> sup_vnfd, PNodeRefType sup_pnfd){
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
		
		return host;
	}
	
	public Connection getConnectionFromData(String hostSrc, String hostDest, int avgLat){
	
		/*Creation of Hosts*/
		Connection connection = new Connection();
		connection.setSourceHost(hostSrc);
		connection.setDestHost(hostDest);
		connection.setAvgLatency(avgLat);
		
		return connection;
	}
	
	public VNFD getVnfdFromData(String id, String vendor, String version, 
								String virtualLinkSrc, String virtualLinkDst, TestAccessType testType){
		
		/*Creation of Vnfd*/
		VNFD vnfd = new VNFD();
		vnfd.setId(id);
		vnfd.setVendor(vendor);
		vnfd.setVersion(version);
		VirtualLink vl1 = new VirtualLink();
		vl1.setSrc(virtualLinkSrc);
		vl1.setDst(virtualLinkDst);
		vl1.setTestAccess(testType);
		vnfd.getVirtualLink().add(vl1);
		
		return vnfd;
	}
	
	public VDU getVduFromData(String id, String VM_image, 
								int cores, int cpu, int setNumOfOp,
								int diskStorage, int memory, int virtualMenmRes,
								int bandwidth){

		/*Creation of Vdu*/
		VDU vdu = new VDU();
		vdu.setId(id);
		vdu.setVmImage(VM_image);
		ComputationalPropertiesType cp1 = new ComputationalPropertiesType();
		cp1.setCores(cores);
		cp1.setCpu(cpu);
		cp1.setNOfOperations(setNumOfOp);
		vdu.setComputationalRequirements(cp1);
		MemoryPropertiesType mp1 = new MemoryPropertiesType();
		mp1.setDiskStorage(diskStorage);
		mp1.setMemory(memory);
		mp1.setVirtualMemRes(virtualMenmRes);
		vdu.setMemoryRequirements(mp1);
		NetworkPropertiesType np1 = new NetworkPropertiesType();
		np1.setBandwidth(bandwidth);
		vdu.setNetworkRequirements(np1);
		
		return vdu;
	}
	
	public Dependency getDependencyFromData(String src, String dest){
		
		Dependency dep = new Dependency();
		Relation rel = new Relation();
		rel.setSrc(src);
		rel.setTarget(dest);
		dep.getRelation().add(rel);
		
		return dep;
	}
	
	public Node getNodeServerFromData(String id, String name, 
							FunctionalTypes functioalType,
							String confName, String confDescr,
							Webserver web){
		
		/*Creation of Node_webServer*/
		Node n1 = new Node();
	    n1.setId(id);
	    n1.setName(name);
	    n1.setFunctionalType(FunctionalTypes.WEBSERVER);
	    Configuration conf1 = new Configuration();
	    conf1.setName(confName);
	    conf1.setDescription(confDescr);
	    conf1.setWebserver(web);
	    n1.setConfiguration(conf1);
		
		return n1;
	}
	
	public Node getNodeClientFromData(String id, String name, 
							FunctionalTypes functioalType,
							String confName, String confDescr,
							Webclient web){
		/*Creation of Node_webClient*/
		Node n1 = new Node();
	    n1.setId(id);
	    n1.setName(name);
	    n1.setFunctionalType(FunctionalTypes.WEBCLIENT);
	    Configuration conf1 = new Configuration();
	    conf1.setName(confName);
	    conf1.setDescription(confDescr);
	    conf1.setWebclient(web);
	    n1.setConfiguration(conf1);
		
		return n1;
	}
	
	public Node getNodeFirewallFromData(String id, String name, 
							FunctionalTypes functioalType,
							String confName, String confDescr,
							Firewall web){

		/*Creation of Node_Firewall*/
		Node n1 = new Node();
	    n1.setId(id);
	    n1.setName(name);
	    n1.setFunctionalType(FunctionalTypes.FIREWALL);
	    Configuration conf1 = new Configuration();
	    conf1.setName(confName);
	    conf1.setDescription(confDescr);
	    conf1.setFirewall(web);
	    n1.setConfiguration(conf1);
		
		return n1;
	}
	
	public Node getNodeCacheFromData(String id, String name, 
							FunctionalTypes functioalType,
							String confName, String confDescr,
							Cache web){

		/*Creation of Node_Cache*/
		Node n1 = new Node();
	    n1.setId(id);
	    n1.setName(name);
	    n1.setFunctionalType(FunctionalTypes.CACHE);
	    Configuration conf1 = new Configuration();
	    conf1.setName(confName);
	    conf1.setDescription(confDescr);
	    conf1.setCache(web);
	    n1.setConfiguration(conf1);
		
		return n1;
	}
	
	public Node getNodeFileModFromData(String id, String name, 
							FunctionalTypes functioalType,
							String confName, String confDescr,
							Fieldmodifier web){

		/*Creation of Node_FileModifier*/
		Node n1 = new Node();
		n1.setId(id);
		n1.setName(name);
		n1.setFunctionalType(functioalType);
		Configuration conf1 = new Configuration();
		conf1.setName(confName);
		conf1.setDescription(confDescr);
		conf1.setFieldmodifier(web);
		n1.setConfiguration(conf1);
		
		return n1;
	}
	
	public Property getNodePropertyHTTPFromData(long graphId,
										PName isolationProperty,
										String src, String dst, String srcPort, String dstPort,
										String Body){
		
		Property prop1 = new Property();
	    prop1.setGraph(graphId);
	    prop1.setName(PName.ISOLATION_PROPERTY);
	    prop1.setSrc(src);
	    prop1.setDst(dst);
	    prop1.setSrcPort(srcPort);
	    prop1.setDstPort(dstPort);
	    HTTPDefinition http = new HTTPDefinition();
	    http.setBody(Body);
	    prop1.setHTTPDefinition(http);
		
		return prop1;
	}
	
	public Property getNodePropertyPOP3FromData(long graphId,
										PName isolationProperty,
										String src, String dst, String srcPort, String dstPort,
										String mailFrom, String Body){

		Property prop1 = new Property();
		prop1.setGraph(graphId);
		prop1.setName(PName.ISOLATION_PROPERTY);
		prop1.setSrc(src);
		prop1.setDst(dst);
		prop1.setSrcPort(srcPort);
		prop1.setDstPort(dstPort);
		POP3Definition pop3 = new POP3Definition();
	    pop3.setEmailFrom(mailFrom);
	    pop3.setBody(Body);
	    prop1.setPOP3Definition(pop3);
		
		return prop1;
	}
	
	public PNFD getPnfdFromData(String id, String vendor, String version, String description, 
								String cpId, ConnectionPointType cpType){

		/*Creation of Pnfd*/
		PNFD pnfd1 = new PNFD();
	    pnfd1.setId(id);
	    pnfd1.setVendor(vendor);
	    pnfd1.setVersion(version);
	    pnfd1.setDescription(description);
	    ConnectionPoint cp = new ConnectionPoint();
	    cp.setId(cpId);
	    cp.setType(cpType);
	    pnfd1.setConnectionPoint(cp);
		
		return pnfd1;	
	}
	
	public ServiceDeploymentFlavour getServDeplFlavFromData(String id, String key, int value){

		/*Creation of Service Deployment Flavour*/
		ServiceDeploymentFlavour sdf1 = new ServiceDeploymentFlavour();
	    sdf1.setId(id);
	    sdf1.setFlavourKey(key);
	    sdf1.setFlavourValue(value);
		
		return sdf1;	
	}
	
	public ConnectionPoint getConnectionPointFromData(String id, ConnectionPointType pointType){

		/*Creation of Connection Point*/
		ConnectionPoint nsdCp = new ConnectionPoint();
	    nsdCp.setId("0");
	    nsdCp.setType(pointType);
		
		return nsdCp;	
	}
	

}
