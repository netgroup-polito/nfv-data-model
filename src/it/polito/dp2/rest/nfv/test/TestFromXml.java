package it.polito.dp2.rest.nfv.test;

import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestFromXml {

    private String baseUrl = "http://localhost:8080/Rest-nfv";
    private static final Logger LOGGER = Logger.getLogger(TestFromXml.class.getName());

    @Test
    public void post_wrong_pni() {
        String path = "/nfv/pni";

        LOGGER.log(Level.FINEST, "[!] NFV{post}(wrong_pni) test [:Started]");

        javax.ws.rs.client.Client client = ClientBuilder.newClient();

        InputStream xmlStream = TestFromXml.class
                .getResourceAsStream("/testfile/wrong_pni.xml");

        WebTarget enterNS = client.target(baseUrl).path(path);
        LOGGER.log(Level.FINEST,"url:"+enterNS.toString());

        Response response = enterNS.request(MediaType.APPLICATION_XML)
                .post(Entity.xml(xmlStream));

        int statusCode = response.getStatus();
        // Assert that correct status code is returned.
        Assert.assertEquals(400, statusCode);

        LOGGER.log(Level.FINEST, "[!] NFV{post}(wrong_pni) test [:Ended]");

        client.close();
    }

    @Test
    public void post_fine_pni() {
        String path = "/nfv/pni";

        LOGGER.log(Level.FINEST, "[!] NFV{post}(pni) test [:Started]");

        javax.ws.rs.client.Client client = ClientBuilder.newClient();
        
        InputStream xmlStream = TestFromXml.class
                .getResourceAsStream("/testfile/ok_pni.xml");

        WebTarget enterNS = client.target(baseUrl).path(path);
        LOGGER.log(Level.FINEST,"url:"+enterNS.toString());

        Response response = enterNS.request(MediaType.APPLICATION_XML)
                .post(Entity.xml(xmlStream));

        int statusCode = response.getStatus();
        // Assert that correct status code is returned.
        Assert.assertEquals(201, statusCode);

        LOGGER.log(Level.FINEST, "[!] NFV{post}(pni) test [:Ended]");

        client.close();
    }
    
    @Test
    public void delete_pni() {
        String path = "/nfv/pni";

        LOGGER.log(Level.FINEST, "[!] NFV{delete}(pni) test [:Started]");

        javax.ws.rs.client.Client client = ClientBuilder.newClient();

        WebTarget enterNS = client.target(baseUrl).path(path);
        LOGGER.log(Level.FINEST,"url:"+enterNS.toString());

        Response response = enterNS.request(MediaType.APPLICATION_XML)
                .delete();

        int statusCode = response.getStatus();
        // Assert that correct status code is returned.
        Assert.assertEquals(204, statusCode);

        LOGGER.log(Level.FINEST, "[!] NFV{delete}(pni) test [:Ended]");

        client.close();
    }

    @Test
    public void post_wrong_ns() {
        String path = "/nfv/ns";

        LOGGER.log(Level.FINEST, "[!] NFV{post}(wrong_ns) test [:Started]");

        javax.ws.rs.client.Client client = ClientBuilder.newClient();

        InputStream xmlStream = TestFromXml.class
                .getResourceAsStream("/testfile/wrong_ns.xml");

        WebTarget enterNS = client.target(baseUrl).path(path);
        LOGGER.log(Level.FINEST,"url:"+enterNS.toString());

        Response response = enterNS.request(MediaType.APPLICATION_XML)
                .post(Entity.xml(xmlStream));

        int statusCode = response.getStatus();
        // Assert that correct status code is returned.
        Assert.assertEquals(400, statusCode);

        LOGGER.log(Level.FINEST, "[!] NFV{post}(wrong_ns) test [:Ended]");

        client.close();
    }

    @Test
    public void post_fine_ns() {
        String path = "/nfv/ns";

        LOGGER.log(Level.FINEST, "[!] NFV{post}(ns) test [:Started]");

        javax.ws.rs.client.Client client = ClientBuilder.newClient();        
        
        InputStream xmlStream = TestFromXml.class
                .getResourceAsStream("/testfile/ok_ns.xml");

        WebTarget enterNS = client.target(baseUrl).path(path);
        LOGGER.log(Level.FINEST,"url:"+enterNS.toString());

        Response response = enterNS.request(MediaType.APPLICATION_XML)
                .post(Entity.xml(xmlStream));

        int statusCode = response.getStatus();
        // Assert that correct status code is returned.
        Assert.assertEquals(201, statusCode);

        LOGGER.log(Level.FINEST, "[!] NFV{post}(ns) test [:Ended]");

        client.close();
    }
    
    @Test
    public void delete_ns() {
        String path = "/nfv/ns";

        LOGGER.log(Level.FINEST, "[!] NFV{delete}(ns) test [:Started]");

        javax.ws.rs.client.Client client = ClientBuilder.newClient();

        WebTarget enterNS = client.target(baseUrl).path(path);
        LOGGER.log(Level.FINEST,"url:"+enterNS.toString());

        Response response = enterNS.request(MediaType.APPLICATION_XML)
                .delete();

        int statusCode = response.getStatus();
        // Assert that correct status code is returned.
        Assert.assertEquals(204, statusCode);

        LOGGER.log(Level.FINEST, "[!] NFV{delete}(ns) test [:Ended]");

        client.close();
    }    
   

}