package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import components.button.Button;
import components.panelRound.PanelRound;
import components.scrollbarCustom.ScrollBarCustom;
import entity.LoaiPhong;
import entity.Phong;
import entity.TrangThaiPhong;
import utils.Utils;

public class QuanLyDatPhong_GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final int gapX = 21;
	private final int gapY = 30;
	private final int widthPhong = 131;
	private final int heightPhong = 130;
	private final int numOfRow = 6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLyDatPhong_GUI frame = new QuanLyDatPhong_GUI();
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
	public QuanLyDatPhong_GUI() {
//		DefaultLayout defaultLayout = new DefaultLayout(this, contentPane, "Quản lý đặt phòng");
//		contentPane = defaultLayout.getJPanel();

		setTitle("Quản lý đặt phòng");
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

		JLabel lblTitle = new JLabel("Quản lý đặt phòng".toUpperCase());
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setBounds(76, 17, 948, 32);
		lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
		pnlHeader.add(lblTitle);
//		End default layout

//		Chọn loại phòng
		JPanel pnlLoaiPhong = new JPanel();
		pnlLoaiPhong.setBackground(Utils.secondaryColor);
		pnlLoaiPhong.setBounds(16, 65, 1054, 58);
		contentPane.add(pnlLoaiPhong);
		pnlLoaiPhong.setLayout(null);

		JLabel lblLoaiPhong = new JLabel("Chọn loại phòng:");
		lblLoaiPhong.setForeground(new Color(0, 0, 0, 130));
		lblLoaiPhong.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblLoaiPhong.setBounds(0, 10, 215, 37);
		pnlLoaiPhong.add(lblLoaiPhong);

		Button btnTatCa = new Button("Tất cả");
		btnTatCa.setFocusable(false);
		btnTatCa.setRadius(9);
		btnTatCa.setBorderColor(Utils.secondaryColor);
		btnTatCa.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnTatCa.setForeground(Color.WHITE);
		btnTatCa.setBorderBtnColor(new Color(0, 0, 0, 81));
		btnTatCa.setBorderWidth(2);
		btnTatCa.setColor(new Color(140, 177, 180, 178));
		btnTatCa.setColorOver(new Color(140, 177, 180, 178));
		btnTatCa.setColorClick(new Color(140, 177, 180, 127));
		btnTatCa.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnTatCa.setBounds(222, 10, 132, 38);
		pnlLoaiPhong.add(btnTatCa);

		Button btnTatCaUnactive = new Button("Tất cả");
		btnTatCaUnactive.setRadius(9);
		btnTatCaUnactive.setForeground(new Color(0, 0, 0, 148));
		btnTatCaUnactive.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnTatCaUnactive.setFocusable(false);
		btnTatCaUnactive.setColorOver(Color.WHITE);
		btnTatCaUnactive.setColorClick(new Color(255, 255, 255, 204));
		btnTatCaUnactive.setColor(Color.WHITE);
		btnTatCaUnactive.setBorderWidth(2);
		btnTatCaUnactive.setBorderColor(Utils.secondaryColor);
		btnTatCaUnactive.setBorderBtnColor(new Color(119, 96, 204));
		btnTatCaUnactive.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnTatCaUnactive.setBounds(222, 10, 132, 38);
		pnlLoaiPhong.add(btnTatCaUnactive);

		Button btnPhongThuong = new Button("Phòng thường");
		btnPhongThuong.setRadius(9);
		btnPhongThuong.setForeground(Color.WHITE);
		btnPhongThuong.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnPhongThuong.setFocusable(false);
		btnPhongThuong.setColorOver(new Color(140, 177, 180, 178));
		btnPhongThuong.setColorClick(new Color(140, 177, 180, 127));
		btnPhongThuong.setColor(new Color(140, 177, 180, 178));
		btnPhongThuong.setBorderWidth(2);
		btnPhongThuong.setBorderColor(Utils.secondaryColor);
		btnPhongThuong.setBorderBtnColor(new Color(0, 0, 0, 81));
		btnPhongThuong.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnPhongThuong.setBounds(374, 10, 132, 38);
		pnlLoaiPhong.add(btnPhongThuong);

		Button btnPhongThuongUnactive = new Button("Phòng thường");
		btnPhongThuongUnactive.setRadius(9);
		btnPhongThuongUnactive.setForeground(new Color(0, 0, 0, 148));
		btnPhongThuongUnactive.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnPhongThuongUnactive.setFocusable(false);
		btnPhongThuongUnactive.setColorOver(Color.WHITE);
		btnPhongThuongUnactive.setColorClick(new Color(255, 255, 255, 204));
		btnPhongThuongUnactive.setColor(Color.WHITE);
		btnPhongThuongUnactive.setBorderWidth(2);
		btnPhongThuongUnactive.setBorderColor(Utils.secondaryColor);
		btnPhongThuongUnactive.setBorderBtnColor(new Color(119, 96, 204));
		btnPhongThuongUnactive.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnPhongThuongUnactive.setBounds(374, 10, 132, 38);
		pnlLoaiPhong.add(btnPhongThuongUnactive);

		Button btnPhongVip = new Button("Phòng VIP");
		btnPhongVip.setRadius(9);
		btnPhongVip.setForeground(Color.WHITE);
		btnPhongVip.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnPhongVip.setFocusable(false);
		btnPhongVip.setColorOver(new Color(140, 177, 180, 178));
		btnPhongVip.setColorClick(new Color(140, 177, 180, 127));
		btnPhongVip.setColor(new Color(140, 177, 180, 178));
		btnPhongVip.setBorderWidth(2);
		btnPhongVip.setBorderColor(Utils.secondaryColor);
		btnPhongVip.setBorderBtnColor(new Color(0, 0, 0, 81));
		btnPhongVip.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnPhongVip.setBounds(526, 10, 120, 38);
		pnlLoaiPhong.add(btnPhongVip);

		Button btnPhongVipUnactive = new Button("Phòng VIP");
		btnPhongVipUnactive.setRadius(9);
		btnPhongVipUnactive.setForeground(new Color(0, 0, 0, 148));
		btnPhongVipUnactive.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnPhongVipUnactive.setFocusable(false);
		btnPhongVipUnactive.setColorOver(Color.WHITE);
		btnPhongVipUnactive.setColorClick(new Color(255, 255, 255, 204));
		btnPhongVipUnactive.setColor(Color.WHITE);
		btnPhongVipUnactive.setBorderWidth(2);
		btnPhongVipUnactive.setBorderColor(Utils.secondaryColor);
		btnPhongVipUnactive.setBorderBtnColor(new Color(119, 96, 204));
		btnPhongVipUnactive.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnPhongVipUnactive.setBounds(526, 10, 120, 38);
		pnlLoaiPhong.add(btnPhongVipUnactive);

//		Date time
		JPanel pnlDateTime = new JPanel();
		pnlDateTime.setBackground(Utils.secondaryColor);
		pnlDateTime.setBounds(949, 0, 105, 58);
		pnlLoaiPhong.add(pnlDateTime);
		pnlDateTime.setLayout(null);

		JLabel lblTime = new JLabel("18:07");
		lblTime.setForeground(new Color(0, 0, 0, 115));
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblTime.setBounds(0, 0, 105, 19);
		pnlDateTime.add(lblTime);

		JLabel lblThu = new JLabel("T2");
		lblThu.setHorizontalAlignment(SwingConstants.CENTER);
		lblThu.setForeground(new Color(0, 0, 0, 115));
		lblThu.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblThu.setBounds(0, 19, 105, 19);
		pnlDateTime.add(lblThu);

		JLabel lblDate = new JLabel("29-09-2022");
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setForeground(new Color(0, 0, 0, 115));
		lblDate.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblDate.setBounds(0, 38, 105, 19);
		pnlDateTime.add(lblDate);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Icon\\clock (1) 1.png"));
		lblNewLabel.setBounds(885, -3, 64, 64);
		pnlLoaiPhong.add(lblNewLabel);

//		Seperator
		JPanel pnlSeperator = new JPanel();
		pnlSeperator.setBounds(0, 124, Utils.width, 4);
		pnlSeperator.setBackground(new Color(0, 0, 0, 64));
		contentPane.add(pnlSeperator);

//		Thống kê loại phòng
		JPanel pnlThongKeLoaiPhong = new JPanel();
		pnlThongKeLoaiPhong.setBackground(Utils.secondaryColor);
		pnlThongKeLoaiPhong.setBounds(924, 168, 146, 270);
		contentPane.add(pnlThongKeLoaiPhong);
		pnlThongKeLoaiPhong.setLayout(null);

		PanelRound pnlPhongTrong = new PanelRound(10);
		pnlPhongTrong.setBackground(Utils.phongTrong);
		pnlPhongTrong.setBounds(0, 0, 146, 60);
		pnlThongKeLoaiPhong.add(pnlPhongTrong);
		pnlPhongTrong.setLayout(null);

		JLabel lblIconPhongTrong = new JLabel("");
		lblIconPhongTrong.setForeground(Color.WHITE);
		lblIconPhongTrong.setIcon(new ImageIcon("Icon\\karaoke (3) 1.png"));
		lblIconPhongTrong.setBounds(12, 12, 35, 35);
		pnlPhongTrong.add(lblIconPhongTrong);

		JLabel lblText1 = new JLabel("Phòng trống");
		lblText1.setForeground(Color.WHITE);
		lblText1.setBounds(59, 11, 75, 19);
		pnlPhongTrong.add(lblText1);
		lblText1.setFont(new Font("Segoe UI", Font.BOLD, 12));

		JLabel lblSoPhongTrong = new JLabel("(2)");
		lblSoPhongTrong.setForeground(Color.WHITE);
		lblSoPhongTrong.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoPhongTrong.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblSoPhongTrong.setBounds(59, 30, 75, 19);
		pnlPhongTrong.add(lblSoPhongTrong);

		PanelRound pnlPhongDangSuDung = new PanelRound(10);
		pnlPhongDangSuDung.setBackground(Utils.phongDangSuDung);
		pnlPhongDangSuDung.setLayout(null);
		pnlPhongDangSuDung.setBounds(0, 105, 146, 60);
		pnlThongKeLoaiPhong.add(pnlPhongDangSuDung);

		JLabel lblIconPhongDSD = new JLabel("");
		lblIconPhongDSD.setIcon(new ImageIcon("Icon\\karaoke (3) 1.png"));
		lblIconPhongDSD.setBounds(12, 12, 35, 35);
		pnlPhongDangSuDung.add(lblIconPhongDSD);

		JLabel lblText2 = new JLabel("Phòng đang");
		lblText2.setForeground(Color.WHITE);
		lblText2.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblText2.setBounds(59, 2, 75, 19);
		pnlPhongDangSuDung.add(lblText2);

		JLabel lblText3 = new JLabel("sử dụng");
		lblText3.setForeground(Color.WHITE);
		lblText3.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblText3.setBounds(59, 21, 75, 19);
		pnlPhongDangSuDung.add(lblText3);

		JLabel lblSoPhongDSD = new JLabel("(5)");
		lblSoPhongDSD.setForeground(Color.WHITE);
		lblSoPhongDSD.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoPhongDSD.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblSoPhongDSD.setBounds(59, 40, 75, 19);
		pnlPhongDangSuDung.add(lblSoPhongDSD);

		PanelRound pnlPhongCho = new PanelRound(10);
		pnlPhongCho.setBackground(Utils.phongCho);
		pnlPhongCho.setLayout(null);
		pnlPhongCho.setBounds(0, 210, 146, 60);
		pnlThongKeLoaiPhong.add(pnlPhongCho);

		JLabel lblIconPhongCho = new JLabel("");
		lblIconPhongCho.setIcon(new ImageIcon("Icon\\karaoke (3) 1.png"));
		lblIconPhongCho.setBounds(12, 12, 35, 35);
		pnlPhongCho.add(lblIconPhongCho);

		JLabel lblText4 = new JLabel("Phòng chờ");
		lblText4.setForeground(Color.WHITE);
		lblText4.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblText4.setBounds(59, 11, 75, 19);
		pnlPhongCho.add(lblText4);

		JLabel lblSoLuongPhongCho = new JLabel("(2)");
		lblSoLuongPhongCho.setForeground(Color.WHITE);
		lblSoLuongPhongCho.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoLuongPhongCho.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblSoLuongPhongCho.setBounds(59, 30, 75, 19);
		pnlPhongCho.add(lblSoLuongPhongCho);

//		Các nút chức năng
		JPanel pnlActions = new JPanel();
		pnlActions.setBackground(Utils.secondaryColor);
		pnlActions.setBounds(16, 487, 523, 69);
		contentPane.add(pnlActions);
		pnlActions.setLayout(null);

		PanelRound pnlDatPhong = new PanelRound(13);
		pnlDatPhong.setBackground(new Color(255, 154, 97));
		pnlDatPhong.setBounds(0, 0, 141, 69);
		pnlActions.add(pnlDatPhong);
		pnlDatPhong.setLayout(null);

		JLabel lblDatPhong = new JLabel("Đặt phòng");
		lblDatPhong.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatPhong.setForeground(Color.WHITE);
		lblDatPhong.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblDatPhong.setBounds(12, 13, 117, 22);
		pnlDatPhong.add(lblDatPhong);

		JLabel lblDatPhongF = new JLabel("F1");
		lblDatPhongF.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatPhongF.setForeground(Color.WHITE);
		lblDatPhongF.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblDatPhongF.setBounds(12, 35, 117, 22);
		pnlDatPhong.add(lblDatPhongF);

		PanelRound pnlChuyenPhong = new PanelRound(13);
		pnlChuyenPhong.setLayout(null);
		pnlChuyenPhong.setBackground(new Color(255, 154, 97));
		pnlChuyenPhong.setBounds(191, 0, 141, 69);
		pnlActions.add(pnlChuyenPhong);

		JLabel lblChuyenPhong = new JLabel("Chuyển phòng");
		lblChuyenPhong.setHorizontalAlignment(SwingConstants.CENTER);
		lblChuyenPhong.setForeground(Color.WHITE);
		lblChuyenPhong.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblChuyenPhong.setBounds(12, 13, 117, 22);
		pnlChuyenPhong.add(lblChuyenPhong);

		JLabel lblChuyenPhongF = new JLabel("F2");
		lblChuyenPhongF.setHorizontalAlignment(SwingConstants.CENTER);
		lblChuyenPhongF.setForeground(Color.WHITE);
		lblChuyenPhongF.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblChuyenPhongF.setBounds(12, 35, 117, 22);
		pnlChuyenPhong.add(lblChuyenPhongF);

		PanelRound pnlGopPhong = new PanelRound(13);
		pnlGopPhong.setLayout(null);
		pnlGopPhong.setBackground(new Color(255, 154, 97));
		pnlGopPhong.setBounds(382, 0, 141, 69);
		pnlActions.add(pnlGopPhong);

		JLabel lblGopPhong = new JLabel("Gộp phòng");
		lblGopPhong.setHorizontalAlignment(SwingConstants.CENTER);
		lblGopPhong.setForeground(Color.WHITE);
		lblGopPhong.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblGopPhong.setBounds(12, 13, 117, 22);
		pnlGopPhong.add(lblGopPhong);

		JLabel lblGopPhongF = new JLabel("F3");
		lblGopPhongF.setHorizontalAlignment(SwingConstants.CENTER);
		lblGopPhongF.setForeground(Color.WHITE);
		lblGopPhongF.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblGopPhongF.setBounds(12, 35, 117, 22);
		pnlGopPhong.add(lblGopPhongF);

//		Danh sách phòng
		JPanel pnlDanhSachPhong = new JPanel();
		pnlDanhSachPhong.setBackground(Utils.secondaryColor);
		pnlDanhSachPhong.setLayout(null);

//		Start Fake Data
		List<Phong> dsPhong = fakeDSPhong();
		for (int i = 0; i < dsPhong.size(); i++) {
			JPanel phong1 = getPhong(dsPhong.get(i));
			phong1.setBounds(getBounds(i));
			pnlDanhSachPhong.add(phong1);
		}

		pnlDanhSachPhong.setPreferredSize(getSizeContainerDanhSachPhong(dsPhong.size()));
//		End Fake Data

		JScrollPane scrDanhSachPhong = new JScrollPane(pnlDanhSachPhong);
		scrDanhSachPhong.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrDanhSachPhong.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrDanhSachPhong.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrDanhSachPhong.setBackground(Utils.secondaryColor);
		scrDanhSachPhong.setBounds(16, 161, 900, 292);
		contentPane.add(scrDanhSachPhong);
		ScrollBarCustom scb = new ScrollBarCustom();
		scb.setBackgroundColor(Utils.secondaryColor);
		scb.setScrollbarColor(Utils.primaryColor);
		scrDanhSachPhong.setVerticalScrollBar(scb);

		btnPhongThuong.setVisible(false);
		btnPhongVip.setVisible(false);
		btnTatCaUnactive.setVisible(false);
		btnTatCa.setVisible(true);
		btnPhongThuongUnactive.setVisible(true);
		btnPhongVipUnactive.setVisible(true);
	}

	private Dimension getSizeContainerDanhSachPhong(int soPhong) {
		int row = (int) Math.ceil(soPhong / numOfRow);

		return new Dimension(890, (row - 1) * gapY + row * heightPhong);
	}

	private Rectangle getBounds(int itemIndex) {
		int row = itemIndex / numOfRow;

		return new Rectangle(itemIndex % numOfRow * (gapX + widthPhong), row * (gapY + heightPhong), widthPhong,
				heightPhong);
	}

	private JPanel getPhong(Phong phong) {
		JPanel pnlPhong1 = new JPanel();
		pnlPhong1.setBackground(Utils.secondaryColor);
		pnlPhong1.setBounds(0, 0, 131, 130);
		pnlPhong1.setLayout(null);

		JLabel lblIcon1 = new JLabel("");
		lblIcon1.setIcon(new ImageIcon("Icon\\vip 1.png"));
		lblIcon1.setBounds(53, 0, 25, 25);
		if (phong.getLoaiPhong().getMaLoai() == "L002")
			pnlPhong1.add(lblIcon1);

		Color bg = Utils.phongDangSuDung;
		if (phong.getTrangThai().equals(TrangThaiPhong.DaDat))
			bg = Utils.phongCho;
		else if (phong.getTrangThai().equals(TrangThaiPhong.Trong))
			bg = Utils.phongTrong;

		PanelRound pnlChiTietPhong = new PanelRound(10);
		pnlChiTietPhong.setBackground(bg);
		pnlChiTietPhong.setBounds(0, 25, 131, 105);
		pnlPhong1.add(pnlChiTietPhong);
		pnlChiTietPhong.setLayout(null);

		JLabel lblMaPhong = new JLabel(phong.getMaPhong());
		lblMaPhong.setHorizontalAlignment(SwingConstants.CENTER);
		lblMaPhong.setForeground(Color.WHITE);
		lblMaPhong.setFont(new Font("Segoe UI", Font.BOLD, 26));
		lblMaPhong.setBounds(0, 0, 131, 33);
		pnlChiTietPhong.add(lblMaPhong);

		JLabel lblTongGio = new JLabel("Tổng giờ: " + (phong.getTrangThai() == TrangThaiPhong.DangThue ? "22h30p" : "0"));
		lblTongGio.setForeground(Color.WHITE);
		lblTongGio.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblTongGio.setBounds(5, 80, 121, 18);
		pnlChiTietPhong.add(lblTongGio);

		JLabel lblGioVao = new JLabel("Giờ vào: " + (phong.getTrangThai() == TrangThaiPhong.DangThue ? "18h" : "0"));
		lblGioVao.setForeground(Color.WHITE);
		lblGioVao.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblGioVao.setBounds(5, 62, 121, 18);
		pnlChiTietPhong.add(lblGioVao);

		JLabel lblSoKhach = new JLabel("Số khách: " + (phong.getTrangThai() == TrangThaiPhong.DangThue ? "2" : "0"));
		lblSoKhach.setForeground(Color.WHITE);
		lblSoKhach.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblSoKhach.setBounds(5, 44, 121, 18);
		pnlChiTietPhong.add(lblSoKhach);

		return pnlPhong1;
	}

	private List<Phong> fakeDSPhong() {
		List<Phong> dsPhong = new ArrayList<>();

		LoaiPhong loaiPhongThuong = new LoaiPhong("L001", "Phòng thường");
		LoaiPhong loaiPhongVip = new LoaiPhong("L002", "Phòng VIP");

		Phong phongThuongTrong = new Phong("01.01", loaiPhongThuong, 5, TrangThaiPhong.Trong);
		Phong phongThuongDangSD = new Phong("01.01", loaiPhongThuong, 5, TrangThaiPhong.DangThue);
		Phong phongThuongCho = new Phong("01.01", loaiPhongThuong, 5, TrangThaiPhong.DaDat);
		Phong phongVipTrong = new Phong("01.01", loaiPhongVip, 5, TrangThaiPhong.Trong);
		Phong phongVipDangSD = new Phong("01.01", loaiPhongVip, 5, TrangThaiPhong.DangThue);
		Phong phongVipCho = new Phong("01.01", loaiPhongVip, 5, TrangThaiPhong.DaDat);

		dsPhong.add(phongThuongTrong);
		dsPhong.add(phongThuongDangSD);
		dsPhong.add(phongThuongCho);
		dsPhong.add(phongVipTrong);
		dsPhong.add(phongVipDangSD);
		dsPhong.add(phongVipCho);

		dsPhong.add(phongVipTrong);
		dsPhong.add(phongVipDangSD);
		dsPhong.add(phongVipCho);
		dsPhong.add(phongThuongTrong);
		dsPhong.add(phongThuongDangSD);
		dsPhong.add(phongThuongCho);

		return dsPhong;
	}
}
