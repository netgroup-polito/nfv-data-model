package services;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import db.PniDB;
import jaxb.*;

//Defines the base URI for all resource URIs.
@ApplicationPath("/")
//The java class declares root resource and provider classes
public class PniServices extends Application {
    private PniDB pniDB = PniDB.getPniDB();

    /**
     * Get all the hosts inside the NFV.PNI
     * @return A list of hosts or null if there are not hosts inside
     */
    public PNI getPNI(){
        return pniDB.getPNI();
    }

    /**
     * Add a new host inside the NFV.PNI
     * @param pni
     * @return The added pni or null if the operation doesn't succeed
     */
    public PNI addPNI(PNI pni){
        return pniDB.addPNI(pni);
    }

    /**
     * Get all the hosts inside the NFV.PNI
     * @return A list of hosts or null if there are not hosts inside
     */
    public Hosts getHosts(){
        return pniDB.getHosts();
    }

    /**
     * Get a host's info inside the NFV.PNI
     * @param hostID
     * @return Asked host or null if the host doesn't exist
     */
    public Host getHostInfo(String hostID){
        return pniDB.getHostInfo(hostID);
    }

    /**
     * Add a new host inside the NFV.PNI
     * @param host
     * @return The added host or null if the operation doesn't succeed
     */
    public Host addHost(Host host){
        return pniDB.addHost(host);
    }

    /**
     * Delete a host from the NFV.PNI
     * @param hostID
     * @return Host removed or null if the host is not present
     */
    public Host deleteHost(String hostID){
        return pniDB.deleteHost(hostID);
    }

    /**
     * Modify a host from the NFV.PNI
     * @param host
     * @return Host modified or null if the host is not present
     */
    public Host modifyHost(Host host){
        return pniDB.modifyHost(host);
    }

    /**
     * Get all the connections inside the NFV.PNI
     * @return A list of connections or null if there are not connections inside
     */
    public Connections getConnections(){
        return pniDB.getConnections();
    }

    /**
     * Get a connection's info inside the NFV.PNI
     * @param connectionSrc
     * @param connectionDst
     * @return Asked connection or null if the connection doesn't exist
     */
    public Connection getConnectionInfo(String connectionSrc, String connectionDst){
        return pniDB.getConnectionInfo(connectionSrc, connectionDst);
    }

    /**
     * Add a new connection inside the NFV.PNI
     * @param connection
     * @return The added connection or null if the connection doesn't succeed
     */
    public Connection addConnection(Connection connection){
        return pniDB.addConnection(connection);
    }

    /**
     * Delete a connection from the NFV.PNI
     * @param connectionSrc
     * @param connectionDst
     * @return connection removed or null if the connection is not present
     */
    public Connection deleteConnection(String connectionSrc, String connectionDst) {
        return pniDB.deleteConnection(connectionSrc, connectionDst);
    }

    /**
     * Modify a connection from the NFV.PNI
     * @param connection
     * @return connection modified or null if the connection is not present
     */
    public Connection modifyConnection(Connection connection) {
        return pniDB.modifyConnection(connection);
    }

}