package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import components.button.Button;
import components.panelRound.PanelRound;
import components.scrollbarCustom.ScrollBarCustom;
import dao.ChiTietDatPhong_DAO;
import dao.ChiTietDichVu_DAO;
import dao.KhachHang_DAO;
import dao.LoaiPhong_DAO;
import dao.NhanVien_DAO;
import entity.ChiTietDatPhong;
import entity.ChiTietDichVu;
import entity.KhachHang;
import entity.LoaiPhong;
import entity.Phong;
import utils.NhanVien;
import utils.Utils;

public class ThongKeKhachHang_GUI extends JPanel {
	private static JLabel lblTime;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static Thread clock() {
		Thread clock = new Thread() {
			@Override
			public void run() {
				for (;;) {
					try {
						LocalDateTime currTime = LocalDateTime.now();
						int day = currTime.getDayOfMonth();
						int month = currTime.getMonthValue();
						int year = currTime.getYear();
						int hour = currTime.getHour();
						int minute = currTime.getMinute();
						int second = currTime.getSecond();
						lblTime.setText(String.format("%s/%s/%s | %s:%s:%s", day < 10 ? "0" + day : day,
								month < 10 ? "0" + month : month, year, hour < 10 ? "0" + hour : hour,
								minute < 10 ? "0" + minute : minute, second < 10 ? "0" + second : second));
						sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};

		clock.start();

		return clock;
	}

	private Main main;
	private JTextField txtSDT;
	private JComboBox<String> cmbDay;
	private JComboBox<String> cmbMonth;
	private JComboBox<String> cmbYear;
	private int top;
	private JTable tblKhachHang;
	private DefaultTableModel tableModel;
	private KhachHang_DAO khachHang_DAO;
	private LoaiPhong_DAO loaiPhong_DAO;
	private Button btnDay;
	private Button btnYear;
	private Button btnMonth;
	private NhanVien_DAO nhanVien_DAO;
	private ChiTietDatPhong_DAO chiTietDatPhong_DAO;
	private ChiTietDichVu_DAO chiTietDichVu_DAO;
	private String maNhanVien;
	private boolean isPhongVIP;
	private List<LoaiPhong> dsLoaiPhong;

	/**
	 * Create the frame.
	 */
	public ThongKeKhachHang_GUI(Main main) {
		this.main = main;
		int padding = (int) Math.floor((Utils.getBodyHeight() - 509) * 1.0 / 3);
		top = padding - 30;

		khachHang_DAO = new KhachHang_DAO();
		nhanVien_DAO = new NhanVien_DAO();
		chiTietDatPhong_DAO = new ChiTietDatPhong_DAO();
		chiTietDichVu_DAO = new ChiTietDichVu_DAO();
		loaiPhong_DAO = new LoaiPhong_DAO();

		dsLoaiPhong = loaiPhong_DAO.getAllLoaiPhong();

		entity.NhanVien nhanVien = NhanVien.getNhanVien();
		maNhanVien = nhanVien.getChucVu().equals(entity.NhanVien.ChucVu.QuanLy) ? "" : nhanVien.getMaNhanVien();

		setBackground(new Color(242, 246, 252));
		setBounds(0, 0, Utils.getScreenWidth(), Utils.getBodyHeight());
		setLayout(null);

		PanelRound pnlContainerAction = new PanelRound();
		pnlContainerAction.setBackground(Color.WHITE);
		pnlContainerAction.setBounds(Utils.getLeft(1052), top, 1052, 200);
		top += 137 + padding;
		pnlContainerAction.setRoundBottomRight(20);
		pnlContainerAction.setRoundTopLeft(20);
		pnlContainerAction.setRoundTopRight(20);
		pnlContainerAction.setRoundBottomLeft(20);
		this.add(pnlContainerAction);
		pnlContainerAction.setLayout(null);

		lblTime = new JLabel("");
		lblTime.setHorizontalAlignment(SwingConstants.LEFT);
		lblTime.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblTime.setBounds(1110, 0, 180, 24);
		this.add(lblTime);
		clock();

//		TÃ¬m kiáº¿m khÃ¡ch hÃ ng theo tÃªn vÃ  sá»‘ Ä‘iá»‡n thoáº¡i

		JLabel lblTimKiemKH = new JLabel("TÃ¬m kiáº¿m khÃ¡ch hÃ ng theo:");
		lblTimKiemKH.setBounds(20, 15, 299, 28);
		lblTimKiemKH.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblTimKiemKH.setForeground(new Color(100, 100, 100));
		pnlContainerAction.add(lblTimKiemKH);

		JPanel pnlRow1 = new JPanel();
		pnlRow1.setBackground(Color.WHITE);
		pnlRow1.setBounds(20, 60, Utils.getScreenWidth() - 90, 30);
		pnlContainerAction.add(pnlRow1);
		pnlRow1.setLayout(null);

		JLabel lblSDT = new JLabel("Sá»‘ Ä‘iá»‡n thoáº¡i: ");
		lblSDT.setBounds(10, 1, 120, 28);
		lblSDT.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblSDT.setForeground(new Color(100, 100, 100));
		pnlRow1.add(lblSDT);

		txtSDT = new JTextField("");
		txtSDT.setText("");
		txtSDT.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtSDT.setBounds(125, 0, 250, 30);
		txtSDT.setBorder(new LineBorder(Utils.primaryColor, 1));
		pnlRow1.add(txtSDT);
		txtSDT.setColumns(10);

//		Button tÃ¬m kiáº¿m theo ngÃ y, thÃ¡ng, nÄƒm
		JPanel pnlRow2 = new JPanel();
		pnlRow2.setBackground(Color.WHITE);
		pnlRow2.setBounds(20, 100, Utils.getScreenWidth() - 90, 35);
		pnlContainerAction.add(pnlRow2);
		pnlRow2.setLayout(null);

		btnDay = new Button("NgÃ y");
		btnDay.setFocusable(false);
		btnDay.setForeground(new Color(100, 100, 100));
		btnDay.setColor(new Color(242, 246, 252));
		btnDay.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnDay.setBounds(470, 1, 118, 35);
		btnDay.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnDay.setBorderColor(new Color(242, 246, 252));
		btnDay.setColorOver(new Color(242, 246, 252));
		btnDay.setColorClick(Utils.primaryColor);
		btnDay.setRadius(10);
		pnlRow2.add(btnDay);

		btnDay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

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

				cmbDay.setEnabled(true);
				cmbMonth.setEnabled(true);
				setDaysToCmb();
			}
		});

		btnMonth = new Button("ThÃ¡ng");
		btnMonth.setFocusable(false);
		btnMonth.setForeground(Color.WHITE);
		btnMonth.setColor(Utils.primaryColor);
		btnMonth.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnMonth.setBounds(670, 1, 118, 35);
		btnMonth.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnMonth.setBorderColor(Utils.primaryColor);
		btnMonth.setColorOver(Utils.primaryColor);
		btnMonth.setColorClick(Utils.primaryColor);
		btnMonth.setRadius(10);
		pnlRow2.add(btnMonth);

		btnMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

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

				cmbDay.setEnabled(false);
				cmbMonth.setEnabled(true);
			}
		});

		btnYear = new Button("NÄƒm");
		btnYear.setFocusable(false);
		btnYear.setForeground(new Color(100, 100, 100));
		btnYear.setColor(new Color(242, 246, 252));
		btnYear.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnYear.setBounds(870, 1, 118, 35);
		btnYear.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnYear.setBorderColor(new Color(242, 246, 252));
		btnYear.setColorOver(Utils.primaryColor);
		btnYear.setColorClick(Utils.primaryColor);
		btnYear.setRadius(10);
		pnlRow2.add(btnYear);

		btnYear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

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

				cmbDay.setEnabled(false);
				cmbMonth.setEnabled(false);

			}
		});

//		TÃ¬m kiáº¿m theo ngÃ y, thÃ¡ng, nÄƒm khÃ¡ch hÃ ng thuÃª phÃ²ng

		JPanel pnlRow3 = new JPanel();
		pnlRow3.setBackground(Color.WHITE);
		pnlRow3.setBounds(30, 150, Utils.getScreenWidth() - 90, 45);
		pnlContainerAction.add(pnlRow3);
		pnlRow3.setLayout(null);

		JLabel lblDay = new JLabel("NgÃ y: ");
		lblDay.setForeground(new Color(100, 100, 100));
		lblDay.setBounds(0, 9, 70, 28);
		lblDay.setFont(new Font("Segoe UI", Font.BOLD, 16));
		pnlRow3.add(lblDay);

		cmbDay = new JComboBox<String>();
		cmbDay.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		cmbDay.setBackground(Color.WHITE);
		cmbDay.setBounds(70, 3, 100, 40);
		cmbDay.setBorder(new EmptyBorder(0, 0, 0, 0));
		cmbDay.setEnabled(false);
		pnlRow3.add(cmbDay);

		JLabel lblMonth = new JLabel("ThÃ¡ng: ");
		lblMonth.setForeground(new Color(100, 100, 100));
		lblMonth.setBounds(269, 9, 70, 28);
		lblMonth.setFont(new Font("Segoe UI", Font.BOLD, 16));
		pnlRow3.add(lblMonth);

		cmbMonth = new JComboBox<String>();
		cmbMonth.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		cmbMonth.setBackground(Color.WHITE);
		cmbMonth.setAlignmentX(CENTER_ALIGNMENT);
		cmbMonth.setBounds(339, 3, 100, 40);
		cmbMonth.setBorder(new EmptyBorder(0, 0, 0, 0));
		cmbMonth.setSelectedItem("11");
		cmbMonth.addItemListener(e -> setDaysToCmb());
		pnlRow3.add(cmbMonth);

		for (int i = 1; i < 13; i++)
			cmbMonth.addItem(Utils.convertIntToString(i));

		JLabel lblYear = new JLabel("NÄƒm: ");
		lblYear.setForeground(new Color(100, 100, 100));
		lblYear.setBounds(538, 9, 70, 28);
		lblYear.setFont(new Font("Segoe UI", Font.BOLD, 16));
		pnlRow3.add(lblYear);

		cmbYear = new JComboBox<String>();
		cmbYear.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		cmbYear.setBackground(Color.WHITE);
		cmbYear.setAlignmentX(CENTER_ALIGNMENT);
		cmbYear.setBounds(608, 3, 100, 40);
		cmbYear.setBorder(new EmptyBorder(0, 0, 0, 0));
		cmbYear.addItemListener(e -> setDaysToCmb());
		pnlRow3.add(cmbYear);

		int yearNow = LocalDate.now().getYear();
		for (int i = 2015; i <= yearNow; ++i)
			cmbYear.addItem(i + "");

		Button btnTimKiem = new Button("TÃ¬m");
		btnTimKiem.setFocusable(false);
		btnTimKiem.setForeground(Color.WHITE);
		btnTimKiem.setColor(Utils.primaryColor);
		btnTimKiem.setBorderColor(Utils.primaryColor);
		btnTimKiem.setRadius(10);
		btnTimKiem.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnTimKiem.setBounds(820, 0, 160, 44);
		btnTimKiem.setColorOver(Utils.primaryColor);
		btnTimKiem.setColorTextOver(Color.WHITE);
		btnTimKiem.setColorTextOut(Color.WHITE);
		btnTimKiem.setColorClick(Utils.primaryColor);
		btnTimKiem.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnTimKiem.setIcon(new ImageIcon("Icon\\searchIcon.png"));
		pnlRow3.add(btnTimKiem);

//		Table

		PanelRound pnlTable = new PanelRound();
		pnlTable.setBackground(Color.WHITE);
		pnlTable.setBounds(Utils.getLeft(1052), 270, 1052, 450);
		pnlTable.setRoundBottomRight(20);
		pnlTable.setRoundTopLeft(20);
		pnlTable.setRoundTopRight(20);
		pnlTable.setRoundBottomLeft(20);
		this.add(pnlTable);
		pnlTable.setLayout(null);

		JScrollPane scr = new JScrollPane();
		scr.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scr.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scr.setBounds(0, 0, 1052, 450);
		scr.setBackground(Utils.primaryColor);
		ScrollBarCustom scp = new ScrollBarCustom();
		scp.setScrollbarColor(new Color(203, 203, 203));
		scr.setVerticalScrollBar(scp);
		pnlTable.add(scr);

		tblKhachHang = new JTable() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				if (isRowSelected(row))
					c.setBackground(Utils.getOpacity(Utils.primaryColor, 0.5f));
				else if (row % 2 == 0)
					c.setBackground(Color.WHITE);
				else
					c.setBackground(new Color(232, 232, 232));
				return c;
			}
		};

		tableModel = new DefaultTableModel(
				new String[] { "MÃ£ khÃ¡ch hÃ ng", "Há» vÃ  tÃªn", "SDT", "Giá»›i tÃ­nh", "Tá»•ng Tiá»n" }, 0);
		tblKhachHang.setModel(tableModel);
		tblKhachHang.getColumnModel().getColumn(0).setPreferredWidth(210);
		tblKhachHang.getColumnModel().getColumn(1).setPreferredWidth(210);
		tblKhachHang.getColumnModel().getColumn(2).setPreferredWidth(210);
		tblKhachHang.getColumnModel().getColumn(3).setPreferredWidth(210);
		tblKhachHang.getColumnModel().getColumn(4).setPreferredWidth(210);

		tblKhachHang.getTableHeader().setBackground(Utils.primaryColor);
		tblKhachHang.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tblKhachHang.getTableHeader().setForeground(Color.WHITE);
		tblKhachHang.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tblKhachHang.getTableHeader()
				.setPreferredSize(new Dimension((int) tblKhachHang.getTableHeader().getPreferredSize().getWidth(), 36));
		tblKhachHang.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
		tblKhachHang.setRowHeight(36);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		tblKhachHang.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tblKhachHang.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		scr.setViewportView(tblKhachHang);

		addAncestorListener(new AncestorListener() {
			Thread clockThread;

			public void ancestorAdded(AncestorEvent event) {
				clockThread = clock();

				cmbMonth.setSelectedIndex(LocalDate.now().getMonthValue() - 1);
				cmbYear.setSelectedItem(yearNow + "");
			}

			public void ancestorMoved(AncestorEvent event) {
			}

			@SuppressWarnings("deprecation")
			public void ancestorRemoved(AncestorEvent event) {
				clockThread.stop();
			}
		});

		cmbDay.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					filterThongKe();
				}
			}
		});

		cmbMonth.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					filterThongKe();
				}
			}
		});

		cmbYear.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					filterThongKe();
				}
			}
		});
	}

	private LoaiPhong getLoaiPhong(LoaiPhong lp) {
		for (LoaiPhong loaiPhong : dsLoaiPhong)
			if (loaiPhong.equals(lp)) {
				if (loaiPhong.getTenLoai().toUpperCase().contains("VIP"))
					isPhongVIP = true;
				else
					isPhongVIP = false;
				return loaiPhong;
			}
		return null;
	}

	private void filterThongKe() {
		String soDienThoai = txtSDT.getText();
		String maKhachHang = "", maKH = "";
		int ngay = 0, thang = 0, nam = 0;
		KhachHang khachHang = khachHang_DAO.getKhachHang(soDienThoai);
		if (khachHang != null)
			maKhachHang = khachHang.getMaKhachHang();

		if (cmbDay.isEnabled())
			ngay = Integer.parseInt(cmbDay.getSelectedItem().toString());
		if (cmbMonth.isEnabled())
			thang = Integer.parseInt(cmbMonth.getSelectedItem().toString());
		nam = Integer.parseInt(cmbYear.getSelectedItem().toString());

		System.out.println(ngay + " " + thang + " " + nam);
		List<ChiTietDatPhong> dsChiTietDatPhong = chiTietDatPhong_DAO.getChiTietDatPhong(ngay, thang, nam, maNhanVien,
				maKhachHang);
		List<ChiTietDichVu> dsChiTietDichVu = chiTietDichVu_DAO.getChiTietDichVu(ngay, thang, nam, maNhanVien,
				maKhachHang);

		Phong phong;
		LocalTime gioVao, gioThuePhong;
		double tongTienPhongThuong = 0, tongTienPhongVIP = 0, tongTien = 0, giaPhong;
		int tongGioHat = 0, tongPhutHat = 0, gio = 0, phut = 0, index;
		List<String> dsKhachHang = new ArrayList<>();
		List<Double> dsTongTien = new ArrayList<>();

		for (ChiTietDatPhong chiTietDatPhong : dsChiTietDatPhong) {
			phong = chiTietDatPhong.getPhong();
			isPhongVIP = false;
			phong.setLoaiPhong(getLoaiPhong(phong.getLoaiPhong()));

			gioVao = chiTietDatPhong.getGioVao();
			gioThuePhong = chiTietDatPhong.getGioRa().minusHours(gioVao.getHour()).minusMinutes(gioVao.getMinute())
					.minusSeconds(gioVao.getSecond());

			gio = gioThuePhong.getHour();
			phut = gioThuePhong.getMinute();

			tongGioHat += gio;
			tongPhutHat += phut;
			giaPhong = (gio + phut * 1.0 / 60) * phong.getGiaTien();

			maKH = chiTietDatPhong.getDonDatPhong().getKhachHang().getMaKhachHang();
			if (dsKhachHang.contains(maKH)) {
				index = dsKhachHang.indexOf(maKH);
				dsTongTien.set(index, dsTongTien.get(index) + giaPhong);
			} else {
				dsKhachHang.add(maKH);
				dsTongTien.add(giaPhong);
			}
			
			for(ChiTietDichVu chiTietDichVu : dsChiTietDichVu) {
				
			}
		}

		dsChiTietDichVu.forEach(chiTietDichVu -> {
			System.out.println(chiTietDichVu);
		});

		for (int i = 0; i < dsKhachHang.size(); i++) {
			addRow(new KhachHang(dsKhachHang.get(i)), dsTongTien.get(i));
		}
	}

	private void setDaysToCmb() {
		if (!cmbDay.isEnabled())
			return;

		int month = Integer.parseInt(cmbMonth.getSelectedItem().toString());
		int year = Integer.parseInt(cmbYear.getSelectedItem().toString());
		int daysOfMonth = getNumberOfDaysInMonth(year, month);
		LocalDate dateNow = LocalDate.now();

		if (month == dateNow.getMonthValue() && year == dateNow.getYear())
			daysOfMonth = dateNow.getDayOfMonth();

		cmbDay.removeAllItems();
		for (int i = 1; i <= daysOfMonth; ++i)
			if (i < 10)
				cmbDay.addItem("0" + i);
			else
				cmbDay.addItem(i + "");
		cmbDay.setSelectedIndex(-1);
	}

	private int getNumberOfDaysInMonth(int year, int month) {
		YearMonth yearMonthObject = YearMonth.of(year, month);
		int daysInMonth = yearMonthObject.lengthOfMonth();
		return daysInMonth;
	}

	private List<KhachHang> addRow(List<KhachHang> list, double tongTien) {
		list.forEach(khachHang -> addRow(khachHang, tongTien));
		return list;
	}

	private void addRow(KhachHang khachHang, double tongTien) {
		khachHang = khachHang_DAO.getKhachHangTheoMa(khachHang.getMaKhachHang());
		tableModel.addRow(new String[] { khachHang.getMaKhachHang(), khachHang.getHoTen(), khachHang.getSoDienThoai(),
				khachHang.isGioiTinh() ? "Nam" : "Ná»¯", Utils.formatTienTe(tongTien) });
	}
}
