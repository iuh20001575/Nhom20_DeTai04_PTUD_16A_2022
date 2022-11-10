package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import components.button.Button;
import components.controlPanel.ControlPanel;
import components.jDialog.JDialogCustom;
import components.jDialog.JDialogCustom.Type;
import components.notification.Notification;
import components.panelRound.PanelRound;
import components.scrollbarCustom.ScrollBarCustom;
import dao.DiaChi_DAO;
import dao.NhanVien_DAO;
import entity.NhanVien;
import entity.Phuong;
import entity.Quan;
import entity.Tinh;
import utils.Utils;

public class QuanLyNhanVien_GUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Button btnEmployeeAdd;
	private Button btnEmployeeEdit;
	private Button btnEmployeeRemove;
	private Button btnEmployeeView;
	private JComboBox<String> cmbMaNhanVien;
	private JComboBox<String> cmbTrangThai;
	private DiaChi_DAO diaChi_DAO;
	private JLabel lblTime;
	private Main main;
	private DefaultComboBoxModel<String> maNhanVienModel;
	private NhanVien_DAO nhanVien_DAO;
	private ControlPanel pnlControl;
	private DefaultTableModel tableModel;
	private JTable tbl;
	private JTextField txtSearch;
	private final int widthPnlContainer = 1086;

	/**
	 * Create the frame.
	 */
	public QuanLyNhanVien_GUI(Main main) {
		this.main = main;
		nhanVien_DAO = new NhanVien_DAO();
		diaChi_DAO = new DiaChi_DAO();

		setBackground(Utils.secondaryColor);
		setBounds(0, 0, Utils.getScreenWidth(), Utils.getBodyHeight());
		setLayout(null);

		JPanel pnlContainer = new JPanel();
		pnlContainer.setBackground(Utils.secondaryColor);
		pnlContainer.setBounds(Utils.getLeft(widthPnlContainer), 0, widthPnlContainer, Utils.getBodyHeight());
		pnlContainer.setLayout(null);
		this.add(pnlContainer);

//		Search
		JPanel pnlSearch = new JPanel();
		pnlSearch.setBackground(Utils.secondaryColor);
		pnlSearch.setBounds(16, 18, 1054, 24);
		pnlContainer.add(pnlSearch);
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

		JPanel pnlSearchForm = new JPanel();
		pnlSearchForm.setBackground(Utils.secondaryColor);
		pnlSearchForm.setBounds(16, 52, 1054, 36);
		pnlSearchForm.setLayout(null);
		pnlContainer.add(pnlSearchForm);

		Button btnSearch = new Button("Tìm");
		btnSearch.setFocusable(false);
		btnSearch.setIcon(new ImageIcon("Icon\\searching.png"));
		btnSearch.setRadius(4);
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnSearch.setBounds(904, -2, 150, 40);
		btnSearch.setBorderColor(Utils.secondaryColor);
		btnSearch.setBackground(new Color(134, 229, 138), new Color(134, 229, 138), new Color(59, 238, 66));
		btnSearch.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlSearchForm.add(btnSearch);

		PanelRound pnlSearchInput = new PanelRound();
		pnlSearchInput.setRounded(4);
		pnlSearchInput.setBackground(Utils.secondaryColor);
		pnlSearchInput.setBounds(0, 0, 894, 36);
		pnlSearchInput.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
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
		pnlActions.setBounds(16, 104, 1054, 36);
		pnlContainer.add(pnlActions);
		pnlActions.setLayout(null);

		btnEmployeeView = new Button("Xem");
		btnEmployeeView.setFocusable(false);
		btnEmployeeView.setIcon(new ImageIcon("Icon\\user 1.png"));
		btnEmployeeView.setBounds(-2, -2, 154, 40);
		btnEmployeeView.setRadius(4);
		btnEmployeeView.setForeground(Color.WHITE);
		btnEmployeeView.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnEmployeeView.setBackground(Utils.primaryColor, Utils.primaryColor, new Color(161, 184, 186));
		btnEmployeeView.setBorderColor(Utils.secondaryColor);
		btnEmployeeView.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlActions.add(btnEmployeeView);

		btnEmployeeAdd = new Button("Thêm");
		btnEmployeeAdd.setFocusable(false);
		btnEmployeeAdd.setIcon(new ImageIcon("Icon\\add-user (2) 1.png"));
		btnEmployeeAdd.setRadius(4);
		btnEmployeeAdd.setForeground(Color.WHITE);
		btnEmployeeAdd.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnEmployeeAdd.setBackground(Utils.primaryColor, Utils.primaryColor, new Color(161, 184, 186));
		btnEmployeeAdd.setBorderColor(Utils.secondaryColor);
		btnEmployeeAdd.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnEmployeeAdd.setBounds(163, -2, 154, 40);
		pnlActions.add(btnEmployeeAdd);

		btnEmployeeEdit = new Button("Sửa");
		btnEmployeeEdit.setFocusable(false);
		btnEmployeeEdit.setIcon(new ImageIcon("Icon\\edit 2.png"));
		btnEmployeeEdit.setRadius(4);
		btnEmployeeEdit.setForeground(Color.WHITE);
		btnEmployeeEdit.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnEmployeeEdit.setBackground(Utils.primaryColor, Utils.primaryColor, new Color(161, 184, 186));
		btnEmployeeEdit.setBorderColor(Utils.secondaryColor);
		btnEmployeeEdit.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnEmployeeEdit.setBounds(328, -2, 154, 40);
		pnlActions.add(btnEmployeeEdit);

		btnEmployeeRemove = new Button("Nghỉ việc");
		btnEmployeeRemove.setEnabled(false);
		btnEmployeeRemove.setFocusable(false);
		btnEmployeeRemove.setIcon(new ImageIcon("Icon\\unemployed 1.png"));
		btnEmployeeRemove.setRadius(4);
		btnEmployeeRemove.setForeground(Color.WHITE);
		btnEmployeeRemove.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnEmployeeRemove.setBackground(Utils.primaryColor, Utils.primaryColor, new Color(161, 184, 186));
		btnEmployeeRemove.setBorderColor(Utils.secondaryColor);
		btnEmployeeRemove.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnEmployeeRemove.setBounds(493, -2, 154, 40);
		pnlActions.add(btnEmployeeRemove);

		cmbTrangThai = new JComboBox<String>();
		cmbTrangThai.setModel(new DefaultComboBoxModel<String>(new String[] { "Trạng thái", "Đang làm", "Nghỉ làm" }));
		cmbTrangThai.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbTrangThai.setBackground(Utils.primaryColor);
		cmbTrangThai.setBounds(904, 0, 150, 36);
		pnlActions.add(cmbTrangThai);

		cmbMaNhanVien = new JComboBox<String>();
		maNhanVienModel = new DefaultComboBoxModel<String>(new String[] { "Mã NV" });
		cmbMaNhanVien.setModel(maNhanVienModel);
		cmbMaNhanVien.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbMaNhanVien.setBackground(Utils.primaryColor);
		cmbMaNhanVien.setBounds(739, 0, 150, 36);
		cmbMaNhanVien.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlActions.add(cmbMaNhanVien);

//		Table danh sách nhân viên
		int topPnlControl = Utils.getBodyHeight() - 80;

		JScrollPane scr = new JScrollPane();
		scr.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scr.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scr.setBounds(16, 158, 1054, topPnlControl - 174);
		scr.setBackground(Utils.primaryColor);
		scr.getViewport().setBackground(Color.WHITE);

		ScrollBarCustom scp = new ScrollBarCustom();
//		Set color scrollbar thumb
		scp.setScrollbarColor(new Color(203, 203, 203));
		scr.setVerticalScrollBar(scp);
		pnlContainer.add(scr);
		tbl = new JTable() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean getShowVerticalLines() {
				return false;
			}

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
		tbl.setAutoCreateRowSorter(true);

		tableModel = new DefaultTableModel(new String[] { "M\u00E3 NV", "H\u1ECD T\u00EAn", "CCCD", "S\u0110T",
				"Ng\u00E0y sinh", "Gi\u1EDBi t\u00EDnh", "\u0110\u1ECBa ch\u1EC9", "Tr\u1EA1ng th\u00E1i" }, 0);
		JTableHeader tblHeader = tbl.getTableHeader();
		TableColumnModel tableColumnModel = tbl.getColumnModel();

		tbl.setModel(tableModel);
		tbl.setFocusable(false);
		tblHeader.setBackground(Utils.primaryColor);
		tbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl.setBackground(Color.WHITE);
		tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableColumnModel.getColumn(0).setPreferredWidth(61);
		tableColumnModel.getColumn(1).setPreferredWidth(180);
		tableColumnModel.getColumn(2).setPreferredWidth(120);
		tableColumnModel.getColumn(3).setPreferredWidth(105);
		tableColumnModel.getColumn(4).setPreferredWidth(100);
		tableColumnModel.getColumn(5).setPreferredWidth(90);
		tableColumnModel.getColumn(6).setPreferredWidth(288);
		tableColumnModel.getColumn(7).setPreferredWidth(100);
		tblHeader.setPreferredSize(new Dimension((int) tblHeader.getPreferredSize().getWidth(), 36));
		tblHeader.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl.setRowHeight(36);
		scr.setViewportView(tbl);

		pnlControl = new ControlPanel(Utils.getLeft(286), topPnlControl, main);
		pnlContainer.add(pnlControl);

//		Sự kiện nút tìm kiếm nhân viên
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				filterNhanVien();
			}
		});

//		Sự kiện nút xem thông tin nhân viên
		btnEmployeeView.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!btnEmployeeView.isEnabled())
					return;
				int row = tbl.getSelectedRow();
				if (row == -1) {
					new JDialogCustom(main, components.jDialog.JDialogCustom.Type.warning).showMessage("Warning",
							"Vui lòng chọn nhân viên muốn xem");
				} else {
					String maNhanVien = (String) tableModel.getValueAt(row, 0);
					NhanVien nhanVien = nhanVien_DAO.getNhanVienTheoMa(maNhanVien);
					ThongTinChiTietNhanVien_GUI jFrame = new ThongTinChiTietNhanVien_GUI(main, nhanVien);
					main.addPnlBody(jFrame, "Thông tin chi tiết nhân viên", 1, 0);
				}
			}
		});

//		Sự kiện nút thêm nhân viên
		btnEmployeeAdd.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				main.addPnlBody(new ThemNhanVien_GUI(main), "Thêm nhân viên", 1, 0);
			};
		});

//		Sự kiện nút sửa thông tin nhân viên
		btnEmployeeEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!btnEmployeeEdit.isEnabled())
					return;
				int row = tbl.getSelectedRow();
				if (row == -1) {
					new JDialogCustom(main, components.jDialog.JDialogCustom.Type.warning).showMessage("Warning",
							"Vui lòng chọn nhân viên muốn sửa");
				} else {
					String maNhanVien = (String) tableModel.getValueAt(row, 0);
					NhanVien nhanVien = nhanVien_DAO.getNhanVienTheoMa(maNhanVien);
					JPanel pnl = new ThongTinChiTietNhanVien_GUI(main, nhanVien, true);
					main.addPnlBody(pnl, "Thông tin chi tiết nhân viên", 1, 0);
				}
			}
		});

//		Sự kiện nút nghỉ việc
		btnEmployeeRemove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!btnEmployeeRemove.isEnabled())
					return;

				int row = tbl.getSelectedRow();

				if (row != -1) {
					String maNhanVien = (String) tbl.getValueAt(row, 0);
					boolean res = nhanVien_DAO.setNghiLam(maNhanVien);

					if (res) {
						new Notification(main, components.notification.Notification.Type.SUCCESS,
								"Cập nhật trạng thái nhân viên thành công").showNotification();
						tableModel.setValueAt("Nghỉ làm", row, 7);
						btnEmployeeRemove.setEnabled(false);
					} else
						new Notification(main, components.notification.Notification.Type.ERROR,
								"Cập nhật trạng thái nhân viên thất bại").showNotification();
				}
			}
		});

//		Sự kiện JComboBox mã nhân viên
		cmbMaNhanVien.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					filterNhanVien();
				}
			}
		});

//		Sự kiện JComboBox trạng thái
		cmbTrangThai.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					filterNhanVien();
				}
			}
		});

//		Sự kiện JTable
		tbl.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent lse) {
				if (!lse.getValueIsAdjusting()) {
					setEnabledBtnActions();
				}
			}
		});

		tbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tbl.isEnabled()) {
					pnlControl.setTrangHienTai(tbl.getSelectedRow() + 1);
				}
			}
		});

//		Panel event
		addAncestorListener(new AncestorListener() {
			Thread clockThread;

			public void ancestorAdded(AncestorEvent event) {
				clockThread = clock();

				setEmptyTable();
				cmbMaNhanVien.removeAllItems();
				cmbMaNhanVien.addItem("Mã NV");
				List<NhanVien> list = nhanVien_DAO.getAllNhanVien();
				addRow(list);
				list.forEach(nhanVien -> cmbMaNhanVien.addItem(nhanVien.getMaNhanVien()));
				pnlControl.setTbl(tbl);
			}

			public void ancestorMoved(AncestorEvent event) {
			}

			@SuppressWarnings("deprecation")
			public void ancestorRemoved(AncestorEvent event) {
				clockThread.stop();
			}
		});
	}

	private List<NhanVien> addRow(List<NhanVien> list) {
		list.forEach(nhanVien -> addRow(nhanVien));
		return list;
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

	public Thread clock() {
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
						e.printStackTrace();
					}
				}
			}
		};

		clock.start();

		return clock;
	}

	private void filterNhanVien() {
		String hoTen = txtSearch.getText();
		String maNhanVien = (String) cmbMaNhanVien.getSelectedItem();
		String trangThai = (String) cmbTrangThai.getSelectedItem();

		if (maNhanVien.equals("Mã NV"))
			maNhanVien = "";
		if (trangThai.equals("Trạng thái"))
			trangThai = "";

		List<NhanVien> list = nhanVien_DAO.filterNhanVien(hoTen, maNhanVien, trangThai);
		setEmptyTable();
		addRow(list);
		pnlControl.setTbl(tbl);

		if (list.size() == 0) {
			JDialogCustom jDialogCustom = new JDialogCustom(main, Type.warning);
			jDialogCustom.showMessage("Thông báo", "Không có nhân viên cần tìm");
		}
	}

	private void setEmptyTable() {
		while (tbl.getRowCount() > 0)
			tableModel.removeRow(0);
	}

	private void setEnabledBtnActions() {
		int row = tbl.getSelectedRow();

		if (row == -1) {
			btnEmployeeView.setEnabled(false);
			btnEmployeeEdit.setEnabled(false);
			btnEmployeeRemove.setEnabled(false);
		} else {
			btnEmployeeView.setEnabled(true);
			btnEmployeeEdit.setEnabled(true);

			String trangThai = (String) tableModel.getValueAt(row, 7);

			if (NhanVien.convertStringToTrangThai(trangThai).equals(NhanVien.TrangThai.DangLam))
				btnEmployeeRemove.setEnabled(true);
			else
				btnEmployeeRemove.setEnabled(false);
		}
	}
}
