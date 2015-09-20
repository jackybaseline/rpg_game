
import javax.swing.*;
import java.awt.*;

public class Bar extends RPanel {
	private int value;
	private int full;
	public Color textColor;

	// public Bar(double width, double height, String filename){
		// super(width, height, filename);
	// }

	public Bar(int x, int y, double width, double height, Color c){
		super(x, y, width, height);
		
		this.setForeground(c);
		this.setBackground(Color.lightGray);
	}

	public void setValue(int v){
		value = v;
	}

	public void setFull(int f){
		full = f;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (full == 0) return;
		
		int width = getWidth() - 4;
		int height = getHeight() - 4;
		int x = 2;
		int y = 2;

		g.setColor(getBackground());
		g.fillRect(x, y, width, height);
		g.setColor(Color.black);
		g.drawRect(x, y, width, height);

		g.setColor(getForeground());
		g.fillRect(x, y, (width * value / full), height);
		g.setColor(Color.black);
		g.drawRect(x, y, (width * value / full), height);

		g.setColor(textColor);
		g.drawString(Integer.toString(value)+"/"+Integer.toString(full), width / 2, (height + 10) / 2);
	}
}