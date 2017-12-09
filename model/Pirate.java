package model;

public class Pirate extends Assignments {

	public Pirate(int[] costs) {
		super(1, costs);
	}

	/**
	 * when the positionInSea of boat is 13, the pirate can go on board
	 */
	public void takeAction(int currentRound, Boat boat) {

		if (currentRound < 2) {
			for (Employee b : employees) {
				boolean success = false;
				for (int i = 0; i < boat.getEmployees().length; i++) {
					// if the boat has at least one empty seat, go board.
					if (boat.getEmployees()[i] == null) {
						boat.getEmployees()[i] = b;
						success = true;
						break;
					}
				}
				if (!success)
					break;
			}
		} else if (currentRound == 2) {
			// Kick off all persons on boat and board.
			Employee[] e = { this.employees[0], this.employees[0], this.employees[0] };
			boat.setEmployees(e);
		}
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("[Pirate]\n");
		for (Employee i : employees) {
			if (i != null)
				s.append("    employee of: " + employees[0].getPlayer().getId() + "\n");
		}

		s.append("    cost: " + costs[0] + "\n");
		return s.toString();
	}
}
