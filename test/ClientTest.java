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

public class ClientTest {

    private String baseUrl = "http://localhost:8080/RESTy_nfv_war_exploded";
    private static final Logger LOGGER = Logger.getLogger(ClientTest.class.getName());

    @Test
    public void test1() {
        String path = "/nfv";

        LOGGER.log(Level.FINEST, "[!] NFV{post}(wrong_nfv) test [:Started]");

        javax.ws.rs.client.Client client = ClientBuilder.newClient();

        InputStream xmlStream = ClientTest.class
                .getResourceAsStream("assertion/wrong_nfv.xml");

        WebTarget enterNS = client.target(baseUrl).path(path);
        LOGGER.log(Level.FINEST,"url:"+enterNS.toString());

        Response response = enterNS.request(MediaType.APPLICATION_XML)
                .post(Entity.xml(xmlStream));

        int statusCode = response.getStatus();
        // Assert that correct status code is returned.
        Assert.assertEquals(400, statusCode);

        LOGGER.log(Level.FINEST, "[!] NFV{post_bad}(wrong_nfv) test [:Ended]");

        client.close();
    }

    @Test
    public void test2() {
        String path = "/nfv";

        LOGGER.log(Level.FINEST, "[!] NFV{post}(ok_nfv) test [:Started]");

        javax.ws.rs.client.Client client = ClientBuilder.newClient();

        InputStream xmlStream = ClientTest.class
                .getResourceAsStream("assertion/ok_nfv.xml");

        WebTarget enterNS = client.target(baseUrl).path(path);
        LOGGER.log(Level.FINEST,"url:"+enterNS.toString());

        Response response = enterNS.request(MediaType.APPLICATION_XML)
                .post(Entity.xml(xmlStream));

        int statusCode = response.getStatus();
        // Assert that correct status code is returned.
        Assert.assertEquals(201, statusCode);

        LOGGER.log(Level.FINEST, "[!] NFV{post}(ok_nfv) test [:Ended]");

        client.close();
    }

    @Test
    public void test3() {
        String path = "/nfv";

        LOGGER.log(Level.FINEST, "[!] NFV{delete}(nfv) test [:Started]");

        javax.ws.rs.client.Client client = ClientBuilder.newClient();

        WebTarget enterNS = client.target(baseUrl).path(path);
        LOGGER.log(Level.FINEST,"url:"+enterNS.toString());

        Response response = enterNS.request(MediaType.APPLICATION_XML)
                .delete();

        int statusCode = response.getStatus();
        // Assert that correct status code is returned.
        //204 {No-Content} -> delete return void, so its ok
        Assert.assertEquals(204, statusCode);

        LOGGER.log(Level.FINEST, "[!] NFV{delete}(nfv) test [:Ended]");

        client.close();
    }

    @Test
    public void test4() {
        String path = "/nfv/pni";

        LOGGER.log(Level.FINEST, "[!] NFV{post}(wrong_pni) test [:Started]");

        javax.ws.rs.client.Client client = ClientBuilder.newClient();

        InputStream xmlStream = ClientTest.class
                .getResourceAsStream("assertion/wrong_pni.xml");

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
    public void test5() {
        String path = "/nfv/pni";

        LOGGER.log(Level.FINEST, "[!] NFV{post}(pni) test [:Started]");

        javax.ws.rs.client.Client client = ClientBuilder.newClient();

        InputStream xmlStream = ClientTest.class
                .getResourceAsStream("assertion/ok_pni.xml");

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
    public void test6() {
        String path = "/nfv/ns";

        LOGGER.log(Level.FINEST, "[!] NFV{post}(wrong_ns) test [:Started]");

        javax.ws.rs.client.Client client = ClientBuilder.newClient();

        InputStream xmlStream = ClientTest.class
                .getResourceAsStream("assertion/wrong_ns.xml");

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
    public void test7() {
        String path = "/nfv/ns";

        LOGGER.log(Level.FINEST, "[!] NFV{post}(ns) test [:Started]");

        javax.ws.rs.client.Client client = ClientBuilder.newClient();

        InputStream xmlStream = ClientTest.class
                .getResourceAsStream("assertion/ok_ns.xml");

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
    public void test8() {
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
    public void test9() {
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