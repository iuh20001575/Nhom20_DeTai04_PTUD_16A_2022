package layouts;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import components.button.Button;
import javaswingdev.GoogleMaterialDesignIcon;
import javaswingdev.GoogleMaterialIcon;
import javaswingdev.drawer.Drawer;
import javaswingdev.drawer.DrawerController;
import javaswingdev.drawer.DrawerItem;

public class DefaultLayout {
	private DrawerController drawer;
	private JPanel jPanel;

	public DefaultLayout(JFrame frame, JPanel contentPane, String title, String headingTitle) {
		frame.setTitle(title);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0, 0, 1100, 610);
		frame.setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setForeground(Color.GRAY);
		contentPane.setBackground(new Color(203, 239, 255));
		frame.setContentPane(contentPane);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(null);

		JPanel pnlHeader = new JPanel();
		pnlHeader.setBackground(new Color(149, 166, 248));
		pnlHeader.setBounds(0, 0, 1086, 65);
		contentPane.add(pnlHeader);
		pnlHeader.setLayout(null);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(-20, -20, 0, 0);
		contentPane.add(btnNewButton);

		Button btnMenu = new Button("|||");
		btnMenu.setBounds(23, 16, 38, 38);
		btnMenu.setForeground(new Color(149, 166, 248));
		btnMenu.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		btnMenu.setBorder(BorderFactory.createEmptyBorder());
		btnMenu.setBackground(Color.WHITE);
		btnMenu.setBorderColor(new Color(149, 166, 248));
		btnMenu.setRadius(8);
		btnMenu.setFocusable(false);
		pnlHeader.add(btnMenu);
		
		JLabel lblTitle = new JLabel(headingTitle.toUpperCase());
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setBounds(76, 17, 948, 32);
		lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
		pnlHeader.add(lblTitle);

		GoogleMaterialIcon googleIcon = new GoogleMaterialIcon();
		googleIcon.setIcon(GoogleMaterialDesignIcon.USB);
		drawer = Drawer.newDrawer(frame).addChild(new DrawerItem("Item 1").icon(googleIcon.toIcon()).build())
				.addChild(new DrawerItem("Item 1").icon(googleIcon.toIcon()).build()).build();

		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (drawer.isShow()) {
					drawer.hide();
				} else
					drawer.show();
			}
		});
		this.jPanel = contentPane;
	}

	public JPanel getJPanel() {
		return jPanel;
	}
}
