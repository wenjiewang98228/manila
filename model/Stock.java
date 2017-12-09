package model;

public class Stock {

	private Goods goods;
	private Player player;

	public Stock(Goods goods, Player player) {
		this.goods = goods;
		this.player = player;
	}

	public Goods getGoods() {
		return goods;
	}

	public int getValue() {
		return goods.getValue();
	}

	public Player getPlayer() {
		return player;
	}

}
