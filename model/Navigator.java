package model;

public class Navigator extends Assignments {

	public Navigator(int[] costs) {
		super(1, costs);
	}

	/**
	 * navigator move the boat forward or backward with 1 step.
	 */
	public void moveBoat(Boat boat, int step) {
		boat.move(step);
	}

	/**
	 * public void choose(int) this function use before roll the dice.The navigation
	 * choose whether to move the boat or not. If get 1, means move,return
	 * 1;otherwise menas not,return 0.
	 */
	public boolean choose(int c) {
		if (c == 1) {
			return true;
		} else
			return false;
	}

}
