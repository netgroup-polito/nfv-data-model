package it.polito.dp2.rest.nfv.services;

import it.polito.dp2.rest.nfv.db.*;
import it.polito.dp2.rest.nfv.jaxb.NFV;

public class NfvServices {
	private NfvDB nfvDB = NfvDB.getNfvDB();
	
	/**
     * Get all the NFV structure
     * @return The NFV or null if there are not element inside
     */
    public NFV getNFV() {
        return nfvDB.getNFV();
    }

    /**
     * Add a NS list
     * @param nfv
     * @return The NFV structure inserted or null if something wrong happened
     */
    public NFV addNFV(NFV nfv) {
        return nfvDB.addNFV(nfv);
    }

    /**
     * Clean the NFV structure
     * @return The NFV structure or null if the NSD exists
     */
    public void deleteNFV() {
    	nfvDB.deleteNFV();
    }

}
