package components.button;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import utils.Utils;

public class Button extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public boolean isOver() {
		return over;
	}

	public void setOver(boolean over) {
		this.over = over;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
		setBackground(color);
	}

	public Color getColorOver() {
		return colorOver;
	}

	public void setColorOver(Color colorOver) {
		this.colorOver = colorOver;
	}

	public Color getColorClick() {
		return colorClick;
	}

	public void setColorClick(Color colorClick) {
		this.colorClick = colorClick;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public Button() {
		// Init Color

		setColor(Color.WHITE);
		colorOver = new Color(255, 255, 255);
		colorClick = new Color(226, 231, 255);
		borderColor = new Color(255, 255, 255);
		setContentAreaFilled(false);
		// Add event mouse
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent me) {
				setBackground(colorOver);
				over = true;
			}

			@Override
			public void mouseExited(MouseEvent me) {
				setBackground(color);
				over = false;

			}

			@Override
			public void mousePressed(MouseEvent me) {
				if (isEnabled())
					setBackground(colorClick);
			}

			@Override
			public void mouseReleased(MouseEvent me) {
				if (over) {
					setBackground(colorOver);
				} else {
					setBackground(color);
				}
			}
		});
	}

	public Color getBorderBtnColor() {
		return borderBtnColor;
	}

	public void setBorderBtnColor(Color borderBtnColor) {
		this.borderBtnColor = borderBtnColor;
	}

	public int getBorderWidth() {
		return borderWidth;
	}

	public void setBorderWidth(int borderWidth) {
		this.borderWidth = borderWidth;
	}

	public Button(String text) {
		this();
		setText(text);
	}

	private boolean over;
	private Color color;
	private Color colorOver;
	private Color colorClick;
	private Color borderColor;
	private Color borderBtnColor = Color.red;
	private int radius = 0;
	private int borderWidth = 0;

	@Override
	protected void paintComponent(Graphics grphcs) {
		Graphics2D g2 = (Graphics2D) grphcs;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		// Paint Border
		g2.setColor(borderColor);
		g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

		if (borderWidth > 0) {
			g2.setColor(borderBtnColor);
			g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, radius, radius);

			g2.setColor(borderColor);
			g2.fillRoundRect(2 + borderWidth, 2 + borderWidth, getWidth() - 4 - borderWidth * 2,
					getHeight() - 4 - borderWidth * 2, radius, radius);
		}

		g2.setColor(isEnabled() ? getBackground() : Utils.getOpacity(getBackground(), 0.4f));
		// Border set 2 Pix
		g2.fillRoundRect(2 + borderWidth, 2 + borderWidth, getWidth() - 4 - borderWidth * 2,
				getHeight() - 4 - borderWidth * 2, radius, radius);
		super.paintComponent(grphcs);
	}
}
