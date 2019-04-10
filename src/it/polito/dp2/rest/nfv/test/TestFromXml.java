package it.polito.dp2.rest.nfv.test;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import it.polito.dp2.rest.nfv.jaxb.NS;
import it.polito.dp2.rest.nfv.jaxb.PNI;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXB;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestFromXml {

    private String baseUrl = "http://localhost:8080/Rest-nfv";
    private static final Logger LOGGER = Logger.getLogger(TestFromXml.class.getName());

    @Test
    public void post_wrong_pni() {
        String path = "/nfv/pni";

        PNI pni = new PNI();
        
        LOGGER.log(Level.FINEST, "[!] NFV{post}(wrong_pni) test [:Started]");

        WebResource resource  = Client.create().resource(baseUrl.concat(path));
        
        FileInputStream xmlStream = null;
		try {
			xmlStream = new FileInputStream( "./testfile/wrong_pni.xml");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        pni = JAXB.unmarshal(xmlStream, PNI.class);

        ClientResponse clientResponse = resource.accept(MediaType.APPLICATION_XML_TYPE).
        		entity(pni).post(ClientResponse.class);
        int statusCode = clientResponse.getStatus();
        // Assert that correct status code is returned.
        Assert.assertEquals(400, statusCode);

        LOGGER.log(Level.FINEST, "[!] NFV{post}(wrong_pni) test [:Ended]");

    }

    @Test
    public void post_fine_pni() {
    	String path = "/nfv/pni";
    	
    	PNI pni = new PNI();
        
        LOGGER.log(Level.FINEST, "[!] NFV{post}(pni) test [:Started]");

        WebResource resource  = Client.create().resource(baseUrl.concat(path));
        
        FileInputStream xmlStream = null;
		try {
			xmlStream = new FileInputStream( "./testfile/ok_pni.xml");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        pni = JAXB.unmarshal(xmlStream, PNI.class);

        ClientResponse clientResponse = resource.accept(MediaType.APPLICATION_XML_TYPE).
        		entity(pni).post(ClientResponse.class);
        int statusCode = clientResponse.getStatus();
        // Assert that correct status code is returned.
        Assert.assertEquals(201, statusCode);

        LOGGER.log(Level.FINEST, "[!] NFV{post}(pni) test [:Ended]");
    }
    
    @Test
    public void z_delete_pni() {
        String path = "/nfv/pni";

        LOGGER.log(Level.FINEST, "[!] NFV{delete}(pni) test [:Started]");

        WebResource resource  = Client.create().resource(baseUrl.concat(path));

        ClientResponse clientResponse = resource.accept(MediaType.APPLICATION_XML_TYPE).
        		delete(ClientResponse.class);
        int statusCode = clientResponse.getStatus();
        // Assert that correct status code is returned.
        Assert.assertEquals(204, statusCode);

        LOGGER.log(Level.FINEST, "[!] NFV{delete}(pni) test [:Ended]");
    }

    @Test
    public void post_wrong_ns() {
        String path = "/nfv/ns";

		NS ns = new NS();
        
        LOGGER.log(Level.FINEST, "[!] NFV{post}(wrong_ns) test [:Started]");

        WebResource resource  = Client.create().resource(baseUrl.concat(path));

        FileInputStream xmlStream = null;
		try {
			xmlStream = new FileInputStream( "./testfile/wrong_ns.xml");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		ns = JAXB.unmarshal(xmlStream, NS.class);

        ClientResponse clientResponse = resource.accept(MediaType.APPLICATION_XML_TYPE).
        		entity(ns).post(ClientResponse.class);
        int statusCode = clientResponse.getStatus();
        // Assert that correct status code is returned.
        Assert.assertEquals(400, statusCode);

        LOGGER.log(Level.FINEST, "[!] NFV{post}(wrong_ns) test [:Ended]");
    }

    @Test
    public void post_fine_ns() {
    	String path = "/nfv/ns";
        
    	NS ns = new NS();
    	
        LOGGER.log(Level.FINEST, "[!] NFV{post}(ns) test [:Started]");

        WebResource resource  = Client.create().resource(baseUrl.concat(path));
        
        FileInputStream xmlStream = null;
		try {
			xmlStream = new FileInputStream( "./testfile/ok_ns.xml");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        ns = JAXB.unmarshal(xmlStream, NS.class);

        ClientResponse clientResponse = resource.accept(MediaType.APPLICATION_XML_TYPE).
        		entity(ns).post(ClientResponse.class);

        int statusCode = clientResponse.getStatus();
        // Assert that correct status code is returned.
        Assert.assertEquals(201, statusCode);

        LOGGER.log(Level.FINEST, "[!] NFV{post}(ns) test [:Ended]");
    }
    
    @Test
    public void z_delete_ns() {
        String path = "/nfv/ns";

        LOGGER.log(Level.FINEST, "[!] NFV{delete}(ns) test [:Started]");

        WebResource resource  = Client.create().resource(baseUrl.concat(path));

        ClientResponse clientResponse = resource.accept(MediaType.APPLICATION_XML_TYPE).
        		delete(ClientResponse.class);
        int statusCode = clientResponse.getStatus();
        // Assert that correct status code is returned.
        Assert.assertEquals(204, statusCode);

        LOGGER.log(Level.FINEST, "[!] NFV{delete}(ns) test [:Ended]");
    }    

}