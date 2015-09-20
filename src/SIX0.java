import java.io.*;

import java.awt.*;

import javax.swing.*;
import javax.imageio.*;

public class SIX0 extends RBot {
	public SIX0() {
		try {
			image[0] = ImageIO.read(new File("image/bot/SIX0/Front.png"));
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		width[0] = 130;
		height[0] = 132;
		try {
			image[1] = ImageIO.read(new File("image/bot/SIX0/Back.png"));
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		width[0] = 130;
		height[0] = 132;

		attribute = Attribute.ELECTRONIC;
		attack = 40;
		defense = 10;
		health_max = 70;
		health = 70;
		power = 40;
		power_max = 40;
		agility = 40;

		number_of_skills = 4;
		skill = new RSkill[number_of_skills];
		skill[0] = new TinySkill();
	}
}