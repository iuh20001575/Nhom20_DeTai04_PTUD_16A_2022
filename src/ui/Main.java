package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import components.button.Button;
import components.drawer.Drawer;
import components.drawer.DrawerController;
import components.jDialog.JDialogCustom;
import components.menu.EventMenuSelected;
import components.menu.Menu;
import components.menu.ModelMenuItem;
import dao.NhanVien_DAO;
import entity.NhanVien;
import entity.NhanVien.ChucVu;
import entity.PanelUI;
import utils.StackPanel;
import utils.Utils;

public class Main extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Main _this;
	private Button btnBack;
	private DrawerController drawer;
	private Menu footer;
	private JLabel lblTitle;
	private Menu menu;
	private NhanVien_DAO nhanVien_DAO;
	private JPanel pnlBody;
	private JPanel pnlContent;

	/**
	 * Create the frame.
	 */
	public Main() {
		_this = this;
		nhanVien_DAO = new NhanVien_DAO();
		JDialogCustom jDialogCustom = new JDialogCustom(_this);

		jDialogCustom.getBtnOK().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(1);
			}
		});

		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(0, 0, 1100, 610);
		setLocationRelativeTo(null);

		pnlContent = new JPanel();
		pnlContent.setForeground(Color.GRAY);
		pnlContent.setBackground(Utils.secondaryColor);
		setContentPane(pnlContent);
		pnlContent.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlContent.setLayout(null);

		JPanel pnlHeader = new JPanel();
		pnlHeader.setBackground(Utils.primaryColor);
		pnlHeader.setBounds(0, 0, 1086, 65);
		pnlContent.add(pnlHeader);
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
		pnlContent.add(pnlBody);

		String maNhanVien = utils.NhanVien.getNhanVien().getMaNhanVien();
		NhanVien nhanVien = nhanVien_DAO.getNhanVienTheoMa(maNhanVien);
		utils.NhanVien.setNhanVien(nhanVien);
		ChucVu chucVu = nhanVien.getChucVu();

//		Code menu
		menu = new Menu();
		footer = new Menu();
		drawer = Drawer.newDrawer(this).addChild(menu).addFooter(footer).build();

		menu.setDrawer(drawer);
		menu.addMenuItem(new ModelMenuItem(new ImageIcon("Icon\\homeIcon.png"), Utils.trangChuMenuItem));
		if (chucVu.equals(NhanVien.ChucVu.QuanLy))
			menu.addMenuItem(new ModelMenuItem(new ImageIcon("Icon\\businessman.png"), Utils.nhanVienMenuItem,
					Utils.quanLyNhanVienMenuItem, Utils.themNhanVienMenuItem));
		menu.addMenuItem(new ModelMenuItem(new ImageIcon("Icon\\users-avatar.png"), Utils.quanLyKhachHangMenuItem));
		menu.addMenuItem(new ModelMenuItem(new ImageIcon("Icon\\doorMenuItem.png"), Utils.quanLyPhongMenuItem));
		menu.addMenuItem(new ModelMenuItem(new ImageIcon("Icon\\doorMenuItem.png"), Utils.quanLyDatPhongMenuItem));
		menu.addMenuItem(new ModelMenuItem(new ImageIcon("Icon\\doorMenuItem.png"), Utils.quanLyDatPhongTruocMenuItem));
		menu.addMenuItem(new ModelMenuItem(new ImageIcon("Icon\\bar-graph.png"), Utils.thongKeMenuItem,
				Utils.thongKeDoanhThuMenuItem, Utils.thongKeHoaDonMenuItem, Utils.thongKeKhachHangMenuItem));
		menu.addMenuItem(new ModelMenuItem(new ImageIcon("Icon\\user.png"), Utils.thongTinCaNhanMenuItem));
		menu.setPreferredSize(new Dimension(getPreferredSize().width, 473));

		footer.setDrawer(drawer);
		footer.addMenuItem(new ModelMenuItem(new ImageIcon("Icon\\logout.png"), Utils.dangXuatMenuItem));
		footer.addMenuItem(new ModelMenuItem(new ImageIcon("Icon\\power.png"), Utils.thoatMenuItem));
		footer.setPreferredSize(new Dimension(getPreferredSize().width, 70));

//		Show/Hide menu
		btnMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawer.show();
			}
		});

		Utils.setMain(this);
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PanelUI pnl = StackPanel.pop();
				boolean isEmpty = StackPanel.empty();

				if (pnl.getTitle().equals("Trang chủ"))
					while (!StackPanel.empty())
						StackPanel.pop();

				StackPanel.push(pnl);

				if (isEmpty)
					jDialogCustom.showMessage("Đóng ứng dụng", "Bạn có muốn đóng ứng dụng không?");
				else {
					backPanel();
				}
			}
		});

		xuLySuKienMenu();
		addPnlBody(new TrangChu_GUI(), "Trang chủ", 0, 0);
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

	public void backPanel() {
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

	public Menu getMenu() {
		return menu;
	}

	@Override
	public void repaint() {
		pnlBody.repaint();
		pnlBody.revalidate();
	}

	@Override
	public void setTitle(String title) {
		super.setTitle(title);
		lblTitle.setText(title.toUpperCase());
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

				List<String> list = menu.getMenu().get(index);
				String titleMenu = list.get(indexSubMenu);

				if (titleMenu.equals(Utils.quanLyNhanVienMenuItem)) {
					title = "Quản lý nhân viên";
					pnl = new QuanLyNhanVien_GUI(_this);
				} else if (titleMenu.equals(Utils.themNhanVienMenuItem)) {
					title = "Thên nhân viên";
					pnl = new ThemNhanVien_GUI(_this);
				} else if (titleMenu.equals(Utils.quanLyKhachHangMenuItem)) {
					title = "Quản lý khách hàng";
					pnl = new QuanLyKhachHang_GUI(_this);
				} else if (titleMenu.equals(Utils.quanLyPhongMenuItem)) {
					title = "Quản lý phòng";
					pnl = new QuanLyPhong_GUI(_this);
				} else if (titleMenu.equals(Utils.quanLyDatPhongMenuItem)) {
					title = "Quản lý đặt phòng";
					pnl = new QuanLyDatPhong_GUI(_this);
				} else if (titleMenu.equals(Utils.quanLyDatPhongTruocMenuItem)) {
					title = "Quản lý đặt phòng trước";
					pnl = new QuanLyPhieuDatPhong_GUI();
				} else if (titleMenu.equals(Utils.thongKeDoanhThuMenuItem)) {
					title = "Thống kê doanh thu";
					pnl = new ThongKeDoanhThu_GUI();
				} else if (titleMenu.equals(Utils.thongKeHoaDonMenuItem)) {
					title = "Thống kê hóa đơn";
					pnl = new ThongKeHoaDon_GUI();
				} else if (titleMenu.equals(Utils.thongKeKhachHangMenuItem)) {
					title = "Thống kê khách hàng";
					pnl = new ThongKeKhachHang_GUI();
				} else if (titleMenu.equals(Utils.thongTinCaNhanMenuItem)) {
					title = "Thông tin cá nhân";
					pnl = new ThongTinCaNhan_GUI(_this);
				} else {
					title = "Trang chủ";
					pnl = new TrangChu_GUI();
				}

				addPnlBody(pnl, title, index, indexSubMenu);
			}
		});

		footer.addEvent(new EventMenuSelected() {

			@Override
			public void menuSelected(int index, int indexSubMenu) {
				footer.clearSelected();
				JDialogCustom jDialogCustom = new JDialogCustom(_this);
				if (index == 1 && indexSubMenu == 0) {
					jDialogCustom.getBtnOK().addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							System.exit(0);
						}
					});

					jDialogCustom.showMessage("Thoát ứng dụng", "Bạn có chắc chắn muốn thoát ứng dụng không?");
				} else {
					jDialogCustom.getBtnOK().addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							utils.NhanVien.setNhanVien(null);
							new DangNhap_GUI().setVisible(true);
							setVisible(false);
						}
					});

					jDialogCustom.showMessage("Đăng xuất", "Bạn có chắc chắn muốn đăng xuất không?");
				}
			}
		});
	}
}
