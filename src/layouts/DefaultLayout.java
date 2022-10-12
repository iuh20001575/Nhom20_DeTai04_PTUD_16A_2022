package layouts;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import components.button.Button;
import components.jDialog.JDialogCustom;
import components.menu.Menu;
import javaswingdev.GoogleMaterialDesignIcon;
import javaswingdev.GoogleMaterialIcon;
import javaswingdev.drawer.Drawer;
import javaswingdev.drawer.DrawerController;
import utils.StackFrame;
import utils.Utils;

public class DefaultLayout {
	private DrawerController drawer;
	private JPanel jPanel;
	private Button btnBack;

	public DefaultLayout(JFrame frame, JPanel contentPane, String title) {
		this(frame, contentPane, title, title);
	}

	public DefaultLayout(JFrame frame, JPanel contentPane, String title, String headingTitle) {
		frame.setTitle(title);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0, 0, 1100, 610);
		frame.setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setForeground(Color.GRAY);
		contentPane.setBackground(Utils.secondaryColor);
		frame.setContentPane(contentPane);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(null);

		JPanel pnlHeader = new JPanel();
		pnlHeader.setBackground(Utils.primaryColor);
		pnlHeader.setBounds(0, 0, 1086, 65);
		contentPane.add(pnlHeader);
		pnlHeader.setLayout(null);

		Button btnMenu = new Button("|||");
		btnMenu.setFocusable(false);
		btnMenu.setBounds(23, 16, 38, 38);
		btnMenu.setForeground(Utils.primaryColor);
		btnMenu.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		btnMenu.setBorder(BorderFactory.createEmptyBorder());
		btnMenu.setBackground(Color.WHITE);
		btnMenu.setBorderColor(Utils.primaryColor);
		btnMenu.setRadius(8);
		btnMenu.setFocusable(false);
		pnlHeader.add(btnMenu);

		JLabel lblTitle = new JLabel(headingTitle.toUpperCase());
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setBounds(76, 17, 948, 32);
		lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
		pnlHeader.add(lblTitle);

		btnBack = new Button();
		btnBack.setFocusable(false);
		btnBack.setIcon(new ImageIcon("Icon\\back 1.png"));
		btnBack.setColor(Utils.primaryColor);
		btnBack.setColorOver(Utils.primaryColor);
		btnBack.setColorClick(Utils.primaryColor);
		btnBack.setBorderColor(Utils.primaryColor);
		btnBack.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnBack.setBounds(954, 1, 62, 62);
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame jFrame = StackFrame.pop();

				if (StackFrame.empty()) {
					JDialogCustom jDialogCustom = new JDialogCustom(frame);

					jDialogCustom.getBtnCancel().addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							StackFrame.push(jFrame);
						};
					});

					jDialogCustom.getBtnOK().addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							System.exit(1);
						};
					});

					jDialogCustom.showMessage("Đóng ứng dụng", "Bạn có muốn đóng ứng dụng không?");
				} else {
					StackFrame.peek().setVisible(true);
					jFrame.setVisible(false);
				}
			}
		});
		pnlHeader.add(btnBack);

//		Code menu
		GoogleMaterialIcon googleIcon = new GoogleMaterialIcon();
		googleIcon.setIcon(GoogleMaterialDesignIcon.USB);
		Menu menu = new Menu();
		drawer = Drawer.newDrawer(frame).addChild(menu).build();
		menu.setDrawer(drawer);

//		Show/Hide menu
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

	public void setjPanel(JPanel jPanel) {
		this.jPanel = jPanel;
	}

	public Button getBtnBack() {
		return btnBack;
	}

	public void setBtnBack(Button btnBack) {
		this.btnBack = btnBack;
	}

}
