package ui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import layouts.DefaultLayout;

public class TrangChu_GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrangChu_GUI frame = new TrangChu_GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TrangChu_GUI() {
//		Default
		DefaultLayout defaultLayout = new DefaultLayout(this, contentPane, "Trang chủ");
		defaultLayout.getBtnBack().setVisible(false);

		JPanel pnl = new JPanel();
		pnl.setBounds(0, 65, 1086, 508);
		defaultLayout.getJPanel().add(pnl);
		pnl.setLayout(null);

		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon("Hinh\\Rectangle 1.png"));
		lblBackground.setBounds(0, 0, 1086, 508);
		pnl.add(lblBackground);
	}
}
