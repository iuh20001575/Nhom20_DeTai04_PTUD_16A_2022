package components.jDialog;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import components.button.ButtonCustom;

public class JDialogCustom extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JFrame fram;
	private Animator animator;
	private Glass glass;
	private boolean show;
	private MessageType messageType = MessageType.CANCEL;
	private Background background1;
	private ButtonCustom btnCancel;
	private ButtonCustom btnOK;
	private javax.swing.JLabel lblIcon;
	private javax.swing.JLabel lblTitle;
	private javax.swing.JTextPane txpMessage;
	private Type type = Type.confirm;

	public JDialogCustom(JFrame fram) {
		super(fram, true);
		this.fram = fram;
		initComponents();
		init();
	}

	public JDialogCustom(JFrame fram, Type type) {
		this(fram);
		this.type = type;
	}

	private void init() {
		setBackground(new Color(0, 0, 0, 0));
		StyledDocument doc = txpMessage.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		txpMessage.setOpaque(false);
		txpMessage.setBackground(new Color(0, 0, 0, 0));
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				closeMessage();
			}
		});
		animator = new Animator(300, new TimingTargetAdapter() {
			@Override
			public void timingEvent(float fraction) {
				float f = show ? fraction : 1f - fraction;
				glass.setAlpha(f - f * 0.3f);
				setOpacity(f);
			}

			@Override
			public void end() {
				if (show == false) {
					dispose();
					glass.setVisible(false);
				}
			}
		});
		animator.setResolution(0);
		animator.setAcceleration(.5f);
		animator.setDeceleration(.5f);
		setOpacity(0f);
		glass = new Glass();
	}

	private void startAnimator(boolean show) {
		if (animator.isRunning()) {
			float f = animator.getTimingFraction();
			animator.stop();
			animator.setStartFraction(1f - f);
		} else {
			animator.setStartFraction(0f);
		}
		this.show = show;
		animator.start();
	}

	public void showMessage(String title, String message) {
		fram.setGlassPane(glass);
		glass.setVisible(true);
		lblTitle.setText(title);
		txpMessage.setText(message);
		setLocationRelativeTo(fram);
		startAnimator(true);
		setVisible(true);
	}

	public void closeMessage() {
		startAnimator(false);
	}

	public MessageType getMessageType() {
		return messageType;
	}

	private void initComponents() {
		background1 = new Background();
		btnCancel = new ButtonCustom();
		btnOK = new ButtonCustom();
		lblIcon = new javax.swing.JLabel();
		lblTitle = new javax.swing.JLabel();
		txpMessage = new javax.swing.JTextPane();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setUndecorated(true);

		background1.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));

		if (type.equals(Type.warning))
			btnCancel.setVisible(false);
		btnCancel.setFocusable(false);
		btnCancel.setBackground(new java.awt.Color(245, 71, 71));
		btnCancel.setText("Cancel");
		btnCancel.setColorHover(new java.awt.Color(255, 74, 74));
		btnCancel.setColorPressed(new java.awt.Color(235, 61, 61));
		btnCancel.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
		btnCancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cmdCancelActionPerformed(evt);
			}
		});

		btnOK.setText("OK");
		btnOK.setFocusable(false);
		btnOK.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
		btnOK.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnOKActionPerformed(evt);
			}
		});

		lblIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

		ImageIcon imageIcon = new javax.swing.ImageIcon(
				String.format("Icon\\%s.png", type.equals(Type.confirm) ? "questionIcon" : "warning_50x50"));

		lblIcon.setIcon(imageIcon); // NOI18N

		lblTitle.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
		lblTitle.setForeground(new java.awt.Color(245, 71, 71));
		lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblTitle.setText("Message Title");

		txpMessage.setEditable(false);
		txpMessage.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
		txpMessage.setForeground(new java.awt.Color(76, 76, 76));
		txpMessage.setText("Message Text\nSimple");
		txpMessage.setFocusable(false);

		javax.swing.GroupLayout background1Layout = new javax.swing.GroupLayout(background1);
		background1.setLayout(background1Layout);
		background1Layout
				.setHorizontalGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(background1Layout.createSequentialGroup()
								.addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
								.addGap(type.equals(Type.warning) ? 96 : 3, type.equals(Type.warning) ? 96 : 3,
										type.equals(Type.warning) ? 96 : 3)
								.addComponent(btnOK, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
								.addGap(type.equals(Type.warning) ? 96 : 0, type.equals(Type.warning) ? 96 : 0,
										type.equals(Type.warning) ? 96 : 0))
						.addComponent(lblIcon, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(txpMessage));
		background1Layout.setVerticalGroup(background1Layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background1Layout.createSequentialGroup()
						.addComponent(lblIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 74,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(20, 20, 20).addComponent(lblTitle)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(txpMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 60,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
										javax.swing.GroupLayout.PREFERRED_SIZE))));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(background1,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(background1,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		pack();
	}

	private void cmdCancelActionPerformed(java.awt.event.ActionEvent evt) {
		messageType = MessageType.CANCEL;
		closeMessage();
	}

	private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {
		messageType = MessageType.OK;
		closeMessage();
	}

	public ButtonCustom getBtnCancel() {
		return btnCancel;
	}

	public ButtonCustom getBtnOK() {
		return btnOK;
	}

	public static enum Type {
		warning, confirm
	}

	public static enum MessageType {
		CANCEL, OK
	}
}
