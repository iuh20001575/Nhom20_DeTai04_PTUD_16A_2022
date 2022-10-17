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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
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
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import components.button.Button;
import components.jDialog.JDialogCustom;
import components.notification.Notification;
import components.panelRound.PanelRound;
import components.scrollbarCustom.ScrollBarCustom;
import components.textField.TextField;
import connectDB.ConnectDB;
import dao.DatPhong_DAO;
import dao.KhachHang_DAO;
import dao.LoaiPhong_DAO;
import entity.KhachHang;
import entity.LoaiPhong;
import entity.NhanVien;
import entity.Phong;
import utils.Utils;

public class DatPhong_GUI extends JFrame implements ItemListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private TextField txtSoDienThoai;
	private TextField txtTenKhachHang;
	private KhachHang_DAO khachHang_DAO;
	private KhachHang khachHang;
	private DatPhong_DAO datPhong_DAO;
	private DefaultTableModel tableModel;
	private JTable tbl;
	private LoaiPhong_DAO loaiPhong_DAO;
	private List<Phong> dsPhongDaChon;
	private JPanel pnlPhong;
	private JComboBox<String> cmbMaPhong;
	private JComboBox<String> cmbLoaiPhong;
	private JComboBox<String> cmbSoLuong;
	private DatPhong_GUI _this;

	/**
	 * Create the frame.
	 * 
	 * @param quanLyDatPhongGUI
	 */
	public DatPhong_GUI(QuanLyDatPhong_GUI quanLyDatPhongGUI) {
		try {
			new ConnectDB().connect();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		_this = this;
		khachHang_DAO = new KhachHang_DAO();
		datPhong_DAO = new DatPhong_DAO();
		loaiPhong_DAO = new LoaiPhong_DAO();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 466);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);
		setLocationRelativeTo(null);

		JPanel pnlContainer = new JPanel();
		pnlContainer.setBackground(Utils.secondaryColor);
		pnlContainer.setBounds(0, 0, 850, 466);
		contentPane.add(pnlContainer);
		pnlContainer.setLayout(null);

		JPanel pnlHeading = new JPanel();
		pnlHeading.setBackground(Utils.primaryColor);
		pnlHeading.setBounds(0, 0, 850, 50);
		pnlContainer.add(pnlHeading);
		pnlHeading.setLayout(null);

		JLabel lblTitle = new JLabel("Đặt phòng");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setBounds(325, 9, 200, 32);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
		pnlHeading.add(lblTitle);

		JPanel pnlBody = new JPanel();
		pnlBody.setBackground(Utils.secondaryColor);
		pnlBody.setBounds(40, 50, 770, 398);
		pnlContainer.add(pnlBody);
		pnlBody.setLayout(null);

		txtSoDienThoai = new TextField();
		txtSoDienThoai.setBackground(Utils.secondaryColor);
		txtSoDienThoai.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtSoDienThoai.setLabelText("Số điện thoại khách");
		txtSoDienThoai.setBounds(0, 0, 340, 55);
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
					} else {
						JDialogCustom jDialogCustom = new JDialogCustom(_this);

						jDialogCustom.getBtnOK().addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								new ThemKhachHang_GUI(_this, soDienThoai).setVisible(true);
								setVisible(false);
							};
						});

						jDialogCustom.getBtnCancel().addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								quanLyDatPhongGUI.closeJFrameSub();
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
		btnSearchSoDienThoai.setBounds(360, 2, 50, 50);
		pnlBody.add(btnSearchSoDienThoai);

		txtTenKhachHang = new TextField();
		txtTenKhachHang.setLabelText("Họ tên khách");
		txtTenKhachHang.setEditable(false);
		txtTenKhachHang.setBackground(Utils.secondaryColor);
		txtTenKhachHang.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTenKhachHang.setBounds(430, 0, 340, 55);
		pnlBody.add(txtTenKhachHang);
		txtTenKhachHang.setColumns(10);

		pnlPhong = new JPanel();
		pnlPhong.setBackground(Utils.secondaryColor);
		pnlPhong.setBounds(0, 121, 770, 225);
		pnlBody.add(pnlPhong);
		pnlPhong.setLayout(null);

		JPanel pnlActions = new JPanel();
		pnlActions.setBackground(Utils.secondaryColor);
		pnlActions.setBounds(574, 0, 36, 225);
		pnlPhong.add(pnlActions);
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
				int row = tbl.getSelectedRow();
				if (row != -1) {
					if (dsPhongDaChon == null)
						dsPhongDaChon = new ArrayList<>();
					Phong phong = new Phong((String) tableModel.getValueAt(row, 0));
					if (dsPhongDaChon.contains(phong))
						return;
					dsPhongDaChon.add(phong);

					showDanhSachPhongDaChon();
				}
			}
		});
		btnNewButton_2.setIcon(new ImageIcon("Icon\\rightArrow_32x32.png"));
		btnNewButton_2.setBounds(0, 94, 36, 36);
		pnlActions.add(btnNewButton_2);

		JScrollPane scrDanhSachPhong = new JScrollPane();
		scrDanhSachPhong.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrDanhSachPhong.setBackground(Utils.secondaryColor);
		scrDanhSachPhong.setBounds(0, 0, 564, 225);
		pnlPhong.add(scrDanhSachPhong);
		ScrollBarCustom scbDanhSachPhong = new ScrollBarCustom();
//		Set color scrollbar thumb
		scbDanhSachPhong.setScrollbarColor(new Color(203, 203, 203));
		scrDanhSachPhong.setVerticalScrollBar(scbDanhSachPhong);

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
		};

		tableModel = new DefaultTableModel(new String[] { "Mã phòng", "Loại phòng", "Số lượng", "Trạng Thái" }, 0);

		tbl.setModel(tableModel);
		tbl.setFocusable(false);
//		Cam
		tbl.getTableHeader().setBackground(new Color(255, 195, 174));
//		Xanh
		tbl.getTableHeader().setBackground(Utils.primaryColor);
		tbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl.setBackground(Color.WHITE);
		tbl.getTableHeader()
				.setPreferredSize(new Dimension((int) tbl.getTableHeader().getPreferredSize().getWidth(), 36));
		tbl.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl.setRowHeight(36);
		scrDanhSachPhong.setViewportView(tbl);
//		Căn phải column 3 table
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.RIGHT);
		tbl.getColumnModel().getColumn(2).setCellRenderer(dtcr);

		JPanel pnlBtnGroup = new JPanel();
		pnlBtnGroup.setBackground(Utils.secondaryColor);
		pnlBtnGroup.setBounds(0, 362, 770, 36);
		pnlBody.add(pnlBtnGroup);
		pnlBtnGroup.setLayout(null);

		Button btnDatPhong = new Button("Đặt phòng");
		btnDatPhong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				Kiểm tra khách hàng đã được nhập hay chưa?
				if (khachHang == null) {
					new Notification(_this, components.notification.Notification.Type.ERROR, "Vui lòng nhập khách hàng")
							.showNotification();
					return;
				}

//				Kiểm tra phòng đã được chọn hay chưa?
				if (dsPhongDaChon.size() <= 0) {
					new Notification(_this, components.notification.Notification.Type.ERROR, "Chọn phòng muốn đặt")
							.showNotification();
					return;
				}

				boolean res = datPhong_DAO.themPhieuDatPhongNgay(khachHang, new NhanVien("NV112"), dsPhongDaChon);
				if (res) {
					quanLyDatPhongGUI.capNhatTrangThaiPhong();
					quanLyDatPhongGUI.closeJFrameSub();
				}
			}
		});
		btnDatPhong.setFocusable(false);
		btnDatPhong.setRadius(8);
		btnDatPhong.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnDatPhong.setBorderColor(Utils.secondaryColor);
		btnDatPhong.setForeground(Color.WHITE);
		btnDatPhong.setColor(Utils.primaryColor);
		btnDatPhong.setColorOver(Utils.getOpacity(Utils.primaryColor, 0.8f));
		btnDatPhong.setColorClick(Utils.getOpacity(Utils.primaryColor, 0.6f));
		btnDatPhong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnDatPhong.setBounds(395, 0, 150, 36);
		pnlBtnGroup.add(btnDatPhong);

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
		pnlFilter.setBounds(0, 70, 770, 36);
		pnlBody.add(pnlFilter);
		pnlFilter.setLayout(null);

		cmbMaPhong = new JComboBox<String>();
		cmbMaPhong.setBackground(Utils.primaryColor);
		cmbMaPhong.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbMaPhong.setModel(new DefaultComboBoxModel<String>(new String[] { "Mã phòng" }));
		cmbMaPhong.setBounds(0, 0, 200, 36);
		pnlFilter.add(cmbMaPhong);

		cmbLoaiPhong = new JComboBox<String>();
		cmbLoaiPhong.setBackground(Utils.primaryColor);
		cmbLoaiPhong
				.setModel(new DefaultComboBoxModel<String>(new String[] { "Loại phòng", "Phòng thường", "Phòng VIP" }));
		cmbLoaiPhong.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbLoaiPhong.setBounds(220, 0, 200, 36);
		pnlFilter.add(cmbLoaiPhong);

		cmbSoLuong = new JComboBox<String>();
		cmbSoLuong.setBackground(Utils.primaryColor);
		cmbSoLuong.setModel(new DefaultComboBoxModel<String>(new String[] { "Số lượng", "5", "10", "20" }));
		cmbSoLuong.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbSoLuong.setBounds(440, 0, 200, 36);
		pnlFilter.add(cmbSoLuong);

		Button btnLamMoi = new Button("Làm mới");
		btnLamMoi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cmbLoaiPhong.removeItemListener(_this);
				cmbMaPhong.removeItemListener(_this);
				cmbSoLuong.removeItemListener(_this);

				emptyTable();
				addRow(datPhong_DAO.getPhongDatNgay());
				cmbLoaiPhong.setSelectedIndex(0);
				cmbMaPhong.setSelectedIndex(0);
				cmbSoLuong.setSelectedIndex(0);

				cmbLoaiPhong.addItemListener(_this);
				cmbMaPhong.addItemListener(_this);
				cmbSoLuong.addItemListener(_this);
			}
		});
		btnLamMoi.setFocusable(false);
		btnLamMoi.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnLamMoi.setBounds(660, 0, 110, 36);
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

		showDanhSachPhongDaChon();

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				List<Phong> list = datPhong_DAO.getPhongDatNgay();
				List<LoaiPhong> loaiPhongs = loaiPhong_DAO.getAllLoaiPhong();

				cmbMaPhong.removeItemListener(_this);

				emptyTable();
				emptyComboBox(cmbMaPhong, "Mã phòng");
				emptyComboBox(cmbLoaiPhong, "Loại phòng");

				addRow(list);
				list.forEach(phong -> {
					cmbMaPhong.addItem(phong.getMaPhong());
				});
				loaiPhongs.forEach(loaiPhong -> cmbLoaiPhong.addItem(loaiPhong.getTenLoai()));

//				
				String soDienThoai = txtSoDienThoai.getText().trim();

				if (Utils.isSoDienThoai(soDienThoai)) {
					khachHang = khachHang_DAO.getKhachHang(soDienThoai);

					if (khachHang != null) {
						txtTenKhachHang.setText(khachHang.getHoTen());
						txtTenKhachHang.requestFocus();
					}
				}

				cmbMaPhong.addItemListener(_this);
				cmbLoaiPhong.addItemListener(_this);
				cmbSoLuong.addItemListener(_this);
			}
		});
	}

	/**
	 * Hiển thị các phòng đã chọn vào mục phòng đã chọn
	 */
	private void showDanhSachPhongDaChon() {
		JScrollPane scrPhongDaChon = new JScrollPane();
		scrPhongDaChon.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrPhongDaChon.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrPhongDaChon.setBackground(Utils.secondaryColor);
		scrPhongDaChon.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true),
				"Ph\u00F2ng \u0111\u00E3 ch\u1ECDn", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		scrPhongDaChon.setBounds(620, 0, 150, 225);

		if (pnlPhong.getComponentAt(620, 0) != null) {
			pnlPhong.remove(pnlPhong.getComponentAt(620, 0));
		}

		pnlPhong.add(scrPhongDaChon);
		ScrollBarCustom scbPhongDaChon = new ScrollBarCustom();
		scbPhongDaChon.setBackgroundColor(Utils.secondaryColor);
		scbPhongDaChon.setScrollbarColor(Utils.primaryColor);
		scrPhongDaChon.setVerticalScrollBar(scbPhongDaChon);

		JPanel pnlPhongDaChon = new JPanel();
		pnlPhongDaChon.setBackground(Utils.secondaryColor);
		scrPhongDaChon.setViewportView(pnlPhongDaChon);
		pnlPhongDaChon.setLayout(null);

		if (dsPhongDaChon == null)
			return;

//		
		int heightItem = 36;
		int gapY = 8;
		int top = 11;
		int countItem = dsPhongDaChon.size();
		for (int i = 0; i < countItem; i++) {
			pnlPhongDaChon.add(getPanelPhongDaChonItem(top + i * (gapY + heightItem), dsPhongDaChon.get(i)));
		}

		pnlPhongDaChon.setPreferredSize(
				new Dimension(140, Math.max(202, top + heightItem * countItem + gapY * (countItem - 1))));
	}

	/**
	 * Get panel phòng đã chọn
	 * 
	 * @param top   khoảng cách top từ container đến item
	 * @param phong phòng được chọn
	 * @return panel
	 */
	private PanelRound getPanelPhongDaChonItem(int top, Phong phong) {
		PanelRound pnlContainerItem = new PanelRound(8);
		pnlContainerItem.setBackground(Utils.primaryColor);
		pnlContainerItem.setBounds(11, top, 118, 36);
		pnlContainerItem.setLayout(null);

		JLabel lblMaPhong = new JLabel(phong.getMaPhong());
		lblMaPhong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblMaPhong.setBounds(4, 0, 94, 36);
		pnlContainerItem.add(lblMaPhong);

		JLabel lblIconClose = new JLabel("");
		lblIconClose.setIcon(new ImageIcon("Icon\\close_16x16.png"));
		lblIconClose.setBounds(94, 10, 16, 16);
		pnlContainerItem.add(lblIconClose);
		lblIconClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dsPhongDaChon.remove(phong);
				showDanhSachPhongDaChon();
			}
		});

		return pnlContainerItem;
	}

	/**
	 * Thêm một phòng vào table
	 * 
	 * @param phong phòng muốn thêm
	 */
	private void addRow(Phong phong) {
		phong.setLoaiPhong(loaiPhong_DAO.getLoaiPhong(phong.getLoaiPhong().getMaLoai()));
		tableModel.addRow(new String[] { phong.getMaPhong(), phong.getLoaiPhong().getTenLoai(),
				phong.getSoLuongKhach() + "", Phong.convertTrangThaiToString(phong.getTrangThai()) });
	}

	/**
	 * Thêm danh sách các phòng vào table
	 * 
	 * @param list danh sách các phòng cần thêm
	 */
	private void addRow(List<Phong> list) {
		emptyTable();

		list.forEach(phong -> {
			addRow(phong);
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
	private void emptyTable() {
		while (tbl.getRowCount() > 0)
			tableModel.removeRow(0);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.DESELECTED)
			return;

		filterPhong();
	}

	/**
	 * Lọc danh sách các phòng theo mã phòng, loại phòng và số lượng
	 */
	private void filterPhong() {
		String maPhong = (String) cmbMaPhong.getSelectedItem();
		String loaiPhong = (String) cmbLoaiPhong.getSelectedItem();
		String soLuong = (String) cmbSoLuong.getSelectedItem();

		if (maPhong.equals("Mã phòng"))
			maPhong = "";
		if (loaiPhong.equals("Loại phòng"))
			loaiPhong = "";

		List<Phong> list = datPhong_DAO.getPhongDatNgay(maPhong, loaiPhong, soLuong);
		emptyTable();
		addRow(list);
	}

}
