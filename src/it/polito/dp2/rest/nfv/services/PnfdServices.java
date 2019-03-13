package it.polito.dp2.rest.nfv.services;

import it.polito.dp2.rest.nfv.db.NsDB;
import it.polito.dp2.rest.nfv.jaxb.PNF;
import it.polito.dp2.rest.nfv.jaxb.PNFD;

public class PnfdServices {
    private NsDB nsDB = NsDB.getNsdDB();

    /**
     * Get all the PNF inside the NSD
     * @param nsdID
     * @return The PNF or null if there are not PNF defined
     */
    public PNF getPNF(String nsdID) {
        return nsDB.getPNF(nsdID);
    }

    /**
     * Add PNF
     * @param nsdID
     * @param pnf
     * @return The added PNF or null if the operation doesn't succeed
     */
    public PNF addPNF(String nsdID, PNF pnf) {
        return nsDB.addPNF(nsdID, pnf);
    }

    /**
     * Delete PNF
     * @param nsdID
     * @return The void PNF
     */
    public PNF deletePNF(String nsdID) {
        return nsDB.deletePNF(nsdID);
    }

    /**
     * Get a PNFD info inside the PNF list
     * @param nsdID
     * @param pnfdID
     * @return The PNFD or null if there is not that PNFD inside
     */
    public PNFD getPNFDInfo(String nsdID, String pnfdID) {
        return nsDB.getPNFDInfo(nsdID, pnfdID);
    }

    /**
     * Add a PNFD inside the PNF
     * @param nsdID
     * @param pnfd
     * @return The added PNFD or null if the operation doesn't succeed
     */
    public PNFD addPNFD(String nsdID, PNFD pnfd) {
        return nsDB.addPNFD(nsdID, pnfd);
    }

    /**
     * Delete a PNFD
     * @param nsdID
     * @param pnfdID
     * @return PNFD removed or null if PNFD is not present
     */
    public PNFD deletePNFD(String nsdID, String pnfdID) {
        return nsDB.deletePNFD(nsdID, pnfdID);
    }

    /**
     * Modify a PNFD
     * @param nsdID
     * @param pnfd
     * @return PNFD modified or null if PNFD is not present
     */
    public PNFD modifyPNFD(String nsdID, PNFD pnfd) {
        return nsDB.modifyPNFD(nsdID, pnfd);
    }
}
