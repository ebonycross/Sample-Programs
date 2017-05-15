import static org.junit.Assert.assertEquals;

import javax.swing.*; //need for Swing classes

import java.awt.*; 
import java.awt.event.*; //need for event listener interface
import java.io.*; // need for file to read/write in
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**Assignment 4, Spring 2015: Driver Class
 * Implement and compute the hashable words
 * File read in is chosen by the user. 
 * Computations executed from the Data Manager classes called Dictionary and DictionaryUtility.
 * @author Ebony Cross
 * 
 */
public class DictionaryGuiDrive extends JFrame {
	private final int WINDOW_WIDTH = 500; //window width
	private final int WINDOW_HEIGHT = 500; //window height
	private JPanel main, button;
	private JTextArea textArea;
	private JButton spellButton, GREButton, readButton, exitButton;
	private  Dictionary commonWords;
	private  Dictionary greWords;
	private DictionaryUtility greUtility;
	private  File commonFile, greFile, newWordsFile;
	static JFileChooser cf = new JFileChooser(); 
	private Dictionary dictionaryObj;
	private ArrayList<String> gWords, notGREWords;
	
	/**constructor*/
	public DictionaryGuiDrive(){
		//set title of window
		setTitle("Spell and GRE Checker");

		//call methods
		buildTextPanel();
		buildButtonPanel();

		//set size of window
		setSize(WINDOW_WIDTH,WINDOW_HEIGHT);

		//set layout manager
		setLayout(new BorderLayout());
		add(main, BorderLayout.CENTER);
		add(button, BorderLayout.SOUTH);

		//specify an action for the close button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		commonWords = new Dictionary();
		greWords = new Dictionary();
		greUtility = new DictionaryUtility();
	
		createCommon();
		createGRE();

		pack();
		setVisible(true);
	}


	/** build a panel that components for the variables needed 
	 */
	public void buildTextPanel(){
		//create new panels and textAreas
		main = new JPanel();
		textArea = new JTextArea();

		//create a scrollpane and add to panel
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(500,300));
		main.add(scrollPane);
		main.setBorder(BorderFactory.createTitledBorder("Text for Spell and GRE Checker"));
	}
	
	/** build a panel that components for the button variables needed 
	 */
	public void buildButtonPanel(){
		//create new buttons
		spellButton = new JButton("Spell Check");
		GREButton = new JButton("GRE Word Check");
		readButton = new JButton("Read Text File");
		exitButton = new JButton("Exit");

		//create action listeners for buttons
		readButton.addActionListener(new ReadButtonListener());
		spellButton.addActionListener(new SpellCheckButtonListener());
		GREButton.addActionListener(new GREButtonListener());
		exitButton.addActionListener(new ExitButtonListener());

		button = new JPanel();
		//add buttons
		button.add(spellButton);
		button.add(GREButton);
		button.add(readButton);
		button.add(exitButton);

	}

	/**
	 * finds words that are not in the Common Words Dictionary
	 */
	private class SpellCheckButtonListener implements ActionListener
	{
		//register an event listener for "Add Appt" Button
		public void actionPerformed(ActionEvent e)
		{
			String actionCommand = e.getActionCommand();

			if(actionCommand.equals("Spell Check")){
				boolean include = false;
				ArrayList<String>notCommonWords = new ArrayList<String>();
				ArrayList<String>cWords = new ArrayList<String>();
				String print="";
				
				
				String [] tokens;
				String [] s = textArea.getText().replace(".", "").split("\\n");
				for(int i = 0; i < s.length; i++){
					tokens = s[i].split(" ");
					for(int j = 0; j < tokens.length; j++){
						cWords.add(tokens[j]);		
					}
				}
				
				
				for(int i = 0; i < cWords.size(); i++){
					include =commonWords.checkWord(cWords.get(i).toLowerCase());
					if(include == true){
						print = "";
						
					}
					if(include == false){
						notCommonWords.add(cWords.get(i));
						
					}
				}
				
				for(int k = 0; k < notCommonWords.size(); k++){
					print += notCommonWords.get(k) + "\n";
			}

				
				
				//notCommonWords = commonWords.checkWords(cWords);
				

//				if(include != true){
//					for(int k = 0; k < notCommonWords.size(); k++){
//						print += notCommonWords.get(k) + "\n";
//					}

					if(notCommonWords.size() > 0){
					//default icon, custom title
					int reply = JOptionPane.showConfirmDialog(null, "The following words are not "
							+ "in the dictionary\n\n\n" + print + "\n\n" +
							"Would you like to add the following words to the dictionary?", "Add to Dictionary", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(reply == JOptionPane.YES_OPTION){
						//add words to the common dictionary
						for(int i = 0; i < notCommonWords.size(); i++){
							commonWords.add(notCommonWords.get(i));
						}
						
//						cWords = new ArrayList<String>();
//						String [] t;
//						String [] line = textArea.getText().replace(".", "").split("\\n");
//						for(int i = 0; i < line.length; i++){
//							t = line[i].split(" ");
//							for(int j = 0; j < t.length; j++){
//								cWords.add(t[j]);		
//							}
//						}
					
						//notCommonWords = commonWords.checkWords(cWords);

					}
					if(reply == JOptionPane.NO_OPTION){
						JOptionPane.showMessageDialog(null, "No words were added to the dictionary","Add to Dictionary", JOptionPane.INFORMATION_MESSAGE);
					}
					}
				
				else{
					JOptionPane.showMessageDialog(null, "All words are in the Dictionary", "Spell Check Status", JOptionPane.INFORMATION_MESSAGE);
				}
			}	
		}
	}
	


	/**
	 * finds words that are not in the GRE Word Dictionary
	 */
	private class GREButtonListener implements ActionListener
	{
		//register an event listener for "Add Appt" Button
		public void actionPerformed(ActionEvent e)
		{
			String actionCommand = e.getActionCommand();

			if(actionCommand.equals("GRE Word Check")){
				gWords = new ArrayList<String>();
				String [] tokens;
				String [] s = textArea.getText().replace(".", "").split("\\n");
				for(int i = 0; i < s.length; i++){
						gWords.add(s[i]);		
					}
				}
				notGREWords = greUtility.checkGRE(gWords, greWords);
				

				if(notGREWords.size() > 0){
					String print = "";

					for(int k = 0; k < notGREWords.size(); k++){
						print += notGREWords.get(k) + "\n";
					}


					//default icon, custom title
					int reply = JOptionPane.showConfirmDialog(null, "The following sentences "
							+ "do not contain a GRE Word\n\n" + print + "\n", "GRE Check Status", JOptionPane.PLAIN_MESSAGE);
				}
				else{
					JOptionPane.showMessageDialog(null, "All sentences contain a GRE word", "Check GRE Status", JOptionPane.INFORMATION_MESSAGE);
				}
			}	
		}
	


/**Given file is found and selected. Then the given file is read into the 
 *program
 */		
private class ReadButtonListener implements ActionListener
{
	//register an event listener for "Add Appt" Button
	public void actionPerformed(ActionEvent e)
	{
		String actionCommand = e.getActionCommand();

		if (actionCommand.equals("Read Text File")){
			JTextArea a = new JTextArea(20,30);
			JFileChooser chooser = new JFileChooser();

			//open file path
			int found = chooser.showDialog(null, "Open");

			//execute if execution is incorrect
			if ( found != JFileChooser.APPROVE_OPTION){
				a.setText("No File Chosen");
			} 
			else{
				try {
					File file = chooser.getSelectedFile();
					Scanner input = new Scanner (file);
					String readLine = "";
					//read file in line by line 
					while(input.hasNext()){
						readLine += input.nextLine() + "\n";
					}

					textArea.setText(readLine);

				}
				catch (FileNotFoundException e1){
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		}
	}
}

/** program terminates when action listener is implemented*/
private class ExitButtonListener implements ActionListener 
{
	//register acttion listener for "Exit" Button
	public void actionPerformed(ActionEvent e)
	{
		//get the action command
		String actionCommand = e.getActionCommand();

		if(actionCommand.equals("Exit")){
			String parentPath;

			parentPath = commonFile.getParent();
			newWordsFile = new File(parentPath+"/test.txt");
			//create test.txt file in same directory as commonwords file
			commonWords.printToFile(newWordsFile);
			//create a new commonWords dictionary with test.txt
			commonWords.create(newWordsFile);

			System.exit(0); //terminate program
		}
	}
}




/**create new common dictionary*/
public void createCommon()
{
	//JFileChooser cf = new JFileChooser();
	cf.setDialogTitle("Choose Common word dictionary file");
	cf.showOpenDialog(null);   		//show file chooser for dictionary
	commonFile = cf.getSelectedFile();
	commonWords.create(commonFile);	//create hash table of dictionary
}

/**create new gre file*/
public void createGRE()
{
	//JFileChooser cf = new JFileChooser();
	cf.setDialogTitle("Choose GRE dictionary file");
	cf.showOpenDialog(null);   		//show file chooser for dictionary
	greFile = cf.getSelectedFile();
	greWords.create(greFile);	//create hash table of dictionary
}

public static void main(String[] argrs){
	new DictionaryGuiDrive();
	//createCommon();
	//createGRE();


}

}//end of program

