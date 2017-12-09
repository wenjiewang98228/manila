package model;

import java.util.List;

import java.util.*;

public class Player {

	private int id;
	private int money;

	private Employee[] employees = new Employee[4];
	private List<Stock> stocks = new LinkedList<Stock>();

	public void SetEmployee(int count) {
		for (int i = 0; i < count; i++) {
			employees[i] = new Employee(this);
		}
	}

	public int countEmployee() {
		int v = 0;
		for (Employee i : employees) {
			if (i != null)
				v++;
		}
		return v;
	}

	public void gainMoney(int profit) {
		this.money += profit;
	}

	public void payMoney(int cost) {
		setMoney(this.money - cost);
	}

	/* getters */
	public int getId() {
		return this.id;
	}

	public int getMoney() {
		return this.money;
	}

	public List<Stock> getStock() {
		return this.stocks;
	}

	public String getStockName() {
		String str = "";
		for (Stock s : stocks) {
			str += s.getGoods().getName() + " ";
		}
		return str;
	}

	public void addStock(Stock x) {
		this.stocks.add(x);
	}

	public void removeStock(Stock x) {
		this.stocks.remove(x);
	}

	/*
	 * assign an employee for this player.
	 */
	public Employee pullEmployee() {
		for (int i = 0; i < employees.length; i++) {
			if (employees[i] != null) {
				Employee g = employees[i];
				return g;
			}
		}
		return null;
	}

	public boolean removeEmployee(Employee e) {
		for (int i = 0; i < employees.length; i++) {
			if (e == employees[i]) {
				employees[i] = null;
			}
		}
		return false;
	}

	/* setters */
	public void setId(int id) {
		this.id = id;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void runForCaptain() {

	}

	public void sellStock(List<Stock> stock) {
		for (int i = 0; i < stock.size(); i++) {
			setMoney(this.money + stock.get(i).getValue());
		}
	}

	public int calculateProfit() {
		int sum = 0;
		for (int i = 0; i < stocks.size(); i++) {
			sum += stocks.get(i).getValue();
		}

		return sum += money;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("[Player]\n");
		s.append("    id: " + id + "\n");
		s.append("    money: " + money + "\n");
		s.append("    stocks: " + "\n");
		for (Stock i : this.stocks)
			if (i != null)
				s.append("        name: " + i.getGoods().getName() + " value: " + i.getValue() + ")\n");
		return s.toString();
	}
}
