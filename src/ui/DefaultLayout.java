package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import javaswingdev.GoogleMaterialDesignIcon;
import javaswingdev.GoogleMaterialIcon;
import javaswingdev.drawer.Drawer;
import javaswingdev.drawer.DrawerController;
import javaswingdev.drawer.DrawerItem;

public class DefaultLayout {
	private DrawerController drawer;
	
	public DefaultLayout(JPanel jPanel, JFrame frame) {
		GoogleMaterialIcon googleIcon = new GoogleMaterialIcon();
		googleIcon.setIcon(GoogleMaterialDesignIcon.USB);
		drawer = Drawer.newDrawer(frame)
				.addChild(new DrawerItem("Item 1").icon(googleIcon.toIcon()).build())
				.addChild(new DrawerItem("Item 1").icon(googleIcon.toIcon()).build())
				.build();
		
		JButton btnMenu = new JButton("|||");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (drawer.isShow()) {
					drawer.hide();
				} else
					drawer.show();
			}
		});
		btnMenu.setBounds(10, 10, 50, 32);
		jPanel.add(btnMenu);
		
		JLabel lblNewLabel = new JLabel("Karaoke Rome");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(70, 10, 940, 32);
		jPanel.add(lblNewLabel);
	}
}
