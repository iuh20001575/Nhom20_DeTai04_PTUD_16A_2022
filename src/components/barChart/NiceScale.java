package components.barChart;

public class NiceScale {

    private double max;
    private int maxTicks = 10;
    private double min;
    private double niceMax;
    private double niceMin;
    private double range;
    private double tickSpacing;

    public NiceScale(final double MIN, final double MAX) {
        min = MIN;
        max = MAX;
        calculate();
    }

    private void calculate() {
        range = niceNum(max - min, false);
        tickSpacing = niceNum(range / (maxTicks - 1), true);
        niceMin = Math.floor(min / tickSpacing) * tickSpacing;
        niceMax = Math.ceil(max / tickSpacing) * tickSpacing;
    }

    public double getMax() {
        return max;
    }

    public int getMaxTicks() {
        return maxTicks;
    }

    public double getMin() {
        return min;
    }

    public double getNiceMax() {
        return niceMax;
    }

    public double getNiceMin() {
        return niceMin;
    }

    public double getTickSpacing() {
        return tickSpacing;
    }

    private double niceNum(final double RANGE, final boolean ROUND) {
        double exponent;
        double fraction;
        double niceFraction;

        exponent = Math.floor(Math.log10(RANGE));
        fraction = RANGE / Math.pow(10, exponent);

        if (ROUND) {
            if (fraction < 1.5) {
                niceFraction = 1;
            } else if (fraction < 3) {
                niceFraction = 2;
            } else if (fraction < 7) {
                niceFraction = 5;
            } else {
                niceFraction = 10;
            }
        } else {
            if (fraction <= 1) {
                niceFraction = 1;
            } else if (fraction <= 2) {
                niceFraction = 2;
            } else if (fraction <= 5) {
                niceFraction = 5;
            } else {
                niceFraction = 10;
            }
        }
        return niceFraction * Math.pow(10, exponent);
    }

    public void setMax(double max) {
        this.max = max;
        calculate();
    }

    public void setMaxTicks(final int MAX_TICKS) {
        maxTicks = MAX_TICKS;
        calculate();
    }

    public void setMin(double min) {
        this.min = min;
        calculate();
    }

    public void setMinMax(final double MIN, final double MAX) {
        min = MIN;
        max = MAX;
        calculate();
    }

}