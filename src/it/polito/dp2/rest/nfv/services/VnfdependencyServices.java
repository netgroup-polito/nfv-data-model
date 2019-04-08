package it.polito.dp2.rest.nfv.services;

import it.polito.dp2.rest.nfv.db.NsDB;
import it.polito.dp2.rest.nfv.jaxb.Graph;
import it.polito.dp2.rest.nfv.jaxb.Node;
import it.polito.dp2.rest.nfv.jaxb.VNFDependency;

import javax.ws.rs.core.UriBuilder;
import java.math.BigInteger;
import java.net.URI;

public class VnfdependencyServices {
    private NsDB nsDB = NsDB.getNsdDB();
    private Pagination pg = new Pagination();
    private int PAGE_SIZE = pg.getPageSize();

    /**
     * Get all the graphs and related nodes inside the VNFDependency
     * @param nsdID: the ID of the considered NSD
     * @param baseURI: base URI for that resources
     * @param page: the requested page
     * @return The VNFDependency or null if there are not graphs inside
     */
    public VNFDependency getVNFDependency(String nsdID, String baseURI, int page) {
        VNFDependency vnfDependency = nsDB.getVNFDependency(nsdID);
        VNFDependency pageVNFDep = new VNFDependency();
        URI next = null;
        String path = "ns/nsd/" + nsdID + "/vnfdependency";
        int pageNum = 0, totPage = 0;

        //do pagination if VNFDependency exists in that NSD
        if(vnfDependency == null){
            return null;
        }

        // Set actual page number and total number of pages
        pageNum = pg.getPage(page);

        int size = vnfDependency.getGraph().size();
        totPage = pg.getTotPage(size);

        // Pagination
        for(int i=(pageNum-1)*PAGE_SIZE, j=0; i<size && j<PAGE_SIZE; i++){
            pageVNFDep.getGraph().add(vnfDependency.getGraph().get(i));
            j++;
        }

        pageVNFDep.setTotalPages(BigInteger.valueOf(totPage));
        pageVNFDep.setPage(BigInteger.valueOf(pageNum));

        next = pg.getNextPage(baseURI, path, pageNum, totPage);
        pageVNFDep.setNext(next.toString());

        return pageVNFDep;
    }

    /**
     * Add a VNFDependency
     * @param nsdID: the ID of the considered NSD
     * @param vnfDependency: VNFDependency to be added
     * @return The added VNFDependency or null if the operation doesn't succeed
     */
    public VNFDependency addVNFDependency(String nsdID, VNFDependency vnfDependency) {
        return nsDB.addVNFDependency(nsdID, vnfDependency);
    }

    /**
     * Delete VNFDependency
     * @param nsdID: the ID of the considered NSD
     * @return The void VNFDependency
     */
    public VNFDependency deleteVNFDependency(String nsdID) {
        return nsDB.deleteVNFDependency(nsdID);
    }

    /**
     * Get a graph inside the VNFDependency
     * @param nsdID: the ID of the considered NSD
     * @param graphID: the ID of the Graph
     * @return The Graph or null if there is not that graph inside
     */
    public Graph getGraph(String nsdID, Long graphID) {
        return nsDB.getGraph(nsdID, graphID);
    }

    /**
     * Add a Graph inside the VNFDependency
     * @param nsdID: the ID of the considered NSD
     * @param graph: the ID of the Graph
     * @return The added graph or null if the operation doesn't succeed
     */
    public Graph addGraph(String nsdID, Graph graph) {
        return nsDB.addGraph(nsdID, graph);
    }

    /**
     * Delete a Graph
     * @param nsdID: the ID of the considered NSD
     * @param graphID: the ID of the Graph
     * @return Graph removed or null if graph is not present
     */
    public Graph deleteGraph(String nsdID, Long graphID) {
        return nsDB.deleteGraph(nsdID, graphID);
    }

    /**
     * Get a node inside a Graph
     * @param nsdID: the ID of the considered NSD
     * @param graphID: the ID of the Graph
     * @param nodeName: the ID of the Node
     * @return The node or null if there is not that node inside
     */
    public Node getNode(String nsdID, Long graphID, String nodeName) {
        return nsDB.getNode(nsdID, graphID, nodeName);
    }

    /**
     * Add a Node inside a Graph
     * @param nsdID: the ID of the considered NSD
     * @param graphID: the ID of the Graph
     * @param node: the Node to be added
     * @return The added node or null if the operation doesn't succeed
     */
    public Node addNode(String nsdID, Long graphID, Node node) {
        return nsDB.addNode(nsdID, graphID, node);
    }

    /**
     * Delete a node
     * @param nsdID: the ID of the considered NSD
     * @param graphID: the ID of the Graph
     * @param nodeName: the ID of the Node
     * @return Node removed or null if node is not present
     */
    public Node deleteNode(String nsdID, Long graphID, String nodeName) {
        return nsDB.deleteNode(nsdID, graphID, nodeName);
    }

    /**
     * Modify a node
     * @param nsdID: the ID of the considered NSD
     * @param graphID: the ID of the Graph
     * @param node: the Node to be modified
     * @return Node modified or null if node is not present
     */
    public Node modifyNode(String nsdID, Long graphID, Node node){
        return nsDB.modifyNode(nsdID, graphID, node);
    }
}
