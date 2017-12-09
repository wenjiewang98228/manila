package model;

public class Goods {

	private String goodsName;
	private int valueLevel;
	private int profit;
	private int costs[];
	private int[] valueTable;

	public Goods(String goodsName, int profit, int[] costs, int[] valueTable) {
		this.goodsName = goodsName;
		this.valueTable = valueTable;
		this.costs = costs;
		this.profit = profit;
		this.valueLevel = 1;
	}

	/* getters */
	public String getName() {
		return goodsName;
	}

	public int getProfit() {
		return profit;
	}

	public int getValue() {
		return valueTable[valueLevel];
	}

	public int[] getCosts() {
		return costs;
	}

	public void setValueLevel(int valueLevel) {
		this.valueLevel = valueLevel;
	}

	public void addValueLevel() {
		this.valueLevel += 1;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("[Goods]\n");
		s.append("    value level: " + valueLevel + ", value: " + getValue() + "\n");
		s.append("    transportion profit: " + profit + "\n");
		for (int i = 0; i < costs.length; i++) {
			s.append("    position " + i + " cost: " + costs[i] + "\n");
		}
		return s.toString();
	}

	public static Goods findWithName(Goods[] goods, String name) {
		for (Goods i : goods) {
			if (i == null)
				continue;
			if (i.getName().equals(name))
				return i;
		}
		return null;
	}
}
