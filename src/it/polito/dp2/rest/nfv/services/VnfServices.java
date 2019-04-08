package it.polito.dp2.rest.nfv.services;

import javax.ws.rs.core.Application;

import it.polito.dp2.rest.nfv.db.NsDB;
import it.polito.dp2.rest.nfv.jaxb.VNF;
import it.polito.dp2.rest.nfv.jaxb.VNFD;

import java.math.BigInteger;
import java.net.URI;

public class VnfServices extends Application {
    private NsDB nsDB = NsDB.getNsdDB();
    private Pagination pg = new Pagination();
    private int PAGE_SIZE = pg.getPageSize();

    /**
     * Get all the VNF inside the NSD
     * @param nsdID: the ID of the considered NSD
     * @param baseURI: base URI for that resources
     * @param page: the requested page
     * @return The VNF or null if the NSD does not exist or there are no VirtualNetworkFunction inside
     */
    public VNF getVNF(String nsdID, String baseURI, int page) {
        VNF vnf = nsDB.getVNF(nsdID);
        VNF pageVNF = new VNF();
        URI next = null;
        String path = "ns/nsd/" + nsdID + "/vnf";
        int pageNum = 0, totPage = 0;

        //do pagination if VNF exists in that NSD
        if(vnf == null){
            return null;
        }

        // Set actual page number and total number of pages
        pageNum = pg.getPage(page);

        int size = vnf.getVNFD().size();
        totPage = pg.getTotPage(size);

        // Pagination
        for(int i=(pageNum-1)*PAGE_SIZE, j=0; i<size && j<PAGE_SIZE; i++){
            pageVNF.getVNFD().add(vnf.getVNFD().get(i));
            j++;
        }

        pageVNF.setTotalPages(BigInteger.valueOf(totPage));
        pageVNF.setPage(BigInteger.valueOf(pageNum));

        next = pg.getNextPage(baseURI, path, pageNum, totPage);
        pageVNF.setNext(next.toString());

        return pageVNF;
    }
    /**
     * Add a VNF
     * @param nsdID: the ID of the considered NSD
     * @param vnf: the VNF to be added
     * @return The added VNF or null if the operation doesn't succeed
     */
    public VNF addVNF(String nsdID, VNF vnf) {
        return nsDB.addVNF(nsdID, vnf);
    }

    /**
     * Delete VNF
     * @param nsdID: the ID of the considered NSD
     * @return The void VNF
     */
    public VNF deleteVNF(String nsdID) {
        return nsDB.deleteVNF(nsdID);
    }

    /**
     * Get a VNFD info inside the VNF list
     * @param nsdID: the ID of the considered NSD
     * @param vnfdID: the ID of the VNFD
     * @return The VNFD or null if there is not that virtual function inside
     */
    public VNFD getVNFDInfo(String nsdID, String vnfdID) {
        return nsDB.getVNFDInfo(nsdID, vnfdID);
    }

    /**
     * Add a VNFD inside the VNF
     * @param nsdID: the ID of the considered NSD
     * @param vnfd: the VNFD to be added
     * @return The added VNFD or null if the operation doesn't succeed
     */
    public VNFD addVNFD(String nsdID, VNFD vnfd) {
        return nsDB.addVNFD(nsdID, vnfd);
    }

    /**
     * Delete a VNFD
     * @param nsdID: the ID of the considered NSD
     * @param vnfdID: the ID of the VNFD
     * @return VNFD removed or null if VNFD is not present
     */
    public VNFD deleteVNFD(String nsdID, String vnfdID) {
        return nsDB.deleteVNFD(nsdID, vnfdID);
    }

    /**
     * Modify a VNFD
     * @param nsdID: the ID of the considered NSD
     * @param vnfd: the VNFD to be modified
     * @return VNFD modified or null if VNFD is not present
     */
    public VNFD modifyVNFD(String nsdID, VNFD vnfd) {
        return nsDB.modifyVNFD(nsdID, vnfd);
    }
}