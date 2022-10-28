package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import components.button.Button;
import components.comboBox.ComboBox;
import components.notification.Notification;
import components.panelRound.PanelRound;
import components.scrollbarCustom.ScrollBarCustom;
import dao.DatPhong_DAO;
import dao.LoaiPhong_DAO;
import entity.LoaiPhong;
import entity.Phong;
import entity.Phong.TrangThai;
import utils.Utils;

public class GopPhong_GUI extends JFrame implements ItemListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pnlContent;
	private JPanel pnlPhongGop;
	private JTable tblPhongCanGop;
	private JTable tblPhongGop;
	private DefaultTableModel tableModelPhongGop;
	private DefaultTableModel tableModelPhongCanGop;
	private Button btnChuyen;
	private Button btnChonPhong;
	private ComboBox<String> cmbMaDatPhong;

	private DatPhong_DAO datPhong_DAO;
	private LoaiPhong_DAO loaiPhong_DAO;

	private GopPhong_GUI _this;

	private final String labelCmbMaDatPhong = "Mã đặt phòng";
	private List<Phong> dsPhongDaChon;
	private List<Phong> dsPhongCanGop;

	/**
	 * Create the frame.
	 * 
	 * @param quanLyDatPhongGUI
	 * @param glass
	 */
	public GopPhong_GUI(QuanLyDatPhong_GUI quanLyDatPhongGUI, JFrame parentFrame) {
		_this = this;
		datPhong_DAO = new DatPhong_DAO();
		loaiPhong_DAO = new LoaiPhong_DAO();

		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 489);
		pnlContent = new JPanel();
		pnlContent.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlContent);
		pnlContent.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);

		JPanel pnlContainer = new JPanel();
		pnlContainer.setBackground(Color.WHITE);
		pnlContainer.setBounds(0, 0, 600, 490);
		pnlContent.add(pnlContainer);
		pnlContainer.setLayout(null);

		JPanel pnlHeader = new JPanel();
		pnlHeader.setBackground(Utils.primaryColor);
		pnlHeader.setBounds(0, 0, 600, 50);
		pnlContainer.add(pnlHeader);
		pnlHeader.setLayout(null);

		JLabel lblTitle = new JLabel("Gộp phòng");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblTitle.setBounds(200, 0, 200, 50);
		pnlHeader.add(lblTitle);

		JLabel lblMaDatPhong = new JLabel(labelCmbMaDatPhong);
		lblMaDatPhong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblMaDatPhong.setBounds(16, 58, 100, 34);
		pnlContainer.add(lblMaDatPhong);

		cmbMaDatPhong = new ComboBox<>();
		cmbMaDatPhong.setBackground(Utils.primaryColor);
		cmbMaDatPhong.setModel(new DefaultComboBoxModel<String>(new String[] { labelCmbMaDatPhong }));
		cmbMaDatPhong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		cmbMaDatPhong.setBounds(126, 59, 150, 32);
		pnlContainer.add(cmbMaDatPhong);

		pnlPhongGop = new JPanel();
		pnlPhongGop.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Ph\u00F2ng c\u1EA7n g\u1ED9p", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlPhongGop.setBackground(Color.WHITE);
		pnlPhongGop.setBounds(16, 108, 568, 150);
		pnlContainer.add(pnlPhongGop);
		pnlPhongGop.setLayout(null);

		JScrollPane scrDanhSachPhong = new JScrollPane();
		scrDanhSachPhong.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrDanhSachPhong.setBackground(Utils.secondaryColor);
		scrDanhSachPhong.getViewport().setBackground(Color.WHITE);
		scrDanhSachPhong.setBounds(2, 17, 366, 130);
		pnlPhongGop.add(scrDanhSachPhong);

		ScrollBarCustom scbDanhSachPhong = new ScrollBarCustom();
//		Set color scrollbar thumb
		scbDanhSachPhong.setScrollbarColor(new Color(203, 203, 203));
		scrDanhSachPhong.setVerticalScrollBar(scbDanhSachPhong);

		tblPhongCanGop = new JTable() {
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

		tableModelPhongCanGop = new DefaultTableModel(new String[] { "Mã phòng", "Loại phòng", "Số lượng" }, 0);

		tblPhongCanGop.setModel(tableModelPhongCanGop);
		tblPhongCanGop.setFocusable(false);
		tblPhongCanGop.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblPhongCanGop.getTableHeader().setBackground(Utils.primaryColor);
		tblPhongCanGop.getTableHeader().setForeground(Color.WHITE);
		tblPhongCanGop.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tblPhongCanGop.getTableHeader().setPreferredSize(
				new Dimension((int) tblPhongCanGop.getTableHeader().getPreferredSize().getWidth(), 24));
		tblPhongCanGop.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tblPhongCanGop.setRowHeight(24);
		scrDanhSachPhong.setViewportView(tblPhongCanGop);
//		Căn phải column 3 table
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.RIGHT);
		tblPhongCanGop.getColumnModel().getColumn(2).setCellRenderer(dtcr);

		JPanel pnlPhongGopThanh = new JPanel();
		pnlPhongGopThanh.setBorder(new TitledBorder(null, "Ph\u00F2ng g\u1ED9p th\u00E0nh", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		pnlPhongGopThanh.setBackground(Color.WHITE);
		pnlPhongGopThanh.setBounds(16, 274, 568, 150);
		pnlContainer.add(pnlPhongGopThanh);
		pnlPhongGopThanh.setLayout(null);

		JScrollPane scrDanhSachPhongGop = new JScrollPane();
		scrDanhSachPhongGop.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrDanhSachPhongGop.setBackground(Utils.secondaryColor);
		scrDanhSachPhongGop.getViewport().setBackground(Color.WHITE);
		scrDanhSachPhongGop.setBounds(3, 17, 561, 130);
		pnlPhongGopThanh.add(scrDanhSachPhongGop);

		ScrollBarCustom scbDanhSachPhongGop = new ScrollBarCustom();
//		Set color scrollbar thumb
		scbDanhSachPhongGop.setScrollbarColor(new Color(203, 203, 203));
		scrDanhSachPhongGop.setVerticalScrollBar(scbDanhSachPhongGop);

		tblPhongGop = new JTable() {
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

		tableModelPhongGop = new DefaultTableModel(new String[] { "Mã phòng", "Loại phòng", "Số lượng", "Trạng thái" },
				0);

		tblPhongGop.setModel(tableModelPhongGop);
		tblPhongGop.setFocusable(false);
		tblPhongGop.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblPhongGop.getTableHeader().setBackground(Utils.primaryColor);
		tblPhongGop.getTableHeader().setForeground(Color.WHITE);
		tblPhongGop.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tblPhongGop.getTableHeader()
				.setPreferredSize(new Dimension((int) tblPhongGop.getTableHeader().getPreferredSize().getWidth(), 24));
		tblPhongGop.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tblPhongGop.setRowHeight(24);
		scrDanhSachPhongGop.setViewportView(tblPhongGop);
//		Căn phải column 3 table
		tblPhongGop.getColumnModel().getColumn(2).setCellRenderer(dtcr);

		JPanel pnlFooter = new JPanel();
		pnlFooter.setBackground(Color.WHITE);
		pnlFooter.setBounds(16, 440, 568, 34);
		pnlContainer.add(pnlFooter);
		pnlFooter.setLayout(null);

		Button btnQuayLai = new Button("Quay lại");
		btnQuayLai.setBorderColor(Color.white);
		btnQuayLai.setRadius(4);
		btnQuayLai.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnQuayLai.setFocusable(false);
		btnQuayLai.setForeground(Color.BLACK);
		btnQuayLai.setColor(Utils.secondaryColor);
		btnQuayLai.setColorOver(Utils.getOpacity(Utils.secondaryColor, 0.8f));
		btnQuayLai.setColorClick(Utils.getOpacity(Utils.secondaryColor, 0.6f));
		btnQuayLai.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnQuayLai.setBounds(0, 0, 127, 34);
		pnlFooter.add(btnQuayLai);

		btnChuyen = new Button("Gộp phòng");
		btnChuyen.setEnabled(false);
		btnChuyen.setBorderColor(Color.WHITE);
		btnChuyen.setRadius(4);
		btnChuyen.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnChuyen.setFocusable(false);
		btnChuyen.setForeground(Color.WHITE);
		btnChuyen.setColor(Utils.primaryColor);
		btnChuyen.setColorOver(Utils.getOpacity(Utils.primaryColor, 0.8f));
		btnChuyen.setColorClick(Utils.getOpacity(Utils.primaryColor, 0.6f));
		btnChuyen.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnChuyen.setBounds(441, 0, 127, 34);
		pnlFooter.add(btnChuyen);

		btnChonPhong = new Button("");
		btnChonPhong.setFocusable(false);
		btnChonPhong.setRadius(8);
		btnChonPhong.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnChonPhong.setColor(Utils.primaryColor);
		btnChonPhong.setColorOver(Utils.getOpacity(Utils.primaryColor, 0.8f));
		btnChonPhong.setColorClick(Utils.getOpacity(Utils.primaryColor, 0.6f));
		btnChonPhong.setBorderColor(Color.WHITE);
		btnChonPhong.setIcon(new ImageIcon("Icon\\rightArrow_32x32.png"));
		btnChonPhong.setBounds(374, 57, 36, 36);
		btnChonPhong.setEnabled(false);
		pnlPhongGop.add(btnChonPhong);

		showDanhSachPhongDaChon();

//		Lắng nghe sự kiện window
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				List<String> dsMaDatPhongDangThue = datPhong_DAO.getMaDatPhongGop();

				cmbMaDatPhong.removeAllItems();
				cmbMaDatPhong.addItem(labelCmbMaDatPhong);
				dsMaDatPhongDangThue.forEach(string -> cmbMaDatPhong.addItem(string));

				cmbMaDatPhong.addItemListener(_this);
			}
		});

//		Sự kiện JTable phòng cần gộp
		tblPhongCanGop.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				btnChonPhong.setEnabled(true);
			};
		});

//		Sự kiện nút chọn phòng
		btnChonPhong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tblPhongCanGop.getSelectedRow();
				if (row != -1) {
					if (dsPhongDaChon == null)
						dsPhongDaChon = new ArrayList<>();
					Phong phong = new Phong((String) tableModelPhongCanGop.getValueAt(row, 0));
					if (dsPhongDaChon.contains(phong))
						return;
					dsPhongDaChon.add(phong);

					showDanhSachPhongDaChon();
					showDanhSachPhongGop();
					setEnabledBtnChuyenPhong();
					capNhatDanhSachPhongDatTruoc();

					if (tblPhongGop.getSelectedRow() != -1)
						btnChuyen.setEnabled(true);
				}
			}
		});

//		Lắng nghe sự kiện table gộp phòng
		tblPhongGop.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setEnabledBtnChuyenPhong();
			};
		});

//		Lắng nghe sự kiện nút gộp phòng
		btnChuyen.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (!btnChuyen.isEnabled())
					return;

				boolean res = datPhong_DAO.gopPhong((String) cmbMaDatPhong.getSelectedItem(), dsPhongDaChon,
						getPhongTuTablePhongGop());

				if (res) {
					quanLyDatPhongGUI.capNhatTrangThaiPhong();
					quanLyDatPhongGUI.closeJFrameSub();
					new Notification(parentFrame, components.notification.Notification.Type.SUCCESS,
							"Gộp phòng thành công").showNotification();
				}
			};
		});

//		Lắng nghe sự kiện nút quay lại
		btnQuayLai.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				quanLyDatPhongGUI.closeJFrameSub();
			};
		});
	}

	/**
	 * Hiển thị các phòng đã chọn vào mục phòng đã chọn
	 */
	private void showDanhSachPhongDaChon() {
		JScrollPane scrPhongDaChon = new JScrollPane();
		scrPhongDaChon.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrPhongDaChon.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrPhongDaChon.setBackground(Color.WHITE);
		scrPhongDaChon.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true),
				"Ph\u00F2ng \u0111\u00E3 ch\u1ECDn", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		scrPhongDaChon.setBounds(416, 17, 150, 130);

		if (pnlPhongGop.getComponentAt(416, 17) != null) {
			pnlPhongGop.remove(pnlPhongGop.getComponentAt(416, 17));
		}

		pnlPhongGop.add(scrPhongDaChon);
		ScrollBarCustom scbPhongDaChon = new ScrollBarCustom();
		scbPhongDaChon.setBackgroundColor(Utils.secondaryColor);
		scbPhongDaChon.setScrollbarColor(Utils.primaryColor);
		scrPhongDaChon.setVerticalScrollBar(scbPhongDaChon);

		JPanel pnlPhongDaChon = new JPanel();
		pnlPhongDaChon.setBackground(Color.WHITE);
		scrPhongDaChon.setViewportView(pnlPhongDaChon);
		pnlPhongDaChon.setLayout(null);

		if (dsPhongDaChon == null)
			return;

		int heightItem = 36;
		int gapY = 8;
		int top = 4;
		int countItem = dsPhongDaChon.size();
		for (int i = 0; i < countItem; i++) {
			pnlPhongDaChon.add(getPanelPhongDaChonItem(top + i * (gapY + heightItem), dsPhongDaChon.get(i)));
		}

		pnlPhongDaChon.setPreferredSize(
				new Dimension(140, Math.max(105, top + heightItem * countItem + gapY * (countItem - 1))));
	}

	/**
	 * Get pnlContainer phòng đã chọn
	 * 
	 * @param top   khoảng cách top từ container đến item
	 * @param phong phòng được chọn
	 * @return pnlContainer
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
				showDanhSachPhongGop();
				showDanhSachPhongDaChon();
				setEnabledBtnChuyenPhong();
				capNhatDanhSachPhongDatTruoc();

				if (dsPhongDaChon == null || dsPhongDaChon.size() <= 0)
					btnChuyen.setEnabled(false);
			}
		});

		return pnlContainerItem;
	}

	/**
	 * Show danh sách phòng gộp to Table
	 */
	private void showDanhSachPhongGop() {
		String maDatPhong = (String) cmbMaDatPhong.getSelectedItem();

		if (maDatPhong.equals(labelCmbMaDatPhong))
			return;

		emptyTable(tblPhongGop, tableModelPhongGop);

		List<LoaiPhong> dsLoaiPhong = loaiPhong_DAO.getAllLoaiPhong();
		List<Phong> dsPhongGop = datPhong_DAO.getPhongCoTheGop(maDatPhong, dsPhongDaChon);

		dsPhongGop.forEach(phong -> {
			for (int i = 0; i < dsLoaiPhong.size(); i++) {
				if (dsLoaiPhong.get(i).equals(phong.getLoaiPhong())) {
					phong.setLoaiPhong(dsLoaiPhong.get(i));
					break;
				}
			}
			addRowTablePhongGop(phong);
		});

		tblPhongGop.scrollRectToVisible(tblPhongGop.getCellRect(0, 0, true));
	}

	/**
	 * Sự kiện ComboBox Mã đặt phòng
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.DESELECTED)
			return;

		String maDatPhong = (String) cmbMaDatPhong.getSelectedItem();
		dsPhongDaChon = null;
		showDanhSachPhongDaChon();
		emptyTable(tblPhongGop, tableModelPhongGop);
		emptyTable(tblPhongCanGop, tableModelPhongCanGop);

		if (!maDatPhong.equals(labelCmbMaDatPhong)) {
			dsPhongCanGop = datPhong_DAO.getPhongDangThue(maDatPhong);
			List<LoaiPhong> dsLoaiPhong = loaiPhong_DAO.getAllLoaiPhong();

			dsPhongCanGop.forEach(phong -> {
				for (int i = 0; i < dsLoaiPhong.size(); i++) {
					if (dsLoaiPhong.get(i).equals(phong.getLoaiPhong())) {
						phong.setLoaiPhong(dsLoaiPhong.get(i));
						break;
					}
				}
				addRowTableCanGop(phong);
			});

			showDanhSachPhongGop();
		}

		setEnabledBtnChuyenPhong();
		btnChonPhong.setEnabled(false);
	}

	/**
	 * Set Enabled nút chuyển phòng
	 */
	private void setEnabledBtnChuyenPhong() {
		if (dsPhongDaChon == null || dsPhongDaChon.size() <= 0 || tblPhongGop.getSelectedRow() == -1)
			btnChuyen.setEnabled(false);
		else
			btnChuyen.setEnabled(true);
	}

	/**
	 * Thêm một phòng vào Table gộp phòng
	 * 
	 * @param phong
	 */
	private void addRowTablePhongGop(Phong phong) {
		tableModelPhongGop.addRow(new String[] { phong.getMaPhong(), phong.getLoaiPhong().getTenLoai(),
				phong.getSoLuongKhach() + "", Phong.convertTrangThaiToString(phong.getTrangThai()) });
	}

	/**
	 * Thêm một phòng vào table phòng cần gộp
	 * 
	 * @param phong
	 */
	private void addRowTableCanGop(Phong phong) {
		tableModelPhongCanGop.addRow(
				new String[] { phong.getMaPhong(), phong.getLoaiPhong().getTenLoai(), phong.getSoLuongKhach() + "" });
	}

	private Phong getPhongTuTablePhongGop() {
		int row = tblPhongGop.getSelectedRow();

		if (row == -1)
			return null;

		String maPhong = (String) tableModelPhongGop.getValueAt(row, 0);
		LoaiPhong loaiPhong = loaiPhong_DAO.getLoaiPhongTheoTenLoai((String) tableModelPhongGop.getValueAt(row, 1));
		int soLuongKhach = Integer.parseInt((String) tableModelPhongGop.getValueAt(row, 2));
		TrangThai trangThai = Phong.convertStringToTrangThai((String) tableModelPhongGop.getValueAt(row, 3));
		return new Phong(maPhong, loaiPhong, soLuongKhach, trangThai);
	}

	/**
	 * Xóa tất cả các row trong table
	 * 
	 * @param jTable
	 * @param tableModel
	 */
	private void emptyTable(JTable jTable, DefaultTableModel tableModel) {
		while (jTable.getRowCount() > 0)
			tableModel.removeRow(0);
	}

	private void capNhatDanhSachPhongDatTruoc() {
		if (dsPhongCanGop == null || dsPhongDaChon == null)
			return;

		emptyTable(tblPhongCanGop, tableModelPhongCanGop);
		btnChonPhong.setEnabled(false);

		for (Phong phong : dsPhongCanGop) {
			if (!dsPhongDaChon.contains(phong))
				addRowTableCanGop(phong);
		}
	}
}
