import javax.swing.*;

public class Main {
	
	
	
	public static void main(String[] args) {
		

		
		JFrame jf= new JFrame("Image Processor");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(new IPPanel());
		jf.setVisible(true);
		jf.pack();
	}

}
