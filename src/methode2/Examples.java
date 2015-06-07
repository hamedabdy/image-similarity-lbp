package methode2;

import java.awt.image.BufferedImage;

import ijGrower.LBP;

public class Examples {

	private LBP lbp;
	private Histograms histograms;

	public Examples(LBP lbp) {
		this.lbp = lbp;
		this.histograms =  new Histograms();
	}

	public BufferedImage example1(double[][] data, byte[][] lbpResults, int pBegin, int pEnd) {
		double[] arr;
		double[] arr2;
		double sumArr2;
		arr = lbp.reshape(data, pBegin, pEnd, pBegin, pEnd);
		arr2 = lbp.reshape(lbpResults, pBegin, pEnd, pBegin, pEnd);
		sumArr2 = lbp.sum(arr2);
		for(int i=0; i<arr.length; i++) {
			System.out.println("original pixel= "+arr[i] + " --LBP--> " + arr2[i]);
		}
		System.out.println("----------------------------------");
		System.out.println("\t\tLBP sum = " + sumArr2);
		System.out.println("----------------------------------");
		System.out.println();
				
		return histograms.plotHistogram(arr2, 4);
	}

}
