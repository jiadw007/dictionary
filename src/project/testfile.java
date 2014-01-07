package project;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
/**
 * class that test the accurate output file in user input mode
 * Please change the file path to the right path
 * All output files are in the project file
 * @author DAWEI JIA
 *
 */
public class testfile {
	
	public static void main(String args[]){
		File myFile=new File("C:/Users/Administrator/Workspaces/MyEclipse 10/dictionary/BTree_level.out");
		
		try{
			FileReader file=new FileReader(myFile);
			LineNumberReader in=new LineNumberReader(file);
			boolean eof=false;
			while(!eof){
				String x=in.readLine();
				if(x==null){
					eof=true;
					System.out.println();
				}else{
					System.out.print(x);
				}
			}
			in.close();
		}catch(IOException e){}
	}

}
