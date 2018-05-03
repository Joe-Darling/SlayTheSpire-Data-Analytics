import java.util.Comparator;

/**
 * Created by Joe on 5/2/2018.
 */
public class ItemComparator implements Comparator<Item> {

    @Override
    public int compare(Item item1, Item item2) {
        if(item1.getQuantity() > item2.getQuantity()){
            return -1;
        }
        else if(item1.getQuantity() == item2.getQuantity()){
            return 0;
        }
        else{
            return 1;
        }
    }
}
