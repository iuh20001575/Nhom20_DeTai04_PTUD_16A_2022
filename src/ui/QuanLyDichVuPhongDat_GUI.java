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
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
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
import dao.DichVu_DAO;
import dao.KhachHang_DAO;
import dao.LoaiDichVu_DAO;
import entity.ChiTietDichVu;
import entity.DichVu;
import entity.DonDatPhong;
import entity.KhachHang;
import entity.LoaiDichVu;
import utils.Utils;

public class QuanLyDichVuPhongDat_GUI extends JFrame implements ItemListener {
	private static final long serialVersionUID = 1L;
	private JPanel pnlContent;
	private TextField txtSoDienThoai;
	private TextField txtTenKhachHang;
	private KhachHang_DAO khachHang_DAO;
	private KhachHang khachHang;
	private DatPhong_DAO datPhong_DAO;
	private LoaiDichVu_DAO loaiDichVu_DAO;
	private DichVu_DAO dichVu_DAO;
	private ChiTietDichVu_DAO chiTietDichVu_DAO;
	private DefaultTableModel tableModel2, tableModel3;
	private JTable tbl2, tbl3;
	private List<DichVu> dsDVDaChon;
	private String maDatPhongChon;
	private JPanel pnlDV;
	private JComboBox<String> cmbDatPhong;
	private JComboBox<String> cmbTenDV;
	private JComboBox<String> cmbLoaiDV;
	private JTextField txtTongTien;

	private QuanLyDichVuPhongDat_GUI _this;

	public QuanLyDichVuPhongDat_GUI(QuanLyDatPhong_GUI quanLyDatPhongGUI, JFrame parentFrame) {
		_this = this;
		khachHang_DAO = new KhachHang_DAO();
		loaiDichVu_DAO = new LoaiDichVu_DAO();
		datPhong_DAO = new DatPhong_DAO();
		dichVu_DAO = new DichVu_DAO();
		chiTietDichVu_DAO = new ChiTietDichVu_DAO();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 466);
		pnlContent = new JPanel();
		pnlContent.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlContent);
		pnlContent.setLayout(null);
		setUndecorated(true);
		setLocationRelativeTo(null);

		JPanel pnlContainer = new JPanel();
		pnlContainer.setBackground(Utils.secondaryColor);
		pnlContainer.setBounds(0, 0, 950, 466);
		pnlContent.add(pnlContainer);
		pnlContainer.setLayout(null);

		JPanel pnlHeading = new JPanel();
		pnlHeading.setBackground(Utils.primaryColor);
		pnlHeading.setBounds(0, 0, 950, 50);
		pnlContainer.add(pnlHeading);
		pnlHeading.setLayout(null);

		JLabel lblTitle = new JLabel("Quản lý dịch vụ");
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

		Button btnSearchSoDienThoai = new Button();
		btnSearchSoDienThoai.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String soDienThoai = txtSoDienThoai.getText().trim();

				if (Utils.isSoDienThoai(soDienThoai)) {
					khachHang = khachHang_DAO.getKhachHang(soDienThoai);

					if (khachHang != null) {
						txtTenKhachHang.setText(khachHang.getHoTen());
						List<DonDatPhong> listDatPhongDangThue = datPhong_DAO.getAllDatPhongDangThue();
						emptyComboBox(cmbDatPhong, "Mã đặt phòng");
						for (DonDatPhong datPhong : listDatPhongDangThue) {
							if (datPhong.getKhachHang().getMaKhachHang().equals(khachHang.getMaKhachHang())) {
								cmbDatPhong.addItem(datPhong.getMaDonDatPhong());
							}
						}
					} else {
						JDialogCustom jDialogCustom = new JDialogCustom(_this);

						jDialogCustom.getBtnOK().addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								Main main = new Main();
								main.addPnlBody(new ThemKhachHang_GUI(main, _this, soDienThoai), "Thêm khách hàng", 2,
										0);
								main.setVisible(true);
								setVisible(false);
							}
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
					List<DonDatPhong> listDatPhongDangThue = datPhong_DAO.getAllDatPhongDangThue();
					emptyComboBox(cmbDatPhong, "Mã đặt phòng");
					for (DonDatPhong datPhong : listDatPhongDangThue) {
						cmbDatPhong.addItem(datPhong.getMaDonDatPhong());
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

		Button btnChonDichVu = new Button("");
		btnChonDichVu.setFocusable(false);
		btnChonDichVu.setRadius(8);
		btnChonDichVu.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnChonDichVu.setColor(Utils.primaryColor);
		btnChonDichVu.setColorOver(Utils.getOpacity(Utils.primaryColor, 0.8f));
		btnChonDichVu.setColorClick(Utils.getOpacity(Utils.primaryColor, 0.6f));
		btnChonDichVu.setBorderColor(Utils.secondaryColor);
		btnChonDichVu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row2 = tbl2.getSelectedRow();
				if (row2 != -1) {
					// kiểm tra mã đặt phòng
					maDatPhongChon = (String) cmbDatPhong.getSelectedItem();
					if (maDatPhongChon.equals("Mã đặt phòng") || maDatPhongChon.equals(null)) {
						JOptionPane.showMessageDialog(_this, "Vui lòng chọn mã đặt phòng", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					if (dsDVDaChon == null)
						dsDVDaChon = new ArrayList<>();

					DichVu DichVuChon = new DichVu((String) tableModel2.getValueAt(row2, 0));

					if (!dsDVDaChon.contains(DichVuChon)) {
//						DichVuChon = dichVu_DAO.getDichVuTheoMa(DichVuChon.getMaDichVu());
//						DichVuChon.setSoLuong(1);
//						dsDVDaChon.add(DichVuChon);
//						dichVu_DAO.capNhatSoLuongDichVuGiam(DichVuChon.getMaDichVu(), DichVuChon.getSoLuong());
//						chiTietDichVu_DAO.themChiTietDichVu(new ChiTietDichVu(DichVuChon,
//								datPhong_DAO.getDatPhong(maDatPhongChon), DichVuChon.getSoLuong()));
//						new ChiTietDichVu(DichVuChon, null, DichVuChon.getSoLuong());
//						new Notification(_this, components.notification.Notification.Type.SUCCESS,
//								"Thêm dịch vụ thành công").showNotification();
//						emptyTable(tbl3, tableModel3);
//						List<DichVu> listDV = dichVu_DAO.getAllDichVuCoSoLuongLonHon0();
//						addRow2(listDV);
//						loadTable3();
//						capNhatThanhTien();
					} else {
						return;
					}

				}
			}
		});
		btnChonDichVu.setIcon(new ImageIcon("Icon\\rightArrow_32x32.png"));
		btnChonDichVu.setBounds(0, 70, 36, 36);
		pnlActions.add(btnChonDichVu);

		Button btnXoaDichVu = new Button("");
		btnXoaDichVu.setFocusable(false);
		btnXoaDichVu.setRadius(8);
		btnXoaDichVu.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnXoaDichVu.setColor(Utils.primaryColor);
		btnXoaDichVu.setColorOver(Utils.getOpacity(Utils.primaryColor, 0.8f));
		btnXoaDichVu.setColorClick(Utils.getOpacity(Utils.primaryColor, 0.6f));
		btnXoaDichVu.setBorderColor(Utils.secondaryColor);
		btnXoaDichVu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tbl3.getSelectedRow();
				if (row != -1) {

					JDialogCustom jDialogCustom = new JDialogCustom(_this);

					jDialogCustom.getBtnOK().addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							DichVu dichVuXoa = new DichVu((String) tableModel3.getValueAt(row, 0));
							chiTietDichVu_DAO.xoaChiTietDichVu(dichVuXoa.getMaDichVu(), maDatPhongChon);
							dichVu_DAO.capNhatSoLuongDichVuTang(dichVuXoa.getMaDichVu(),
									Integer.parseInt(((String) tbl3.getValueAt(row, 2))));
							dsDVDaChon.remove(dichVuXoa);
							new Notification(_this, components.notification.Notification.Type.SUCCESS,
									"Xóa dịch vụ thành công").showNotification();
							addRow3(dsDVDaChon);
							addRow2(dichVu_DAO.getAllDichVuCoSoLuongLonHon0());
							capNhatThanhTien();
							tableModel3.removeRow(row);
						}
					});

					jDialogCustom.getBtnCancel().addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							return;
						}
					});
					jDialogCustom.showMessage("Warning", "Bạn chắc chắn muốn xóa dịch vụ này");
				}
			}
		});
		btnXoaDichVu.setIcon(new ImageIcon("Icon\\bin.png"));
		btnXoaDichVu.setBounds(0, 130, 36, 36);
		pnlActions.add(btnXoaDichVu);

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
		pnlBtnGroup.setBounds(0, 362, 900, 36);
		pnlBody.add(pnlBtnGroup);
		pnlBtnGroup.setLayout(null);

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
		btnQuayLai.setBounds(0, 0, 150, 36);
		pnlBtnGroup.add(btnQuayLai);

		txtTongTien = new JTextField();
		txtTongTien.setEditable(false);
		txtTongTien.setText("Tổng tiền: " + Utils.formatTienTe(0));
		txtTongTien.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTongTien.setBackground(new Color(203, 239, 255));
		txtTongTien.setBounds(649, 0, 220, 36);
		pnlBtnGroup.add(txtTongTien);

		JPanel pnlFilter = new JPanel();
		pnlFilter.setBackground(Utils.secondaryColor);
		pnlFilter.setBounds(0, 70, 870, 36);
		pnlBody.add(pnlFilter);
		pnlFilter.setLayout(null);

		cmbDatPhong = new JComboBox<>();
		cmbDatPhong.addItem("Mã đặt phòng");
		List<DonDatPhong> listDatPhongDangThue = datPhong_DAO.getAllDatPhongDangThue();
		for (DonDatPhong datPhong : listDatPhongDangThue) {
			cmbDatPhong.addItem(datPhong.getMaDonDatPhong());
		}
		cmbDatPhong.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbDatPhong.setBackground(Utils.primaryColor);
		cmbDatPhong.setBounds(40, 0, 200, 36);
		pnlFilter.add(cmbDatPhong);

		cmbTenDV = new JComboBox<>();
		cmbTenDV = new JComboBox<>();
		cmbTenDV.addItem("Tên dịch vụ");
		ArrayList<DichVu> listDichVu = (ArrayList<DichVu>) dichVu_DAO.getAllDichVuCoSoLuongLonHon0();
		for (DichVu dichVu : listDichVu) {
			cmbTenDV.addItem(dichVu.getTenDichVu());
		}
		cmbTenDV.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbTenDV.setBackground(Utils.primaryColor);
		cmbTenDV.setBounds(280, 0, 200, 36);
		pnlFilter.add(cmbTenDV);

		cmbLoaiDV = new JComboBox<>();
		cmbLoaiDV.setBackground(Utils.primaryColor);
		cmbLoaiDV.addItem("Phân loại");
		ArrayList<LoaiDichVu> listLoaiDV = (ArrayList<LoaiDichVu>) loaiDichVu_DAO.getAllLoaiDichVu();
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
				cmbDatPhong.removeItemListener(_this);
				cmbLoaiDV.setSelectedIndex(0);
				cmbTenDV.setSelectedIndex(0);
				cmbDatPhong.setSelectedIndex(0);
				cmbLoaiDV.addItemListener(_this);
				cmbTenDV.addItemListener(_this);
				cmbDatPhong.addItemListener(_this);

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

				List<DichVu> listDV = dichVu_DAO.getAllDichVuCoSoLuongLonHon0();

				cmbTenDV.removeItemListener(_this);

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
				cmbDatPhong.addItemListener(_this);
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
				if (e.getType() == TableModelEvent.UPDATE) {
					int row3 = tbl3.getSelectedRow();

					if (row3 != -1) {
						// Dịch vụ thay đổi trong table3
						DichVu DichVuThayDoiSoLuong = new DichVu((String) tableModel3.getValueAt(row3, 0));
						// chi tiết dịch vụ trong chi tiết dịch vụ cua table3
						ChiTietDichVu chiTietDichVuThayDoi = chiTietDichVu_DAO
								.getChiTietDichVuTheoMa((String) tableModel3.getValueAt(row3, 0), maDatPhongChon);

						if (chiTietDichVuThayDoi.getSoLuong() > Integer.parseInt((String) tbl3.getValueAt(row3, 2))) {
							chiTietDichVu_DAO.capNhatSoLuongDichVu(DichVuThayDoiSoLuong.getMaDichVu(), maDatPhongChon,
									Integer.parseInt((String) tbl3.getValueAt(row3, 2)));
							dichVu_DAO.capNhatSoLuongDichVuTang(DichVuThayDoiSoLuong.getMaDichVu(),
									chiTietDichVuThayDoi.getSoLuong()
											- Integer.parseInt((String) tbl3.getValueAt(row3, 2)));
							dsDVDaChon.forEach(dichVu1 -> {
								if (dichVu1.getMaDichVu().equals(DichVuThayDoiSoLuong.getMaDichVu())) {
									dichVu1.setSoLuong(Integer.parseInt((String) tbl3.getValueAt(row3, 2)));
									return;
								}
							});
						} else if (chiTietDichVuThayDoi.getSoLuong() < Integer
								.parseInt((String) tbl3.getValueAt(row3, 2))) {
							dichVu_DAO.getAllDichVuCoSoLuongLonHon0().forEach(dichVu -> {
								if (dichVu.getMaDichVu().equals(DichVuThayDoiSoLuong.getMaDichVu())) {
									if (dichVu.getSoLuong() < (Integer.parseInt((String) tbl3.getValueAt(row3, 2))
											- chiTietDichVuThayDoi.getSoLuong())) {
										JOptionPane.showMessageDialog(_this, "Số lượng tồn không đủ", "Error",
												JOptionPane.ERROR_MESSAGE);
									} else {
										chiTietDichVu_DAO.capNhatSoLuongDichVu(DichVuThayDoiSoLuong.getMaDichVu(),
												maDatPhongChon, Integer.parseInt((String) tbl3.getValueAt(row3, 2)));
										dichVu_DAO.capNhatSoLuongDichVuGiam(DichVuThayDoiSoLuong.getMaDichVu(),
												Integer.parseInt((String) tbl3.getValueAt(row3, 2))
														- chiTietDichVuThayDoi.getSoLuong());
										dsDVDaChon.forEach(dichVu1 -> {
											if (dichVu1.getMaDichVu().equals(DichVuThayDoiSoLuong.getMaDichVu())) {
												dichVu1.setSoLuong(Integer.parseInt((String) tbl3.getValueAt(row3, 2)));
												return;
											}
										});
									}
								}
							});
						}
					}
					addRow3(dsDVDaChon);
					capNhatThanhTien();
					addRow2(dichVu_DAO.getAllDichVuCoSoLuongLonHon0());
				}
			}
		});

		if (pnlDV.getComponentAt(620, 0) != null) {
			pnlDV.remove(pnlDV.getComponentAt(620, 0));
		}

		pnlDV.add(scrDanhSachDichVuDuocChon);

		cmbTenDV.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					filterDichVu();
				}
			}
		});

		cmbLoaiDV.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					filterDichVu();
				}
			}
		});

		cmbDatPhong.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// Biến tạm
				DichVu dichVuTrongChiTiet = new DichVu();
				// Làm sạch table3 và danh sách dịch vụ chọn
				emptyTable(tbl3, tableModel3);
				if (dsDVDaChon != null) {
					dsDVDaChon.removeAll(dsDVDaChon);
				}
				// lấy mã đặt phòng được chọn
				maDatPhongChon = (String) cmbDatPhong.getSelectedItem();
				if (dsDVDaChon == null)
					dsDVDaChon = new ArrayList<>();
				List<ChiTietDichVu> ListChiTietDV = new ArrayList<>();
				ListChiTietDV = chiTietDichVu_DAO.getAllChiTietDichVu();
				// lấy danh sách chi tiết của phòng được chọn
				for (ChiTietDichVu chiTietDichVu : ListChiTietDV) {
					if (maDatPhongChon.equals(chiTietDichVu.getChiTietDatPhong().getDonDatPhong().getMaDonDatPhong())) {
						if (dsDVDaChon.contains(chiTietDichVu.getDichVu()))
							return;

						dichVuTrongChiTiet = chiTietDichVu.getDichVu();
						dichVuTrongChiTiet.setSoLuong(chiTietDichVu.getSoLuong());

						dsDVDaChon.add(dichVuTrongChiTiet);
					}
				}
				addRow3(dsDVDaChon);
				capNhatThanhTien();
			}
		});
	}

	/**
	 * Thêm một DV vào table
	 *
	 * @param dichVu dichVu muốn thêm
	 */
	private void addRow2(DichVu dichVu) {
		tableModel2.addRow(new String[] { dichVu.getMaDichVu(), dichVu.getTenDichVu(), dichVu.getSoLuong() + "",
				Utils.formatTienTe(dichVu.getGiaBan()) });
	}

	/**
	 * Thêm danh sách các DV vào table
	 *
	 * @param list danh sách các DV cần thêm
	 */
	private void addRow2(List<DichVu> list) {
		emptyTable(tbl2, tableModel2);

		list.forEach(dichVu -> addRow2(dichVu));
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
		emptyTable(tbl3, tableModel3);
		list.forEach(dichVu -> addRow3(dichVu));
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
	private void emptyTable(JTable tbl, DefaultTableModel model) {
		while (tbl.getRowCount() > 0)
			model.removeRow(0);
	}

	private void loadTable3() {
		maDatPhongChon = (String) cmbDatPhong.getSelectedItem();

		if (maDatPhongChon.equals("Mã đặt phòng") || maDatPhongChon.equals(null))
			return;
		addRow3(dsDVDaChon);
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

		List<DichVu> list = dichVu_DAO.getDichVuTheoMaVaLoai(maDV, loaiDV);
		emptyTable(tbl2, tableModel2);
		addRow2(list);
	}

	private void capNhatThanhTien() {
		if (dsDVDaChon == null)
			txtTongTien.setText("Tổng tiền: " + Utils.formatTienTe(0));
		double tongTien = 0;
		for (DichVu dichVu : dsDVDaChon)
			tongTien += dichVu.getGiaBan() * dichVu.getSoLuong();
		txtTongTien.setText("Tổng tiền: " + Utils.formatTienTe(tongTien));
	}

}
