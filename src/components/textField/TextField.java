package components.textField;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import utils.Utils;

public class TextField extends JTextField {
	private final Animator animator;
	private boolean animateHinText = true;
	private float location;
	private boolean show;
	private boolean mouseOver = false;
	private String labelText = "Label";
	private Color lineColor = new Color(3, 155, 216);
	private Color error = new Color(235, 87, 87);
	private Color textColor = Color.BLACK;
	private Color textDisabledColor = Utils.getOpacity(Color.BLACK, 0.6f);
	private Icon icon;
	private boolean isError = false;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getLabelText() {
		return labelText;
	}

	public void setLabelText(String labelText) {
		this.labelText = labelText;
	}

	public Color getLineColor() {
		return lineColor;
	}

	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}

	public Icon getIcon() {
		return icon;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
		initBorder();
	}

	public boolean isError() {
		return isError;
	}

	public void setError(boolean isError) {
		this.isError = isError;
		setForeground(isError ? error : textColor);
		repaint();
	}

	public TextField() {
		textColor = getForeground();
		setBorder(new EmptyBorder(20, 3, 10, 3));
		setSelectionColor(new Color(76, 204, 255));
		setDisabledTextColor(textDisabledColor);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent me) {
				mouseOver = true;
				repaint();
			}

			@Override
			public void mouseExited(MouseEvent me) {
				mouseOver = false;
				repaint();
			}
		});
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent fe) {
				showing(false);
			}

			@Override
			public void focusLost(FocusEvent fe) {
				showing(true);
			}
		});
		TimingTarget target = new TimingTargetAdapter() {
			@Override
			public void begin() {
				animateHinText = getText().equals("");
			}

			@Override
			public void timingEvent(float fraction) {
				location = fraction;
				repaint();
			}

		};
		animator = new Animator(300, target);
		animator.setResolution(0);
		animator.setAcceleration(0.5f);
		animator.setDeceleration(0.5f);

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				setError(false);
			}
		});
	}

	private void showing(boolean action) {
		if (animator.isRunning()) {
			animator.stop();
		} else {
			location = 1;
		}
		animator.setStartFraction(1f - location);
		show = action;
		location = 1f - location;
		animator.start();
	}

	@Override
	public void paint(Graphics grphcs) {
		super.paint(grphcs);
		Graphics2D g2 = (Graphics2D) grphcs;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
		int width = getWidth();
		int height = getHeight();
		if (mouseOver) {
			g2.setColor(lineColor);
		} else {
			g2.setColor(new Color(150, 150, 150));
		}
		g2.fillRect(2, height - 1, width - 4, 1);
		createHintText(g2);
		createLineStyle(g2);
		g2.dispose();
	}

	private void createHintText(Graphics2D g2) {
		Insets in = getInsets();
		g2.setColor(isError ? error : Utils.labelTextField);
		FontMetrics ft = g2.getFontMetrics();
		Rectangle2D r2 = ft.getStringBounds(labelText, g2);
		double height = getHeight() - in.top - in.bottom;
		double textY = (height - r2.getHeight()) / 2;
		double size;
		if (animateHinText) {
			if (show) {
				size = 18 * (1 - location);
			} else {
				size = 18 * location;
			}
		} else {
			size = 18;
		}
		g2.drawString(labelText, in.left, (int) (in.top + textY + ft.getAscent() - size));
	}

	private void createLineStyle(Graphics2D g2) {
		if (isFocusOwner()) {
			double width = getWidth() - 4;
			int height = getHeight();
			g2.setColor(lineColor);
			double size;
			if (show) {
				size = width * (1 - location);
			} else {
				size = width * location;
			}
			double x = (width - size) / 2;
			g2.fillRect((int) (x + 2), height - 2, (int) size, 2);
		}
	}

	@Override
	public void setText(String string) {
		if (!getText().equals(string)) {
			showing(string.equals(""));
		}
		super.setText(string);
	}

	private void initBorder() {
		if (icon != null)
			setBorder(new EmptyBorder(20, 3, 10, 38));
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		paintIcon(g);
	}

	private void paintIcon(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if (icon != null) {
			Image suffix = ((ImageIcon) icon).getImage();
//            int y = (getHeight() - icon.getIconHeight()) / 2;
			g2.drawImage(suffix, getWidth() - icon.getIconWidth() - 3, 20, this);
		}
	}
}
