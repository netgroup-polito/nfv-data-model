package it.polito.dp2.rest.nfv.services;

import javax.ws.rs.core.Application;

import it.polito.dp2.rest.nfv.db.NsDB;
import it.polito.dp2.rest.nfv.jaxb.VNF;
import it.polito.dp2.rest.nfv.jaxb.VNFD;

public class VnfdServices extends Application {
    private NsDB nsDB = NsDB.getNsdDB();

    /**
     * Get all the VNF inside the NSD
     * @param nsdID
     * @return The VNF or null if there are not virtual function inside
     */
    public VNF getVNF(String nsdID) {
        return nsDB.getVNF(nsdID);
    }
    /**
     * Add a VNF
     * @param nsdID
     * @param vnf
     * @return The added VNF or null if the operation doesn't succeed
     */
    public VNF addVNF(String nsdID, VNF vnf) {
        return nsDB.addVNF(nsdID, vnf);
    }

    /**
     * Delete VNF
     * @param nsdID
     * @return The void VNF
     */
    public VNF deleteVNF(String nsdID) {
        return nsDB.deleteVNF(nsdID);
    }

    /**
     * Get a VNFD info inside the VNF list
     * @param nsdID
     * @param vnfdID
     * @return The VNFD or null if there is not that virtual function inside
     */
    public VNFD getVNFDInfo(String nsdID, String vnfdID) {
        return nsDB.getVNFDInfo(nsdID, vnfdID);
    }

    /**
     * Add a VNFD inside the VNF
     * @param nsdID
     * @param vnfd
     * @return The added VNFD or null if the operation doesn't succeed
     */
    public VNFD addVNFD(String nsdID, VNFD vnfd) {
        return nsDB.addVNFD(nsdID, vnfd);
    }

    /**
     * Delete a VNFD
     * @param nsdID
     * @param vnfdID
     * @return VNFD removed or null if VNFD is not present
     */
    public VNFD deleteVNFD(String nsdID, String vnfdID) {
        return nsDB.deleteVNFD(nsdID, vnfdID);
    }

    /**
     * Modify a VNFD
     * @param nsdID
     * @param vnfd
     * @return VNFD modified or null if VNFD is not present
     */
    public VNFD modifyVNFD(String nsdID, VNFD vnfd) {
        return nsDB.modifyVNFD(nsdID, vnfd);
    }
}