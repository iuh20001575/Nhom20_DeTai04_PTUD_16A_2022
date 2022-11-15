package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import components.button.Button;
import components.comboBox.ComboBox;
import components.textField.TextField;
import dao.DonDatPhong_DAO;
import dao.PhieuDatPhong_DAO;
import entity.ChiTietDatPhong;
import utils.Utils;

public class ThongTinChiTietPhieuDatPhong_GUI extends JPanel implements ItemListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ChiTietDatPhong chiTietDatPhong;
	private TextField txtMaDatPhong;
	private TextField txtPhong;
	private JComboBox<String> cmbTrangThai;
	private JComboBox<String> cmbLoaiPhong;
	private TextField txtSLPhong;
	private TextField txtMaKH;
	private TextField txtHoTenKH;
	private TextField txtSDT;
	private TextField txtDichVu;
	private TextField txtTGLP;
	private TextField txtTGNP;
	private DonDatPhong_DAO datPhong_DAO;
	private Button btnNhanPhong;
	private Button btnCapNhat;
	private Button btnHuyPhong;

	/**
	 * Create the frame.
	 */
	public ThongTinChiTietPhieuDatPhong_GUI(Main main, ChiTietDatPhong chiTietDatPhong) {
		new PhieuDatPhong_DAO();
		datPhong_DAO = new DonDatPhong_DAO();
		this.chiTietDatPhong = chiTietDatPhong;

		setBackground(Utils.secondaryColor);
		setBounds(0, 0, 1086, 508);
		setLayout(null);

		JPanel pnlContainer = new JPanel();
		pnlContainer.setBackground(Utils.secondaryColor);
		pnlContainer.setBounds(40, 7, 948, 488);
		this.add(pnlContainer);
		pnlContainer.setLayout(null);

		JPanel pnlRow1 = new JPanel();
		pnlRow1.setBackground(Utils.secondaryColor);
		pnlRow1.setBounds(0, 0, 948, 75);
		pnlContainer.add(pnlRow1);
		pnlRow1.setLayout(null);

		txtMaDatPhong = new TextField();
		txtMaDatPhong.setLineColor(Utils.lineTextField);
		txtMaDatPhong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtMaDatPhong.setLabelText("Mã phiếu đặt");
		txtMaDatPhong.setBounds(50, 10, 449, 50);
		txtMaDatPhong.setBackground(Utils.secondaryColor);
		pnlRow1.add(txtMaDatPhong);
		txtMaDatPhong.setColumns(10);

		txtSLPhong = new TextField();
		txtSLPhong.setLineColor(Utils.lineTextField);
		txtSLPhong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtSLPhong.setLabelText("Số lượng phòng");
		txtSLPhong.setBounds(550, 10, 449, 50);
		txtSLPhong.setBackground(Utils.secondaryColor);
		pnlRow1.add(txtSLPhong);
		txtSLPhong.setColumns(10);

		JPanel pnlRow2 = new JPanel();
		pnlRow2.setBackground(Utils.secondaryColor);
		pnlRow2.setBounds(0, 80, 948, 75);
		pnlContainer.add(pnlRow2);
		pnlRow2.setLayout(null);

		txtPhong = new TextField();
		txtPhong.setLineColor(Utils.lineTextField);
		txtPhong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtPhong.setLabelText("Phòng");
		txtPhong.setBounds(50, 20, 300, 50);
		txtPhong.setBackground(Utils.secondaryColor);
		pnlRow2.add(txtPhong);
		txtPhong.setColumns(10);

		JLabel lblLoaiPhong = new JLabel("Loại phòng");
		lblLoaiPhong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblLoaiPhong.setBounds(400, 10, 80, 25);
		lblLoaiPhong.setForeground(Utils.labelTextField);
		pnlRow2.add(lblLoaiPhong);

		cmbLoaiPhong = new ComboBox<>();
		cmbLoaiPhong.setModel(new DefaultComboBoxModel<String>());
		cmbLoaiPhong.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbLoaiPhong.setBackground(Utils.primaryColor);
		cmbLoaiPhong.setBounds(400, 40, 200, 36);
		pnlRow2.add(cmbLoaiPhong);

		JLabel lblTrangThai = new JLabel("Trạng thái");
		lblTrangThai.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTrangThai.setBounds(700, 10, 80, 25);
		lblTrangThai.setForeground(Utils.labelTextField);
		pnlRow2.add(lblTrangThai);

		cmbTrangThai = new ComboBox<>();
		cmbTrangThai.setModel(new DefaultComboBoxModel<String>());
		cmbTrangThai.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbTrangThai.setBackground(Utils.primaryColor);
		cmbTrangThai.setBounds(700, 40, 200, 36);
		pnlRow2.add(cmbTrangThai);

		JPanel pnlRow3 = new JPanel();
		pnlRow3.setBackground(Utils.secondaryColor);
		pnlRow3.setBounds(0, 170, 948, 75);
		pnlContainer.add(pnlRow3);
		pnlRow3.setLayout(null);

		txtMaKH = new TextField();
		txtMaKH.setLineColor(Utils.lineTextField);
		txtMaKH.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtMaKH.setLabelText("Mã khách hàng");
		txtMaKH.setBounds(50, 10, 449, 50);
		txtMaKH.setBackground(Utils.secondaryColor);
		pnlRow3.add(txtMaKH);
		txtMaKH.setColumns(10);

		txtHoTenKH = new TextField();
		txtHoTenKH.setLineColor(Utils.lineTextField);
		txtHoTenKH.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtHoTenKH.setLabelText("Họ và tên khách hàng");
		txtHoTenKH.setBounds(520, 10, 449, 50);
		txtHoTenKH.setBackground(Utils.secondaryColor);
		pnlRow3.add(txtHoTenKH);
		txtHoTenKH.setColumns(10);

		JPanel pnlRow4 = new JPanel();
		pnlRow4.setBackground(Utils.secondaryColor);
		pnlRow4.setBounds(0, 250, 948, 75);
		pnlContainer.add(pnlRow4);
		pnlRow4.setLayout(null);

		txtSDT = new TextField();
		txtSDT.setLineColor(Utils.lineTextField);
		txtSDT.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtSDT.setLabelText("Số điện thoại");
		txtSDT.setBounds(50, 20, 449, 50);
		txtSDT.setBackground(Utils.secondaryColor);
		pnlRow4.add(txtSDT);
		txtSDT.setColumns(10);

		txtDichVu = new TextField();
		txtDichVu.setLineColor(Utils.lineTextField);
		txtDichVu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtDichVu.setLabelText("Thông tin đặt món");
		txtDichVu.setText("Xem chi tiết");
		txtDichVu.setBounds(550, 20, 449, 50);
		txtDichVu.setBackground(Utils.secondaryColor);
		txtDichVu.setFocusable(false);
		pnlRow4.add(txtDichVu);
		txtDichVu.setColumns(10);

		JPanel pnlRow5 = new JPanel();
		pnlRow5.setBackground(Utils.secondaryColor);
		pnlRow5.setBounds(0, 330, 948, 75);
		pnlContainer.add(pnlRow5);
		pnlRow5.setLayout(null);

		txtTGLP = new TextField();
		txtTGLP.setLineColor(Utils.lineTextField);
		txtTGLP.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTGLP.setLabelText("Thời gian lập phiếu");
		txtTGLP.setBounds(50, 20, 449, 50);
		txtTGLP.setBackground(Utils.secondaryColor);
		pnlRow5.add(txtTGLP);
		txtTGLP.setColumns(40);

		txtTGNP = new TextField();
		txtTGNP.setLineColor(Utils.lineTextField);
		txtTGNP.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTGNP.setLabelText("Thời gian nhận phòng");
		txtTGNP.setBounds(550, 20, 449, 50);
		txtTGNP.setBackground(Utils.secondaryColor);
		pnlRow5.add(txtTGNP);
		txtTGNP.setColumns(40);

		JPanel pnlActions = new JPanel();
		pnlActions.setBackground(Utils.secondaryColor);
		pnlActions.setBounds(0, 440, 948, 48);
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
		txtMaKH.setEnabled(false);
		txtMaKH.setEnabled(false);

	}

	private void setPhieuDatPhongVaoForm(ChiTietDatPhong chiTietDatPhong) {
		String maDatPhong = chiTietDatPhong.getDonDatPhong().getMaDonDatPhong();
		txtMaDatPhong.setText(maDatPhong);
		txtMaKH.setText(datPhong_DAO.getDatPhong(maDatPhong).getKhachHang().getMaKhachHang());
		txtHoTenKH.setText(datPhong_DAO.getDatPhong(maDatPhong).getKhachHang().getHoTen());
		txtSDT.setText(datPhong_DAO.getDatPhong(maDatPhong).getKhachHang().getSoDienThoai());
		txtTGLP.setText(Utils.convertLocalTimeToString(datPhong_DAO.getDatPhong(maDatPhong).getGioDatPhong()) + " - "
				+ Utils.formatDate(datPhong_DAO.getDatPhong(maDatPhong).getNgayDatPhong()));
		txtTGNP.setText(Utils.convertLocalTimeToString(datPhong_DAO.getDatPhong(maDatPhong).getGioNhanPhong()) + " - "
				+ Utils.formatDate(datPhong_DAO.getDatPhong(maDatPhong).getNgayNhanPhong()));
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub

	}
}
