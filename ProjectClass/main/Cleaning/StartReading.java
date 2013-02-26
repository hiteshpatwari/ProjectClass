package main.Cleaning;

import java.awt.BufferCapabilities.FlipContents;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class StartReading {

	private static String CLEANED_FILE_PATH = "/media/Data/StackOverflow/stackoverflow_com/posts/cleaned/";
	private static String ORIGINAL_FILE_PATH = "/media/Data/StackOverflow/stackoverflow_com/posts/";

/*	private static String CLEANED_FILE_PATH = "C:\\Documents and Settings\\Hitesh_Patwari\\Desktop\\Project\\cleaned\\c1\\";
	private static String ORIGINAL_FILE_PATH = "C:\\Documents and Settings\\Hitesh_Patwari\\Desktop\\Project\\cleaned\\";
*/	
	static File Ori_folder = new File(ORIGINAL_FILE_PATH);	
	static File cleaned_folder = new File(CLEANED_FILE_PATH);
	
	public StartReading() {	
		if(!Ori_folder.exists()) {
			System.out.println("File " + ORIGINAL_FILE_PATH + " does not exists !");
		}
		else if(!Ori_folder.isDirectory()) {
			System.out.println("Given path is not a directory !");
		}
		
		if(!cleaned_folder.isDirectory()) {
			cleaned_folder.mkdir();
		}
	}
	
	public static void CleanFile(File file) throws IOException {
		
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		
		File newFile = new File(CLEANED_FILE_PATH, file.getName());
		//File newFile = new File(CLEANED_FILE_PATH+file.getName());
		System.out.println(newFile.getAbsolutePath());
		
		if(!newFile.exists()) {
			newFile.createNewFile();
		}
		FileWriter fw = new FileWriter(newFile);
		BufferedWriter bw = new BufferedWriter(fw);
		
		String line = "";
		
		fis = new FileInputStream(file);
		isr = new InputStreamReader(fis);
		br = new BufferedReader(isr);
		
		
		while((line = br.readLine()) != null) {
			line = CleanText.processString(line);
			bw.write(line+"\n");
		}
		bw.close();
		br.close();
		isr.close();
		fis.close();
	}
	
	public static void main(String [] args) throws ParserConfigurationException, SAXException, IOException {
		
		System.out.println(Ori_folder.listFiles().length);
		File [] files = new File[Ori_folder.listFiles().length];
		
		files = Ori_folder.listFiles();
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc;
		
		Arrays.sort(files);
		
		String rootName;
		NodeList rows;
		NamedNodeMap attrMap;
		
		long startTime = System.currentTimeMillis();
		
		for(File f : files) {
				if(!f.isDirectory()) {
					System.out.println("Cleaning started for file : " + f);
					CleanFile(f);
				}
		}
		
		long stopTime = System.currentTimeMillis();
		
		System.out.println(" Total time taken for processing file : " + (stopTime - startTime) / 3600);
		/*
		 * Reusing the variable files for constructing XML documents
		 */
		
		files = new File[cleaned_folder.listFiles().length];
		
		for(File f : files) {
			if(f.isDirectory()) {
				continue;
			}
			doc = dBuilder.parse(f);
			rootName = doc.getDocumentElement().getTagName();
			if(!rootName.equals("posthistory")) {
				rows = doc.getDocumentElement().getElementsByTagName("row");
				System.out.println("length of the root node " + rows.getLength());
			}
			else {
				rows = doc.getDocumentElement().getElementsByTagName("row");
				System.out.println("length of the row " + rows.getLength());
			}
			
			for(int i = 0; i < rows.getLength(); i++) {
				Node node = rows.item(i);
				System.out.println("executing " + i + " row");
				if(node.getNodeType() == Node.ELEMENT_NODE) { 
					attrMap = node.getAttributes();
					
					System.out.println(attrMap.getNamedItem("Id"));
					
				}
			}
		}		
	}
}
