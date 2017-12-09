package model;

public class Insurer extends Assignments {

	public static final int feedback = 10;

	public Insurer() {
		super(1, new int[] { -feedback, -feedback, -feedback });
	}

	/**
	 * the insurer must pay money if the fixfactory has boat. factory will take
	 * response to report how much to pay for. Notice this guy may pay money for
	 * *all* sink boats...
	 */
	public void payToFactory(int moneyToPay) {
		/*
		 * if(factory == null) return; for (int i = 0; i <
		 * factory.getEmployees().length; i++) { if (factory.getEmployees()[i] != null)
		 * { this.employee.getPlayer().payMoney(factory.getProfits()[i]); } }
		 */
		if (super.countEmployee() != 0) {
			int moneyToPayEach = moneyToPay / super.countEmployee();
			for (Employee i : employees) {
				System.out.println("Player " + i.getPlayer().getId() + " pay " + moneyToPayEach + " to insurer.");
				/*
				 * Game.game.textArea.setText(Game.game.textArea.getText() + "\nPlayer " +
				 * i.getPlayer().getId() + " pay " + moneyToPayEach + " to insurer.")
				 */;
				i.getPlayer().payMoney(moneyToPayEach);
			}
		}
	}

}
