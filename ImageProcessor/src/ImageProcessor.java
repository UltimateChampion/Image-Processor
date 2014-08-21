import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.Thread;

import javax.imageio.ImageIO;


public class ImageProcessor  {
	
	public static void main(String[] args) {
		
		
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("rb.jpg"));
		} catch (IOException e) {
			System.out.println("Failed to read image.");
		}
		
		int[] z = new int[img.getWidth()*img.getHeight()];
		img.getRGB(0, 0, img.getWidth(), img.getHeight(), z, 0, 1);
		
		ImageProcessor ip = new ImageProcessor();
		int[] info = ip.imgRGBPercent(z);
		
		double sumTotal = info[0] + info[1] + info[2];
		double percentRed = info[0] / sumTotal;
		double percentBlue = info[1] / sumTotal;
		double percentGreen = info[2] / sumTotal;
		
		System.out.println("Total: "+sumTotal+"\nPercentage of R: "+percentRed+"\nPercentage of B: "+percentBlue+"\nPercentage of G: "+percentGreen);
	}
	

	public ImageProcessor() {
		
	}
	
	public void process(int[] x, int startPoint) {
		
		(new Thread(new ReadThread(x, startPoint))).start();	
	}
	
	public int[] imgRGBPercent(int[] x) {
		
		
		ReadThread first = new ReadThread(x, 0);
		ReadThread second = new ReadThread(x, 1);
		ReadThread third = new ReadThread(x, 2);
		ReadThread fourth = new ReadThread(x, 3);
		
		Thread a = (new Thread(first));
		Thread b = (new Thread(second));
		Thread c = (new Thread(third));
		Thread d = (new Thread(fourth));
		
		long timeMetric = System.currentTimeMillis();
		a.start();
		b.start();
		c.start();
		d.start();
		
		
		try {
			
			a.join();
		}
		catch (Exception e) {
			
		}
		
		try {
			
			b.join();
		}
		catch (Exception e) {
			
		}
		
		try {
			
			c.join();
		}
		catch (Exception e) {
			
		}

		try {
			
			d.join();
		}
		catch (Exception e) {
			
		}
		
		int redTotal, blueTotal, greenTotal;
		
		int[] firstTotal = first.getRGBTotals();
		int[] secondTotal = second.getRGBTotals();
		int[] thirdTotal = third.getRGBTotals();
		int[] fourthTotal = fourth.getRGBTotals();

		redTotal = firstTotal[0] + secondTotal[0] + thirdTotal[0] + fourthTotal[0];
		blueTotal = firstTotal[1] + secondTotal[1] + thirdTotal[1] + fourthTotal[1];
		greenTotal = firstTotal[2] + secondTotal[2] + thirdTotal[2] + fourthTotal[2];
		
		System.out.println(greenTotal);
		
		System.out.println(System.currentTimeMillis() - timeMetric);
		
		timeMetric = System.currentTimeMillis();

		
		int[] out = new int[3];
		out[0] = redTotal;
		out[1] = blueTotal;
		out[2] = greenTotal;
		return out;
	}
	
	
	private class ReadThread implements Runnable {

		private int[] colorArr;
		private int startPoint, redTotal, blueTotal, greenTotal;
		private ColorValue cv;
		
		public ReadThread(int[] x, int startPoint) {
			
			colorArr = x;
			this.startPoint = startPoint;
			cv = new ColorValue();
		}
		
		@Override
		public void run() {
			
			//System.out.println("hit");
			
			for (int i = startPoint; i < colorArr.length; i+=4 ) {
				redTotal += ColorValue.getRed(colorArr[i]);
				blueTotal += ColorValue.getBlue(colorArr[i]);
				greenTotal += ColorValue.getGreen(colorArr[i]);

			}	
			
		}
		
		public int[] getRGBTotals() {
			
			int[] out = new int[3];
			out[0] = redTotal;
			out[1] = blueTotal;
			out[2] = greenTotal;
			
			return out;
		}
	}
	
}
