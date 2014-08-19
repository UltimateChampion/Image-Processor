import java.awt.image.BufferedImage;

import java.io.*;

import javax.imageio.*;
import javax.swing.*;

public class IPPanel extends JPanel {

	private JLabel f;
	
	public IPPanel() {
		
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("pmario.jpg"));
		} catch (IOException e) {
			System.out.println("Failed to read image.");
		}
		
		
		for (int i = 0 ; i < 200; i ++) {
			for (int j = 0 ; j < 200; j ++) {
				img.setRGB(i, j, ~img.getRGB(i, j));
			}
		}
		
		
		f = new JLabel(new ImageIcon(img));
		
		JPanel jpl = new JPanel();
		jpl.add(f);
		add(jpl);	
	}
}
