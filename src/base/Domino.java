/*##### - NO PROPER INDENTATION
 * The code is not indented properly, needs to be indented
 * */
//there are 6 methods inside this methods
package base;

/**
 * @author Kevan Buckley, maintained by __student
 * @version 2.0, 2014
 */
public class Domino implements Comparable<Domino> {
    public final DominoValues values;
    public int hx;
    public int hy;
    public int lx;
    public int ly;
    public boolean placed = false;

    public Domino(int high, int low) {
        this.values = new DominoValues(high, low);
    }

    public void place(int highX, int highY, int lowX, int lowY) {
        this.hx = highX;
        this.hy = highY;
        this.lx = lowX;
        this.ly = lowY;
        placed = true;
    }

    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append("[");
        result.append(Integer.toString(values.getHigh()));
        result.append(Integer.toString(values.getLow()));
        result.append("]");
        if (!placed) {
            result.append("unplaced");
        } else {
            result.append("(");
            result.append(Integer.toString(hx + 1));
            result.append(",");
            result.append(Integer.toString(hy + 1));
            result.append(")");
            result.append("(");
            result.append(Integer.toString(lx + 1));
            result.append(",");
            result.append(Integer.toString(ly + 1));
            result.append(")");
        }
        return result.toString();
    }

    public void invert() {
        int temp = hx;
        hx = lx;
        lx = temp;

        temp = hy;
        hy = ly;
        ly = temp;
    }

    public boolean isDominoHorizontal() {
        return hy == ly;
    }

    public int compareTo(Domino arg0) {
        if (values.getHigh() < arg0.values.getHigh()) {
            return 1;
        }
        return values.getLow() - arg0.values.getLow();
    }
}


class DominoValues {
    public final int high;
    public final int low;

    public DominoValues(int high, int low) {
        this.high = high;
        this.low = low;
    }

    public int getHigh() {
        return high;
    }

    public int getLow() {
        return low;
    }
}