package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import components.button.Button;
import components.scrollbarCustom.ScrollBarCustom;
import utils.Utils;

public class QuanLyPhieuDatPhong_GUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tbl;
	private JTextField txtSoDienThoai;

	/**
	 * Create the frame.
	 */
	public QuanLyPhieuDatPhong_GUI() {
		setBackground(Utils.secondaryColor);
		setBounds(0, 0, 1086, 508);
		setLayout(null);

		JPanel pnlSearch = new JPanel();
		pnlSearch.setBackground(Utils.secondaryColor);
		pnlSearch.setBounds(16, 20, 1054, 36);
		this.add(pnlSearch);
		pnlSearch.setLayout(null);

		JPanel pnlMaPhieuDat = new JPanel();
		pnlMaPhieuDat.setBackground(Utils.secondaryColor);
		pnlMaPhieuDat.setBounds(0, 0, 300, 36);
		pnlSearch.add(pnlMaPhieuDat);
		pnlMaPhieuDat.setLayout(null);

		JLabel lblMaPhieuDat = new JLabel("Mã phiếu đặt");
		lblMaPhieuDat.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		lblMaPhieuDat.setBounds(0, 0, 150, 36);
		pnlMaPhieuDat.add(lblMaPhieuDat);

		JComboBox<String> cboMaPhieuDat = new JComboBox<String>();
		cboMaPhieuDat.setModel(new DefaultComboBoxModel<String>());
		cboMaPhieuDat.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cboMaPhieuDat.setBackground(Utils.secondaryColor);
		cboMaPhieuDat.setBounds(150, 0, 150, 36);
		pnlMaPhieuDat.add(cboMaPhieuDat);

		JPanel pnlSoDienThoai = new JPanel();
		pnlSoDienThoai.setBackground(Utils.secondaryColor);
		pnlSoDienThoai.setBounds(315, 0, 275, 36);
		pnlSearch.add(pnlSoDienThoai);
		pnlSoDienThoai.setLayout(null);

		JLabel lblSoDienThoai = new JLabel("SĐT khách");
		lblSoDienThoai.setBounds(0, 0, 115, 36);
		lblSoDienThoai.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		pnlSoDienThoai.add(lblSoDienThoai);

		JPanel pnlInputSoDienThoai = new JPanel();
		pnlInputSoDienThoai.setBackground(Utils.secondaryColor);
		pnlInputSoDienThoai.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		pnlInputSoDienThoai.setBounds(125, 0, 150, 36);
		pnlSoDienThoai.add(pnlInputSoDienThoai);
		pnlInputSoDienThoai.setLayout(null);

		txtSoDienThoai = new JTextField();
		txtSoDienThoai.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		txtSoDienThoai.setColumns(10);
		txtSoDienThoai.setBorder(null);
		txtSoDienThoai.setBackground(Utils.secondaryColor);
		txtSoDienThoai.setBounds(8, 1, 134, 34);
		pnlInputSoDienThoai.add(txtSoDienThoai);

		JPanel pnlTrangThai = new JPanel();
		pnlTrangThai.setBackground(Utils.secondaryColor);
		pnlTrangThai.setBounds(605, 0, 275, 36);
		pnlSearch.add(pnlTrangThai);
		pnlTrangThai.setLayout(null);

		JLabel lblTrangThai = new JLabel("Trạng thái");
		lblTrangThai.setBounds(0, 0, 115, 36);
		lblTrangThai.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		pnlTrangThai.add(lblTrangThai);

		JComboBox<String> cboTrangThai = new JComboBox<String>();
		cboTrangThai.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cboTrangThai.setBackground(Utils.secondaryColor);
		cboTrangThai.setBounds(125, 0, 150, 36);
		pnlTrangThai.add(cboTrangThai);

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
		pnlSearch.add(btnSearch);

		JPanel pnlActions = new JPanel();
		pnlActions.setBackground(Utils.secondaryColor);
		pnlActions.setBounds(16, 76, 1054, 36);
		this.add(pnlActions);
		pnlActions.setLayout(null);

		Button btnXemPhong = new Button("Xem phòng");
		btnXemPhong.setFocusable(false);
		btnXemPhong.setIcon(new ImageIcon("Icon\\room.png"));
		btnXemPhong.setRadius(4);
		btnXemPhong.setForeground(Color.WHITE);
		btnXemPhong.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnXemPhong.setColorOver(Utils.primaryColor);
		btnXemPhong.setColorClick(new Color(161, 184, 186));
		btnXemPhong.setColor(Utils.primaryColor);
		btnXemPhong.setBorderColor(Utils.secondaryColor);
		btnXemPhong.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnXemPhong.setBounds(0, 0, 200, 36);
		pnlActions.add(btnXemPhong);

		Button btnNhanPhong = new Button("Nhận phòng");
		btnNhanPhong.setRadius(4);
		btnNhanPhong.setIcon(new ImageIcon("Icon\\check-in (1).png"));
		btnNhanPhong.setForeground(Color.WHITE);
		btnNhanPhong.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnNhanPhong.setFocusable(false);
		btnNhanPhong.setColorOver(Utils.primaryColor);
		btnNhanPhong.setColorClick(new Color(161, 184, 186));
		btnNhanPhong.setColor(Utils.primaryColor);
		btnNhanPhong.setBorderColor(Utils.secondaryColor);
		btnNhanPhong.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnNhanPhong.setBounds(220, 0, 200, 36);
		pnlActions.add(btnNhanPhong);

		Button btnHuyPhong = new Button("Hủy phòng");
		btnHuyPhong.setRadius(4);
		btnHuyPhong.setIcon(new ImageIcon("Icon\\door.png"));
		btnHuyPhong.setForeground(Color.WHITE);
		btnHuyPhong.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnHuyPhong.setFocusable(false);
		btnHuyPhong.setColorOver(Utils.primaryColor);
		btnHuyPhong.setColorClick(new Color(161, 184, 186));
		btnHuyPhong.setColor(Utils.primaryColor);
		btnHuyPhong.setBorderColor(Utils.secondaryColor);
		btnHuyPhong.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnHuyPhong.setBounds(440, 0, 200, 36);
		pnlActions.add(btnHuyPhong);

		Button btnXuatPDF = new Button("Xuất PDF");
		btnXuatPDF.setRadius(4);
		btnXuatPDF.setIcon(new ImageIcon("Icon\\add-file.png"));
		btnXuatPDF.setForeground(Color.WHITE);
		btnXuatPDF.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnXuatPDF.setFocusable(false);
		btnXuatPDF.setColorOver(Utils.primaryColor);
		btnXuatPDF.setColorClick(new Color(161, 184, 186));
		btnXuatPDF.setColor(Utils.primaryColor);
		btnXuatPDF.setBorderColor(Utils.secondaryColor);
		btnXuatPDF.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnXuatPDF.setBounds(660, 0, 200, 36);
		pnlActions.add(btnXuatPDF);

		JScrollPane scr = new JScrollPane();
		scr.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scr.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scr.setBounds(16, 132, 1054, 366);
		scr.setBackground(Utils.primaryColor);
		ScrollBarCustom scp = new ScrollBarCustom();
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
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				if (row % 2 == 0)
					c.setBackground(Color.WHITE);
				else
					c.setBackground(new Color(232, 232, 232));
				return c;
			}
		};
		tbl.setModel(new DefaultTableModel(new Object[][] {
				{ "MDP0001", "0385489526", "15:00 - 27/09/2022", "18:00 - 29/09/2022", "2 - 02.01, 02.02", "Đang chờ" },
				{ "MDP0001", "0385489526", "15:00 - 27/09/2022", "18:00 - 29/09/2022", "2 - 02.01, 02.02", "Đang chờ" },
				{ "MDP0001", "0385489526", "15:00 - 27/09/2022", "18:00 - 29/09/2022", "2 - 02.01, 02.02", "Đang chờ" },
				{ "MDP0001", "0385489526", "15:00 - 27/09/2022", "18:00 - 29/09/2022", "2 - 02.01, 02.02", "Đang chờ" },
				{ "MDP0001", "0385489526", "15:00 - 27/09/2022", "18:00 - 29/09/2022", "2 - 02.01, 02.02", "Đang chờ" },
				{ "MDP0001", "0385489526", "15:00 - 27/09/2022", "18:00 - 29/09/2022", "2 - 02.01, 02.02", "Đang chờ" },
				{ "MDP0001", "0385489526", "15:00 - 27/09/2022", "18:00 - 29/09/2022", "2 - 02.01, 02.02", "Đang chờ" },
				{ "MDP0001", "0385489526", "15:00 - 27/09/2022", "18:00 - 29/09/2022", "2 - 02.01, 02.02", "Đang chờ" },
				{ "MDP0001", "0385489526", "15:00 - 27/09/2022", "18:00 - 29/09/2022", "2 - 02.01, 02.02", "Đang chờ" },
				{ "MDP0001", "0385489526", "15:00 - 27/09/2022", "18:00 - 29/09/2022", "2 - 02.01, 02.02", "Đang chờ" },
				{ "MDP0001", "0385489526", "15:00 - 27/09/2022", "18:00 - 29/09/2022", "2 - 02.01, 02.02", "Đang chờ" },
				{ "MDP0001", "0385489526", "15:00 - 27/09/2022", "18:00 - 29/09/2022", "2 - 02.01, 02.02", "Đang chờ" },
				{ "MDP0001", "0385489526", "15:00 - 27/09/2022", "18:00 - 29/09/2022", "2 - 02.01, 02.02", "Đang chờ" },
				{ "MDP0001", "0385489526", "15:00 - 27/09/2022", "18:00 - 29/09/2022", "2 - 02.01, 02.02", "Đang chờ" },
				{ "MDP0001", "0385489526", "15:00 - 27/09/2022", "18:00 - 29/09/2022", "2 - 02.01, 02.02", "Đang chờ" },
				{ "MDP0001", "0385489526", "15:00 - 27/09/2022", "18:00 - 29/09/2022", "2 - 02.01, 02.02", "Đang chờ" },
				{ "MDP0001", "0385489526", "15:00 - 27/09/2022", "18:00 - 29/09/2022", "2 - 02.01, 02.02", "Đang chờ" },
				{ "MDP0001", "0385489526", "15:00 - 27/09/2022", "18:00 - 29/09/2022", "2 - 02.01, 02.02", "Đang chờ" },
				{ "MDP0001", "0385489526", "15:00 - 27/09/2022", "18:00 - 29/09/2022", "2 - 02.01, 02.02", "Đang chờ" },
				{ "MDP0001", "0385489526", "15:00 - 27/09/2022", "18:00 - 29/09/2022", "2 - 02.01, 02.02", "Đang chờ" },
				{ "MDP0001", "0385489526", "15:00 - 27/09/2022", "18:00 - 29/09/2022", "2 - 02.01, 02.02",
						"Đang chờ" }, },
				new String[] { "Mã phiếu đặt", "SĐT khách", "Thời gian lập phiếu", "Thời gian nhận phòng", "Phòng",
						"Trạng thái" }));
		tbl.getColumnModel().getColumn(0).setPreferredWidth(150);
		tbl.getColumnModel().getColumn(1).setPreferredWidth(150);
		tbl.getColumnModel().getColumn(2).setPreferredWidth(200);
		tbl.getColumnModel().getColumn(3).setPreferredWidth(200);
		tbl.getColumnModel().getColumn(4).setPreferredWidth(218);
		tbl.getColumnModel().getColumn(5).setPreferredWidth(125);
//		Cam
		tbl.getTableHeader().setBackground(new Color(255, 195, 174));
//		Xanh
		tbl.getTableHeader().setBackground(Utils.primaryColor);
		tbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl.setBackground(Utils.secondaryColor);
		tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tbl.getTableHeader()
				.setPreferredSize(new Dimension((int) tbl.getTableHeader().getPreferredSize().getWidth(), 36));
		tbl.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl.setRowHeight(36);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		tbl.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tbl.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		tbl.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		tbl.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		tbl.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		tbl.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
		scr.setViewportView(tbl);
	}
}
