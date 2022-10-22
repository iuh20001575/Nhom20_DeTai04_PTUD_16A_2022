package components.barChart;

public class ModelChart {
    private String label;
    private double values[];

    public ModelChart() {
    }

    public ModelChart(String label, double[] values) {
        this.label = label;
        this.values = values;
    }

    public String getLabel() {
        return label;
    }

    public double getMaxValues() {
        double max = 0;
        for (double v : values) {
            if (v > max) {
                max = v;
            }
        }
        return max;
    }

    public double[] getValues() {
        return values;
    }
    public void setLabel(String label) {
        this.label = label;
    }

    public void setValues(double[] values) {
        this.values = values;
    }
}