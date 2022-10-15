package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import components.button.Button;
import components.controlPanel.ControlPanel;
import components.jDialog.JDialogCustom;
import components.panelRound.PanelRound;
import components.scrollbarCustom.ScrollBarCustom;
import connectDB.ConnectDB;
import dao.DiaChi_DAO;
import dao.NhanVien_DAO;
import entity.NhanVien;
import entity.Phuong;
import entity.Quan;
import entity.Tinh;
import layouts.DefaultLayout;
import utils.StackFrame;
import utils.Utils;

public class QuanLyNhanVien_GUI extends JFrame implements WindowListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JLabel lblTime;
	private JTextField txtSearch;
	private JTable tbl;
	private DefaultTableModel tableModel;
	private NhanVien_DAO nhanVien_DAO;
	private DiaChi_DAO diaChi_DAO;
	private DefaultComboBoxModel<String> maNhanVienModel;
	private JComboBox<String> cboMaNhanVien;
	private JComboBox<String> cboTrangThai;
	private ControlPanel pnlControl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLyNhanVien_GUI frame = new QuanLyNhanVien_GUI();
					StackFrame.push(frame);
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
	public QuanLyNhanVien_GUI() {
		JFrame _this = this;

		try {
			new ConnectDB().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		nhanVien_DAO = new NhanVien_DAO();
		diaChi_DAO = new DiaChi_DAO();

		DefaultLayout defaultLayout = new DefaultLayout(this, contentPane, "Quản lý nhân viên");
		contentPane = defaultLayout.getJPanel();

//		setTitle("Quản lý nhân viên");
//		setResizable(false);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(0, 0, 1100, 610);
//		setLocationRelativeTo(null);
//
//		contentPane = new JPanel();
//		contentPane.setForeground(Color.GRAY);
//		contentPane.setBackground(Utils.secondaryColor);
//		setContentPane(contentPane);
//		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
//		contentPane.setLayout(null);
//
//		JPanel pnlHeader = new JPanel();
//		pnlHeader.setBackground(Utils.primaryColor);
//		pnlHeader.setBounds(0, 0, 1086, 65);
//		contentPane.add(pnlHeader);
//		pnlHeader.setLayout(null);
//
//		Button btnMenu = new Button("|||");
//		btnMenu.setBounds(23, 16, 38, 38);
//		btnMenu.setForeground(Utils.primaryColor);
//		btnMenu.setFont(new Font("Segoe UI", Font.PLAIN, 24));
//		btnMenu.setBorder(BorderFactory.createEmptyBorder());
//		btnMenu.setBackground(Color.WHITE);
//		btnMenu.setBorderColor(Utils.primaryColor);
//		btnMenu.setRadius(8);
//		btnMenu.setFocusable(false);
//		pnlHeader.add(btnMenu);
//
//		JLabel lblTitle = new JLabel("THÔNG TIN NHÂN VIÊN");
//		lblTitle.setForeground(Color.WHITE);
//		lblTitle.setBounds(76, 17, 948, 32);
//		lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
//		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
//		pnlHeader.add(lblTitle);
//
//		Button btnBack = new Button();
//		btnBack.setFocusable(false);
//		btnBack.setIcon(new ImageIcon("Icon\\back 1.png"));
//		btnBack.setColor(Utils.primaryColor);
//		btnBack.setColorOver(Utils.primaryColor);
//		btnBack.setColorClick(Utils.primaryColor);
//		btnBack.setBorderColor(Utils.primaryColor);
//		btnBack.setBorder(new EmptyBorder(0, 0, 0, 0));
//		btnBack.setBounds(954, 1, 62, 62);
//		pnlHeader.add(btnBack);
//		End default layout

//		Search
		JPanel pnlSearch = new JPanel();
		pnlSearch.setBackground(Utils.secondaryColor);
		pnlSearch.setBounds(16, 83, 1054, 24);
		contentPane.add(pnlSearch);
		pnlSearch.setLayout(null);

		JLabel lblSearch = new JLabel("TÌM KIẾM NHÂN VIÊN THEO TÊN:");
		lblSearch.setBounds(0, -1, 299, 27);
		lblSearch.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		pnlSearch.add(lblSearch);

		lblTime = new JLabel("");
		lblTime.setHorizontalAlignment(SwingConstants.LEFT);
		lblTime.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblTime.setBounds(874, 0, 180, 24);
		pnlSearch.add(lblTime);
		clock();

		JPanel pnlSearchForm = new JPanel();
		pnlSearchForm.setBackground(Utils.secondaryColor);
		pnlSearchForm.setBounds(16, 117, 1054, 36);
		contentPane.add(pnlSearchForm);
		pnlSearchForm.setLayout(null);

		Button btnSearch = new Button("Tìm");
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				filterNhanVien();
			}
		});
		btnSearch.setFocusable(false);
		btnSearch.setIcon(new ImageIcon("Icon\\searching.png"));
		btnSearch.setRadius(4);
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setColor(new Color(134, 229, 138));
		btnSearch.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnSearch.setBounds(904, -2, 150, 40);
		btnSearch.setBorderColor(Utils.secondaryColor);
		btnSearch.setColorOver(new Color(134, 229, 138));
		btnSearch.setColorClick(new Color(59, 238, 66));
		btnSearch.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlSearchForm.add(btnSearch);

		PanelRound pnlSearchInput = new PanelRound();
		pnlSearchInput.setRounded(4);
		pnlSearchInput.setBackground(Utils.secondaryColor);
		pnlSearchInput.setBounds(0, 0, 894, 36);
		pnlSearchInput.setBorder(new LineBorder(Color.BLACK));
		pnlSearchInput.setRounded(4);
		pnlSearchForm.add(pnlSearchInput);
		pnlSearchInput.setLayout(null);

		txtSearch = new JTextField();
		txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		txtSearch.setBackground(Utils.secondaryColor);
		txtSearch.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtSearch.setBounds(9, 1, 876, 34);
		pnlSearchInput.add(txtSearch);
		txtSearch.setColumns(10);

//		Actions
		JPanel pnlActions = new JPanel();
		pnlActions.setBackground(Utils.secondaryColor);
		pnlActions.setBounds(16, 169, 1054, 36);
		contentPane.add(pnlActions);
		pnlActions.setLayout(null);

		Button btnEmployeeView = new Button("Xem");
		btnEmployeeView.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tbl.getSelectedRow();
				if (row == -1) {
					new JDialogCustom(_this, components.jDialog.JDialogCustom.Type.warning).showMessage("Warning",
							"Vui lòng chọn nhân viên muốn xem");
				} else {
					JFrame jFrame = new ThongTinChiTietNhanVien_GUI(new NhanVien((String) tbl.getValueAt(row, 0)));
					StackFrame.push(jFrame);
					jFrame.setVisible(true);
					_this.setVisible(false);
				}
			}
		});
		btnEmployeeView.setFocusable(false);
		btnEmployeeView.setIcon(new ImageIcon("Icon\\user 1.png"));
		btnEmployeeView.setBounds(0, 0, 150, 36);
		btnEmployeeView.setRadius(4);
		btnEmployeeView.setForeground(Color.WHITE);
		btnEmployeeView.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnEmployeeView.setColorOver(Utils.primaryColor);
		btnEmployeeView.setColorClick(new Color(161, 184, 186));
		btnEmployeeView.setColor(Utils.primaryColor);
		btnEmployeeView.setBorderColor(Utils.secondaryColor);
		btnEmployeeView.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlActions.add(btnEmployeeView);

		Button btnEmployeeAdd = new Button("Thêm");
		btnEmployeeAdd.setFocusable(false);
		btnEmployeeAdd.setIcon(new ImageIcon("Icon\\add-user (2) 1.png"));
		btnEmployeeAdd.setRadius(4);
		btnEmployeeAdd.setForeground(Color.WHITE);
		btnEmployeeAdd.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnEmployeeAdd.setColorOver(Utils.primaryColor);
		btnEmployeeAdd.setColorClick(new Color(161, 184, 186));
		btnEmployeeAdd.setColor(Utils.primaryColor);
		btnEmployeeAdd.setBorderColor(Utils.secondaryColor);
		btnEmployeeAdd.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnEmployeeAdd.setBounds(165, 0, 150, 36);
		pnlActions.add(btnEmployeeAdd);

		Button btnEmployeeEdit = new Button("Sửa");
		btnEmployeeEdit.setFocusable(false);
		btnEmployeeEdit.setIcon(new ImageIcon("Icon\\edit 2.png"));
		btnEmployeeEdit.setRadius(4);
		btnEmployeeEdit.setForeground(Color.WHITE);
		btnEmployeeEdit.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnEmployeeEdit.setColorOver(Utils.primaryColor);
		btnEmployeeEdit.setColorClick(new Color(161, 184, 186));
		btnEmployeeEdit.setColor(Utils.primaryColor);
		btnEmployeeEdit.setBorderColor(Utils.secondaryColor);
		btnEmployeeEdit.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnEmployeeEdit.setBounds(330, 0, 150, 36);
		pnlActions.add(btnEmployeeEdit);

		Button btnEmployeeRemove = new Button("Nghỉ việc");
		btnEmployeeRemove.setFocusable(false);
		btnEmployeeRemove.setIcon(new ImageIcon("Icon\\unemployed 1.png"));
		btnEmployeeRemove.setRadius(4);
		btnEmployeeRemove.setForeground(Color.WHITE);
		btnEmployeeRemove.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnEmployeeRemove.setColorOver(Utils.primaryColor);
		btnEmployeeRemove.setColorClick(new Color(161, 184, 186));
		btnEmployeeRemove.setColor(Utils.primaryColor);
		btnEmployeeRemove.setBorderColor(Utils.secondaryColor);
		btnEmployeeRemove.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnEmployeeRemove.setBounds(495, 0, 150, 36);
		pnlActions.add(btnEmployeeRemove);

		cboTrangThai = new JComboBox<String>();
		cboTrangThai.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					filterNhanVien();
				}
			}
		});
		cboTrangThai.setModel(new DefaultComboBoxModel<String>(new String[] { "Trạng thái", "Đang làm", "Nghỉ làm" }));
		cboTrangThai.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cboTrangThai.setBackground(Utils.primaryColor);
		cboTrangThai.setBounds(904, 0, 150, 36);
		pnlActions.add(cboTrangThai);

		cboMaNhanVien = new JComboBox<String>();
		cboMaNhanVien.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					filterNhanVien();
				}
			}
		});
		maNhanVienModel = new DefaultComboBoxModel<String>(new String[] { "Mã NV" });
		cboMaNhanVien.setModel(maNhanVienModel);
		cboMaNhanVien.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cboMaNhanVien.setBackground(Utils.primaryColor);
		cboMaNhanVien.setBounds(739, 0, 150, 36);
		cboMaNhanVien.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlActions.add(cboMaNhanVien);

//		Table danh sách nhân viên
		JScrollPane scr = new JScrollPane();
		scr.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scr.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scr.setBounds(16, 223, 1054, 300);
		scr.setBackground(Utils.primaryColor);
		scr.getViewport().setBackground(Color.WHITE);
		ScrollBarCustom scp = new ScrollBarCustom();
//		Set color scrollbar thumb
		scp.setScrollbarColor(new Color(203, 203, 203));
		scr.setVerticalScrollBar(scp);
		contentPane.add(scr);
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

		tableModel = new DefaultTableModel(new String[] { "M\u00E3 NV", "H\u1ECD T\u00EAn", "CCCD", "S\u0110T",
				"Ng\u00E0y sinh", "Gi\u1EDBi t\u00EDnh", "\u0110\u1ECBa ch\u1EC9", "Tr\u1EA1ng th\u00E1i" }, 0);

		tbl.setModel(tableModel);
		tbl.setFocusable(false);
//		Cam
		tbl.getTableHeader().setBackground(new Color(255, 195, 174));
//		Xanh
		tbl.getTableHeader().setBackground(Utils.primaryColor);
		tbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl.setBackground(Color.WHITE);
		tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tbl.getColumnModel().getColumn(0).setPreferredWidth(61);
		tbl.getColumnModel().getColumn(1).setPreferredWidth(180);
		tbl.getColumnModel().getColumn(2).setPreferredWidth(120);
		tbl.getColumnModel().getColumn(3).setPreferredWidth(105);
		tbl.getColumnModel().getColumn(4).setPreferredWidth(100);
		tbl.getColumnModel().getColumn(5).setPreferredWidth(90);
		tbl.getColumnModel().getColumn(6).setPreferredWidth(288);
		tbl.getColumnModel().getColumn(7).setPreferredWidth(100);
		tbl.getTableHeader()
				.setPreferredSize(new Dimension((int) tbl.getTableHeader().getPreferredSize().getWidth(), 36));
		tbl.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl.setRowHeight(36);
//		tbl.setShowGrid(false);
		scr.setViewportView(tbl);

		pnlControl = new ControlPanel(400, 529, this);
		contentPane.add(pnlControl);

		tbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tbl.isEnabled()) {
					pnlControl.setTrangHienTai(tbl.getSelectedRow() + 1);
				}
			}
		});

		this.addWindowListener(this);
	}

	public static void clock() {
		Thread clock = new Thread() {
			@Override
			public void run() {
				for (;;) {
					try {
						LocalDateTime currTime = LocalDateTime.now();
						int day = currTime.getDayOfMonth();
						int month = currTime.getMonthValue();
						int year = currTime.getYear();
						int hour = currTime.getHour();
						int minute = currTime.getMinute();
						int second = currTime.getSecond();
						lblTime.setText(String.format("%s/%s/%s | %s:%s:%s", day < 10 ? "0" + day : day,
								month < 10 ? "0" + month : month, year, hour < 10 ? "0" + hour : hour,
								minute < 10 ? "0" + minute : minute, second < 10 ? "0" + second : second));
						sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};

		clock.start();
	}

	private void filterNhanVien() {
		String hoTen = txtSearch.getText();
		String maNhanVien = (String) cboMaNhanVien.getSelectedItem();
		String trangThai = (String) cboTrangThai.getSelectedItem();

		if (maNhanVien.equals("Mã NV"))
			maNhanVien = "";
		if (trangThai.equals("Trạng thái"))
			trangThai = "";

		List<NhanVien> list = nhanVien_DAO.filterNhanVien(hoTen, maNhanVien, trangThai);
		setEmptyTable();
		addRow(list);
		pnlControl.setTbl(tbl);

		if (list.size() == 0)
			System.out.println("Rỗng");
	}

	private void addRow(NhanVien nhanVien) {
		Tinh tinh = diaChi_DAO.getTinh(nhanVien.getTinh());
		Quan quan = diaChi_DAO.getQuan(nhanVien.getTinh(), nhanVien.getQuan());
		Phuong phuong = diaChi_DAO.getPhuong(nhanVien.getQuan(), nhanVien.getPhuong());
		tableModel.addRow(new String[] { nhanVien.getMaNhanVien(), nhanVien.getHoTen(), nhanVien.getCccd(),
				nhanVien.getSoDienThoai(), Utils.formatDate(nhanVien.getNgaySinh()),
				nhanVien.isGioiTinh() ? "Nam" : "Nữ", String.format("%s, %s, %s, %s", tinh.getTinh(), quan.getQuan(),
						phuong.getPhuong(), nhanVien.getDiaChiCuThe()),
				NhanVien.convertTrangThaiToString(nhanVien.getTrangThai()) });
	}

	private List<NhanVien> addRow(List<NhanVien> list) {
		list.forEach(nhanVien -> addRow(nhanVien));
		return list;
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
		setEmptyTable();
		addRow(nhanVien_DAO.getAllNhanVien()).forEach(nhanVien -> maNhanVienModel.addElement(nhanVien.getMaNhanVien()));
		pnlControl.setTbl(tbl);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

//	private NhanVien getNhanVienTuTable(int row) {
//		String maNhanVien = (String) tableModel.getValueAt(row, 0);
//		String hoTen = (String) tableModel.getValueAt(row, 1);
//		String cccd = (String) tableModel.getValueAt(row, 2);
//		String soDienThoai = (String) tableModel.getValueAt(row, 3);
//		LocalDate ngaySinh = Utils.getLocalDate((String) tableModel.getValueAt(row, 4));
//		boolean gioiTinh = ((String) tableModel.getValueAt(row, 5)).equals("Nam") ? true : false;
//		String diaChi = (String) tableModel.getValueAt(row, 6);
//		TrangThai trangThai = ((String) tableModel.getValueAt(row, 7)).equals("Đang làm") ? TrangThai.DangLam
//				: TrangThai.NghiLam;
//		return new NhanVien(maNhanVien, hoTen, cccd, soDienThoai, ngaySinh, gioiTinh, null, null, null, diaChi, null,
//				row, null, trangThai);
//	}

	private void setEmptyTable() {
		while (tbl.getRowCount() > 0)
			tableModel.removeRow(0);
	}
}
