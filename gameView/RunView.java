package gameView;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.Game;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RunView {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblPlayone;
	private JLabel lblPlaytwo;
	private JLabel lblPlaythree;
	private int cur = 0;

	public void active() {
		frame.setVisible(true);
		refresh();
	}

	public void hide() {
		frame.setVisible(false);
	}

	public void refresh() {
		textField.setText("0");
		cur = 0;
		colorAdapt();
	}

	private void colorAdapt() {
		lblPlayone.setBackground(Color.white);
		lblPlaytwo.setBackground(Color.white);
		lblPlaythree.setBackground(Color.white);
		if (cur == 0)
			lblPlayone.setBackground(Color.YELLOW);
		if (cur == 1)
			lblPlaytwo.setBackground(Color.YELLOW);
		if (cur == 2)
			lblPlaythree.setBackground(Color.YELLOW);
	}

	private void nextPlayer() {
		cur++;
		cur = cur % 3;
		colorAdapt();
	}

	/**
	 * Create the application.
	 */
	public RunView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(700, 300, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("竞选船长");
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 432, 253);
		panel.setOpaque(true);
		panel.setBackground(new Color(224, 255, 255));
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		lblPlayone = new JLabel("PlayOne");
		lblPlayone.setBounds(66, 26, 72, 18);
		lblPlayone.setOpaque(true);
		lblPlayone.setBackground(new Color(176, 224, 230));
		panel.add(lblPlayone);

		lblPlaytwo = new JLabel("PlayTwo");
		lblPlaytwo.setBounds(186, 26, 72, 18);
		lblPlaytwo.setOpaque(true);
		lblPlaytwo.setBackground(new Color(176, 224, 230));
		panel.add(lblPlaytwo);

		lblPlaythree = new JLabel("PlayThree");
		lblPlaythree.setBounds(292, 26, 72, 18);
		lblPlaythree.setOpaque(true);
		lblPlaythree.setBackground(new Color(176, 224, 230));
		panel.add(lblPlaythree);

		textField = new JTextField();
		textField.setBackground(Color.WHITE);
		textField.setEditable(false);
		textField.setBounds(174, 103, 86, 24);
		panel.add(textField);
		textField.setColumns(10);

		JLabel label = new JLabel("当前出价：");
		label.setBounds(94, 106, 94, 18);
		panel.add(label);

		JLabel label_1 = new JLabel("船老大：");
		label_1.setBounds(116, 160, 72, 18);
		panel.add(label_1);

		JLabel lblBossname = new JLabel("BossName");
		lblBossname.setBounds(186, 160, 72, 18);
		panel.add(lblBossname);

		textField_1 = new JTextField();
		textField_1.setBounds(126, 202, 86, 24);
		panel.add(textField_1);
		textField_1.setColumns(10);

		JButton buttonAdd = new JButton("竞标：");
		buttonAdd.setOpaque(true);
		buttonAdd.setBackground(new Color(176, 224, 230));
		buttonAdd.setBounds(36, 202, 86, 24);
		panel.add(buttonAdd);

		JButton buttonGiveUp = new JButton("放弃");
		buttonGiveUp.setBackground(new Color(176, 224, 230));
		buttonGiveUp.setBounds(226, 202, 86, 24);
		panel.add(buttonGiveUp);

		JButton buttonConfirm = new JButton("确认");
		buttonConfirm.setBackground(new Color(176, 224, 230));
		buttonConfirm.setBounds(321, 202, 86, 24);
		panel.add(buttonConfirm);

		buttonAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				try {
					int curPrice = Integer.parseInt(textField.getText());
					int nxtPrice = Integer.parseInt(textField_1.getText());
					if (nxtPrice > curPrice) {
						textField.setText("" + nxtPrice);
						lblBossname.setText("Player " + (Game.scene.players[cur].getId()+1));
						textField_1.setText("");
						nextPlayer();
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		});

		buttonGiveUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				nextPlayer();
			}
		});

		buttonConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Captain assign to palyer " + cur);
				Game.game.textArea.setText(Game.game.textArea.getText() + "palyer " + (cur+1) + "当选船老大\n船老大可以移动船只，共移动九次\n");
				Game.scene.SetCaptain(cur, Integer.parseInt(textField.getText()));
				hide();
				Game.load.active();
			}
		});
	}
}
