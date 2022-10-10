package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import components.jDialog.JDialogCustom;

public class DemoJDialog extends JFrame {

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
					DemoJDialog frame = new DemoJDialog();
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
	public DemoJDialog() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JDialogCustom jDialogCustom = new JDialogCustom(this);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jDialogCustom.showMessage("Error", "Message Notification");
			}
		});
		btnNewButton.setBounds(102, 101, 85, 21);
		contentPane.add(btnNewButton);

//		table = new JTable();
//		table.setModel(new DefaultTableModel(
//			new Object[][] {
//				{null, null, null, null},
//				{null, null, null, null},
//				{null, null, null, null},
//				{null, null, null, null},
//			},
//			new String[] {
//				"New column 1", "New column 2", "New column 3", "New column 4"
//			}
//		));
//		table.setBounds(0, 0, 436, 263);
//		contentPane.add(table);
//		
////		GoogleMaterialIcon googleIcon = new GoogleMaterialIcon();
////		googleIcon.setIcon(GoogleMaterialDesignIcon.SEARCH);
////		googleIcon.setSize(30);
//		FontAwesomeIcon fontAwesome = new FontAwesomeIcon();
//		fontAwesome.setIcon(FontAwesome.SEARCH);
//		fontAwesome.setSize(30);
	}
}
