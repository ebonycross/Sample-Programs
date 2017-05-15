/**
 * Assignment 4, Spring 2015
 * The HashableWorld class holds information about that implements HashableWordInterface,  
 * @author E Cross
 *
 */
public class HashableWord implements HashableWordInterface {
	String word = "";
	
	/**constructor*/
	HashableWord(){
		word = null;
	}
	
	HashableWord(String w){
		word = w;
	}

	/**retrieve hashable word
	 *  
	 * @return word 
	 */
	public String getWord() {
		return word;
	}

	/**
	 * set hashable word 
	 * @param word hashable word to be set
	 */
	public void setWord(String word) {
		this.word = word;
	}

	/**
	 * compute the hash code of the word
	 * @return hash code
	 */
	public int hashCode(){
		final int HASH_MULTIPLIER = 31;
		int h = 0;
		
		for(int i = 0; i < word.length(); i++){
			h = HASH_MULTIPLIER * h + word.charAt(i);
		}
	
		return Math.abs(h);	
	}

	/**
	 * Compares a String portion of a HashableWord to a String
	 * portion of another HashableWord 
	 * @param s String to compare
	 * @return true is Strings are same or else false
	 */
	public boolean equals(HashableWordInterface s){
		String other = "";
		if(s.equals(this)){
			return true;
		}
		if(s.equals(null) || this.getClass() != s.getClass()){
			return false;
		}
		
		HashableWord otherString = (HashableWord) s;
		
		
		return this.word == otherString.word || (this.word != null && 
				this.word.equals(otherString.word));
	}
	
	/**
	 * returns the string portion of the HashableWord
	 * @return string portion of the HashableWord
	 */
	public String getString()
	{
		return word;
	}

}//end of program
