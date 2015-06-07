package methode2;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Histograms {

	private BufferedImage bi;
	private Tools tools;

	public Histograms() {
		tools = new Tools();
	}

	public BufferedImage zoom(BufferedImage image, int zoomLevel){
		int newImageWidth = image.getWidth() * zoomLevel;
		int newImageHeight = image.getHeight() * zoomLevel;
		BufferedImage resizedImage = new BufferedImage(newImageWidth , newImageHeight, image.getType());
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(image, 0, 0, newImageWidth , newImageHeight , null);
		//g.setColor(Color.BLACK);
		//g.drawString("hello", 5, 15);
		g.dispose();
		return resizedImage;
	}


	public BufferedImage plotHistogram(BufferedImage image, int numberOfBins) {
		int width = numberOfBins, height = image.getHeight();
		int pixel;
		int[] pixels = new int[width];
		BufferedImage output = new BufferedImage(width, height, image.getType());
		Graphics2D g = output.createGraphics();
		g.setColor(Color.BLACK);
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
		// Initialize output to blank
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				output.setRGB(i, j,  tools.mixColor(255, 255, 255));
			}
		}
		g.drawString("0 --> 255", 5, 15);
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				pixel =  tools.getPixel(image, x, y) / 3;
				pixels[pixel]++;
				//System.out.println("pixel[" + pixel + "]= " + pixels[pixel]);
			}
		}
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = pixels[i] / 5;
			//if (pixels[i] > height)	pixels[i] = height - 1;
			//System.out.println("pixel[" + i + "]= " + pixels[i]);
		}
		// Drawing bins into a BufferedImage (output)
		for (int x = 0; x < width; x++) {
			int y = height - 1;
			do {
				output.setRGB(x, y,  tools.mixColor(0, 0, 0));
				y--;
			} while (y > 0 && y > height - pixels[x]);
		}
		return output;
	}

	public BufferedImage plotHistogram(BufferedImage image) {
		int width = 256, height = image.getHeight();
		int pixel;
		int[] pixels = new int[width];
		BufferedImage output = new BufferedImage(width, height, image.getType());
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
		// Initialize output to blank
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				output.setRGB(i, j,  tools.mixColor(255, 255, 255));
			}
		}
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				pixel =  tools.getPixel(image, x, y) / 3;
				pixels[pixel]++;
				//System.out.println("pixel[" + pixel + "]= " + pixels[pixel]);
			}
		}
		Graphics2D g = output.createGraphics();
		g.setColor(Color.BLACK);
		g.drawString("0 --> "+(width-1), 5, 15);
		g.dispose();
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = pixels[i] / 5;
			//if (pixels[i] > height)	pixels[i] = height - 1;
			//System.out.println("pixel[" + i + "]= " + pixels[i]);
		}
		// Drawing bins into a BufferedImage (output)
		for (int x = 0; x < width; x++) {
			int y = height - 1;
			do {
				output.setRGB(x, y,  tools.mixColor(0, 0, 0));
				y--;
			} while (y > 0 && y > height - pixels[x]);
		}
		return output;
	}

	public BufferedImage plotHistogram(double[] hist, int zoom) {
		int multiplier = 10;
		int width = (int)((tools.max(hist)+1)+multiplier), height = (hist.length+multiplier);
		double[] pixels = new double[hist.length];
		int pixel = 0;
		for (int i = 0; i < hist.length; i++) {
			pixel = (int) hist[i];
			pixels[pixel] += (1*(multiplier/2));
			//System.out.println("pixel[" + pixel + "]= " + pixels[pixel]);
		}

		BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				// Initialize output to white color
				output.setRGB(i, j,  tools.mixColor(255, 255, 255));
			}
		}

		int max =  (int)tools.max(hist)-2;
		for (int x = 0; x < max; x++) {
			int y = pixels.length - 1;
			do {
				output.setRGB(x+5, y+5,  tools.mixColor(0, 0, 0));
				y--;
				//System.out.println("x: " + x + " y: " + y);
				//System.out.println(hist.length - hist[x]);
			} while (y > 0 && y > pixels.length - pixels[x]);
		}
		
		output = zoom(output, zoom);
		Graphics2D g = output.createGraphics();
		g.setColor(Color.BLUE);
		g.drawString("0 --> "+(int)tools.max(hist), 5, 20);
		g.dispose();
		return output;
	}

	public BufferedImage plotHorizHistogram(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		int[] pixels = new int[height];
		BufferedImage output = new BufferedImage(width, height, image.getType());
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				// Initialize output to white color
				output.setRGB(x, y,  tools.mixColor(255, 255, 255));
				// we count our white pixels in a binary image
				if ( tools.getPixel(image, x, y) != 0)
					pixels[y]++;
			}
		}
		for (int x = width - 1; x > 0; x--) {
			for (int y = 0; y < height; y++) {
				if (pixels[y] != 0) {
					output.setRGB(x, y,  tools.mixColor(0, 0, 0));
					pixels[y]--;
				}
			}
		}
		return output;
	}

	public BufferedImage plotVertiHistogram(BufferedImage image) {
		int[] pixels = new int[image.getWidth()];
		BufferedImage output = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}

		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				// Initialize output to white color
				output.setRGB(x, y,  tools.mixColor(255, 255, 255));
				// we count our white pixels in a binary image
				if ( tools.getPixel(image, x, y) != 0)
					pixels[x]++;
			}
		}
		// we draw the counted black pixels
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = image.getHeight() - 1; y > image.getHeight()- pixels[x]; y--) {
				output.setRGB(x, y,  tools.mixColor(0, 0, 0));
			}
		}
		return output;
	}

	public BufferedImage getBi() {
		return bi;
	}

	public void setBi(BufferedImage bi) {
		this.bi = bi;
	}

}
