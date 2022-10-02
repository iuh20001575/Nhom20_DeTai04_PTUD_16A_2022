package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import layouts.DefaultLayout;

public class QuanLyNhanVien_GUI extends JFrame {

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
					QuanLyNhanVien_GUI frame = new QuanLyNhanVien_GUI();
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
	public QuanLyNhanVien_GUI() {
		new DefaultLayout(this, contentPane, "Quản lý nhân viên", "Quản lý nhân viên");
//		setTitle("Quản lý nhân viên");
//		setResizable(false);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(0, 0, 1100, 610);
//		setLocationRelativeTo(null);
//
//		contentPane = new JPanel();
//		contentPane.setForeground(Color.GRAY);
//		contentPane.setBackground(Color.WHITE);
//		setContentPane(contentPane);
//		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
//		contentPane.setLayout(null);
//
//		JPanel panel = new JPanel();
//		panel.setBackground(new Color(149, 166, 248));
//		panel.setBounds(0, 0, 1100, 65);
//		contentPane.add(panel);
//		panel.setLayout(null);
//		
//		JButton btnNewButton = new JButton("New button");
//		btnNewButton.setBounds(-20, -20, 0, 0);
//		contentPane.add(btnNewButton);
//
//		Button btnMenu = new Button("|||");
//		btnMenu.setBounds(23, 16, 38, 38);
//		btnMenu.setForeground(new Color(149, 166, 248));
//		btnMenu.setFont(new Font("Segoe UI", Font.PLAIN, 24));
//		btnMenu.setBorder(BorderFactory.createEmptyBorder());
//		btnMenu.setBackground(Color.WHITE);
//		btnMenu.setBorderColor(new Color(149, 166, 248));
//		btnMenu.setRadius(8);
//		btnMenu.setFocusable(false);
//		panel.add(btnMenu);
//
//		JLabel lblTitle = new JLabel("THÔNG TIN NHÂN VIÊN");
//		lblTitle.setForeground(Color.WHITE);
//		lblTitle.setBounds(76, 17, 948, 32);
//		lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
//		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
//		panel.add(lblTitle);
	}
}
