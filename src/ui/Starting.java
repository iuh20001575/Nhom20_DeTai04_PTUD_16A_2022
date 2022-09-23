package ui;

import javax.swing.SwingUtilities;

public class Starting {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new BarChart().setVisible(true);
			}
		});
	}
}
