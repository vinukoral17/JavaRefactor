package baseTesting;

import org.junit.jupiter.api.Test;

import base.IOLibrary;

import org.junit.jupiter.api.Assertions;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.InetAddress;

public class IOLibraryTest {

    @Test
    public void testGetString() {
        String input = "Hello, world!";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        String output = IOLibrary.getString();

        Assertions.assertEquals(input, output);
    }

    @Test
    public void testGetIPAddress() {
        String input = "127.0.0.1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        InetAddress output = IOLibrary.getIPAddress();

        Assertions.assertNotNull(output);
        Assertions.assertEquals(input, output.getHostAddress());
    }
}