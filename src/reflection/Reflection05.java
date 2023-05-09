package reflection;
import java.lang.reflect.Field;

public class Reflection05 {
	  public static void main(String[] args) throws Exception {
	    SimpleMultiply s = new SimpleMultiply();
	    Field[] fields = s.getClass().getDeclaredFields();
	    System.out.printf("There are %d fields\n", fields.length);

	    for (Field f : fields) {
	      System.out.printf("field name=%s type=%s value=%d\n", f.getName(),
	          f.getType(), f.getInt(s));
	    }
	  }
	}
