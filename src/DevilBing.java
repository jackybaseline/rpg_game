
import java.io.*;

import java.awt.*;

import javax.swing.*;
import javax.imageio.*;

public class DevilBing extends RBot {
	public DevilBing() {
		try {
			image[0] = ImageIO.read(new File("image/bot/DevilBing/Front.png"));
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		width[0] = 130;
		height[0] = 132;
		try {
			image[1] = ImageIO.read(new File("image/bot/DevilBing/Back.png"));
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		width[0] = 130;
		height[0] = 132;

		attribute = Attribute.BIOTIC;
		attack = 20;
		health_max = 80;
		health = 40;
		power = 10;
		power_max = 30;
		agility = 60;

		number_of_skills = 4;
		skill = new RSkill[number_of_skills];
		skill[0] = new TinySkill();
	}
}