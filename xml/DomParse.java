import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException; 
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


//http://tutorials.jenkov.com/java-xml/dom.html

public class DomParse {

	public static void main(String[] args){
		
		//Creating A Java DOM XML Parser
		DocumentBuilderFactory builderFactory =
		DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		Document document=null;
		try {
			builder = builderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();  
		}
		
		// Parsing XML with a Java DOM Parser
		try {
			document = builder.parse(new FileInputStream("Alejandro Fernandez - Canta Corazon.xml"));
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	
	
		//The DOM Document Element
		Element rootElement = document.getDocumentElement();
		
		//DOM Elements, Child Elements, and the Node Interface
		NodeList nodes = rootElement.getChildNodes();
		
		for(int i=0; i<nodes.getLength(); i++){
			Node node = nodes.item(i);
			//System.out.println(node);
			if(node instanceof Element){
				//a child element to process
				Element child = (Element) node;
				String attribute = child.getAttribute("width");


			}
		}
		
		
		
	}
}