package org.jcalc.handlers;

import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.stage.Stage;
import org.jcalc.dto.PlayerDTO;
import org.jcalc.dto.SuperDTO;
import org.jcalc.scenebuilders.SceneController;
import org.jcalc.scenebuilders.StaticSceneBuilder;
import org.jcalc.scenebuilders.components.iButton;
import org.jcalc.scenebuilders.components.iTextField;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class StaticSystemController
{
    public static String defaultProjectBackgroundPath = "./pic/background.png";
    public static String defaultProjectImagePath = "./pic/emptyProject.png";
    public static String pathToProjectImage = defaultProjectImagePath;
    public static String imagePathString = "Project image";
    public static Background projectBackground = null;
    public static String projectName = "Super project";

    /**Order settings**/
    public static Long selectedPlayerID = null;
    public static Integer SelectedIdIndex1 = null;
    public static Integer SelectedIdIndex2 = null;

    public static Integer selectedMergeID = null;
    public static boolean mergeActive = false;

    /**COMPARE**/
    public static int compareTo = 1;
    public static int compareToPoints = 0;
    public static int compareToTeamPoints = 1;
    public static int compareToID = 2;
    public static int compareToName = 3;
    public static int compareToClan = 4;
    public static int compareToPlayedRaces = 5;
    public static int compareToBestLaps = 6;
    public static int compareToAvgPosition = 7;
    public static int compareToAvgLapPosition = 8;
    public static int compareToTimeDifference = 9;
    public static int compareToRacePerformance = 10;
    public static int compareToLapPerformance = 11;
    public static int compareToTotalTime = 12;
    public static int compareToPosition = 13;
    public static int compareToTeamPosition = 14;
    /****/
    public static List<iButton> eventButtonList = new ArrayList<>();
    public static boolean screenStretcher = false;

    /**Settings**/
    public static List<iTextField> OptionScorefields = new ArrayList<>();
    public static List<iTextField> OptionScorefields2 = new ArrayList<>();

    public static boolean ShowIDinPlayerView = true;
    public static byte SelectedChartType = 1;
    public static boolean AutoSplitEvents = true;
    public static boolean TeamsByClan = false;
    public static int MaxEventsToRemember = 100;
    public static int MaxPlayerCount = 100;
    public static int FormatVersion = 3;
    public static int FormatID = 2856;
    public static boolean autoCorrectEventSize = true;

    /**EventView settings**/
    public static boolean ShowWarnings = true;
    public static int ScrollEventButtonIndex = 0;
    public static int MaxScrollEventButton = 20;
    public static Double ScrollPaneWidth = 0.0;
    public static Integer selectedId = null;
    public static Long freePlayerId = 99999999999999999l;

    public static boolean EnablePlayerIDEditing = false;
    /**TableView settings**/
    public static boolean UseCustomPoints = false;
    public static boolean TableViewEnabled = false;
    public static int[] scoreArray = {0,20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1,0,0,0,0,0,0,0,0,0,0,100};
    public static int[] scoreArray2 = {0,20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1,0,0,0,0,0,0,0,0,0,0,100};
    /**Temporary values**/
    public static String FileNameString = "./";
    public static File temporarySuperJSONFile = new File(FileNameString);
    /**Scene related values**/
    public static final String Version = "v0.62";
    public static final String Name = "jCalc";
    public static int SceneControl = 0;     //0 = MainScene, 1 = TableScene
    public static final int MaxScenes = 2;
    public static Stage MainStage;
    public static Scene MainScene = StaticSceneBuilder.CreateMainScene();
    public static Scene TableScene;
    public static Scene ObjectScene;
    public static Scene LoadingScene = StaticSceneBuilder.CreateLoadingScene();

    public static double correcto = 10033;

    /**Item related static values**/
    public static int FreeID = 0;
    public static List<SuperDTO> SuperDTOlist = new ArrayList<>();
    public static List<PlayerDTO> PlayerDTOlist = new ArrayList<>();
    public static List<iTextField> TextFieldListInGridPane = new ArrayList<>();
    public static List<iButton> ThreadUpdatableComponents = new ArrayList<>();

    //public static GridRunnable gridRunnable = new GridRunnable();
    public static Thread gridThread;

    public static void loadOtherComponents()
    {
        StaticFileLoader.loadProjectImage(StaticSystemController.pathToProjectImage, false);
    }
    public static void autoCorrect()
    {
        int mx = 0;
        if(selectedId != null)
        {
            for (SuperDTO dto : SuperDTOlist)
            {
                if (selectedId == dto.getProgId())
                {
                    for (int i = 0; i < dto.m_playerStats.length; i++)
                    {
                        if (mx <= dto.m_playerStats[i].getPoints().size())
                        {
                            mx = dto.m_playerStats[i].getPoints().size();
                        }
                    }
                }
            }

            for (SuperDTO dto : SuperDTOlist)
            {
                if (selectedId == dto.getProgId())
                {
                    for (int i = 0; i < dto.m_playerStats.length; i++)
                    {
                        if (mx > dto.m_playerStats[i].getPoints().size())
                        {
                            int add = 0;
                            List<Integer> list = dto.m_playerStats[i].getPoints();

                            for(add = mx-dto.m_playerStats[i].getPoints().size(); add > 0; add--)
                            {
                                list.add(0);
                            }

                            dto.m_playerStats[i].setPoints(dto.m_playerStats[i].getID(),list.toString());
                        }
                    }
                }
            }
        }
    }
    public static void updateViews(boolean updateAll, boolean updateTables)
    {
        if(updateTables)
        {
            StaticSceneBuilder.refreshGridList(false);

            for(SuperDTO superDTO: SuperDTOlist)
            {
                if(superDTO.secondScoresOn)
                {
                    superDTO.scoreArray = scoreArray2;
                }
                else
                {
                    superDTO.scoreArray = scoreArray;
                }
            }
        }
        for(SuperDTO dto: SuperDTOlist)
        {
            dto.setEventCounts();
        }

        if(autoCorrectEventSize)autoCorrect();

        if (!updateAll) {
            double w;
            double h;
            TextFieldListInGridPane = new ArrayList<>();
            ThreadUpdatableComponents = new ArrayList<>();

            if (MainStage == null) {
                w = StaticSceneBuilder.SceneWidth;
                h = StaticSceneBuilder.SceneHeight;
            } else {
                w = MainStage.getWidth();
                h = MainStage.getHeight();
            }

            if (SceneControl == 0) {
                MainScene = null;
                StaticSystemController.MainStage.setHeight(h);
                StaticSystemController.MainStage.setWidth(w);

                //MainScene = StaticSceneBuilder.CreateMainScene();
                //MainStage.setScene(MainScene);
                MainStage.setScene(StaticSceneBuilder.CreateMainScene());
                StaticSystemController.MainStage.show();
            } else if (SceneControl == 2) {
                MainScene = null;
                StaticSystemController.MainStage.setHeight(h);
                StaticSystemController.MainStage.setWidth(w);

                //ObjectScene = StaticSceneBuilder.CreateObjectScene();
                //MainStage.setScene(ObjectScene);
                MainStage.setScene(StaticSceneBuilder.CreateObjectScene());
                StaticSystemController.MainStage.show();
            } else if (SceneControl == 1) {
                MainScene = null;
                StaticSystemController.MainStage.setHeight(h);
                StaticSystemController.MainStage.setWidth(w);

                //TableScene = StaticSceneBuilder.CreateTableScene();
                //MainStage.setScene(TableScene);
                MainStage.setScene(StaticSceneBuilder.CreateTableScene());
                StaticSystemController.MainStage.show();
            }

            MainScene = MainStage.getScene();
            correcto *= -1;
            System.out.println("Scene updated");

            SceneController.setCorrection();
        }
        else {
            /**NOT IMPLEMENTED**/
        }
    }
}
