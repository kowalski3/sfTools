import java.util.HashMap;
import java.util.Map;
import java.io.*;
import java.util.Comparator;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;



public class CheckRowsSame{
	
	private Map<String, String> archiveMap = new HashMap<String, String>();
	private File sfData = new File("testData.csv");
	
	
	public void readDataFromFile() {
		BufferedReader in = null;
			try{	
				in = new BufferedReader(new FileReader(sfData));
				String line;
				while ((line = in.readLine()) != null) {
					String[] dataRow = line.split(",");
					try{
						if(isInteger(dataRow[0])){
							archiveMap.put(dataRow[0], line);
						}
					}catch(ArrayIndexOutOfBoundsException ex){
						//BAD PROGRAMMING BUT NEED IT FOR NOW
					}
					
				}
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
			} catch (IOException ex){
				ex.printStackTrace();
			} finally{
				closeReader(in);
			}
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
	
	//ensures that data is integer before adding to map
	public static boolean isInteger(String s) {
		try { 
			Integer.parseInt(s); 
		} catch(NumberFormatException e) { 
			return false; 
		}
		return true;
	}
	
	 
	private void launch(){
		readDataFromFile();	
		Collections.sort(archiveMap);
		FileWriter.writeToFile(archiveMap);
	} 
	
	public static void main(String[] args){
		new CheckRowsSame().launch();
	}
	
}