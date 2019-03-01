package services;

import db.NsDB;
import jaxb.ConnectionPoint;
import jaxb.ConnectionPoints;

import javax.ws.rs.ApplicationPath;

//Defines the base URI for all resource URIs.
@ApplicationPath("/")
//The java class declares root resource and provider classes
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
    public ConnectionPoints deletevConnectionPoints(String nsdID) {
        return nsDB.deletevConnectionPoints(nsdID);
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
     * @param cp
     * @return ConnectionPoint removed or null if ConnectionPoint is not present
     */
    public ConnectionPoint deleteConnectionPoint(String nsdID, ConnectionPoint cp) {
        return nsDB.deleteConnectionPoint(nsdID, cp);
    }
}
