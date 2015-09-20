import java.awt.*;
import javax.swing.*;

enum Attribute {MACHINARY, ELECTRONIC, BIOTIC}
public abstract class RBot {
	private static final int MAX_SKILL_NUMBER = 4;

	/** [0] is front */
	public Image[] image = new Image[2];
	public double[] width = new double[2];
	public double[] height = new double[2];

	protected Attribute attribute;
	protected int attack;
	protected int special_attack;
	protected int defense;
	protected int special_defense;
	protected int health;
	protected int health_max;
	protected int power;
	protected int power_max;
	protected int agility;
	
	protected int level;

	protected int number_of_skills;
	protected RSkill[] skill;
	
	public Image getImage(int which) {return image[which];}
	public double getWidth(int which) {return width[which];}
	public double getHeight(int which) {return height[which];}
	
	public int getfrontsizex() {return (int)width[0];}
	public int getfrontsizey() {return (int)height[0];}
	public int getbacksizex() {return (int)width[1];}
	public int getbacksizey() {return (int)height[1];}
	
	public Attribute getAttribute() {return attribute;}
	public int getAttack() {return attack;}
	public int getSpecialAttack() {return special_attack;}
	public int getDefense() {return defense;}
	public int getSpecialDefense() {return special_defense;}
	public int getHealth() {return health;}
	public int getHealthMax() {return health_max;}
	public int getPower() {return power;}
	public int getPowerMax() {return power_max;}
	public int getAgility() {return agility;}
	
	public void setAttribute(Attribute attribute) {this.attribute = attribute;}
	public void setAttack(int attack) {this.attack = attack;}
	public void setSpecialAttack(int special_attack) {this.special_attack = special_attack;}
	public void setDefense(int defense) {this.defense = defense;}
	public void setSpeicalDefense(int special_defense) {this.special_defense = special_defense;}
	public void setHealth(int health) {
		if (health >= 0)
			this.health = health;
		if (health > health_max)
			this.health = health_max;
		if(health <0)
			this.health=0;
	}
	public void setPower(int power) {
		if (power >= 0)
			this.power = power;
		if (power > power_max)
			this.power = power_max;
		if(power<0)
			this.power=0;
	}
	public void setRate(int agility) {this.agility = agility;}
}