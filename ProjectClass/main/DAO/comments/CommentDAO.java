package main.DAO.comments;

import java.io.File;
import java.io.IOException;

import javax.xml.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class CommentDAO {

	private static void readXML(File file)
	    {
	        Document document;
	        DocumentBuilder documentBuilder;
	        DocumentBuilderFactory documentBuilderFactory;
	        NodeList nodeList;
	        Comment comment;
	        
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
	            
	            for (int index = 0; index < nodeList.getLength(); index++)
	            {
	            	comment = new Comment();
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
	                    		comment.setCOMMENT_ID(temp.getNodeValue());
	                    	} else if (switchTemp.equals("PostId")) {
	                    		comment.setPOST_ID(temp.getNodeValue());
	                    	} else if (switchTemp.equals("Text")) {
	                    		comment.setCOMMENT_TEXT(temp.getNodeValue());
	                    	} else if (switchTemp.equals("CreationDate")) {
	                    		comment.setCREATION_DATE(temp.getNodeValue());
	                    	} else if (switchTemp.equals("UserId")) {
	                    		comment.setCOMMENT_USER_ID(temp.getNodeValue());
	                    	} else {
	                    		System.out.println("skipping");
	                    	}
	                    }
	                }
	                System.out.println(" New Comment Fetched !!! ");
	                System.out.println("User id : " + comment.getCOMMENT_USER_ID() + " Text :" + comment.getCOMMENT_TEXT() +
	                		 " Comment id : " + comment.getCOMMENT_ID() + " Post Id : " + comment.getPOST_ID() + " Date : " + comment.getCREATION_DATE());
	            }
	        }
	        catch (Exception exception)
	        {
	            exception.printStackTrace();
	        }
	    }

		public static void main(String [] args) throws SAXException, IOException, ParserConfigurationException {
			
			File file = new File("/media/Data/StackOverflow/stackoverflow_com/comments/cleaned/");
			
			readXML(file);			
		}
}
