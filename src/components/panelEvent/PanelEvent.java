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
	private boolean isActive = true;

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
		setBackground(isActive ? backgroundColor : Utils.getOpacity(backgroundColor, 0.6f));
	}

	public PanelEvent(int rounded) {
		super(rounded);
		backgroundColor = getBackground();

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (isOver || !isEnabled())
					return;
				setBackground(Utils.getOpacity(backgroundColor, 0.9f));
				isOver = true;
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (isOver) {
					setActive(isActive);
					isOver = false;
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (isEnabled())
					setBackground(Utils.getOpacity(backgroundColor, 0.8f));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				setActive(isActive);
			}
		});
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
		setBackground(backgroundColor);
	}
}
