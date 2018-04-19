import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

public class Rdm {

	static Random r1 = new Random();
	static int xMax = 6000;
	static int yMax = 4000;
	static int iterations = 200;
	static int corners = 5;
	static BufferedImage image = new BufferedImage(xMax, yMax, BufferedImage.TYPE_INT_RGB);
	public static void main(String[] args) throws IOException {
		for (int i = 0; i < iterations; i++) {
			drawToImage(drawPolygon(getPolygon(corners)));
		}
		File outputfile = new File("image_1.png");
		ImageIO.write(image, "png", outputfile);
	}
	
	private static Polygon getPolygon(int corners) {
		Polygon p1 = new Polygon();
		for (int i = 0; i < corners; i++)
			p1.addPoint(r1.nextInt(xMax), r1.nextInt(yMax));
		return p1;
	}
	
	private static BufferedImage drawPolygon(Polygon p) {
		BufferedImage res = new BufferedImage(xMax, yMax, BufferedImage.TYPE_INT_RGB);
		Graphics2D g1 = res.createGraphics();
		g1.setColor(new Color(r1.nextInt(5), r1.nextInt(5), r1.nextInt(5)));
		g1.fillPolygon(p);
		return res;
	}
	
	private static void drawToImage(BufferedImage other) {
		int colorOther = 0;
		int colorImage = 0;
		int blue = 0;
		int green = 0;
		int red = 0;
		for (int x = 0; x < xMax; x++) {
			for (int y = 0; y < yMax; y++) {
				colorOther = other.getRGB(x, y);
				colorImage = image.getRGB(x, y);
				blue = ((colorOther & 0xff) + (colorImage & 0xff));
				green = (((colorOther & 0xff00) >> 8) + ((colorImage & 0xff00) >> 8));
				red = (((colorOther & 0xff0000) >> 16) + ((colorImage & 0xff0000) >> 16));
				image.setRGB(x, y, (red << 16 | green << 8 | blue));
			}
		}
	}

}
