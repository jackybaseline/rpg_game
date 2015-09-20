
import java.awt.*;
import javax.swing.*;

enum Control {
	NONE,
	SKILL,
	ITEM,
	BOT,
	SURRENDER
}
public class BattlePanel extends JPanel {
	/** [0] is enemy */
	private Control control = Control.NONE;
	private RPanel[] botpanel = new RPanel[2];
	private StatPanel[] statpanel = new StatPanel[2];
	private ControlPanel controlpanel;
	
	private Image image;
	
	public BattlePanel() {
		add(botpanel[0] = new RPanel(550, 50, 200, 200));
		add(statpanel[0] = new StatPanel(100, 50, 400, 150));
		add(botpanel[1] = new RPanel(100, 300, 200, 200));
		add(statpanel[1] = new StatPanel(350, 350, 400, 150));
		add(controlpanel = new ControlPanel(100, 520, 860, 160));
		
		setBackground(Color.white);
		setLayout(null);
		setVisible(false);
	}
	public BattlePanel(double x, double y, double width, double height) {
		this();
		
		setBounds((int)x, (int)y, (int)width, (int)height);
	}
	
	public void begin(RPlayer p1, RPlayer p0) {
		add(p0.bot[0], 0);
		add(p1.bot[0], 1);
		controlpanel.set(p1);
		controlpanel.set(p1.bot[0]);
		
		setVisible(true);
	}
	public void end() {
		setVisible(false);
	}
	
	public void add(RBot bot, int which) {
		botpanel[which].changeImage(bot.image[which]);
		statpanel[which].initial(bot);
	}

	public void enter() {
		switch (control) {
			case NONE:
				control = controlpanel.confirm();
			break;
			case SKILL:
			break;
			case ITEM:
			break;
			case BOT:
			break;
			case SURRENDER:
			break;
		}
	}
	
	public void moveUp() {
		switch (control) {
			case NONE:
				controlpanel.selectUp();
			break;
		}
	}
	public void moveDown() {
		switch (control) {
			case NONE:
				controlpanel.selectDown();
			break;
		}
	}
	public void moveLeft() {
		switch (control) {
			case NONE:
				controlpanel.selectLeft();
			break;
		}
	}
	public void moveRight() {
		switch (control) {
			case NONE:
				controlpanel.selectRight();
			break;
		}
	}
	
	// public void paint(Graphics g) {
		// g.setColor(Color.red);
		// g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
	// }
}