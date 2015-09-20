import java.awt.*;
import javax.swing.*;

public class John extends RPlayer {

	public John(double blockWidth, double blockHeight) {
		point = new Point(-1, -1);
		charWidth = 28;
		charHeight = 32;
		panel = new PlayerPanel(charWidth, charHeight, blockWidth, blockHeight, point, "image/character/John.png");

		addbot(new DevilBing());
	}

}