package Flashcards;

import java.io.File;

public class App {
    public static void main(String[] args) {
        Controller controller = new Controller();
        File outputFile = null;
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                switch (args[i]) {
                    case "-import":
                        controller.fromFile(new File(args[i+1]));
                        break;
                    case "-export":
                        outputFile = new File(args[i+1]);
                }
            }
        }
        boolean exit = false;
        while (!exit) {
            controller.show("Input the action (add, remove, import, export, ask, log, hardest card, reset stats, exit):");
            exit = controller.execute();
        }
        if (outputFile != null) {
            controller.toFile(outputFile);
        }
    }
}
