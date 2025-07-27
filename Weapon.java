import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Weapon extends Item {
    private int dmg;

    Weapon() throws FileNotFoundException{
        FileInputStream fis = new FileInputStream("weapons-1.txt");
        Scanner file = new Scanner(fis);
        ArrayList<String> weaponTypes = new ArrayList<String>();
        while(file.hasNext()){
            weaponTypes.add(file.nextLine());
        }
        type = weaponTypes.get((int)(Math.random()*weaponTypes.size()));
        uses = (int)(Math.random()*20)+10; //10-30
        dmg = (int)(Math.random()*10)+1; //1-10
    }
    Weapon(String type,int uses,int dmg){
        super(type,uses);
        this.dmg = dmg;
    }

    public int getDamage(){
        return dmg;
    }
    public void setDamage(int dmg){
        this.dmg=dmg;
    }
    @Override
    public String toString(){
        return super.toString() + " that does " + dmg + " damage.";
    }
    


}