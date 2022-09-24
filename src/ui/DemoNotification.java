package ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import components.button.ButtonCustom;
import components.notification.Notification;
import layouts.DefaultLayout;

public class DemoNotification extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DemoNotification frame = new DemoNotification();
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
	public DemoNotification() {
		JFrame frame = this;
		
		DefaultLayout df = new DefaultLayout(frame, contentPane, "Demo Notification");
		contentPane = df.getJPanel();

		JButton btnNewButton = new JButton("Success Btn");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Notification notification = new Notification(frame, Notification.Type.SUCCESS,
						"Success Notification Message");
				notification.showNotification();
			}
		});
		btnNewButton.setBounds(458, 270, 105, 21);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Info Btn");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Notification notification = new Notification(frame, Notification.Type.INFO,
						"Info Notification Message");
				notification.showNotification();
			}
		});
		btnNewButton_1.setBounds(604, 270, 105, 21);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Warning Btn");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Notification notification = new Notification(frame, Notification.Type.WARNING,
						"Warning Notification Message");
				notification.showNotification();
			}
		});
		btnNewButton_2.setBounds(458, 301, 105, 21);
		contentPane.add(btnNewButton_2);

		ButtonCustom btnNewButton_3 = new ButtonCustom("Error Btn");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Notification notification = new Notification(frame, Notification.Type.ERROR,
						"Error Notification Message");
				notification.showNotification();
			}
		});
		btnNewButton_3.setBounds(604, 301, 105, 21);
		contentPane.add(btnNewButton_3);
	}

}
