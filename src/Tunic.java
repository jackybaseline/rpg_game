import java.io.*;

import java.awt.*;

import javax.swing.*;
import javax.imageio.*;

public class Tunic extends RBot {
	public Tunic() {
		try {
			image[0] = ImageIO.read(new File("image/bot/Tunic/Front.png"));
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		width[0] = 130;
		height[0] = 132;
		try {
			image[1] = ImageIO.read(new File("image/bot/Tunic/Back.png"));
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		width[0] = 130;
		height[0] = 132;

		attribute = Attribute.MACHINARY;
		attack = 30;
		defense = 10;
		health_max = 70;
		health = 20;
		power = 10;
		power_max = 40;
		agility = 50;

		number_of_skills = 4;
		skill = new RSkill[number_of_skills];
		skill[0] = new TinySkill();
	}
}