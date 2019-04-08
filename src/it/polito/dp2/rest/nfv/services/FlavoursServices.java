package it.polito.dp2.rest.nfv.services;

import it.polito.dp2.rest.nfv.db.NsDB;
import it.polito.dp2.rest.nfv.jaxb.Flavours;
import it.polito.dp2.rest.nfv.jaxb.ServiceDeploymentFlavour;

import java.math.BigInteger;
import java.net.URI;

public class FlavoursServices {
    private NsDB nsDB = NsDB.getNsdDB();
    private Pagination pg = new Pagination();
    private int PAGE_SIZE = pg.getPageSize();

    /**
     * Get all the flavours defined for the NSD
     * @param nsdID: the ID of the considered NSD
     * @param page: the requested page
     * @param baseURI: base URI for that resources
     * @return The Flavours or null if the NSD does not exist or there are not ServiceDeploymentFlavour defined
     */
    public Flavours getFlavours(String nsdID, String baseURI, int page) {
        Flavours flavours = nsDB.getFlavours(nsdID);
        Flavours pageFlav = new Flavours();
        URI next = null;
        String path = "ns/nsd/" + nsdID + "/flavours";
        int pageNum = 0, totPage = 0;

        //do pagination if Flavours exists in that NSD
        if(flavours == null){
            return null;
        }

        // Set actual page number and total number of pages
        pageNum = pg.getPage(page);

        int size = flavours.getServiceDeploymentFlavour().size();
        totPage = pg.getTotPage(size);

        // Pagination
        for(int i=(pageNum-1)*PAGE_SIZE, j=0; i<size && j<PAGE_SIZE; i++){
            pageFlav.getServiceDeploymentFlavour().add(flavours.getServiceDeploymentFlavour().get(i));
            j++;
        }

        pageFlav.setTotalPages(BigInteger.valueOf(totPage));
        pageFlav.setPage(BigInteger.valueOf(pageNum));

        next = pg.getNextPage(baseURI, path, pageNum, totPage);
        pageFlav.setNext(next.toString());

        return pageFlav;
    }

    /**
     * Add Flavours
     * @param nsdID: the ID of the considered NSD
     * @param flavours: the Flavours to be added
     * @return The added Flavours or null if the operation doesn't succeed
     */
    public Flavours addFlavours(String nsdID, Flavours flavours) {
        return nsDB.addFlavours(nsdID, flavours);
    }

    /**
     * Delete Flavours
     * @param nsdID: the ID of the considered NSD
     * @return The void Flavours
     */
    public Flavours deleteFlavours(String nsdID) {
        return nsDB.deleteFlavours(nsdID);
    }

    /**
     * Get a flavour info inside the Flavours
     * @param nsdID: the ID of the considered NSD
     * @param sdfID: the ID of the ServiceDeploymentFlavour
     * @return The ServiceDeploymentFlavour or null if the NSD does not exist or there is not that ServiceDeploymentFlavour inside
     */
    public ServiceDeploymentFlavour getServiceDeploymentFlavour(String nsdID, String sdfID) {
        return nsDB.getServiceDeploymentFlavour(nsdID, sdfID);
    }

    /**
     * Add a ServiceDeploymentFlavour inside the Flavours
     * @param nsdID: the ID of the considered NSD
     * @param sdf: the ServiceDeploymentFlavour to be added
     * @return The added ServiceDeploymentFlavour or null if the operation doesn't succeed
     */
    public ServiceDeploymentFlavour addServiceDeploymentFlavour(String nsdID, ServiceDeploymentFlavour sdf) {
        return nsDB.addServiceDeploymentFlavour(nsdID, sdf);
    }

    /**
     * Delete a ServiceDeploymentFlavour
     * @param nsdID: the ID of the considered NSD
     * @param sdfID: the ID of the ServiceDeploymentFlavours
     * @return ServiceDeploymentFlavour removed or null if ServiceDeploymentFlavour is not present
     */
    public ServiceDeploymentFlavour deleteServiceDeploymentFlavour(String nsdID, String sdfID) {
        return nsDB.deleteServiceDeploymentFlavour(nsdID, sdfID);
    }
}
