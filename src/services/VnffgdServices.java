package services;


import db.NsDB;
import jaxb.NetworkForwardingPaths;
import jaxb.VNFFGD;

import javax.ws.rs.ApplicationPath;

//Defines the base URI for all resource URIs.
@ApplicationPath("/")
//The java class declares root resource and provider classes
public class VnffgdServices {
    private NsDB nsDB = NsDB.getNsdDB();

    public VNFFGD getVNFFGD(String nsdID) {
        return nsDB.getVNFFGD(nsdID);
    }

    public VNFFGD addVNFFGD(String nsdID, VNFFGD vnffgd) {
        return nsDB.addVNFFGD(nsdID, vnffgd);
    }

    public VNFFGD deletevVNFFGD(String nsdID) {
        return nsDB.deletevVNFFGD(nsdID);
    }

    public NetworkForwardingPaths getNetworkForwardingPathsInfo(String nsdID, String nfpID) {
        return nsDB.getNetworkForwardingPathsInfo(nsdID, nfpID);
    }

    public NetworkForwardingPaths addNetworkForwardingPaths(String nsdID, NetworkForwardingPaths nfp) {
        return nsDB.addNetworkForwardingPaths(nsdID, nfp);
    }

    public NetworkForwardingPaths deleteNetworkForwardingPaths(String nsdID, NetworkForwardingPaths nfp) {
        return nsDB.deleteNetworkForwardingPaths(nsdID, nfp);
    }

    public NetworkForwardingPaths modifyNetworkForwardingPaths(String nsdID, NetworkForwardingPaths nfp) {
        return nsDB.modifyNetworkForwardingPaths(nsdID, nfp);
    }
}
