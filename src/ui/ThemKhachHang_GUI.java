package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.plaf.metal.MetalButtonUI;

import com.raven.datechooser.DateChooser;

import components.button.Button;
import components.notification.Notification;
import components.notification.Notification.Type;
import components.radio.RadioButtonCustom;
import components.textField.TextField;
import dao.DiaChi_DAO;
import dao.KhachHang_DAO;
import entity.KhachHang;
import entity.Phuong;
import entity.Quan;
import entity.Tinh;
import utils.Utils;

public class ThemKhachHang_GUI extends JPanel implements ItemListener, MouseListener {
	private static final long serialVersionUID = 1L;

	private JComboBox<String> cmbPhuong;
	private JComboBox<String> cmbQuan;
	private JComboBox<String> cmbTinh;
	private DateChooser dateChoose;
	private DiaChi_DAO DiaChi_DAO;
	private JFrame jFrameParent = null;
	private KhachHang_DAO khachHang_DAO;
	private Main main;
	private Phuong phuong;
	private Quan quan;
	private RadioButtonCustom radNam, radNu;
	private Tinh tinh;
	private TextField txtCCCD;
	private TextField txtDiaChiCT;
	private TextField txtMa;
	private TextField txtNgaySinh;
	private TextField txtSDT;
	private TextField txtTen;

	/**
	 * @wbp.parser.constructor
	 */
	public ThemKhachHang_GUI(Main main) {
		this.main = main;
		khachHang_DAO = new KhachHang_DAO();
		DiaChi_DAO = new DiaChi_DAO();
		int padding = (int) Math.floor((Utils.getBodyHeight() - 371) * 1.0 / 7);
		int top = padding;
		int left = Utils.getLeft(792);

		setBackground(new Color(203, 239, 255));
		setBounds(0, 0, Utils.getScreenWidth(), Utils.getBodyHeight());
		setLayout(null);

		JPanel pnlRow1 = new JPanel();
		pnlRow1.setBackground(Utils.secondaryColor);
		pnlRow1.setBounds(left, top, 792, 55);
		top += padding + 55;
		add(pnlRow1);
		pnlRow1.setLayout(null);

		txtMa = new TextField();
		txtMa.setEditable(false);
		txtMa.setLabelText("Mã khách hàng:");
		txtMa.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtMa.setBackground(new Color(203, 239, 255));
		txtMa.setBounds(0, 0, 371, 55);
		pnlRow1.add(txtMa);

		txtTen = new TextField();
		txtTen.setLabelText("Họ tên khách hàng:");
		txtTen.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTen.setBackground(new Color(203, 239, 255));
		txtTen.setBounds(421, 0, 371, 55);
		pnlRow1.add(txtTen);

		JPanel pnlRow2 = new JPanel();
		pnlRow2.setBackground(Utils.secondaryColor);
		pnlRow2.setBounds(left, top, 792, 55);
		top += padding + 55;
		add(pnlRow2);
		pnlRow2.setLayout(null);

		txtCCCD = new TextField();
		txtCCCD.setLabelText("Căn cước công dân:");
		txtCCCD.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtCCCD.setBackground(new Color(203, 239, 255));
		txtCCCD.setBounds(0, 0, 371, 55);
		pnlRow2.add(txtCCCD);

		txtNgaySinh = new TextField();
		txtNgaySinh.setIcon(new ImageIcon("Icon\\add-event 2.png"));
		txtNgaySinh.setLineColor(new Color(149, 166, 248));
		txtNgaySinh.setLabelText("Ngày sinh:");
		txtNgaySinh.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtNgaySinh.setColumns(10);
		txtNgaySinh.setBackground(new Color(203, 239, 255));
		txtNgaySinh.setBounds(421, 0, 371, 55);
		pnlRow2.add(txtNgaySinh);
		dateChoose = new DateChooser();
		dateChoose.setDateFormat("dd/MM/yyyy");
		dateChoose.setTextRefernce(txtNgaySinh);

		JPanel pnlRow3 = new JPanel();
		pnlRow3.setBackground(Utils.secondaryColor);
		pnlRow3.setBounds(left, top, 792, 55);
		top += padding + 55;
		add(pnlRow3);
		pnlRow3.setLayout(null);

		JPanel pnlGioiTinh = new JPanel();
		pnlGioiTinh.setBackground(Utils.secondaryColor);
		pnlGioiTinh.setBounds(0, 0, 371, 55);
		pnlRow3.add(pnlGioiTinh);
		pnlGioiTinh.setLayout(null);

		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setForeground(Utils.labelTextField);
		lblGioiTinh.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblGioiTinh.setBounds(2, 0, 371, 19);
		pnlGioiTinh.add(lblGioiTinh);

		JPanel pnlGroupGioiTinh = new JPanel();
		pnlGroupGioiTinh.setBackground(Utils.secondaryColor);
		pnlGroupGioiTinh.setBounds(2, 30, 138, 16);
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
		radNam.setSelected(true);

		ButtonGroup buttonGroupGioiTinh = new ButtonGroup();
		buttonGroupGioiTinh.add(radNam);
		buttonGroupGioiTinh.add(radNu);

		txtSDT = new TextField();
		txtSDT.setLabelText("Số điện thoại:");
		txtSDT.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtSDT.setBackground(new Color(203, 239, 255));
		txtSDT.setBounds(421, 0, 371, 55);
		pnlRow3.add(txtSDT);

		JPanel pnlRow4 = new JPanel();
		pnlRow4.setBackground(Utils.secondaryColor);
		pnlRow4.setBounds(left, top, 792, 71);
		top += padding + 71;
		add(pnlRow4);
		pnlRow4.setLayout(null);

		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblDiaChi.setBounds(0, 0, 200, 19);
		lblDiaChi.setForeground(Utils.labelTextField);
		pnlRow4.add(lblDiaChi);

		cmbTinh = new JComboBox<String>();
		ArrayList<Tinh> listTinh = (ArrayList<Tinh>) DiaChi_DAO.getTinh();
		for (Tinh tinh : listTinh) {
			cmbTinh.addItem(tinh.getTinh());
		}
		cmbTinh.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbTinh.setBackground(Utils.primaryColor);
		cmbTinh.setBounds(0, 35, 220, 36);
		pnlRow4.add(cmbTinh);

		cmbQuan = new JComboBox<String>();
		String tinhSelected = (String) cmbTinh.getSelectedItem();
		Tinh tinh = DiaChi_DAO.getTinh(tinhSelected);
		ThemKhachHang_GUI.this.tinh = tinh;
		ArrayList<Quan> listQuan = (ArrayList<Quan>) DiaChi_DAO.getQuan(ThemKhachHang_GUI.this.tinh);
		for (Quan quan : listQuan) {
			cmbQuan.addItem(quan.getQuan());
		}

		cmbQuan.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbQuan.setBackground(new Color(140, 177, 180));
		cmbQuan.setBounds(250, 35, 220, 36);
		pnlRow4.add(cmbQuan);

		cmbPhuong = new JComboBox<String>();
		cmbQuan.setModel(new DefaultComboBoxModel<String>());
		// cmbPhuong.setModel(new DefaultComboBoxModel<String>(new String[] { "Phong
		// Điền" }));
		cmbPhuong.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbPhuong.setBackground(new Color(140, 177, 180));
		cmbPhuong.setBounds(500, 35, 220, 36);
		pnlRow4.add(cmbPhuong);

		JPanel pnlRow5 = new JPanel();
		pnlRow5.setBackground(Utils.secondaryColor);
		pnlRow5.setBounds(left, top, 792, 55);
		top += padding + 55;
		add(pnlRow5);
		pnlRow5.setLayout(null);

		txtDiaChiCT = new TextField();
		txtDiaChiCT.setLineColor(new Color(149, 200, 248));
		txtDiaChiCT.setLabelText("Địa chỉ cụ thể");
		txtDiaChiCT.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtDiaChiCT.setColumns(10);
		txtDiaChiCT.setBackground(new Color(203, 239, 255));
		txtDiaChiCT.setBounds(0, 0, 371, 55);
		pnlRow5.add(txtDiaChiCT);

		JPanel pnlActions = new JPanel();
		pnlActions.setBackground(Utils.secondaryColor);
		pnlActions.setBounds(left, top, 792, 50);
		add(pnlActions);
		pnlActions.setLayout(null);

		Button btnLuu = new Button("Lưu");
		btnLuu.setUI(new MetalButtonUI() {
			protected Color getDisabledTextColor() {
				return Color.WHITE;
			}
		});
		btnLuu.setIcon(new ImageIcon("Icon\\floppy-disk 1.png"));
		btnLuu.setRadius(8);
		btnLuu.setForeground(Color.WHITE);
		btnLuu.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		btnLuu.setColorOver(new Color(140, 177, 180));
		btnLuu.setColorClick(new Color(140, 177, 180, 204));
		btnLuu.setColor(new Color(140, 177, 180));
		btnLuu.setBorderColor(new Color(203, 239, 255));
		btnLuu.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnLuu.setBounds(421, 0, 250, 50);
		pnlActions.add(btnLuu);

		btnLuu.addMouseListener(this);

		Button btnHuy = new Button("Hủy");
		btnHuy.setUI(new MetalButtonUI() {
			protected Color getDisabledTextColor() {
				return Color.WHITE;
			}
		});
		btnHuy.setIcon(new ImageIcon("Icon\\cancelled 1.png"));
		btnHuy.setRadius(8);
		btnHuy.setForeground(Color.WHITE);
		btnHuy.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		btnHuy.setColorOver(new Color(140, 177, 180));
		btnHuy.setColorClick(new Color(140, 177, 180, 204));
		btnHuy.setColor(new Color(140, 177, 180));
		btnHuy.setBorderColor(new Color(203, 239, 255));
		btnHuy.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnHuy.setBounds(121, 0, 250, 50);
		pnlActions.add(btnHuy);

		btnHuy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				main.backPanel();
			}
		});

		cmbTinh.addItemListener(this);
		cmbQuan.addItemListener(this);
		cmbPhuong.addItemListener(this);
		
//		Sự kiện txtTen
		txtTen.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtTen.setError(false);
			}
		});

//		Sự kiện txtCCCD
		txtCCCD.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtCCCD.setError(false);
			}
		});

//		Sự kiện txtSDT
		txtSDT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtSDT.setError(false);
			}
		});

//		Sự kiện txtDiaChiCT
		txtDiaChiCT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtDiaChiCT.setError(false);
			}
		});



		
//		Sự kiện nút lưu
		btnLuu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!validator())
					return;
				KhachHang khachHang = getKhachHangTuForm();
				if (khachHang_DAO.themKhachHang(khachHang)) {
					new Notification(main, components.notification.Notification.Type.SUCCESS,
							"Đã thêm khách hàng mới thành công").showNotification();
					xoaRong();
					txtMa.setText(khachHang_DAO.getMaKhachHang());
					repaint();
					if (jFrameParent != null) {
						jFrameParent.setVisible(false);
						jFrameParent.setVisible(true);
						main.setVisible(false);
					}
				}
				else
					new Notification(main, Type.ERROR, "Thêm khách hàng thất bại").showNotification();
				

			}
		});
		

		addAncestorListener(new AncestorListener() {

			@Override
			public void ancestorAdded(AncestorEvent event) {
				// TODO Auto-generated method stub
				dateChoose.showPopup();
				dateChoose.hidePopup();
			}

			@Override
			public void ancestorMoved(AncestorEvent event) {
				// TODO Auto-generated method stub

			}

			@Override
			public void ancestorRemoved(AncestorEvent event) {
				// TODO Auto-generated method stub

			}
		});

		txtMa.setText(khachHang_DAO.getMaKhachHang());
	}

	public ThemKhachHang_GUI(Main main, JFrame jFrameParent, String soDienThoai) {
		this(main);
		setjFrameParent(jFrameParent);
		txtSDT.setText(soDienThoai);
		txtSDT.setEnabled(false);
	}

	/**
	 * Get nhân viên từ form
	 *
	 * @return nhanVien
	 */
	private KhachHang getKhachHangTuForm() {
		String sma = txtMa.getText();
		String sten = txtTen.getText();
		LocalDate sngaySinh = Utils.getLocalDate(txtNgaySinh.getText());
		Boolean gioiTinh = radNam.isSelected();
		String sCCCD = txtCCCD.getText();
		String sSDT = txtSDT.getText();
		String sTinh = cmbTinh.getSelectedItem().toString();
		String sQuan = cmbQuan.getSelectedItem().toString();

		String sPhuong = cmbPhuong.getSelectedItem().toString();
		Tinh tinhSelect = DiaChi_DAO.getTinh(sTinh);
		quan = DiaChi_DAO.getQuan(tinhSelect, sQuan);
		phuong = DiaChi_DAO.getPhuong(quan, sPhuong);
		String sDCCT = txtDiaChiCT.getText();

		return new KhachHang(sma, sten, sCCCD, sngaySinh, gioiTinh, sSDT, tinhSelect, quan, phuong,
				sDCCT, false);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		Object object = e.getSource();
		if (e.getStateChange() != ItemEvent.SELECTED) {
			return;
		}
		if (cmbTinh.equals(object)) {

			String tinhSelected = (String) cmbTinh.getSelectedItem();
			cmbPhuong.setEnabled(false);
			cmbQuan = resizeComboBox(cmbQuan);
			quan = null;
			phuong = null;
			Tinh tinh = DiaChi_DAO.getTinh(tinhSelected);
			ThemKhachHang_GUI.this.tinh = tinh;
			ArrayList<Quan> listQuan = (ArrayList<Quan>) DiaChi_DAO.getQuan(ThemKhachHang_GUI.this.tinh);
			listQuan.sort(new Comparator<Quan>() {
				@Override
				public int compare(Quan o1, Quan o2) {
					return o1.getQuan().compareToIgnoreCase(o2.getQuan());
				}
			});
			for (Quan quan : listQuan) {
				cmbQuan.addItem(quan.getQuan());
			}
			cmbQuan.setSelectedItem(listQuan.get(1));
			cmbQuan.setEnabled(true);

		}
		if (cmbQuan.equals(object)) {
			String tinhSelected = (String) cmbTinh.getSelectedItem();
			String quanSelected = (String) cmbQuan.getSelectedItem();
			cmbPhuong = resizeComboBox(cmbPhuong);
			phuong = null;
			Tinh tinh = DiaChi_DAO.getTinh(tinhSelected);
			Quan quan = DiaChi_DAO.getQuan(tinh, quanSelected);
			ThemKhachHang_GUI.this.quan = quan;
			ArrayList<Phuong> listPhuong = (ArrayList<Phuong>) DiaChi_DAO.getPhuong(ThemKhachHang_GUI.this.quan);
			listPhuong.sort(new Comparator<Phuong>() {

				@Override
				public int compare(Phuong o1, Phuong o2) {
					return o1.getPhuong().compareToIgnoreCase(o2.getPhuong());
				}
			});
			for (Phuong phuong : listPhuong) {
				cmbPhuong.addItem(phuong.getPhuong());
			}
			cmbPhuong.setEnabled(true);
		}

	}

	
	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	private <E> JComboBox<E> resizeComboBox(JComboBox<E> list) {
		list.removeAllItems();
		// list.addItem((E) firstLabel);
		return list;
	}
	private void setjFrameParent(JFrame jFrameParent) {
		this.jFrameParent = jFrameParent;
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
		txt.selectAll();
		txt.requestFocus();
		return false;
	}

	/**
	 * Kiểm tra thông tin nhân viên
	 *
	 * @return true nếu thông tin nhân viên hợp lệ
	 */
	private boolean validator() {
		String vietNamese = Utils.getVietnameseDiacriticCharacters() + "A-Z";
		String vietNameseLower = Utils.getVietnameseDiacriticCharactersLower() + "a-z";

		String ten = txtTen.getText().trim();

		if (ten.length() <= 0)
			return showThongBaoLoi(txtTen, "Vui lòng nhập họ tên khách hàng");

		if (Pattern.matches(String.format(".*[^%s%s ].*", vietNamese, vietNameseLower), ten))
			return showThongBaoLoi(txtTen, "Họ tên chỉ chứa các ký tự chữ cái");

		if (!Pattern.matches(
				String.format("[%s][%s]*( [%s][%s]*)+", vietNamese, vietNameseLower, vietNamese, vietNameseLower), ten))
			return showThongBaoLoi(txtTen, "Họ tên phải bắt đầu bằng ký tự hoa và có ít nhất 2 từ");

		String cccd = txtCCCD.getText().trim();

		if (cccd.length() <= 0)
			return showThongBaoLoi(txtCCCD, "Vui lòng nhập số căn cước công dân");

		if (!Pattern.matches("\\d{12}", cccd))
			return showThongBaoLoi(txtCCCD, "Số căn cước công dân phải là 12 ký tự số");

		if (khachHang_DAO.isCCCDDaTonTai(cccd))
			return showThongBaoLoi(txtCCCD, "Số căn cước công dân đã tồn tại");

		String soDienThoai = txtSDT.getText().trim();

		if (soDienThoai.length() <= 0)
			return showThongBaoLoi(txtSDT, "Vui lòng nhập số điện thoại");

		if (!Utils.isSoDienThoai(soDienThoai))
			return showThongBaoLoi(txtSDT, "Số điện thoại phải bắt đầu bằng số 0, theo sau là 9 ký tự số");

		if (khachHang_DAO.isSoDienThoaiDaTonTai(soDienThoai))
			return showThongBaoLoi(txtSDT, "Số điện thoại đã tồn tại");

		String ngaySinh = txtNgaySinh.getText();
		long daysElapsed = java.time.temporal.ChronoUnit.DAYS.between(Utils.getLocalDate(ngaySinh), LocalDate.now());
		boolean isDuTuoi = daysElapsed / (18 * 365) > 0;

		if (!isDuTuoi) {
			new Notification(main, Type.ERROR, "Khách hàng chưa đủ 18 tuổi").showNotification();
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

		if (tinh.equals(Tinh.getTinhLabel()) || tinh.equals(null)) {
			new Notification(main, Type.ERROR, "Vui lòng chọn tỉnh/ thành phố").showNotification();
			cmbTinh.showPopup();
			return false;
		}

		String sQuan = (String) cmbQuan.getSelectedItem();

		if ( sQuan == null || sQuan.equals(Quan.getQuanLabel()) ) {
			new Notification(main, Type.ERROR, "Vui lòng chọn quận/ huyện").showNotification();
			String tinhSelected = (String) cmbTinh.getSelectedItem();
			cmbPhuong.setEnabled(false);
			cmbQuan = resizeComboBox(cmbQuan);
			quan = null;
			phuong = null;
			Tinh sTinh = DiaChi_DAO.getTinh(tinhSelected);
			ThemKhachHang_GUI.this.tinh = sTinh;
			ArrayList<Quan> listQuan = (ArrayList<Quan>) DiaChi_DAO.getQuan(ThemKhachHang_GUI.this.tinh);
			listQuan.sort(new Comparator<Quan>() {
				@Override
				public int compare(Quan o1, Quan o2) {
					return o1.getQuan().compareToIgnoreCase(o2.getQuan());
				}
			});
			for (Quan quan : listQuan) {
				cmbQuan.addItem(quan.getQuan());
			}
			cmbQuan.setSelectedItem(listQuan.get(1));
			cmbQuan.setEnabled(true);
			cmbQuan.showPopup();
			return false;
		}

		String sPhuong = (String) cmbPhuong.getSelectedItem();

		if (sPhuong == null || sPhuong.equals(Phuong.getPhuongLabel())) {
			new Notification(main, Type.ERROR, "Vui lòng chọn phường/ xã").showNotification();
			String tinhSelected = (String) cmbTinh.getSelectedItem();
			String quanSelected = (String) cmbQuan.getSelectedItem();
			cmbPhuong = resizeComboBox(cmbPhuong);
			phuong = null;
			Tinh sTinh = DiaChi_DAO.getTinh(tinhSelected);
			Quan quan = DiaChi_DAO.getQuan(sTinh, quanSelected);
			ThemKhachHang_GUI.this.quan = quan;
			ArrayList<Phuong> listPhuong = (ArrayList<Phuong>) DiaChi_DAO.getPhuong(ThemKhachHang_GUI.this.quan);
			listPhuong.sort(new Comparator<Phuong>() {

				@Override
				public int compare(Phuong o1, Phuong o2) {
					return o1.getPhuong().compareToIgnoreCase(o2.getPhuong());
				}
			});
			for (Phuong phuong : listPhuong) {
				cmbPhuong.addItem(phuong.getPhuong());
			}
			cmbPhuong.setEnabled(true);
			cmbPhuong.showPopup();
			return false;
		}

		String diaChi = txtDiaChiCT.getText().trim();

		if (diaChi.length() <= 0)
			return showThongBaoLoi(txtDiaChiCT, "Vui lòng nhập địa chỉ");

		return true;
	}
	
	/**
	 * Xóa rỗng các textfield và làm mới ComboBox
	 */
	private void xoaRong() {
		txtTen.setText("");
		txtCCCD.setText("");
		txtSDT.setText("");
		dateChoose.toDay();

		cmbPhuong.setSelectedIndex(0);
		cmbQuan.setSelectedIndex(0);
		cmbTinh.setSelectedIndex(0);
		cmbPhuong.setSelectedIndex(0);
		cmbQuan.setEnabled(false);
		cmbPhuong.setEnabled(false);
		txtDiaChiCT.setText("");
		
		txtTen.requestFocus();
		main.repaint();
	}
}
