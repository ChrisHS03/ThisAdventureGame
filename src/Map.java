import java.util.HashMap;

public class Map {
    private HashMap<String, Room> rooms;

    public Map() {
        rooms = new HashMap<>();
        setupRooms();
    }

    private void setupRooms() {
        Room room1 = new Room("1.Dusty Foyer", "A grand chandelier hangs precariously from\nthe ceiling, " +
                "its crystals dim and dusty. The floorboards creak with each step,\n" +
                "echoing through the empty, cold space.");
        Room room2 = new Room("2.Creepy Library", "Tall shelves packed with ancient, musty books.\n" +
                "A large armchair by the window seems to have been recently occupied,\n" +
                "though no one is in sight.");
        Room room3 = new Room("3.Shadowy Hallway", "Flickering candle sconces cast long,\n" +
                "wavering shadows along the walls. The air feels thick, and whispers\n" +
                "seem to follow you, though no one else is there.");
        Room room4 = new Room("4.Abandoned Kitchen", "Rusty pots and pans hang from the ceiling,\n" +
                "swaying gently as if touched by unseen hands. The scent of stale bread\n" +
                "and old decay lingers in the air.");
        Room room5 = new Room("5.Chilled Basement", "The stairs creak as you descend into darkness.\n" +
                " A single, bare light bulb flickers above, revealing dusty crates, a broken\n" +
                "rocking chair, and a sense of dread that lingers in the cold, damp air.");
        Room room6 = new Room("6.Gloomy Bedroom", "An old canopy bed dominates the room, its\n" +
                " curtains tattered and yellowed with age. A broken mirror on the dresser\n" +
                "reflects an empty room, but the air feels thick with someone—or\n" +
                "something—watching.");
        Room room7 = new Room("7.Creaking Attic", "The low ceiling forces you to crouch as you\n" +
                "navigate through piles of forgotten trunks and old furniture covered in white\n" +
                "sheets. The only sound is the slow, steady drip of water from somewhere above.");
        Room room8 = new Room("8.Rotted Conservatory", "Overgrown vines twist around shattered\n" +
                "windows, and the faint smell of decaying plants fills the air. A grand piano\n" +
                "sits in the corner, keys stained and yellowed, as if waiting for ghostly\n" +
                " fingers to play.");
        Room room9 = new Room("9.Ghostly Dining Room", "A long, dust-covered table stretches\n" +
                "out under a tarnished chandelier. The chairs are pushed back, as if the\n" +
                "occupants had left in a hurry. The faint sound of clinking glasses and\n" +
                "murmured voices lingers, then vanishes as quickly as it appeared.");

        room1.setEast(room2);
        room1.setSouth(room4);

        room2.setWest(room1);
        room2.setEast(room3);

        room3.setWest(room2);
        room3.setSouth(room6);

        room4.setNorth(room1);
        room4.setSouth(room7);

        room5.setSouth(room8);

        room6.setNorth(room3);
        room6.setSouth(room9);

        room7.setNorth(room4);
        room7.setEast(room8);

        room8.setWest(room7);
        room8.setNorth(room5);
        room8.setEast(room9);

        room9.setNorth(room6);
        room9.setWest(room8);

        // Add rooms to the map
        rooms.put("1.Dusty Foyer", room1);
        rooms.put("2.Creepy Library", room2);
        rooms.put("3.Shadowy Hallway", room3);
        rooms.put("4.Abandoned Kitchen", room4);
        rooms.put("5.Chilled Basement", room5);
        rooms.put("6.Gloomy Bedroom", room6);
        rooms.put("7.Creaking Attic", room7);
        rooms.put("8.Rotted Conservatory", room8);
        rooms.put("9.Ghostly Dining Room", room9);
    }

    public Room getRoom(String roomName) {
        return rooms.get(roomName);
    }
}
