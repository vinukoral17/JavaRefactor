package base;

import java.util.List;

public class RotateDominos {
	private DominoHelper helper;
	
    public RotateDominos(List<Domino> dominoes) {
        this.helper = new DominoHelper(dominoes, dominoes);
    }
    
    public void rotateDominoes() {
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 6; y++) {
                tryToRotateDominoAt(x, y);
            }
        }
    }
    
    private void tryToRotateDominoAt(int x, int y) {
        Domino d = helper.findDominoAt(x, y);
        boolean weFancyARotation = Math.random() < 0.5;
        if (thisIsTopLeftOfDomino(x, y, d)) {
            if (d.isDominoHorizontal() && weFancyARotation && theCellBelowIsTopLeftOfHorizontalDomino(x, y)) {
                Domino e = helper.findDominoAt(x, y + 1);
                e.hx = x;
                e.lx = x;
                d.hx = x + 1;
                d.lx = x + 1;
                e.ly = y + 1;
                e.hy = y;
                d.ly = y + 1;
                d.hy = y;
            } else if (!d.isDominoHorizontal() && weFancyARotation && theCellToTheRightIsTopLeftOfVerticalDomino(x, y)) {
                Domino e = helper.findDominoAt(x + 1, y);
                e.hx = x;
                e.lx = x + 1;
                d.hx = x;
                d.lx = x + 1;
                e.ly = y + 1;
                e.hy = y + 1;
                d.ly = y;
                d.hy = y;
            }
        }
    }
    
    private boolean theCellToTheRightIsTopLeftOfVerticalDomino(int x, int y) {
        Domino e = helper.findDominoAt(x + 1, y);
        return thisIsTopLeftOfDomino(x + 1, y, e) && !e.isDominoHorizontal();
    }

    private boolean theCellBelowIsTopLeftOfHorizontalDomino(int x, int y) {
        Domino e = helper.findDominoAt(x, y + 1);
        return thisIsTopLeftOfDomino(x, y + 1, e) && e.isDominoHorizontal();
    }

    private boolean thisIsTopLeftOfDomino(int x, int y, Domino d) {
        return (x == Math.min(d.lx, d.hx)) && (y == Math.min(d.ly, d.hy));
    }
    
}
