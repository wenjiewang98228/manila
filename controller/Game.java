package controller;

import gameView.*;

public class Game {
	public static Scene scene = new Scene();
	public static StartView start;
	public static GameView game;
	public static LoadGoodsView load;
	public static RunView run;

	public static void main(String[] args) throws Exception {
		scene.Init();
		start = new StartView();
		run = new RunView();
		load = new LoadGoodsView();
		game = new GameView();

	}

}
