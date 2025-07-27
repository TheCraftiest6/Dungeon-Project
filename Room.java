import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Room {
    private boolean isSolid;
    private ArrayList<Item> items = new ArrayList<Item>();
    private ArrayList<Monster> monsters = new ArrayList<Monster>();

    public Room() throws FileNotFoundException{
        isSolid = true;
    }
    public Room(boolean isSolid){
        this.isSolid = isSolid;
    }

    public void setIsSolid(boolean isSolid){
        this.isSolid = isSolid;
    }
    public boolean getIsSolid(){
        return isSolid;
    }
    public void addMonster(Monster m){
        monsters.add(m);
    }
    public void addItem(Item i){
        items.add(i);
    }
    public ArrayList<Monster> getMonsters(){
        return monsters;
    }
    public ArrayList<Item> getItems(){
        return items;
    }
}
