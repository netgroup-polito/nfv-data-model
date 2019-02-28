package services;

import javax.ws.rs.ApplicationPath;

import db.NsDB;
import jaxb.NSD;
import jaxb.NS;

//Defines the base URI for all resource URIs.
@ApplicationPath("/")
//The java class declares root resource and provider classes
public class NsServices {
    private NsDB nsDB = NsDB.getNsdDB();

    /**
     * Get all the nsd and related elements of the network
     * @return All the NSD or null if the NS is void
     */
    public NS getNSD() {
        return nsDB.getNSD();
    }

    /**
     * Get the info of a NSD
     * @param nsdID
     * @return The NSD or null if the NSD does not exist
     */
    public NSD getNSDInfo(String nsdID){
        return nsDB.getNSDInfo(nsdID);
    }

    /**
     * Add a NSD
     * @param nsd
     * @return The NSD or null if the NSD exists
     */
    public NSD addNSD(NSD nsd) {
        return nsDB.addNSD(nsd);
    }


    /**
     * Delete a NSD
     * @param nsdID
     * @return The NSD or null if the NSD does not exist
     */
    public NSD deleteNSD(String nsdID) {
        return nsDB.deleteNSD(nsdID);
    }
}
