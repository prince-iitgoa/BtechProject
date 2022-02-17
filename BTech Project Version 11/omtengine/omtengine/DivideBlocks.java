/*STEP 2 __________ Divides the given stave into blocks of notes -> Filtering out empty spaces (level -1 segmentation_____*/

package omtengine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.FileInputStream;
import java.io.IOException;

//import javax.imageio.ImageIO;

//import drawimage.DisplayImage;
import drawimage.DrawRectangle;

	public class DivideBlocks {
		
		private BufferedImage buffimage;
	
		int xPro[];
		int thres;
		int startend[];
		int coun;
		int stavearray[][];
		int sp[];
		int stavno;
		double position;
		int sharp;
		int flat;
		int transpose;
		public DivideBlocks(double position,BufferedImage buffimg,int thres,int coun,int sp[],int stavearray[][],int startend[],int stavno,int sharp,int flat,int transpose)
		{
			this.buffimage = buffimg;
			this.thres=thres;
			this.startend=startend;
			this.stavearray=stavearray;
			this.coun=coun;
			this.stavno=stavno;
			this.position=position;
			this.sp=sp;
			this.sharp=sharp;
			this.flat=flat;
			this.transpose=transpose;
			
		}
		
	
		public void divideb(DrawRectangle drawblk,PitchDuration pd) //division into bars //parameter to draw rectangles #rvk
		{

			//new DisplayImage(buffimage);

		//	BufferedImage img[]= new BufferedImage[20];
			int count=0, height=buffimage.getHeight();
			
			//float midt1=0,midt2=0;
			int width = buffimage.getWidth();
		
				
			 xPro= new int[width];
			//int count1=1;
			
			XProjection xpro= new XProjection(buffimage);
	       
	        xpro.calcXProjection(0, height, 0,width);
	       // System.out.println(height);
	        //xpro.printXProjection();
			xPro=xpro.getXProjection();
			int tx1=0,tx2=0;
			
			for(int i=0;i<width;i++)
			{
				if(xPro[i]>thres) // is something other than stave present here.
				{
					
						tx1=i-2;   //-2 added
						for(;xPro[i]>thres && i<width;i++);
					
						tx2=i+2;  //was -1
						if(tx2!=tx1 && tx2>tx1 )
							count++;
				}
			}
			
		BufferedImage img[]= new BufferedImage[count];
		BufferedImage imgcopy[]= new BufferedImage[count];
		int tx1tx2[][]=new int[count][2];
		//int flag=0;
		count=0;
			
			for(int i=0;i<width;i++)
			{
				if(xPro[i]>thres) // is something other than stave present here.
				{
					//if(flag==0)
					{
						
						//flag=1;
						tx1=i-2;
						for(;xPro[i]>thres && i<width;i++);
					
						tx2=i+2;
						if(tx2!=tx1 && tx2>tx1)
						{
					//	count++;
					/*}
					else
					{
						flag=0;
						tx2=i;
						i--;
						count++; ///////*/
						img[count] = new BufferedImage(tx2-tx1, buffimage.getHeight(),1);  
						imgcopy[count] = new BufferedImage(tx2-tx1, buffimage.getHeight(),1);  
						
						// draws the image chunk  
						Graphics2D gr = img[count].createGraphics();  
						
						tx1tx2[count][0]=tx1;
						tx1tx2[count][1]=tx2;
						
						Graphics2D gr1 = imgcopy[count++].createGraphics();  
						
						gr.drawImage(buffimage, 0, 0, tx2-tx1, buffimage.getHeight(), tx1,0, tx2, buffimage.getHeight(), null);  
						gr1.drawImage(buffimage, 0, 0, tx2-tx1, buffimage.getHeight(), tx1,0, tx2, buffimage.getHeight(), null);
						if(tx2<width-.01*width)// to prevent out of cordinate bounds while drawing #rvk
						if(tx2>=width)
						{
							//System.out.println("."+( tx1+10)+",0,"+ (tx2-10)+","+ buffimage.getHeight());
							drawblk.drawfig( tx1, startend[0]+2, tx2,startend[1]-2, Color.GREEN);
						}
						else
						{
							//System.out.println( tx1+2+",0,"+ tx2+","+ buffimage.getHeight());
							drawblk.drawfig( tx1+2, startend[0]+2, tx2,startend[1]-2, Color.GREEN);
						}// #rvk
						gr.dispose();
						gr1.dispose();
						}
					}
				}
			/*	else
				{
					if(i==width-1 && flag==0)
			
					{
						tx2=width;
						//img[count] = new BufferedImage(tx2-tx1, buffimage.getHeight(),1);
						//Graphics2D gr = img[count++].createGraphics();  
		                
						//gr.drawImage(buffimage, 0, 0, tx2-tx1, buffimage.getHeight(), tx1,0, tx2, buffimage.getHeight(), null);  
						
					//	gr.dispose(); 
					}
				}*/
			}

		// System.out.println(count +"blocks identified");
		// imgcopy=img;
		 
		// int thres=sp[1];
	       //writing mini images into image files    ////@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	       for (int i = 0; i < count; i++)  /// calling next step ie glyph division inside this module itself//-1
	         {  
	       
	        	try {
	        		
	        		 //ImageIO.write(img[i], "png", new File("blocktest" + (coun)+(i+1) + ".png"));
	                 
	               // int temp1=buffImages.getHeight();
	                SegmentNotes seg=new SegmentNotes(img[i],sp[1],sp[0]);
	               int posit[][]= seg.divideseg();
	              
	               DivideNotes divno=new DivideNotes(position,img[i],imgcopy[i],posit,thres,coun,i,stavearray[coun][0],stavearray[coun][1],sp,tx1tx2[i],startend,stavno,sharp,flat,transpose);
	               ///#RVK^
	               
	               divno.dividen(drawblk,pd);
	              position=divno.returnposition();
				
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
	        }			
		
	}
		public double returnposition()
		{
			return position;
		}
	public int[] returnbars()
	{
		return xPro;
	}
/*	public static void main(String args[]) throws IOException
	{
		BufferedImage buffImages;
		 int sp[]=new int[2];
        	File file = new File("img4.png");
            FileInputStream fis= new FileInputStream(file);
            buffImages=ImageIO.read(fis);
            
            
            StaveParameters sparam= new StaveParameters(buffImages);
	     
	       int enH=buffImages.getHeight();
	        int enW=buffImages.getWidth();
	        sparam.calcStaveParameters(0, enH, 0, enW);
    sp=sparam.findStaveParameters();
    StaveDetection sdetec= new StaveDetection(buffImages);
    sdetec.tb=sp[0];
  //  sdetec.tw=sp[1];
 //   sdetec.DetectStave(starH, enH, starW, enW);
            
            
          	int thres=5*sdetec.tb+2;  // 2 set to divide slurs
            System.out.println("thereshld "+thres);
           // int temp1=buffImages.getHeight();
            DivideBlocks divblo=new DivideBlocks(buffImages,thres);
            divblo.divideb();
	}*/
}
			

