package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

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
import components.notification.Notification;
import components.radio.RadioButtonCustom;
import components.textField.TextField;
import dao.DiaChi_DAO;
import dao.KhachHang_DAO;
import entity.KhachHang;
import entity.PanelUI;
import entity.Phuong;
import entity.Quan;
import entity.Tinh;
import utils.StackPanel;
import utils.Utils;

public class ThemKhachHang_GUI extends JPanel implements ItemListener, MouseListener {
	private static final long serialVersionUID = 1L;

	private DateChooser dateChoose;
	private DiaChi_DAO DiaChi_DAO;
	private KhachHang_DAO khachHang_DAO;
	private JComboBox<String> cmbTinh;
	private JComboBox<String> cmbQuan;
	private JComboBox<String> cmbPhuong;
	private JFrame jFrameParent = null;

	private Tinh tinh;
	private Quan quan;
	private Phuong phuong;
	private TextField txtSDT;
	private TextField txtMa;
	private TextField txtTen;
	private TextField txtCCCD;
	private TextField txtNgaySinh;
	private RadioButtonCustom radNam;
	private TextField txtDiaChiCT;
	private JFrame main;

	public ThemKhachHang_GUI(Main main, JFrame jFrameParent, String soDienThoai) {
		this(main);
		setjFrameParent(jFrameParent);
		txtSDT.setText(soDienThoai);
	}

	private void setjFrameParent(JFrame jFrameParent) {
		this.jFrameParent = jFrameParent;
	}

	/**
	 * @wbp.parser.constructor
	 */
	public ThemKhachHang_GUI(Main main) {
		this.main = main;
		khachHang_DAO = new KhachHang_DAO();
		DiaChi_DAO = new DiaChi_DAO();

		setBackground(new Color(203, 239, 255));
		setBounds(0, 0, 1100, 500);
		setLayout(null);

		txtMa = new TextField();
		txtMa.setEditable(false);
		txtMa.setLabelText("Mã khách hàng:");
		txtMa.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtMa.setBackground(new Color(203, 239, 255));
		txtMa.setBounds(44, 5, 371, 55);
		this.add(txtMa);

		txtTen = new TextField();
		txtTen.setLabelText("Họ tên khách hàng:");
		txtTen.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTen.setBackground(new Color(203, 239, 255));
		txtTen.setBounds(516, 5, 371, 55);
		this.add(txtTen);

		txtCCCD = new TextField();
		txtCCCD.setLabelText("Căn cước công dân:");
		txtCCCD.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtCCCD.setBackground(new Color(203, 239, 255));
		txtCCCD.setBounds(44, 85, 371, 55);
		this.add(txtCCCD);

		txtNgaySinh = new TextField();
		txtNgaySinh.setIcon(new ImageIcon("Icon\\add-event 2.png"));
		txtNgaySinh.setLineColor(new Color(149, 166, 248));
		txtNgaySinh.setLabelText("Ngày sinh:");
		txtNgaySinh.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtNgaySinh.setColumns(10);
		txtNgaySinh.setBackground(new Color(203, 239, 255));
		txtNgaySinh.setBounds(516, 85, 371, 55);
		this.add(txtNgaySinh);
		dateChoose = new DateChooser();
		dateChoose.setDateFormat("dd/MM/yyyy");
		dateChoose.setTextRefernce(txtNgaySinh);

		JPanel pnlGioiTinh = new JPanel();
		pnlGioiTinh.setBackground(Utils.secondaryColor);
		pnlGioiTinh.setBounds(44, 165, 371, 55);
		this.add(pnlGioiTinh);
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

		RadioButtonCustom radNu = new RadioButtonCustom("Nữ");
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
		this.add(txtSDT);

		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblDiaChi.setBounds(44, 245, 200, 19);
		lblDiaChi.setForeground(Utils.labelTextField);
		this.add(lblDiaChi);

		cmbTinh = new JComboBox<String>();
		ArrayList<Tinh> listTinh = (ArrayList<Tinh>) DiaChi_DAO.getTinh();
		for (Tinh tinh : listTinh) {
			cmbTinh.addItem(tinh.getTinh());
		}
		cmbTinh.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbTinh.setBackground(Utils.primaryColor);
		cmbTinh.setBounds(44, 280, 220, 36);
		this.add(cmbTinh);

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
		cmbQuan.setBounds(324, 280, 220, 36);
		this.add(cmbQuan);

		cmbPhuong = new JComboBox<String>();
		cmbQuan.setModel(new DefaultComboBoxModel<String>());
		// cmbPhuong.setModel(new DefaultComboBoxModel<String>(new String[] { "Phong
		// Điền" }));
		cmbPhuong.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbPhuong.setBackground(new Color(140, 177, 180));
		cmbPhuong.setBounds(604, 280, 220, 36);
		this.add(cmbPhuong);

		txtDiaChiCT = new TextField();
		txtDiaChiCT.setLineColor(new Color(149, 200, 248));
		txtDiaChiCT.setLabelText("Địa chỉ cụ thể");
		txtDiaChiCT.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtDiaChiCT.setColumns(10);
		txtDiaChiCT.setBackground(new Color(203, 239, 255));
		txtDiaChiCT.setBounds(44, 341, 371, 55);
		this.add(txtDiaChiCT);

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
		this.add(btnLuu);

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
		btnHuy.setBounds(800, 420, 250, 50);
		this.add(btnHuy);

		btnHuy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (jFrameParent == null) {
					StackPanel.pop();
					PanelUI panelUI = StackPanel.peek();
					if (panelUI.getTitle().equals("Trang chủ")) {
						while (!StackPanel.empty())
							StackPanel.pop();
						StackPanel.push(new PanelUI(new TrangChu_GUI(), "Trang chủ", 0, 0));
					}
					main.addPnlBody(panelUI);
					main.getMenu().setSelectedMenu(panelUI.getIndex(), panelUI.getIndexSubmenu());
				} else {
					main.setVisible(false);
					jFrameParent.setVisible(false);
				}
			}
		});

		cmbTinh.addItemListener(this);
		cmbQuan.addItemListener(this);
		cmbPhuong.addItemListener(this);

		txtMa.setText(khachHang_DAO.getMaKhachHang());
	}

	private <E> JComboBox<E> resizeComboBox(JComboBox<E> list) {
		list.removeAllItems();
		// list.addItem((E) firstLabel);
		return list;
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
		Tinh tinhSelect = DiaChi_DAO.getTinh(sTinh);
		quan = DiaChi_DAO.getQuan(tinhSelect, sQuan);
		phuong = DiaChi_DAO.getPhuong(quan, sPhuong);
		String sDCCT = txtDiaChiCT.getText();

		KhachHang khachHang = new KhachHang(sma, sten, sCCCD, sngaySinh, gioiTinh, sSDT, tinhSelect, quan, phuong,
				sDCCT);
		if (khachHang_DAO.themKhachHang(khachHang)) {
			new Notification(main, components.notification.Notification.Type.SUCCESS,
					"Đã thêm khách hàng mới thành công").showNotification();

			if (jFrameParent != null) {
				jFrameParent.setVisible(false);
				jFrameParent.setVisible(true);
				main.setVisible(false);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
