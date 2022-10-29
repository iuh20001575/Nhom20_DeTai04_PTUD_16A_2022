package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private Button btnDay, btnMonth, btnYear;
	private String valueDay = "";
	private String valueMonth = "";
	private String valueYear = "";
	private JLabel lblResDate;

	public ThongKeDoanhThu_GUI() {
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
		lblRevenueCalculation.setForeground(new Color(100, 100, 100));
		pnlRevenueCalculation.add(lblRevenueCalculation);

		btnDay = new Button("Ngày");
		btnDay.setFocusable(false);

		btnDay.setForeground(new Color(100, 100, 100));
		btnDay.setColor(Color.WHITE);
		btnDay.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnDay.setBounds(500, 0, 118, 44);
		btnDay.setColorOver(Utils.primaryColor);
		btnDay.setColorClick(Utils.primaryColor);
		btnDay.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnDay.setBorder(new LineBorder(new Color(146, 146, 146), 1));
		btnDay.setRadius(5);
		pnlRevenueCalculation.add(btnDay);

		btnDay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnDay.setColor(Utils.primaryColor);
				btnDay.setForeground(Color.WHITE);
				btnDay.setColorTextOut(Color.WHITE);
				btnYear.setColor(Color.WHITE);
				btnYear.setForeground(new Color(100, 100, 100));
				btnYear.setColorTextOut(new Color(100, 100, 100));
				btnMonth.setColor(Color.WHITE);
				btnMonth.setForeground(new Color(100, 100, 100));
				btnMonth.setColorTextOut(new Color(100, 100, 100));
				cboDay.setEnabled(true);
				cboMonth.setEnabled(true);
				valueDay = cboDay.getSelectedItem().toString();
				valueMonth = cboMonth.getSelectedItem().toString();
				valueYear = cboYear.getSelectedItem().toString();
			}
		});

		btnMonth = new Button("Tháng");
		btnMonth.setFocusable(false);
		btnMonth.setRadius(5);
		btnMonth.setForeground(new Color(100, 100, 100));
		btnMonth.setColor(Color.WHITE);
		btnMonth.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnMonth.setBounds(658, 0, 118, 44);
		btnMonth.setColorOver(Utils.primaryColor);
		btnMonth.setColorClick(Utils.primaryColor);
		btnMonth.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnMonth.setBorder(new LineBorder(new Color(146, 146, 146), 1));
		pnlRevenueCalculation.add(btnMonth);

		btnMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnMonth.setColor(Utils.primaryColor);
				btnMonth.setForeground(Color.WHITE);
				btnMonth.setColorTextOut(Color.WHITE);
				btnYear.setColor(Color.WHITE);
				btnYear.setForeground(new Color(100, 100, 100));
				btnYear.setColorTextOut(new Color(100, 100, 100));
				btnDay.setColor(Color.WHITE);
				btnDay.setForeground(new Color(100, 100, 100));
				btnDay.setColorTextOut(new Color(100, 100, 100));
				cboDay.setEnabled(false);
				cboMonth.setEnabled(true);
				valueDay = "";
				valueMonth = cboMonth.getSelectedItem().toString();
				valueYear = cboYear.getSelectedItem().toString();

			}
		});

		btnYear = new Button("Năm");
		btnYear.setFocusable(false);
		btnYear.setRadius(5);
		btnYear.setForeground(new Color(100, 100, 100));
		btnYear.setColor(Color.WHITE);
		btnYear.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnYear.setBounds(826, 0, 118, 44);
		btnYear.setColorOver(Utils.primaryColor);
		btnYear.setColorClick(Utils.primaryColor);
		btnYear.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnYear.setBorder(new LineBorder(new Color(146, 146, 146), 1));
		pnlRevenueCalculation.add(btnYear);

		btnYear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnYear.setColor(Utils.primaryColor);
				btnYear.setForeground(Color.WHITE);
				btnYear.setColorTextOut(Color.WHITE);
				btnMonth.setColor(Color.WHITE);
				btnMonth.setForeground(new Color(100, 100, 100));
				btnMonth.setColorTextOut(new Color(100, 100, 100));
				btnDay.setColor(Color.WHITE);
				btnDay.setForeground(new Color(100, 100, 100));
				btnDay.setColorTextOut(new Color(100, 100, 100));
				cboDay.setEnabled(false);
				cboMonth.setEnabled(false);
				valueDay = "";
				valueMonth = "";
				valueYear = cboYear.getSelectedItem().toString();

			}
		});

		// ================== Kết thúc phần Button Tính doanh thu
		// ================== Bắt đầu phần hiển thị ngày, tháng, năm
		JPanel pnlDate = new JPanel();
		pnlDate.setBackground(Utils.secondaryColor);
		pnlDate.setBounds(70, 111, 946, 44);
		this.add(pnlDate);
		pnlDate.setLayout(null);

		JLabel lblDay = new JLabel("Ngày: ");
		lblDay.setForeground(new Color(100, 100, 100));
		lblDay.setBounds(336, 0, 70, 28);
		lblDay.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		pnlDate.add(lblDay);

		String days[] = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15",
				"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
		cboDay = new JComboBox<String>(days);
		cboDay.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		cboDay.setBackground(Color.WHITE);
		cboDay.setBounds(406, 0, 100, 36);
		cboDay.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlDate.add(cboDay);

		cboDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				valueDay = cboDay.getSelectedItem().toString();
			}
		});

		JLabel lblMonth = new JLabel("Tháng: ");
		lblMonth.setForeground(new Color(100, 100, 100));
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

		cboMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				valueMonth = cboMonth.getSelectedItem().toString();
			}
		});

		JLabel lblYear = new JLabel("Năm: ");
		lblYear.setForeground(new Color(100, 100, 100));
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

		cboYear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				valueYear = cboYear.getSelectedItem().toString();
			}
		});

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
		btnStatisticize.setColorTextOver(Color.WHITE);
		btnStatisticize.setColorTextOut(Color.WHITE);
		btnStatisticize.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnStatisticize.setBorder(new LineBorder(Color.WHITE, 1));
		this.add(btnStatisticize);

		JPanel pnlResult = new JPanel();
		pnlResult.setBackground(Utils.secondaryColor);
		pnlResult.setBounds(70, 235, 946, 44);
		this.add(pnlResult);
		pnlResult.setLayout(null);

		JLabel lblResult = new JLabel("Doanh thu trong: ");
		lblResult.setBounds(0, 0, 299, 28);
		lblResult.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblResult.setForeground(new Color(100, 100, 100));
		pnlResult.add(lblResult);

		lblResDate = new JLabel("");
		lblResDate.setBounds(170, 0, 299, 28);
		lblResDate.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblResDate.setForeground(new Color(100, 100, 100));
		pnlResult.add(lblResDate);

		btnStatisticize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (valueDay.trim() == "" && valueMonth.trim() != "") {
					lblResDate.setText(valueMonth + "/" + valueYear);
				} else if (valueDay.trim() == "" && valueMonth.trim() == "") {
					lblResDate.setText(valueYear);
				} else
					lblResDate.setText(valueDay + "/" + valueMonth + "/" + valueYear);
			}
		});

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
		chart.addLegend("", new Color(149, 166, 248));
		chart.addLegend("", new Color(149, 166, 248));
		chart.addLegend("", new Color(149, 166, 248));
		chart.addData(new ModelChart("01/01/2022 - 10/01/2022", new double[] { 0, 10, 0 }));
		chart.addData(new ModelChart("11/01/2022 - 20/01/2022", new double[] { 0, 40, 0 }));
		chart.addData(new ModelChart("21/01/2022 - 30/01/2022", new double[] { 0, 20, 0 }));
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
