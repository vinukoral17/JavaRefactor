package base;

import java.util.LinkedList;
import java.util.List;

public class DominoGrid {

    private List<Domino> _d;
    private List<Domino> _g;
    private int[][] grid;
    private int[][] gg;

    public DominoGrid() {
        _d = new LinkedList<Domino>();
        _g = new LinkedList<Domino>();
        grid = new int[7][8];
        gg = new int[7][8];
        generateDominoes();
        shuffleDominoesOrder();
        invertSomeDominoes();
        placeDominoes();
        generateGuesses();
    }

    private void generateDominoList(List<Domino> list) {
        int count = 0;
        int x = 0;
        int y = 0;
        for (int l = 0; l <= 6; l++) {
            for (int h = l; h <= 6; h++) {
                Domino d = new Domino(h, l);
                list.add(d);
                if (list == _d) {
                    d.place(x, y, x + 1, y);
                    x += 2;
                    if (x > 6) {
                        x = 0;
                        y++;
                    }
                }
                count++;
            }
        }
        if (count != 28) {
            System.out.println("something went wrong generating dominoes");
            System.exit(0);
        }
    }

    public void generateDominoes() {
        generateDominoList(_d);
    }

    public void generateGuesses() {
        generateDominoList(_g);
    }

    public void collateGrid() {
        for (Domino d : _d) {
            if (!d.placed) {
                grid[d.hy][d.hx] = 9;
                grid[d.ly][d.lx] = 9;
            } else {
                grid[d.hy][d.hx] = d.values.high;
                grid[d.ly][d.lx] = d.values.low;
            }
        }
    }

    public void collateGuessGrid() {
        for (int r = 0; r < 7; r++) {
            for (int c = 0; c < 8; c++) {
                gg[r][c] = 9;
            }
        }
        for (Domino d : _g) {
            if (d.placed) {
                gg[d.hy][d.hx] = d.values.high;
                gg[d.ly][d.lx] = d.values.low;
            }
        }
    }

    public void printGrid() {
        for (int are = 0; are < 7; are++) {
            for (int see = 0; see < 8; see++) {
                if (grid[are][see] != 9) {
                    System.out.printf("%d", grid[are][see]);
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }

    public void printGuessGrid() {
        for (int are = 0; are < 7; are++) {
            for (int see = 0; see < 8; see++) {
                if (gg[are][see] != 9) {
                    System.out.printf("%d", gg[are][see]);
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }

    public void shuffleDominoesOrder() {
        List<Domino> shuffled = new LinkedList<Domino>();

        while (_d.size() > 0) {
			int n = (int) (Math.random() * _d.size());
			shuffled.add(_d.get(n));
			_d.remove(n);
		}

		_d = shuffled;
	}

	public void invertSomeDominoes() {
		for (Domino d : _d) {
			if (Math.random() > 0.5) {
				d.invert();
			}
		}
	}

	public void placeDominoes() {
		int x = 0;
		int y = 0;
		int count = 0;
		for (Domino d : _d) {
			count++;
			d.place(x, y, x + 1, y);
			x += 2;
			if (x > 6) {
				x = 0;
				y++;
			}
		}
		if (count != 28) {
			System.out.println("something went wrong generating dominoes");
			System.exit(0);
		}
	}
	
}