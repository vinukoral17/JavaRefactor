package portfoliotask4;

public class Reflection02 {
	public static void main(String[] args) {
		SimpleMultiply s = new SimpleMultiply();
	    s.multiplyFloatA();
	    // s.squareB(); // if you uncomment this you will get a compiler error
	    float a = s.a;
	    // int b = s.b; // if you uncomment this you will get a compiler error
	    System.out.println("s=" + s);
	  }

}
