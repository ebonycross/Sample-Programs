/**Assignment 4, Fall 2014: Driver Class
 * Implement and compute the records and winners of six soccer teams
 * designated soccer teams by read in a given numeric file. 
 * Computations executed from the worker class called League.
 * 
 * @author Ebony Cross
 * 
 */

import javax.swing.*; //need for Swing classes
import java.awt.*; 
import java.awt.event.*; //need for event listener interface
import java.io.*; // need for file to read in
import java.util.Scanner;

public class LeagueDriver extends JFrame{
	private final int WINDOW_WIDTH = 800; //window width
	private final int WINDOW_HEIGHT = 300; //window height
	private int[][] pts = new int[6][10]; //array passed into League constructor
	private JTextField[][] field = new JTextField[6][3]; //empty textfields
	private JLabel teamNames, totalPts, winLossTie, title; //title headers
	private JPanel centerPanel, northPanel, southPanel; //panel to hold componenets for record and winner fields
	private JButton fileButton, calcButton, champWinButton, earthWinButton, exitButton; //button for action events
	private League myLeague; //create a new reference from the League class 

	/**no-arg constructor */
	public LeagueDriver(){
		//set title of window
		setTitle("Desert Soccer League");

		//set size of window
		setSize(WINDOW_WIDTH,WINDOW_HEIGHT);

		//create title and add to north region 
		title = new JLabel("2014 Desert Soccer League Totals");
		northPanel = new JPanel();
		northPanel.add(title);

		//call methods that build the panels 
		buildScorePanel();
		buildButtonPanel();

		setLayout(new BorderLayout());

		//add panels to to content pane
		add(northPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);

		//specify an action for the close button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setVisible(true);

	}
/*----------------------------------------------------------------------------*/
	/**build a panel that components for the record board are needed
	 * to calculate records and winners
	 */
	private void buildScorePanel(){
		centerPanel = new JPanel();

		//create labels for each column
		teamNames = new JLabel("Team");
		totalPts = new JLabel("Total Points");
		winLossTie = new JLabel("Win-Loss-Tie");

		//add labels to panel
		centerPanel.setLayout(new GridLayout(7,3));
		centerPanel.add(teamNames);
		centerPanel.add(totalPts);
		centerPanel.add(winLossTie);

		//create new instances of 2d JTextField array and add to the panel
		for(int i = 0; i < field.length; i++){
			for(int col = 0; col < field[i].length; col++){
				field[i][col] = new JTextField();
				centerPanel.add(field[i][col]);
				field[i][col].setEditable(false); //turn textfield edit ability off

			}
		}
	}
/*----------------------------------------------------------------------------*/

	/**build panel for button that components for the record board are needed
	 * to calculate records and winners
	 */
	public void buildButtonPanel(){
		//create new panel reference
		southPanel = new JPanel();
		
		//create buttons and references 
		fileButton = new JButton("Read Input File"); 
		calcButton = new JButton("Calculate Points"); 
		champWinButton = new JButton("Championship Winner");
		earthWinButton = new JButton("Earth Cup Winner");
		exitButton = new JButton("Exit");

		//create action listeners for corresponding event
		fileButton.addActionListener(new FileButtonListener());
		calcButton.addActionListener(new CalcButtonListener());
		champWinButton.addActionListener(new ChampWinButtonListener());
		earthWinButton.addActionListener(new EarthWinButtonListener());
		exitButton.addActionListener(new ExitButtonListener());

		southPanel.setLayout(new FlowLayout());

		//add buttons to panel
		southPanel.add(fileButton);
		southPanel.add(calcButton);
		southPanel.add(champWinButton);
		southPanel.add(earthWinButton);
		southPanel.add(exitButton);

	}
/*----------------------------------------------------------------------------*/
	
	/**Given file is found and selected. Then the given file is read into the 
	 *program and assigned to the 2d array of ints  
	 */
	private class FileButtonListener implements ActionListener 
	{
		//register event listener for "Read Input File" button
		public void actionPerformed(ActionEvent e) 
		{

			try{
				//get the action command
				String actionCommand = e.getActionCommand();

				//create new JFileChooser to successful select file to read 
				if(actionCommand.equals("Read Input File")){
					JTextArea a = new JTextArea(20,30);
					JFileChooser chooser = new JFileChooser();

					//open file path
					int found = chooser.showDialog(null, "Open");

					//execute if execution is incorrect
					if ( found != JFileChooser.APPROVE_OPTION){
						a.setText("No File Chosen");
					} 
					else{
						File file = chooser.getSelectedFile();
						Scanner input = new Scanner (file);

						//read in file and assign numeric values to 2d array of ints
						while(input.hasNextInt()){
							for(int row = 0; row < pts.length; row++){
								for(int col = 0; col < pts[row].length; col++){
									pts[row][col] = input.nextInt();
								}
							}
						}
					}
				}

				//pass array into constructor
				myLeague = new League(pts);

				//catch for except thrown
			}catch (FileNotFoundException e1){
				e1.printStackTrace();
			}

		}
	}

/*----------------------------------------------------------------------------*/
	/**methods of League are called to implement "Calculate Points" button.
	 * values retrieved from the League methods are then assigned in the 2d 
	 * array of JTextFields 
	 */
	private class CalcButtonListener implements ActionListener 
	{
		//register event listener for "Calculate Points" button
		public void actionPerformed(ActionEvent e)
		{
			//get the action command
			String actionCommand = e.getActionCommand();

			if(actionCommand.equals("Calculate Points")){
				String name;
				
				//get names of the teams
				for(int index = 0; index < pts.length; index++){
					name = myLeague.getNames(index);
					field[index][0].setText(name);
				}

				//get total score for each team
				for(int index = 0; index < pts.length; index++){
					int number = (myLeague.getTotal(index));
					field[index][1].setText(number + "");
				}

				//get String of record of wins, losses and ties
				for(int index = 0; index < pts.length; index++){
					myLeague.getWin(index);
					myLeague.getLoss(index);
					myLeague.getTie(index);
					
					String totalScore = (myLeague.getRecords());
					field[index][2].setText(totalScore);
				}
			}

		}
	}
/*----------------------------------------------------------------------------*/	
	
	/**call method from League class to display computation of Championship
	 * winner
	 */
	private class ChampWinButtonListener implements ActionListener 
	{
		//register event listener for "Championship Winner" Button
		public void actionPerformed(ActionEvent e)
		{
			//get the action command
			String actionCommand = e.getActionCommand();

			//display results of method called
			if(actionCommand.equals("Championship Winner")){
				JOptionPane.showMessageDialog(null,"The Championship Winner is " + 
						(myLeague.championshipWinner()));
			}
		}
		
	}
/*----------------------------------------------------------------------------*/
	
	/**call method from League class to display computation of Earth Cup 
	 * winner
	 */
	private class EarthWinButtonListener implements ActionListener 
	{
		//register event listener for "Earth Cup Winner" Button
		public void actionPerformed(ActionEvent e)
		{
			//get the action command
			String actionCommand = e.getActionCommand();

			//display results of method called
			if(actionCommand.equals("Earth Cup Winner")){
				JOptionPane.showMessageDialog(null,"The Earth Cup Winner is " + 
						(myLeague.earthWinner()));
			}
		}
		
	}
/*----------------------------------------------------------------------------*/
	
	/** program terminates when action listener is implemented*/
	private class ExitButtonListener implements ActionListener 
	{
		//register acttion listener for "Exit" Button
		public void actionPerformed(ActionEvent e)
		{
			//get the action command
			String actionCommand = e.getActionCommand();

			if(actionCommand.equals("Exit")){
				System.exit(0); //terminate program
			}
		}
	}
/*----------------------------------------------------------------------------*/
	
	public static void main(String[] args){

		LeagueDriver myGui = new LeagueDriver();


	}
}
