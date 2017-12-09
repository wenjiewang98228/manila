package gameView;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JRadioButton;

import controller.Game;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoadGoodsView {

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
	public LoadGoodsView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("微软雅黑", Font.PLAIN, 20));
		frame.getContentPane().setBackground(new Color(224, 255, 255));
		frame.setBounds(500, 300, 773, 474);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel label = new JLabel("请选择要载的货物（4选3）：");
		label.setFont(new Font("宋体", Font.PLAIN, 16));
		label.setBounds(32, 24, 219, 27);
		frame.getContentPane().add(label);

		JLabel lblCocoa = new JLabel("");
		lblCocoa.setIcon(new ImageIcon(LoadGoodsView.class.getResource("/images/Cocoa.jpg")));
		lblCocoa.setBounds(572, 85, 150, 203);
		frame.getContentPane().add(lblCocoa);

		JLabel lblJade = new JLabel("");
		lblJade.setIcon(new ImageIcon(LoadGoodsView.class.getResource("/images/jad.jpg")));
		lblJade.setBounds(392, 85, 150, 203);
		frame.getContentPane().add(lblJade);

		JLabel lblNut = new JLabel("");
		lblNut.setIcon(new ImageIcon(LoadGoodsView.class.getResource("/images/Nut.jpg")));
		lblNut.setBounds(209, 85, 150, 203);
		frame.getContentPane().add(lblNut);

		JLabel lblSilk = new JLabel("");
		lblSilk.setIcon(new ImageIcon(LoadGoodsView.class.getResource("/images/Silk.jpg")));
		lblSilk.setBounds(32, 85, 150, 203);
		frame.getContentPane().add(lblSilk);

		JButton button = new JButton("确定");
		button.setBackground(new Color(248, 248, 255));
		button.setBounds(307, 355, 113, 27);
		frame.getContentPane().add(button);

		JRadioButton rdbtnNut = new JRadioButton("Nut");
		rdbtnNut.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		rdbtnNut.setBackground(new Color(224, 255, 255));
		rdbtnNut.setBounds(259, 297, 61, 32);
		frame.getContentPane().add(rdbtnNut);

		JRadioButton rdbtnJade = new JRadioButton("Jade");
		rdbtnJade.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		rdbtnJade.setBackground(new Color(224, 255, 255));
		rdbtnJade.setBounds(433, 297, 94, 32);
		frame.getContentPane().add(rdbtnJade);

		JRadioButton rdbtnCocoa = new JRadioButton("Cocoa");
		rdbtnCocoa.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		rdbtnCocoa.setBackground(new Color(224, 255, 255));
		rdbtnCocoa.setBounds(619, 297, 79, 32);
		frame.getContentPane().add(rdbtnCocoa);

		JRadioButton rdbtnSilk = new JRadioButton("Silk");
		rdbtnSilk.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		rdbtnSilk.setBackground(new Color(224, 255, 255));
		rdbtnSilk.setBounds(71, 297, 61, 32);
		frame.getContentPane().add(rdbtnSilk);

		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int[] s = new int[4];
				s[0] = rdbtnSilk.isSelected() ? 1 : 0;
				s[1] = rdbtnNut.isSelected() ? 1 : 0;
				s[2] = rdbtnJade.isSelected() ? 1 : 0;
				s[3] = rdbtnCocoa.isSelected() ? 1 : 0;
				int sum = s[0] + s[1] + s[2] + s[3];
				if (sum != 3)
					return;
				int[] g = new int[3];
				int k = 0;
				for (int i = 0; i < 4; i++)
					if (s[i] == 1)
						g[k++] = i;

				System.out.println("Select cargo: " + g[0] + " | " + g[1] + " | " + g[2]);
				Game.scene.CaptainChooseGoods(g[0], g[1], g[2]);
				if (Game.game.textArea.getRows() >= 12) {
					Game.game.textArea.setText("");
				}
				Game.game.textArea
						.setText(Game.game.textArea.getText() + "Select cargo: " + g[0] + " | " + g[1] + " | " + g[2]);
				hide();
				Game.game.active();
			}
		});
	}
}
