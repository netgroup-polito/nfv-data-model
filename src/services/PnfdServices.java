package services;

import db.NsDB;
import jaxb.PNF;
import jaxb.PNFD;

import javax.ws.rs.ApplicationPath;

//Defines the base URI for all resource URIs.
@ApplicationPath("/")
//The java class declares root resource and provider classes
public class PnfdServices {
    private NsDB nsDB = NsDB.getNsdDB();

    public PNF getPNF(String nsdID) {
        return nsDB.getPNF(nsdID);
    }

    public PNF addPNF(String nsdID, PNF pnf) {
        return nsDB.addPNF(nsdID, pnf);
    }

    public PNF deletevPNF(String nsdID) {
        return nsDB.deletevPNF(nsdID);
    }

    public PNFD getPNFDInfo(String nsdID, String pnfdID) {
        return nsDB.getPNFDInfo(nsdID, pnfdID);
    }

    public PNFD addPNFD(String nsdID, PNFD pnfd) {
        return nsDB.addPNFD(nsdID, pnfd);
    }

    public PNFD deletePNFD(String nsdID, PNFD pnfd) {
        return nsDB.deletePNFD(nsdID, pnfd);
    }

    public PNFD modifyPNFD(String nsdID, PNFD pnfd) {
        return nsDB.modifyPNFD(nsdID, pnfd);
    }
}
