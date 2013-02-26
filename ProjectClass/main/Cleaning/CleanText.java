package main.Cleaning;

import java.io.BufferedReader;
import java.io.FileReader;

public class CleanText {


	public static String processString(String string) {	
		string = string.replaceAll("[.]{2,}",".");
	
		string = removeSlash(string);
		string = string.replaceAll("\\{", "");
		string = string.replaceAll("}", "");
		string = string.replaceAll("\\(", "");
		string = string.replaceAll("\\)", "");
		string = string.replaceAll("\\/b", "");
		string = string.replaceAll("(---)*","");
		string = string.replaceAll("&#xA;", "");
		string = string.replaceAll("&#xB;", "");
		string = string.replaceAll("&#xC;", "");
		string = string.replaceAll("&#xD;", "");
		string = string.replaceAll("&#xE;", "");
		string = string.replaceAll("&lt;", " ");
		string = string.replaceAll("&gt;", " ");
		string = string.replaceAll("&quot;", " ");
		string = string.replaceAll("&amp;", " ");
		string = string.replaceAll("(#)*", "");
		
		// replacing all non-ascii characters
		string = string.replaceAll("[^\\x00-\\x7F]", "");
		string = string.replaceAll("[^\\x20-\\x7F]", "");
		
		string = string.replaceAll("\\s", " ");
		
		
		string = string.trim();
		
		return string;
	}
	
	
	private static String removeNewLine(String str) {
		
		String string = str;
		int indx = 1;
		
		while(indx != -1) {
		
			indx = string.indexOf("\\n"); //, indx);
			
			String t = "";
			if(indx != -1) {
				
				t = string.substring(0, indx); // - 1);
				t += string.substring(indx + 2); //string.substring(indx + 2);
				string = t;
			}	
		}
		return string;
	}
	
	private static String removeSlash(String str) {
		
		String string = str;
		int indx = 1;
		
		while(indx != -1) {
		
			indx = string.indexOf("\\"); //, indx);
			
			String t = "";
			if(indx != -1) {
				
				t = string.substring(0, indx); // - 1);
				t += string.substring(indx + 1); //string.substring(indx + 2);
				string = t;
			}	
		}
		return string.trim();
	}

	public static void main(String [] args) {
		
		String s = "<row Id=\"1332647\" PostHistoryTypeId=\"5\" PostId=\"723397\" RevisionGUID=\"###########cd3aafe8-47ee-497d-a4e8-948c2a769d7e\" CreationDate=\"2009-04-06T22:27:07.567\" UserId=\"40414\" Comment=\"Added examples of articles and filenames\" Text=\"I have a large number of text files (1000+) containing articles from academic journals. Each article's file contains a &quot;stub&quot; from the end of the previous article (at the beginning) and from the beginning of the next article (at the end). I need to remove these stubs in preparation for running a frequency analysis on the articles because the stubs are duplicate data. &#xD;&#xA;&#xD;&#xA;I know I can use diff to output the differences between the files, ignore whitespace and treat the files as text, and then compare them manually, but this doesn't male much sense when dealing with this much data. Accuracy does not have to be 100%, so a script that compared each file to the next file and then removed 1 copy of the duplicate text would be perfect. This seems like it would be a pretty common issue when programming so I am surprised that I haven't been able to find anything that does this.&#xD;&#xA;&#xD;&#xA;The file names sort in order, so a script that compares each file to the next sequentially should work. E.G.&#xD;&#xA;&#xD;&#xA;&lt;pre&gt;bul_9_5_181.txt&#xD;&#xA;bul_9_5_186.txt&#xD;&#xA;&lt;/pre&gt;&#xD;&#xA;&#xD;&#xA;are two articles, one starting on page 181 and the other on page 186. &#xD;&#xA;&#xD;&#xA;Note: I am an academic doing content analysis of old journal articles for a project in the history of psychology. I am no programmer, but I do have 10+ years experience with linux and can usually figure things out as I go. &#xD;&#xA;&#xD;&#xA;Thanks for your help&#xD;&#xA;&#xD;&#xA;&lt;b&gt;---example stub at beginning of file: everything before &quot;AFFECTIVE PHENOMENA � EXPERIMENTAL&quot; is duplicate from previous file----&lt;/b&gt;&#xD;&#xA;&#xD;&#xA;SYN&amp;STHESIA&#xD;&#xA;&#xD;&#xA;ISI&#xD;&#xA;&#xD;&#xA;the majority of Portugese words signifying black objects or ideas relating to black. This association is, admittedly, no true synsesthesia, but the author believes that it is only a matter of degree between these logical and spontaneous associations and genuine cases of colored audition.&#xD;&#xA;REFERENCES&#xD;&#xA;&#xD;&#xA;DOWNEY, JUNE E. A Case of Colored Gustation. Amer. J. of Psycho!., 1911, 22, S28-539MEDEIROS-E-ALBUQUERQUE. Sur un phenomene de synopsie presente par des millions de sujets. / . de psychol. norm, et path., 1911, 8, 147-151. MYERS, C. S. A Case of Synassthesia. Brit. J. of Psychol., 1911, 4, 228-238.&#xD;&#xA;&#xD;&#xA;AFFECTIVE PHENOMENA � EXPERIMENTAL&#xD;&#xA;BY PROFESSOR JOHN F. .SHEPARD&#xD;&#xA;University of Michigan&#xD;&#xA;&#xD;&#xA;Three articles have appeared from the Leipzig laboratory during the year. Drozynski (2) objects to the use of gustatory and olfactory stimuli in the study of organic reactions with feelings, because of the disturbance of breathing that may be involved. He uses rhythmical auditory stimuli, and finds that when given at different rates and in various groupings,&#xD;&#xA;&#xD;&#xA;&lt;b&gt;---this is from the end of the same file, everything AFTER &quot;1911. Pp.39&quot; is duplicate from the next article---&lt;/b&gt;&#xD;&#xA;&#xD;&#xA;Pleasantness of Colors. Arner. J. of Psychol., 1911, 22, 578-579. 8. WASHBURN, M. F . and CRAWFORD, D . Fluctuations in the Affective Value of Colors During Fixation for One Minute. Amer. J. of Psychol., 1911, 22, 579-J82. 9. WELLS, F . L. and FORBES, A. On Certain Electrical Processes in the Human Body and their Relation to Emotional Reactions. (No. 16 of Archives of Psychology). New York: The Science Press, 1911. Pp. 39.&#xD;&#xA;&#xD;&#xA;AFFECTIVE PHENOMENA � DESCRIPTIVE AND THEORETICAL&#xD;&#xA;BY PROFESSOR H. N. GARDINER Smith College&#xD;&#xA;&#xD;&#xA;Fundamental questions are discussed systematically by Rehmke (18) in a second edition of a well-digested treatise, a characteristic feature of which is its attempt to relate feeling, emotion and mood.&#xD;&#xA;Feeling (Gefuhl) is defined as a Bestimmtheitsbesonderheit des zustdnd-&#xD;&#xA;&#xD;&#xA;lichen Bewusstseins. Consciousness being conceived as the individual soul, its state is assumed to be at any given moment simple and unique; hence the momentary feeling is always one of pleasure or displeasure, never &quot;mixed.&quot; It is determined, not by any one, but by the totality of the objective factors, those being massgebend which are in the focus of attention. A &quot; feeling,&quot; in the ordinary sense, is a complex of the affective state and the &quot;determining&quot; and &quot;accompanying&quot; objective components, the &quot;determining&quot; objects of attention giving the kind of feeling, the &quot;accompanying&quot; organic sensations being mainly responsible for its obscure &quot;coloring&quot; and its degree. Mood (Stimmung) appears in a certain contrast to &quot;feeling&quot; in that in it organic sensation is the &quot;determining&quot; factor and no particular object occupies the focus of attention. Emotion {Affeki) is not contrasted with &quot;feeling,&quot; but is &quot;feeling&quot; characterized by the intensity of the &quot;accompanying&quot; organic sensations, which are rightly included in the emotion; we must not, however, confuse, with James and Lange, { the bodily  } changes which give rise&#xD;&#xA;&#xD;&#xA;&lt;b&gt;---end example---&#xC;&lt;/b&gt;\" />";
		
		System.out.println(s);
		System.out.println();
		System.out.println(processString(s));
	}		
}