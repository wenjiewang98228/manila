package gameView;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.*;

import controller.Game;
import model.Player;
import model.Stock;

public class BuyStockView {

	private JFrame frame;

	public void active() {
		frame.setVisible(true);
	}

	public void hide() {
		frame.setVisible(false);
	}

	/**
	 * Create the application.
	 */
	public BuyStockView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("微软雅黑", Font.PLAIN, 20));
		frame.getContentPane().setBackground(new Color(224, 255, 255));
		frame.setBounds(700, 300, 362, 222);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel label = new JLabel("船长是否选择购买股票？");
		label.setFont(new Font("幼圆", Font.PLAIN, 17));
		label.setBounds(79, 32, 230, 39);
		frame.getContentPane().add(label);

		JButton cancelbtn = new JButton("否");
		cancelbtn.setBackground(new Color(248, 248, 255));
		cancelbtn.setBounds(200, 100, 56, 27);
		frame.getContentPane().add(cancelbtn);

		JButton OKbutton = new JButton("是");
		OKbutton.setBackground(new Color(255, 255, 255));
		OKbutton.setBounds(78, 100, 56, 27);
		frame.getContentPane().add(OKbutton);

		OKbutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Random rand = new Random();
				int id = rand.nextInt(Game.scene.goods.length);
				Player captain = Game.scene.cap.getPlayer();
				captain.addStock(new Stock(Game.scene.goods[id], captain));
				captain.payMoney(Game.scene.goods[id].getValue());
				hide();
				Game.game.update();

			}
		});

		cancelbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				hide();

			}
		});

	}
}
