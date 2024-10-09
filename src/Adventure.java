import java.util.HashSet;

public class Adventure {
    private Player player;
    private Map map;

    public Adventure(String playerName) {
        map = new Map();
        player = new Player(playerName, map.getRoom("1.Dusty Foyer"));
    }

    public void getHealth(){
        getPlayer().getHealth();
    }
    public void eat(String input){
        getPlayer().eat(input);
    }

    public void equipWeapon(String input){
        getPlayer().equipWeapon(input);
    }
    public void attack(){
        getPlayer().attack();
    }

    public void takeItem(String inputItem){
        getPlayer().takeItem(inputItem);
    }

    public void dropItem(String item){
        getPlayer().dropItem(item);
    }

    public void printPlayerItem(){
        player.printPlayerItem();
    }

    public void move(String direction) {
        player.move(direction);
    }

    public void lookAround() {
        player.lookAround();
    }
    public Player getPlayer(){
        return player;
    }

    @Override
    public String toString() {
        return player.getCurrentRoom().getName() + ", " + player.getCurrentRoom().getDespriction();
    }
}
