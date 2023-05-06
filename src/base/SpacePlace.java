package base;

/**
 * @author Kevan Buckley, maintained by __student
 * @version 2.0, 2014
 */ // there are 10 methods inside this class

public class SpacePlace { // this class is a simple Java class that represent a location in space
/*
 * It has 4 instance variables: xOrg, yOrg, theta, and phi. 
 * The xOrg and yOrg instance variables are both int type and are used to store the x and y coordinate of the location, respectively.
 * The theta and phi instance variables are both double type and represent the spherical polar coordinates of the location.
 * */
	private int xOrg;
	private int yOrg;
	private double theta;
	private double phi;

	public SpacePlace() {
		xOrg = 0;
		yOrg = 0;
	}
/*
 * The class provides two constructors, a default constructor that sets both xOrg and yOrg to 0, 
 * and another constructor that takes theta and phi as arguments and sets their values accordingly.
 * */
	public SpacePlace(double theta, double phi) {
		super();
		this.theta = theta;
		this.phi = phi;
	}

	public int getxOrg() {
		return xOrg;
	}

	public void setxOrg(int xOrg) {
		this.xOrg = xOrg;
	}

	public int getyOrg() {
		return yOrg;
	}

	public void setyOrg(int yOrg) {
		this.yOrg = yOrg;
	}

	public double getTheta() {
		return theta;
	}

	public void setTheta(double theta) {
		this.theta = theta;
	}

	public double getPhi() {
		return phi;
	}

	public void setPhi(double phi) {
		this.phi = phi;
	}

}