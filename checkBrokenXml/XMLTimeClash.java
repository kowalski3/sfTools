import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class XMLTimeClash {
	
	
	
	private void traverseFolders(String startDirectory) throws Exception{
	
		File directory = new File(startDirectory);

		// get all the files from a directory
		File[] fList = directory.listFiles();
		
		for (File file : fList) {
			if (file.isFile()) {		
				if(file.getName().contains(".xml")) {
				Double clash = checkXmlForClash(xmlToArray(file));
				
				if(clash != null){
					System.out.println(file.getName());
					System.out.println(clash);
					System.out.println();
					System.out.println();
				}
				
			} else if (file.isDirectory()) {
			traverseFolders(file.getPath());
			}
			}	
		}
	}
	
	public Double checkXmlForClash(ArrayList<Double> xmlArrayList){
		for(int i = 0; i < xmlArrayList.size()-1; i++){
			if(xmlArrayList.get(i) > xmlArrayList.get(i+1)) {
				return xmlArrayList.get(i);	
			}				
		}
		return null;
	}
	
	
	public ArrayList<Double> xmlToArray(File file){
			BufferedReader in = null;
			ArrayList<Double> xmlTime = new ArrayList<Double>();
			try{	
				in = new BufferedReader(new FileReader(file));
				String line;
				while ((line = in.readLine()) != null) {
					if(line.contains("<Time>")){
						//System.out.println(line);
						String timeStr = line.substring(line.indexOf(">")+1, line.lastIndexOf("<")); 
						xmlTime.add(Double.parseDouble(timeStr));
					}
				}
					
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
			} catch (IOException ex){
				ex.printStackTrace();
			} finally{
				closeReader(in);
			}
			
			return xmlTime;
	}
	private void closeReader(Reader reader){
		try{
			if(reader != null){
				reader.close();
			}
			} catch(IOException ex){
				ex.printStackTrace();
		}
	}
	
	
	
	
	public static void main(String[] args) throws Exception {
		new XMLTimeClash().traverseFolders("C:\\Users\\Julian.SUNFLYKARAOKE\\Desktop\\TEST");
	}


}
