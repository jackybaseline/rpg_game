
import java.awt.*;
import javax.swing.*;
public class BagselectPanel extends JPanel 
{
	protected Image image;
	protected double width;
	protected double height;
	public BagselectPanel( double width, double height,String name) 
	{
		this.width=width;
		this.height=height;
		setBounds((int)(width*0.1),0, (int)width, (int)height);
		setLayout(null);
		BagtitleLabel title_l=new BagtitleLabel(width,height,name);
		add(title_l);

		image=this.getToolkit().getImage("image/bag/selectpanel.png");
		setVisible(false);

	}
	public void paintComponent(Graphics g) 
	{
    	g.drawImage(image,0, 0, (int)width, (int)height,null);
  	}
}
class StatusPanel extends BagselectPanel
{
	private BagcontentLabel timelabel;
	public StatusPanel(double width, double height)
	{
		super(width,height," Status");
		MasterimageLabel masterimagelabel=new MasterimageLabel(width,height);
		add(masterimagelabel);
		BagcontentLabel bagcontentlabel=new BagcontentLabel(0.2,0.55,width,height,"Name: Fatty");
		add(bagcontentlabel);
		String str="Game time: ";	
		timelabel=new BagcontentLabel(0.2,0.70,width,height,str);
		add(timelabel);
		timelabel=new BagcontentLabel(0.2,0.75,width,height,str);
		add(timelabel);
	}
	public void setmaster(MasterPlayer master)
	{
		String str="Have Bot: ";
		str+=String.valueOf(master.getbot().length);
		BagcontentLabel bagcontentlabel=new BagcontentLabel(0.2,0.60,width,height,str);
		add(bagcontentlabel);
		str="Have Item: ";
		str+=String.valueOf(master.getitem().length);
		bagcontentlabel=new BagcontentLabel(0.2,0.65,width,height,str);
		add(bagcontentlabel);
		str="Game time: ";
		long time=(System.currentTimeMillis()-master.initialtime);
	 	int ms = (int)( time% 1000);
	 	time /= 1000;
     	int sc = (int)(time % 60);  
     	time /= 60;
     	int mn = (int)(time% 60);  
     	time/= 60;
     	int hr = (int)time;
		timelabel.setText(String.format("%3d:%2d:%2d",hr,mn,sc));
	}

}
class BotPanel extends BagselectPanel
{
	private OneBotPanel[]  onebotpanel=new OneBotPanel[0];
	private int bot_max;
	private int bot_now;
	public BotPanel(double width, double height)
	{
		super(width,height,"<html>&nbsp;&nbsp;Bot</html>");
		ArrowLabel leftlabel=new ArrowLabel((width*0.02),(int)(height*0.5),width,height,true);
		ArrowLabel rightlabel=new ArrowLabel((width*0.9),(height*0.5),width,height,false);
		add(leftlabel);
		add(rightlabel);
	}
	public void updatebot(MasterPlayer master)
	{
		onebotpanel=new OneBotPanel[master.getbot().length];
		bot_max=master.getbot().length;
		for(int i=0;i<master.getbot().length;++i)
			add(onebotpanel[i]=new OneBotPanel(master.getbot()[i]));
		onebotpanel[0].setVisible(true);
	}
	public void removeallbot(MasterPlayer master)
	{
		for(int i=0;i<master.getbot().length;++i)
			remove(onebotpanel[i]);
	}
	public void setfirstbot(MasterPlayer master)
	{
		for(int i=0;i<master.getbot().length;++i)
		{
			if(i==bot_now)
			{
				RBot temp=master.getbot()[0];
				master.getbot()[0]=master.getbot()[i];
				master.getbot()[i]=temp;
			}
		}
		removeallbot(master);
		updatebot(master);
	}
	public void changebot(boolean right)
	{
		onebotpanel[bot_now].setVisible(false);
		if(right)
		{
			++bot_now;
			if(bot_now==bot_max)
				bot_now=0;
		}
		else
		{
			--bot_now;
			if(bot_now==-1)
				bot_now=bot_max-1;	
		}
		onebotpanel[bot_now].setVisible(true);
	}
	private class OneBotPanel extends JPanel 
	{
		public OneBotPanel(RBot bot)
		{
			setBounds((int)(width*0.1),(int)(height*0.2), (int)(width*0.8), (int)(height*0.7));
			setLayout(null);
			setOpaque(false);
			RBotimageLabel frontimagelabel=new RBotimageLabel(bot.getfrontsizex(),bot.getfrontsizey(),0,0,width*0.25,height*0.43,bot);
			add(frontimagelabel);

			StatPanel statpanel=new StatPanel((int)(width*0.3),(int)(height*0.1),(int)(width*0.4),(int)(height*0.2),bot);
			add(statpanel);
			BagcontentLabel bagcontentlabel=new BagcontentLabel(0,0.45,(int)(width*0.5), (int)(height*0.7),"Attack: "+bot.getAttack());
			add(bagcontentlabel);
			bagcontentlabel=new BagcontentLabel(0.6,0.45,(int)(width*0.7), (int)(height*0.7),"Special_Attack: "+bot.getSpecialAttack() );
			add(bagcontentlabel);
			bagcontentlabel=new BagcontentLabel(0,0.55,(int)(width*0.6), (int)(height*0.7),"Defense: "+bot.getDefense() );
			add(bagcontentlabel);
			bagcontentlabel=new BagcontentLabel(0.6,0.55,(int)(width*0.7), (int)(height*0.7),"Special_Defense: "+bot.getSpecialDefense() );
			add(bagcontentlabel);
			bagcontentlabel=new BagcontentLabel(0,0.65,(int)(width*0.6), (int)(height*0.7),"Agility: "+bot.getAgility() );
			add(bagcontentlabel);
			switch(bot.getAttribute())
			{
				case MACHINARY:
					bagcontentlabel=new BagcontentLabel(0.6,0.65,(int)(width*0.7), (int)(height*0.7),"Attribute: MACHINARY");
					break;
				case ELECTRONIC:
					bagcontentlabel=new BagcontentLabel(0.6,0.65,(int)(width*0.7), (int)(height*0.7),"Attribute: ELECTRONIC");
					break;
				case BIOTIC:
					bagcontentlabel=new BagcontentLabel(0.6,0.65,(int)(width*0.7), (int)(height*0.7),"Attribute: BIOTIC");
					break;
			}
			add(bagcontentlabel);		
			setVisible(false);
		}
	}
}
class ItemPanel extends BagselectPanel
{
	private OneBotPanel[]  onebotpanel=new OneBotPanel[0];
	private RItemLabel[] itemlabel=new RItemLabel[0];
	private RItemdescriptionLabel itemdescriptionlabel;
	private RItemselectLabel itemselectlabel;
	private StatPanel statpanel;

	private int item_max;
	private int item_now;
	private int bot_max;
	private int bot_now;
	private double width;
	private double height;
	public ItemPanel(double width, double height)
	{
		super(width,height,"<html>&nbsp;&nbsp;Item</html>");
		this.width=width;
		this.height=height;
		ArrowLabel leftlabel=new ArrowLabel((int)(width*0.6),(int)(height*0.5),width,height,true);
		ArrowLabel rightlabel=new ArrowLabel((int)(width*0.9),(int)(height*0.5),width,height,false);
		add(leftlabel);
		add(rightlabel);
		RItemtitleLabel itemtitleLabel=new RItemtitleLabel(width,height);
		add(itemtitleLabel);
	}
	public void updatebot(MasterPlayer master)
	{
		onebotpanel=new OneBotPanel[master.getbot().length];
		bot_max=master.getbot().length;
		for(int i=0;i<master.getbot().length;++i)
			add(onebotpanel[i]=new OneBotPanel(master.getbot()[i]));
		onebotpanel[0].setVisible(true);
	}

	public void removeallbot(MasterPlayer master)
	{
		for(int i=0;i<master.getbot().length;++i)
		{
			statpanel.terminate();
			remove(onebotpanel[i]);
		}
	}
	public void changebot(boolean right)
	{
		onebotpanel[bot_now].setVisible(false);
		if(right)
		{
			++bot_now;
			if(bot_now==bot_max)
				bot_now=0;
		}
		else
		{
			--bot_now;
			if(bot_now==-1)
				bot_now=bot_max-1;	
		}
		onebotpanel[bot_now].setVisible(true);
	}
	private class OneBotPanel extends JPanel 
	{
		public OneBotPanel(RBot bot)
		{
			setBounds((int)(width*0.6),(int)(height*0.2), (int)(width*0.4), (int)(height*0.7));
			setLayout(null);
			setOpaque(false);
			RBotimageLabel frontimagelabel=new RBotimageLabel(bot.getfrontsizex(),bot.getfrontsizey(),width*0.1,0,width*0.3,height*0.5,bot);
			add(frontimagelabel);
			statpanel=new StatPanel((int)(width*0.1),(int)(height*0.4),(int)(width*0.25),(int)(height*0.15));
			
			statpanel.initial(bot);
			add(statpanel);
			setVisible(false);
		}
	}

	public void updateitem(MasterPlayer master)
	{
		if (master.item.length == 0)
			return;
			
		itemlabel=new RItemLabel[master.getitem().length];
		item_max=master.getitem().length;
		item_now=0;
		for(int i=0;i<master.getitem().length;++i)
			add(itemlabel[i]=new RItemLabel(master.getitem()[i],width,height,i));
		if(master.getitem().length>8)
		{
			for(int i=8;i<master.getitem().length;++i)
				itemlabel[i].setVisible(false);
		}
		itemdescriptionlabel=new RItemdescriptionLabel(master.getitem()[0],width,height);
		add(itemdescriptionlabel);
		itemselectlabel=new RItemselectLabel(width,height);
		add(itemselectlabel);
	}
	public void removeallitem(MasterPlayer master)
	{
		if (master.item.length == 0)
			return;
			
		for(int i=0;i<master.getitem().length;++i)
			remove(itemlabel[i]);
		remove(itemselectlabel);
		remove(itemdescriptionlabel);
	}

	public void changeitem(MasterPlayer master,boolean down)
	{
		if (master.item.length == 0)
			return;
			
		if(down)
		{
			if(item_now==item_max-1)
			{
				for(int i=item_max-1;i>item_max-9;--i)
				{
					if(i%8==7&&i!=item_max-1)
						break;
					else if(i<0)
						break;
					itemlabel[i].setVisible(false);
				}
				for(int i=0;i<8;++i)
				{
					if(i==item_max)
						break;
					itemlabel[i].setVisible(true);
				}
			}
			else if(item_now%8==7)
			{
				for(int i=item_now;i>item_now-9;--i)
				{
					if(i<0)
						break;	
					itemlabel[i].setVisible(false);
				}
				for(int i=item_now+1;i<item_now+9;++i)
				{
					if(i==item_max)
						break;
					itemlabel[i].setVisible(true);
				}
			}

			if(item_now==item_max-1)
				item_now=0;
			else
				++item_now;
		}
		else
		{

			if(item_now==0)
			{
				for(int i=item_now;i<item_now+8;++i)
				{
					if(i%8==0&&i!=0)
						break;
					else if(i==item_max)
						break;
					itemlabel[i].setVisible(false);
				}
				for(int i=item_max-1;i>item_max-9;--i)
				{
					if(i%8==7&&i!=item_max-1)
						break;
					else if(i==-1)
						break;
					itemlabel[i].setVisible(true);
				}
			}
			else if(item_now%8==0)
			{
				for(int i=item_now;i<item_now+8;++i)
				{
					if(i%8==0&&i!=item_now)
						break;
					else if(i==item_max)
						break;
					itemlabel[i].setVisible(false);
				}
				for(int i=item_now-1;i>item_now-9;--i)
					itemlabel[i].setVisible(true);
			}


			if(item_now==0)
				item_now=item_max-1;
			else
				--item_now;
		}
		itemselectlabel.move(item_now);
		if(master.getitem().length==0)
			return ;
		itemdescriptionlabel.setText(master.getitem()[item_now].getdescription());
	}
	public void useitem(MasterPlayer master)
	{
		if (master.item.length == 0)
			return;
			
		switch(master.getitem()[item_now].use(master.getbot()[bot_now]))
		{
			case 0:  //already dead
				return ;
			case -1:     //full blood
				return ;

		}
		if(!master.getitem()[item_now].usecount())
		{
			removeallitem(master);
			master.removeitem(item_now);
			updateitem(master);
			repaint();
			// add bot's setHP  &&refresh
		}
		else
			itemlabel[item_now].changecount(master.getitem()[item_now]);
	}
}
class EquipPanel extends BagselectPanel
{
	public EquipPanel(double width, double height)
	{
		super(width,height," Equip");
	}
}
class MapPanel extends BagselectPanel
{
	public MapPanel(double width, double height)
	{
		super(width,height,"<html>&nbsp;&nbsp;Map</html>");
	}
}

class BagtitleLabel extends JLabel
{
	public BagtitleLabel(double width,double height,String name)
	{
		setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		setText(""+name);
		setFont(new Font("AR CENA", Font.BOLD, 65));
		setForeground(Color.red );
		setBounds((int)(width*0.35), (int)(height*0.06), (int)width,(int)height/7);
	}
}

class BagcontentLabel extends JLabel
{
	public BagcontentLabel(double x,double y,double width,double height,String name)
	{
		setText(""+name);
		setFont(new Font("AR CENA", Font.BOLD, 30));
		setBounds((int)(width*x), (int)(height*y), (int)(width*0.5),(int)(height*0.1));
		setHorizontalAlignment(JLabel.CENTER);
	}
}

class ArrowLabel extends JLabel
{
	private Image image;
	public ArrowLabel(double x,double y,double width,double height,boolean left)
	{
		if(left)
		{
			image=getToolkit().getImage("image/bag/arrowleft.png");
			setBounds((int)x,(int)y,100,100);
		}
		else
		{
			image=getToolkit().getImage("image/bag/arrowright.png");
			setBounds((int)x,(int)y,100,100);
		}
	}
	public void paintComponent(Graphics g) 
	{
    	g.drawImage(image,0, 0, 100, 100,null);
  	}
}
class MasterimageLabel extends JLabel {
	Image image;
	public MasterimageLabel(double width,double height)
	{ 
		image=this.getToolkit().getImage("image/bag/Master.png");
		setBounds((int)(width*0.37),(int)(height*0.16),(int)(width*0.3),(int)(height*0.5));
	}
	public void paint(Graphics g)
	{
		g.drawImage(image,0,0,209,277,null);
	}
}