import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.io.*;

public class ProductCreator{
	
	private Map<String, Map<String, String>> compilationToProductsMap= new HashMap<String, Map<String, String>>();
	private File productData = new File("productData.csv");
	
	
	
	public void addToProductMap(String line){
		String[] dataRow = line.split(",");
		if(compilationToProductsMap.get(dataRow[0]) == null){		
			compilationToProductsMap.put(dataRow[0], new HashMap<String,String>());
		}
		
		compilationToProductsMap.get(dataRow[0]).put(dataRow[1], dataRow[2]);
		
	}
	
	
	public void findFile(String filePrefix) {
		File directory = new File("C:\\Users\\Julian.SUNFLYKARAOKE\\Desktop\\TESTFOLDER");
		// get all the files from a directory
		File[] fList = directory.listFiles();
		
		for (File file : fList) {
			if (file.isFile()) {
				if(file.getName().contains(filePrefix)) System.out.println(file.getName());
			} else if (file.isDirectory()) {
				findFile(file.getPath());
			}
		}
	}
	
	
	public void createProductMap(){
			BufferedReader in = null;
			try{	
				in = new BufferedReader(new FileReader(productData));
				String line;
				while ((line = in.readLine()) != null) {
					addToProductMap(line);
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
	
	public void getCompilationData(String product){
		Map<String, String> tempMap = compilationToProductsMap.get(product);
	
		for(Map.Entry<String, String> entry : tempMap.entrySet()){
			findFile(entry.getKey());
			//System.out.printf("Key : %s and Value: %s %n", entry.getKey(), entry.getValue());
		}

	}
	
	
	public static boolean isInteger(String s) {
		try { 
			Integer.parseInt(s); 
		} catch(NumberFormatException e) { 
			return false; 
		}
		return true;
	}
	
	private void launch(String product){
		createProductMap();
		getCompilationData(product);
	}
	
	public static void main(String[] args){

			new ProductCreator().launch(args[0]);
		
	}
}