package components.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import components.drawer.DrawerController;
import components.scrollbarCustom.ScrollBarCustom;
import net.miginfocom.swing.MigLayout;

public class Menu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DrawerController drawer;
	private final List<EventMenuSelected> events = new ArrayList<>();
	private int index = -1;
	private MigLayout menuLayout;
	private JPanel pnlMenu;
	private List<List<String>> menu;

	public List<List<String>> getMenu() {
		return menu;
	}

	public Menu() {
		init();
		menu = new ArrayList<>();
	}

	public void addEvent(EventMenuSelected event) {
		events.add(event);
	}

	// Public Method
	public void addMenuItem(ModelMenuItem menu) {
		pnlMenu.add(createMenuItem(menu), "h 35!");
	}

	public void addSpace(int size) {
		pnlMenu.add(new JLabel(), "h " + size + "!");
	}

	public void addTitle(String title) {
		JLabel label = new JLabel(title);
		label.setBorder(new EmptyBorder(15, 20, 5, 5));
		label.setFont(label.getFont().deriveFont(Font.BOLD));
//		Color text title
		label.setForeground(new Color(170, 170, 170));
		pnlMenu.add(label);
	}

	public void clearSelected() {
		for (Component com : pnlMenu.getComponents()) {
			if (com instanceof MenuItem) {
				MenuItem item = (MenuItem) com;
				item.clearSelected();
			}
		}
	}

	private JPanel createMenuItem(ModelMenuItem item) {
		MenuItem menuItem = new MenuItem(item, ++index, menuLayout);

		List<String> subMenu = new ArrayList<>();
		subMenu.add(item.getMenuName());
		if (menuItem.isHasSubMenu())
			subMenu.addAll(Arrays.asList(item.getSubMenu()));

		menu.add(subMenu);

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

	private JPanel createPanelMenu() {
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		menuLayout = new MigLayout("wrap,fillx,inset 0,gapy 0", "[fill]");
		panel.setLayout(menuLayout);

		return panel;
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

	public DrawerController getDrawer() {
		return drawer;
	}

	private void init() {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		JScrollPane scroll = createScroll();
		pnlMenu = createPanelMenu();
		scroll.setViewportView(pnlMenu);
		scroll.getViewport().setOpaque(false);
		scroll.setViewportBorder(null);
		add(scroll);
	}

	private void runEvent(int index, int indexSubMenu) {
		for (EventMenuSelected event : events) {
			event.menuSelected(index, indexSubMenu);
		}
	}

	public void setDrawer(DrawerController drawer) {
		this.drawer = drawer;
	}

	public void setSelectedIndex(int index, int indexSubMenu) {
		for (Component com : pnlMenu.getComponents()) {
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
		for (Component com : pnlMenu.getComponents()) {
			if (com instanceof MenuItem) {
				MenuItem item = (MenuItem) com;
				if (item.getIndex() == index) {
					item.setSelectedIndex(indexSubMenu);
					break;
				}
			}
		}
	}
}
