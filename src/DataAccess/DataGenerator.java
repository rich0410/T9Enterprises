/**
 * This class will be used to load 'dummy' data. It will read data from a .csv file and pass it to the controller. In the future it could be modified to 
 * read from a file uploaded by the user.
 */
package DataAccess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DataGenerator {
	
	private Scanner schedScanner = null;
	private HashMap<String, String> names = new HashMap<String, String>();
	private HashMap<String, ArrayList<String>> schedule = new HashMap<String, ArrayList<String>>();
	private String[] records;
	private ArrayList<String> sched;
	private String line;

	
	/**
	 * Constructor
	 */
	public DataGenerator(){
	}
	
	/**
	 * Reads the .csv file one line at a time. Creating the records necessary to create a timetable object.
	 */
	public void readFile(File file){
		
		//Create the scanner object to read the file.
		try {
			schedScanner = new Scanner(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//Read one line at a time
		while(schedScanner.hasNext()){					
			
			line = schedScanner.nextLine(); 		//Store the line as a string
			records = line.split(","); 				//Separate the values 
			sched = new ArrayList<String>();
			
			//Extract the name 
			if(records[0].equals("")){
			}
			else if(records[0].equals("NAME")){					
				names.put("First Name", records[1]);
				names.put("Last Name", records[2]);
			}
			else{
				for(int i=1; i<20; i++){
					try{
						if(records[i]==null){
							sched.add("");
						}
						else{
							sched.add(records[i]);
						}
					}catch(IndexOutOfBoundsException ioobe){
						sched.add("");
					}
				}
				schedule.put(records[0], sched);
			}
			
		}
	}
	
	/**
	 * Retrieve the first and last name of the teacher.
	 * @return
	 */
	public HashMap<String, String> getNames(){
		return names;
	}
	
	/**
	 * Retrieve the schedule information for the teacher.
	 * @return
	 */
	public HashMap<String, ArrayList<String>> getSched(){
		return schedule;
	}
}
