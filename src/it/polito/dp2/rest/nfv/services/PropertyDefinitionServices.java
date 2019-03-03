package it.polito.dp2.rest.nfv.services;

import it.polito.dp2.rest.nfv.db.NsDB;
import it.polito.dp2.rest.nfv.jaxb.Property;
import it.polito.dp2.rest.nfv.jaxb.PropertyDefinition;

public class PropertyDefinitionServices {
    private NsDB nsDB = NsDB.getNsdDB();

    /**
     * Get all the PropertyDefinition
     * @param nsdID
     * @return The PropertyDefintion or null if there are not PropertyDefinition defined
     */
    public PropertyDefinition getPropertyDefinition(String nsdID) {
        return nsDB.getPropertyDefinition(nsdID);
    }

    /**
     * Get all a Property
     * @param nsdID
     * @return The Property or null if there are not Property defined
     */
    public Property getProperty(String nsdID, Long graphID) {
        return nsDB.getProperty(nsdID, graphID);
    }

    /**
     * Add Property
     * @param nsdID
     * @param property
     * @return The added Property or null if the operation doesn't succeed
     */
    public Property addProperty(String nsdID, Property property) {
        return nsDB.addProperty(nsdID, property);
    }

    /**
     * Delete Property
     * @param nsdID
     * @return The deleted Property
     */
    public Property deleteProperty(String nsdID, Long graphID) {
        return nsDB.deleteProperty(nsdID, graphID);
    }

    /**
     * Modify a Property
     * @param nsdID
     * @return The modified Property
     */
    public Property modifyProperty(String nsdID, Property property) {
        return nsDB.modifyProperty(nsdID, property);
    }
}