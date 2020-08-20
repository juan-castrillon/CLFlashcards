package Flashcards;

import java.io.File;
import java.util.Scanner;

/**
 * Class to generate program to interact with the Flashcard object
 */
/*public class AppNO {    /**
 * Method that simplifies passing commands to the set of flashcards from the user
 * @param set Flashcard set to pass the commands to
 * @param command Command to be executed (Limited options)
 * @param in Scanner in order to read console input
 * @return true only if the exit command was executed, otherwise false
 */

/*public static boolean executeCommand(FlashcardSet set, String command, Scanner in) {
    boolean exit = false;
    set.printAndLog(command, true);
    switch (command) {
        case "add":
            set.add();
            break;
        case "remove":
            set.remove();
            break;
        case "import":
            set.printAndLog("File name:", false);
            String fileName = in.nextLine(); //Ask the user for a filename
            set.printAndLog(fileName, true);
            File file = new File(fileName); //Create a File object to pass to the set method
            set.fromFile(file);
            break;
        case "export":
            set.printAndLog("File Name:", false);
            String name = in.nextLine();//Ask the user for a filename
            set.printAndLog(name, true);
            File writingFile = new File(name);//Create a File object to pass to the set method
            set.toFile(writingFile);
            break;
        case "ask":
            set.printAndLog("How many times to ask?", false);
            int times = Integer.parseInt(in.nextLine()); //Allow the user to determine a number of times he wants to be asked.
            set.printAndLog(String.valueOf(times), true);
            for (int i = 0; i < times ; i++) { //Asking all the times
                set.ask();
            }
            break;
        case "exit":
            set.printAndLog("Bye!", false);
            exit = true; //Exit the program.
            break;
        case "hardest card":
            set.hardest();
            break;
        case "log":
            set.printAndLog("File Name:", false);
            String logName = in.nextLine(); //Ask the user for a filename
            set.printAndLog(logName, true);
            File logFile = new File(logName); //Create a File object to pass to the set method
            set.saveLog(logFile);
            break;
        case "reset stats":
            set.resetErrors();
            break;
        default:
            set.printAndLog("Command not valid", false); //If a non-recognized command is inputted
            break;

    }
    return exit;
}
    public void main(String[] args) {
        Scanner in = new Scanner(System.in);
        FlashcardSet set = new FlashcardSet(); //New flashcard set
        File inputFile; //In case of "-input" CL argument present
        File outputFile = null; //In case of "-export" CL argument present
        boolean export = false; //Will be true case of "-export" CL argument present
        if (args.length > 0) { //If there is any CL arguments
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("-import")) { //If the "-import" argument is present, execute the import
                    inputFile = new File(args[i + 1]);
                    set.fromFile(inputFile);
                } else if (args[i].equals("-export")) { //If the "-export" argument is present, change the export flag
                    export = true;
                    outputFile = new File(args[i + 1]);
                }
            }
        }
        boolean exit = false;
        while (!exit) {
            set.printAndLog("Input the action (add, remove, import, export, ask, log, hardest card, reset stats, exit):", false);
            String a = in.nextLine();
            exit = executeCommand(set,a, in); //Runs indefinitely until the "exit" command is passed
        }
        if (export) { //If at the beggining, the "-export" CL argument was present, output the set to the specified file
            set.toFile(outputFile);
        }
    }
}*/

