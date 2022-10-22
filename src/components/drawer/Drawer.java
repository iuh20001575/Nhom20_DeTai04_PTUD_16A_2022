package components.drawer;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import components.drawer.scroll.ScrollBar;
import net.miginfocom.swing.MigLayout;

public class Drawer implements DrawerController {

	protected class DrawerPanel extends JComponent {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private float animate = 0f;

		private final MigLayout layout;

		private final boolean leftDrawer;
		private final DrawerPanelItem panel;
		private final float targetAlpha;
		private final int width;
		public DrawerPanel(int width, float targetAlpha, boolean leftDrawer) {
			this.width = width;
			this.targetAlpha = targetAlpha;
			this.leftDrawer = leftDrawer;
			layout = new MigLayout();
			setLayout(layout);
			panel = new DrawerPanelItem();
			panel.setOpaque(false);
			panel.setLayout(new MigLayout("inset 0, wrap, gap 0", " [" + width + "!,fill]", "[fill,top]"));
			if (leftDrawer) {
				add(panel, "pos -" + width + " 0 n 100%");
			} else {
				add(panel, "pos 100% 0 n 100%");
			}
		}
		public void addItem(Component com) {
			panel.add(com);
		}

		public void addItem(Component com, Object cmd) {
			panel.add(com, cmd);
		}

		public float getAnimate() {
			return animate;
		}

		@Override
		protected void paintComponent(Graphics g) {
			if (targetAlpha != 0) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setColor(getBackground());
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, animate * targetAlpha));
				g2.fill(new Rectangle(0, 0, getWidth(), getHeight()));
				g2.dispose();
			}
			super.paintComponent(g);
		}

		public void setAnimate(float animate) {
			this.animate = animate;
			if (leftDrawer) {
				int w = (int) ((width * animate) - width);
				layout.setComponentConstraints(panel, "pos " + w + " 0 n 100%");
			} else {
				int w = (int) ((width * animate));
				layout.setComponentConstraints(panel, "pos 100%-" + w + " 0 n 100%");
			}
			panel.revalidate();
			if (targetAlpha != 0) {
				repaint();
			}
		}

		public void setDrawerBackground(Color color) {
			panel.setBackground(color);
		}
	}

	private class DrawerPanelItem extends JComponent {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public DrawerPanelItem() {
			addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
				}
			});
		}

		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setColor(getBackground());
			g2.fill(new Rectangle(0, 0, getWidth(), getHeight()));
			g2.dispose();
			super.paintComponent(g);
		}
	}
	public static Drawer newDrawer(JFrame fram) {
		return new Drawer(fram);
	}
	private Animator animator;
	private Color background = new Color(30, 30, 30);
	private float backgroundTransparent = 0.5f;
	private final List<Component> childrens;
	private boolean closeOnPress = true;
	private Color drawerBackground = Color.WHITE;
	private int drawerWidth = 250;
	private int duration = 500;
	private boolean enableScroll = false;
	private boolean enableScrollUI = true;
	private final List<EventDrawer> events;
	private final List<Component> footers;
	private final JFrame fram;
	private Component header;
	private int headerHeight = 150;
	private int index = 0;
	private boolean isShow;
	private boolean itemAlignLeft = true;
	private int itemHeight = 45;
	private boolean leftDrawer = true;
	private MouseListener mouseEvent;

	private DrawerPanel panelDrawer;

	private int resolution = 10;

	private Drawer(JFrame fram) {
		this.fram = fram;
		childrens = new ArrayList<>();
		footers = new ArrayList<>();
		events = new ArrayList<>();
	}

	public Drawer addChild(Component... component) {
		for (Component com : component) {
			if (com instanceof DrawerItem) {
				DrawerItem item = (DrawerItem) com;
				item.setIndex(index++);
				item.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						runEvent(item.getIndex(), item);
					}
				});
			}
			childrens.add(com);
		}
		return this;
	}

	public Drawer addFooter(Component... component) {
		for (Component com : component) {
			if (com instanceof DrawerItem) {
				DrawerItem item = (DrawerItem) com;
				item.setIndex(index++);
				item.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						runEvent(item.getIndex(), item);
					}
				});
			}
			footers.add(com);
		}
		return this;
	}

	public Drawer background(Color color) {
		background = color;
		return this;
	}

	public Drawer backgroundTransparent(float backgroundTransparent) {
		this.backgroundTransparent = backgroundTransparent;
		return this;
	}

	public DrawerController build() {
		panelDrawer = new DrawerPanel(drawerWidth, backgroundTransparent, leftDrawer);
		panelDrawer.setBackground(background);
		panelDrawer.setDrawerBackground(drawerBackground);
		if (header != null) {
			panelDrawer.addItem(header, "h " + headerHeight);
		}
		if (enableScroll) {
			JPanel panelItem = new JPanel(new MigLayout("wrap, inset 0, gap 0, fill", "fill"));
			panelItem.setOpaque(false);
			for (Component com : childrens) {
				if (com.toString().equals("drawer")) {
					checkAlign(com);
					panelItem.add(com, "height " + itemHeight);
				} else {
					panelItem.add(com);
				}
			}
			if (!footers.isEmpty()) {
				panelItem.add(new JLabel(), "push");
				for (Component com : footers) {
					if (com.toString().equals("drawer")) {
						checkAlign(com);
						panelItem.add(com, "height " + itemHeight);
					} else {
						panelItem.add(com);
					}
				}
			}
			panelDrawer.addItem(createScroll(panelItem), "h 100%");
		} else {
			for (Component com : childrens) {
				if (com.toString().equals("drawer")) {
					checkAlign(com);
					panelDrawer.addItem(com, "height " + itemHeight);
				} else {
					panelDrawer.addItem(com);
				}
			}
			if (!footers.isEmpty()) {
				panelDrawer.addItem(new JLabel(), "push");
				for (Component com : footers) {
					if (com.toString().equals("drawer")) {
						checkAlign(com);
						panelDrawer.addItem(com, "height " + itemHeight);
					} else {
						panelDrawer.addItem(com);
					}
				}
			}
		}
		createAnimator(duration, resolution);
		fram.setGlassPane(panelDrawer);
		fram.getGlassPane().setVisible(true);
		return this;
	}

	private void checkAlign(Component com) {
		if (!itemAlignLeft) {
			JButton item = (JButton) com;
			item.setHorizontalTextPosition(JButton.LEFT);
			item.setHorizontalAlignment(JButton.RIGHT);
		}
	}

	public Drawer closeOnPress(boolean closeOnPress) {
		this.closeOnPress = closeOnPress;
		return this;
	}

	private void createAnimator(int duration, int resolution) {
		animator = new Animator(duration, new TimingTargetAdapter() {
			@Override
			public void end() {
				if (panelDrawer.getAnimate() == 0) {
					fram.getGlassPane().setVisible(false);
					isShow = false;
				}
			}

			@Override
			public void timingEvent(float fraction) {
				if (isShow) {
					panelDrawer.setAnimate(fraction);
				} else {
					panelDrawer.setAnimate(1f - fraction);
				}
			}

		});
		animator.setAcceleration(.5f);
		animator.setDeceleration(.5f);
		animator.setResolution(resolution);
		if (closeOnPress) {
			mouseEvent = new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					hide();
				}
			};
		}
	}

	private JScrollPane createScroll(Component com) {
		JScrollPane scroll = new JScrollPane();
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		scroll.setViewportView(com);
		scroll.setBorder(null);
		scroll.setViewportBorder(null);
		if (enableScrollUI) {
			ScrollBar sv = new ScrollBar(JScrollBar.VERTICAL);
			scroll.setVerticalScrollBar(sv);
			ScrollBar sh = new ScrollBar(JScrollBar.HORIZONTAL);
			scroll.setHorizontalScrollBar(sh);
		}
		return scroll;
	}

	public Drawer drawerBackground(Color color) {
		drawerBackground = color;
		return this;
	}

	public Drawer drawerWidth(int drawerWidth) {
		this.drawerWidth = drawerWidth;
		return this;
	}

	public Drawer duration(int duration) {
		this.duration = duration;
		return this;
	}

	public Drawer enableScroll(boolean enableScroll) {
		this.enableScroll = enableScroll;
		return this;
	}

	public Drawer enableScrollUI(boolean enableScrollUI) {
		this.enableScrollUI = enableScrollUI;
		return this;
	}

	public Drawer event(EventDrawer event) {
		this.events.add(event);
		return this;
	}

	public DrawerPanel getPanelDrawer() {
		return panelDrawer;
	}

	public Drawer header(Component component) {
		this.header = component;
		return this;
	}

	public Drawer headerHeight(int headerHeight) {
		this.headerHeight = headerHeight;
		return this;
	}

	@Override
	public void hide() {
		if (isShow) {
			if (closeOnPress) {
				panelDrawer.removeMouseListener(mouseEvent);
			}
			start(false);
		}
	}

	@Override
	public boolean isShow() {
		return isShow;
	}

	public Drawer itemAlignLeft(boolean itemAlignLeft) {
		this.itemAlignLeft = itemAlignLeft;
		return this;
	}

	public Drawer itemHeight(int itemHeight) {
		this.itemHeight = itemHeight;
		return this;
	}

	public Drawer leftDrawer(boolean leftDrawer) {
		this.leftDrawer = leftDrawer;
		return this;
	}

	public Drawer resolution(int resolution) {
		this.resolution = resolution;
		return this;
	}

	private void runEvent(int index, DrawerItem item) {
		for (EventDrawer event : events) {
			event.selected(index, item);
		}
	}

	public Drawer separator(int height, Color color) {
		JLabel label = new JLabel();
		label.setPreferredSize(new Dimension(0, height));
		label.setBackground(color);
		label.setOpaque(true);
		childrens.add(label);
		return this;
	}

	public void setPanelDrawer(DrawerPanel panelDrawer) {
		this.panelDrawer = panelDrawer;
	}

	@Override
	public void show() {
		if (!isShow) {
			fram.setGlassPane(panelDrawer);
			fram.getGlassPane().setVisible(true);
			if (closeOnPress) {
				panelDrawer.removeMouseListener(mouseEvent);
				panelDrawer.addMouseListener(mouseEvent);
			}
			start(true);
		}
	}

	public Drawer space(int height) {
		JLabel label = new JLabel();
		label.setPreferredSize(new Dimension(0, height));
		childrens.add(label);
		return this;
	}

	private void start(boolean isShow) {
		if (animator.isRunning()) {
			animator.stop();
			float f = animator.getTimingFraction();
			animator.setStartFraction(1f - f);
		} else {
			animator.setStartFraction(0f);
		}
		this.isShow = isShow;
		animator.start();
	}
}