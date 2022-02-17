/*_____ */package omtengine;

//import java.awt.Color;
import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;

//import javax.imageio.ImageIO;

import projection.Projection;
//import drawimage.DrawRectangle;

/*
 * 
 * @author Prince Mathew
 * @version 1.0
 */

public class StaveDetection
{
	private BufferedImage buffImage;
	private int stavearray[][];
	private int height;
	int stavelineno=0;
	//private int width;
	int blackp;
 int tb,tw;
	
	/*public static void main(String args[]) throws IOException			//Main defined to test code separately
	{
		
    int starH,enH,starW,enW;
    int sp[]=new int[2];
    
	File file = new File("score1.png"); // I have bear.jpg in my working directory  
    FileInputStream fis = new FileInputStream(file);  
    BufferedImage buffImage = ImageIO.read(fis); //reading the image file  
    
   StaveParameters sparam= new StaveParameters(buffImage);
	        starH=1;
	        starW=1;
	        enH=buffImage.getHeight();
	        enW=buffImage.getWidth();
	        sparam.calcStaveParameters(starH, enH, starW, enW);
    sp=sparam.findStaveParameters();
    StaveDetection sdetec= new StaveDetection(buffImage);
    sdetec.tb=sp[0];
    sdetec.tw=sp[1];
    sdetec.DetectStave(starH, enH, starW, enW);
	
	}*/
	public StaveDetection(BufferedImage buffImage)
	{
		this.buffImage = buffImage;
		height = buffImage.getHeight();
		//width=buffImage.getWidth();
	}
	
	
	/**
	 * Cacluate the Y-Projection of the BufferedImage
	 * @param startH Desired start Y-Coordinate of the BufferedImage
	 * @param endH Desired end Y-Coordinate of the BufferedImage
	 * @param startW Desired start X-Coordinate of the BufferedImage
	 * @param endW Desired end X-Coordinate of the BufferedImage
	 */

	public int[][] DetectStave(int startH, int endH, int startW, int endW,int blackp)
	{
	
		int height = endH - startH + 1;
		this.blackp=blackp;
		this.height = height;
		//int width= endW-startW+1;
		//this.width= width;
		int stavethres;
		int no,carray=0;
		
		int yProjection[];
		int tempstavearrayno[][]=new int[20][2];
		yProjection = new int[height];

		for (int i = startH; i < endH; i += 1)
		{
			for (int j = startW; j < endW; j += 1)
			{
				int color;
				try
				{
					color = buffImage.getRGB(j, i);
					if (color == -1) // white pixel
					{
						// Do nothing
					} else
					{
						yProjection[i - startH] += 1;
					}	
				}
				catch (ArrayIndexOutOfBoundsException e)
				{
					//ABSOLUTELY NEED TO FIX THIS
					//System.out.println("Array out of bounds exception -- calcYProjection");
					//System.out.println("Values:  i=" + i + "  j=" + j);
				}
				
			}
		}
		
		new Projection(yProjection,yProjection.length,"Stave Detection");
		
		no=0;
		int i;
		int maxy=0;
		for(i=startH;i<endH;i+=1)
		{
			if(yProjection[i]>maxy)
				maxy=yProjection[i];
		}
		if(blackp<2)
			stavethres= 88*maxy/100;			//percentage -- dynamically assigned  77 88..
		else if(blackp<6)
			stavethres= 77*maxy/100;			//percentage -- dynamically assigned  77 88.
		else if(blackp<10)
			stavethres= 66*maxy/100;
		else
			stavethres= 55*maxy/100;
		//int flag=0;
		
		for(i=startH;i<endH;i+=1)
		{
		//	int j;
			//lab1:
			if(yProjection[i]>stavethres)
			{	
				
				no+=1;
				
				if(no==5)
				{
					
					stavelineno+=1;
				
					tempstavearrayno[carray][0]=i-4*(tb+tw)+1;
					tempstavearrayno[carray][1]=i+tb;
					no=0;
					carray+=1;
				}
				i=i+tb+tw-2;
				//break lab1;
			}
		/*	else
			{//approximation1
				
				if(flag==0)
				{
					no+=2;
					flag=1;
				}
				else
				{
					no=0;
					flag=0;
				}
			}*/
		}

		System.out.println("Number of Staves Recognized");
		System.out.println(stavelineno);
		stavearray=new int[stavelineno][2];
	//	
		for(i=0;i<stavelineno;i++)
		{
			stavearray[i][0]=tempstavearrayno[i][0];
			stavearray[i][1]=tempstavearrayno[i][1];
			System.out.println(stavearray[i][0]);
			System.out.println(stavearray[i][1]);
			System.out.println("\n");
		}
		//drawrec.writeimg();
		return stavearray;
	}

	/*public int Returnstavestart()
	{
		return stavearray[0][0];
	}*/
	/*
	 * Returns the resulting Y-Projection of the BufferedImage
	 * @return yProjection
	 */
	
	/*public int[] getYProjection()
	{
		return yProjection;
	}
	
	*/
	/**
	 * Prints the Y-Projection of the BufferedImage
	 *
	 */
	
	/*public void printYProjection()
	{
		System.out.println("Y-Projection");
		for (int i = 0; i < height; i += 1)
		{
			System.out.println(yProjection[i]);
		}
		System.out.println("END Y-Projection");
	}
	
	*/
	/**
	 * Return the size of the yProjection array. In other words, <code> height = endH - startH </code>
	 * @return height
	 */
	
	public int getHeight()
	{
		return height;
	}
	
	
	
	//CONSIDER DELETING THESE METHODS
	
	/*public void writeYProjectionFile(DataOutputStream yProjOutput)
	{
		// findStaves();
		// System.out.println("********* Y-Projection **********");
		for (int i = 0; i < height; i += 1)
		{
			try
			{
				yProjOutput.writeBytes(String.valueOf(yProjection[i]));
				yProjOutput.writeBytes("\n");
			} catch (IOException e)
			{
				System.out.println("Could not write to outfile in method printY");
				e.printStackTrace();
			}

		}
		// System.out.println(bPixels[i]);
		// System.out.println("********* END Y-Projection **********");
	}
*/
	
	/*
	public int getStartW()
	{
		return startW;
	}

	public int getEndW()
	{
		return endW;
	}

	public int getStartH()
	{
		return startH;
	}

	public int getEndH()
	{
		return endH;
	}
	*/
}
