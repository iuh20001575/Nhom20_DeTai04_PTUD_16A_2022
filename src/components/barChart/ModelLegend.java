package components.barChart;

import java.awt.Color;

public class ModelLegend {
    private Color color;
    private String name;

    public ModelLegend() {
    }

    public ModelLegend(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    public void setName(String name) {
        this.name = name;
    }
}