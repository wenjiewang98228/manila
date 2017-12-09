package model;

public abstract class Assignments {
	protected Employee[] employees;
	protected int[] costs;

	public Assignments() {
		employees = new Employee[0];
	}

	public Assignments(int count, int[] costs) {
		employees = new Employee[count];
		this.costs = costs;
	}

	public Assignments(Employee[] e) {
		employees = e.clone();
	}

	public int countEmployee() {
		int v = 0;
		for (int i = 0; i < employees.length; i++)
			if (employees[i] != null)
				v++;
		return v;
	}

	public Employee[] getEmployees() {
		return employees;
	}

	public void setEmployees(Employee[] e) {
		employees = e;
	}

	public boolean setEmployee(Employee e) {
		if (e == null)
			return false;
		for (int i = 0; i < employees.length; i++) {
			if (employees[i] == null) {
				employees[i] = e;
				e.getPlayer().payMoney(costs[i]);
				System.out.print("Player " + e.getPlayer().getId() + " pay " + costs[i] + " ");
				// Game.game.textArea.setText(Game.game.textArea.getText() + "\nPlayer " +
				// e.getPlayer().getId() + " pay " + costs[i] + " ");
				// System.out.print("for " + this.toString());
				System.out.println(" rest : " + e.getPlayer().getMoney());
				// Game.game.textArea.setText(Game.game.textArea.getText() + " rest : " +
				// e.getPlayer().getMoney());
				e.getPlayer().removeEmployee(e);
				return true;
			}
		}
		return false;
	}

	public void removeEmployee(Employee e) {
		for (int i = 0; i < employees.length; i++)
			if (employees[i] == e)
				employees[i] = null;
	}

	public int[] getCosts() {
		return costs;
	}

	public void clean() {
		for (int i = 0; i < employees.length; i++)
			employees[i] = null;
	}
}
