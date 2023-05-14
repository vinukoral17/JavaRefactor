package portfoliotask4;
import java.lang.reflect.Field;

public class Reflection01 {
	public static void main(String[] args) throws Exception {
	    SimpleMultiply s = new SimpleMultiply();
	    System.out.println("s=" + s);

	    // Using reflection to access fields
	    Field fieldA = s.getClass().getField("a");
	    System.out.println("a=" + fieldA.get(s));

	    Field fieldB = s.getClass().getDeclaredField("b");
	    fieldB.setAccessible(true);
	    System.out.println("b=" + fieldB.get(s));
	  }
	
}