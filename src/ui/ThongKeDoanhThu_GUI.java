package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import components.barChart.Chart;
import components.barChart.ModelChart;
import components.button.Button;
import components.controlPanel.ControlPanel;
import connectDB.ConnectDB;
import utils.StackFrame;
import utils.Utils;

public class ThongKeDoanhThu_GUI extends JFrame implements WindowListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tbl1, tbl2;
	private DefaultTableModel tableModel;
	private JComboBox<String> cboDay, cboMonth, cboYear;
	private ControlPanel pnlControl;
	private Chart chart;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThongKeDoanhThu_GUI frame = new ThongKeDoanhThu_GUI();
					StackFrame.push(frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ThongKeDoanhThu_GUI() {
		JFrame _this = this;

		try {
			new ConnectDB().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}

//		DefaultLayout defaultLayout = new DefaultLayout(this, contentPane, "Quản lý nhân viên");
//		contentPane = defaultLayout.getJPanel();

		setTitle("Quản lý nhân viên");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1100, 610);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setForeground(Color.GRAY);
		contentPane.setBackground(Utils.secondaryColor);
		setContentPane(contentPane);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(null);

		// ================== Bắt đầu phần Header
		JPanel pnlHeader = new JPanel();
		pnlHeader.setBackground(Utils.primaryColor);
		pnlHeader.setBounds(0, 0, 1086, 65);
		contentPane.add(pnlHeader);
		pnlHeader.setLayout(null);

		Button btnMenu = new Button("|||");
		btnMenu.setBounds(24, 20, 40, 40);
		btnMenu.setForeground(Utils.primaryColor);
		btnMenu.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		btnMenu.setBorder(BorderFactory.createEmptyBorder());
		btnMenu.setBackground(Color.WHITE);
		btnMenu.setBorderColor(Utils.primaryColor);
		btnMenu.setRadius(8);
		btnMenu.setFocusable(false);
		pnlHeader.add(btnMenu);

		JLabel lblTitle = new JLabel("THỐNG KÊ DOANH THU");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setBounds(80, 22, 948, 32);
		lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
		pnlHeader.add(lblTitle);

		Button btnBack = new Button();
		btnBack.setFocusable(false);
		btnBack.setIcon(new ImageIcon("Icon\\back 1.png"));
		btnBack.setColor(Utils.primaryColor);
		btnBack.setColorOver(Utils.primaryColor);
		btnBack.setColorClick(Utils.primaryColor);
		btnBack.setBorderColor(Utils.primaryColor);
		btnBack.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnBack.setBounds(954, 1, 62, 62);
		pnlHeader.add(btnBack);

		// ================== Kết thúc phần Header
		// ================== Bắt đầu phần Button Tính doanh thu

		JPanel pnlRevenueCalculation = new JPanel();
		pnlRevenueCalculation.setBackground(Utils.secondaryColor);
		pnlRevenueCalculation.setBounds(70, 90, 946, 44);
		contentPane.add(pnlRevenueCalculation);
		pnlRevenueCalculation.setLayout(null);

		JLabel lblRevenueCalculation = new JLabel("Tính doanh thu theo:");
		lblRevenueCalculation.setBounds(0, 0, 299, 28);
		lblRevenueCalculation.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblRevenueCalculation.setForeground(new Color(146, 146, 146));
		pnlRevenueCalculation.add(lblRevenueCalculation);

		Button btnDay = new Button("Ngày");
		btnDay.setFocusable(false);

		btnDay.setForeground(Color.GRAY);
		btnDay.setColor(Color.WHITE);
		btnDay.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnDay.setBounds(500, 0, 118, 44);
		btnDay.setColorOver(Utils.primaryColor);
		btnDay.setColorClick(Utils.primaryColor);
		btnDay.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnDay.setBorder(new LineBorder(new Color(146, 146, 146), 1));
		btnDay.setRadius(5);
		pnlRevenueCalculation.add(btnDay);

		Button btnMonth = new Button("Tháng");
		btnMonth.setFocusable(false);
		btnMonth.setRadius(5);
		btnMonth.setForeground(Color.GRAY);
		btnMonth.setColor(Color.WHITE);
		btnMonth.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnMonth.setBounds(658, 0, 118, 44);
		btnMonth.setColorOver(Utils.primaryColor);
		btnMonth.setColorClick(Utils.primaryColor);
		btnMonth.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnMonth.setBorder(new LineBorder(new Color(146, 146, 146), 1));
		pnlRevenueCalculation.add(btnMonth);

		Button btnYear = new Button("Năm");
		btnYear.setFocusable(false);
		btnYear.setRadius(5);
		btnYear.setForeground(Color.GRAY);
		btnYear.setColor(Color.WHITE);
		btnYear.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnYear.setBounds(826, 0, 118, 44);
		btnYear.setColorOver(Utils.primaryColor);
		btnYear.setColorClick(Utils.primaryColor);
		btnYear.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnYear.setBorder(new LineBorder(new Color(146, 146, 146), 1));
		pnlRevenueCalculation.add(btnYear);

		// ================== Kết thúc phần Button Tính doanh thu
		// ================== Bắt đầu phần hiển thị ngày, tháng, năm
		JPanel pnlDate = new JPanel();
		pnlDate.setBackground(Utils.secondaryColor);
		pnlDate.setBounds(70, 176, 946, 44);
		contentPane.add(pnlDate);
		pnlDate.setLayout(null);

		JLabel lblDay = new JLabel("Ngày: ");
		lblDay.setForeground(new Color(146, 146, 146));
		lblDay.setBounds(336, 0, 70, 28);
		lblDay.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		pnlDate.add(lblDay);

		String city[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
		cboDay = new JComboBox(city);
		cboDay.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		cboDay.setBackground(Color.WHITE);
		cboDay.setBounds(406, 0, 100, 36);
		cboDay.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlDate.add(cboDay);

		JLabel lblMonth = new JLabel("Tháng: ");
		lblMonth.setForeground(new Color(146, 146, 146));
		lblMonth.setBounds(556, 0, 70, 28);
		lblMonth.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		pnlDate.add(lblMonth);

		String month[] = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
		cboMonth = new JComboBox(month);
		cboMonth.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		cboMonth.setBackground(Color.WHITE);
		cboMonth.setAlignmentX(CENTER_ALIGNMENT);
		cboMonth.setBounds(626, 0, 100, 36);
		cboMonth.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlDate.add(cboMonth);

		JLabel lblYear = new JLabel("Năm: ");
		lblYear.setForeground(new Color(146, 146, 146));
		lblYear.setBounds(776, 0, 70, 28);
		lblYear.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		pnlDate.add(lblYear);

		String year[] = { "2020", "2021", "2022", "2023", "2024" };
		cboYear = new JComboBox(year);
		cboYear.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		cboYear.setBackground(Color.WHITE);
		cboYear.setAlignmentX(CENTER_ALIGNMENT);
		cboYear.setBounds(846, 0, 100, 36);
		cboYear.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlDate.add(cboYear);

		// ================== Kết thúc phần hiển thị ngày, tháng, năm

		Button btnStatisticize = new Button("Thống kê doanh thu");
		btnStatisticize.setFocusable(false);
		btnStatisticize.setRadius(5);
		btnStatisticize.setForeground(Color.WHITE);
		btnStatisticize.setColor(Utils.primaryColor);
		btnStatisticize.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnStatisticize.setBounds(813, 255, 205, 44);
		btnStatisticize.setColorOver(Utils.primaryColor);
		btnStatisticize.setColorClick(Utils.primaryColor);
		btnStatisticize.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnStatisticize.setBorder(new LineBorder(Color.WHITE, 1));
		contentPane.add(btnStatisticize);

		JPanel pnlResult = new JPanel();
		pnlResult.setBackground(Utils.secondaryColor);
		pnlResult.setBounds(70, 300, 946, 44);
		contentPane.add(pnlResult);
		pnlResult.setLayout(null);

		JLabel lblResult = new JLabel("Doanh thu trong: 01/2022");
		lblResult.setBounds(0, 0, 299, 28);
		lblResult.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblResult.setForeground(new Color(146, 146, 146));
		pnlResult.add(lblResult);

		JScrollPane scr1 = new JScrollPane();
		scr1.setBounds(70, 350, 445, 75);
		scr1.setBackground(Utils.primaryColor);
		contentPane.add(scr1);

		tbl1 = new JTable() {
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
		tbl1.setModel(new DefaultTableModel(new Object[][] { { "100.000.000 VNĐ", "110.000.000 VNĐ" } },
				new String[] { "Tiền thu", "Doanh thu ước tính" }));
		tbl1.getColumnModel().getColumn(0).setPreferredWidth(220);
		tbl1.getColumnModel().getColumn(1).setPreferredWidth(222);
		tbl1.getTableHeader().setBackground(Utils.primaryColor);
		tbl1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl1.setBackground(Utils.secondaryColor);
		tbl1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tbl1.getTableHeader()
				.setPreferredSize(new Dimension((int) tbl1.getTableHeader().getPreferredSize().getWidth(), 36));
		tbl1.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl1.setRowHeight(36);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		tbl1.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tbl1.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		scr1.setViewportView(tbl1);

		JScrollPane scr2 = new JScrollPane();
		scr2.setBounds(70, 430, 445, 75);
		scr2.setBackground(Utils.primaryColor);
		contentPane.add(scr2);

		tbl2 = new JTable() {
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
		tbl2.setModel(new DefaultTableModel(new Object[][] { { "120", "125" } },
				new String[] { "Số lượng khách hàng", "Yêu cầu phục vụ" }));
		tbl2.getColumnModel().getColumn(0).setPreferredWidth(220);
		tbl2.getColumnModel().getColumn(1).setPreferredWidth(222);
		tbl2.getTableHeader().setBackground(Utils.primaryColor);
		tbl2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl2.setBackground(Utils.secondaryColor);
		tbl2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tbl2.getTableHeader()
				.setPreferredSize(new Dimension((int) tbl2.getTableHeader().getPreferredSize().getWidth(), 36));
		tbl2.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl2.setRowHeight(36);
		DefaultTableCellRenderer centerRenderer2 = new DefaultTableCellRenderer();
		centerRenderer2.setHorizontalAlignment(JLabel.CENTER);
		tbl2.getColumnModel().getColumn(0).setCellRenderer(centerRenderer2);
		tbl2.getColumnModel().getColumn(1).setCellRenderer(centerRenderer2);
		scr2.setViewportView(tbl2);

		chart = new Chart();
		chart.addLegend("Tháng 7", new Color(238, 255, 65));
		chart.addLegend("Tháng 8", new Color(24, 255, 255));
		chart.addLegend("Tháng 9", new Color(105, 240, 174));
		chart.addData(new ModelChart("Hát", new double[] { 10, 0, 0 }));
		chart.addData(new ModelChart("Dịch vụ", new double[] { 50, 40, 90 }));
		chart.addData(new ModelChart("Khác", new double[] { 10, 20, 30 }));
		chart.setBounds(550, 350, 465, 250);
		contentPane.add(chart);
		
		this.addWindowListener(this);
	}

	@Override
	public void windowOpened(WindowEvent e) {
//		pnlControl.setTbl(tbl1);
//		pnlControl.setTbl(tbl2);
		chart.start();
	}

	@Override
	public void windowClosing(WindowEvent e) {

	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	@Override
	public void windowIconified(WindowEvent e) {

	}

	@Override
	public void windowDeiconified(WindowEvent e) {

	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowDeactivated(WindowEvent e) {

	}

	private void setEmptyTable() {
		while (tbl1.getRowCount() > 0)
			tableModel.removeRow(0);
	}
}
