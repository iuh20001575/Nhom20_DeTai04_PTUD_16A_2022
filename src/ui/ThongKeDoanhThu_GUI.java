package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import components.barChart.Chart;
import components.barChart.ModelChart;
import components.button.Button;
import components.panelRound.PanelRound;
import utils.Utils;

public class ThongKeDoanhThu_GUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private PanelRound pnlResultContainer, pnlChart;
	private JComboBox<String> cboDay, cboMonth, cboYear;
	private Chart chart;
	private Button btnDay, btnMonth, btnYear;
	private String valueDay="";
	private String valueMonth="";
	private String valueYear="";
	private JLabel lblResDate, lblTongDoanhThuKQ, lblTongTienPhongKQ, 
	lblDoanhThuPhongThuongKQ, lblDoanhThuPhongVIPKQ, lblTongTienDVKQ, 
	lblTongSoGHKQ, lblTongSoHDKQ, lblDoanhThuTrungBinhKQ, lblResult;

	public ThongKeDoanhThu_GUI() {
		setBackground(new Color(242, 246, 252));
		setBounds(0, 0, Utils.getScreenWidth(), Utils.getBodyHeight());
		setLayout(null);

		// ==================  Bắt đầu phần Button Tính doanh thu

		PanelRound pnlContainerAction = new PanelRound();
		pnlContainerAction.setBackground(Color.WHITE);
		pnlContainerAction.setBounds(90, 20, Utils.getScreenWidth()-195, 145);
		pnlContainerAction.setRoundBottomRight(20);
		pnlContainerAction.setRoundTopLeft(20);
		pnlContainerAction.setRoundTopRight(20);
		pnlContainerAction.setRoundBottomLeft(20);
		this.add(pnlContainerAction);
		pnlContainerAction.setLayout(null);


		JPanel pnlRevenueCalculation = new JPanel();
		pnlRevenueCalculation.setBackground(Color.WHITE);
		pnlRevenueCalculation.setBounds(20, 15, Utils.getScreenWidth()-235, 46);
		pnlContainerAction.add(pnlRevenueCalculation);
		pnlRevenueCalculation.setLayout(null);

		JLabel lblRevenueCalculation = new JLabel("Thống kê doanh thu theo:");
		lblRevenueCalculation.setBounds(0, 5, 299, 28);
		lblRevenueCalculation.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblRevenueCalculation.setForeground(new Color(100, 100, 100));
		pnlRevenueCalculation.add(lblRevenueCalculation);

		btnDay = new Button("Ngày");
		btnDay.setFocusable(false);

		btnDay.setForeground(new Color(100, 100, 100));
		btnDay.setColor(new Color(242, 246, 252));
		btnDay.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnDay.setBounds(541, 1, 118, 44);
		btnDay.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnDay.setBorderColor(new Color(242, 246, 252));
		btnDay.setColorOver(new Color(242, 246, 252));
		btnDay.setColorClick(Utils.primaryColor);
		btnDay.setRadius(10);
		pnlRevenueCalculation.add(btnDay);

		btnDay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pnlChart.setBounds((Utils.getScreenWidth()-235)/2 + 100, 180, 0, 0);
				pnlResultContainer.setBounds(90, 180, 0, 0);

				btnDay.setColor(Utils.primaryColor);
				btnDay.setBorderColor(Utils.primaryColor);
				btnDay.setColorOver(Utils.primaryColor);
				btnDay.setForeground(Color.WHITE);

				btnMonth.setColor(new Color(242, 246, 252));
				btnMonth.setForeground(new Color(100, 100, 100));
				btnMonth.setColorTextOut(new Color(100, 100, 100));
				btnMonth.setBorderColor(new Color(242, 246, 252));
				btnMonth.setColorOver(new Color(242, 246, 252));

				btnYear.setColor(new Color(242, 246, 252));
				btnYear.setForeground(new Color(100, 100, 100));
				btnYear.setColorTextOut(new Color(100, 100, 100));
				btnYear.setBorderColor(new Color(242, 246, 252));
				btnYear.setColorOver(new Color(242, 246, 252));

				cboDay.setEnabled(true);
				cboMonth.setEnabled(true);
				valueDay = cboDay.getSelectedItem().toString();
				valueMonth = cboMonth.getSelectedItem().toString();
				valueYear = cboYear.getSelectedItem().toString();
			}
		});

		btnMonth = new Button("Tháng");
		btnMonth.setFocusable(false);

		btnMonth.setForeground(Color.WHITE);
		btnMonth.setColor(Utils.primaryColor);
		btnMonth.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnMonth.setBounds(719, 1, 118, 44);
		btnMonth.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnMonth.setBorderColor(Utils.primaryColor);
		btnMonth.setColorOver(Utils.primaryColor);
		btnMonth.setColorClick(Utils.primaryColor);
		btnMonth.setRadius(10);
		pnlRevenueCalculation.add(btnMonth);

		btnMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlChart.setBounds((Utils.getScreenWidth()-235)/2 + 100, 180, 0, 0);
				pnlResultContainer.setBounds(90, 180, 0, 0);

				btnMonth.setColor(Utils.primaryColor);
				btnMonth.setBorderColor(Utils.primaryColor);
				btnMonth.setColorOver(Utils.primaryColor);
				btnMonth.setForeground(Color.WHITE);

				btnYear.setColor(new Color(242, 246, 252));
				btnYear.setForeground(new Color(100, 100, 100));
				btnYear.setColorTextOut(new Color(100, 100, 100));
				btnYear.setBorderColor(new Color(242, 246, 252));
				btnYear.setColorOver(new Color(242, 246, 252));

				btnDay.setColor(new Color(242, 246, 252));
				btnDay.setForeground(new Color(100, 100, 100));
				btnDay.setColorTextOut(new Color(100, 100, 100));
				btnDay.setBorderColor(new Color(242, 246, 252));
				btnDay.setColorOver(new Color(242, 246, 252));

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
		btnYear.setColor(new Color(242, 246, 252));
		btnYear.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnYear.setBounds(897, 1, 118, 44);
		btnYear.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnYear.setBorderColor(new Color(242, 246, 252));
		btnYear.setColorOver(Utils.primaryColor);
		btnYear.setColorClick(Utils.primaryColor);
		btnYear.setRadius(10);
		pnlRevenueCalculation.add(btnYear);

		btnYear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlChart.setBounds((Utils.getScreenWidth()-235)/2 + 100, 180, 0, 0);
				pnlResultContainer.setBounds(90, 180, 0, 0);

				btnYear.setColor(Utils.primaryColor);
				btnYear.setBorderColor(Utils.primaryColor);
				btnYear.setColorOver(Utils.primaryColor);
				btnYear.setForeground(Color.WHITE);

				btnMonth.setColor(new Color(242, 246, 252));
				btnMonth.setForeground(new Color(100, 100, 100));
				btnMonth.setColorTextOut(new Color(100, 100, 100));
				btnMonth.setBorderColor(new Color(242, 246, 252));
				btnMonth.setColorOver(new Color(242, 246, 252));

				btnDay.setColor(new Color(242, 246, 252));
				btnDay.setForeground(new Color(100, 100, 100));
				btnDay.setColorTextOut(new Color(100, 100, 100));
				btnDay.setBorderColor(new Color(242, 246, 252));
				btnDay.setColorOver(new Color(242, 246, 252));

				cboDay.setEnabled(false);
				cboMonth.setEnabled(false);
				valueDay = "";
				valueMonth = "";
				valueYear = cboYear.getSelectedItem().toString();

			}
		});

		// ==================  Kết thúc phần Button Tính doanh thu
		// ==================  Bắt đầu phần hiển thị ngày, tháng, năm
		JPanel pnlDate = new JPanel();
		pnlDate.setBackground(Color.WHITE);
		pnlDate.setBounds(20, 80, Utils.getScreenWidth()-235, 46);
		pnlContainerAction.add(pnlDate);
		pnlDate.setLayout(null);

		JLabel lblDay = new JLabel("Ngày: ");
		lblDay.setForeground(new Color(100, 100, 100));
		lblDay.setBounds(0, 5, 70, 28);
		lblDay.setFont(new Font("Segoe UI", Font.BOLD, 16));
		pnlDate.add(lblDay);

		String days[] = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
				"11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
				"21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
		cboDay = new JComboBox<String>(days);
		cboDay.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		cboDay.setBackground(Color.WHITE);
		cboDay.setBounds(70, 4, 100, 40);
		cboDay.setBorder(new EmptyBorder(0, 0, 0, 0));
		cboDay.setEnabled(false);
		pnlDate.add(cboDay);

		cboDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				valueDay = cboDay.getSelectedItem().toString();
			}
		});

		JLabel lblMonth = new JLabel("Tháng: ");
		lblMonth.setForeground(new Color(100, 100, 100));
		lblMonth.setBounds(240, 5, 70, 28);
		lblMonth.setFont(new Font("Segoe UI", Font.BOLD, 16));
		pnlDate.add(lblMonth);

		String months[] = {"01","02", "03", "04", "05", "06","07", "08", "09", "10", "11", "12"};
		cboMonth = new JComboBox<String>(months);
		cboMonth.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		cboMonth.setBackground(Color.WHITE);
		cboMonth.setAlignmentX(CENTER_ALIGNMENT);
		cboMonth.setBounds(310, 4, 100, 40);
		cboMonth.setBorder(new EmptyBorder(0, 0, 0, 0));
		cboMonth.setSelectedItem("11");
		pnlDate.add(cboMonth);

		cboMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				valueMonth = cboMonth.getSelectedItem().toString();
			}
		});

		JLabel lblYear = new JLabel("Năm: ");
		lblYear.setForeground(new Color(100, 100, 100));
		lblYear.setBounds(480, 5, 70, 28);
		lblYear.setFont(new Font("Segoe UI", Font.BOLD, 16));
		pnlDate.add(lblYear);

		String years[] = {"2018","2019", "2020", "2021", "2022"};
		cboYear = new JComboBox<String>(years);
		cboYear.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		cboYear.setBackground(Color.WHITE);
		cboYear.setAlignmentX(CENTER_ALIGNMENT);
		cboYear.setBounds(550, 4, 100, 40);
		cboYear.setBorder(new EmptyBorder(0, 0, 0, 0));
		cboYear.setSelectedItem("2022");
		pnlDate.add(cboYear);

		cboYear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				valueYear = cboYear.getSelectedItem().toString();
			}
		});

		// ==================  Kết thúc phần hiển thị ngày, tháng, năm

		Button btnStatisticize = new Button("Thống kê doanh thu");
		btnStatisticize.setFocusable(false);
		btnStatisticize.setForeground(Color.WHITE);
		btnStatisticize.setColor(Utils.primaryColor);
		btnStatisticize.setBorderColor(Utils.primaryColor);
		btnStatisticize.setRadius(10);
		btnStatisticize.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnStatisticize.setBounds(810, 1, 205, 44);
		btnStatisticize.setColorOver(Utils.primaryColor);
		btnStatisticize.setColorTextOver(Color.WHITE);
		btnStatisticize.setColorTextOut(Color.WHITE);
		btnStatisticize.setColorClick(Utils.primaryColor);
		btnStatisticize.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlDate.add(btnStatisticize);

		btnStatisticize.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pnlResultContainer.setBounds(90, 180, (Utils.getScreenWidth()-235)/2 - 10, Utils.getBodyHeight() - 230);

				// Day
				if (valueDay.trim()!="" && valueMonth.trim()!="") {
					lblResDate.setText(valueDay + "/" + valueMonth +"/" + valueYear);
					lblResult.setText("Doanh thu trong: ");

					// doanh thu
					int randomPhongThuong = ThreadLocalRandom.current().nextInt(1, 15 + 1);
					int randomPhongVIP= ThreadLocalRandom.current().nextInt(1, 15 + 1);
					int randomDichVu= ThreadLocalRandom.current().nextInt(1, 7 + 1);
					int randomGioHat = ThreadLocalRandom.current().nextInt(1, 6 + 1);
					int randomHoaDon = ThreadLocalRandom.current().nextInt(1, 10 + 1);

					lblTongDoanhThuKQ.setText(randomPhongThuong*100000 + randomPhongVIP*100000+ randomDichVu*100000+ " VNĐ");
					lblTongTienPhongKQ.setText(randomPhongThuong*100000 + randomPhongVIP*100000+ " VNĐ");
					lblDoanhThuPhongThuongKQ.setText(randomPhongThuong*100000+" VNĐ");
					lblDoanhThuPhongVIPKQ.setText(randomPhongVIP*100000+" VNĐ");
					lblTongTienDVKQ.setText(randomDichVu*100000+" VNĐ");
					lblTongSoGHKQ.setText(randomGioHat+" giờ");
					lblTongSoHDKQ.setText(randomHoaDon +"");
					lblDoanhThuTrungBinhKQ.setText((randomPhongThuong*100000 + randomPhongVIP*100000+ randomDichVu*100000)/randomHoaDon +" VNĐ/ngày");
					
					// chart
					pnlChart.setBounds((Utils.getScreenWidth()-235)/2 + 100, 180, 0, 0);

				}
				// Month
				if(valueDay.trim()=="" && valueMonth.trim()!="")
				{
					pnlChart.setBounds((Utils.getScreenWidth()-235)/2 + 100, 180, (Utils.getScreenWidth()-235)/2 + 30, Utils.getBodyHeight() - 230);
					lblResDate.setText(valueMonth +"/" + valueYear);
					lblResult.setText("Doanh thu trong: ");
					
					// chart
					String date1 = "01/" + valueMonth +"/" + valueYear + " - 10/" + valueMonth +"/" + valueYear;
					String date2 = "11/" + valueMonth +"/" + valueYear + " - 20/" + valueMonth +"/" + valueYear;
					String date3 = "21/" + valueMonth +"/" + valueYear + " - 30/" + valueMonth +"/" + valueYear;
					chart = new Chart();
					chart.addLegend("", Utils.primaryColor);
					chart.addLegend("", Utils.primaryColor);
					chart.addLegend("", Utils.primaryColor);
					int randomNum1 = ThreadLocalRandom.current().nextInt(1, 10 + 1);
					int randomNum2 = ThreadLocalRandom.current().nextInt(1, 10 + 1);
					int randomNum3= ThreadLocalRandom.current().nextInt(1, 10 + 1);
					chart.addData(new ModelChart(date1, new double[] {0, randomNum1*5, 0}));
					chart.addData(new ModelChart(date2, new double[] {0, randomNum2*5, 0}));
					chart.addData(new ModelChart(date3, new double[] {0, randomNum3*5, 0}));
					chart.setBounds(10, 10, (Utils.getScreenWidth()-235)/2 + 10, Utils.getBodyHeight() - 210);
					pnlChart.add(chart);
					chart.start();

					// doanh thu
					int randomPhongThuong = ThreadLocalRandom.current().nextInt(1, 15 + 1);
					int randomPhongVIP= ThreadLocalRandom.current().nextInt(1, 15 + 1);
					int randomDichVu= ThreadLocalRandom.current().nextInt(1, 7 + 1);
					int randomGioHat = ThreadLocalRandom.current().nextInt(1, 6 + 1) * 30;
					int randomHoaDon = ThreadLocalRandom.current().nextInt(5, 10 + 1) * 30;

					lblTongDoanhThuKQ.setText(randomPhongThuong*30*100000 + randomPhongVIP*30*100000+ randomDichVu*30*100000+ " VNĐ");
					lblTongTienPhongKQ.setText(randomPhongThuong*100000 + randomPhongVIP*30*100000+ " VNĐ");
					lblDoanhThuPhongThuongKQ.setText(randomPhongThuong*30*100000+" VNĐ");
					lblDoanhThuPhongVIPKQ.setText(randomPhongVIP*30*100000+" VNĐ");
					lblTongTienDVKQ.setText(randomDichVu*30*100000+" VNĐ");
					lblTongSoGHKQ.setText(randomGioHat+" giờ");
					lblTongSoHDKQ.setText(randomHoaDon +"");
					lblDoanhThuTrungBinhKQ.setText((randomPhongThuong*30*100000 + randomPhongVIP*30*100000+ randomDichVu*30*100000)/randomHoaDon +" VNĐ/ngày");

				}

				// Year
				if(valueDay.trim()=="" && valueMonth.trim()=="" && valueYear.trim()!="")
				{
					pnlChart.setBounds((Utils.getScreenWidth()-235)/2 + 100, 180, (Utils.getScreenWidth()-235)/2 + 30, Utils.getBodyHeight() - 230);
					lblResDate.setText(valueYear);
					lblResult.setText("Doanh thu trong: ");
					// doanh thu
					int randomPhongThuong = ThreadLocalRandom.current().nextInt(1, 15 + 1);
					int randomPhongVIP= ThreadLocalRandom.current().nextInt(1, 15 + 1);
					int randomDichVu= ThreadLocalRandom.current().nextInt(1, 7 + 1);
					int randomGioHat = ThreadLocalRandom.current().nextInt(1, 6 + 1) * 365;
					int randomHoaDon = ThreadLocalRandom.current().nextInt(5, 10 + 1) * 365;

					lblTongDoanhThuKQ.setText(randomPhongThuong*365*100000 + randomPhongVIP*365*100000+ randomDichVu*365*100000+ " VNĐ");
					lblTongTienPhongKQ.setText(randomPhongThuong*100000 + randomPhongVIP*365*100000+ " VNĐ");
					lblDoanhThuPhongThuongKQ.setText(randomPhongThuong*365*100000+" VNĐ");
					lblDoanhThuPhongVIPKQ.setText(randomPhongVIP*365*100000+" VNĐ");
					lblTongTienDVKQ.setText(randomDichVu*365*100000+" VNĐ");
					lblTongSoGHKQ.setText(randomGioHat+" giờ");
					lblTongSoHDKQ.setText(randomHoaDon +"");
					lblDoanhThuTrungBinhKQ.setText((randomPhongThuong*365*100000 + randomPhongVIP*365*100000+ randomDichVu*365*100000)/randomHoaDon +" VNĐ/ngày");

					// chart
					String date1 = "01/" + valueYear + " - 04/" +valueYear;
					String date2 = "05/" + valueYear + " - 08/" +valueYear;
					String date3 = "09/" + valueYear + " - 12/" +valueYear;
					chart = new Chart();
					chart.addLegend("", Utils.primaryColor);
					chart.addLegend("", Utils.primaryColor);
					chart.addLegend("", Utils.primaryColor);
					int randomNum1 = ThreadLocalRandom.current().nextInt(1, 10 + 1);
					int randomNum2 = ThreadLocalRandom.current().nextInt(1, 10 + 1);
					int randomNum3= ThreadLocalRandom.current().nextInt(1, 10 + 1);
					chart.addData(new ModelChart(date1, new double[] {0, randomNum1*5, 0}));
					chart.addData(new ModelChart(date2, new double[] {0, randomNum2*5, 0}));
					chart.addData(new ModelChart(date3, new double[] {0, randomNum3*5, 0}));
					chart.setBounds(10, 10, (Utils.getScreenWidth()-235)/2 + 10, Utils.getBodyHeight() - 210);
					pnlChart.add(chart);
					chart.start();
				}
			}

		});

		// Kết quả doanh thu
		pnlResultContainer = new PanelRound();
		pnlResultContainer.setBounds(90, 180, (Utils.getScreenWidth()-235)/2 - 10, Utils.getBodyHeight() - 230);
		pnlResultContainer.setBackground(Color.WHITE);
		pnlResultContainer.setRoundBottomRight(20);
		pnlResultContainer.setRoundTopLeft(20);
		pnlResultContainer.setRoundTopRight(20);
		pnlResultContainer.setRoundBottomLeft(20);
		pnlResultContainer.setLayout(null);
		this.add(pnlResultContainer);

		// Phần Header Doanh thu hiện tại
		lblResult = new JLabel("Doanh thu trong tháng hiện tại: ", SwingConstants.LEFT);
		lblResult.setBounds(20, 15, 299, 24);
		lblResult.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblResult.setForeground(new Color(100, 100, 100));
		pnlResultContainer.add(lblResult);

		lblResDate = new JLabel("11/2022", SwingConstants.RIGHT);
		lblResDate.setBounds(180, 15, 299, 24);
		lblResDate.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblResDate.setForeground(new Color(100, 100, 100));
		pnlResultContainer.add(lblResDate);

		// Phần line
		JPanel line = new JPanel();
		line.setBounds(20, 50, (Utils.getScreenWidth()-235)/2 - 50, 2);
		line.setLayout(null);
		line.setBackground(new Color(100, 100, 100));
		pnlResultContainer.add(line);

		// Phần Result
		PanelRound pnlResult = new PanelRound();
		pnlResult.setBounds(20, 65, (Utils.getScreenWidth()-235)/2 - 50, 300);
		pnlResult.setBackground(Color.WHITE);
		pnlResult.setLayout(null);
		pnlResultContainer.add(pnlResult);

		// Tổng doanh thu
		JPanel pnlTongDoanhThu = new JPanel();
		pnlTongDoanhThu.setBounds(0, 0, (Utils.getScreenWidth()-235)/2 - 50, 30);
		pnlTongDoanhThu.setBackground(Color.WHITE);
		pnlTongDoanhThu.setLayout(new BorderLayout());
		pnlResult.add(pnlTongDoanhThu);

		JLabel lblTongDoanhThu = new JLabel("Tổng doanh thu: ");
		lblTongDoanhThu.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblTongDoanhThu.setForeground(Utils.primaryColor);
		pnlTongDoanhThu.add(lblTongDoanhThu, BorderLayout.WEST);

		lblTongDoanhThuKQ = new JLabel("");
		lblTongDoanhThuKQ.setBounds(-1, 0, 299, 28);
		lblTongDoanhThuKQ.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTongDoanhThuKQ.setForeground(new Color(100, 100, 100));
		pnlTongDoanhThu.add(lblTongDoanhThuKQ, BorderLayout.EAST);

		// Tổng tiền phòng
		JPanel pnlTongTienPhong = new JPanel();
		pnlTongTienPhong.setBounds(20, 35, (Utils.getScreenWidth()-235)/2 - 70, 30);
		pnlTongTienPhong.setBackground(Color.WHITE);
		pnlTongTienPhong.setLayout(new BorderLayout());
		pnlResult.add(pnlTongTienPhong);

		JLabel lblTongTienPhong = new JLabel("Tổng tiền phòng: ");
		lblTongTienPhong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTongTienPhong.setForeground(new Color(100, 100, 100));
		pnlTongTienPhong.add(lblTongTienPhong, BorderLayout.WEST);

		lblTongTienPhongKQ = new JLabel("");
		lblTongTienPhongKQ.setBounds(-1, 0, 299, 28);
		lblTongTienPhongKQ.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTongTienPhongKQ.setForeground(new Color(100, 100, 100));
		pnlTongTienPhong.add(lblTongTienPhongKQ, BorderLayout.EAST);

		// Doanh thu phòng thường
		JPanel pnlDoanhThuPhongThuong = new JPanel();
		pnlDoanhThuPhongThuong.setBounds(40, 70, (Utils.getScreenWidth()-235)/2 - 90, 30);
		pnlDoanhThuPhongThuong.setBackground(Color.WHITE);
		pnlDoanhThuPhongThuong.setLayout(new BorderLayout());
		pnlResult.add(pnlDoanhThuPhongThuong);

		JLabel lblDoanhThuPhongThuong = new JLabel("Doanh thu phòng thường: ");
		lblDoanhThuPhongThuong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblDoanhThuPhongThuong.setForeground(new Color(100, 100, 100));
		pnlDoanhThuPhongThuong.add(lblDoanhThuPhongThuong, BorderLayout.WEST);

		lblDoanhThuPhongThuongKQ = new JLabel("");
		lblDoanhThuPhongThuongKQ.setBounds(-1, 0, 299, 28);
		lblDoanhThuPhongThuongKQ.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblDoanhThuPhongThuongKQ.setForeground(new Color(100, 100, 100));
		pnlDoanhThuPhongThuong.add(lblDoanhThuPhongThuongKQ, BorderLayout.EAST);

		// Doanh thu phòng VIP
		JPanel pnlDoanhThuPhongVIP = new JPanel();
		pnlDoanhThuPhongVIP.setBounds(40, 105, (Utils.getScreenWidth()-235)/2 - 90, 30);
		pnlDoanhThuPhongVIP.setBackground(Color.WHITE);
		pnlDoanhThuPhongVIP.setLayout(new BorderLayout());
		pnlResult.add(pnlDoanhThuPhongVIP);

		JLabel lblDoanhThuPhongVIP = new JLabel("Doanh thu phòng VIP: ");
		lblDoanhThuPhongVIP.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblDoanhThuPhongVIP.setForeground(new Color(100, 100, 100));
		pnlDoanhThuPhongVIP.add(lblDoanhThuPhongVIP, BorderLayout.WEST);

		lblDoanhThuPhongVIPKQ = new JLabel("");
		lblDoanhThuPhongVIPKQ.setBounds(-1, 0, 299, 28);
		lblDoanhThuPhongVIPKQ.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblDoanhThuPhongVIPKQ.setForeground(new Color(100, 100, 100));
		pnlDoanhThuPhongVIP.add(lblDoanhThuPhongVIPKQ, BorderLayout.EAST);

		// Tổng tiền dịch vụ
		JPanel pnlTongTienDV = new JPanel();
		pnlTongTienDV.setBounds(20, 140, (Utils.getScreenWidth()-235)/2 - 70, 30);
		pnlTongTienDV.setBackground(Color.WHITE);
		pnlTongTienDV.setLayout(new BorderLayout());
		pnlResult.add(pnlTongTienDV);

		JLabel lblTongTienDV = new JLabel("Tổng tiền dịch vụ: ");
		lblTongTienDV.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTongTienDV.setForeground(new Color(100, 100, 100));
		pnlTongTienDV.add(lblTongTienDV, BorderLayout.WEST);

		lblTongTienDVKQ = new JLabel("");
		lblTongTienDVKQ.setBounds(-1, 0, 299, 28);
		lblTongTienDVKQ.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTongTienDVKQ.setForeground(new Color(100, 100, 100));
		pnlTongTienDV.add(lblTongTienDVKQ, BorderLayout.EAST);

		// Tổng số giờ hát
		JPanel pnlTongSoGH = new JPanel();
		pnlTongSoGH.setBounds(20, 175, (Utils.getScreenWidth()-235)/2 - 70, 30);
		pnlTongSoGH.setBackground(Color.WHITE);
		pnlTongSoGH.setLayout(new BorderLayout());
		pnlResult.add(pnlTongSoGH);

		JLabel lblTongSoGH = new JLabel("Tổng số giờ hát: ");
		lblTongSoGH.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTongSoGH.setForeground(new Color(100, 100, 100));
		pnlTongSoGH.add(lblTongSoGH, BorderLayout.WEST);

		lblTongSoGHKQ = new JLabel("");
		lblTongSoGHKQ.setBounds(-1, 0, 299, 28);
		lblTongSoGHKQ.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTongSoGHKQ.setForeground(new Color(100, 100, 100));
		pnlTongSoGH.add(lblTongSoGHKQ, BorderLayout.EAST);


		// Tổng số hoá đơn
		JPanel pnlTongSoHD = new JPanel();
		pnlTongSoHD.setBounds(0, 210, (Utils.getScreenWidth()-235)/2 - 50, 30);
		pnlTongSoHD.setBackground(Color.WHITE);
		pnlTongSoHD.setLayout(new BorderLayout());
		pnlResult.add(pnlTongSoHD);

		JLabel lblTongSoHD = new JLabel("Tổng số hoá đơn: ");
		lblTongSoHD.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblTongSoHD.setForeground(Utils.primaryColor);
		pnlTongSoHD.add(lblTongSoHD, BorderLayout.WEST);

		lblTongSoHDKQ = new JLabel("");
		lblTongSoHDKQ.setBounds(-1, 0, 299, 28);
		lblTongSoHDKQ.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTongSoHDKQ.setForeground(new Color(100, 100, 100));
		pnlTongSoHD.add(lblTongSoHDKQ, BorderLayout.EAST);

		// Doanh thu trung bình
		JPanel pnlDoanhThuTrungBinh = new JPanel();
		pnlDoanhThuTrungBinh.setBounds(0, 245, (Utils.getScreenWidth()-235)/2 - 50, 30);
		pnlDoanhThuTrungBinh.setBackground(Color.WHITE);
		pnlDoanhThuTrungBinh.setLayout(new BorderLayout());
		pnlResult.add(pnlDoanhThuTrungBinh);

		JLabel lblDoanhThuTrungBinh = new JLabel("Doanh thu trung bình: ");
		lblDoanhThuTrungBinh.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblDoanhThuTrungBinh.setForeground(Utils.primaryColor);
		pnlDoanhThuTrungBinh.add(lblDoanhThuTrungBinh, BorderLayout.WEST);

		lblDoanhThuTrungBinhKQ = new JLabel("");
		lblDoanhThuTrungBinhKQ.setBounds(-1, 0, 299, 28);
		lblDoanhThuTrungBinhKQ.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblDoanhThuTrungBinhKQ.setForeground(new Color(100, 100, 100));
		pnlDoanhThuTrungBinh.add(lblDoanhThuTrungBinhKQ, BorderLayout.EAST);

		int randomPhongThuong = ThreadLocalRandom.current().nextInt(1, 15 + 1);
		int randomPhongVIP= ThreadLocalRandom.current().nextInt(1, 15 + 1);
		int randomDichVu= ThreadLocalRandom.current().nextInt(1, 7 + 1);
		int randomGioHat = ThreadLocalRandom.current().nextInt(1, 6 + 1) * 30;
		int randomHoaDon = ThreadLocalRandom.current().nextInt(5, 10 + 1) * 30;

		lblTongDoanhThuKQ.setText(randomPhongThuong*30*100000 + randomPhongVIP*30*100000+ randomDichVu*30*100000+ " VNĐ");
		lblTongTienPhongKQ.setText(randomPhongThuong*100000 + randomPhongVIP*30*100000+ " VNĐ");
		lblDoanhThuPhongThuongKQ.setText(randomPhongThuong*30*100000+" VNĐ");
		lblDoanhThuPhongVIPKQ.setText(randomPhongVIP*30*100000+" VNĐ");
		lblTongTienDVKQ.setText(randomDichVu*30*100000+" VNĐ");
		lblTongSoGHKQ.setText(randomGioHat+" giờ");
		lblTongSoHDKQ.setText(randomHoaDon +"");
		lblDoanhThuTrungBinhKQ.setText((randomPhongThuong*30*100000 + randomPhongVIP*30*100000+ randomDichVu*30*100000)/randomHoaDon +" VNĐ/ngày");
		
		
		// pnlChart
		pnlChart = new PanelRound();
		pnlChart.setBounds((Utils.getScreenWidth()-235)/2 + 100, 180, (Utils.getScreenWidth()-235)/2 + 30, Utils.getBodyHeight() - 230);
		pnlChart.setBackground(Color.WHITE);
		pnlChart.setRoundBottomRight(20);
		pnlChart.setRoundTopLeft(20);
		pnlChart.setRoundTopRight(20);
		pnlChart.setRoundBottomLeft(20);
		pnlChart.setLayout(null);
		this.add(pnlChart);
		
		// chart
		String date1 = "01/" + 11 +"/" + 2022 + " - 10/" + 11 +"/" + 2022;
		String date2 = "11/" + 11 +"/" + 2022 + " - 20/" + 11 +"/" + 2022;
		String date3 = "21/" + 11 +"/" + 2022 + " - 30/" + 11 +"/" + 2022;
		chart = new Chart();
		chart.addLegend("", Utils.primaryColor);
		chart.addLegend("", Utils.primaryColor);
		chart.addLegend("", Utils.primaryColor);
		int randomNum1 = ThreadLocalRandom.current().nextInt(1, 10 + 1);
		int randomNum2 = ThreadLocalRandom.current().nextInt(1, 10 + 1);
		int randomNum3= ThreadLocalRandom.current().nextInt(1, 10 + 1);
		chart.addData(new ModelChart(date1, new double[] {0, randomNum1*5, 0}));
		chart.addData(new ModelChart(date2, new double[] {0, randomNum2*5, 0}));
		chart.addData(new ModelChart(date3, new double[] {0, randomNum3*5, 0}));
		chart.setBounds(10, 10, (Utils.getScreenWidth()-235)/2 + 10, Utils.getBodyHeight() - 210);
		pnlChart.add(chart);
		chart.start();
	}


}
