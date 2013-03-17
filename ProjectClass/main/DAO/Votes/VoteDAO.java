package main.DAO.Votes;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import main.Connetion.CheckConnection;
import main.Connetion.MySQLConnection;
import main.DAO.posthistory.PostHistory;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;

public class VoteDAO {

	static String path = "/media/Data/StackOverflow/stackoverflow_com/votes/";
	static File base_path = new File(path);
	static CheckConnection chk = new CheckConnection();
	public static DateTimeFormatter formatter = DateTimeFormat.forPattern("YYYY-MM-dd HH:mm:ss");
	public static DateTime parsed;
	public static Date date;
	
	private static void readXML(File file)
    {
		// Variables for DOM parser
        Document document;
        DocumentBuilder documentBuilder;
        DocumentBuilderFactory documentBuilderFactory;
        NodeList nodeList;
        Vote vote;
        
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
                    		vote.setCREATION_DATE(temp.getNodeValue() + " 00:00:00");
                    	} else if (switchTemp.equals("BountyAmount")) {
                    		vote.setBOUNTY_AMOUNT(temp.getNodeValue());
                    	} else if (switchTemp.endsWith("UserId")) {
                    		vote.setVOTE_USER_ID(temp.getNodeValue());
                    	}
                    }
                }
                
                //chk.updateDateInVotesDocument(vote);
                chk.insertInDB(vote);
                System.out.println(index + " records Inserted in DB");
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

	
	public static void findKeyWord() {
		
		chk.createVotesCollection();
		
		chk.GetNoOfVotesForAType("1");
		chk.GetNoOfVotesForAType("2");
		chk.GetNoOfVotesForAType("3");
		chk.GetNoOfVotesForAType("4");
		chk.GetNoOfVotesForAType("5");
		chk.GetNoOfVotesForAType("6");
		chk.GetNoOfVotesForAType("7");
		chk.GetNoOfVotesForAType("8");
		chk.GetNoOfVotesForAType("9");
		chk.GetNoOfVotesForAType("10");
		chk.GetNoOfVotesForAType("11");
		chk.GetNoOfVotesForAType("12");
		chk.GetNoOfVotesForAType("13");
	}
	
	public static void getNoOfVotesInDateRange(String start, String end) {
		
		parsed = formatter.parseDateTime(start + " " + "00:00:00");
		Date startDate = parsed.toDate();
		
		parsed = formatter.parseDateTime(end + " " + "00:00:00");
		Date endDate = parsed.toDate();
		
		int i = chk.getNoOfVotesForDates(startDate, endDate);
		
		System.out.println(i);
	}
	
	public static void getVotesWithNoUserIDS() {
		chk.getVotes();
	}
	
	public static void main(String [] args) throws SAXException, IOException, ParserConfigurationException {
	
		chk.createVotesCollection();
		
		getNoOfVotesInDateRange("2008-01-01","2009-01-01");
		getNoOfVotesInDateRange("2009-01-01","2010-01-01");

		getVotesWithNoUserIDS();
		//insertFromFiles();
		
		/*findKeyWord();
		System.out.println(CheckConnection.iTotalVotes);*/
		
	}
	
	public static void insertFromFiles() {
		
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
