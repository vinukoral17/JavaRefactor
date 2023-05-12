package base;

import java.util.List;

public class DominoHelper {

    private List<Domino> dominoList;
    private List<Domino> guessList;

    public DominoHelper(List<Domino> dominoList, List<Domino> guessList) {
        this.dominoList = dominoList;
        this.guessList = guessList;
    }

    public Domino findDominoAt(int x, int y) {
        for (Domino d : dominoList) {
            int dominoLowX = d.lx;
            int dominoLowY = d.ly;
            int dominoHighX = d.hx;
            int dominoHighY = d.hy;
            if ((dominoLowX == x && dominoLowY == y) || (dominoHighX == x && dominoHighY == y)) {
                return d;
            }
        }
        return null;
    }

    public Domino findGuessAt(int x, int y) {
        for (Domino d : guessList) {
            int guessLowX = d.lx;
            int guessLowY = d.ly;
            int guessHighX = d.hx;
            int guessHighY = d.hy;
            if ((guessLowX == x && guessLowY == y) || (guessHighX == x && guessHighY == y)) {
                return d;
            }
        }
        return null;
    }

    public Domino findGuessByLH(int x, int y) {
        for (Domino d : guessList) {
            int guessLow = d.values.low;
            int guessHigh = d.values.high;
            if ((guessLow == x && guessHigh == y) || (guessHigh == x && guessLow == y)) {
                return d;
            }
        }
        return null;
    }

    public Domino findDominoByLH(int x, int y) {
        for (Domino d : dominoList) {
            int dominoLow = d.values.low;
            int dominoHigh = d.values.high;
            if ((dominoLow == x && dominoHigh == y) || (dominoHigh == x && dominoLow == y)) {
                return d;
            }
        }
        return null;
    }

    public void printDominoList(List<Domino> dominoList) {
        for (Domino d : dominoList) {
            System.out.println(d);
        }
    }

    public void printDominoes() {
        printDominoList(dominoList);
    }

    public void printGuesses() {
        printDominoList(guessList);
    }
}
