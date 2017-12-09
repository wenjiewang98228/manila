package model;

public class Port extends Assignments {
	private final int[] profits;

	public Port(int[] costs, int[] profits) {
		super(3, costs);
		this.profits = profits;
	}

	public void setProfits(int[] p) {
		for (int i = 0; i < profits.length; i++)
			profits[i] = p[i];
	}

	/*
	 * the boat at posision arrived. give money to employee who take this position.
	 * give money to employees on the arrival boats.
	 * 
	 * @param position cooresponding port(cost/profit) pair for that boat.
	 */
	public void setBoatArrival(Boat boat, int position) {
		// pay money for workers on the boat.
		int profit = boat.caculateProfit();
		for (Employee i : boat.getEmployees())
			if (i != null) {
				System.out.println("Player " + i.getPlayer().getId() + " gain " + profit + " from boat cargo.");
				/*
				 * Game.game.textArea.setText(Game.game.textArea.getText() + "\nPlayer " +
				 * i.getPlayer().getId() + " gain " + profit + " from boat cargo.");
				 */
				i.getPlayer().gainMoney(profit);
			}

		// pay money to workers in the port.
		for (int i = 0; i < 3; i++) {
			if (employees[i] != null) {
				System.out
						.println("Player " + employees[i].getPlayer().getId() + " gain " + profits[i] + " from port.");
				/*
				 * Game.game.textArea.setText(Game.game.textArea.getText() + "\nPlayer " +
				 * employees[i].getPlayer().getId() + " gain " + profits[i] + " from port.");
				 */
				employees[i].getPlayer().gainMoney(profits[i]);
				employees[i] = null;
				break;
			}
		}
	}

	public int[] getCosts() {
		return costs;
	}

	public int[] getProfits() {
		return profits;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("[Port]\n");
		for (Employee i : employees)
			if (i != null)
				s.append("    employee: " + i.getPlayer().getId() + "\n");
		for (int i = 0; i < costs.length; i++)
			s.append("    cost at position " + i + " : " + costs[i] + "\n");
		for (int i = 0; i < profits.length; i++)
			s.append("    profit at position " + i + " : " + profits[i] + "\n");
		return s.toString();
	}

}
