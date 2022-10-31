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
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import components.button.Button;
import components.controlPanel.ControlPanel;
import components.jDialog.JDialogCustom;
import components.notification.Notification;
import components.panelRound.PanelRound;
import components.scrollbarCustom.ScrollBarCustom;
import dao.DichVu_DAO;
import dao.LoaiDichVu_DAO;
import entity.DichVu;
import entity.LoaiDichVu;
import entity.NhanVien;
import utils.Utils;

public class QuanLyDichVu_GUI extends JPanel {
	private static final long SerialVersionUID = 1L;

	private static JLabel lblTime;
	private JTextField txtSearch;
	private JComboBox<String> cmbLoaiDV;
	private JComboBox<String> cmbSoLuong;
	private LoaiDichVu_DAO LoaiDichVu_DAO;
	private DichVu_DAO DichVu_DAO;
	private JTable tbl;
	private DefaultTableModel tableModel;
	private ControlPanel pnlControl;
	private Button btnXem, btnThem, btnSua, btnXoa;
	public QuanLyDichVu_GUI(Main main) {

		LoaiDichVu_DAO = new LoaiDichVu_DAO();
		DichVu_DAO = new DichVu_DAO();

		setBackground(Utils.secondaryColor);
		setBounds(0, 0, 1086, 508);
		setLayout(null);

//		Search
		JPanel pnlSearch = new JPanel();
		pnlSearch.setBackground(Utils.secondaryColor);
		pnlSearch.setBounds(16, 0, 1054, 24);
		this.add(pnlSearch);
		pnlSearch.setLayout(null);

		JLabel lblSearch = new JLabel("TÌM KIẾM DỊCH VỤ THEO TÊN:");
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
		pnlSearchForm.setBounds(16, 34, 1054, 36);
		this.add(pnlSearchForm);
		pnlSearchForm.setLayout(null);

		Button btnSearch = new Button("Tìm");
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

		JPanel pnlButton = new JPanel();
		pnlButton.setBackground(Utils.secondaryColor);
		pnlButton.setBounds(16, 87, 1060, 40);
		this.add(pnlButton);
		pnlButton.setLayout(null);

		btnXem = new Button("Xem");

		btnXem.setFocusable(false);
		btnXem.setIcon(new ImageIcon("Icon\\searching.png"));
		btnXem.setRadius(4);
		btnXem.setForeground(Color.WHITE);
		btnXem.setColor(new Color(134, 229, 138));
		btnXem.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnXem.setBounds(0, 0, 150, 40);
		btnXem.setBorderColor(Utils.secondaryColor);
		btnXem.setColorOver(new Color(134, 229, 138));
		btnXem.setColorClick(new Color(59, 238, 66));
		btnXem.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlButton.add(btnXem);

		btnThem = new Button("Thêm");

		btnThem.setFocusable(false);
		btnThem.setIcon(new ImageIcon("Icon\\searching.png"));
		btnThem.setRadius(4);
		btnThem.setForeground(Color.WHITE);
		btnThem.setColor(new Color(134, 229, 138));
		btnThem.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnThem.setBounds(180, 0, 150, 40);
		btnThem.setBorderColor(Utils.secondaryColor);
		btnThem.setColorOver(new Color(134, 229, 138));
		btnThem.setColorClick(new Color(59, 238, 66));
		btnThem.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlButton.add(btnThem);

		btnSua = new Button("Sửa");

		btnSua.setFocusable(false);
		btnSua.setIcon(new ImageIcon("Icon\\searching.png"));
		btnSua.setRadius(4);
		btnSua.setForeground(Color.WHITE);
		btnSua.setColor(new Color(134, 229, 138));
		btnSua.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnSua.setBounds(360, 0, 150, 40);
		btnSua.setBorderColor(Utils.secondaryColor);
		btnSua.setColorOver(new Color(134, 229, 138));
		btnSua.setColorClick(new Color(59, 238, 66));
		btnSua.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlButton.add(btnSua);

		btnXoa = new Button("Xóa");

		btnXoa.setFocusable(false);
		btnXoa.setIcon(new ImageIcon("Icon\\searching.png"));
		btnXoa.setRadius(4);
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setColor(new Color(134, 229, 138));
		btnXoa.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnXoa.setBounds(540, 0, 150, 40);
		btnXoa.setBorderColor(Utils.secondaryColor);
		btnXoa.setColorOver(new Color(134, 229, 138));
		btnXoa.setColorClick(new Color(59, 238, 66));
		btnXoa.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlButton.add(btnXoa);

		cmbLoaiDV = new JComboBox<String>();
		cmbLoaiDV.addItem("Phân loại");
		ArrayList<LoaiDichVu> listLoaiDV = (ArrayList<LoaiDichVu>) LoaiDichVu_DAO.getAllLoaiDichVu();
		for (LoaiDichVu loaiDV : listLoaiDV) {
			cmbLoaiDV.addItem(loaiDV.getTenLoaiDichVu());
		}

		cmbLoaiDV.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbLoaiDV.setBackground(Utils.primaryColor);
		cmbLoaiDV.setBounds(700, 0, 150, 36);
		pnlButton.add(cmbLoaiDV);

		cmbSoLuong = new JComboBox<String>();

		cmbSoLuong.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Số lượng", "<50", "50-100", "100-200", ">200" }));
		cmbSoLuong.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbSoLuong.setBackground(Utils.primaryColor);
		cmbSoLuong.setBounds(900, 0, 150, 36);
		cmbSoLuong.setSelectedItem("Loa");
		pnlButton.add(cmbSoLuong);

		JScrollPane scr = new JScrollPane();
		scr.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scr.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scr.setBounds(16, 140, 1054, 300);
		scr.setBackground(Utils.primaryColor);
		ScrollBarCustom scp = new ScrollBarCustom();
//		Set color scrollbar thumb
		scp.setScrollbarColor(new Color(203, 203, 203));
		scr.setVerticalScrollBar(scp);
		this.add(scr);

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
				new String[] { "Mã DV", "TênDV", "Đơn vị tính", "Số lượng", "Loại", "Giá mua", "Giá bán" }, 0);

		tbl.setModel(tableModel);
		tbl.setFocusable(false);
//		Cam
		tbl.getTableHeader().setBackground(new Color(255, 195, 174));
//		Xanh
		tbl.getTableHeader().setBackground(Utils.primaryColor);
		tbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl.setBackground(Color.WHITE);
		tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tbl.getColumnModel().getColumn(0).setPreferredWidth(80);
		tbl.getColumnModel().getColumn(1).setPreferredWidth(224);
		tbl.getColumnModel().getColumn(2).setPreferredWidth(120);
		tbl.getColumnModel().getColumn(3).setPreferredWidth(120);
		tbl.getColumnModel().getColumn(4).setPreferredWidth(180);
		tbl.getColumnModel().getColumn(5).setPreferredWidth(160);
		tbl.getColumnModel().getColumn(6).setPreferredWidth(160);
		tbl.getTableHeader()
				.setPreferredSize(new Dimension((int) tbl.getTableHeader().getPreferredSize().getWidth(), 36));
		tbl.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl.setRowHeight(36);
//		tbl.setShowGrid(false);
		scr.setViewportView(tbl);
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.RIGHT);
		tbl.getColumnModel().getColumn(3).setCellRenderer(dtcr);
		tbl.getColumnModel().getColumn(5).setCellRenderer(dtcr);
		tbl.getColumnModel().getColumn(6).setCellRenderer(dtcr);
		pnlControl = new ControlPanel(400, 529, main);
		pnlControl.setLocation(400, 464);
		// pnlControl.add(contentPane);
		this.add(pnlControl);

		setEmptyTable();
		List<DichVu> listDV = (List<DichVu>) DichVu_DAO.getAllDichVu();
		addRow(listDV);
		pnlControl.setTbl(tbl);
		// addRow(DichVu_DAO.getAllDichVu()).forEach(dichVu ->
		// maNhanVienModel.addElement(nhanVien.getMaNhanVien()));
		pnlControl.setTbl(tbl);

		// Sự kiện nút tìm kiếm dịch vụ
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				filterDichVu();
			}
		});
//		Sự kiện nút thêm nhân viên
		btnThem.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				main.addPnlBody(new ThemDichVu_GUI(main), "Thêm dịch vụ", 1, 0);
			};
		});
//		Sự kiện nút xem dịch vụ
		btnXem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!btnXem.isEnabled())
					return;
				int row = tbl.getSelectedRow();
				if (row == -1) {
					new JDialogCustom(main, components.jDialog.JDialogCustom.Type.warning).showMessage("Warning",
							"Vui lòng chọn dịch vụ muốn xem");
				} else {
					String maDichVu = tableModel.getValueAt(row, 0).toString();
					main.addPnlBody(new ThongTinChiTietDichVu_GUI(main, DichVu_DAO.getDichVuTheoMa(maDichVu), false),
							"Thông tin chi tiết dịch vụ", 1, 0);
					setEmptyTable();
					addRow(DichVu_DAO.getAllDichVu());
				}
			}
		});

//		Sự kiện nút sửa thông tin dịch vụ
		btnSua.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!btnSua.isEnabled())
					return;
				int row = tbl.getSelectedRow();
				if (row == -1) {
					new JDialogCustom(main, components.jDialog.JDialogCustom.Type.warning).showMessage("Warning",
							"Vui lòng chọn dịch vụ muốn sửa");
				} else {
					String maDichVu = tableModel.getValueAt(row, 0).toString();
					main.addPnlBody(new ThongTinChiTietDichVu_GUI(main, DichVu_DAO.getDichVuTheoMa(maDichVu), true),
							"Thông tin chi tiết dịch vụ", 1, 0);
				}
			}
		});

//		Sự kiện nút xóa dịch vụ
		btnXoa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!btnXoa.isEnabled())
					return;

				int row = tbl.getSelectedRow();

				if (row != -1) {

					JDialogCustom jDialogCustom = new JDialogCustom(main);

					jDialogCustom.getBtnOK().addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							boolean res = DichVu_DAO.xoaDichVu(tbl.getValueAt(row, 0).toString());
							if (res)
								new Notification(main, components.notification.Notification.Type.SUCCESS,
										"Xóa dịch vụ thành công");
							else
								new Notification(main, components.notification.Notification.Type.ERROR,
										"Xóa dịch vụ thất bại");
							setEmptyTable();
							addRow(DichVu_DAO.getAllDichVu());
						};
					});

					jDialogCustom.getBtnCancel().addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							return;
						};
					});
					jDialogCustom.showMessage("Warning", "Bạn chắc chắn muốn xóa dịch vụ này");
					
				}
				else {
					new JDialogCustom(main, components.jDialog.JDialogCustom.Type.warning).showMessage("Warning",
							"Vui lòng chọn dịch vụ muốn xóa");
				}
			}
		});
		
//		Sự kiện JComboBox loại dịch vụ
		cmbLoaiDV.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					filterDichVu();
				}
			}
		});

//		Sự kiện JComboBox trạng thái
		cmbSoLuong.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					filterDichVu();
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
	}

	private void addRow(DichVu dichVu) {

		tableModel.addRow(new String[] { dichVu.getMaDichVu(), dichVu.getTenDichVu(), dichVu.getDonViTinh(),
				String.valueOf(dichVu.getSoLuong()), dichVu.getLoaiDichVu().getTenLoaiDichVu(),
				Utils.formatTienTe(dichVu.getGiaMua()), Utils.formatTienTe(dichVu.getGiaMua() * 1.5) });
	}

	private List<DichVu> addRow(List<DichVu> list) {
		list.forEach(dichVu -> addRow(dichVu));
		return list;
	}

	private void setEmptyTable() {
		while (tbl.getRowCount() > 0)
			tableModel.removeRow(0);
	}

	private void filterDichVu() {
		String tenDichVu = txtSearch.getText();
		String tenLoaiDV = cmbLoaiDV.getSelectedItem().toString();
		String soLuong = cmbSoLuong.getSelectedItem().toString();

		if (tenLoaiDV.equals("Phân loại"))
			tenLoaiDV = "";
		if (soLuong.equals("Số lượng"))
			soLuong = "";
		List<DichVu> list = DichVu_DAO.filterDichVu(tenDichVu, tenLoaiDV, soLuong);
		setEmptyTable();
		addRow(list);
	}

	private void setEnabledBtnActions() {
		int row = tbl.getSelectedRow();

		if (row == -1) {
			btnXem.setEnabled(false);
			btnThem.setEnabled(false);
			btnSua.setEnabled(false);
			btnXoa.setEnabled(false);
		} else {
			btnXem.setEnabled(true);
			btnSua.setEnabled(true);
			btnThem.setEnabled(true);
			btnXoa.setEnabled(true);
		}
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

}
