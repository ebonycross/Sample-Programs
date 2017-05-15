import java.util.ArrayList;

/**Assignment 4, Spring 2015: DictionaryUtility (Data Manager)
 * Implements DictionaryUtilityInterface interface and its methods.As the data manager, 
 * computations and methods for Driver class are created.
 * Contains reference objects for HashableWord and HashTable.
 * 
 * @author Ebony Cross
 * 
 */
public class DictionaryUtility implements DictionaryUtilityInterface {
	HashTable hTable;
	HashableWord hWord;
	
	DictionaryUtility(){
		//hTable = new HashTable();
		hWord = new HashableWord();
	}
	
	/** returns an array of Strings which are sentences that don't contain a sentence
	 * 
	 * @param a ArrayList of Strings (sentences)
	 * @param d the Dictionary to compare against
	 * @return the ArrayList of Strings (sentences) that don't contain a gre word
	 */
	public ArrayList<String> checkGRE(ArrayList<String> a, DictionaryInterface d) {
		boolean success = false;

		
		ArrayList<String> notGREWords = new ArrayList<String>();
		for(int i = 0; i < a.size(); i++){

			success = checkGRE(a.get(i), d);
			if(success == false){
				String s = a.get(i).replace(".","");
				notGREWords.add(s);
			}
		}
		return notGREWords;
	}

	/** returns true if the String (sentence) contains a gre word
	 * 
	 * @param s String (sentence)
	 * @param d dictionary to compare it against
	 * @return true if gre word is in string (sentence) false if not.
	 */
	public boolean checkGRE(String s, DictionaryInterface d) {
		
		boolean included;
	
		//System.out.println(s);
		String word = s.replace(".","");
		//System.out.println(word);

		String token[] = word.split(" ");

		for(int i = 0; i < token.length; i++){
			
			//hWord = new HashableWord(token[i]);
			included = d.checkWord(token[i]);
			//hTable.add(hWord);
			if(included == true){
				//System.out.println(token[i] + " is in GRE dictionary");
				included = true;
				return included;
			}
			else{
				//System.out.println(token[i] + " is NOT in GRE dictionary");
				
			}

		}
		included = false;
		return included;
	}
}//end of program











