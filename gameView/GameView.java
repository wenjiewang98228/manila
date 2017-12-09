package gameView;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import controller.Game;
import java.awt.*;

import model.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class GameView {

	private JFrame frame;

	JLabel fixPos3;
	JLabel fixPos2;
	JLabel fixPos1;
	JLabel fixProfit3;
	JLabel fixProfit2;
	JLabel fixProfit1;
	JLabel portPos3;
	JLabel portPos2;
	JLabel portPos1;
	JLabel portProfit1;
	JLabel portProfit2;
	JLabel portProfit3;
	JLabel piratePos;
	JLabel navigatorPos;
	JLabel insurerPos;
	JLabel lblInsurer;
	JLabel lblNavigator;
	JLabel lblPirate;
	JLabel lblFix;

	JLabel goodsName1;
	JLabel lblBoat1;
	JLabel posA1;
	JLabel posA2;
	JLabel posA3;

	JLabel goodsName2;
	JLabel lblBoat2;
	JLabel posB1;
	JLabel posB2;
	JLabel posB3;

	JLabel goodsName3;
	JLabel lblBoat3;
	JLabel posC1;
	JLabel posC2;
	JLabel posC3;

	JLabel[][] boatPos;
	JLabel[] boatProfitPos;
	JLabel[] boatLabelPos;

	JButton btnRoll;
	JLabel lblInfobackground;

	private JLabel lblPcolor1;
	private JLabel lblPname1;
	private JLabel lblPmoney1;
	private JLabel lblPcolor2;
	private JLabel lblPname2;
	private JLabel lblPmoney2;
	private JLabel lblPcolor3;
	private JLabel lblPname3;
	private JLabel lblPmoney3;
	private JLabel lblStock;
	private JLabel lblSilkValue;
	private JLabel lblNutValue;
	private JLabel lblNut;
	private JLabel lblCocoaValue;
	private JLabel lblCocoa;
	private JLabel lblJade;
	private JLabel lblJadeValue;
	private JLabel lblP1;
	private JLabel PStock1;
	private JLabel lblP2;
	private JLabel PStock2;
	private JLabel lblP3;
	private JLabel PStock3;
	private JLabel boatProfit1;
	private JLabel boatProfit2;
	private JLabel boatProfit3;
	private JLabel pirateAct;
	private JLabel pirateSkip;
	private JLabel finishLabel;

	public JTextArea textArea;
	private JScrollPane scrollPane;

	private final int step = 35; // 20 pixels per move.
	private final Color[] playerColor = { Color.BLUE, Color.YELLOW, Color.RED };

	private int baseYCoord0; // picture
	private int baseYCoord1; // pos1
	private int baseYCoord2; // pos2
	private int baseYCoord3; // pos3
	private int baseYCoord4; // goodsName
	private int baseYCoord5; // boatProfit

	private boolean stepAssigning;
	private int stepAssignRest;

	private int currentAssigning;
	private boolean employeeAssigning;

	private boolean rounding;
	private int round;
	private boolean navigating;
	private boolean pirating;

	private boolean finished;
	private boolean endGame;
	private final JLabel lblSea = new JLabel("");
	private JLabel buyText;
	private JLabel lblPort;
	private JLabel name1;
	private JLabel name3;
	private JLabel name2;
	private JLabel name4;
	private JLabel label;

	public void active() {
		refresh();
		frame.setVisible(true);
	}

	public void hide() {
		frame.setVisible(false);
	}

	/*
	 * set all things to the original state.
	 */
	public void refresh() {
		stepAssigning = true;
		stepAssignRest = 9;
		employeeAssigning = false;
		rounding = false;
		round = 0;
		navigating = false;
		pirating = false;
		finished = false;
		btnRoll.setText("Roll");
		Game.scene.Reset();
		update();
	}

	/*
	 * make graphics synchronized with the scene data.
	 */
	void update() {
		updateGoodsOnBoat();
		boatMoveSync();
		for (int i = 0; i < Game.scene.boatsCount; i++)
			assignmentSync(Game.scene.boats[i], boatPos[i]);
		assignmentSync(Game.scene.factory, new JLabel[] { fixPos1, fixPos2, fixPos3 });
		assignmentSync(Game.scene.port, new JLabel[] { portPos1, portPos2, portPos3 });
		assignmentSync(Game.scene.navigator, new JLabel[] { navigatorPos });
		assignmentSync(Game.scene.pirate, new JLabel[] { piratePos });
		assignmentSync(Game.scene.insurer, new JLabel[] { insurerPos });
		updatePlayerInfo();
		updateStockValue();
	}

	private void boatMoveSync() {
		for (int id = 0; id < Game.scene.boatsCount; id++) {
			JLabel goodsName = id == 0 ? goodsName1 : id == 1 ? goodsName2 : goodsName3;
			JLabel profit = id == 0 ? boatProfit1 : id == 1 ? boatProfit2 : boatProfit3;
			JLabel[] pos = boatPos[id];
			JLabel pic = id == 0 ? lblBoat1 : id == 1 ? lblBoat2 : lblBoat3;
			int move = Game.scene.boats[id].getPositionInSea();
			profit.setBounds(profit.getX(), baseYCoord5 - step * move, profit.getWidth(), profit.getHeight());
			goodsName.setBounds(goodsName.getX(), baseYCoord4 - step * move, goodsName.getWidth(),
					goodsName.getHeight());
			pos[0].setBounds(pos[0].getX(), baseYCoord1 - step * move, pos[0].getWidth(), pos[0].getHeight());
			pos[1].setBounds(pos[1].getX(), baseYCoord2 - step * move, pos[1].getWidth(), pos[1].getHeight());
			pos[2].setBounds(pos[2].getX(), baseYCoord3 - step * move, pos[2].getWidth(), pos[2].getHeight());
			pic.setBounds(pic.getX(), baseYCoord0 - step * move, pic.getWidth(), pic.getHeight());
		}
	}

	private void assignmentSync(Assignments asi, JLabel[] pos) {
		for (int i = 0; i < asi.getEmployees().length; i++) {
			Employee g = asi.getEmployees()[i];
			if (asi instanceof Insurer && g == null) {
				pos[i].setBackground(new Color(255, 250, 205));
				pos[i].setText("" + (-asi.getCosts()[i]));
			} else if (g == null) {
				pos[i].setBackground(new Color(176, 224, 230));
				pos[i].setText("" + asi.getCosts()[i]);
			} else {
				pos[i].setBackground(playerColor[g.getPlayer().getId()]);
				pos[i].setText("");
			}
		}
	}

	private void updatePlayerInfo() {
		lblPmoney1.setText("" + Game.scene.players[0].getMoney());
		lblPmoney2.setText("" + Game.scene.players[1].getMoney());
		lblPmoney3.setText("" + Game.scene.players[2].getMoney());
		PStock1.setText(Game.scene.players[0].getStockName());
		PStock2.setText(Game.scene.players[1].getStockName());
		PStock3.setText(Game.scene.players[2].getStockName());
	}

	private void updateStockValue() {
		lblSilkValue.setText("" + Game.scene.goods[0].getValue());
		lblNutValue.setText("" + Game.scene.goods[1].getValue());
		lblJadeValue.setText("" + Game.scene.goods[2].getValue());
		lblCocoaValue.setText("" + Game.scene.goods[3].getValue());
	}

	private void updateGoodsOnBoat() {
		boatProfit1.setText("" + Game.scene.boats[0].getGoods().getProfit());
		boatProfit2.setText("" + Game.scene.boats[1].getGoods().getProfit());
		boatProfit3.setText("" + Game.scene.boats[2].getGoods().getProfit());
		goodsName1.setText(Game.scene.boats[0].getGoods().getName());
		goodsName2.setText(Game.scene.boats[1].getGoods().getName());
		goodsName3.setText(Game.scene.boats[2].getGoods().getName());
	}

	/**
	 * count all profit and apply them.
	 */
	private void balance() {
		endGame = Game.scene.balance();
	}

	/**
	 * end this round and go back to captain running.
	 */
	private void finish() {
		if (!endGame) {
			refresh();
			frame.setVisible(false);
			Game.run.active();
		} else {
			int winner = 1;
			if (Game.scene.players[0].getMoney() > Game.scene.players[1].getMoney()) {
				if (Game.scene.players[0].getMoney() > Game.scene.players[2].getMoney()) {
					winner = 1;
				} else if (Game.scene.players[1].getMoney() > Game.scene.players[2].getMoney()) {
					winner = 2;
				} else {
					winner = 3;
				}
			}
			String text = "Player  " + String.valueOf(winner) + "  win!Congratulations!";
			finishLabel.setText(text);
			finishLabel.setVisible(true);

		}
	}

	class StepAssignAdapter extends MouseAdapter {
		int c;
		GameView view;

		public StepAssignAdapter(int id, GameView view) {
			c = id;
			this.view = view;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (!stepAssigning)
				return;
			Game.scene.boats[c].move(1);
			view.stepAssignRest--;
			view.update();
			System.out.println("assignment rest: " + stepAssignRest);
			textArea.setText(textArea.getText() + "剩余移动次数: " + stepAssignRest + "\n");
			if (stepAssignRest == 0) {
				stepAssigning = false;
				System.out.println("step assigning finished.");
				textArea.setText(textArea.getText() + "船老大船只移动完毕\n");
				employeeAssigning = true;
				currentAssigning = Game.scene.cap.getPlayer().getId();
			}
		}
	}

	class AssignmentClickAdapter extends MouseAdapter {
		Assignments assignments;
		GameView view;

		public AssignmentClickAdapter(Assignments assignments, GameView view) {
			this.assignments = assignments;
			this.view = view;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (!employeeAssigning)
				return;
			boolean succ = assignments.setEmployee(Game.scene.players[currentAssigning].pullEmployee());
			if (!succ)
				return;
			System.out.println("player: " + Game.scene.players[currentAssigning].getId() + " rest employee: "
					+ Game.scene.players[currentAssigning].countEmployee());
			textArea.setText(textArea.getText() + "player: " + Game.scene.players[currentAssigning].getId() + " 剩余员工数: "
					+ Game.scene.players[currentAssigning].countEmployee() + "\n");
			currentAssigning++;
			currentAssigning %= Game.scene.playerCount;
			view.update();
			boolean hasRest = false;
			if (round == 0) {
				for (int i = 0; i < Game.scene.playerCount; i++) {
					if (Game.scene.players[i].countEmployee() > 2) {
						System.out.println(Game.scene.players[i].countEmployee());
						hasRest = true;
						break;
					}
				}
			} else if (round == 1) {
				for (int i = 0; i < Game.scene.playerCount; i++) {
					if (Game.scene.players[i].countEmployee() == 2) {
						hasRest = true;
						break;
					}

				}
			} else if (round == 2) {
				for (int i = 0; i < Game.scene.playerCount; i++) {
					if (Game.scene.players[i].countEmployee() != 0) {
						hasRest = true;
						break;
					}
				}
			}
			if (!hasRest) {
				employeeAssigning = false;
				// pirating = true;
				navigating = true;
				rounding = true;
				textArea.setText(textArea.getText() + "放置员工完毕\n");
				System.out.println("employee assigning finished.");
				textArea.setText(textArea.getText() + "领航员和海盗可以进行操作\n");
				System.out.println("navigator and pirate action enabled.After the navi,will the pirate.");
				textArea.setText(textArea.getText() + "领航员鼠标左键控制船前进1格,右键控制船后退1格\n");
				System.out.println("left click onto a boat to navigate.");
				textArea.setText(textArea.getText() + "领航员操作完成后，海盗进行操作\n");
				System.out.println("right click onto a boat to pirate.");
			}
		}
	}

	class NavigationAdapter extends MouseAdapter {
		int c;
		GameView view;

		public NavigationAdapter(int id, GameView view) {
			c = id;
			this.view = view;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (!navigating) {
				pirating = true;
				return;
			}
			if (Game.scene.navigator.countEmployee() == 0) {
				pirating = true;
				return;
			}
			if (Game.scene.boats[c].getPositionInSea() >= Game.scene.arrivalPosition) {
				pirating = true;
				return;
			}
			int move = e.getButton() == MouseEvent.BUTTON1 ? 1 : -1;
			Game.scene.boats[c].move(move);
			Game.scene.navigator.setEmployees(new Employee[] { null });
			pirating = true;
			navigating = false;
			System.out.println("Current round:" + round);
			view.update();
			textArea.setText(textArea.getText() + "领航员操作：  " + move + "\n");
			System.out.println("navigator operation:  " + move);
		}
	}

	class NextRoundAdapter extends MouseAdapter {
		JButton binding;

		public NextRoundAdapter(JButton bd) {
			binding = bd;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (!rounding)
				return;
			Game.scene.nextRound();
			update();
			round++;
			textArea.setText(textArea.getText() + "\nround " + round + " finished.");
			System.out.println("round " + round + " finished.");
			employeeAssigning = true;
			navigating = false;

			if (round == 3) {
				rounding = false;
				pirating = false;
				navigating = false;
				balance();
				update();
				finished = true;
				textArea.setText(textArea.getText() + "下一回合!\n");
				binding.setText("Next round!");
			}
		}
	}

	class PirateAdapter extends MouseAdapter {
		int c;
		GameView view;

		public PirateAdapter(int id, GameView view) {
			c = id;
			this.view = view;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (!pirating)
				return;
			// [!]TODO.
			if (Game.scene.pirate.countEmployee() == 0) {
				if (round == 3)
					balance();
				pirating = false;
				return;
			}
			if (Game.scene.boats[c].getPositionInSea() != Game.scene.arrivalPosition - 1)
				return;
			Game.scene.pirate.takeAction(round, Game.scene.boats[c]);
			Game.scene.pirate.setEmployees(new Employee[] { null });
			pirating = false;
			if (round == 3)
				balance();
			view.update();
			textArea.setText(textArea.getText() + "海盗占领了船只" + c + "\n");
			System.out.println("Pirate operation: get onto boat  " + c);
		}
	}

	class RevertAdapter extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (!finished)
				return;
			finish();
		}
	}

	/**
	 * Create the application.
	 */
	public GameView() {
		initialize();
		boatPos = new JLabel[][] { { posA1, posA2, posA3 }, { posB1, posB2, posB3 }, { posC1, posC2, posC3 } };
		boatProfitPos = new JLabel[] { goodsName1, goodsName2, goodsName3 };
		boatLabelPos = new JLabel[] { lblBoat1, lblBoat2, lblBoat3 };
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(500, 100, 1024, 742);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel playPanel = new JPanel();
		playPanel.setBounds(0, 0, 741, 695);
		frame.getContentPane().add(playPanel);

		for (int i = 0; i < 3; i++) {

		}
		playPanel.setLayout(null);

		name1 = new JLabel("Port");
		name1.setBounds(87, 13, 96, 41);
		name1.setBackground(new Color(95, 158, 160));
		name1.setForeground(Color.WHITE);
		name1.setFont(new Font("Adobe Caslon Pro Bold", Font.ITALIC, 36));
		playPanel.add(name1);

		finishLabel = new JLabel("Finish");
		finishLabel.setForeground(new Color(255, 99, 71));
		finishLabel.setFont(new Font("Segoe UI Black", Font.ITALIC, 44));
		finishLabel.setVisible(false);
		finishLabel.setBounds(113, 260, 674, 174);
		playPanel.add(finishLabel);

		name2 = new JLabel("Navigator");
		name2.setBounds(10, 471, 114, 46);
		name2.setForeground(Color.WHITE);
		name2.setFont(new Font("Adobe Caslon Pro Bold", Font.ITALIC, 24));
		name2.setHorizontalAlignment(SwingConstants.CENTER);
		playPanel.add(name2);

		name3 = new JLabel("Fixfactory");
		name3.setBounds(591, 35, 136, 41);
		name3.setForeground(Color.WHITE);
		name3.setBackground(new Color(95, 158, 160));
		name3.setFont(new Font("Adobe Caslon Pro Bold", Font.ITALIC, 29));
		name3.setHorizontalAlignment(SwingConstants.CENTER);
		playPanel.add(name3);

		name4 = new JLabel("Insurer");
		name4.setForeground(Color.WHITE);
		name4.setFont(new Font("Adobe Caslon Pro Bold", Font.ITALIC, 27));
		name4.setBounds(641, 388, 114, 46);
		playPanel.add(name4);
		posA3 = new JLabel("");
		posA3.setBounds(220, 687, 30, 16);
		posA3.setOpaque(true);
		posA3.setHorizontalAlignment(SwingConstants.CENTER);
		posA3.setFont(new Font("宋体", Font.PLAIN, 15));
		posA3.setBackground(Color.WHITE);
		playPanel.add(posA3);

		posA2 = new JLabel("");
		posA2.setBounds(220, 668, 30, 16);
		posA2.setOpaque(true);
		posA2.setHorizontalAlignment(SwingConstants.CENTER);
		posA2.setFont(new Font("宋体", Font.PLAIN, 15));
		posA2.setBackground(Color.WHITE);
		playPanel.add(posA2);

		posA1 = new JLabel("");
		posA1.setBounds(220, 648, 30, 16);
		posA1.setOpaque(true);
		posA1.setHorizontalAlignment(SwingConstants.CENTER);
		posA1.setFont(new Font("宋体", Font.PLAIN, 15));
		posA1.setBackground(Color.WHITE);
		playPanel.add(posA1);

		posB3 = new JLabel("");
		posB3.setBounds(352, 687, 30, 16);
		posB3.setOpaque(true);
		posB3.setHorizontalAlignment(SwingConstants.CENTER);
		posB3.setFont(new Font("宋体", Font.PLAIN, 17));
		posB3.setBackground(Color.WHITE);
		playPanel.add(posB3);

		posB2 = new JLabel("");
		posB2.setBounds(352, 668, 30, 16);
		posB2.setOpaque(true);
		posB2.setHorizontalAlignment(SwingConstants.CENTER);
		posB2.setFont(new Font("宋体", Font.PLAIN, 17));
		posB2.setBackground(Color.WHITE);
		playPanel.add(posB2);

		posB1 = new JLabel("");
		posB1.setBounds(352, 648, 30, 16);
		posB1.setOpaque(true);
		posB1.setHorizontalAlignment(SwingConstants.CENTER);
		posB1.setFont(new Font("宋体", Font.PLAIN, 17));
		posB1.setBackground(Color.WHITE);
		playPanel.add(posB1);

		posC1 = new JLabel("");
		posC1.setBounds(495, 648, 30, 16);
		posC1.setOpaque(true);
		posC1.setHorizontalAlignment(SwingConstants.CENTER);
		posC1.setFont(new Font("宋体", Font.PLAIN, 17));
		posC1.setBackground(Color.WHITE);
		playPanel.add(posC1);

		posC2 = new JLabel("");
		posC2.setBounds(495, 668, 30, 16);
		posC2.setOpaque(true);
		posC2.setHorizontalAlignment(SwingConstants.CENTER);
		posC2.setFont(new Font("宋体", Font.PLAIN, 17));
		posC2.setBackground(Color.WHITE);
		playPanel.add(posC2);

		posC3 = new JLabel("");
		posC3.setBounds(495, 687, 30, 16);
		posC3.setOpaque(true);
		posC3.setHorizontalAlignment(SwingConstants.CENTER);
		posC3.setFont(new Font("宋体", Font.PLAIN, 17));
		posC3.setBackground(Color.WHITE);
		playPanel.add(posC3);

		fixPos3 = new JLabel("2");
		fixPos3.setBounds(697, 195, 30, 27);
		fixPos3.setOpaque(true);
		fixPos3.setHorizontalAlignment(SwingConstants.CENTER);
		fixPos3.setFont(new Font("宋体", Font.PLAIN, 17));
		fixPos3.setBackground(new Color(176, 224, 230));
		playPanel.add(fixPos3);

		fixPos2 = new JLabel("3");
		fixPos2.setBounds(697, 162, 30, 27);
		fixPos2.setOpaque(true);
		fixPos2.setHorizontalAlignment(SwingConstants.CENTER);
		fixPos2.setFont(new Font("宋体", Font.PLAIN, 17));
		fixPos2.setBackground(new Color(176, 224, 230));
		playPanel.add(fixPos2);

		fixPos1 = new JLabel("4");
		fixPos1.setBounds(697, 126, 30, 27);
		fixPos1.setOpaque(true);
		fixPos1.setHorizontalAlignment(SwingConstants.CENTER);
		fixPos1.setFont(new Font("宋体", Font.PLAIN, 17));
		fixPos1.setBackground(new Color(176, 224, 230));
		playPanel.add(fixPos1);

		fixProfit3 = new JLabel("15");
		fixProfit3.setBounds(673, 195, 30, 27);
		fixProfit3.setOpaque(true);
		fixProfit3.setHorizontalAlignment(SwingConstants.CENTER);
		fixProfit3.setFont(new Font("宋体", Font.PLAIN, 17));
		fixProfit3.setBackground(new Color(255, 250, 205));
		playPanel.add(fixProfit3);

		fixProfit2 = new JLabel("8");
		fixProfit2.setBounds(673, 162, 30, 27);
		fixProfit2.setOpaque(true);
		fixProfit2.setHorizontalAlignment(SwingConstants.CENTER);
		fixProfit2.setFont(new Font("宋体", Font.PLAIN, 17));
		fixProfit2.setBackground(new Color(255, 250, 205));
		playPanel.add(fixProfit2);

		fixProfit1 = new JLabel("6");
		fixProfit1.setBounds(673, 126, 30, 27);
		fixProfit1.setOpaque(true);
		fixProfit1.setHorizontalAlignment(SwingConstants.CENTER);
		fixProfit1.setFont(new Font("宋体", Font.PLAIN, 17));
		fixProfit1.setBackground(new Color(255, 250, 205));
		playPanel.add(fixProfit1);

		portPos3 = new JLabel("2");
		portPos3.setBounds(10, 60, 30, 27);
		portPos3.setOpaque(true);
		portPos3.setHorizontalAlignment(SwingConstants.CENTER);
		portPos3.setFont(new Font("宋体", Font.PLAIN, 17));
		portPos3.setBackground(new Color(176, 224, 230));
		playPanel.add(portPos3);

		portPos2 = new JLabel("3");
		portPos2.setBounds(10, 102, 30, 27);
		portPos2.setOpaque(true);
		portPos2.setHorizontalAlignment(SwingConstants.CENTER);
		portPos2.setFont(new Font("宋体", Font.PLAIN, 17));
		portPos2.setBackground(new Color(176, 224, 230));
		playPanel.add(portPos2);

		portPos1 = new JLabel("4");
		portPos1.setBounds(10, 138, 30, 27);
		portPos1.setOpaque(true);
		portPos1.setHorizontalAlignment(SwingConstants.CENTER);
		portPos1.setFont(new Font("宋体", Font.PLAIN, 17));
		portPos1.setBackground(new Color(176, 224, 230));
		playPanel.add(portPos1);

		portProfit1 = new JLabel("6");
		portProfit1.setBounds(38, 138, 30, 27);
		portProfit1.setOpaque(true);
		portProfit1.setHorizontalAlignment(SwingConstants.CENTER);
		portProfit1.setFont(new Font("宋体", Font.PLAIN, 17));
		portProfit1.setBackground(new Color(255, 250, 205));
		playPanel.add(portProfit1);

		portProfit2 = new JLabel("8");
		portProfit2.setBounds(38, 102, 30, 27);
		portProfit2.setOpaque(true);
		portProfit2.setHorizontalAlignment(SwingConstants.CENTER);
		portProfit2.setFont(new Font("宋体", Font.PLAIN, 17));
		portProfit2.setBackground(new Color(255, 250, 205));
		playPanel.add(portProfit2);

		portProfit3 = new JLabel("15");
		portProfit3.setBounds(38, 60, 30, 27);
		portProfit3.setOpaque(true);
		portProfit3.setHorizontalAlignment(SwingConstants.CENTER);
		portProfit3.setFont(new Font("宋体", Font.PLAIN, 17));
		portProfit3.setBackground(new Color(255, 250, 205));
		playPanel.add(portProfit3);

		piratePos = new JLabel("5");
		piratePos.setBounds(47, 294, 30, 27);
		piratePos.setOpaque(true);
		piratePos.setHorizontalAlignment(SwingConstants.CENTER);
		piratePos.setFont(new Font("宋体", Font.PLAIN, 17));
		piratePos.setBackground(new Color(176, 224, 230));
		playPanel.add(piratePos);

		navigatorPos = new JLabel("2");
		navigatorPos.setBounds(57, 422, 30, 27);
		navigatorPos.setOpaque(true);
		navigatorPos.setBackground(new Color(176, 224, 230));
		navigatorPos.setHorizontalAlignment(SwingConstants.CENTER);
		navigatorPos.setFont(new Font("宋体", Font.PLAIN, 17));
		playPanel.add(navigatorPos);

		insurerPos = new JLabel("10");
		insurerPos.setBounds(673, 480, 30, 27);
		insurerPos.setOpaque(true);
		insurerPos.setHorizontalAlignment(SwingConstants.CENTER);
		insurerPos.setFont(new Font("宋体", Font.PLAIN, 17));
		insurerPos.setBackground(new Color(255, 250, 205));
		playPanel.add(insurerPos);

		lblInsurer = new JLabel("");
		lblInsurer.setBounds(619, 419, 136, 131);
		lblInsurer.setIcon(new ImageIcon(GameView.class.getResource("/images/insurerImg.png")));
		playPanel.add(lblInsurer);

		lblNavigator = new JLabel("");
		lblNavigator.setBounds(0, 378, 173, 112);
		lblNavigator.setIcon(new ImageIcon(GameView.class.getResource("/images/navigatorImg.png")));
		playPanel.add(lblNavigator);

		lblPirate = new JLabel("pirate");
		lblPirate.setBounds(20, 243, 102, 103);
		lblPirate.setIcon(new ImageIcon(GameView.class.getResource("/images/pirateImg.png")));
		playPanel.add(lblPirate);

		lblFix = new JLabel("");
		lblFix.setBounds(628, 60, 113, 232);
		lblFix.setHorizontalAlignment(SwingConstants.CENTER);
		lblFix.setIcon(new ImageIcon(GameView.class.getResource("/images/fixImg.png")));
		playPanel.add(lblFix);

		goodsName2 = new JLabel("");
		goodsName2.setBounds(342, 621, 51, 27);
		goodsName2.setForeground(Color.WHITE);
		goodsName2.setOpaque(true);
		goodsName2.setHorizontalAlignment(SwingConstants.CENTER);
		goodsName2.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		goodsName2.setBackground(new Color(70, 130, 180));
		playPanel.add(goodsName2);

		goodsName3 = new JLabel("");
		goodsName3.setBounds(485, 621, 51, 27);
		goodsName3.setForeground(Color.WHITE);
		goodsName3.setOpaque(true);
		goodsName3.setHorizontalAlignment(SwingConstants.CENTER);
		goodsName3.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		goodsName3.setBackground(new Color(70, 130, 180));
		playPanel.add(goodsName3);

		goodsName1 = new JLabel("");
		goodsName1.setBounds(208, 621, 54, 27);
		goodsName1.setForeground(Color.WHITE);
		goodsName1.setOpaque(true);
		goodsName1.setHorizontalAlignment(SwingConstants.CENTER);
		goodsName1.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		goodsName1.setBackground(new Color(70, 130, 180));
		playPanel.add(goodsName1);

		boatProfit1 = new JLabel("");
		boatProfit1.setBounds(213, 597, 44, 27);
		boatProfit1.setOpaque(true);
		boatProfit1.setBackground(Color.WHITE);
		boatProfit1.setForeground(Color.DARK_GRAY);
		boatProfit1.setFont(new Font("Adobe Caslon Pro Bold", Font.PLAIN, 16));
		boatProfit1.setHorizontalAlignment(SwingConstants.CENTER);
		playPanel.add(boatProfit1);

		boatProfit2 = new JLabel("");
		boatProfit2.setBounds(346, 597, 44, 27);
		boatProfit2.setOpaque(true);
		boatProfit2.setBackground(Color.WHITE);
		boatProfit2.setHorizontalAlignment(SwingConstants.CENTER);
		boatProfit2.setForeground(Color.DARK_GRAY);
		boatProfit2.setFont(new Font("Adobe Caslon Pro Bold", Font.PLAIN, 16));
		playPanel.add(boatProfit2);

		boatProfit3 = new JLabel("");
		boatProfit3.setBounds(488, 597, 44, 27);
		boatProfit3.setOpaque(true);
		boatProfit3.setBackground(Color.WHITE);
		boatProfit3.setHorizontalAlignment(SwingConstants.CENTER);
		boatProfit3.setForeground(Color.DARK_GRAY);
		boatProfit3.setFont(new Font("Adobe Caslon Pro Bold", Font.PLAIN, 16));
		playPanel.add(boatProfit3);

		lblBoat3 = new JLabel("");
		lblBoat3.setBounds(459, 556, 102, 243);
		lblBoat3.setIcon(new ImageIcon(GameView.class.getResource("/images/boatImg3.png")));
		playPanel.add(lblBoat3);

		lblBoat2 = new JLabel("");
		lblBoat2.setBounds(316, 556, 102, 243);
		lblBoat2.setIcon(new ImageIcon(GameView.class.getResource("/images/boatImg3.png")));
		playPanel.add(lblBoat2);

		lblBoat1 = new JLabel("");
		lblBoat1.setBounds(184, 556, 102, 243);
		lblBoat1.setIcon(new ImageIcon(GameView.class.getResource("/images/boatImg3.png")));
		playPanel.add(lblBoat1);

		lblCocoa = new JLabel("Cocoa:");
		lblCocoa.setBounds(627, 638, 51, 27);
		lblCocoa.setHorizontalAlignment(SwingConstants.CENTER);
		lblCocoa.setForeground(Color.WHITE);
		lblCocoa.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblCocoa.setBackground(Color.WHITE);
		playPanel.add(lblCocoa);

		lblCocoaValue = new JLabel(Game.scene.goods[3].getValue() + "");
		lblCocoaValue.setBounds(667, 642, 44, 18);
		lblCocoaValue.setHorizontalAlignment(SwingConstants.CENTER);
		lblCocoaValue.setForeground(Color.WHITE);
		lblCocoaValue.setFont(new Font("Segoe UI", Font.BOLD, 15));
		playPanel.add(lblCocoaValue);

		lblJade = new JLabel("Jade:");
		lblJade.setBounds(627, 618, 51, 27);
		lblJade.setHorizontalAlignment(SwingConstants.CENTER);
		lblJade.setForeground(Color.WHITE);
		lblJade.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblJade.setBackground(Color.WHITE);
		playPanel.add(lblJade);

		lblJadeValue = new JLabel(Game.scene.goods[2].getValue() + "");
		lblJadeValue.setBounds(667, 622, 44, 18);
		lblJadeValue.setHorizontalAlignment(SwingConstants.CENTER);
		lblJadeValue.setForeground(Color.WHITE);
		lblJadeValue.setFont(new Font("Segoe UI", Font.BOLD, 15));
		playPanel.add(lblJadeValue);

		lblNut = new JLabel("Nut:");
		lblNut.setBounds(627, 597, 51, 27);
		lblNut.setHorizontalAlignment(SwingConstants.CENTER);
		lblNut.setForeground(Color.WHITE);
		lblNut.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNut.setBackground(Color.WHITE);
		playPanel.add(lblNut);

		lblNutValue = new JLabel(Game.scene.goods[1].getValue() + "");
		lblNutValue.setBounds(667, 601, 44, 18);
		lblNutValue.setHorizontalAlignment(SwingConstants.CENTER);
		lblNutValue.setForeground(Color.WHITE);
		lblNutValue.setFont(new Font("Segoe UI", Font.BOLD, 15));
		playPanel.add(lblNutValue);

		JLabel lblSilk = new JLabel("Silk:");
		lblSilk.setBounds(627, 577, 51, 27);
		lblSilk.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblSilk.setForeground(Color.WHITE);
		lblSilk.setHorizontalAlignment(SwingConstants.CENTER);
		lblSilk.setBackground(Color.WHITE);
		playPanel.add(lblSilk);

		lblSilkValue = new JLabel(Game.scene.goods[0].getValue() + "");
		lblSilkValue.setBounds(667, 581, 44, 18);
		lblSilkValue.setForeground(Color.WHITE);
		lblSilkValue.setHorizontalAlignment(SwingConstants.CENTER);
		lblSilkValue.setFont(new Font("Segoe UI", Font.BOLD, 15));
		playPanel.add(lblSilkValue);

		JLabel lblBuyStock = new JLabel("");
		lblBuyStock.setBounds(0, 505, 155, 141);
		lblBuyStock.setHorizontalAlignment(SwingConstants.LEFT);
		lblBuyStock.setIcon(new ImageIcon(GameView.class.getResource("/images/buyStockImg.png")));
		playPanel.add(lblBuyStock);

		buyText = new JLabel("buy stock");
		buyText.setBounds(20, 630, 96, 36);
		buyText.setFont(new Font("Algerian", Font.BOLD, 15));
		buyText.setForeground(Color.WHITE);
		buyText.setHorizontalAlignment(SwingConstants.CENTER);
		playPanel.add(buyText);

		lblPort = new JLabel("");
		lblPort.setBounds(1, -4, 113, 237);
		lblPort.setIcon(new ImageIcon(GameView.class.getResource("/images/portImg.png")));
		playPanel.add(lblPort);

		pirateAct = new JLabel("操作");
		pirateAct.setVisible(false);
		pirateAct.setOpaque(true);
		pirateAct.setForeground(UIManager.getColor("textHighlight"));
		pirateAct.setBackground(SystemColor.controlHighlight);
		pirateAct.setBounds(103, 274, 36, 18);
		playPanel.add(pirateAct);

		pirateSkip = new JLabel("跳过");
		pirateSkip.setVisible(false);
		pirateSkip.setOpaque(true);
		pirateSkip.setBackground(UIManager.getColor("info"));
		pirateSkip.setForeground(UIManager.getColor("textHighlight"));
		pirateSkip.setBounds(103, 299, 36, 18);
		playPanel.add(pirateSkip);

		lblSea.setBounds(0, 13, 741, 695);
		lblSea.setIcon(new ImageIcon(GameView.class.getResource("/images/SeaImg.jpg")));
		playPanel.add(lblSea);

		JPanel infoPanel = new JPanel();
		infoPanel.setBounds(740, 0, 266, 695);
		frame.getContentPane().add(infoPanel);
		infoPanel.setLayout(null);

		btnRoll = new JButton("Roll");
		btnRoll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRoll.setBackground(Color.WHITE);
		btnRoll.setBounds(63, 617, 136, 54);
		infoPanel.add(btnRoll);

		JPanel playersView = new JPanel();
		playersView.setBackground(Color.WHITE);
		playersView.setBounds(14, 13, 238, 579);
		infoPanel.add(playersView);
		playersView.setLayout(null);

		lblPcolor1 = new JLabel("");
		lblPcolor1.setBackground(Color.BLUE);
		lblPcolor1.setOpaque(true);
		lblPcolor1.setBounds(69, 10, 10, 11);
		playersView.add(lblPcolor1);

		lblPname1 = new JLabel("Player1");
		lblPname1.setFont(new Font("幼圆", Font.BOLD, 15));
		lblPname1.setBounds(81, 0, 68, 29);
		playersView.add(lblPname1);

		lblPmoney1 = new JLabel("$" + Game.scene.players[0].getMoney());
		lblPmoney1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPmoney1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblPmoney1.setBounds(161, 6, 35, 18);
		playersView.add(lblPmoney1);

		lblPcolor2 = new JLabel("");
		lblPcolor2.setOpaque(true);
		lblPcolor2.setBackground(Color.YELLOW);
		lblPcolor2.setBounds(69, 33, 10, 11);
		playersView.add(lblPcolor2);

		lblPname2 = new JLabel("Player2");
		lblPname2.setFont(new Font("幼圆", Font.BOLD, 15));
		lblPname2.setBounds(81, 23, 68, 29);
		playersView.add(lblPname2);

		lblPmoney2 = new JLabel("$" + Game.scene.players[1].getMoney());
		lblPmoney2.setHorizontalAlignment(SwingConstants.CENTER);
		lblPmoney2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblPmoney2.setBounds(161, 29, 35, 18);
		playersView.add(lblPmoney2);

		lblPcolor3 = new JLabel("");
		lblPcolor3.setOpaque(true);
		lblPcolor3.setBackground(Color.RED);
		lblPcolor3.setBounds(69, 56, 10, 11);
		playersView.add(lblPcolor3);

		lblPname3 = new JLabel("Player3");
		lblPname3.setFont(new Font("幼圆", Font.BOLD, 15));
		lblPname3.setBounds(79, 46, 68, 29);
		playersView.add(lblPname3);

		lblPmoney3 = new JLabel("$" + Game.scene.players[2].getMoney());
		lblPmoney3.setHorizontalAlignment(SwingConstants.CENTER);
		lblPmoney3.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblPmoney3.setBounds(161, 52, 35, 18);
		playersView.add(lblPmoney3);

		lblStock = new JLabel("Stock:");
		lblStock.setBounds(14, 77, 58, 18);
		playersView.add(lblStock);

		lblP1 = new JLabel("P1:");
		lblP1.setFont(new Font("宋体", Font.PLAIN, 13));
		lblP1.setHorizontalAlignment(SwingConstants.CENTER);
		lblP1.setEnabled(false);
		lblP1.setBounds(14, 98, 35, 18);
		playersView.add(lblP1);

		PStock1 = new JLabel(Game.scene.players[0].getStockName());
		PStock1.setBounds(48, 98, 140, 18);
		playersView.add(PStock1);

		lblP2 = new JLabel("P2:");
		lblP2.setHorizontalAlignment(SwingConstants.CENTER);
		lblP2.setFont(new Font("宋体", Font.PLAIN, 13));
		lblP2.setEnabled(false);
		lblP2.setBounds(14, 120, 35, 18);
		playersView.add(lblP2);

		PStock2 = new JLabel(Game.scene.players[1].getStockName());
		PStock2.setBounds(48, 120, 157, 18);
		playersView.add(PStock2);

		lblP3 = new JLabel("P3:");
		lblP3.setHorizontalAlignment(SwingConstants.CENTER);
		lblP3.setFont(new Font("宋体", Font.PLAIN, 13));
		lblP3.setEnabled(false);
		lblP3.setBounds(14, 142, 35, 18);
		playersView.add(lblP3);

		PStock3 = new JLabel(Game.scene.players[1].getStockName());
		PStock3.setBounds(48, 142, 176, 18);
		playersView.add(PStock3);

		JScrollPane pane = new JScrollPane();
		pane.setLocation(0, 160);
		pane.setSize(238, 419);
		textArea = new JTextArea();
		textArea.setFont(new Font("宋体", Font.PLAIN, 16));
		textArea.setLineWrap(true);
		textArea.setBounds(0, 163, 154, 212);
		pane.setViewportView(textArea);
		playersView.add(pane);

		lblInfobackground = new JLabel("");
		lblInfobackground.setIcon(new ImageIcon(GameView.class.getResource("/images/infoBG4.png")));
		lblInfobackground.setBounds(0, 0, 266, 695);
		infoPanel.add(lblInfobackground);

		baseYCoord0 = lblBoat3.getY();
		baseYCoord1 = posC1.getY();
		baseYCoord2 = posC2.getY();
		baseYCoord3 = posC3.getY();
		baseYCoord4 = goodsName3.getY();
		baseYCoord5 = boatProfit3.getY();

		btnRoll.addMouseListener(new RevertAdapter());
		btnRoll.addMouseListener(new NextRoundAdapter(btnRoll));

		lblBuyStock.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new BuyStockView();
				lblBuyStock.setVisible(false);
				buyText.setVisible(false);
			}
		});
		lblPirate.addMouseListener(new AssignmentClickAdapter(Game.scene.pirate, this));
		lblInsurer.addMouseListener(new AssignmentClickAdapter(Game.scene.insurer, this));
		lblNavigator.addMouseListener(new AssignmentClickAdapter(Game.scene.navigator, this));
		lblFix.addMouseListener(new AssignmentClickAdapter(Game.scene.factory, this));
		lblBoat3.addMouseListener(new NavigationAdapter(2, this));
		lblBoat2.addMouseListener(new NavigationAdapter(1, this));
		lblBoat1.addMouseListener(new NavigationAdapter(0, this));
		lblBoat3.addMouseListener(new AssignmentClickAdapter(Game.scene.boats[2], this));
		lblBoat2.addMouseListener(new AssignmentClickAdapter(Game.scene.boats[1], this));
		lblBoat1.addMouseListener(new AssignmentClickAdapter(Game.scene.boats[0], this));

		lblBoat3.addMouseListener(new StepAssignAdapter(2, this));
		lblBoat2.addMouseListener(new StepAssignAdapter(1, this));
		lblBoat1.addMouseListener(new StepAssignAdapter(0, this));
		lblPort.addMouseListener(new AssignmentClickAdapter(Game.scene.port, this));

		// test
		lblBoat3.addMouseListener(new PirateAdapter(2, this));
		lblBoat2.addMouseListener(new PirateAdapter(1, this));
		lblBoat1.addMouseListener(new PirateAdapter(0, this));
	}
}
