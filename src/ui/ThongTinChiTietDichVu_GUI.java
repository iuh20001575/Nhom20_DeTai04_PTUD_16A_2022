package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

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
import components.notification.Notification.Type;
import components.textField.TextField;

import utils.Utils;

import entity.DichVu;
import entity.LoaiDichVu;
import entity.NhanVien;
import entity.TaiKhoan;
import dao.LoaiDichVu_DAO;
import dao.DichVu_DAO;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ThongTinChiTietDichVu_GUI extends JPanel implements ItemListener {
	private static final long serialVersionUID = 1L;

	private LoaiDichVu_DAO loaiDichVu_DAO;
	private DichVu dichVu;
	private DichVu_DAO dichVu_DAO;
	private JComboBox<String> cmbLoaiDichVu;
	private JLabel lblTime;
	private JLabel lblThu;
	private JLabel lblDate;
	private Main main;
	private TextField txtMa, txtTen, txtDonViTinh, txtSoLuong, txtGiaMua;

	public ThongTinChiTietDichVu_GUI(Main jFrame, DichVu dichVu, boolean isCapNhat) {

		main = jFrame;
		dichVu_DAO = new DichVu_DAO();
		loaiDichVu_DAO = new LoaiDichVu_DAO();

		setBackground(Utils.secondaryColor);
		setBounds(0, 0, 1086, 508);
		setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(203, 239, 255));
		panel_1.setBounds(0, 0, 1100, 500);
		this.add(panel_1);
		panel_1.setLayout(null);

		txtMa = new TextField();
		txtMa.setLabelText("Mã dịch vụ:");
		txtMa.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtMa.setBackground(new Color(203, 239, 255));
		txtMa.setBounds(44, 25, 842, 55);
		panel_1.add(txtMa);

		txtTen = new TextField();
		txtTen.setLabelText("Tên dịch vụ:");
		txtTen.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTen.setBackground(new Color(203, 239, 255));
		txtTen.setBounds(44, 105, 842, 55);
		panel_1.add(txtTen);

		txtDonViTinh = new TextField();
		txtDonViTinh.setLabelText("Đơn vị tính:");
		txtDonViTinh.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtDonViTinh.setBackground(new Color(203, 239, 255));
		txtDonViTinh.setBounds(44, 195, 371, 55);
		panel_1.add(txtDonViTinh);

		txtSoLuong = new TextField();
		txtSoLuong.setLabelText("Số lượng:");
		txtSoLuong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtSoLuong.setBackground(new Color(203, 239, 255));
		txtSoLuong.setBounds(516, 195, 371, 55);
		panel_1.add(txtSoLuong);

		cmbLoaiDichVu = new JComboBox<String>();
		ArrayList<LoaiDichVu> listLoaiDV = (ArrayList<LoaiDichVu>) loaiDichVu_DAO.getAllLoaiDichVu();
		for (LoaiDichVu loaiDV : listLoaiDV) {
			cmbLoaiDichVu.addItem(loaiDV.getTenLoaiDichVu());
		}
		cmbLoaiDichVu.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbLoaiDichVu.setBackground(Utils.primaryColor);
		cmbLoaiDichVu.setBounds(44, 290, 371, 45);
		panel_1.add(cmbLoaiDichVu);

		txtGiaMua = new TextField();
		txtGiaMua.setLabelText("Giá mua:");
		txtGiaMua.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtGiaMua.setBackground(new Color(203, 239, 255));
		txtGiaMua.setBounds(516, 285, 371, 50);
		panel_1.add(txtGiaMua);

//		Date time

		JPanel pnlDateTime = new JPanel();
		pnlDateTime.setBackground(Utils.secondaryColor);
		pnlDateTime.setBounds(949, 5, 105, 58);
		panel_1.add(pnlDateTime);
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
		panel_1.add(lblNewLabel);

		Button btnCapNhat = new Button("Cập nhật");
		btnCapNhat.setIcon(new ImageIcon("Icon\\edit 1.png"));
		btnCapNhat.setFocusable(false);
		btnCapNhat.setRadius(8);
		btnCapNhat.setBorderColor(Utils.secondaryColor);
		btnCapNhat.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnCapNhat.setColor(Utils.primaryColor);
		btnCapNhat.setColorOver(Utils.primaryColor);
		btnCapNhat.setColorClick(Utils.getOpacity(Utils.primaryColor, 0.8f));
		btnCapNhat.setForeground(Color.WHITE);
		btnCapNhat.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		btnCapNhat.setBounds(200, 420, 250, 50);
		panel_1.add(btnCapNhat);

		Button btnHuy = new Button("Hủy");
		btnHuy.setVisible(false);
		btnHuy.setIcon(new ImageIcon("Icon\\cancelled 1.png"));
		btnHuy.setFocusable(false);
		btnHuy.setRadius(8);
		btnHuy.setBorderColor(Utils.secondaryColor);
		btnHuy.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnHuy.setBackground(Utils.primaryColor, 1, 0.8f);
		btnHuy.setForeground(Color.WHITE);
		btnHuy.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		btnHuy.setBounds(200, 420, 250, 50);
		panel_1.add(btnHuy);

		Button btnLuu = new Button("Lưu");
		btnLuu.setUI(new MetalButtonUI() {
			protected Color getDisabledTextColor() {
				return Color.WHITE;
			}
		});
		btnLuu.setEnabled(false);
		btnLuu.setIcon(new ImageIcon("Icon\\floppy-disk 1.png"));
		btnLuu.setRadius(8);
		btnLuu.setForeground(Color.WHITE);
		btnLuu.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		btnLuu.setColorOver(new Color(140, 177, 180));
		btnLuu.setColorClick(new Color(140, 177, 180, 204));
		btnLuu.setColor(new Color(140, 177, 180));
		btnLuu.setBorderColor(new Color(203, 239, 255));
		btnLuu.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnLuu.setBounds(500, 420, 250, 50);
		panel_1.add(btnLuu);
		
		setEnabledForm(false);
		
		this.dichVu = dichVu;
		setDichVuVaoForm(dichVu);
		

//		Sự kiện nút cập nhật
		btnCapNhat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnCapNhat.setVisible(false);
				btnHuy.setVisible(true);
				btnLuu.setEnabled(true);
				setEnabledForm(true);
			}
		});

//		Sự kiện nút hủy
		btnHuy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// setErrorAllJTextField(false);
				// setDichVuVaoForm(dichVu);
				btnCapNhat.setVisible(true);
				btnHuy.setVisible(false);
				btnLuu.setEnabled(false);
				setEnabledForm(false);
				ThongTinChiTietDichVu_GUI.this.main.repaint();
			}
		});

//		Sự kiện nút lưu
		btnLuu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!btnLuu.isEnabled())
					return;
//				if (!validator())
//					return;

				DichVu dichVu = getDichVuTuForm();
				boolean res = dichVu_DAO.suaDichVu(dichVu);
				
				if (res) {
					new Notification(jFrame, components.notification.Notification.Type.SUCCESS,
							"Cập nhật dịch vụ thành công").showNotification();
					btnCapNhat.setVisible(true);
					btnHuy.setVisible(false);
					btnLuu.setEnabled(false);
					setEnabledForm(false);
					ThongTinChiTietDichVu_GUI.this.main.repaint();
				}else {
					new Notification(main, Type.ERROR, "Cập nhật thông tin dịch vụ thất bại").showNotification();
				}
			}
		});

		clock();

	}

	@SuppressWarnings("unchecked")
	private <E> JComboBox<E> resizeComboBox(JComboBox<E> list) {
		list.removeAllItems();
		// list.addItem((E) firstLabel);
		return list;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		Object object = e.getSource();
		if (e.getStateChange() != ItemEvent.SELECTED) {
			return;
		}
	}

	/**
	 * Get dịch vụ từ form
	 * 
	 * @return dịch vụ
	 */
	private DichVu getDichVuTuForm() {
		String maDichVu = txtMa.getText();
		String tenDichVu = txtTen.getText().trim();
		String donViTinh = txtDonViTinh.getText().trim();
		String soLuong = txtSoLuong.getText().trim();
		String giaMua = txtGiaMua.getText().trim();
		String tenLoaiDichVu = cmbLoaiDichVu.getSelectedItem().toString();
		LoaiDichVu loaiDichVu = loaiDichVu_DAO.getLoaiDichVuTheoTen(tenLoaiDichVu);
		return new DichVu(maDichVu, tenDichVu, Integer.parseInt(soLuong), donViTinh, loaiDichVu ,Double.parseDouble(giaMua));
	}
	
	/**
	 * Set dịch vụ vào form
	 * 
	 * @param dichVu
	 */
	private void setDichVuVaoForm(DichVu dichVu) {
		txtMa.setText(dichVu.getMaDichVu());
		txtTen.setText(dichVu.getTenDichVu());
		txtSoLuong.setText(String.valueOf(dichVu.getSoLuong()));
		txtDonViTinh.setText(dichVu.getDonViTinh());
		txtGiaMua.setText(String.valueOf(dichVu.getGiaMua()));
		cmbLoaiDichVu.setSelectedItem(dichVu.getLoaiDichVu().getTenLoaiDichVu());
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
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};

		clock.start();
	}

	/**
	 * set Enabled input form
	 * 
	 * @param b
	 */
	private void setEnabledForm(boolean b) {
		txtMa.setEnabled(b);
		txtSoLuong.setEnabled(b);
		txtTen.setEnabled(b);
		txtDonViTinh.setEnabled(b);
		txtGiaMua.setEnabled(b);
		cmbLoaiDichVu.setEnabled(b);
	}
}
