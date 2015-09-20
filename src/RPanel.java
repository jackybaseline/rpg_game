import java.io.*;

import java.awt.*;
import javax.swing.*;

public class RPanel extends JPanel {
	protected Image image;
	
	public RPanel() {
		setLayout(null);
	}
	public RPanel(double w, double h) {
		this();
		
		setSize(new Dimension((int)w, (int)h));
	}
	public RPanel(int x, int y, double w, double h) {
		this(w, h);
		
		setLocation(x, y);
	}
	public RPanel(double w, double h, String filename) {
		this(w, h);
		
		image = getToolkit().getImage(filename);
	}
	public RPanel(double w, double h, Image img) {
		this(w, h);
		
		image = img;
	}
	
	public void changeImage(Image img) {
		image = img;
	}
	
	public void paintComponent(Graphics g) {
		if (image != null)
			g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		else
			super.paintComponent(g);
	}
}