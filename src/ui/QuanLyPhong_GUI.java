package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
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
import components.comboBox.ComboBox;
import components.scrollbarCustom.ScrollBarCustom;
import utils.Utils;

public class QuanLyPhong_GUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtTimKiem;
	private JTable tbl;
	private DefaultTableModel tableModel;

	/**
	 * Create the panel.
	 * @param _this 
	 */
	public QuanLyPhong_GUI(Main _this) {
		setBackground(Utils.secondaryColor);
		setBounds(0, 0, 1086, 508);
		setLayout(null);

		JPanel pnlHeader = new JPanel();
		pnlHeader.setBounds(0, 6, 1086, 64);
		pnlHeader.setBackground(Utils.secondaryColor);
		add(pnlHeader);
		pnlHeader.setLayout(null);

		JPanel pnlTimKiem = new JPanel();
		pnlTimKiem.setBounds(32, 9, 611, 46);
		pnlTimKiem.setBackground(Utils.secondaryColor);
		pnlHeader.add(pnlTimKiem);
		pnlTimKiem.setLayout(null);

		JLabel lblTimKiem = new JLabel("Tìm kiếm phòng");
		lblTimKiem.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
		lblTimKiem.setFont(new Font("Segoe UI", Font.BOLD, 25));
		lblTimKiem.setBounds(0, 1, 195, 43);
		pnlTimKiem.add(lblTimKiem);

		txtTimKiem = new JTextField();
		txtTimKiem.setBorder(new LineBorder(new Color(128, 151, 172), 2, true));
		txtTimKiem.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		txtTimKiem.setBackground(Utils.secondaryColor);
		txtTimKiem.setBounds(211, 1, 244, 43);
		pnlTimKiem.add(txtTimKiem);
		txtTimKiem.setColumns(10);

		Button btnTimKiem = new Button("Tìm kiếm");
		btnTimKiem.setIcon(new ImageIcon("Icon\\search_34x34.png"));
		btnTimKiem.setFocusable(false);
		btnTimKiem.setRadius(9);
		btnTimKiem.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnTimKiem.setBorderColor(Utils.secondaryColor);
		btnTimKiem.setBackground(Utils.getRGBA(140, 177, 180, 0.7f), 0.9f, 0.8f);
		btnTimKiem.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnTimKiem.setForeground(Color.WHITE);
		btnTimKiem.setBounds(471, 0, 140, 47);
		pnlTimKiem.add(btnTimKiem);

		JPanel pnlThoiGian = new JPanel();
		pnlThoiGian.setBackground(Utils.secondaryColor);
		pnlThoiGian.setBounds(854, 0, 200, 64);
		pnlHeader.add(pnlThoiGian);
		pnlThoiGian.setLayout(null);

		JLabel lnlIcon = new JLabel("");
		lnlIcon.setIcon(new ImageIcon("Icon\\clock (1) 1.png"));
		lnlIcon.setBounds(0, 0, 64, 64);
		pnlThoiGian.add(lnlIcon);

		JLabel lblGio = new JLabel("18:07");
		lblGio.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
		lblGio.setHorizontalAlignment(SwingConstants.CENTER);
		lblGio.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblGio.setBounds(64, 1, 136, 21);
		pnlThoiGian.add(lblGio);

		JLabel lblThu = new JLabel("T2");
		lblThu.setHorizontalAlignment(SwingConstants.CENTER);
		lblThu.setForeground(new Color(0, 0, 0, 140));
		lblThu.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblThu.setBounds(64, 22, 136, 21);
		pnlThoiGian.add(lblThu);

		JLabel lblNgay = new JLabel("26-09-2022");
		lblNgay.setHorizontalAlignment(SwingConstants.CENTER);
		lblNgay.setForeground(new Color(0, 0, 0, 140));
		lblNgay.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNgay.setBounds(64, 43, 136, 21);
		pnlThoiGian.add(lblNgay);

		JPanel pnlActions = new JPanel();
		pnlActions.setBackground(Utils.secondaryColor);
		pnlActions.setBounds(0, 90, 1086, 49);
		add(pnlActions);
		pnlActions.setLayout(null);

		Button btnThem = new Button("Thêm");
		btnThem.setIcon(new ImageIcon("Icon\\add 1.png"));
		btnThem.setFocusable(false);
		btnThem.setRadius(9);
		btnThem.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnThem.setBorderColor(Utils.secondaryColor);
		btnThem.setBackground(Utils.getRGBA(140, 177, 180, 0.7f), 0.9f, 0.8f);
		btnThem.setForeground(Color.WHITE);
		btnThem.setFont(new Font("Segoe UI", Font.BOLD, 27));
		btnThem.setBounds(32, -2, 173, 53);
		pnlActions.add(btnThem);

		Button btnSua = new Button("Thêm");
		btnSua.setIcon(new ImageIcon("Icon\\update 1.png"));
		btnSua.setText("Sửa");
		btnSua.setFocusable(false);
		btnSua.setRadius(9);
		btnSua.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnSua.setBorderColor(Utils.secondaryColor);
		btnSua.setBackground(Utils.getRGBA(140, 177, 180, 0.7f), 0.9f, 0.8f);
		btnSua.setForeground(Color.WHITE);
		btnSua.setFont(new Font("Segoe UI", Font.BOLD, 27));
		btnSua.setBounds(221, 0, 173, 53);
		pnlActions.add(btnSua);

		Button btnXoa = new Button("Thêm");
		btnXoa.setText("Xóa");
		btnXoa.setIcon(new ImageIcon("Icon\\download 1.png"));
		btnXoa.setFocusable(false);
		btnXoa.setRadius(9);
		btnXoa.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnXoa.setBorderColor(Utils.secondaryColor);
		btnXoa.setBackground(Utils.getRGBA(140, 177, 180, 0.7f), 0.9f, 0.8f);
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setFont(new Font("Segoe UI", Font.BOLD, 27));
		btnXoa.setBounds(410, 0, 173, 53);
		pnlActions.add(btnXoa);

		ComboBox<String> cmbLoaiPhong = new ComboBox<String>();
		cmbLoaiPhong.setFocusable(false);
		cmbLoaiPhong.setModel(new DefaultComboBoxModel<String>(new String[] { "Loại phòng" }));
		cmbLoaiPhong.setForeground(Color.WHITE);
		cmbLoaiPhong.setBackground(Utils.getRGBA(140, 177, 180, 0.7f));
		cmbLoaiPhong.setFont(new Font("Segoe UI", Font.BOLD, 20));
		cmbLoaiPhong.setBounds(890, 0, 164, 49);
		pnlActions.add(cmbLoaiPhong);

		JScrollPane scr = new JScrollPane();
		scr.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scr.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scr.setBounds(32, 171, 1022, 305);
		scr.setBackground(Utils.primaryColor);
		scr.getViewport().setBackground(Color.WHITE);

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
				return false;
			}

			@Override
			/**
			 * Set màu từng dòng cho Table
			 */
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				if (isRowSelected(row))
					c.setBackground(Utils.getRGBA(96, 96, 96, 0.5f));
				else
					c.setBackground(Color.WHITE);
				return c;
			}
		};
		tbl.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
		tbl.setAutoCreateRowSorter(true);

		tableModel = new DefaultTableModel(new String[] { "Mã phòng", "Loại phòng", "Giá tiền", "Số lượng khách" }, 0);
		tbl.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null }, { null, null, null, null }, { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null }, },
				new String[] { "M\u00E3 ph\u00F2ng", "Lo\u1EA1i ph\u00F2ng", "Gi\u00E1 ti\u1EC1n",
						"S\u1ED1 l\u01B0\u1EE3ng kh\u00E1ch" }));
		tbl.setFocusable(false);
		tbl.getTableHeader().setBackground(Utils.primaryColor);
		tbl.setFont(new Font("Segoe UI", Font.BOLD, 20));
		tbl.setBackground(Color.WHITE);
		tbl.getTableHeader()
				.setPreferredSize(new Dimension((int) tbl.getTableHeader().getPreferredSize().getWidth(), 36));
		tbl.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 20));
		tbl.getTableHeader().setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
		tbl.setRowHeight(36);
		scr.setViewportView(tbl);
	}
}
