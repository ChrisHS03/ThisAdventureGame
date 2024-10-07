public class RangedWepon extends Wepon{

    private int ammo;

    public RangedWepon(String longname, String shortname, int damage, int ammo){
        super(longname, shortname, damage);
        this.ammo = ammo;
    }

    @Override
    public int getRemainingUses(){
        return ammo;
    }

    @Override
    public void setRemainingUses(int input){
        ammo = ammo-input;
    }
}
