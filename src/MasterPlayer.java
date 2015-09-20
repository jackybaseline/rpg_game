
import java.awt.*;
import javax.swing.*;

public class MasterPlayer extends RPlayer {
	protected Point _point;
	public long initialtime;
	
	public MasterPlayer(double blockWidth, double blockHeight) {
		initialtime = System.currentTimeMillis();
		
		point = new Point(-1, -1);
		_point = new Point(-1, -1);
		charWidth = 16;
		charHeight = 21;
		panel = new PlayerPanel(charWidth, charHeight, blockWidth, blockHeight, _point, "image/character/Master.png");
		
		addbot(new DevilBing());
		addbot(new Bing());
		addbot(new IronBird());
		addbot(new Tunic());
		
		additem(new HPItem("Candy", "CRUSH!", 3));
		additem(new HPItem("Candy", "CRUSH!", 3));
		additem(new HPItem("Candy", "CRUSH!", 3));
		additem(new HPItem("Candy", "CRUSH!", 3));
		additem(new HPItem("Candy", "CRUSH!", 3));
		additem(new HPItem("Apple", "A kind of fruit", 5));
		additem(new HPItem("Banana", "A kind of fruit", 10));
		additem(new HPItem("Banana", "A kind of fruit", 10));;
		additem(new HPItem("Guava", "A kind of fruit", 10));;
		additem(new PPItem("Chocolate", "CHOCO!", 3));
		additem(new HPItem("The Red", "BLOOD!",100 ));
		additem(new PPItem("Milk", "A kind of drinks which can get longer", 10));
		additem(new PPItem("Juice", "A kind of drinks ", 5));
		additem(new PPItem("Water", "A kind of drinks ", 1));
		additem(new PPItem("Water", "A kind of drinks ", 1));
		additem(new PPItem("Water", "A kind of drinks ", 1));
		additem(new PPItem("Water", "A kind of drinks ", 1));
		additem(new HPItem("Recovery", "High recovery", 50));
		additem(new PPItem("The Blue", "Magic", 100));
		additem(new HPItem("Poison", "It's dangerous", -20));
		additem(new PPItem("Virus", "It's dangerous", -20));
		additem(new HPItem("HP slice", "Little HP", 1));
		additem(new HPItem("HP reduce", "HP loss", -1));
		additem(new PPItem("PP slice", "Little PP", 1));
		additem(new PPItem("PP reduce", "PP loss", -1));	
		additem(new HPItem("HP FULL", "Greates", 999));		
		additem(new PPItem("PP FULL", "Greates", 999));	
		additem(new HPItem("Bye-bye", "Never see you", -999));	
		additem(new PPItem("OOM", "Out of mana", -999));	
	}
	
	public void locate(int x, int y) {
		super.locate(x, y);
		_point.x = x;
		_point.y = y;
	}
	public void move() {
		panel.walk();
	}
	public void move(boolean go) {
		this.move();
		
		switch(direction) {
			case UP:	point.y--; break;
			case DOWN:	point.y++; break;
			case LEFT:	point.x--; break;
			case RIGHT:	point.x++; break;
		}
		if (go) {
			switch(direction) {
				case UP:	_point.y--; break;
				case DOWN:	_point.y++; break;
				case LEFT:	_point.x--; break;
				case RIGHT:	_point.x++; break;
			}
		}
	}
}