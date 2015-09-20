import java.awt.*;
import javax.swing.*;

public class StatPanel extends RPanel {
	private RBot rbot;
	public Bar hp;
	public Bar mp;
	
	private Updater updater;

	public StatPanel(int x, int y, double w, double h) {
		super(x, y, w, h);
		
		add(hp = new Bar(0, 0, w, h / 2, Color.red));
		add(mp = new Bar(0, (int)(h / 2), w, h / 2, Color.blue));
		
		// updater = new Updater();
	}
	public StatPanel(int x, int y, double w, double h, RBot rbot) {
		super(x, y, w, h);
		
		setOpaque(false);
		
		add(hp = new Bar(0, 0, w, h / 2, Color.red));
		add(mp = new Bar(0, (int)(h / 2), w, h / 2, Color.blue));
		
		hp.setValue(rbot.getHealth());
		hp.setFull(rbot.getHealthMax());
		mp.setValue(rbot.getPower());
		mp.setFull(rbot.getPowerMax());
		
		// updater = new Updater();
	}
	
	public void initial(RBot rbot) {
		this.rbot = rbot;
		
		updater = new Updater();
		updater.start();
	}
	
	public void terminate() {
		updater.done();
	}
	
	class Updater extends Thread implements Runnable {
		private boolean DONE;
		public void run() {
			while(!DONE) {
				System.out.print("");
				hp.setValue(rbot.getHealth());
				hp.setFull(rbot.getHealthMax());
				mp.setValue(rbot.getPower());
				mp.setFull(rbot.getPowerMax());
			}
		}
		
		public void done() {
			DONE = true;
		}
	}
}