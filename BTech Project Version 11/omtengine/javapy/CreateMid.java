package javapy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CreateMid {
	
	public CreateMid(int noofnotes,double[][] note,String opfile,int volume,int tempo) throws IOException
	{
	     for(int i=0;i<noofnotes;i++)
	     {
	    	 for(int j=0;j<3;j++)
	    	 {
	    		 System.out.print(note[i][j]+ " ");
	    	 }
	    	 System.out.println("");
	     }
	     
	     savef("test.jarp",opfile,volume,tempo,note);
	     
	    
	     
	     //System.out.println(img.length);  
	     
	 	//String cmd = "python jesmidi.py";        
	 	//InputStream is = Runtime.getRuntime().exec(cmd).getInputStream();
	     
	     String cmd[]=new String[2];
	     cmd[0]="python";
	     cmd[1]="jesmidi.py";
	     ProcessBuilder pb=new ProcessBuilder(cmd);
	     pb.start();
	     
	     String cmd2[]=new String[2];
	     cmd2[0]="C:/KMPlayer/KMPlayer";
	     cmd2[1]=opfile;
	     ProcessBuilder pb2=new ProcessBuilder(cmd2);
	     pb2.start();
	     
	 	/*String cmd2 = "vlc output.mid";        
	 	InputStream is2 = Runtime.getRuntime().exec(cmd2).getInputStream();*/
	     

	            
			
	}
	
	
	public CreateMid(String opfile) throws IOException
	{
		 String cmd[]=new String[2];
	     cmd[0]="python";
	     cmd[1]="jesmidi.py";
	     ProcessBuilder pb=new ProcessBuilder(cmd);
	     pb.start();
	     
	     String cmd2[]=new String[2];
	     cmd2[0]="C:/KMPlayer/KMPlayer";
	     cmd2[1]=opfile;
	     ProcessBuilder pb2=new ProcessBuilder(cmd2);
	     pb2.start();
	}
	
	
	
	
    public static void savef(String filename,String opfile,int volume,int tempo, double[][] note) throws IOException
    {
    //	double temptime=0;
   	 	BufferedWriter writer =null;
   	 	try
   	 	{
   	 		writer= new BufferedWriter(new FileWriter(filename));
   	 		writer.write(opfile);
   	 		writer.newLine();
   	 		writer.write(String.valueOf(volume));
   	 		writer.newLine();
   	 		writer.write(String.valueOf(tempo));
   	 		writer.newLine();
   	 		for (int i=0;i<note.length;i++)
   	 		{
   	 			writer.write(String.valueOf((int)note[i][0]));
   	 			writer.newLine();
   	 			writer.write(String.valueOf(note[i][1]));
   	 			writer.newLine();
   	 			writer.write(String.valueOf(note[i][2]));
	 			writer.newLine();
   	 			/*writer.write(String.valueOf(temptime));
   	 			writer.newLine();*/
   	 			//writer.newLine();
   	 		//	temptime+=note[i][1];
   	 			
   	 			//writer.flush();
   	 		}
   		 
   	 	}catch(IOException e){
   		e.printStackTrace();
   	 	}finally{
   	 		if(writer!=null){
   	 			writer.close();
   	 		}
   	 	}
   	 
   	 
    }

}
