package org.jcalc.handlers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.jcalc.dto.SuperDTO;
import org.jcalc.scenebuilders.SceneController;
import org.jcalc.scenebuilders.components.iDialog;
import org.jcalc.scenebuilders.iMenuBar;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StaticFileSaver
{
    public static final String pathString = "path:";
    public static final String nameString = "name:";
    public static final String pathToBackgroundString = "project_background:";
    public static final String showWarningsLoaderString = "Show additional warnings";
    public static final String screenStretcherLoaderString = "Screen stretcher";

    public static void saveCustomData(String data, String format, String fileName, String description, String title)
    {
        try
        {
            String filename = StaticFileSaver.ChooseCustomFile(fileName, format, description, title);
            if(!fileName.contains(format)) {
                fileName += format;
            }
            File file = new File(fileName);

                //boolean res = file.createNewFile();
                FileWriter writer = new FileWriter(filename);
                writer.write(data);

                writer.flush();
                writer.close();
        }
        catch(IOException e)
        {
            System.out.println("Something went wrong");
        }
    }

    public static void SaveAll(String fileName)
    {
        try
        {
            String filename = StaticFileSaver.ChooseSuperFile(fileName);
            if(!fileName.contains(".sjson"))
            {
                fileName += ".sjson";
            }
            File file = new File(fileName);

            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();

            List<SuperDTO> temp = new ArrayList<>();

            temp = StaticSystemController.SuperDTOlist;

            //boolean res = file.createNewFile();

            FileWriter writer = new FileWriter(filename);
            gson.toJson(temp, writer);

            String jcalc = "";
            jcalc += pathString + StaticSystemController.pathToProjectImage +"\n";
            jcalc += nameString + StaticSystemController.projectName + "\n";
            jcalc += pathToBackgroundString + StaticSystemController.defaultProjectBackgroundPath + "\n";

            System.out.println("File " + filename + " saved succesfully");

            try
            {
                writer.flush();
                writer.close();
            }
            catch(IOException e)
            {
                System.out.println("Something went wrong");
            }
            File file2 = new File(filename.replaceAll(".sjson",".jcalc"));
            boolean res2 = file2.createNewFile();
            FileWriter writer2 = new FileWriter(filename.replaceAll(".sjson",".jcalc"));
            writer2.write(jcalc);
            try
            {
                writer2.flush();
                writer2.close();
            }
            catch(IOException e)
            {
                System.out.println("Something went wrong");
            }

        }
        catch (IOException e)
        {
            System.out.println("Something went wrong");
        }
    }

    public static void saveSettings()
    {
        File file = new File("./dat/settings.dat");
        String settingsString = "**EVENT OPTIONS**\n";
        settingsString += iMenuBar.customScoresString + ": " + StaticSystemController.UseCustomPoints + "\n";
        settingsString += iMenuBar.playerIDEditingString + ": " +  StaticSystemController.EnablePlayerIDEditing + "\n";
        settingsString += iMenuBar.autoCorrectString + ": " + StaticSystemController.autoCorrectEventSize + "\n";
        settingsString += iMenuBar.autoSplitSessionsString + ": " + StaticSystemController.AutoSplitEvents + "\n";
        settingsString += iMenuBar.teamsByClanString + ": " + StaticSystemController.TeamsByClan + "\n\n";

        settingsString += "**TABLE OPTIONS**\n";
        settingsString += StaticSystemController.imagePathString + ": " + StaticSystemController.pathToProjectImage + "\n";
        settingsString += iMenuBar.verticalHeadersString + ": " + "false +\n"; // StaticYGridPaneController.enableVerticalHeaders + "\n";
        settingsString += iMenuBar.showRaceResultsSeparatelyString + ": " + StaticYGridPaneController.showEveryRaceResults + "\n";
        settingsString += iMenuBar.showRacePointsSeparatelyString + ": " + StaticYGridPaneController.showEveryRacePoints + "\n";
        settingsString += iMenuBar.showLapResultsSeparatelyString + ": " + StaticYGridPaneController.showEveryLapResults + "\n";
        settingsString += iMenuBar.showPlayerIdString + ": " + StaticYGridPaneController.showPlayerId + "\n";
        settingsString += iMenuBar.showPlayerNameString + ": " + StaticYGridPaneController.showPlayerName + "\n";
        settingsString += iMenuBar.showPlayerClanString + ": " + StaticYGridPaneController.showClan + "\n";
        settingsString += iMenuBar.showPlayerLapResults + ": " + StaticYGridPaneController.showLapResults + "\n";
        settingsString += iMenuBar.showPlayerRaceResults + ": " + StaticYGridPaneController.showRaceResults + "\n";
        settingsString += iMenuBar.showPlayerBestLapsString + ": " + StaticYGridPaneController.showBestLaps + "\n";
        settingsString += iMenuBar.showPlayerTotalTimeString + ": " + StaticYGridPaneController.showTotalTimes + "\n";
        settingsString += iMenuBar.showAvgPositionString + ": " + StaticYGridPaneController.showAveragePosition + "\n";
        settingsString += iMenuBar.showAvgLapPositionString + ": " + StaticYGridPaneController.showAverageLapPosition + "\n";
        settingsString += iMenuBar.showTimeDifferenceString + ": " + StaticYGridPaneController.showTimeDifference + "\n";
        settingsString += iMenuBar.showRacePerformanceString + ": " + StaticYGridPaneController.showRacePerformance + "\n";
        settingsString += iMenuBar.showLapPerformanceString + ": " + StaticYGridPaneController.showLapPerformance + "\n";
        settingsString += iMenuBar.showTotalPointsString + ": " + StaticYGridPaneController.showTotalPoints + "\n";
        settingsString += iMenuBar.showTotalTeamPointsString + ": " + StaticYGridPaneController.showTotalTeamPoints + "\n";
        settingsString += iMenuBar.showPlayerRacePointsString + ": " + StaticYGridPaneController.showRacePoints + "\n";
        //settingsString += iMenuBar.showTeamPositionString + ": " + StaticYGridPaneController.showTeamPoints + "\n";
        settingsString += iMenuBar.showPlayerPositionString + ": " + StaticYGridPaneController.showPosition + "\n";
        settingsString += iMenuBar.showPlayedRaces + ": " + StaticYGridPaneController.showPlayedEvents + "\n\n";

        settingsString += "**JSON OPTIONS**\n";
        settingsString += "Format ID: " + StaticSystemController.FormatID + "\n";
        settingsString += "Format version: " + StaticSystemController.FormatVersion + "\n";
        settingsString += "Max events to remember: " + StaticSystemController.MaxEventsToRemember + "\n";
        settingsString += "Max player count: " + StaticSystemController.MaxPlayerCount + "\n\n";

        settingsString += "**COMMON OPTIONS**\n";
        //settingsString += "Sidebar events: " + StaticSystemController.MaxScrollEventButton + "\n";
        settingsString += showWarningsLoaderString + ":" + StaticSystemController.ShowWarnings + "\n";
        settingsString += screenStretcherLoaderString + ": " + StaticSystemController.screenStretcher + "\n\n";
        //settingsString += "Tableview enabled: " + StaticSystemController.TableViewEnabled + "\n\n";


        String sc1 = "";
        for(int i = 0; i < StaticSystemController.scoreArray.length; i++) {
            sc1 += StaticSystemController.scoreArray[i];
            if (i != StaticSystemController.scoreArray.length - 1) {
                sc1 += ", ";
            }
        }

        String sc2 = "";
        for(int i = 0; i < StaticSystemController.scoreArray2.length; i++) {
            sc2 += StaticSystemController.scoreArray2[i];
            if (i != StaticSystemController.scoreArray2.length - 1) {
                sc2 += ", ";
            }
        }
        settingsString += "**POINT OPTIONS**\n";
        settingsString += "Score option 1: " + sc1 + "\n";
        settingsString += "Score option 2: " + sc2 + "\n";

        try {
            boolean res = file.createNewFile();
            FileWriter writer = new FileWriter("./dat/settings.dat");
            writer.write(settingsString);

            writer.flush();
            writer.close();
        }
        catch (IOException e)
        {
            if(StaticSystemController.ShowWarnings) {
                iDialog dialog = new iDialog("Save settings", "\".../dat/settings.dat\" can not be reach.", "ERROR");
                dialog.showAndWait();
            }
        }
    }

    public static String ChooseFile(String fileName)
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("./"));

        Window stage = StaticSystemController.MainStage.getScene().getWindow();
        fileChooser.setTitle("Save event JSON -file");
        fileChooser.setInitialFileName(fileName);
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON -file","*.json"));

        String mFile = "";
        File file = fileChooser.showSaveDialog(stage);
        if (file != null)
        {
            fileChooser.setInitialDirectory(file.getParentFile());
            mFile = file.toString();
        }
        else
        {
            mFile = "";
        }

        stage.setOnCloseRequest(windowEvent -> {new SceneController().SwitchScene(0,false);});

        return mFile;
    }
    public static String ChooseCustomFile(String fileName, String extensions, String description, String title)
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("./"));
        String ext = "*" + extensions;

        Window stage = StaticSystemController.MainStage.getScene().getWindow();
        fileChooser.setTitle(title);
        fileChooser.setInitialFileName(fileName);
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(description,ext));

        String mFile = "";
        File file = fileChooser.showSaveDialog(stage);
        if (file != null)
        {
            fileChooser.setInitialDirectory(file.getParentFile());
            mFile = file.toString();
        }
        else
        {
            mFile = "";
        }

        stage.setOnCloseRequest(windowEvent -> {new SceneController().SwitchScene(0,false);});

        //if(mFile.split(".").length < 2)
        //{
        //    mFile += ".sjson";
        //}
        return mFile;
    }

    public static String ChooseSuperFile(String fileName)
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("./"));

        Window stage = StaticSystemController.MainStage.getScene().getWindow();
        fileChooser.setTitle("Save SuperJSON -file");
        fileChooser.setInitialFileName(fileName);
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("SuperJSON -file","*.sjson"));

        String mFile = "";
        File file = fileChooser.showSaveDialog(stage);
        if (file != null)
        {
            fileChooser.setInitialDirectory(file.getParentFile());
            mFile = file.toString();
        }
        else
        {
            mFile = "";
        }

        stage.setOnCloseRequest(windowEvent -> {new SceneController().SwitchScene(0,false);});

        //if(mFile.split(".").length < 2)
        //{
        //    mFile += ".sjson";
        //}
        return mFile;
    }

    public static void SaveSingleEvent(String fileName)
    {
        try
        {
            String filename = StaticFileSaver.ChooseFile(fileName);
            if(!filename.contains(".json"))
            {
                filename += ".json";
            }
            File file = new File(fileName);

            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();

            SuperDTO temp = new SuperDTO();

            for (SuperDTO superDTO: StaticSystemController.SuperDTOlist)
            {
                if(StaticSystemController.selectedId == superDTO.getProgId())
                {
                    temp = superDTO;
                }
            }

            //boolean res = file.createNewFile();

            FileWriter writer = new FileWriter(filename);
            gson.toJson(temp, writer);;

            System.out.println("File " + filename + " saved succesfully");

            try
            {
                writer.flush();
                writer.close();
            }
            catch(IOException e)
            {
                System.out.println("Something went wrong");
            }

        }
        catch (IOException e)
        {
            System.out.println("Something went wrong");
        }
    }

    public static void SaveSingleEventAsCompatible(String fileName)
    {
        try
        {
            String filename = StaticFileSaver.ChooseFile(fileName);
            if(!filename.contains(".json"))
            {
                filename += ".json";
            }
            File file = new File(fileName);

            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();

            SuperDTO temp = new SuperDTO();

            for (SuperDTO superDTO: StaticSystemController.SuperDTOlist)
            {
                if(StaticSystemController.selectedId == superDTO.getProgId())
                {
                    temp = superDTO;
                    temp.scoreArray = new int[0];
                }
            }

            //boolean res = file.createNewFile();

            FileWriter writer = new FileWriter(filename);
            String json = gson.toJson(temp);
            BufferedReader bf = new BufferedReader(new StringReader(json));
            String finalJson = "";
            String line = "";

            while ((line = bf.readLine()) != null)
            {
                String hand = line.replaceAll(" ", "");
                if(!hand.startsWith("\"secondScoresOn\"") && !hand.startsWith("\"progid\"") && !hand.startsWith("\"eventName\"") && !hand.startsWith("\"scoreArray\""))
                {
                    finalJson += line;
                }
            }
            bf.close();
            char charAtPos;
            charAtPos = finalJson.charAt(finalJson.length()-2);
            //System.out.println(charAtPos);
            if(charAtPos==',')
            {
                StringBuilder str = new StringBuilder(finalJson);
                str.deleteCharAt(finalJson.length()-2);
                //System.out.println(str);
                finalJson = str.toString();
                //System.out.println(finalJson);
            }

            gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();

            JsonObject sss = gson.fromJson(finalJson, JsonObject.class);

            gson.toJson(sss,writer);
            //System.out.println(finalJson);

            System.out.println("File " + filename + " saved succesfully");

            try
            {
                writer.flush();
                writer.close();
            }
            catch(IOException e)
            {
                System.out.println("Something went wrong");
            }

        }
        catch (IOException e)
        {
            System.out.println("Something went wrong");
        }
    }
}

