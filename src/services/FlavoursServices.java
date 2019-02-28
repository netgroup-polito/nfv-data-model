package services;

import db.NsDB;
import jaxb.Flavours;
import jaxb.PNF;
import jaxb.ServiceDeploymentFlavour;

import javax.ws.rs.ApplicationPath;

//Defines the base URI for all resource URIs.
@ApplicationPath("/")
//The java class declares root resource and provider classes
public class FlavoursServices {
    private NsDB nsDB = NsDB.getNsdDB();

    public Flavours getFlavours(String nsdID) {
        return nsDB.getFlavours(nsdID);
    }

    public Flavours addFlavours(String nsdID, Flavours flavours) {
        return nsDB.addFlavours(nsdID, flavours);
    }

    public PNF deletevFlavours(String nsdID) {
        return nsDB.deletevFlavours(nsdID);
    }

    public ServiceDeploymentFlavour getServiceDeploymentFlavour(String nsdID, String sdfID) {
        return nsDB.getServiceDeploymentFlavour(nsdID, sdfID);
    }

    public ServiceDeploymentFlavour addServiceDeploymentFlavour(String nsdID, ServiceDeploymentFlavour sdf) {
        return nsDB.addServiceDeploymentFlavour(nsdID, sdf);
    }

    public ServiceDeploymentFlavour deleteServiceDeploymentFlavour(String nsdID, ServiceDeploymentFlavour sdf) {
        return nsDB.deleteServiceDeploymentFlavour(nsdID, sdf);
    }
}
