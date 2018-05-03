import java.util.ArrayList;

/**
 * Created by Joe on 4/20/2018.
 */
public class Run {
    private ArrayList path_per_floor;
    private String character_chosen;
    private ArrayList items_purchased;
    private ArrayList gold_per_floor;
    private int floor_reached;
    private int campfire_rested;
    private int playTime;
    private ArrayList current_hp_per_floor;
    private ArrayList items_purged;
    private int gold;
    private int campfire_rituals;
    private int campfire_upgraded;
    private boolean is_hardmode;
    private ArrayList path_taken;
    private int purchased_purges;
    private boolean victory;
    private ArrayList master_deck;
    private ArrayList max_hp_per_floor;
    private ArrayList relics;
    private int campfire_meditates;
    private ArrayList card_choices;
    private ArrayList potions_floor_usage;
    private ArrayList damage_taken;
    private ArrayList boss_relics;
    private ArrayList potion_floor_spawned;
    private String seed;
    private String killed_by;
    private boolean is_trial;
    private boolean is_ascension_mode;


    public ArrayList getPath_per_floor() {
        return path_per_floor;
    }

    public String getCharacter_chosen() {
        return character_chosen;
    }

    public ArrayList getItems_purchased() {
        return items_purchased;
    }

    public ArrayList getGold_per_floor() {
        return gold_per_floor;
    }

    public int getFloor_reached() {
        return floor_reached;
    }

    public int getCampfire_rested() {
        return campfire_rested;
    }

    public int getPlayTime() {
        return playTime;
    }

    public ArrayList getCurrent_hp_per_floor() {
        return current_hp_per_floor;
    }

    public ArrayList getItems_purged() {
        return items_purged;
    }

    public int getGold() {
        return gold;
    }

    public int getCampfire_rituals() {
        return campfire_rituals;
    }

    public int getCampfire_upgraded() {
        return campfire_upgraded;
    }

    public boolean isIs_hardmode() {
        return is_hardmode;
    }

    public ArrayList getPath_taken() {
        return path_taken;
    }

    public int getPurchased_purges() {
        return purchased_purges;
    }

    public boolean isVictory() {
        return victory;
    }

    public boolean isIs_ascension_mode(){
        return is_ascension_mode;
    }

    public ArrayList getMaster_deck() {
        return master_deck;
    }

    public ArrayList getMax_hp_per_floor() {
        return max_hp_per_floor;
    }

    public ArrayList getRelics() {
        return relics;
    }

    public int getCampfire_meditates() {
        return campfire_meditates;
    }

    public ArrayList getCard_choices() {
        return card_choices;
    }

    public ArrayList getPotions_floor_usage() {
        return potions_floor_usage;
    }

    public ArrayList getDamage_taken() {
        return damage_taken;
    }

    public ArrayList getBoss_relics() {
        return boss_relics;
    }

    public ArrayList getPotion_floor_spawned() {
        return potion_floor_spawned;
    }

    public String getSeed() {
        return seed;
    }

    public String getKilled_by() {
        return killed_by;
    }

    public boolean isIs_trial() {
        return is_trial;
    }

    @Override
    public String toString(){
        return "not setup yet";
    }
}
