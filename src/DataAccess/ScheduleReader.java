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

public class ScheduleReader {
	
	private Scanner schedScanner = null;
	private ArrayList<HashMap<String,String>> schedule;
	private String[] records;
	private String[] labels;
	private HashMap<String,String> time;
	private String line;

	
	/**
	 * Constructor
	 */
	public ScheduleReader(){
	}
	
	/**
	 * Reads the .csv file one line at a time. Creating the records necessary to create a timetable object.
	 * @return 
	 */
	public ArrayList<HashMap<String, String>> readFile(File file){
		
		//Create the scanner object to read the file.
		try {
			schedScanner = new Scanner(new FileReader(file));
		
		
			line = schedScanner.nextLine();
			labels = line.split(",");
			
			//Read one line at a time
			while(schedScanner.hasNext()){					
				
				line = schedScanner.nextLine(); 		//Store the line as a string
				records = line.split(","); 				//Separate the values 
				
				time = new HashMap<String,String>();
				
				time.put("Course", records[0]);
				time.put("Duration", records[1]);
				time.put("Day", records[2]);
				time.put("Time", records[3]);
				time.put("Room", records[4]);
				
				schedule.add(time);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return schedule;
	}
}
