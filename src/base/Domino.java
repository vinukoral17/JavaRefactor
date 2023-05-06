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
public int high;
public int low;
public int hx;
public int hy;
public int lx;
public int ly;
public boolean placed = false;

public Domino(int high, int low) {
super();
this.high = high;
this.low = low;
}

public void place(int hx, int hy, int lx, int ly) {
this.hx = hx;
this.hy = hy;
this.lx = lx;
this.ly = ly;
placed = true;
}



public String toString() {
StringBuffer result = new StringBuffer();
result.append("[");
result.append(Integer.toString(high));
result.append(Integer.toString(low));
result.append("]");
if(!placed){
result.append("unplaced");
} else {
result.append("(");
result.append(Integer.toString(hx+1));
result.append(",");
result.append(Integer.toString(hy+1));
result.append(")");
result.append("(");
result.append(Integer.toString(lx+1));
result.append(",");
result.append(Integer.toString(ly+1));
result.append(")");
}
return result.toString();
}

/** turn the domino around 180 degrees clockwise*/

/*##### - UNUSED VARIABLES
 * The tx and ty variables in the invert() 
 * method are used only for temporary storage and can be eliminated.*/

public void invert() {
int tx = hx;
hx = lx;
lx = tx;

int ty = hy;
hy = ly;
ly = ty;
}

/*
 * Method to check if the domino is horizontal
 * @return boolean value indicating if the domino piece is placed horizontally
 * */
public boolean ishl() {
return hy==ly;
}

/*The method CompareTo() compared 'Domino' objects based on thier high and low values
 * @param arg() the Domino object to tbe compared with this object.
 * @return an integer value indicating the order of the two objects
 * If 'this.higher' is less than `arg0.high` it returns `1`
 * Otherwise, it return `this.low - arg0.low`.
 * */

public int compareTo(Domino arg0) {
if(this.high < arg0.high){
return 1;
}
return this.low - arg0.low;
}



}