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

public abstract class ConnectionGenius {
	//abstract methods
    public abstract void downloadWebVersion();
    public abstract void connectToWebService();
    public abstract void awayWeGo();

    // Define a static factory method named createConnectionGenius that returns an instance
    public static ConnectionGenius createConnectionGenius(InetAddress ipa) {
        return new FixConnectionGenius(ipa);
    }
    private static class FixConnectionGenius extends ConnectionGenius {
    	//Declare a private instance variable named ipa
        private InetAddress ipa;
        
        // Define a constructor that takes an instance of InetAddress as input parameter and assign it to the ipa variable
        public FixConnectionGenius(InetAddress ipa) {
            this.ipa = ipa;
        }
        // Implement the abstract method downloadWebVersion() and output messages to the console
        @Override
        public void downloadWebVersion() {
            System.out.println("Getting specialised web version.");
            System.out.println("Wait a couple of moments");
        }
        @Override
        public void connectToWebService() {
            System.out.println("Connecting");
        }

        @Override
        public void awayWeGo() {
            System.out.println("Ready to play");
        }

    }
}

