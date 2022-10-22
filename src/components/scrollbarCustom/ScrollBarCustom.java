package components.scrollbarCustom;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollBar;

public class ScrollBarCustom extends JScrollBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color backgroundColor = Color.WHITE;
	private Dimension scrollbar = new Dimension(8, 8);
	private Color scrollbarColor = Color.RED;

	public ScrollBarCustom() {
		setPreferredSize(scrollbar);
		setForeground(scrollbarColor);
		setBackground(backgroundColor);
		setUI(new ModernScrollBarUI());
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public Dimension getScrollbar() {
		return scrollbar;
	}

	public Color getScrollbarColor() {
		return scrollbarColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		setBackground(backgroundColor);
	}

	public void setScrollbar(Dimension scrollbar) {
		setPreferredSize(scrollbar);
	}

	public void setScrollbarColor(Color scrollbarColor) {
		setForeground(scrollbarColor);
	}

}
