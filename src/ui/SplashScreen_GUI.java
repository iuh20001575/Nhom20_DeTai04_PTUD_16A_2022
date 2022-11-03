package ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class SplashScreen_GUI extends JWindow {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblLoading;
	private JLabel lblPhanTram;
	private JProgressBar prg;

	/**
	 * Create the frame.
	 */
	public SplashScreen_GUI() {
		setBounds(0, 0, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("KARAOKE ROME");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 40));
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setBounds(0, 20, 450, 60);
		contentPane.add(lblTitle);
		setLocationRelativeTo(null);

		JLabel lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon("Icon\\microphone.png"));
		lblIcon.setBounds(97, 100, 256, 256);
		contentPane.add(lblIcon);

		prg = new JProgressBar();
		prg.setBounds(0, 389, 450, 11);
		contentPane.add(prg);

		lblLoading = new JLabel("Loading...");
		lblLoading.setForeground(Color.WHITE);
		lblLoading.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblLoading.setBounds(10, 355, 350, 24);
		contentPane.add(lblLoading);

		lblPhanTram = new JLabel("0%");
		lblPhanTram.setForeground(Color.WHITE);
		lblPhanTram.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblPhanTram.setBounds(390, 355, 50, 24);
		contentPane.add(lblPhanTram);

		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon("Icon\\SplashScreen.png"));
		lblBackground.setBounds(0, 0, 450, 400);
		contentPane.add(lblBackground);
	}

	public void handleOpen(JFrame jFrame) {
		Thread thread = new Thread() {
			@Override
			public void run() {
				for (int i = 0; i <= 10; i++) {
					try {
						prg.setValue(i * 10);
						lblPhanTram.setText(i * 10 + "%");

						if (i == 1)
							lblLoading.setText("Turning On Modules...");
						else if (i == 2)
							lblLoading.setText("Loading Modules...");
						else if (i == 5)
							lblLoading.setText("Connecting to Database...");
						else if (i == 7)
							lblLoading.setText("Connection Successfull!");
						else if (i == 8)
							lblLoading.setText("Launching Application...");
						sleep(100);

						if (i == 10) {
							jFrame.setAlwaysOnTop(true);
							jFrame.setVisible(true);
							dispose();
						}
					} catch (Exception e) {
					}
				}
			}
		};

		thread.start();
	}
}
