package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import components.button.Button;
import components.scrollbarCustom.ScrollBarCustom;
import utils.Utils;

public class ThongKeHoaDon_GUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tableThongKe;

	/**
	 * Create the frame.
	 */
	public ThongKeHoaDon_GUI() {
		setBackground(Utils.secondaryColor);
		setBounds(0, 0, 1086, 508);
		setLayout(null);

		JPanel pnlChonNTN = new JPanel();
		pnlChonNTN.setBorder(new LineBorder(Utils.secondaryColor));
		pnlChonNTN.setForeground(new Color(0, 0, 0));
		pnlChonNTN.setBounds(0, 0, 1096, 175);
		pnlChonNTN.setBackground(Utils.secondaryColor);
		this.add(pnlChonNTN);
		pnlChonNTN.setLayout(null);

		JTextPane lblTKTheoTime = new JTextPane();
		lblTKTheoTime.setBounds(133, 22, 233, 33);
		lblTKTheoTime.setText("Thống kê hoá đơn theo:");
		lblTKTheoTime.setForeground(Color.GRAY);
		lblTKTheoTime.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblTKTheoTime.setBackground(new Color(203, 239, 255));
		pnlChonNTN.add(lblTKTheoTime);

		Button btnNgay = new Button("Ngày");
		btnNgay.setBounds(424, 22, 102, 39);
		pnlChonNTN.add(btnNgay);
		btnNgay.setForeground(Color.GRAY);
		btnNgay.setBackground(Color.WHITE);
		btnNgay.setRadius(8);
		btnNgay.setColor(new Color(255, 255, 255));
		btnNgay.setFont(new Font("Segoe UI", Font.BOLD, 20));

		Button btnThang = new Button("Tháng");
		btnThang.setFocusable(false);
		btnThang.setBounds(574, 22, 102, 39);
		btnThang.setText("Tháng");
		btnThang.setRadius(8);
		btnThang.setForeground(Color.WHITE);
		btnThang.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnThang.setColor(new Color(140, 177, 180));
		btnThang.setColorOver(new Color(140, 177, 180));
		btnThang.setColorClick(new Color(140, 177, 180));
		btnThang.setBorderColor(new Color(140, 177, 180));
		btnThang.setBackground(new Color(140, 177, 180));
		pnlChonNTN.add(btnThang);

		Button btnNam = new Button("Ngày");
		btnNam.setBounds(734, 22, 102, 39);
		pnlChonNTN.add(btnNam);
		btnNam.setText("Năm");
		btnNam.setRadius(8);
		btnNam.setForeground(Color.GRAY);
		btnNam.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnNam.setColor(Color.WHITE);
		btnNam.setBackground(Color.WHITE);

		JTextPane lblNgay = new JTextPane();
		lblNgay.setBounds(360, 80, 61, 33);
		pnlChonNTN.add(lblNgay);
		lblNgay.setText("Ngày:");
		lblNgay.setForeground(Color.GRAY);
		lblNgay.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNgay.setBackground(new Color(203, 239, 255));

		JComboBox<String> cboNgay = new JComboBox<String>();
		cboNgay.setBounds(451, 80, 113, 38);
		pnlChonNTN.add(cboNgay);
		cboNgay.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		cboNgay.setBackground(Color.WHITE);

		JTextPane lblThang = new JTextPane();
		lblThang.setBounds(606, 80, 71, 33);
		pnlChonNTN.add(lblThang);
		lblThang.setText("Tháng:");
		lblThang.setForeground(Color.GRAY);
		lblThang.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblThang.setBackground(new Color(203, 239, 255));

		JComboBox<String> cboThang = new JComboBox<String>();
		cboThang.setBounds(706, 80, 113, 38);
		pnlChonNTN.add(cboThang);
		cboThang.setFont(new Font("Segoe UI", Font.PLAIN, 16));

		JTextPane lblNam = new JTextPane();
		lblNam.setBounds(867, 80, 69, 38);
		pnlChonNTN.add(lblNam);
		lblNam.setText("Năm:");
		lblNam.setForeground(Color.GRAY);
		lblNam.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNam.setBackground(new Color(203, 239, 255));

		JComboBox<String> cboNam = new JComboBox<String>();
		cboNam.setBounds(956, 80, 113, 38);
		pnlChonNTN.add(cboNam);
		cboNam.setFont(new Font("Segoe UI", Font.PLAIN, 16));

		Button btnThongKe = new Button("Ngày");
		btnThongKe.setBounds(811, 130, 258, 39);
		pnlChonNTN.add(btnThongKe);
		btnThongKe.setIcon(new ImageIcon("Icon\\statistics.png"));
		btnThongKe.setForeground(Color.WHITE);
		btnThongKe.setText("Thống kê hoá đơn:");
		btnThongKe.setRadius(8);
		btnThongKe.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnThongKe.setColor(new Color(140, 177, 180, 127));
		btnThongKe.setColorClick(new Color(140, 177, 180, 127));
		btnThongKe.setColorOver(new Color(140, 177, 180, 127));
		btnThongKe.setBorderColor(new Color(140, 177, 180));
		btnThongKe.setBackground(new Color(140, 177, 180));

		JPanel pnlThongKe = new JPanel();
		pnlThongKe.setBackground(Utils.secondaryColor);
		pnlThongKe.setBorder(new LineBorder(Utils.secondaryColor));
		pnlThongKe.setBounds(0, 172, 1096, 345);

		this.add(pnlThongKe);
		pnlThongKe.setLayout(null);

		JTextPane lblTitleTable = new JTextPane();
		lblTitleTable.setBounds(40, 0, 221, 33);
		pnlThongKe.add(lblTitleTable);
		lblTitleTable.setText("Hoá đơn trong: 2/2022");
		lblTitleTable.setForeground(Color.GRAY);
		lblTitleTable.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblTitleTable.setBackground(new Color(203, 239, 255));

		JScrollPane scr = new JScrollPane();
		scr.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scr.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scr.setBounds(10, 31, 650, 323);
		scr.setBackground(Utils.primaryColor);
		ScrollBarCustom scp = new ScrollBarCustom();
		scp.setScrollbarColor(new Color(203, 203, 203));
		scr.setVerticalScrollBar(scp);
		pnlThongKe.add(scr);

		tableThongKe = new JTable() {
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
		tableThongKe.setModel(new DefaultTableModel(
				new Object[][] { { "MKH0001", "Xem chi tiết", "MKH001", "Xem chi tiết", "12-02-2022", "400.000VNĐ" }, },
				new String[] { "Mã hoá đơn", "Mã phòng", "Mã KH", "Dịch vụ", "Ngày thuê", "Thành tiền" }));
		tableThongKe.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableThongKe.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableThongKe.getColumnModel().getColumn(2).setPreferredWidth(100);
		tableThongKe.getColumnModel().getColumn(3).setPreferredWidth(100);
		tableThongKe.getColumnModel().getColumn(4).setPreferredWidth(100);
		tableThongKe.getColumnModel().getColumn(5).setPreferredWidth(150);
		// Cam
		tableThongKe.getTableHeader().setBackground(new Color(255, 195, 174));
		// Xanh
		tableThongKe.getTableHeader().setBackground(Utils.primaryColor);
		tableThongKe.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		tableThongKe.setBackground(Utils.secondaryColor);
		tableThongKe.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableThongKe.getTableHeader()
				.setPreferredSize(new Dimension((int) tableThongKe.getTableHeader().getPreferredSize().getWidth(), 36));
		tableThongKe.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tableThongKe.setRowHeight(36);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		tableThongKe.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		scr.setViewportView(tableThongKe);

	}
}
