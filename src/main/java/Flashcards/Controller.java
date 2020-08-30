package Flashcards;

import java.io.File;

/**
 * Class that acts between intermediary between the client app and the flashcard set and GUI
 */
public class Controller {

    private final CommandLineUI GUI;
    private final FlashcardSet set;

    /**
     * Constructor of the class
     */
    public Controller() {
        this.GUI = new CommandLineUI();
        this.set = new FlashcardSet();
    }

    /**
     * Method for adding a card to the set, using the GUI to interact with the user
     */
    public void add() {
        String term, definition;
        GUI.printToGUI("The card:");//Ask the user for the definition of the card
        term = GUI.read(); //Read the card's definition from console
        if (set.hasTerm(term)) { //Check if the set already has a card with the inputted definition
            GUI.printToGUI("The card \"" + term + "\" already exists. Try again:\n");
            return; //If it does, don't save the card to the set
        }
        GUI.printToGUI("The definition of card:"); //Ask the user for the definition of the card
        definition = GUI.read(); //Get the definition from the user
        if (set.hasDefinition(definition)) { //Check if one of the cards in the set, already has this definition
            GUI.printToGUI("The definition \"" + definition + "\" already exists. Try again:\n");
            return; //If one of them does, don't save the card and return
        }
        set.add(term,definition);
        GUI.printToGUI(String.format("The pair (%s : %s) has been added.\n", term, definition)); //Let the user know the card was correctly added

    }

    /**
     * Method for removing a card from the set, based on its term, using the GUI to interact with the user
     */
    public void remove() {
        String term;
        GUI.printToGUI("The card: ");
        term = GUI.read(); //Ask the user for the name of the card to be removed
        if (set.hasTerm(term)) { //Search the set for the card
            set.remove(term);
            GUI.printToGUI("The card has been removed");
        } else { //If the card is not in the set, let the user know
            GUI.printToGUI("Can't remove \"" + term + "\"\n");
        }
    }

    /**
     * Method for asking the user the definition of random cards in the set. Using the GUI,
     * the user determines how many questions will be asked. If a wrong answer is right for
     * a different card, the controller lets the user know that. Finally, shows the user, the
     * correct answer, if the one given was wrong.
     */
    public void ask() {
        GUI.printToGUI("How many times?");
        int times = Integer.parseInt(GUI.read());
        for (int i = 0; i < times; i++) {
            String question  = set.getRandomCard();
            //Interactive Asking------------------------------------------------------------------------------
            GUI.printToGUI("Print the definition of \"" + question + "\":\n");
            String answer = GUI.read(); //Ask the user for the definition of the card
            if (set.ask(question,answer)) { //If the users answer was correct
                GUI.printToGUI("Correct answer.");
            } else {
                String correctAnswer = set.getDefinition(question);
                if (set.hasDefinition(answer)) {
                    String existing = set.getCardByDefinition(answer);
                    GUI.printToGUI(String.format("Wrong answer. The correct one is \"%s\", " +
                            "you've just written the definition of " +
                            "\"%s\".\n", correctAnswer, existing));
                } else {
                    GUI.printToGUI("Wrong answer. The correct one is \"" + correctAnswer + "\".");
                }
            }
        }
    }

    /**
     * Method for reading a set of cards from a text file, whose name is given by the user
     * in the GUI.
     */
    public void fromFile() {
        GUI.printToGUI("File name:");
        String fileName = GUI.read(); //Ask the user for a filename
        File file = new File(fileName); //Create a File object to pass to the set method
        fromFile(file);
    }

    /**
     * Method for reading a set of cards from a text file given the file
     * @param file File to read from
     */
    public void fromFile(File file) {
        int added = set.fromFile(file);
        GUI.printToGUI( added >= 0 ? added + " cards have been loaded." : "File not found.");
    }

    /**
     * Method to save the current set to a text file, whose name is given by the user in the GUI
     */
    public void toFile() {
        GUI.printToGUI("File Name:");
        String name = GUI.read();//Ask the user for a filename
        File writingFile = new File(name);//Create a File object to pass to the set method
        toFile(writingFile);
    }

    /**
     * Save the current set to a file given the file
     * @param file File for the set to be saved at
     */
    public void toFile(File file){
        int saved = set.toFile(file);
        GUI.printToGUI( saved >= 0 ? saved + " cards have been saved." : "Error while exporting");
    }

    /**
     * Method for getting the hardest card(s) in the set. These are the ones with more mistakes
     */
    public void getHardest() {
        String hardest = set.hardest();
        if (hardest.isEmpty()) { //If there are no cards
            GUI.printToGUI("There are no cards with errors.");
        } else {
            int biggest = set.getErrors(hardest);
            GUI.printToGUI(String.format("The hardest card is %s. You have %d errors answering it.\n", hardest, biggest));
        }

    }

    /**
     * Method for saving a log of all interaction between the app and the user. Using the GUI,
     * the user inputs the name of the text file in which the log will be saved.
     */
    public void saveLog() {
        GUI.printToGUI("File Name:");
        String logName = GUI.read(); //Ask the user for a filename
        File logFile = new File(logName); //Create a File object to pass to the set method
        GUI.saveLog(logFile);
    }

    /**
     * Reset the errors of all cards in the set (Mistakes -> 0)
     */
    public void resetErrors(){
        set.resetErrors();
        GUI.printToGUI("Card statistics has been reset.");
    }

    /**
     * Main controller method that takes a command and executes the appropriate method
     * @return True if the command is to exit the app, otherwise False
     */
    public boolean execute() {
        GUI.printToGUI("Input the action (add, remove, import, export, ask, log, hardest card, reset stats, exit):");
        boolean exit = false;
        String command = GUI.read();
        switch (command) {
            case "add" :
                add();
                break;
            case "remove" :
                remove();
                break;
            case "ask" :
                ask();
                break;
            case "import" :
                fromFile();
                break;
            case "export" :
                toFile();
                break;
            case "exit" :
                GUI.printToGUI("Bye!");
                exit = true;
                break;
            case "hardest card":
                getHardest();
                break;
            case "log":
                saveLog();
                break;
            case "reset stats":
                resetErrors();
                break;
            default:
                GUI.printToGUI("Command not valid!");
                break;
        }
        return exit;
    }

}
