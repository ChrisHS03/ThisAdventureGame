import java.util.ArrayList;

public class Player {
    private String name;
    private int healthPoints;
    private Room currentRoom;
    private ArrayList<Item> playerItems = new ArrayList<Item>();
    private Weapon equippedWeaponItem;
    private boolean dead = false;

    public Player(String name, Room startingRoom) {
        this.name = name;
        this.healthPoints = 100;
        this.currentRoom = startingRoom;
        equippedWeaponItem = null;
    }

    public String getName() {
        return name;
    }

    public boolean getDead(){
        return dead;
    }

    public void takeDamage(int input) {
        this.healthPoints = healthPoints - input;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
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
            if (healthPoints<=0){
                dead = true;
            }
            getHealth();
        }
    }

    public void equipWeapon(String weponInput) {
        String[] inputParts = weponInput.split(" ");
        if (inputParts.length < 2) {
            System.out.println("Please specify a valid wepon item.");
            return;
        }

        String wepon = inputParts[1];
        boolean weponFound = false;

        // Search for wepon in the player's inventory
        for (Item item : playerItems) {
            if (item instanceof Weapon && item.getLongname().contains(wepon)) {
                System.out.println(item.getLongname() + " is equipped");
                equippedWeaponItem = (Weapon) item;
                if (equippedWeaponItem instanceof RangedWeapon){
                    System.out.println(equippedWeaponItem.getRemainingUses() + " remaining uses");
                }
                weponFound = true;
                break;
            }
        }
        if (!weponFound) {
            System.out.println("That wepon is not nearby.");
            System.out.println("A wepon must be in your inventory before you can equip it.");
        }
    }

    public void attack(){
        if (equippedWeaponItem ==null){
            System.out.println("you cant attack with no weapon equipped!");
        } else {
            if (equippedWeaponItem instanceof RangedWeapon){
                if (equippedWeaponItem.getRemainingUses()==0){
                    System.out.println("your wepon has no more ammo! find a new wepon");
                } else {
                    if (getCurrentRoom().getEnemies().isEmpty()){
                        System.out.println("you are attacking thin air");
                        equippedWeaponItem.setRemainingUses(1);
                        System.out.println(equippedWeaponItem.getRemainingUses() + " remaining ammo");
                    } else {
                        getCurrentRoom().getEnemies().get(0).hit(equippedWeaponItem,getCurrentRoom());
                        equippedWeaponItem.setRemainingUses(1);
                        System.out.println(equippedWeaponItem.getRemainingUses() + " remaining ammo");
                        if (getCurrentRoom().getEnemies().isEmpty()){

                        } else {
                            getCurrentRoom().getEnemies().get(0).attack(this);
                        }
                    }
                }
            } else {
                if (getCurrentRoom().getEnemies().isEmpty()){
                    System.out.println("you are attacking thin air");
                } else {
                    getCurrentRoom().getEnemies().get(0).hit(equippedWeaponItem,getCurrentRoom());
                    if (getCurrentRoom().getEnemies().isEmpty()){

                    } else {
                        getCurrentRoom().getEnemies().get(0).attack(this);
                    }

                }
            }
            if (healthPoints<=0){
                dead =  true;
            }
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
        getCurrentRoom().printEnemies();
    }

    public void takeItem(String inputItem) {
        String[] inputParts = inputItem.split(" ");
        if (inputParts.length < 2) {
            System.out.println("Please specify a valid item.");
            return;
        }
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
        if (equippedWeaponItem !=null){
            System.out.println("Eqquiped wepon: " + equippedWeaponItem.getLongname());
        }
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
