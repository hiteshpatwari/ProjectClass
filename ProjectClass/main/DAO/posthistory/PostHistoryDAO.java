package main.DAO.posthistory;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import main.Connetion.CheckConnection;
import main.DAO.posthistory.PostHistory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class PostHistoryDAO {

	
	static String path = "/media/Data/StackOverflow/stackoverflow_com/posthistory/cleaned/";
	static File base_path = new File(path);

	
	private static void readXML(File file)
    {
		// Variables for DOM parser
        Document document;
        DocumentBuilder documentBuilder;
        DocumentBuilderFactory documentBuilderFactory;
        NodeList nodeList;
        PostHistory posthistory;
        CheckConnection chk = new CheckConnection();
        
        try
        {
            documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(file);
            document.getDocumentElement().normalize();
            
            System.out.println(document.getElementsByTagName("row"));
            nodeList = document.getElementsByTagName("row");
            Node node;
            String switchTemp;
            String tagValues;
            List<String> tagList = null;
            
            chk.createPostHistoryCollection();
            
            for (int index = 0; index < nodeList.getLength(); index++)
            {
            	posthistory = new PostHistory();
                node = nodeList.item(index);
                
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element element = (Element) node;	                    
                    NamedNodeMap attributes = element.getAttributes();
                    Node temp = null;
                    
                    /*
                     * Now, this is where we need to write the logic for storing the Attributes' value in DB, in this case
                     * with each iteration of the for loop we need to get the value in an object (comments, posts, etc) and
                     * store that object at the end in db.
                     */
                    for(int i = 0 ; i < attributes.getLength(); i++) {
                    	temp = attributes.item(i);
                    	//System.out.println(" Node Name : " +temp.getNodeName() + " with Value : " +  temp.getNodeValue());
                    	switchTemp = temp.getNodeName();
                    	
                    	if(switchTemp.equals("Id")) {
                    		posthistory.setID(temp.getNodeValue());
                    	} else if (switchTemp.equals("PostHistoryTypeId")) {
                    		posthistory.setPOST_HISTORY_TYPE_ID(temp.getNodeValue());
                    	} else if (switchTemp.equals("PostId")) {
                    		posthistory.setPOST_ID(temp.getNodeValue());
                    	} else if (switchTemp.equals("CreationDate")) {
                    		posthistory.setCREATION_DATE(temp.getNodeValue());
                    	} else if (switchTemp.equals("RevisionGUID")) {
                    		posthistory.setREVISION_GUID(temp.getNodeValue());
                    	} else if (switchTemp.equals("Text")) {
                    		posthistory.setTEXT(temp.getNodeValue());
                    	} else if (switchTemp.equals("UserId")) {
                    		posthistory.setUSER_ID(temp.getNodeValue());
                    	} else if (switchTemp.equals("UserDisplayName")) {
                    		posthistory.setUSER_DISPLAY_NAME(temp.getNodeValue());
                    	} else if (switchTemp.equals("Comment")) {
                    		posthistory.setCOMMENT(temp.getNodeValue());
                    	} else if (switchTemp.equals("CloseReasonId")) {
                    		posthistory.setCLOSE_REASON_ID(temp.getNodeValue());
                    	}
                    }
                }
                
                chk.insertInDB(posthistory);
                System.out.println(index + " records Inserted in DB");
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

	public static void main(String [] args) throws SAXException, IOException, ParserConfigurationException {
	
		File [] files = new File[base_path.listFiles().length];
		
		files = base_path.listFiles();
		
		Arrays.sort(files);
		
		for(File f : files) {
			if(!f.isDirectory()) {
				readXML(f);
			}
		}
	}
}
