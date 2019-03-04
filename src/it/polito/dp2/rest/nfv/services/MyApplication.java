package it.polito.dp2.rest.nfv.services;

import it.polito.dp2.rest.nfv.resources.*;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/nfv")
public class MyApplication extends Application{
	//The method returns a non-empty collection with classes, that must be included in the published JAX-RS application
	@Override
	public Set<Class<?>> getClasses() {
	HashSet<Class<?>> h = new HashSet<Class<?>>();
	
	h.add(NfvResources.class);
	h.add(ConnectionPointsResources.class);
	h.add(FlavoursResources.class);
	h.add(NsResources.class);
	h.add(PnfdResources.class);
	h.add(PniResources.class);
	h.add(PropertyDefinitionResources.class);
	h.add(VnfdependencyResources.class);
	h.add(VnfdResources.class);
	h.add(VnffgdResources.class);
	
	return h;
	}
}
