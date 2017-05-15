import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.JFileChooser;

/**Assignment 4, Spring 2015: Dictionary (Data Manager)
 * Implements DictionaryInterface interface and its methods.As the data manager, 
 * computations and methods for Driver class are created.
 * Contains reference objects for HashableWord and HashTable.
 * 
 * @author Ebony Cross
 * 
 */
public class Dictionary implements DictionaryInterface {

	private HashTable words1; 
	private HashableWord w;
	private ArrayList<String> notWords;

	/**constructor*/
	Dictionary(){
		words1 = new HashTable();
		w = new HashableWord();
		notWords = new ArrayList<String>();
	}

	/**
	 * check if a word is in the dictionary
	 * @param s String
	 * @return true if word is in dictionary or false if not
	 */
	public boolean checkWord(String s){
		boolean included;

		HashableWord hWord = new HashableWord(s);

		included = words1.contains(hWord);
		return included;
		}

	/**
	 * check if words in an ArrayList are in the dictionary
	 * @param words ArrayList of words to be checked
	 * @return an ArrayList of all words not in dictionary
	 */
	public ArrayList<String> checkWords(ArrayList<String> words){
		//boolean excluded = false;
		notWords = new ArrayList<String>();
		for(int i = 0; i < words.size(); i++){
			HashableWord hWord = new HashableWord(words.get(i).toLowerCase());
			
			if(words1.contains(hWord) == false){
				
				notWords.add(words.get(i));	
				
			}
		}
		System.out.println(notWords);
		//Collections.sort(notWords);
		return notWords;

	}

	/**
	 * create hash table of strings in the file contents
	 * @param f File
	 * @return true if File is found and words added, returns false if file not found
	 */
	public boolean create(File f){
		words1 = new HashTable();

		boolean fileExists;

		if (!f.exists()){
			System.out.println("file not chosen");
			//a.setText("No File Chosen");
			fileExists = false;
			return fileExists;

		} 
		else{
			try {
				
			//f = chooser.getSelectedFile();
			Scanner input = new Scanner(f);
			
			String fileWord = "";
			while(input.hasNext()){
				fileWord = input.nextLine();
				//System.out.println(fileWord);
				
				w = new HashableWord(fileWord.toLowerCase());
				words1.add(w);
				//System.out.println(words1);
				
			}
			//System.out.println(words1.toString());
			
			input.close();
			} 
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		
			fileExists = true;
			return fileExists;
		}
	}

	/**
	 * add word to dictionary
	 * @param word String to add to dictionary
	 */
	public void add(String word){
		w = new HashableWord(word);
		words1.add(w);
	}

	/**
	 * write words in dictionary to a file
	 */
	public boolean printToFile(File f){
		boolean fileExist;

		
			try {
	
					PrintWriter outputFile = new PrintWriter(f);
					outputFile.write(words1.toString());
					
					//outputFile.println(words1.toString());
					outputFile.close();
				}

			 catch (IOException e1) {
				e1.printStackTrace();
			}
			
		return false;

	}

}//end of program



