/**Assignment 1, Spring 2015: Driver Class
 * Implement and compute the appointments
 * File read in is chosen by the user. 
 * Computations executed from the Data Manager class called Appointments.
 * @author Ebony Cross
 * 
 */
import javax.swing.*; //need for Swing classes
import java.awt.*; 
import java.awt.event.*; //need for event listener interface
import java.io.*; // need for file to read/write in
import java.util.GregorianCalendar;
import java.util.Scanner;

public class AppointmentGUIDriver extends JFrame {

	private final int WINDOW_WIDTH = 300; //window width
	private final int WINDOW_HEIGHT = 300; //window height
	private JLabel line1, line2, line3, hr, Minute; //create labels for teams
	private JTextField dayTextField, monthTextField, describe,yearTextField, 
	hourTextField, MinTextField; //create textfields for team entry
	private JRadioButton onceRadio, weekRadio, dailyRadio; //create position buttons
	private JButton addApptButton, readButton, displayButton, exitButton; //create event listener buttons
	private JPanel panelLine1, panel1, panel2, lowerButtonPanel, apptTimePanel,apptsBook, apptDatePanel,
	radButtonPanel, radButtonPanel2, panelLine3, MinPanel, hourPanel; //create panels
	private Appointments apptsObj = new Appointments();; // create instance variable of CurlerTeam type
	private int day, month, year, hour, minute;
	private boolean success = false;
	private String[] tokens;
	private int numOfDigits = 0;
	private String d = "null";
	private int num;

	/**no-arg constructor */
	public AppointmentGUIDriver(){
		//set title of window

		setTitle("Appointment Book");

		//call methods
		buildTypePanel();
		buildButtonPanel();

		//set size of window
		setSize(WINDOW_WIDTH,WINDOW_HEIGHT);

		//set layout manager
		setLayout(new BorderLayout());
		add(panel1, BorderLayout.CENTER);
		add(panel2, BorderLayout.SOUTH);

		//specify an action for the close button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pack();
		setVisible(true);
	}



	/** build a panel that components for the variables needed to 
	 */
	public void buildTypePanel(){
		//create new panel
		panelLine1 = new JPanel(); 
		JPanel panelLine2 = new JPanel();
		panelLine3 = new JPanel();
		hourPanel = new JPanel();
		MinPanel = new JPanel();
		apptDatePanel = new JPanel();
		apptTimePanel = new JPanel();

		//create labels and textfields for appt time
		hr = new JLabel("Hour");
		hr.setFont(new Font("SansSerif", Font.BOLD, 12));
		Minute = new JLabel("Minute");
		Minute.setFont(new Font("SansSerif", Font.BOLD, 12));
		hourTextField = new JTextField(10);
		MinTextField = new JTextField(10); 


		//create labels and textfields for appt date
		line1 = new JLabel("Day");
		line1.setFont(new Font("SansSerif", Font.BOLD, 12));
		line2 = new JLabel("Month");
		line2.setFont(new Font("SansSerif", Font.BOLD, 12));
		line3 = new JLabel("Year");
		line3.setFont(new Font("SansSerif", Font.BOLD, 12));
		dayTextField = new JTextField(10);
		monthTextField = new JTextField(10); 
		yearTextField = new JTextField(10);

		//set layout manager
		panelLine1.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelLine2.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelLine3.setLayout(new FlowLayout(FlowLayout.LEFT));
		hourPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		MinPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		//add to panel to appt time
		hourPanel.add(hr);
		hourPanel.add(hourTextField);
		MinPanel.add(Minute);
		MinPanel.add(MinTextField);

		//add to panel to appt date
		panelLine1.add(line1);
		panelLine1.add(dayTextField); 
		panelLine2.add(line2);
		panelLine2.add(monthTextField); 
		panelLine3.add(line3);
		panelLine3.add(yearTextField);

		//create new nested for appt time
		apptTimePanel.setBorder(BorderFactory.createTitledBorder("Appointment Time"));
		apptTimePanel.setLayout(new GridLayout(2,2));
		apptTimePanel.add(hourPanel);
		apptTimePanel.add(MinPanel);

		//create new panel for appt dates
		apptDatePanel.setBorder(BorderFactory.createTitledBorder("Appointment Date"));
		apptDatePanel.setLayout(new GridLayout(3,2));
		apptDatePanel.add(panelLine1);
		apptDatePanel.add(panelLine2);
		apptDatePanel.add(panelLine3);


		//create new panel
		radButtonPanel = new JPanel();
		radButtonPanel2 = new JPanel();
		JPanel radPanel3 = new JPanel();

		//create new radiobuttons
		onceRadio = new JRadioButton("One Time", true);
		onceRadio.setToolTipText("Select Button or press O for one time Appointment");
		onceRadio.setMnemonic(KeyEvent.VK_O);
		dailyRadio = new JRadioButton("Daily");
		dailyRadio.setToolTipText("Select Button or press D for daily Appointment");
		dailyRadio.setMnemonic(KeyEvent.VK_D);
		weekRadio = new JRadioButton("Weekly");
		weekRadio.setToolTipText("Select Button or press W for weekly Appointment");
		weekRadio.setMnemonic(KeyEvent.VK_W);

		//create a ButtonGroup object
		ButtonGroup group = new ButtonGroup();

		//Add the radio buttons to the ButtonGroup Object
		group.add(onceRadio);
		group.add(dailyRadio);
		group.add(weekRadio);

		radPanel3.setBorder(BorderFactory.createTitledBorder("Appointment Type"));

		//create action listeners for radio buttons
		onceRadio.addActionListener(new addApptButtonListener());
		dailyRadio.addActionListener(new addApptButtonListener());
		weekRadio.addActionListener(new addApptButtonListener());

		//add buttons to panel
		radButtonPanel.setLayout(new FlowLayout());
		radButtonPanel.add(onceRadio);
		radButtonPanel.add(dailyRadio);
		radButtonPanel.add(weekRadio);

		radPanel3.setLayout(new GridLayout(3,3));
		radPanel3.add(radButtonPanel);
		radPanel3.add(radButtonPanel2);


		//create nested panel
		apptsBook = new JPanel();
		apptsBook.setLayout(new GridLayout(1,2));
		apptsBook.add(apptDatePanel);
		apptsBook.add(apptTimePanel);
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2,1));
		panel1.add(radPanel3);
		panel1.add(apptsBook);
	}

	/*
		/** build panels that components hold the variables for the buttons needed to 
	 */
	public void buildButtonPanel(){
		//create nested panel
		panel2 = new JPanel();
		lowerButtonPanel = new JPanel();
		JPanel bottomButtonPanel = new JPanel();

		//create new buttons
		addApptButton = new JButton("Add Appt");
		addApptButton.setToolTipText("Click here or press A to add appointment");
		addApptButton.setMnemonic(KeyEvent.VK_A);
		displayButton = new JButton("Display Appts");
		displayButton.setToolTipText("Click here or press S to display todays appointments");
		displayButton.setMnemonic(KeyEvent.VK_S);
		readButton= new JButton("ReadFile");
		readButton.setToolTipText("Click here or press R to read in file");
		readButton.setMnemonic(KeyEvent.VK_R);
		exitButton = new JButton("Exit");
		exitButton.setToolTipText("Click here or press E to exit");
		exitButton.setMnemonic(KeyEvent.VK_E);

		//create action listeners for JButtons
		addApptButton.addActionListener(new addApptButtonListener());
		displayButton.addActionListener(new DisplayButtonListener());
		readButton.addActionListener(new ReadButtonListener());
		exitButton.addActionListener(new ExitButtonListener());



		//add buttons to panels
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(2,1));
		describe = new JTextField(30);
		JLabel desc = new JLabel("Appt Description");
		topPanel.add(desc);
		topPanel.add(describe);
		lowerButtonPanel.add(addApptButton);
		lowerButtonPanel.add(displayButton);
		lowerButtonPanel.add(readButton);
		bottomButtonPanel.add(exitButton);


		//set manager layout
		panel2.setLayout(new GridLayout(3,1));

		//nested panels
		panel2.add(topPanel);
		panel2.add(lowerButtonPanel);
		panel2.add(bottomButtonPanel);
	}


	/** textfields have values that are passed to  EmployeesManager class via the addEmployee method
	 * when action listener is implemented
	 */
	private class addApptButtonListener implements ActionListener
	{
		//register an event listener for "Add Appt" Button
		public void actionPerformed(ActionEvent e)
		{
			String actionCommand = e.getActionCommand();

			if (actionCommand.equals("Add Appt")){
				//apptsObj = new Appointments();
				success = false;
				while(success == false){
					try{

						//get the action command
						//retrieve input for day
						String firstLine = dayTextField.getText();
						if(dayTextField.getText().isEmpty()){
							dayTextField.setText("0");
						}else{
							day = Integer.parseInt(firstLine);
						}

						//retrieve input for month
						String secLine = monthTextField.getText();
						if(monthTextField.getText().isEmpty()){
							monthTextField.setText("0");
						}else{
							month = Integer.parseInt(secLine);
						}

						//retreive input of year
						String thirdLine = yearTextField.getText();
						if(yearTextField.getText().isEmpty()){
							yearTextField.setText("0");
						}else{
							year = Integer.parseInt(thirdLine);
						}

						//retrieve input of hour
						String hourLine = hourTextField.getText();
						if(hourTextField.getText().isEmpty()){
							hourTextField.setText("0");
						}else{
							hour = Integer.parseInt(hourLine);
						}

						//retrieve input of minute
						String minuteLine = MinTextField.getText();
						if(MinTextField.getText().isEmpty()){
							MinTextField.setText("0");
						}else{
							minute = Integer.parseInt(minuteLine);
						}

						//retrieve input of description

						d = describe.getText();
						if(describe.getText().isEmpty()){
							describe.setText("null");
						}

						char type = '1';
						GregorianCalendar date = null;


						String addResult = "";


						if (onceRadio.isSelected()){
							type = 'O';
							date = new GregorianCalendar(year, month-1, day, hour, minute);
						}


						else if (weekRadio.isSelected()){
							type = 'W';
							date = new GregorianCalendar(year, month-1, day, hour, minute);
						}

						else if (dailyRadio.isSelected()){
							type = 'D';
							date = new GregorianCalendar(year, (month-1), day, hour, minute);
						}
						Validate(day, month, year, hour, minute);
						success = true;

						apptsObj.addAppt(type, date, d);

						//addResult = " " + apptsObj.displayAppt(day, month-1, year);
						if (!(addResult == null)){
							JOptionPane.showMessageDialog(null,  addResult, "Success", JOptionPane.PLAIN_MESSAGE);
						}

					}
					catch(IllegalNumFormatException e1){
						String input;

						JOptionPane.showMessageDialog(null, e1.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
						
						//check 4 digits for year
						if(num ==1){
							input =JOptionPane.showInputDialog("Enter valid year");
							yearTextField.setText(input);
						}
						
						//check day
						else if(num == 2){
							input =JOptionPane.showInputDialog("Enter valid day");
							dayTextField.setText(input);
						}
						//check day
						else if(num == 3){
							input =JOptionPane.showInputDialog("Enter valid day");
							dayTextField.setText(input);
						}
						//check month
						else if(num == 4){
							input =JOptionPane.showInputDialog("Enter valid month");
							monthTextField.setText(input);
						}
						//check number of date for month with 30 days
						else if(num ==  5){
							input =JOptionPane.showInputDialog("Enter valid day");
							dayTextField.setText(input);
						}

						//check if year leap 
						else if(num == 6){
							input =JOptionPane.showInputDialog("Enter valid day");
							dayTextField.setText(input);
							String in =JOptionPane.showInputDialog("Enter valid year");
							yearTextField.setText(in);
						}
					
					//check if valid february date 
					else if(num == 7){
						input =JOptionPane.showInputDialog("Enter valid day");
						dayTextField.setText(input);
					}
					//check hour
					else if(num == 8){
						input =JOptionPane.showInputDialog("Enter valid hour");
						hourTextField.setText(input);
					}
					//check for valid minutes entered
					else if(num == 9){
						input =JOptionPane.showInputDialog("Enter valid minute");
						MinTextField.setText(input);
					}
				}




			}
			MinTextField.setText("");
			hourTextField.setText("");
			describe.setText("");
		}
	}
}



/**Given file is found and selected. Then the given file is read into the 
 *program
 */
private class ReadButtonListener implements ActionListener{
	//register event listener for "Read Votes" button
	public void actionPerformed(ActionEvent e) 
	{
		//get the action command
		String actionCommand = e.getActionCommand();

		//create new JFileChooser to successful select file to read 
		if(actionCommand.equals("ReadFile")){
			//apptsObj = new Appointments();
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
					int day = 0;
					int month = 0;
					int year = 0;
					int hour = 0;
					int minute = 0;

					//read file in line by line 
					while(input.hasNext()){
						String readLine = input.nextLine();	
						tokens = readLine.split("[ ]+");
						String d = "";
						char t = 0;

						for(int i = 0; i < tokens.length; i++){
							//retrieve type 
							if(i == 0){
								t = tokens[i].charAt(i);
							}

							//retrieve month
							else if(i == 1){
								month = Integer.parseInt(tokens[i]);
							}
							//retrieve day
							else if(i == 2){
								day = Integer.parseInt(tokens[i]);
							}
							//retrieve year 
							else if(i == 3){
								year = Integer.parseInt(tokens[i]);
							}
							//retrieve hour
							else if(i == 4){
								hour = Integer.parseInt(tokens[i]);
							}
							//retrieve minute 
							else if(i == 5){
								minute = Integer.parseInt(tokens[i]);
							}
							//retrieve description
							else if(i >5){
								d += tokens[i] + " ";
							}

						}
						//add appt obj
						apptsObj.addAppt(t, new GregorianCalendar(year, month-1, day, hour, minute), d);

					}

					input.close();
				}

				catch (FileNotFoundException e1){
					e1.printStackTrace();
				}
			}

		}
	}

}


/**display team name, players first and last name and corresponding team positions*/
private class DisplayButtonListener implements ActionListener 
{
	//register an event listener for "Add Employee" Button
	public void actionPerformed(ActionEvent e)
	{
		//get the action command
		String actionCommand = e.getActionCommand();

		if(actionCommand.equals("Display Appts")){
			//JOptionPane.showMessageDialog(null, "show " + apptsObj.GetAppointmentCount());
			String firstLine = dayTextField.getText();
			int day = Integer.parseInt(firstLine);

			//retrieve input for month
			String secLine = monthTextField.getText();
			int month = Integer.parseInt(secLine);

			//retreive input of year
			String thirdLine = yearTextField.getText();
			int year = Integer.parseInt(thirdLine);

			JOptionPane.showMessageDialog(null, apptsObj.displayAppt(day, month-1, year), "Today's Appointments", JOptionPane.PLAIN_MESSAGE);

			JOptionPane.showMessageDialog(null, apptsObj.toString(), "Appointment Book", JOptionPane.PLAIN_MESSAGE);
			dayTextField.setText("");
			monthTextField.setText("");
			yearTextField.setText("");


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

/**
 * method creates a exception if data input is invalid
 * @param day date of the month
 * @param month month in the year from input data
 * @param year year from input data
 * @param hour data input
 * @param minute dat input
 * @throws IllegalNumFormatException formatting exception
 */
public void Validate(int day, int month, int year, int hour, int minute) throws IllegalNumFormatException{
	month = month -1;
	numOfDigits = 0;

	int number = year;
	//computes the number of digits inputted for year
	do
	{
		number = number / 10;
		numOfDigits++;
	} while (number > 0);


	//check number of digit, must have 4 
	if(!(numOfDigits == 4)){
		num = 1;
		throw new IllegalNumFormatException("Year must be a four-digit number");
	}
	//check number of day
	else if(day > 31){
		num = 2;
		throw new IllegalNumFormatException("day is greater than 31");
	}
	//check number of day
	else if(day < 1){
		num = 3;
		throw new IllegalNumFormatException("day is less than 1");
	}
	//check number of month
	else if((month < 0) || (month > 11)){
		num = 4;
		throw new IllegalNumFormatException("Month entered must be between 1 to 12");
	}
	//check number of month with 30 
	else if(((month == 1) || (month == 3) || (month == 5) ||
			(month == 8) || (month == 10)) && (day == 31)){
		num = 5;
		throw new IllegalNumFormatException("day must be less than 31");
	}
	//check if year leap 
	else if((month == 1) && (day == 29)) {
		if(!((year % 400 == 0) || (year % 4 == 0))){
			num = 6;
			throw new IllegalNumFormatException("This is not a leap, enter a day "
					+ "between 0 to 28 for month of February and valid year");	
		}
	}
	//check if valid february date 
	else if((month == 1) && (day == 30)){
		num = 7;
		throw new IllegalNumFormatException("day is greater than 30, invalid data for February");
	}
	//check hour
	else if((hour < 0) || (hour > 23)){
		num = 8;
		throw new IllegalNumFormatException("hour entered must be between 0 to 23");
	}
	//check for valid minutes entered
	else if((minute < 0) || (minute > 59)){
		num = 9;
		throw new IllegalNumFormatException("minute entered must be between 0 to 59");
	}
}

//display gui in main method
public static void main(String[]args){
	new AppointmentGUIDriver();
}
}






