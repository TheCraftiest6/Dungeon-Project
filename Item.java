public abstract class Item {
    protected String type;
    protected int uses;

    Item(){
        type = "";
        uses = 0;
    }
    Item(String type, int uses){
        this.type = type;
        this.uses=uses;
    }
    
    public String getType(){
        return type;
    }
    public int getUses(){
        return uses;
    }
    public void setType(String type){
        this.type = type;
    }
    public void setUses(int uses){
        this.uses=uses;
    }
    public String toString(){
        return type + " with " + uses + " uses";
    }

}
