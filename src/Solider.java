import java.awt.*;
import javax.swing.*;

public class Solider extends RPlayer {

	public Solider(double blockWidth, double blockHeight) {
		point = new Point(-1, -1);
		charWidth = 28;
		charHeight = 46;
		panel = new PlayerPanel(charWidth, charHeight, blockWidth, blockHeight, point, "image/character/Solider.png");

		addbot(new DevilBing());
	}

}