package it.polito.dp2.rest.nfv.services;

import it.polito.dp2.rest.nfv.db.NsDB;
import it.polito.dp2.rest.nfv.jaxb.ConnectionPoint;
import it.polito.dp2.rest.nfv.jaxb.ConnectionPoints;
import it.polito.dp2.rest.nfv.jaxb.ConnectionPoint;
import it.polito.dp2.rest.nfv.jaxb.ConnectionPoints;

import javax.ws.rs.ApplicationPath;

public class ConnectionPointsServices {
    private NsDB nsDB = NsDB.getNsdDB();

    /**
     * Get all the Connection Points defined for the NSD
     * @param nsdID
     * @return The Connection Points or null if there are not Connection Points defined
     */
    public ConnectionPoints getConnectionPoints(String nsdID) {
        return nsDB.getConnectionPoints(nsdID);
    }

    /**
     * Add Connection Points
     * @param nsdID
     * @param cp
     * @return The added Connection Points or null if the operation doesn't succeed
     */
    public ConnectionPoints addConnectionPoints(String nsdID, ConnectionPoints cp) {
        return nsDB.addConnectionPoints(nsdID, cp);
    }

    /**
     * Delete Connection Points
     * @param nsdID
     * @return The void Connection Points
     */
    public ConnectionPoints deleteConnectionPoints(String nsdID) {
        return nsDB.deleteConnectionPoints(nsdID);
    }

    /**
     * Get a ConnectionPoint info inside the Connection Points
     * @param nsdID
     * @param cpID
     * @return The ConnectionPoint or null if there is not that ConnectionPoint inside
     */
    public ConnectionPoint getConnectionPoint(String nsdID, String cpID) {
        return nsDB.getConnectionPoint(nsdID, cpID);
    }

    /**
     * Add a ConnectionPoint inside the Connection Points
     * @param nsdID
     * @param cp
     * @return The added ConnectionPoint or null if the operation doesn't succeed
     */
    public ConnectionPoint addConnectionPoint(String nsdID, ConnectionPoint cp) {
        return nsDB.addConnectionPoint(nsdID, cp);
    }
    /**
     * Delete a ConnectionPoint
     * @param nsdID
     * @param cpID
     * @return ConnectionPoint removed or null if ConnectionPoint is not present
     */
    public ConnectionPoint deleteConnectionPoint(String nsdID, String cpID) {
        return nsDB.deleteConnectionPoint(nsdID, cpID);
    }
}
