/**
 * Created by Joe on 5/2/2018.
 * Data collection class to hold a card/relic and the amount of times it's been seen in a single object.
 */
public class Item {

    private String name;
    private int quantity; // Amount of copies of this item seen by all runs
    private int totalGames;

    public Item(String name, int quantity){
        this.name = name;
        this.quantity = quantity;
        totalGames = 0;
    }

    public void setTotalGames(int games){
        totalGames = games;
    }

    public String getName(){
        return name;
    }

    public int getQuantity(){
        return quantity;
    }

    public void incrementQuantity(){
        quantity++;
    }

    public float getAverageShowing(){
        return ((float)quantity / totalGames) * 100;
    }

    @Override
    public String toString(){
        if(totalGames == 0){
            return name + ": " + quantity;
        }
        return name + ": " + DataAnalyzer.round(getAverageShowing());
    }


}
