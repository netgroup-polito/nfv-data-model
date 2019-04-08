package it.polito.dp2.rest.nfv.services;

import javax.ws.rs.core.Application;
import java.math.BigInteger;
import java.net.URI;

import it.polito.dp2.rest.nfv.db.PniDB;
import it.polito.dp2.rest.nfv.jaxb.PNI;
import it.polito.dp2.rest.nfv.jaxb.Host;
import it.polito.dp2.rest.nfv.jaxb.Hosts;
import it.polito.dp2.rest.nfv.jaxb.Connection;
import it.polito.dp2.rest.nfv.jaxb.Connections;

public class PniServices extends Application {
    private PniDB pniDB = PniDB.getPniDB();
    private Pagination pg = new Pagination();
    private int PAGE_SIZE = pg.getPageSize();

    /**
     * Get all the hosts and connection inside the NFV.PNI
     * @param baseURI: base URI for that resources
     * @param page: the requested page
     * @return A list of hosts and connections or the void ones if there are not hosts/connections inside
     */
    public PNI getPNI(String baseURI, int page){
        PNI pni = pniDB.getPNI();
        PNI pagePNI = new PNI();
        URI next = null;
        String path = "pni";
        int pageNum = 0, totPage = 0;

        pagePNI.setHosts(new Hosts());
        pagePNI.setConnections(new Connections());

        // Set actual page number and total number of pages
        pageNum = pg.getPage(page);

        int sizeHost = pni.getHosts().getHost().size();
        int sizeConn = pni.getConnections().getConnection().size();
        totPage = pg.getTotPage(sizeHost+sizeConn);

        // Pagination
        for(int i=(pageNum-1)*PAGE_SIZE, j=0; i<sizeHost+sizeConn && j<PAGE_SIZE; i++){
            if(i < sizeHost){
                pagePNI.getHosts().getHost().add(pni.getHosts().getHost().get(i));
                j++;
            }
            else{
                pagePNI.getConnections().getConnection().add(pni.getConnections().getConnection().get(i-sizeHost));
                j++;
            }
        }

        pagePNI.setTotalPages(BigInteger.valueOf(totPage));
        pagePNI.setPage(BigInteger.valueOf(pageNum));

        next = pg.getNextPage(baseURI, path, pageNum, totPage);
        pagePNI.setNext(next.toString());

        return pagePNI;
    }

    /**
     * Add a PNI
     * @param pni: the PNI to be added
     * @return The added PNI or null if the operation doesn't succeed
     */
    public PNI addPNI(PNI pni){
        return pniDB.addPNI(pni);
    }

    /**
     * Delete PNI
     * @return The void PNI
     */
    public PNI deletePNI(){
        return pniDB.deletePNI();
    }

    /**
     * Get all the hosts inside the NFV.PNI
     * @param baseURI: the base URI for that resources
     * @param page: the requested page
     * @return A list of hosts or the void one if there are not hosts inside
     */
    public Hosts getHosts(String baseURI, int page){
        Hosts hosts = pniDB.getHosts();
        Hosts pageHosts = new Hosts();
        URI next = null;
        String path = "pni/hosts";
        int pageNum = 0, totPage = 0;

        // Set actual page number and total number of pages
        pageNum = pg.getPage(page);

        int sizeHosts = hosts.getHost().size();
        totPage = pg.getTotPage(sizeHosts);

        // Pagination
        for(int i=(pageNum-1)*PAGE_SIZE, j=0; i<sizeHosts && j<PAGE_SIZE; i++){
            pageHosts.getHost().add(hosts.getHost().get(i));
            j++;
        }

        pageHosts.setTotalPages(BigInteger.valueOf(totPage));
        pageHosts.setPage(BigInteger.valueOf(pageNum));

        next = pg.getNextPage(baseURI, path, pageNum, totPage);
        pageHosts.setNext(next.toString());

        return pageHosts;
    }
    
    /**
     * Add list of Host
     * @param hosts: Hosts to be added
     * @return The added Hosts or null if the operation doesn't succeed
     */
    public Hosts addHosts(Hosts hosts){
    	return pniDB.addHosts(hosts);
    }

    /**
     * Get a host's info inside the NFV.PNI
     * @param hostID: the ID of the Host
     * @return Asked hHst or null if the host doesn't exist
     */
    public Host getHostInfo(String hostID){
        return pniDB.getHostInfo(hostID);
    }

    /**
     * Add a new host inside the NFV.PNI
     * @param host: the Host to be added
     * @return The added Host or null if the operation doesn't succeed
     */
    public Host addHost(Host host){
        return pniDB.addHost(host);
    }

    /**
     * Delete a host from the NFV.PNI
     * @param hostID: the id of the Host
     * @return Host removed or null if the host is not present
     */
    public Host deleteHost(String hostID){
        return pniDB.deleteHost(hostID);
    }

    /**
     * Modify a host from the NFV.PNI
     * @param host: the host to be modified
     * @return Host modified or null if the host is not present
     */
    public Host modifyHost(Host host){
        return pniDB.modifyHost(host);
    }

    /**
     * Get all the connections inside the NFV.PNI
     * @param baseURI: base URI for that resources
     * @param page: the requested page
     * @return A list of connections or the void one if there are not connections inside
     */
    public Connections getConnections(String baseURI, int page){
        Connections conn = pniDB.getConnections();
        Connections pageConn = new Connections();
        URI next = null;
        String path = "pni/connections";
        int pageNum = 0, totPage = 0;

        // Set actual page number and total number of pages
        pageNum = pg.getPage(page);

        int sizeConn = conn.getConnection().size();
        totPage = pg.getTotPage(sizeConn);

        // Pagination
        for(int i=(pageNum-1)*PAGE_SIZE, j=0; i<sizeConn && j<PAGE_SIZE; i++){
            pageConn.getConnection().add(conn.getConnection().get(i));
            j++;
        }

        pageConn.setTotalPages(BigInteger.valueOf(totPage));
        pageConn.setPage(BigInteger.valueOf(pageNum));

        next = pg.getNextPage(baseURI, path, pageNum, totPage);
        pageConn.setNext(next.toString());

        return pageConn;
    }
    
    /**
     * Add list of Connection
     * @param connections: Connections to be added
     * @return The added connections or null if the operation doesn't succeed
     */
    public Connections addConnections(Connections connections){
    	return pniDB.addConnections(connections);
    }

    /**
     * Get a connection's info inside the NFV.PNI
     * @param connectionSrc: the Source of that Connection
     * @param connectionDst: the Dest for that Connection
     * @return Asked Connection or null if the Connection doesn't exist
     */
    public Connection getConnectionInfo(String connectionSrc, String connectionDst){
        return pniDB.getConnectionInfo(connectionSrc, connectionDst);
    }

    /**
     * Add a new connection inside the NFV.PNI
     * @param connection: the Connection to be added
     * @return The added connection or null if the connection doesn't succeed
     */
    public Connection addConnection(Connection connection){
        return pniDB.addConnection(connection);
    }

    /**
     * Delete a connection from the NFV.PNI
     * @param connectionSrc: the Source for that Connection
     * @param connectionDst: the Dest for that Connection
     * @return Connection removed or null if the Connection is not present
     */
    public Connection deleteConnection(String connectionSrc, String connectionDst) {
        return pniDB.deleteConnection(connectionSrc, connectionDst);
    }

    /**
     * Modify a Connection from the NFV.PNI
     * @param connection: Connection to be modified
     * @return Connection modified or null if the Connection is not present
     */
    public Connection modifyConnection(Connection connection) {
        return pniDB.modifyConnection(connection);
    }

}