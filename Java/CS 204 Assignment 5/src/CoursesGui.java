import javax.swing.*; //need for Swing classes
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.*;
import javax.swing.table.TableModel;
import java.awt.*; 
import java.awt.event.*; //need for event listener interface
import java.io.*; // need for file to read/write in
import java.util.Scanner;

/**Assignment 5, Spring 2015: Driver Class
 * Implement and compute the appointments
 * File read in is chosen by the user. 
 * Computations executed from the Data Manager class called Appointments.
 * @author Ebony Cross
 * 
 */
public class CoursesGui extends JFrame {

	private final int WINDOW_WIDTH = 200; //window width
	private final int WINDOW_HEIGHT = 200; //window height
	private JLabel courseLine1, profLine2, descLine3, credLine4, dayLine5, timeLine6; //create labels for teams
	private JTextField courseTextField, profTextField,descTextField, 
	credTextField, dayTextField,timeTextField; //create course field entry
	private JButton printButton, deleteButton, addButton,editButton, readButton, readBinButton, exitButton; //create event listener buttons
	private JPanel panelLine1, panel1, panel2, lowerButtonPanel,addCourseDetailPanel, courseDetailPanel,
	radButtonPanel, panelLine3, panelLine5,panelLine6; //create panels
	private Courses managerObj;
	private DataManagerInterface courses;
	private String[] tokens;
	private Course c;
	private JTable table;
	private JPanel listing, a;
	int number = 0;;
	private File textOutputFile, newTextFile;



	/**no-arg constructor */
	public CoursesGui(){
		managerObj = new Courses();
		setTitle("Montgomery College Mini-Catalog");


		//call methods
		buildTypePanel();

		tablePanel();


		//set size of window
		setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
		JPanel t = new JPanel();
		t.setLayout(new GridLayout(2,1));
		t.add(panel1);
		t.add(listing);

		JPanel west = new JPanel();

		west.setLayout(new GridLayout(1,2));

		west.add(listing);
		west.add(panel2);



		//set layout manager
		setLayout(new BorderLayout());

		add(west, BorderLayout.CENTER);
		add(panel1, BorderLayout.NORTH);//title
		add(lowerButtonPanel, BorderLayout.SOUTH);

		
		//specify an action for the close button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pack();
		setVisible(true);
	}



	/** build a panel that components for the variables needed to 
	 */
	public void buildTypePanel(){
		JLabel title = new JLabel("Montgomery College Mini-Catalog");

		//create new panel
		panelLine1 = new JPanel(); 
		JPanel panelLine2 = new JPanel();
		panelLine3 = new JPanel();
		JPanel panelLine4 = new JPanel();
		panelLine5 = new JPanel();
		panelLine6 = new JPanel();
		courseDetailPanel = new JPanel();

		panelLine6.add(title);


		//create labels and textfields for appt date
		courseLine1 = new JLabel("Course Name");
		courseLine1.setFont(new Font("SansSerif", Font.BOLD, 12));
		profLine2 = new JLabel("Instructor Name");
		profLine2.setFont(new Font("SansSerif", Font.BOLD, 12));
		descLine3 = new JLabel("Description");
		descLine3.setFont(new Font("SansSerif", Font.BOLD, 12));
		credLine4 = new JLabel("Credit");
		credLine4.setFont(new Font("SansSerif", Font.BOLD, 12));
		dayLine5 = new JLabel("Days");
		dayLine5.setFont(new Font("SansSerif", Font.BOLD, 12));
		timeLine6 = new JLabel("Time");
		timeLine6.setFont(new Font("SansSerif", Font.BOLD, 12));
		courseTextField = new JTextField(10);
		profTextField = new JTextField(10); 
		descTextField = new JTextField(10);
		credTextField = new JTextField(10);
		dayTextField = new JTextField(10);
		timeTextField = new JTextField(10);

		//set layout manager
		panelLine1.setLayout(new GridLayout(6,1));
		panelLine2.setLayout(new GridLayout(6,1));
		panelLine3.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelLine4.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelLine5.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelLine6.setLayout(new FlowLayout(FlowLayout.LEFT));
		//add to panel 
		panelLine1.add(courseLine1);
		panelLine2.add(courseTextField); 
		panelLine1.add(profLine2);
		panelLine2.add(profTextField); 
		panelLine1.add(descLine3);
		panelLine2.add(descTextField);
		panelLine1.add(credLine4);
		panelLine2.add(credTextField);
		panelLine1.add(dayLine5);
		panelLine2.add(dayTextField);
		panelLine1.add(timeLine6);
		panelLine2.add(timeTextField);


		//create new panels
		radButtonPanel = new JPanel();
		radButtonPanel.setLayout(new GridLayout(6,2));

		JPanel radPanel3 = new JPanel();

		radPanel3.add(title);	

		JPanel d = new JPanel();
		d.setLayout(new FlowLayout(FlowLayout.LEFT));
		d.setLayout(new GridLayout(2,1));
		d.add(radPanel3);
		//d.add(a);


		//create new panel for course detail panel
		courseDetailPanel.setBorder(BorderFactory.createTitledBorder("Course Details"));
		courseDetailPanel.setLayout(new GridLayout(1,2));
		courseDetailPanel.add(panelLine1);
		courseDetailPanel.add(panelLine2);


		//create nested panel
		addCourseDetailPanel = new JPanel();
		addCourseDetailPanel.setLayout(new GridLayout(1,1));
		addCourseDetailPanel.add(courseDetailPanel);


		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2,1));
		panel1.add(d);


		//create nested panel
		panel2 = new JPanel();
		lowerButtonPanel = new JPanel();


		//create new buttons
		printButton = new JButton("Print Catalog");
		addButton = new JButton("Insert Course");
		addButton.setToolTipText("Click here or press A to add appointment");
		addButton.setMnemonic(KeyEvent.VK_A);
		deleteButton =  new JButton("Delete Course");
		editButton = new JButton("Edit Course");
		readButton= new JButton("Read Text File");
		readButton.setToolTipText("Click here or press R to read in file");
		readButton.setMnemonic(KeyEvent.VK_R);
		readBinButton = new JButton("Read Binary File");
		exitButton = new JButton("Exit");
		exitButton.setToolTipText("Click here or press E to exit");
		exitButton.setMnemonic(KeyEvent.VK_E);

		//		//create action listeners for JButtons
		printButton.addActionListener(new printButtonListener());
		addButton.addActionListener(new addButtonListener());
		deleteButton.addActionListener(new deleteButtonListener());
		editButton.addActionListener(new editButtonListener());
		readButton.addActionListener(new ReadButtonListener());
		readBinButton.addActionListener(new ReadBinButtonListener());
		exitButton.addActionListener(new ExitButtonListener());



		//add buttons to panels

		lowerButtonPanel.add(printButton);
		lowerButtonPanel.add(addButton);
		lowerButtonPanel.add(deleteButton);
		lowerButtonPanel.add(editButton);
		lowerButtonPanel.add(readButton);
		lowerButtonPanel.add(readBinButton);
		lowerButtonPanel.add(exitButton);


		//set manager layout
		panel2.setLayout(new GridLayout(2,1));

		//nested panels
		panel2.add(addCourseDetailPanel);


	}

	public void tablePanel(){
		listing = new JPanel();

		String[] colName = {"Course", "Instructor", "Description", "Credits", "Days","Times"};
		String[][] rowData = {{" ",  ""," ", " ", " ", " "," "},
				{" ", " "," ", " ", " ", " "," "},
				{" ", " "," ", " ", " ", " "," "}};
		table = new JTable(rowData, colName);

		JScrollPane scrollPane = new JScrollPane(table);
		
		table.setShowGrid(true);
		scrollPane.setPreferredSize(new Dimension(450,200));
		
		listing.add(scrollPane);
		table.addMouseListener(new mouseClickedListener());
	}
	
	private class mouseClickedListener extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getSource() == table){
				int row = table.getSelectedRow();
				int column =table.getColumnCount();
				
				courseTextField.setText((String) table.getValueAt(row, 0));
				profTextField.setText((String) table.getValueAt(row, 1)); 
				descTextField.setText((String) table.getValueAt(row, 2));
				credTextField.setText((String) table.getValueAt(row, 3));
				dayTextField.setText((String) table.getValueAt(row, 4));
				timeTextField.setText((String) table.getValueAt(row, 5));
				
				
				for(int i = 0; i < column; i++) {
				    System.out.println(table.getValueAt(row, i));
				}
			
			System.out.println("rows" + row + "cols " + column);
				
			}
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

	
			
		}
		
	

	/** textfields have values that are passed to Manager class via the add method
	 * when action listener is implemented
	 */
	private class deleteButtonListener implements ActionListener
	{
		//register an event listener for "Add Appt" Button
		public void actionPerformed(ActionEvent e)
		{
			String actionCommand = e.getActionCommand();

			if (actionCommand.equals("Delete Course")){
			if(courseTextField.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Please select a row or enter the course name");
			}
			try {
			if(!(courseTextField.getText().isEmpty())){
			
				String firstLine = courseTextField.getText();
				validateEdit(firstLine);
					managerObj.remove(firstLine);
					JOptionPane.showMessageDialog(null, "Course has been deleted");
					
			}
				} 
			catch(CourseException e2){
				JOptionPane.showMessageDialog(null, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);}
//			}catch (Exception e1) {
//					JOptionPane.showMessageDialog(null, e1.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
//					e1.printStackTrace();
//				}
				finally{
					String[] header = {"Course", "Instructor", "Description", "Credit", "Days","Times"};
					//System.out.println("courses: " + managerObj.toString());
					String[][] rowdata = managerObj.toTable();
					courseTextField.setText("");
					profTextField.setText("");
					descTextField.setText("");
					credTextField.setText("");
					dayTextField.setText("");
					timeTextField.setText("");
					table.setModel(new DefaultTableModel(rowdata, header));
					table.repaint();
				}

			}
		}
	}
	
	/** textfields have values that are passed to Manager class via the edit method
	 * when action listener is implemented
	 */
	private class editButtonListener implements ActionListener
	{
		//register an event listener for "Add Appt" Button
		public void actionPerformed(ActionEvent e)
		{
			String actionCommand = e.getActionCommand();

			if (actionCommand.equals("Edit Course")){
			if(table.getSelectedRow() < 0){
				JOptionPane.showMessageDialog(null, "Please select a row");
			} 
			try {
			if(table.getSelectedRow() > 0){
			
			String line1 = courseTextField.getText();
			String line2 = profTextField.getText();
			String line3 = descTextField.getText();
			String line4 = credTextField.getText();
			int num = Integer.parseInt(line4);
			String line5 = dayTextField.getText();
			String line6 = timeTextField.getText();
			
			
				validateEdit(line1);
	 
	

				managerObj.remove(line1);
				managerObj.add(line1,line2, line3, num, line5, line6);
			}
			}
			catch(CourseException e2){
				JOptionPane.showMessageDialog(null, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			
			 catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
			finally{
				String[] header = {"Course", "Instructor", "Description", "Credit", "Days","Times"};
				//System.out.println("courses: " + managerObj.toString());
				String[][] rowdata = managerObj.toTable();
				courseTextField.setText("");
				profTextField.setText("");
				descTextField.setText("");
				credTextField.setText("");
				dayTextField.setText("");
				timeTextField.setText("");
				table.setModel(new DefaultTableModel(rowdata, header));
				table.repaint();
			}
			}
		}
	}

	/** textfields have values that are passed to  Manager class via the add method
	 * when action listener is implement
	 */
	private class addButtonListener implements ActionListener
	{
		//register an event listener for "Add Appt" Button
		public void actionPerformed(ActionEvent e)
		{
			String actionCommand = e.getActionCommand();

			if (actionCommand.equals("Insert Course")){	
				String line1 = courseTextField.getText();
				if(courseTextField.getText().isEmpty()){
					JOptionPane.showMessageDialog(null,"information missing from a field", "Missing Information", JOptionPane.INFORMATION_MESSAGE);
				}
				//retrieve input for prof
				String line2 = profTextField.getText();
				if(profTextField.getText().isEmpty()){
					profTextField.setText("");
					JOptionPane.showMessageDialog(null,"information missing from a field", "Missing Information", JOptionPane.INFORMATION_MESSAGE);
				}

				String line3 = descTextField.getText();
				if(descTextField.getText().isEmpty()){
					descTextField.setText("");
					JOptionPane.showMessageDialog(null,"information missing from a field", "Missing Information", JOptionPane.INFORMATION_MESSAGE);
				}

				//retrieve input of minute
				String line4 = credTextField.getText();
				int num = Integer.parseInt(line4);
				if(credTextField.getText().isEmpty()){
					credTextField.setText("");
					JOptionPane.showMessageDialog(null,"information missing from a field", "Missing Information", JOptionPane.INFORMATION_MESSAGE);
					//num = 0;
				}

				//retrieve input of minute
				String line5 = dayTextField.getText();
				if(dayTextField.getText().isEmpty()){
					dayTextField.setText("");
					JOptionPane.showMessageDialog(null,"information missing from a field", "Missing Information", JOptionPane.INFORMATION_MESSAGE);
				}

				//retrieve input of minute
				String line6 = timeTextField.getText();
				if(timeTextField.getText().isEmpty()){
					timeTextField.setText("");
					JOptionPane.showMessageDialog(null,"information missing from a field", "Missing Information", JOptionPane.INFORMATION_MESSAGE);
				}


				try {
					if(!((timeTextField.getText().isEmpty()) && (credTextField.getText().isEmpty()) && (descTextField.getText().isEmpty())
						&&	(profTextField.getText().isEmpty()) && (credTextField.getText().isEmpty()))){
					validateAdd(line1);
					managerObj.add(line1,line2, line3, num, line5, line6);

					String[] header = {"Course", "Instructor", "Description", "Credit", "Days","Times"};
					//System.out.println("courses: " + managerObj.toString());
					String[][] rowdata = managerObj.toTable();
					table.setModel(new DefaultTableModel(rowdata, header));
					table.repaint();
				}

				}
				catch(CourseException e2){
					JOptionPane.showMessageDialog(null, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}


			}
		}
	}

	/**Given file is found and selected. Then the given file is read into the 
	 *program
	 */
	private class ReadBinButtonListener implements ActionListener{
		//register event listener for "Read Votes" button
		public void actionPerformed(ActionEvent e) 
		{
			//get the action command
			String actionCommand = e.getActionCommand();

			//create new JFileChooser to successful select file to read 
			if(actionCommand.equals("Read Binary File")){
				//apptsObj = new Appointments();
				JTextArea a = new JTextArea(20,30);
				JFileChooser chooser = new JFileChooser();

				//open file path
				int found = chooser.showDialog(null, "Open");

				//execute if execution is incorrect
				if ( found != JFileChooser.APPROVE_OPTION){
					a.setText("No File Chosen");
					c = new Course();
				} 
				else{
					try {

						File file = chooser.getSelectedFile();
						boolean fileExists;
						//inStream = null;
						int counter = 0;
						FileInputStream inStream = new FileInputStream(file);
						ObjectInputStream in = new ObjectInputStream(inStream);
						while(inStream.available() > 0){
						 c = (Course) in.readObject();
						 managerObj.add(c);
						 counter++;
						}
						in.close();

						System.out.print("read method: " + managerObj);
						System.out.println("counter: " + counter);
					}
					//					catch (EOFException e1) {
					//						e1.printStackTrace();
					//					} catch (FileNotFoundException e1) {
					//						// TODO Auto-generated catch block
					//						e1.printStackTrace();
					//					} catch (IOException e1) {
					//						// TODO Auto-generated catch block
					//						e1.printStackTrace();
					//					} catch (ClassNotFoundException e1) {
					//						// TODO Auto-generated catch block
					//						e1.printStackTrace();
					//					}
					catch (Exception e1){
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
					finally{
						String[] header = {"Course", "Instructor", "Description", "Credit", "Days","Times"};
						//System.out.println("courses: " + managerObj.toString());
						String[][] rowdata = managerObj.toTable();
						table.setModel(new DefaultTableModel(rowdata, header));
						table.repaint();
					}

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
			if(actionCommand.equals("Read Text File")){
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
						textOutputFile = chooser.getSelectedFile();
						managerObj.createFile(textOutputFile);
					}

					catch (Exception e1){
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
					finally{
						String[] header = {"Course", "Instructor", "Description", "Credit", "Days","Times"};
						//System.out.println("courses: " + managerObj.toString());
						String[][] rowdata = managerObj.toTable();
						table.setModel(new DefaultTableModel(rowdata, header));
						table.repaint();
					}

				}

			}

		}
	}

	/** program prints text file when action listener is implemented*/
	private class printButtonListener implements ActionListener 
	{
		//register acttion listener for "Exit" Button
		public void actionPerformed(ActionEvent e)
		{

			//get the action command
			String actionCommand = e.getActionCommand();

			if(actionCommand.equals("Print Catalog")){
				JFileChooser chooser = new JFileChooser();

				//open file path
				int found = chooser.showDialog(null, "Save");

				//execute if execution is incorrect
				if (found == JFileChooser.APPROVE_OPTION){


					File file = chooser.getSelectedFile();
					managerObj.printToFile(file);


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
					int reply = JOptionPane.showConfirmDialog(null, "Save courses as a binary file?", "Save Courses", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(reply == JOptionPane.YES_OPTION){
						JFileChooser chooser = new JFileChooser();

						//open file path
						int found = chooser.showDialog(null, "Save");

						//execute if execution is incorrect
						if (found == JFileChooser.APPROVE_OPTION){


							//File file = chooser.getSelectedFile();
							String withExtension = chooser.getSelectedFile().getAbsolutePath() + ".bin";
							File file = new File( withExtension );
							managerObj.writeBinaryFile(file);
							
							JOptionPane.showMessageDialog(null, "The binary file " + file + " can now be read into the program","Save Courses", JOptionPane.INFORMATION_MESSAGE);
							
							System.exit(0); //terminate program
						
						}
						
				}
					
					if(reply == JOptionPane.NO_OPTION){
						JOptionPane.showMessageDialog(null, "No binary file was saved","Save Courses", JOptionPane.INFORMATION_MESSAGE);
						
						System.exit(0); //terminate program
					}
					
					}
					
				}
			}
		
	public void validateEdit(String key) throws CourseException{
		if(managerObj.search(key) == null){
			throw new CourseException("Course does not exists, enter valid course name");
	}
	}
	public void validateAdd(String key) throws CourseException{
		if(managerObj.search(key) != null){
			throw new CourseException("course alreay exists, enter new valid course name");
	}
	}



	//display gui in main method
	public static void main(String[]args){
		new CoursesGui();
	}
}//end of program










