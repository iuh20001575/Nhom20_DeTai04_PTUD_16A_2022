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
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import components.button.Button;
import components.comboBox.ComboBox;
import components.notification.Notification;
import components.scrollbarCustom.ScrollBarCustom;
import dao.DonDatPhong_DAO;
import dao.LoaiPhong_DAO;
import dao.Phong_DAO;
import entity.LoaiPhong;
import entity.Phong;
import utils.Utils;

public class ChuyenPhong_GUI extends JFrame implements ItemListener {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private ChuyenPhong_GUI _this;
	private ComboBox<String> cmbLoaiPhong;
	private ComboBox<String> cmbMaPhong;
	private ComboBox<String> cmbPhongHienTai;
	private ComboBox<String> cmbSoKhach;
	private DonDatPhong_DAO datPhong_DAO;
	private List<LoaiPhong> dsLoaiPhong;
	private final String labelCmbLoaiPhong = "Loại phòng";
	private final String labelCmbMaPhong = "Mã phòng";
	private final String labelCmbSoKhach = "Số khách";
	private LoaiPhong_DAO loaiPhong_DAO;
	private String maPhong;
	private Phong_DAO phong_DAO;
	private JPanel pnlContent;
	private DefaultTableModel tableModel;
	private JTable tbl;

	/**
	 * Create the frame.
	 *
	 * @param quanLyDatPhongGUI
	 * @param glass
	 */
	public ChuyenPhong_GUI(QuanLyDatPhong_GUI quanLyDatPhongGUI, JFrame parentFrame) {
		_this = this;
		loaiPhong_DAO = new LoaiPhong_DAO();
		phong_DAO = new Phong_DAO();
		datPhong_DAO = new DonDatPhong_DAO();

		setType(Type.UTILITY);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		pnlContent = new JPanel();
		pnlContent.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlContent);
		pnlContent.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);

		JPanel pnlContainer = new JPanel();
		pnlContainer.setBackground(Color.WHITE);
		pnlContainer.setBounds(0, 0, 600, 400);
		pnlContent.add(pnlContainer);
		pnlContainer.setLayout(null);

		JPanel pnlHeader = new JPanel();
		pnlHeader.setBackground(Utils.primaryColor);
		pnlHeader.setBounds(0, 0, 600, 50);
		pnlContainer.add(pnlHeader);
		pnlHeader.setLayout(null);

		JLabel lblTitle = new JLabel("Chuyển phòng");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblTitle.setBounds(200, 0, 200, 50);
		pnlHeader.add(lblTitle);

		JPanel pnlPhongHienTai = new JPanel();
		pnlPhongHienTai.setBackground(Color.WHITE);
		pnlPhongHienTai.setBounds(0, 50, 600, 50);
		pnlContainer.add(pnlPhongHienTai);
		pnlPhongHienTai.setLayout(null);

		JLabel lblPhongHienTai = new JLabel("Phòng hiện tại");
		lblPhongHienTai.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblPhongHienTai.setBounds(16, 10, 130, 30);
		pnlPhongHienTai.add(lblPhongHienTai);

		cmbPhongHienTai = new ComboBox<>();
		cmbPhongHienTai.setBackground(Utils.primaryColor);
		cmbPhongHienTai.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		cmbPhongHienTai.setBounds(150, 7, 90, 36);
		pnlPhongHienTai.add(cmbPhongHienTai);

		JLabel lblIconNext = new JLabel("");
		lblIconNext.setIcon(new ImageIcon("Icon\\next_32x32.png"));
		lblIconNext.setBounds(245, 9, 32, 32);
		pnlPhongHienTai.add(lblIconNext);

		JLabel lblMaPhongMoi = new JLabel("");
		lblMaPhongMoi.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblMaPhongMoi.setBounds(289, 7, 70, 36);
		pnlPhongHienTai.add(lblMaPhongMoi);

		JPanel pnlFilter = new JPanel();
		pnlFilter.setBackground(Color.WHITE);
		pnlFilter.setBounds(0, 106, 600, 30);
		pnlContainer.add(pnlFilter);
		pnlFilter.setLayout(null);

		cmbMaPhong = new ComboBox<>();
		cmbMaPhong.setBackground(Utils.primaryColor);
		cmbMaPhong.setModel(new DefaultComboBoxModel<>(new String[] { labelCmbMaPhong }));
		cmbMaPhong.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		cmbMaPhong.setBounds(16, 0, 127, 30);
		pnlFilter.add(cmbMaPhong);

		cmbLoaiPhong = new ComboBox<>();
		cmbLoaiPhong.setBackground(Utils.primaryColor);
		cmbLoaiPhong.setModel(new DefaultComboBoxModel<>(new String[] { labelCmbLoaiPhong }));
		cmbLoaiPhong.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		cmbLoaiPhong.setBounds(163, 0, 147, 30);
		pnlFilter.add(cmbLoaiPhong);

		cmbSoKhach = new ComboBox<>();
		cmbSoKhach.setBackground(Utils.primaryColor);
		cmbSoKhach.setModel(new DefaultComboBoxModel<>(new String[] { labelCmbSoKhach, "5", "10", "20" }));
		cmbSoKhach.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		cmbSoKhach.setBounds(330, 0, 127, 30);
		pnlFilter.add(cmbSoKhach);

		Button btnLamMoi = new Button("Làm mới");
		btnLamMoi.setBorderColor(Color.WHITE);
		btnLamMoi.setRadius(4);
		btnLamMoi.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnLamMoi.setFocusable(false);
		btnLamMoi.setForeground(Color.BLACK);
		btnLamMoi.setColor(Utils.secondaryColor);
		btnLamMoi.setColorOver(Utils.getOpacity(Utils.secondaryColor, 0.8f));
		btnLamMoi.setColorClick(Utils.getOpacity(Utils.secondaryColor, 0.6f));
		btnLamMoi.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnLamMoi.setBounds(477, -2, 107, 34);
		pnlFilter.add(btnLamMoi);

		JPanel pnlFooter = new JPanel();
		pnlFooter.setBackground(Color.WHITE);
		pnlFooter.setBounds(0, 354, 600, 30);
		pnlContainer.add(pnlFooter);
		pnlFooter.setLayout(null);

		Button btnQuayLai = new Button("Quay lại");
		btnQuayLai.setBorderColor(Color.WHITE);
		btnQuayLai.setRadius(4);
		btnQuayLai.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnQuayLai.setFocusable(false);
		btnQuayLai.setForeground(Color.BLACK);
		btnQuayLai.setColor(Utils.secondaryColor);
		btnQuayLai.setColorOver(Utils.getOpacity(Utils.secondaryColor, 0.8f));
		btnQuayLai.setColorClick(Utils.getOpacity(Utils.secondaryColor, 0.6f));
		btnQuayLai.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnQuayLai.setBounds(16, -2, 127, 34);
		pnlFooter.add(btnQuayLai);

		Button btnChuyen = new Button("Chuyển");
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
		btnChuyen.setBounds(457, -2, 127, 34);
		pnlFooter.add(btnChuyen);

//		Table danh sách
		JScrollPane scr = new JScrollPane();
		scr.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scr.setBounds(16, 146, 568, 198);
		scr.setBackground(Utils.primaryColor);
		scr.getViewport().setBackground(Color.WHITE);
		ScrollBarCustom scb = new ScrollBarCustom();
//		Set color scrollbar thumb
		scb.setScrollbarColor(new Color(203, 203, 203));
		scr.setVerticalScrollBar(scb);
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

		tableModel = new DefaultTableModel(
				new String[] { "Mã phòng", "Loại phòng", "Số người", "Giá tiền/ giờ", "Trạng thái" }, 0);
		TableColumnModel tableColumnModel = tbl.getColumnModel();
		JTableHeader tblHeader = tbl.getTableHeader();

		tbl.setModel(tableModel);
		tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbl.setFocusable(false);
		tblHeader.setBackground(Utils.primaryColor);
		tbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tblHeader.setPreferredSize(new Dimension((int) tblHeader.getPreferredSize().getWidth(), 24));
		tblHeader.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl.setRowHeight(24);

		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.RIGHT);
		tableColumnModel.getColumn(2).setCellRenderer(dtcr);
		tableColumnModel.getColumn(3).setCellRenderer(dtcr);
		scr.setViewportView(tbl);

//		Sự kiện window
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				dsLoaiPhong = loaiPhong_DAO.getAllLoaiPhong();
				List<Phong> dsPhong = datPhong_DAO.getPhongDatNgay();
				List<Phong> dsPhongDangThue = phong_DAO.getAllPhongDangThue();

				Utils.emptyTable(tbl);
				cmbPhongHienTai.removeAllItems();
				cmbMaPhong.removeAllItems();
				cmbLoaiPhong.removeAllItems();

				cmbMaPhong.addItem(labelCmbMaPhong);
				cmbLoaiPhong.addItem(labelCmbLoaiPhong);

				dsLoaiPhong.forEach(loaiPhong -> cmbLoaiPhong.addItem(loaiPhong.getTenLoai()));
				dsPhong.forEach(phong -> {
					addRow(phong);
					cmbMaPhong.addItem(phong.getMaPhong());
				});
				dsPhongDangThue.forEach(phong -> cmbPhongHienTai.addItem(phong.getMaPhong()));
				if (maPhong != null)
					cmbPhongHienTai.setSelectedItem(maPhong);

				cmbMaPhong.addItemListener(_this);
				cmbLoaiPhong.addItemListener(_this);
				cmbSoKhach.addItemListener(_this);
			}
		});

//		Sự kiện nút làm mới
		btnLamMoi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cmbMaPhong.removeItemListener(_this);
				cmbLoaiPhong.removeItemListener(_this);
				cmbSoKhach.removeItemListener(_this);

				cmbMaPhong.setSelectedIndex(0);
				cmbLoaiPhong.setSelectedIndex(0);
				cmbSoKhach.setSelectedIndex(0);
				lblMaPhongMoi.setText("");
				btnChuyen.setEnabled(false);

				filterDanhSachPhong();

				cmbMaPhong.addItemListener(_this);
				cmbLoaiPhong.addItemListener(_this);
				cmbSoKhach.addItemListener(_this);
			}
		});

//		Sự kiện JTable
		tbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tbl.getSelectedRow();

				if (row != -1) {
					String maPhong = (String) tableModel.getValueAt(row, 0);

					lblMaPhongMoi.setText(maPhong);
					btnChuyen.setEnabled(true);
				}
			}
		});

		tbl.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent lse) {
				if (!lse.getValueIsAdjusting()) {
					int row = tbl.getSelectedRow();
					if (row == -1)
						btnChuyen.setEnabled(false);
					else {
						String maPhong = (String) tableModel.getValueAt(row, 0);
						btnChuyen.setEnabled(true);
						lblMaPhongMoi.setText(maPhong);
					}
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

//		Sự kiện nút chuyển phòng
		btnChuyen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!btnChuyen.isEnabled())
					return;

				String maPhongCu = (String) cmbPhongHienTai.getSelectedItem();
				String maPhongMoi = lblMaPhongMoi.getText().trim();
				boolean res = datPhong_DAO.chuyenPhong(maPhongCu, maPhongMoi);

				if (res) {
					quanLyDatPhongGUI.capNhatTrangThaiPhong();
					quanLyDatPhongGUI.closeJFrameSub();
					new Notification(parentFrame, components.notification.Notification.Type.SUCCESS,
							"Chuyển phòng thành công").showNotification();
				}
			}
		});
	}

	public ChuyenPhong_GUI(QuanLyDatPhong_GUI quanLyDatPhongGUI, JFrame parentFrame, String maPhong) {
		this(quanLyDatPhongGUI, parentFrame);
		this.maPhong = maPhong;
	}

	private void addRow(List<Phong> list) {
		list.forEach(phong -> addRow(phong));
	}

	private void addRow(Phong phong) {
		LoaiPhong loaiPhong = null;

		for (LoaiPhong lp : dsLoaiPhong) {
			if (lp.equals(phong.getLoaiPhong())) {
				loaiPhong = lp;
				break;
			}
		}

		phong.setLoaiPhong(loaiPhong);

		tableModel.addRow(new String[] { phong.getMaPhong(), loaiPhong.getTenLoai(), phong.getSoLuongKhach() + "",
				Utils.formatTienTe(phong.getGiaTien()), Phong.convertTrangThaiToString(phong.getTrangThai()) });
	}

	private void filterDanhSachPhong() {
		String maPhong = (String) cmbMaPhong.getSelectedItem();
		String loaiPhong = (String) cmbLoaiPhong.getSelectedItem();
		String soKhach = (String) cmbSoKhach.getSelectedItem();

		if (maPhong.equals(labelCmbMaPhong))
			maPhong = "";
		if (loaiPhong != null && loaiPhong.equals(labelCmbLoaiPhong))
			loaiPhong = "";

		List<Phong> dsPhong = datPhong_DAO.getPhongDatNgay(maPhong, loaiPhong, soKhach);
		Utils.emptyTable(tbl);
		addRow(dsPhong);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.DESELECTED)
			return;
		filterDanhSachPhong();
	}
}
