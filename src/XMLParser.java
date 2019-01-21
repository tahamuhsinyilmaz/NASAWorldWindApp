import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParser {
	static final String CHANGE_LAT = "latitude";
	static final String CHANGE_LONG = "longitude";
	double lat,lon,alt;
	String name,path="";
	File fXmlFile;
	DocumentBuilderFactory dbFactory;
	DocumentBuilder dBuilder;
	Document doc;
	NodeList nList;
	
	public XMLParser(String path) {
		this.path=path;	
		try {
			fXmlFile = new File(this.path);
			dbFactory = DocumentBuilderFactory.newInstance();
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			nList = doc.getElementsByTagName("data");
			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
			
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					name= eElement.getElementsByTagName("name").item(0).getTextContent();
					lat=Double.parseDouble(eElement.getElementsByTagName("lat").item(0).getTextContent());
					lon=Double.parseDouble(eElement.getElementsByTagName("lon").item(0).getTextContent());
					alt=Double.parseDouble(eElement.getElementsByTagName("alt").item(0).getTextContent());
				}
			}

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public double getLatitude() {
		return lat;
	}
	public double getLongitude() {
		return lon;
	}
	public double getAltitude() {
		return alt;
	}
	public String getName() {
		return name;
	}
	public void manupulateElements(String newElement, String operation) {
			try {
				nList = doc.getElementsByTagName("data");
				switch (operation) {
				case "latitude":
					for (int temp = 0; temp < nList.getLength(); temp++) {
						Node nNode = nList.item(temp);	
						if (nNode.getNodeType() == Node.ELEMENT_NODE) {

							Element eElement = (Element) nNode;
							eElement.getElementsByTagName("lat").item(0).setTextContent(newElement);;
							System.out.println(eElement.getElementsByTagName("lat").item(0).getTextContent());
						}
					}
					break;
				case "longitude":
					for (int temp = 0; temp < nList.getLength(); temp++) {

						Node nNode = nList.item(temp);
					
						if (nNode.getNodeType() == Node.ELEMENT_NODE) {

							Element eElement = (Element) nNode;
							eElement.getElementsByTagName("lon").item(0).setTextContent(newElement);;
						}
					}
					break;
					
				}
				
				// write the content into xml file
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(fXmlFile);
				transformer.transform(source, result);
				
			} catch (DOMException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransformerConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransformerFactoryConfigurationError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		
	}
	
	


}
