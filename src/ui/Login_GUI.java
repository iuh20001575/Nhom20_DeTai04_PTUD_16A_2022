package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import components.panelRound.PanelRound;
import utils.Utils;

public class Login_GUI extends JFrame {

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
					Login_GUI frame = new Login_GUI();
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
	public Login_GUI() {
		setTitle("Đăng nhập");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 810, 483);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel container = new JPanel();
		container.setBounds(0, 0, 800, 450);
		contentPane.add(container);
		container.setLayout(null);

		PanelRound panel = new PanelRound();
		panel.setRounded(24);
		panel.setBounds(100, 25, 600, 400);
		panel.setBackground(Utils.getRGBA(255, 255, 255, 0.3f));	
		container.add(panel);

		BufferedImage img;
		try {
			img = ImageIO.read(new File("Hinh\\loginBackground.jpg"));
			ImageIcon imageIcon = new ImageIcon(img);
			JLabel jLabel = new JLabel(imageIcon);
			jLabel.setBounds(0, 0, 790, 450);
			container.add(jLabel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setLocationRelativeTo(null);
	}
}
