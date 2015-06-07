package methode2;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImageJPanel extends JPanel {
	
	private DecolorizerModules dm;
	
	public ImageJPanel(BufferedImage bi) {
		super(new BorderLayout());		
		
		this.dm = new DecolorizerModules();
		
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(bi));
		this.add(label, BorderLayout.NORTH);
		
		BufferedImage bi2 = dm.grayImage(bi);
		
		JLabel label2 = new JLabel();
		label2.setIcon(new ImageIcon(bi2));
		this.add(label2, BorderLayout.SOUTH);
	}

}
