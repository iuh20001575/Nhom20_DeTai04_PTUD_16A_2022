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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.metal.MetalButtonUI;

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
import entity.Phuong;
import entity.Quan;
import entity.TaiKhoan;
import entity.Tinh;
import utils.NhanVien;
import utils.Utils;

public class ThongTinCaNhan_GUI extends JPanel implements ItemListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ComboBox<String> cmbPhuong;
	private ComboBox<String> cmbQuan;
	private ComboBox<String> cmbTinh;
	private DateChooser dateChoose;
	private DiaChi_DAO diaChi_DAO;
	private boolean isEnabledEventPhuong;
	private boolean isEnabledEventQuan;
	private boolean isEnabledEventTinh;
	private Main main;
	private entity.NhanVien nhanVien;
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
	private TextField txtMaNhanVien;
	private TextField txtMatKhau;
	private TextField txtNgaySinh;
	private TextField txtSoDienThoai;

	/**
	 * Create the frame.
	 * 
	 * @param main
	 */
	public ThongTinCaNhan_GUI(Main main) {
		nhanVien_DAO = new NhanVien_DAO();
		taiKhoan_DAO = new TaiKhoan_DAO();
		diaChi_DAO = new DiaChi_DAO();
		this.main = main;

		setBackground(Utils.secondaryColor);
		setBounds(0, 0, 1086, 508);
		setLayout(null);

		JPanel pnlRow1 = new JPanel();
		pnlRow1.setBackground(Utils.secondaryColor);
		pnlRow1.setBounds(69, 20, 948, 55);
		this.add(pnlRow1);
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
		pnlRow2.setBounds(69, 95, 948, 55);
		this.add(pnlRow2);

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
		pnlRow3.setBounds(69, 170, 948, 55);
		this.add(pnlRow3);

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
		pnlRow4.setBounds(69, 245, 948, 65);
		this.add(pnlRow4);
		pnlRow4.setLayout(null);

		JLabel lblDiaChi = new JLabel("Địa chỉ");
		lblDiaChi.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblDiaChi.setBounds(4, 6, 50, 19);
		lblDiaChi.setForeground(Utils.labelTextField);
		pnlRow4.add(lblDiaChi);

		cmbTinh = new ComboBox<>();
		cmbTinh.setModel(new DefaultComboBoxModel<String>(new String[] { "TP. Cần Thơ" }));
		cmbTinh.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbTinh.setBackground(Utils.primaryColor);
		cmbTinh.setBounds(4, 29, 200, 36);
		pnlRow4.add(cmbTinh);

		cmbQuan = new ComboBox<>();
		cmbQuan.setModel(new DefaultComboBoxModel<String>(new String[] { "Phong Điền" }));
		cmbQuan.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbQuan.setBackground(new Color(140, 177, 180));
		cmbQuan.setBounds(220, 29, 200, 36);
		pnlRow4.add(cmbQuan);

		cmbPhuong = new ComboBox<>();
		cmbPhuong.setModel(new DefaultComboBoxModel<String>(new String[] { "Phong Điền" }));
		cmbPhuong.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbPhuong.setBackground(new Color(140, 177, 180));
		cmbPhuong.setBounds(440, 29, 200, 36);
		pnlRow4.add(cmbPhuong);

		JPanel pnlRow5 = new JPanel();
		pnlRow5.setLayout(null);
		pnlRow5.setBackground(new Color(203, 239, 255));
		pnlRow5.setBounds(69, 330, 948, 55);
		this.add(pnlRow5);

		txtDiaChiCT = new TextField();
		txtDiaChiCT.setLineColor(new Color(149, 166, 248));
		txtDiaChiCT.setLabelText("Địa chỉ cụ thể");
		txtDiaChiCT.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtDiaChiCT.setColumns(10);
		txtDiaChiCT.setBackground(new Color(203, 239, 255));
		txtDiaChiCT.setBounds(0, 0, 449, 55);
		pnlRow5.add(txtDiaChiCT);

		txtMatKhau = new TextField();
		txtMatKhau.setLineColor(new Color(149, 166, 248));
		txtMatKhau.setLabelText("Mật khẩu");
		txtMatKhau.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtMatKhau.setColumns(10);
		txtMatKhau.setBackground(new Color(203, 239, 255));
		txtMatKhau.setBounds(499, 0, 449, 55);
		pnlRow5.add(txtMatKhau);

		JPanel pnlActions = new JPanel();
		pnlActions.setBackground(Utils.secondaryColor);
		pnlActions.setBounds(69, 427, 948, 48);
		this.add(pnlActions);
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
		btnCapNhat.setBounds(199, 0, 250, 48);
		pnlActions.add(btnCapNhat);

		Button btnHuy = new Button("Hủy");
		btnHuy.setVisible(false);
		btnHuy.setIcon(new ImageIcon("Icon\\cancelled 1.png"));
		btnHuy.setFocusable(false);
		btnHuy.setRadius(8);
		btnHuy.setBorderColor(Utils.secondaryColor);
		btnHuy.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnHuy.setColor(Utils.primaryColor);
		btnHuy.setColorOver(Utils.primaryColor);
		btnHuy.setColorClick(Utils.getOpacity(Utils.primaryColor, 0.8f));
		btnHuy.setForeground(Color.WHITE);
		btnHuy.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		btnHuy.setBounds(199, 0, 250, 48);
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
		btnLuu.setBounds(499, 0, 250, 48);
		pnlActions.add(btnLuu);

		txtMaNhanVien.setEnabled(false);
		setEnabledForm(false);

		this.nhanVien = NhanVien.getNhanVien();
		setNhanVienVaoForm(nhanVien);

		cmbTinh.addItemListener(this);
		cmbQuan.addItemListener(this);
		cmbPhuong.addItemListener(this);

//		Sự kiện txtHoTen
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

//		Sự kiện txtMatKhau
		txtMatKhau.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtMatKhau.setError(false);
			}
		});

//		Sự kiện nút cập nhật
		btnCapNhat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnCapNhat.setVisible(false);
				btnHuy.setVisible(true);
				btnLuu.setEnabled(true);
				setEnabledForm(true);
			}
		});

//		Sự kiện nút hủy
		btnHuy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setErrorAllJTextField(false);
				setNhanVienVaoForm(nhanVien);
				btnCapNhat.setVisible(true);
				btnHuy.setVisible(false);
				btnLuu.setEnabled(false);
				setEnabledForm(false);
				ThongTinCaNhan_GUI.this.main.repaint();
			}
		});

//		Sự kiện nút lưu
		btnLuu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!btnLuu.isEnabled())
					return;

				if (!validator())
					return;

				entity.NhanVien nhanVien = getNhanVienTuForm();
				boolean res;

				res = nhanVien_DAO.capNhatNhanVien(nhanVien);

				if (res) {
					new Notification(main, Type.SUCCESS, "Cập nhật thông tin nhân viên thành công").showNotification();
					btnCapNhat.setVisible(true);
					btnHuy.setVisible(false);
					btnLuu.setEnabled(false);
					setEnabledForm(false);
					ThongTinCaNhan_GUI.this.main.repaint();
				} else {
					new Notification(main, Type.ERROR, "Cập nhật thông tin nhân viên thất bại").showNotification();
				}
			}
		});
	}

	private void setErrorAllJTextField(boolean b) {
		txtCCCD.setError(b);
		txtDiaChiCT.setError(b);
		txtHoTen.setError(b);
		txtMatKhau.setError(b);
		txtNgaySinh.setError(b);
		txtSoDienThoai.setError(b);
	}

	/**
	 * Get nhân viên từ form
	 * 
	 * @return nhân viên
	 */
	private entity.NhanVien getNhanVienTuForm() {
		String maNhanVien = txtMaNhanVien.getText();
		String hoTen = txtHoTen.getText().trim();
		String cccd = txtCCCD.getText().trim();
		String soDienThoai = txtSoDienThoai.getText().trim();
		LocalDate ngaySinh = Utils.getLocalDate(txtNgaySinh.getText());
		String diaChiCuThe = txtDiaChiCT.getText().trim();
		String matKhau = txtMatKhau.getText().trim();
		TaiKhoan taiKhoan = new TaiKhoan(maNhanVien, matKhau);
		boolean gioiTinh = radNam.isSelected();
		return new entity.NhanVien(maNhanVien, hoTen, cccd, soDienThoai, ngaySinh, gioiTinh, tinh, quan, phuong,
				diaChiCuThe, nhanVien.getChucVu(), nhanVien.getLuong(), taiKhoan, nhanVien.getTrangThai());
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
			cmbQuan = (ComboBox<String>) resizeComboBox(cmbQuan, Quan.getQuanLabel());
			quan = null;
			phuong = null;

			if (tinhSelected.equals(Tinh.getTinhLabel())) {
				cmbQuan.setSelectedIndex(0);
				cmbQuan.setEnabled(false);
				tinh = null;
				return;
			}
			Tinh tinh = diaChi_DAO.getTinh(tinhSelected);
			this.tinh = tinh;
			setQuanToComboBox(this.tinh);
			cmbQuan.setEnabled(true);
			isEnabledEventQuan = true;
			isEnabledEventPhuong = true;
		} else if (cmbQuan.equals(object)) {
			if (!isEnabledEventQuan)
				return;
			isEnabledEventPhuong = false;
			isEnabledEventQuan = false;
			String quanSelected = (String) cmbQuan.getSelectedItem();
			cmbPhuong = (ComboBox<String>) resizeComboBox(cmbPhuong, Quan.getQuanLabel());
			phuong = null;

			if (quanSelected.equals(Quan.getQuanLabel())) {
				cmbPhuong.setSelectedIndex(0);
				cmbPhuong.setEnabled(false);
				quan = null;
			} else {
				Quan quan = diaChi_DAO.getQuan(tinh, quanSelected);
				this.quan = quan;
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
			this.phuong = phuong;
			isEnabledEventPhuong = false;
		}

	}

	@SuppressWarnings("unchecked")
	/**
	 * Remove tất cả các item của JComboBox và thêm firstLabel vào JComboBox
	 * 
	 * @param <E>
	 * @param list
	 * @param firstLabel
	 * @return
	 */
	private <E> JComboBox<E> resizeComboBox(JComboBox<E> list, String firstLabel) {
		list.removeAllItems();
		list.addItem((E) firstLabel);
		return list;
	}

	/**
	 * set Enabled input form
	 * 
	 * @param b
	 */
	private void setEnabledForm(boolean b) {
		txtCCCD.setEnabled(b);
		txtDiaChiCT.setEnabled(b);
		txtHoTen.setEnabled(b);
		txtMatKhau.setEnabled(b);
		txtNgaySinh.setEnabled(b);
		txtSoDienThoai.setEnabled(b);
		radNam.setEnabled(b);
		radNu.setEnabled(b);
		cmbTinh.setEnabled(b);
		cmbQuan.setEnabled(b);
		cmbPhuong.setEnabled(b);
	}

	/**
	 * Set nhân viên vào form
	 * 
	 * @param nhanVien
	 */
	private void setNhanVienVaoForm(entity.NhanVien nhanVien) {
		txtMaNhanVien.setText(nhanVien.getMaNhanVien());
		txtCCCD.setText(nhanVien.getCccd());
		txtDiaChiCT.setText(nhanVien.getDiaChiCuThe());
		txtHoTen.setText(nhanVien.getHoTen());
		TaiKhoan taiKhoan = taiKhoan_DAO.getTaiKhoan(nhanVien.getMaNhanVien());
		txtMatKhau.setText(taiKhoan.getMatKhau());
		txtNgaySinh.setText(Utils.formatDate(nhanVien.getNgaySinh()));
		txtSoDienThoai.setText(nhanVien.getSoDienThoai());
		setTinhToComboBox();
		setQuanToComboBox(tinh);
		setPhuongToComboBox(quan);
		if (nhanVien.isGioiTinh())
			radNam.setSelected(true);
		else
			radNu.setSelected(true);
	}

	/**
	 * Set phường vào JComboBox
	 * 
	 * @param quan
	 */
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

		phuongs.forEach(phuong -> {
			int index = phuongs.indexOf(phuong);
			cmbPhuong.addItem(phuong.getPhuong());
			if (phuong.getId().equals(this.nhanVien.getPhuong().getId())) {
				cmbPhuong.setSelectedIndex(index + 1);
				this.phuong = phuong;
			}
		});

		isEnabledEventPhuong = true;
	}

	/**
	 * Set quận vào JComboBox
	 * 
	 * @param tinh
	 */
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
		quans.forEach(quan -> {
			int index = quans.indexOf(quan);
			cmbQuan.addItem(quan.getQuan());
			if (quan.getId().equals(this.nhanVien.getQuan().getId())) {
				cmbQuan.setSelectedIndex(index + 1);
				this.quan = quan;
			}
		});

		isEnabledEventQuan = true;
	}

	/**
	 * Set tỉnh vào JComboBox
	 */
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

		tinhs.forEach(tinh -> {
			int index = tinhs.indexOf(tinh);
			cmbTinh.addItem(tinh.getTinh());
			if (tinh.getId().equals(this.nhanVien.getTinh().getId())) {
				cmbTinh.setSelectedIndex(index + 1);
				this.tinh = tinh;
			}
		});

		isEnabledEventTinh = true;
	}

	/**
	 * Hiển thị thông báo lỗi và focus các JTextField
	 * 
	 * @param txt     JtextField cần focus
	 * @param message thông báo lỗi
	 * @return false
	 */
	private boolean showThongBaoLoi(TextField txt, String message) {
		new Notification(main, Type.ERROR, message).showNotification();
		txt.setError(true);
		txt.requestFocus();
		return false;
	}

	/**
	 * Kiểm tra thông tin nhân viên từ form
	 * 
	 * @return
	 */
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

		if (nhanVien_DAO.isCCCDDaTonTai(nhanVien, cccd))
			return showThongBaoLoi(txtCCCD, "Số căn cước công dân đã tồn tại");

		String soDienThoai = txtSoDienThoai.getText().trim();

		if (soDienThoai.length() <= 0)
			return showThongBaoLoi(txtSoDienThoai, "Vui lòng nhập số điện thoại");

		if (!Utils.isSoDienThoai(soDienThoai))
			return showThongBaoLoi(txtSoDienThoai, "Số điện thoại phải bắt đầu bằng số 0, theo sau là 9 ký tự số");

		if (nhanVien_DAO.isSoDienThoaiDaTonTai(nhanVien, soDienThoai))
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

		String matKhau = txtMatKhau.getText();

		if (matKhau.length() <= 0)
			return showThongBaoLoi(txtMatKhau, "Vui lòng nhập mật khẩu");

		if (matKhau.length() < 8)
			return showThongBaoLoi(txtMatKhau, "Mật khẩu phải có ít nhất 8 ký tự");

		if (!Pattern.matches(".*[A-Z]+.*", matKhau))
			return showThongBaoLoi(txtMatKhau, "Mật khẩu phải có ít nhất 1 ký tự hoa");

		if (!Pattern.matches(".*[a-z]+.*", matKhau))
			return showThongBaoLoi(txtMatKhau, "Mật khẩu phải có ít nhất 1 ký tự thường");

		if (!Pattern.matches(".*[0-9]+.*", matKhau))
			return showThongBaoLoi(txtMatKhau, "Mật khẩu phải có ít nhất 1 ký tự số");

		if (!Pattern.matches(".*[^A-Za-z0-9]+.*", matKhau))
			return showThongBaoLoi(txtMatKhau, "Mật khẩu phải có ít nhất 1 ký tự đặc biệt");

		return true;
	}
}
