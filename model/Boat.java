package model;

public class Boat extends Assignments {
	private int positionInSea = 0;
	private Goods goods;

	public Boat() {
		super(3, null);
		goods = null;
	}

	/**
	 * if success,return true; if the boat full,return false.
	 */
	public boolean getOnBoard(Employee e) {
		return super.setEmployee(e);
	}

	/**
	 * use to move the boat,called by the Boat Boss (while init the
	 * position),navigator (be- fore the dice being roll) or the game.
	 */
	public void move(int n) {
		this.positionInSea += n;
	}

	/**
	 * this function use to init the boat's goods,called by the Boat Boss.
	 */
	public void loadGoods(Goods goods) {
		this.goods = goods;
		this.costs = goods.getCosts();
	}

	/**
	 * this function use while the boat get to the port. If no one is on boat,return
	 * 0.
	 */
	public int caculateProfit() {
		int value = this.goods.getProfit();
		int number = 0;
		for (int i = 0; i < employees.length; i++) {
			if (employees[i] != null) {
				number += 1;
			}
		}
		if (number == 0)
			return 0;
		else {
			int profit = value / number;
			return profit;
		}
	}

	public int getPositionInSea() {
		return positionInSea;
	}

	public void setPositionInSea(int positionInSea) {
		this.positionInSea = positionInSea;
	}

	public Goods getGoods() {
		return goods;
	}

	@Override
	public void clean() {
		super.clean();
		this.positionInSea = 0;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();

		s.append("[Boat]\n");
		s.append("    position in sea: " + positionInSea + "\n");

		if (costs == null) {
			s.append("    position cost not assigned.\n");
		} else {
			for (int i = 0; i < costs.length; i++)
				s.append("    position " + i + " cost: " + costs[i] + "\n");
		}

		if (goods != null) {
			s.append("    goods: " + goods.getName() + "\n");
		} else {
			s.append("    goods not assigned.\n");
		}
		return s.toString();
	}

}
