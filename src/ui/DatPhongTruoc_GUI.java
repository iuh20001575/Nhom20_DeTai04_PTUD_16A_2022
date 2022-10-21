package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.raven.datechooser.DateChooser;

import components.button.Button;
import components.comboBox.ComboBox;
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
import entity.NhanVien;
import entity.Phong;
import utils.Utils;

public class DatPhongTruoc_GUI extends JFrame implements ItemListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String labelCmbMaPhong = "Mã phòng";
	private JPanel contentPane;
	private TextField txtSoDienThoai;
	private TextField txtTenKhachHang;
	private JPanel pnlPhong;
	private JTable tbl;
	private DefaultTableModel tableModel;
	private JComboBox<String> cmbMaPhong;
	private JComboBox<String> cmbLoaiPhong;
	private JComboBox<String> cmbSoLuong;
	private DateChooser dateChoose;
	private DatPhong_DAO datPhong_DAO;
	private List<Phong> dsPhongDaChon;
	private List<Phong> dsPhongDatTruoc;
	private LoaiPhong_DAO loaiPhong_DAO;
	private KhachHang_DAO khachHang_DAO;
	private KhachHang khachHang;
	private DatPhongTruoc_GUI _this;
	private JComboBox<String> cmbGio;
	private JComboBox<String> cmbPhut;
	private TextField txtNgayNhanPhong;
	private Button btnChonPhong;
	private Button btnDatPhong;

	/**
	 * Create the frame.
	 * 
	 * @param quanLyDatPhongGUI
	 * @param glass
	 */
	public DatPhongTruoc_GUI(QuanLyDatPhong_GUI quanLyDatPhongGUI) {
		_this = this;

		try {
			new ConnectDB().connect();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		datPhong_DAO = new DatPhong_DAO();
		loaiPhong_DAO = new LoaiPhong_DAO();
		khachHang_DAO = new KhachHang_DAO();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 850, 536);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);
		setLocationRelativeTo(null);

		JPanel pnlContainer = new JPanel();
		pnlContainer.setBackground(Utils.secondaryColor);
		pnlContainer.setBounds(0, 0, 850, 536);
		contentPane.add(pnlContainer);
		pnlContainer.setLayout(null);

		JPanel pnlHeading = new JPanel();
		pnlHeading.setBackground(Utils.primaryColor);
		pnlHeading.setBounds(0, 0, 850, 50);
		pnlContainer.add(pnlHeading);
		pnlHeading.setLayout(null);

		JLabel lblTitle = new JLabel("Đặt phòng trước");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setBounds(325, 9, 200, 32);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
		pnlHeading.add(lblTitle);

		JPanel pnlBody = new JPanel();
		pnlBody.setBackground(Utils.secondaryColor);
		pnlBody.setBounds(0, 50, 850, 486);
		pnlContainer.add(pnlBody);
		pnlBody.setLayout(null);

		txtSoDienThoai = new TextField();
		txtSoDienThoai.setBackground(Utils.secondaryColor);
		txtSoDienThoai.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtSoDienThoai.setLabelText("Số điện thoại khách");
		txtSoDienThoai.setBounds(40, 0, 340, 55);
		pnlBody.add(txtSoDienThoai);
		txtSoDienThoai.setColumns(10);

		Button btnSearchSoDienThoai = new Button();
		btnSearchSoDienThoai.setFocusable(false);
		btnSearchSoDienThoai.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnSearchSoDienThoai.setRadius(4);
		btnSearchSoDienThoai.setBorderColor(Utils.secondaryColor);
		btnSearchSoDienThoai.setColor(Utils.primaryColor);
		btnSearchSoDienThoai.setColorOver(Utils.getOpacity(Utils.primaryColor, 0.9f));
		btnSearchSoDienThoai.setColorClick(Utils.getOpacity(Utils.primaryColor, 0.8f));
		btnSearchSoDienThoai.setIcon(new ImageIcon("Icon\\user_searching.png"));
		btnSearchSoDienThoai.setBounds(400, 2, 50, 50);
		pnlBody.add(btnSearchSoDienThoai);

		txtTenKhachHang = new TextField();
		txtTenKhachHang.setLabelText("Họ tên khách");
		txtTenKhachHang.setEditable(false);
		txtTenKhachHang.setBackground(Utils.secondaryColor);
		txtTenKhachHang.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTenKhachHang.setBounds(470, 0, 340, 55);
		pnlBody.add(txtTenKhachHang);
		txtTenKhachHang.setColumns(10);

		pnlPhong = new JPanel();
		pnlPhong.setBackground(Utils.secondaryColor);
		pnlPhong.setBounds(40, 187, 770, 225);
		pnlBody.add(pnlPhong);
		pnlPhong.setLayout(null);

		JPanel pnlActions = new JPanel();
		pnlActions.setBackground(Utils.secondaryColor);
		pnlActions.setBounds(574, 0, 36, 225);
		pnlPhong.add(pnlActions);
		pnlActions.setLayout(null);

		btnChonPhong = new Button("");
		btnChonPhong.setFocusable(false);
		btnChonPhong.setRadius(8);
		btnChonPhong.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnChonPhong.setColor(Utils.primaryColor);
		btnChonPhong.setColorOver(Utils.getOpacity(Utils.primaryColor, 0.8f));
		btnChonPhong.setColorClick(Utils.getOpacity(Utils.primaryColor, 0.6f));
		btnChonPhong.setBorderColor(Utils.secondaryColor);
		btnChonPhong.setIcon(new ImageIcon("Icon\\rightArrow_32x32.png"));
		btnChonPhong.setBounds(0, 94, 36, 36);
		btnChonPhong.setEnabled(false);
		pnlActions.add(btnChonPhong);

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
//		Set background cho phần header của table
		tbl.getTableHeader().setBackground(Utils.primaryColor);
		tbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl.setBackground(Color.WHITE);
//		Set chiều rộng và chiều cao cho phần header của table
		tbl.getTableHeader()
				.setPreferredSize(new Dimension((int) tbl.getTableHeader().getPreferredSize().getWidth(), 36));
		tbl.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl.setRowHeight(36);
		scrDanhSachPhong.setViewportView(tbl);
//		Căn phải cell 3 table
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.RIGHT);
		tbl.getColumnModel().getColumn(2).setCellRenderer(dtcr);

		JPanel pnlBtnGroup = new JPanel();
		pnlBtnGroup.setBackground(Utils.secondaryColor);
		pnlBtnGroup.setBounds(40, 432, 770, 36);
		pnlBody.add(pnlBtnGroup);
		pnlBtnGroup.setLayout(null);

		btnDatPhong = new Button("Đặt phòng trước");
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
		btnDatPhong.setEnabled(false);
		pnlBtnGroup.add(btnDatPhong);

		Button btnQuayLai = new Button("Quay lại");
		btnQuayLai.setFocusable(false);
		btnQuayLai.setRadius(8);
		btnQuayLai.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnQuayLai.setBorderColor(Utils.secondaryColor);
		btnQuayLai.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnQuayLai.setBounds(225, 0, 150, 36);
		pnlBtnGroup.add(btnQuayLai);

		JPanel pnlFilter = new JPanel();
		pnlFilter.setBackground(Utils.secondaryColor);
		pnlFilter.setBounds(40, 140, 770, 36);
		pnlBody.add(pnlFilter);
		pnlFilter.setLayout(null);

		cmbMaPhong = new JComboBox<String>();
		cmbMaPhong.setBackground(Utils.primaryColor);
		cmbMaPhong.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbMaPhong.setModel(new DefaultComboBoxModel<String>(new String[] { labelCmbMaPhong }));
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
		btnLamMoi.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnLamMoi.setRadius(4);
		btnLamMoi.setBorderColor(Utils.secondaryColor);
		btnLamMoi.setColor(Color.WHITE);
		btnLamMoi.setColorOver(Utils.getOpacity(Color.BLACK, 0.05f));
		btnLamMoi.setColorClick(Utils.getOpacity(Color.BLACK, 0.1f));
		btnLamMoi.setFocusable(false);
		btnLamMoi.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnLamMoi.setBounds(660, 0, 110, 36);
		pnlFilter.add(btnLamMoi);

		txtNgayNhanPhong = new TextField();
		txtNgayNhanPhong.setIcon(new ImageIcon("Icon\\add-event 2.png"));
		txtNgayNhanPhong.setLabelText("Ngày nhận phòng");
		txtNgayNhanPhong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtNgayNhanPhong.setColumns(10);
		txtNgayNhanPhong.setBackground(new Color(203, 239, 255));
		txtNgayNhanPhong.setBounds(40, 70, 340, 55);
		pnlBody.add(txtNgayNhanPhong);
		dateChoose = new DateChooser();
		dateChoose.setTextRefernce(txtNgayNhanPhong);
		dateChoose.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				// TODO Auto-generated method stub
				if (evt.getPropertyName().equals("graphicsConfiguration")) {
					setTimeComboBox(-1);
				}
			}
		});

		Button btnSearchPhongDatTruoc = new Button();
		btnSearchPhongDatTruoc.setIcon(new ImageIcon("Icon\\statistics.png"));
		btnSearchPhongDatTruoc.setRadius(4);
		btnSearchPhongDatTruoc.setFocusable(false);
		btnSearchPhongDatTruoc.setColorOver(new Color(140, 177, 180, 230));
		btnSearchPhongDatTruoc.setColorClick(new Color(140, 177, 180, 204));
		btnSearchPhongDatTruoc.setColor(new Color(140, 177, 180));
		btnSearchPhongDatTruoc.setBorderColor(new Color(203, 239, 255));
		btnSearchPhongDatTruoc.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnSearchPhongDatTruoc.setBounds(675, 72, 50, 50);
		pnlBody.add(btnSearchPhongDatTruoc);

		cmbGio = new ComboBox<String>();
		cmbGio.setModel(new DefaultComboBoxModel<String>());
		cmbGio.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbGio.setBackground(new Color(140, 177, 180));
		cmbGio.setBounds(400, 89, 70, 36);
		pnlBody.add(cmbGio);

		cmbPhut = new ComboBox<String>();
		cmbPhut.setModel(new DefaultComboBoxModel<String>());
		cmbPhut.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbPhut.setBackground(new Color(140, 177, 180));
		cmbPhut.setBounds(537, 89, 70, 36);
		pnlBody.add(cmbPhut);

		JLabel lblTime = new JLabel("Giờ nhận phòng");
		lblTime.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTime.setBounds(400, 66, 200, 19);
		pnlBody.add(lblTime);

		JLabel lblGio = new JLabel("giờ");
		lblGio.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblGio.setBounds(480, 95, 34, 30);
		pnlBody.add(lblGio);

		JLabel lblPhut = new JLabel("phút");
		lblPhut.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblPhut.setBounds(612, 95, 45, 30);
		pnlBody.add(lblPhut);

		showDanhSachPhongDaChon();
		cmbMaPhong.addItemListener(_this);
		cmbLoaiPhong.addItemListener(_this);
		cmbSoLuong.addItemListener(_this);

//		Sự kiện window
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				setTimeComboBox(LocalTime.now().getHour());
			}

			public void windowActivated(WindowEvent e) {
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
			};
		});

//		Sự kiện txtSoDienThoai
		txtSoDienThoai.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtSoDienThoai.setError(false);
				txtTenKhachHang.setText("");
				khachHang = null;
				btnDatPhong.setEnabled(false);
			}
		});

//		Sự kiện nút tìm kiếm khách hàng theo số điện thoại
		btnSearchSoDienThoai.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String soDienThoai = txtSoDienThoai.getText().trim();

				if (Utils.isSoDienThoai(soDienThoai)) {
					khachHang = khachHang_DAO.getKhachHang(soDienThoai);

					if (khachHang != null) {
						txtTenKhachHang.setText(khachHang.getHoTen());

						if (dsPhongDaChon != null && dsPhongDaChon.size() > 0)
							btnDatPhong.setEnabled(true);
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

//		Sự kiện tìm kiếm phòng đặt trước
		btnSearchPhongDatTruoc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LocalDate ngayNhanPhong = Utils.getLocalDate(txtNgayNhanPhong.getText());
				LocalDate dateNow = LocalDate.now();
				cmbMaPhong.removeItemListener(_this);
				cmbMaPhong.removeAllItems();
				cmbMaPhong.addItem(labelCmbMaPhong);

				if (ngayNhanPhong.isBefore(dateNow)) {
					JDialogCustom jDialogCustom = new JDialogCustom(_this,
							components.jDialog.JDialogCustom.Type.warning);
					jDialogCustom.showMessage("Lỗi", "Ngày nhận phòng không được trước ngày hiện tại.");
					dateChoose.showPopup();
					return;
				}

				int gio = Integer.parseInt((String) cmbGio.getSelectedItem()),
						phut = Integer.parseInt((String) cmbPhut.getSelectedItem());
				LocalTime gioNhanPhong = LocalTime.of(gio, phut);

				dsPhongDatTruoc = datPhong_DAO.getPhongDatTruoc(ngayNhanPhong, gioNhanPhong);
				dsPhongDatTruoc.forEach(phong -> cmbMaPhong.addItem(phong.getMaPhong()));
				addRow(dsPhongDatTruoc);
				cmbMaPhong.addItemListener(_this);
			}
		});

//		Sự kiện nút làm mới
		btnLamMoi.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int gio = Integer.parseInt((String) cmbGio.getSelectedItem()),
						phut = Integer.parseInt((String) cmbPhut.getSelectedItem());
				LocalTime gioNhanPhong = LocalTime.of(gio, phut);
				LocalDate ngayNhanPhong = Utils.getLocalDate(txtNgayNhanPhong.getText());

				cmbLoaiPhong.removeItemListener(_this);
				cmbMaPhong.removeItemListener(_this);
				cmbSoLuong.removeItemListener(_this);

				emptyTable();
				addRow(datPhong_DAO.getPhongDatTruoc(ngayNhanPhong, gioNhanPhong));
				cmbLoaiPhong.setSelectedIndex(0);
				cmbMaPhong.setSelectedIndex(0);
				cmbSoLuong.setSelectedIndex(0);
				capNhatDanhSachPhongDatTruoc();

				cmbLoaiPhong.addItemListener(_this);
				cmbMaPhong.addItemListener(_this);
				cmbSoLuong.addItemListener(_this);
			};
		});

//		Sự kiện JTable
		tbl.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				btnChonPhong.setEnabled(true);
			};
		});

		tbl.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent lse) {
				if (!lse.getValueIsAdjusting()) {
					btnChonPhong.setEnabled(false);
				}
			}
		});

//		Sự kiện nút chọn phòng
		btnChonPhong.addMouseListener(new MouseAdapter() {
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
					capNhatDanhSachPhongDatTruoc();
					showDanhSachPhongDaChon();
					if (khachHang != null)
						btnDatPhong.setEnabled(true);
				}
			}
		});

//		Sự kiện nút quay lại
		btnQuayLai.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				quanLyDatPhongGUI.closeJFrameSub();
			}
		});

//		Sự kiện nút đặt phòng
		btnDatPhong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!btnDatPhong.isEnabled())
					return;

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

				LocalDate ngayNhanPhong = Utils.getLocalDate(txtNgayNhanPhong.getText());
				int gio = Integer.parseInt((String) cmbGio.getSelectedItem()),
						phut = Integer.parseInt((String) cmbPhut.getSelectedItem());
				LocalTime gioNhanPhong = LocalTime.of(gio, phut);
				boolean res = datPhong_DAO.themPhieuDatPhongTruoc(khachHang, new NhanVien("NV112"), dsPhongDaChon,
						ngayNhanPhong, gioNhanPhong);
				if (res) {
					quanLyDatPhongGUI.capNhatTrangThaiPhong();
					quanLyDatPhongGUI.closeJFrameSub();
				}
			}
		});
	}

	private void setTimeComboBox(int gioSelect) {
		cmbGio.removeItemListener(_this);

		cmbGio.removeAllItems();
		cmbPhut.removeAllItems();

		final int gioMoCua = 7;
		final int gioDongCua = 24;

		LocalTime timeNow = LocalTime.now();
		LocalDate dateSelect = Utils.getLocalDate(txtNgayNhanPhong.getText());
		int gio = timeNow.getHour(), phut = timeNow.getMinute();

		if (dateSelect.isBefore(LocalDate.now())) {
			cmbGio.setEnabled(false);
			cmbPhut.setEnabled(false);
			return;
		}
		cmbGio.setEnabled(true);
		cmbPhut.setEnabled(true);

		if (dateSelect.isEqual(LocalDate.now())) {
			for (int i = gio; i < gioDongCua; i++)
				cmbGio.addItem(i + "");
			if (gio == gioSelect || gioSelect == -1) {
				if (phut > 30) {
					cmbGio.removeItemAt(0);
					for (int j = 0; j < 60; j += 5)
						cmbPhut.addItem(j + "");
				} else {
					phut += (5 - phut % 5);
					for (int i = phut; i < 60; i += 5)
						cmbPhut.addItem(i + "");
				}
			} else
				for (int j = 0; j < 60; j += 5)
					cmbPhut.addItem(j + "");
		} else {
			for (int i = gioMoCua; i < gioDongCua; i++)
				cmbGio.addItem(i + "");
			for (int j = 0; j < 60; j += 5)
				cmbPhut.addItem(j + "");
		}
		cmbGio.setSelectedItem(gioSelect + "");
		cmbGio.addItemListener(_this);
	}

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
			pnlPhongDaChon.add(getPanelphongDaChonItem(top + i * (gapY + heightItem), dsPhongDaChon.get(i)));
		}

		pnlPhongDaChon.setPreferredSize(
				new Dimension(140, Math.max(202, top + heightItem * countItem + gapY * (countItem - 1))));
	}

	private PanelRound getPanelphongDaChonItem(int top, Phong phong) {
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
				capNhatDanhSachPhongDatTruoc();
				showDanhSachPhongDaChon();
				if (dsPhongDaChon.size() <= 0)
					btnDatPhong.setEnabled(false);
			}
		});

		return pnlContainerItem;
	}

	private void addRow(Phong phong) {
		phong.setLoaiPhong(loaiPhong_DAO.getLoaiPhong(phong.getLoaiPhong().getMaLoai()));
		tableModel.addRow(new String[] { phong.getMaPhong(), phong.getLoaiPhong().getTenLoai(),
				phong.getSoLuongKhach() + "", Phong.convertTrangThaiToString(phong.getTrangThai()) });
	}

	private void addRow(List<Phong> list) {
		emptyTable();

		list.forEach(phong -> {
			addRow(phong);
		});
	}

	private void emptyTable() {
		while (tbl.getRowCount() > 0)
			tableModel.removeRow(0);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		Object o = e.getSource();

		if (e.getStateChange() == ItemEvent.DESELECTED)
			return;
		if (o.equals(cmbGio)) {
			setTimeComboBox(Integer.parseInt((String) cmbGio.getSelectedItem()));
		} else {
			filterPhongDatTruoc();
		}
	}

	private void filterPhongDatTruoc() {
		String maPhong = (String) cmbMaPhong.getSelectedItem();
		String loaiPhong = (String) cmbLoaiPhong.getSelectedItem();
		String soLuong = (String) cmbSoLuong.getSelectedItem();

		if (maPhong.equals("Mã phòng"))
			maPhong = "";
		if (loaiPhong.equals("Loại phòng"))
			loaiPhong = "";

		int gio = Integer.parseInt((String) cmbGio.getSelectedItem()),
				phut = Integer.parseInt((String) cmbPhut.getSelectedItem());
		LocalTime gioNhanPhong = LocalTime.of(gio, phut);
		LocalDate ngayNhanPhong = Utils.getLocalDate(txtNgayNhanPhong.getText());
		dsPhongDatTruoc = datPhong_DAO.getPhongDatTruoc(ngayNhanPhong, gioNhanPhong, maPhong, loaiPhong, soLuong);
		emptyTable();
		addRow(dsPhongDatTruoc);
		capNhatDanhSachPhongDatTruoc();
	}

	private void capNhatDanhSachPhongDatTruoc() {
		if (dsPhongDatTruoc == null || dsPhongDaChon == null)
			return;

		emptyTable();
		btnChonPhong.setEnabled(false);

		for (Phong phong : dsPhongDatTruoc) {
			if (!dsPhongDaChon.contains(phong))
				addRow(phong);
		}
	}
}
