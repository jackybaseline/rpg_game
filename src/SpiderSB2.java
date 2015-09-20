import java.io.*;

import java.awt.*;

import javax.swing.*;
import javax.imageio.*;

public class SpiderSB2 extends RBot {
	public SpiderSB2() {
		try {
			image[0] = ImageIO.read(new File("image/bot/SpiderSB2/Front.png"));
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		width[0] = 130;
		height[0] = 132;
		try {
			image[1] = ImageIO.read(new File("image/bot/SpiderSB2/Back.png"));
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		width[0] = 130;
		height[0] = 132;

		attribute = Attribute.ELECTRONIC;
		attack = 30;
		defense = 0;
		health_max = 70;
		health = 70;
		power = 50;
		power_max = 50;
		agility = 70;

		number_of_skills = 4;
		skill = new RSkill[number_of_skills];
		skill[0] = new TinySkill();
	}
}