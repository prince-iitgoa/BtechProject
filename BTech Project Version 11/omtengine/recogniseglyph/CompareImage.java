package recogniseglyph;

//import mlmodule;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
//import java.io.File;
//import java.io.FileInputStream;
import java.io.IOException;






import javax.imageio.ImageIO;
//import javax.imageio.ImageIO;
import javax.media.jai.InterpolationNearest;
import javax.media.jai.JAI;
import javax.media.jai.iterator.RandomIter;
import javax.media.jai.iterator.RandomIterFactory;

import omtengine.PitchDuration;
//import drawimage.DisplayImage;

//import java.util.ArrayList;




class FindSymbol {


    // The reference image "signature" (25 representative pixels, each in R,G,B).
	// We use instances of Color to make things simpler.
	private Color[][] signature;
    
	// The base size of the images.
	private static final int baseSize = 300;
	private int count=0;
	private int durationx=0;
	int imgno;
	

	
	public FindSymbol(int[] reference, BufferedImage srcimage, String note,int imgno) throws Exception
    {
		RenderedImage src=(RenderedImage)srcimage;
		
		// Put the reference, scaled,
		RenderedImage ref = rescale(src);
		
        // Calculate the signature vector for the reference.
		signature = calcSignature(ref);  
		
		int[][] otherimg=new int[5][5];
		this.imgno=imgno;
		
		// For each image, calculate its signature and its distance from the
	    // reference signature.
	    //RenderedImage[] rothers = new RenderedImage[others.length];
	    double[] distances = new double[imgno];
	    int k=0;
	    for (int o = 0; o < imgno; o++)
	    {
	    	
	    	for(int x=0;x<5;x++)
	    		for(int y=0;y<5;y++)
	    		{
	    			otherimg[x][y]=reference[k];
	    			k++;
	    		}
	    	distances[o] = calcDistance(otherimg);
	    	
	    }
	    
	    
	    for (int o = 0; o < imgno; o++)
	    {
	    	//System.out.printf("%s[%d] % 13.3f\n", note,o,distances[o]);
	    	if(distances[o]==0)
	    		count=count+1000+1000*((1/imgno)*10);  //Got exact same image in training set
	    	if(distances[o]<100)
	    		count+=100+100*((1/imgno)*10);
	    	else if(distances[o]<500)
	    		count+=25+25*((1/imgno)*5);
	    	else if(distances[o]<1000)
	    		count+=10+10*((1/imgno));
	    	else if (distances[o]<2000)
	    		count+=3+(3*((1/imgno)));
	    	
	    }
	    int[] tempSig=new int[25];;
	    int g=0;
	    String temp=new String();
	    for(int i=0;i<5;i++)
	    {
	    	for(int j=0;j<5;j++)
	    	{
	    		tempSig[g]=signature[i][j].getRGB();
	    		temp= temp+ Integer.toString(tempSig[g])+", ";
	    		g++;
	    	}
	    }
	    
	    ImageIO.write(srcimage, "png", new File(temp + ".png"));
	    mlmodule testing=new mlmodule();
	    durationx=testing.ClassifySignature(tempSig);
	    
    }
	

    public int weight()
    {
    	return 10000000;  //count
    }
    public int getdurm()
    {
    	return durationx;
    }
	
	
	private RenderedImage rescale(RenderedImage i)
	{
	     float scaleW = ((float) baseSize) / i.getWidth();
	     float scaleH = ((float) baseSize) / i.getHeight();
	         // Scales the original image
	     ParameterBlock pb = new ParameterBlock();
	     pb.addSource(i);
	     pb.add(scaleW);
	     pb.add(scaleH);
	     pb.add(0.0F);
	     pb.add(0.0F);
	     pb.add(new InterpolationNearest());
	     // Creates a new, scaled image and uses it on the DisplayJAI component
	     return JAI.create("scale", pb);
	}
	
	/*
	 * This method calculates and returns signature vectors for the input image.
	 */
	private Color[][] calcSignature(RenderedImage i)
	{
		// Get memory for the signature.
	    Color[][] sig = new Color[5][5];
	    // For each of the 25 signature values average the pixels around it.
	    // Note that the coordinate of the central pixel is in proportions.
	    float[] prop = new float[]{1f / 10f, 3f / 10f, 5f / 10f, 7f / 10f, 9f / 10f};
	    //System.out.println("next: ");
	    for (int x = 0; x < 5; x++)
	    	for (int y = 0; y < 5; y++)
	    	{
	    		//System.out.println("prop[x]="+prop[x]+" prop[y]="+prop[y]);
	    		sig[x][y] = averageAround(i, prop[x], prop[y]);
	    	//	System.out.print(sig[x][y]);
	    	}
	     return sig;
	 }
	
	 /*
	  * This method averages the pixel values around a central point and return the
	  * average as an instance of Color. The point coordinates are proportional to
	  * the image.
	  */
	 private Color averageAround(RenderedImage i, double px, double py)
	 {
		 
		 // Get an iterator for the image.
		 RandomIter iterator = RandomIterFactory.create(i, null);
		 // Get memory for a pixel and for the accumulator.
		 double[] pixel = new double[3];
		 double[] accum = new double[3];
		 // The size of the sampling area.
		 int sampleSize = 15;
		 int numPixels = 0;
		 // Sample the pixels.
		 //System.out.println("x limit "+px);
		 for (double x = px * baseSize - sampleSize; x < px * baseSize + sampleSize; x++)
	     {
			 for (double y = py * baseSize - sampleSize; y < py * baseSize + sampleSize; y++)
			 {
				 iterator.getPixel((int) x, (int) y, pixel);
				 //pixel[0]=i.get
				 accum[0] += pixel[0];
				 accum[1] += pixel[1];
				 accum[2] += pixel[2];
				 numPixels++;
			  }
	     }
		 // Average the accumulated values.
		 accum[0] /= numPixels;
		 accum[1] /= numPixels;
		 accum[2] /= numPixels;
		 return new Color((int) accum[0], (int) accum[1], (int) accum[2]);
	 }

	 /*
	  * This method get all image files in the same directory as the reference.
	  * Just for kicks include also the reference image.
	  */
	 /*private File[] getOtherImageFiles(String reference)
	 {
		 File dir = new File(reference);
	     // List all the image files in that directory.
	     File[] others = dir.listFiles(new ImageFileFilter());
	     return others;
	 }*/
	 
	 
	 private double calcDistance(int[][] other)
     {
		 // Calculate the signature for that image.
		 //Color[][] sigOther = calcSignature(other);

		 // There are several ways to calculate distances between two vectors,
		 // we will calculate the sum of the distances between the RGB values of
		 // pixels in the same positions.
		 double dist = 0;
		 
		 for (int x = 0; x < 5; x++)
			 for (int y = 0; y < 5; y++)
			 {
				 //int blue = other & 0x000000FF;
				 //int green = (other>> 8) & 0x000000FF;
				 //int red = (other>> 16) & 0x000000FF;
				 //System.out.println(other[x][y]);
				 // nw System.out.print(signature[x][y]);
				 int r1 = signature[x][y].getRed();
				 int g1 = signature[x][y].getGreen();
				 int b1 = signature[x][y].getBlue();
				 int r2 = (other[x][y]>>16) & 0xFF;
				 int g2 = (other[x][y]>>8) & 0xFF;
				 int b2 = (other[x][y]) & 0xFF;
				 double tempDist = Math.sqrt((r1 - r2) * (r1 - r2) + (g1 - g2)* (g1 - g2) + (b1 - b2) * (b1 - b2));
		//		System.out.print(signature[x][y].getRGB());
				 dist += tempDist;
				// System.out.println(dist);
				 
			 }
		 return dist;
     }

}







public class CompareImage {
	
	double position;
	int n;
	public CompareImage(double position,BufferedImage image,int midilist[],int n,PitchDuration pd) throws Exception
	
	{
		 //new DisplayImage(image);
		this.n=n;
		 this.position=position;
		int treble[]={-1,-6316129,-7500403,-5263441,-1,-1,-2302756,-8224126,-3355444,-1,-65794,-6579301,-7105645,-3487030,-1,-197380,-1710619,-8026747,-11842741,-1,-197380,-1710619,-3026479,-1710619,-1,-1,-1118482,-4671304,-1118482,-1,-1,-1118482,-14803426,-6645094,-1,-1,-14145496,-8882056,-6184543,-1,-1,-1644826,-11513776,-2829100,-1,-1,-14013910,-6974059,-2763307,-1,-1,-6316129,-7500403,-5263441,-1,-1,-2302756,-8224126,-3355444,-1,-65794,-6579301,-7105645,-3487030,-1,-197380,-1710619,-8026747,-11842741,-1,-197380,-1710619,-3026479,-1710619,-1,-2368549,-1973791,-921103,-2105377,-1,-197380,-2105377,-13027015,-2894893,-1,-1579033,-1973791,-8882056,-4079167,-131587,-65794,-6447715,-12369085,-7237231,-1,-723724,-11316397,-9145228,-7039852,-1,-1,-1710619,-10000537,-5526613,-1,-1,-9342607,-7303024,-3618616,-1,-65794,-4210753,-7829368,-3355444,-1,-197380,-4276546,-7368817,-10000537,-1,-197380,-1710619,-3487030,-1907998,-1,-5263441,-1118482,-1710619,-1710619,-1,-1,-1118482,-7434610,-6710887,-15132391,-526345,-5066062,-9013642,-7697782,-4342339,-197380,-9605779,-1710619,-9539986,-7763575,-1,-1118482,-1710619,-9803158,-1,-2368549,-2565928,-4210753,-1118482,-1,-526345,-2500135,-13355980,-5789785,-131587,-1579033,-4802890,-10329502,-2171170,-1842205,-3487030,-4013374,-9474193,-11119018,-65794,-1,-12829636,-8026747,-7829368,-1,-1,-1118482,-11447983,-1776412,-1,-1,-6316129,-7566196,-6381922,-855310,-6381922,-3223858,-11316397,-3355444,-1052689,-65794,-13224394,-7697782,-12303292,-1,-1,-1118482,-4671304,-4737097,-1,-1,-1710619,-10000537,-5526613,-1,-1,-9342607,-7303024,-3618616,-1,-65794,-4210753,-7829368,-3355444,-1,-197380,-4276546,-7368817,-10000537,-1,-197380,-1710619,-3487030,-1907998,-1,-1,-1710619,-3487030,-1710619,-1,-1,-1710619,-12829636,-4473925,-1,-1,-10724260,-7105645,-5131855,-1,-1,-1710619,-9934744,-3355444,-1,-1,-11382190,-5921371,-3881788,-1};
		 int bass[]={-1,-2829100,-1118482,-1118482,-1,-1,-1184275,-6052957,-1118482,-1,-1,-6908266,-12829636,-1118482,-1,-1,-4802890,-7303024,-1118482,-1,-1,-1118482,-1118482,-1118482,-1,-1,-2829100,-1118482,-1118482,-1,-1,-1184275,-6052957,-1118482,-1,-1,-6908266,-12829636,-1118482,-1,-1,-4802890,-7303024,-1118482,-1,-1,-1118482,-1118482,-1118482,-1};
		 int n2[]={-1,-1710619,-1710619,-3618616,-1,-1,-1710619,-1710619,-7829368,-1,-1,-1710619,-1710619,-7829368,-1,-1,-1710619,-1710619,-7303024,-1,-1710619,-3223858,-3223858,-3223858,-1710619,-1,-1710619,-1710619,-1710619,-1,-1,-1710619,-1710619,-1710619,-1710619,-1,-1710619,-1710619,-2236963,-855310,-1,-1710619,-1710619,-1907998,-1,-1710619,-3223858,-3223858,-3223858,-1710619};
		
		 int n4[]={-1,-1710619,-6645094,-5723992,-1,-1,-1710619,-11645362,-1710619,-1,-1,-1710619,-13816531,-1710619,-1,-1,-1710619,-14342875,-1710619,-1,-1,-1710619,-8158333,-1710619,-1,-1,-1710619,-3947581,-3947581,-1,-1,-1710619,-14408668,-14408668,-1,-1,-1710619,-16448251,-16448251,-1,-1,-1710619,-12105913,-12105913,-1,-1118482,-2697514,-2697514,-2697514,-1118482,-1,-1710619,-12698050,-8224126,-1,-1,-1710619,-12895429,-1710619,-1,-1,-1710619,-12895429,-1710619,-1,-1,-1710619,-11184811,-1710619,-1,-1,-1710619,-5460820,-1710619,-1,-1,-1118482,-1710619,-6250336,-3355444,-1,-1118482,-5000269,-1710619,-1,-1,-1118482,-7500403,-1710619,-1,-1,-1118482,-7960954,-1710619,-1,-1,-1118482,-4539718,-1710619,-1,-1,-1710619,-1710619,-1710619,-1,-1,-1710619,-1710619,-2171170,-1,-1,-1710619,-1710619,-3355444,-1,-1,-1710619,-1710619,-3355444,-1,-1,-1710619,-10263709,-10790053,-1,-1,-1710619,-1710619,-1710619,-1,-1,-1710619,-1710619,-1710619,-7368817,-1,-1710619,-1710619,-1710619,-6776680,-1,-1710619,-1710619,-1710619,-1973791,-1,-1710619,-1710619,-1710619,-1,-1,-1710619,-1710619,-4408132,-1,-1,-1710619,-1710619,-9408400,-1,-1,-1710619,-1710619,-11776948,-1,-1,-1710619,-1710619,-12105913,-1,-1,-1710619,-10263709,-12500671,-1,-1,-1710619,-6645094,-5723992,-1,-1,-1710619,-11645362,-1710619,-1,-1,-1710619,-13816531,-1710619,-1,-1,-1710619,-14342875,-1710619,-1,-1,-1710619,-8158333,-1710619,-1,-1,-1710619,-3947581,-3947581,-1,-1,-1710619,-14408668,-14408668,-1,-1,-1710619,-16448251,-16448251,-1,-1,-1710619,-12105913,-12105913,-1,-1118482,-2697514,-2697514,-2697514,-1118482,-1,-1710619,-1710619,-5658199,-1,-1,-1710619,-1710619,-7566196,-1,-1,-1710619,-1710619,-6908266,-1,-1,-1710619,-1710619,-4539718,-1,-1,-9342607,-10263709,-1710619,-1,-1,-1710619,-1710619,-1710619,-1,-1,-1710619,-1710619,-2171170,-1,-1,-1710619,-1710619,-3355444,-1,-1,-1710619,-1710619,-3355444,-1,-1,-1710619,-10263709,-10790053,-1,-1,-1710619,-12763843,-8750470,-1,-1,-1710619,-12895429,-1710619,-1,-1,-1710619,-12895429,-1710619,-1,-1,-1710619,-11382190,-1710619,-1,-1,-1710619,-6316129,-1710619,-1,-1,-1710619,-1710619,-10329502,-1,-1,-1710619,-1710619,-15132391,-1,-1,-1710619,-1710619,-15132391,-1,-1,-1710619,-1710619,-13421773,-1,-1,-4868683,-10263709,-7566196,-1};
		int n8[]={-1,-4473925,-1710619,-1710619,-1,-1,-6184543,-1710619,-4473925,-1,-1,-7303024,-1710619,-15132391,-1,-1,-8421505,-1710619,-15461356,-1,-1,-1907998,-2236963,-2039584,-1,-1,-1710619,-1710619,-4144960,-1,-1,-1710619,-1710619,-14935012,-1,-1,-1710619,-1710619,-15856114,-1,-1,-1710619,-7237231,-5000269,-1,-1,-1710619,-1710619,-1710619,-1,-1,-9539986,-1710619,-1710619,-1,-1,-9539986,-1710619,-1973791,-1,-1,-9539986,-2763307,-2565928,-1,-1,-10658467,-7763575,-1710619,-1,-1,-9539986,-1710619,-1710619,-1,-1,-1710619,-1710619,-1710619,-1,-1,-1710619,-1710619,-8618884,-1,-1,-1710619,-1710619,-16185079,-1,-1,-1710619,-8224126,-10592674,-1,-1,-1710619,-1710619,-1710619,-1,-1,-1710619,-1710619,-1710619,-1,-1,-1710619,-1710619,-3355444,-1,-1,-1710619,-2829100,-1710619,-1,-1,-11382190,-7237231,-1710619,-1,-1,-3618616,-1710619,-1710619,-1,-1,-1710619,-1710619,-1710619,-1,-1,-1710619,-1710619,-3355444,-1,-1,-1710619,-2829100,-1710619,-1,-1,-11382190,-7237231,-1710619,-1,-1,-3618616,-1710619,-1710619,-1,-1,-1710619,-1710619,-1710619,-1,-1,-1710619,-1710619,-4473925,-1,-1,-1710619,-1710619,-15132391,-1,-1,-1710619,-1710619,-15461356,-1,-1,-1710619,-2236963,-2039584,-1,-1,-1118482,-1710619,-10066330,-1,-1,-1118482,-1710619,-15987700,-1,-1,-1118482,-1710619,-14935012,-1,-1,-1118482,-8224126,-5526613,-1,-1,-1118482,-1710619,-1710619,-1,-1,-1710619,-1710619,-10658467,-1,-1,-2829100,-4210753,-7303024,-1,-1,-1710619,-1710619,-1710619,-1,-1,-1710619,-6250336,-1710619,-1,-1,-1710619,-2763307,-1710619,-1,-1,-1710619,-2105377,-1710619,-1,-1,-1710619,-1710619,-3552823,-1,-1,-1710619,-1710619,-7829368,-1,-1,-1710619,-7763575,-8947849,-1,-1,-1710619,-1710619,-1710619,-1};
		 int n16[]={-1,-1118482,-1710619,-8947849,-1,-1,-1118482,-9737365,-5197648,-1,-1,-1118482,-7829368,-8487298,-1,-1,-1118482,-9342607,-9803158,-1,-1,-1118482,-7829368,-6250336,-1,-1,-1118482,-1710619,-4605511,-1,-1,-1118482,-1710619,-8947849,-1,-1,-1118482,-1710619,-8947849,-1,-1,-1118482,-7829368,-1710619,-1,-1,-1118482,-7829368,-7500403,-1,-1,-1118482,-1710619,-9342607,-1,-1,-1118482,-1710619,-9539986,-1,-1,-1118482,-1710619,-6645094,-1,-1,-8421505,-1710619,-2171170,-1,-1,-8421505,-1710619,-9539986,-1,-1,-8421505,-1710619,-9539986,-1,-1,-8421505,-3750202,-4934476,-1,-1,-8421505,-1710619,-7895161,-1,-1,-8421505,-1710619,-9539986,-1,-1,-8421505,-3750202,-4934476,-1};
		 int n32[]={-1,-1710619,-1513240,-1381654,-6118750,-1,-1315861,-8882056,-12434878,-6052957,-1,-1250068,-1710619,-9145228,-5855578,-1,-1184275,-1250068,-9474193,-5921371,-1,-1184275,-1447447,-10197916,-5460820,-1,-2236963,-855310,-986896,-1,-1,-8092540,-6316129,-14013910,-1,-1,-3750202,-921103,-12171706,-1,-1,-1842205,-986896,-12369085,-1,-1,-2105377,-723724,-12369085,-1,-921103,-1250068,-1315861,-1447447,-1,-1973791,-1513240,-1710619,-1513240,-1,-1,-1579033,-1644826,-3223858,-1,-5197648,-6908266,-6513508,-8553091,-1,-10921639,-1513240,-1250068,-1513240,-2039584,-10921639,-1447447,-1842205,-1447447,-3158065,-10526881,-1447447,-2631721,-1447447,-1,-10395295,-1447447,-8421505,-1513240,-1,-12698050,-7303024,-3684409,-1842205,-1,-10461088,-1447447,-1842205,-1907998,-1,-8553091,-2960686,-2236963,-1250068,-1184275,-7960954,-2697514,-2039584,-1447447,-65794,-7895161,-2565928,-3618616,-1907998,-1,-10461088,-6579301,-8289919,-1315861,-1,-8289919,-2894893,-1776412,-855310,-1,-1,-2368549,-5000269,-7500403,-1,-197380,-7960954,-10461088,-9013642,-1,-4144960,-2171170,-5789785,-7434610,-1,-855310,-1973791,-6250336,-7500403,-1,-1,-2302756,-6513508,-7105645,-1,-10395295,-1579033,-1907998,-1447447,-263173,-10263709,-1447447,-1907998,-1447447,-65794,-10263709,-1447447,-2829100,-1447447,-1,-13750738,-9671572,-10658467,-1447447,-1,-10724260,-1447447,-1842205,-1447447,-1,-3750202,-8684677,-986896,-986896,-2631721,-3552823,-8092540,-986896,-1118482,-2829100,-3092272,-7368817,-921103,-4013374,-2894893,-2829100,-10132123,-6908266,-986896,-2171170,-2500135,-1447447,-1052689,-986896,-2631721,-5921371,-1644826,-1381654,-986896,-1,-6381922,-2302756,-1579033,-789517,-1,-6381922,-8158333,-1579033,-855310,-1,-9342607,-11382190,-1579033,-1052689,-1,-7631989,-3158065,-1513240,-1381654,-1,-1,-1447447,-6447715,-1447447,-1,-1,-1447447,-10526881,-1710619,-1,-1,-1447447,-9145228,-4605511,-1,-5855578,-7039852,-1710619,-5131855,-1,-5789785,-6645094,-1644826,-3223858,-1,-5263441,-6974059,-2500135,-1907998,-1,-4408132,-6513508,-7434610,-2565928,-1,-3881788,-6316129,-13487566,-3487030,-1,-2829100,-9605779,-5460820,-4013374,-1,-2763307,-7434610,-2236963,-4342339,-1,-1,-1644826,-1250068,-11579569,-4473925,-1,-2631721,-6974059,-14277082,-3750202,-1,-6974059,-1381654,-12829636,-3158065,-1,-4408132,-1381654,-12961222,-2434342,-1,-1315861,-1381654,-13553359,-1973791,-10855846,-1447447,-1842205,-1447447,-1,-10855846,-1907998,-4473925,-1447447,-1,-10724260,-1710619,-8421505,-1447447,-1,-12369085,-8289919,-3684409,-1447447,-592138,-6118750,-2236963,-1842205,-1447447,-4671304};
		 int r4[]={-1,-1710619,-1710619,-1710619,-1,-1,-1710619,-9474193,-1710619,-1,-1,-1710619,-14211289,-3355444,-1,-1,-1710619,-5131855,-1710619,-1,-3947581,-5263441,-5263441,-5263441,-3947581,-1,-1776412,-2236963,-1184275,-1,-1,-2236963,-13421773,-1381654,-1,-1,-2236963,-11316397,-1184275,-1,-1,-2236963,-13092808,-1710619,-1,-1,-2171170,-14145496,-1447447,-1,-1,-1118482,-1118482,-1118482,-1,-1,-1118482,-11645362,-1118482,-1,-1,-1118482,-14540254,-2829100,-1,-1,-1118482,-6645094,-1118482,-1,-1710619,-2697514,-2697514,-2697514,-1710619,-1,-1118482,-1118482,-1118482,-1,-1,-1118482,-11645362,-1118482,-1,-1,-1118482,-14540254,-2829100,-1,-1,-1118482,-6645094,-1118482,-1,-1710619,-2697514,-2697514,-2697514,-1710619,-1,-1118482,-1118482,-1118482,-1,-1,-1118482,-11645362,-1118482,-1,-1,-1118482,-14540254,-2829100,-1,-1,-1118482,-6645094,-1118482,-1,-1710619,-2697514,-2697514,-2697514,-1710619,-1,-1710619,-1710619,-1710619,-1,-1,-1710619,-9474193,-1710619,-1,-1,-1710619,-14211289,-3355444,-1,-1,-1710619,-5131855,-1710619,-1,-3947581,-5263441,-5263441,-5263441,-3947581,-1,-1118482,-1118482,-1118482,-1,-1,-1118482,-11645362,-1118482,-1,-1,-1118482,-14540254,-2829100,-1,-1,-1118482,-6645094,-1118482,-1,-1710619,-2697514,-2697514,-2697514,-1710619};
		 int s1[]={-1,-1710619,-15263977,-1710619,-1,-1,-1710619,-13158601,-1710619,-1,-1,-1710619,-1710619,-1710619,-1,-1,-1710619,-10263709,-1710619,-1,-1,-1710619,-8882056,-1710619,-1,-197380,-1710619,-1842205,-1907998,-1,-197380,-1710619,-2829100,-3355444,-1,-197380,-8421505,-11119018,-5000269,-1,-197380,-2171170,-9934744,-10000537,-1,-197380,-1710619,-2171170,-2236963,-1};
		
		 
		 //String imagename=br;		 
		 //0-treble, 1-bass
		 int symbol,i;
		// int[] symbol = new int[9];  // *** WHEN ADDING A NEW SYMBOL, CHANGE LIMIT **** //
		 int max=0,pos=-1;
	/*	 ArrayList<Integer> pitch= 	new ArrayList<Integer>();
		 ArrayList<Double> duration= 	new ArrayList<Double>();*/
		 try{
			 //File file = new File(imagename); // I have bear.jpg in my working directory  
			 //FileInputStream fis = new FileInputStream(file);
			 //BufferedImage image = ImageIO.read(fis);
			 System.setProperty("com.sun.media.jai.disableMediaLib", "true"); // to get rid of medialib error
			 
			// 
			 FindSymbol streb=new FindSymbol(treble,image,"treble",10); //comparison array, image to be compared, its name, no.of images
			/* symbol[0]=streb.weight();
			 //System.out.println("next folder");
			 FindSymbol sbass= new FindSymbol(bass,image,"bass",2);
			 symbol[1]=sbass.weight();
			 
			 //System.out.println("next folder");
			 FindSymbol sn8= new FindSymbol(n8,image,"1/8th",10);
			 symbol[2]=sn8.weight();
			 //System.out.println("next folder");
			 FindSymbol sn16= new FindSymbol(n16,image,"1/16",4);
			 symbol[3]=sn16.weight();
			 //System.out.println("next folder");
			 FindSymbol sn32= new FindSymbol(n32,image,"1/32",13);
			 symbol[4]=sn32.weight();
			 //System.out.print(treble[0].length);
			 FindSymbol sn4= new FindSymbol(n4,image,"1/4",13);
			 symbol[5]=sn4.weight();
			 FindSymbol sn2= new FindSymbol(n2,image,"1/2",2);
			 symbol[6]=sn2.weight();
			 FindSymbol sr4= new FindSymbol(r4,image,"r/4",7);
			 symbol[7]=sr4.weight();
			 FindSymbol ss1= new FindSymbol(s1,image,"s1",2);
			 symbol[8]=ss1.weight(); //comparison array, image to be compared, its name, no.of images*/
			 int dur=streb.getdurm(); // Returns duration after classification
			 // pos=streb.weight();
			// System.out.println("\nPredicted clsa :"+dur);
			 if(dur!=-1)
			 {
				 switch(dur)
				 {
				 case 0:
					 System.out.println("treble");
					 //System.out.println(midi);
					 break;
				 case 1:
					 System.out.println("bass");
					 //System.out.println(midi);
					 break;
				 case 2:
					 System.out.println("1/8th - duration 1/2");
					 for(i=0;i<n;i++)
					 {
					 System.out.println(midilist[i]);
					 pd.add(midilist[i], 1,position);
					 }
					 this.position=this.position+1;
					 break;
				 case 3:
					 System.out.println("1/16th - duration 1/4");
					 
					 for(i=0;i<n;i++)
					 {
					 System.out.println(midilist[i]);
					 pd.add(midilist[i], 0.5,position);
					 }
					 this.position=this.position+0.5;
					 break;
				 case 4:
					 System.out.println("1/32th - duration 1/8");
					 for(i=0;i<n;i++)
					 {
					 System.out.println(midilist[i]);
					 pd.add(midilist[i], 0.25,position);
					 }
					 this.position=this.position+0.25;
					 break;
				 case 5:
					 System.out.println("1/4th - duration 1");
					 for(i=0;i<n;i++)
					 {
					 System.out.println(midilist[i]);
					 pd.add(midilist[i], 2,position);
					 }
					 this.position=this.position+2;
					 break;
				 case 6:
					 System.out.println("1/2th - duration 2");
					 for(i=0;i<n;i++)
					 {
					 System.out.println(midilist[i]);
					 pd.add(midilist[i], 4,position);
					 }
					 this.position=this.position+4;
					 break;
				 case 7:  //vcrpyrvh rest realy adjstmta :P
 					 System.out.println("Rest 1/4");
					// this.position=this.position+2;
				 case 8:
					 System.out.println("scale");
					 break;
				 case 9:
					 System.out.println("Rest 1");
					// this.position=this.position+4;
					 break;
				 }
			 }
			 else
			 {
				 System.out.println("Cannot find anything / yet to recognize");
			 
			 }
		 }
		 catch (IOException e){
			 e.printStackTrace();
		 }
		 
			

	}
	public double returnposition()
	{
		System.out.print("compare imge" +position);
		return position;
	}

}
