
import javax.swing.*; //need for Swing classes
import javax.swing.table.DefaultTableModel;

import java.awt.*; 
import java.awt.event.*; //need for event listener interface
import java.io.*; // need for file to read/write in
import java.util.Scanner;


/**Assignment 3, Spring 2015: Driver Class
 * Implement and compute the appointments
 * File read in is chosen by the user. 
 * Computations executed from the Data Manager class called Appointments.
 * @author Ebony Cross
 * 
 */
public class ServiceGui extends JFrame {

	private final int WINDOW_WIDTH = 300; //window width
	private final int WINDOW_HEIGHT = 300; //window height
	private JLabel orderLine1, ownerLine2, makeLine3, modelLine4; //create labels for teams
	private JTextField orderTextField, ownerTextField, describe,makeTextField, 
	modelTextField; //create textfields for team entry
	private JRadioButton orderRadio, makeRadio, ownerRadio; //create position buttons
	private JButton deleteButton, addApptButton, readButton, exitButton; //create event listener buttons
	private JPanel panelLine1, panel1, panel2, lowerButtonPanel,addServicePanel, serviceDetailPanel,
	radButtonPanel, panelLine3; //create panels
	private ServiceOrdersManager managerObj; // = new ServiceOrdersManager(); ; // = new ServiceOrdersManager(); // create instance variable of CurlerTeam type
	private int orderNum;
	private String[] tokens;
	private JCheckBox ckOil, ckSafety, ckTest;
	private ServiceOrder ord;
	private JTable table;
	private JPanel listing, a;
	int number = 0;;


	/**no-arg constructor */
	public ServiceGui(){
		//set title of window
		managerObj = new ServiceOrdersManager();
		setTitle("Cars R Us Auto Shop");
		

		//call methods
		buildTypePanel();
		
		tablePanel();


		//set size of window
		setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
		JPanel t = new JPanel();
		t.setLayout(new GridLayout(2,1));
		t.add(a);
		t.add(listing);
		JPanel west = new JPanel();
		
		west.setLayout(new GridLayout(1,2));
		
		west.add(t);
		west.add(panel2);
		//set layout manager
		setLayout(new BorderLayout());
		add(west, BorderLayout.SOUTH);
		add(panel1, BorderLayout.NORTH);
		

		//specify an action for the close button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pack();
		setVisible(true);
	}



	/** build a panel that components for the variables needed to 
	 */
	public void buildTypePanel(){
		JLabel title = new JLabel("Cars R Us Auto Shop");
		JLabel title1 = new JLabel("5100 Mannakee Street");
		JLabel title2 = new JLabel("Rockville,MD 20850");
		
		//create new panel
		panelLine1 = new JPanel(); 
		JPanel panelLine2 = new JPanel();
		panelLine3 = new JPanel();
		JPanel panelLine4 = new JPanel();
		serviceDetailPanel = new JPanel();




		//create labels and textfields for appt date
		orderLine1 = new JLabel("Order #");
		orderLine1.setFont(new Font("SansSerif", Font.BOLD, 12));
		ownerLine2 = new JLabel("Owner");
		ownerLine2.setFont(new Font("SansSerif", Font.BOLD, 12));
		makeLine3 = new JLabel("Make");
		makeLine3.setFont(new Font("SansSerif", Font.BOLD, 12));
		modelLine4 = new JLabel("Model");
		modelLine4.setFont(new Font("SansSerif", Font.BOLD, 12));
		orderTextField = new JTextField(10);
		ownerTextField = new JTextField(10); 
		makeTextField = new JTextField(10);
		modelTextField = new JTextField(10);

		//set layout manager
		panelLine1.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelLine2.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelLine3.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelLine4.setLayout(new FlowLayout(FlowLayout.LEFT));
		//add to panel 
		panelLine1.add(orderLine1);
		panelLine1.add(orderTextField); 
		panelLine2.add(ownerLine2);
		panelLine2.add(ownerTextField); 
		panelLine3.add(makeLine3);
		panelLine3.add(makeTextField);
		panelLine4.add(modelLine4);
		panelLine4.add(modelTextField);


		//create new panels
		radButtonPanel = new JPanel();
		radButtonPanel.setLayout(new GridLayout(4,2));
		
		JPanel radPanel3 = new JPanel();

		//create new radiobuttons
		orderRadio = new JRadioButton("order#", true);
		orderRadio.setToolTipText("Select Button or press O for order number");
		orderRadio.setMnemonic(KeyEvent.VK_O);
		ownerRadio = new JRadioButton("owner");
		ownerRadio.setToolTipText("Select Button or press D for owner");
		ownerRadio.setMnemonic(KeyEvent.VK_D);
		makeRadio = new JRadioButton("make");
		makeRadio.setToolTipText("Select Button or press W for make of car");
		makeRadio.setMnemonic(KeyEvent.VK_W);


		//checkbox buttons
		ckOil = new JCheckBox("oil change");
		ckSafety = new JCheckBox("Safety");
		ckTest = new JCheckBox("Emissions Test");

		//check buttons action listeners
		ckOil.addActionListener(new addServiceButtonListener());
		ckSafety.addActionListener(new addServiceButtonListener());
		ckTest.addActionListener(new addServiceButtonListener());

		JPanel ckBox = new JPanel();
		ckBox.add(ckOil);
		ckBox.add(ckSafety);
		ckBox.add(ckTest);
		ckBox.setBorder(BorderFactory.createTitledBorder("Service"));

		//create a ButtonGroup object
		ButtonGroup group = new ButtonGroup();

		//Add the radio buttons to the ButtonGroup Object
		group.add(orderRadio);
		group.add(ownerRadio);
		group.add(makeRadio);

		radButtonPanel.setBorder(BorderFactory.createTitledBorder("Display Sorted By"));

		//create action listeners for radio buttons
		orderRadio.addActionListener(new radioButtonListener());
		ownerRadio.addActionListener(new radioButtonListener());
		makeRadio.addActionListener(new radioButtonListener());

		//add buttons to panel
		radButtonPanel.setLayout(new GridLayout(1,3));
		//radButtonPanel.setPreferredSize(new Dimension(1,1));
		radButtonPanel.add(orderRadio);
		radButtonPanel.add(ownerRadio);
		radButtonPanel.add(makeRadio);
		 a = new JPanel();
		a.add(radButtonPanel);
		
		

		radPanel3.setLayout(new GridLayout(4,2));
		radPanel3.add(title);
		radPanel3.add(title1);
		radPanel3.add(title2);
		
		JPanel d = new JPanel();
		d.setLayout(new FlowLayout(FlowLayout.LEFT));
		d.setLayout(new GridLayout(2,1));
		d.add(radPanel3);
		//d.add(a);
		

		//create new panel for appt dates
		serviceDetailPanel.setBorder(BorderFactory.createTitledBorder("Service Order Details"));
		serviceDetailPanel.setLayout(new GridLayout(5,1));
		serviceDetailPanel.add(panelLine1);
		serviceDetailPanel.add(panelLine2);
		serviceDetailPanel.add(panelLine3);
		serviceDetailPanel.add(panelLine4);
		serviceDetailPanel.add(ckBox);

		//create nested panel
		addServicePanel = new JPanel();
		addServicePanel.setLayout(new GridLayout(1,1));
		addServicePanel.add(serviceDetailPanel);


		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2,1));
		panel1.add(d);
		
		
		//create nested panel
		panel2 = new JPanel();
		lowerButtonPanel = new JPanel();
		

		//create new buttons
		addApptButton = new JButton("Start Service");
		addApptButton.setToolTipText("Click here or press A to add appointment");
		addApptButton.setMnemonic(KeyEvent.VK_A);
		deleteButton =  new JButton("Finish Service");
		readButton= new JButton("ReadFile");
		readButton.setToolTipText("Click here or press R to read in file");
		readButton.setMnemonic(KeyEvent.VK_R);
		exitButton = new JButton("Exit");
		exitButton.setToolTipText("Click here or press E to exit");
		exitButton.setMnemonic(KeyEvent.VK_E);

		//create action listeners for JButtons
		addApptButton.addActionListener(new addServiceButtonListener());
		deleteButton.addActionListener(new deleteServiceButtonListener());
		readButton.addActionListener(new ReadButtonListener());
		exitButton.addActionListener(new ExitButtonListener());



		//add buttons to panels
		JPanel topPanel = new JPanel();
		//topPanel.setLayout(new GridLayout(2,1));
		
		lowerButtonPanel.add(addApptButton);
		lowerButtonPanel.add(deleteButton);
		lowerButtonPanel.add(readButton);
		lowerButtonPanel.add(exitButton);


		//set manager layout
		panel2.setLayout(new GridLayout(2,1));

		//nested panels
		panel2.add(addServicePanel);
		panel2.add(lowerButtonPanel);
		
	}

	public void tablePanel(){
		listing = new JPanel();

		String[] colName =  {" ", " ", " ", " ", "oil", "safety", "test"};;
		String[][] rowData = {{" ",  ""," ", " ", " ", " "," "},
				{" ", " "," ", " ", " ", " "," "},
				{" ", " "," ", " ", " ", " "," "}};
		table = new JTable(rowData, colName);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(450,200));
		listing.add(scrollPane);
	}

	/** textfields have values that are passed to  EmployeesManager class via the addEmployee method
	 * when action listener is implemented
	 */
	private class deleteServiceButtonListener implements ActionListener
	{
		//register an event listener for "Add Appt" Button
		public void actionPerformed(ActionEvent e)
		{
			String actionCommand = e.getActionCommand();

			if (actionCommand.equals("Finish Service")){
				String firstLine = orderTextField.getText();
				if(orderTextField.getText().isEmpty()){
					orderTextField.setText("0");
				}else{
					orderNum = Integer.parseInt(firstLine);
				}
			}
			try {
				managerObj.finishService(orderNum);
			} catch (ServiceException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
			finally{
				String[] header = {"order#", "owner", "make", "model", "oil", "safety", "test"};
				//new ServiceOrdersManager();
				String[][] rowdata = managerObj.listByKeyTable(1);
				table.setModel(new DefaultTableModel(rowdata, header));
				table.repaint();
			}

		}
	}

	/** textfields have values that are passed to  EmployeesManager class via the addEmployee method
	 * when action listener is implemented
	 */
	private class addServiceButtonListener implements ActionListener
	{
		//register an event listener for "Add Appt" Button
		public void actionPerformed(ActionEvent e)
		{
			String actionCommand = e.getActionCommand();

			if (actionCommand.equals("Start Service")){	
				String firstLine = orderTextField.getText();
				orderNum = Integer.parseInt(firstLine);


				//retrieve input for month
				String ownerLine = ownerTextField.getText();
				if(ownerTextField.getText().isEmpty()){
					ownerTextField.setText("");
				}

				String makeLine = makeTextField.getText();
				if(makeTextField.getText().isEmpty()){
					makeTextField.setText("");
				}



				//retrieve input of minute
				String modelLine = modelTextField.getText();
				if(modelTextField.getText().isEmpty()){
					modelTextField.setText("");
				}

				String safetyService = "no";
				String oilService = "no";
				String emissionService = "no";

				if(ckOil.isSelected() == true){
					oilService = "yes";
				}


				if(ckSafety.isSelected()){
					safetyService = "yes";
				}


				if(ckTest.isSelected()){
					emissionService = "yes";
				}


				try {
					//ord = new ServiceOrder(day, ownerLine, makeLine, modelLine, oilService, safetyService, emissionService);
					managerObj.startService(orderNum, ownerLine, makeLine, modelLine, oilService, safetyService, emissionService);
					if(orderRadio.isSelected() == true){
						String[] header = {"order#", "owner", "make", "model", "oil", "safety", "test"};
						System.out.println("order: " + managerObj.toString());
						String[][] rowdata = managerObj.listByKeyTable(1);
						table.setModel(new DefaultTableModel(rowdata, header));
						table.repaint();
					}
					if(ownerRadio.isSelected()){
						String[] header = {"owner", "order#", "make", "model", "oil", "safety", "test"};
						System.out.println("owner: " + managerObj.toString());
						String[][] rowdata = managerObj.listByKeyTable(2);
						table.setModel(new DefaultTableModel(rowdata, header));
						table.repaint();
					}
					if(makeRadio.isSelected()){
						String[] header = {"make", "model","owner","order#" ,"oil", "safety", "test"};
						//new ServiceOrdersManager();
						String[][] rowdata = managerObj.listByKeyTable(3);
						table.setModel(new DefaultTableModel(rowdata, header));
						table.repaint();
					}

					/*if(number ==1){
						String[] header = {"order#", "owner", "make", "model", "oil", "safety", "test"};
						System.out.println("order: " + managerObj.toString());
						String[][] rowdata = managerObj.listByKeyTable(1);
						table.setModel(new DefaultTableModel(rowdata, header));
						table.repaint();
					}
						if(number ==2){
							String[] header = {"owner", "order#", "make", "model", "oil", "safety", "test"};
							System.out.println("owner: " + managerObj.toString());
							String[][] rowdata = managerObj.listByKeyTable(2);
							table.setModel(new DefaultTableModel(rowdata, header));
							table.repaint();
						}
						if(number == 3){
							String[] header = {"make", "model","owner","order#" ,"oil", "safety", "test"};
							//new ServiceOrdersManager();
							String[][] rowdata = managerObj.listByKeyTable(3);
							table.setModel(new DefaultTableModel(rowdata, header));
							table.repaint();
						}*/

				} catch (ServiceException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}/*
				finally{
					if(orderRadio.isSelected()){
					String[] header = {"order#", "owner", "make", "model", "oil", "safety", "test"};
					System.out.println("order: " + managerObj.toString());
					String[][] rowdata = managerObj.listByKeyTable(1);
					table.setModel(new DefaultTableModel(rowdata, header));
					table.repaint();
				}
					if(ownerRadio.isSelected()){
						String[] header = {"order#", "owner", "make", "model", "oil", "safety", "test"};
						System.out.println("owner: " + managerObj.toString());
						String[][] rowdata = managerObj.listByKeyTable(2);
						table.setModel(new DefaultTableModel(rowdata, header));
						table.repaint();
					}
					if(makeRadio.isSelected()){
						String[] header = {"order#", "owner", "make", "model", "oil", "safety", "test"};
						//new ServiceOrdersManager();
						String[][] rowdata = managerObj.listByKeyTable(3);
						table.setModel(new DefaultTableModel(rowdata, header));
						table.repaint();
					}


			}*/
			}
		}
	}





	private class radioButtonListener implements ActionListener
	{
		//register an event listener for "Add Appt" Button
		public void actionPerformed(ActionEvent e)
		{
			//String actionCommand = e.getActionCommand();

			if (e.getSource() == orderRadio){
				if(orderRadio.isSelected()){
					number = 1;
					String[] header = {"order#", "owner", "make", "model", "oil", "safety", "test"};
					String[][] rowdata = managerObj.listByKeyTable(1);
					table.setModel(new DefaultTableModel(rowdata, header));

					table.repaint();

				}
			}

			else if (e.getSource() == ownerRadio){
				if(ownerRadio.isSelected()){
					number = 2;
					String[] header = {"owner", "order#", "make", "model", "oil", "safety", "test"};
					String[][] rowdata = managerObj.listByKeyTable(2);
					table.setModel(new DefaultTableModel(rowdata, header));
					table.repaint();
				}
			}

			else if (e.getSource() == makeRadio){
				if(makeRadio.isSelected()){
					number = 3;
					String[] header = {"make", "model", "owner", "order #", "oil", "safety", "test"};
					String[][] rowdata = managerObj.listByKeyTable(3);
					System.out.print(rowdata);
					table.setModel(new DefaultTableModel(rowdata, header));
					table.repaint();
				}
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
						int order = 0;
						String owner = "";
						String make= "";
						String model = "";
						String services = "";

						//read file in line by line 
						while(input.hasNext()){
							String readLine = input.nextLine();	

							//tokens = readLine.split("[ ]+");
							String d = "";
							String oil = "";
							String safety = "";
							String test = "";

							order = Integer.parseInt(readLine);
							System.out.println(order);
							owner =input.nextLine();
							System.out.println(owner);
							make = input.nextLine();
							System.out.println(make);
							model = input.nextLine();
							System.out.println(model);
							services =input.nextLine();	
							System.out.println(services);
							tokens = services.split("[ ]+");


							for(int i = 0; i < tokens.length; i++){
								//retrieve type 
								if(i == 0){
									oil = tokens[i];
								}

								//retrieve month
								else if(i == 1){
									safety = tokens[i];
								}
								//retrieve day
								else if(i == 2){
									test = tokens[i];
								}


							}
							try {
								managerObj.startService(order, owner, make, model, oil, safety, test);
							} catch (ServiceException e1) {
								JOptionPane.showMessageDialog(null, e1.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
								e1.printStackTrace();
							}
							finally{
								String[] colName = {"order#", "owner", "make", "model", "oil", "safety", "test"};
								String[][] rowData = managerObj.listByKeyTable(1);
								table.setModel(new DefaultTableModel(rowData, colName));
								table.repaint();

							}
						}

						input.close();
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
				System.exit(0); //terminate program
			}
		}
	}




	//display gui in main method
	public static void main(String[]args){
		new ServiceGui();
	}
}//end of program








