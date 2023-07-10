package org.jcalc.handlers;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.jcalc.dto.SuperDTO;
import org.jcalc.scenebuilders.StaticActionHandler;
import org.jcalc.scenebuilders.StaticSceneBuilder;
import org.jcalc.scenebuilders.components.iDialog;
import org.jcalc.scenebuilders.iMenuBar;
//import jdk.jfr.Description;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StaticFileLoader
{
    public List<Object> loadAll(String fileName)
    {
        return new ArrayList<Object>();
    }

    public static void loadProjectBackgroundImage(String path, boolean fileChooser) //TODO
    {
        String filename = "";
        if(fileChooser)
        {
            if (path != null)
            {
                filename = ChooseImageFile(path);
            }
            else
            {
                filename = ChooseImageFile("");
            }
            Image img = null;
        }
        else
        {
            filename = path;
        }
        Image img = null;
        try
        {
            FileInputStream input = new FileInputStream(filename);
            img = new Image(input);
            input.close();
            BackgroundImage backgroundImage = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(StaticSceneBuilder.SceneWidth,StaticSceneBuilder.SceneHeight,true,true,true,true));
            Background background = new Background(backgroundImage);
            //StaticSystemController.projectBackground = background;
            StaticSystemController.defaultProjectBackgroundPath = filename;
        }
        catch (FileNotFoundException e) {
            System.out.println(e);
        }
        catch (IOException e) {
            System.out.println(e);
        }
        catch (NullPointerException e)
        {
            System.out.println(e);
        }
    }

    public static void loadCustomBacgroundImage(String path, boolean fileChooser) //TODO
    {
        String filename = "";
        if(fileChooser)
        {
            if (path != null)
            {
                filename = ChooseImageFile(path);
            }
            else
            {
                filename = ChooseImageFile("");
            }
            Image img = null;
        }
        else
        {
            filename = path;
        }
        Image img = null;
        try
        {
            FileInputStream input = new FileInputStream(filename);
            img = new Image(input);
            input.close();
            BackgroundImage backgroundImage = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(StaticSceneBuilder.SceneWidth,StaticSceneBuilder.SceneHeight,true,true,true,true));
            Background background = new Background(backgroundImage);
            //StaticSystemController.projectBackground = background;
            StaticSystemController.defaultProjectBackgroundPath = filename;
        }
        catch (FileNotFoundException e) {
            System.out.println(e);
        }
        catch (IOException e) {
            System.out.println(e);
        }
        catch (NullPointerException e)
        {
            System.out.println(e);
        }
    }

    public static void loadProjectImage(String path, boolean fileChooser) //TODO
    {
        String filename = "";
        if(fileChooser)
        {
            if (path != null)
            {
                filename = ChooseImageFile(path);
            }
            else
            {
                filename = ChooseImageFile("");
            }
            Image img = null;
        }
        else
        {
            filename = path;
        }
        Image img = null;
        try
        {
            FileInputStream input = new FileInputStream(filename);
            img = new Image(input);
            input.close();
            BackgroundImage backgroundImage = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(StaticSceneBuilder.SceneWidth,StaticSceneBuilder.SceneHeight,true,true,true,true));
            Background background = new Background(backgroundImage);
            StaticSystemController.projectBackground = background;
            StaticSystemController.pathToProjectImage = filename;
        }
        catch (FileNotFoundException e) {
            System.out.println(e);
        }
        catch (IOException e) {
            System.out.println(e);
        }
        catch (NullPointerException e)
        {
            System.out.println(e);
        }
    }
    public static void setSettings(String np)
    {
        BufferedReader reader = new BufferedReader(new StringReader(np));

        try
        {
            String line = "";
            while ((line = reader.readLine()) != null)
            {
                String[] linedata = line.split(":");
                if(linedata[0].startsWith(iMenuBar.customScoresString))
                {
                    if(linedata[1].contains("false")) {StaticSystemController.UseCustomPoints = false;}
                    else {StaticSystemController.UseCustomPoints = true;}
                }
                if(linedata[0].startsWith(iMenuBar.playerIDEditingString))
                {
                    if(linedata[1].contains("false")) {StaticSystemController.EnablePlayerIDEditing = false;}
                    else {StaticSystemController.EnablePlayerIDEditing = true;}
                }
                if(linedata[0].startsWith(iMenuBar.autoCorrectString))
                {
                    if(linedata[1].contains("false")) {StaticSystemController.autoCorrectEventSize = false;}
                    else {StaticSystemController.autoCorrectEventSize = true;}
                }
                if(linedata[0].startsWith(StaticFileSaver.showWarningsLoaderString))
                {
                    if(linedata[1].contains("false")) {StaticSystemController.ShowWarnings = false;}
                    else {StaticSystemController.ShowWarnings = true;}
                }
                if(linedata[0].startsWith("Tableview enabled"))
                {
                    if(linedata[1].contains("false")) {StaticSystemController.TableViewEnabled = false;}
                    else {StaticSystemController.TableViewEnabled = true;}
                }
                if(linedata[0].startsWith(iMenuBar.teamsByClanString))
                {
                    if(linedata[1].contains("false")) {StaticSystemController.TeamsByClan = false;}
                    else {StaticSystemController.TeamsByClan = true;}
                }
                if(linedata[0].startsWith(iMenuBar.showPlayerIdString))
                {
                    if(linedata[1].contains("false")) {StaticYGridPaneController.showPlayerId = false;}
                    else {StaticYGridPaneController.showPlayerId = true;}
                }
                if(linedata[0].startsWith(iMenuBar.showPlayerNameString))
                {
                    if(linedata[1].contains("false")) {StaticYGridPaneController.showPlayerName = false;}
                    else {StaticYGridPaneController.showPlayerName = true;}
                }
                if(linedata[0].startsWith(iMenuBar.showPlayerClanString))
                {
                    if(linedata[1].contains("false")) {StaticYGridPaneController.showClan = false;}
                    else {StaticYGridPaneController.showClan = true;}
                }
                if(linedata[0].startsWith(iMenuBar.showPlayerLapResults))
                {
                    if(linedata[1].contains("false")) {StaticYGridPaneController.showLapResults = false;}
                    else {StaticYGridPaneController.showLapResults = true;}
                }
                if(linedata[0].startsWith(iMenuBar.showPlayerRaceResults))
                {
                    if(linedata[1].contains("false")) {StaticYGridPaneController.showRaceResults = false;}
                    else {StaticYGridPaneController.showRaceResults = true;}
                }
                if(linedata[0].startsWith(iMenuBar.showPlayerBestLapsString))
                {
                    if(linedata[1].contains("false")) {StaticYGridPaneController.showBestLaps = false;}
                    else {StaticYGridPaneController.showBestLaps = true;}
                }
                if(linedata[0].startsWith(iMenuBar.showPlayerTotalTimeString))
                {
                    //if(linedata[1].contains("false")) {StaticYGridPaneController.showTotalTimes = false;}
                    //else {StaticYGridPaneController.showTotalTimes = true;}
                }
                if(linedata[0].startsWith(iMenuBar.showAvgPositionString))
                {
                    if(linedata[1].contains("false")) {StaticYGridPaneController.showAveragePosition = false;}
                    else {StaticYGridPaneController.showAveragePosition = true;}
                }
                if(linedata[0].startsWith(iMenuBar.showAvgLapPositionString))
                {
                    if(linedata[1].contains("false")) {StaticYGridPaneController.showAverageLapPosition = false;}
                    else {StaticYGridPaneController.showAverageLapPosition = true;}
                }
                if(linedata[0].startsWith(iMenuBar.showRaceResultsSeparatelyString))
                {
                    if(linedata[1].contains("false")) {StaticYGridPaneController.showEveryRaceResults = false;}
                    else {StaticYGridPaneController.showEveryRaceResults = true;}
                }
                if(linedata[0].startsWith(iMenuBar.showRacePointsSeparatelyString))
                {
                    if(linedata[1].contains("false")) {StaticYGridPaneController.showEveryRacePoints = false;}
                    else {StaticYGridPaneController.showEveryRacePoints = true;}
                }
                if(linedata[0].startsWith(iMenuBar.showLapResultsSeparatelyString))
                {
                    if(linedata[1].contains("false")) {StaticYGridPaneController.showEveryLapResults = false;}
                    else {StaticYGridPaneController.showEveryLapResults = true;}
                }
                if(linedata[0].startsWith(iMenuBar.verticalHeadersString))
                {
                    if(linedata[1].contains("false")) {StaticYGridPaneController.enableVerticalHeaders = false;}
                    else {StaticYGridPaneController.enableVerticalHeaders = true;}
                }
                if(linedata[0].startsWith(iMenuBar.showTimeDifferenceString))
                {
                    //if(linedata[1].contains("false")) {StaticYGridPaneController.showTimeDifference = false;}
                    //else {StaticYGridPaneController.showTimeDifference = true;}
                }
                if(linedata[0].startsWith(iMenuBar.showRacePerformanceString))
                {
                    //if(linedata[1].contains("false")) {StaticYGridPaneController.showRacePerformance = false;}
                    //else {StaticYGridPaneController.showRacePerformance = true;}
                }
                if(linedata[0].startsWith(iMenuBar.showLapPerformanceString))
                {
                    //if(linedata[1].contains("false")) {StaticYGridPaneController.showLapPerformance = false;}
                    //else {StaticYGridPaneController.showLapPerformance = true;}
                }
                if(linedata[0].startsWith(iMenuBar.showTotalPointsString))
                {
                    if(linedata[1].contains("false")) {StaticYGridPaneController.showTotalPoints = false;}
                    else {StaticYGridPaneController.showTotalPoints = true;}
                }
                //if(linedata[0].startsWith(iMenuBar.showTeamPointsString))
                //{
                    //if(linedata[1].contains("false")) {StaticYGridPaneController.showTotalTeamPoints = false;}
                    //else {StaticYGridPaneController.showTotalTeamPoints = true;}
                //}
                /**if(linedata[0].startsWith(StaticSystemController.imagePathString))
                {
                    if(!linedata[1].contains("null"))
                    {
                        StaticSystemController.pathToProjectImage = linedata[1].replaceAll(" ", "");
                    }
                    else StaticSystemController.pathToProjectImage = StaticSystemController.defaultProjectImagePath;

                    //StaticFileLoader.loadProjectImage(StaticSystemController.pathToProjectImage);
                }**/
                if(linedata[0].startsWith(iMenuBar.showPlayerRacePointsString))
                {
                    if(linedata[1].contains("false")) {StaticYGridPaneController.showRacePoints = false;}
                    else {StaticYGridPaneController.showRacePoints = true;}
                }
                if(linedata[0].startsWith(iMenuBar.showTotalTeamPointsString))
                {
                    if(linedata[1].contains("false")) {StaticYGridPaneController.showTotalTeamPoints = false;}
                    else {StaticYGridPaneController.showTotalTeamPoints = true;}
                }
                if(linedata[0].startsWith(iMenuBar.showPlayerPositionString))
                {
                    if(linedata[1].contains("false")) {StaticYGridPaneController.showPosition = false;}
                    else {StaticYGridPaneController.showPosition = true;}
                }
                if(linedata[0].startsWith(iMenuBar.showTeamPositionString))
                {
                    if(linedata[1].contains("false")) {StaticYGridPaneController.showTeamPosition = false;}
                    else {StaticYGridPaneController.showTeamPosition = true;}
                }
                if(linedata[0].startsWith(iMenuBar.showPlayedRaces))
                {
                    if(linedata[1].contains("false")) {StaticYGridPaneController.showPlayedEvents = false;}
                    else {StaticYGridPaneController.showPlayedEvents = true;}
                }
                if(linedata[0].startsWith(iMenuBar.autoSplitSessionsString))
                {
                    if(linedata[1].contains("false")) {StaticSystemController.AutoSplitEvents = false;}
                    else {StaticSystemController.AutoSplitEvents = true;}
                }
                if(linedata[0].startsWith(StaticFileSaver.screenStretcherLoaderString))
                {
                    if(linedata[1].contains("false")) {StaticSystemController.screenStretcher = false;}
                    else {StaticSystemController.screenStretcher = true;}
                }

                if(linedata[0].startsWith("Format ID"))
                {
                    StaticSystemController.FormatID = Integer.parseInt(linedata[1].replaceAll(" ",""));
                }
                if(linedata[0].startsWith("Format version"))
                {
                    StaticSystemController.FormatVersion = Integer.parseInt(linedata[1].replaceAll(" ",""));
                }
                if(linedata[0].startsWith("Max events to remember"))
                {
                    StaticSystemController.MaxEventsToRemember = Integer.parseInt(linedata[1].replaceAll(" ",""));
                }
                if(linedata[0].startsWith("Max player count"))
                {
                    StaticSystemController.MaxPlayerCount = Integer.parseInt(linedata[1].replaceAll(" ",""));
                }
                if(linedata[0].startsWith("Sidebar events"))
                {
                    StaticSystemController.MaxScrollEventButton = Integer.parseInt(linedata[1].replaceAll(" ",""));
                }

                if(linedata[0].startsWith("Score option 1"))
                {
                    String[] arr = linedata[1].split(", ");

                    for(int i = 0; i < arr.length; i++)
                    {
                        StaticSystemController.scoreArray[i] = Integer.parseInt(arr[i].replaceAll(" ",""));
                    }
                }
                if(linedata[0].startsWith("Score option 2"))
                {
                    String[] arr = linedata[1].split(", ");

                    for(int i = 0; i < arr.length; i++)
                    {
                        StaticSystemController.scoreArray2[i] = Integer.parseInt(arr[i].replaceAll(" ",""));
                    }
                }
            }
        }
        catch (IOException e)
        {
            if(StaticSystemController.ShowWarnings)
            {
                iDialog dialog = new iDialog("Load settings", "\".../dat/settings.dat\" can not be reach.", "ERROR");
                dialog.showAndWait();
            }
        }
        catch (NumberFormatException e)
        {
            if(StaticSystemController.ShowWarnings)
            {
                iDialog dialog = new iDialog("Load settings", "\".../dat/settings.dat\" can not be reach.", "ERROR");
                dialog.showAndWait();
            }
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            if(StaticSystemController.ShowWarnings)
            {
                iDialog dialog = new iDialog("Load settings", "\".../dat/settings.dat\" can not be reach.", "ERROR");
                dialog.showAndWait();
            }
        }
    }
    public static void LoadSettings()
    {
        String filename = "./dat/settings.dat";
        File file = new File(filename);

        String notParsedData = "";

        try
        {
            FileInputStream inputStream = new FileInputStream(file);
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            BufferedReader reader = new BufferedReader(new InputStreamReader(dataInputStream));

            String line = "";
            while ((line = reader.readLine()) != null)
            {
                notParsedData += line + "\n";
            }

            //System.out.println("File loaded successfully");
            //System.out.println(notParsedData);
            setSettings(notParsedData);

            reader.close();

        }
        catch(FileNotFoundException e)
        {
            System.out.println("File " + file + " not found");
        }
        catch(IOException e)
        {
            System.out.println("Reading file failed");
        }
    }

    //@Description("Add new JSON -file to SuperDTOList")
    public static boolean AddNewJSONtoSuperDTOlist(String filename)
    {
        if(!filename.equals(""))
        {
            try
            {
                String raw = ParseToRaw(getFileName(filename));

                if(raw.contains("m_formatId") && raw.contains("m_formatVersion") && raw.contains("m_totalEventCount") && raw.contains("m_maxEventsToRemember") && raw.contains("m_rememberedEventsCount") && raw.contains("m_playerStats") && raw.indexOf('{') == 0)
                {
                    for(SuperDTO superDTO: StaticSystemController.SuperDTOlist)
                    {
                        if (superDTO.getProgId() >= StaticSystemController.FreeID)
                        {
                            StaticSystemController.FreeID = superDTO.getProgId() + 1;
                        }
                    }

                    Type type = new TypeToken<SuperDTO>() {}.getType();

                    SuperDTO superDTO = new Gson().fromJson(raw, type);
                    superDTO.setProgId(StaticSystemController.FreeID);

                    StaticSystemController.FreeID += 1;

                    if(!StaticSystemController.AutoSplitEvents)
                    {
                        StaticSystemController.SuperDTOlist.add(superDTO);
                    }

                    else
                    {
                        StaticActionHandler.SplitAll(superDTO,true);
                    }

                    return true;
                }
                else
                {
                    System.out.println("File is not in valid format");
                    return false;
                }
            }
            catch (NullPointerException e)
            {
                System.out.println(e);
                return false;
            }
            catch (StringIndexOutOfBoundsException e)
            {
                System.out.println(e);
                return false;
            }
            catch (JsonSyntaxException e)
            {
                System.out.println(e);
                return false;
            }
        }
        return true;
    }
    public static boolean MergeOldProjectJsonFile(String filename)
    {
        if(!filename.equals(""))
        {
            try
            {
                String raw = ParseToRawSuper(getFileName(filename),false,filename);

                if(raw.contains("m_formatId") && raw.contains("m_formatVersion") && raw.contains("m_totalEventCount") && raw.contains("m_maxEventsToRemember") && raw.contains("m_rememberedEventsCount") && raw.contains("m_playerStats") && raw.indexOf('[') == 0)
                {
                    Type type = new TypeToken<List<SuperDTO>>() {}.getType();
                    List<SuperDTO> temp = new Gson().fromJson(raw, type);

                    int newfreeid = 0;
                    for (SuperDTO superDTO : temp)
                    {
                        if (newfreeid <= superDTO.getProgId())
                        {
                            newfreeid = superDTO.getProgId();
                        }
                    }
                    StaticSystemController.FreeID = newfreeid;

                    for (SuperDTO superDTO : temp)
                    {
                        superDTO.setProgId(newfreeid);
                        newfreeid++;
                    }

                    for(SuperDTO superDTO: StaticSystemController.SuperDTOlist)
                    {
                        temp.add(superDTO);
                        superDTO.setProgId(newfreeid);
                        newfreeid++;
                    }

                    StaticSystemController.SuperDTOlist = temp;

                    return true;
                }
                else
                {
                    System.out.println("File is not in valid format");
                    return false;
                }

            }
            catch (NullPointerException e)
            {
                System.out.println(e);
                return false;
            }
            catch (StringIndexOutOfBoundsException e)
            {
                System.out.println(e);
                return false;
            }
            catch (JsonSyntaxException e)
            {
                System.out.println(e);
                return false;
            }
        }

        return true;
    }
    public static boolean LoadOldProjectJsonFile(String filename)
    {
        if(!filename.equals(""))
        {
            try
            {
                String raw = ParseToRawSuper(getFileName(filename),true,filename);

                if(raw.contains("m_formatId") && raw.contains("m_formatVersion") && raw.contains("m_totalEventCount") && raw.contains("m_maxEventsToRemember") && raw.contains("m_rememberedEventsCount") && raw.contains("m_playerStats") && raw.indexOf('[') == 0)
                {
                    Type type = new TypeToken<List<SuperDTO>>() {}.getType();
                    List<SuperDTO> temp = new Gson().fromJson(raw, type);

                    int newfreeid = 0;
                    for (SuperDTO superDTO : temp)
                    {
                        if (newfreeid <= superDTO.getProgId())
                        {
                            newfreeid = superDTO.getProgId();
                        }
                    }
                    StaticSystemController.FreeID = newfreeid;

                    for (SuperDTO superDTO : temp) {
                        superDTO.setProgId(newfreeid);
                        newfreeid++;
                    }

                    StaticSystemController.SuperDTOlist = temp;
                    return true;
                }
                else
                {
                    System.out.println("File is not in valid format");
                    return false;
                }

            }
            catch (NullPointerException e)
            {
                System.out.println(e);
                return false;
            }
            catch (StringIndexOutOfBoundsException e)
            {
                System.out.println(e);
                return false;
            }
            catch (JsonSyntaxException e)
            {
                System.out.println(e);
                return false;
            }
        }
        return true;
    }
    public static String ChooseJSONFile()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("./"));

        Window stage = StaticSystemController.MainStage.getScene().getWindow();
        fileChooser.setTitle("Load raw JSON -file");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON -file","*.json"));

        String mFile = "";
        File file = fileChooser.showOpenDialog(stage);
        if (file != null)
        {
            fileChooser.setInitialDirectory(file.getParentFile());
            mFile = file.toString();
        }
        else
        {
            mFile = StaticSystemController.FileNameString;
            return "";
        }

        //stage.setOnCloseRequest(windowEvent -> {new SceneController().SwitchScene(0,false);});

        return mFile.toString();
    }

    public static String ChooseProjectJsonFile()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("./"));

        Window stage = StaticSystemController.MainStage.getScene().getWindow();
        fileChooser.setTitle("Load project SuperJSON -file");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("SuperJSON -file","*.sjson"));

        String mFile = "";
        File file = fileChooser.showOpenDialog(stage);
        if (file != null)
        {
            fileChooser.setInitialDirectory(file.getParentFile());
            mFile = file.toString();
        }
        else
        {
            mFile = StaticSystemController.FileNameString;
            return "";
        }

        //stage.setOnCloseRequest(windowEvent -> {new SceneController().SwitchScene(0,false);});

        return mFile.toString();
    }

    public static String ChooseImageFile(String path)
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("./"));

        Window stage = StaticSystemController.MainStage.getScene().getWindow();
        fileChooser.setTitle("Load project image");
        //fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(""));

        String mFile = "";
        File file = fileChooser.showOpenDialog(stage);
        if (file != null)
        {
            fileChooser.setInitialDirectory(file.getParentFile());
            mFile = file.toString();
        }
        else
        {
            mFile = StaticSystemController.FileNameString;
            return "";
        }

        //stage.setOnCloseRequest(windowEvent -> {new SceneController().SwitchScene(0,false);});

        return mFile.toString();
    }

    public static String ChooseMergeProjectJsonFile()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("./"));

        Window stage = StaticSystemController.MainStage.getScene().getWindow();
        fileChooser.setTitle("Merge project: SuperJSON -file");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("SuperJSON -file","*.sjson"));

        String mFile = "";
        File file = fileChooser.showOpenDialog(stage);
        if (file != null)
        {
            fileChooser.setInitialDirectory(file.getParentFile());
            mFile = file.toString();
        }
        else
        {
            mFile = StaticSystemController.FileNameString;
            return "";
        }

        //stage.setOnCloseRequest(windowEvent -> {new SceneController().SwitchScene(0,false);});

        return mFile.toString();
    }

    public static File getFileName(String filename)
    {
        if(filename == null)
        {
            return StaticSystemController.temporarySuperJSONFile;
        }
        else
        {
            return new File(filename);
        }
    }
    public static String ParseToRaw(File file)
    {
        String notParsedData = "";

        try
        {
            FileInputStream inputStream = new FileInputStream(file);
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            BufferedReader reader = new BufferedReader(new InputStreamReader(dataInputStream));

            String line = "";
            while ((line = reader.readLine()) != null) {
                notParsedData = notParsedData + line;
            }
            notParsedData += "";
            String notP = notParsedData.substring(notParsedData.indexOf("{"));
            notP.trim();

            notParsedData = notP;

            System.out.println("File loaded successfully");

            reader.close();

        }
        catch(FileNotFoundException e)
        {
            System.out.println("File " + file + " not found");
        }
        catch(IOException e)
        {
            System.out.println("Reading file failed");
        }

        return notParsedData;
    }

    public static String ParseToRawSuper(File file, boolean addImage, String filename)
    {
        String notParsedData = "";

        try
        {
            FileInputStream inputStream = new FileInputStream(file);
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            BufferedReader reader = new BufferedReader(new InputStreamReader(dataInputStream));

            String line = "";
            while ((line = reader.readLine()) != null) {
                notParsedData = notParsedData + line;
            }
            notParsedData += "";
            String notP = notParsedData.substring(notParsedData.indexOf("["));
            notP.trim();

            notParsedData = notP;

            System.out.println("File loaded successfully");

            reader.close();

            if(addImage)
            {
                File file2 = new File(filename.replaceAll("sjson","jcalc"));

                FileInputStream inputStream2 = new FileInputStream(file2);
                DataInputStream dataInputStream2 = new DataInputStream(inputStream2);
                BufferedReader reader2 = new BufferedReader(new InputStreamReader(dataInputStream2));

                //String[] ss = {};
                String line2 = "";
                while ((line2 = reader2.readLine()) != null) {
                    //ss = line2.split(":");
                    if(line2.contains(StaticFileSaver.pathString)) {
                        StaticSystemController.pathToProjectImage = line2.replaceAll(StaticFileSaver.pathString,"");
                        //System.out.println(line2.replaceAll(StaticFileSaver.pathString,""));
                        loadProjectImage(StaticSystemController.pathToProjectImage,false);
                    }
                    if(line2.contains(StaticFileSaver.pathToBackgroundString)) {
                        StaticSystemController.defaultProjectBackgroundPath = line2.replaceAll(StaticFileSaver.pathToBackgroundString,"");
                        //System.out.println(line2.replaceAll(StaticFileSaver.pathString,""));
                        loadCustomBacgroundImage(StaticSystemController.defaultProjectBackgroundPath,false);
                    }
                    else if(line2.contains(StaticFileSaver.nameString)) {
                        StaticSystemController.projectName = line2.replaceAll(StaticFileSaver.nameString,"");
                        //System.out.println(line2.replaceAll(StaticFileSaver.nameString,""));
                        //loadProjectImage(StaticSystemController.projectName,false);
                    }
                }
            }

        }
        catch(FileNotFoundException e)
        {
            System.out.println("File " + file + " not found");
        }
        catch(IOException e)
        {
            System.out.println("Failed to read the file.");
        }

        return notParsedData;
    }

    public static Background LoadImageToBackground(String file)
    {
        Image img = null;
        try
        {
            FileInputStream input = new FileInputStream(file);
            img = new Image(input);
            input.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e);
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
        BackgroundImage backgroundImage = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(StaticSceneBuilder.SceneWidth,StaticSceneBuilder.SceneHeight,true,true,true,true));
        Background background = new Background(backgroundImage);

        return background;
    }
}

