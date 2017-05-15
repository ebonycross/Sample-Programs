import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
/**Assignment 5, Spring 2015: ServiceOrdersManager (Data Manager)
 * Implements CourseManagerInterface interface and its methods.As the data manager, 
 * computations and methods for Driver class are created.
 * Contains reference objects in binary tree.
 * 
 * @author Ebony Cross
 * 
 */
public class Courses implements DataManagerInterface {
	private static final long serialVersionUID = 3331792168968133629L;
	private BST<Object> bst;
	private Course c;
	private ArrayList<Object> arr;
	private String [] table;
	private ArrayList<DataElementInterface>list;
	private String[][] table2D;
	private FileInputStream inStream;
	private FileOutputStream outStream;

	/**no-arg constructor*/
	public Courses(){
		bst = new BST<Object>();
		//c = new Course();	
	}

	/**
	 * Adds a course into the binary search tree
	 * @param course - course to be added
	 */
	public void add(DataElementInterface course) {
		bst.add(course);
	}

	/**
	 * Creates a course from the following parameters and then adds the 
	 * course to the binary search tree
	 * @param cn course number
	 * @param in instructor
	 * @param desc course description
	 * @param cred number of credits
	 * @param day days of class
	 * @param hour time of class
	 */
	public void add(String cn, String in, String desc, int cred, String day,
			String hour) {
		c = new Course(cn, in,desc, cred, day, hour);
		bst.add(c);

	}

	/**
	 * 
	 * @param key course number of course to search for
	 * @return reference to CourseElement if found, null if not found
	 */
	public boolean remove(String key) {
		boolean result;

		DataElementInterface c1 = new Course();
		c1 = search(key);

//		System.out.println("c1" +c1);
//		System.out.println();
//		System.out.println(bst.inOrderTraversal());
		if(bst.remove(c1)){

//			System.out.println("c1 after" +c1);
//			System.out.println();
//			System.out.println(bst.inOrderTraversal());
			result = true;
			return result;
		}
		else{
			result = false;
			return result;
		}
	}

	/**
	 * Returns the number of elements in the Data Collection
	 * @return number of elements in the Data Collection
	 */
	public DataElementInterface search(String key) {
		c = new Course();
		//c.setKey(key);
		c.courseName = key;
		//System.out.println("bst "+bst.search(key));
		

		if(bst.search(c) == null)
			return null;
		else {
			Course c1 = new Course(c);
			//System.out.print(c.getKey());
			return c1;
		}		
	}

	/**
	 * Returns the number of elements in the Data Collection
	 * @return number of elements in the Data Collection
	 */
	public int size() {
		int num = bst.size();
		return num;
	}

	/**
	 * The courses in the binary search tree are put in an
	 * ArrayList based on in order traversal 
	 * @return ArrayList of CourseElements in order
	 */
	public ArrayList<DataElementInterface> toArrayList() {
		arr = new ArrayList<Object>(bst.inOrderTraversal());

		list = new ArrayList<DataElementInterface>(bst.copyArray(arr));
		//toString();

		//		System.out.print(bst.inOrderTraversal());
			
		return list;
	}

	/**
	 * The courses in the binary search tree are put in a
	 * two-dimensional array of Strings to populate the table
	 * [][0] - course number
	 * [][1] - instructor
	 * [][2] - description
	 * [][3] - credits
	 * [][4] - days
	 * [][5] - hours/time
	 * @return two dimensional array of Strings to populate the table
	 */
	public String[][] toTable() {
		toArrayList();
		table2D = new String[list.size()][6];
		table = new String[6];
		//System.out.println("list in toTable()" + list);
		//System.out.println("outside loop");

		for(int i = 0; i < list.size(); i++){

			table = list.get(i).toTableRow();
			//System.out.println("inside loop at index "+i +": " +list.get(i));


			for(int k = 0; k < table.length; k++){
				//System.out.println("table in toTable() index "+ k +": "+ table[k]);
			}


			for(int j = 0; j < table.length; j++){
				table2D[i][j] = table[j];
				//System.out.println("2d table at index: "+i +j +"  " +table2D[i][j]);

			}

		}
		//		table = c.toTableRow();
		//		System.out.println("course arr: " + c.toTableRow());
		//		System.out.println("table: " +table.toString());
		return table2D;
	}

	/**
	 * create binary file from the file contents
	 * @param f File
	 * @return true if File is found and words added, returns false if file not found
	 */
	public boolean readBinaryFile(File f){
		 c = new Course();

		boolean fileExists;

		
			inStream = null;
			int counter = 0;
			try {
				inStream = new FileInputStream(f);
				ObjectInputStream in = new ObjectInputStream(inStream);
				while(inStream.available() > 0){
				 c =(Course)in.readObject();
				bst.add(c);
				counter++;
				}
				
				in.close();
			}
			catch (EOFException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			fileExists = true;
			return fileExists;

		}
	
	/**
	 * write binary file of strings in the file contents
	 * @param f File
	 * @return true if File is written, returns false if file not written successfully
	 */
	public boolean writeBinaryFile(File f){
		toArrayList();
		boolean fileExist;
		outStream = null;
		try {

			outStream = new FileOutputStream(f);
			ObjectOutputStream out = new ObjectOutputStream(outStream);
			for(int i = 0; i < list.size(); i++){
				out.writeObject(list.get(i));
			}
			//out.writeObject(toString());

			out.close();
		}//end of try block
		catch(Exception e)
		{
			System.out.println("Error "+e.getMessage());
		}




		return false;

	}


	/**
	 * create hash table of strings in the file contents
	 * @param f File
	 * @return true if File is found and words added, returns false if file not found
	 */
	public boolean createFile(File f){

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
					String course = input.nextLine();
					String prof = input.nextLine();
					String desc = input.nextLine();
					String cred = input.nextLine();
					int credits = Integer.parseInt(cred);
					String day = input.nextLine();
					String time = input.nextLine();

					//System.out.println(fileWord);

					c = new Course(course, prof, desc, credits, day, time);
					bst.add(c);
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
	 * write words in dictionary to a file
	 * @param f file 
	 * @return true if File is found and words added, returns false if file not found
	 */
	public boolean printToFile(File f){
		boolean fileExist;


		try {
			
			PrintWriter outputFile = new PrintWriter(f);
			outputFile.println(toString());
			outputFile.close();
//
//			PrintWriter outputFile = new PrintWriter(f);
//			outputFile.write(toString());
//
//			//outputFile.println(words1.toString());
//			outputFile.close();
		}

		catch (IOException e1) {
			e1.printStackTrace();
		}

		return true;

	}
	
	
	/**
     * String representation of the DataElement object
     * @return string 
     */
	public String toString(){
		String print = "";

		toArrayList();

		for(int i = 0; i < list.size(); i++){
			print += list.get(i).toString();

		}
		System.out.print("print:" + "\n" + print);
		return print;
	}


}//end of program
