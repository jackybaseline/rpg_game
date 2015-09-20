
import java.awt.*;
import javax.swing.*;

public class RBotimageLabel extends JLabel
{
	protected Image image;
	protected int sizex;
	protected int sizey;
	protected double width;
	protected double height;
	public RBotimageLabel(int sizex,int sizey,double x,double y,double width, double height,RBot bot) 
	{
		this.sizex=sizex;
		this.sizey=sizey;
		this.width=width;
		this.height=height;
		setBounds((int)x,(int)y, (int)width, (int)height);
		image = bot.image[0];
       // try {
            // image = ImageIO.read( new File(imagename));
        // }  catch (IOException ex) {
            // ex.printStackTrace();
        // }
	}
	public void paint(Graphics g)
	{
		g.drawImage(image,0,0,(int)width, (int)height,0,0,sizex,sizey,null);
	}
}