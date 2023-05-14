package portfoliotask4;

import java.lang.reflect.Field;

//Accessing Private Members

public class Reflection02 {
	public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
		SimpleMultiply s = new SimpleMultiply();
	    s.multiplyFloatA();
	    
	    // Accessing private member b using reflection
	    Field bField = SimpleMultiply.class.getDeclaredField("b");
	    bField.setAccessible(true);
	    float b = bField.getFloat(s);
	    
	    System.out.println("s=" + s);
	    System.out.println("b=" + b);
	  }

}

/*
 * I first obtain a reference to the private field "b" using the "getDeclaredField" method of the "Class" class, 
 * passing in the name of the field as a string. 
 * I then call the "setAccessible" method of the "Field" class, passing in "true" to allow me to access the private field. 
 * Finally, I call the "getFloat" method of the "Field" class, passing in the object "s" to get the value of the private field "b". */
