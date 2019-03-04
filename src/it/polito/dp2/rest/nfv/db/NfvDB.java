package it.polito.dp2.rest.nfv.db;

import it.polito.dp2.rest.nfv.jaxb.*;

public class NfvDB {
	
    private static NfvDB nfvDB = new NfvDB();
    private PniDB pniDB = PniDB.getPniDB();
    private NsDB nsDB = NsDB.getNsdDB();
    
    public static NfvDB getNfvDB(){
        return nfvDB;
    }

    NFV nfv;

    private NfvDB(){
        nfv = new NFV();
        nfv.setNS(new NS());
        nfv.setPNI(new PNI());
    }

    public NFV getNFV(){
    	nfv.setPNI(pniDB.getPNI());
    	nfv.setNS(nsDB.getNS());
    	
        return nfv;
    }

    public NFV addNFV(NFV nfvAdd){
        deleteNFV();

        nfv.setNS(nfvAdd.getNS());
        nfv.setPNI(nfvAdd.getPNI());

        return nfv;
    }

    public void deleteNFV(){
    	nfv.setPNI(new PNI());
    	nfv.setNS(new NS());

    }
    
}
