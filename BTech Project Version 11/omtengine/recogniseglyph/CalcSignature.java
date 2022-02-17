package recogniseglyph;


import java.awt.Color;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.media.jai.InterpolationNearest;
import javax.media.jai.JAI;
import javax.media.jai.iterator.RandomIter;
import javax.media.jai.iterator.RandomIterFactory;



public class CalcSignature {
	
	
	
    // The reference image "signature" (25 representative pixels, each in R,G,B).
	// We use instances of Color to make things simpler.
	private Color[][] signature;
    
	// The base size of the images.
	private static final int baseSize = 300;
	
	
	private Color[][] osig;
	//int[25][][]
	

	
	// Where are all the files?
	//private static final String basePath =  "/home/rahul/project/image_comparison/images";
	
	public CalcSignature(String reference) throws IOException
    {		
        // Now we need a component to store X images in a stack, where X is the
        // number of images in the same directory as the original one.
		File[] others = getOtherImageFiles(reference);
		int noteRGB[]=new int[others.length*25];
		// For each image, calculate its signature and its distance from the
	    // reference signature.
	    RenderedImage[] rothers = new RenderedImage[others.length];
	    double[] distances = new double[others.length];
	    //System.out.println(others.length);
	    int k=0;
	    for (int o = 0; o < others.length; o++)
	    {
	    	rothers[o] = rescale(ImageIO.read(others[o]));
	    	osig=calcSignature(rothers[o]);	    	
	    	System.out.println(osig[0][1]);
	    	
	    
	   
	    
	    int l=0,m;
	    int xy;
	    for(int x=0;x<5;x++)
	    	for(int y=0;y<5;y++)
	    	{
	    		//xy=x*10+y;
	    		//if(l==1)
	    		//	l=0;
	    		noteRGB[k]=osig[x][y].getRGB();
	    		//noteRGB[k][l+1]=xy;
	    		//System.out.println("ori- "+osig[x][y].getRGB()+" "+osig[x][y].getBlue()+ " " +osig[x][y].getGreen()+ " " +osig[x][y].getRed() + " ");
	    		//int blue = noteRGB[k] & 0x000000FF;
				//int green = (noteRGB[k]>> 8) & 0x000000FF;
				//int red = (noteRGB[k]>> 16) & 0x000000FF;
				//System.out.println("dup- "+noteRGB[k]+" "+blue+ " " +green+ " " +red + " ");
				//System.out.println(osig[x][y].getRGB()+" "+osig[x][y].getBlue()+ " " +osig[x][y].getGreen()+ " " +osig[x][y].getRed() + " ");
	    		k++;l++;
	    	}
	    }
	    System.out.println("No of Images: "+others.length);
	    System.out.print('{');
	    for(int x=0;x<noteRGB.length;x++)
	    {
	    		if(x<noteRGB.length-1)
	    			System.out.print(noteRGB[x] + ",");
	    		else
	    			System.out.print(noteRGB[x]+ "};");
	    }
   

	    
    }
	
    public Color[][] weight()
    {
    	return osig;
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
	    for (int x = 0; x < 5; x++)
	    	for (int y = 0; y < 5; y++)
	    		sig[x][y] = averageAround(i, prop[x], prop[y]);
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
		 for (double x = px * baseSize - sampleSize; x < px * baseSize + sampleSize; x++)
	     {
			 for (double y = py * baseSize - sampleSize; y < py * baseSize + sampleSize; y++)
			 {
				 iterator.getPixel((int) x, (int) y, pixel);
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
	 private File[] getOtherImageFiles(String reference)
	 {
		 File dir = new File(reference);
	     // List all the image files in that directory.
	     File[] others = dir.listFiles(new ImageFileFilter());
	     return others;
	 }
	 

	 public static void main(String args[])
	 {
		 System.setProperty("com.sun.media.jai.disableMediaLib", "true"); // to get rid of medialib error
		 String treble="/home/rahul/project/train/trble/";
		 String bass="/home/rahul/project/train/bass/";
		 String n2="/home/rahul/project/train/n2/";
		 String n4="/home/rahul/project/train/n4/";
		 String n8="/home/rahul/project/train/n8/";
		 String n16="/home/rahul/project/train/n16/";
		 String n32="/home/rahul/project/train/n32/";
		 String r4="/home/rahul/project/train/r4/";
		 String s1="/home/rahul/project/train/s1/";
		 //String imagename="11.png";		 
		 //0-treble, 1-bass, 2-crotchet
		 //int[] symbol = new int[3]; 
		 //int max=0,pos=-1;
		 try{
			 //File file = new File(imagename); // I have bear.jpg in my working directory  
			 //FileInputStream fis = new FileInputStream(file);
			 //BufferedImage image = ImageIO.read(fis);
			 CalcSignature streb=new CalcSignature(bass);
		 }
		 catch (IOException e){
			 e.printStackTrace();
		 }
		 
			
	 }



}
