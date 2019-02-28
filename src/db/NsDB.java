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

    public NS getNSD(){
        NS ns = new NS();

        if(ns.getNSD().addAll(nsdMap.values()))
            return ns;

        return null;
    }

    public NSD getNSDInfo(String nsdID){
        return nsdMap.get(nsdID);
    }

    public NSD addNSD(NSD nsd){
        return nsdMap.put(nsd.getId(), nsd);
    }

    public NSD deleteNSD(String nsdID){
        return nsdMap.remove(nsdID);
    }

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
        if(getGraph(nsdID, graph.getId()) != null){
            if(nsdMap.get(nsdID).getVNFDependency().getGraph().add(graph))
                return graph;
        }

        return null;
    }

    public Graph deleteGraph(String nsdID, Long graphID){
        for(Graph graph : getVNFDependency(nsdID).getGraph()) {
            if(graph.getId().equals(graphID))
                getVNFDependency(nsdID).getGraph().remove(graph);
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
        if(getGraph(nsdID, graphID).getNode().add(node))
            return node;

        return null;
    }

    public Node deleteNode(String nsdID, Long graphID, String nodeName){
        for(Node node : getGraph(nsdID, graphID).getNode()) {
            if(node.getName().equals(nodeName))
                getGraph(nsdID, graphID).getNode().remove(node);
        }

        return null;
    }

    public VNF getVNF(String nsdID){
        return nsdMap.get(nsdID).getVNF();
    }

    public VNFD getVNFDInfo(String nsdID, String vnfdID){
        for(VNFD vnfd : getVNF(nsdID).getVNFD()) {
            if(vnfd.getId().equals(vnfdID))
                return vnfd;
        }

        return null;
    }

    public VNFFGD getVNFFGD(String nsdID){
        return nsdMap.get(nsdID).getVNFFGD();
    }

    public PNF getPNF(String nsdID){
        return nsdMap.get(nsdID).getPNF();
    }

    public Flavours getFlavours(String nsdID){
        return nsdMap.get(nsdID).getFlavours();
    }

    public ConnectionPoints getConnectionPoints(String nsdID){
        return nsdMap.get(nsdID).getConnectionPoints();
    }
}
