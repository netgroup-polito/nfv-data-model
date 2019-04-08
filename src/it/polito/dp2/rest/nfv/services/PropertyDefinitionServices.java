package it.polito.dp2.rest.nfv.services;

import it.polito.dp2.rest.nfv.db.NsDB;
import it.polito.dp2.rest.nfv.jaxb.Property;
import it.polito.dp2.rest.nfv.jaxb.PropertyDefinition;

import java.math.BigInteger;
import java.net.URI;

public class PropertyDefinitionServices {
    private NsDB nsDB = NsDB.getNsdDB();
    private Pagination pg = new Pagination();
    private int PAGE_SIZE = pg.getPageSize();

    /**
     * Get all the PropertyDefinition
     * @param nsdID: the ID of the considered NSD
     * @param page: the requested page
     * @param baseURI: base URI for that resources
     * @return The PropertyDefinition or null if the NSD does not exist or there are not PropertyDefinition defined
     */
    public PropertyDefinition getPropertyDefinition(String nsdID, String baseURI, int page) {
        PropertyDefinition propertyDefinition = nsDB.getPropertyDefinition(nsdID);
        PropertyDefinition pagePropDef = new PropertyDefinition();
        URI next = null;
        String path = "ns/nsd/" + nsdID +"/propertydefinition";
        int pageNum = 0, totPage = 0;

        //do pagination if PropertyDefinition exists in that NSD
        if(propertyDefinition == null){
            return null;
        }

        // Set actual page number and total number of pages
        pageNum = pg.getPage(page);

        int size = propertyDefinition.getProperty().size();
        totPage = pg.getTotPage(size);

        // Pagination
        for(int i=(pageNum-1)*PAGE_SIZE, j=0; i<size && j<PAGE_SIZE; i++){
            pagePropDef.getProperty().add(propertyDefinition.getProperty().get(i));
            j++;
        }

        pagePropDef.setTotalPages(BigInteger.valueOf(totPage));
        pagePropDef.setPage(BigInteger.valueOf(pageNum));

        next = pg.getNextPage(baseURI, path, pageNum, totPage);
        pagePropDef.setNext(next.toString());

        return pagePropDef;
    }

    /**
     * Add Property Definition
     * @param nsdID: the ID of the considered NSD
     * @param propertyDef: PropertyDefinition to be added
     * @return The added PropertyDefinition or null if the operation doesn't succeed
     */
    public PropertyDefinition addPropertyDefinition(String nsdID, PropertyDefinition propertyDef) {
		return nsDB.addPropertyDefinition(nsdID, propertyDef);
	}

    /**
     * Delete PropertyDefinition
     * @param nsdID: the ID of the considered NSD
     * @return The deleted Property
     */
	public PropertyDefinition deletePropertyDefinition(String nsdID) {
		return nsDB.deletePropertyDefinition(nsdID);
	}

	/**
     * Get a Property
     * @param nsdID: the ID of the considered NSD
     * @param graphID: the ID of the Graph
     * @return The Property or null if there are not Property defined
     */
    public Property getProperty(String nsdID, Long graphID) {
        return nsDB.getProperty(nsdID, graphID);
    }

    /**
     * Add Property
     * @param nsdID: the ID of the considered NSD
     * @param property: Property to be added
     * @return The added Property or null if the operation doesn't succeed
     */
    public Property addProperty(String nsdID, Property property) {
        return nsDB.addProperty(nsdID, property);
    }

    /**
     * Delete Property
     * @param nsdID: the ID of the considered NSD
     * @return The deleted Property
     */
    public Property deleteProperty(String nsdID, Long graphID) {
        return nsDB.deleteProperty(nsdID, graphID);
    }

    /**
     * Modify a Property
     * @param nsdID: the ID of the considered NSD
     * @param property: Property to be modified
     * @return The modified Property
     */
    public Property modifyProperty(String nsdID, Property property) {
        return nsDB.modifyProperty(nsdID, property);
    }
}