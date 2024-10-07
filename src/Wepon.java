public class Wepon extends Item{

    private int damage;

    public Wepon(String longname, String shortname, int damage){
        super(longname, shortname);
        this.damage = damage;
    }

    public int getRemainingUses(){
        return 0;
    }

    public void setRemainingUses(int input){
    }

    public int getDamage(){
        return damage;
    }
}
