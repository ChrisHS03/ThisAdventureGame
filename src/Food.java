public class Food extends Item{
    private int foodHp;

    public Food(String longname, String shortname, int hp){
        super(longname, shortname);
        this.foodHp = hp;
    }

    public int getFoodHp(){
        return foodHp;
    }
}
