package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import components.barChart.Chart;
import components.barChart.ModelChart;
import components.button.Button;
import connectDB.ConnectDB;
import utils.Utils;

public class ThongKeDoanhThu_GUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tbl1, tbl2;
	private DefaultTableModel tableModel;
	private JComboBox<String> cboDay, cboMonth, cboYear;
	private Chart chart;

	public ThongKeDoanhThu_GUI() {
		try {
			new ConnectDB().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		setBackground(Utils.secondaryColor);
		setBounds(0, 0, 1086, 508);
		setLayout(null);
		// ================== Bắt đầu phần Button Tính doanh thu

		JPanel pnlRevenueCalculation = new JPanel();
		pnlRevenueCalculation.setBackground(Utils.secondaryColor);
		pnlRevenueCalculation.setBounds(70, 25, 946, 44);
		this.add(pnlRevenueCalculation);
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
		pnlDate.setBounds(70, 111, 946, 44);
		this.add(pnlDate);
		pnlDate.setLayout(null);

		JLabel lblDay = new JLabel("Ngày: ");
		lblDay.setForeground(new Color(146, 146, 146));
		lblDay.setBounds(336, 0, 70, 28);
		lblDay.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		pnlDate.add(lblDay);

		String city[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
		cboDay = new JComboBox<String>(city);
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
		cboMonth = new JComboBox<String>(month);
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
		cboYear = new JComboBox<String>(year);
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
		btnStatisticize.setBounds(813, 190, 205, 44);
		btnStatisticize.setBorderColor(Utils.secondaryColor);
		btnStatisticize.setColorOver(Utils.primaryColor);
		btnStatisticize.setColorClick(Utils.primaryColor);
		btnStatisticize.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnStatisticize.setBorder(new LineBorder(Color.WHITE, 1));
		this.add(btnStatisticize);

		JPanel pnlResult = new JPanel();
		pnlResult.setBackground(Utils.secondaryColor);
		pnlResult.setBounds(70, 235, 946, 44);
		this.add(pnlResult);
		pnlResult.setLayout(null);

		JLabel lblResult = new JLabel("Doanh thu trong: 01/2022");
		lblResult.setBounds(0, 0, 299, 28);
		lblResult.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblResult.setForeground(new Color(146, 146, 146));
		pnlResult.add(lblResult);

		JScrollPane scr1 = new JScrollPane();
		scr1.setBounds(70, 285, 445, 75);
		scr1.setBackground(Utils.primaryColor);
		this.add(scr1);

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
		scr2.setBounds(70, 365, 445, 75);
		scr2.setBackground(Utils.primaryColor);
		this.add(scr2);

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
		chart.setBounds(550, 285, 465, 250);
		this.add(chart);

		chart.start();
	}

	@SuppressWarnings("unused")
	private void setEmptyTable() {
		while (tbl1.getRowCount() > 0)
			tableModel.removeRow(0);
	}
}
