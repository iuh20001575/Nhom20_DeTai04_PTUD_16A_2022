package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.time.LocalDateTime;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import components.button.Button;
import components.panelRound.PanelRound;
import layouts.DefaultLayout;

public class QuanLyNhanVien_GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JLabel lblNewLabel_1;
	private JTextField textField;

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
		DefaultLayout defaultLayout = new DefaultLayout(this, contentPane, "Quản lý nhân viên", "Quản lý nhân viên");
		contentPane = defaultLayout.getJPanel();
		// setTitle("Quản lý nhân viên");
//		setResizable(false);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(0, 0, 1100, 610);
//		setLocationRelativeTo(null);
//
//		contentPane = new JPanel();
//		contentPane.setForeground(Color.GRAY);
//		contentPane.setBackground(new Color(203, 239, 255));
//		setContentPane(contentPane);
//		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
//		contentPane.setLayout(null);
//
//		JPanel panel = new JPanel();
//		panel.setBackground(new Color(149, 166, 248));
//		panel.setBounds(0, 0, 1086, 65);
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

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(203, 239, 255));
		panel_1.setBounds(16, 83, 1054, 24);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("TÌM KIẾM NHÂN VIÊN THEO TÊN:");
		lblNewLabel.setBounds(0, -1, 299, 27);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		panel_1.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(874, 0, 180, 24);
		panel_1.add(lblNewLabel_1);
		clock();

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(203, 239, 255));
		panel_2.setBounds(16, 117, 1054, 36);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		Button btnNewButton_1 = new Button("Tìm");
		btnNewButton_1.setIcon(new ImageIcon("Icon\\searching.png"));
		btnNewButton_1.setRadius(4);
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setColor(new Color(134, 229, 138));
		btnNewButton_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnNewButton_1.setBounds(904, -2, 150, 40);
		btnNewButton_1.setBorderColor(new Color(203, 239, 255));
		btnNewButton_1.setColorOver(new Color(134, 229, 138));
		btnNewButton_1.setColorClick(new Color(59, 238, 66));
		btnNewButton_1.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel_2.add(btnNewButton_1);

		PanelRound panel_3 = new PanelRound();
		panel_3.setRounded(4);
		panel_3.setBackground(new Color(203, 239, 255));
		panel_3.setBounds(0, 0, 894, 36);
		panel_3.setBorder(new LineBorder(Color.BLACK));
		panel_3.setRound(4);
		panel_2.add(panel_3);
		panel_3.setLayout(null);

		textField = new JTextField();
		textField.setBackground(new Color(203, 239, 255));
		textField.setBorder(new EmptyBorder(0, 0, 0, 0));
		textField.setBounds(9, 1, 876, 34);
		panel_3.add(textField);
		textField.setColumns(10);
	}

	public static void clock() {
		Thread clock = new Thread() {
			@Override
			public void run() {
				for (;;) {
					try {
						LocalDateTime currTime = LocalDateTime.now();
						int day = currTime.getDayOfMonth();
						int month = currTime.getMonthValue();
						int year = currTime.getYear();
						int hour = currTime.getHour();
						int minute = currTime.getMinute();
						int second = currTime.getSecond();
						lblNewLabel_1.setText(String.format("%s/%s/%s | %s:%s:%s", day < 10 ? "0" + day : day,
								month < 10 ? "0" + month : month, year, hour < 10 ? "0" + hour : hour,
								minute < 10 ? "0" + minute : minute, second < 10 ? "0" + second : second));
						sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};

		clock.start();
	}
}
