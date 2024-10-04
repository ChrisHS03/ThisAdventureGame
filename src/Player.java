import java.util.ArrayList;

public class Player {
    private String name;
    private int healthPoints;
    private Room currentRoom;
    private ArrayList<Item> playerItems = new ArrayList<Item>();

    public Player(String name, Room startingRoom) {
        this.name = name;
        this.healthPoints = 100;
        this.currentRoom = startingRoom;
    }

    public String getName() {
        return name;
    }

    public void setHealthPoints(int input) {
        healthPoints = input;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void getHealth() {
        if (healthPoints < 40) {
            System.out.println("Hp: " + healthPoints + ". You are not looking good, maybe eat something yummy before fighting");
        } else if (healthPoints >= 40 && healthPoints < 70) {
            System.out.println("Hp: " + healthPoints + ". You are a little beaten up, but maybe still up for a fight");
        } else {
            System.out.println("Hp: " + healthPoints + ". You are looking very fit for at battle");
        }
    }

    public void eat(String foodInput) {
        // Safeguard to avoid ArrayIndexOutOfBoundsException
        String[] inputParts = foodInput.split(" ");
        if (inputParts.length < 2) {
            System.out.println("Please specify a valid food item.");
            return;
        }

        String food = inputParts[1];
        boolean foodFound = false;

        // Search for food in the player's inventory
        for (Item item : playerItems) {
            if (item instanceof Food && item.getLongname().contains(food)) {
                consumeFood((Food) item);
                playerItems.remove(item);
                foodFound = true;
                break;
            }
        }

        // If food is not found in the player's inventory, search in the room's items
        if (!foodFound) {
            for (Item item : getCurrentRoom().getRoomItems()) {
                if (item instanceof Food && item.getLongname().contains(food)) {
                    consumeFood((Food) item);
                    getCurrentRoom().getRoomItems().remove(item);
                    foodFound = true;
                    break;
                }
            }
        }

        if (!foodFound) {
            System.out.println("That food is not nearby.");
        }
    }

    // Helper method to apply the food effect
    private void consumeFood(Food foodItem) {
        int change = foodItem.getFoodHp();

        if (change > 0) {
            // Positive food effect
            if (healthPoints + change > 100) {
                setHealthPoints(100);
            } else {
                setHealthPoints(healthPoints + change);
            }
            System.out.println("That was yummy!");
            getHealth();
        } else {
            // Negative food effect
            setHealthPoints(healthPoints + change);  // Handles both positive and negative changes
            System.out.println("That was disgusting!");
            getHealth();
        }
    }

public void setCurrentRoom(Room room) {
    this.currentRoom = room;
}

public void removePlayerItem(Item item) {
    playerItems.remove(item);
}

public void addPlayerItem(Item item) {
    playerItems.add(item);
}

public void move(String direction) {
    Room currentRoom = getCurrentRoom();
    Room nextRoom = null;

    switch (direction) {
        case "north":
            nextRoom = currentRoom.getNorth();
            break;
        case "south":
            nextRoom = currentRoom.getSouth();
            break;
        case "east":
            nextRoom = currentRoom.getEast();
            break;
        case "west":
            nextRoom = currentRoom.getWest();
            break;
    }

    if (nextRoom != null) {
        setCurrentRoom(nextRoom);
        lookAround();
    } else {
        System.out.println("You cannot go that way");
    }
}

public void lookAround() {
    Room currentRoom = getCurrentRoom();
    System.out.println(currentRoom.getName() + ", " + currentRoom.getDespriction());
    getCurrentRoom().printRoomItems();
}

public void takeItem(String inputItem) {
    String item = inputItem.split(" ")[1];

    for (int i = 0; i < getCurrentRoom().getRoomItems().size(); i++) {

        if (getCurrentRoom().getRoomItems().get(i).getLongname().contains(item)) {
            addPlayerItem(getCurrentRoom().getRoomItems().get(i));
            System.out.println("The " + getCurrentRoom().getRoomItems().get(i).getLongname() + " is added to your inventory");
            getCurrentRoom().removeRoomItem(getCurrentRoom().getRoomItems().get(i));

        } else {
            System.out.println("");
        }
    }
}

public void printPlayerItem() {
    int counter = 0;
    if (!playerItems.isEmpty())
        for (Item i : playerItems) {
            counter++;
            System.out.println(counter + ". " + i.getLongname());
        }
    else {
        System.out.println("You have no items");
    }
}

public ArrayList getPlayerItem() {
    return playerItems;
}

public void dropItem(String inputItem) {
    String item = inputItem.split(" ")[1];

    for (int i = 0; i < playerItems.size(); i++) {

        if (playerItems.get(i).getLongname().contains(item)) {
            getCurrentRoom().addRoomItem(playerItems.get(i));
            System.out.println(playerItems.get(i).getLongname() + " has been dropped");
            removePlayerItem(playerItems.get(i));
        } else {
            System.out.println("");
        }
    }
}


}
