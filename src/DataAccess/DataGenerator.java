package DataAccess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

public class DataGenerator {
	
	private Scanner schedScanner = null;
	private HashMap<String, String> names = new HashMap<String, String>();
	private HashMap<String, String[]> schedule = new HashMap<String, String[]>();
	private String[] records;
	private String[] sched = new String[21];
	private String line;

	
	public DataGenerator(){
	}
	
	public void readFile(){
		
		try {
			schedScanner = new Scanner(new FileReader(new File("schedule.csv")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		while(schedScanner.hasNext()){					
			
			line = schedScanner.nextLine(); 		
			records = line.split(","); 	
			
			if(records[0]=="NAME"){
				names.put("First Name", records[1]);
				names.put("Last Name", records[2]);
			}
			else{
				System.arraycopy(records, 3, sched, 0, sched.length);
				schedule.put(records[0], sched);
			}
			
		}
	}
	
	public HashMap<String, String> getNames(){
		return names;
	}
	
	public HashMap<String, String[]> getSched(){
		return schedule;
	}
}
