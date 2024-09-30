import java.util.HashSet;
import java.util.Objects;

public class Adventure {
    private Player player;
    private Map map;
    private HashSet<String> discoveredRooms;

    public Adventure(String playerName) {
        map = new Map();
        player = new Player(playerName, map.getRoom("1.Dusty Foyer"));
        discoveredRooms = new HashSet<>();
        discoveredRooms.add(player.getCurrentRoom().getName()); // Starting room is discovered

    }

    public void move(String direction) {
        Room currentRoom = player.getCurrentRoom();
        Room nextRoom = null;

        switch (direction) {
            case "north": nextRoom = currentRoom.getNorth(); break;
            case "south": nextRoom = currentRoom.getSouth(); break;
            case "east": nextRoom = currentRoom.getEast(); break;
            case "west": nextRoom = currentRoom.getWest(); break;
        }

        if (nextRoom != null) {
            player.setCurrentRoom(nextRoom);
            discoveredRooms.add(nextRoom.getName());
            lookAround();
        } else {
            System.out.println("You cannot go that way");
        }
    }

    public void getMap() {
        char c = player.getCurrentRoom().getName().charAt(0);
        String defaultRoom = "?????";

        String room1 = discoveredRooms.contains("1") ? "Dusty Foyer" : defaultRoom;
        String room2 = discoveredRooms.contains("2") ? "Creepy Library" : defaultRoom;
        String room3 = discoveredRooms.contains("3") ? "Shadowy Hallway" : defaultRoom;
        String room4 = discoveredRooms.contains("4") ? "Abandoned Kitchen" : defaultRoom;
        String room5 = discoveredRooms.contains("5") ? "Chilled Basement" : defaultRoom;
        String room6 = discoveredRooms.contains("6") ? "Gloomy Bedroom" : defaultRoom;
        String room7 = discoveredRooms.contains("7") ? "Creaking Attic" : defaultRoom;
        String room8 = discoveredRooms.contains("8") ? "Rotted Conservatory" : defaultRoom;
        String room9 = discoveredRooms.contains("9") ? "Ghostly Dining Room" : defaultRoom;

        // Uppercase the current room
        switch (c) {
            case '1': room1 = room1.toUpperCase(); break;
            case '2': room2 = room2.toUpperCase(); break;
            case '3': room3 = room3.toUpperCase(); break;
            case '4': room4 = room4.toUpperCase(); break;
            case '5': room5 = room5.toUpperCase(); break;
            case '6': room6 = room6.toUpperCase(); break;
            case '7': room7 = room7.toUpperCase(); break;
            case '8': room8 = room8.toUpperCase(); break;
            case '9': room9 = room9.toUpperCase(); break;
            default:
                System.out.println();
        }

        int maxLength = 20;
        room1 = centerText(room1, maxLength);
        room2 = centerText(room2, maxLength);
        room3 = centerText(room3, maxLength);
        room4 = centerText(room4, maxLength);
        room5 = centerText(room5, maxLength);
        room6 = centerText(room6, maxLength);
        room7 = centerText(room7, maxLength);
        room8 = centerText(room8, maxLength);
        room9 = centerText(room9, maxLength);

        System.out.println("Your location is shown by uppercase\n");
        System.out.println("[" + room1 + "] -- [" + room2 + "] -- [" + room3 + "]");
        System.out.println("          |                                                   |   ");
        System.out.println("[" + room4 + "]    [" + room5 + "]    [" + room6 + "]");
        System.out.println("          |                         |                         |   ");
        System.out.println("[" + room7 + "] -- [" + room8 + "] -- [" + room9 + "]");
    }

    private String centerText(String text, int width) {
        if (text.length() >= width) {
            return text.substring(0, width); // Truncate if the text is longer than the max length
        }
        int padding = (width - text.length()) / 2;
        String paddingStr = " ".repeat(padding);
        // Add spaces equally on both sides
        return paddingStr + text + paddingStr + (text.length() % 2 == 0 ? "" : " "); // Ensures even centering
    }

    private void lookAround() {
        Room currentRoom = player.getCurrentRoom();
        System.out.println(currentRoom.getName() + ", " + currentRoom.getDespriction());
        player.getCurrentRoom().printRoomItems();
    }
    public Player getPlayer(){
        return player;
    }


    @Override
    public String toString() {
        return player.getCurrentRoom().getName() + ", " + player.getCurrentRoom().getDespriction();
    }
}
