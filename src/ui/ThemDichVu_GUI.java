package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.metal.MetalButtonUI;

import components.button.Button;
import components.notification.Notification;
import components.textField.TextField;
import dao.DichVu_DAO;
import dao.LoaiDichVu_DAO;
import entity.DichVu;
import entity.LoaiDichVu;
import utils.Utils;

public class ThemDichVu_GUI extends JPanel implements ItemListener {
	private static final long serialVersionUID = 1L;

	private LoaiDichVu_DAO loaiDichVu_DAO;
	private DichVu_DAO dichVu_DAO;
	private JComboBox<String> cmbLoaiDichVu;
	private JLabel lblTime;
	private JLabel lblThu;
	private JLabel lblDate;
	private Main main;

	public ThemDichVu_GUI(Main jFrame) {
		main = jFrame;
		dichVu_DAO = new DichVu_DAO();
		loaiDichVu_DAO = new LoaiDichVu_DAO();

		setBackground(Utils.secondaryColor);
		setBounds(0, 0, 1086, 508);
		setLayout(null);

		JPanel pnlContainer = new JPanel();
		pnlContainer.setBackground(new Color(203, 239, 255));
		pnlContainer.setBounds(0, 0, 1100, 500);
		this.add(pnlContainer);
		pnlContainer.setLayout(null);

		TextField txtMa = new TextField();
		txtMa.setLabelText("Mã dịch vụ");
		txtMa.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtMa.setBackground(new Color(203, 239, 255));
		txtMa.setBounds(44, 25, 842, 55);
		pnlContainer.add(txtMa);

		TextField txtTen = new TextField();
		txtTen.setLabelText("Tên dịch vụ");
		txtTen.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTen.setBackground(new Color(203, 239, 255));
		txtTen.setBounds(44, 105, 842, 55);
		pnlContainer.add(txtTen);

		TextField txtDonViTinh = new TextField();
		txtDonViTinh.setLabelText("Đơn vị tính");
		txtDonViTinh.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtDonViTinh.setBackground(new Color(203, 239, 255));
		txtDonViTinh.setBounds(44, 195, 371, 55);
		pnlContainer.add(txtDonViTinh);

		TextField txtSoLuong = new TextField();
		txtSoLuong.setLabelText("Số lượng");
		txtSoLuong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtSoLuong.setBackground(new Color(203, 239, 255));
		txtSoLuong.setBounds(516, 195, 371, 55);
		pnlContainer.add(txtSoLuong);

		cmbLoaiDichVu = new JComboBox<String>();
		ArrayList<LoaiDichVu> listLoaiDV = (ArrayList<LoaiDichVu>) loaiDichVu_DAO.getAllLoaiDichVu();
		for (LoaiDichVu loaiDV : listLoaiDV) {
			cmbLoaiDichVu.addItem(loaiDV.getTenLoaiDichVu());
		}
		cmbLoaiDichVu.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbLoaiDichVu.setBackground(Utils.primaryColor);
		cmbLoaiDichVu.setBounds(44, 290, 371, 45);
		pnlContainer.add(cmbLoaiDichVu);

		TextField txtGiaMua = new TextField();
		txtGiaMua.setLabelText("Giá mua");
		txtGiaMua.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtGiaMua.setBackground(new Color(203, 239, 255));
		txtGiaMua.setBounds(516, 285, 371, 50);
		pnlContainer.add(txtGiaMua);

//		Date time

		JPanel pnlDateTime = new JPanel();
		pnlDateTime.setBackground(Utils.secondaryColor);
		pnlDateTime.setBounds(949, 5, 105, 58);
		pnlContainer.add(pnlDateTime);
		pnlDateTime.setLayout(null);

		lblTime = new JLabel("10:30");
		lblTime.setForeground(new Color(0, 0, 0, 115));
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblTime.setBounds(0, 0, 105, 19);
		pnlDateTime.add(lblTime);

		lblThu = new JLabel("T2");
		lblThu.setHorizontalAlignment(SwingConstants.CENTER);
		lblThu.setForeground(new Color(0, 0, 0, 115));
		lblThu.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblThu.setBounds(0, 19, 105, 19);
		pnlDateTime.add(lblThu);

		lblDate = new JLabel("29-09-2022");
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setForeground(new Color(0, 0, 0, 115));
		lblDate.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblDate.setBounds(0, 38, 105, 19);
		pnlDateTime.add(lblDate);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Icon\\clock (1) 1.png"));
		lblNewLabel.setBounds(885, 2, 64, 64);
		pnlContainer.add(lblNewLabel);

		Button btnLuu = new Button("Lưu");
		btnLuu.setUI(new MetalButtonUI() {
			protected Color getDisabledTextColor() {
				return Color.WHITE;
			}
		});
		btnLuu.setIcon(new ImageIcon("Icon\\floppy-disk 1.png"));
		btnLuu.setRadius(8);
		btnLuu.setForeground(Color.WHITE);
		btnLuu.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		btnLuu.setColorOver(new Color(140, 177, 180));
		btnLuu.setColorClick(new Color(140, 177, 180, 204));
		btnLuu.setColor(new Color(140, 177, 180));
		btnLuu.setBorderColor(new Color(203, 239, 255));
		btnLuu.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnLuu.setBounds(280, 420, 250, 50);
		pnlContainer.add(btnLuu);

		btnLuu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String sma = txtMa.getText();
				String sten = txtTen.getText();
				int sSoLuong = Integer.valueOf(txtSoLuong.getText());
				String sDonViTinh = txtDonViTinh.getText();
				double sGiaMua = Double.valueOf(txtGiaMua.getText());
				String sLoaiDichVu = cmbLoaiDichVu.getSelectedItem().toString();
				LoaiDichVu loaiDichVuSelect = loaiDichVu_DAO.getLoaiDichVuTheoTen(sLoaiDichVu);
				if (dichVu_DAO.themDichVu(new DichVu(sma, sten, sSoLuong, sDonViTinh, loaiDichVuSelect, sGiaMua))) {
					new Notification(jFrame, components.notification.Notification.Type.SUCCESS,
							"Đã thêm dịch vụ mới thành công").showNotification();
				}
			}
		});

		Button btnHuy = new Button("Hủy");
		btnHuy.setUI(new MetalButtonUI() {
			protected Color getDisabledTextColor() {
				return Color.WHITE;
			}
		});
		btnHuy.setIcon(new ImageIcon("Icon\\cancelled 1.png"));
		btnHuy.setRadius(8);
		btnHuy.setForeground(Color.WHITE);
		btnHuy.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		btnHuy.setColorOver(new Color(140, 177, 180));
		btnHuy.setColorClick(new Color(140, 177, 180, 204));
		btnHuy.setColor(new Color(140, 177, 180));
		btnHuy.setBorderColor(new Color(203, 239, 255));
		btnHuy.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnHuy.setBounds(570, 420, 250, 50);
		pnlContainer.add(btnHuy);

		btnHuy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				main.backPanel();

			}
		});

		clock();

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() != ItemEvent.SELECTED) {
			return;
		}
	}

	public void clock() {
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
						lblTime.setText(String.format("%s:%s", hour < 10 ? "0" + hour : hour,
								minute < 10 ? "0" + minute : minute));
						LocalDate date = LocalDate.now();
						DayOfWeek dayNow = date.getDayOfWeek();
						String thu = "T2";
						if (dayNow.getValue() == DayOfWeek.TUESDAY.getValue())
							thu = "T3";
						else if (dayNow.getValue() == DayOfWeek.WEDNESDAY.getValue())
							thu = "T4";
						else if (dayNow.getValue() == DayOfWeek.THURSDAY.getValue())
							thu = "T5";
						else if (dayNow.getValue() == DayOfWeek.FRIDAY.getValue())
							thu = "T6";
						else if (dayNow.getValue() == DayOfWeek.SATURDAY.getValue())
							thu = "T7";
						else if (dayNow.getValue() == DayOfWeek.SUNDAY.getValue())
							thu = "CN";
						lblThu.setText(thu);
						lblDate.setText(String.format("%s-%s-%d", day < 10 ? "0" + day : day,
								month < 10 ? "0" + month : month, year));
						sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};

		clock.start();
	}

}
