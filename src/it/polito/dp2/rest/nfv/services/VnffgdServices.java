package it.polito.dp2.rest.nfv.services;

import it.polito.dp2.rest.nfv.db.NsDB;
import it.polito.dp2.rest.nfv.jaxb.NetworkForwardingPaths;
import it.polito.dp2.rest.nfv.jaxb.VNFFGD;

import java.math.BigInteger;
import java.net.URI;

public class VnffgdServices {
    private NsDB nsDB = NsDB.getNsdDB();
    private Pagination pg = new Pagination();
    private int PAGE_SIZE = pg.getPageSize();

    /**
     * Get all the VNFFGD inside the NSD
     * @param nsdID: the ID of the considered NSD
     * @param baseURI: base URI for that resources
     * @param page: the requested page
     * @return The VNFFGD or null if the NSD does not exist ot there are not Forwarding graph inside
     */
    public VNFFGD getVNFFGD(String nsdID, String baseURI, int page) {
        VNFFGD vnffgd = nsDB.getVNFFGD(nsdID);
        VNFFGD pageVNFFGD = new VNFFGD();
        URI next = null;
        String path = "ns/nsd/" + nsdID + "/vnffgd";
        int pageNum = 0, totPage = 0;

        //do pagination if VNFFGD exists in that NSD
        if(vnffgd == null){
            return null;
        }

        // Set actual page number and total number of pages
        pageNum = pg.getPage(page);

        int size = vnffgd.getNetworkForwardingPaths().size();
        totPage = pg.getTotPage(size);

        // Pagination
        for(int i=(pageNum-1)*PAGE_SIZE, j=0; i<size && j<PAGE_SIZE; i++){
            pageVNFFGD.getNetworkForwardingPaths().add(vnffgd.getNetworkForwardingPaths().get(i));
            j++;
        }

        pageVNFFGD.setTotalPages(BigInteger.valueOf(totPage));
        pageVNFFGD.setPage(BigInteger.valueOf(pageNum));

        next = pg.getNextPage(baseURI, path, pageNum, totPage);
        pageVNFFGD.setNext(next.toString());

        return pageVNFFGD;
    }

    /**
     * Add a VNFFGD
     * @param nsdID: the ID of the considered NSD
     * @param vnffgd: VNNFGD to be added
     * @return The added VNFFGD or null if the operation doesn't succeed
     */
    public VNFFGD addVNFFGD(String nsdID, VNFFGD vnffgd) {
        return nsDB.addVNFFGD(nsdID, vnffgd);
    }

    /**
     * Delete VNFFGD
     * @param nsdID: the ID of the considered NSD
     * @return The void vnffgd
     */
    public VNFFGD deleteVNFFGD(String nsdID) {
        return nsDB.deleteVNFFGD(nsdID);
    }

    /**
     * Get a VNP info inside the VNFFGD
     * @param nsdID: the ID of the considered NSD
     * @param nfpID: the id of the NetworkForwardingPath
     * @return The VNFP or null if there is not that path inside
     */
    public NetworkForwardingPaths getNetworkForwardingPathsInfo(String nsdID, String nfpID) {
        return nsDB.getNetworkForwardingPathsInfo(nsdID, nfpID);
    }

    /**
     * Add a NFP inside the VNFFGD
     * @param nsdID: the ID of the considered NSD
     * @param nfp: the NetworkForwardingPath to be added
     * @return The added VNP or null if the operation doesn't succeed
     */
    public NetworkForwardingPaths addNetworkForwardingPaths(String nsdID, NetworkForwardingPaths nfp) {
        return nsDB.addNetworkForwardingPaths(nsdID, nfp);
    }

    /**
     * Delete a NFP
     * @param nsdID: the ID of the considered NSD
     * @param nfpID: the ID of the NetworkForwardingPath
     * @return NFP removed or null if NFP is not present
     */
    public NetworkForwardingPaths deleteNetworkForwardingPaths(String nsdID, String nfpID) {
        return nsDB.deleteNetworkForwardingPaths(nsdID, nfpID);
    }

    /**
     * Modify a NFP
     * @param nsdID: the ID of the considered NSD
     * @param nfp: the NetworkForwardingPath to be modified
     * @return NFP modified or null if NFP is not present
     */
    public NetworkForwardingPaths modifyNetworkForwardingPaths(String nsdID, NetworkForwardingPaths nfp) {
        return nsDB.modifyNetworkForwardingPaths(nsdID, nfp);
    }
}
