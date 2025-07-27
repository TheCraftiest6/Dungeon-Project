import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws FileNotFoundException{
        Scanner scnr = new Scanner(System.in);
        Dungeon dun = new Dungeon();

        System.out.println("Would you like to generate or load a dungeon?");
        System.out.println("Type 'generate' or 'load'");
        String input = scnr.next();
        
        if(input.equalsIgnoreCase("generate")){
            System.out.println("Enter filename to create");
            input = scnr.next();
            dun.generate();
            dun.print();
            dun.save(input);
        }
        else if(input.equalsIgnoreCase("load")){
            System.out.println("Enter filename to load");
            input = scnr.next();
            dun.load(input);
        }


        
        

    }
    
}
