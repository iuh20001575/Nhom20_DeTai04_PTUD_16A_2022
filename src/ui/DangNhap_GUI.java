package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import components.button.Button;
import components.panelRound.PanelRound;
import components.textField.TextField;
import keeptoo.KGradientPanel;
import utils.Utils;

public class DangNhap_GUI extends JFrame {

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
					DangNhap_GUI frame = new DangNhap_GUI();
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
	public DangNhap_GUI() {
		setAutoRequestFocus(false);
		setTitle("Đăng nhập");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1100, 610);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		KGradientPanel panel = new KGradientPanel();
		panel.setBounds(0, 0, 1100, 610);
//		panel.setkEndColor(new java.awt.Color(0, 204, 204));
		panel.setkEndColor(Utils.getRGBA(0, 204, 204, 1f));
		panel.setkGradientFocus(600);
//		panel.setkStartColor(new java.awt.Color(153, 0, 153));
		panel.setkStartColor(Utils.getRGBA(153, 0, 153, 0.8f));
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Utils.getRGBA(0, 0, 0, 0));
		panel_1.setBounds(150, 105, 800, 400);
		panel.add(panel_1);
		panel_1.setLayout(null);

		PanelRound panel_2 = new PanelRound();
		panel_2.setRound(16);
		panel_2.setBounds(400, 0, 400, 400);
		panel_2.setBackground(Color.WHITE);
		panel_1.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel = new JLabel("Đăng nhập");
		lblNewLabel.setForeground(Utils.primaryColor);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 34));
		lblNewLabel.setBounds(0, 40, 400, 51);
		panel_2.add(lblNewLabel);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(40, 139, 320, 221);
		panel_2.add(panel_3);
		panel_3.setLayout(null);

		TextField textField = new TextField();
		textField.setLabelText("Mã nhân viên");
		textField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		textField.setBounds(0, 0, 320, 55);
		panel_3.add(textField);
		textField.setColumns(10);

		TextField textField_1 = new TextField();
		textField_1.setLabelText("Mật khẩu");
		textField_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		textField_1.setColumns(10);
		textField_1.setBounds(0, 75, 320, 55);
		panel_3.add(textField_1);

		Button btnNewButton = new Button("Đăng nhập");
		btnNewButton.setIcon(new ImageIcon("Icon\\sign-in.png"));
		btnNewButton.setFocusable(false);
		btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnNewButton.setColor(Utils.primaryColor);
		btnNewButton.setForeground(Color.white);
		btnNewButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnNewButton.setBorderColor(Color.WHITE);
		btnNewButton.setColorOver(Utils.getOpacity(Utils.primaryColor, 0.9f));
		btnNewButton.setColorClick(Utils.getOpacity(Utils.primaryColor, 0.8f));
		btnNewButton.setRadius(8);
		btnNewButton.setBounds(170, 148, 150, 40);
		panel_3.add(btnNewButton);

		Button btnNewButton_1 = new Button("Thoát");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon("Icon\\exit (1).png"));
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.setBorderWidth(1);
		btnNewButton_1.setBorderBtnColor(Utils.primaryColor);
		btnNewButton_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnNewButton_1.setColor(Color.WHITE);
		btnNewButton_1.setForeground(Utils.primaryColor);
		btnNewButton_1.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnNewButton_1.setBorderColor(Color.white);
		btnNewButton_1.setColorOver(Utils.getOpacity(Color.BLACK, 0.025f));
		btnNewButton_1.setColorClick(Utils.getOpacity(Color.BLACK, 0.05f));
		btnNewButton_1.setRadius(8);
		btnNewButton_1.setBounds(0, 148, 150, 40);
		panel_3.add(btnNewButton_1);

		JLabel lblNewLabel_1 = new JLabel("Quên mật khẩu?");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_1.setForeground(Utils.primaryColor);
		lblNewLabel_1.setBounds(210, 200, 110, 21);
		panel_3.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel();
		lblNewLabel_2.setIcon(new ImageIcon("Icon\\login.png"));
		lblNewLabel_2.setBounds(72, 144, 256, 256);
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("KARAOKE ROME");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 36));
		lblNewLabel_3.setBounds(0, 0, 400, 83);
		panel_1.add(lblNewLabel_3);
	}
}
