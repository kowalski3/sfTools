import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

//http://www.freeformatter.com/xpath-tester.html


//use rar reading methods to read file from directory and feed to module
public class XPathTest {
	
	
	private void getLyricsInXml()throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true); // never forget this!
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse("sample.xml");

		XPathFactory xpathfactory = XPathFactory.newInstance();
		XPath xpath = xpathfactory.newXPath();
		
		
		// 1) Get all Text Elements
		XPathExpression expr = xpath.compile("//Text/text()");
		Object result = expr.evaluate(doc, XPathConstants.NODESET);
		NodeList nodes = (NodeList) result;
		for (int i = 0; i < nodes.getLength(); i++) {
			if(containsLyrics(nodes.item(i).getNodeValue())){
				System.out.println(nodes.item(i).getNodeValue());
			}
			
		}
	}
	
	public boolean containsLyrics(String lyricLine){
		if(lyricLine.equals("Instrumental")
		|| lyricLine.contains("seconds")){
			return false;
		}
		return true;
		
	}
	
	public static void main(String[] args) throws Exception {
		new XPathTest().getLyricsInXml();
	}
}
