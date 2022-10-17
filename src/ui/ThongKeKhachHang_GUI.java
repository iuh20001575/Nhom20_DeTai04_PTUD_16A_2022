package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import components.button.Button;
import components.scrollbarCustom.ScrollBarCustom;
import layouts.DefaultLayout;
import utils.Utils;

public class ThongKeKhachHang_GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel tableModel;
	private JTable tableThongKe;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThongKeKhachHang_GUI frame = new ThongKeKhachHang_GUI();
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
	public ThongKeKhachHang_GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 610);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		DefaultLayout defaultLayout = new DefaultLayout(this, contentPane, "Thống kê khách hàng");
		contentPane = defaultLayout.getJPanel();

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
//		JLabel lblTitle = new JLabel("THỐNG KÊ KHÁCH HÀNG");
//		lblTitle.setBounds(71, 18, 948, 32);
//		lblTitle.setForeground(Color.WHITE);
//		lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
//		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
//		pnlHeader.add(lblTitle);
//		End PnHeader

		JPanel pnlChonNTN = new JPanel();
		pnlChonNTN.setBorder(new LineBorder(Utils.secondaryColor));
		pnlChonNTN.setForeground(new Color(0, 0, 0));
		pnlChonNTN.setBounds(0, 0, 1100, 211);
		pnlChonNTN.setBackground(Utils.secondaryColor);
		contentPane.add(pnlChonNTN);
		pnlChonNTN.setLayout(null);

		JTextPane lblTKKH = new JTextPane();
		lblTKKH.setBounds(73, 85, 262, 33);
		lblTKKH.setText("Thống kê khách hàng theo:");
		lblTKKH.setForeground(Color.GRAY);
		lblTKKH.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblTKKH.setBackground(new Color(203, 239, 255));
		pnlChonNTN.add(lblTKKH);

		Button btnNgay = new Button("Ngày");
		btnNgay.setBounds(358, 150, 102, 39);
		pnlChonNTN.add(btnNgay);
		btnNgay.setForeground(Color.GRAY);
		btnNgay.setBackground(Color.WHITE);
		btnNgay.setRadius(8);
		btnNgay.setColor(new Color(255, 255, 255));
		btnNgay.setFont(new Font("Segoe UI", Font.BOLD, 20));

		Button btnThang = new Button("Tháng");
		btnThang.setBounds(500, 150, 102, 39);
		btnThang.setFocusable(false);
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
		btnNam.setBounds(639, 150, 102, 39);
		pnlChonNTN.add(btnNam);
		btnNam.setText("Năm");
		btnNam.setRadius(8);
		btnNam.setForeground(Color.GRAY);
		btnNam.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnNam.setColor(Color.WHITE);
		btnNam.setBackground(Color.WHITE);

		JTextPane lblNgay = new JTextPane();
		lblNgay.setBounds(358, 85, 61, 33);
		pnlChonNTN.add(lblNgay);
		lblNgay.setText("Ngày:");
		lblNgay.setForeground(Color.GRAY);
		lblNgay.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNgay.setBackground(new Color(203, 239, 255));

		JComboBox cboNgay = new JComboBox();
		cboNgay.setBounds(451, 85, 113, 38);
		pnlChonNTN.add(cboNgay);
		cboNgay.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		cboNgay.setBackground(Color.WHITE);

		JTextPane lblThang = new JTextPane();
		lblThang.setBounds(606, 85, 71, 33);
		pnlChonNTN.add(lblThang);
		lblThang.setText("Tháng:");
		lblThang.setForeground(Color.GRAY);
		lblThang.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblThang.setBackground(new Color(203, 239, 255));

		JComboBox cboThang = new JComboBox();
		cboThang.setBounds(706, 85, 113, 38);
		pnlChonNTN.add(cboThang);
		cboThang.setFont(new Font("Segoe UI", Font.PLAIN, 16));

		JTextPane lblNam = new JTextPane();
		lblNam.setBounds(867, 85, 69, 38);
		pnlChonNTN.add(lblNam);
		lblNam.setText("Năm:");
		lblNam.setForeground(Color.GRAY);
		lblNam.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNam.setBackground(new Color(203, 239, 255));

		JComboBox cboNam = new JComboBox();
		cboNam.setBounds(956, 85, 113, 38);
		pnlChonNTN.add(cboNam);
		cboNam.setFont(new Font("Segoe UI", Font.PLAIN, 16));

		Button btnThongKe = new Button("Ngày");
		btnThongKe.setBounds(771, 150, 298, 39);
		pnlChonNTN.add(btnThongKe);
		btnThongKe.setIcon(new ImageIcon("Icon\\statistics.png"));
		btnThongKe.setForeground(Color.WHITE);
		btnThongKe.setText("Thống kê khách hàng:");
		btnThongKe.setRadius(8);
		btnThongKe.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnThongKe.setColor(new Color(140, 177, 180, 127));
		btnThongKe.setColorClick(new Color(140, 177, 180, 127));
		btnThongKe.setColorOver(new Color(140, 177, 180, 127));
		btnThongKe.setBorderColor(new Color(140, 177, 180));
		btnThongKe.setBackground(new Color(140, 177, 180));

		JPanel pnlThongKe = new JPanel();
		pnlThongKe.setBounds(0, 209, 1100, 364);
		contentPane.add(pnlThongKe);
		pnlThongKe.setBackground(Utils.secondaryColor);
		pnlThongKe.setBorder(new LineBorder(Utils.secondaryColor));
		pnlThongKe.setLayout(null);

		JTextPane lblTitleTable = new JTextPane();
		lblTitleTable.setBounds(33, 0, 366, 33);
		pnlThongKe.add(lblTitleTable);
		lblTitleTable.setText("Thông tin khách hàng trong: 2/2022");
		lblTitleTable.setForeground(Color.GRAY);
		lblTitleTable.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblTitleTable.setBackground(new Color(203, 239, 255));

		JScrollPane scr = new JScrollPane();
		scr.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scr.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scr.setBounds(10, 46, 650, 308);
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
				// TODO Auto-generated method stub
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
				new Object[][] { { "MKH0001", "Nguyễn Phương Anh", "090190002456", "Nữ", "0314595218", "MHD001" }, },
				new String[] { "Mã KH", "Họ tên khách", "CCCD", "Giới tính", "SĐT khách", "Mã hoá đơn" }));
		tableThongKe.getColumnModel().getColumn(0).setPreferredWidth(70);
		tableThongKe.getColumnModel().getColumn(1).setPreferredWidth(190);
		tableThongKe.getColumnModel().getColumn(2).setPreferredWidth(110);
		tableThongKe.getColumnModel().getColumn(3).setPreferredWidth(70);
		tableThongKe.getColumnModel().getColumn(4).setPreferredWidth(100);
		tableThongKe.getColumnModel().getColumn(5).setPreferredWidth(100);
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
