package omtengine;

import java.util.ArrayList;

public class PitchDuration {
	
	 ArrayList<Integer> pitch;//= 	new ArrayList<Integer>();
	 ArrayList<Double> duration;//= 	new ArrayList<Double>();
	 ArrayList<Double> position;
	 public PitchDuration(){
		 pitch= 	new ArrayList<Integer>();
		 duration= 	new ArrayList<Double>();
		 position= new ArrayList<Double>();
	 }
	 
	 public void add(int pit,double dur,double pos)
	 {
		 pitch.add(pit);
		 duration.add(dur);
		 position.add(pos);
	 }
	 
	 public int sizeoflist()
	 {
		 return pitch.size();
	 }
	 
	 public double[] valu(int i)
	 {
		 double temp[][]=new double[1][3];
		 //System.out.println(pitch.get(i)+" "+duration.get(i));
		 temp[0][0]=pitch.get(i);
		 temp[0][1]=duration.get(i);
		 temp[0][2]=position.get(i);
		 return temp[0];
	 }

}
