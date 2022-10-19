package entity;

import javax.swing.JPanel;

public class PanelUI {
	private JPanel jPanel;
	private String title;
	private int index;
	private int indexSubmenu;

	public PanelUI(JPanel jPanel, String title, int index, int indexSubmenu) {
		super();
		this.jPanel = jPanel;
		this.title = title;
		this.index = index;
		this.indexSubmenu = indexSubmenu;
	}

	public JPanel getjPanel() {
		return jPanel;
	}

	public void setjPanel(JPanel jPanel) {
		this.jPanel = jPanel;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndexSubmenu() {
		return indexSubmenu;
	}

	public void setIndexSubmenu(int indexSubmenu) {
		this.indexSubmenu = indexSubmenu;
	}

}
