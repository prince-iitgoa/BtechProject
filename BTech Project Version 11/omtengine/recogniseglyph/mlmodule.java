package recogniseglyph;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import sun.org.mozilla.javascript.internal.regexp.SubString;
import weka.classifiers.Classifier;
import weka.core.Utils;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;

public class mlmodule {
		public int ClassifySignature(int tempsig[]) throws Exception
		{
			
			//String rootPath="J:/test/";
			String temp=null;
			//int[] x={-197380,-1710619,-1842205,-1907998,-1,-197380,-1710619,-2829100,-3355444,-1,-197380,-8421505,-11119018,-5000269,-1,-197380,-2171170,-9934744,-10000537,-1,-197380,-1710619,-2171170,-2236963,-1};
				//String teststrin="";
			//	InputStream is= new ByteArrayInputStream(teststrin.getBytes());
				//BufferedReader reader= new BufferedReader(new InputStreamReader(is));
			//	Instances m_Data=null;
		//	System.out.println("hello");
			for(int u=0;u<25;u++)
			{
			//	System.out.print(tempsig[u]);
				temp=temp+","+tempsig[u];
			}
			temp+=",?";
			//	Reader xyz;
			//	m_Data= new Instances(xyz);  //aah
			//	Instance inst= makeInstance(reader,xyz);
			//	Instance inst= makeInstance(cleanupString())
			String[] temp1=temp.split("null,");
					//temp=sCurrentLine.substring(sCurrentLine.indexOf("{") + 1, sCurrentLine.indexOf("}"));
		//	System.out.print(temp1[1]);
			BufferedWriter out=null;
			FileWriter fstream=new FileWriter("trry.arff",true);
	//		System.out.println("hello1");
			out=new BufferedWriter(fstream);
			out.write("\n");
			out.write(temp1[1]);
			out.close();
			fstream.close();
				int i=0;
				
				BufferedReader reader= new BufferedReader(new FileReader("trry.arff"));
				Instances test= new Instances(reader);
				test.setClassIndex(test.numAttributes()-1);
			//	reader.close();
		//		System.out.println("hello2");
				Instances newinst = new Instances(test);
				Classifier clas =(Classifier) weka.core.SerializationHelper.read("QuavIBknew.model");
		//		System.out.println(test.numAttributes()-1);
				
				i=test.numInstances()-1;
				//for(i=0; i<test.numInstances();i++)
			//	{
					int finalClass=(int)clas.classifyInstance(test.instance(i));
					newinst.instance(i).setClassValue(finalClass);
				//	System.out.println(finalClass); 	// I-th class.
		
				//}
			//	System.out.println(newinst.toString());
			
				//System.out.println("\nClass Predicted : "+finalClass);
		//		System.out.println("hello3");
				reader.close();
				return finalClass;
		}
}
