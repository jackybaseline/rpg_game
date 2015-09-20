import java.io.*;

import java.awt.*;

import javax.swing.*;
import javax.imageio.*;

public class Naivel extends RBot {
	public Naivel() {
		try {
			image[0] = ImageIO.read(new File("image/bot/Naivel/Front.png"));
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		width[0] = 130;
		height[0] = 132;
		try {
			image[1] = ImageIO.read(new File("image/bot/Naivel/Back.png"));
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		width[0] = 130;
		height[0] = 132;

		attribute = Attribute.ELECTRONIC;
		attack = 20;
		defense = 20;
		health_max = 100;
		health = 100;
		power = 80;
		power_max = 80;
		agility = 50;

		number_of_skills = 4;
		skill = new RSkill[number_of_skills];
		skill[0] = new TinySkill();
	}
}