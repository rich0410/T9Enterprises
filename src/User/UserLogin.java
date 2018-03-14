package User;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/***
 * This class is used for User Login
 * Under progress
 */

public class UserLogin {
    
	private URL url;
	private HttpURLConnection connection;
	private int returnCode;
	
    
    public UserLogin(){
    	
    }
    
	public boolean authenticate(String userID, String password) {
		
		try {
			url = new URL(""+userID+""+password+"");						//The actual URL will be provided by Ed. 
			connection = (HttpURLConnection)url.openConnection(); 
			connection.setRequestMethod("GET");
			connection.connect();

			returnCode = connection.getResponseCode();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		boolean result = false;
		
		if (returnCode == 0){												//Evaluate the return code, and return a boolean to indicate whether the 
			result = true;													//user information is valid.
		}
		else{
			result = false;
		}
		
		return result;
		
	}


}
