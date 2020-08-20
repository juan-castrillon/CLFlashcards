package Flashcards;

public class App {
    public static void main(String[] args) {
        Controller controller = new Controller();
        boolean exit = false;
        while (!exit) {
            controller.show("Input the action (add, remove, import, export, ask, log, hardest card, reset stats, exit):");
            exit = controller.execute();
        }
    }
}
