package model;

public class Employee {

	private Player player;

	public Employee(Player p) {
		player = p;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}
