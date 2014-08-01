import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class RenameFiles{
	
	
	public String getNewFileName(String oldFileName){
		String newFileName = oldFileName.substring(0, oldFileName.indexOf("_"));
		return newFileName += oldFileName.substring(oldFileName.indexOf(" "), oldFileName.length());
	}
	
	public void renameAllFiles(String directoryName) {
		File directory = new File(directoryName);
		// get all the files from a directory
		File[] fList = directory.listFiles();
		
		for (File file : fList) {
			if (file.isFile()) {
				
				File newFile = new File(getNewFileName(file.getName()));
				
				System.out.println(file.renameTo(newFile));
				
				
		
			} else if (file.isDirectory()) {
				renameAllFiles(file.getPath());
			}
		}
	}
	
	

	public void showFileNameChanges(String directoryName) {
		File directory = new File(directoryName);
		// get all the files from a directory
		File[] fList = directory.listFiles();
		
		for (File file : fList) {
			if (file.isFile()) {
				String newFileName = getNewFileName(file.getName()); 
				System.out.println(file.getName());
				System.out.println(newFileName);
				System.out.println();
				System.out.println();
		
			} else if (file.isDirectory()) {
				showFileNameChanges(file.getPath());
			}
		}
	}
	
	
	
	public void launch(String directoryName){
		File directory = new File(directoryName);
		// get all the files from a directory
		File[] fList = directory.listFiles();
	
		//showFileNameChanges(directoryName);
		renameAllFiles(directoryName);
	}
	
	
	public static void main(String[] args){
		//try{
			new RenameFiles().launch("C:\\Users\\Julian.SUNFLYKARAOKE\\Desktop\\TESTFOLDER");
		//} catch (StringIndexOutOfBoundsException ex){
		//}
	}
}