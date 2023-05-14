package portfoliotask4;

public class SimpleMultiply {

	public float a = 3;
	private float b = 2.5f;

	public SimpleMultiply() {
	}

	public SimpleMultiply(float a, float b) {
		this.a = a;
		this.b = b;
	}

	public void multiplyFloatA() {
		this.a *= this.a;
	}

	public void multiplyFloatB() {
		this.b *= this.b;
	}

	private void setA(float a) {
		this.a = a;
	}

	public float getFloatA() {
		return a;
	}

	private void setFloatA(float a) {
		this.a = a;
	}

	private float getFloatB() {
		return b;
	}

	private void setFloatB(float b) {
		this.b = b;
	}

	public String toString() {
		return String.format("(a:%.2f, b:%.2f)", a, b);
	}
}
