package base;

public class Measurement {

    private final int x;
    private final int y;
    private final int diameter;

    public Measurement(int x, int y, int diameter) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDiameter() {
        return diameter;
    }

    public int getRadius() {
        return diameter / 2;
    }
}