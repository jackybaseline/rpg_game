import java.io.*;

import java.awt.*;

import javax.swing.*;
import javax.imageio.*;

public class Doong extends RBot {
	public Doong() {
		try {
			image[0] = ImageIO.read(new File("image/bot/Doong/Front.png"));
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		width[0] = 130;
		height[0] = 132;
		try {
			image[1] = ImageIO.read(new File("image/bot/Doong/Back.png"));
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		width[0] = 130;
		height[0] = 132;

		attribute = Attribute.ELECTRONIC;
		attack = 30;
		defense = 0;
		health_max = 100;
		health = 100;
		power = 20;
		power_max = 20;
		agility = 40;

		number_of_skills = 4;
		skill = new RSkill[number_of_skills];
		skill[0] = new TinySkill();
	}
}