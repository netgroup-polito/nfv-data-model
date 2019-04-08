package it.polito.dp2.rest.nfv.db;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import it.polito.dp2.rest.nfv.jaxb.*;

public class NsDB {
    private static NsDB nsdDB = new NsDB();

    public static NsDB getNsdDB(){
        return nsdDB;
    }

    private ConcurrentMap<String, NSD> nsdMap;

    private NsDB(){
        nsdMap = new ConcurrentHashMap<String, NSD>();
    }

    /** --- NSD --- **/
    public NS getNS(){
        NS ns = new NS();

        ns.getNSD().addAll(nsdMap.values());

        return ns;
    }

    public synchronized NS addNS(NS ns){
        for (NSD nsd : ns.getNSD())
            addNSD(nsd);

        return ns;
    }

    public void deleteNS(){
        nsdMap.clear();
    }

    public NSD getNSDInfo(String nsdID){
        return nsdMap.get(nsdID);
    }

    public synchronized NSD addNSD(NSD nsd){
    	NSD newNSD = new NSD();

    	/* init of the new obj */
    	newNSD.setConnectionPoints(new ConnectionPoints());
    	newNSD.setFlavours(new Flavours());
    	newNSD.setPNF(new PNF());
    	newNSD.setPropertyDefinition(new PropertyDefinition());
    	newNSD.setVNF(new VNF());
    	newNSD.setVNFDependency(new VNFDependency());
    	newNSD.setVNFFGD(new VNFFGD());
    	
    	newNSD.setId(nsd.getId());
    	newNSD.setVendor(nsd.getVendor());
    	newNSD.setVersion(nsd.getVersion());
    	newNSD.setConnectionPoints(nsd.getConnectionPoints());
    	newNSD.setFlavours(nsd.getFlavours());
    	newNSD.setPNF(nsd.getPNF());
    	newNSD.setPropertyDefinition(nsd.getPropertyDefinition());
    	newNSD.setVNF(nsd.getVNF());
    	newNSD.setVNFDependency(nsd.getVNFDependency());
    	newNSD.setVNFFGD(nsd.getVNFFGD());
    	
        return nsdMap.put(nsd.getId(), newNSD);
    }

    public NSD deleteNSD(String nsdID){
        return nsdMap.remove(nsdID);
    }
    
    /** --- PropertyDefinition --- **/
    public PropertyDefinition getPropertyDefinition(String nsdID){
        if(nsdMap.get(nsdID) != null)
            return nsdMap.get(nsdID).getPropertyDefinition();

        return null;
    }

    public synchronized PropertyDefinition addPropertyDefinition(String nsdID, PropertyDefinition propertyDef){
        if(nsdMap.get(nsdID) == null)
            return null;

        deletePropertyDefinition(nsdID);

        for(Property p : propertyDef.getProperty())
            nsdMap.get(nsdID).getPropertyDefinition().getProperty().add(p);

        return propertyDef;
    }

    public PropertyDefinition deletePropertyDefinition(String nsdID){
        if(nsdMap.get(nsdID) == null)
            return null;

    	nsdMap.get(nsdID).setPropertyDefinition(new PropertyDefinition());
    	
        return getPropertyDefinition(nsdID);
    }

    public Property getProperty(String nsdID, Long graphID){
        if(nsdMap.get(nsdID) == null)
            return null;

        for(Property p : getPropertyDefinition(nsdID).getProperty()){
            if(p.getGraph() == graphID)
                return p;
        }

        return null;
    }

    public synchronized Property addProperty(String nsdID, Property property){
        if(nsdMap.get(nsdID) == null)
            return null;

        if(getProperty(nsdID, property.getGraph()) == null){
            getPropertyDefinition(nsdID).getProperty().add(property);
            return property;
        }

        return null;
    }

    public Property deleteProperty(String nsdID, Long graphID){
        if(nsdMap.get(nsdID) == null)
            return null;

        for(Property p : getPropertyDefinition(nsdID).getProperty()){
            if(p.getGraph() == graphID){
                getPropertyDefinition(nsdID).getProperty().remove(p);
                return p;
            }
        }

        return null;
    }

    public Property modifyProperty(String nsdID, Property property){
        if(nsdMap.get(nsdID) == null)
            return null;

        if(deleteProperty(nsdID, property.getGraph()) != null){
        	addProperty(nsdID, property);
        	return property;
        }

        return null;
    }

    /** --- VNFDependency --- **/
    public VNFDependency getVNFDependency(String nsdID){
        if(nsdMap.get(nsdID) != null)
            return nsdMap.get(nsdID).getVNFDependency();

        return null;
    }

    public synchronized VNFDependency addVNFDependency(String nsdID, VNFDependency vnfDependency){
        if(nsdMap.get(nsdID) == null)
            return null;

        deleteVNFDependency(nsdID);

        for(Graph g : vnfDependency.getGraph())
            nsdMap.get(nsdID).getVNFDependency().getGraph().add(g);

        return vnfDependency;
    }

    public VNFDependency deleteVNFDependency(String nsdID){
        if(nsdMap.get(nsdID) == null)
            return null;

    	nsdMap.get(nsdID).setVNFDependency(new VNFDependency());
        	
        return getVNFDependency(nsdID);
    }

    public Graph getGraph(String nsdID, Long graphID){
        if(nsdMap.get(nsdID) == null)
            return null;

        for(Graph graph : getVNFDependency(nsdID).getGraph()) {
            if(graph.getId().equals(graphID))
                return graph;
        }

        return null;
    }

    public synchronized Graph addGraph(String nsdID, Graph graph){
        if(nsdMap.get(nsdID) == null)
            return null;

        if(getGraph(nsdID, graph.getId()) == null){
            if(nsdMap.get(nsdID).getVNFDependency().getGraph().add(graph))
                return graph;
        }

        return null;
    }

    public Graph deleteGraph(String nsdID, Long graphID){
        if(nsdMap.get(nsdID) == null)
            return null;

        for(Graph graph : getVNFDependency(nsdID).getGraph()) {
            if(graph.getId().equals(graphID)) {
                getVNFDependency(nsdID).getGraph().remove(graph);
                return graph;
            }
        }

        return null;
    }

    public Node getNode(String nsdID, Long graphID, String nodeName){
        if(nsdMap.get(nsdID) == null)
            return null;

        for(Node node : getGraph(nsdID, graphID).getNode()) {
            if(node.getName().equals(nodeName))
                return node;
        }

        return null;
    }

    public synchronized Node addNode(String nsdID, Long graphID, Node node){
        if(nsdMap.get(nsdID) == null)
            return null;

        Graph graph = getGraph(nsdID, graphID);

        for (Node n : graph.getNode()) {
            if(n.getName().equals(node.getName()))
                return null;
        }

        graph.getNode().add(node);
        return node;
    }

    public Node deleteNode(String nsdID, Long graphID, String nodeName){
        if(nsdMap.get(nsdID) == null)
            return null;

        for(Node node : getGraph(nsdID, graphID).getNode()) {
            if(node.getName().equals(nodeName)) {
                getGraph(nsdID, graphID).getNode().remove(node);
                return node;
            }
        }

        return null;
    }

    public Node modifyNode(String nsdID, Long graphID, Node node){
        if(nsdMap.get(nsdID) == null)
            return null;

        if(getNode(nsdID, graphID, node.getName()) != null){
            deleteNode(nsdID, graphID, node.getName());
            addNode(nsdID, graphID, node);
            return node;
        }

        return null;
    }

    /** --- VNF --- **/
    public VNF getVNF(String nsdID){
        if(nsdMap.get(nsdID) != null)
            return nsdMap.get(nsdID).getVNF();

        return null;
    }

    public synchronized VNF addVNF(String nsdID, VNF vnf){
        if(nsdMap.get(nsdID) == null)
            return null;

    	deleteVNF(nsdID);

    	for(VNFD v : vnf.getVNFD())
            nsdMap.get(nsdID).getVNF().getVNFD().add(v);

        return vnf;
    }

    public VNF deleteVNF(String nsdID){
        if(nsdMap.get(nsdID) == null)
            return null;

        VNF vnf = new VNF();

        nsdMap.get(nsdID).setVNF(vnf);

        return vnf;
    }

    public VNFD getVNFDInfo(String nsdID, String vnfdID){
        if(nsdMap.get(nsdID) == null)
            return null;

        for(VNFD vnfd : getVNF(nsdID).getVNFD()) {
            if(vnfd.getId().equals(vnfdID))
                return vnfd;
        }

        return null;
    }

    public synchronized VNFD addVNFD(String nsdID, VNFD vnfd){
        if(nsdMap.get(nsdID) == null)
            return null;

        if(getVNFDInfo(nsdID, vnfd.getId()) == null){
            getVNF(nsdID).getVNFD().add(vnfd);
            return vnfd;
        }

        return null;
    }

    public VNFD deleteVNFD(String nsdID, String vnfdID){
        if(nsdMap.get(nsdID) == null)
            return null;

        for(VNFD vnfd : getVNF(nsdID).getVNFD()) {
            if(vnfd.getId().equals(vnfdID)){
                getVNF(nsdID).getVNFD().remove(vnfd);
                return vnfd;
            }
        }

        return null;
    }

    public VNFD modifyVNFD(String nsdID, VNFD vnfd){
        if(nsdMap.get(nsdID) == null)
            return null;

        if(deleteVNFD(nsdID, vnfd.getId()) == null)
            return null;
        if(addVNFD(nsdID, vnfd) == null)
            return null;

        return vnfd;
    }

    /** --- VNFFGD --- **/
    public VNFFGD getVNFFGD(String nsdID){
        if(nsdMap.get(nsdID) == null)
            return null;

        if(nsdMap.get(nsdID) != null)
            return nsdMap.get(nsdID).getVNFFGD();

        return null;
    }

    public synchronized VNFFGD addVNFFGD(String nsdID, VNFFGD vnffgd){
        if(nsdMap.get(nsdID) == null)
            return null;

        deleteVNFFGD(nsdID);

        for(NetworkForwardingPaths n : vnffgd.getNetworkForwardingPaths())
            getVNFFGD(nsdID).getNetworkForwardingPaths().add(n);

        return vnffgd;
    }

    public VNFFGD deleteVNFFGD(String nsdID){
        if(nsdMap.get(nsdID) == null)
            return null;

        VNFFGD vnffgd = new VNFFGD();

        nsdMap.get(nsdID).setVNFFGD(vnffgd);

        return vnffgd;
    }

    public NetworkForwardingPaths getNetworkForwardingPathsInfo(String nsdID, String nfpID){
        if(nsdMap.get(nsdID) == null)
            return null;

        for(NetworkForwardingPaths nfp : getVNFFGD(nsdID).getNetworkForwardingPaths()){
            if(nfp.getId().equals(nfpID))
                return nfp;
        }

        return null;
    }

    public synchronized NetworkForwardingPaths addNetworkForwardingPaths(String nsdID, NetworkForwardingPaths nfp){
        if(nsdMap.get(nsdID) == null)
            return null;

        if(getNetworkForwardingPathsInfo(nsdID, nfp.getId()) == null){
            getVNFFGD(nsdID).getNetworkForwardingPaths().add(nfp);
            return nfp;
        }

        return null;
    }

    public NetworkForwardingPaths deleteNetworkForwardingPaths(String nsdID, String nfpID){
        if(nsdMap.get(nsdID) == null)
            return null;

        for(NetworkForwardingPaths nfp : getVNFFGD(nsdID).getNetworkForwardingPaths()){
            if(nfp.getId().equals(nfpID)){
            	getVNFFGD(nsdID).getNetworkForwardingPaths().remove(nfp);
                return nfp;
            }
        }

        return null;
    }

    public NetworkForwardingPaths modifyNetworkForwardingPaths(String nsdID, NetworkForwardingPaths nfp){
        if(nsdMap.get(nsdID) == null)
            return null;

        if(deleteNetworkForwardingPaths(nsdID, nfp.getId()) == null)
            return null;
        if(addNetworkForwardingPaths(nsdID, nfp) == null)
            return null;

        return nfp;
    }

    /** --- PNF --- **/
    public PNF getPNF(String nsdID){
        if(nsdMap.get(nsdID) != null)
            return nsdMap.get(nsdID).getPNF();

        return null;
    }

    public synchronized PNF addPNF(String nsdID, PNF pnf){
        if(nsdMap.get(nsdID) == null)
            return null;

        deletePNF(nsdID);

        for(PNFD p : pnf.getPNFD())
            getPNF(nsdID).getPNFD().add(p);

        return pnf;
    }

    public PNF deletePNF(String nsdID){
        if(nsdMap.get(nsdID) == null)
            return null;

        PNF pnf = new PNF();

        nsdMap.get(nsdID).setPNF(pnf);

        return pnf;
    }

    public PNFD getPNFDInfo(String nsdID, String pnfdID){
        if(nsdMap.get(nsdID) == null)
            return null;

        for(PNFD pnfd : getPNF(nsdID).getPNFD()){
            if(pnfd.getId().equals(pnfdID))
                return pnfd;
        }

        return null;
    }

    public synchronized PNFD addPNFD(String nsdID, PNFD pnfd){
        if(nsdMap.get(nsdID) == null)
            return null;

        if(getPNFDInfo(nsdID, pnfd.getId()) == null){
            getPNF(nsdID).getPNFD().add(pnfd);
            return pnfd;
        }

        return null;
    }

    public PNFD deletePNFD(String nsdID, String pnfdID){
        if(nsdMap.get(nsdID) == null)
            return null;

        for(PNFD pnfd : getPNF(nsdID).getPNFD()){
            if(pnfd.getId().equals(pnfdID)){
            	getPNF(nsdID).getPNFD().remove(pnfd);
                return pnfd;
            }
        }

        return null;
    }

    public PNFD modifyPNFD(String nsdID, PNFD pnfd){
        if(nsdMap.get(nsdID) == null)
            return null;

        if(deletePNFD(nsdID, pnfd.getId()) == null)
            return null;
        if(addPNFD(nsdID, pnfd) == null)
            return null;

        return pnfd;
    }

    /** --- Flavours --- **/
    public Flavours getFlavours(String nsdID){
        if(nsdMap.get(nsdID) != null)
            return nsdMap.get(nsdID).getFlavours();

        return null;
    }

    public synchronized Flavours addFlavours(String nsdID, Flavours flavours){
        if(nsdMap.get(nsdID) == null)
            return null;

        deleteFlavours(nsdID);

        for(ServiceDeploymentFlavour s : flavours.getServiceDeploymentFlavour())
            getFlavours(nsdID).getServiceDeploymentFlavour().add(s);

        return flavours;
    }

    public Flavours deleteFlavours(String nsdID){
        if(nsdMap.get(nsdID) == null)
            return null;

    	Flavours flavours = new Flavours();

        nsdMap.get(nsdID).setFlavours(flavours);

        return flavours;
    }

    public ServiceDeploymentFlavour getServiceDeploymentFlavour(String nsdID, String sdfID){
        if(nsdMap.get(nsdID) == null)
            return null;

        for(ServiceDeploymentFlavour s : getFlavours(nsdID).getServiceDeploymentFlavour()){
            if(s.getId().equals(sdfID))
                return s;
        }

        return null;
    }

    public synchronized ServiceDeploymentFlavour addServiceDeploymentFlavour(String nsdID, ServiceDeploymentFlavour sdf){
        if(nsdMap.get(nsdID) == null)
            return null;

        for(ServiceDeploymentFlavour s : getFlavours(nsdID).getServiceDeploymentFlavour()){
            if(s.getId().equals(sdf.getId()))
                return null;
        }

        getFlavours(nsdID).getServiceDeploymentFlavour().add(sdf);
        return sdf;
    }

    public ServiceDeploymentFlavour deleteServiceDeploymentFlavour(String nsdID, String sdfID){
        if(nsdMap.get(nsdID) == null)
            return null;

        for(ServiceDeploymentFlavour s : getFlavours(nsdID).getServiceDeploymentFlavour()){
            if(s.getId().equals(sdfID)){
            	 getFlavours(nsdID).getServiceDeploymentFlavour().remove(s);
                 return s;
            }
        }

        return null;
    }

    /** --- ConnectionPoints --- **/
    public ConnectionPoints getConnectionPoints(String nsdID){
        if(nsdMap.get(nsdID) != null)
            return nsdMap.get(nsdID).getConnectionPoints();

        return null;
    }

    public synchronized ConnectionPoints addConnectionPoints(String nsdID, ConnectionPoints cp){
        if(nsdMap.get(nsdID) == null)
            return null;

        deleteConnectionPoints(nsdID);

        for(ConnectionPoint c : cp.getConnectionPoint())
            getConnectionPoints(nsdID).getConnectionPoint().add(c);

        return cp;
    }

    public ConnectionPoints deleteConnectionPoints(String nsdID){
        if(nsdMap.get(nsdID) == null)
            return null;

        ConnectionPoints cp = new ConnectionPoints();

        nsdMap.get(nsdID).setConnectionPoints(cp);

        return cp;
    }

    public ConnectionPoint getConnectionPoint(String nsdID, String cpID){
        if(nsdMap.get(nsdID) == null)
            return null;

        for(ConnectionPoint c : getConnectionPoints(nsdID).getConnectionPoint()){
            if(c.getId().equals(cpID))
                return c;
        }

        return null;
    }

    public synchronized ConnectionPoint addConnectionPoint(String nsdID, ConnectionPoint cp){
        if(nsdMap.get(nsdID) == null)
            return null;

        for(ConnectionPoint c : getConnectionPoints(nsdID).getConnectionPoint()){
            if(c.getId().equals(cp.getId()))
                return null;
        }

        getConnectionPoints(nsdID).getConnectionPoint().add(cp);
        return cp;
    }

    public ConnectionPoint deleteConnectionPoint(String nsdID, String cp){
        if(nsdMap.get(nsdID) == null)
            return null;

        for(ConnectionPoint c : getConnectionPoints(nsdID).getConnectionPoint()){
            if(c.getId().equals(cp)){
                getConnectionPoints(nsdID).getConnectionPoint().remove(c);
                return c;
            }
        }

        return null;
    }
}