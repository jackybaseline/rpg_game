import java.awt.*;
import javax.swing.*;
enum Item
{
    HP("hp regen"), PP("pp regen"), CURE("cure alleffect"), LEVEL("leve up"),
    POISON("cure poison"), BURN("cure burn"), FREEZE("cure freeze"), PARALYSIS("cure paralysis"), SLEEP("cure sleep");
    private String text;
	Item(String text) {
	    this.text = text;
	  }
	public String getText() {
	    return this.text;
	  }

} 
public abstract class RItem
{
	protected String name;
	protected Item kind;
	protected int hp_regen;
	protected int pp_regen;
	protected int count;
	protected String description;
	public RItem(String name,String description,Item kind,int hp_regen,int pp_regen)
	{
		this.name=name;
		this.description=description;
		this.kind=kind;
		this.hp_regen=hp_regen;
		this.pp_regen=pp_regen;
		count=1;
	}
	public abstract int use(RBot object);
	public int getcount()
	{
		return count;
	}
	public int gethp_regen()
	{
		return hp_regen;
	}
	public int getpp_regen()
	{
		return pp_regen;
	}
	public String getname()
	{
		return name;
	}
	public String getdescription()
	{
		return description;
	}
	public Item getkind()
	{
		return kind;
	}
	public void addcount()
	{
		++count;
	}
	public boolean usecount()
	{
		--count;
		if(count==0)
			return false;
		return true;
	}
}
class HPItem extends RItem
{
	public HPItem(String name,String description,int hp_regen)
	{
		super(name,description,Item.HP,hp_regen,0);
	}
	public int use(RBot object)
	{
		if(object.getHealth()<=0)
			return 0;
		else if(object.getHealth()==object.getHealthMax()&&hp_regen>0)
			return -1;
		object.setHealth(object.getHealth()+hp_regen);
		return 1;
	}
}
class PPItem extends RItem
{
	public PPItem(String name,String description,int pp_regen)
	{
		super(name,description,Item.PP,0,pp_regen);
	}
	public int use(RBot object)
	{
		if(object.getPower()==object.getPowerMax()&&pp_regen>0)
			return -1;
		object.setPower(object.getPower()+pp_regen);
		return 1;
	}
}

class RItemtitleLabel extends JLabel
{
	public RItemtitleLabel(double width,double height)
	{	
		String str1="Name";
		String str2="Kind";
		String str3="Num";
		String str4="regen";
		setText(String.format("%-12s %-14s %3s  %5s",str1,str2,str3,str4));
		setFont(new Font("AR CENA", Font.BOLD, 30));
		setBounds((int)(width*0.04),(int)(height*0.18),(int)(width*0.55),(int)(height*0.07));
	}
}
class RItemLabel extends JLabel
{
	public RItemLabel(RItem item,double width,double height,int order)
	{
		switch(item.getkind())
		{	
			case HP:
				setText(String.format("%-12s %-14s %3d   %4d",item.getname(),item.getkind().getText(),item.getcount(),item.gethp_regen()));
				break;
			case PP:
				setText(String.format("%-12s %-14s %3d   %4d",item.getname(),item.getkind().getText(),item.getcount(),item.getpp_regen()));
				break;
			default:
				setText(String.format("%-12s %-14s %4d",item.getname(),item.getkind().getText(),item.getcount()));
				break;
		}
		setFont(new Font("AR CENA", Font.BOLD, 30));
		if(order%8==0)	
			setBounds((int)(width*0.04),(int)(height*0.25),(int)(width*0.55),(int)(height*0.07));
		else
			setBounds((int)(width*0.04),(int)(height*0.25)+(order%8)*(int)(height*0.07),(int)(width*0.55),(int)(height*0.07));
	}
	public void changecount(RItem item)
	{
		switch(item.getkind())
		{	
			case HP:
				setText(String.format("%-12s %-14s %3d   %4d",item.getname(),item.getkind().getText(),item.getcount(),item.gethp_regen()));
				break;
			case PP:
				setText(String.format("%-12s %-14s %3d   %4d",item.getname(),item.getkind().getText(),item.getcount(),item.getpp_regen()));
				break;
			default:
				setText(String.format("%-12s %-14s %4d",item.getname(),item.getkind().getText(),item.getcount()));
				break;
		}
	}
}

class RItemdescriptionLabel extends JLabel
{
	private double width;
	private double height;
	public RItemdescriptionLabel(RItem item,double width,double height)
	{
		this.width=width;
		this.height=height;
		setBounds((int)(width*0.04),(int)(height*0.25)+8*(int)(height*0.07),(int)(width),(int)(height*0.07));
		setText(item.getdescription());
		setForeground(Color.blue);
		setFont(new Font("AR CENA", Font.BOLD, 30));
	}
}

class RItemselectLabel extends JLabel
{
	Image image;
	private int interval;
	private double width;
	private double height;
	public RItemselectLabel(double width,double height)
	{
		this.width=width;
		this.height=height;
		setBounds((int)(width*0.04),(int)(height*0.25),(int)(width*0.55),(int)(height*0.07));
		image=getToolkit().getImage("image/bag/selectitem.png");
		interval=(int)(height*0.25);
	}
	public void paintComponent(Graphics g) 
	{
    	g.drawImage(image,0, 0, 700, 110,null);
  	}
	public void move(int order)
	{
		if(order%8==0)	
			setBounds((int)(width*0.04),(int)(height*0.25),(int)(width*0.55),(int)(height*0.07));
		else
			setBounds((int)(width*0.04),(int)(height*0.25)+(order%8)*(int)(height*0.07),(int)(width*0.55),(int)(height*0.07));
	}
}

