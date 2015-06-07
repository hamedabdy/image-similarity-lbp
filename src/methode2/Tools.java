package methode2;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import com.mortennobel.imagescaling.experimental.ImprovedMultistepRescaleOp;

public class Tools {
	
	public Tools() {
		
	}
	
	public double max(double[] array) {
		double max = array[0];
		for (int i = 0; i < array.length; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}		
		return max;
	}
	
	public int max(int[] array) {
		int max = array[0];
		for (int i = 0; i < array.length; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}		
		return max;
	}
	
	public int getPixel(BufferedImage image, int x, int y) {
		int red = 0, green = 0, blue = 0, pixel = 0;
		Color color;
		color = new Color(image.getRGB(x, y));
		red = color.getRed();
		green = color.getGreen();
		blue = color.getBlue();
		pixel = red + green + blue;
		return pixel;
	}

	public int mixColor(int red, int green, int blue) {
		return red << 16 | green << 8 | blue;
	}
	
	public void printMatrix(byte[][] matrix){
		for (int x = 0; x< matrix.length;++x){
			for (int y = 0; y<matrix[x].length;++y){
				System.out.print(matrix[x][y]+"\t");
			}
			System.out.println();
		}
	}
	
	public void printMatrix(double[][] matrix){
		DecimalFormat f = new DecimalFormat("0.#");
		for (int x = 0; x< matrix.length;++x){
			for (int y = 0; y<matrix[x].length;++y){
				System.out.print(f.format(matrix[x][y])+"\t");
			}
			System.out.println();
		}
	}
	
	public double[][] imageAsDoubleMatrix(BufferedImage image) {
		double[][] imageMatrix = new double[image.getWidth()][image.getHeight()];
		double pixel = 0;
		for (int x = 0; x< image.getWidth();++x){
			for (int y = 0; y<image.getHeight();++y){
				//System.out.print((getPixel(image, x, y)/3)+"  ");
				pixel = getPixel(image, x, y)/3;
				imageMatrix[x][y] = pixel;
			}
			//System.out.println();
		}
		return imageMatrix;
	}
	
	public BufferedImage byteMatrixToImage(byte[][] matrix) {
		BufferedImage bufferedImage = new BufferedImage(matrix.length, matrix[0].length, BufferedImage.TYPE_INT_RGB);
	    for (int i = 0; i < matrix.length; i++) {
	        for (int j = 0; j < matrix[0].length; j++) {
	            int pixel=matrix[i][j];
	            //System.out.println("The pixel in Matrix: "+pixel);
	            bufferedImage.setRGB(i, j, mixColor(pixel, pixel, pixel));
	            //System.out.println("The pixel in BufferedImage: "+bufferedImage.getRGB(i, j));
	        }
	    }
		return bufferedImage;
	}
	
	public BufferedImage byteMatrixToImage(byte[][] matrix, int multiplier) {
		BufferedImage bufferedImage = new BufferedImage(matrix.length, matrix[0].length, BufferedImage.TYPE_INT_RGB);
	    for (int i = 0; i < matrix.length; i++) {
	        for (int j = 0; j < matrix[0].length; j++) {
	            int pixel=matrix[i][j]*multiplier;
	            //System.out.println("The pixel in Matrix: "+pixel);
	            bufferedImage.setRGB(i, j, mixColor(pixel, pixel, pixel));
	            //System.out.println("The pixel in BufferedImage: "+bufferedImage.getRGB(i, j));
	        }
	    }
		return bufferedImage;
	}
	
	public BufferedImage doubleMatrixToImage(double[][] matrix) {
		BufferedImage bufferedImage = new BufferedImage(matrix.length, matrix[0].length, BufferedImage.TYPE_INT_RGB);
	    for (int i = 0; i < matrix.length; i++) {
	        for (int j = 0; j < matrix[0].length; j++) {
	            int pixel=(int) matrix[i][j];
	            //System.out.println("The pixel in Matrix: "+pixel);
	            bufferedImage.setRGB(i, j, mixColor(pixel, pixel, pixel));
	            //System.out.println("The pixel in BufferedImage: "+bufferedImage.getRGB(i, j));
	        }
	    }
		return bufferedImage;
	}
	
	public BufferedImage rescaleImage(BufferedImage  imageToRescale, BufferedImage image) {
		ImprovedMultistepRescaleOp resampleOp = new ImprovedMultistepRescaleOp (image.getWidth(), image.getHeight());
		return resampleOp.filter(imageToRescale, null);
	}
	
	public double difference(double value1, double value2) {
		double dif = Math.abs(value1-value2);
		System.out.println("Difference: " + dif);
		return dif;
	}
	
	public double euclideanDistance(double value1, double value2) {
		double distance = Math.sqrt(Math.pow(value1-value2, 2));
		System.out.println("Euclidean distance: " + distance);
		return distance;
	}

}
