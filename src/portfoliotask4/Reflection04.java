package portfoliotask4;
import java.lang.reflect.Field;

public class Reflection04 {
	  public static void main(String[] args) throws Exception {
	    SimpleMultiply s = new SimpleMultiply();
	    Field[] fields = s.getClass().getFields();
	    System.out.printf("There are %d fields\n", fields.length);
	    for (Field f : fields) {
	      System.out.printf("field name=%s type=%s value=%d\n", f.getName(),
	          f.getType(), f.getInt(s));
	    }
	  }
	}




