package components.menu;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class Item extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private float alpha;
	private Animator animator;
	private Icon icon;
	private final int index;
	private final Color mainColor = SystemColor.MAIN_COLOR_2;
	private final boolean mainMenu;
	private boolean mouseEnter;

	public Item(boolean mainMenu, int index) {
		this.mainMenu = mainMenu;
		this.index = index;
		init();
	}

	public int getIndex() {
		return index;
	}

	public Color getMainColor() {
		return mainColor;
	}

	private void init() {
		setContentAreaFilled(false);
		setHorizontalAlignment(JButton.LEFT);
		setForeground(new Color(50, 50, 50));
		setFocusable(false);
		if (mainMenu) {
			setBorder(new EmptyBorder(0, 20, 0, 0));
		} else {
			setBorder(new EmptyBorder(0, 51, 0, 0));
		}
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setForeground(mainColor);
				setGoogleIcon(icon);
				if (!mainMenu) {
					mouseEnter = true;
					startAnimator();
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (!isSelected()) {
					setForeground(new Color(50, 50, 50));
					setGoogleIcon(icon);
				}
				if (!mainMenu) {
					mouseEnter = false;
					startAnimator();
				}
			}
		});
		if (!mainMenu) {
			animator = new Animator(0, new TimingTargetAdapter() {
				@Override
				public void timingEvent(float fraction) {
					alpha = mouseEnter ? fraction : 1f - fraction;
					alpha = Math.min(1, alpha);
					repaint();
				}
			});
			animator.setResolution(0);
			animator.setAcceleration(.5f);
			animator.setDeceleration(.5f);
		}
	}

	public boolean isMainMenu() {
		return mainMenu;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (!mainMenu) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setColor(new Color(170, 170, 170));
			int height = getHeight();
			int size = 6;
			int y = (height - size) / 2;
			g2.drawOval(27, y, size, size);
			g2.setColor(mainColor);
			if (isSelected()) {
				alpha = 1;
			}

			try {
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Math.min(1, alpha)));
			} catch (IllegalArgumentException e) {
				// TODO: handle exception
			}
			g2.fillOval(27, y, size + 1, size + 1);
			g2.dispose();
		} else {
			if (isSelected()) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setPaint(new GradientPaint(0, 3, SystemColor.MAIN_COLOR_1, 3, getHeight() - 6,
						SystemColor.MAIN_COLOR_2));
				g2.fillRect(0, 3, 3, getHeight() - 6);
				g2.dispose();
			}
		}
	}

	public void setGoogleIcon(Icon icon) {
		if (icon != null) {
			this.icon = icon;
			setIcon(icon);
		}
	}

	@Override
	public void setSelected(boolean b) {
		super.setSelected(b);
		if (b || mouseEnter) {
			setForeground(mainColor);
		} else {
			alpha = 0;
			setForeground(new Color(50, 50, 50));
		}
		setGoogleIcon(icon);
	}

	private void startAnimator() {
		if (animator.isRunning()) {
			float f = animator.getTimingFraction();
			animator.stop();
			animator.setStartFraction(1f - f);
		} else {
			animator.setStartFraction(0f);
		}
		animator.start();
	}
}
