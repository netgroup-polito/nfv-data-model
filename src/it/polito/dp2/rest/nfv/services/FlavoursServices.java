package it.polito.dp2.rest.nfv.services;

import it.polito.dp2.rest.nfv.db.NsDB;
import it.polito.dp2.rest.nfv.jaxb.Flavours;
import it.polito.dp2.rest.nfv.jaxb.ServiceDeploymentFlavour;
import it.polito.dp2.rest.nfv.jaxb.Flavours;
import it.polito.dp2.rest.nfv.jaxb.PNF;
import it.polito.dp2.rest.nfv.jaxb.ServiceDeploymentFlavour;

import javax.ws.rs.ApplicationPath;

public class FlavoursServices {
    private NsDB nsDB = NsDB.getNsdDB();

    /**
     * Get all the flavours defined for the NSD
     * @param nsdID
     * @return The Flavours or null if there are not ServiceDeploymentFlavour defined
     */
    public Flavours getFlavours(String nsdID) {
        return nsDB.getFlavours(nsdID);
    }

    /**
     * Add Flavours
     * @param nsdID
     * @param flavours
     * @return The added Flavours or null if the operation doesn't succeed
     */
    public Flavours addFlavours(String nsdID, Flavours flavours) {
        return nsDB.addFlavours(nsdID, flavours);
    }

    /**
     * Delete Flavours
     * @param nsdID
     * @return The void flavours
     */
    public PNF deleteFlavours(String nsdID) {
        return nsDB.deleteFlavours(nsdID);
    }

    /**
     * Get a flavour info inside the Flavours
     * @param nsdID
     * @param sdfID
     * @return The ServiceDeploymentFlavour or null if there is not that ServiceDeploymentFlavour inside
     */
    public ServiceDeploymentFlavour getServiceDeploymentFlavour(String nsdID, String sdfID) {
        return nsDB.getServiceDeploymentFlavour(nsdID, sdfID);
    }

    /**
     * Add a ServiceDeploymentFlavour inside the Flavours
     * @param nsdID
     * @param sdf
     * @return The added ServiceDeploymentFlavour or null if the operation doesn't succeed
     */
    public ServiceDeploymentFlavour addServiceDeploymentFlavour(String nsdID, ServiceDeploymentFlavour sdf) {
        return nsDB.addServiceDeploymentFlavour(nsdID, sdf);
    }

    /**
     * Delete a ServiceDeploymentFlavour
     * @param nsdID
     * @param sdfID
     * @return ServiceDeploymentFlavour removed or null if ServiceDeploymentFlavour is not present
     */
    public ServiceDeploymentFlavour deleteServiceDeploymentFlavour(String nsdID, String sdfID) {
        return nsDB.deleteServiceDeploymentFlavour(nsdID, sdfID);
    }
}
