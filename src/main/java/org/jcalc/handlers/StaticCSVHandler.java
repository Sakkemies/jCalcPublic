package org.jcalc.handlers;

import javafx.util.Pair;
import org.jcalc.dto.Player;
import org.jcalc.dto.SuperDTO;
import org.jcalc.dto.Team;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;

public class StaticCSVHandler
{

    public static String dataText = "";
    public static String dataSplitter = ";";
    public static List<String> keyPairs = new ArrayList<>();
    public static void createCSVFile()
    {
        StaticDTOHandler.updatePlayerList();
        keyPairs = new ArrayList<>();
        dataText = "";
        setFields();
        createDATA();
    }

    public static void createDATA()
    {
        for(Player player: StaticDTOHandler.playerList) {
            dataText += "\n";
            if (StaticYGridPaneController.showPosition) {
                dataText += player.position + dataSplitter;
            }
            if (StaticYGridPaneController.showTeamPosition) {
                dataText += player.teamPosition + dataSplitter;
            }
            if (StaticYGridPaneController.showPlayerId) {
                dataText += player.id + dataSplitter;
            }
            if (StaticYGridPaneController.showPlayerName) {
                dataText += player.name + dataSplitter;
            }
            if (StaticYGridPaneController.showClan) {
                dataText += player.clan + dataSplitter;
            }
            if (StaticYGridPaneController.showLapResults) {
                dataText += player.lapResults + dataSplitter;
            }
            if (StaticYGridPaneController.showRaceResults) {
                dataText += player.raceResults + dataSplitter;
            }
            if (StaticYGridPaneController.showBestLaps) {
                dataText += player.bestLaps + dataSplitter;
            }
            if (StaticYGridPaneController.showPlayedEvents) {
                dataText += player.playedRaces + dataSplitter;
            }
            if (StaticYGridPaneController.showAveragePosition) {
                dataText += player.averagePosition + dataSplitter;
            }
            if (StaticYGridPaneController.showAverageLapPosition) {
                dataText += player.averageLapPosition + dataSplitter;
            }
            if (StaticYGridPaneController.showTotalTimes) {
                dataText += player.totalTime + dataSplitter;
            }
            if (StaticYGridPaneController.showTimeDifference) {
                dataText += player.timeDifference + dataSplitter;
            }
            if (StaticYGridPaneController.showRacePerformance) {
                dataText += player.racePerformance + dataSplitter;
            }
            if (StaticYGridPaneController.showLapPerformance) {
                dataText += player.lapPerformance + dataSplitter;
            }
            if (StaticYGridPaneController.showRacePoints) {
                dataText += player.racePoints + dataSplitter;
            }
            //if (StaticYGridPaneController.showTeamPoints) {
            //    dataText += "" + dataSplitter;
            //}

            //if (StaticSystemController.SuperDTOlist != null && StaticSystemController.SuperDTOlist.size() != 0) {
                //for (SuperDTO sup : StaticSystemController.SuperDTOlist) {
                    int raP = player.racePointList.size();
                    int raR = player.racePosList.size();
                    int laP = player.lapPosList.size();
                    for (int i = 0; i < raR; i++) {
                        if (StaticYGridPaneController.showEveryRaceResults) {
                            dataText += player.racePosList.get(i) + dataSplitter;
                        }
                    }

                    for(int i = 0; i < raP; i++)
                    {
                        if (StaticYGridPaneController.showEveryRacePoints) {
                            dataText += player.racePointList.get(i) + dataSplitter;
                        }
                    }
                    for(int i = 0; i < laP; i++)
                    {
                        if (StaticYGridPaneController.showEveryLapResults) {
                            dataText += player.lapPosList.get(i) + dataSplitter;
                        }
                    }
                //}
            //}

            if (StaticYGridPaneController.showTotalPoints) {
                dataText += player.totalPoints + dataSplitter;
            }
            if (StaticYGridPaneController.showTotalTeamPoints)
            {
                if(StaticSystemController.TeamsByClan)
                {
                    for(Team team: StaticDTOHandler.teamList)
                    {
                        if(player.clan.equals(team.TAG))
                        {
                            dataText += team.givePoints() + dataSplitter;
                            break;
                        }
                    }
                }
                else dataText += player.totalTeamPoints + dataSplitter;
            }
        }
        System.out.println(dataText);
        StaticFileSaver.saveCustomData(dataText,".csv",StaticSystemController.projectName,"Export CSV", "Export CSV");
    }

    public static final String IDString = "ID;";
    public static final String playerNameString = "Name;";
    public static final String clanString = "Clan;";
    public static final String lapResultsString = "Lap results;";
    public static final String raceResultsString = "Race results;";
    public static final String bestLapsString = "Best laps;";
    public static final String playedEventsString = "Played;";
    public static final String avgPositionString = "Average position;";
    public static final String avgLapPositionString = "Average best lap position;";
    public static final String totalTimeString = "Total time;";
    public static final String timeDifferenceString = "Time difference;";
    public static final String racePerformanceString = "Race performance;";
    public static final String lapPerformanceString = "Lap performance;";
    public static final String racePointsString = "Race points;";
    public static final String teamPointsString = "Team points;";
    public static final String totalPointsString = "Total points;";
    public static final String totalTeamPointsString = "Total team points;";
    public static final String positionString = "Position;";
    public static final String teamPositionString = "Team position;";
    public static final String everyRaceResultsString = "Race position: ";
    public static final String everyRacePointsString = "Race points: ";
    public static final String everyLapResultsString = "Lap results: ";


    public static void setFields()
    {
        if(StaticYGridPaneController.showPosition) {dataText += positionString; keyPairs.add(positionString);}
        if(StaticYGridPaneController.showTeamPosition) {dataText += teamPositionString; keyPairs.add(teamPositionString);}
        if(StaticYGridPaneController.showPlayerId) {dataText += IDString; keyPairs.add(IDString);}
        if(StaticYGridPaneController.showPlayerName) {dataText += playerNameString; keyPairs.add(playerNameString);}
        if(StaticYGridPaneController.showClan) {dataText += clanString; keyPairs.add(clanString);}
        if(StaticYGridPaneController.showLapResults) {dataText += lapResultsString; keyPairs.add(lapResultsString);}
        if(StaticYGridPaneController.showRaceResults) {dataText += raceResultsString; keyPairs.add(raceResultsString);}
        if(StaticYGridPaneController.showBestLaps) {dataText += bestLapsString; keyPairs.add(bestLapsString);}
        if(StaticYGridPaneController.showPlayedEvents) {dataText += playedEventsString; keyPairs.add(playedEventsString);}
        if(StaticYGridPaneController.showAveragePosition) {dataText += avgPositionString; keyPairs.add(avgPositionString);}
        if(StaticYGridPaneController.showAverageLapPosition) {dataText += avgLapPositionString; keyPairs.add(avgLapPositionString);}
        if(StaticYGridPaneController.showTotalTimes) {dataText += totalTimeString; keyPairs.add(totalTimeString);}
        if(StaticYGridPaneController.showTimeDifference) {dataText += timeDifferenceString; keyPairs.add(timeDifferenceString);}
        if(StaticYGridPaneController.showRacePerformance) {dataText += racePerformanceString; keyPairs.add(racePerformanceString);}
        if(StaticYGridPaneController.showLapPerformance) {dataText += lapPerformanceString; keyPairs.add(lapPerformanceString);}
        if(StaticYGridPaneController.showRacePoints) {dataText += racePointsString; keyPairs.add(racePointsString);}
        //if(StaticYGridPaneController.showTeamPoints) {dataText += teamPointsString; keyPairs.add(teamPointsString);}

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
                    if(StaticYGridPaneController.showEveryRaceResults) {dataText += everyRaceResultsString + sup.getSuperDTOname() + dataSplitter; keyPairs.add((everyRaceResultsString + sup.getSuperDTOname()) + dataSplitter);}
                    if(StaticYGridPaneController.showEveryRacePoints) {dataText += everyRacePointsString + sup.getSuperDTOname() + dataSplitter; keyPairs.add((everyRacePointsString + sup.getSuperDTOname()) + dataSplitter);}
                    if(StaticYGridPaneController.showEveryLapResults) {dataText += everyLapResultsString + sup.getSuperDTOname() + dataSplitter; keyPairs.add((everyLapResultsString + sup.getSuperDTOname()) + dataSplitter);}
                }
            }
        }

        if(StaticYGridPaneController.showTotalPoints) {dataText += totalPointsString; keyPairs.add(totalPointsString);}
        if(StaticYGridPaneController.showTotalTeamPoints) {dataText += totalTeamPointsString; keyPairs.add(totalTeamPointsString);}

        //System.out.println(dataText);
    }
}
