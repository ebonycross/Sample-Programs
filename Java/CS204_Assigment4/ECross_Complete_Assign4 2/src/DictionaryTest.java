import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class DictionaryTest {

	private static ArrayList<String> words, words2;
	private static ArrayList<String> result, result2, test1, test2;
	private static Dictionary commonWords;
	private static Dictionary greWords;
	private static DictionaryUtility greUtility;
	private static File commonFile, greFile, testFile;
	static JFileChooser cf = new JFileChooser(); 
	
	@BeforeClass
	public static void oneTimeSetUp() throws Exception {
		greUtility = new DictionaryUtility();
		commonWords = new Dictionary();
		greWords = new Dictionary();
		words = new ArrayList<String>();
		words2 = new ArrayList<String>();
		test1 = new ArrayList<String>();
		test1.add("I abhor tests.");
		test1.add("They make me sick to my abdomen.");
		test1.add("So I must adhere to my study schedule.");
		test1.add("I hate tests.");
		
		String[] w = {"they", "make", "me", "sick", "to", "my", "abdomen"};
		for(int i=0;i<w.length;i++)
			words.add(w[i]);
		
		test2 = new ArrayList<String>();
		test2.add("Antioch does not have an abundant time today.");
		test2.add("So today's meeting must be brisk.");
		test2.add("I'll try not to show distress while we talk.");
		test2.add("We shall see.");
		
		String[] w2 = {"Antioch", "does", "not", "have", "an", "abundant", "time", "today"};
		for(int i=0;i<w2.length;i++){
			words2.add(w2[i]);
		}
	
		
		//cf = new JFileChooser();
		createCommon();
		createGRE();
	}
	
	@AfterClass
	public static void oneTimeTearDown() throws Exception {
		words = words2 = null;
		greUtility = null;
		commonWords = null;
		greWords = null;
		test1 = test2 = null;
	}

	@Test
	public void testCheckWords() {
		result = commonWords.checkWords(words);
		assertEquals(true, result.get(0).equals("sick"));
		assertEquals(true, result.get(1).equals("abdomen"));
	}
	
	@Test
	// TO BE IMPLEMENTED BY STUDENT
	public void testCheckWordsStudent()
	{
		// Provide additional test for the checkWords() method
		// with different data than above
		result2 = commonWords.checkWords(words2);
		assertEquals(true, result2.get(0).equals("Antioch"));
		assertEquals(true, result2.get(1).equals("abundant"));
		//fail("Not yet implemented by Student");
	}
	@Test
	public void testCheckWord(){
		assertEquals(true, commonWords.checkWord("heavy"));
		assertEquals(false, commonWords.checkWord("applicable"));
	}
	
	@Test
	// TO BE IMPLEMENTED BY STUDENT
	public void testCheckWordStudent()
	{
		// Provide additional test for the checkWord() method
		// with different data than above
		assertEquals(true, commonWords.checkWord("almost"));
		assertEquals(false, commonWords.checkWord("meter"));
		//fail("Not yet implemented by Student");
	}
	
	@Test
	public void testAdd(){
		assertEquals(false, commonWords.checkWord("applicable"));
		commonWords.add("applicable");
		assertEquals(true, commonWords.checkWord("applicable"));
		//restore commonWords back to original
		commonWords.create(commonFile);
	}
	
	@Test
	public void testCheckGreArrayList()
	{
		result = greUtility.checkGRE(test1, greWords);
		assertEquals(result.get(0),"I hate tests");
		
	}
	
	@Test
	// TO BE IMPLEMENTED BY STUDENT
	public void testCheckGreArrayListStudent()
	{
		// Provide additional test for the checkGRE(ArrayList<String> a, Dictionary d)
		// method, with different data than above
		result2 = greUtility.checkGRE(test2, greWords);
		assertEquals(result2.get(0),"We shall see");
		//fail("Not yet implemented by Student");
	}
	
	@Test
	// TO BE IMPLEMENTED BY STUDENT
	public void testCheckGreStringStudent()
	{
		// test the DictionaryUtility method checkGRE(String s, Dictionary d)
		// must be at least 2 tests
		String s1 = "I'll try not to divulge while I talk.";
		String s2 = "We shall see.";
		
		
		assertEquals(true, greUtility.checkGRE(s1.toLowerCase(), greWords));
		assertEquals(false, greUtility.checkGRE(s2.toLowerCase(), greWords));
		//fail("Not yet implemented by Student");
	}
	
	@Test
	public void testPrintToFile()
	{
		String parentPath;
		commonWords.add("basketball");
		parentPath = commonFile.getParent();
		testFile = new File(parentPath+"/test.txt");
		//create test.txt file in same directory as commonwords file
		commonWords.printToFile(testFile);
		//create a new commonWords dictionary with test.txt
		commonWords.create(testFile);
		assertEquals(true, commonWords.checkWord("heavy"));
		assertEquals(true, commonWords.checkWord("basketball"));
		//restore commonWords back to original
		commonWords.create(commonFile);
	}
	
	public static void createCommon()
	{
		//JFileChooser cf = new JFileChooser();
		cf.setDialogTitle("Choose Common word dictionary file");
		cf.showOpenDialog(null);   		//show file chooser for dictionary
		commonFile = cf.getSelectedFile();
		commonWords.create(commonFile);	//create hash table of dictionary
	}
	
	public static void createGRE()
	{
		//JFileChooser cf = new JFileChooser();
		cf.setDialogTitle("Choose GRE dictionary file");
		cf.showOpenDialog(null);   		//show file chooser for dictionary
		greFile = cf.getSelectedFile();
		greWords.create(greFile);	//create hash table of dictionary
	}
}