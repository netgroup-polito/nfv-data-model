package it.polito.dp2.rest.nfv.services;

import it.polito.dp2.rest.nfv.db.NsDB;
import it.polito.dp2.rest.nfv.jaxb.PNF;
import it.polito.dp2.rest.nfv.jaxb.PNFD;

import java.math.BigInteger;
import java.net.URI;

public class PnfServices {
    private NsDB nsDB = NsDB.getNsdDB();
    private Pagination pg = new Pagination();
    private int PAGE_SIZE = pg.getPageSize();

    /**
     * Get all the PNF inside the NSD
     * @param nsdID: the ID of the considered NSD
     * @param baseURI: the base URI for that resources
     * @param page: the requested page
     * @return The PNF or null if the NSD does not exist or there are not PNF defined
     */
    public PNF getPNF(String nsdID, String baseURI, int page) {
        PNF pnf = nsDB.getPNF(nsdID);
        PNF pagePNF = new PNF();
        URI next = null;
        String path = "ns/nsd/" + nsdID + "/pbf";
        int pageNum = 0, totPage = 0;

        //do pagination if VNFFGD exists in that NSD
        if(pnf == null){
            return null;
        }

        // Set actual page number and total number of pages
        pageNum = pg.getPage(page);

        int size = pnf.getPNFD().size();
        totPage = pg.getTotPage(size);

        // Pagination
        for(int i=(pageNum-1)*PAGE_SIZE, j=0; i<size && j<PAGE_SIZE; i++){
            pagePNF.getPNFD().add(pnf.getPNFD().get(i));
            j++;
        }

        pagePNF.setTotalPages(BigInteger.valueOf(totPage));
        pagePNF.setPage(BigInteger.valueOf(pageNum));

        next = pg.getNextPage(baseURI, path, pageNum, totPage);
        pagePNF.setNext(next.toString());

        return pagePNF;
    }

    /**
     * Add PNF
     * @param nsdID:the ID of the considered NSD
     * @param pnf: the PNF to be added
     * @return The added PNF or null if the operation doesn't succeed
     */
    public PNF addPNF(String nsdID, PNF pnf) {
        return nsDB.addPNF(nsdID, pnf);
    }

    /**
     * Delete PNF
     * @param nsdID: the ID of the NSD
     * @return The void PNF
     */
    public PNF deletePNF(String nsdID) {
        return nsDB.deletePNF(nsdID);
    }

    /**
     * Get a PNFD info inside the PNF list
     * @param nsdID: the ID of the considered NSD
     * @param pnfdID: the ID of the PNFD
     * @return The PNFD or null if the NSD does not exist or there is not that PNFD inside
     */
    public PNFD getPNFDInfo(String nsdID, String pnfdID) {
        return nsDB.getPNFDInfo(nsdID, pnfdID);
    }

    /**
     * Add a PNFD inside the PNF
     * @param nsdID: the ID of the considered NSD
     * @param pnfd: the ID of the PNFD
     * @return The added PNFD or null if the operation doesn't succeed
     */
    public PNFD addPNFD(String nsdID, PNFD pnfd) {
        return nsDB.addPNFD(nsdID, pnfd);
    }

    /**
     * Delete a PNFD
     * @param nsdID: the ID of the considered NSD
     * @param pnfdID: the ID of the PNFD
     * @return PNFD removed or null if the NSD does not exist or the PNFD is not present inside
     */
    public PNFD deletePNFD(String nsdID, String pnfdID) {
        return nsDB.deletePNFD(nsdID, pnfdID);
    }

    /**
     * Modify a PNFD
     * @param nsdID: the ID of the considered NSD
     * @param pnfd: the ID of the PNFD
     * @return PNFD modified or null if the NSD does not exist or the PNFD is not present inside
     */
    public PNFD modifyPNFD(String nsdID, PNFD pnfd) {
        return nsDB.modifyPNFD(nsdID, pnfd);
    }
}
