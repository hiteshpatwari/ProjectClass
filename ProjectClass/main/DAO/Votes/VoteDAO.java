package main.DAO.Votes;

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

import com.mongodb.BasicDBObject;

public class VoteDAO {

	static String path = "/media/Data/StackOverflow/stackoverflow_com/votes/";
	static File base_path = new File(path);
	static CheckConnection chk = new CheckConnection();
	
	private static void readXML(File file)
    {
		// Variables for DOM parser
        Document document;
        DocumentBuilder documentBuilder;
        DocumentBuilderFactory documentBuilderFactory;
        NodeList nodeList;
        Vote vote;
        
        
        chk.createVotesCollection();
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
            	vote = new Vote();
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
                    		vote.setVOTE_ID(temp.getNodeValue());
                    	} else if (switchTemp.equals("VoteTypeId")) {
                    		vote.setVOTE_TYPE_ID(temp.getNodeValue());
                    	} else if (switchTemp.equals("PostId")) {
                    		vote.setVOTE_POST_ID(temp.getNodeValue());
                    	} else if (switchTemp.equals("CreationDate")) {
                    		vote.setCREATION_DATE(temp.getNodeValue());
                    	} else if (switchTemp.equals("BountyAmount")) {
                    		vote.setBOUNTY_AMOUNT(temp.getNodeValue());
                    	} else if (switchTemp.endsWith("UserId")) {
                    		vote.setVOTE_USER_ID(temp.getNodeValue());
                    	}
                    }
                }
                chk.insertInDB(vote);
                System.out.println(index + " records Inserted in DB");
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

	
	public void FindKeyWord(String keyword) {
		BasicDBObject query = new BasicDBObject();
		query.put("post_history_id_type", "");
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
