import java.io.*;
import java.util.*;

public class Dungeon{
    private Room[][] dungeon = new Room[10][10];
    private final Random random = new Random();

    public void generate() throws FileNotFoundException{
        System.out.println("Generating dungeon...");
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                dungeon[i][j] = new Room();
            }
        }

        int row = 5;
        int col = 5;
        int r;
        dungeon[row][col].setIsSolid(false);
        int count = (int) (Math.random()*40)+10;
        while(count > 0){
            r = (int)(Math.random()*4);
            if(r == 0 && row < 9){
                row++;
            }
            else if(r == 1 && row > 0){
                row--;
            }
            else if(r == 2 && col < 9){
                col++;
            }
            else if(r == 3 && col > 0){
                col--;
            }

            if(dungeon[row][col].getIsSolid()){
                dungeon[row][col].setIsSolid(false);
                //add in items and monsters if theres a room
                int amount = (int)(Math.random()*4);
                for(int i = 0; i<amount;i++){
                    dungeon[row][col].addMonster(new Monster());
                }
                amount = (int)(Math.random()*4);
                for(int i=0;i<amount;i++){
                    if (random.nextBoolean()){
                        dungeon[row][col].addItem(new Weapon());
                    }
                    else{
                        dungeon[row][col].addItem(new Potion());
                    }
                }
                count--;
            }  
        }

    }

    public void save(String s){
        try{
            FileOutputStream fos = new FileOutputStream(s);
            PrintWriter output = new PrintWriter(fos);
            
            ArrayList<Monster> monsters = new ArrayList<Monster>();
            ArrayList<Integer> monsterX = new ArrayList<Integer>();
            ArrayList<Integer> monsterY = new ArrayList<Integer> ();
            ArrayList<Item> items = new ArrayList<Item>();
            ArrayList<Integer> itemX = new ArrayList<Integer>();
            ArrayList<Integer> itemY = new ArrayList<Integer> ();

            for(int i=0;i<10;i++){
                for(int j=0;j<10;j++){
                    if(dungeon[i][j].getIsSolid()){
                        output.print("\u25a0");
                    }
                    else{
                    output.print("\u25a1");
                    ArrayList<Monster> mon = dungeon[i][j].getMonsters();
                    for(Monster m : mon){
                        monsters.add(m);
                        monsterX.add(j);
                        monsterY.add(i);
                    }
                    ArrayList<Item> items2 = dungeon[i][j].getItems();
                    for(Item it: items2){
                        items.add(it);
                        itemX.add(j);
                        itemY.add(i);
                    }
                    }
                }
                output.println("");
            }
            output.println("---");
            for(int i = 0;i<monsters.size();i++){
                output.println( "Monster," + monsterX.get(i)+ "," + monsterY.get(i) + "," + monsters.get(i).getMonsterType() + "," + monsters.get(i).getMonsterHealth()+ ","+monsters.get(i).getMonsterWeapon().getType());
            }
            for(int i = 0;i<items.size();i++){
                if(items.get(i) instanceof Potion){
                    output.println("Potion," + itemX.get(i)+","+itemY.get(i)+","+items.get(i).getType()+","+items.get(i).getUses()+","+((Potion)items.get(i)).getQuality());
                }
                else if(items.get(i) instanceof Weapon){
                    output.println("Weapon," + itemX.get(i)+","+itemY.get(i)+","+items.get(i).getType()+","+items.get(i).getUses()+","+((Weapon)items.get(i)).getDamage());
                }
            } 
            
            output.close();
            System.out.println("Saved to " + s);
        }
        catch(FileNotFoundException e){
            System.out.println(s + "not found!");
        }
    }

    public void load(String f){
        try{
            FileInputStream fis = new FileInputStream(f);
            Scanner file = new Scanner(fis);
            
            System.out.println("Loading file " + f);

            for(int i=0; i<10; i++){
                String line = file.next();
                for(int j=0; j<10; j++){
                    Room r = new Room();
                    if(line.charAt(j) == ('\u25a1')){
                        r.setIsSolid(false);
                    }
                    dungeon[i][j]= r;
                }
            }
            file.next();
            while(file.hasNext()){
                try{
                    String[] line = file.nextLine().split(",");
                    int x = Integer.parseInt(line[1]);
                    int y = Integer.parseInt(line[2]);
                    if(line[0].equals("Monster")){
                        Weapon w = new Weapon();
                        w.setType(line[5]);
                        Monster m = new Monster(line[3],Integer.parseInt(line[4]),w);
                        dungeon[y][x].addMonster(m);
                    }
                    else if (line[0].equals("Potion")){
                        Potion p = new Potion(line[3],Integer.parseInt(line[4]),Integer.parseInt(line[5]));
                        dungeon[y][x].addItem(p);
                    }
                    else if (line[0].equals("Weapon")){
                        Weapon w = new Weapon(line[3],Integer.parseInt(line[4]),Integer.parseInt(line[5]));
                        dungeon[y][x].addItem(w);
                    } 
                }
                catch(IndexOutOfBoundsException e){

                }
                
            }   
            print();
        }
        catch(FileNotFoundException e){
            System.out.println(f + "not found!");
        }

    }

    public void print(){
        ArrayList<Monster> monsters = new ArrayList<Monster>();
        ArrayList<Integer> monsterX = new ArrayList<Integer>();
        ArrayList<Integer> monsterY = new ArrayList<Integer> ();
        ArrayList<Item> items = new ArrayList<Item>();
        ArrayList<Integer> itemX = new ArrayList<Integer>();
        ArrayList<Integer> itemY = new ArrayList<Integer> ();
        System.out.println("Layout");
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                if(dungeon[i][j].getIsSolid()){
                    System.out.print("\u25a0");
                }
                else{
                    System.out.print("\u25a1");
                    ArrayList<Monster> mon = dungeon[i][j].getMonsters();
                    for(Monster m : mon){
                        monsters.add(m);
                        monsterX.add(j);
                        monsterY.add(i);
                    }
                    ArrayList<Item> items2 = dungeon[i][j].getItems();
                    for(Item it: items2){
                        items.add(it);
                        itemX.add(j);
                        itemY.add(i);
                    }
                }
            }
            System.out.println("");
        }
        System.out.println("Monsters:");
        for(int i = 0;i<monsters.size();i++){
            System.out.println( "[" + monsterX.get(i)+ ", " + monsterY.get(i) + "]: " + monsters.get(i));
        }
        System.out.println("Items:");
        for(int i = 0;i<items.size();i++){
            System.out.println( "[" + itemX.get(i)+ ", " + itemY.get(i) + "]: " + items.get(i));
        }
    }

}