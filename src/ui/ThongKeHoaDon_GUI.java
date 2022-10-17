package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import components.button.Button;
import components.scrollbarCustom.ScrollBarCustom;
import layouts.DefaultLayout;
import utils.Utils;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;



public class ThongKeHoaDon_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableThongKe;
	private DefaultTableModel tableModel;
	private JScrollPane scroll;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThongKeHoaDon_GUI frame = new ThongKeHoaDon_GUI();
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
	public ThongKeHoaDon_GUI() {
		
//		DefaultLayout defaultLayout = new DefaultLayout(this, contentPane, "Thống kê hoá đơn");
//		contentPane = defaultLayout.getJPanel();
		
		setTitle("Thống kê hoá đơn");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1100, 610);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(null);

		JPanel pnlHeader = new JPanel();
		pnlHeader.setBackground(Utils.primaryColor);
		pnlHeader.setBounds(0, 0, 1086, 65);
		contentPane.add(pnlHeader);
		pnlHeader.setLayout(null);

		Button btnMenu = new Button("|||");
		btnMenu.setBounds(23, 16, 38, 38);
		btnMenu.setForeground(Utils.primaryColor);
		btnMenu.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		btnMenu.setBorder(BorderFactory.createEmptyBorder());
		btnMenu.setBackground(Color.WHITE);
		btnMenu.setBorderColor(Utils.primaryColor);
		btnMenu.setRadius(8);
		btnMenu.setFocusable(false);
		pnlHeader.add(btnMenu);

		JLabel lblTitle = new JLabel("THỐNG KÊ HOÁ ĐƠN");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setBounds(76, 17, 948, 32);
		lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
		pnlHeader.add(lblTitle);
//		End default layout
		
		JPanel pnlChonNTN = new JPanel();
		pnlChonNTN.setBorder(new LineBorder(Utils.secondaryColor));
		pnlChonNTN.setForeground(new Color(0, 0, 0));
		pnlChonNTN.setBounds(0, 65, 1096, 175);
		pnlChonNTN.setBackground(Utils.secondaryColor);
		contentPane.add(pnlChonNTN);
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
		btnNgay.setColor(new Color(255,255,255));
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
		
		JComboBox cboNgay = new JComboBox();
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
		
		JComboBox cboThang = new JComboBox();
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
		
		JComboBox cboNam = new JComboBox();
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
		pnlThongKe.setBounds(0, 237, 1096, 345);
		
		contentPane.add(pnlThongKe);
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
		tableThongKe.setModel(new DefaultTableModel(new Object[][]{
			{"MKH0001","Xem chi tiết","MKH001","Xem chi tiết","12-02-2022","400.000VNĐ"},
			},
			new String[] {"Mã hoá đơn","Mã phòng","Mã KH","Dịch vụ","Ngày thuê","Thành tiền"}));
		tableThongKe.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableThongKe.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableThongKe.getColumnModel().getColumn(2).setPreferredWidth(100);
		tableThongKe.getColumnModel().getColumn(3).setPreferredWidth(100);
		tableThongKe.getColumnModel().getColumn(4).setPreferredWidth(100);
		tableThongKe.getColumnModel().getColumn(5).setPreferredWidth(150);
	//	Cam
		tableThongKe.getTableHeader().setBackground(new Color(255, 195, 174));
	//	Xanh
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
