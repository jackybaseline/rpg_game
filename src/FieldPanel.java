import java.io.*;
import java.util.*;
import java.lang.reflect.Constructor;

import java.awt.*;
import javax.swing.*;

public class FieldPanel extends JPanel {
	public static final int BLOCK_W = 32;
	public static final int BLOCK_H = 32;
	public static final int DEF_COL = 24;
	public static final int DEF_ROW = 20;
	
	public int fullWidth, fullHeight;
	public double blockWidth, blockHeight;
	
	private int x, y, w, h;
	private double sx, sy, sw, sh;
	private boolean scaled;
	
	private Image image;
	private int infoRow, infoCol;
	private int[][] info;
	
	public RPlayer[] npc;
	
	public FieldPanel() {
		setBackground(Color.black);
		setLayout(null);
		
		Thread render = new Thread(new Runnable() {
			public void run() {
				while(true) {
					if (Math.abs(sx - x * BLOCK_W) < 7)
						sx = x * BLOCK_W;
					else
						sx -= (sx - x * BLOCK_W) / 7;
					if (Math.abs(sy - y * BLOCK_H) < 7)
						sy = y * BLOCK_H;
					else
						sy -= (sy - y * BLOCK_H) / 7;
					
					try {Thread.sleep(10);} catch (InterruptedException ie) {ie.printStackTrace();}
				}
			}
		});
		render.start();
	}
	public FieldPanel(double width, double height) {
		this();
		
		fullWidth = (int)width;
		fullHeight = (int)height;
		blockWidth = width / DEF_COL;
		blockHeight = height / DEF_ROW;
		setSize(new Dimension((int)fullWidth, (int)fullHeight));
	}
	public FieldPanel(double width, double height, String filename) {
		this(width, height);
		
		changeImage(filename);
	}
	
	public void add(RPlayer player) {
		add(player.panel);
	}
	
	public void setPoint() {}
	public void moveUp(RPlayer player) {
		if ((info[player.getY()][player.getX()] & 8) > 0) {
			if (player instanceof MasterPlayer) {
				MasterPlayer master = (MasterPlayer)player;
				if (y > 0 && master.panel.getLocation().y / master.panel.getHeight() < DEF_ROW / 2) {
					this.y--;
					moveNPC('y', 1);
					master.move(false);
				}
				else {
					master.move(true);
				}
			}
		}
		else {
			player.move();
			System.out.println(player.getY() + " " + player.getX() + " " +  info[player.getY()][player.getX()]);
		}
	}
	public void moveDown(RPlayer player) {
		if ((info[player.getY()][player.getX()] & 4) > 0) {
			if (player instanceof MasterPlayer) {
				MasterPlayer master = (MasterPlayer)player;
				if (y + h < infoRow && master.panel.getLocation().y / master.panel.getHeight() >= DEF_ROW / 2) {
					this.y++;
                    moveNPC('y', -1);
					master.move(false);
				}
				else {
					master.move(true);
				}
			}
		}
		else {
			player.move();
			System.out.println(player.getY() + " " + player.getX() + " " + info[player.getY()][player.getX()]);
		}
	}
	public void moveLeft(RPlayer player) {
		if ((info[player.getY()][player.getX()] & 2) > 0) {
			if (player instanceof MasterPlayer) {
				MasterPlayer master = (MasterPlayer)player;
				if (x > 0 && master.panel.getLocation().x / master.panel.getWidth() < DEF_COL / 2) {
					this.x--;
                    moveNPC('x', 1);
					master.move(false);
				}
				else {
					master.move(true);
				}
			}
		}
		else {
			player.move();
			System.out.println(player.getY() + " " + player.getX() + " " + info[player.getY()][player.getX()]);
		}
	}
	public void moveRight(RPlayer player) {
		if ((info[player.getY()][player.getX()] & 1) > 0) {
			if (player instanceof MasterPlayer) {
				MasterPlayer master = (MasterPlayer)player;
				if (x + w < infoCol && master.panel.getLocation().x / master.panel.getWidth() >= DEF_COL / 2) {
					this.x++;
                    moveNPC('x', -1);
					master.move(false);
				}
				else {
					master.move(true);
				}
			}
		}
		else {
			player.move();
			System.out.println(player.getY() + " " + player.getX() + " " + info[player.getY()][player.getX()]);
		}
	}
	
    public void moveNPC(char coordinate, int t){
    	for ( int i = 0; i < npc.length; i++ ){
    		if ( coordinate == 'x' )
    			npc[i].locate(npc[i].getX()+t, npc[i].getY());
			if ( coordinate == 'y' )
    			npc[i].locate(npc[i].getX(), npc[i].getY()+t);
    	}
    }
	
	public void changeImage(String imageFilename) {
		image = getToolkit().getImage(imageFilename);
		
		scaled = false;
	}
	public void changeImage(int x, int y, String infoFilename, String imageFilename, RPlayer master, int mx, int my) {
		removeAll();
		
    	add(master);
    	master.locate(mx, my);
		
		Scanner scanner;
		try {
			scanner = new Scanner(new FileReader(infoFilename));
		} catch (FileNotFoundException fnfe) {
			scanner = null;
			fnfe.printStackTrace();
		}
		
        int numberNPC = scanner.nextInt();
        npc = new RPlayer[numberNPC];
        for (int i = 0; i < numberNPC; i++){
        	String classname = scanner.next();
			System.out.println(classname);
        	try{
	        	Constructor c = Class.forName(classname).getConstructor(Double.TYPE, Double.TYPE);
	        	npc[i] = (RPlayer)c.newInstance(this.blockWidth, this.blockHeight);
	        	npc[i].locate(scanner.nextInt(), scanner.nextInt());
	        	npc[i].look(scanner.nextInt());
	        	this.add(npc[i]);
        	}
        	catch(Exception e){}
        }
		
		infoRow = scanner.nextInt();
		infoCol = scanner.nextInt();
		
		info = new int[infoRow][infoCol];
		for (int i = 0; i < infoRow; i++)
			for (int j = 0; j < infoCol; j++)
				info[i][j] = scanner.nextInt();
		
		// Panel Width > Image Width
		if (infoCol <= DEF_COL) {
			setLocation((int)((DEF_COL - infoCol) * blockWidth / 2), getLocation().y);
			setSize(new Dimension(infoCol * (int)blockWidth, getHeight()));
			this.x = 0;
			this.w = infoCol;
			this.sx = 0;
			this.sw = infoCol * BLOCK_W;
		}
		// Panel Width <= Image Width
		else {
			setLocation(0, getLocation().y);
			setSize(new Dimension(fullWidth, getHeight()));
			this.x = x;
			this.w = DEF_COL;
			this.sx = x * BLOCK_W;
			this.sw = DEF_COL * BLOCK_W;
		}
		// Panel Height > Image Height
		if (infoRow <= DEF_ROW) {
			setLocation(getLocation().x, (int)((DEF_ROW - infoRow) * blockHeight / 2));
			setSize(new Dimension(getWidth(), infoRow * (int)blockHeight));
			this.y = 0;
			this.h = infoRow;
			this.sy = 0;
			this.sh = infoRow * BLOCK_H;
		}
		// Panel Height <= Image Height
		else {
			setLocation(getLocation().x, 0);
			setSize(new Dimension(getWidth(), fullHeight));
			this.y = y;
			this.h = DEF_ROW;
			this.sy = y * BLOCK_H;
			this.sh = DEF_ROW * BLOCK_H;
		}
		
		image = getToolkit().getImage(imageFilename);
		
		scaled = true;
	}
	
	public void paintComponent(Graphics g) {
		if (scaled) {
			g.drawImage(image, 0, 0, getWidth(), getHeight(), (int)sx, (int)sy, (int)(sx + sw), (int)(sy + sh), this);
		}
		else {
			g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		}
	}
}