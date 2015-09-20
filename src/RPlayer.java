import java.awt.*;
import javax.swing.*;

public class RPlayer {
	public static final int DOWN = 0;
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	public static final int UP = 3;
	
	protected int direction;
	
	protected Point point;
	protected int charWidth, charHeight;
	protected PlayerPanel panel;
	
	protected RBot[] bot = new RBot[0];
	protected RItem[] item = new RItem[0];
	// protected REquip[] equip;
	
	public RBot[] getbot() {return bot;}
	public RItem[] getitem() {return item;}
	
	public void addbot(RBot newbot) {
		RBot[] temp = bot;
		bot = new RBot[bot.length + 1];
		for (int i = 0; i < bot.length - 1; i++)
			bot[i] = temp[i];
		bot[bot.length - 1] = newbot;
	}
	public void additem(RItem newitem) {
		for(int i=0;i<item.length;++i)
			if(item[i].getname()==newitem.getname())
			{
				item[i].addcount();
				return ;
			}
		RItem[] temp=item;
		item=new RItem[item.length+1];
		for(int i=0;i<item.length-1;++i)
			item[i]=temp[i];
		item[item.length-1]=newitem;
	}
	public void removeitem(int order) {
		RItem[] temp=item;
		item=new RItem[item.length-1];
		for(int i=0;i<order;++i)
			item[i]=temp[i];
		for(int i=order;i<item.length;++i)
			item[i]=temp[i+1];
	}
	
	public void locate(int x, int y) {
		point.x = x;
		point.y = y;
	}
	public void look(int dir) {
		panel.look(dir);
		direction = dir;
	}
	public void move() {
		panel.walk();
		
		switch(direction) {
			case UP:	point.y--; break;
			case DOWN:	point.y++; break;
			case LEFT:	point.x--; break;
			case RIGHT:	point.x++; break;
		}
	}
	
	public int getX() {
		return point.x;
	}
	public int getY() {
		return point.y;
	}
	public int lookAt() {
		return direction;
	}
}