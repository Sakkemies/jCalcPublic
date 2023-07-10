package org.jcalc.scenebuilders.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import org.jcalc.dto.*;
import org.jcalc.handlers.StaticDTOHandler;
import org.jcalc.handlers.StaticSystemController;
import org.jcalc.handlers.StaticYGridPaneController;
import org.jcalc.scenebuilders.StaticSceneBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.jcalc.scenebuilders.StaticSceneBuilder.InvisibleBackground;
import static org.jcalc.scenebuilders.StaticSceneBuilder.RealInvisibleBackground;

public class yGridPane extends GridPane
{

    private static final int minWidth = 10;
    //private static final int maxWidth = 200;
    private static final int prefWidthMedium = 100;
    private static final int prefWidth = 200;
    private static final int prefSmallWidth = 35;
    private static final int minHeight = 25;
    private static final int maxWidth = 1000;
    private static final int maxHeight = 60;
    private int index = 0;

    public List<iTextField> textFields = new ArrayList<>();
    List<Player> playerList;
    List<Team> teamList;
    public yGridPane()
    {
        this.setBackground(InvisibleBackground);
        //this.setBorder(StaticSceneBuilder.BorderForPane);
        this.setPadding(new Insets(10,10,10,10));
        this.setHgap(2);
        this.setVgap(2);
    }

    public void setAction(iTextField textField, String key)
    {
        if(key.equals(totalPointsString))
        {
            textField.setOnMouseClicked(event -> {StaticSystemController.compareTo = StaticSystemController.compareToPoints;StaticSystemController.updateViews(false,true);});
        }
        if(key.equals(totalTeamPointsString))
        {
            textField.setOnMouseClicked(event -> {StaticSystemController.compareTo = StaticSystemController.compareToTeamPoints;StaticSystemController.updateViews(false,true);});
        }
        if(key.equals(nameString))
        {
            textField.setOnMouseClicked(event -> {StaticSystemController.compareTo = StaticSystemController.compareToName;StaticSystemController.updateViews(false,true);});
        }
        if(key.equals(IDString))
        {
            textField.setOnMouseClicked(event -> {StaticSystemController.compareTo = StaticSystemController.compareToID;StaticSystemController.updateViews(false,true);});
        }
        if(key.equals(clanString))
        {
            textField.setOnMouseClicked(event -> {StaticSystemController.compareTo = StaticSystemController.compareToClan;StaticSystemController.updateViews(false,true);});
        }
        if(key.equals(playedRacesString))
        {
            textField.setOnMouseClicked(event -> {StaticSystemController.compareTo = StaticSystemController.compareToPlayedRaces;StaticSystemController.updateViews(false,true);});
        }
        if(key.equals(bestLapsString))
        {
            textField.setOnMouseClicked(event -> {StaticSystemController.compareTo = StaticSystemController.compareToBestLaps;StaticSystemController.updateViews(false,true);});
        }
        if(key.equals(lapPerformanceString))
        {
            textField.setOnMouseClicked(event -> {StaticSystemController.compareTo = StaticSystemController.compareToLapPerformance;StaticSystemController.updateViews(false,true);});
        }
        if(key.equals(racePerformanceString))
        {
            textField.setOnMouseClicked(event -> {StaticSystemController.compareTo = StaticSystemController.compareToRacePerformance;StaticSystemController.updateViews(false,true);});
        }
        if(key.equals(timeDifferenceString))
        {
            textField.setOnMouseClicked(event -> {StaticSystemController.compareTo = StaticSystemController.compareToTimeDifference;StaticSystemController.updateViews(false,true);});
        }
        if(key.equals(avgLapPositionString))
        {
            textField.setOnMouseClicked(event -> {StaticSystemController.compareTo = StaticSystemController.compareToAvgLapPosition;StaticSystemController.updateViews(false,true);});
        }
        if(key.equals(avgPositionString))
        {
            textField.setOnMouseClicked(event -> {StaticSystemController.compareTo = StaticSystemController.compareToAvgPosition;StaticSystemController.updateViews(false,true);});
        }
        if(key.equals(totalTimeString))
        {
            textField.setOnMouseClicked(event -> {StaticSystemController.compareTo = StaticSystemController.compareToTotalTime;StaticSystemController.updateViews(false,true);});
        }
        if(key.equals(positionString))
        {
            textField.setOnMouseClicked(event -> {StaticSystemController.compareTo = StaticSystemController.compareToPosition;StaticSystemController.updateViews(false,true);});
        }
        if(key.equals(teamPositionString))
        {
            textField.setOnMouseClicked(event -> {StaticSystemController.compareTo = StaticSystemController.compareToTeamPosition;StaticSystemController.updateViews(false,true);});
        }
    }

    public void setBigField(iTextField textField,int e, boolean header)
    {
        textField.setPrefHeight(maxHeight);
        textField.setMinHeight(minHeight);
        textField.setMaxHeight(maxHeight);

        textField.setMaxWidth(maxWidth);
        textField.setPrefWidth(prefWidth);
        textField.setMinWidth(prefWidth);

        if(!header)
        {
            if (e % 2 == 0) {
                textField.setBackground(StaticSceneBuilder.YGridPaneBackground);
            } else {
                textField.setBackground(StaticSceneBuilder.YGridPane2Background);
            }
        }
        else
        {
            textField.setBackground(StaticSceneBuilder.YGridPaneHeaderBackground);
        }
    }
    public void setMediumField(iTextField textField,int e,boolean header)
    {
        textField.setPrefHeight(maxHeight);
        textField.setMinHeight(minHeight);
        textField.setMaxHeight(maxHeight);

        textField.setPrefWidth(prefWidthMedium);
        textField.setMinWidth(prefWidthMedium);
        textField.setMaxWidth(maxWidth);

        if(!header) {
            if (e % 2 == 0) {
                textField.setBackground(StaticSceneBuilder.YGridPaneBackground);
            } else {
                textField.setBackground(StaticSceneBuilder.YGridPane2Background);
            }
        }
        else
        {
            textField.setBackground(StaticSceneBuilder.YGridPaneHeaderBackground);
        }
    }
    public void setSmallRedField(iTextField textField,int e,boolean header)
    {
        textField.setPrefHeight(maxHeight);
        textField.setMaxHeight(maxHeight);
        textField.setMinHeight(minHeight);

        textField.setPrefWidth(prefSmallWidth);
        textField.setMinWidth(prefSmallWidth);
        textField.setMaxWidth(maxWidth);

        if(!header) {
            if (e % 2 == 0) {
                textField.setBackground(StaticSceneBuilder.YGridPaneRedBackground);
            } else {
                textField.setBackground(StaticSceneBuilder.YGridPane2RedBackground);
            }
        }
        else
        {
            textField.setBackground(StaticSceneBuilder.YGridPaneHeaderBackground);
        }
    }
    public void setSmallField(iTextField textField,int e,boolean header)
    {
        textField.setPrefHeight(maxHeight);
        textField.setMaxHeight(maxHeight);
        textField.setMinHeight(minHeight);

        textField.setPrefWidth(prefSmallWidth);
        textField.setMinWidth(prefSmallWidth);
        textField.setMaxWidth(maxWidth);

        if(!header) {
            if (e % 2 == 0) {
                textField.setBackground(StaticSceneBuilder.YGridPaneBackground);
            } else {
                textField.setBackground(StaticSceneBuilder.YGridPane2Background);
            }
        }
        else
        {
            textField.setBackground(StaticSceneBuilder.YGridPaneHeaderBackground);
        }
    }

    public static final String IDString = "ID";
    public static final String nameString = "Name";
    public static final String clanString = "Clan";
    public static final String playedRacesString = "Played";
    public static final String lapResultsString = "Lap results";
    public static final String raceResultsString = "Race results";
    public static final String bestLapsString = "Best laps";
    public static final String totalTimeString = "Total time";
    public static final String avgPositionString = "Avg. position";
    public static final String avgLapPositionString = "Avg. best lap position";
    public static final String timeDifferenceString = "Time difference";
    public static final String racePerformanceString = "Race performance";
    public static final String lapPerformanceString = "Lap performance";
    public static final String racePointsString = "Race points";
    public static final String raceSepString = "Race: ";
    public static final String lapSepString = "Lap: ";
    public static final String pointsSepString = "Points: ";
    public static final String totalPointsString = "Total points";
    public static final String totalTeamPointsString = "Total team points";
    public static final String positionString = "Position";
    public static final String teamPositionString = "Team position";
    public void createTable()
    {
        List<Pair<String, Boolean>> listOfPairs = new ArrayList<>();

        listOfPairs.add(new Pair<String, Boolean>(positionString, StaticYGridPaneController.showPosition));
        listOfPairs.add(new Pair<String, Boolean>(teamPositionString, StaticYGridPaneController.showTeamPosition));
        listOfPairs.add(new Pair<String, Boolean>(IDString, StaticYGridPaneController.showPlayerId));
        listOfPairs.add(new Pair<String, Boolean>(nameString, StaticYGridPaneController.showPlayerName));
        listOfPairs.add(new Pair<String, Boolean>(clanString, StaticYGridPaneController.showClan));
        listOfPairs.add(new Pair<String, Boolean>(playedRacesString, StaticYGridPaneController.showPlayedEvents));
        listOfPairs.add(new Pair<String, Boolean>(lapResultsString, StaticYGridPaneController.showLapResults));
        listOfPairs.add(new Pair<String, Boolean>(raceResultsString, StaticYGridPaneController.showRaceResults));
        listOfPairs.add(new Pair<String, Boolean>(bestLapsString, StaticYGridPaneController.showBestLaps));
        listOfPairs.add(new Pair<String, Boolean>(totalTimeString, StaticYGridPaneController.showTotalTimes));
        listOfPairs.add(new Pair<String, Boolean>(avgPositionString, StaticYGridPaneController.showAveragePosition));
        listOfPairs.add(new Pair<String, Boolean>(avgLapPositionString, StaticYGridPaneController.showAverageLapPosition));
        listOfPairs.add(new Pair<String, Boolean>(timeDifferenceString, StaticYGridPaneController.showTimeDifference));
        listOfPairs.add(new Pair<String, Boolean>(racePerformanceString, StaticYGridPaneController.showRacePerformance));
        listOfPairs.add(new Pair<String, Boolean>(lapPerformanceString, StaticYGridPaneController.showLapPerformance));
        listOfPairs.add(new Pair<String, Boolean>(racePointsString, StaticYGridPaneController.showRacePoints));
        //listOfPairs.add(new Pair<String, Boolean>("Team player points", StaticYGridPaneController.showTeamPoints));

        int events = 0;
        if(StaticSystemController.SuperDTOlist != null && StaticSystemController.SuperDTOlist.size() != 0)
        {
            for (SuperDTO sup : StaticSystemController.SuperDTOlist) {
                int t = 0;
                for (int i = 0; i < sup.m_playerStats.length; i++) {
                    if (t < sup.m_playerStats[i].getEventStatsLength()) {
                        t = sup.m_playerStats[i].getEventStatsLength();
                    }
                }
                for (int i = 0; i < t; i++) {
                    listOfPairs.add((new Pair<String, Boolean>(raceSepString + sup.getSuperDTOname(), StaticYGridPaneController.showEveryRaceResults)));
                }
            }

            for (SuperDTO sup : StaticSystemController.SuperDTOlist) {
                int t = 0;
                for (int i = 0; i < sup.m_playerStats.length; i++) {
                    if (t < sup.m_playerStats[i].getEventStatsLength()) {
                        t = sup.m_playerStats[i].getEventStatsLength();
                    }
                }
                for (int i = 0; i < t; i++) {
                    listOfPairs.add((new Pair<String, Boolean>(pointsSepString + sup.getSuperDTOname(), StaticYGridPaneController.showEveryRacePoints)));
                }
            }

            for (SuperDTO sup : StaticSystemController.SuperDTOlist) {
                int t = 0;
                for (int i = 0; i < sup.m_playerStats.length; i++) {
                    if (t < sup.m_playerStats[i].getEventStatsLength()) {
                        t = sup.m_playerStats[i].getEventStatsLength();
                    }
                }
                for (int i = 0; i < t; i++) {
                    listOfPairs.add((new Pair<String, Boolean>(lapSepString + sup.getSuperDTOname(), StaticYGridPaneController.showEveryLapResults)));
                }
            }

            listOfPairs.add(new Pair<String, Boolean>(totalPointsString, StaticYGridPaneController.showTotalPoints));
            listOfPairs.add(new Pair<String, Boolean>(totalTeamPointsString, StaticYGridPaneController.showTotalTeamPoints));
            //listOfPairs.add(new Pair<String, Boolean>(positionString, StaticYGridPaneController.showPosition));
            //listOfPairs.add(new Pair<String, Boolean>(teamPositionString, StaticYGridPaneController.showTeamPosition));

            int i = 0;
            for (Pair<String, Boolean> pair : listOfPairs) {
                if (pair.getValue()) {
                    if (!StaticYGridPaneController.enableVerticalHeaders) {
                        if (!pair.getKey().startsWith(raceSepString) && !pair.getKey().startsWith(pointsSepString) && !pair.getKey().startsWith(lapSepString)) {
                            iTextField textField = new iTextField(pair.getKey());
                            Tooltip tooltip = new Tooltip(pair.getKey());
                            textField.setTooltip(tooltip);
                            setSmallField(textField,0,true);
                            setAction(textField, pair.getKey());
                            add(textField, i, 0);
                            i++;
                        } else if (pair.getKey().startsWith(raceSepString)) {
                            iTextField textField = new iTextField(pair.getKey().replaceFirst(raceSepString, ""));
                            Tooltip tooltip = new Tooltip(pair.getKey());
                            textField.setTooltip(tooltip);
                            setSmallField(textField,0,true);
                            add(textField, i, 0);
                            i++;
                        } else if (pair.getKey().startsWith(pointsSepString)) {
                            iTextField textField = new iTextField(pair.getKey().replaceFirst(pointsSepString, ""));
                            Tooltip tooltip = new Tooltip(pair.getKey());
                            textField.setTooltip(tooltip);
                            setSmallField(textField,0,true);
                            add(textField, i, 0);
                            i++;
                        } else if (pair.getKey().startsWith(lapSepString)) {
                            iTextField textField = new iTextField(pair.getKey().replaceFirst(lapSepString, ""));
                            Tooltip tooltip = new Tooltip(pair.getKey());
                            textField.setTooltip(tooltip);
                            setSmallField(textField,0,true);
                            add(textField, i, 0);
                            i++;
                        }
                    }
                    else {
                        if (!pair.getKey().startsWith("Race: ") && !pair.getKey().startsWith("Points: ") && !pair.getKey().startsWith("Lap: ")) { //TODO
                            iTextField textField = new iTextField(pair.getKey());
                            Tooltip tooltip = new Tooltip(pair.getKey());
                            textField.setBackground(RealInvisibleBackground);
                            textField.setTooltip(tooltip);

                            textField.setRotate(90);
                            textField.setAlignment(Pos.CENTER_RIGHT);

                            textField.setPrefWidth(prefWidth);
                            textField.setMinWidth(minWidth);
                            textField.setMinHeight(minHeight);
                            textField.setMaxWidth(maxWidth);
                            textField.setMaxHeight(maxHeight);
                            //add(textField, i, 0);
                            getChildren().add(textField);
                            textFields.add(textField);
                            i++;
                        } else if (pair.getKey().startsWith("Race: ")) {
                            iTextField textField = new iTextField(pair.getKey().replaceFirst("Race: ", ""));
                            textField.setBackground(RealInvisibleBackground);
                            Tooltip tooltip = new Tooltip(pair.getKey());
                            textField.setTooltip(tooltip);

                            textField.setRotate(90);
                            textField.setAlignment(Pos.CENTER_RIGHT);
                            textField.setPrefHeight(prefWidth);
                            textField.setMinHeight(prefWidth);
                            textField.setMinHeight(minHeight);
                            textField.setMaxWidth(maxWidth);
                            textField.setMaxHeight(maxHeight);

                            textField.setPrefWidth(prefSmallWidth);
                            textField.setMinWidth(prefSmallWidth);
                            //add(textField, i, 0);
                            getChildren().add(textField);
                            textFields.add(textField);
                            i++;
                        } else if (pair.getKey().startsWith("Points: ")) {
                            iTextField textField = new iTextField(pair.getKey().replaceFirst("Points: ", ""));
                            textField.setBackground(RealInvisibleBackground);
                            Tooltip tooltip = new Tooltip(pair.getKey());
                            textField.setTooltip(tooltip);

                            textField.setRotate(90);
                            textField.setAlignment(Pos.CENTER_RIGHT);
                            textField.setPrefHeight(prefWidth);
                            textField.setMinHeight(prefWidth);
                            textField.setMinHeight(minHeight);
                            textField.setMaxWidth(maxWidth);
                            textField.setMaxHeight(maxHeight);

                            textField.setPrefWidth(prefSmallWidth);
                            textField.setMinWidth(prefSmallWidth);
                            //add(textField, i, 0);
                            getChildren().add(textField);
                            textFields.add(textField);
                            i++;
                        } else if (pair.getKey().startsWith("Lap: ")) {
                            iTextField textField = new iTextField(pair.getKey().replaceFirst("Lap: ", ""));
                            textField.setBackground(RealInvisibleBackground);
                            Tooltip tooltip = new Tooltip(pair.getKey());
                            textField.setTooltip(tooltip);

                            textField.setRotate(90);
                            textField.setAlignment(Pos.CENTER_RIGHT);
                            textField.setPrefHeight(prefWidth);
                            textField.setMinHeight(prefWidth);
                            textField.setMinHeight(minHeight);
                            textField.setMaxWidth(maxWidth);
                            textField.setMaxHeight(maxHeight);

                            textField.setPrefWidth(prefSmallWidth);
                            textField.setMinWidth(prefSmallWidth);
                            //add(textField, i, 0);
                            getChildren().add(textField);
                            textFields.add(textField);
                            i++;
                        }
                    }
                }
            }

            StaticDTOHandler.updatePlayerList();
            playerList = StaticDTOHandler.getPlayerList();
            teamList = StaticDTOHandler.getTeamList();

            //setPlayerAndTeamPositions();

            //if (StaticSystemController.compareTo == StaticSystemController.compareToTeamPoints && StaticSystemController.TeamsByClan) sortByTeam();
            //else if (StaticSystemController.compareTo == StaticSystemController.compareToTeamPoints && !StaticSystemController.TeamsByClan) Collections.sort(playerList, new PlayerComparator());
            //else Collections.sort(playerList, new PlayerComparator());

            int e = 1;
            i = 0;
            for (Player player : playerList) {
                int race_counter = 0;
                int race_counter2 = 0;
                int race_counter3 = 0;

                for (Pair<String, Boolean> pair : listOfPairs) {
                    if (pair.getValue()) {
                        if (pair.getKey().startsWith(lapSepString)) {
                            if (player.lapPosList.size() > race_counter3) {
                                if (player.lapPosList.get(race_counter3) != 0) {
                                    iTextField textField = new iTextField(String.valueOf(player.lapPosList.get(race_counter3)));

                                    setSmallField(textField, e,false);

                                    add(textField, i, e);
                                } else {
                                    iTextField textField = new iTextField("");

                                    setSmallRedField(textField, e,false);

                                    add(textField, i, e);
                                }

                                race_counter3++;
                            }
                            i++;
                        } else if (pair.getKey().startsWith(raceSepString)) {
                            if (player.racePosList.size() > race_counter) {
                                if (player.racePosList.get(race_counter) != 0) {
                                    iTextField textField = new iTextField(String.valueOf(player.racePosList.get(race_counter)));

                                    setSmallField(textField, e,false);

                                    add(textField, i, e);
                                } else {
                                    iTextField textField = new iTextField("");

                                    setSmallRedField(textField, e,false);

                                    add(textField, i, e);
                                }

                                race_counter++;
                            }
                            i++;
                        } else if (pair.getKey().startsWith(pointsSepString)) {
                            if (player.racePointList.size() > race_counter2) {
                                if (player.racePointList.get(race_counter2) != 0) {
                                    iTextField textField = new iTextField(String.valueOf(player.racePointList.get(race_counter2)));
                                    setSmallField(textField, e,false);

                                    add(textField, i, e);

                                } else {
                                    iTextField textField = new iTextField("");
                                    setSmallRedField(textField, e,false);

                                    add(textField, i, e);
                                }
                                race_counter2++;
                            }
                            i++;
                        } else if (pair.getKey().equals(IDString)) {
                            iTextField textField = new iTextField(String.valueOf(player.id));
                            setBigField(textField,e,false);

                            add(textField, i, e);
                            i++;
                        } else if (pair.getKey().equals(nameString)) {
                            iTextField textField = new iTextField(String.valueOf(player.name));
                            setBigField(textField,e,false);


                            add(textField, i, e);
                            i++;
                        } else if (pair.getKey().equals(playedRacesString)) {
                            iTextField textField = new iTextField(String.valueOf(player.playedRaces));
                            setMediumField(textField,e,false);

                            add(textField, i, e);
                            i++;
                        } else if (pair.getKey().equals(clanString)) {
                            iTextField textField = new iTextField(String.valueOf(player.clan));
                            setMediumField(textField,e,false);

                            add(textField, i, e);
                            i++;
                        } else if (pair.getKey().equals(totalPointsString)) {
                            iTextField textField = new iTextField(String.valueOf(player.totalPoints));
                            setMediumField(textField,e,false);

                            add(textField, i, e);
                            i++;
                        } else if (pair.getKey().equals(avgLapPositionString)) {
                            iTextField textField = new iTextField(String.valueOf(player.averageLapPosition));
                            setMediumField(textField,e,false);

                            add(textField, i, e);
                            i++;
                        } else if (pair.getKey().equals(avgPositionString)) {
                            iTextField textField = new iTextField(String.valueOf(player.averagePosition));
                            setMediumField(textField,e,false);

                            add(textField, i, e);
                            i++;
                        } else if (pair.getKey().equals(totalTeamPointsString)) {
                            if (StaticSystemController.TeamsByClan) {
                                if (teamList != null) {
                                    for (Team team : teamList) {
                                        if (team.TAG.equals(player.clan)) {
                                            iTextField textField = new iTextField(String.valueOf(team.givePoints()));
                                            setMediumField(textField,e,false);
                                            add(textField, i, e);
                                            break;
                                        }
                                    }
                                }
                            } else {
                                iTextField textField = new iTextField(String.valueOf(StaticDTOHandler.giveAsIntegerValue(player.teamPointList)));
                                setMediumField(textField,e,false);
                                add(textField, i, e);
                            }
                            i++;
                        } else if (pair.getKey().equals("Team player points")) { //REMOVED
                            if (StaticSystemController.TeamsByClan) {
                                if (teamList != null) {
                                    for (Team team : teamList) {
                                        if (team.TAG.equals(player.clan)) {
                                            iTextField textField = new iTextField(String.valueOf(team.givePointsAsString()));

                                            setMediumField(textField,e,false);
                                            add(textField, i, e);
                                            break;
                                        }
                                    }
                                }
                                i++;
                            } else {
                                iTextField textField = new iTextField(String.valueOf(player.totalTeamPoints));
                                setMediumField(textField,e,false);

                                add(textField, i, e);
                                i++;
                            }
                        } else if (pair.getKey().equals(bestLapsString)) {
                            iTextField textField = new iTextField(String.valueOf(player.bestLaps));
                            setMediumField(textField,e,false);

                            add(textField, i, e);
                            i++;
                        } else if (pair.getKey().equals(lapResultsString)) {
                            iTextField textField = new iTextField(String.valueOf(player.lapResults));
                            Tooltip tooltip = new Tooltip(player.lapResults);
                            textField.setTooltip(tooltip);
                            setBigField(textField,e,false);

                            add(textField, i, e);
                            i++;
                        } else if (pair.getKey().equals(raceResultsString)) {
                            iTextField textField = new iTextField(String.valueOf(player.raceResults));
                            Tooltip tooltip = new Tooltip(player.raceResults);
                            textField.setTooltip(tooltip);
                            setBigField(textField,e,false);

                            add(textField, i, e);
                            i++;
                        } else if (pair.getKey().equals(racePointsString)) {
                            iTextField textField = new iTextField(String.valueOf(player.givePointsAsString()));
                            Tooltip tooltip = new Tooltip(player.givePointsAsString());
                            textField.setTooltip(tooltip);
                            setBigField(textField,e,false);

                            add(textField, i, e);
                            i++;
                        }
                        else if (pair.getKey().equals(positionString)) {
                            iTextField textField = new iTextField(String.valueOf(player.position));
                            Tooltip tooltip = new Tooltip(String.valueOf(player.position));
                            textField.setTooltip(tooltip);
                            setMediumField(textField,e,false);

                            add(textField, i, e);
                            i++;
                        }
                        else if (pair.getKey().equals(teamPositionString)) {
                            iTextField textField = new iTextField(String.valueOf(player.teamPosition));
                            Tooltip tooltip = new Tooltip(String.valueOf(player.teamPosition));
                            textField.setTooltip(tooltip);
                            setMediumField(textField,e,false);

                            add(textField, i, e);
                            i++;
                        }else {
                            iTextField textField = new iTextField("N/A");
                            setMediumField(textField,e,false);

                            add(textField, i, e);
                            i++;
                        }
                    }
                }
                i = 0;
                e++;
            }
        }
    }

    public void setPlayerAndTeamPositions()
    {
        int tem = StaticSystemController.compareTo;
        StaticSystemController.compareTo = StaticSystemController.compareToPoints;
        Collections.sort(playerList, new PlayerComparator());

        int i = 1;
        for(Player player: playerList)
        {
            player.position = i;
            i++;
        }

        if(StaticSystemController.TeamsByClan) {
            Collections.sort(teamList, new TeamComparator());

            i = 1;
            for(Team team: teamList) {
                for (Player player : playerList)
                {
                    if(player.clan.equals(team.TAG)) {
                        player.teamPosition = i;
                    }
                }
                i++;
            }
        }
        StaticSystemController.compareTo = tem;

    }
    public void sortByTeam()
    {
        List<Player> temp = new ArrayList<>();
        Collections.sort(playerList,new PlayerComparator());
        Collections.sort(teamList, new TeamComparator());
        if(StaticSystemController.TeamsByClan) {
            for (Team team : teamList) {
                for (Player player : playerList) {
                    if (player.clan.equals(team.TAG)) {
                        temp.add(player);
                    }
                }
            }
        }


        playerList = temp;
    }
}
