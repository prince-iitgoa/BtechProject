package projection;

import drawimage.DisplayImage;
import omtengine.XYChart;

public class Projection {
	public Projection(int[] Projection, int length, String name)
	{
		XYChart tes=new XYChart(Projection,length,name);
		new DisplayImage(tes.getChart(1000, 600));
	}

}
