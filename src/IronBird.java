import java.io.*;

import java.awt.*;

import javax.swing.*;
import javax.imageio.*;

public class IronBird extends RBot {
	public IronBird() {
		try {
			image[0] = ImageIO.read(new File("image/bot/IronBird/Front.png"));
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		width[0] = 130;
		height[0] = 132;
		try {
			image[1] = ImageIO.read(new File("image/bot/IronBird/Back.png"));
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		width[0] = 130;
		height[0] = 132;

		attribute = Attribute.ELECTRONIC;
		attack = 20;
		defense = 0;
		health_max = 60;
		health = 60;
		power = 50;
		power_max = 50;
		agility = 70;

		number_of_skills = 4;
		skill = new RSkill[number_of_skills];
		skill[0] = new TinySkill();
	}
}