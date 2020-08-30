package Flashcards;

import java.io.File;

public class App {
    public static void main(String[] args) {
        Controller controller = new Controller();
        File outputFile = null; //Will be used if argument is set
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                switch (args[i]) {
                    case "-import": // if the argument --import is used, read the filepath and import the set
                        controller.fromFile(new File(args[i+1]));
                        break;
                    case "-export": // if --export is used, save the file to export later
                        outputFile = new File(args[i+1]);
                }
            }
        }
        boolean exit = false;
        while (!exit) { //Loop controlled by the controller
            exit = controller.execute();
        }
        if (outputFile != null) { //If export argument was set
            controller.toFile(outputFile);
        }
    }
}
