/**Assignment 6, Fall 2014: Driver Class
 * Implement and compute the votes summaries for teams
 * File read in is chosen by the user. 
 * Computations executed from the Data Manager class called CollegeFootballPlayoffManager.
 * 
 * @author Ebony Cross
 * 
 */
import javax.swing.*;
import java.awt.*; 
import java.awt.event.*; //need for event listener interface
import java.io.*; // need for file to read/write in
import java.util.ArrayList;
import java.util.Scanner;

public class CFBTGuiDriver extends JFrame {
	private final int WINDOW_WIDTH = 800; //window width
	private final int WINDOW_HEIGHT = 800; //window height
	//private JTextField[][] field = new JTextField[12][10]; //empty textfields
	private JLabel[][] teamNames = new JLabel[2][10]; //labels for conference and team names
	private JTextField[] rankScore = new JTextField[10]; //fields to hold scores for rankings
	private JTextField[][] score = new JTextField[12][10];//fields to display all votes
	private JPanel buttonPanel,scorePanel, rankPanel, teamNamePanel;  //panel to hold components 
	private JButton readVotes, rank, details, exit; //create button references
	private JPanel labelPanel; //create panel for JLabel
	private CollegeFootballPlayoffManager cteam = new CollegeFootballPlayoffManager(); //create object of CRPM
	private ArrayList<CollegeFootballTeam> list = new ArrayList<CollegeFootballTeam>(); //create ArrayLis of CFT

	/**no-arg constructor */
	public CFBTGuiDriver(){
		//set title of window
		setTitle("College Football Playoff Selection");

		//set size of window
		setSize(WINDOW_WIDTH,WINDOW_HEIGHT);

		//create title and add to north region 
		labelPanel = new JPanel();
		JLabel title = new JLabel("Low Score is High Ranking");
		labelPanel.add(title);

		//call methods that build the panels
		buildButtonPanel();
		buildTeamNamePanel();
		buildRankPanel();
		buildScorePanel();
		

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4,1));
		panel.add(buttonPanel);
		panel.add(teamNamePanel);
		panel.add(rankPanel);
		panel.add(labelPanel);
		
		
		labelPanel.setVisible(false);
		setLayout(new BorderLayout());
		add(panel, BorderLayout.NORTH);
		add(scorePanel, BorderLayout.CENTER);
		

		JOptionPane.showMessageDialog(null, "Select Read Votes to begin...");

		//specify an action for the close button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}
	/**build panel for button that components for the displays that are needed
	 * to show the team name, conference and total votes 
	 */
	public void buildButtonPanel(){
		//create new panel reference
		buttonPanel = new JPanel();

		//create buttons and references
		readVotes = new JButton("Read Votes");
		readVotes.setEnabled(true);
		rank = new JButton("Rank Teams");
		rank.setEnabled(false);
		details = new JButton("Show Details");
		details.setEnabled(false);
		exit = new JButton("Exit");
		exit.setEnabled(true);

		//create action listeners for corresponding event
		readVotes.addActionListener(new ReadButtonListener());
		rank.addActionListener(new RankButtonListener());
		details.addActionListener(new ShowButtonListener());
		exit.addActionListener(new ExitButtonListener());


		//set new layout for button panel
		buttonPanel.setLayout(new FlowLayout());

		//add buttons to panel
		buttonPanel.add(readVotes);
		buttonPanel.add(rank);
		buttonPanel.add(details);
		buttonPanel.add(exit);
	}



	/**build panel for components for the displays that are needed
	 * to show the team name, conference and total votes 
	 */
	public void buildTeamNamePanel(){
		//create new panel reference
		teamNamePanel = new JPanel();
		teamNamePanel.setLayout(new GridLayout(2,10));

		//create new instances of 2d JTextField array and add to the panel
		for(int row = 0; row < teamNames.length; row++){
			for(int col = 0; col < teamNames[row].length; col++){
				teamNames[row][col] = new JLabel();
				teamNamePanel.add(teamNames[row][col]);
			}	
		}
		teamNamePanel.setVisible(false);
	}

	/**build panel for components for the displays rank of team determined by
	 * sum of the total votes of a given team 
	 */
	public void buildRankPanel(){
		//create new panel reference
		rankPanel = new JPanel();
		rankPanel.setLayout(new GridLayout(1,10));

		//create new instances of 1d JTextField array and add to the panel
		for(int col = 0; col < 10; col++){
			rankScore[col] = new JTextField();
			rankPanel.add(rankScore[col]);	
		}
		
		rankPanel.setVisible(false);
	}

	/**build panel for components for the displays all scores of team*/
	public void buildScorePanel(){
		//create new panel reference
		scorePanel = new JPanel();
		scorePanel.setLayout(new GridLayout(12,10));

		//create new instances of 2d JTextField array and add to the panel
		for(int row = 0; row < score.length; row++){
			for(int col = 0; col < score[row].length; col++){
				score[row][col] = new JTextField();
				scorePanel.add(score[row][col]);
			}	
		}
		scorePanel.setVisible(false);
	}


	/**Given file is found and selected. Then the given file is read into the 
	 *program and assigned to the 2d array 
	 */
	private class ReadButtonListener implements ActionListener{
		//register event listener for "Read Votes" button
		public void actionPerformed(ActionEvent e) 
		{

			try{
				//get the action command
				String actionCommand = e.getActionCommand();

				//create new JFileChooser to successful select file to read 
				if(actionCommand.equals("Read Votes")){
					teamNamePanel.setVisible(false);
					rankPanel.setVisible(false);
					scorePanel.setVisible(false);
					labelPanel.setVisible(false);
					details.setEnabled(false);
					rank.setEnabled(false);
					
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
						cteam.readVotes(file);
						rank.setEnabled(true);
					}
				}
			}
			catch (FileNotFoundException e1){
				e1.printStackTrace();
			}
		}

	}

	/**methods of CollegeFootballPlayoffManager are called to implement "Rank Teams" button.
	 * values retrieved from the CFPManager using inherited methods are then assigned in the 2d 
	 * array of JTextFields 
	 */
	private class RankButtonListener implements ActionListener{
		//register event listener for "Rank team" button

		public void actionPerformed(ActionEvent e) 
		{
			//get the action command
			String actionCommand = e.getActionCommand();
			if(actionCommand.equals("Rank Teams")){
				//change visibility of appropriate components
				list =cteam.rankTeams();
				
				//change visibility of appropriate components
				teamNamePanel.setVisible(false);
				rankPanel.setVisible(false);
				scorePanel.setVisible(false);
				labelPanel.setVisible(false);
				readVotes.setEnabled(false);
				
				//change visibility of appropriate components
				teamNamePanel.setVisible(true);
				labelPanel.setVisible(true);
				rankPanel.setVisible(true);

				//set textfields for names (1 team per col)
				for(int col = 0; col < 10; col++){
					for(int row = 0; row < 2; row+=2){
						String tNam = list.get(col).getName();	
						teamNames[row][col].setText(tNam);
						String cNam = list.get(col).getConference();
						teamNames[row+1][col].setText(cNam);
					}
				}

				//set textfield for sum of votes (1 team per col, corresponds to appopiate team)
				for(int index = 0; index < 10; index++){
					rankScore[index].setText(list.get(index).getSumOfVotes() + "");	
					rankScore[index].setEditable(false);
				}
				//change visibility of appropriate components
				details.setEnabled(true);
			}
		}
	}

	/**Given file is found and selected. Then the given file is read into the 
	 *program and assigned to the 2d array 
	 */
	private class ShowButtonListener implements ActionListener{
		//register event listener for "Show Details" button
		public void actionPerformed(ActionEvent e) 
		{
				//get the action command
				String actionCommand = e.getActionCommand();
				int[] a;
				//create new JFileChooser to successful select file to read 
				if(actionCommand.equals("Show Details")){
					//change visibility of appropriate components
					teamNamePanel.setVisible(false);
					rankPanel.setVisible(false);
					scorePanel.setVisible(false);
					labelPanel.setVisible(false);
					readVotes.setEnabled(false);
					rank.setEnabled(false);
					
					//call ArrayList into rank Teams methods
					list =cteam.rankTeams();
					
					//change visibility of appropriate components
					teamNamePanel.setVisible(true);
					scorePanel.setVisible(true);
					rankPanel.setVisible(false);
					
					for(int col = 0; col < 10; col++){
						for(int row = 0; row < 12; row++){
							//System.out.println(list.get(0).getVotes() + " hello");
							a=list.get(col).getVotes();
							score[row][col].setText(a[row] + "");
							score[row][col].setEditable(false);
						}
					}
					//change visibility of appropriate components
					readVotes.setEnabled(true);
					rank.setEnabled(false);
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
				System.exit(0); //terminate program
			}
		}
	}
	

	public static void main(String[] args){
		CFBTGuiDriver myGui = new CFBTGuiDriver();
	}
}
