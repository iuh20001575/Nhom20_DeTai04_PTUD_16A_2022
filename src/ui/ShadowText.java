package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.TextLayout;

import javax.swing.JLabel;

public class ShadowText extends JLabel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ShadowText() {
	}

	public void paint(Graphics g) {
		int x = 10;
		int y = 100;

		
		Font font = getFont();
		Graphics2D g1 = (Graphics2D) g;

		TextLayout textLayout = new TextLayout(getText(), font, g1.getFontRenderContext());
		g1.setPaint(new Color(150, 150, 150));
		textLayout.draw(g1, x + 3, y + 3);

		g1.setPaint(Color.BLACK);
		textLayout.draw(g1, x, y);
	}
}
