import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import jaxb.*;

public class NsdDB {
    private static NsdDB nsdDB = new NsdDB();

    public static NsdDB getNsdDB(){
        return nsdDB;
    }

    private ConcurrentMap<Long, Graph> graphMap;
    private ConcurrentMap<String, PropertyDefinition> propertyMap;
    private ConcurrentMap<String, VNFD> vnfdMap;
    private ConcurrentMap<String, VNFFGD> vnffgdMap;
    private ConcurrentMap<String, PNFD> pnfdMap;
    private ConcurrentMap<String, ServiceDeploymentFlavour> flavourMap;
    private ConcurrentMap<String, ConnectionPoint> connectionpointMap;

    private NsdDB(){
        graphMap = new ConcurrentHashMap<Long, Graph>();
        propertyMap = new ConcurrentHashMap<String, PropertyDefinition>();
        vnfdMap = new ConcurrentHashMap<String, VNFD>();
        vnffgdMap = new ConcurrentHashMap<String, VNFFGD>();
        pnfdMap = new ConcurrentHashMap<String, PNFD>();
        flavourMap = new ConcurrentHashMap<String, ServiceDeploymentFlavour>();
        connectionpointMap = new ConcurrentHashMap<String, ConnectionPoint>();
    }

    /* --- Graph --- */
    public VNFDependency getGraphs(){
        VNFDependency vnfdep = new VNFDependency();

        for(Graph g : graphMap.values())
            vnfdep.getGraph().add(g);

        return vnfdep;
    }

    public Graph getGraphInfo(Long graphID){
        return graphMap.get(graphID);
    }

    public synchronized Graph addGraph(Graph graph){
        if(!graphMap.containsKey(graph.getId())){
            graphMap.put(graph.getId(), graph);
            return graph;
        }else{
            return null;
        }
    }

    public Graph deleteGraph(Long graphID){
        return graphMap.remove(graphID);
    }

    public synchronized Node addNodetoGraph(Long graphID, Node node){
        if(!graphMap.get(graphID).getNode().contains(node)){
            graphMap.get(graphID).getNode().add(node);
            return node;
        }else{
            return null;
        }
    }

    public Node deleteNodefromGraph(Long graphID, Node node){
        if(graphMap.get(graphID).getNode().contains(node)){
            graphMap.get(graphID).getNode().remove(node);
            return node;
        }else{
            return null;
        }
    }

    /* --- VNFD --- */
    public ArrayList<VNFD> getVNFDlist(){
        ArrayList<VNFD> vnfdlist = new ArrayList<VNFD>();

        for(VNFD v : vnfdMap.values())
            vnfdlist.add(v);

        return vnfdlist;
    }

    public VNFD getVNFDInfo(String vnfdID){
        return vnfdMap.get(vnfdID);
    }

    public synchronized VNFD addVNFD(VNFD vnfd){
        if(!vnfdMap.containsKey(vnfd.getId())){
            vnfdMap.put(vnfd.getId(), vnfd);
            return vnfd;
        }else{
            return null;
        }
    }

    public VNFD deleteVNFD(String vnfdID){
        return vnfdMap.remove(vnfdID);
    }

    public VNFD modifyVNFD(VNFD vnfd){
        return vnfdMap.replace(vnfd.getId(), vnfd);
    }

    public synchronized VirtualLink addVL(VirtualLink vl){
        if(vnfdMap.get(vl.getSrc())!=null && vnfdMap.get(vl.getDst())!=null){
            vnfdMap.get(vl.getSrc()).getVirtualLink().add(vl);
            vnfdMap.get(vl.getDst()).getVirtualLink().add(vl);
            return vl;
        }else{
            return null;
        }
    }

    public VirtualLink deleteVL(VirtualLink vl){
        if(vnfdMap.get(vl.getSrc()).getVirtualLink().contains(vl) && vnfdMap.get(vl.getDst()).getVirtualLink().contains(vl)){
            vnfdMap.get(vl.getSrc()).getVirtualLink().remove(vl);
            vnfdMap.get(vl.getDst()).getVirtualLink().remove(vl);
            return vl;
        }else{
            return null;
        }
    }

    public synchronized VDU addVDU(String vnfdID, VDU vdu){
        if(!vnfdMap.get(vnfdID).getVDU().contains(vdu)){
            vnfdMap.get(vnfdID).getVDU().add(vdu);
            return vdu;
        }else{
            return null;
        }
    }

    public VDU deleteVDU(String vnfdID, VDU vdu){
        if(vnfdMap.get(vnfdID).getVDU().contains(vdu)){
            vnfdMap.get(vnfdID).getVDU().remove(vdu);
            return vdu;
        }else{
            return null;
        }
    }

    public synchronized Dependency.Relation addDependency(String vnfdID, Dependency.Relation relation){
        boolean findSrc = false, findDst = false;

        for(VDU vdu: vnfdMap.get(vnfdID).getVDU()) {
            if(vdu.getId().equals(relation.getSrc()))
                findSrc = true;
            if(vdu.getId().equals(relation.getTarget()))
                findDst = true;
        }
        if(findSrc && findDst){
            vnfdMap.get(vnfdID).getDependency().getRelation().add(relation);
            return relation;
        }else{
            return null;
        }
    }

    public Dependency.Relation deleteDependency(String vnfdID, Dependency.Relation relation){
        if(vnfdMap.get(vnfdID).getDependency().getRelation().contains(relation)) {
            vnfdMap.get(vnfdID).getDependency().getRelation().remove(relation);
            return relation;
        }else{
            return null;
        }
    }
}
