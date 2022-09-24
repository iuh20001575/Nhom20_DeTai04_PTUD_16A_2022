package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.formdev.flatlaf.FlatIntelliJLaf;

import layouts.DefaultLayout;

public class Home_GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		FlatIntelliJLaf.registerCustomDefaultsSource("style");
		FlatIntelliJLaf.setup();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home_GUI frame = new Home_GUI();
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
	public Home_GUI() {
//		Default
		new DefaultLayout(this, contentPane, "Trang chủ");
	}

}
