package components.comboBox;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JComboBox;

import components.listCellRenderer.ListCellRendererCustom;
import utils.Utils;

public class ComboBox<E> extends JComboBox<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color backgroundEnabled = Utils.primaryColor;
	private Color foregroundEnabled = Color.BLACK;
	private Color backgroundUnEnabled = Utils.getOpacity(Utils.primaryColor, 0.6f);
	private Color foregroundUnEnabled = Utils.getOpacity(Color.BLACK, 0.6f);

	@SuppressWarnings("unchecked")
	public ComboBox() {
		// TODO Auto-generated constructor stub
		super();
		ComboBox<E> _this = this;
		setRenderer(new ListCellRendererCustom());

		addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				// TODO Auto-generated method stub
				if (evt.getNewValue().getClass().equals(Boolean.class)) {
					boolean value = (boolean) evt.getNewValue();
					if (evt.getPropertyName().equals("enabled")) {
						if (value) {
							_this.setForeground(foregroundEnabled);
							_this.setBackground(backgroundEnabled);
						} else {
							_this.setForeground(foregroundUnEnabled);
							_this.setBackground(backgroundUnEnabled);
						}
					}
				}
			}
		});
	}

	public Color getBackgroundUnEnabled() {
		return backgroundUnEnabled;
	}

	public void setBackgroundUnEnabled(Color backgroundUnEnabled) {
		this.backgroundUnEnabled = backgroundUnEnabled;
	}

	public Color getForegroundUnEnabled() {
		return foregroundUnEnabled;
	}

	public void setForegroundUnEnabled(Color foregroundUnEnabled) {
		this.foregroundUnEnabled = foregroundUnEnabled;
	}

	public Color getBackgroundEnabled() {
		return backgroundEnabled;
	}

	public void setBackgroundEnabled(Color backgroundEnabled) {
		this.backgroundEnabled = backgroundEnabled;
	}

	public Color getForegroundEnabled() {
		return foregroundEnabled;
	}

	public void setForegroundEnabled(Color foregroundEnabled) {
		this.foregroundEnabled = foregroundEnabled;
	}

}
