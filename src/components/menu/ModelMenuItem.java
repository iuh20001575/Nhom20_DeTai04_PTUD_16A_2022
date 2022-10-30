package components.menu;

import javax.swing.Icon;

public class ModelMenuItem {
	private Icon icon;
	private String menuName;
	private String subMenu[];

	public ModelMenuItem() {
	}

	public ModelMenuItem(Icon icon, String menuName, String... subMenu) {
		this.icon = icon;
		this.menuName = menuName;
		this.subMenu = subMenu;
	}

	public Icon getIcon() {
		return icon;
	}

	public String getMenuName() {
		return menuName;
	}

	public String[] getSubMenu() {
		return subMenu;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public void setSubMenu(String[] subMenu) {
		this.subMenu = subMenu;
	}
}
