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

	public static enum Type {
		ERROR, INFO, SUCCESS, WARNING
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Animator animator;
	private javax.swing.JButton btnClose;
	private JDialog dialog;
	private final Frame fram;
	private BufferedImage imageShadow;
	private javax.swing.JLabel lblIcon;
	private javax.swing.JLabel lblMessage;
	private javax.swing.JLabel lblMessageText;
	private javax.swing.JPanel pnl;
	private int shadowSize = 6;
	private boolean showing;
	private Thread thread;
	private Type type;

	public Notification(Frame fram, Type type, String message) {
		this.fram = fram;
		this.type = type;
		initComponents();
		init(message);
		initAnimator();
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

	private void cmdCloseActionPerformed(java.awt.event.ActionEvent evt) {
		closeNotification();
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

	private void init(String message) {
		setBackground(Color.WHITE);
		dialog = new JDialog(fram);
		dialog.setUndecorated(true);
		dialog.setFocusableWindowState(false);
		dialog.setBackground(new Color(0, 0, 0, 0));
		dialog.add(this);
		dialog.setSize(getPreferredSize());
		if (type == Type.SUCCESS) {
			lblIcon.setIcon(new javax.swing.ImageIcon("Icon\\sucessNotification.png"));
			lblIcon.setPreferredSize(new Dimension(30, 30));
			lblMessage.setText("Success");
		} else if (type == Type.INFO) {
			lblIcon.setIcon(new javax.swing.ImageIcon("Icon\\infoNotification.png"));
			lblMessage.setText("Info");
		} else if (type == Type.WARNING) {
			lblIcon.setIcon(new javax.swing.ImageIcon("Icon\\warningNotification.png"));
			lblMessage.setText("Warning");
		} else {
			lblIcon.setIcon(new javax.swing.ImageIcon("Icon\\attentionNotification.png"));
			lblMessage.setText("Error");
		}
		lblMessageText.setText(message);
	}

	private void initAnimator() {
		TimingTarget target = new TimingTargetAdapter() {
			private int top;
			private int x;

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

			@Override
			public void timingEvent(float fraction) {
				float alpha = showing ? 1 - fraction : fraction;
				dialog.setLocation((int) (x + dialog.getWidth() * (1 - alpha)), top + 30);
				dialog.setOpacity(alpha);
			}
		};
		animator = new Animator(500, target);
		animator.setResolution(5);
	}

	private void initComponents() {
		lblIcon = new javax.swing.JLabel();
		pnl = new javax.swing.JPanel();
		lblMessage = new javax.swing.JLabel();
		lblMessageText = new javax.swing.JLabel();
		btnClose = new javax.swing.JButton();

		lblIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblIcon.setIcon(new javax.swing.ImageIcon("Icon\\sucessNotification.png")); // NOI18N

		pnl.setOpaque(false);

		lblMessage.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
		lblMessage.setForeground(new java.awt.Color(38, 38, 38));
		lblMessage.setText("Message");

		lblMessageText.setForeground(new java.awt.Color(127, 127, 127));
		lblMessageText.setText("Message Text");

		javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(pnl);
		pnl.setLayout(panelLayout);
		panelLayout.setHorizontalGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelLayout.createSequentialGroup().addContainerGap()
						.addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(lblMessage).addComponent(lblMessageText))
						.addContainerGap(217, Short.MAX_VALUE)));
		panelLayout.setVerticalGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelLayout.createSequentialGroup().addContainerGap().addComponent(lblMessage).addGap(3, 3, 3)
						.addComponent(lblMessageText).addContainerGap()));

		btnClose.setIcon(new javax.swing.ImageIcon("Icon\\closeNotification.png")); // NOI18N
		btnClose.setBorder(null);
		btnClose.setContentAreaFilled(false);
		btnClose.setFocusable(false);
		btnClose.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cmdCloseActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(20, 20, 20).addComponent(lblIcon).addGap(10, 10, 10)
						.addComponent(pnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(btnClose)
						.addGap(15, 15, 15)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addGap(10, 10, 10)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(btnClose, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(pnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(lblIcon, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGap(10, 10, 10)));
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

	public void showNotification() {
		animator.start();
	}

	private void sleep() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
	}
}
