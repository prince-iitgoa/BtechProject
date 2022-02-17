package omtengine;

import java.awt.image.BufferedImage;
//import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileWriter;
import java.io.IOException;
//import java.io.InputStream;

import javapy.CreateMid;

import javax.imageio.ImageIO;

import drawimage.DrawRectangle;
import drawimage.DisplayImage;

public class OpenMusicTranscriptor {


	public OpenMusicTranscriptor(String filename,String vol,String shp,String flt,String trp,String tmp,String ofl,int bw,int typeflag)//// static void main(String args[])
	{
		//int a=0,b=100,c=150,d=200,nos;
		  int starH,enH,starW,enW;
		    int sp[]=new int[2];
		 //   int arr[][];
		    //int typeflag=1;  /// send value to this variable ok.. ! Good luck in combining the modules.! :)   //////////////////////////////////
		    double startposition=0.0,endposition=0.0;
		    int volume=(int)Double.parseDouble(vol) ;//Integer.parseInt(vol);
		    int sharp=(int)Double.parseDouble(shp);
		    int flat=(int)Double.parseDouble(flt);
		    int transpose=(int)Double.parseDouble(trp);
		    int tempo=(int)Double.parseDouble(tmp);
		    String opfile=ofl;
		    double position=0.0;
		    //System.out.println(volume+" "+sharp+" "+flat+" "+transpose+" "+tempo+" "+opfile);
		    
		    
		 
		//int stavearray[][]=new int[arr.length][2];
		//int imgw,imgh;
		BufferedImage buffImage;
		//String filename="scale.png";  //change filename here #rvk
		
		
		try{
        	File file = new File(filename);
            FileInputStream fis= new FileInputStream(file);
            buffImage=ImageIO.read(fis);
            
            System.out.println(bw);
            DoBlackandWhite dobw=new DoBlackandWhite(buffImage);
            if(bw==1)
    		{
            	System.out.println("BW");
            	buffImage=dobw.doBW();
    		}
            
            new DisplayImage(buffImage);
            
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
    arr=sdetec.DetectStave(starH, enH, starW, enW,sp[0]);
    
    PitchDuration pd=new PitchDuration();
    
    DrawRectangle drawblk=new DrawRectangle(filename); //Declare an image to draw rectangles #rvk
    
    BufferedImage img[]= new BufferedImage[arr.length];
    DivideImage imgs= new DivideImage(buffImage, arr);  //arr-> Stave array
    
    img=imgs.dodivide(drawblk);                         //parameter to draw rectangles #rvk

    int startend[][]=new int[arr.length][2];
    startend=imgs.returnstartend();
    
   	int thres=5*sdetec.tb+2;  // 2 set to divide slurs
     System.out.println("thereshld "+thres);
    // int temp1=buffImages.getHeight();
     int k=0;
     int tempflag=0;
	 
     for(k=0;k<arr.length;k++)
     {
    	 if(tempflag==0)
    	 {
    		 startposition=position;
    		 DivideBlocks divblo=new DivideBlocks(position,img[k],thres,k,sp,arr,startend[k],k+1,sharp,flat,transpose);
    	 
    		 divblo.divideb(drawblk,pd);                     //parameter to draw rectangles #rvk
    		 position=divblo.returnposition();
    		 endposition=position;
    		 if(typeflag==1)
    		 {
    			 position=startposition;
    			 tempflag=1;
    		 }
    	 }
    	 else
    	 {
    		 DivideBlocks divblo=new DivideBlocks(position,img[k],thres,k,sp,arr,startend[k],k+1,sharp,flat,transpose);
        	 
    		 divblo.divideb(drawblk,pd);                     //parameter to draw rectangles #rvk
    		 position=endposition;
    		 tempflag=0;
    		 
    	 }
    	 
     }
  //   DisplayImage test=new Displayimage()
     
     
     // the notes identified and its duration    #rvk
     int noofnotes=pd.sizeoflist();
     double note[][]=new double[noofnotes][3];
     
     for(int i=0;i<noofnotes;i++)
     {
    	 note[i]=pd.valu(i);    	 
     }
     
     new CreateMid(noofnotes,note,opfile,volume,tempo);
     drawblk.writeimg(); //#rvk
}
		catch(IOException e){
			   e.printStackTrace();
        }
	}
	

}
