/*__________ Divides the given stave into blocks of notes --> Filtering out empty spaces (level -1 segmentation_____*/

package omtengine;

	//import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
//import java.io.FileInputStream;
import java.io.IOException;
import java.awt.Color;

import javax.imageio.ImageIO;

	public class SegmentNotes {
		
		private BufferedImage buffimage;
		//private	BufferedImage copy;
		int xPro[];
		int locthresw=0;
		 int locthresb=0;
		 int pos[][];
		
		public SegmentNotes(BufferedImage buffimg,int thresw,int thresb)
		{
			this.buffimage = buffimg;
			this.locthresw=thresw;
			this.locthresb=thresb;
			//this.copy=buffimg;
			
		}
		
	
		public int[][] divideseg() throws IOException//division into bars
		{
		//	BufferedImage img[]= new BufferedImage[20];
			/*File file = new File("block13"+ ".png");
            FileInputStream fis= new FileInputStream(file);*/
           // buffimage=ImageIO.read(fis);
			//float midt1=0,midt2=0;
			int countt=0;
			int width = buffimage.getWidth();
		//    int sp[]=new int[2];
			int count=0;// height=buffimage.getHeight();	
			 xPro= new int[width];
			//int count1=1;
		//	XProjection xpro= new XProjection(buffimage);
	       
	  //      xpro.calcXProjection(0, height, 0,width);
	       // System.out.println(height);
	    //    xpro.printXProjection();
		//	xPro=xpro.getXProjection();
		//	 BufferedImage temp;
			
			int endW=buffimage.getWidth();
			int endH=buffimage.getHeight();
			int countb=0,countw=0;
			int k=0;//l=0;
			
			//int flag1;
			int thre=locthresw-2; //threw really
			
			
			
			
			///////////////////////////
			
			
			
			//filling empty noteheads  ---=------------------------------------>include this code from here.....
			
			 for (int i =0; i < endH; i += 1)
				{
				// System.out.println("");
					for (int j = 0; j < endW; j += 1)
					{
						int colo = 0;
						
							colo = buffimage.getRGB(j, i);
									
									if (colo != -1) //if black pixel
									{
										//countb++;
									//	System.out.print(countw+", ");
										if(countw<locthresw/*-2*locthresb*/)  //working perfectly :) // detectin empty note heads
										{
											for(k=j;k>=j-countw && k>0;k--)
											{
														buffimage.setRGB(k, i,Color.BLACK.getRGB()); // set to black
											}
										}
											
										countw=0;
									}
									else
									{
										countw++;
										/*if(countb<thre || countb>2*thre)
										{
											for(k=j-1;k>=j-countb && k>0;k--)//j-1;
											{
												buffimage.setRGB(i,k,-1); // set to white
											}
											
											//b[countb]+=1;
										}
										countb=0;*/
									}
							
					}
				}
			
			
			
			
			
			
			////////////////////////
			
			
			
			
			
			
			
			
			 for (int i =0; i < endW; i += 1)
				{
					for (int j = 0; j < endH; j += 1)
					{
						int colo = 0;
						
							colo = buffimage.getRGB(i, j);
									
									if (colo != -1) //if black pixel
									{
										countb++;
									/*	if(countw>thre && countw<locthresw-3)  //maynot work as expected // detectin empty note heads
										{
											for(k=0;k>j-countw;k--)
											{
														buffimage.setRGB(i, j, 0);; // set to black
											}
										}*/
											
										//countw=0;
									}
									else
									{
										//countw++;
										if(countb<thre-3*locthresb || countb>4*thre+3*locthresb)  // 2.locthresb     2*thre
										{
											for(k=j-1;k>=j-countb && k>0;k--)//j-1;
											{
												buffimage.setRGB(i,k,-1); // set to white
											}
											
											//b[countb]+=1;
										}
										countb=0;
									}
							
					}
				}
			 
			 
			 //now the image contains lines whose height is similar to a note thickness
			 countb=0;countw=0; 
			 
			 
			 for (int i =0; i < endH; i += 1)
				{
					for (int j = 0; j < endW; j += 1)
					{
						int colo = 0;
						
							colo = buffimage.getRGB(j, i);
									
									if (colo != -1) //if black pixel
									{
										countb++;
										/*if(countw>thre && countw<locthresw-3)  //maynot work as expected // detectin empty note heads
										{
											for(k=0;k>j-countw;k--)
											{
														buffimage.setRGB(); // set to black
											}
										}
											
										countw=0;*/
									}
									else
									{
										//countw++;
										if(countb<thre-3*locthresb || countb>2*thre)   //2*locthresb  //2*locthresb
										{
										
											for(k=j-1;k>=j-countb && k>0;k--)
											{
												//System.out.println("hello");
												buffimage.setRGB(k,i,-1); // set to white
											}
											
											//b[countb]+=1;
										}
										countb=0;
									}
							
					}
				}
			 
		 ImageIO.write(buffimage, "png", new File("nostave" +(countt++)+ ".png"));
			 //To break into individual notes			 
		 int xProjection[]=new int[endW];
			 for (int i = 0; i < endW; i += 1)
				{
					for (int j = 0; j < endH; j += 1)
					{
						int color = 0;
						
							color = buffimage.getRGB(i, j);
						
						if (color != -1) //if black pixel
						{
							xProjection[i] += 1;
						}
					}
				}
			 System.out.println(endW);
			 for(int i=0;i<endW;i++)
				 System.out.print(xProjection[i]+",");
			 int segthres=locthresw;
			int flag=0;
			int countnote=0;
			
			//
			 for(int i=0;i<endW;i++)
			 {
				 if(xProjection[i]>=locthresw)//-2*locthresb)   //////////
				 {
					 if(flag==1)
					 {
						 flag=0;
						 countnote++;
					 	
				 	}
				 }
				else
				{
						// temp=i;
						 flag=1;
				
					/* img[count] = new BufferedImage(tx2-tx1, buffimage.getHeight(),1);  
						
						// draws the image chunk  
						Graphics2D gr = img[count++].createGraphics();  
	                
						gr.drawImage(buffimage, 0, 0, tx2-tx1, buffimage.getHeight(), tx1,0, tx2, buffimage.getHeight(), null);  
						
						gr.dispose();*/
			 	}
			 }
			 flag=0;count=0;
			 int tx1=0,tx2=0;
				int temp=0;//j;
				int pos[][]=new int[countnote][2];
		//	 BufferedImage img[]= new BufferedImage[countnote];
			 for(int i=0;i<endW;i++)
			 {
				 if(xProjection[i]>locthresw)
				 {
				/*	 count++;
					 temp=i;
				 }
				 else
				 {*/
					 if(flag==1)
					 {
						 flag=0;
						// countnote++;
						 
						 if(temp-segthres>0)
							 tx1=temp-segthres;
						 else
							 tx1=0;
						 
						 for(;xProjection[i]>locthresw && i<xProjection.length-2;i++);  // && condition new added sndy
						 {
							 if(i+segthres>endW)
								 tx2=endW;
							 else
								 tx2=i+segthres;
							
						 }
						 if(count<countnote)
						{
							 pos[count][0]=tx1;
						
						 	pos[count][1]=tx2;
						 //	System.out.println("\n"+pos[count][0]+"  "+pos[count][1]);
						 	count++;
						} 	
					 }
					  	
						 	/*
							img[count] = new BufferedImage(tx2-tx1, copy.getHeight(),1);  
								
								// draws the image chunk  
								Graphics2D gr = img[count++].createGraphics();  
			                
								gr.drawImage(copy, 0, 0, tx2-tx1, copy.getHeight(), tx1,0, tx2, copy.getHeight(), null);  
								
								gr.dispose();*/
				 		
				 }
				else
					 {
						 temp=i;
						 flag=1;
					 }
			 }
			 System.out.println("\n"+countnote +"Glyphs identified");
	
			 return(pos);
		       //writing mini images into image files  
		   /*    for (int i = 0; i < count; i++)
		         {  
		       
		        	try {
						ImageIO.write(img[i], "png", new File("glyph" + (i+1) + ".png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
		        }		
			 */
			 //now image contains only symbols similar to noteheads
			 //take x-projection to divide into segmnts
			
			 //identifynotes only if they match some symbol. will be easier to compute rather than handling an array
			 
		
			 /*int tx1=0,tx2=0;
			int i,min=0;
		/*	for(i=0;i<width;i++)
			{
				if(min>xPro[i])
					min=xPro[i];
			}
			locthres=locthres+min; //
			int temp=0;
			for(i=0;i<width;i++)
			{
				if(xPro[i]>locthres) // is something other than stave present here.
				{
					
						tx1=i;
						temp=xPro[i];
						for(;xPro[i]>locthres && i<width;i++)
						{
							
							if(xPro[i]>temp+locthres || xPro[i]<temp-locthres)
							{
								//tx2=i-1;
								break;
							}
						}
						i--;
						tx2=i;
						if(tx2!=tx1)
							count++;
				}
			}
			
		BufferedImage img[]= new BufferedImage[count];
		int flag=0;
		
		count=0;
			
			for(i=0;i<width;i++)
			{
				if(xPro[i]>locthres) // is something other than stave present here.
				{
					//if(flag==0)
					{
						
						//flag=1;
						tx1=i;
						temp=xPro[i];
						for(;xPro[i]>locthres && i<width;i++)
						{
							if(xPro[i]>temp+locthres || xPro[i]<temp-locthres)
							{
								break;
							}
						}
						i--;
						tx2=i;
						if(tx2!=tx1)
						{
					//	count++;
					/*}
					else
					{
						flag=0;
						tx2=i;
						i--;
						count++; ///////*/
					/*	img[count] = new BufferedImage(tx2-tx1, buffimage.getHeight(),1);  
					
						// draws the image chunk  
						Graphics2D gr = img[count++].createGraphics();  
	                
						gr.drawImage(buffimage, 0, 0, tx2-tx1, buffimage.getHeight(), tx1,0, tx2, buffimage.getHeight(), null);  
						
						gr.dispose();
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
			//}
		
	//	 System.out.println(count +"Note Segments identified");
	 
	       //writing mini images into image files  
	  /*      for (i = 0; i < count; i++)
	         {  
	       
	        	try {
					ImageIO.write(img[i], "png", new File("segment" + (i+1) + ".png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
	        }*/			
		
	}
	/*public int[][] returnpos()
	{
		/*for(int i=0;i<pos.length;i++)
		{
			System.out.println(pos[i][0]+"  "+pos[i][1]);
		}*/
	/*	return this.pos;
	}
	public int[] returnbars()
	{
		return xPro;
	}*/
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
            
            
      /*      StaveParameters sparam= new StaveParameters(buffImages);
	     
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
            SegmentNotes seg=new SegmentNotes(buffImages,thres,sp[0]);
            seg.divideseg();
	}*/
}
			

