public class Enemy {
    private String name;
    private int health;
    private Weapon weapon;

    public Enemy(String name, int health, Weapon weapon){
        this.name = name;
        this.health = health;
        this.weapon = weapon;
    }

    public String getName(){
        return name;
    }

    public void takeDamage(int damage){
        this.health = health - damage;
    }

    public void attack(Player player){
        player.takeDamage(weapon.getDamage());
        System.out.println(name + " attacked you for " + weapon.getDamage() + " damage");
    }

    public void hit(Weapon playerWeapon, Room room){
        takeDamage(playerWeapon.getDamage());
        System.out.println(name + " has taken " + playerWeapon.getDamage() + " damage");
        if (health<=0){
            System.out.println("you have slain " + name + ", " + weapon.getLongname() + " has been dropped");
            room.addRoomItem(weapon);
            room.removeEnemy(this);
        }
    }
}
