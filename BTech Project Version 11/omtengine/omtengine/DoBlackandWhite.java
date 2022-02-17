/*_____ To convert an image into black and white _____*/
package omtengine;

import java.awt.Color;
import java.awt.image.BufferedImage;

//import javax.imageio.ImageIO;  

import drawimage.DisplayImage;

//import java.io.*;


  
public class DoBlackandWhite
{
	/*public static void main(String[] args) throws IOException 
	{  	  
	  
	File file = new File("img3.png"); // I have bear.jpg in my working directory  
    FileInputStream fis = new FileInputStream(file);  
    BufferedImage buffImage = ImageIO.read(fis); //reading the image file 
*/    
	BufferedImage buffImage;
    
	public DoBlackandWhite(BufferedImage buffImage)
	{

    	this.buffImage = buffImage;
	}
	
	public BufferedImage doBW()
	{
		int THRES=450;
		for (int i=0; i<buffImage.getHeight(); i+=1)
			for (int j=0; j<buffImage.getWidth(); j+=1)
			{
				//new code jithin
				int rgba = buffImage.getRGB(j, i);
				Color col=new Color(rgba,true);
				
				
				if(col.getRed() + col.getBlue() + col.getGreen() > THRES)
					buffImage.setRGB(j, i, Color.WHITE.getRGB());
				//	col=new Color(0,0,0);
				else
				//	col=new Color(255,255,255);
					buffImage.setRGB(j, i, Color.BLACK.getRGB());
				/*
				
				int pix = buffImage.getRGB(j, i);
				//if it's not a black or white pixel, set it to white
				if (pix != Color.WHITE.getRGB() && pix != Color.BLACK.getRGB())
					buffImage.setRGB(j, i, Color.WHITE.getRGB());*/
			}
		
		
		for (int i=1; i<buffImage.getWidth()-1; i+=1)
			for (int j=1; j<buffImage.getHeight()-1; j+=1)
			{
				
				if (buffImage.getRGB(i, j) == 0)
				{
					int p1 = buffImage.getRGB(i-1, j-1);
					int p2 = buffImage.getRGB(i-1, j);
					int p3 = buffImage.getRGB(i-1, j+1);
					int p4 = buffImage.getRGB(i, j-1);
					int p5 = buffImage.getRGB(i, j+1);
					int p6 = buffImage.getRGB(i-1, j-1);
					int p7 = buffImage.getRGB(i-1, j);
					int p8 = buffImage.getRGB(i-1, j+1);
				
					if (p1==-1 && p2==-1 && p3==-1 && p4==-1 && p5==-1 && p6==-1 && p7==-1 && p8==-1)
					{
						buffImage.setRGB(i, j, -1);
					}
				}
			}
		//ImageIO.write(buffImage,"png", new File("blacknwhite" + ".png"));
		new DisplayImage(buffImage);
		return buffImage;
	}
}

	
