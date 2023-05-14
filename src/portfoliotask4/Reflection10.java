package portfoliotask4;

import java.lang.reflect.Method;

public class Reflection10 {
	  public static void main(String[] args) throws Exception {
		SimpleMultiply s = new SimpleMultiply();
	    Method m = s.getClass().getDeclaredMethod("setA", float.class);
	    m.setAccessible(true);
	    m.invoke(s, 76);
	    System.out.println(s);
	  }
	}
