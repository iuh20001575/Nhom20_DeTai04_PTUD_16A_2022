package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import components.button.Button;
import components.scrollbarCustom.ScrollBarCustom;
import components.textField.TextField;
import connectDB.ConnectDB;
import dao.ChiTietDatPhong_DAO;
import dao.DatPhong_DAO;
import dao.KhachHang_DAO;
import dao.NhanVien_DAO;
import entity.ChiTietDatPhong;
import entity.DatPhong;
import entity.KhachHang;
import entity.NhanVien;
import utils.Utils;

public class ThanhToan_GUI extends JFrame implements ItemListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private TextField txtTienNhan;
	private TextField txtTienThua;
	private JTable tbl;
	private DefaultTableModel tableModel;
	private double tienThanhToan = 152000;
	private DatPhong_DAO datPhong_DAO;
	private JComboBox<String> cmbMaDatPhong;
	private ThanhToan_GUI _this;
	private DatPhong datPhong;
	private KhachHang_DAO khachHang_DAO;
	private NhanVien_DAO nhanVien_DAO;
	private JComboBox<String> cmbSoDienThoai;
	private JLabel lblTenKhach;
	private JLabel lblGioNhanPhong;
	private JLabel lblTenNhanVien;
	private List<ChiTietDatPhong> dsChiTietDatPhong;
	private JLabel lblTongThoiLuong;
	private ChiTietDatPhong_DAO chiTietDatPhong_DAO;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThanhToan_GUI frame = new ThanhToan_GUI();
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
	public ThanhToan_GUI() {
		try {
			new ConnectDB().connect();
		} catch (Exception e) {
			// TODO: handle exception
		}

		_this = this;
		datPhong_DAO = new DatPhong_DAO();
		khachHang_DAO = new KhachHang_DAO();
		nhanVien_DAO = new NhanVien_DAO();
		chiTietDatPhong_DAO = new ChiTietDatPhong_DAO();

		setType(Type.UTILITY);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("Icon\\room.png"));
		setTitle("Thanh toán");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 600, 550);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel pnlContainer = new JPanel();
		pnlContainer.setBackground(Color.WHITE);
		pnlContainer.setBounds(0, 0, 586, 513);
		contentPane.add(pnlContainer);
		pnlContainer.setLayout(null);

		JPanel pnlHeader = new JPanel();
		pnlHeader.setBackground(Utils.primaryColor);
		pnlHeader.setBounds(0, 0, 586, 50);
		pnlContainer.add(pnlHeader);
		pnlHeader.setLayout(null);

		JLabel lblTitle = new JLabel("THANH TOÁN");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblTitle.setBounds(0, 0, 536, 50);
		pnlHeader.add(lblTitle);

		JPanel pnlBody = new JPanel();
		pnlBody.setBackground(Color.WHITE);
		pnlBody.setBounds(10, 65, 566, 433);
		pnlContainer.add(pnlBody);
		pnlBody.setLayout(null);

		JPanel pnlRow1 = new JPanel();
		pnlRow1.setBackground(Color.WHITE);
		pnlRow1.setBounds(0, 0, 566, 24);
		pnlBody.add(pnlRow1);
		pnlRow1.setLayout(null);

		JPanel pnlMaDatPhong = new JPanel();
		pnlMaDatPhong.setBackground(Color.WHITE);
		pnlMaDatPhong.setBounds(0, 0, 275, 24);
		pnlRow1.add(pnlMaDatPhong);
		pnlMaDatPhong.setLayout(null);

		JLabel lblLabelMaDatPhong = new JLabel("Mã đặt phòng");
		lblLabelMaDatPhong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblLabelMaDatPhong.setBounds(0, 0, 115, 24);
		pnlMaDatPhong.add(lblLabelMaDatPhong);

		cmbMaDatPhong = new JComboBox<String>();
		cmbMaDatPhong.setBackground(Utils.primaryColor);
		cmbMaDatPhong.setForeground(Color.BLACK);
		cmbMaDatPhong.setModel(new DefaultComboBoxModel<String>(new String[] { "Mã đặt phòng" }));
		cmbMaDatPhong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		cmbMaDatPhong.setBounds(125, 0, 150, 24);
		pnlMaDatPhong.add(cmbMaDatPhong);

		JPanel pnlTenNhanVien = new JPanel();
		pnlTenNhanVien.setBackground(Color.WHITE);
		pnlTenNhanVien.setLayout(null);
		pnlTenNhanVien.setBounds(291, 0, 275, 24);
		pnlRow1.add(pnlTenNhanVien);

		JLabel lblLabelTenNhanVien = new JLabel("Tên nhân viên");
		lblLabelTenNhanVien.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblLabelTenNhanVien.setBounds(0, 0, 115, 24);
		pnlTenNhanVien.add(lblLabelTenNhanVien);

		lblTenNhanVien = new JLabel("");
		lblTenNhanVien.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTenNhanVien.setBounds(125, 0, 150, 24);
		pnlTenNhanVien.add(lblTenNhanVien);

		JPanel pnlRow2 = new JPanel();
		pnlRow2.setBackground(Color.WHITE);
		pnlRow2.setLayout(null);
		pnlRow2.setBounds(0, 32, 566, 24);
		pnlBody.add(pnlRow2);

		JPanel pnlTenKhach = new JPanel();
		pnlTenKhach.setBackground(Color.WHITE);
		pnlTenKhach.setLayout(null);
		pnlTenKhach.setBounds(0, 0, 275, 24);
		pnlRow2.add(pnlTenKhach);

		JLabel lblLabelTenKhach = new JLabel("Tên khách");
		lblLabelTenKhach.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblLabelTenKhach.setBounds(0, 0, 115, 24);
		pnlTenKhach.add(lblLabelTenKhach);

		lblTenKhach = new JLabel("");
		lblTenKhach.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTenKhach.setBounds(125, 0, 150, 24);
		pnlTenKhach.add(lblTenKhach);

		JPanel pnlGioNhanPhong = new JPanel();
		pnlGioNhanPhong.setBackground(Color.WHITE);
		pnlGioNhanPhong.setLayout(null);
		pnlGioNhanPhong.setBounds(291, 0, 275, 24);
		pnlRow2.add(pnlGioNhanPhong);

		JLabel lbllabelGioNhanPhong = new JLabel("Giờ nhận phòng");
		lbllabelGioNhanPhong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lbllabelGioNhanPhong.setBounds(0, 0, 115, 24);
		pnlGioNhanPhong.add(lbllabelGioNhanPhong);

		lblGioNhanPhong = new JLabel("");
		lblGioNhanPhong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblGioNhanPhong.setBounds(125, 0, 150, 24);
		pnlGioNhanPhong.add(lblGioNhanPhong);

		JPanel pnlRow3 = new JPanel();
		pnlRow3.setBackground(Color.WHITE);
		pnlRow3.setLayout(null);
		pnlRow3.setBounds(0, 64, 566, 24);
		pnlBody.add(pnlRow3);

		JPanel pnlSoDienThoai = new JPanel();
		pnlSoDienThoai.setBackground(Color.WHITE);
		pnlSoDienThoai.setLayout(null);
		pnlSoDienThoai.setBounds(0, 0, 275, 24);
		pnlRow3.add(pnlSoDienThoai);

		JLabel lblLabelSoDienThoai = new JLabel("SĐT khách");
		lblLabelSoDienThoai.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblLabelSoDienThoai.setBounds(0, 0, 115, 24);
		pnlSoDienThoai.add(lblLabelSoDienThoai);

		cmbSoDienThoai = new JComboBox<String>();
		cmbSoDienThoai.setModel(new DefaultComboBoxModel<String>(new String[] { "Số điện thoại" }));
		cmbSoDienThoai.setForeground(Color.BLACK);
		cmbSoDienThoai.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		cmbSoDienThoai.setBackground(new Color(140, 177, 180));
		cmbSoDienThoai.setBounds(125, 0, 150, 24);
		pnlSoDienThoai.add(cmbSoDienThoai);

		JPanel pnlTongThoiLuong = new JPanel();
		pnlTongThoiLuong.setBackground(Color.WHITE);
		pnlTongThoiLuong.setLayout(null);
		pnlTongThoiLuong.setBounds(291, 0, 275, 24);
		pnlRow3.add(pnlTongThoiLuong);

		JLabel lblLabelTongThoiLuong = new JLabel("Tổng thời lượng");
		lblLabelTongThoiLuong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblLabelTongThoiLuong.setBounds(0, 0, 115, 24);
		pnlTongThoiLuong.add(lblLabelTongThoiLuong);

		lblTongThoiLuong = new JLabel("");
		lblTongThoiLuong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTongThoiLuong.setBounds(125, 0, 150, 24);
		pnlTongThoiLuong.add(lblTongThoiLuong);

		JPanel pnlActions = new JPanel();
		pnlActions.setBackground(Color.WHITE);
		pnlActions.setBounds(0, 395, 566, 38);
		pnlBody.add(pnlActions);
		pnlActions.setLayout(null);

		Button btnQuayLai = new Button("Quay lại");
		btnQuayLai.setFocusable(false);
		btnQuayLai.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnQuayLai.setRadius(4);
		btnQuayLai.setColor(Utils.secondaryColor);
		btnQuayLai.setColorOver(Utils.getOpacity(Utils.secondaryColor, 0.8f));
		btnQuayLai.setColorClick(Utils.getOpacity(Utils.secondaryColor, 0.6f));
		btnQuayLai.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnQuayLai.setBounds(0, 0, 100, 38);
		pnlActions.add(btnQuayLai);

		Button btnThanhToan = new Button("Thanh toán");
		btnThanhToan.setFocusable(false);
		btnThanhToan.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnThanhToan.setRadius(4);
		btnThanhToan.setColor(Utils.primaryColor);
		btnThanhToan.setColorOver(Utils.getOpacity(Utils.primaryColor, 0.8f));
		btnThanhToan.setColorClick(Utils.getOpacity(Utils.primaryColor, 0.6f));
		btnThanhToan.setForeground(Color.WHITE);
		btnThanhToan.setColor(Utils.primaryColor);
		btnThanhToan.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnThanhToan.setBounds(466, 0, 100, 38);
		pnlActions.add(btnThanhToan);

		JCheckBox chkXuatHoaDon = new JCheckBox("Xuất hóa đơn");
		chkXuatHoaDon.setFocusable(false);
		chkXuatHoaDon.setBackground(Color.WHITE);
		chkXuatHoaDon.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		chkXuatHoaDon.setBounds(336, 7, 120, 24);
		pnlActions.add(chkXuatHoaDon);

		JPanel pnlFooter = new JPanel();
		pnlFooter.setBackground(Color.WHITE);
		pnlFooter.setBounds(0, 268, 566, 119);
		pnlBody.add(pnlFooter);
		pnlFooter.setLayout(null);

		JPanel pnlFooterRow1 = new JPanel();
		pnlFooterRow1.setLayout(null);
		pnlFooterRow1.setBackground(Color.WHITE);
		pnlFooterRow1.setBounds(0, 0, 566, 24);
		pnlFooter.add(pnlFooterRow1);

		JPanel pnlMaDatPhong_1 = new JPanel();
		pnlMaDatPhong_1.setLayout(null);
		pnlMaDatPhong_1.setBackground(Color.WHITE);
		pnlMaDatPhong_1.setBounds(0, 0, 275, 24);
		pnlFooterRow1.add(pnlMaDatPhong_1);

		JLabel lblTinDchV = new JLabel("Tiền dịch vụ");
		lblTinDchV.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTinDchV.setBounds(0, 0, 115, 24);
		pnlMaDatPhong_1.add(lblTinDchV);

		JLabel lblVnd = new JLabel("");
		lblVnd.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblVnd.setBounds(125, 0, 150, 24);
		pnlMaDatPhong_1.add(lblVnd);

		JPanel pnlGioNhanPhong_2 = new JPanel();
		pnlGioNhanPhong_2.setLayout(null);
		pnlGioNhanPhong_2.setBackground(Color.WHITE);
		pnlGioNhanPhong_2.setBounds(291, 0, 275, 24);
		pnlFooterRow1.add(pnlGioNhanPhong_2);

		JLabel lblTinPhng = new JLabel("Tiền phòng");
		lblTinPhng.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTinPhng.setBounds(0, 0, 115, 24);
		pnlGioNhanPhong_2.add(lblTinPhng);

		JLabel lblVnd_1 = new JLabel("");
		lblVnd_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblVnd_1.setBounds(125, 0, 150, 24);
		pnlGioNhanPhong_2.add(lblVnd_1);

		JPanel pnlFooterRow2 = new JPanel();
		pnlFooterRow2.setLayout(null);
		pnlFooterRow2.setBackground(Color.WHITE);
		pnlFooterRow2.setBounds(0, 32, 566, 24);
		pnlFooter.add(pnlFooterRow2);

		JPanel pnlMaDatPhong_1_1 = new JPanel();
		pnlMaDatPhong_1_1.setLayout(null);
		pnlMaDatPhong_1_1.setBackground(Color.WHITE);
		pnlMaDatPhong_1_1.setBounds(0, 0, 275, 24);
		pnlFooterRow2.add(pnlMaDatPhong_1_1);

		JLabel lblThuVat = new JLabel("Thuế VAT");
		lblThuVat.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblThuVat.setBounds(0, 0, 115, 24);
		pnlMaDatPhong_1_1.add(lblThuVat);

		JLabel lblVnd_2 = new JLabel("");
		lblVnd_2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblVnd_2.setBounds(125, 0, 150, 24);
		pnlMaDatPhong_1_1.add(lblVnd_2);

		JPanel pnlGioNhanPhong_2_1 = new JPanel();
		pnlGioNhanPhong_2_1.setLayout(null);
		pnlGioNhanPhong_2_1.setBackground(Color.WHITE);
		pnlGioNhanPhong_2_1.setBounds(291, 0, 275, 24);
		pnlFooterRow2.add(pnlGioNhanPhong_2_1);

		JLabel lblTinThanhTon = new JLabel("Tiền thanh toán");
		lblTinThanhTon.setForeground(Color.RED);
		lblTinThanhTon.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTinThanhTon.setBounds(0, 0, 115, 24);
		pnlGioNhanPhong_2_1.add(lblTinThanhTon);

		JLabel lblVnd_1_1 = new JLabel("");
		lblVnd_1_1.setForeground(Color.RED);
		lblVnd_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblVnd_1_1.setBounds(125, 0, 150, 24);
		pnlGioNhanPhong_2_1.add(lblVnd_1_1);

		JPanel pnlRow6 = new JPanel();
		pnlRow6.setBackground(Color.WHITE);
		pnlRow6.setBounds(0, 64, 566, 55);
		pnlFooter.add(pnlRow6);
		pnlRow6.setLayout(null);

		txtTienNhan = new TextField() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Color getBackground() {
				if (!isEditable())
					return Color.WHITE;
				return super.getBackground();
			}
		};
		txtTienNhan.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtTienNhan.setError(false);
			}

			@Override
			public void focusLost(FocusEvent e) {
				xuLyTienNhanHopLe();
			}
		});
		txtTienNhan.setEditable(false);
		txtTienNhan.setLabelText("Tiền nhận");
		txtTienNhan.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTienNhan.setBounds(0, 0, 275, 55);
		pnlRow6.add(txtTienNhan);
		txtTienNhan.setColumns(10);
		txtTienNhan.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if ((e.getKeyChar() >= '0' && e.getKeyChar() <= '9') || e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
					txtTienNhan.setEditable(true);
				} else {
					txtTienNhan.setEditable(false);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				xuLyTienNhanHopLe();
			}
		});

		txtTienThua = new TextField() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Color getBackground() {
				if (!isEditable())
					return Color.WHITE;
				return super.getBackground();
			}
		};
		txtTienThua.setEditable(false);
		txtTienThua.setLabelText("Tiền thừa");
		txtTienThua.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTienThua.setColumns(10);
		txtTienThua.setBounds(291, 0, 275, 55);
		pnlRow6.add(txtTienThua);

//		Table danh sách nhân viên
		JScrollPane scr = new JScrollPane();
		scr.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scr.setBounds(0, 98, 566, 156);
		scr.setBackground(Utils.primaryColor);
		scr.getViewport().setBackground(Color.WHITE);
		ScrollBarCustom scp = new ScrollBarCustom();
//		Set color scrollbar thumb
		scp.setScrollbarColor(new Color(203, 203, 203));
		scr.setVerticalScrollBar(scp);
		pnlBody.add(scr);
		tbl = new JTable() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			/**
			 * Set màu từng dòng cho Table
			 */
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				if (isRowSelected(row))
					c.setBackground(Utils.getOpacity(Utils.primaryColor, 0.3f));
				else if (row % 2 == 0)
					c.setBackground(Color.WHITE);
				else
					c.setBackground(new Color(232, 232, 232));
				return c;
			}

			@Override
			public boolean getShowVerticalLines() {
				// TODO Auto-generated method stub
				return false;
			}
		};

		tableModel = new DefaultTableModel(
				new String[] { "STT", "Tên dịch vụ", "Số lượng/Thời gian", "Đơn giá", "Thành tiền" }, 0);

		tbl.setModel(tableModel);
		tbl.setFocusable(false);
//		Cam
//		tbl.getTableHeader().setBackground(new Color(255, 195, 174));
//		Xanh
		tbl.getTableHeader().setBackground(Utils.primaryColor);
		tbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tbl.getColumnModel().getColumn(0).setPreferredWidth(40);
		tbl.getColumnModel().getColumn(1).setPreferredWidth(150);
		tbl.getColumnModel().getColumn(2).setPreferredWidth(150);
		tbl.getColumnModel().getColumn(3).setPreferredWidth(113);
		tbl.getColumnModel().getColumn(4).setPreferredWidth(113);
		tbl.getTableHeader()
				.setPreferredSize(new Dimension((int) tbl.getTableHeader().getPreferredSize().getWidth(), 24));
		tbl.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl.setRowHeight(24);
		scr.setViewportView(tbl);

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				List<DatPhong> list = datPhong_DAO.getAllDatPhongDangThue();

				list.forEach(datPhong -> {
					cmbMaDatPhong.addItem(datPhong.getMaDatPhong());
					KhachHang khachHang = khachHang_DAO.getKhachHangTheoMa(datPhong.getKhachHang().getMaKhachHang());
					cmbSoDienThoai.addItem(khachHang.getSoDienThoai());
				});

				cmbMaDatPhong.addItemListener(_this);
				cmbSoDienThoai.addItemListener(_this);
			}
		});
	}

	/**
	 * Xử lý tiền nhận
	 */
	private void xuLyTienNhanHopLe() {
		String tienNhanS = txtTienNhan.getText().trim();

		if (tienNhanS.length() <= 0) {
			txtTienNhan.setError(true);
			return;
		}

		double tienNhan = Double.parseDouble(tienNhanS);

		if (tienNhan < tienThanhToan)
			txtTienNhan.setError(true);
		else {
			txtTienNhan.setError(false);
			txtTienThua.setText(Utils.formatTienTe(tienNhan - tienThanhToan));
		}
	}

	private void emptyTable() {
		while (tbl.getRowCount() > 0)
			tableModel.removeRow(0);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.DESELECTED)
			return;
		Object o = e.getSource();
		boolean isMaDatPhong = true;
		cmbMaDatPhong.removeItemListener(_this);
		cmbSoDienThoai.removeItemListener(_this);
		if (o.equals(cmbMaDatPhong)) {
			if (((String) cmbMaDatPhong.getSelectedItem()).equals("Mã đặt phòng")) {
				datPhong = null;
				cmbSoDienThoai.setSelectedIndex(0);
			} else {
				datPhong = datPhong_DAO.getDatPhong((String) cmbMaDatPhong.getSelectedItem());
			}
		} else if (o.equals(cmbSoDienThoai)) {
			isMaDatPhong = false;
			if (((String) cmbSoDienThoai.getSelectedItem()).equals("Số điện thoại")) {
				datPhong = null;
				cmbMaDatPhong.setSelectedIndex(0);
			} else {
				datPhong = datPhong_DAO.getDatPhongNgayTheoSoDienThoai((String) cmbSoDienThoai.getSelectedItem());
			}
		}
		if (datPhong != null) {
			KhachHang khachHang = khachHang_DAO.getKhachHangTheoMa(datPhong.getKhachHang().getMaKhachHang());
			NhanVien nhanVien = nhanVien_DAO.getNhanVienTheoMa(datPhong.getNhanVien().getMaNhanVien());

			if (isMaDatPhong) {
				cmbSoDienThoai.setSelectedItem(khachHang.getSoDienThoai());
			} else {
				cmbMaDatPhong.setSelectedItem(datPhong.getMaDatPhong());
			}

			lblTenKhach.setText(khachHang.getHoTen());
			lblTenNhanVien.setText(nhanVien.getHoTen());
			lblGioNhanPhong.setText(Utils.convertLocalTimeToString(datPhong.getGioNhanPhong()) + " - "
					+ Utils.formatDate(datPhong.getNgayNhanPhong()));
			dsChiTietDatPhong = chiTietDatPhong_DAO.getAllChiTietDatPhong(datPhong);
			System.out.println(dsChiTietDatPhong);
		} else {
			lblTenKhach.setText("");
			lblTenNhanVien.setText("");
			lblGioNhanPhong.setText("");
			lblTongThoiLuong.setText("");
			dsChiTietDatPhong = null;
			emptyTable();
		}
		cmbMaDatPhong.addItemListener(_this);
		cmbSoDienThoai.addItemListener(_this);
	}
}
