package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import components.button.Button;
import components.jDialog.JDialogCustom;
import components.notification.Notification;
import components.scrollbarCustom.ScrollBarCustom;
import components.textField.TextField;
import dao.ChiTietDichVu_DAO;
import dao.DatPhong_DAO;
import dao.KhachHang_DAO;
import entity.ChiTietDichVu;
import entity.DatPhong;
import entity.DichVu;
import entity.KhachHang;
import entity.LoaiDichVu;
import utils.Utils;

public class ThemDichVuVaoPhong_GUI extends JFrame implements ItemListener, WindowListener {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private TextField txtSoDienThoai;
	private TextField txtTenKhachHang;
	private KhachHang_DAO khachHang_DAO;
	private KhachHang khachHang;
	private DatPhong_DAO datPhong_DAO;
	private dao.LoaiDichVu_DAO LoaiDichVu_DAO;
	private dao.DichVu_DAO DichVu_DAO;
	private ChiTietDichVu_DAO chiTietDichVu_DAO;
	private DefaultTableModel tableModel2, tableModel3;
	private JTable tbl2, tbl3;
	private List<DichVu> dsDVDaChon;
	private String maDatPhongChon;
	private JPanel pnlDV;
	private JComboBox<String> cmbDatPhong;
	private JComboBox<String> cmbTenDV;
	private JComboBox<String> cmbLoaiDV;
	private Button btnDatDV;

	private ThemDichVuVaoPhong_GUI _this;

	public ThemDichVuVaoPhong_GUI(QuanLyDatPhong_GUI quanLyDatPhongGUI, JFrame parentFrame) {
		_this = this;
		khachHang_DAO = new KhachHang_DAO();
		LoaiDichVu_DAO = new dao.LoaiDichVu_DAO();
		datPhong_DAO = new DatPhong_DAO();
		DichVu_DAO = new dao.DichVu_DAO();
		chiTietDichVu_DAO = new ChiTietDichVu_DAO();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 466);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);
		setLocationRelativeTo(null);

		JPanel pnlContainer = new JPanel();
		pnlContainer.setBackground(Utils.secondaryColor);
		pnlContainer.setBounds(0, 0, 950, 466);
		contentPane.add(pnlContainer);
		pnlContainer.setLayout(null);

		JPanel pnlHeading = new JPanel();
		pnlHeading.setBackground(Utils.primaryColor);
		pnlHeading.setBounds(0, 0, 950, 50);
		pnlContainer.add(pnlHeading);
		pnlHeading.setLayout(null);

		JLabel lblTitle = new JLabel("Thêm dịch vụ vào phòng");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setBounds(325, 9, 300, 32);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
		pnlHeading.add(lblTitle);

		JPanel pnlBody = new JPanel();
		pnlBody.setBackground(Utils.secondaryColor);
		pnlBody.setBounds(40, 50, 870, 398);
		pnlContainer.add(pnlBody);
		pnlBody.setLayout(null);

		txtSoDienThoai = new TextField();
		txtSoDienThoai.setBackground(Utils.secondaryColor);
		txtSoDienThoai.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtSoDienThoai.setLabelText("Số điện thoại khách");
		txtSoDienThoai.setBounds(0, 0, 380, 55);
		pnlBody.add(txtSoDienThoai);
		txtSoDienThoai.setColumns(10);
		btnDatDV = new Button("Đặt dịch vụ");
		Button btnSearchSoDienThoai = new Button();
		btnSearchSoDienThoai.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String soDienThoai = txtSoDienThoai.getText().trim();

				if (Utils.isSoDienThoai(soDienThoai)) {
					khachHang = khachHang_DAO.getKhachHang(soDienThoai);

					if (khachHang != null) {
						txtTenKhachHang.setText(khachHang.getHoTen());
						List<DatPhong> listDatPhongDangThue = datPhong_DAO.getAllDatPhongDangThue();
						emptyComboBox(cmbDatPhong, "Mã đặt phòng");
						for (DatPhong datPhong : listDatPhongDangThue) {
							if (datPhong.getKhachHang().getMaKhachHang().equals(khachHang.getMaKhachHang())) {
								cmbDatPhong.addItem(datPhong.getMaDatPhong());
							}

						}

					} else {
						JDialogCustom jDialogCustom = new JDialogCustom(_this);

						jDialogCustom.getBtnOK().addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								Main main = new Main();
								main.addPnlBody(new ThemKhachHang_GUI(main, _this, soDienThoai), "Thêm khách hàng", 2,
										0);
								main.setVisible(true);
								setVisible(false);
							};
						});

						jDialogCustom.showMessage("Warning",
								"Khách hàng không có trong hệ thống, bạn có muốn thêm khách hàng mới không?");
					}
				} else {
					new Notification(_this, components.notification.Notification.Type.ERROR,
							"Số điện thoại không hợp lệ").showNotification();
					txtSoDienThoai.setError(true);
				}
				if (txtTenKhachHang.getText().equals("") || txtTenKhachHang == null) {
					List<DatPhong> listDatPhongDangThue = datPhong_DAO.getAllDatPhongDangThue();
					emptyComboBox(cmbDatPhong, "Mã đặt phòng");
					for (DatPhong datPhong : listDatPhongDangThue) {
						cmbDatPhong.addItem(datPhong.getMaDatPhong());
					}
				}
			}

		});

		btnSearchSoDienThoai.setFocusable(false);
		btnSearchSoDienThoai.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnSearchSoDienThoai.setRadius(4);
		btnSearchSoDienThoai.setBorderColor(Utils.secondaryColor);
		btnSearchSoDienThoai.setColor(Utils.primaryColor);
		btnSearchSoDienThoai.setColorOver(Utils.getOpacity(Utils.primaryColor, 0.9f));
		btnSearchSoDienThoai.setColorClick(Utils.getOpacity(Utils.primaryColor, 0.8f));
		btnSearchSoDienThoai.setIcon(new ImageIcon("Icon\\user_searching.png"));
		btnSearchSoDienThoai.setBounds(410, 2, 50, 50);
		pnlBody.add(btnSearchSoDienThoai);

		txtTenKhachHang = new TextField();
		txtTenKhachHang.setLabelText("Họ tên khách");
		txtTenKhachHang.setEditable(false);
		txtTenKhachHang.setBackground(Utils.secondaryColor);
		txtTenKhachHang.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTenKhachHang.setBounds(490, 0, 380, 55);
		pnlBody.add(txtTenKhachHang);
		txtTenKhachHang.setColumns(10);

		pnlDV = new JPanel();
		pnlDV.setBackground(Utils.secondaryColor);
		pnlDV.setBounds(0, 121, 870, 225);
		pnlBody.add(pnlDV);
		pnlDV.setLayout(null);

		JPanel pnlActions = new JPanel();
		pnlActions.setBackground(Utils.secondaryColor);
		pnlActions.setBounds(410, 0, 36, 225);
		pnlDV.add(pnlActions);
		pnlActions.setLayout(null);

		Button btnNewButton_2 = new Button("");
		btnNewButton_2.setFocusable(false);
		btnNewButton_2.setRadius(8);
		btnNewButton_2.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnNewButton_2.setColor(Utils.primaryColor);
		btnNewButton_2.setColorOver(Utils.getOpacity(Utils.primaryColor, 0.8f));
		btnNewButton_2.setColorClick(Utils.getOpacity(Utils.primaryColor, 0.6f));
		btnNewButton_2.setBorderColor(Utils.secondaryColor);
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row2 = tbl2.getSelectedRow();
				if (row2 != -1) {
					maDatPhongChon = (String) cmbDatPhong.getSelectedItem();
					if (dsDVDaChon == null)
						dsDVDaChon = new ArrayList<>();

					DichVu DichVuChon = new DichVu((String) tableModel2.getValueAt(row2, 0));

					if (dsDVDaChon.contains(DichVuChon))
						return;
					DichVuChon = DichVu_DAO.getDichVuTheoMa(DichVuChon.getMaDichVu());
					DichVuChon.setSoLuong(1);
					dsDVDaChon.add(DichVuChon);
					addRow3(dsDVDaChon);

				}
				if (maDatPhongChon != "Mã đặt phòng" && dsDVDaChon != null && dsDVDaChon.size() > 0)
					btnDatDV.setEnabled(true);
			}
		});
		btnNewButton_2.setIcon(new ImageIcon("Icon\\rightArrow_32x32.png"));
		btnNewButton_2.setBounds(0, 70, 36, 36);
		pnlActions.add(btnNewButton_2);

		Button btnNewButton_3 = new Button("");
		btnNewButton_3.setFocusable(false);
		btnNewButton_3.setRadius(8);
		btnNewButton_3.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnNewButton_3.setColor(Utils.primaryColor);
		btnNewButton_3.setColorOver(Utils.getOpacity(Utils.primaryColor, 0.8f));
		btnNewButton_3.setColorClick(Utils.getOpacity(Utils.primaryColor, 0.6f));
		btnNewButton_3.setBorderColor(Utils.secondaryColor);
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tbl3.getSelectedRow();
				if (row != -1) {

					DichVu DichVuXoa = new DichVu((String) tableModel3.getValueAt(row, 0));
					dsDVDaChon.remove(DichVuXoa);
					addRow3(dsDVDaChon);
				}
				if (maDatPhongChon != "Mã đặt phòng" && dsDVDaChon != null && dsDVDaChon.size() > 0)
					btnDatDV.setEnabled(true);
				else
					btnDatDV.setEnabled(false);
			}
		});
		btnNewButton_3.setIcon(new ImageIcon("Icon\\bin.png"));
		btnNewButton_3.setBounds(0, 130, 36, 36);
		pnlActions.add(btnNewButton_3);

		// table Danh sách dịch vụ

		JScrollPane scrDanhSachDichVu = new JScrollPane();
		scrDanhSachDichVu.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrDanhSachDichVu.setBackground(Utils.secondaryColor);
		scrDanhSachDichVu.setBounds(0, 0, 400, 225);
		pnlDV.add(scrDanhSachDichVu);
		ScrollBarCustom scbDanhSachDichVu = new ScrollBarCustom();
		// Set color scrollbar thumb
		scbDanhSachDichVu.setScrollbarColor(new Color(203, 203, 203));
		scrDanhSachDichVu.setVerticalScrollBar(scbDanhSachDichVu);

		tbl2 = new JTable() {
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
		};

		tableModel2 = new DefaultTableModel(new String[] { "Mã DV", "Tên DV", "Số lượng", "Đơn giá" }, 0);

		tbl2.setModel(tableModel2);
		tbl2.setFocusable(false);
//		Xanh
		tbl2.getTableHeader().setBackground(Utils.primaryColor);
		tbl2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl2.setBackground(Color.WHITE);
		tbl2.getTableHeader()
				.setPreferredSize(new Dimension((int) tbl2.getTableHeader().getPreferredSize().getWidth(), 36));
		tbl2.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl2.setRowHeight(36);
		tbl2.getColumnModel().getColumn(0).setPreferredWidth(80);
		tbl2.getColumnModel().getColumn(1).setPreferredWidth(130);
		tbl2.getColumnModel().getColumn(2).setPreferredWidth(80);
		tbl2.getColumnModel().getColumn(3).setPreferredWidth(110);

		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.RIGHT);
		tbl2.getColumnModel().getColumn(2).setCellRenderer(dtcr);
		tbl2.getColumnModel().getColumn(3).setCellRenderer(dtcr);

		scrDanhSachDichVu.setViewportView(tbl2);

		JPanel pnlBtnGroup = new JPanel();
		pnlBtnGroup.setBackground(Utils.secondaryColor);
		pnlBtnGroup.setBounds(0, 362, 770, 36);
		pnlBody.add(pnlBtnGroup);
		pnlBtnGroup.setLayout(null);

		btnDatDV.setEnabled(false);
		btnDatDV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ArrayList<DichVu> dsDVChonSoLuong = new ArrayList<>();
				boolean res1 = true, res2 = true;
				DichVu DichVuChon;
				for (int i = 0; i < tableModel3.getRowCount(); i++) {
					DichVuChon = new DichVu((String) tableModel3.getValueAt(i, 0));
					DichVuChon.setSoLuong(Integer.valueOf((String) tbl3.getValueAt(i, 2)));
					dsDVChonSoLuong.add(DichVuChon);
				}

				List<ChiTietDichVu> ListChiTietDV = new ArrayList<ChiTietDichVu>();
				ListChiTietDV = chiTietDichVu_DAO.getAllChiTietDichVu();
				int CH = 0;
				for (DichVu dichVuChon : dsDVChonSoLuong) {
					res1 = DichVu_DAO.CapNhatSoLuongDichVu(dichVuChon.getMaDichVu(), dichVuChon.getSoLuong());
					for (ChiTietDichVu chiTietDichVu : ListChiTietDV) {
						if (maDatPhongChon.equals(chiTietDichVu.getDatPhong().getMaDatPhong())) {
							if (dichVuChon.getMaDichVu().equals(chiTietDichVu.getDichVu().getMaDichVu())) {
								res2 = chiTietDichVu_DAO.CapNhatSoLuongDichVu(dichVuChon.getMaDichVu(),
										chiTietDichVu.getDatPhong().getMaDatPhong(), dichVuChon.getSoLuong());
								CH = 1;
								break;
							}
						}
					}
					if (CH == 0) {
						res2 = chiTietDichVu_DAO.ThemChiTietDichVu(new ChiTietDichVu(dichVuChon,
								datPhong_DAO.getDatPhong(maDatPhongChon), dichVuChon.getSoLuong()));
					}

					System.out.print(CH);
					System.out.print(res1);
					System.out.print(res2);
					if (!res1 || !res2) {
						new Notification(_this, components.notification.Notification.Type.ERROR,
								"Đặt dịch vụ không thành công").showNotification();

					} else {
						new Notification(_this, components.notification.Notification.Type.SUCCESS,
								"Đặt dịch vụ thành công").showNotification();
						emptyTable3();
						dsDVDaChon.removeAll(dsDVDaChon);

						addRow2(DichVu_DAO.getAllDichVu());
					}

				}

			}
		});
		btnDatDV.setFocusable(false);
		btnDatDV.setRadius(8);
		btnDatDV.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnDatDV.setBorderColor(Utils.secondaryColor);
		btnDatDV.setForeground(Color.WHITE);
		btnDatDV.setColor(Utils.primaryColor);
		btnDatDV.setColorOver(Utils.getOpacity(Utils.primaryColor, 0.8f));
		btnDatDV.setColorClick(Utils.getOpacity(Utils.primaryColor, 0.6f));
		btnDatDV.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnDatDV.setBounds(395, 0, 150, 36);
		pnlBtnGroup.add(btnDatDV);

		Button btnQuayLai = new Button("Quay lại");
		btnQuayLai.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				quanLyDatPhongGUI.closeJFrameSub();
			}
		});
		btnQuayLai.setFocusable(false);
		btnQuayLai.setRadius(8);
		btnQuayLai.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnQuayLai.setBorderColor(Utils.secondaryColor);
		btnQuayLai.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnQuayLai.setBounds(225, 0, 150, 36);
		pnlBtnGroup.add(btnQuayLai);

		JPanel pnlFilter = new JPanel();
		pnlFilter.setBackground(Utils.secondaryColor);
		pnlFilter.setBounds(0, 70, 870, 36);
		pnlBody.add(pnlFilter);
		pnlFilter.setLayout(null);

		cmbDatPhong = new JComboBox<String>();
		cmbDatPhong.addItem("Mã đặt phòng");
		List<DatPhong> listDatPhongDangThue = datPhong_DAO.getAllDatPhongDangThue();
		for (DatPhong datPhong : listDatPhongDangThue) {
			cmbDatPhong.addItem(datPhong.getMaDatPhong());
		}
		cmbDatPhong.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbDatPhong.setBackground(Utils.primaryColor);
		cmbDatPhong.setBounds(40, 0, 200, 36);
		pnlFilter.add(cmbDatPhong);

		cmbTenDV = new JComboBox<String>();
		cmbTenDV = new JComboBox<String>();
		cmbTenDV.addItem("Tên dịch vụ");
		ArrayList<DichVu> listDichVu = (ArrayList<DichVu>) DichVu_DAO.getAllDichVu();
		for (DichVu dichVu : listDichVu) {
			cmbTenDV.addItem(dichVu.getTenDichVu());
		}
		cmbTenDV.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbTenDV.setBackground(Utils.primaryColor);
		cmbTenDV.setBounds(280, 0, 200, 36);
		pnlFilter.add(cmbTenDV);

		cmbLoaiDV = new JComboBox<String>();
		cmbLoaiDV.setBackground(Utils.primaryColor);
		cmbLoaiDV.addItem("Phân loại");
		ArrayList<LoaiDichVu> listLoaiDV = (ArrayList<LoaiDichVu>) LoaiDichVu_DAO.getAllLoaiDichVu();
		for (LoaiDichVu loaiDV : listLoaiDV) {
			cmbLoaiDV.addItem(loaiDV.getTenLoaiDichVu());
		}
		cmbLoaiDV.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbLoaiDV.setBounds(520, 0, 200, 36);
		pnlFilter.add(cmbLoaiDV);

		Button btnLamMoi = new Button("Làm mới");
		btnLamMoi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cmbLoaiDV.removeItemListener(_this);
				cmbTenDV.removeItemListener(_this);
				cmbLoaiDV.setSelectedIndex(0);
				cmbTenDV.setSelectedIndex(0);
				cmbLoaiDV.addItemListener(_this);
				cmbTenDV.addItemListener(_this);

			}
		});
		btnLamMoi.setFocusable(false);
		btnLamMoi.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnLamMoi.setBounds(760, 0, 110, 36);
		pnlFilter.add(btnLamMoi);

		txtSoDienThoai.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				khachHang = null;
				txtTenKhachHang.setText("");
				txtSoDienThoai.setError(false);
			}
		});

		txtSoDienThoai.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtSoDienThoai.setError(false);
			}
		});

		showDanhSachDichVuDaChon();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {

				List<DichVu> listDV = DichVu_DAO.getAllDichVu();

				cmbTenDV.removeItemListener(_this);

				// addRow1(listDatPhongDangThue);
				addRow2(listDV);

				String soDienThoai = txtSoDienThoai.getText().trim();

				if (Utils.isSoDienThoai(soDienThoai)) {
					khachHang = khachHang_DAO.getKhachHang(soDienThoai);

					if (khachHang != null) {
						txtTenKhachHang.setText(khachHang.getHoTen());
						txtTenKhachHang.requestFocus();
					}
				}

				cmbTenDV.addItemListener(_this);
				cmbLoaiDV.addItemListener(_this);

			}
		});
	}

	private void showDanhSachDichVuDaChon() {

		JScrollPane scrDanhSachDichVuDuocChon = new JScrollPane();
		scrDanhSachDichVuDuocChon.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrDanhSachDichVuDuocChon.setBackground(Utils.secondaryColor);
		scrDanhSachDichVuDuocChon.setBounds(460, 0, 410, 225);
		pnlDV.add(scrDanhSachDichVuDuocChon);
		ScrollBarCustom scbDanhSachDichVuDuocChon = new ScrollBarCustom();
		// Set color scrollbar thumb
		scbDanhSachDichVuDuocChon.setScrollbarColor(new Color(203, 203, 203));
		scrDanhSachDichVuDuocChon.setVerticalScrollBar(scbDanhSachDichVuDuocChon);

		tbl3 = new JTable() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				if (column == 2)
					return true;
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
		};

		tableModel3 = new DefaultTableModel(new String[] { "Mã DV", "Tên DV", "Số lượng", "Thành tiền" }, 0);
		tbl3.setModel(tableModel3);
		tbl3.setFocusable(false);
		// Cam
		tbl3.getTableHeader().setBackground(new Color(255, 195, 174));
//		Xanh
		tbl3.getTableHeader().setBackground(Utils.primaryColor);
		tbl3.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl3.setBackground(Color.WHITE);
		tbl3.getTableHeader()
				.setPreferredSize(new Dimension((int) tbl3.getTableHeader().getPreferredSize().getWidth(), 36));
		tbl3.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl3.setRowHeight(36);

		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.RIGHT);
		tbl3.getColumnModel().getColumn(0).setPreferredWidth(80);
		tbl3.getColumnModel().getColumn(1).setPreferredWidth(130);
		tbl3.getColumnModel().getColumn(2).setPreferredWidth(80);
		tbl3.getColumnModel().getColumn(3).setPreferredWidth(120);

		tbl3.getColumnModel().getColumn(3).setCellRenderer(dtcr);
		tbl3.getColumnModel().getColumn(2).setCellRenderer(dtcr);
		scrDanhSachDichVuDuocChon.setViewportView(tbl3);

		tbl3.getModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				switch (e.getType()) {
				case TableModelEvent.INSERT:
					break;
				case TableModelEvent.UPDATE:
					int row3 = tbl3.getSelectedRow();
					if (row3 != -1) {
						DichVu DichVuThayDoiSoLuong = new DichVu((String) tableModel3.getValueAt(row3, 0));
						dsDVDaChon.forEach(dichVu -> {
							if (dichVu.getMaDichVu().equals(DichVuThayDoiSoLuong.getMaDichVu())) {
								dichVu.setSoLuong(Integer.parseInt((String) tbl3.getValueAt(row3, 2)));
								return;
							}
						});
					}
					addRow3(dsDVDaChon);
					break;
				case TableModelEvent.DELETE:
					break;
				}

			}
		});

		if (pnlDV.getComponentAt(620, 0) != null) {
			pnlDV.remove(pnlDV.getComponentAt(620, 0));
		}

		pnlDV.add(scrDanhSachDichVuDuocChon);

		cmbTenDV.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					filterDichVu();
				}
			}
		});

		cmbLoaiDV.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					filterDichVu();
				}
			}
		});

		cmbDatPhong.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				maDatPhongChon = (String) cmbDatPhong.getSelectedItem();
				if (maDatPhongChon != "Mã đặt phòng" && dsDVDaChon != null && dsDVDaChon.size() > 0)
					btnDatDV.setEnabled(true);
				else
					btnDatDV.setEnabled(false);
			}
		});

	}

//	private PanelRound getPanelDichVuDaChonItem(int top, DichVu dichVu) {
//		PanelRound pnlContainerItem = new PanelRound(8);
//		pnlContainerItem.setBackground(Utils.primaryColor);
//		pnlContainerItem.setBounds(11, top, 118, 36);
//		pnlContainerItem.setLayout(null);
//
//		JLabel lblMaDV = new JLabel(dichVu.getTenDichVu());
//		lblMaDV.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		lblMaDV.setBounds(4, 0, 94, 36);
//		pnlContainerItem.add(lblMaDV);
//
//		JLabel lblIconClose = new JLabel("");
//		lblIconClose.setIcon(new ImageIcon("Icon\\close_16x16.png"));
//		lblIconClose.setBounds(94, 10, 16, 16);
//		pnlContainerItem.add(lblIconClose);
//		lblIconClose.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				dsDVDaChon.remove(dichVu);
//				showDanhSachDichVuDaChon();
//			}
//		});
//
//		return pnlContainerItem;
//	}

	/**
	 * Thêm một DV vào table
	 * 
	 * @param dichVu dichVu muốn thêm
	 */
	private void addRow2(DichVu dichVu) {
		// dichVu.setLoaiPhong(loaiPhong_DAO.getLoaiPhong(phong.getLoaiPhong().getMaLoai()));
		tableModel2.addRow(new String[] { dichVu.getMaDichVu(), dichVu.getTenDichVu(), dichVu.getSoLuong() + "",
				Utils.formatTienTe(dichVu.getGiaBan()) });
	}

	/**
	 * Thêm danh sách các DV vào table
	 * 
	 * @param list danh sách các DV cần thêm
	 */
	private void addRow2(List<DichVu> list) {
		emptyTable2();

		list.forEach(dichVu -> {
			addRow2(dichVu);
		});
	}

	/**
	 * Thêm một DV vào table
	 * 
	 * @param dichVu dichVu muốn thêm
	 */
	private void addRow3(DichVu dichVu) {
		tableModel3.addRow(new String[] { dichVu.getMaDichVu(), dichVu.getTenDichVu(),
				String.valueOf(dichVu.getSoLuong()), Utils.formatTienTe(dichVu.getGiaBan() * dichVu.getSoLuong()) });
	}

	/**
	 * Thêm danh sách các DV vào table
	 * 
	 * @param list danh sách các DV cần thêm
	 */
	private void addRow3(List<DichVu> list) {
		emptyTable3();
		list.forEach(dichVu -> {
			addRow3(dichVu);
		});
	}

	/**
	 * Xóa tất các các item trong ComboBox và thêm label vào ComboBox
	 * 
	 * @param jComboBox ComboBox
	 * @param label
	 */
	private void emptyComboBox(JComboBox<String> jComboBox, String label) {
		emptyComboBox(jComboBox);
		jComboBox.addItem(label);
	}

	/**
	 * Xóa tất các các item trong ComboBox và thêm label vào ComboBox
	 * 
	 * @param jComboBox
	 */
	private void emptyComboBox(JComboBox<String> jComboBox) {
		jComboBox.removeAllItems();
	}

	/**
	 * Xóa tất các các row trong table
	 */

	private void emptyTable2() {
		while (tbl2.getRowCount() > 0)
			tableModel2.removeRow(0);
	}

	private void emptyTable3() {
		while (tbl3.getRowCount() > 0)
			tableModel3.removeRow(0);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.DESELECTED)
			return;

		filterDichVu();
	}

	/**
	 * Lọc danh sách các phòng theo mã phòng, loại phòng và số lượng
	 */
	private void filterDichVu() {
		String maDV = (String) cmbTenDV.getSelectedItem();
		String loaiDV = (String) cmbLoaiDV.getSelectedItem();

		if (maDV.equals("Tên dịch vụ"))
			maDV = "";
		if (loaiDV.equals("Phân loại"))
			loaiDV = "";

		List<DichVu> list = DichVu_DAO.getDichVuTheoMaVaLoai(maDV, loaiDV);
		emptyTable2();
		addRow2(list);

	}

	@SuppressWarnings("unused")
	private boolean kiemTraCTDichVuTonTai(List<ChiTietDichVu> list, String maDatPhong, String maDichVu) {
		for (ChiTietDichVu chiTietDichVu : list) {
			if (chiTietDichVu.getDatPhong().getMaDatPhong().equals(maDatPhong)) {
				if (chiTietDichVu.getDichVu().getMaDichVu().equals(maDichVu))
					return true;
			}
		}
		return false;
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
