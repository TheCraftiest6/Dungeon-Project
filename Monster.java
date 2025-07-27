import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Monster {
    private String type;
    private int health;
    private Weapon weapon;

    Monster() throws FileNotFoundException{
        Scanner file = new Scanner(new FileInputStream("monsters-1.txt"));
        ArrayList<String> monsterTypes = new ArrayList<String>();
        while(file.hasNext()){
            monsterTypes.add(file.nextLine());
        }
        type = monsterTypes.get((int)(Math.random()*monsterTypes.size()));
        health = (int)(Math.random()*10)+10; //10-20
        weapon = new Weapon();
    }
    Monster(String type, int health, Weapon weapon){
        this.type = type;
        this.health = health;
        this.weapon = weapon;
    }
    
    public String getMonsterType(){
        return type;
    }
    public int getMonsterHealth(){
        return health;
    }
    public Weapon getMonsterWeapon(){
        return weapon;
    }
    public String toString(){
        return type + " with " + health + " health, weilding a " + weapon.getType();
    }
}
