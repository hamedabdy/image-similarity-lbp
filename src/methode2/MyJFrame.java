package methode2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MyJFrame extends JFrame {

	private File[] selectedFiles;
	private BufferedImage bi, bi2;
	private Tools tools = new Tools();
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int screenWidth = (int)screenSize.getWidth();
	private int screenHeight = (int)screenSize.getHeight();
	private double[] lbpResSum = {0,0};

	public MyJFrame() {
		JPanel panel = new JPanel();
		JButton fileChooserBtn = new JButton("Choose images...");
		makeFileChooserButton(fileChooserBtn);

		JButton showLbp1Btn = new JButton("Show LBP 1");
		JButton showLbpB2tn = new JButton("Show LBP 2");
		JButton showPartialHistBtn = new JButton("Show partial histogram");
		JButton showImagesBtn = new JButton("Show images");

		setLbp1BtnActionListener(showLbp1Btn);
		setLbp2BtnActionListener(showLbpB2tn);
		setImagesBtnActionListener(showImagesBtn);
		setPartialHistBtnActionListener(showPartialHistBtn);

		panel.setLayout(new GridLayout(3, 3));
		panel.add(fileChooserBtn);
		panel.add(showLbp1Btn);
		panel.add(showLbpB2tn);
		panel.add(showImagesBtn);
		panel.add(showPartialHistBtn);

		JButton showLbpEuclidean = new JButton("Show LBP Euclidean Distance");
		showEuclideanActionListener(showLbpEuclidean);
		panel.add(showLbpEuclidean);

		this.add(panel, BorderLayout.CENTER);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void makeFileChooserButton(JButton button) {

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir")+"/images/");
				fileChooser.setMultiSelectionEnabled(true);
				int res = fileChooser.showOpenDialog(null);
				fileChooser.setVisible(true);
				selectedFiles = new File[fileChooser.getSelectedFiles().length];
				selectedFiles = fileChooser.getSelectedFiles();
				if(res == JFileChooser.APPROVE_OPTION) {
					try {
						bi = ImageIO.read(selectedFiles[0]);
						bi2 = ImageIO.read(selectedFiles[1]);
					} catch (IOException ex) {
						ex.printStackTrace();
					}
					// rescaleing bi2 to bi
					Dimension biD = new Dimension();
					Dimension bi2D = new Dimension();
					biD.setSize(bi.getWidth(), bi.getHeight());
					bi2D.setSize(bi2.getWidth(), bi2.getHeight());
					if(!biD.equals(bi2D)) bi2 = tools.rescaleImage(bi2, bi);
				}
			}
		});

	}

	private void setLbp1BtnActionListener(JButton button) {
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				if(selectedFiles != null) {
					System.out.println(bi.getHeight());
					frame = new JFrame("Image: " + selectedFiles[0].getName());
					LbpJPanel panelLbp1 = new LbpJPanel(bi);
					JPanel panelLbp2 = new LbpJPanel2(bi);
					lbpResSum[0] = panelLbp1.getLbpResSum();
					frame.getContentPane().add(panelLbp1, BorderLayout.NORTH);
					frame.getContentPane().add(panelLbp2, BorderLayout.SOUTH);
					frame.pack();		
					int frameWidth = frame.getWidth();
					frame.setLocation(frameWidth+5, 0);
					frame.setVisible(true);
				} else JOptionPane.showMessageDialog(frame
						, "Please select images first using file chooser!"
						, "WARNING!", JOptionPane.WARNING_MESSAGE);
			}
		});
	}

	private void setLbp2BtnActionListener(JButton button) {
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				if(selectedFiles != null) {
					System.out.println(bi.getHeight());
					frame = new JFrame("Image: " + selectedFiles[1].getName());
					LbpJPanel panelLbp11 = new LbpJPanel(bi2);
					JPanel panelLbp22 = new LbpJPanel2(bi2);
					lbpResSum[1] = panelLbp11.getLbpResSum();
					frame.getContentPane().add(panelLbp11, BorderLayout.NORTH);
					frame.getContentPane().add(panelLbp22, BorderLayout.SOUTH);
					frame.pack();		
					int frameWidth = frame.getWidth();
					frame.setLocation(frameWidth+5, 0);
					frame.setVisible(true);
				} else JOptionPane.showMessageDialog(frame
						, "Please select images first using file chooser!"
						, "WARNING!", JOptionPane.WARNING_MESSAGE);
			}
		});
	}

	private void setPartialHistBtnActionListener(JButton button) {
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				if(selectedFiles != null) { 
					frame = new JFrame("Sample LBP Hist of One Region");
					LbpJPanel panelLbp1 = new LbpJPanel(bi);
					JPanel panelPartialLbpHist = new PartialLbpHistJPanel(panelLbp1.getPartialLbpHist());
					frame.getContentPane().add(panelPartialLbpHist, BorderLayout.CENTER);
					frame.pack();
					frame.setLocation(screenWidth, screenHeight);
					frame.setSize(350, 550);
					frame.setVisible(true);
				} else JOptionPane.showMessageDialog(frame
						, "Please select images first using file chooser!"
						, "WARNING!", JOptionPane.WARNING_MESSAGE);
			}
		});
	}

	private void setImagesBtnActionListener(JButton button) {
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				if(selectedFiles != null) { 
					frame = new JFrame("Images: " + selectedFiles[0].getName() + ", " + selectedFiles[1].getName());
					JPanel panelImage = new ImageJPanel(bi);
					JPanel panelImage2 = new ImageJPanel(bi2);
					frame.getContentPane().add(panelImage, BorderLayout.WEST);
					frame.getContentPane().add(panelImage2, BorderLayout.EAST);
					frame.pack();
					frame.setLocation(screenWidth, 0);
					frame.setVisible(true);
				} else JOptionPane.showMessageDialog(frame
						, "Please select images first using file chooser!"
						, "WARNING!", JOptionPane.WARNING_MESSAGE);
			}
		});
	}

	private void showEuclideanActionListener(JButton button) {
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				if(selectedFiles != null) {
					frame = new JFrame("Results for:" + selectedFiles[0].getName() + ", " + selectedFiles[1].getName());
					JPanel panel = new JPanel();
					JTextArea textArea = new JTextArea("", 5, 20);
					textArea.setEditable(false);
					double res = tools.euclideanDistance(lbpResSum[0], lbpResSum[1]);
					textArea.setText(Double.toString(res));
					panel.add(textArea);
					frame.getContentPane().add(panel);
					frame.pack();
					frame.setLocation(screenWidth/2, 0);
					frame.setVisible(true);
				} else JOptionPane.showMessageDialog(frame
						, "Please select images first using file chooser!"
						, "WARNING!", JOptionPane.WARNING_MESSAGE);
			}
		});
	}
}
