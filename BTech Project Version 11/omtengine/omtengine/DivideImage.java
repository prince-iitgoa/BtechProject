/*STEP 1_____ Divides a given notation in different staves_____*/
package omtengine;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
//import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import drawimage.*;

public class DivideImage {
	
	private BufferedImage buffimage;
	int a,b,c,d,mid;
	int stavearray[][];
	int startend[][];
	
	public DivideImage(BufferedImage buffimg, int stavearray[][])
	{
		this.stavearray= new int[stavearray.length][2];
		this.buffimage = buffimg;
		this.stavearray = stavearray;
	}
	
	public BufferedImage[] dodivide(DrawRectangle drawrec) //number of staves //parameter to draw image #rvk
	{
		BufferedImage img[]= new BufferedImage[stavearray.length];
		int startend[][]=new int[stavearray.length][2]; // start end cordinated of calculated blocks
		this.startend=startend;
		int count=0, height;
		//DivideBars Divbar;
		float midt1=0,midt2=0;
		
		int width = buffimage.getWidth();
		//DrawRectangle drawrec = new DrawRectangle("score1.png");
		for(int i=0;i<stavearray.length;i++)
		{
			
			//for(int j=0;j<1;j++)
			{
				height = stavearray[i][1]-stavearray[i][0];
				//Initialize the image array with image chunks  
				if(i==0 && i==stavearray.length-1)
				{
					if(stavearray[i][0]<0.75*height)
						midt1=0;
					else
							midt1=(float) (0.75*height);
					if(stavearray[i][1]+0.75*height>buffimage.getHeight())
						midt2=buffimage.getHeight()-stavearray[i][1];
					else
						midt2=(float)0.75*height;	
				}
				else if(i==0)
				{
					if(stavearray[i][0]<0.75*height)
						midt1=0;
					else
							midt1=(float)0.75*height;
					midt2=(float)0.75*height;
				}
				else if(i==stavearray.length-1)
				{
					if(stavearray[i][1]+0.75*height>buffimage.getHeight())
						midt2=buffimage.getHeight()-stavearray[i][1];
					else
						midt2=(float)0.75*height;
					midt1=(float)0.75*height;
				}
				else
				{
					midt1=(float) (0.75*height);
					midt2=(float) (0.75*height);
				}
           //     midt=stavearray[i][1]-stavearray[i+1][0];

				
                img[count] = new BufferedImage(buffimage.getWidth(), height+(int)midt1+(int)midt2,1);  
               
                startend[count][0]=stavearray[i][0]-(int)midt1;
                startend[count][1]=stavearray[i][1]+(int)midt2;
                // draws the image chunk  
                Graphics2D gr = img[count++].createGraphics();  
                
                gr.drawImage(buffimage, 0, 0, width, height+(int)midt1+(int)midt2, 0,stavearray[i][0]-(int)midt1, width, stavearray[i][1]+(int)midt2, null);
               
                drawrec.drawfig((int)(width*0.01),stavearray[i][0]-(int)midt1,(int)(width-width*0.01), stavearray[i][1]+(int)midt1, Color.MAGENTA);
    			//^#rvk
                
             //   Divbar=new DivideBars(img[i],(int)midt1,(int)midt2);
              //  Divbar.divideb();
                
                gr.dispose(); 
			//	System.out.println("i 1 "+stavearray[i][1]+'\n');
			//	System.out.println("i 0 "+stavearray[i][0]+'\n');
			//	System.out.println(height); 
			}
			
		}
		//drawrec.writeimg(); 
  
        //writing mini images into image files    ////@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        for (int i = 0; i < img.length; i++) {  
            try {
				ImageIO.write(img[i], "png", new File("imgtest" + i + ".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
        }		
		return img;
			
	}
	int[][] returnstartend()
	{
		return startend;
	}
	
	//use width of stave in this file
	
	/*public static void main(String args[])
	{
		//int a=0,b=100,c=150,d=200,nos;
		  int starH,enH,starW,enW;
		    int sp[]=new int[2];
		 //   int arr[][];
		 
		//int stavearray[][]=new int[arr.length][2];
		//int imgw,imgh;
		BufferedImage buffImage;
		try{
        	File file = new File("scale.png");
            FileInputStream fis= new FileInputStream(file);
            buffImage=ImageIO.read(fis);
            
            
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
    int arr[][]=new int[sdetec.stavelineno][2];
    arr=sdetec.DetectStave(starH, enH, starW, enW);
            
            DivideImage imgs= new DivideImage(buffImage, arr);
            imgs.dodivide();
		}
		catch(IOException e){
			   e.printStackTrace();
        }
	}*/

}

