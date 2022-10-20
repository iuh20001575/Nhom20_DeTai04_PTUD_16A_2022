package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import components.button.Button;
import components.jDialog.JDialogCustom;
import components.menu.EventMenuSelected;
import components.menu.Menu;
import connectDB.ConnectDB;
import dao.NhanVien_DAO;
import drawer.Drawer;
import drawer.DrawerController;
import entity.NhanVien;
import entity.PanelUI;
import utils.StackPanel;
import utils.Utils;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Menu menu;
	private Button btnBack;
	private Main _this;
	private DrawerController drawer;
	private JLabel lblTitle;
	private JPanel pnlBody;
	private NhanVien_DAO nhanVien_DAO;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		try {
			new ConnectDB().connect();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		_this = this;
		nhanVien_DAO = new NhanVien_DAO();
		JDialogCustom jDialogCustom = new JDialogCustom(_this);

		jDialogCustom.getBtnOK().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.exit(1);
			};
		});

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
		btnMenu.setFocusable(false);
		btnMenu.setBounds(23, 16, 38, 38);
		btnMenu.setForeground(Utils.primaryColor);
		btnMenu.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		btnMenu.setBorder(BorderFactory.createEmptyBorder());
		btnMenu.setBackground(Color.WHITE);
		btnMenu.setBorderColor(Utils.primaryColor);
		btnMenu.setRadius(8);
		btnMenu.setFocusable(false);
		pnlHeader.add(btnMenu);

		lblTitle = new JLabel("TRANG CHỦ");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setBounds(76, 17, 948, 32);
		lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
		pnlHeader.add(lblTitle);

		btnBack = new Button();
		btnBack.setFocusable(false);
		btnBack.setIcon(new ImageIcon("Icon\\back 1.png"));
		btnBack.setColor(Utils.primaryColor);
		btnBack.setColorOver(Utils.primaryColor);
		btnBack.setColorClick(Utils.primaryColor);
		btnBack.setBorderColor(Utils.primaryColor);
		btnBack.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnBack.setBounds(954, 1, 62, 62);
		pnlHeader.add(btnBack);
//		End Default Layout

		pnlBody = new JPanel();
		pnlBody.setLayout(null);
		pnlBody.setBounds(0, 65, 1086, 508);
		contentPane.add(pnlBody);

//		Code menu
		menu = new Menu();
		drawer = Drawer.newDrawer(this).addChild(menu).build();
		menu.setDrawer(drawer);

//		Show/Hide menu
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawer.show();
			}
		});

		Utils.setMain(this);
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PanelUI jPanel = StackPanel.pop();
				boolean isEmpty = StackPanel.empty();
				StackPanel.push(jPanel);

				if (isEmpty)
					jDialogCustom.showMessage("Đóng ứng dụng", "Bạn có muốn đóng ứng dụng không?");
				else {
					StackPanel.pop();
					PanelUI panelUI = StackPanel.peek();
					if (panelUI.getTitle().equals("Trang chủ")) {
						while (!StackPanel.empty())
							StackPanel.pop();
						StackPanel.push(new PanelUI(new TrangChu_GUI(), "Trang chủ", 0, 0));
					}
					addPnlBody(panelUI);
					menu.setSelectedMenu(panelUI.getIndex(), panelUI.getIndexSubmenu());
				}
			}
		});

		xuLySuKienMenu();
		addPnlBody(new TrangChu_GUI(), "Trang chủ", 0, 0);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
//				String maNhanVien = utils.NhanVien.getNhanVien().getMaNhanVien();
				String maNhanVien = "NV111";
				NhanVien nhanVien = nhanVien_DAO.getNhanVienTheoMa(maNhanVien);
				utils.NhanVien.setNhanVien(nhanVien);
			}
		});
	}

	/**
	 * Xử lý sự kiện khi nhấn vào menu item
	 */
	private void xuLySuKienMenu() {
		menu.addEvent(new EventMenuSelected() {

			@Override
			public void menuSelected(int index, int indexSubMenu) {
				JPanel pnl;
				String title;

				if (index == 1 && indexSubMenu == 0) {
					title = "Quản lý nhân viên";
					pnl = new QuanLyNhanVien_GUI(_this);
				} else if (index == 2 && indexSubMenu == 0) {
					title = "Quản lý khách hàng";
					pnl = new QuanLyKhachHang_GUI(_this);
				} else if (index == 3 && indexSubMenu == 0) {
					title = "Quản lý đặt phòng";
					pnl = new QuanLyDatPhong_GUI(_this);
				} else if (index == 4 && indexSubMenu == 0) {
					title = "Quản lý đặt phòng trước";
					pnl = new QuanLyPhieuDatPhong_GUI();
				} else if (index == 5 && indexSubMenu == 1) {
					title = "Thống kê doanh thu";
					pnl = new ThongKeDoanhThu_GUI();
				} else if (index == 5 && indexSubMenu == 2) {
					title = "Thống kê hóa đơn";
					pnl = new ThongKeHoaDon_GUI();
				} else if (index == 5 && indexSubMenu == 3) {
					title = "Thống kê khách hàng";
					pnl = new ThongKeKhachHang_GUI();
				} else if (index == 6 && indexSubMenu == 0) {
					title = "Quản lý đặt phòng trước";
					pnl = new ThongTinCaNhan_GUI();
				} else {
					title = "Trang chủ";
					pnl = new TrangChu_GUI();
				}

				addPnlBody(pnl, title, index, indexSubMenu);
			}
		});
	}

	/**
	 * Thay đổi phần container UI
	 * 
	 * @param panelUI panel UI
	 */
	public void addPnlBody(PanelUI panelUI) {
		pnlBody.removeAll();
		pnlBody.add(panelUI.getjPanel());
		pnlBody.repaint();
		pnlBody.revalidate();
		setTitle(panelUI.getTitle());
	}

	/**
	 * Thay đổi phần container UI
	 * 
	 * @param pnl          panel cần thay đổi
	 * @param title        title của trang
	 * @param index        index menu
	 * @param indexSubmenu index submenu
	 */
	public void addPnlBody(JPanel pnl, String title, int index, int indexSubmenu) {
		PanelUI panelUI = new PanelUI(pnl, title, index, indexSubmenu);
		menu.setSelectedMenu(index, indexSubmenu);
		addPnlBody(panelUI);
		StackPanel.push(panelUI);
	}

	@Override
	public void setTitle(String title) {
		super.setTitle(title);
		lblTitle.setText(title.toUpperCase());
	}
}
