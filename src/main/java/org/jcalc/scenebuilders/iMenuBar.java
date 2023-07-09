package org.jcalc.scenebuilders;

import javafx.scene.control.*;
import org.jcalc.dto.SuperDTO;
import org.jcalc.handlers.*;
import org.jcalc.scenebuilders.components.UIUtils;
import org.jcalc.scenebuilders.components.iDialog;

import java.util.ArrayList;

public class iMenuBar extends MenuBar
{
    public final static String fileString = "File";
    public final static String newProjectString = "New project";
    public final static String newProjectInitialString = "Super project";
    public final static String loadSession = "Add file";
    public final static String mergeProjectsString = "Merge project";
    public final static String loadProjectString = "Load project";
    public final static String saveProjectString = "Save project";
    public final static String exitString = "Exit";
    public final static String tableViewString = "Score table view";
    public final static String objectViewString = "Player view";
    public final static String sessionEditView = "Session editing view";
    public final static String settingsString = "Settings";
    public final static String projectSettingsString = "Project and session settings";
    public final static String autoSplitSessionsString = "Automatically split sessions to events";
    public final static String viewMenuString = "View";
    public MenuBar getMenuBar()
    {
        Menu fileMenu = UIUtils.getDefaultMenu(fileString);

        MenuItem newProject = UIUtils.getDefaultMenuItem(newProjectString);
        newProject.setOnAction(e ->
        {
            iDialog dialog = new iDialog(newProjectString, "Are you sure you want to start a new project? The current project will be deleted.","CONFIRM");
            dialog.showAndWait();

            if(dialog.getResult().toString().contains("Yes")) {
                StaticSystemController.SuperDTOlist = new ArrayList<>();
                StaticSystemController.projectName = newProjectInitialString;
                StaticSystemController.pathToProjectImage = StaticSystemController.defaultProjectImagePath;
                StaticFileLoader.loadProjectImage(StaticSystemController.defaultProjectImagePath, false);
                StaticSystemController.updateViews(false, true);
            }
        });


        MenuItem loadRawJson = UIUtils.getDefaultMenuItem(loadSession);
        loadRawJson.setOnAction(e ->
        {if(!StaticJsonHandler.LoadRawJsonFile(StaticFileLoader.ChooseJSONFile(),true))
        {
            iDialog dialog = new iDialog(loadSession, "Failed to load the event! Json -file could be in wrong format.","ERROR");
            dialog.showAndWait();
        };});

        MenuItem mergeProjectsJson = UIUtils.getDefaultMenuItem(mergeProjectsString);
        mergeProjectsJson.setOnAction(e ->
        {
            if(!StaticJsonHandler.LoadMergeSuperJsonFile(StaticFileLoader.ChooseMergeProjectJsonFile(),true))
            {

                SceneController.switchToLoadingScene();

                iDialog dialog = new iDialog(mergeProjectsString, "Failed to load the project! sJson -file could be in wrong format.","ERROR");
                dialog.showAndWait();

                SceneController.SwitchScene(StaticSystemController.SceneControl,true);
            }
        });

        MenuItem loadJSON = UIUtils.getDefaultMenuItem(loadProjectString);
        loadJSON.setOnAction(e ->
        {
            iDialog dialog = new iDialog(loadProjectString, "Are you sure you want to open a new project? The current project will be deleted.","CONFIRM");
            dialog.showAndWait();

            if(dialog.getResult().toString().contains("Yes"))
            {
                if(!StaticJsonHandler.LoadOldProjectJsonFile(StaticFileLoader.ChooseProjectJsonFile(), true))
                {
                    SceneController.switchToLoadingScene();

                    iDialog dialog2 = new iDialog(loadProjectString, "Failed to load the project! sJson -file could be in wrong format.","ERROR");
                    dialog2.showAndWait();

                    SceneController.SwitchScene(StaticSystemController.SceneControl,true);
                }
            }
        });

        MenuItem saveProject = UIUtils.getDefaultMenuItem(saveProjectString);
        saveProject.setOnAction(e ->
        {
            StaticFileSaver.SaveAll(StaticSystemController.projectName);});

        MenuItem exit = UIUtils.getDefaultMenuItem(exitString);
        exit.setOnAction(e ->
        {
            iDialog dialog = new iDialog(exitString, "Are you sure?","CONFIRM");
            dialog.showAndWait();

            if(dialog.getResult().toString().contains("Yes"))
            {
                System.exit(0);
            }
        });

        SeparatorMenuItem sp3 = UIUtils.getDefaultSeparatorMenuItem();
        SeparatorMenuItem sp4 = UIUtils.getDefaultSeparatorMenuItem();
        SeparatorMenuItem sp5 = UIUtils.getDefaultSeparatorMenuItem();
        fileMenu.getItems().addAll(newProject,sp3,loadRawJson,mergeProjectsJson,loadJSON,sp4,saveProject,sp5,exit);

        Menu sceneMenu = UIUtils.getDefaultMenu(viewMenuString);

        MenuItem mainView = UIUtils.getDefaultMenuItem(objectViewString);
        mainView.setOnAction(e ->
        {SceneController.SwitchScene(0, true);});

        MenuItem tableView = UIUtils.getDefaultMenuItem(tableViewString);
        tableView.setOnAction(e ->
        {SceneController.SwitchScene(1, true);});

        MenuItem eventView = UIUtils.getDefaultMenuItem(sessionEditView);
        eventView.setOnAction(e ->
        {SceneController.SwitchScene(2, true);});

        sceneMenu.getItems().addAll(mainView,tableView,eventView);

        Menu settingsMenu = UIUtils.getDefaultMenu(settingsString);
        MenuItem projectSettings = UIUtils.getDefaultMenuItem(projectSettingsString);
        projectSettings.setOnAction(e ->
                {
                    iDialog dialog = new iDialog(settingsString, "","SETTINGS");
                    dialog.showAndWait();
                }
        );

        MenuItem loadProjectImage = UIUtils.getDefaultMenuItem(loadImageString);
        loadProjectImage.setOnAction(e ->
        {

            SceneController.switchToLoadingScene();

            StaticFileLoader.loadProjectImage(StaticSystemController.pathToProjectImage, true);

            SceneController.SwitchScene(StaticSystemController.SceneControl,true);
        });

        MenuItem loadProjectBackground = UIUtils.getDefaultMenuItem(loadProjectBackgroundString);
        loadProjectBackground.setOnAction(e ->
        {

            SceneController.switchToLoadingScene();

            StaticFileLoader.loadProjectBackgroundImage(StaticSystemController.defaultProjectBackgroundPath, true);

            SceneController.SwitchScene(StaticSystemController.SceneControl,true);
        });

        CheckMenuItem setCustomScores = getCustomScoreItem();
        CheckMenuItem setPlayeridEditing = getEnablePlayerIDediting();
        CheckMenuItem setAutoCorrectEvents = getAutoCorrectItem();
        CheckMenuItem setTeamsByClan = getTeamsByClan();
        SeparatorMenuItem sp1 = UIUtils.getDefaultSeparatorMenuItem();
        SeparatorMenuItem sp2 = UIUtils.getDefaultSeparatorMenuItem();
        SeparatorMenuItem sp6 = UIUtils.getDefaultSeparatorMenuItem();

        settingsMenu.getItems().addAll(projectSettings, sp6, loadProjectImage, loadProjectBackground, sp1, setCustomScores, setTeamsByClan,sp2,setPlayeridEditing,setAutoCorrectEvents,getEnableAutoSplitting());

        this.getMenus().addAll(fileMenu,sceneMenu,settingsMenu,getExportMenu());

        return this;
    }

    public static final String customScoresString = "Custom points";
    public CheckMenuItem getCustomScoreItem()
    {
        CheckMenuItem setCustomScores = UIUtils.getDefaultCheckMenuItem(customScoresString);
        if(StaticSystemController.UseCustomPoints)
        {
            setCustomScores.setSelected(true);
        }
        else
        {
            setCustomScores.setSelected(false);
        }

        setCustomScores.setOnAction(e -> {
            if(setCustomScores.isSelected())
            {
                StaticSystemController.UseCustomPoints = true;
                StaticSystemController.updateViews(false,false);
            }
            else
            {
                StaticSystemController.UseCustomPoints = false;
                StaticSystemController.updateViews(false,false);
            }
            StaticFileSaver.saveSettings();
        });


        return setCustomScores;
    }

    public static final String autoCorrectString = "Automatically fix the amount of session events";
    public CheckMenuItem getAutoCorrectItem()
    {
        CheckMenuItem autoCorrectEvents = UIUtils.getDefaultCheckMenuItem(autoCorrectString);
        if(StaticSystemController.autoCorrectEventSize)
        {
            autoCorrectEvents.setSelected(true);
        }
        else
        {
            autoCorrectEvents.setSelected(false);
        }

        autoCorrectEvents.setOnAction(e -> {
            if(autoCorrectEvents.isSelected())
            {
                StaticSystemController.autoCorrectEventSize = true;
                StaticSystemController.updateViews(false,false);
            }
            else
            {
                StaticSystemController.autoCorrectEventSize = false;
                StaticSystemController.updateViews(false,false);
            }
            StaticFileSaver.saveSettings();
        });


        return autoCorrectEvents;
    }

    public static final String teamsByClanString = "Team points are calculated according to clans";
    public CheckMenuItem getTeamsByClan()
    {
        CheckMenuItem teamsByClan = UIUtils.getDefaultCheckMenuItem(teamsByClanString);
        if(StaticSystemController.TeamsByClan)
        {
            teamsByClan.setSelected(true);
        }
        else
        {
            teamsByClan.setSelected(false);
        }

        teamsByClan.setOnAction(e -> {
            if(teamsByClan.isSelected())
            {
                StaticSystemController.TeamsByClan = true;
                StaticSystemController.updateViews(false,false);
            }
            else
            {
                StaticSystemController.TeamsByClan = false;
                StaticSystemController.updateViews(false,false);
            }
            StaticFileSaver.saveSettings();
        });

        return teamsByClan;
    }

    public static final String playerIDEditingString = "Allow editing players ID";
    public CheckMenuItem getEnablePlayerIDediting()
    {
        CheckMenuItem setPlayerIdEditing = UIUtils.getDefaultCheckMenuItem(playerIDEditingString);
        if(StaticSystemController.EnablePlayerIDEditing)
        {
            setPlayerIdEditing.setSelected(true);
        }
        else
        {
            setPlayerIdEditing.setSelected(false);
        }

        setPlayerIdEditing.setOnAction(e -> {
            if(setPlayerIdEditing.isSelected())
            {
                StaticSystemController.EnablePlayerIDEditing = true;
                StaticSystemController.updateViews(false,false);
            }
            else
            {
                StaticSystemController.EnablePlayerIDEditing = false;
                StaticSystemController.updateViews(false,false);
            }
            StaticFileSaver.saveSettings();
        });


        return setPlayerIdEditing;
    }

    public static final String autoSplittingString = "Automatically split loaded sessions";
    public CheckMenuItem getEnableAutoSplitting()
    {
        CheckMenuItem splitEvents = UIUtils.getDefaultCheckMenuItem(autoSplittingString);
        if(StaticSystemController.AutoSplitEvents) {splitEvents.setSelected(true);}else splitEvents.setSelected(false);
        splitEvents.setOnAction(actionEvent -> {if(splitEvents.isSelected())StaticSystemController.AutoSplitEvents=true;else StaticSystemController.AutoSplitEvents=false;});

        splitEvents.setOnAction(e -> {
            if(splitEvents.isSelected())
            {
                StaticSystemController.AutoSplitEvents = true;
                StaticSystemController.updateViews(false,false);
            }
            else
            {
                StaticSystemController.AutoSplitEvents = false;
                StaticSystemController.updateViews(false,false);
            }
            StaticFileSaver.saveSettings();
        });


        return splitEvents;
    }

    public CheckMenuItem getShowPlayerIDPlayerView()
    {
        CheckMenuItem setPlayerIdEditing = UIUtils.getDefaultCheckMenuItem(playerIDEditingString);
        if(StaticSystemController.ShowIDinPlayerView)
        {
            setPlayerIdEditing.setSelected(true);
        }
        else
        {
            setPlayerIdEditing.setSelected(false);
        }

        setPlayerIdEditing.setOnAction(e -> {
            if(setPlayerIdEditing.isSelected())
            {
                StaticSystemController.ShowIDinPlayerView = true;
                StaticSystemController.updateViews(false,false);
            }
            else
            {
                StaticSystemController.ShowIDinPlayerView = false;
                StaticSystemController.updateViews(false,false);
            }
            StaticFileSaver.saveSettings();
        });


        return setPlayerIdEditing;
    }

    public static final String showPlayerIDStringPlayer = "Show player ID";
    public MenuBar getPlayerMenuBar()
    {
        Menu playerMenu = UIUtils.getDefaultMenu(objectViewString);

        CheckMenuItem showPlayerID = UIUtils.getDefaultCheckMenuItem(showPlayerIDStringPlayer);
        if(StaticSystemController.ShowIDinPlayerView){showPlayerID.setSelected(true);}else{showPlayerID.setSelected(false);}
        showPlayerID.setOnAction(e ->
        {
            if(StaticSystemController.ShowIDinPlayerView){StaticSystemController.ShowIDinPlayerView = false;}else{StaticSystemController.ShowIDinPlayerView = true;}
            StaticFileSaver.saveSettings();
            StaticSystemController.updateViews(false,true);
        });

        playerMenu.getItems().add(showPlayerID);

        this.getMenuBar();

        this.getMenus().add(playerMenu);

        return this;
    }

    public static final String mergeAllEventsString = "Merge all sessions";
    public static final String splitAllSessionsString = "Split all sessions to multiple events";
    public static final String saveSelectedSession = "Save selected session";
    public static final String exportSelectedSession = "Export selected session as TSU compatible json";
    public static final String loadImageString = "Load project icon";
    public static final String loadProjectBackgroundString = "Load background";
    public MenuBar getEventMenuBar()
    {
        Menu eventMenu = UIUtils.getDefaultMenu(sessionEditView);

        MenuItem mergeAllEvents = UIUtils.getDefaultMenu(mergeAllEventsString);
        mergeAllEvents.setOnAction(e ->
        {

            SceneController.switchToLoadingScene();

            iDialog dialog = new iDialog(mergeAllEventsString, "Are you sure? ","CONFIRM");
            dialog.showAndWait();

            if(dialog.getResult().toString().contains("Yes"))
            {
                StaticActionHandler.MergeAllEvents();
                SceneController.SwitchScene(StaticSystemController.SceneControl,true);
            }

            SceneController.SwitchScene(StaticSystemController.SceneControl,true);
        });

        MenuItem splitAllEvents = UIUtils.getDefaultMenu(splitAllSessionsString);
        splitAllEvents.setOnAction(e ->
        {

            SceneController.switchToLoadingScene();

            iDialog dialog = new iDialog(splitAllSessionsString, "Are you sure? ","CONFIRM");
            dialog.showAndWait();

            if(dialog.getResult().toString().contains("Yes"))
            {
                StaticActionHandler.SplitAllEvents();
                SceneController.SwitchScene(StaticSystemController.SceneControl,true);
            }

            SceneController.SwitchScene(StaticSystemController.SceneControl,true);
        });


        MenuItem saveEvent = UIUtils.getDefaultMenuItem(saveSelectedSession);
        saveEvent .setOnAction(e ->
        {String filename = "";
            if(StaticSystemController.selectedId != null)
            {
                for(SuperDTO sup: StaticSystemController.SuperDTOlist)
                {
                    if(sup.getProgId() == StaticSystemController.selectedId)
                    {
                        filename = sup.getSuperDTOname();
                    }
                }
                StaticFileSaver.SaveSingleEvent(filename);}});

        MenuItem saveCompatibleEvent = UIUtils.getDefaultMenuItem(exportSelectedSession);
        saveCompatibleEvent .setOnAction(e ->
        {String filename = "";
            if(StaticSystemController.selectedId != null)
            {
                for(SuperDTO sup: StaticSystemController.SuperDTOlist)
                {
                    if(sup.getProgId() == StaticSystemController.selectedId)
                    {
                        filename = sup.getSuperDTOname();
                    }
                }
                StaticFileSaver.SaveSingleEventAsCompatible(filename);}});

        SeparatorMenuItem sp1 = UIUtils.getDefaultSeparatorMenuItem();
        eventMenu.getItems().addAll(mergeAllEvents,splitAllEvents,sp1, saveEvent,saveCompatibleEvent);

        this.getMenuBar();
        this.getMenus().add(eventMenu);

        return this;
    }

    public static final String verticalHeadersString = "Vertical headers";
    public static final String showRaceResultsSeparatelyString = "Show race results separately";
    public static final String showRacePointsSeparatelyString = "Show race points separately";
    public static final String showLapResultsSeparatelyString = "Show lap results separately";
    public static final String showPlayerIdString = "Show player ID";
    public static final String showPlayerNameString = "Show player name";
    public static final String showPlayerClanString = "Show player clan";
    public static final String showPlayerLapResults = "Show player lap results";
    public static final String showPlayerRaceResults = "Show player race results";
    public static final String showPlayerBestLapsString = "Show player best laps";
    public static final String showPlayerTotalTimeString = "Show player total time";
    public static final String showAvgPositionString = "Show player avg. position";
    public static final String showAvgLapPositionString = "Show player avg. best lap position";
    public static final String showTimeDifferenceString = "Show time difference";
    public static final String showRacePerformanceString = "Show player race performance";
    public static final String showLapPerformanceString = "Show player lap performance";
    //public static final String

    public static final String exportAsCSVString = "Export project as csv -file";

    public MenuBar getTableMenuBar()
    {
        this.getMenuBar();
        Menu tableMenu = UIUtils.getDefaultMenu(tableViewString);

        CheckMenuItem vHeaders = UIUtils.getDefaultCheckMenuItem(verticalHeadersString); vHeaders.setDisable(true);

        if(StaticYGridPaneController.enableVerticalHeaders){vHeaders.setSelected(true);}else{vHeaders.setSelected(false);}
        vHeaders.setOnAction(e ->
        {
            if(StaticYGridPaneController.enableVerticalHeaders){StaticYGridPaneController.enableVerticalHeaders = false;}else{StaticYGridPaneController.enableVerticalHeaders = true;}
            StaticFileSaver.saveSettings();
            StaticSystemController.updateViews(false,true);
        });

        CheckMenuItem showEveryRaceActive = UIUtils.getDefaultCheckMenuItem(showRaceResultsSeparatelyString);

        if(StaticYGridPaneController.showEveryRaceResults){showEveryRaceActive.setSelected(true);}else{showEveryRaceActive.setSelected(false);}
        showEveryRaceActive.setOnAction(e ->
        {
            if(StaticYGridPaneController.showEveryRaceResults){StaticYGridPaneController.showEveryRaceResults = false;}else{StaticYGridPaneController.showEveryRaceResults = true;}
            StaticFileSaver.saveSettings();
            StaticSystemController.updateViews(false,true);
        });

        CheckMenuItem showEveryPointsActive = UIUtils.getDefaultCheckMenuItem(showRacePointsSeparatelyString);

        if(StaticYGridPaneController.showEveryRacePoints){showEveryPointsActive.setSelected(true);}else{showEveryPointsActive.setSelected(false);}
        showEveryPointsActive.setOnAction(e ->
        {
            if(StaticYGridPaneController.showEveryRacePoints){StaticYGridPaneController.showEveryRacePoints = false;}else{StaticYGridPaneController.showEveryRacePoints = true;}
            StaticFileSaver.saveSettings();
            StaticSystemController.updateViews(false,true);
        });

        CheckMenuItem showEveryLapResultsActive = UIUtils.getDefaultCheckMenuItem(showLapResultsSeparatelyString);

        if(StaticYGridPaneController.showEveryLapResults){showEveryLapResultsActive.setSelected(true);}else{showEveryLapResultsActive.setSelected(false);}
        showEveryLapResultsActive.setOnAction(e ->
        {
            if(StaticYGridPaneController.showEveryLapResults){StaticYGridPaneController.showEveryLapResults = false;}else{StaticYGridPaneController.showEveryLapResults = true;}
            StaticFileSaver.saveSettings();
            StaticSystemController.updateViews(false,true);
        });

        SeparatorMenuItem sp1 = UIUtils.getDefaultSeparatorMenuItem();
        SeparatorMenuItem sp2 = UIUtils.getDefaultSeparatorMenuItem();

        CheckMenuItem playerIdActive = UIUtils.getDefaultCheckMenuItem(showPlayerIdString);
        if(StaticYGridPaneController.showPlayerId){playerIdActive.setSelected(true);}else{playerIdActive.setSelected(false);}
        playerIdActive .setOnAction(e ->
        {
            if(StaticYGridPaneController.showPlayerId){StaticYGridPaneController.showPlayerId = false;}else{StaticYGridPaneController.showPlayerId = true;}
            StaticFileSaver.saveSettings();
            StaticSystemController.updateViews(false,true);
        });

        CheckMenuItem playerNameActive = UIUtils.getDefaultCheckMenuItem(showPlayerNameString);
        if(StaticYGridPaneController.showPlayerName){playerNameActive.setSelected(true);}else{playerNameActive.setSelected(false);}
        playerNameActive .setOnAction(e ->
        {
            if(StaticYGridPaneController.showPlayerName){StaticYGridPaneController.showPlayerName = false;}else{StaticYGridPaneController.showPlayerName = true;}
            StaticFileSaver.saveSettings();
            StaticSystemController.updateViews(false,true);
        });

        CheckMenuItem playerClanActive = UIUtils.getDefaultCheckMenuItem(showPlayerClanString);
        if(StaticYGridPaneController.showClan){playerClanActive.setSelected(true);}else{playerClanActive.setSelected(false);}
        playerClanActive .setOnAction(e ->
        {
            if(StaticYGridPaneController.showClan){StaticYGridPaneController.showClan = false;}else{StaticYGridPaneController.showClan = true;}
            StaticFileSaver.saveSettings();
            StaticSystemController.updateViews(false,true);
        });

        CheckMenuItem playerLapResults = UIUtils.getDefaultCheckMenuItem(showPlayerLapResults);
        if(StaticYGridPaneController.showLapResults){playerLapResults.setSelected(true);}else{playerLapResults.setSelected(false);}
        playerLapResults .setOnAction(e ->
        {
            if(StaticYGridPaneController.showLapResults){StaticYGridPaneController.showLapResults = false;}else{StaticYGridPaneController.showLapResults = true;}
            StaticFileSaver.saveSettings();
            StaticSystemController.updateViews(false,true);
        });

        CheckMenuItem playerRaceResults = UIUtils.getDefaultCheckMenuItem(showPlayerRaceResults);
        if(StaticYGridPaneController.showRaceResults){playerRaceResults.setSelected(true);}else{playerRaceResults.setSelected(false);}
        playerRaceResults .setOnAction(e ->
        {
            if(StaticYGridPaneController.showRaceResults){StaticYGridPaneController.showRaceResults = false;}else{StaticYGridPaneController.showRaceResults = true;}
            StaticFileSaver.saveSettings();
            StaticSystemController.updateViews(false,true);
        });

        CheckMenuItem playerBestLaps = UIUtils.getDefaultCheckMenuItem(showPlayerBestLapsString);
        if(StaticYGridPaneController.showBestLaps){playerBestLaps.setSelected(true);}else{playerBestLaps.setSelected(false);}
        playerBestLaps .setOnAction(e ->
        {
            if(StaticYGridPaneController.showBestLaps){StaticYGridPaneController.showBestLaps = false;}else{StaticYGridPaneController.showBestLaps = true;}
            StaticFileSaver.saveSettings();
            StaticSystemController.updateViews(false,true);
        });

        CheckMenuItem playerTotalTime = UIUtils.getDefaultCheckMenuItem(showPlayerTotalTimeString); playerTotalTime.setDisable(true);
        if(StaticYGridPaneController.showTotalTimes){playerTotalTime.setSelected(true);}else{playerTotalTime.setSelected(false);}
        playerTotalTime .setOnAction(e ->
        {
            if(StaticYGridPaneController.showTotalTimes){StaticYGridPaneController.showTotalTimes = false;}else{StaticYGridPaneController.showTotalTimes = true;}
            StaticFileSaver.saveSettings();
            StaticSystemController.updateViews(false,true);
        });

        CheckMenuItem playerAveragePosition = UIUtils.getDefaultCheckMenuItem(showAvgPositionString);
        if(StaticYGridPaneController.showAveragePosition){playerAveragePosition.setSelected(true);}else{playerAveragePosition.setSelected(false);}
        playerAveragePosition .setOnAction(e ->
        {
            if(StaticYGridPaneController.showAveragePosition){StaticYGridPaneController.showAveragePosition = false;}else{StaticYGridPaneController.showAveragePosition = true;}
            StaticFileSaver.saveSettings();
            StaticSystemController.updateViews(false,true);
        });

        CheckMenuItem playerAverageLapPosition = UIUtils.getDefaultCheckMenuItem(showAvgLapPositionString);
        if(StaticYGridPaneController.showAverageLapPosition){playerAverageLapPosition.setSelected(true);}else{playerAverageLapPosition.setSelected(false);}
        playerAverageLapPosition .setOnAction(e ->
        {
            if(StaticYGridPaneController.showAverageLapPosition){StaticYGridPaneController.showAverageLapPosition = false;}else{StaticYGridPaneController.showAverageLapPosition = true;}
            StaticFileSaver.saveSettings();
            StaticSystemController.updateViews(false,true);
        });

        CheckMenuItem playerTimeDifference = UIUtils.getDefaultCheckMenuItem(showTimeDifferenceString); playerTimeDifference.setDisable(true);
        if(StaticYGridPaneController.showTimeDifference){playerTimeDifference.setSelected(true);}else{playerTimeDifference.setSelected(false);}
        playerTimeDifference .setOnAction(e ->
        {
            //if(StaticYGridPaneController.showTimeDifference){StaticYGridPaneController.showTimeDifference = false;}else{StaticYGridPaneController.showTimeDifference = true;}
            //StaticFileSaver.saveSettings();
            //StaticSystemController.updateViews(false,true);
        });

        CheckMenuItem playerRacePerformance = UIUtils.getDefaultCheckMenuItem(showRacePerformanceString); playerRacePerformance.setDisable(true);
        if(StaticYGridPaneController.showRacePerformance){playerRacePerformance.setSelected(true);}else{playerRacePerformance.setSelected(false);}
        playerRacePerformance .setOnAction(e ->
        {
            //if(StaticYGridPaneController.showRacePerformance){StaticYGridPaneController.showRacePerformance = false;}else{StaticYGridPaneController.showRacePerformance = true;}
            //StaticFileSaver.saveSettings();
            //StaticSystemController.updateViews(false,true);
        });

        CheckMenuItem playerLapPerformance = UIUtils.getDefaultCheckMenuItem(showLapPerformanceString); playerLapPerformance.setDisable(true);
        if(StaticYGridPaneController.showLapPerformance){playerLapPerformance.setSelected(true);}else{playerLapPerformance.setSelected(false);}
        playerLapPerformance .setOnAction(e ->
        {
            //if(StaticYGridPaneController.showLapPerformance){StaticYGridPaneController.showLapPerformance = false;}else{StaticYGridPaneController.showLapPerformance = true;}
            //StaticFileSaver.saveSettings();
            //StaticSystemController.updateViews(false,true);
        });

        CheckMenuItem playerRacePoints = UIUtils.getDefaultCheckMenuItem(showPlayerRacePointsString);
        if(StaticYGridPaneController.showRacePoints){playerRacePoints.setSelected(true);}else{playerRacePoints.setSelected(false);}
        playerRacePoints .setOnAction(e ->
        {
            if(StaticYGridPaneController.showRacePoints){StaticYGridPaneController.showRacePoints = false;}else{StaticYGridPaneController.showRacePoints = true;}
            StaticFileSaver.saveSettings();
            StaticSystemController.updateViews(false,true);
        });

        /**CheckMenuItem playerTeamRacePoints = new CheckMenuItem("Show team player points"); playerTeamRacePoints.setDisable(true);
        if(StaticYGridPaneController.showTeamPoints){playerTeamRacePoints.setSelected(true);}else{playerTeamRacePoints.setSelected(false);}
        playerTeamRacePoints .setOnAction(e ->
        {
            if(StaticYGridPaneController.showTeamPoints){StaticYGridPaneController.showTeamPoints = false;}else{StaticYGridPaneController.showTeamPoints = true;}
            StaticFileSaver.saveSettings();
            StaticSystemController.updateViews(false,true);
        });**/

        CheckMenuItem playerTotalTeamPoints = UIUtils.getDefaultCheckMenuItem(showTotalTeamPointsString);
        if(StaticYGridPaneController.showTotalTeamPoints){playerTotalTeamPoints.setSelected(true);}else{playerTotalTeamPoints.setSelected(false);}
        playerTotalTeamPoints .setOnAction(e ->
        {
            if(StaticYGridPaneController.showTotalTeamPoints){StaticYGridPaneController.showTotalTeamPoints = false;}else{StaticYGridPaneController.showTotalTeamPoints = true;}
            StaticFileSaver.saveSettings();
            StaticSystemController.updateViews(false,true);
        });

        CheckMenuItem playerTotalPoints =UIUtils.getDefaultCheckMenuItem(showTotalPointsString);
        if(StaticYGridPaneController.showTotalPoints){playerTotalPoints.setSelected(true);}else{playerTotalPoints.setSelected(false);}
        playerTotalPoints .setOnAction(e ->
        {
            if(StaticYGridPaneController.showTotalPoints){StaticYGridPaneController.showTotalPoints = false;}else{StaticYGridPaneController.showTotalPoints = true;}
            StaticFileSaver.saveSettings();
            StaticSystemController.updateViews(false,true);
        });

        CheckMenuItem playerPosition = UIUtils.getDefaultCheckMenuItem(showPlayerPositionString);
        if(StaticYGridPaneController.showPosition){playerPosition.setSelected(true);}else{playerPosition.setSelected(false);}
        playerPosition .setOnAction(e ->
        {
            if(StaticYGridPaneController.showPosition){StaticYGridPaneController.showPosition = false;}else{StaticYGridPaneController.showPosition = true;}
            StaticFileSaver.saveSettings();
            StaticSystemController.updateViews(false,true);
        });

        CheckMenuItem playerTeamPosition = UIUtils.getDefaultCheckMenuItem(showTeamPositionString);
        if(StaticYGridPaneController.showTeamPosition){playerTeamPosition.setSelected(true);}else{playerTeamPosition.setSelected(false);}
        playerTeamPosition .setOnAction(e ->
        {
            if(StaticYGridPaneController.showTeamPosition){StaticYGridPaneController.showTeamPosition = false;}else{StaticYGridPaneController.showTeamPosition = true;}
            StaticFileSaver.saveSettings();
            StaticSystemController.updateViews(false,true);
        });

        CheckMenuItem playerPlayedEvents = UIUtils.getDefaultCheckMenuItem(showPlayedRaces);
        if(StaticYGridPaneController.showPlayedEvents){playerPlayedEvents.setSelected(true);}else{playerPlayedEvents.setSelected(false);}
        playerPlayedEvents .setOnAction(e ->
        {
            if(StaticYGridPaneController.showPlayedEvents){StaticYGridPaneController.showPlayedEvents = false;}else{StaticYGridPaneController.showPlayedEvents = true;}
            StaticFileSaver.saveSettings();
            StaticSystemController.updateViews(false,true);
        });

        tableMenu.getItems().addAll(vHeaders, sp1, showEveryRaceActive,showEveryPointsActive, showEveryLapResultsActive,sp2,playerIdActive, playerNameActive,playerClanActive,
                playerLapResults,playerRaceResults,playerBestLaps,playerTotalTime,playerAveragePosition,
                playerAverageLapPosition,playerTimeDifference,playerRacePerformance,playerLapPerformance,
                playerRacePoints,playerTotalTeamPoints,playerTotalPoints,playerTeamPosition,playerPosition,playerPlayedEvents);

        this.getMenus().add(tableMenu);

        return this;
    }

    public static final String exportMenuString = "Export...";
    public static final String exportProjectAsTSUString = "Export project as TSU compatible json";
    public static Menu getExportMenu()
    {
        Menu exportMenu = UIUtils.getDefaultMenu(exportMenuString);

        MenuItem exportAsCSV = UIUtils.getDefaultMenuItem(exportAsCSVString);
        exportAsCSV.setOnAction(e ->
        {
            StaticCSVHandler.createCSVFile();
        });

        MenuItem exportAsTSU = UIUtils.getDefaultMenuItem(exportProjectAsTSUString);
        exportAsTSU.setOnAction(e ->
        {
            //StaticCSVHandler.createCSVFile();
        });

        exportMenu.getItems().addAll(exportAsCSV, exportAsTSU);
        return exportMenu;
    }
    public static final String showPlayerRacePointsString = "Show player race points";
    public static final String showTotalTeamPointsString = "Show total team points";
    public static final String showTotalPointsString = "Show total points";
    public static final String showPlayerPositionString = "Show player position";
    public static final String showTeamPositionString = "Show team position";
    public static final String showPlayedRaces = "Show played races";

}
