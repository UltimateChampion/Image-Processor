
public class ColorValue {
	
	public void ColorValue() {
		
	}
	
	public static int getRed(int x) {
		
		return ((x >> 16) & (0x000000FF));
	}
	
	public static int getBlue(int x) {
		
		return ((x >> 8) & (0x000000FF));
	}
	
	public static int getGreen(int x) {
		
		return (x & (0x000000FF));
	}


}
