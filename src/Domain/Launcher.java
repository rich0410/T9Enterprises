package Domain;

import DataAccess.Database;

public class Launcher {

	static Controller ctl;
	
	public static void main(String[] args) {
		
		ctl = Controller.getController();
		ctl.startup(args);
		

	}

}
