package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.raven.datechooser.DateChooser;

import components.button.Button;
import components.comboBox.ComboBox;
import components.notification.Notification;
import components.notification.Notification.Type;
import components.radio.RadioButtonCustom;
import components.textField.TextField;
import dao.DiaChi_DAO;
import dao.NhanVien_DAO;
import dao.TaiKhoan_DAO;
import entity.NhanVien;
import entity.NhanVien.ChucVu;
import entity.NhanVien.TrangThai;
import entity.Phuong;
import entity.Quan;
import entity.TaiKhoan;
import entity.Tinh;
import utils.Utils;

public class ThemNhanVien_GUI extends JPanel implements ItemListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<String> cmbChucVu;
	private JComboBox<String> cmbPhuong;
	private JComboBox<String> cmbQuan;
	private JComboBox<String> cmbTinh;
	private JComboBox<String> cmbTrangThai;
	private DateChooser dateChoose;
	private DiaChi_DAO diaChi_DAO;
	private boolean isEnabledEventPhuong = false;
	private boolean isEnabledEventQuan = false;
	private boolean isEnabledEventTinh = false;
	private NhanVien nhanVien;
	private NhanVien_DAO nhanVien_DAO;
	private Phuong phuong;
	private Quan quan;
	private RadioButtonCustom radNam;
	private RadioButtonCustom radNu;
	private TaiKhoan_DAO taiKhoan_DAO;
	private Tinh tinh;
	private TextField txtCCCD;
	private TextField txtDiaChiCT;
	private TextField txtHoTen;
	private TextField txtLuong;
	private TextField txtMaNhanVien;
	private TextField txtMatKhau;
	private TextField txtNgaySinh;
	private TextField txtSoDienThoai;
	private JFrame main;

	public static void main(String[] args) {
		Main main = new Main();
		main.addPnlBody(new ThemNhanVien_GUI(main), "Thêm nhân viên", 2, 0);
		main.setVisible(true);
	}

	/**
	 * Create the frame.
	 * 
	 * @wbp.parser.constructor
	 */
	public ThemNhanVien_GUI(JFrame jFrame) {
		nhanVien_DAO = new NhanVien_DAO();
		taiKhoan_DAO = new TaiKhoan_DAO();
		diaChi_DAO = new DiaChi_DAO();
		main = jFrame;

		setBackground(Utils.secondaryColor);
		setBounds(0, 0, 1086, 508);
		setLayout(null);

		JPanel pnlContainer = new JPanel();
		pnlContainer.setBackground(Utils.secondaryColor);
		pnlContainer.setBounds(69, 7, 948, 425);
		this.add(pnlContainer);
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

		txtHoTen = new TextField();
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

		txtCCCD = new TextField();
		txtCCCD.setLineColor(new Color(149, 166, 248));
		txtCCCD.setLabelText("Căn cước công dân");
		txtCCCD.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtCCCD.setColumns(10);
		txtCCCD.setBackground(new Color(203, 239, 255));
		txtCCCD.setBounds(0, 0, 449, 55);
		pnlRow2.add(txtCCCD);

		txtSoDienThoai = new TextField();
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

		txtNgaySinh = new TextField();
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

		radNam = new RadioButtonCustom("Nam");
		radNam.setFocusable(false);
		radNam.setBackground(Utils.secondaryColor);
		radNam.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		radNam.setBounds(0, -2, 59, 21);
		pnlGroupGioiTinh.add(radNam);

		radNu = new RadioButtonCustom("Nữ");
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

		cmbTinh = new ComboBox<>();
		cmbTinh.setModel(new DefaultComboBoxModel<String>());
		cmbTinh.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbTinh.setBackground(Utils.primaryColor);
		cmbTinh.setBounds(4, 29, 200, 36);
		pnlRow4.add(cmbTinh);

		cmbQuan = new ComboBox<>();
		cmbQuan.setModel(new DefaultComboBoxModel<String>());
		cmbQuan.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbQuan.setBackground(new Color(140, 177, 180));
		cmbQuan.setBounds(220, 29, 200, 36);
		pnlRow4.add(cmbQuan);

		cmbPhuong = new ComboBox<>();
		cmbPhuong.setModel(new DefaultComboBoxModel<String>());
		cmbPhuong.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbPhuong.setBackground(new Color(140, 177, 180));
		cmbPhuong.setBounds(440, 29, 200, 36);
		pnlRow4.add(cmbPhuong);

		txtDiaChiCT = new TextField();
		txtDiaChiCT.setLineColor(new Color(149, 166, 248));
		txtDiaChiCT.setLabelText("Địa chỉ cụ thể");
		txtDiaChiCT.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtDiaChiCT.setColumns(10);
		txtDiaChiCT.setBackground(new Color(203, 239, 255));
		txtDiaChiCT.setBounds(660, 10, 288, 55);
		pnlRow4.add(txtDiaChiCT);

		JPanel pnlRow5 = new JPanel();
		pnlRow5.setLayout(null);
		pnlRow5.setBackground(new Color(203, 239, 255));
		pnlRow5.setBounds(0, 290, 948, 55);
		pnlContainer.add(pnlRow5);

		txtLuong = new TextField();
		txtLuong.setLineColor(new Color(149, 166, 248));
		txtLuong.setLabelText("Lương");
		txtLuong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtLuong.setColumns(10);
		txtLuong.setBackground(new Color(203, 239, 255));
		txtLuong.setBounds(0, 0, 449, 55);
		pnlRow5.add(txtLuong);

		txtMatKhau = new TextField();
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
		lblChucVu.setForeground(Utils.labelTextField);
		lblChucVu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblChucVu.setBounds(4, 6, 100, 19);
		pnlChucVu.add(lblChucVu);

		cmbChucVu = new ComboBox<>();
		cmbChucVu.setModel(new DefaultComboBoxModel<String>(new String[] {
				NhanVien.convertChucVuToString(ChucVu.QuanLy), NhanVien.convertChucVuToString(ChucVu.NhanVien) }));
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
		lblTrangThai.setForeground(Utils.labelTextField);
		lblTrangThai.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTrangThai.setBounds(4, 6, 100, 19);
		pnlTrangThai.add(lblTrangThai);

		cmbTrangThai = new ComboBox<>();
		cmbTrangThai.setModel(
				new DefaultComboBoxModel<String>(new String[] { NhanVien.convertTrangThaiToString(TrangThai.DangLam),
						NhanVien.convertTrangThaiToString(TrangThai.NghiLam) }));
		cmbTrangThai.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbTrangThai.setBackground(new Color(140, 177, 180));
		cmbTrangThai.setBounds(4, 29, 200, 36);
		pnlTrangThai.add(cmbTrangThai);

		JPanel pnlActions = new JPanel();
		pnlActions.setBackground(Utils.secondaryColor);
		pnlActions.setBounds(69, 447, 948, 48);
		this.add(pnlActions);
		pnlActions.setLayout(null);

		Button btnThem = new Button("Cập nhật");
		btnThem.setIcon(new ImageIcon("Icon\\unemployed 1.png"));
		btnThem.setText("Thêm");
		btnThem.setRadius(8);
		btnThem.setForeground(Color.WHITE);
		btnThem.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		btnThem.setFocusable(false);
		btnThem.setColorOver(new Color(140, 177, 180));
		btnThem.setColorClick(new Color(140, 177, 180, 204));
		btnThem.setColor(new Color(140, 177, 180));
		btnThem.setBorderColor(new Color(203, 239, 255));
		btnThem.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnThem.setBounds(698, 0, 250, 48);
		pnlActions.add(btnThem);

		Button btnHuy = new Button("Cập nhật");
		btnHuy.setIcon(new ImageIcon("Icon\\cancelled 1.png"));
		btnHuy.setText("Hủy");
		btnHuy.setRadius(8);
		btnHuy.setForeground(Color.BLACK);
		btnHuy.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		btnHuy.setFocusable(false);
		btnHuy.setColorOver(Utils.getOpacity(Color.WHITE, 0.8f));
		btnHuy.setColorClick(Utils.getOpacity(Color.WHITE, 0.6f));
		btnHuy.setColor(Color.WHITE);
		btnHuy.setBorderColor(new Color(203, 239, 255));
		btnHuy.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnHuy.setBounds(0, 0, 250, 48);
		pnlActions.add(btnHuy);

		txtMaNhanVien.setText(nhanVien_DAO.taoMaNhanVien());
		cmbQuan.addItem(Quan.getQuanLabel());
		cmbPhuong.addItem(Phuong.getPhuongLabel());

		setTinhToComboBox();
		cmbQuan.setEnabled(false);
		cmbPhuong.setEnabled(false);

//		Sự kiện txtHoten
		txtHoTen.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtHoTen.setError(false);
			}
		});

//		Sự kiện txtCCCD
		txtCCCD.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtCCCD.setError(false);
			}
		});

//		Sự kiện txtSoDienThoai
		txtSoDienThoai.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtSoDienThoai.setError(false);
			}
		});

//		Sự kiện txtDiaChiCT
		txtDiaChiCT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtDiaChiCT.setError(false);
			}
		});

//		Sự kiện txtLuong
		txtLuong.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtLuong.setError(false);
			}
		});

//		Sự kiện txtMatKhau
		txtMatKhau.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtMatKhau.setError(false);
			}
		});

//		Sự kiện nút thêm
		btnThem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!validator())
					return;
			}
		});

		txtMaNhanVien.setEnabled(false);
		cmbTinh.addItemListener(this);
		cmbQuan.addItemListener(this);
		cmbPhuong.addItemListener(this);
	}

	private NhanVien getNhanVienTuForm() {
		String maNhanVien = txtMaNhanVien.getText();
		String hoTen = txtHoTen.getText().trim();
		String cccd = txtCCCD.getText().trim();
		String soDienThoai = txtSoDienThoai.getText().trim();
		LocalDate ngaySinh = Utils.getLocalDate(txtNgaySinh.getText());
		String diaChiCuThe = txtDiaChiCT.getText().trim();
		String luong = txtLuong.getText().trim();
		double luongDouble = luong.endsWith(" ₫") ? Utils.convertStringToTienTe(luong) : Double.parseDouble(luong);
		String matKhau = txtMatKhau.getText().trim();
		TaiKhoan taiKhoan = new TaiKhoan(maNhanVien, matKhau);
		boolean gioiTinh = radNam.isSelected();
		ChucVu chucVu = NhanVien.convertStringToChucVu((String) cmbChucVu.getSelectedItem());
		TrangThai trangThai = NhanVien.convertStringToTrangThai((String) cmbTrangThai.getSelectedItem());
		return new NhanVien(maNhanVien, hoTen, cccd, soDienThoai, ngaySinh, gioiTinh, tinh, quan, phuong, diaChiCuThe,
				chucVu, luongDouble, taiKhoan, trangThai);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		Object object = e.getSource();
		if (e.getStateChange() != ItemEvent.SELECTED) {
			return;
		}
		if (cmbTinh.equals(object)) {
			if (!isEnabledEventTinh)
				return;
			isEnabledEventQuan = false;
			isEnabledEventPhuong = false;
			String tinhSelected = (String) cmbTinh.getSelectedItem();

			cmbPhuong.setSelectedIndex(0);
			cmbPhuong.setEnabled(false);
			cmbQuan = resizeComboBox(cmbQuan, Quan.getQuanLabel());
			quan = null;
			phuong = null;

			if (tinhSelected.equals(Tinh.getTinhLabel())) {
				cmbQuan.setSelectedIndex(0);
				cmbQuan.setEnabled(false);
				tinh = null;
				return;
			}
			Tinh tinh = diaChi_DAO.getTinh(tinhSelected);
			ThemNhanVien_GUI.this.tinh = tinh;
			setQuanToComboBox(ThemNhanVien_GUI.this.tinh);
			cmbQuan.setEnabled(true);
			isEnabledEventQuan = true;
			isEnabledEventPhuong = true;
		} else if (cmbQuan.equals(object)) {
			if (!isEnabledEventQuan)
				return;
			isEnabledEventPhuong = false;
			isEnabledEventQuan = false;
			String quanSelected = (String) cmbQuan.getSelectedItem();
			cmbPhuong = resizeComboBox(cmbPhuong, Quan.getQuanLabel());
			phuong = null;

			if (quanSelected.equals(Quan.getQuanLabel())) {
				cmbPhuong.setSelectedIndex(0);
				cmbPhuong.setEnabled(false);
				quan = null;
			} else {
				Quan quan = diaChi_DAO.getQuan(tinh, quanSelected);
				ThemNhanVien_GUI.this.quan = quan;
				cmbPhuong.setEnabled(true);
				setPhuongToComboBox(this.quan);
			}

			isEnabledEventPhuong = true;
			isEnabledEventQuan = true;
		} else if (cmbPhuong.equals(object)) {
			if (!isEnabledEventPhuong)
				return;
			isEnabledEventPhuong = false;
			String phuongSelect = (String) cmbPhuong.getSelectedItem();

			if (phuongSelect.equals(Phuong.getPhuongLabel())) {
				phuong = null;
				return;
			}

			Phuong phuong = diaChi_DAO.getPhuong(quan, phuongSelect);
			ThemNhanVien_GUI.this.phuong = phuong;
			isEnabledEventPhuong = false;
		}

	}

	@SuppressWarnings("unchecked")
	private <E> JComboBox<E> resizeComboBox(JComboBox<E> list, String firstLabel) {
		list.removeAllItems();
		list.addItem((E) firstLabel);
		return list;
	}

	private void setNhanVienVaoForm(NhanVien nhanVien) {
		txtMaNhanVien.setText(nhanVien.getMaNhanVien());
		txtCCCD.setText(nhanVien.getCccd());
		txtDiaChiCT.setText(nhanVien.getDiaChiCuThe());
		txtHoTen.setText(nhanVien.getHoTen());
		txtLuong.setText(Utils.formatTienTe(nhanVien.getLuong()));
		TaiKhoan taiKhoan = taiKhoan_DAO.getTaiKhoan(nhanVien.getMaNhanVien());
		txtMatKhau.setText(taiKhoan.getMatKhau());
		txtNgaySinh.setText(Utils.formatDate(nhanVien.getNgaySinh()));
		txtSoDienThoai.setText(nhanVien.getSoDienThoai());
		cmbChucVu.setSelectedItem(NhanVien.convertChucVuToString(nhanVien.getChucVu()));
		cmbTrangThai.setSelectedItem(NhanVien.convertTrangThaiToString(nhanVien.getTrangThai()));
		setTinhToComboBox();
		setQuanToComboBox(tinh);
		setPhuongToComboBox(quan);
		if (nhanVien.isGioiTinh())
			radNam.setSelected(true);
		else
			radNu.setSelected(true);
	}

	private void setPhuongToComboBox(Quan quan) {
		isEnabledEventPhuong = false;

		resizeComboBox(cmbPhuong, Phuong.getPhuongLabel());

		List<Phuong> phuongs = diaChi_DAO.getPhuong(quan);

		phuongs.sort(new Comparator<Phuong>() {

			@Override
			public int compare(Phuong o1, Phuong o2) {
				return o1.getPhuong().compareToIgnoreCase(o2.getPhuong());
			}
		});

		phuongs.forEach(phuong -> cmbPhuong.addItem(phuong.getPhuong()));

		isEnabledEventPhuong = true;
	}

	private void setQuanToComboBox(Tinh tinh) {
		isEnabledEventQuan = false;

		resizeComboBox(cmbQuan, Quan.getQuanLabel());
		List<Quan> quans = diaChi_DAO.getQuan(tinh);
		quans.sort(new Comparator<Quan>() {
			@Override
			public int compare(Quan o1, Quan o2) {
				return o1.getQuan().compareToIgnoreCase(o2.getQuan());
			}
		});
		quans.forEach(quan -> cmbQuan.addItem(quan.getQuan()));

		isEnabledEventQuan = true;
	}

	private void setTinhToComboBox() {
		isEnabledEventTinh = false;

		resizeComboBox(cmbTinh, Tinh.getTinhLabel());

		List<Tinh> tinhs = diaChi_DAO.getTinh();

		tinhs.sort(new Comparator<Tinh>() {
			@Override
			public int compare(Tinh o1, Tinh o2) {
				return o1.getTinh().compareToIgnoreCase(o2.getTinh());
			}
		});

		tinhs.forEach(tinh -> cmbTinh.addItem(tinh.getTinh()));

		isEnabledEventTinh = true;
	}

	private boolean showThongBaoLoi(TextField txt, String message) {
		new Notification(main, Type.ERROR, message).showNotification();
		txt.setError(true);
		txt.requestFocus();
		return false;
	}

	private boolean validator() {
		String vietNamese = Utils.getVietnameseDiacriticCharacters() + "A-Z";
		String vietNameseLower = Utils.getVietnameseDiacriticCharactersLower() + "a-z";

		String hoTen = txtHoTen.getText().trim();

		if (hoTen.length() <= 0)
			return showThongBaoLoi(txtHoTen, "Vui lòng nhập họ tên nhân viên");

		if (!Pattern.matches(
				String.format("[%s][%s]*( [%s][%s]*)+", vietNamese, vietNameseLower, vietNamese, vietNameseLower),
				hoTen))
			return showThongBaoLoi(txtHoTen, "Họ tên phải bắt đầu bằng ký tự hoa và có ít nhất 2 từ");

		String cccd = txtCCCD.getText().trim();

		if (cccd.length() <= 0)
			return showThongBaoLoi(txtCCCD, "Vui lòng nhập số căn cước công dân");

		if (!Pattern.matches("\\d{12}", cccd))
			return showThongBaoLoi(txtCCCD, "Số căn cước công dân phải có 12 ký tự số");

		if (nhanVien_DAO.isCCCDDaTonTai(cccd))
			return showThongBaoLoi(txtCCCD, "Số căn cước công dân đã tồn tại");

		String soDienThoai = txtSoDienThoai.getText().trim();

		if (soDienThoai.length() <= 0)
			return showThongBaoLoi(txtSoDienThoai, "Vui lòng nhập số điện thoại");

		if (!Utils.isSoDienThoai(soDienThoai))
			return showThongBaoLoi(txtSoDienThoai, "Số điện thoại phải bắt đầu bằng số 0, theo sau là 9 ký tự số");

		if (nhanVien_DAO.isSoDienThoaiDaTonTai(soDienThoai))
			return showThongBaoLoi(txtSoDienThoai, "Số điện thoại đã tồn tại");

		String ngaySinh = txtNgaySinh.getText();
		long daysElapsed = java.time.temporal.ChronoUnit.DAYS.between(Utils.getLocalDate(ngaySinh), LocalDate.now());
		boolean isDuTuoi = daysElapsed / (18 * 365) > 0;

		if (!isDuTuoi) {
			new Notification(main, Type.ERROR, "Nhân viên chưa đủ 18 tuổi").showNotification();
			dateChoose.showPopup();
			return false;
		}

		boolean isNamSelected = radNam.isSelected();
		boolean isNuSelected = radNu.isSelected();

		if (!isNamSelected && !isNuSelected) {
			new Notification(main, Type.ERROR, "Vui lòng chọn giới tính").showNotification();
			return false;
		}

		String tinh = (String) cmbTinh.getSelectedItem();

		if (tinh.equals(Tinh.getTinhLabel())) {
			new Notification(main, Type.ERROR, "Vui lòng chọn tỉnh/ thành phố").showNotification();
			cmbTinh.showPopup();
			return false;
		}

		String quan = (String) cmbQuan.getSelectedItem();

		if (quan.equals(Quan.getQuanLabel())) {
			new Notification(main, Type.ERROR, "Vui lòng chọn quận/ huyện").showNotification();
			cmbQuan.showPopup();
			return false;
		}

		String phuong = (String) cmbPhuong.getSelectedItem();

		if (phuong.equals(Phuong.getPhuongLabel())) {
			new Notification(main, Type.ERROR, "Vui lòng chọn phường/ xã").showNotification();
			cmbPhuong.showPopup();
			return false;
		}

		String diaChi = txtDiaChiCT.getText().trim();

		if (diaChi.length() <= 0)
			return showThongBaoLoi(txtDiaChiCT, "Vui lòng nhập địa chỉ");

		String luong = txtLuong.getText().trim();

		if (luong.length() <= 0)
			return showThongBaoLoi(txtLuong, "Vui lòng nhập lương");

		if (!Utils.isDouble(luong))
			return showThongBaoLoi(txtLuong, "Lương chỉ chứa các ký tự số");

		double luongNumber = Double.parseDouble(luong);

		if (luongNumber <= 0)
			return showThongBaoLoi(txtLuong, "Lương phải lớn hơn 0");

		String matKhau = txtMatKhau.getText();

		if (matKhau.length() <= 0)
			return showThongBaoLoi(txtMatKhau, "Vui lòng nhập mật khẩu");

		if (matKhau.length() < 8)
			return showThongBaoLoi(txtMatKhau, "Mật khẩu phảu có ít nhất 8 ký tự");

		if (!Pattern.matches(".*[A-Z].*", matKhau))
			return showThongBaoLoi(txtMatKhau, "Mật khẩu phảu chứa ít nhất 1 ký tự hoa");

		if (!Pattern.matches(".*[a-z].*", matKhau))
			return showThongBaoLoi(txtMatKhau, "Mật khẩu phảu chứa ít nhất 1 ký tự thường");

		if (!Pattern.matches(".*[0-9].*", matKhau))
			return showThongBaoLoi(txtMatKhau, "Mật khẩu phảu chứa ít nhất 1 ký tự số");

		if (!Pattern.matches(".*[^A-Za-z0-9].*", matKhau))
			return showThongBaoLoi(txtMatKhau, "Mật khẩu phảu chứa ít nhất 1 ký tự đặc biệt");

		return true;
	}
}
