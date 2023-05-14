package base;

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
		    drawDigitGivenCentre(g, new Measurement(x, y, diameter), n);
		}

		
		public void drawHeadings(Graphics g) {
		    RowOfDigits row1 = new RowOfDigits(10, 30, 7, 20, 1);
		    RowOfDigits row2 = new RowOfDigits(30, 10, 8, 20, 1);

		    drawRowOfDigits(g, row1);
		    drawRowOfDigits(g, row2);
		}

		private void drawRowOfDigits(Graphics g, RowOfDigits row) {
		    for (int i = 0; i < row.getCount(); i++) {
		        fillDigitGivenCentre(g, new Measurement(row.getNum1() + i * row.getSpacing(),
		            row.getNum2() + i * row.getSpacing(), 20), row.getInitialDigit() + i);
		    }
		}

		public void drawDomino(Graphics g, Domino d, DominoValues dv) {
		    if (d.placed) {
		        int y = Math.min(d.ly, d.hy);
		        int x = Math.min(d.lx, d.hx);
		        int w = Math.abs(d.lx - d.hx) + 1;
		        Measurement dominoCircle = new Measurement(20 + x * 20, 20 + y * 20, w * 20);
		        g.setColor(Color.WHITE);
		        g.fillRect(dominoCircle.getX(), dominoCircle.getY(), dominoCircle.getDiameter(), dominoCircle.getDiameter());
		        g.setColor(Color.RED);
		        g.drawRect(dominoCircle.getX(), dominoCircle.getY(), dominoCircle.getDiameter(), dominoCircle.getDiameter());
		        drawDigitGivenCentre(g, new Measurement(30 + d.hx * 20, 30 + d.hy * 20, 20), dv.high, Color.BLUE);
		        drawDigitGivenCentre(g, new Measurement(30 + d.lx * 20, 30 + d.ly * 20, 20), dv.low, Color.BLUE);
		    }
		}

		void drawDigitGivenCentre(Graphics g, Measurement size, int n) {
		    FontMetrics fm = g.getFontMetrics();
		    String txt = Integer.toString(n);
		    g.drawString(txt, size.getCenterX() - fm.stringWidth(txt) / 2,
		        size.getCenterY() + fm.getMaxAscent() / 2);
		}

		void drawDigitGivenCentre(Graphics g, Measurement size, int n, Color c) {
		    g.setColor(c);
		    drawDigitGivenCentre(g, size, n);
		}

		void fillDigitGivenCentre(Graphics g, Measurement size, int n) {
		    g.setColor(Color.GREEN);
		    g.fillOval(size.getX(), size.getY(), size.getDiameter(), size.getDiameter());
		    g.setColor(Color.BLACK);
		    g.drawOval(size.getX(), size.getY(), size.getDiameter(), size.getDiameter());
		    drawDigitGivenCentre(g, size, n);
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