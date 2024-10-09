public abstract class Weapon extends Item{

    private int damage;

    public Weapon(String longname, String shortname, int damage){
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
