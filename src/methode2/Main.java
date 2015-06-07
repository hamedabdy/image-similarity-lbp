package methode2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {


	public static void main(String[] args) {
//		File image = new File("images/f01.jpg");
//		File image2 = new File("images/f07.jpg");
//		BufferedImage bi = null;
//		BufferedImage bi2 = null;
//		Tools tools = new Tools();
//
//		try {
//			bi = ImageIO.read(image);
//			bi2 = ImageIO.read(image2);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		// rescaleing bi2 to bi
//		bi2 = tools.rescaleImage(bi2, bi);
//
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		int screenWidth = (int)screenSize.getWidth();
//		int screenHeight = (int)screenSize.getHeight();
//		
//		
//		JFrame frame = new JFrame("Image: " + image.getName());
//		LbpJPanel panelLbp1 = new LbpJPanel(bi);
//		JPanel panelLbp2 = new LbpJPanel2(bi);
//
//		frame.getContentPane().add(panelLbp1, BorderLayout.CENTER);
//		frame.getContentPane().add(panelLbp2, BorderLayout.EAST);
//		frame.pack();
//		frame.setVisible(true);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//		
//		JFrame frame2 = new JFrame("Image: " + image2.getName());
//		LbpJPanel panelLbp11 = new LbpJPanel(bi2);
//		JPanel panelLbp22 = new LbpJPanel2(bi2);
//
//		frame2.getContentPane().add(panelLbp11, BorderLayout.CENTER);
//		frame2.getContentPane().add(panelLbp22, BorderLayout.EAST);
//		frame2.pack();		
//		int frameWidth = frame.getWidth();
//		frame2.setLocation(frameWidth+5, 0);
//		frame2.setVisible(true);
//		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//		
//		JFrame frame4 = new JFrame("Sample LBP Hist of One Region");
//		JPanel panelPartialLbpHist = new PartialLbpHistJPanel(panelLbp1.getPartialLbpHist());
//
//		frame4.getContentPane().add(panelPartialLbpHist, BorderLayout.CENTER);
//		frame4.pack();
//		frame4.setLocation(screenWidth, screenHeight);
//		frame4.setSize(350, 550);
//		frame4.setVisible(true);
//		frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		
//		
//		JFrame frame3 = new JFrame("Images: " + image.getName() + ", " +image2.getName());
//		JPanel panelImage = new ImageJPanel(bi);
//		JPanel panelImage2 = new ImageJPanel(bi2);
//
//		frame3.getContentPane().add(panelImage, BorderLayout.WEST);
//		frame3.getContentPane().add(panelImage2, BorderLayout.EAST);
//		frame3.pack();
//		frame3.setLocation(screenWidth, 0);
//		frame3.setVisible(true);
//		frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
///*		System.out.println("Difference between "+ image.getName() + " and " + image2.getName());
//		tools.difference(panelLbp1.getLbpResSum(), panelLbp11.getLbpResSum());
//*/		
//		System.out.println("Euclidean distance between "+ image.getName() + " and " + image2.getName());
//		tools.euclideanDistance(panelLbp1.getLbpResSum(), panelLbp11.getLbpResSum());
		
		JFrame framex = new MyJFrame();
		framex.pack();
		framex.setVisible(true);
		
	}

}
