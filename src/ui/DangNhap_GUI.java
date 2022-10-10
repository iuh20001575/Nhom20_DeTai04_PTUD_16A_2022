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

		KGradientPanel pnlContainer = new KGradientPanel();
		pnlContainer.setBounds(0, 0, 1100, 610);
//		panel.setkEndColor(new java.awt.Color(0, 204, 204));
		pnlContainer.setkEndColor(Utils.getRGBA(0, 204, 204, 1f));
		pnlContainer.setkGradientFocus(600);
//		panel.setkStartColor(new java.awt.Color(153, 0, 153));
		pnlContainer.setkStartColor(Utils.getRGBA(153, 0, 153, 0.8f));
		contentPane.add(pnlContainer);
		pnlContainer.setLayout(null);

		JPanel pnlDangNhap = new JPanel();
		pnlDangNhap.setBackground(Utils.getRGBA(0, 0, 0, 0));
		pnlDangNhap.setBounds(150, 105, 800, 400);
		pnlContainer.add(pnlDangNhap);
		pnlDangNhap.setLayout(null);

		PanelRound pnlFormDangNhap = new PanelRound();
		pnlFormDangNhap.setRounded(16);
		pnlFormDangNhap.setBounds(400, 0, 400, 400);
		pnlFormDangNhap.setBackground(Color.WHITE);
		pnlDangNhap.add(pnlFormDangNhap);
		pnlFormDangNhap.setLayout(null);

		JLabel lblDangNhap = new JLabel("Đăng nhập");
		lblDangNhap.setForeground(Utils.primaryColor);
		lblDangNhap.setHorizontalAlignment(SwingConstants.CENTER);
		lblDangNhap.setFont(new Font("Segoe UI", Font.BOLD, 34));
		lblDangNhap.setBounds(0, 40, 400, 51);
		pnlFormDangNhap.add(lblDangNhap);

		JPanel pnlForm = new JPanel();
		pnlForm.setBackground(Color.WHITE);
		pnlForm.setBounds(40, 139, 320, 221);
		pnlFormDangNhap.add(pnlForm);
		pnlForm.setLayout(null);

		TextField txtMaNhanVien = new TextField();
		txtMaNhanVien.setLabelText("Mã nhân viên");
		txtMaNhanVien.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtMaNhanVien.setBounds(0, 0, 320, 55);
		pnlForm.add(txtMaNhanVien);
		txtMaNhanVien.setColumns(10);

		TextField txtMatKhau = new TextField();
		txtMatKhau.setLabelText("Mật khẩu");
		txtMatKhau.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtMatKhau.setColumns(10);
		txtMatKhau.setBounds(0, 75, 320, 55);
		pnlForm.add(txtMatKhau);

		Button btnDangNhap = new Button("Đăng nhập");
		btnDangNhap.setIcon(new ImageIcon("Icon\\sign-in.png"));
		btnDangNhap.setFocusable(false);
		btnDangNhap.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnDangNhap.setColor(Utils.primaryColor);
		btnDangNhap.setForeground(Color.white);
		btnDangNhap.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnDangNhap.setBorderColor(Color.WHITE);
		btnDangNhap.setColorOver(Utils.getOpacity(Utils.primaryColor, 0.9f));
		btnDangNhap.setColorClick(Utils.getOpacity(Utils.primaryColor, 0.8f));
		btnDangNhap.setRadius(8);
		btnDangNhap.setBounds(170, 148, 150, 40);
		pnlForm.add(btnDangNhap);

		Button btnThoat = new Button("Thoát");
		btnThoat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		btnThoat.setIcon(new ImageIcon("Icon\\exit (1).png"));
		btnThoat.setFocusable(false);
		btnThoat.setBorderWidth(1);
		btnThoat.setBorderBtnColor(Utils.primaryColor);
		btnThoat.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnThoat.setColor(Color.WHITE);
		btnThoat.setForeground(Utils.primaryColor);
		btnThoat.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnThoat.setBorderColor(Color.white);
		btnThoat.setColorOver(Utils.getOpacity(Color.BLACK, 0.025f));
		btnThoat.setColorClick(Utils.getOpacity(Color.BLACK, 0.05f));
		btnThoat.setRadius(8);
		btnThoat.setBounds(0, 148, 150, 40);
		pnlForm.add(btnThoat);

		JLabel lblQuenMatKhau = new JLabel("Quên mật khẩu?");
		lblQuenMatKhau.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblQuenMatKhau.setForeground(Utils.primaryColor);
		lblQuenMatKhau.setBounds(210, 200, 110, 21);
		pnlForm.add(lblQuenMatKhau);

		JLabel lblBackground = new JLabel();
		lblBackground.setIcon(new ImageIcon("Icon\\login.png"));
		lblBackground.setBounds(72, 144, 256, 256);
		pnlDangNhap.add(lblBackground);

		JLabel lblTieuDe = new JLabel("KARAOKE ROME");
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setForeground(Color.WHITE);
		lblTieuDe.setFont(new Font("Segoe UI", Font.BOLD, 36));
		lblTieuDe.setBounds(0, 0, 400, 83);
		pnlDangNhap.add(lblTieuDe);
	}
}
