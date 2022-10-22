package components.menu;

import javaswingdev.GoogleMaterialDesignIcon;

public class ModelMenuItem {
	private GoogleMaterialDesignIcon icon;
	private String menuName;
	private String subMenu[];

	public ModelMenuItem() {
	}

	public ModelMenuItem(GoogleMaterialDesignIcon icon, String menuName, String... subMenu) {
		this.icon = icon;
		this.menuName = menuName;
		this.subMenu = subMenu;
	}

	public GoogleMaterialDesignIcon getIcon() {
		return icon;
	}

	public String getMenuName() {
		return menuName;
	}

	public String[] getSubMenu() {
		return subMenu;
	}

	public void setIcon(GoogleMaterialDesignIcon icon) {
		this.icon = icon;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public void setSubMenu(String[] subMenu) {
		this.subMenu = subMenu;
	}
}
