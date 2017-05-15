/**Assignment 5, Fall 2014: Driver Class
 * Implement and compute the curler players and their teams
 * designated curler teams are written to a file. 
 * Computations executed from the Data Manager class called CurlerTeam.
 * 
 * @author Ebony Cross
 * 
 */
import javax.swing.*; //need for Swing classes
import java.awt.*; 
import java.awt.event.*; //need for event listener interface
import java.io.*; // need for file to read/write in
import java.util.Scanner;


public class GuiCurlerTeam extends JFrame {
	private final int WINDOW_WIDTH = 500; //window width
	private final int WINDOW_HEIGHT = 700; //window height
	private JLabel title, country, firstNam, lastNam, pos; //create labels for teams
	private JTextField countryTextField, firstNamTextField, 
	lastNamTextField, posTextField; //create textfields for team entry
	private JRadioButton throwerRadio, sweeperRadio, skipRadio; //create position buttons
	private JButton addPlayerButton, newTeamButton, printTeamButton, saveTeamButton, exitButton; //create event listener buttons
	private JPanel titlePanel, teamPanel, posPanel,panel1, panel2, lowerButtonPanel, upperButtonPanel, radButtonPanel; //create panels
	private ImageIcon image; //allows image to be imported
	private CurlerTeam cTeam; // create instance variable of CurlerTeam type

	/**no-arg constructor */
	public GuiCurlerTeam(){
		//set title of window

		setTitle("Create Curler Team");

		//call methods
		buildTeamPanel();
		buildNorthPanel();
		buildButtonPanel();

		//set size of window
		setSize(WINDOW_WIDTH,WINDOW_HEIGHT);

		//set layout manager
		setLayout(new BorderLayout());
		add(panel1, BorderLayout.CENTER);
		add(titlePanel, BorderLayout.NORTH);
		add(panel2, BorderLayout.SOUTH);

		//specify an action for the close button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pack();
		setVisible(true);
	}

	/**build a panel that components are used for the header of the gui component
	 * 
	 */
	public void buildNorthPanel(){
		titlePanel = new JPanel();

		//create labels and import image
		image = new ImageIcon(getClass().getResource("PyeongchangSmall.jpg"));
		title = new JLabel("Curler Teams", image, SwingConstants.LEFT);
		title.setFont(new Font("SansSerif", Font.BOLD, 30));

		//titlePanel.setLayout(new GridLayout(1,2));
		titlePanel.setPreferredSize(new Dimension(500,150));
		titlePanel.add(title);
	}

	/** build a panel that components for the variables needed to 
	 * create the curler teams and players
	 */
	public void buildTeamPanel(){
		//create new panel
		teamPanel = new JPanel(); 
		JPanel teamPanel1 = new JPanel();
		JPanel teamPanel2 = new JPanel();

		//create labels and textfields
		country = new JLabel("Country:");
		country.setFont(new Font("SansSerif", Font.BOLD, 18));
		firstNam = new JLabel("First Name:");
		firstNam.setFont(new Font("SansSerif", Font.BOLD, 18));
		lastNam = new JLabel("Last Name:");
		lastNam.setFont(new Font("SansSerif", Font.BOLD, 18));
		countryTextField = new JTextField(20);
		firstNamTextField = new JTextField(15); 
		lastNamTextField = new JTextField(20);

		//set layout manager
		teamPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		teamPanel1.setLayout(new FlowLayout(FlowLayout.LEFT));
		teamPanel2.setLayout(new FlowLayout(FlowLayout.LEFT));
		//teamPanel.setLayout(new GridLayout(3,1));
		
		
		//add to panel
		teamPanel.add(country);
		teamPanel.add(countryTextField); 
		teamPanel1.add(firstNam);
		teamPanel1.add(firstNamTextField); 
		teamPanel2.add(lastNam);
		teamPanel2.add(lastNamTextField); 

		//create new panel
		radButtonPanel = new JPanel();

		//create new radiobuttons
		throwerRadio = new JRadioButton("thrower", true);
		//throwerRadio.setSelected(true);
		sweeperRadio = new JRadioButton("sweeper");
		skipRadio = new JRadioButton("skip");
		
		//create a ButtonGroup object
		ButtonGroup group = new ButtonGroup();
		
		//Add the radio buttons to the ButtonGroup Object
		group.add(throwerRadio);
		group.add(sweeperRadio);
		group.add(skipRadio);

		radButtonPanel.setBorder(BorderFactory.createTitledBorder("Position"));

		//create action listeners for radio buttons
		throwerRadio.addActionListener(new AddPlayerButtonListener());
		sweeperRadio.addActionListener(new AddPlayerButtonListener());
		skipRadio.addActionListener(new AddPlayerButtonListener());

		//add buttons to panel
		radButtonPanel.setLayout(new FlowLayout());
		radButtonPanel.add(throwerRadio);
		radButtonPanel.add(sweeperRadio);
		radButtonPanel.add(skipRadio);

		//create nested panel
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(4,1));
		panel1.add(teamPanel);
		panel1.add(teamPanel1);
		panel1.add(teamPanel2);
		panel1.add(radButtonPanel);

	}


	/** build panels that components hold the variables for the buttons needed to 
	 * create the curler teams and players
	 */
	public void buildButtonPanel(){
		//create nested panel
		panel2 = new JPanel();
		upperButtonPanel = new JPanel();
		lowerButtonPanel = new JPanel();

		//create new buttons
		addPlayerButton = new JButton("Add Player");
		newTeamButton= new JButton("New Team");
		printTeamButton = new JButton("Print Team");
		saveTeamButton = new JButton("Save Team");
		exitButton = new JButton("Exit");

		//create action listeners for JButtons
		newTeamButton.addActionListener(new newTeamButtonListener());
		addPlayerButton.addActionListener(new AddPlayerButtonListener());
		saveTeamButton.addActionListener(new SaveTeamButtonListener());
		printTeamButton.addActionListener(new PrintTeamButtonListener());
		exitButton.addActionListener(new ExitButtonListener());


		//add buttons to panels
		upperButtonPanel.add(addPlayerButton);
		upperButtonPanel.add(printTeamButton);
		lowerButtonPanel.add(saveTeamButton);
		lowerButtonPanel.add(newTeamButton);
		lowerButtonPanel.add(exitButton);

		//set manager layout
		panel2.setLayout(new GridLayout(2,1));

		//nested panels
		panel2.add(upperButtonPanel);
		panel2.add(lowerButtonPanel);
	}


	/** textfields have values that are passed to  CurlerTeam class via a constructor
	 * when action listener is implemented
	 */
	private class newTeamButtonListener implements ActionListener 
	{
		//register an event listener for "new team" Button
		public void actionPerformed(ActionEvent e)
		{
			//get the action command
			String actionCommand = e.getActionCommand();
			String input;
			if (actionCommand.equals("New Team")){
				input =JOptionPane.showInputDialog("What country does this team play for?");
				countryTextField.setText(input);
				countryTextField.setEditable(false);
				cTeam = new CurlerTeam(input);
			}
		}
	}


	/** textfields have values that are passed to  CurlerTeam class via the addPlayer method
	 * when action listener is implemented
	 */
	private class AddPlayerButtonListener implements ActionListener
	{
		//register an event listener for "Add Player" Button
		public void actionPerformed(ActionEvent e)
		{
			String actionCommand = e.getActionCommand();
			
			if (actionCommand.equals("Add Player")){
			//get the action command
			String LName = lastNamTextField.getText();
			String FName = firstNamTextField.getText();
			String tempPosition;
			String addResult;

			if (throwerRadio.isSelected())
				tempPosition = "thrower";
			else if (sweeperRadio.isSelected())
				tempPosition = "sweeper";
			else
				tempPosition = "skip";

			addResult = cTeam.addPlayer(FName, LName, tempPosition);
			if (addResult != null)
			{
				JOptionPane.showMessageDialog(null,  addResult, "Error", JOptionPane.ERROR_MESSAGE);	
			}
			else
				JOptionPane.showMessageDialog(null,  "Player added", "Success", JOptionPane.PLAIN_MESSAGE);
		}
	}
	}
	
	/**data is save to file when action listener is implemented*/
	private class SaveTeamButtonListener implements ActionListener 
	{
		//register an event listener for "Add Player" Button
		public void actionPerformed(ActionEvent e)
		{
			try{
				//get the action command
				String actionCommand = e.getActionCommand();
				if(actionCommand.equals("Save Team")){
					JFileChooser chooser = new JFileChooser();

					//open file path
					int found = chooser.showDialog(null, "Save");

					//execute if execution is incorrect
					if ( found == JFileChooser.APPROVE_OPTION){
						File file = chooser.getSelectedFile();
						PrintWriter outputFile = new PrintWriter(file);
						outputFile.println(cTeam.printTeam());
						outputFile.close();
					}
				}
			}
			//catch exception
			catch (FileNotFoundException e1){
				e1.printStackTrace();
			}
		}
	}

	/**display team name, players first and last name and corresponding team positions*/
	private class PrintTeamButtonListener implements ActionListener 
	{
		//register an event listener for "Add Player" Button
		public void actionPerformed(ActionEvent e)
		{
			//get the action command
			String actionCommand = e.getActionCommand();

			if(actionCommand.equals("Print Team")){
				JOptionPane.showMessageDialog(null, cTeam.printTeam(), "Team Roaster", JOptionPane.PLAIN_MESSAGE);
			}
		}
	}

	/** program terminates when action listener is implemented
	 */
	private class ExitButtonListener implements ActionListener 
	{
		//register an event listener for "Add Player" Button
		public void actionPerformed(ActionEvent e)
		{
			//get the action command
			String actionCommand = e.getActionCommand();

			if(actionCommand.equals("Exit")){
				System.exit(0);
			}
		}
	}

	public static void main(String[]args){
		new GuiCurlerTeam();
	}
}
