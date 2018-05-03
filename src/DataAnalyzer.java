import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Joe on 4/20/2018.
 * This class just contains a bunch of functions that can be used on run data to get certain analytics.
 */
public class DataAnalyzer {

    public static float round(float d) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    List<Run> getWinningRuns(List<Run> runs){
        List<Run> result = new ArrayList<>();
        for(Run run : runs){
            if(run.isVictory()){
                result.add(run);
            }
        }

        return result;
    }

    List<Run> getLosingRuns(List<Run> runs){
        List<Run> result = new ArrayList<>();
        for(Run run : runs){
            if(!run.isVictory()){
                result.add(run);
            }
        }

        return result;
    }

    List<Run> getRunsThatMadeItToLevel3(List<Run> runs){
        List<Run> result = new ArrayList<>();
        for(Run run : runs){
            if(run.getFloor_reached() > 32){
                result.add(run);
            }
        }

        return result;
    }

    List<Run> getAscensionRuns(List<Run> runs){
        List<Run> result = new ArrayList<>();
        for(Run run : runs){
            if(run.isIs_ascension_mode()){
                result.add(run);
            }
        }

        return result;
    }

    List<Run> getRunsWithMoreThan30Cards(List<Run> runs){
        List<Run> result = new ArrayList<>();
        for(Run run : runs){
            if(run.getMaster_deck().size() >= 30){
                result.add(run);
            }
        }

        return result;
    }

    float winPercentage(List<Run> runs){
        List<Run> winningGames = getWinningRuns(runs);

        float gamesWon = winningGames.size();
        float totalGames = runs.size();
        return (gamesWon/totalGames) * 100;
    }

    float averageDeckSize(List<Run> runs){
        float total = 0;
        float count = 0;
        for(Run run : runs){
            count++;
            total += run.getMaster_deck().size();
        }

        return total / count;
    }

    float averageRelicCount(List<Run> runs){
        float total = 0;
        float count = 0;
        for(Run run : runs){
            count++;
            total += run.getRelics().size();
        }

        return total / count;
    }

    float averagePotionUsage(List<Run> runs){
        float total = 0;
        float count = 0;
        for(Run run : runs){
            count++;
            total += run.getPotions_floor_usage().size();
        }

        return total / count;
    }

    float potionSpawn(List<Run> runs){
        float total = 0;
        float count = 0;
        for(Run run : runs){
            count++;
            if(run.getPotion_floor_spawned() != null) {
                total += run.getPotion_floor_spawned().size();
            }
        }

        return total / count;
    }

    float averageCampfireRests(List<Run> runs){
        float total = 0;
        float count = 0;
        for(Run run : runs){
            count++;
            total += run.getCampfire_rested();
        }

        return total / count;
    }

    float averageCampfireUpgrades(List<Run> runs){
        float total = 0;
        float count = 0;
        for(Run run : runs){
            count++;
            total += run.getCampfire_upgraded();
        }

        return total / count;
    }

    float mysteryRoomsEntered(List<Run> runs){
        float total = 0;
        float count = 0;
        for(Run run : runs){
            count++;
            ArrayList path = run.getPath_taken();
            for(Object s : path){
                if(s.equals("?")){
                    total += 1;
                }
            }
        }

        return total / count;
    }

    float enemyRoomsEntered(List<Run> runs){
        float total = 0;
        float count = 0;
        for(Run run : runs){
            count++;
            ArrayList path = run.getPath_taken();
            for(Object s : path){
                if(s.equals("M")){
                    total += 1;
                }
            }
        }

        return total / count;
    }

    float eliteRoomsEntered(List<Run> runs){
        float total = 0;
        float count = 0;
        for(Run run : runs){
            count++;
            ArrayList path = run.getPath_taken();
            for(Object s : path){
                if(s.equals("E")){
                    total += 1;
                }
            }
        }

        return total / count;
    }

    float shopsEntered(List<Run> runs){
        float total = 0;
        float count = 0;
        for(Run run : runs){
            count++;
            ArrayList path = run.getPath_taken();
            for(Object s : path){
                if(s.equals("$")){
                    total += 1;
                }
            }
        }

        return total / count;
    }

    float restStopsEntered(List<Run> runs){
        float total = 0;
        float count = 0;
        for(Run run : runs){
            count++;
            ArrayList path = run.getPath_taken();
            for(Object s : path){
                if(s.equals("R")){
                    total += 1;
                }
            }
        }

        return total / count;
    }

    String mostDiedToEnemy(List<Run> runs){
        HashMap<String, Integer> table = new HashMap<>();
        String scariest = "Collector"; // This isn't safe, in a sample size without a collector death we will crash.
        for(Run run : runs){
            if(run.getKilled_by() != null){
                String killer = run.getKilled_by();
                if(table.containsKey(killer)){
                    table.put(killer, (table.get(killer) + 1));
                }
                else{
                    table.put(killer, 1);
                }
            }
        }
        for(String boss : table.keySet()){
            if(table.get(scariest) < table.get(boss)){
                scariest = boss;
            }
        }
        return scariest;
    }

    String leastDiedToEnemy(List<Run> runs){
        HashMap<String, Integer> table = new HashMap<>();
        String easiest = "Collector"; // This isn't safe, in a sample size without a collector death we will crash.
        for(Run run : runs){
            if(run.getKilled_by() != null){
                String killer = run.getKilled_by();
                if(table.containsKey(killer)){
                    table.put(killer, (table.get(killer) + 1));
                }
                else{
                    table.put(killer, 1);
                }
            }
        }
        for(String boss : table.keySet()){
            if(table.get(easiest) > table.get(boss)){
                easiest = boss;
            }
        }
        return easiest;
    }

    float averageFloorReached(List<Run> runs){
        float total = 0;
        float count = 0;
        for(Run run : runs){
            count++;
            total += run.getFloor_reached();
        }

        return total / count;
    }

    int[] deathsByLevel(List<Run> runs){
        int[] levelCount = {0, 0, 0}; // ind 0 is level 1, 1 is 2, and 2 is 3
        for(Run run : runs){
            if(run.getFloor_reached() < 17){
                levelCount[0]++;
            }
            else if(run.getFloor_reached() > 16 && run.getFloor_reached() < 33){
                levelCount[1]++;
            }
            else{
                levelCount[2]++;
            }
        }

        return levelCount;
    }

    float averageCardsRemoved(List<Run> runs){
        float total = 0;
        float count = 0;
        for(Run run : runs){
            count++;
            total += run.getPurchased_purges();
        }

        return total / count;
    }

    /**
     * Determines how often each card appears in decks. This query combines upgraded and base level cards.
     * @param runs
     * @return
     */
    String cardAppearanceRates(List<Run> runs){
        List<Item> cardAppearances = new ArrayList<>();
        for(Run run : runs){
            List<String> cardsSeen = new ArrayList<>();
            for(Object cardObj : run.getMaster_deck()){
                boolean hasBeenSeen = false;
                String card = (String)cardObj;
                while(card.contains("+") || card.contains("_")){ // In case searing blow is like +1000 or some shit
                    card = card.substring(0, card.length() - 2);
                }
                for(Item i : cardAppearances){
                    if(i.getName().equals(card)){
                        hasBeenSeen = true;
                        if (!cardsSeen.contains(card)) {
                            i.incrementQuantity();
                        }
                    }
                }
                if(!hasBeenSeen){
                    cardAppearances.add(new Item(card, 1));
                }
                cardsSeen.add(card);
            }
        }

        cardAppearances.sort(new ItemComparator());
        for(Item i : cardAppearances){
            i.setTotalGames(runs.size());
        }

        String result = "";
        for(int i = 0; i < 10; i++){
            Item item = cardAppearances.get(i);
            result += (i + 1) + ". " + item + "%\n";
        }
        return result;
    }

    String relicAppearanceRates(List<Run> runs){
        List<Item> relicAppearances = new ArrayList<>();
        for(Run run : runs){
            List<String> relicsSeen = new ArrayList<>();
            for(Object cardObj : run.getRelics()){
                boolean hasBeenSeen = false;
                String relic = (String)cardObj;
                while(relic.contains("+")){ // In case searing blow is like +1000 or some shit
                    relic = relic.substring(0, relic.length()-2);
                }
                for(Item i : relicAppearances){
                    if(i.getName().equals(relic)){
                        hasBeenSeen = true;
                        if (!relicsSeen.contains(relic)) {
                            i.incrementQuantity();
                        }
                    }
                }
                if(!hasBeenSeen){
                    relicAppearances.add(new Item(relic, 1));
                }

                relicsSeen.add(relic);
            }
        }
        relicAppearances.sort(new ItemComparator());
        for(Item i : relicAppearances){
            i.setTotalGames(runs.size());
        }

        String result = "\n";
        for(int i = 0; i < 10; i++){
            Item item = relicAppearances.get(i);
            result += (i + 1) + ". " + item + "%\n";
        }
        return result;
    }

    List<Item> spookiestEnemy(List<Run> runs){
        List<Item> enemyDeaths = new ArrayList<>();
        for(Run run : runs){
            boolean hasBeenSeen = false;
            if(run.getKilled_by() != null){
                String boss = run.getKilled_by();
                    for(Item i : enemyDeaths){
                        if(i.getName().equals(boss)){
                            hasBeenSeen = true;
                            i.incrementQuantity();
                        }
                    }
                    if(!hasBeenSeen){
                        enemyDeaths.add(new Item(boss, 1));
                    }
            }

        }

        enemyDeaths.sort(new ItemComparator());
        return enemyDeaths;
    }

    float relicTest(List<Run> runs){
        int total = 0;
        float totalGames = runs.size();
        for(Run run : runs){
            if(run.getRelics().contains("Burning Blood")){
                total++;
            }
        }
        System.out.println(total);
        return (total/totalGames) * 100;
    }

    float isSozuOP(List<Run> runs){
        int totalWins = 0;
        float totalGames = 0;
        for(Run run : runs){
            if(run.getRelics().get(0).equals("Sozu")){
                if(run.isVictory()){
                    totalWins++;
                }
                totalGames++;
            }
        }
        return (totalWins/totalGames) * 100;
    }

    float rollingBossVsNot(List<Run> runs){
        int totalWins = 0;
        float totalGames = 0;
        int totalWinsRolled = 0;
        float totalGamesRolled = 0;

        for(Run run : runs){
            if(!run.getRelics().get(0).equals("Burning Blood") && !run.getRelics().get(0).equals("Ring of the Snake")){
                if(run.isVictory()){
                    totalWins++;
                }
                totalGames++;
            }
            else{
                if(run.isVictory()){
                    totalWinsRolled++;
                }
                totalGamesRolled++;
            }
        }
        System.out.println((totalWinsRolled / totalGamesRolled) * 100);
        return (totalWins/totalGames) * 100;
    }

    float goldGainVsNot(List<Run> runs){
        int totalWins = 0;
        float totalGames = 0;
        int totalWinsWithGold = 0;
        float totalGamesWithGold = 0;

        for(Run run : runs) {
            if (run.getGold_per_floor() != null && run.getGold_per_floor().size() > 0) {
                if ((double) run.getGold_per_floor().get(0) < 150) {
                    if (run.isVictory()) {
                        totalWins++;
                    }
                    totalGames++;
                } else {
                    if (run.isVictory()) {
                        totalWinsWithGold++;
                    }
                    totalGamesWithGold++;
                }
            }
        }
        System.out.println("When using gold: " + (totalWinsWithGold / totalGamesWithGold) * 100);
        return (totalWins/totalGames) * 100;
    }

    float isNeowLamnetOP(List<Run> runs){
        int totalWins = 0;
        float totalGames = 0;
        for(Run run : runs){
            if(run.getRelics() != null && run.getRelics().size() > 1) {
                if (run.getRelics().get(1).equals("NeowsBlessing")) {
                    if (run.isVictory()) {
                        totalWins++;
                    }
                    totalGames++;
                }
            }
        }
        return (totalWins/totalGames) * 100;
    }

    float howOPIsOffering(List<Run> runs){
        int totalWins = 0;
        float totalGames = 0;
        for(Run run : runs){
            int offerings = 0;
            for(Object s : run.getMaster_deck()){
                String card = (String)s;
                if(card.equals("Noxious Fumes")){
                    offerings++;
                }
            }
            if(offerings == 2){
                if(run.isVictory()) {
                    totalWins++;
                }
                totalGames++;
            }

        }
        return (totalWins/totalGames) * 100;
    }




}
