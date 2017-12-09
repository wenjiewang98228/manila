package controller;

import java.util.*;
import model.*;

public class Scene {
	public final int[] portCost = { 2, 3, 4 };
	public final int[] portProfit = { 6, 8, 15 };

	public final int silkProfit = 18;
	public final int[] silkCost = { 1, 2, 3 };

	public final int nutProfit = 24;
	public final int[] nutCost = { 2, 3, 4 };

	public final int jadeProfit = 30;
	public final int[] jadeCost = { 3, 4, 5 };

	public final int cocoaProfit = 36;
	public final int[] cocoaCost = { 4, 5, 6, 7 };

	public final int[] shipyardCost = { 2, 3, 4 };
	public final int[] shipyardProfit = { 6, 8, 15 };

	public final int[] navigatorCost = { 2, 5 };
	public final int[] pirateCost = { 5, 5 };

	public final int[] value = { 0, 5, 10, 20, 30 };

	public final int playerCount = 3;
	public final int playerEmployeeCount = 4;
	public final int boatsCount = 3;
	public final int boatsPosCount = 3;
	public final int roundCount = 3;
	public final int arrivalPosition = 14;
	public final int initStockCount = 2;
	public final int initialMoney = 30;
	public final int endGameStockValue = 30;

	public final Dice dice = new Dice();

	public Insurer insurer;
	public Navigator navigator;
	public Pirate pirate;
	public Port port;
	public FixingFactory factory;
	public Captain cap;

	public final Boat[] boats = new Boat[3];
	public final Player[] players = new Player[3];
	public final Goods[] goods = new Goods[4];

	public int ReadInt(Scanner sc, String s) {
		System.out.print(s);
		return sc.nextInt();
	}

	public void Init() {
		Random rand = new Random();

		// Setup containment...
		goods[0] = new Goods("Silk", silkProfit, silkCost, value);
		goods[1] = new Goods("Nut", nutProfit, nutCost, value);
		goods[2] = new Goods("Jade", jadeProfit, jadeCost, value);
		goods[3] = new Goods("Cocoa", cocoaProfit, cocoaCost, value);

		// players...
		for (int i = 0; i < playerCount; i++) {
			players[i] = new Player();
			players[i].setId(i);
			players[i].setMoney(initialMoney);
		}

		// initially, give players 2 stocks each.
		for (int i = 0; i < playerCount; i++) {
			for (int j = 0; j < initStockCount; j++) {
				int id = rand.nextInt(goods.length);
				players[i].addStock(new Stock(goods[id], players[i]));
			}
		}

		// boats...
		for (int i = 0; i < boatsCount; i++) {
			boats[i] = new Boat();
		}

		// Insurer...
		insurer = new Insurer();

		// Factory...
		factory = new FixingFactory(shipyardCost, shipyardProfit, insurer);

		// Ports...
		port = new Port(portCost, portProfit);

		// Pirate...
		pirate = new Pirate(pirateCost);

		// Navigator...
		navigator = new Navigator(navigatorCost);

		// SceneTools.DisplayInfo(this);
	}

	/*
	 * Set game to a large round's beginning...
	 */
	public void Reset() {
		insurer.clean();
		navigator.clean();
		pirate.clean();
		port.clean();
		factory.clean();
		for (int i = 0; i < playerCount; i++)
			players[i].SetEmployee(playerEmployeeCount);
		for (Boat i : boats)
			i.clean(); // will not remove the assignment of cargo.
	}

	public void SetCaptain(int playerID, int cost) {
		players[playerID].payMoney(cost);
		cap = new Captain(players[playerID]);
	}

	public void CaptainChooseGoods(int a, int b, int c) {
		boats[0].loadGoods(goods[a]);
		boats[1].loadGoods(goods[b]);
		boats[2].loadGoods(goods[c]);
	}

	public void nextRound() {
		for (int i = 0; i < boatsCount; i++) {
			boats[i].move(dice.getNumber());
			if (boats[i].getPositionInSea() > arrivalPosition) {
				boats[i].setPositionInSea(arrivalPosition);
			}
		}
	}

	/**
	 * notice this function may be called at any round and make effect.
	 * 
	 * @return whether the game is ended.
	 */
	public boolean balance() {
		for (int i = 0; i < boatsCount; i++) {
			if (boats[i].getPositionInSea() == arrivalPosition) {
				port.setBoatArrival(boats[i], i);
				boats[i].getGoods().addValueLevel();
				if (boats[i].getGoods().getValue() >= endGameStockValue) {
					System.out.println("Game finished.");
					return true;
				}
			} else {
				factory.takeSinkBoat();
			}
		}
		return false;
	}
}
