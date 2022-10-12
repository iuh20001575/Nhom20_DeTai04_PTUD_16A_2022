package components.panelEvent;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import components.panelRound.PanelRound;
import utils.Utils;

public class PanelEvent extends PanelRound {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color backgroundColor;
	private boolean isOver;

	public PanelEvent(int rounded) {
		super(rounded);
		backgroundColor = getBackground();

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (isOver)
					return;
				setBackground(Utils.getOpacity(backgroundColor, 0.9f));
				isOver = true;
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (isOver) {
					setBackground(backgroundColor);
					isOver = false;
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				setBackground(Utils.getOpacity(backgroundColor, 0.8f));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				setBackground(backgroundColor);
			}
		});
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
		setBackground(backgroundColor);
	}
}
