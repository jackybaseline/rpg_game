import java.io.*;

import java.awt.*;
import javax.swing.*;

public class PlayerPanel extends RPanel {
	public static final int DOWN = 0;
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	public static final int UP = 3;
	
	protected Point point;
	protected int charWidth;
	protected int charHeight;
	protected int sx, sy, sw, sh;
	protected int shiftX, shiftY;
	
	public PlayerPanel(int cw, int ch, double w, double h, Point p, String filename) {
		super(w, h, filename);
		
		charWidth = cw;
		charHeight = ch;
		sx = charWidth;
		point = p;
		
		Thread render = new Thread(new Runnable() {
			public void run() {
				while(true) {
					double X = getLocation().x;
					double Y = getLocation().y;
					if (Math.abs(X - point.x * getWidth()) < 7)
						X = point.x * getWidth();
					else
						X -= (X - point.x * getWidth()) / 7;
					if (Math.abs(Y - point.y * getHeight()) < 7)
						Y = point.y * getHeight();
					else
						Y -= (Y - point.y * getHeight()) / 7;
					setLocation((int)X, (int)Y);

					try {Thread.sleep(20);} catch (InterruptedException ie) {ie.printStackTrace();}
				}
			}
		});
		render.start();
	}
	
	public void look(int dir) {
		sx = charWidth;
		sy = charHeight * dir;
	}
	public void walk() {
		Thread walker = new Thread(new Runnable() {
			public void run() {
				sx = 0;
				try {Thread.sleep(100);} catch (InterruptedException ie) {ie.printStackTrace();}
				
				sx = charWidth * 2;
				try {Thread.sleep(100);} catch (InterruptedException ie) {ie.printStackTrace();}
				
				sx = charWidth;
				System.out.println("Walker Complete");
			}
		});
		walker.start();
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, getWidth(), getHeight(), sx, sy, sx + charWidth, sy + charHeight, this);
	}
}