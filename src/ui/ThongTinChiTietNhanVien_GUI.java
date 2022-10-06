package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.metal.MetalButtonUI;

import com.raven.datechooser.DateChooser;

import components.button.Button;
import components.radio.RadioButtonCustom;
import components.textField.TextField;
import layouts.DefaultLayout;
import utils.Utils;

public class ThongTinChiTietNhanVien_GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private TextField txtMaNhanVien;
	private DateChooser dateChoose;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThongTinChiTietNhanVien_GUI frame = new ThongTinChiTietNhanVien_GUI();
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
	public ThongTinChiTietNhanVien_GUI() {
		DefaultLayout defaultLayout = new DefaultLayout(this, contentPane, "Thông tin chi tiết nhân viên");
		contentPane = defaultLayout.getJPanel();

		JPanel pnlContainer = new JPanel();
		pnlContainer.setBackground(Utils.secondaryColor);
		pnlContainer.setBounds(69, 72, 948, 425);
		contentPane.add(pnlContainer);
		pnlContainer.setLayout(null);

		JPanel pnlRow1 = new JPanel();
		pnlRow1.setBackground(Utils.secondaryColor);
		pnlRow1.setBounds(0, 0, 948, 55);
		pnlContainer.add(pnlRow1);
		pnlRow1.setLayout(null);

		txtMaNhanVien = new TextField();
		txtMaNhanVien.setLineColor(Utils.lineTextField);
		txtMaNhanVien.setBackground(Utils.secondaryColor);
		txtMaNhanVien.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtMaNhanVien.setLabelText("Mã nhân viên");
		txtMaNhanVien.setBounds(0, 0, 449, 55);
		pnlRow1.add(txtMaNhanVien);
		txtMaNhanVien.setColumns(10);

		TextField txtHoTen = new TextField();
		txtHoTen.setLineColor(Utils.lineTextField);
		txtHoTen.setLabelText("Họ tên");
		txtHoTen.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtHoTen.setColumns(10);
		txtHoTen.setBackground(new Color(203, 239, 255));
		txtHoTen.setBounds(499, 0, 449, 55);
		pnlRow1.add(txtHoTen);

		JPanel pnlRow2 = new JPanel();
		pnlRow2.setLayout(null);
		pnlRow2.setBackground(new Color(203, 239, 255));
		pnlRow2.setBounds(0, 70, 948, 55);
		pnlContainer.add(pnlRow2);

		TextField txtCCCD = new TextField();
		txtCCCD.setLineColor(new Color(149, 166, 248));
		txtCCCD.setLabelText("Căn cước công dân");
		txtCCCD.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtCCCD.setColumns(10);
		txtCCCD.setBackground(new Color(203, 239, 255));
		txtCCCD.setBounds(0, 0, 449, 55);
		pnlRow2.add(txtCCCD);

		TextField txtSoDienThoai = new TextField();
		txtSoDienThoai.setLineColor(new Color(149, 166, 248));
		txtSoDienThoai.setLabelText("Số điện thoại");
		txtSoDienThoai.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtSoDienThoai.setColumns(10);
		txtSoDienThoai.setBackground(new Color(203, 239, 255));
		txtSoDienThoai.setBounds(499, 0, 449, 55);
		pnlRow2.add(txtSoDienThoai);

		JPanel pnlRow3 = new JPanel();
		pnlRow3.setLayout(null);
		pnlRow3.setBackground(new Color(203, 239, 255));
		pnlRow3.setBounds(0, 140, 948, 55);
		pnlContainer.add(pnlRow3);

		TextField txtNgaySinh = new TextField();
		txtNgaySinh.setIcon(new ImageIcon("Icon\\add-event 2.png"));
		txtNgaySinh.setLineColor(new Color(149, 166, 248));
		txtNgaySinh.setLabelText("Ngày sinh");
		txtNgaySinh.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtNgaySinh.setColumns(10);
		txtNgaySinh.setBackground(new Color(203, 239, 255));
		txtNgaySinh.setBounds(0, 0, 449, 55);
		pnlRow3.add(txtNgaySinh);
		dateChoose = new DateChooser();
		dateChoose.setTextRefernce(txtNgaySinh);

		JPanel pnlGioiTinh = new JPanel();
		pnlGioiTinh.setBackground(Utils.secondaryColor);
		pnlGioiTinh.setBounds(499, 0, 449, 55);
		pnlRow3.add(pnlGioiTinh);
		pnlGioiTinh.setLayout(null);

		JLabel lblGioiTinh = new JLabel("Giới tính");
		lblGioiTinh.setForeground(Utils.labelTextField);
		lblGioiTinh.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblGioiTinh.setBounds(4, 6, 60, 19);
		pnlGioiTinh.add(lblGioiTinh);

		JPanel pnlGroupGioiTinh = new JPanel();
		pnlGroupGioiTinh.setBackground(Utils.secondaryColor);
		pnlGroupGioiTinh.setBounds(4, 39, 138, 16);
		pnlGioiTinh.add(pnlGroupGioiTinh);
		pnlGroupGioiTinh.setLayout(null);

		RadioButtonCustom radNam = new RadioButtonCustom("Nam");
		radNam.setFocusable(false);
		radNam.setBackground(Utils.secondaryColor);
		radNam.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		radNam.setBounds(0, -2, 59, 21);
		pnlGroupGioiTinh.add(radNam);

		RadioButtonCustom radNu = new RadioButtonCustom("Nữ");
		radNu.setFocusable(false);
		radNu.setBackground(Utils.secondaryColor);
		radNu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		radNu.setBounds(79, -2, 59, 21);
		pnlGroupGioiTinh.add(radNu);

		ButtonGroup buttonGroupGioiTinh = new ButtonGroup();
		buttonGroupGioiTinh.add(radNam);
		buttonGroupGioiTinh.add(radNu);

		JPanel pnlRow4 = new JPanel();
		pnlRow4.setBackground(Utils.secondaryColor);
		pnlRow4.setBounds(0, 210, 948, 65);
		pnlContainer.add(pnlRow4);
		pnlRow4.setLayout(null);

		JLabel lblDiaChi = new JLabel("Địa chỉ");
		lblDiaChi.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblDiaChi.setBounds(4, 6, 50, 19);
		lblDiaChi.setForeground(Utils.labelTextField);
		pnlRow4.add(lblDiaChi);

		JComboBox<String> cmbTinh = new JComboBox<String>();
		cmbTinh.setModel(new DefaultComboBoxModel<String>(new String[] { "TP. Cần Thơ" }));
		cmbTinh.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbTinh.setBackground(Utils.primaryColor);
		cmbTinh.setBounds(4, 29, 200, 36);
		pnlRow4.add(cmbTinh);

		JComboBox<String> cmbQuan = new JComboBox<String>();
		cmbQuan.setModel(new DefaultComboBoxModel<String>(new String[] { "Phong Điền" }));
		cmbQuan.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbQuan.setBackground(new Color(140, 177, 180));
		cmbQuan.setBounds(220, 29, 200, 36);
		pnlRow4.add(cmbQuan);

		JComboBox<String> cmbHuyen = new JComboBox<String>();
		cmbHuyen.setModel(new DefaultComboBoxModel<String>(new String[] { "Phong Điền" }));
		cmbHuyen.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbHuyen.setBackground(new Color(140, 177, 180));
		cmbHuyen.setBounds(440, 29, 200, 36);
		pnlRow4.add(cmbHuyen);

		TextField txtDiaChiCT_1 = new TextField();
		txtDiaChiCT_1.setLineColor(new Color(149, 166, 248));
		txtDiaChiCT_1.setLabelText("Địa chỉ cụ thể");
		txtDiaChiCT_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtDiaChiCT_1.setColumns(10);
		txtDiaChiCT_1.setBackground(new Color(203, 239, 255));
		txtDiaChiCT_1.setBounds(660, 10, 288, 55);
		pnlRow4.add(txtDiaChiCT_1);

		JPanel pnlRow5 = new JPanel();
		pnlRow5.setLayout(null);
		pnlRow5.setBackground(new Color(203, 239, 255));
		pnlRow5.setBounds(0, 290, 948, 55);
		pnlContainer.add(pnlRow5);

		TextField txtDiaChiCT = new TextField();
		txtDiaChiCT.setLineColor(new Color(149, 166, 248));
		txtDiaChiCT.setLabelText("Lương");
		txtDiaChiCT.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtDiaChiCT.setColumns(10);
		txtDiaChiCT.setBackground(new Color(203, 239, 255));
		txtDiaChiCT.setBounds(0, 0, 449, 55);
		pnlRow5.add(txtDiaChiCT);

		TextField txtMatKhau = new TextField();
		txtMatKhau.setLineColor(new Color(149, 166, 248));
		txtMatKhau.setLabelText("Mật khẩu");
		txtMatKhau.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtMatKhau.setColumns(10);
		txtMatKhau.setBackground(new Color(203, 239, 255));
		txtMatKhau.setBounds(499, 0, 449, 55);
		pnlRow5.add(txtMatKhau);

		JPanel pnlRow6 = new JPanel();
		pnlRow6.setLayout(null);
		pnlRow6.setBackground(new Color(203, 239, 255));
		pnlRow6.setBounds(0, 360, 948, 65);
		pnlContainer.add(pnlRow6);

		JPanel pnlChucVu = new JPanel();
		pnlChucVu.setBackground(Utils.secondaryColor);
		pnlChucVu.setBounds(0, 0, 449, 65);
		pnlRow6.add(pnlChucVu);
		pnlChucVu.setLayout(null);

		JLabel lblChucVu = new JLabel("Chức vụ");
		lblChucVu.setForeground(new Color(150, 150, 150));
		lblChucVu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblChucVu.setBounds(4, 6, 100, 19);
		pnlChucVu.add(lblChucVu);

		JComboBox<String> cmbChucVu = new JComboBox<String>();
		cmbChucVu.setModel(new DefaultComboBoxModel(new String[] { "Nhân viên" }));
		cmbChucVu.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbChucVu.setBackground(new Color(140, 177, 180));
		cmbChucVu.setBounds(4, 29, 200, 36);
		pnlChucVu.add(cmbChucVu);

		JPanel pnlTrangThai = new JPanel();
		pnlTrangThai.setLayout(null);
		pnlTrangThai.setBackground(new Color(203, 239, 255));
		pnlTrangThai.setBounds(499, 0, 449, 65);
		pnlRow6.add(pnlTrangThai);

		JLabel lblTrangThai = new JLabel("Trạng thái");
		lblTrangThai.setForeground(new Color(150, 150, 150));
		lblTrangThai.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTrangThai.setBounds(4, 6, 100, 19);
		pnlTrangThai.add(lblTrangThai);

		JComboBox<String> cmbTrangThai = new JComboBox<String>();
		cmbTrangThai.setModel(new DefaultComboBoxModel(new String[] { "Đang làm" }));
		cmbTrangThai.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbTrangThai.setBackground(new Color(140, 177, 180));
		cmbTrangThai.setBounds(4, 29, 200, 36);
		pnlTrangThai.add(cmbTrangThai);

		JPanel pnlActions = new JPanel();
		pnlActions.setBackground(Utils.secondaryColor);
		pnlActions.setBounds(69, 512, 948, 48);
		contentPane.add(pnlActions);
		pnlActions.setLayout(null);

		Button btnCapNhat = new Button("Cập nhật");
		btnCapNhat.setIcon(new ImageIcon("Icon\\edit 1.png"));
		btnCapNhat.setFocusable(false);
		btnCapNhat.setRadius(8);
		btnCapNhat.setBorderColor(Utils.secondaryColor);
		btnCapNhat.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnCapNhat.setColor(Utils.primaryColor);
		btnCapNhat.setColorOver(Utils.primaryColor);
		btnCapNhat.setColorClick(Utils.getOpacity(Utils.primaryColor, 0.8f));
		btnCapNhat.setForeground(Color.WHITE);
		btnCapNhat.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		btnCapNhat.setBounds(49, 0, 250, 48);
		pnlActions.add(btnCapNhat);

		Button btnHuy = new Button("Hủy");
		btnHuy.setVisible(false);
		btnHuy.setIcon(new ImageIcon(
				"D:\\Code\\stt51_haAnhThao_20001575\\Nhom20_DeTai04_PTUD_16A_2022\\Icon\\cancelled 1.png"));
		btnHuy.setFocusable(false);
		btnHuy.setRadius(8);
		btnHuy.setBorderColor(Utils.secondaryColor);
		btnHuy.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnHuy.setColor(Utils.primaryColor);
		btnHuy.setColorOver(Utils.primaryColor);
		btnHuy.setColorClick(Utils.getOpacity(Utils.primaryColor, 0.8f));
		btnHuy.setForeground(Color.WHITE);
		btnHuy.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		btnHuy.setBounds(49, 0, 250, 48);
		pnlActions.add(btnHuy);

		Button btnLuu = new Button("Lưu");
		btnLuu.setUI(new MetalButtonUI() {
			protected Color getDisabledTextColor() {
				return Color.WHITE;
			}
		});
		btnLuu.setEnabled(false);
		btnLuu.setIcon(new ImageIcon("Icon\\floppy-disk 1.png"));
		btnLuu.setRadius(8);
		btnLuu.setForeground(Color.WHITE);
		btnLuu.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		btnLuu.setFocusable(false);
		btnLuu.setColorOver(new Color(140, 177, 180));
		btnLuu.setColorClick(new Color(140, 177, 180, 204));
		btnLuu.setColor(new Color(140, 177, 180));
		btnLuu.setBorderColor(new Color(203, 239, 255));
		btnLuu.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnLuu.setBounds(349, 0, 250, 48);
		pnlActions.add(btnLuu);

		Button btnNghiViec = new Button("Cập nhật");
		btnNghiViec.setIcon(new ImageIcon("Icon\\unemployed 1.png"));
		btnNghiViec.setText("Nghỉ việc");
		btnNghiViec.setRadius(8);
		btnNghiViec.setForeground(Color.WHITE);
		btnNghiViec.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		btnNghiViec.setFocusable(false);
		btnNghiViec.setColorOver(new Color(140, 177, 180));
		btnNghiViec.setColorClick(new Color(140, 177, 180, 204));
		btnNghiViec.setColor(new Color(140, 177, 180));
		btnNghiViec.setBorderColor(new Color(203, 239, 255));
		btnNghiViec.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnNghiViec.setBounds(648, 0, 250, 48);
		pnlActions.add(btnNghiViec);
	}
}
