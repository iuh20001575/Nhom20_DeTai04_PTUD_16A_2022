package layouts;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import javaswingdev.GoogleMaterialDesignIcon;
import javaswingdev.GoogleMaterialIcon;
import javaswingdev.drawer.Drawer;
import javaswingdev.drawer.DrawerController;
import javaswingdev.drawer.DrawerItem;

public class DefaultLayout {
	private DrawerController drawer;
	private JPanel jPanel;

	public DefaultLayout(JFrame frame, JPanel contentPane, String title) {
		frame.setTitle(title);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0, 0, 1100, 610);
		frame.setLocationRelativeTo(null);

		contentPane = new JPanel();
		frame.setContentPane(contentPane);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(null);

		JButton btnMenu = new JButton("|||");

		btnMenu.setBounds(10, 10, 50, 32);
		contentPane.add(btnMenu);

		JLabel lblTitle = new JLabel("Karaoke Rome");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(70, 10, 940, 32);
		contentPane.add(lblTitle);

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
