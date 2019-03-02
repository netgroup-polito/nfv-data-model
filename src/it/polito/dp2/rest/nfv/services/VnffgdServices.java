package it.polito.dp2.rest.nfv.services;


import it.polito.dp2.rest.nfv.db.NsDB;
import it.polito.dp2.rest.nfv.jaxb.NetworkForwardingPaths;
import it.polito.dp2.rest.nfv.jaxb.VNFFGD;
import it.polito.dp2.rest.nfv.jaxb.NetworkForwardingPaths;
import it.polito.dp2.rest.nfv.jaxb.VNFFGD;

import javax.ws.rs.ApplicationPath;

public class VnffgdServices {
    private NsDB nsDB = NsDB.getNsdDB();

    /**
     * Get all the VNFFGD inside the NSD
     * @param nsdID
     * @return The VNFFGD or null if there are not Forwarding graph inside
     */
    public VNFFGD getVNFFGD(String nsdID) {
        return nsDB.getVNFFGD(nsdID);
    }

    /**
     * Add a VNFFGD
     * @param nsdID
     * @param vnffgd
     * @return The added VNFFGD or null if the operation doesn't succeed
     */
    public VNFFGD addVNFFGD(String nsdID, VNFFGD vnffgd) {
        return nsDB.addVNFFGD(nsdID, vnffgd);
    }

    /**
     * Delete VNFFGD
     * @param nsdID
     * @return The void vnffgd
     */
    public VNFFGD deleteVNFFGD(String nsdID) {
        return nsDB.deleteVNFFGD(nsdID);
    }

    /**
     * Get a VNP info inside the VNFFGD
     * @param nsdID
     * @param nfpID
     * @return The VNFP or null if there is not that path inside
     */
    public NetworkForwardingPaths getNetworkForwardingPathsInfo(String nsdID, String nfpID) {
        return nsDB.getNetworkForwardingPathsInfo(nsdID, nfpID);
    }

    /**
     * Add a NFP inside the VNFFGD
     * @param nsdID
     * @param nfp
     * @return The added VNP or null if the operation doesn't succeed
     */
    public NetworkForwardingPaths addNetworkForwardingPaths(String nsdID, NetworkForwardingPaths nfp) {
        return nsDB.addNetworkForwardingPaths(nsdID, nfp);
    }

    /**
     * Delete a NFP
     * @param nsdID
     * @param nfpID
     * @return NFP removed or null if NFP is not present
     */
    public NetworkForwardingPaths deleteNetworkForwardingPaths(String nsdID, String nfpID) {
        return nsDB.deleteNetworkForwardingPaths(nsdID, nfpID);
    }

    /**
     * Modify a NFP
     * @param nsdID
     * @param nfp
     * @return NFP modified or null if NFP is not present
     */
    public NetworkForwardingPaths modifyNetworkForwardingPaths(String nsdID, NetworkForwardingPaths nfp) {
        return nsDB.modifyNetworkForwardingPaths(nsdID, nfp);
    }
}
