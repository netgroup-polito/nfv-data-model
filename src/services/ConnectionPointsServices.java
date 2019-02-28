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

    public ConnectionPoints getConnectionPoints(String nsdID) {
        return nsDB.getConnectionPoints(nsdID);
    }

    public ConnectionPoints addConnectionPoints(String nsdID, ConnectionPoints cp) {
        return nsDB.addConnectionPoints(nsdID, cp);
    }

    public ConnectionPoints deletevConnectionPoints(String nsdID) {
        return nsDB.deletevConnectionPoints(nsdID);
    }

    public ConnectionPoint getConnectionPoint(String nsdID, String cpID) {
        return nsDB.getConnectionPoint(nsdID, cpID);
    }

    public ConnectionPoint addConnectionPoint(String nsdID, ConnectionPoint cp) {
        return nsDB.addConnectionPoint(nsdID, cp);
    }

    public ConnectionPoint deleteConnectionPoint(String nsdID, ConnectionPoint cp) {
        return nsDB.deleteConnectionPoint(nsdID, cp);
    }
}
