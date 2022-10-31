package ui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TrangChu_GUI extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the frame.
	 */
	public TrangChu_GUI() {
		setBounds(0, 0, 1086, 508);
		setLayout(null);

		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon("Hinh\\Rectangle 1.png"));
		lblBackground.setBounds(0, 0, 1086, 508);
		this.add(lblBackground);
	}
}
