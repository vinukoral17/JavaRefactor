package base;

import java.net.InetAddress;
 //there are 5 methods here
/*
 * Comment header: The comment header indicating the author and version of the code is outdated.*/
/**
 * @author Kevan Buckley, maintained by __student
 * @version 2.0, 2014
 */
/*
 * Method names: The names of the method such as "downloadWebVersion" and
 * and "awayWeGo" are not very descriptive and do not clearly convey their purpose*/

/*
 * Inefficient implementation: The code does not make use of the InetAddress instance
 * variable, which may indicate an inefficient implementation */

public class ConnectionGenius {

	InetAddress ipa;
	
	// this function provides functionality to connect to a web service
	public ConnectionGenius(InetAddress ipa) {
		this.ipa = ipa;
	}
	// the four methods
	public void fireUpGame() {
		//calls three other methods in order
		downloadWebVersion();
		connectToWebService();
		awayWeGo();
	}
	/*
	 * Hard-coded strings: The messages outputted to the console are hard-coded string and may not be suitable for 
	 * Localisation or internationalisation*/
	public void downloadWebVersion() {
		//outputs messages to the console indicating that a special web
		// version is being downloaded and to wait for a moment
		System.out.println("Getting specialised web version.");
		System.out.println("Wait a couple of moments");
	}
	
	//outputs a message to the console indicating that the system is connecting to 
	// the web service
	public void connectToWebService() {
		System.out.println("Connecting");
	}
	// outputs a message to the console indicating that the system is ready to play
	public void awayWeGo() {
		System.out.println("Ready to play");
	}

}
