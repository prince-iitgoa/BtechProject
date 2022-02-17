/*_____ Predefined- used to draw chart _____*/
package omtengine;

import java.awt.image.BufferedImage;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class XYChart
{
	private JFreeChart chart;
	/*public static void main(String args[])
	{
		int a[]={10,20,30,40,50,60,70,80,90,100,90,80,70,60,50,40,30,20,10,0};
		String test="test1";
		XYChart tes=new XYChart(a,20,test);
		tes.getChart(20, 20);
	}*/
	public XYChart(int data[], int size, String name)
	{
		XYSeries series = new XYSeries(name);
		for (int i=0; i<size; i+=1)
			series.add(i, data[i]);
		XYDataset xyDataset = new XYSeriesCollection(series);
        
		chart = ChartFactory.createXYAreaChart(name, "width", "# Pixels", xyDataset, PlotOrientation.VERTICAL, true, false, false);
		
	}
	
	public XYChart(int size, int data[], String name)
	{
		XYSeries series = new XYSeries(name);
		for (int i=0; i<size; i+=1)
			series.add(i, data[i]);
		XYDataset xyDataset = new XYSeriesCollection(series);
        
		chart = ChartFactory.createXYAreaChart(name, "width", "# Pixels", xyDataset, PlotOrientation.HORIZONTAL, true, false, false);
		
	}
	
	public BufferedImage getChart(int x, int y)
	{
		return chart.createBufferedImage(x, y);
	}
}
