import java.awt.*;
import javax.swing.*;

enum Direction {
	UP,
	DOWN,
	LEFT,
	RIGHT
}
public class SelectPanel extends RPanel {
	public SelectPanel(int x, int y, double w, double h) {
		super(x, y, w, h);
		
		setBackground(Color.black);
	}
	
	public void move(Direction direction) {
		switch (direction) {
			case UP:
				setLocation(getLocation().x, getLocation().y - getHeight());
			break;
			case DOWN:
				setLocation(getLocation().x, getLocation().y + getHeight());
			break;
			case LEFT:
				setLocation(getLocation().x - getWidth(), getLocation().y);
			break;
			case RIGHT:
				setLocation(getLocation().x + getWidth(), getLocation().y);
			break;
		}
	}
}