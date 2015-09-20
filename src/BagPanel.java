
import java.awt.*;
import javax.swing.*;

public class BagPanel extends JPanel {
	private int nowselect;
	private SelectLabel select_l;

	private MenuPanel menupanel;
	private StatusPanel statuspanel;
    private BotPanel botpanel;
    private ItemPanel itempanel;
    private EquipPanel equippanel;
    private MapPanel mappanel;

	public BagPanel() {
		setBackground(Color.blue);
		setLayout(null);
		setOpaque(false);
		setVisible(false);
	}
	public BagPanel(double width, double height) {
		this();
		
		setSize((int)width, (int)height);
		
		add(menupanel=new MenuPanel(getWidth(),getHeight()));
		add(statuspanel=new StatusPanel(getWidth() * 0.9, getHeight() * 0.84));
       	add(botpanel=new BotPanel(getWidth() * 0.9, getHeight() * 0.84));
        add(itempanel=new ItemPanel(getWidth() * 0.9, getHeight() * 0.84));
        add(equippanel=new EquipPanel(getWidth() * 0.9, getHeight() * 0.84));
        add(mappanel=new MapPanel(getWidth() * 0.9, getHeight() * 0.84));
	}
	
	public void move(boolean down)
	{
		if(down)
		{
			if(nowselect==6)
				nowselect=0;
			else
				++nowselect;
		}
		else
		{
			if(nowselect==0)
				nowselect=6;
			else
				--nowselect;
		}
		menupanel.getselectlabel().move(down);
	}
	
	public Mode enter(Mode mode) {
        // menupanel.setVisible(false);

        // event.action=Action.NONE;
		switch(nowselect) {
			case 0:
				menupanel.setVisible(false);
				statuspanel.setVisible(true);
				return Mode.STATUS;
			case 1:
				menupanel.setVisible(false);
				//botpanel.setVisible(true);    //delete
				return Mode.BOT;
			case 2:
				menupanel.setVisible(false);
				//itempanel.setVisible(true);     //delete
				return Mode.ITEM;
			case 3:
				menupanel.setVisible(false);
				equippanel.setVisible(true);
				return Mode.EQUIP;
			case 4:
				menupanel.setVisible(false);
				mappanel.setVisible(true);
				return Mode.MAP;
			case 5:
				//save
				System.out.println("Save");
				return mode;
			case 6:
				setVisible(false);
				return Mode.FIELD;
		}
		return Mode.FIELD;
	}

	public MenuPanel getmenupanel() {return menupanel;}
	public StatusPanel getstatuspanel() {return statuspanel;}
	public BotPanel getbotpanel() {return botpanel;}
	public ItemPanel getitempanel() {return itempanel;}
	public EquipPanel getequippanel() {return equippanel;}
	public MapPanel getmappanel() {return mappanel;}
}
class MenuPanel extends JPanel
{
	private Image image;
	private double width;
	private double height;
	private SelectLabel select_l;
	public MenuPanel(double width,double height)
	{
		BagLabel status_l=new BagLabel(width,height," Status",0);
		BagLabel bot_l=new BagLabel(width,height,"<html>&nbsp;&nbsp;Bot</html>",1);
		BagLabel item_l=new BagLabel(width,height,"<html>&nbsp;&nbsp;Item</html>",2);
		BagLabel equip_l=new BagLabel(width,height," Equip",3);
		BagLabel map_l=new BagLabel(width,height,"<html>&nbsp;&nbsp;Map</html>",4);
		BagLabel save_l=new BagLabel(width,height,"<html>&nbsp;&nbsp;Save</html>",5);
		BagLabel exit_l=new BagLabel(width,height,"<html>&nbsp;&nbsp;Exit</html>",6);

		select_l=new SelectLabel(width,height);

		setLayout(null);

		add(status_l);
		add(bot_l);
		add(item_l);
		add(equip_l);
		add(map_l);
		add(save_l);
		add(exit_l);
		add(select_l);
		
		this.width=width;
		this.height=height;
		setBounds(0,0,(int)(width*0.215),(int)height);
		image=this.getToolkit().getImage("image/bag/menu.png");
	}
	public SelectLabel getselectlabel()
	{
		return select_l;
	}
	public void paintComponent(Graphics g) {
    g.drawImage(image,0, 0, (int)(width), (int)height,null);
  }	
}
class BagLabel extends JLabel
{
	public BagLabel(double width,double height,String name,int order)
	{
		setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		setText(""+name);
		setFont(new Font("AR CENA", Font.BOLD, 65));
		setBounds((int)0, (int)(order*height/7), (int)width,(int)height/7);
	}
}
class SelectLabel extends JLabel
{
	ImageIcon image;
	private int interval;
	private double width;
	private double height;
	public SelectLabel(double width,double height)
	{
		image=new ImageIcon("image/bag/select.png");
   		setIcon(image); 
		setBounds(0,0, (int)width,(int)height/7);
		this.width=width;
		this.height=height;
		interval=(int)(height/7);
	}
	public void move(boolean down)
	{
		if(down)
		{
			if(interval==(int)(height/7)*13)
				interval=(int)(height/7);
			else
				interval+=(int)(height/7)*2;
		}
		else
		{
			if(interval==(int)(height/7))
				interval=(int)(height/7)*13;
			else
				interval-=(int)(height/7)*2;
		}
		setBounds(0,0, (int)width,interval);
	}

}