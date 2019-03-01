package db;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import jaxb.*;

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
            nsdMap.put(nsd.getId(),nsd);

        return ns;
    }

    public void deleteNS(){
        nsdMap.clear();
    }

    public NSD getNSDInfo(String nsdID){
        return nsdMap.get(nsdID);
    }

    public synchronized NSD addNSD(NSD nsd){
        return nsdMap.put(nsd.getId(), nsd);
    }

    public NSD deleteNSD(String nsdID){
        return nsdMap.remove(nsdID);
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
        nsdMap.get(nsdID).getVNFDependency().getGraph().clear();

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

    /* --- VNF --- */
    public VNF getVNF(String nsdID){
        return nsdMap.get(nsdID).getVNF();
    }

    public synchronized VNF addVNF(String nsdID, VNF vnf){
        nsdMap.get(nsdID).getVNF().getVNFD().clear();
        nsdMap.get(nsdID).getVNF().getVNFD().addAll(vnf.getVNFD());

        return vnf;
    }

    public VNF deleteVNF(String nsdID){
        VNF vnf = new VNF();

        nsdMap.get(nsdID).getVNF().getVNFD().clear();

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
        getVNFFGD(nsdID).getNetworkForwardingPaths().clear();
        getVNFFGD(nsdID).getNetworkForwardingPaths().addAll(vnffgd.getNetworkForwardingPaths());

        return vnffgd;
    }

    public VNFFGD deleteVNFFGD(String nsdID){
        VNFFGD vnffgd = new VNFFGD();

        getVNFFGD(nsdID).getNetworkForwardingPaths().clear();

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
            if(nfp.getId().equals(nfpID))
                getVNFFGD(nsdID).getNetworkForwardingPaths().remove(nfp);
                return nfp;
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
        getPNF(nsdID).getPNFD().clear();
        getPNF(nsdID).getPNFD().addAll(pnf.getPNFD());

        return pnf;
    }

    public PNF deletePNF(String nsdID){
        PNF pnf = new PNF();

        getPNF(nsdID).getPNFD().clear();

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
            if(pnfd.getId().equals(pnfdID))
                getPNF(nsdID).getPNFD().remove(pnfd);
                return pnfd;
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
        getFlavours(nsdID).getServiceDeploymentFlavour().clear();
        getFlavours(nsdID).getServiceDeploymentFlavour().addAll(flavours.getServiceDeploymentFlavour());

        return flavours;
    }

    public PNF deleteFlavours(String nsdID){
        PNF pnf = new PNF();

        getFlavours(nsdID).getServiceDeploymentFlavour().clear();

        return pnf;
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
            if(s.getId().equals(sdfID))
                getFlavours(nsdID).getServiceDeploymentFlavour().remove(s);
                return s;
        }

        return null;
    }

    /* --- ConnectionPoints --- */
    public ConnectionPoints getConnectionPoints(String nsdID){
        return nsdMap.get(nsdID).getConnectionPoints();
    }

    public synchronized ConnectionPoints addConnectionPoints(String nsdID, ConnectionPoints cp){
        getConnectionPoints(nsdID).getConnectionPoint().clear();
        getConnectionPoints(nsdID).getConnectionPoint().addAll(cp.getConnectionPoint());

        return cp;
    }

    public ConnectionPoints deleteConnectionPoints(String nsdID){
        ConnectionPoints cp = new ConnectionPoints();

        getConnectionPoints(nsdID).getConnectionPoint().clear();

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
