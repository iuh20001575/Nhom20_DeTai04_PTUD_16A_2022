package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
import components.jDialog.JDialogCustom;
import components.radio.RadioButtonCustom;
import components.textField.TextField;
import connectDB.ConnectDB;
import layouts.DefaultLayout;
import utils.StackFrame;
import utils.Utils;
import dao.DiaChi_DAO;
import dao.KhachHang_DAO;
import entity.Tinh;
import entity.Quan;
import entity.KhachHang;
import entity.Phuong;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class XemKhachHang_GUI extends JFrame implements ItemListener, WindowListener {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DateChooser dateChoose;
	private DiaChi_DAO DiaChi_DAO;
	private KhachHang_DAO khachHang_DAO;
	private JComboBox<String> cmbTinh;
	private JComboBox<String> cmbQuan;
	private JComboBox<String> cmbPhuong;
	private KhachHang khachHang;
	private Tinh tinh;
	private Quan quan;
	private Phuong phuong;

	private boolean isEnabledEventTinh = false;
	private boolean isEnabledEventQuan = false;
	private boolean isEnabledEventPhuong = false;

	TextField txtMa, txtTen, txtCCCD, txtSDT, txtNgaySinh, txtTinh, txtQuan, txtPhuong, txtDiaChiCT;
	RadioButtonCustom radNam, radNu;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					XemKhachHang_GUI frame = new XemKhachHang_GUI(khachHang);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public XemKhachHang_GUI(KhachHang khachHang) {

		JFrame _this = this;

		try {
			new ConnectDB().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		khachHang_DAO = new KhachHang_DAO();
		DiaChi_DAO = new DiaChi_DAO();
		this.khachHang = khachHang_DAO.getKhachHangTheoMa(khachHang.getMaKhachHang());

		// TODO Auto-generated constructor stub
		DefaultLayout defaultLayout = new DefaultLayout(this, contentPane, "Xem khách hàng", "Xem khách hàng");
		contentPane = defaultLayout.getJPanel();

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(203, 239, 255));
		panel_1.setBounds(0, 65, 1100, 500);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		txtMa = new TextField();
		txtMa.setLabelText("Mã khách hàng:");
		txtMa.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtMa.setBackground(new Color(203, 239, 255));
		txtMa.setBounds(44, 5, 371, 55);
		panel_1.add(txtMa);

		txtTen = new TextField();
		txtTen.setLabelText("Họ tên khách hàng:");
		txtTen.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTen.setBackground(new Color(203, 239, 255));
		txtTen.setBounds(516, 5, 371, 55);
		panel_1.add(txtTen);

		txtCCCD = new TextField();
		txtCCCD.setLabelText("Căn cước công dân:");
		txtCCCD.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtCCCD.setBackground(new Color(203, 239, 255));
		txtCCCD.setBounds(44, 85, 371, 55);
		panel_1.add(txtCCCD);

		txtNgaySinh = new TextField();
		txtNgaySinh.setIcon(new ImageIcon("Icon\\add-event 2.png"));
		txtNgaySinh.setLineColor(new Color(149, 166, 248));
		txtNgaySinh.setLabelText("Ngày sinh:");
		txtNgaySinh.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtNgaySinh.setColumns(10);
		txtNgaySinh.setBackground(new Color(203, 239, 255));
		txtNgaySinh.setBounds(516, 85, 371, 55);
		panel_1.add(txtNgaySinh);
		dateChoose = new DateChooser();
		dateChoose.setTextRefernce(txtNgaySinh);

		JPanel pnlGioiTinh = new JPanel();
		pnlGioiTinh.setBackground(Utils.secondaryColor);
		pnlGioiTinh.setBounds(44, 165, 371, 55);
		panel_1.add(pnlGioiTinh);
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
		txtSDT.setBounds(516, 165, 371, 50);
		panel_1.add(txtSDT);

		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblDiaChi.setBounds(44, 245, 200, 19);
		lblDiaChi.setForeground(Utils.labelTextField);
		panel_1.add(lblDiaChi);

		cmbTinh = new JComboBox<String>();
		ArrayList<Tinh> listTinh = (ArrayList<Tinh>) DiaChi_DAO.getTinh();
		for (Tinh tinh : listTinh) {
			cmbTinh.addItem(tinh.getTinh());
		}
		cmbTinh.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbTinh.setBackground(Utils.primaryColor);
		cmbTinh.setBounds(44, 280, 220, 36);
		panel_1.add(cmbTinh);

		cmbQuan = new JComboBox<String>();
		String tinhSelected = (String) cmbTinh.getSelectedItem();
		Tinh tinh = DiaChi_DAO.getTinh(tinhSelected);
		XemKhachHang_GUI.this.tinh = tinh;
		ArrayList<Quan> listQuan = (ArrayList<Quan>) DiaChi_DAO.getQuan(XemKhachHang_GUI.this.tinh);
		for (Quan quan : listQuan) {
			cmbQuan.addItem(quan.getQuan());
		}

		cmbQuan.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbQuan.setBackground(new Color(140, 177, 180));
		cmbQuan.setBounds(324, 280, 220, 36);
		panel_1.add(cmbQuan);

		cmbPhuong = new JComboBox<String>();
		cmbQuan.setModel(new DefaultComboBoxModel<String>());
		// cmbPhuong.setModel(new DefaultComboBoxModel<String>(new String[] { "Phong
		// Điền" }));
		cmbPhuong.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbPhuong.setBackground(new Color(140, 177, 180));
		cmbPhuong.setBounds(604, 280, 220, 36);
		panel_1.add(cmbPhuong);

		txtDiaChiCT = new TextField();
		txtDiaChiCT.setLineColor(new Color(149, 200, 248));
		txtDiaChiCT.setLabelText("Địa chỉ cụ thể");
		txtDiaChiCT.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtDiaChiCT.setColumns(10);
		txtDiaChiCT.setBackground(new Color(203, 239, 255));
		txtDiaChiCT.setBounds(44, 341, 371, 55);
		panel_1.add(txtDiaChiCT);

		Button btnCapNhat = new Button("Cập nhật");
		btnCapNhat.setUI(new MetalButtonUI() {
			protected Color getDisabledTextColor() {
				return Color.WHITE;
			}
		});
		btnCapNhat.setIcon(new ImageIcon("Icon\\floppy-disk 1.png"));
		btnCapNhat.setRadius(8);
		btnCapNhat.setForeground(Color.WHITE);
		btnCapNhat.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		btnCapNhat.setColorOver(new Color(140, 177, 180));
		btnCapNhat.setColorClick(new Color(140, 177, 180, 204));
		btnCapNhat.setColor(new Color(140, 177, 180));
		btnCapNhat.setBorderColor(new Color(203, 239, 255));
		btnCapNhat.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnCapNhat.setBounds(100, 420, 250, 50);
		panel_1.add(btnCapNhat);
		
		btnCapNhat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					JFrame jFrame = new CapNhatKhachHang_GUI(getKhachHangTuForm());
					StackFrame.push(jFrame);
					jFrame.setVisible(true);
					_this.setVisible(false);
			}
		});

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
		btnLuu.setBounds(500, 420, 250, 50);
		panel_1.add(btnLuu);

		btnLuu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String sma = txtMa.getText();
				String sten = txtTen.getText();
				LocalDate sngaySinh = Utils.getLocalDate(txtNgaySinh.getText());
				Boolean gioiTinh = radNam.isSelected();
				String sCCCD = txtCCCD.getText();
				String sSDT = txtSDT.getText();
				String sTinh = cmbTinh.getSelectedItem().toString();
				String sQuan = cmbQuan.getSelectedItem().toString();
				String sPhuong = cmbPhuong.getSelectedItem().toString();
				String sDCCT = txtDiaChiCT.getText();
				khachHang_DAO.themKhachHang(
						new KhachHang(sma, sten, sCCCD, sngaySinh, gioiTinh, sSDT, DiaChi_DAO.getTinh(sTinh),
								DiaChi_DAO.getQuan(tinh, sQuan), DiaChi_DAO.getPhuong(quan, sPhuong), sDCCT));

			}
		});

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
		btnHuy.setBounds(800, 420, 250, 50);
		panel_1.add(btnHuy);

		btnHuy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame jFrame = StackFrame.pop();
				StackFrame.peek().setVisible(true);
				jFrame.setVisible(false);

			}
		});

		cmbTinh.addItemListener(this);
		cmbQuan.addItemListener(this);
		cmbPhuong.addItemListener(this);

		setKhachHangVaoForm(this.khachHang);
		txtMa.setEnabled(false);

	}

	private boolean validator() {
		return true;
	}

	@SuppressWarnings("unchecked")
	private <E> JComboBox<E> resizeComboBox(JComboBox<E> list, String firstLabel) {
		list.removeAllItems();
		list.addItem((E) firstLabel);
		return list;
	}

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
		String sDCCT = txtDiaChiCT.getText();
		return new KhachHang(sma, sten, sCCCD, sngaySinh, gioiTinh, sSDT,DiaChi_DAO.getTinh(sTinh),
				DiaChi_DAO.getQuan(tinh, sQuan), DiaChi_DAO.getPhuong(quan, sPhuong), sDCCT);
	}

	private void setKhachHangVaoForm(KhachHang khachHang) {
		txtMa.setText(khachHang.getMaKhachHang());
		txtCCCD.setText(khachHang.getCccd());
		txtDiaChiCT.setText(khachHang.getDiaChiCuThe());
		txtTen.setText(khachHang.getHoTen());
		txtNgaySinh.setText(Utils.formatDate(khachHang.getNgaySinh()));
		txtSDT.setText(khachHang.getSoDienThoai());
		setTinhToComboBox();
		setQuanToComboBox(tinh);
		setPhuongToComboBox(quan);
		if (khachHang.isGioiTinh())
			radNam.setSelected(true);
		else
			radNu.setSelected(true);
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
			Tinh tinh = DiaChi_DAO.getTinh(tinhSelected);
			XemKhachHang_GUI.this.tinh = tinh;
			setQuanToComboBox(XemKhachHang_GUI.this.tinh);
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
				Quan quan = DiaChi_DAO.getQuan(tinh, quanSelected);
				XemKhachHang_GUI.this.quan = quan;
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

			Phuong phuong = DiaChi_DAO.getPhuong(quan, phuongSelect);
			XemKhachHang_GUI.this.phuong = phuong;
			isEnabledEventPhuong = false;
		}

	}

	private void setTinhToComboBox() {
		isEnabledEventTinh = false;

		resizeComboBox(cmbTinh, Tinh.getTinhLabel());

		List<Tinh> tinhs = DiaChi_DAO.getTinh();

		tinhs.sort(new Comparator<Tinh>() {
			@Override
			public int compare(Tinh o1, Tinh o2) {
				return o1.getTinh().compareToIgnoreCase(o2.getTinh());
			}
		});

		tinhs.forEach(tinh -> {
			int index = tinhs.indexOf(tinh);
			cmbTinh.addItem(tinh.getTinh());
			if (tinh.getId().equals(XemKhachHang_GUI.this.khachHang.getTinh().getId())) {
				cmbTinh.setSelectedIndex(index + 1);
				XemKhachHang_GUI.this.tinh = tinh;
			}
		});

		isEnabledEventTinh = true;
	}

	private void setQuanToComboBox(Tinh tinh) {
		isEnabledEventQuan = false;

		resizeComboBox(cmbQuan, Quan.getQuanLabel());
		List<Quan> quans = DiaChi_DAO.getQuan(tinh);
		quans.sort(new Comparator<Quan>() {
			@Override
			public int compare(Quan o1, Quan o2) {
				return o1.getQuan().compareToIgnoreCase(o2.getQuan());
			}
		});
		quans.forEach(quan -> {
			int index = quans.indexOf(quan);
			cmbQuan.addItem(quan.getQuan());
			if (quan.getId().equals(XemKhachHang_GUI.this.khachHang.getQuan().getId())) {
				cmbQuan.setSelectedIndex(index + 1);
				XemKhachHang_GUI.this.quan = quan;
			}
		});

		isEnabledEventQuan = true;
	}

	private void setPhuongToComboBox(Quan quan) {
		isEnabledEventPhuong = false;

		resizeComboBox(cmbPhuong, Phuong.getPhuongLabel());

		List<Phuong> phuongs = DiaChi_DAO.getPhuong(quan);

		phuongs.sort(new Comparator<Phuong>() {

			@Override
			public int compare(Phuong o1, Phuong o2) {
				return o1.getPhuong().compareToIgnoreCase(o2.getPhuong());
			}
		});

		phuongs.forEach(phuong -> {
			int index = phuongs.indexOf(phuong);
			cmbPhuong.addItem(phuong.getPhuong());
			if (phuong.getId().equals(XemKhachHang_GUI.this.khachHang.getPhuong().getId())) {
				cmbPhuong.setSelectedIndex(index + 1);
				XemKhachHang_GUI.this.phuong = phuong;
			}
		});

		isEnabledEventPhuong = true;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}