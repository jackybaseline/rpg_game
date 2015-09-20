import java.awt.*;
import javax.swing.*;

public class DialogPanel extends JPanel {
	private static final int LINE = 2;

	private Image image;
	private int tx1, ty1;
	private int tx2, ty2;

	Thread thread;

	public DialogPanel() {
		setBackground(Color.gray);

		setVisible(false);
	}
	public DialogPanel(double x, double y, double width, double height) {
		this();

		setBounds((int)x, (int)y, (int)width, (int)height);
	}

	public void changeImage(String imageFilename) {
		tx1 = 0;
		tx2 = 0;
		ty1 = (int)getHeight()/2;
		ty2 = 2*(int)getHeight()/2;

		//while ( image != null ){}
        image = getToolkit().getImage(imageFilename);
		setVisible(true);

        thread = new Thread(new Runnable() {
			public void run() {
				while(true){
					System.out.println("tx1: " + tx1 + " " + "ty1: " + ty1);
			        System.out.println("tx2: " + tx2 + " " + "ty2: " + ty2);
					if ( tx1 < getWidth() ){
						// tx1 += getWidth()/10;
						tx1 += 3;
					}
					else if ( tx2 < getWidth() ){
						// tx2 += getWidth()/10;
						tx2 += 3;
					}
					else{
						break;
					}

					try{ Thread.sleep(5); }
					catch(Exception e){}
				}
				try{ Thread.sleep(1000); }
				catch(Exception e){}
				setVisible(false);
				//image = null;
			}
		});
        thread.start();
    }

	public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, tx1, ty1, 0, 0, tx1, ty1, this);
        g.drawImage(image, 0, 0, tx2, ty2, 0, 0, tx2, ty2, this);
    }
}