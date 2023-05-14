package portfoliotask4;

import java.lang.reflect.*;

public class Reflection08 {
  public static void main(String[] args) throws Exception {
    SimpleMultiply s = new SimpleMultiply();
    Field[] fields = s.getClass().getDeclaredFields();
    System.out.printf(" There are %f fields \n", fields.length);
    for (Field f : fields) {
      f.setAccessible(true);
      Float x = f.getFloat(s);
      x++;
      f.setFloat(s, x);
      System.out.printf( "field name= %s  type= %s  value= %f \n", f.getName(),
          f.getType(), f.getFloat(s));
    }
  }
}