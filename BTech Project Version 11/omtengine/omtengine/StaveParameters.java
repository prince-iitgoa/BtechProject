/*_____ Program to find stave parameters such as stave line thickness ,distance between stave lines */
package omtengine;

	import java.awt.image.BufferedImage;
	//import java.io.File;
//	import java.io.FileInputStream;
//	import java.io.IOException;

//	import javax.imageio.ImageIO;

	/**
	 * The <code> XProjection </code> class will calculate the X-Projection of an image. The constructor
	 * is given a <code> BufferedImage </code> and then the <code> calcXProjection </method> is invoked
	 * to calculate the X-Projection of the <code> BufferedImage </code>.
	 * <p>
	 * The <code> XProjection </code> class is used as follows:
	 * <p> 
	 * <code>
	 * XProjection xProj = new XProjection(BufferedImage); <br>
	 * xProj.calcXProjection(startH, endH, startW, endW); <br>
	 * </code>
	 * <p>
	 * Calling the <code> calcXProjection </code> method will place the X-Projection of the <code> BufferedImage </code>
	 * in a int[] array which can be obtained by calling the <code> getYProjection </code> method.
	 * <p>
	 * 
	 * @author Prince Mathew
	 * @version 1.0
	 */

public class StaveParameters {
	

	
		//private int xProjection[];
		//private int size;
		private BufferedImage buffImage;
		private int b[]=new int[100];
		private int w[]=new int[100];
		
		
		/*public static void main(String args[]) throws IOException
		{
			int starH,enH,starW,enW;
	        File file = new File("ulttest.png"); // I have bear.jpg in my working directory  
	        FileInputStream fis = new FileInputStream(file);  
	        BufferedImage buffImage = ImageIO.read(fis); //reading the image file  
	        
	        StaveParameters sparam= new StaveParameters(buffImage);
	        starH=1;
	        starW=1;
	        enH=buffImage.getHeight();
	        enW=buffImage.getWidth();
	        sparam.calcStaveParameters(starH, enH, starW, enW);
	       
	       sparam.printW();
	       sparam.printB();
	       sparam.findStaveParameters();
	        
		}*/
		public StaveParameters(BufferedImage buffImage)
		{
			this.buffImage = buffImage;
		}
	
		
		public void calcStaveParameters(int startH, int endH, int startW, int endW)
		{
		//	int size = Math.abs(endW - startW) + 1;
			int countb=1,countw=1;
			
			//System.out.println("Size: " + size);
		//	this.size = size;
	//		xProjection = new int[size];
			
			for (int i = startW; i < endW; i += 1)
			{
				for (int j = startH; j < endH; j += 1)
				{
					int color = 0;
					
						color = buffImage.getRGB(i, j);
					
					if (color != -1) //if black pixel
					{
						countb++;
						if(countw<100 && countw!=0)
							w[countw]+=1;
						countw=0;
					}
					else
					{
						countw++;
						if(countb<100 & countb!=0)
							b[countb]+=1;
						countb=0;
					}
				}
			}
		}
		
		/**
		 * Returns the resulting X-Projection of the BufferedImage
		 * @return xProjection
		 */
		
		public int[] getW()
		{
			return w;
		}
		
		public int[] getB()
		{
			return b;
		}
		
		
		public int[] findStaveParameters()
		{
			int maxb=0,maxw=0,i;
			int tb=0,tw=0;
			int sp[]=new int[2];
			for(i=0;i<100;i++)
			{
				if(b[i]>maxb)
				{
					maxb=b[i];
					tb=i;
				}
				if(w[i]>maxw)
				{
					maxw=w[i];
					tw=i;
				}
			}
			sp[0]=tb;sp[1]=tw;
			
			System.out.println("Line Thickness");
			System.out.println(tb);
			System.out.println("Distance Between Lines");
			System.out.println(tw);
			return sp;
		}
		/**
		 * Prints the X-Projection of the BufferedImage
		 *
		 */
		public void printB()
		{
			System.out.println("Black Pixels");
			for (int i=0; i<100; i+=1)
			{
				System.out.println(b[i]);
			}
			System.out.println("END Black Pixels");
		}
		public void printW()
		{
			System.out.println("White Pixels");
			for (int i=0; i<100; i+=1)
			{
				System.out.println(w[i]);
			}
			System.out.println("END White Pixels");
		}
		
	}