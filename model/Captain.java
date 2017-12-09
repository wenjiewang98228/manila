package model;

public class Captain {

	private Player player;

	public Captain(Player p) {
		setPlayer(p);
	}

	public void buyStock(Stock s) {
		this.player.getStock().add(s);
		this.player.payMoney(s.getValue());
	}

	public void initPosition(Boat[] boats, int i, int j, int k) {
		boats[0].move(i);
		boats[1].move(j);
		boats[2].move(k);
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
