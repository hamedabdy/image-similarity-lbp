package methode2;
import java.awt.image.BufferedImage;


public class DecolorizerModules {

	private Tools tools;
	
	public DecolorizerModules() {
		tools = new Tools();
	}

	public BufferedImage grayImage(BufferedImage image) {
		BufferedImage output = new BufferedImage(image.getWidth(),image.getHeight(), image.getType());
		int pixel = 0;
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				pixel =  tools.getPixel(image, x, y) / 3;
				pixel =  tools.mixColor(pixel, pixel, pixel);
				output.setRGB(x, y, pixel);
			}
		}
		return output;
	}

	public BufferedImage binarizeImage(BufferedImage grayImage, int threshold) {
		BufferedImage output = new BufferedImage(grayImage.getWidth(), grayImage.getHeight(), grayImage.getType());
		int pixel = 0;
		for (int x = 0; x < grayImage.getWidth(); x++) {
			for (int y = 0; y < grayImage.getHeight(); y++) {
				pixel =  tools.getPixel(grayImage, x, y) / 3;
				if (pixel > threshold) {
					output.setRGB(x, y,  tools.mixColor(0, 0, 0));
				} else {
					output.setRGB(x, y,  tools.mixColor(255, 255, 255));
				}
			}
		}
		return output;
	}

	public BufferedImage colorFilter(BufferedImage image, int seuil){
		BufferedImage output = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		for(int x=0; x<image.getWidth(); x++){
			for(int y=0; y<image.getHeight(); y++){	
				/*
				output.setRGB(x, y, (image.getRGB(x, y) & 0xff00ff00)
                        | ((image.getRGB(x, y) & 0xff0000) >> 16)
                        | ((image.getRGB(x, y) & 0xff) << 16)); */
				if( tools.getPixel(image, x, y) > seuil){
					output.setRGB(x, y,  tools.mixColor(255, 255, 255));
				}
				else output.setRGB(x, y, image.getRGB(x, y));
			}
		}
		return output;
	}
	
}
