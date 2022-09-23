package ui;

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import barChart.Chart;
import barChart.ModelChart;

public class BarChart extends JFrame implements WindowListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Chart chart;

	public BarChart() {
		this.setTitle("Bar Chart");
		this.setSize(1000, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		JPanel jPanel = new JPanel();
		this.add(jPanel);
		
		chart = new Chart();
		chart.addLegend("Tháng 7", new Color(238, 255, 65));
		chart.addLegend("Tháng 8", new Color(24, 255, 255));
		chart.addLegend("Tháng 9", new Color(105, 240, 174));
		chart.addData(new ModelChart("Hát", new double[] {10, 20, 30}));
		chart.addData(new ModelChart("Dịch vụ", new double[] {50, 40, 90}));
		chart.addData(new ModelChart("Khác", new double[] {10, 20, 30}));
		
		jPanel.add(chart);
		
		this.addWindowListener(this);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		chart.start();
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
