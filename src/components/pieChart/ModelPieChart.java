package components.pieChart;

import java.awt.Color;

/**
 * Thành phần của biểu đồ tròn
 * 
 * @author ThaoHa
 *
 */
public class ModelPieChart {
	private String name;
	private double values;
	private Color color;

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

	public ModelPieChart() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getValues() {
		return values;
	}

	public void setValues(double values) {
		this.values = values;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
