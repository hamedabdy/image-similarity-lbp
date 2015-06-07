package methode2;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PartialLbpHistJPanel extends JPanel {
	
	
	public PartialLbpHistJPanel(BufferedImage bi) {
		super(new BorderLayout());
		
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(bi));
		this.add(label, BorderLayout.NORTH);

	}

}
