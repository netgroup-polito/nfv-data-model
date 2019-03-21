package it.polito.dp2.rest.nfv.db;

import it.polito.dp2.rest.nfv.jaxb.*;

public class NfvDB {
	
    private static NfvDB nfvDB = new NfvDB();
    private PniDB pniDB = PniDB.getPniDB();
    private NsDB nsDB = NsDB.getNsdDB();
    
    public static NfvDB getNfvDB(){
        return nfvDB;
    }

    private NFV nfv;

    private NfvDB(){
        nfv = new NFV();
        nfv.setNS(nsDB.getNS());
        nfv.setPNI(pniDB.getPNI());
    }

    public NFV getNFV(){
        nfv.setNS(nsDB.getNS());
        nfv.setPNI(pniDB.getPNI());

        return nfv;
    }

    public NFV addNFV(NFV nfvAdd){
        deleteNFV();

        nfv.setPNI(pniDB.addPNI(nfvAdd.getPNI()));
        nfv.setNS(nsDB.addNS(nfvAdd.getNS()));

        return nfv;
    }

    public void deleteNFV(){
        pniDB.deletePNI();
        nsDB.deleteNS();

        nfv.setNS(nsDB.getNS());
        nfv.setPNI(pniDB.getPNI());
    }
}