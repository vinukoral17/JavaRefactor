package base;

/*###### - UNUSED IMPORTS 
 * The java.awt.* import is not being used and should be removed.
 * 
 * */
import java.awt.*;

import javax.swing.*;

/**
 * @author Kevan Buckley, maintained by __student
 * @version 2.0, 2014
 */ // there are 10 methods inside this class

public class PictureFrame {
	public int[] reroll = null;
	public Main master = null;
	public Game grd = null;
	/*
	 * ##### - LONG METHOD The DominoPanel class has multiple long methods that
	 * perform multiple task and are difficult to understand
	 */
	public class DominoPanel extends JPanel {
		private static final long serialVersionUID = 4190229282411119364L;

		private void drawGrid(Graphics g) {
		    for (int row = 0; row < 7; row++) {
		        for (int col = 0; col < 8; col++) {
		            drawDigit(g, row, col);
		        }
		    }
		}

		private void drawDigit(Graphics g, int row, int col) {
		    int x = 30 + col * 20;
		    int y = 30 + row * 20;
		    int diameter = 20;
		    int n = master.grid[row][col];
		    drawDigitGivenCentre(g, x, y, diameter, n);
		}

		
		public void drawHeadings(Graphics g) {
		    RowOfDigits row1 = new RowOfDigits(10, 30, 7, 20, 1);
		    RowOfDigits row2 = new RowOfDigits(30, 10, 8, 20, 1);

		    drawRowOfDigits(g, row1);
		    drawRowOfDigits(g, row2);
		}

		private void drawRowOfDigits(Graphics g, RowOfDigits row) {
		    for (int i = 0; i < row.getCount(); i++) {
		        fillDigitGivenCentre(g, row.getNum1() + i * row.getSpacing(), 
		            row.getNum2() + i * row.getSpacing(), 20, row.getInitialDigit() + i);
		    }
		}

		public void drawDomino(Graphics g, Domino d, DominoValues dv) {
			if (d.placed) {
				int y = Math.min(d.ly, d.hy);
				int x = Math.min(d.lx, d.hx);
				int w = Math.abs(d.lx - d.hx) + 1;
				int h = Math.abs(d.ly - d.hy) + 1;
				g.setColor(Color.WHITE);
				g.fillRect(20 + x * 20, 20 + y * 20, w * 20, h * 20);
				g.setColor(Color.RED);
				g.drawRect(20 + x * 20, 20 + y * 20, w * 20, h * 20);
				drawDigitGivenCentre(g, 30 + d.hx * 20, 30 + d.hy * 20, 20, dv.high, Color.BLUE);
				drawDigitGivenCentre(g, 30 + d.lx * 20, 30 + d.ly * 20, 20, dv.low, Color.BLUE);
			}
		}

		void drawDigitGivenCentre(Graphics g, int x, int y, int diameter, int n) {
			int radius = diameter / 2;
			
			FontMetrics fm = g.getFontMetrics();
			String txt = Integer.toString(n);
			g.drawString(txt, x - fm.stringWidth(txt) / 2, y + fm.getMaxAscent() / 2);
		}

		void drawDigitGivenCentre(Graphics g, int x, int y, int diameter, int n, Color c) { // Long parameter list
			int radius = diameter / 2;
			g.setColor(c);
			FontMetrics fm = g.getFontMetrics();
			String txt = Integer.toString(n);
			g.drawString(txt, x - fm.stringWidth(txt) / 2, y + fm.getMaxAscent() / 2);
		}

		void fillDigitGivenCentre(Graphics g, int x, int y, int diameter, int n) {
			int radius = diameter / 2;
			g.setColor(Color.GREEN);
			g.fillOval(x - radius, y - radius, diameter, diameter);
			g.setColor(Color.BLACK);
			g.drawOval(x - radius, y - radius, diameter, diameter);
			FontMetrics fm = g.getFontMetrics();
			String txt = Integer.toString(n);
			g.drawString(txt, x - fm.stringWidth(txt) / 2, y + fm.getMaxAscent() / 2);
		}

		protected void paintComponent(Graphics g) {
			g.setColor(Color.YELLOW);
			g.fillRect(0, 0, getWidth(), getHeight());

			Location l = new Location(1, 2);

			if (master.mode == 1) {
				l.drawGridLines(g);
				drawHeadings(g);
				drawGrid(g);
				grd.drawGuesses(g);
			}
			if (master.mode == 0) {
				l.drawGridLines(g);
				drawHeadings(g);
				drawGrid(g);
				grd.drawDominoes(g);
			}
		}

		public Dimension getPreferredSize() {
			return new Dimension(202, 182);
		}
	}

	public DominoPanel dp;

	public void PictureFrame(Main main) {
		master = main;
		if (dp == null) {
			JFrame f = new JFrame("Abominodo");
			dp = new DominoPanel();
			f.setContentPane(dp);
			f.pack();
			f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			f.setVisible(true);
		}
	}

	public void reset() {

	}

}