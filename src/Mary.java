import java.awt.*;
import javax.swing.*;

public class Mary extends RPlayer {

	public Mary(double blockWidth, double blockHeight) {
		point = new Point(-1, -1);
		charWidth = 32;
		charHeight = 43;
		panel = new PlayerPanel(charWidth, charHeight, blockWidth, blockHeight, point, "image/character/Mary.png");

		addbot(new DevilBing());
	}

}