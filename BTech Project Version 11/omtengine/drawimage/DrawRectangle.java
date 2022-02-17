package drawimage;


import java.awt.Color;
//import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DrawRectangle
{
	BufferedImage image=new BufferedImage(10,10, BufferedImage.TYPE_INT_RGB);
	public DrawRectangle(String filename)
	{
		File file = new File(filename); //  
		  
	    try {
	    	FileInputStream fis = new FileInputStream(file);
			image = ImageIO.read(fis);
			//System.out.println(image.getType());
			if(image.getType()==0) 							//if the image is not in color space, then change it
			{
				BufferedImage newImg = new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_RGB);
				ColorConvertOp op = new ColorConvertOp(null);
				op.filter(image, newImg);
				this.image=newImg;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	}
	
	public DrawRectangle(BufferedImage image)
	{
		if(image.getType()==0) 							//if the image is not in color space, then change it
		{
			BufferedImage newImg = new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_RGB);
			ColorConvertOp op = new ColorConvertOp(null);
			op.filter(image, newImg);
			this.image=newImg;
		}
	}
	
	
	
	public void drawfig(int x1,int y1, int x2, int y2,Color colo)
	{
		int t;
		
		for(int i=x1;i<=x2;i++)          //horizontal line
		{
			for(int k=0;k<2;k++)        // k gives the thickness
			{
				t=i;
				if(i>=image.getWidth())
					t=image.getWidth()-1;
				if(y1+k>=image.getHeight())
					y1=image.getHeight()-k-5;
				if(y2+k>=image.getHeight())
					y2=(image.getHeight()-5);
			//System.out.println("width "+image.getWidth()+" height "+image.getHeight()+" i "+i+" t "+t+" y1 "+y1+" y2 "+y2 );
			image.setRGB(t, y1+k,colo.getRGB());
			image.setRGB(t, y2+k,colo.getRGB());
			}
			//System.out.print(colo.getRGB());
		}
		
		for(int i=y1;i<=y2;i++)          //vertical line
		{
			for(int k=0;k<2;k++)
			{
				t=i;
				if(i>=image.getHeight())
					t=image.getHeight()-1;
				if(x1+k>=image.getWidth())
					x1=image.getWidth()-k-1;
				if(x2+k>=image.getWidth())
					x2=image.getWidth()-k-1;
				//System.out.println("width "+image.getWidth()+" height "+image.getHeight()+" i "+i+" t "+t+" x1 "+x1+" x2 "+x2 );
				image.setRGB(x1+k, t, colo.getRGB());
				image.setRGB(x2+k, t, colo.getRGB());
			}
		}

	}
	public void writeimg()
	{
		new DisplayImage(image);
		//ImageIO.write(image, "png", new File("drawfig"+ ".png"));		//TO WRITE TO A FILE
	}
	/*public void displaytext(String str,int x,int y)
	{
		//Graphics g = null;
		str="ha ha ha";
	//	g.drawString(str, x, y);
		image.getGraphics();
		
		
	}*/
}
