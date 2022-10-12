package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import components.button.Button;
import components.textField.TextField;
import utils.Utils;

public class DatPhong_GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private TextField textField;
	private TextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DatPhong_GUI frame = new DatPhong_GUI();
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
	public DatPhong_GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);
		setLocationRelativeTo(null);

		JPanel pnlContainer = new JPanel();
		pnlContainer.setBackground(Utils.secondaryColor);
		pnlContainer.setBounds(0, 0, 800, 400);
		contentPane.add(pnlContainer);
		pnlContainer.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Utils.primaryColor);
		panel_1.setBounds(0, 0, 800, 50);
		pnlContainer.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Đặt phòng");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(300, 9, 200, 32);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
		panel_1.add(lblNewLabel);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Utils.secondaryColor);
		panel_2.setBounds(40, 50, 720, 350);
		pnlContainer.add(panel_2);
		panel_2.setLayout(null);

		textField = new TextField();
		textField.setBackground(Utils.secondaryColor);
		textField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		textField.setLabelText("Số điện thoại khách");
		textField.setBounds(0, 0, 280, 55);
		panel_2.add(textField);
		textField.setColumns(10);

		Button btnNewButton = new Button();
		btnNewButton.setFocusable(false);
		btnNewButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnNewButton.setRadius(4);
		btnNewButton.setBorderColor(Utils.secondaryColor);
		btnNewButton.setColor(Utils.primaryColor);
		btnNewButton.setColorOver(Utils.getOpacity(Utils.primaryColor, 0.9f));
		btnNewButton.setColorClick(Utils.getOpacity(Utils.primaryColor, 0.8f));
		btnNewButton.setIcon(new ImageIcon("Icon\\user_searching.png"));
		btnNewButton.setBounds(300, 2, 50, 50);
		panel_2.add(btnNewButton);

		textField_1 = new TextField();
		textField_1.setLabelText("Họ tên khách");
		textField_1.setEditable(false);
		textField_1.setBackground(Utils.secondaryColor);
		textField_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		textField_1.setBounds(370, 0, 350, 55);
		panel_2.add(textField_1);
		textField_1.setColumns(10);
	}
}
