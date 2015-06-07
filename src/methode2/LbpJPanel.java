package methode2;

import ijGrower.LBP;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LbpJPanel extends JPanel {
	
	private DecolorizerModules dm;
	private LBP lbp;
	private Tools tools;
	private Histograms histograms;
	private BufferedImage partialLbpHist;
	private double[] lbpResReshape;
	private double lbpResSum;
	
	public LbpJPanel(BufferedImage bi) {
		super(new BorderLayout());
		this.dm = new DecolorizerModules();
		this.tools = new Tools();
		this.histograms = new Histograms();
		
		bi = dm.grayImage(bi);
		double[][] data = tools.imageAsDoubleMatrix(bi);
		//dm.printMatrix(data);
		lbp = new LBP(8, 2);
		//double[][] n = lbp.getNeighbourhood();
		//dm.printMatrix(n);
		byte[][] lbpResults = lbp.getLBP(data);
		//dm.printMatrix(lbpResults);
		
		lbpResReshape = lbp.reshape(lbpResults, 0, lbpResults.length-1, 0, lbpResults[0].length-1);
		lbpResSum = lbp.sum(lbpResReshape);
		
		BufferedImage bi3 = histograms.plotHistogram(bi);
		
		Examples e = new Examples(lbp);
		this.partialLbpHist = e.example1(data, lbpResults, 150, 160);
		
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

	public BufferedImage getPartialLbpHist() {
		return partialLbpHist;
	}

	public double[] getLbpResReshape() {
		return lbpResReshape;
	}

	public double getLbpResSum() {
		return lbpResSum;
	}

}
