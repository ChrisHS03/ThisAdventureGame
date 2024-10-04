import java.util.Scanner;

public class Userinterface {
    public void display() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String playerName = input.nextLine();

        Adventure adventure = new Adventure(playerName);
        boolean active = true;
        System.out.println("Welcome, " + playerName + "!");
        System.out.println("Commands:\nGo north, Go south, Go east, Go west, Look, Help, Exit\n");
        System.out.println("Play!");

        while (active) {
            String userInput = input.nextLine().toLowerCase();

            if (userInput.contains("go north") || userInput.equals("n")) {
                System.out.println("going north");
                adventure.move("north");
            } else if (userInput.contains("go south") || userInput.equals("s")) {
                System.out.println("going south");
                adventure.move("south");
            } else if (userInput.contains("go east") || userInput.equals("e")) {
                System.out.println("going east");
                adventure.move("east");
            } else if (userInput.contains("go west") || userInput.equals("w")) {
                System.out.println("going west");
                adventure.move("west");
            } else if (userInput.contains("look")) {
                System.out.println("looking around");
                adventure.lookAround();
            } else if (userInput.contains("help")) {
                System.out.println("helping");
                System.out.println("Commands:\nGo north, Go south, Go east, Go west, Look, Help, Exit\n");
                System.out.println("Use Go north, Go south, Go east & Go west to move around" +
                        "\nUse Look to get some information about the room you are in\nUse Help to get some help" +
                        "\nUse Exit to exit the game");
            } else if (userInput.contains("exit")) {
                System.out.println("Exiting");
                active = false;
            } else if (userInput.contains("take")){
                adventure.takeItem(userInput);
            } else if (userInput.contains("inventory")){
                adventure.printPlayerItem();
            } else if (userInput.contains("drop")){
                adventure.dropItem(userInput);
            } else if (userInput.contains("health")){
                adventure.getHealth();
            } else if (userInput.contains("eat")){
                adventure.eat(userInput);
            } else {
                System.out.println("invalid command");
            }
        }
        input.close();
    }
}
