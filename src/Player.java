import java.util.ArrayList;

public class Player {
    private String name;
    private Room currentRoom;
    private ArrayList<Item> playerItems = new ArrayList<Item>();

    public Player(String name, Room startingRoom) {
        this.name = name;
        this.currentRoom = startingRoom;
    }

    public String getName() {
        return name;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    public void removePlayerItem(Item item){
        playerItems.remove(item);
    }
    public void addPlayerItem(Item item){
        playerItems.add(item);
    }
    public ArrayList<Item> getPlayerItems(){
        return playerItems;
    }

    public void takeItem(String item){
        item = item.substring(5,item.length());

        for (int i = 0; i < getCurrentRoom().getRoomItems().size() ;i++){

            if (getCurrentRoom().getRoomItems().get(i).getShortname().contains(item)){
                addPlayerItem(getCurrentRoom().getRoomItems().get(i));
                System.out.println("The " + getCurrentRoom().getRoomItems().get(i).getLongname() + " is added to your inventory");
                getCurrentRoom().removeRoomItem(getCurrentRoom().getRoomItems().get(i));

            } else {
                System.out.println("womp womp");
            }
        }
    }
    public void printPlayerItem(){
        int counter = 0;
        for (Item i : playerItems){
            counter++;
            System.out.println(counter + ". " + i.getLongname());
        }
    }
    public void dropItem(String item){
        item = item.substring(5,item.length());

        for (int i = 0; i < playerItems.size() ;i++){

            if (playerItems.get(i).getShortname().contains(item)){
                getCurrentRoom().addRoomItem(playerItems.get(i));
                System.out.println(playerItems.get(i).getLongname() + " has been dropped");
                removePlayerItem(playerItems.get(i));
            } else {
                System.out.println("womp womp");
            }
        }
    }


}
