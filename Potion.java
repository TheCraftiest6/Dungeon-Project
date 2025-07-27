import java.util.Random;

public class Potion extends Item {
    private int quality;
    private final Random random = new Random();

    Potion(){
        if(random.nextBoolean()){
            type = "Healing";
        }
        else{
            type = "Repairing";
        }
        uses = (int)(Math.random()*10)+1; //1-10
        quality = (int)(Math.random()*10)+1; //1-10
    }
    Potion(String type,int uses,int quality){
        super(type,uses);
        this.quality = quality;
    }

    public int getQuality(){
        return quality;
    }
    @Override
    public String toString(){
        return type + " potion, quality " + quality + " with " + uses + " uses";
    }
   
    
}
