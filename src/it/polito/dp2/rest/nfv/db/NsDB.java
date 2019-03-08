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

    /* --- NSD --- */
    public NS getNS(){
        NS ns = new NS();
        
        ns.getNSD().addAll(nsdMap.values());
        return ns;
    }

    public NS addNS(NS ns){
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
    	NSD newnsd = new NSD();
    	/* init of the new obj */
    	newnsd.setConnectionPoints(new ConnectionPoints());
    	newnsd.setFlavours(new Flavours());
    	newnsd.setPNF(new PNF());
    	newnsd.setPropertyDefinition(new PropertyDefinition());
    	newnsd.setVNF(new VNF());
    	newnsd.setVNFDependency(new VNFDependency());
    	newnsd.setVNFFGD(new VNFFGD());
    	
    	newnsd.setId(nsd.getId());
    	newnsd.setVendor(nsd.getId());
    	newnsd.setVersion(nsd.getId());
    	newnsd.setConnectionPoints(nsd.getConnectionPoints());
    	newnsd.setFlavours(nsd.getFlavours());
    	newnsd.setPNF(nsd.getPNF());
    	newnsd.setPropertyDefinition(nsd.getPropertyDefinition());
    	newnsd.setVNF(nsd.getVNF());
    	newnsd.setVNFDependency(nsd.getVNFDependency());
    	newnsd.setVNFFGD(nsd.getVNFFGD());
    	
        return nsdMap.put(nsd.getId(), newnsd);
    }

    public NSD deleteNSD(String nsdID){
        return nsdMap.remove(nsdID);
    }
    
    /* --- PropertyDefinition --- */
    public PropertyDefinition getPropertyDefinition(String nsdID){
        return nsdMap.get(nsdID).getPropertyDefinition();
    }

    public synchronized PropertyDefinition addPropertyDefinition(String nsdID, PropertyDefinition propertyDef){
        deletePropertyDefinition(nsdID);
        
        if(nsdMap.get(nsdID).getPropertyDefinition().getProperty().addAll(propertyDef.getProperty()))
            return propertyDef;

        return null;
    }

    public PropertyDefinition deletePropertyDefinition(String nsdID){
    	nsdMap.get(nsdID).setPropertyDefinition(new PropertyDefinition());
    	
        return getPropertyDefinition(nsdID);
    }

    public Property getProperty(String nsdID, Long graphID){
        for(Property p : getPropertyDefinition(nsdID).getProperty()){
            if(p.getGraph() == graphID)
                return p;
        }

        return null;
    }

    public synchronized Property addProperty(String nsdID, Property property){
        if(getProperty(nsdID, property.getGraph()) == null){
            getPropertyDefinition(nsdID).getProperty().add(property);
            return property;
        }

        return null;
    }

    public Property deleteProperty(String nsdID, Long graphID){
        for(Property p : getPropertyDefinition(nsdID).getProperty()){
            if(p.getGraph() == graphID){
                getPropertyDefinition(nsdID).getProperty().remove(p);
                return p;
            }
        }

        return null;
    }

    public Property modifyProperty(String nsdID, Property property){
        if(deleteProperty(nsdID, property.getGraph()) != null){
        	addProperty(nsdID, property);
        	return property;
        }

        return null;
    }

    /* --- VNFDependency --- */
    public VNFDependency getVNFDependency(String nsdID){
        return nsdMap.get(nsdID).getVNFDependency();
    }

    public synchronized VNFDependency addVNFDependency(String nsdID, VNFDependency vnfDependency){
        deleteVNFDependency(nsdID);
        
        if(nsdMap.get(nsdID).getVNFDependency().getGraph().addAll(vnfDependency.getGraph()))
            return vnfDependency;

        return null;
    }

    public VNFDependency deleteVNFDependency(String nsdID){
    	nsdMap.get(nsdID).setVNFDependency(new VNFDependency());
        	
        return getVNFDependency(nsdID);
    }

    public Graph getGraph(String nsdID, Long graphID){
        for(Graph graph : getVNFDependency(nsdID).getGraph()) {
            if(graph.getId().equals(graphID))
                return graph;
        }

        return null;
    }

    public synchronized Graph addGraph(String nsdID, Graph graph){
        if(getGraph(nsdID, graph.getId()) == null){
            if(nsdMap.get(nsdID).getVNFDependency().getGraph().add(graph))
                return graph;
        }

        return null;
    }

    public Graph deleteGraph(String nsdID, Long graphID){
        for(Graph graph : getVNFDependency(nsdID).getGraph()) {
            if(graph.getId().equals(graphID)) {
                getVNFDependency(nsdID).getGraph().remove(graph);
                return graph;
            }
        }

        return null;
    }

    public Node getNode(String nsdID, Long graphID, String nodeName){
        for(Node node : getGraph(nsdID, graphID).getNode()) {
            if(node.getName().equals(nodeName))
                return node;
        }

        return null;
    }

    public synchronized Node addNode(String nsdID, Long graphID, Node node){
        Graph graph = getGraph(nsdID, graphID);

        for (Node n : graph.getNode()) {
            if(n.getName().equals(node.getName()))
                return null;
        }

        graph.getNode().add(node);
        return node;
    }

    public Node deleteNode(String nsdID, Long graphID, String nodeName){
        for(Node node : getGraph(nsdID, graphID).getNode()) {
            if(node.getName().equals(nodeName)) {
                getGraph(nsdID, graphID).getNode().remove(node);
                return node;
            }
        }

        return null;
    }

    public Node modifyNode(String nsdID, Long graphID, Node node){
        if(getNode(nsdID, graphID, node.getName()) != null){
            deleteNode(nsdID, graphID, node.getName());
            addNode(nsdID, graphID, node);
            return node;
        }

        return null;
    }

    /* --- VNF --- */
    public VNF getVNF(String nsdID){
        return nsdMap.get(nsdID).getVNF();
    }

    public synchronized VNF addVNF(String nsdID, VNF vnf){
    	deleteVNF(nsdID);
        nsdMap.get(nsdID).getVNF().getVNFD().addAll(vnf.getVNFD());

        return vnf;
    }

    public VNF deleteVNF(String nsdID){
        VNF vnf = new VNF();

        nsdMap.get(nsdID).setVNF(vnf);

        return vnf;
    }

    public VNFD getVNFDInfo(String nsdID, String vnfdID){
        for(VNFD vnfd : getVNF(nsdID).getVNFD()) {
            if(vnfd.getId().equals(vnfdID))
                return vnfd;
        }

        return null;
    }

    public synchronized VNFD addVNFD(String nsdID, VNFD vnfd){
        if(getVNFDInfo(nsdID, vnfd.getId()) == null){
            getVNF(nsdID).getVNFD().add(vnfd);
            return vnfd;
        }

        return null;
    }

    public VNFD deleteVNFD(String nsdID, String vnfdID){
        for(VNFD vnfd : getVNF(nsdID).getVNFD()) {
            if(vnfd.getId().equals(vnfdID))
                getVNF(nsdID).getVNFD().remove(vnfd);
                return vnfd;
        }

        return null;
    }

    public VNFD modifyVNFD(String nsdID, VNFD vnfd){
        if(deleteVNFD(nsdID, vnfd.getId()) == null)
            return null;
        if(addVNFD(nsdID, vnfd) == null)
            return null;

        return vnfd;
    }

    /* --- VNFFGD --- */
    public VNFFGD getVNFFGD(String nsdID){
        return nsdMap.get(nsdID).getVNFFGD();
    }

    public synchronized VNFFGD addVNFFGD(String nsdID, VNFFGD vnffgd){
        deleteVNFFGD(nsdID);
        getVNFFGD(nsdID).getNetworkForwardingPaths().addAll(vnffgd.getNetworkForwardingPaths());

        return vnffgd;
    }

    public VNFFGD deleteVNFFGD(String nsdID){
        VNFFGD vnffgd = new VNFFGD();

        nsdMap.get(nsdID).setVNFFGD(vnffgd);

        return vnffgd;
    }

    public NetworkForwardingPaths getNetworkForwardingPathsInfo(String nsdID, String nfpID){
        for(NetworkForwardingPaths nfp : getVNFFGD(nsdID).getNetworkForwardingPaths()){
            if(nfp.getId().equals(nfpID))
                return nfp;
        }

        return null;
    }

    public synchronized NetworkForwardingPaths addNetworkForwardingPaths(String nsdID, NetworkForwardingPaths nfp){
        if(getNetworkForwardingPathsInfo(nsdID, nfp.getId()) == null){
            getVNFFGD(nsdID).getNetworkForwardingPaths().add(nfp);
            return nfp;
        }

        return null;
    }

    public NetworkForwardingPaths deleteNetworkForwardingPaths(String nsdID, String nfpID){
        for(NetworkForwardingPaths nfp : getVNFFGD(nsdID).getNetworkForwardingPaths()){
            if(nfp.getId().equals(nfpID)){
            	getVNFFGD(nsdID).getNetworkForwardingPaths().remove(nfp);
                return nfp;
            }
        }


        return null;
    }

    public NetworkForwardingPaths modifyNetworkForwardingPaths(String nsdID, NetworkForwardingPaths nfp){
        if(deleteNetworkForwardingPaths(nsdID, nfp.getId()) == null)
            return null;
        if(addNetworkForwardingPaths(nsdID, nfp) == null)
            return null;

        return nfp;
    }

    /* --- PNF --- */
    public PNF getPNF(String nsdID){
        return nsdMap.get(nsdID).getPNF();
    }

    public synchronized PNF addPNF(String nsdID, PNF pnf){
        deletePNF(nsdID);
        getPNF(nsdID).getPNFD().addAll(pnf.getPNFD());

        return pnf;
    }

    public PNF deletePNF(String nsdID){
        PNF pnf = new PNF();

        nsdMap.get(nsdID).setPNF(pnf);

        return pnf;
    }

    public PNFD getPNFDInfo(String nsdID, String pnfdID){
        for(PNFD pnfd : getPNF(nsdID).getPNFD()){
            if(pnfd.getId().equals(pnfdID))
                return pnfd;
        }

        return null;
    }

    public synchronized PNFD addPNFD(String nsdID, PNFD pnfd){
        if(getPNFDInfo(nsdID, pnfd.getId()) == null){
            getPNF(nsdID).getPNFD().add(pnfd);
            return pnfd;
        }

        return null;
    }

    public PNFD deletePNFD(String nsdID, String pnfdID){
        for(PNFD pnfd : getPNF(nsdID).getPNFD()){
            if(pnfd.getId().equals(pnfdID)){
            	getPNF(nsdID).getPNFD().remove(pnfd);
                return pnfd;
            }
        }

        return null;
    }

    public PNFD modifyPNFD(String nsdID, PNFD pnfd){
        if(deletePNFD(nsdID, pnfd.getId()) == null)
            return null;
        if(addPNFD(nsdID, pnfd) == null)
            return null;

        return pnfd;
    }

    /* --- Flavours --- */
    public Flavours getFlavours(String nsdID){
        return nsdMap.get(nsdID).getFlavours();
    }

    public synchronized Flavours addFlavours(String nsdID, Flavours flavours){
        deleteFlavours(nsdID);
        getFlavours(nsdID).getServiceDeploymentFlavour().addAll(flavours.getServiceDeploymentFlavour());

        return flavours;
    }

    public Flavours deleteFlavours(String nsdID){
    	Flavours flavours = new Flavours();

        nsdMap.get(nsdID).setFlavours(flavours);

        return flavours;
    }

    public ServiceDeploymentFlavour getServiceDeploymentFlavour(String nsdID, String sdfID){
        for(ServiceDeploymentFlavour s : getFlavours(nsdID).getServiceDeploymentFlavour()){
            if(s.getId().equals(sdfID))
                return s;
        }

        return null;
    }

    public synchronized ServiceDeploymentFlavour addServiceDeploymentFlavour(String nsdID, ServiceDeploymentFlavour sdf){
        for(ServiceDeploymentFlavour s : getFlavours(nsdID).getServiceDeploymentFlavour()){
            if(s.getId().equals(sdf.getId()))
                return null;
        }

        getFlavours(nsdID).getServiceDeploymentFlavour().add(sdf);
        return sdf;
    }

    public ServiceDeploymentFlavour deleteServiceDeploymentFlavour(String nsdID, String sdfID){
        for(ServiceDeploymentFlavour s : getFlavours(nsdID).getServiceDeploymentFlavour()){
            if(s.getId().equals(sdfID)){
            	 getFlavours(nsdID).getServiceDeploymentFlavour().remove(s);
                 return s;
            }
        }

        return null;
    }

    /* --- ConnectionPoints --- */
    public ConnectionPoints getConnectionPoints(String nsdID){
        return nsdMap.get(nsdID).getConnectionPoints();
    }

    public synchronized ConnectionPoints addConnectionPoints(String nsdID, ConnectionPoints cp){
        deleteConnectionPoints(nsdID);
        getConnectionPoints(nsdID).getConnectionPoint().addAll(cp.getConnectionPoint());

        return cp;
    }

    public ConnectionPoints deleteConnectionPoints(String nsdID){
        ConnectionPoints cp = new ConnectionPoints();

        nsdMap.get(nsdID).setConnectionPoints(cp);

        return cp;
    }

    public ConnectionPoint getConnectionPoint(String nsdID, String cpID){
        for(ConnectionPoint c : getConnectionPoints(nsdID).getConnectionPoint()){
            if(c.getId().equals(cpID))
                return c;
        }

        return null;
    }

    public synchronized ConnectionPoint addConnectionPoint(String nsdID, ConnectionPoint cp){
        for(ConnectionPoint c : getConnectionPoints(nsdID).getConnectionPoint()){
            if(c.getId().equals(cp.getId()))
                return null;
        }

        getConnectionPoints(nsdID).getConnectionPoint().add(cp);
        return cp;
    }

    public ConnectionPoint deleteConnectionPoint(String nsdID, String cp){
        for(ConnectionPoint c : getConnectionPoints(nsdID).getConnectionPoint()){
            if(c.getId().equals(cp)){
                getConnectionPoints(nsdID).getConnectionPoint().remove(c);
                return c;
            }
        }

        return null;
    }
}
