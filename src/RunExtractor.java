import com.google.gson.Gson;
import com.sun.deploy.util.ArrayUtil;
import jdk.nashorn.internal.parser.JSONParser;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Created by Joe on 4/20/2018.
 */
public class RunExtractor {

    enum Character{IRONCLAD, SILENT, BOTH}

    public void displayRawRunData(){
        File runDataFolder = new File("rundata");
        File[] users = runDataFolder.listFiles();
        int total = 0;
        int overall = 0;
        if(users != null) {
            for (File userRunData : users){
                File[] ironCladRuns = userRunData.listFiles()[0].listFiles();
                File[] silentRuns = userRunData.listFiles()[1].listFiles();
                System.out.print(userRunData.getName() + ": ");
                total += ironCladRuns.length;
                overall += ironCladRuns.length;
                total += silentRuns.length;
                overall += silentRuns.length;
                System.out.println(total);
                total = 0;
            }
        }
        System.out.println(overall);
    }

    public List<Run> getRuns(Character character){
        File runDataFolder = new File("rundata");
        File[] users = runDataFolder.listFiles();
        List<Run> runs = new ArrayList<>();
        Gson gson = new Gson();

        if(users != null) {
            for (File userRunData : users){

                File[] bothCharRunData = userRunData.listFiles();
                if(bothCharRunData != null){
                    File[] runData;
                    switch (character){
                        case IRONCLAD:
                            runData = bothCharRunData[0].listFiles();
                            break;
                        case SILENT:
                            runData = bothCharRunData[1].listFiles();
                            break;
                        case BOTH:
                            throw new NotImplementedException();
                        default:
                            throw new ValueException("Unknown Enum Value");
                    }

                    if(runData != null) {
                        for (File run : runData) {
                            try {
                                runs.add(gson.fromJson(new Scanner(run).nextLine(), Run.class));
                            } catch (IOException e) {
                                System.out.println("IO issue with file: " + run.getName());
                            }
                        }
                    }
                }
            }
        }
        return runs;
    }



    public static void main(String[] args) {

        RunExtractor runExtractor = new RunExtractor();
        DataAnalyzer da = new DataAnalyzer();

        List<Run> icRuns = runExtractor.getRuns(Character.IRONCLAD);
        //icRuns = da.getAscensionRuns(icRuns);
        System.out.println(icRuns.size());
        List<Run> tsRuns = runExtractor.getRuns(Character.SILENT);
        //tsRuns = da.getAscensionRuns(tsRuns);
        System.out.println(tsRuns.size());

        List<Run> winningICRuns = da.getWinningRuns(icRuns);
        List<Run> winningTSRuns = da.getWinningRuns(tsRuns);

        List<Run> losingICRuns = da.getLosingRuns(icRuns);
        //losingICRuns = da.getRunsThatMadeItToLevel3(losingICRuns);
        List<Run> losingTSRuns = da.getLosingRuns(tsRuns);
        //losingTSRuns = da.getRunsThatMadeItToLevel3(losingTSRuns);

        System.out.println("Overall Average:");
        System.out.println("Ironclad: " + da.rollingBossVsNot(icRuns));
        System.out.println("The Silent: " + da.rollingBossVsNot(tsRuns));

        System.out.println("\nWinning Average:");
        System.out.println("Ironclad: " + da.rollingBossVsNot(winningICRuns));
        System.out.println("The Silent: " + da.rollingBossVsNot(winningTSRuns));

        System.out.println("\nLosing Average:");
        System.out.println("Ironclad: " + da.rollingBossVsNot(losingICRuns));
        System.out.println("The Silent: " + da.rollingBossVsNot(losingTSRuns));
    }
}
