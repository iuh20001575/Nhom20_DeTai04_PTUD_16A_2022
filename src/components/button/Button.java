package components.button;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import utils.Utils;

/**
 * Custom Button
 *
 */
public class Button extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color borderBtnColor = Color.red;
	private Color borderColor;
	private int borderWidth = 0;
	private Color color;
	private Color colorClick;
	private Color colorOver;
	private boolean over;
	private int radius = 0;
	private Color colorTextOver;
	private Color colorTextOut;

	public Button() {
//		Khởi tạo màu sắc
		setColor(Color.WHITE);
		colorOver = new Color(255, 255, 255);
		colorClick = new Color(226, 231, 255);
		borderColor = new Color(255, 255, 255);
		colorTextOver = getForeground();
		colorTextOut = getForeground();
		setContentAreaFilled(false);

//		Lắng nghe sự kiện
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent me) {
				if (isEnabled()) {
					setBackground(colorOver);
					setForeground(colorTextOver);
					over = true;
				}
			}

			@Override
			public void mouseExited(MouseEvent me) {
				setBackground(color);
				setForeground(colorTextOut);
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

	public Button(String text) {
		this();
		setText(text);
	}

	@Override
	public void setForeground(Color fg) {
		super.setForeground(fg);
		colorTextOver = fg;
		colorTextOut = fg;
	}

	/**
	 * Get border color
	 * 
	 * @return border color
	 */
	public Color getBorderBtnColor() {
		return borderBtnColor;
	}

	/**
	 * Get margin background color
	 * 
	 * @return margin background color
	 */
	public Color getBorderColor() {
		return borderColor;
	}

	/**
	 * Get độ rộng của border
	 * 
	 * @return độ rộng của border
	 */
	public int getBorderWidth() {
		return borderWidth;
	}

	/**
	 * Get background color của button
	 * 
	 * @return background color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Get background color khi click button
	 * 
	 * @return background color khi click button
	 */
	public Color getColorClick() {
		return colorClick;
	}

	/**
	 * Get background color khi button hover
	 * 
	 * @return background color button hover
	 */
	public Color getColorOver() {
		return colorOver;
	}

	/**
	 * Get border-radius
	 * 
	 * @return border-radius
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * Hover Button
	 * 
	 * @return true nếu button được hover
	 * @return false nếu button không hover
	 */
	public boolean isOver() {
		return over;
	}

	@Override
	protected void paintComponent(Graphics grphcs) {
		Graphics2D g2 = (Graphics2D) grphcs;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

//		Vẽ margin
		g2.setColor(borderColor);
		g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

//		Vẽ border
		if (borderWidth > 0) {
			g2.setColor(borderBtnColor);
			g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, radius, radius);

			g2.setColor(borderColor);
			g2.fillRoundRect(2 + borderWidth, 2 + borderWidth, getWidth() - 4 - borderWidth * 2,
					getHeight() - 4 - borderWidth * 2, radius, radius);
		}

//		Vẽ background button
//		Margin default 2px
		g2.setColor(isEnabled() ? getBackground() : Utils.getOpacity(getBackground(), 0.4f));
		g2.fillRoundRect(2 + borderWidth, 2 + borderWidth, getWidth() - 4 - borderWidth * 2,
				getHeight() - 4 - borderWidth * 2, radius, radius);
		super.paintComponent(grphcs);
	}

	/**
	 * Set border color
	 * 
	 * @param borderBtnColor border color
	 */
	public void setBorderBtnColor(Color borderBtnColor) {
		this.borderBtnColor = borderBtnColor;
	}

	/**
	 * Set margin background color
	 * 
	 * @param borderColor margin background color
	 */
	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	/**
	 * Set độ rộng của border
	 * 
	 * @param borderWidth độ rộng của border
	 */
	public void setBorderWidth(int borderWidth) {
		this.borderWidth = borderWidth;
	}

	/**
	 * Set background color cho button
	 * 
	 * @param color background color
	 */
	public void setColor(Color color) {
		this.color = color;
		setBackground(color);
	}

	/**
	 * Set background color khi click
	 * 
	 * @param colorClick background color
	 */
	public void setColorClick(Color colorClick) {
		this.colorClick = colorClick;
	}

	/**
	 * Set background color khi button hover
	 * 
	 * @param colorOver
	 */
	public void setColorOver(Color colorOver) {
		this.colorOver = colorOver;
	}

	/**
	 * Set trạng thái hover cho button
	 * 
	 * @param over true nếu button được hover, false nếu button không được hover
	 */
	public void setOver(boolean over) {
		this.over = over;
	}

	public void setColorTextOver(Color colorTextOver) {
		this.colorTextOver = colorTextOver;
	}

	public void setColorTextOut(Color colorTextOut) {
		this.colorTextOut = colorTextOut;
	}

	/**
	 * Get border-radius
	 * 
	 * @param radius border-radius
	 */
	public void setRadius(int radius) {
		this.radius = radius;
	}

	public void setBackground(Color background, Color backgroundOver, Color backgroundClick) {
		setColor(background);
		setColorClick(backgroundClick);
		setColorOver(backgroundOver);
	}

	public void setBackground(Color background, float opacityOver, float opacityClick) {
		setColor(background);
		setColorClick(Utils.getOpacity(background, opacityClick));
		setColorOver(Utils.getOpacity(background, opacityOver));
	}
}
