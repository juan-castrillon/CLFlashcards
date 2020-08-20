package Flashcards;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class Controller {
    private CommandLineUI GUI;
    private FlashcardSet set;

    public Controller() {
        this.GUI = new CommandLineUI();
        this.set = new FlashcardSet();
    }
    public void show(String message) {
        GUI.printAndLog(message,false);
    }
    public void log(String message) {
        GUI.printAndLog(message,true);
    }
    public void add() {
        String term, definition;
        show("The card:");//Ask the user for the definition of the card
        term = GUI.read(); //Read the card's definition from console
        log(term);
        if (set.hasTerm(term)) { //Check if the set already has a card with the inputted definition
            show("The card \"" + term + "\" already exists. Try again:\n");
            return; //If it does, don't save the card to the set
        }
        show("The definition of card:"); //Ask the user for the definition of the card
        definition = GUI.read(); //Get the definition from the user
        log(definition);
        if (set.hasDefinition(definition)) { //Check if one of the cards in the set, already has this definition
            show("The definition \"" + definition + "\" already exists. Try again:\n");
            return; //If one of them does, don't save the card and return
        }
        set.add(term,definition);
        show(String.format("The pair (%s : %s) has been added.\n", term, definition)); //Let the user know the card was correctly added

    }
    public void remove() {
        String term;
        show("The card: ");
        term = GUI.read(); //Ask the user for the name of the card to be removed
        log(term);
        if (set.hasTerm(term)) { //Search the set for the card
            set.remove(term);
            show("The card has been removed");
        } else { //If the card is not in the set, let the user know
            show("Can't remove \"" + term + "\"\n");
        }
    }
    public void ask() {
        show("How many times?");
        int times = Integer.parseInt(GUI.read());
        log(times + "");
        for (int i = 0; i < times; i++) {
            String question  = set.getRandomCard();
            //Interactive Asking------------------------------------------------------------------------------
            show("Print the definition of \"" + question + "\":\n");
            String answer = GUI.read(); //Ask the user for the definition of the card
            log(answer);
            if (set.ask(question,answer)) { //If the users answer was correct
                show("Correct answer.");
                continue;
            } else {
                String correctAnswer = set.getDefinition(question);
                if (set.hasDefinition(answer)) {
                    String existing = set.getCardByDefinition(answer);
                    show(String.format("Wrong answer. The correct one is \"%s\", " +
                            "you've just written the definition of " +
                            "\"%s\".\n", correctAnswer, existing));
                } else {
                    show("Wrong answer. The correct one is \"" + correctAnswer + "\".");
                }
            }
        }
    }
    public void fromFile() {
        show("File name:");
        String fileName = GUI.read(); //Ask the user for a filename
        log(fileName);
        File file = new File(fileName); //Create a File object to pass to the set method
        int added = set.fromFile(file);
        show( added >= 0 ? added + " cards have been loaded." : "File not found.");
    }
    public void toFile() {
        show("File Name:");
        String name = GUI.read();//Ask the user for a filename
        log(name);
        File writingFile = new File(name);//Create a File object to pass to the set method
        int saved = set.toFile(writingFile);
        show( saved >= 0 ? saved + " cards have been saved." : "Error while exporting");
    }
    public void getHardest() {
        String hardest = set.hardest();
        if (hardest.isEmpty()) { //If there are no cards
            show("There are no cards with errors.");
        } else {
            int biggest = set.getErrors(hardest);
            show(String.format("The hardest card is %s. You have %d errors answering it.\n", hardest, biggest));
        }

    }
    public void saveLog() {
        show("File Name:");
        String logName = GUI.read(); //Ask the user for a filename
        log(logName);
        File logFile = new File(logName); //Create a File object to pass to the set method
        GUI.saveLog(logFile);
    }
    public void resetErrors(){
        set.resetErrors();
        show("Card statistics has been reset.");
    }

    public boolean execute() {
        boolean exit = false;
        String command = GUI.read();
        log(command);
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
                show("Bye!");
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
                show("Command not valid!");
                break;
        }
        return exit;
    }

}
