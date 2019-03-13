package it.polito.dp2.rest.nfv.db;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import it.polito.dp2.rest.nfv.jaxb.Connection;
import it.polito.dp2.rest.nfv.jaxb.Connections;
import it.polito.dp2.rest.nfv.jaxb.Host;
import it.polito.dp2.rest.nfv.jaxb.Hosts;
import it.polito.dp2.rest.nfv.jaxb.PNI;

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

        pni.setHosts(getHosts());
        pni.setConnections(getConnections());

        return pni;
    }

    public PNI addPNI(PNI pni){
        deletePNI();

        for(Host h : pni.getHosts().getHost())
            hostMap.put(h.getId(), h);

        for(Connection c : pni.getConnections().getConnection())
            connectionMap.put(c.getSourceHost().concat(c.getDestHost()), c);

        return pni;
    }

    public PNI deletePNI(){
        hostMap.clear();
        connectionMap.clear();

        return getPNI();
    }

    public Hosts getHosts(){
        Hosts hosts = new Hosts();

        for(Host h : hostMap.values())
            hosts.getHost().add(h);

        return hosts;
    }
    
    public synchronized Hosts addHosts(Hosts hosts){
    	for(Host h : hosts.getHost()){
    		if(getHostInfo(h.getId()) == null){
    			hostMap.put(h.getId(), h);
    		}
    	}
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
    
    public synchronized Connections addConnections(Connections connections){
    	for(Connection c : connections.getConnection()){
    		addConnection(c);
    	}
    	return connections;
    }
    

    public Connection getConnectionInfo(String connectionSrc, String connectionDst){
        return connectionMap.get(connectionSrc.concat(connectionDst));
    }

    public synchronized Connection addConnection(Connection connection){
        if(hostMap.containsKey(connection.getSourceHost()) && hostMap.containsKey(connection.getDestHost())){
            if(!connectionMap.containsKey(connection.getSourceHost().concat(connection.getDestHost()))){
                connectionMap.put(connection.getSourceHost().concat(connection.getDestHost()), connection);
                return connection;
            }else{
                return null;
            }
        }
        return null;
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
