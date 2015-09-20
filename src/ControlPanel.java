import java.awt.*;
import javax.swing.*;

public class ControlPanel extends RPanel {
	private int command = 0;

	// private int skill_now = 0;
	// private int item_now = 0;
	// private int bot_now = 0;
	
	private RPanel displaypanel;
	private StatPanel statpanel;
	// private DescriptionPanel descriptionpanel;
	private DialogPanel dialogpanel;
	private RPanel[] commandpanel = new RPanel[4];
	private SelectPanel selectpanel;
	
	private RBot bot;
	private RPlayer player;
	
	public ControlPanel(int x, int y, double w, double h) {
		super(x, y, w, h);
		
		add(displaypanel = new RPanel(5, 5, 150, 150));
		displaypanel.setVisible(false);
		
		add(statpanel = new StatPanel(160, 5, 240, 150));
		statpanel.setVisible(false);
		
		// add(descriptionpanel = new DescriptionPanel(160, 5, 240, 150));
		// descriptionpanel.setVisible(false);
		
		add(dialogpanel = new DialogPanel(0, 0, 410, 160));
		
		add(commandpanel[0] = new RPanel(410, 0, 225, 80));
		commandpanel[0].setVisible(false);
		
		add(commandpanel[1] = new RPanel(635, 0, 225, 80));
		commandpanel[1].setVisible(false);
		
		add(commandpanel[2] = new RPanel(410, 80, 225, 80));
		commandpanel[2].setVisible(false);
		
		add(commandpanel[3] = new RPanel(635, 80, 225, 80));
		commandpanel[3].setVisible(false);
		
		add(selectpanel = new SelectPanel(410, 0, 225, 80));
	}
	
	public void set(RBot b) {bot = b;}
	public void set(RPlayer p) {player = p;}
	
	public Control confirm() {
		if (command == 0) {
			return Control.SKILL;
		}
		else if (command == 1) {
			return Control.ITEM;
		}
		else if (command == 2) {
			return Control.BOT;
		}
		else {
			return Control.SURRENDER;
		}
	}
	
	public void selectUp() {
		if (command == 2 || command == 3) {
			popDown();
			command -= 2;
			popUp();
			selectpanel.move(Direction.UP);
		}
	}
	public void selectDown() {
		if (command == 0 || command == 1) {
			popDown();
			command += 2;
			popUp();
			selectpanel.move(Direction.DOWN);
		}
	}
	public void selectLeft() {
		if (command == 1 || command == 3) {
			popDown();
			command--;
			popUp();
			selectpanel.move(Direction.LEFT);
		}
	}
	public void selectRight() {
		if (command == 0 || command == 2) {
			popDown();
			command++;
			popUp();
			selectpanel.move(Direction.RIGHT);
		}
	}
	
	public void popUp() {
		switch (command) {
			case 0:
				
			break;
			case 1:
				// Item Panel show
			break;
			case 2:
				// Bot Panel show
			break;
			case 3:
				// dialogpanel.changeImage("");
			break;
		}
	}
	public void popDown() {
		switch (command) {
			case 0:
				
			break;
			case 1:
				// Item Panel show
			break;
			case 2:
				// Bot Panel not show
			break;
			case 3:
				dialogpanel.setVisible(false);
			break;
		}
	}
}


















