package methode2;

import ijGrower.LBP;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LbpJPanel2 extends JPanel {
	
	DecolorizerModules dm;
	LBP lbp;
	Tools tools;
	Histograms histograms;
	
	public LbpJPanel2(BufferedImage bi) {
		super(new BorderLayout());
		this.dm = new DecolorizerModules();
		this.tools = new Tools();
		this.histograms = new Histograms();
		
		bi = dm.grayImage(bi);
		double[][] data = tools.imageAsDoubleMatrix(bi);
		//dm.printMatrix(data);
		lbp = new LBP(16, 8);
		//double[][] n = lbp.getNeighbourhood();
		//dm.printMatrix(n);
		byte[][] lbpResults = lbp.getLBP(data);
		//dm.printMatrix(lbpResults);
		
		BufferedImage bi3 = histograms.plotHistogram(bi);
		
		Examples e = new Examples(lbp);
		e.example1(data, lbpResults, 90, 94);
		
		bi = tools.byteMatrixToImage(lbpResults, 100);
		
		BufferedImage bi2 = histograms.plotHistogram(bi);
		
		this.setLayout(new GridLayout(1, 3));
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(bi3));
		this.add(label);
		
		JLabel label2 = new JLabel();
		label2.setIcon(new ImageIcon(bi));
		this.add(label2);
		
		JLabel label3 = new JLabel();
		label3.setIcon(new ImageIcon(bi2));
		this.add(label3);
	}

}
