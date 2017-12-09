package model;

import java.util.Random;

public class Dice {

	public int getNumber() {
		Random random = new Random();

		int num = random.nextInt(6) + 1;

		return num;
	}
}
