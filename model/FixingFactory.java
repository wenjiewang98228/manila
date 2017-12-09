package model;

public class FixingFactory extends Assignments {
	private int[] profits;
	private Insurer bindedInsurer;

	public FixingFactory(int[] costs, int[] profits, Insurer insurer) {
		super(3, costs);
		this.profits = profits;
		this.bindedInsurer = insurer;
	}

	/*
	 * to tell an insurer when factory gets sink boat it shall pay.
	 */
	public void bindInsurer(Insurer insurer) {
		bindedInsurer = insurer;
	}

	/*
	 * take active when a boat sinks.
	 */
	public void takeSinkBoat() {
		// employees work for factory take money.
		int cost = 0;
		for (int i = 0; i < employees.length; i++) {
			if (employees[i] != null) {
				System.out.println(
						"Player " + employees[i].getPlayer().getId() + " gain " + profits[i] + " from factory.");
				/*
				 * Game.game.textArea.setText(Game.game.textArea.getText() + "\nPlayer " +
				 * employees[i].getPlayer().getId() + " gain " + profits[i] + " from factory.");
				 */
				employees[i].getPlayer().gainMoney(profits[i]);
				cost += profits[i];
				// take my money and get out.
				employees[i] = null;
				break;
			}
		}
		// insurer pay the money...
		bindedInsurer.payToFactory(cost);
	}

	public int[] getProfits() {
		return profits;
	}

	public void setProfits(int[] profits) {
		this.profits = profits;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("[Factory]\n");
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
