package it.polito.dp2.rest.nfv.services;

import it.polito.dp2.rest.nfv.db.NsDB;
import it.polito.dp2.rest.nfv.jaxb.Graph;
import it.polito.dp2.rest.nfv.jaxb.Node;
import it.polito.dp2.rest.nfv.jaxb.VNFDependency;

public class VnfdependencyServices {
    private NsDB nsDB = NsDB.getNsdDB();

    /**
     * Get all the graphs and related nodes inside the VNFDependency
     * @param nsdID
     * @return The VNFDependency or null if there are not graphs inside
     */
    public VNFDependency getVNFDependency(String nsdID) {
        return nsDB.getVNFDependency(nsdID);
    }

    /**
     * Add a VNFDependency
     * @param nsdID
     * @param vnfDependency
     * @return The added VNFDependency or null if the operation doesn't succeed
     */
    public VNFDependency addVNFDependency(String nsdID, VNFDependency vnfDependency) {
        return nsDB.addVNFDependency(nsdID, vnfDependency);
    }

    /**
     * Delete VNFDependency
     * @param nsdID
     * @return The void VNFDependency
     */
    public VNFDependency deleteVNFDependency(String nsdID) {
        return nsDB.deleteVNFDependency(nsdID);
    }

    /**
     * Get a graph inside the VNFDependency
     * @param nsdID
     * @param graphID
     * @return The Graph or null if there is not that graph inside
     */
    public Graph getGraph(String nsdID, Long graphID) {
        return nsDB.getGraph(nsdID, graphID);
    }

    /**
     * Add a Graph inside the VNFDependency
     * @param nsdID
     * @param graph
     * @return The added graph or null if the operation doesn't succeed
     */
    public Graph addGraph(String nsdID, Graph graph) {
        return nsDB.addGraph(nsdID, graph);
    }

    /**
     * Delete a Graph
     * @param nsdID
     * @param graphID
     * @return Graph removed or null if graph is not present
     */
    public Graph deleteGraph(String nsdID, Long graphID) {
        return nsDB.deleteGraph(nsdID, graphID);
    }

    /**
     * Get a node inside a Graph
     * @param nsdID
     * @param graphID
     * @param nodeName
     * @return The node or null if there is not that node inside
     */
    public Node getNode(String nsdID, Long graphID, String nodeName) {
        return nsDB.getNode(nsdID, graphID, nodeName);
    }

    /**
     * Add a Node inside a Graph
     * @param nsdID
     * @param graphID
     * @param node
     * @return The added node or null if the operation doesn't succeed
     */
    public Node addNode(String nsdID, Long graphID, Node node) {
        return nsDB.addNode(nsdID, graphID, node);
    }

    /**
     * Delete a node
     * @param nsdID
     * @param graphID
     * @param nodeName
     * @return Node removed or null if node is not present
     */
    public Node deleteNode(String nsdID, Long graphID, String nodeName) {
        return nsDB.deleteNode(nsdID, graphID, nodeName);
    }
}
