import raroscope.*;
import java.io.*;
import java.util.Enumeration;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class ReadRar{
	ArrayList<String> files = new ArrayList<String>();

	
	public void readFile(String fileName){
		
		RARFile file = null;
		try{
			file = new RARFile(fileName);
		} catch(IOException ex){
			System.out.println(ex);
		}
		
		try{
			Enumeration<RAREntry> entries = file.entries();
		
			System.out.println("FILE: "+ fileName);
			System.out.println();
			while (entries.hasMoreElements()) {
				
				RAREntry entry = entries.nextElement();
				System.out.println(entry.getName());
			}
			
			System.out.println();
			System.out.println();
		} catch (NullPointerException ex){
			System.out.println(ex);
		}
	}
	
	
	
	public void listFilesInDirectory(String directoryName) {
		File directory = new File(directoryName);

		// get all the files from a directory
		File[] fList = directory.listFiles();
		
		for (File file : fList) {
			if (file.isFile()) {
				
				if(file.getName().contains(".rar")) {
					readFile(file.getAbsolutePath());
					//files.add(file.getName());
				}
				
			} else if (file.isDirectory()) {
				listFilesInDirectory(file.getPath());
			}
		}
	}
	
	
	
	public void launch(){
	
		listFilesInDirectory("Z:\\Sunfly PRODUCERS Raw Audio\\Willy Vargas (NEO Music)\\001 APE Component Files");
		
		/*for(String fileName : files){
			readFile(fileName);
		}*/
		
	}
	
	
	public static void main(String[] args){
		new ReadRar().launch();
	}
}