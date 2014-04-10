import java.util.HashMap;
import java.util.Map;
import java.io.*;
import java.util.Comparator;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;



public class ProductDataCheck{
	
	private Map<Integer, ArrayList<String[]>> archiveMap = new HashMap<Integer, ArrayList<String[]>>();
	private File sfData = new File("archiveCheck.csv");
	

	private ArrayList<String> dataMismatch = new ArrayList<String>();
	private Map<Integer, String> productsMap = new HashMap<Integer, String> ();
	
	
	/**
	 * Create products list
	 *
	*/
	public void createProductsList(){
		for(Map.Entry<Integer, ArrayList<String[]>> entry : archiveMap.entrySet()){
		//if more than one product check to see if the data matches.
			if(entry.getValue().size() > 1) createProductsList(entry.getValue());
		}
	}
	private void createProductsList(ArrayList<String[]> dataRows){
		String value = "";
		int key = Integer.parseInt(dataRows.get(0)[0]); // KEY;
		value += Integer.toString(key) + "|";
		for(int i = 0; i < dataRows.size(); i++){
			value += dataRows.get(i)[1] + ", "; // PRODUCTS
			loadDataIntoProductsMap(key,value);	
		}
	}
	
	
	/**
	 * Check for mismatching rows
	 *
	*/
	public void checkMisMatch(){
		// iterate through keys
		for(Map.Entry<Integer, ArrayList<String[]>> entry : archiveMap.entrySet()){
			//if more than one product check to see if the data matches.
			if(entry.getValue().size() > 1) checkMisMatch(entry.getValue());
		}
	}
	private void checkMisMatch(ArrayList<String[]> dataRows){
		for(int i = 0; i < dataRows.size(); i++){
			for(int j = i+1; j < dataRows.size(); j++){
				if(!Arrays.equals(dataRows.get(i), dataRows.get(j))){
					dataMismatch.add(dataRows.get(i)[0]);
					return;
				}	
			}
		}
	}
	
	
	/**
	 * Add data to maps
	 *
	*/
	public void loadDataIntoArchiveMap(int key, String[] value){
		if(archiveMap.get(key)== null){
			archiveMap.put(key, new ArrayList<String[]>());	
		}
		archiveMap.get(key).add(value);
	}
	
	public void loadDataIntoProductsMap(int key, String value){
		if(productsMap.get(key)== null){
			productsMap.put(key, value);
			return;
		}
		productsMap.put(key, value);
	}
	
	/**
	 * Read file
	 *
	*/
	public void readDataFromFile() {
		BufferedReader in = null;
			try{	
				in = new BufferedReader(new FileReader(sfData));
				String line;
				while ((line = in.readLine()) != null) {
					String[] dataRow = line.split(",");
					if(isInteger(dataRow[0])){
						loadDataIntoArchiveMap(Integer.parseInt(dataRow[0]), dataRow);
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
	
	
	
	/**
	 *launch and main
	 *
	 */
	private void launch(){
		readDataFromFile();
		
		/*
		checkMisMatch();
		System.out.println(dataMismatch);
		System.out.println(mismatchCount);
		*/
		
		createProductsList();
		FileWriter.writeToFile(productsMap);
		//System.out.println(productsMap);
		
	} 
	
	public static void main(String[] args){
		new ProductDataCheck().launch();
	}
	
}