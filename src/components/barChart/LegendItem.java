package components.barChart;

public class LegendItem extends javax.swing.JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private LabelColor lblColor;
	private javax.swing.JLabel lblName;

	public LegendItem(ModelLegend data) {
		initComponents();
		setOpaque(false);
		lblColor.setBackground(data.getColor());
		lblName.setText(data.getName());
	}
	
	private void initComponents() {

		lblColor = new LabelColor();
		lblName = new javax.swing.JLabel();

		lblColor.setText("labelColor1");

		lblName.setForeground(new java.awt.Color(100, 100, 100));
		lblName.setText("Name");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addComponent(lblColor, javax.swing.GroupLayout.PREFERRED_SIZE, 16,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(lblName)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addComponent(lblName).addGap(0, 0, Short.MAX_VALUE))
						.addComponent(lblColor, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addContainerGap()));
	}
}