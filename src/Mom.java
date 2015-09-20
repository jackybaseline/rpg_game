import java.awt.*;
import javax.swing.*;

public class Mom extends RPlayer {

	public Mom(double blockWidth, double blockHeight) {
		point = new Point(-1, -1);
		charWidth = 28;
		charHeight = 46;
		panel = new PlayerPanel(charWidth, charHeight, blockWidth, blockHeight, point, "image/character/Mom.png");

		addbot(new DevilBing());
	}

}