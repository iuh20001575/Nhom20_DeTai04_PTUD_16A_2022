package ui;

import javax.swing.SwingUtilities;

// Đăng nhập 800ms

public class Starting {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new DangNhap_GUI().setVisible(true);
			}
		});
	}
}
