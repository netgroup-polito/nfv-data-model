package services;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import db.NsDB;
import jaxb.VNF;
import jaxb.VNFD;

//Defines the base URI for all resource URIs.
@ApplicationPath("/")
//The java class declares root resource and provider classes
public class VnfdServices extends Application {
    private NsDB nsDB = NsDB.getNsdDB();

    public VNF getVNF(String nsdID) {
        return nsDB.getVNF(nsdID);
    }

    public VNF addVNF(String nsdID, VNF vnf) {
        return nsDB.addVNF(nsdID, vnf);
    }

    public VNF deleteVNF(String nsdID) {
        return nsDB.deleteVNF(nsdID);
    }

    public VNFD getVNFDInfo(String nsdID, String vnfdID) {
        return nsDB.getVNFDInfo(nsdID, vnfdID);
    }

    public VNFD addVNFD(String nsdID, VNFD vnfd) {
        return nsDB.addVNFD(nsdID, vnfd);
    }

    public VNFD deleteVNFD(String nsdID, VNFD vnfd) {
        return nsDB.deleteVNFD(nsdID, vnfd);
    }

    public VNFD modifyVNFD(String nsdID, VNFD vnfd) {
        return nsDB.modifyVNFD(nsdID, vnfd);
    }
}