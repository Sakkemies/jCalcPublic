package org.jcalc.handlers;

import org.jcalc.dto.Player;
import org.jcalc.dto.SuperDTO;
import org.jcalc.dto.Team;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class StaticHTMLHandler
{
    public static String HTMLData = "";
    public static String backgroundColor = "#2b2b2b";
    public static String borderColor = "#240303";
    public static String headerColor = "#2a2f33";
    public static String row1color = "#1a1a1a";
    public static String row2color = "#242424";
    public static String fontColor = "#f0f0f0";
    public static String playerNames = "";
    public static void createHTMLFile()
    {
        HTMLData = "";
        getStart();
    }

    public static void getStart()
    {
        StaticDTOHandler.updatePlayerList();
        HTMLData += "<!DOCTYPE html>\n";
        getHead(StaticSystemController.projectName);
        getBody();
        StaticFileSaver.saveCustomData(HTMLData,".html",StaticSystemController.projectName,"Export HTML", "Export HTML");
    }
    public static void getHead(String head)
    {
        HTMLData += "<html>\n<head>\n";
        HTMLData += "<title>" + head + "</title>\n";
        getStyle("table", "collapse", "100%", "15px", borderColor);
        getStyleForTable();

        HTMLData += "</head>\n";
    }
    public static void getStyleForTable()
    {
        File file = new File(StaticSystemController.defaultProjectBackgroundPath);
        String path = file.getAbsolutePath();

        HTMLData += "<style>\n";
        HTMLData += "th{  \n" +
                "        border: 1px dodded " +borderColor+";   \n" +
                "        padding: 15px;\n" +
                "        font-family:Georgia, Garamond, Serif;color:#f0f0f0;\n" +
                "        opacity: 0.85;\n" +
                "        font:bold 18px/1.1em Arial, Helvetica, Sans-Serif;text-shadow: 4px 4px 4px #696969;color:white; \n" +
                "    }\n" +"td{  \n" +
                "        border: 1px dodded " +borderColor+";   \n" +
                "        padding: 15px;\n" +
                "        font-family:Georgia, Garamond, Serif;color:#f0f0f0;\n" +
                "        opacity: 0.85;\n" +
                "        font:bold 15px/1.1em Arial, Helvetica, Sans-Serif;text-shadow: 4px 4px 4px #696969;color:white; \n" +
                "    }\n" +
                "    h3{  \n" +
                "        border: 0px solid green;   \n" +
                "        padding: 25px;\n" +
                "        text-align: center;" +
                "        font:bold 25px/1.1em Arial, Helvetica, Sans-Serif;text-shadow: 4px 4px 4px black;color:white; \n" +
                "    }" +
                "    GeneratedMarquee {\n" +
                "        font:bold 10px/1.1em Arial, Helvetica, Sans-Serif;text-shadow: 4px 4px 4px #696969;color:white; \n" +
                "        color:#f0f0f0;\n" +
                "        background-color:#f0f0f0;\n" +
                "        padding:1.5em;}\n" +
                "       body {\n" +
                //"       background-image: url(\'" + path +"\');\n" +
                "       background-image: url('background.png');\n" +
                "       background-repeat: no-repeat;\n" +
                "       background-attachment: fixed;\n" +
                "       background-size: 100% 100%;\n" +
                "       }\n";
        System.out.println(path);
        HTMLData += "</style>\n";
    }

    public static void getStyle(String component, String border_collapse, String width, String padding, String border)
    {
        HTMLData += "<style>\n";
        HTMLData += component+"{border-collapse: " + border_collapse + "; width: " + width + ";}\n";
        //HTMLData += "th,td{"+"border: " + border +"; padding: " + padding +"; font color=" + fontColor + "}\n";
        HTMLData += "</style>\n";
    }

    public static void getBody()
    {
        HTMLData += "<body bgcolor=" + backgroundColor + ">\n<table>\n";
        //HTMLData += "<body>\n<table>\n";
        HTMLData += "<h3>" + StaticSystemController.projectName + "</h3>";
        HTMLData += "<marquee class=\"GeneratedMarquee\" direction=\"left\" scrollamount=\"8\" behavior=\"scroll\">" + playerNames +"</marquee>";
        getHeaders();
        int index = 0;
        for(Player player: StaticDTOHandler.playerList) {
            getDatas(index,player);
            index++;
        }
        HTMLData += "</table>\n</body>\n</html>\n";
    }

    public static void getHeaders()
    {
        HTMLData += "<tr>\n";

        if(StaticYGridPaneController.showPosition) {getHeader(positionString); keyPairs.add(positionString);}
        if(StaticYGridPaneController.showTeamPosition) {getHeader(teamPositionString); keyPairs.add(teamPositionString);}
        if(StaticYGridPaneController.showPlayerId) {getHeader(IDString); keyPairs.add(IDString);}
        if(StaticYGridPaneController.showPlayerName) {getHeader(playerNameString); keyPairs.add(playerNameString);}
        if(StaticYGridPaneController.showClan) {getHeader(clanString); keyPairs.add(clanString);}
        if(StaticYGridPaneController.showLapResults) {getHeader(lapResultsString); keyPairs.add(lapResultsString);}
        if(StaticYGridPaneController.showRaceResults) {getHeader(raceResultsString); keyPairs.add(raceResultsString);}
        if(StaticYGridPaneController.showBestLaps) {getHeader(bestLapsString); keyPairs.add(bestLapsString);}
        if(StaticYGridPaneController.showPlayedEvents) {getHeader(playedEventsString); keyPairs.add(playedEventsString);}
        if(StaticYGridPaneController.showAveragePosition) {getHeader(avgPositionString); keyPairs.add(avgPositionString);}
        if(StaticYGridPaneController.showAverageLapPosition) {getHeader(avgLapPositionString); keyPairs.add(avgLapPositionString);}
        if(StaticYGridPaneController.showTotalTimes) {getHeader(totalTimeString); keyPairs.add(totalTimeString);}
        if(StaticYGridPaneController.showTimeDifference) {getHeader(timeDifferenceString); keyPairs.add(timeDifferenceString);}
        if(StaticYGridPaneController.showRacePerformance) {getHeader(racePerformanceString); keyPairs.add(racePerformanceString);}
        if(StaticYGridPaneController.showLapPerformance) {getHeader(lapPerformanceString); keyPairs.add(lapPerformanceString);}
        if(StaticYGridPaneController.showRacePoints) {getHeader(racePointsString); keyPairs.add(racePointsString);}
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
                    if(StaticYGridPaneController.showEveryRaceResults) {getHeader(everyRaceResultsString + sup.getSuperDTOname()); keyPairs.add((everyRaceResultsString + sup.getSuperDTOname()));}
                    if(StaticYGridPaneController.showEveryRacePoints) {getHeader(everyRacePointsString + sup.getSuperDTOname()); keyPairs.add((everyRacePointsString + sup.getSuperDTOname()));}
                    if(StaticYGridPaneController.showEveryLapResults) {getHeader(everyLapResultsString + sup.getSuperDTOname()); keyPairs.add((everyLapResultsString + sup.getSuperDTOname()));}
                }
            }
        }

        if(StaticYGridPaneController.showTotalPoints) {getHeader(totalPointsString); keyPairs.add(totalPointsString);}
        if(StaticYGridPaneController.showTotalTeamPoints) {getHeader(totalTeamPointsString); keyPairs.add(totalTeamPointsString);}

       // for(int i = 0; i < 6; i++)
        //{
        //    getHeader("Header " + i);
       // }
        HTMLData += "</tr>\n";
    }

    public static void getHeader(String header)
    {

        HTMLData += "<th bgcolor=" + headerColor + ">" + header + "</th>\n";
    }
    public static void getData(String data, int index)
    {
        if(index%2 == 0) HTMLData += "<td bgcolor=" + row1color + ">"+ data + "</td>\n";
        else HTMLData += "<td bgcolor=" + row2color + ">"+ data + "</td>\n";
    }
    public static void getDatas(int index,Player player)
    {
        if(!playerNames.equals(""))playerNames+=", " + player.name;
        else playerNames += player.name;

        HTMLData += "<tr>\n";

        if (StaticYGridPaneController.showPosition) {
            getData(player.position+"",index);
        }
        if (StaticYGridPaneController.showTeamPosition) {
            getData(player.teamPosition +"",index);
        }

            if (StaticYGridPaneController.showPlayerId) {
                getData(player.id+"",index);
            }
            if (StaticYGridPaneController.showPlayerName) {
                getData(player.name,index);
            }
            if (StaticYGridPaneController.showClan) {
                getData(player.clan,index);
            }
            if (StaticYGridPaneController.showLapResults) {
                getData(player.lapResults,index);
            }
            if (StaticYGridPaneController.showRaceResults) {
                getData(player.raceResults,index);
            }
            if (StaticYGridPaneController.showBestLaps) {
                getData(player.bestLaps+"",index);
            }
            if (StaticYGridPaneController.showPlayedEvents) {
                getData(player.playedRaces+"",index);
            }
            if (StaticYGridPaneController.showAveragePosition) {
                getData(player.averagePosition+"",index);
            }
            if (StaticYGridPaneController.showAverageLapPosition) {
                getData(player.averageLapPosition+"",index);
            }
            if (StaticYGridPaneController.showTotalTimes) {
                getData(player.totalTime+"",index);
            }
            if (StaticYGridPaneController.showTimeDifference) {
                getData(player.timeDifference+"",index);
            }
            if (StaticYGridPaneController.showRacePerformance) {
                getData(player.racePerformance+"",index);
            }
            if (StaticYGridPaneController.showLapPerformance) {
                getData(player.lapPerformance,index);
            }
            if (StaticYGridPaneController.showRacePoints) {
                getData(player.racePoints+"",index);
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
                    getData(player.racePosList.get(i)+"",index);
                }
            }

            for(int i = 0; i < raP; i++)
            {
                if (StaticYGridPaneController.showEveryRacePoints) {
                    getData(player.racePointList.get(i)+"",index);
                }
            }
            for(int i = 0; i < laP; i++)
            {
                if (StaticYGridPaneController.showEveryLapResults) {
                    getData(player.lapPosList.get(i)+"",index);
                }
            }
            //}
            //}

            if (StaticYGridPaneController.showTotalPoints) {
                getData(player.totalPoints+"",index);
            }
            if (StaticYGridPaneController.showTotalTeamPoints)
            {
                if(StaticSystemController.TeamsByClan)
                {
                    for(Team team: StaticDTOHandler.teamList)
                    {
                        if(player.clan.equals(team.TAG))
                        {
                            getData(team.givePoints()+"",index);
                            break;
                        }
                    }
                }
                else getData(player.totalTeamPoints+"",index);
            }
        HTMLData += "</tr>\n";
    }

    public static List<String> keyPairs = new ArrayList<>();
    public static final String IDString = "ID";
    public static final String playerNameString = "Name";
    public static final String clanString = "Clan";
    public static final String lapResultsString = "Lap results";
    public static final String raceResultsString = "Race results";
    public static final String bestLapsString = "Best laps";
    public static final String playedEventsString = "Played";
    public static final String avgPositionString = "Average position";
    public static final String avgLapPositionString = "Average best lap position";
    public static final String totalTimeString = "Total time";
    public static final String timeDifferenceString = "Time difference";
    public static final String racePerformanceString = "Race performance";
    public static final String lapPerformanceString = "Lap performance";
    public static final String racePointsString = "Race points";
    //public static final String teamPointsString = "Team points";
    public static final String totalPointsString = "Total points";
    public static final String totalTeamPointsString = "Total team points";
    public static final String positionString = "Position";
    public static final String teamPositionString = "Team position";
    public static final String everyRaceResultsString = "Race position: ";
    public static final String everyRacePointsString = "Race points: ";
    public static final String everyLapResultsString = "Lap results: ";
}


