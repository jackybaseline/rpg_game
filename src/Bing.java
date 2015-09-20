
import java.io.*;

import java.awt.*;

import javax.swing.*;
import javax.imageio.*;

public class Bing extends RBot {
	public Bing() {
		try {
			image[0] = ImageIO.read(new File("image/bot/Bing/Front.png"));
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		width[0] = 130;
		height[0] = 132;
		try {
			image[1] = ImageIO.read(new File("image/bot/Bing/Back.png"));
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		width[0] = 130;
		height[0] = 132;

		attribute = Attribute.BIOTIC;
		attack = 10;
		defense = 10;
		health_max = 80;
		health = 20;
		power = 10;
		power_max = 40;
		agility = 70;

		number_of_skills = 4;
		skill = new RSkill[number_of_skills];
		skill[0] = new TinySkill();
	}
}