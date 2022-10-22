package components.pieChart;

import java.awt.Color;

/**
 * Thành phần của biểu đồ tròn
 *
 */
public class ModelPieChart {
	private Color color;
	private String name;
	private double values;

	public ModelPieChart() {
	}

	/**
	 * Khởi tạo thành phần của biểu đồ tròn
	 * 
	 * @param name   tên thành phần
	 * @param values giá trị của thành phần
	 * @param color  màu sắc của thành phần
	 */
	public ModelPieChart(String name, double values, Color color) {
		this.name = name;
		this.values = values;
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public String getName() {
		return name;
	}

	public double getValues() {
		return values;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setValues(double values) {
		this.values = values;
	}
}
