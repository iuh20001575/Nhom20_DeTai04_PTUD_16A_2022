package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.raven.datechooser.DateChooser;

import components.textField.TextField;
import components.textField.TextFieldIcon;

public class DefaultUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private TextFieldIcon textField;
	private DateChooser dateChoose;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DefaultUI frame = new DefaultUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DefaultUI() {
		setTitle("title");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1100, 610);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(null);

		JButton btnMenu = new JButton("|||");
		btnMenu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		btnMenu.setBounds(16, 10, 50, 32);
		contentPane.add(btnMenu);

		JLabel lblTitle = new JLabel("Karaoke Rome");
		lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(76, 10, 948, 32);
		contentPane.add(lblTitle);

		textField = new TextFieldIcon();
		textField.setLabelText("Ngày sinh");
		textField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		textField.setBounds(0, 303, 203, 55);
//		ImageIcon icon = new ImageIcon("Icon\\date.png");
//		textField.setIcon(icon);
		textField.setColumns(10);
		contentPane.add(textField);

		TextField textField_1 = new TextField();
		textField_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		textField_1.setColumns(10);
		textField_1.setBounds(0, 92, 203, 55);
		textField_1.setIcon(new ImageIcon("Icon\\date.png"));
		contentPane.add(textField_1);

		dateChoose = new DateChooser();
//		dateChoose.setBounds(213, 180, 272, 201);
//		contentPane.add(dateChoose);
		dateChoose.setTextRefernce(textField_1);

//		GoogleMaterialIcon googleIcon = new GoogleMaterialIcon();
//		googleIcon.setIcon(GoogleMaterialDesignIcon.USB);
//		drawer = Drawer.newDrawer(frame).addChild(new DrawerItem("Item 1").icon(googleIcon.toIcon()).build())
//				.addChild(new DrawerItem("Item 1").icon(googleIcon.toIcon()).build()).build();
//
//		btnMenu.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if (drawer.isShow()) {
//					drawer.hide();
//				} else
//					drawer.show();
//			}
//		});
	}
}
