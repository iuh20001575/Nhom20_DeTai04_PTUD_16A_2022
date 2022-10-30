package ui;

import javax.swing.SwingUtilities;

// Đăng nhập 800ms

public class Starting {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				DangNhap_GUI dangNhap_GUI = new DangNhap_GUI();
				SplashScreen splashScreen = new SplashScreen();

				splashScreen.setVisible(true);
				splashScreen.hanleOpen(dangNhap_GUI);
			}
		});
	}
}
