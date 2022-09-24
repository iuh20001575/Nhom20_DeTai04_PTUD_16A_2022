package components.notification;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JDialog;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class Notification extends javax.swing.JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JDialog dialog;
	private Animator animator;
	private final Frame fram;
	private boolean showing;
	private Thread thread;
	private BufferedImage imageShadow;
	private int shadowSize = 6;
	private Type type;
	private javax.swing.JButton cmdClose;
	private javax.swing.JLabel lbIcon;
	private javax.swing.JLabel lbMessage;
	private javax.swing.JLabel lbMessageText;
	private javax.swing.JPanel panel;

	public Notification(Frame fram, Type type, String message) {
		this.fram = fram;
		this.type = type;
		initComponents();
		init(message);
		initAnimator();
	}

	private void init(String message) {
		setBackground(Color.WHITE);
		dialog = new JDialog(fram);
		dialog.setUndecorated(true);
		dialog.setFocusableWindowState(false);
		dialog.setBackground(new Color(0, 0, 0, 0));
		dialog.add(this);
		dialog.setSize(getPreferredSize());
		if (type == Type.SUCCESS) {
			lbIcon.setIcon(new javax.swing.ImageIcon("Icon\\sucessNotification.png"));
			lbIcon.setPreferredSize(new Dimension(30, 30));
			lbMessage.setText("Success");
		} else if (type == Type.INFO) {
			lbIcon.setIcon(new javax.swing.ImageIcon("Icon\\infoNotification.png"));
			lbMessage.setText("Info");
		} else if (type == Type.WARNING) {
			lbIcon.setIcon(new javax.swing.ImageIcon("Icon\\warningNotification.png"));
			lbMessage.setText("Warning");
		} else {
			lbIcon.setIcon(new javax.swing.ImageIcon("Icon\\attentionNotification.png"));
			lbMessage.setText("Error");
		}
		lbMessageText.setText(message);
	}

	private void initAnimator() {
		TimingTarget target = new TimingTargetAdapter() {
			private int x;
			private int top;

			@Override
			public void timingEvent(float fraction) {
				float alpha = showing ? 1 - fraction : fraction;
				dialog.setLocation((int) (x + dialog.getWidth() * (1 - alpha)), top + 30);
				dialog.setOpacity(alpha);
			}

			@Override
			public void begin() {
				if (!showing) {
					dialog.setOpacity(0f);
					int margin = 10;
					int y = 0;

					x = fram.getX() + fram.getWidth() - dialog.getWidth() - margin;
					y = fram.getY();
					top = y;
					dialog.setLocation(x, y);
					dialog.setVisible(true);
				}
			}

			@Override
			public void end() {
				showing = !showing;
				if (showing) {
					thread = new Thread(new Runnable() {
						@Override
						public void run() {
							sleep();
							closeNotification();
						}
					});
					thread.start();
				} else {
					dialog.dispose();
				}
			}
		};
		animator = new Animator(500, target);
		animator.setResolution(5);
	}

	public void showNotification() {
		animator.start();
	}

	private void closeNotification() {
		if (thread != null && thread.isAlive()) {
			thread.interrupt();
		}
		if (animator.isRunning()) {
			if (!showing) {
				animator.stop();
				showing = true;
				animator.start();
			}
		} else {
			showing = true;
			animator.start();
		}
	}

	private void sleep() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
	}

	@Override
	public void paint(Graphics grphcs) {
		Graphics2D g2 = (Graphics2D) grphcs.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(getBackground());
		g2.drawImage(imageShadow, 0, 0, null);
		int x = shadowSize;
		int y = shadowSize;
		int width = getWidth() - shadowSize * 2;
		int height = getHeight() - shadowSize * 2;
		g2.fillRect(x, y, width, height);
		if (type == Type.SUCCESS) {
			g2.setColor(new Color(18, 163, 24));
		} else if (type == Type.INFO) {
			g2.setColor(new Color(28, 139, 206));
		} else if (type == Type.WARNING) {
			g2.setColor(new Color(241, 196, 15));
		} else {
			g2.setColor(new Color(240, 72, 68));
		}
		g2.fillRect(6, 5, 5, getHeight() - shadowSize * 2 + 1);
		g2.dispose();
		super.paint(grphcs);
	}

	@Override
	public void setBounds(int i, int i1, int i2, int i3) {
		super.setBounds(i, i1, i2, i3);
		createImageShadow();
	}

	private void createImageShadow() {
		imageShadow = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = imageShadow.createGraphics();
		g2.drawImage(createShadow(), 0, 0, null);
		g2.dispose();
	}

	private BufferedImage createShadow() {
		BufferedImage img = new BufferedImage(getWidth() - shadowSize * 2, getHeight() - shadowSize * 2,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = img.createGraphics();
		g2.fillRect(0, 0, img.getWidth(), img.getHeight());
		g2.dispose();
		return new ShadowRenderer(shadowSize, 0.3f, new Color(100, 100, 100)).createShadow(img);
	}

	private void initComponents() {
		lbIcon = new javax.swing.JLabel();
		panel = new javax.swing.JPanel();
		lbMessage = new javax.swing.JLabel();
		lbMessageText = new javax.swing.JLabel();
		cmdClose = new javax.swing.JButton();

		lbIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lbIcon.setIcon(new javax.swing.ImageIcon("Icon\\sucessNotification.png")); // NOI18N

		panel.setOpaque(false);

		lbMessage.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
		lbMessage.setForeground(new java.awt.Color(38, 38, 38));
		lbMessage.setText("Message");

		lbMessageText.setForeground(new java.awt.Color(127, 127, 127));
		lbMessageText.setText("Message Text");

		javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
		panel.setLayout(panelLayout);
		panelLayout.setHorizontalGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelLayout.createSequentialGroup().addContainerGap()
						.addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(lbMessage).addComponent(lbMessageText))
						.addContainerGap(217, Short.MAX_VALUE)));
		panelLayout.setVerticalGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelLayout.createSequentialGroup().addContainerGap().addComponent(lbMessage).addGap(3, 3, 3)
						.addComponent(lbMessageText).addContainerGap()));

		cmdClose.setIcon(new javax.swing.ImageIcon("Icon\\closeNotification.png")); // NOI18N
		cmdClose.setBorder(null);
		cmdClose.setContentAreaFilled(false);
		cmdClose.setFocusable(false);
		cmdClose.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cmdCloseActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(20, 20, 20).addComponent(lbIcon).addGap(10, 10, 10)
						.addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(cmdClose)
						.addGap(15, 15, 15)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addGap(10, 10, 10)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(cmdClose, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(lbIcon, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGap(10, 10, 10)));
	}

	private void cmdCloseActionPerformed(java.awt.event.ActionEvent evt) {
		closeNotification();
	}

	public static enum Type {
		SUCCESS, INFO, WARNING, ERROR
	}
}
