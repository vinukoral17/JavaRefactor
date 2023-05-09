package baseTesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import base.ConnectionGenius;

import java.net.InetAddress;


public class ConnectionGeniusTest {

    @Test
    public void testCreateConnectionGenius() throws Exception {
        InetAddress ipa = InetAddress.getLocalHost();
        ConnectionGenius cg = ConnectionGenius.createConnectionGenius(ipa);
        assertNotNull(cg);
        assertEquals(ipa, ((ConnectionGenius.FixConnectionGenius)cg).ipa);
    }

    @Test
    public void testDownloadWebVersion() {
        ConnectionGenius cg = new ConnectionGenius.FixConnectionGenius(null);
        cg.downloadWebVersion();
        // check that the output was printed to the console
        // you may need to use a testing library to redirect System.out
    }

    @Test
    public void testConnectToWebService() {
        ConnectionGenius cg = new ConnectionGenius.FixConnectionGenius(null);
        cg.connectToWebService();
        // check that the output was printed to the console
        // you may need to use a testing library to redirect System.out
    }

    @Test
    public void testAwayWeGo() {
        ConnectionGenius cg = new ConnectionGenius.FixConnectionGenius(null);
        cg.awayWeGo();
        // check that the output was printed to the console
        // you may need to use a testing library to redirect System.out
    }

}