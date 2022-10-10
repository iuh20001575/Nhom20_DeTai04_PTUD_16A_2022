package components.radio;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JRadioButton;

import utils.Utils;

public class RadioButtonCustom extends JRadioButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color radioColor = new Color(149, 166, 248);

	public RadioButtonCustom() {
		setOpaque(false);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setBackground(radioColor);
	}

	public RadioButtonCustom(String text) {
		this();
		setText(text);
	}

	public Color getRadioColor() {
		return radioColor;
	}

	public void setRadioColor(Color radioColor) {
		this.radioColor = radioColor;
	}

	@Override
	public void paint(Graphics grphcs) {
		super.paint(grphcs);
		Graphics2D g2 = (Graphics2D) grphcs;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int ly = (getHeight() - 16) / 2;
		if (isSelected()) {
			if (isEnabled()) {
				g2.setColor(radioColor);
			} else {
				g2.setColor(radioColor);
			}
			g2.fillOval(1, ly, 16, 16);
			g2.setColor(Utils.secondaryColor);
			g2.fillOval(2, ly + 1, 14, 14);
			if (isEnabled()) {
				g2.setColor(radioColor);
			} else {
				g2.setColor(Utils.getOpacity(radioColor, 0.6f));
			}
			g2.fillOval(5, ly + 4, 8, 8);
		} else {
			if (isFocusOwner()) {
				g2.setColor(radioColor);
			} else {
				g2.setColor(radioColor);
			}
			g2.fillOval(1, ly, 16, 16);
			g2.setColor(Utils.secondaryColor);
			g2.fillOval(2, ly + 1, 14, 14);
		}
		g2.dispose();
	}
}