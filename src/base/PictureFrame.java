package base;

import javax.swing.*;
import java.awt.*;

public class PictureFrame {
    public int[] reroll;
    public Main master;

    private static final Color WHITE = Color.WHITE;
    private static final Color RED = Color.RED;
    private static final Color BLUE = Color.BLUE;
    private static final Color BLACK = Color.BLACK;
    private static final Color GREEN = Color.GREEN;
    private static final Color YELLOW = Color.YELLOW;

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
                g.setColor(WHITE);
                g.fillRect(20 + x * 20, 20 + y * 20, w * 20, h * 20);
                g.setColor(RED);
                g.drawRect(20 + x * 20, 20 + y * 20, w * 20, h * 20);
                drawDigitGivenCentre(g, 30 + d.hx * 20, 30 + d.hy * 20, 20, dv.high, BLUE);
                drawDigitGivenCentre(g, 30 + d.lx * 20, 30 + d.ly * 20, 20, dv.low, BLUE);
            }
        }

        private void drawDigitGivenCentre(Graphics g, int x, int y, int diameter, int n) {
            int radius = diameter / 2;
            g.setColor(BLACK);
            FontMetrics fm = g.getFontMetrics();
            String txt = Integer.toString(n);
            g.drawString(txt, x - fm.stringWidth(txt) / 2, y + fm.getMaxAscent() / 2);
        }

        private void drawDigitGivenCentre(Graphics g, int x, int y, int diameter, int n, Color c) {
            int radius = diameter / 2;
            g.setColor(c);
            g.fillOval(x - radius, y - radius, diameter, diameter);
            drawDigitGivenCentre(g, x, y, diameter, n);
            }
        private void fillDigitGivenCentre(Graphics g, int x, int y, int diameter, int n) {
            int radius = diameter / 2;
            g.setColor(YELLOW);
            g.fillOval(x - radius, y - radius, diameter, diameter);
            drawDigitGivenCentre(g, x, y, diameter, n);
        }

        private void fillDigitGivenCentre(Graphics g, int x, int y, int diameter, int n, Color c) {
            int radius = diameter / 2;
            g.setColor(c);
            g.fillOval(x - radius, y - radius, diameter, diameter);
            drawDigitGivenCentre(g, x, y, diameter, n);
        }
    }
}
