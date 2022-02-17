/*__________ Divides the given stave into blocks of notes -> Filtering out empty spaces (level -1 segmentation_____*/

package omtengine;

	import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;

import javax.imageio.ImageIO;

//import drawimage.DisplayImage;
import drawimage.DrawRectangle;

	public class DivideNotes {
		
		private BufferedImage buffimage;
	
		int xPro[];
		int thres;
		int coun;
		int c;
		int posi[][];
		int sp[];
		int startend[];
		int stavestart;
		int staveend;
		int startstv;
		int endstv;
		int stavno;
		int tx1tx2[];
		int sharp;
		int flat;
		double position;
		int transpose;
		private BufferedImage org;
		
		public DivideNotes(double position,BufferedImage buffimg,BufferedImage org,int pos[][],int thres,int coun,int c,int stavestart,int staveend,int sp[],int tx1tx2[],int startend[],int stavno,int sharp,int flat,int transpose)
		{
			this.buffimage = buffimg;
			this.posi=pos;
			this.position=position;
			this.startend=startend;
			this.stavno=stavno;
			this.sp=sp;
			this.org=org;
			//this.stavestart=0;
			this.startstv=stavestart;
			//this.staveend=0;
			this.tx1tx2=tx1tx2;
			this.endstv=staveend;
			this.thres=thres;
			this.c=c;
			this.coun=coun;
			this.sharp=sharp;
			this.flat=flat;
			this.transpose=transpose;
			
		}
		
	
		public void dividen(DrawRectangle drawblk,PitchDuration pd) 
		{
			
			int count=0;//height=buffimage.getHeight();
			
			//float midt1=0,midt2=0;
			//int width = buffimage.getWidth();
		
				
			 //xPro= new int[width];
			//int count1=1;
			//XProjection xpro= new XProjection(buffimage);
	       
	      //  xpro.calcXProjection(0, height, 0,width);
	       // System.out.println(height);
	       // xpro.printXProjection();
		//	xPro=xpro.getXProjection();
			int tx1=0,tx2=1;
			int width=posi.length;
			System.out.println("width"+width);
			BufferedImage img[]= new BufferedImage[width];
			BufferedImage imgorg[]= new BufferedImage[width];
			count=0;
			for(int i=0;i<width;i++)
			{
				tx1=posi[i][0];
				tx2=posi[i][1]+1;
						img[count] = new BufferedImage(tx2-tx1, buffimage.getHeight(),1);  
						imgorg[count] = new BufferedImage(tx2-tx1, org.getHeight(),1);
					//	if(i<width-1)
							drawblk.drawfig(tx1tx2[0]+tx1+1, startend[0]+4,tx1tx2[0]+tx2-2,startend[1]-4, Color.BLUE);
						// draws the image chunk  
						
						Graphics2D gr = img[count].createGraphics();  
						Graphics2D gr1 = imgorg[count++].createGraphics();
						gr.drawImage(buffimage, 0, 0, tx2-tx1, buffimage.getHeight(), tx1,0, tx2, buffimage.getHeight(), null);  
						gr1.drawImage(org, 0, 0, tx2-tx1, org.getHeight(), tx1,0, tx2, org.getHeight(), null);  
						gr.dispose();
			}
				
			
		
	//	 System.out.println(width +"Glyphs identified");
	 
			stavestart=buffimage.getHeight()*3/10;
	       //writing mini images into image files  
	        for (int i = 0; i < width; i++)
	         {  
	       
	        	try {
					//ImageIO.write(imgorg[i], "png", new File("glyph" +coun+c+(i+1) + ".png"));
					//ImageIO.write(img[i], "png", new File("glyph00" +coun+c+(i+1) + ".png"));
					
					YProjection ypro=new YProjection(img[i]);
					ypro.calcYProjection(0, img[i].getHeight(), 0, img[i].getWidth());
//					ypro.printYProjection();
					int Ypro[]=ypro.getYProjection();
			
				  	
				  	Notedetection notedetec=new Notedetection(position,coun,img[i],imgorg[i],Ypro,sp,stavestart,tx1tx2[0]+posi[i][0]+1, startend[0]+4,stavno,sharp,flat,transpose);///coupling not ok!!!
				  	notedetec.Recognizenotes(drawblk,pd);
					position=notedetec.returnposition();
					
					
	        		
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
	        }			
	      
		}
		  public double returnposition()
	        {
	        	return position;
	        }
		
	
	/*public static void main(String args[]) throws IOException
	{
		BufferedImage buffImages,copy;
		int sp[]=new int[2];
		
        	File file = new File("block13.png");
            FileInputStream fis= new FileInputStream(file);
            buffImages=ImageIO.read(fis);
            
       /* 	File file1 = new File("block13.png");
            FileInputStream fis1= new FileInputStream(file);
            copy=ImageIO.read(fis1);*/
            
            
      /*     StaveParameters sparam= new StaveParameters(buffImages);
	     
	       int enH=buffImages.getHeight();
	        int enW=buffImages.getWidth();
	        sparam.calcStaveParameters(0, enH, 0, enW);
    sp=sparam.findStaveParameters();
    StaveDetection sdetec= new StaveDetection(buffImages);
 //   sdetec.tb=sp[0];
 //   sdetec.tw=sp[1];
   // sdetec.DetectStave(0, enH, 0, enW);
            
            
          	int thres=sp[1];
          	System.out.println(thres);
           
           // int temp1=buffImages.getHeight();
            SegmentNotes seg=new SegmentNotes(sp[1],sp[0]);
           int posit[][]= seg.divideseg();
          
           DivideNotes divno=new DivideNotes(buffImages,posit,thres,sp);
            divno.dividen();
	}*/
}
			

