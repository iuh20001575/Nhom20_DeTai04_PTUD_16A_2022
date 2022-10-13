package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import components.button.Button;
import components.notification.Notification;
import components.textField.TextField;
import connectDB.ConnectDB;
import dao.DatPhong_DAO;
import dao.KhachHang_DAO;
import entity.KhachHang;
import entity.Phong;
import utils.Utils;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

public class DatPhong_GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private TextField textField;
	private TextField textField_1;
	private KhachHang_DAO khachHang_DAO;
	private KhachHang khachHang;
	private DatPhong_DAO datPhong_DAO;

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
		try {
			new ConnectDB().connect();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JFrame _this = this;
		khachHang_DAO = new KhachHang_DAO();
		datPhong_DAO = new DatPhong_DAO();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);
		setLocationRelativeTo(null);

		JPanel pnlContainer = new JPanel();
		pnlContainer.setBackground(Utils.secondaryColor);
		pnlContainer.setBounds(0, 0, 800, 415);
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
		panel_2.setBounds(40, 50, 720, 360);
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
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String soDienThoai = textField.getText().trim();

				if (isSoDienThoai(soDienThoai)) {
					khachHang = khachHang_DAO.getKhachHang(soDienThoai);

					if (khachHang != null) {
						textField_1.setText(khachHang.getHoTen());
					} else {
						System.out.println("Khách hàng khôn tồn tại");
					}
				} else {
					new Notification(_this, components.notification.Notification.Type.ERROR,
							"Số điện thoại không hợp lệ").showNotification();
					textField.setError(true);
				}
			}
		});
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

		JPanel panel = new JPanel();
		panel.setBackground(Utils.secondaryColor);
		panel.setBounds(0, 70, 720, 225);
		panel_2.add(panel);
		panel.setLayout(null);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Utils.secondaryColor);
		panel_4.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Ph\u00F2ng \u0111\u00E3 ch\u1ECDn", TitledBorder.LEADING, TitledBorder.TOP,
				new Font("Segoe UI", Font.PLAIN, 12), new Color(0, 0, 0)));
		panel_4.setBounds(620, 0, 100, 225);
		panel.add(panel_4);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Utils.secondaryColor);
		panel_3.setBounds(0, 311, 720, 36);
		panel_2.add(panel_3);
		panel_3.setLayout(null);

		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				khachHang = null;
				textField_1.setText("");
				textField.setError(false);
			}
		});

		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textField.setError(false);
			}
		});

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				List<Phong> list = datPhong_DAO.getPhongDatNgay();
				System.out.println(list);
			}
		});
	}

	private boolean isSoDienThoai(String soDienThoai) {
		Pattern pattern = Pattern.compile("0[0-9]{9}");
		Matcher matcher = pattern.matcher(soDienThoai);
		return matcher.matches();
	}
}
