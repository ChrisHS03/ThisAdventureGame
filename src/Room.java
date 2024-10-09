import java.util.ArrayList;

public class Room {
    private String name;
    private String despriction;
    private Room north, south, east, west;
    private ArrayList<Item> roomItems = new ArrayList<Item>();
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    public Room(String name, String despriction){
        this.name = name;
        this.despriction = despriction;
    }
    public String getName(){
        return name;
    }
    public String getDespriction(){
        return despriction;
    }
    public Room getNorth(){return north;}
    public void setNorth(Room north){this.north = north;}

    public Room getSouth(){return south;}
    public void setSouth(Room south){this.south = south;}

    public Room getEast(){return east;}
    public void setEast(Room east){this.east = east;}

    public Room getWest(){return west;}
    public void setWest(Room west){this.west = west;}

    public ArrayList<Item> getRoomItems(){
        return roomItems;
    }
    public ArrayList<Enemy> getEnemies(){
        return enemies;
    }

    public void removeRoomItem(Item item){
        roomItems.remove(item);
    }
    public void addRoomItem(Item item){
        roomItems.add(item);
    }
    public void removeEnemy(Enemy enemy){
        enemies.remove(enemy);
    }

    public void addEnemy(Enemy enemy){
        enemies.add(enemy);
    }

    public void printRoomItems(){
        for (Item i : roomItems){
            System.out.println("item: " + i.getLongname());
        }
    }
    public void printEnemies(){
        for (Enemy i : enemies){
            System.out.println("enemy: " + i.getName());
        }
    }
}
