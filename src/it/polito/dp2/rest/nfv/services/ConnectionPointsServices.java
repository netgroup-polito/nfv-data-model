package it.polito.dp2.rest.nfv.services;

import it.polito.dp2.rest.nfv.db.NsDB;
import it.polito.dp2.rest.nfv.jaxb.ConnectionPoint;
import it.polito.dp2.rest.nfv.jaxb.ConnectionPoints;

import java.math.BigInteger;
import java.net.URI;

public class ConnectionPointsServices {
    private NsDB nsDB = NsDB.getNsdDB();
    private Pagination pg = new Pagination();
    private int PAGE_SIZE = pg.getPageSize();

    /**
     * Get all the Connection Points defined for the NSD
     * @param nsdID: the ID of the considered NSD
     * @param baseURI: base URI for that resources
     * @param page: the requested page
     * @return The ConnectionPoints or null if the NSD does not exist or there are not ConnectionPoints defined in that NSD
     */
    public ConnectionPoints getConnectionPoints(String nsdID, String baseURI, int page) {
        ConnectionPoints cps = nsDB.getConnectionPoints(nsdID);
        ConnectionPoints pageCPS = new ConnectionPoints();
        URI next = null;
        String path = "ns/nsd/" + nsdID + "/cps";
        int pageNum = 0, totPage = 0;

        //do pagination if Flavours exists in that NSD
        if(cps == null){
            return null;
        }

        // Set actual page number and total number of pages
        pageNum = pg.getPage(page);

        int size = cps.getConnectionPoint().size();
        totPage = pg.getTotPage(size);

        // Pagination
        for(int i=(pageNum-1)*PAGE_SIZE, j=0; i<size && j<PAGE_SIZE; i++){
            pageCPS.getConnectionPoint().add(cps.getConnectionPoint().get(i));
            j++;
        }

        pageCPS.setTotalPages(BigInteger.valueOf(totPage));
        pageCPS.setPage(BigInteger.valueOf(pageNum));

        next = pg.getNextPage(baseURI, path, pageNum, totPage);
        pageCPS.setNext(next.toString());

        return pageCPS;
    }

    /**
     * Add Connection Points
     * @param nsdID: the ID of the considered NSD
     * @param cp: the ConnectionPoints to be added
     * @return The added ConnectionPoints or null if the operation doesn't succeed
     */
    public ConnectionPoints addConnectionPoints(String nsdID, ConnectionPoints cp) {
        return nsDB.addConnectionPoints(nsdID, cp);
    }

    /**
     * Delete Connection Points
     * @param nsdID: the ID of the considered NSD
     * @return The void ConnectionPoints
     */
    public ConnectionPoints deleteConnectionPoints(String nsdID) {
        return nsDB.deleteConnectionPoints(nsdID);
    }

    /**
     * Get a ConnectionPoint info inside the Connection Points
     * @param nsdID: the ID of the considered NSD
     * @param cpID: the ID of the ConnectionPoint
     * @return The ConnectionPoint or null if the NSD does not exist or there is not ConnectionPoint inside
     */
    public ConnectionPoint getConnectionPoint(String nsdID, String cpID) {
        return nsDB.getConnectionPoint(nsdID, cpID);
    }

    /**
     * Add a ConnectionPoint inside the Connection Points
     * @param nsdID: the ID of the considered NSD
     * @param cp: the ConnectionPoint to be added
     * @return The added ConnectionPoint or null if the operation doesn't succeed
     */
    public ConnectionPoint addConnectionPoint(String nsdID, ConnectionPoint cp) {
        return nsDB.addConnectionPoint(nsdID, cp);
    }

    /**
     * Delete a ConnectionPoint
     * @param nsdID: the ID of the considered NSD
     * @param cpID: the ID of the ConnectionPoint
     * @return ConnectionPoint removed or null if ConnectionPoint is not present
     */
    public ConnectionPoint deleteConnectionPoint(String nsdID, String cpID) {
        return nsDB.deleteConnectionPoint(nsdID, cpID);
    }
}
