package Flashcards;
//Imports for necessary dependencies

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;


/**
 *  Class for representing a flashcard set, it includes capabilities to store
 *  terms, definitions and mistakes made in each card and methods to interact with the class.
 */
public class FlashcardSet {
    private final Map <String, String> set; //Structure representing Term - Definition pairs
    private final Map <String, Integer> errors; //Structure relating Term - Mistakes

    /**
     * Constructor for the class
     */
    public FlashcardSet() {
        this.set = new HashMap<>(); //HashMap because guaranteed order is not required
        this.errors = new HashMap<>(); //HashMap because guaranteed order is not required
    }

    /**
     * Method that allows a Flashcard to be added to the set
     * [User Interaction Based]
     */
    public void add(String term, String definition) {
        this.set.putIfAbsent(capitalize(term),capitalize(definition)); //Save the card with its definition
        this.errors.put(capitalize(term), 0); //Initialize the new card with 0 mistakes
    }
    /**
     * Method that allows to remove a card from the set
     * [User Interaction Based]
     */
    public void remove(String term) {
        this.set.remove(term); //Remove the card from the set
        this.errors.remove(term); //Remove the card from the error counts
    }
    /**
     * Method that creates an interactive random asking sequence, to ask the user about the contents of the cards in the set.
     * The program selects random cards in the set and asks the user.
     * [User Interaction Based]
     */
    public boolean ask(String question,String answer) {
        String correctAnswer = this.set.get(question); //Get the correct answer
        int numErrors = this.errors.get(question); //Check how many mistakes does the card has already
        if (answer.equalsIgnoreCase(correctAnswer)) { //If the users answer was correct
            return true;
        } else { //If the user's answer is just wrong
            this.errors.put(question,numErrors + 1); //Update the number of errors of the card
            return false;
        }

    }
    /**
     * Method to import a set of cards from a text file
     * Each card in the file has to be formatted in the following way: "_NAME : _DEFINITION : _MISTAKES"
     * This method does not overwrite the current set if there is one, just adds additional cards
     * @param file File to import from
     */
    public int fromFile(File file) {
        try (Scanner fileReader = new Scanner(file)) { //Using a fileReader and a trying with resources construct to ensure no resource leaking
            String line;
            String[] result;
            int counter = 0; //Counter to know how many cards were imported from the file
            while (fileReader.hasNextLine()) { //Read all the lines in the file
                line = fileReader.nextLine(); //Process each line
                result = line.split(" : "); //Getting the data according to the previously specified format
                this.set.put(capitalize(result[0]),capitalize(result[1]));
                this.errors.put(capitalize(result[0]),Integer.parseInt(result[2]));
                counter++; //One more card was added
            }
            return counter;
        } catch (FileNotFoundException e) { //If the file is not found
            return -1;
        }
    }
    /**
     * Method to export the set to a text file following the format specified in the fromFile method
     * This method exports the current set, but does not reset it. In the scope of the program, more cards can be added afterwards
     * @param file Text file in which the flashcards will be written
     */
    public int toFile(File file){
        try (PrintWriter writer = new PrintWriter(file)) { //Trying with resources
            String line;
            for (String term : this.set.keySet()) { //Process each card in the set
                line = term + " : " + this.set.get(term) + " : " + this.errors.get(term);
                writer.println(line); //Write the card in the file
            }
            return this.set.size();
        } catch (FileNotFoundException e) { //If the file is not found, let the user know.
            return -1;
        }

    }
    /**
     * Method that allows to know the hardest cards in the set.
     * The hardest cards are considered to be the ones with the more mistakes
     */
    public String hardest() {
        if (this.errors.size() == 0) { //If there are no cards
            return "";
        } else { //Analise all cards in the set
            int biggest = 0;
            Set<String> keys = new HashSet<>(); //Set for storing all possible cards with the highest mistake count
            for (Integer i : this.errors.values()) { //Finding the biggest mistake count
                if (i > biggest) {
                    biggest = i;
                }
            }
            if (biggest == 0) {
                return "";
            }
            for (String key : this.errors.keySet()) { //Saving all cards, that have the highest mistake count, in the set
                if (this.errors.get(key) == biggest) {
                    keys.add("\"" + key + "\"");
                }
            }
            return keys.toString() //Remove the [] from the standard toString representation of a set
                    .replace("[", "")
                    .replace("]", "");
        }
    }
    /**
     * Method to reset all mistakes in the cards in the set.
     * Resetting means making all cards in the set have 0 mistakes
     */
    public void resetErrors() {
        this.errors.replaceAll((k, v) -> 0);
    }
    public boolean hasTerm (String term) {

        return this.set.containsKey(capitalize(term));
    }
    public boolean hasDefinition(String definition) {
        return this.set.containsValue(capitalize(definition));
    }
    public String getRandomCard() {
        Random random = new Random();
        int index = random.nextInt(this.set.size()); //Generate a random index to get a random card
        String[] keys = this.set.keySet().toArray(String[]::new); //Convert the set into an index accessible array
        return keys[index]; //Get a random card
    }
    public String getCardByDefinition (String definition) {
        definition = capitalize(definition);
        String result = "";
        for (String t1 : this.set.keySet()) {
            if(this.set.get(t1).equals(definition)) { //Search for the card that has the inputted definition, and let the user know about the mistake
                result = t1;
                break;
            }
        }
        return result;
    }
    public String getDefinition(String term) {
        return this.set.get(capitalize(term));
    }
    public int getErrors (String term) {
        term = term.split(",")[0].replaceAll("\"","");
        return this.errors.getOrDefault(term,-1);
    }
    private String capitalize(String word){
        return word.substring(0,1).toUpperCase() + word.substring(1).toLowerCase();
    }

}

