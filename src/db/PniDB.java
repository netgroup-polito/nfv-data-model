package db;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import jaxb.Connection;
import jaxb.Connections;
import jaxb.Host;
import jaxb.Hosts;
import jaxb.PNI;

public class PniDB {
    private static PniDB pniDB = new PniDB();

    public static PniDB getPniDB(){
        return pniDB;
    }

    private ConcurrentMap<String, Host> hostMap;
    private ConcurrentMap<String, Connection> connectionMap; //String ID: sourcedestination

    private PniDB(){
        hostMap = new ConcurrentHashMap<String, Host>();
        connectionMap = new ConcurrentHashMap<String, Connection>();
    }

    public PNI getPNI(){
        PNI pni = new PNI();

        for(Host h : hostMap.values())
            pni.getHosts().getHost().add(h);

        for(Connection c : connectionMap.values())
            pni.getConnections().getConnection().add(c);

        return pni;
    }

    public Hosts getHosts(){
        Hosts hosts = new Hosts();

        for(Host h : hostMap.values())
            hosts.getHost().add(h);

        return hosts;
    }

    public Host getHostInfo(String hostID){
        return hostMap.get(hostID);
    }

    public synchronized Host addHost(Host host){
        if(!hostMap.containsKey(host.getId())){
            hostMap.put(host.getId(), host);
            return host;
        }else{
            return null;
        }
    }

    public Host deleteHost(String hostID){
        return hostMap.remove(hostID);
    }

    public Host modifyHost(Host host){
        return hostMap.replace(host.getId(), host);
    }

    public Connections getConnections(){
        Connections connections = new Connections();

        for(Connection c : connectionMap.values())
            connections.getConnection().add(c);

        return connections;
    }

    public Connection getConnectionInfo(String connectionSrc, String connectionDst){
        return connectionMap.get(connectionSrc.concat(connectionDst));
    }

    public synchronized Connection addConnection(Connection connection){
        if(!connectionMap.containsKey(connection.getSourceHost().concat(connection.getDestHost()))){
            connectionMap.put(connection.getSourceHost().concat(connection.getDestHost()), connection);
            return connection;
        }else{
            return null;
        }
    }

    public Connection deleteConnection(String connectionSrc, String connectionDst){
        return connectionMap.remove(connectionSrc.concat(connectionDst));
    }

    public Connection modifyConnection(Connection connection){
        if(pniDB.getConnections().getConnection().contains(connection))
            return connectionMap.replace(connection.getSourceHost().concat(connection.getDestHost()), connection);
        else
            return null;
    }
}
