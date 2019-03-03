package it.polito.dp2.rest.nfv.services;

import it.polito.dp2.rest.nfv.db.NsDB;
import it.polito.dp2.rest.nfv.jaxb.NS;
import it.polito.dp2.rest.nfv.jaxb.NSD;

public class NsServices {
    private NsDB nsDB = NsDB.getNsdDB();

    /**
     * Get all the virtualized NSD
     * @return The NS or null if there are not graphs inside
     */
    public NS getNS() {
        return nsDB.getNS();
    }

    /**
     * Add a NS list
     * @param ns
     * @return The NS or null if the NSD exists
     */
    public NS addNS(NS ns) {
        return nsDB.addNS(ns);
    }

    /**
     * Delete all NS
     * @return The NS or null if the NSD exists
     */
    public void deleteNS() {
        nsDB.deleteNS();
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
