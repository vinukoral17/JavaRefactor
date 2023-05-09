package baseTesting;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import java.awt.Graphics;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import base.Main;
import base.PictureFrame;

class PictureFrameTest {

	private PictureFrame pictureFrame;
	
	@Before
	public void setUp() {
		pictureFrame = new PictureFrame();
		pictureFrame.master = new Main();
		pictureFrame.master.grid = new int[7][8];
	}
	
	
	@Test
	public void testDrawHeadings() {
		// Create a JPanel to draw the headings on
		JPanel panel = new JPanel() {
			private static final long serialVersionUID = 1L;
			
			@Override
			public void paintComponent(Graphics g) {
				pictureFrame.new DominoPanel().drawHeadings(g);
			}
		};
		
		// Render the panel to an image and compare it to an expected image
		BufferedImage expectedImage = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
		panel.paint(expectedImage.getGraphics());
		BufferedImage actualImage = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
		actualImage.getGraphics().drawImage(expectedImage, 0, 0, null);
		assertArrayEquals(expectedImage.getRGB(0, 0, expectedImage.getWidth(), expectedImage.getHeight(), null, 0, expectedImage.getWidth()), actualImage.getRGB(0, 0, actualImage.getWidth(), actualImage.getHeight(), null, 0, actualImage.getWidth()));
	}
}


