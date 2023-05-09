package reflection;

public class SimpleMultiply {

	  public float a = 3.5f;
	  private float b = 2.5f;

	  public SimpleMultiply() {
	  }

	  public SimpleMultiply(float a, float b) {
	    this.a = a;
	    this.b = b;
	  }

	  public void multiplyFloatA(float factor) {
	    this.a *= factor;
	  }

	  private void multiplyFloatB(float factor) {
	    this.b *= factor;
	  }

	  public float getFloatA() {
	    return a;
	  }

	  private void setFloatA(float a) {
	    this.a = a;
	  }

	  public float getFloatB() {
	    return b;
	  }

	  public void setFloatB(float b) {
	    this.b = b;
	  }

	  public String toString() {
	    return String.format("(a:%.2f, b:%.2f)", a, b);
	  }
	}