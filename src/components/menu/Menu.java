package components.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import components.scrollbarCustom.ScrollBarCustom;
import drawer.DrawerController;
import javaswingdev.GoogleMaterialDesignIcon;
import net.miginfocom.swing.MigLayout;

public class Menu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int index = -1;
	private final List<EventMenuSelected> events = new ArrayList<>();
	private DrawerController drawer;

	public Menu() {
		init();
	}

	private void init() {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		JScrollPane scroll = createScroll();
		panelMenu = createPanelMenu();
		scroll.setViewportView(panelMenu);
		scroll.getViewport().setOpaque(false);
		scroll.setViewportBorder(null);
		add(scroll);
	}

	private JScrollPane createScroll() {
		JScrollPane scroll = new JScrollPane();
		scroll.setBorder(null);
//		Set background menu
		scroll.setBackground(Color.white);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBar(new ScrollBarCustom());
		return scroll;
	}

	private JPanel createPanelMenu() {
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		menuLayout = new MigLayout("wrap,fillx,inset 0,gapy 0", "[fill]");
		panel.setLayout(menuLayout);

		return panel;
	}

	private JPanel createMenuItem(ModelMenuItem item) {
		MenuItem menuItem = new MenuItem(item, ++index, menuLayout);
		menuItem.setDrawer(drawer);
		menuItem.addEvent(new EventMenuSelected() {
			@Override
			public void menuSelected(int index, int indexSubMenu) {
				if (!menuItem.isHasSubMenu() || indexSubMenu != 0) {
					clearSelected();
					setSelectedIndex(index, indexSubMenu);
				}
			}
		});
		return menuItem;
	}

	private void runEvent(int index, int indexSubMenu) {
		for (EventMenuSelected event : events) {
			event.menuSelected(index, indexSubMenu);
		}
	}

	// Public Method
	public void addMenuItem(ModelMenuItem menu) {
		panelMenu.add(createMenuItem(menu), "h 35!");
	}

	public void addTitle(String title) {
		JLabel label = new JLabel(title);
		label.setBorder(new EmptyBorder(15, 20, 5, 5));
		label.setFont(label.getFont().deriveFont(Font.BOLD));
//		Color text title
		label.setForeground(new Color(170, 170, 170));
		panelMenu.add(label);
	}

	public void addSpace(int size) {
		panelMenu.add(new JLabel(), "h " + size + "!");
	}

	public void setSelectedIndex(int index, int indexSubMenu) {
		for (Component com : panelMenu.getComponents()) {
			if (com instanceof MenuItem) {
				MenuItem item = (MenuItem) com;
				if (item.getIndex() == index) {
					item.setSelectedIndex(indexSubMenu);
					runEvent(index, indexSubMenu);
					break;
				}
			}
		}
	}

	public void setSelectedMenu(int index, int indexSubMenu) {
		clearSelected();
		for (Component com : panelMenu.getComponents()) {
			if (com instanceof MenuItem) {
				MenuItem item = (MenuItem) com;
				if (item.getIndex() == index) {
					item.setSelectedIndex(indexSubMenu);
					break;
				}
			}
		}
	}

	public void clearSelected() {
		for (Component com : panelMenu.getComponents()) {
			if (com instanceof MenuItem) {
				MenuItem item = (MenuItem) com;
				item.clearSelected();
			}
		}
	}

	public void addEvent(EventMenuSelected event) {
		events.add(event);
	}

	public DrawerController getDrawer() {
		return drawer;
	}

	public void setDrawer(DrawerController drawer) {
		this.drawer = drawer;

		addTitle("MAIN");
		addMenuItem(new ModelMenuItem(GoogleMaterialDesignIcon.HOME, "Trang chủ"));
		addMenuItem(new ModelMenuItem(GoogleMaterialDesignIcon.DASHBOARD, "Quản lý nhân viên"));
		addMenuItem(new ModelMenuItem(GoogleMaterialDesignIcon.DASHBOARD, "Quản lý khách hàng"));
		addMenuItem(new ModelMenuItem(GoogleMaterialDesignIcon.DASHBOARD, "Quản lý đặt phòng"));
		addMenuItem(new ModelMenuItem(GoogleMaterialDesignIcon.DASHBOARD, "Quản lý đặt phòng trước"));
		addMenuItem(new ModelMenuItem(GoogleMaterialDesignIcon.DASHBOARD, "Thống kê", "Doanh thu", "Hóa đơn", "Khách hàng"));
		addMenuItem(new ModelMenuItem(GoogleMaterialDesignIcon.DASHBOARD, "Thông tin cá nhân"));
		setPreferredSize(new Dimension(getPreferredSize().width, 610));
	}

	private MigLayout menuLayout;
	private JPanel panelMenu;
}
