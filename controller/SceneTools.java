package controller;

import model.*;

public class SceneTools {
	public static String GetPlayersInfo(Scene sc) {
		StringBuilder s = new StringBuilder();
		s.append("==> players:\n");
		for (Player i : sc.players)
			if (i != null)
				s.append(i.toString());
		return s.toString();
	}

	public static String GetBoatsInfo(Scene sc) {
		StringBuilder s = new StringBuilder();
		s.append("==> Boats\n");
		for (Boat i : sc.boats)
			if (i != null)
				s.append(i.toString());
		return s.toString();
	}

	public static String GetPortsInfo(Scene sc) {
		StringBuilder s = new StringBuilder();
		s.append("==> ports:\n");
		s.append(sc.port.toString());
		return s.toString();
	}

	public static String GetFactoryInfo(Scene sc) {
		StringBuilder s = new StringBuilder();
		s.append("==> factory:\n");
		s.append(sc.factory.toString());
		return s.toString();
	}

	public static String GetPirateInfo(Scene sc) {
		StringBuilder s = new StringBuilder();
		s.append("==> pirate:\n");
		s.append(sc.pirate.toString());
		return s.toString();
	}

	public static String GetGoodsInfo(Scene sc) {
		StringBuilder s = new StringBuilder();
		s.append("==> goods:\n");
		for (Goods i : sc.goods)
			if (i != null)
				s.append(i.toString());
		return s.toString();
	}

	public static String GetInfo(Scene sc) {
		StringBuilder s = new StringBuilder();
		s.append("=========== begin ==========\n");
		s.append(GetPlayersInfo(sc));
		s.append(GetBoatsInfo(sc));
		s.append(GetPortsInfo(sc));
		s.append(GetFactoryInfo(sc));
		s.append(GetPirateInfo(sc));
		s.append(GetGoodsInfo(sc));
		s.append("============ end ===========\n");

		return s.toString();
	}

	public static void DisplayInfo(Scene sc) {
		System.out.print(GetInfo(sc));
	}

}
