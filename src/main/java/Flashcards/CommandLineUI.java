package Flashcards;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Command Line User Interface. Apart from reading and displaying also has built-in
 * logging capabilities.
 */
public class CommandLineUI {
    private final Scanner in;
    private final ArrayList<String> log;

    /**
     * Constructor of the class. Initializes the input with a scanner in the standard input
     */
    public CommandLineUI() {
        this.log = new ArrayList<>();
        this.in = new Scanner(System.in);
    }

    /**
     * Method to provide writing capabilities to the log, without exposing the field
     * @param term String representing the term to be written into the log
     */
    public void writeLog (String term) {
        this.log.add(term);
    }

    /**
     * Method for easing the logging and printing to screen, making them one operation, and making the code more readable
     * @param message Message to be printed to screen (and logged).
     */
    public void printToGUI(String message) {
        System.out.println(message);
        this.writeLog(message);
    }

    /**
     * Method to read, and log, the user input in the console
     * @return Users input in the console (last line)
     */
    public String read() {
        String reading = in.nextLine();
        writeLog(reading);
        return reading;
    }

    /**
     * Method that allows to save the log into a text file
     * @param file File in which the log will be written
     */
    public void saveLog(File file) {
        try (PrintWriter writer = new PrintWriter(file)) {
            for (String line : this.log) {
                writer.println(line); //Save each entry of the log in a separate file
            }
            this.printToGUI("The log has been saved");
        } catch (FileNotFoundException e) { //If the file is not found
            this.printToGUI("Error while saving log");
        }
    }

}
