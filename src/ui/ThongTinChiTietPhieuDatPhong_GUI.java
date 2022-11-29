package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import components.button.Button;
import components.comboBox.ComboBox;
import components.textField.TextField;
import dao.ChiTietDatPhong_DAO;
import dao.DonDatPhong_DAO;
import dao.KhachHang_DAO;
import dao.NhanVien_DAO;
import dao.PhieuDatPhong_DAO;
import entity.ChiTietDatPhong;
import entity.DonDatPhong;
import entity.KhachHang;
import entity.NhanVien;
import utils.Utils;

public class ThongTinChiTietPhieuDatPhong_GUI extends JPanel implements ItemListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Button btnCapNhat;
	private Button btnHuyPhong;
	private Button btnNhanPhong;
	private Button btnSuaPhong;
	private ChiTietDatPhong chiTietDatPhong;
	private ChiTietDatPhong_DAO chiTietDatPhong_DAO;
	private DonDatPhong_DAO donDatPhong_DAO;
	private KhachHang_DAO khachHang_DAO;
	private Main main;
	private NhanVien_DAO nhanVien_DAO;
	private PhieuDatPhong_DAO phieuDatPhong_DAO;
	private TextField txtKhachHang;
	private TextField txtMaDatPhong;
	private TextField txtMaKH;
	private TextField txtNhanVien;
	private TextField txtPhong;
	private TextField txtSDT;
	private TextField txtSLPhong;
	private TextField txtTGLP;
	private TextField txtTGNP;
	private TextField txtTrangThai;
	private final int widthPnlContainer = 948;


	/**
	 * Create the frame.
	 */
	public ThongTinChiTietPhieuDatPhong_GUI(Main main, ChiTietDatPhong chiTietDatPhong) {
		phieuDatPhong_DAO = new PhieuDatPhong_DAO();
		donDatPhong_DAO = new DonDatPhong_DAO();
		khachHang_DAO = new KhachHang_DAO();
		nhanVien_DAO = new NhanVien_DAO();
		chiTietDatPhong_DAO = new ChiTietDatPhong_DAO();
		this.chiTietDatPhong = chiTietDatPhong;
		this.main = main;
		int padding = (int) Math.floor((Utils.getBodyHeight() - 428) / 5);
		int top = padding;

		setBackground(Utils.secondaryColor);
		setBounds(0, 0, Utils.getScreenWidth(), Utils.getBodyHeight());
		setLayout(null);

		JPanel pnlContainer = new JPanel();
		pnlContainer.setBackground(Utils.secondaryColor);
		pnlContainer.setBounds(Utils.getLeft(widthPnlContainer), 0, widthPnlContainer, Utils.getBodyHeight());
		this.add(pnlContainer);
		pnlContainer.setLayout(null);

		JPanel pnlRow1 = new JPanel();
		pnlRow1.setBackground(Utils.secondaryColor);
		pnlRow1.setBounds(0, top, 948, 75);
		top += padding;
		top += 55;
		pnlContainer.add(pnlRow1);
		pnlRow1.setLayout(null);

		txtMaDatPhong = new TextField();
		txtMaDatPhong.setLineColor(Utils.lineTextField);
		txtMaDatPhong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtMaDatPhong.setLabelText("Mã phiếu đặt");
		txtMaDatPhong.setBounds(50, 10, 449, 50);
		txtMaDatPhong.setBackground(Utils.secondaryColor);
		txtMaDatPhong.setEnabled(false);
		pnlRow1.add(txtMaDatPhong);
		txtMaDatPhong.setColumns(10);

		txtNhanVien = new TextField();
		txtNhanVien.setLineColor(Utils.lineTextField);
		txtNhanVien.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtNhanVien.setLabelText("Nhân viên");
		txtNhanVien.setBounds(550, 10, 449, 50);
		txtNhanVien.setBackground(Utils.secondaryColor);
		txtNhanVien.setEnabled(false);
		pnlRow1.add(txtNhanVien);
		txtNhanVien.setColumns(10);

		JPanel pnlRow2 = new JPanel();
		pnlRow2.setBackground(Utils.secondaryColor);
		pnlRow2.setBounds(0, top, 948, 75);
		top += padding;
		top += 55;
		pnlContainer.add(pnlRow2);
		pnlRow2.setLayout(null);
		
		txtPhong = new TextField();
		txtPhong.setLineColor(Utils.lineTextField);
		txtPhong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtPhong.setBounds(50, 20, 300, 50);
		txtPhong.setLabelText("Phòng");
		txtPhong.setBackground(Utils.secondaryColor);
		txtPhong.setEnabled(false);
		pnlRow2.add(txtPhong);
		txtPhong.setColumns(10);
		
		txtSLPhong = new TextField();
		txtSLPhong.setLineColor(Utils.lineTextField);
		txtSLPhong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtSLPhong.setBounds(400, 20, 150, 50);
		txtSLPhong.setLabelText("Số lượng phòng");
		txtSLPhong.setBackground(Utils.secondaryColor);
		txtSLPhong.setEnabled(false);
		pnlRow2.add(txtSLPhong);
		txtSLPhong.setColumns(10);

		txtTrangThai = new TextField();
		txtTrangThai.setLineColor(Utils.lineTextField);
		txtTrangThai.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTrangThai.setLabelText("Trạng thái");
		txtTrangThai.setBounds(600, 20, 150, 50);
		txtTrangThai.setBackground(Utils.secondaryColor);
		txtTrangThai.setEnabled(false);
		pnlRow2.add(txtTrangThai);
		txtTrangThai.setColumns(10);
		
		btnSuaPhong = new Button("Sửa phòng");
		btnSuaPhong.setIcon(new ImageIcon("Icon\\change-door.png"));
		btnSuaPhong.setFocusable(false);
		btnSuaPhong.setRadius(8);
		btnSuaPhong.setBorderColor(Utils.secondaryColor);
		btnSuaPhong.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnSuaPhong.setColor(Utils.primaryColor);
		btnSuaPhong.setColorOver(Utils.primaryColor);
		btnSuaPhong.setColorClick(Utils.getOpacity(Utils.primaryColor, 0.8f));
		btnSuaPhong.setForeground(Color.WHITE);
		btnSuaPhong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnSuaPhong.setBounds(780, 30, 135, 40);
		pnlRow2.add(btnSuaPhong);

		JPanel pnlRow3 = new JPanel();
		pnlRow3.setBackground(Utils.secondaryColor);
		pnlRow3.setBounds(0, top, 948, 75);
		top += padding;
		top += 55;
		pnlContainer.add(pnlRow3);
		pnlRow3.setLayout(null);

		txtKhachHang = new TextField();
		txtKhachHang.setLineColor(Utils.lineTextField);
		txtKhachHang.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtKhachHang.setLabelText("Khách hàng");
		txtKhachHang.setBounds(50, 10, 449, 50);
		txtKhachHang.setBackground(Utils.secondaryColor);
		txtKhachHang.setEnabled(false);
		pnlRow3.add(txtKhachHang);
		txtKhachHang.setColumns(10);

		txtSDT = new TextField();
		txtSDT.setLineColor(Utils.lineTextField);
		txtSDT.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtSDT.setLabelText("Số điện thoại");
		txtSDT.setBounds(520, 10, 449, 50);
		txtSDT.setBackground(Utils.secondaryColor);
		txtSDT.setEnabled(false);
		pnlRow3.add(txtSDT);
		txtSDT.setColumns(10);

		JPanel pnlRow4 = new JPanel();
		pnlRow4.setBackground(Utils.secondaryColor);
		pnlRow4.setBounds(0, top, 948, 75);
		top += padding;
		top += 55;
		pnlContainer.add(pnlRow4);
		pnlRow4.setLayout(null);

		txtTGLP = new TextField();
		txtTGLP.setLineColor(Utils.lineTextField);
		txtTGLP.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTGLP.setLabelText("Thời gian lập phiếu");
		txtTGLP.setBounds(50, 20, 449, 50);
		txtTGLP.setBackground(Utils.secondaryColor);
		txtTGLP.setEnabled(false);
		pnlRow4.add(txtTGLP);
		txtTGLP.setColumns(40);

		txtTGNP = new TextField();
		txtTGNP.setLineColor(Utils.lineTextField);
		txtTGNP.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTGNP.setLabelText("Thời gian nhận phòng");
		txtTGNP.setBounds(550, 20, 449, 50);
		txtTGNP.setBackground(Utils.secondaryColor);
		txtTGNP.setEnabled(false);
		pnlRow4.add(txtTGNP);
		txtTGNP.setColumns(40);

		JPanel pnlActions = new JPanel();
		pnlActions.setBackground(Utils.secondaryColor);
		pnlActions.setBounds(0, top, 948, 48);
		pnlContainer.add(pnlActions);
		pnlActions.setLayout(null);

		btnNhanPhong = new Button("Nhận phòng");
		btnNhanPhong.setIcon(new ImageIcon("Icon\\check-in (1).png"));
		btnNhanPhong.setFocusable(false);
		btnNhanPhong.setRadius(8);
		btnNhanPhong.setBorderColor(Utils.secondaryColor);
		btnNhanPhong.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnNhanPhong.setColor(Utils.primaryColor);
		btnNhanPhong.setColorOver(Utils.primaryColor);
		btnNhanPhong.setColorClick(Utils.getOpacity(Utils.primaryColor, 0.8f));
		btnNhanPhong.setForeground(Color.WHITE);
		btnNhanPhong.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		btnNhanPhong.setBounds(70, 0, 250, 48);
		pnlActions.add(btnNhanPhong);

		btnCapNhat = new Button("Cập nhật");
		btnCapNhat.setIcon(new ImageIcon("Icon\\door.png"));
		btnCapNhat.setFocusable(false);
		btnCapNhat.setRadius(8);
		btnCapNhat.setBorderColor(Utils.secondaryColor);
		btnCapNhat.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnCapNhat.setColor(Utils.primaryColor);
		btnCapNhat.setColorOver(Utils.primaryColor);
		btnCapNhat.setColorClick(Utils.getOpacity(Utils.primaryColor, 0.8f));
		btnCapNhat.setForeground(Color.WHITE);
		btnCapNhat.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		btnCapNhat.setBounds(370, 0, 250, 48);
		pnlActions.add(btnCapNhat);
//		btnCapNhat.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				setEnabledForm(true);
//				btnCapNhat.setVisible(false);
//				btnLuu.setEnabled(true);
//			}
//		});

		btnHuyPhong = new Button("Huỷ phòng");
		btnHuyPhong.setIcon(new ImageIcon("Icon\\edit 1.png"));
		btnHuyPhong.setFocusable(false);
		btnHuyPhong.setRadius(8);
		btnHuyPhong.setBorderColor(Utils.secondaryColor);
		btnHuyPhong.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnHuyPhong.setColor(Utils.primaryColor);
		btnHuyPhong.setColorOver(Utils.primaryColor);
		btnHuyPhong.setColorClick(Utils.getOpacity(Utils.primaryColor, 0.8f));
		btnHuyPhong.setForeground(Color.WHITE);
		btnHuyPhong.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		btnHuyPhong.setBounds(670, 0, 250, 48);
		pnlActions.add(btnHuyPhong);

		setPhieuDatPhongVaoForm(this.chiTietDatPhong);

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub

	}

	private void setPhieuDatPhongVaoForm(ChiTietDatPhong chiTietDatPhong) {
		String maDatPhong = chiTietDatPhong.getDonDatPhong().getMaDonDatPhong();
		DonDatPhong donDatPhong = donDatPhong_DAO.getDatPhong(maDatPhong);
		KhachHang khachHang = khachHang_DAO.getKhachHangTheoMa(donDatPhong.getKhachHang().getMaKhachHang());
		NhanVien nhanVien = nhanVien_DAO.getNhanVienTheoMa(donDatPhong.getNhanVien().getMaNhanVien());
		
		List<ChiTietDatPhong> listChiTietDatPhong = chiTietDatPhong_DAO.getAllChiTietDatPhong(donDatPhong);
		List<String> listPhong = new ArrayList<String>();
		listChiTietDatPhong.forEach( list -> listPhong.add(list.getPhong().getMaPhong()));
		
		txtMaDatPhong.setText(maDatPhong);
		txtKhachHang.setText(khachHang.getMaKhachHang() + " - " + khachHang.getHoTen());
		txtNhanVien.setText(nhanVien.getMaNhanVien() + " - " + nhanVien.getHoTen());
		txtPhong.setText(String.join(", ", listPhong));
		txtSLPhong.setText(listPhong.size()+"");
		txtTrangThai.setText(DonDatPhong.convertTrangThaiToString(donDatPhong.getTrangThai()));
		txtKhachHang.setText(khachHang.getMaKhachHang() + " - " + khachHang.getHoTen());
		txtSDT.setText(khachHang.getSoDienThoai());
		txtTGLP.setText(Utils.convertLocalTimeToString(donDatPhong.getGioDatPhong()) + " - "
				+ Utils.formatDate(donDatPhong.getNgayDatPhong()));
		txtTGNP.setText(Utils.convertLocalTimeToString(donDatPhong.getGioNhanPhong()) + " - "
				+ Utils.formatDate(donDatPhong.getNgayNhanPhong()));
	}
}
