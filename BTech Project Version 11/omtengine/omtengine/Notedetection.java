package omtengine;
																							//REWRITE THE CODE
import java.awt.image.BufferedImage;
//values to be received :start pixel of the first stave line and distance between stave lines... find all the y-cordinates of the stave lines
//+ stave line thickness.
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;


//import javax.imageio.ImageIO;


import recogniseglyph.CompareImage;
import drawimage.DrawRectangle;



public class Notedetection 
{
	
	BufferedImage buffImag;
	int threshold;
	int YProjection[];
	int thresblack;
	int staren[][]=new int[1][2];
	int startstave;
	int xstart;
	double position;
	BufferedImage org;
	int ystart;
	int stavno;
	int sharp=0;				// values from Gui-- > just get those values and display them..k..
	int flat=0;
	int transpose;
	int coun;
	int countt;
	
public Notedetection(double position,int coun,BufferedImage buffImages,BufferedImage org,int yp[],int thres[],int startstave,int xstart, int ystart,int stavno,int sharp,int flat,int transpose)
{
	this.coun=coun;
	this.buffImag=buffImages;
	this.YProjection=yp;
	this.startstave=startstave;
	this.threshold=thres[1];   //new sunday
	this.org=org;
	this.thresblack=thres[0];
	this.xstart=xstart;
	this.stavno=stavno; //staveno+1
	this.ystart=ystart;
	this.sharp=sharp;
	this.flat=flat;
	this.position=position;
	this.transpose=transpose;
	
//	this.staren=staren;
}
public void Recognizenotes(DrawRectangle drawblk,PitchDuration pd) throws Exception
{
	int i,count=0;
	int start[] =new int[20],end[]=new int[20];
	int height;
	int midilist[]=new int[10];
	//int pos[]=new int[20];
	int staveflag=1;         
	int no=0;
	int temp,h;
	//staveflag=stavno%2;      //write code to include tis line if we need to detect both hand notation
	int pos[] =new int[20];
	int midi = 0;
	
	for(i=0;i<buffImag.getHeight();i++)
	{
		if(YProjection[i]>threshold-2*thresblack)
		{
			if(count==0)
			{
				start[no]=i;
			}
			count++;
		}
		else
		{
			if(count>=threshold-2*thresblack)
			{
				height=i-start[no];
				temp=height/(threshold-thresblack);  //+thresblck
			//	if(temp<2)
				{
				end[no]=start[no]+threshold;//end=i;
				pos[no]=(start[no]+end[no])/2;
				}
				//System.out.println("pos "+pos[no]);
			
				if(temp>=2)
				{
				//	pos[no]=start+height/2;
					// /4
					for(h=1;h<temp;h++)	
					{	
						start[++no]=end[no-1]+1;
						end[no]=start[no]+threshold;
						pos[no]=(start[no]+end[no])/2;
					//	System.out.println("pos "+pos[no]);
					//	System.out.println("Position "+(h+1)+"    "+start+" "+end);
					//	start+=threshold+1;
						
					//	no++;
					}
					//if(temp<2)
						//no++;
				//	System.out.println("number "+(no));
					
				}
				
					no++;
		//		else if(height)
				
			}
			count=0;
		}
	}
	System.out.println((no)+" Notes Detected ");
	int beg=0,nocop=no;//tempcount=0;
	/*for(i=0;i<buffImag.getHeight();i++)		//WORKIN logic
	{
		if(YProjection[i]==buffImag.getWidth())
		{
			tempcount++;
		}
		else
		{
			if(tempcount!=0)
			{
				if(tempcount>=thresblack-3 && tempcount<=thresblack+2)
					{
					beg=i;
					break;
					}
				else
					tempcount=0;
			}
		}
	}*/
	
	beg=startstave;			
	
	/*	int notepos=(beg-pos)*2/threshold;
	if (notepos % 7 == -3)
	System.out.print("B2 ");
	else if (notepos % 7 == -2)
	System.out.print("C3 ");
	else if (notepos % 7 == -1)
	System.out.print("D3 ");
	else if (notepos % 7 == 0)
		System.out.print("E3 ");
		else if (notepos % 7 == 1)
		System.out.print("F3 ");
		else if (notepos % 7 == 2)
		System.out.print("G3 ");
		else if (notepos % 7 == 3)
		System.out.print("A3 ");
		else if (notepos % 7 == 4)
			System.out.print("B3 ");
			else if (notepos % 7 == 5)
			System.out.print("C4 ");
			else if (notepos % 7 == 6)
			System.out.print("D4 ");
			else if (notepos % 7 == 7)
				System.out.print("E4 ");
			else 
				System.out.println("cannot determine");
	*/
	
//	System.out.println("height "+buffImag.getHeight());
	
//	System.out.println("Start "+beg);

	////////////				//uses top point of each line in a stave
//	System.out.println("line distance "+threshold);
	int dist=thresblack+threshold;			

	
	
	//Refine CODE AND LOGIC
	int s1=beg+thresblack/2;
//	System.out.println("S1 "+s1);
	int s2=s1+dist;
//	System.out.println("S2 "+s2);
	int s3=s2+dist;
//	System.out.println("S3 "+s3);
	int s4=s3+dist;
//	System.out.println("S4 "+s4);
	int s5=s4+dist;
//	System.out.println("S5 "+s5);
	int s6=s5+dist;
//	System.out.println("S6 "+s6);
	int s7=s6+dist;
	int s8=s7+dist;
	
	int s20=s1-dist;
	int s19=s20-dist;
	int s18=s19-dist;
	int s17=s18-dist;

	
	
	
	int d0=(s20+s1)/2;//-((thresblack/2)+(threshold/2));
	//System.out.println("\nD0 "+d0);
	int d1=(s1+s2)/2;
//	System.out.println("D1 "+d1);
	int d2=(s2+s3)/2;
//	System.out.println("D2 "+d2);
	int d3=(s3+s4)/2;
//	System.out.println("D3 "+d3);
	int d4=(s4+s5)/2;
	//System.out.println("D4 "+d4);
	int d5=(s5+s6)/2;
	//System.out.println("D5 "+d5);
	int d6=d5+(s6+s7)/2;//
//	System.out.println("D6 "+d6);
	int d7=d6+(s7+s8)/2;
	int d8=d7+((thresblack/2)+(threshold/2));
	
	int d19=(s19+s20)/2;
	int d18=(s18+s19)/2;
	int d17=(s17+s18)/2;
	
	int lim=threshold/3;   // /4 wrkin fin
	
	for(i=0;i<nocop;i++)
	{
	no=i;  // changed         // while recgnizing chords
	
	if(staveflag==1)
	{///////////////
		
	
	/*if(pos[no]>s17-lim && pos[no]<s17+lim)
	{
		midi=79;
		System.out.println("G5");
	}
	else if(pos[no]>d17-lim && pos[no]<d17+lim)
	{
		midi=77;
		System.out.println("F5");
	}
	else if(pos[no]>s18-lim && pos[no]<s18+lim)
	{
		midi=76;
		System.out.println("E5");
	}*/
	if(pos[no]>d18-lim && pos[no]<d18+lim)
	{
		midi=74;
		System.out.println("D5");
	}
	else if(pos[no]>s19-lim && pos[no]<s19+lim)
	{
		midi=72;
		System.out.println("C5");
	}
		
	else if(pos[no]>d19-lim && pos[no]<d19+lim)
	{
		midi=71;
		System.out.println("B4");
	}
	else if(pos[no]>s20-lim && pos[no]<s20+lim)
	{
		midi=69;
		System.out.println("A4");
	}
	else if(pos[no]>d0-lim && pos[no]<d0+lim)
	{
		midi=67;
		System.out.println("G4");
	}
	else if(pos[no]>s1-lim && pos[no]<s1+lim)
	{
		midi=65;
		System.out.println("F4");
	}
	else if(pos[no]>d1-lim && pos[no]<d1+lim)
	{
		midi=64;
		System.out.println("E4");
	}
	
	else if(pos[no]>s2-lim && pos[no]<s2+lim)
	{
		midi=62;
		System.out.println("D4");
	}
	else if(pos[no]>d2-lim && pos[no]<d2+lim)
	{
		midi=60;
		System.out.println("C4");
	//	drawblk.displaytext("C4",xstart,ystart);
	}
	
	else if(pos[no]>s3-lim && pos[no]<s3+lim)
	{
		midi=59;
		System.out.println("B3");
	}
	else if(pos[no]>d3-lim && pos[no]<d3+lim)
	{
		midi=57;
		System.out.println("A3");
	}
	else if(pos[no]>=s4-lim && pos[no]<=s4+lim)
	{
		midi=55;
		System.out.println("G3");
	}
	else if(pos[no]>d4-lim && pos[no]<d4+lim)
	{
		midi=53;
		System.out.println("F3");
	}
	else if(pos[no]>s5-lim && pos[no]<s5+lim)
	{
		midi=52;
		System.out.println("E3");
	}
	else if(pos[no]>d5-lim && pos[no]<d5+lim)
	{
		midi=50;
		System.out.println("D3");
	}
	else if(pos[no]>s6-lim && pos[no]<s6+lim)
	{
		midi=48;
		System.out.println("C3");
	}
	else if(pos[no]>=d6-lim && pos[no]<d6+lim)
	{
		midi=47;
		System.out.println("B2");
	}
	else if(pos[no]>s7-lim && pos[no]<s7+lim)
	{
		midi=45;
		System.out.println("A2");
	}
	else if(pos[no]>d7-lim && pos[no]<d7+lim)
	{
		midi=43;
		System.out.println("G2");
	}
	else if(pos[no]>s8-lim && pos[no]<s8+lim)
	{
		midi=41;
		System.out.println("F2");
	}
	else if(pos[no]>d8-lim && pos[no]<d8+lim)
	{
		midi=40;
		System.out.println("E2");
	}
	else
		System.out.println(" Code yet to be written ");
	
	}
	else
	{///////////////////
		
		
		if(pos[no]>s17-lim && pos[no]<s17+lim)
		{
			midi=59;
			System.out.println("B3");
		}
		else if(pos[no]>d17-lim && pos[no]<d17+lim)
		{
			midi=57;
			System.out.println("A3");
		}
		else if(pos[no]>s18-lim && pos[no]<s18+lim)
		{
			midi=55;
			System.out.println("G3");
		}
		else if(pos[no]>d18-lim && pos[no]<d18+lim)
		{
			midi=53;
			System.out.println("F3");
		}
		else if(pos[no]>s19-lim && pos[no]<s19+lim)
		{
			midi=52;
			System.out.println("E3");
		}
			
		else if(pos[no]>d19-lim && pos[no]<d19+lim)
		{
			midi=50;
			System.out.println("D3");
		}
		else if(pos[no]>s20-lim && pos[no]<s20+lim)
		{
			midi=48;
			System.out.println("C3");
		}
		else if(pos[no]>d0-lim && pos[no]<d0+lim)
		{
			midi=47;
			System.out.println("B2");
		}
		else if(pos[no]>s1-lim && pos[no]<s1+lim)
		{
			midi=45;
			System.out.println("A2");
		}
		else if(pos[no]>d1-lim && pos[no]<d1+lim)
		{
			midi=43;
			System.out.println("G2");
		}
		
		else if(pos[no]>s2-lim && pos[no]<s2+lim)
		{
			midi=41;
			System.out.println("F2");
		}
		else if(pos[no]>d2-lim && pos[no]<d2+lim)
		{
			midi=40;
			System.out.println("E2");
		//	drawblk.displaytext("C4",xstart,ystart);
		}
		
		else if(pos[no]>s3-lim && pos[no]<s3+lim)
		{
			midi=38;
			System.out.println("D2");
		}
		else if(pos[no]>d3-lim && pos[no]<d3+lim)
		{
			midi=36;
			System.out.println("C2");
		}
		else if(pos[no]>=s4-lim && pos[no]<=s4+lim)
		{
			midi=35;
			System.out.println("B1");
		}
		else if(pos[no]>d4-lim && pos[no]<d4+lim)
		{
			midi=33;
			System.out.println("A1");
		}
		else if(pos[no]>s5-lim && pos[no]<s5+lim)
		{
			midi=31;
			System.out.println("G1");
		}
		else if(pos[no]>d5-lim && pos[no]<d5+lim)
		{
			midi=29;
			System.out.println("F1");
		}
		else if(pos[no]>s6-lim && pos[no]<s6+lim)
		{
			midi=28;
			System.out.println("E1");
		}
		else if(pos[no]>=d6-lim && pos[no]<d6+lim)
		{
			midi=26;
			System.out.println("D1");
		}
		else if(pos[no]>s7-lim && pos[no]<s7+lim)
		{
			midi=24;
			System.out.println("C1");
		}
		else if(pos[no]>d7-lim && pos[no]<d7+lim)
		{
			midi=23;
			System.out.println("B0");
		}
		else if(pos[no]>s8-lim && pos[no]<s8+lim)
		{
			midi=21;
			System.out.println("A0");
		}
		else if(pos[no]>d8-lim && pos[no]<d8+lim)
		{
			midi=19;
			System.out.println("G0");
		}
		else
			System.out.println(" Code yet to be written ");
		
		
		
	
		
	}	
	midilist[i]=midi;  //pass this
	}
	countt=i;
	//testin 
	for(i=0;i<nocop;i++)
		System.out.println(midilist[i]);
	CompareImage cmpimg;
	
	if(midi!=0)
	{
		if(sharp!=0)
		{
		if(sharp==1)
		{
			if(midi==29 ||midi==41||midi==53||midi==65||midi==77)
				midi=midi+1;
		}
		else if(sharp==2)
		{
			if(midi==29 ||midi==41||midi==53||midi==65||midi==77||midi==24||midi==36||midi==48||midi==60||midi==72)
				midi=midi+1;
		}
		else if(sharp==3)
		{
			if(midi==29 ||midi==41||midi==53||midi==65||midi==77||midi==24||midi==36||midi==48||midi==60||midi==72||midi==19||midi==31||midi==43||midi==55||midi==67||midi==79)
				midi=midi+1;
		}
		else if(sharp==4)
		{
			if(midi==29 ||midi==41||midi==53||midi==65||midi==77||midi==24||midi==36||midi==48||midi==60||midi==72||midi==19||midi==31||midi==43||midi==55||midi==67||midi==79||midi==26||midi==38||midi==50||midi==62||midi==74)
				midi=midi+1;
		}
		else if(sharp==5)
		{
			if(midi==29 ||midi==41||midi==53||midi==65||midi==77||midi==24||midi==36||midi==48||midi==60||midi==72||midi==19||midi==31||midi==43||midi==55||midi==67||midi==79||midi==26||midi==38||midi==50||midi==62||midi==74||midi==21||midi==33||midi==45||midi==57||midi==69)
				midi=midi+1;
		}
		else if(sharp==6)
		{
			if(midi==29 ||midi==41||midi==53||midi==65||midi==77||midi==24||midi==36||midi==48||midi==60||midi==72||midi==19||midi==31||midi==43||midi==55||midi==67||midi==79||midi==26||midi==38||midi==50||midi==62||midi==74||midi==21||midi==33||midi==45||midi==57||midi==69||midi==28||midi==40||midi==52||midi==64||midi==76)
				midi=midi+1;
		}
		else if(sharp==7)
		{
			if(midi==29 ||midi==41||midi==53||midi==65||midi==77||midi==24||midi==36||midi==48||midi==60||midi==72||midi==19||midi==31||midi==43||midi==55||midi==67||midi==79||midi==26||midi==38||midi==50||midi==62||midi==74||midi==21||midi==33||midi==45||midi==57||midi==69||midi==28||midi==40||midi==52||midi==64||midi==76||midi==23||midi==35||midi==47||midi==59||midi==71||midi==29||midi==41||midi==53||midi==65)
				midi=midi+1;
		}
		}
		else
		{
			if(flat!=0)
			{
				if(flat==1)
				{
					if(midi==23||midi==35||midi==47||midi==59||midi==71)
						midi=midi-1;
				}
				if(flat==2)
				{
					if(midi==23||midi==35||midi==47||midi==59||midi==71||midi==28||midi==40||midi==52||midi==64||midi==76)
						midi=midi-1;
				}
				if(flat==3)
				{
					if(midi==23||midi==35||midi==47||midi==59||midi==71||midi==28||midi==40||midi==52||midi==64||midi==76||midi==21||midi==33||midi==45||midi==57||midi==69)
						midi=midi-1;
				}
				if(flat==4)
				{
					if(midi==23||midi==35||midi==47||midi==59||midi==71||midi==28||midi==40||midi==52||midi==64||midi==76||midi==21||midi==33||midi==45||midi==57||midi==69||midi==26||midi==38||midi==50||midi==62||midi==74)
						midi=midi-1;
				}
				if(flat==5)
				{
					if(midi==23||midi==35||midi==47||midi==59||midi==71||midi==28||midi==40||midi==52||midi==64||midi==76||midi==21||midi==33||midi==45||midi==57||midi==69||midi==26||midi==38||midi==50||midi==62||midi==74||midi==19||midi==31||midi==43||midi==55||midi==67||midi==79)
						midi=midi-1;
				}
				if(flat==6)
				{
					if(midi==23||midi==35||midi==47||midi==59||midi==71||midi==28||midi==40||midi==52||midi==64||midi==76||midi==21||midi==33||midi==45||midi==57||midi==69||midi==26||midi==38||midi==50||midi==62||midi==74||midi==19||midi==31||midi==43||midi==55||midi==67||midi==79||midi==24||midi==36||midi==48||midi==60||midi==72)
						midi=midi-1;
				}
				if(flat==7)
				{
					if(midi==23||midi==35||midi==47||midi==59||midi==71||midi==28||midi==40||midi==52||midi==64||midi==76||midi==21||midi==33||midi==45||midi==57||midi==69||midi==26||midi==38||midi==50||midi==62||midi==74||midi==19||midi==31||midi==43||midi==55||midi==67||midi==79||midi==24||midi==36||midi==48||midi==60||midi==72||midi==29||midi==41||midi==53||midi==65||midi==77)
						midi=midi-1;
				}
			}	
		}
		for(i=0;i<countt;i++)
			midilist[i]=midilist[i]+transpose;
		System.out.println("transpose"+transpose);
		midi+=transpose;
		
		cmpimg=new CompareImage(position,org,midilist,countt,pd);
		position=cmpimg.returnposition();
		System.out.print("note detection : "+position);
		
	}
	/*int d1=s1-threshold/2-thresblack/2;
	int d2=s2-threshold/2-thresblack/2;
	int d3=s3-threshold/2-thresblack/2;
	int d4=s4-threshold/2-thresblack/2;
	int d5=s5-threshold/2-thresblack/2;
	int d6=s6-threshold/2-thresblack/2;
	*/
	//System.out.println("S5 "+s5);
/*	if(start>=d1 && end<=d2)
		System.out.println("THE NOTE IS :F4");
	else if(start>=s1 && start<d2 && end>d2 && end<=s2)
		System.out.println("THE NOTE IS :E4");
	
	/*else if(start<s2 && end>s2)
		System.out.println("THE NOTE IS :D4");
	else if(start<s3 && end>s3)
		System.out.println("THE NOTE IS :B3");
	else if(start<s4 && end>s4)
		System.out.println("THE NOTE IS :G3");
	else if(start<s5 && end>s5)
		System.out.println("THE NOTE IS :E3");*/
	/*else if(start>=d2 &&  end<=d3)
		System.out.println("THE NOTE IS :D4");
	else if(start>=s2 && start<d3 && end>d3 && end<=s3)
		System.out.println("THE NOTE IS :C4");
	
	else if(start>=d3 &&  end<=d4)
		System.out.println("THE NOTE IS :B3");
	else if(start>=s3 && start<d4 && end>d4 && end<=s4)
		System.out.println("THE NOTE IS :A3");
	
	else if(start>=d4 &&  end<=d5)
		System.out.println("THE NOTE IS :G3");
	else if(start>=s4 && start<d5 && end>d5 && end<=s5)
		System.out.println("THE NOTE IS :F3");
	
	else if(start>=d5 &&  end<=d6)
		System.out.println("THE NOTE IS :E3");
	else if(start>=s5 && start<d6 && end>d6 && end<=s6)
		System.out.println("THE NOTE IS :D3");
	
	
	else
		System.out.println("Need to code for this note "); */
	
}
public double returnposition()
{
	return position;
}
/*public static void main(String args[]) throws IOException
{

	BufferedImage buffImage;
	File file = new File("glyph6.png");
    FileInputStream fis= new FileInputStream(file);
    buffImage=ImageIO.read(fis);
	*/
/*	YProjection ypro=new YProjection(buffImage);
	ypro.calcYProjection(0, buffImage.getHeight(), 0, buffImage.getWidth());
//	ypro.printYProjection();
	int Ypro[]=ypro.getYProjection();
	//
	int sp[]=new int[2];
	int startend[][]=new int[20][2];
	 
    StaveParameters sparam= new StaveParameters(buffImage);
 
   int enH=buffImage.getHeight();
    int enW=buffImage.getWidth();
    sparam.calcStaveParameters(0, enH, 0, enW);
sp=sparam.findStaveParameters();


   
 // 	int thres=sp[1];
  	//System.out.println("ThresHold "+thres);
  	//
  	
  	Notedetection notedetec=new Notedetection(buffImage,Ypro,sp);
  	notedetec.Recognizenotes();
   
	
}*/
}
