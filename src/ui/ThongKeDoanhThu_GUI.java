package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import components.barChart.Chart;
import components.barChart.ModelChart;
import components.button.Button;
import utils.Utils;

public class ThongKeDoanhThu_GUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel pnlResult, pnlResultTitle, pnlChart;
	private JComboBox<String> cboDay, cboMonth, cboYear;
	private Chart chart;
	private Button btnDay, btnMonth, btnYear;
	private String valueDay = "";
	private String valueMonth = "";
	private String valueYear = "";
	private JLabel lblResDate, lblTongDoanhThuKQ, lblTongTienPhongKQ, lblDoanhThuPhongThuongKQ, lblDoanhThuPhongVIPKQ;

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

		JLabel lblRevenueCalculation = new JLabel("Thống kê doanh thu theo:");
		lblRevenueCalculation.setBounds(0, 0, 299, 28);
		lblRevenueCalculation.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblRevenueCalculation.setForeground(new Color(100, 100, 100));
		pnlRevenueCalculation.add(lblRevenueCalculation);

		btnDay = new Button("Ngày");
		btnDay.setFocusable(false);

		btnDay.setForeground(new Color(100, 100, 100));
		btnDay.setColor(Color.WHITE);
		btnDay.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnDay.setBorderColor(Utils.secondaryColor);
		btnDay.setBounds(500, 0, 118, 44);
		btnDay.setColorOver(Utils.primaryColor);
		btnDay.setColorClick(Utils.primaryColor);
		btnDay.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnDay.setBorder(new LineBorder(Utils.secondaryColor, 1));
		pnlRevenueCalculation.add(btnDay);

		btnDay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pnlChart.setBounds(550, 230, 0, 0);
				pnlResult.setBounds(70, 230, 0, 0);
				pnlResultTitle.setBounds(70, 200, 0, 0);
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
		btnMonth.setForeground(new Color(100, 100, 100));
		btnMonth.setColor(Color.WHITE);
		btnMonth.setBorderColor(Utils.secondaryColor);

		btnMonth.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnMonth.setBounds(658, 0, 118, 44);
		btnMonth.setColorOver(Utils.primaryColor);
		btnMonth.setColorClick(Utils.primaryColor);
		btnMonth.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnMonth.setBorder(new LineBorder(Utils.secondaryColor, 1));
		pnlRevenueCalculation.add(btnMonth);

		btnMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlChart.setBounds(550, 230, 0, 0);
				pnlResult.setBounds(70, 230, 0, 0);
				pnlResultTitle.setBounds(70, 200, 0, 0);
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
		btnYear.setForeground(new Color(100, 100, 100));
		btnYear.setColor(Color.WHITE);
		btnYear.setBorderColor(Utils.secondaryColor);

		btnYear.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnYear.setBounds(826, 0, 118, 44);
		btnYear.setColorOver(Utils.primaryColor);
		btnYear.setColorClick(Utils.primaryColor);
		btnYear.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnYear.setBorder(new LineBorder(Utils.secondaryColor, 1));
		pnlRevenueCalculation.add(btnYear);
		btnYear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlChart.setBounds(550, 230, 0, 0);
				pnlResult.setBounds(70, 230, 0, 0);
				pnlResultTitle.setBounds(70, 200, 0, 0);
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
		pnlDate.setBounds(70, 90, 946, 44);
		this.add(pnlDate);
		pnlDate.setLayout(null);

		JLabel lblDay = new JLabel("Ngày: ");
		lblDay.setForeground(new Color(100, 100, 100));
		lblDay.setBounds(336, 2, 70, 28);
		lblDay.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		pnlDate.add(lblDay);

		String days[] = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15",
				"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
		cboDay = new JComboBox<String>(days);
		cboDay.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		cboDay.setBackground(Color.WHITE);
		cboDay.setBounds(406, 2, 100, 36);
		cboDay.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlDate.add(cboDay);

		cboDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				valueDay = cboDay.getSelectedItem().toString();
			}
		});

		JLabel lblMonth = new JLabel("Tháng: ");
		lblMonth.setForeground(new Color(100, 100, 100));
		lblMonth.setBounds(556, 2, 70, 28);
		lblMonth.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		pnlDate.add(lblMonth);

		String months[] = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
		cboMonth = new JComboBox<String>(months);
		cboMonth.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		cboMonth.setBackground(Color.WHITE);
		cboMonth.setAlignmentX(CENTER_ALIGNMENT);
		cboMonth.setBounds(626, 2, 100, 36);
		cboMonth.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlDate.add(cboMonth);

		cboMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				valueMonth = cboMonth.getSelectedItem().toString();
			}
		});

		JLabel lblYear = new JLabel("Năm: ");
		lblYear.setForeground(new Color(100, 100, 100));
		lblYear.setBounds(776, 2, 70, 28);
		lblYear.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		pnlDate.add(lblYear);

		String years[] = { "2018", "2019", "2020", "2021", "2022" };
		cboYear = new JComboBox<String>(years);
		cboYear.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		cboYear.setBackground(Color.WHITE);
		cboYear.setAlignmentX(CENTER_ALIGNMENT);
		cboYear.setBounds(846, 2, 100, 36);
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
		btnStatisticize.setForeground(Color.WHITE);
		btnStatisticize.setColor(Utils.primaryColor);
		btnStatisticize.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnStatisticize.setBounds(813, 155, 205, 44);
		btnStatisticize.setBorderColor(Utils.secondaryColor);
		btnStatisticize.setColorOver(Utils.primaryColor);
		btnStatisticize.setColorTextOver(Color.WHITE);
		btnStatisticize.setColorTextOut(Color.WHITE);
		btnStatisticize.setColorClick(Utils.primaryColor);
		btnStatisticize.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnStatisticize.setBorder(new LineBorder(Utils.secondaryColor, 1));
		this.add(btnStatisticize);

		pnlResultTitle = new JPanel();
		pnlResultTitle.setBackground(Utils.secondaryColor);

		pnlResultTitle.setBounds(70, 200, 0, 0);
		this.add(pnlResultTitle);
		pnlResultTitle.setLayout(null);

		JLabel lblResult = new JLabel("Doanh thu trong: ");
		lblResult.setBounds(0, 0, 299, 30);

		lblResult.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblResult.setForeground(new Color(100, 100, 100));
		pnlResultTitle.add(lblResult);

		lblResDate = new JLabel("");
		lblResDate.setBounds(170, 0, 299, 28);
		lblResDate.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblResDate.setForeground(new Color(100, 100, 100));
		pnlResultTitle.add(lblResDate);

		pnlChart = new JPanel();
		pnlChart.setBackground(Utils.secondaryColor);
		pnlChart.setBounds(550, 230, 465, 260);
		this.add(pnlChart);
		pnlChart.setLayout(null);

		btnStatisticize.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pnlResult.setBounds(70, 230, 450, 265);
				pnlResultTitle.setBounds(70, 200, 946, 30);

				// Day
				if (valueDay.trim() != "" && valueMonth.trim() != "") {
					lblResDate.setText(valueDay + "/" + valueMonth + "/" + valueYear);

					int randomPhongThuong = ThreadLocalRandom.current().nextInt(1, 15 + 1);
					int randomPhongVIP = ThreadLocalRandom.current().nextInt(1, 15 + 1);
					int randomDichVu = ThreadLocalRandom.current().nextInt(1, 7 + 1);

					lblTongDoanhThuKQ.setText(randomPhongThuong * 100000 + randomPhongVIP * 30 * 100000
							+ randomDichVu * 30 * 100000 + " VNĐ");
					lblTongTienPhongKQ.setText(randomPhongThuong * 100000 + randomPhongVIP * 30 * 100000 + " VNĐ");
					lblDoanhThuPhongThuongKQ.setText(randomPhongThuong * 100000 + " VNĐ");
					lblDoanhThuPhongVIPKQ.setText(randomPhongVIP * 100000 + " VNĐ");
					pnlChart.setBounds(550, 230, 0, 0);

				}
				// Month
				if (valueDay.trim() == "" && valueMonth.trim() != "") {
					pnlChart.setBounds(550, 230, 465, 260);
					lblResDate.setText(valueMonth + "/" + valueYear);

					// chart
					String date1 = "01/" + valueMonth + "/" + valueYear + " - 10/" + valueMonth + "/" + valueYear;
					String date2 = "11/" + valueMonth + "/" + valueYear + " - 20/" + valueMonth + "/" + valueYear;
					String date3 = "21/" + valueMonth + "/" + valueYear + " - 30/" + valueMonth + "/" + valueYear;
					chart = new Chart();
					chart.addLegend("", Utils.primaryColor);
					chart.addLegend("", Utils.primaryColor);
					chart.addLegend("", Utils.primaryColor);
					int randomNum1 = ThreadLocalRandom.current().nextInt(1, 10 + 1);
					int randomNum2 = ThreadLocalRandom.current().nextInt(1, 10 + 1);
					int randomNum3 = ThreadLocalRandom.current().nextInt(1, 10 + 1);
					chart.addData(new ModelChart(date1, new double[] { 0, randomNum1 * 5, 0 }));
					chart.addData(new ModelChart(date2, new double[] { 0, randomNum2 * 5, 0 }));
					chart.addData(new ModelChart(date3, new double[] { 0, randomNum3 * 5, 0 }));
					chart.setBounds(0, 0, 465, 260);
					pnlChart.add(chart);
					chart.start();

					int randomPhongThuong = ThreadLocalRandom.current().nextInt(1, 15 + 1);
					int randomPhongVIP = ThreadLocalRandom.current().nextInt(1, 15 + 1);
					int randomDichVu = ThreadLocalRandom.current().nextInt(1, 7 + 1);
					lblTongDoanhThuKQ.setText(randomPhongThuong * 30 * 100000 + randomPhongVIP * 30 * 100000
							+ randomDichVu * 30 * 100000 + " VNĐ");
					lblTongTienPhongKQ.setText(randomPhongThuong * 30 * 100000 + randomPhongVIP * 30 * 100000 + " VNĐ");
					lblDoanhThuPhongThuongKQ.setText(randomPhongThuong * 30 * 100000 + " VNĐ");
					lblDoanhThuPhongVIPKQ.setText(randomPhongVIP * 30 * 100000 + " VNĐ");

				}

				// Year
				if (valueDay.trim() == "" && valueMonth.trim() == "" && valueYear.trim() != "") {
					pnlChart.setBounds(550, 230, 465, 260);
					lblResDate.setText(valueYear);

					int randomPhongThuong = ThreadLocalRandom.current().nextInt(1, 15 + 1);
					int randomPhongVIP = ThreadLocalRandom.current().nextInt(1, 15 + 1);
					int randomDichVu = ThreadLocalRandom.current().nextInt(1, 7 + 1);

					lblTongDoanhThuKQ.setText(randomPhongThuong * 365 * 100000 + randomPhongVIP * 30 * 100000
							+ randomDichVu * 30 * 100000 + " VNĐ");
					lblTongTienPhongKQ
							.setText(randomPhongThuong * 365 * 100000 + randomPhongVIP * 30 * 100000 + " VNĐ");
					lblDoanhThuPhongThuongKQ.setText(randomPhongThuong * 365 * 100000 + " VNĐ");
					lblDoanhThuPhongVIPKQ.setText(randomPhongVIP * 365 * 100000 + " VNĐ");

					// chart
					String date1 = "01/" + valueYear + " - 04/" + valueYear;
					String date2 = "05/" + valueYear + " - 08/" + valueYear;
					String date3 = "09/" + valueYear + " - 12/" + valueYear;
					chart = new Chart();
					chart.addLegend("", Utils.primaryColor);
					chart.addLegend("", Utils.primaryColor);
					chart.addLegend("", Utils.primaryColor);
					int randomNum1 = ThreadLocalRandom.current().nextInt(1, 10 + 1);
					int randomNum2 = ThreadLocalRandom.current().nextInt(1, 10 + 1);
					int randomNum3 = ThreadLocalRandom.current().nextInt(1, 10 + 1);
					chart.addData(new ModelChart(date1, new double[] { 0, randomNum1 * 5, 0 }));
					chart.addData(new ModelChart(date2, new double[] { 0, randomNum2 * 5, 0 }));
					chart.addData(new ModelChart(date3, new double[] { 0, randomNum3 * 5, 0 }));
					chart.setBounds(0, 0, 465, 260);
					pnlChart.add(chart);
					chart.start();
				}
			}

		});

		pnlResult = new JPanel();
		pnlResult.setBounds(70, 230, 0, 0);
		pnlResult.setBackground(Utils.secondaryColor);
		this.add(pnlResult);
		pnlResult.setLayout(null);

		JLabel lblTongDoanhThu = new JLabel("Tổng doanh thu: ");
		lblTongDoanhThu.setBounds(0, 5, 299, 28);
		lblTongDoanhThu.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblTongDoanhThu.setForeground(new Color(100, 100, 100));
		pnlResult.add(lblTongDoanhThu);

		lblTongDoanhThuKQ = new JLabel("");
		lblTongDoanhThuKQ.setText(valueDay);
		lblTongDoanhThuKQ.setBounds(135, 5, 299, 28);
		lblTongDoanhThuKQ.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTongDoanhThuKQ.setForeground(new Color(100, 100, 100));
		pnlResult.add(lblTongDoanhThuKQ);

		JLabel lblTongTienPhong = new JLabel("Tổng tiền phòng: ");
		lblTongTienPhong.setBounds(0, 35, 299, 28);
		lblTongTienPhong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTongTienPhong.setForeground(new Color(100, 100, 100));
		pnlResult.add(lblTongTienPhong);

		lblTongTienPhongKQ = new JLabel("");
		lblTongTienPhongKQ.setBounds(130, 35, 299, 28);
		lblTongTienPhongKQ.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTongTienPhongKQ.setForeground(new Color(100, 100, 100));
		pnlResult.add(lblTongTienPhongKQ);

		JLabel lblDoanhThuPhongThuong = new JLabel("Doanh thu phòng thường: ");
		lblDoanhThuPhongThuong.setBounds(20, 65, 299, 28);
		lblDoanhThuPhongThuong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblDoanhThuPhongThuong.setForeground(new Color(100, 100, 100));
		pnlResult.add(lblDoanhThuPhongThuong);

		lblDoanhThuPhongThuongKQ = new JLabel("");
		lblDoanhThuPhongThuongKQ.setBounds(215, 65, 299, 28);
		lblDoanhThuPhongThuongKQ.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblDoanhThuPhongThuongKQ.setForeground(new Color(100, 100, 100));
		pnlResult.add(lblDoanhThuPhongThuongKQ);

		JLabel lblDoanhThuPhongVIP = new JLabel("Doanh thu phòng VIP: ");
		lblDoanhThuPhongVIP.setBounds(20, 95, 299, 28);
		lblDoanhThuPhongVIP.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblDoanhThuPhongVIP.setForeground(new Color(100, 100, 100));
		pnlResult.add(lblDoanhThuPhongVIP);

		lblDoanhThuPhongVIPKQ = new JLabel("");
		lblDoanhThuPhongVIPKQ.setBounds(215, 95, 299, 28);
		lblDoanhThuPhongVIPKQ.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblDoanhThuPhongVIPKQ.setForeground(new Color(100, 100, 100));
		pnlResult.add(lblDoanhThuPhongVIPKQ);

		JLabel lblTongTienDichVu = new JLabel("Tổng tiền dịch vụ: ");
		lblTongTienDichVu.setBounds(0, 125, 299, 28);
		lblTongTienDichVu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTongTienDichVu.setForeground(new Color(100, 100, 100));
		pnlResult.add(lblTongTienDichVu);

		JLabel lblTongSoGioHat = new JLabel("Tổng số giờ hát: ");
		lblTongSoGioHat.setBounds(0, 155, 299, 28);
		lblTongSoGioHat.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTongSoGioHat.setForeground(new Color(100, 100, 100));
		pnlResult.add(lblTongSoGioHat);

		JLabel lblTongSoHoaDon = new JLabel("Tổng số hoá đơn: ");
		lblTongSoHoaDon.setBounds(0, 185, 299, 28);
		lblTongSoHoaDon.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblTongSoHoaDon.setForeground(new Color(100, 100, 100));
		pnlResult.add(lblTongSoHoaDon);

		JLabel lblDoanhThuTrungBinh = new JLabel("Doanh thu trung bình: ");
		lblDoanhThuTrungBinh.setBounds(0, 215, 299, 28);
		lblDoanhThuTrungBinh.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblDoanhThuTrungBinh.setForeground(new Color(100, 100, 100));
		pnlResult.add(lblDoanhThuTrungBinh);
	}

}
