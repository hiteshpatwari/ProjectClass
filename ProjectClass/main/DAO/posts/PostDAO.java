package main.DAO.posts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import main.DAO.posts.Post;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class PostDAO {

	private static void readXML(File file)
    {
        Document document;
        DocumentBuilder documentBuilder;
        DocumentBuilderFactory documentBuilderFactory;
        NodeList nodeList;
        Post post;
        
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

            for (int index = 0; index < nodeList.getLength(); index++)
            {
            	post = new Post();
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
                    		post.setPOST_ID(temp.getNodeValue());
                    	} else if (switchTemp.equals("PostTypeId")) {
                    		post.setPOST_TYPE_ID(temp.getNodeValue());
                    	} else if (switchTemp.equals("AcceptedAnswerId")) {
                    		post.setACCEPTED_ANSWER_ID(temp.getNodeValue());
                    	} else if (switchTemp.equals("CreationDate")) {
                    		post.setCREATION_DATE(temp.getNodeValue());
                    	} else if (switchTemp.equals("Score")) {
                    		post.setSCORE(temp.getNodeValue());
                    	} else if (switchTemp.equals("ViewCount")) {
                    		post.setVIEW_COUNT(temp.getNodeValue());
                    	} else if (switchTemp.equals("Body")) {
                    		post.setPOST_TEXT(temp.getNodeValue());
                    	} else if (switchTemp.equals("OwnerUserId")) {
                    		post.setOWNER_USER_ID(temp.getNodeValue());
                    	} else if (switchTemp.equals("LastEditorUserId")) {
                    		post.setLAST_EDITOR_ID(temp.getNodeValue());
                    	} else if (switchTemp.equals("LastEditorDisplayName")) {
                    		post.setLAST_EDITOR_NAME(temp.getNodeValue());
                    	} else if (switchTemp.equals("LastEditDate")) {
                    		post.setLAST_EDIT_DATE(temp.getNodeValue());
                    	} else if (switchTemp.equals("LastActivityDate")) {
                    		post.setLAST_ACTIVITY_DATE(temp.getNodeValue());
                    	} else if (switchTemp.equals("Title")) {
                    		post.setTITLE(temp.getNodeValue());
                    	} else if (switchTemp.equals("Tags")) {
                    		tagValues = temp.getNodeValue();
                    		String [] tags = tagValues.split("\\s");
                    		tagList = new ArrayList<String>();
                    		for(String s : tags) {
                    			tagList.add(s);
                    		}
                    		post.setTAGS(tagList);
                    	}
                    }
                }
                System.out.println(" New Post Fetched !!! ");
                System.out.println( post.getPOST_ID() + "\t" + post.getPOST_TEXT() + "\t" + post.getTITLE());
                for(String s : tagList) {
                	System.out.println(s + "\t");
                }
                }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

	public static void main(String [] args) throws SAXException, IOException, ParserConfigurationException {
		
		File file = new File("/media/Data/StackOverflow/stackoverflow_com/posts/cleaned/0000");
		
		readXML(file);			
	}
}
