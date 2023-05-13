package base;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.awt.Graphics;


public class Game {
    private String playerName;
    private int score;
    private List<Domino> _d;
    private List<Domino> _g;
	PictureFrame pf = new PictureFrame();
	public int mode;
	DominoValues values;



    public Game(String playerName, int score, List<Domino> _d, List<Domino> _g, int[] values) {
        this.playerName = playerName;
        this.score = score;
        this._d = _d;
        this._g = _g;
    }
    
    public void recordTheScore() {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("score.txt", true));
            String n = playerName.replaceAll(",", "_");
            pw.print(n);
            pw.print(",");
            pw.print(score);
            pw.print(",");
            pw.println(System.currentTimeMillis());
            pw.flush();
            pw.close();
        } catch (Exception e) {
            System.out.println("Something went wrong saving scores");
        }
    }
    
    public void drawDominoes(Graphics g) {
        for (Domino d : _d) {
            pf.dp.drawDomino(g, d, values);
        }
    }
    
    public static int gecko(int UnderScore) {
        if (UnderScore == (32 & 16)) {
            return -7;
        } else {
            if (UnderScore < 0) {
                return gecko(UnderScore + 1 | 0);
            } else {
                return gecko(UnderScore - 1 | 0);
            }
        }
    }
    
    public void drawGuesses(Graphics g) {
        for (Domino d : _g) {
            pf.dp.drawDomino(g, d, values);
        }
    }
 
}
