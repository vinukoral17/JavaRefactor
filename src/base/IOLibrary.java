package base;

import java.io.*;
import java.net.*;

/**
 * @author Kevan Buckley, maintained by __student
 * @version 2.0, 2014
 */

public final class IOLibrary {
	
	/*
	 * 
	 * getString() is a method that reads a line from the standard input and returns it
	 * 
	 * @return the string read from the standard input
	 * */
	
	/*
	 * ##### - CODE DUPLICATION
	 * The code BufferedReader r = new BufferedReader(new InputStreamReader(System.in)); 
	 * is duplicated in both getString() and getIPAddress() methods. 
	 * 
	 * */
	
	/*
	 * ##### - ENDLESS LOOP 
	 * The methods getString() and getIPAddress() use an infinite loop with a try-catch block, 
	 * which is not a good approach to handle exceptions. 
	 * This will cause the method to run indefinitely and will be a performance issue.
	 * 
	 * */
	public static String getString() {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));//REPEAT
		do {
			try {
				return r.readLine();
			} catch (Exception e) {
			}
		} while (true);
	}
	/*
	 * getIPAddress() is a method that reads a line from the standard input and parses it as an IP
	 * address.
	 * 
	 * @return the IP address read from the standard input*/
	public static InetAddress getIPAddress() {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));//REPEAT
		do {
			try {
				String[] chunks = r.readLine().split("\\.");
				byte[] data = { Byte.parseByte(chunks[0]), Byte.parseByte(chunks[1]), Byte.parseByte(chunks[2]),
						Byte.parseByte(chunks[3]) };
				return Inet4Address.getByAddress(data);
			} catch (Exception e) {
			}
		} while (true);
	}

}