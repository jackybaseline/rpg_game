import java.io.*;

import java.awt.*;

import javax.swing.*;
import javax.imageio.*;

public class MiniDragon extends RBot {
	public MiniDragon() {
		try {
			image[0] = ImageIO.read(new File("image/bot/MiniDragon/Front.png"));
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		width[0] = 130;
		height[0] = 132;
		try {
			image[1] = ImageIO.read(new File("image/bot/MiniDragon/Back.png"));
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		width[0] = 130;
		height[0] = 132;

		attribute = Attribute.ELECTRONIC;
		attack = 20;
		defense = 10;
		health_max = 70;
		health = 70;
		power = 30;
		power_max = 30;
		agility = 70;

		number_of_skills = 4;
		skill = new RSkill[number_of_skills];
		skill[0] = new TinySkill();
	}
}