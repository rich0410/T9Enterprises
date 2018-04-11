package Domain;

/**
 * The main launcher class
 */
public class Launcher {

    static Controller ctl;

    public static void main(String[] args) {

        ctl = Controller.getController();
        ctl.startup();
        ctl.initGUI(args);


    }

}
