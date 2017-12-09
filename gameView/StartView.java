package gameView;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

import controller.Game;

import java.awt.Color;

public class StartView {

	private JFrame frame;

	public void active() {
		frame.setVisible(true);
	}

	public void hide() {
		frame.setVisible(false);
	}

	public StartView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(500, 100, 1024, 748);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//
		frame.setVisible(true);
		//
		frame.getContentPane().setLayout(null);

		JLabel lblStart = new JLabel("Start!");
		lblStart.setBounds(391, 313, 184, 75);
		frame.getContentPane().add(lblStart);
		lblStart.setForeground(new Color(0, 102, 102));
		lblStart.setHorizontalAlignment(SwingConstants.CENTER);
		lblStart.setFont(new Font("AR HERMANN", Font.BOLD, 60));

		JLabel lblbackground = new JLabel("");
		lblbackground.setBounds(0, 0, 1006, 701);
		frame.getContentPane().add(lblbackground);
		lblbackground.setIcon(new ImageIcon(StartView.class.getResource("/images/startBG.jpg")));

		lblStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				hide();
				Game.run.active();
			}
		});
	}
}
