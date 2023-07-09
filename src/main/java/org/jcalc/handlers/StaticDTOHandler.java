package org.jcalc.handlers;

import org.jcalc.dto.Player;
import org.jcalc.dto.SuperDTO;
import org.jcalc.dto.Team;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class StaticDTOHandler
{
    public static List<Player> playerList = new ArrayList<>();
    public static List<Team> teamList = new ArrayList<>();
    public static int index = 0;

    public static List<Player> getPlayerList()
    {
        return playerList;
    }
    public static List<Team> getTeamList()
    {
        return teamList;
    }

    public static void updatePlayerList()
    {
        playerList = new ArrayList<>();
        teamList = new ArrayList<>();
        index = 0;

        for(SuperDTO superDTO: StaticSystemController.SuperDTOlist)
        {

            for(int i = 0; i < superDTO.m_playerStats.length; i++)
            {
                boolean found = false;
                if(playerList!=null)
                {
                    for (Player player: playerList)
                    {
                        if (player.id == superDTO.m_playerStats[i].getID())
                        {
                            found = true;
                            player.name = superDTO.m_playerStats[i].getName();
                            player.clan = superDTO.m_playerStats[i].getClan();
                            player.raceResults += ", " + giveAsString(superDTO.m_playerStats[i].getRank());
                            player.lapResults += ", " + giveAsString(superDTO.m_playerStats[i].getLapRank());
                            player.bestLaps += countBestLaps(superDTO.m_playerStats[i].getRank(),superDTO.m_playerStats[i].getLapRank());
                            player.playedRaces += countPlayedRaces(superDTO.m_playerStats[i].getRank());
                            if(!StaticSystemController.UseCustomPoints) player.teamPointList.addAll(superDTO.m_playerStats[i].getTeamPoints(superDTO));
                            else player.teamPointList.addAll(superDTO.m_playerStats[i].getPoints(superDTO));
                            for(int io = 0; io < index; io++)
                            {
                                if(player.lapPosList.size() < index)
                                {
                                    player.lapPosList.add(0);
                                }
                                if(player.racePosList.size() < index)
                                {
                                    player.racePosList.add(0);
                                }
                                if(player.racePointList.size() < index)
                                {
                                    player.racePointList.add(0);
                                }
                            }
                            player.lapPosList.addAll(superDTO.m_playerStats[i].getLapRank());
                            player.racePosList.addAll(superDTO.m_playerStats[i].getRank());
                            player.racePointList.addAll(superDTO.m_playerStats[i].getPoints(superDTO));
                            player.averagePosition = getAsAvr(player.racePosList);
                            player.averageLapPosition = getAsAvr(player.lapPosList);

                            List<Integer> points = superDTO.m_playerStats[i].getPoints(superDTO);
                            List<Integer> teamPoints = superDTO.m_playerStats[i].getTeamPoints(superDTO);
                            List<Integer> racePositions = superDTO.m_playerStats[i].getRank();
                            List<Integer> lapPositions = superDTO.m_playerStats[i].getLapRank();

                            for(Integer p: points)
                            {
                                player.totalPoints+=p;
                                player.racePoints+=p + ", ";
                            }

                            for(Integer p: teamPoints)
                            {
                                player.totalTeamPoints+=p;
                            }


                            if(StaticSystemController.TeamsByClan) {updateTeam(superDTO.m_playerStats[i],superDTO,player);}

                        }
                    }
                }
                else
                {
                    Player player = updatePlayer(superDTO.m_playerStats[i],superDTO);
                    if(StaticSystemController.TeamsByClan) {updateTeam(superDTO.m_playerStats[i],superDTO,player);}
                    found = true;
                    player.eraseUn();
                }
                if(!found)
                {
                    Player player = updatePlayer(superDTO.m_playerStats[i],superDTO);
                    if(StaticSystemController.TeamsByClan) {updateTeam(superDTO.m_playerStats[i],superDTO,player);}
                    player.eraseUn();
                }
            }
            int count = 0;
            for(SuperDTO.Playerstats pl : superDTO.m_playerStats)
            {
                if(pl.getEventStatsLength() > count)
                {
                    count = pl.getEventStatsLength();
                }
            }
            index+=count;
        }

        smoothLists();
    }

    public static void smoothLists()
    {
        int size = 0;
        int sizeLap = 0;
        int sizePoints = 0;

        int overallCounter = 0;

        if(playerList != null)
        {
            for (Player pl : playerList)
            {
                for(int e = 0; e < pl.racePosList.size(); e++)
                {
                    if(pl.racePosList.get(e) == null)
                    {
                        pl.racePosList.set(e,0);
                    }
                }

                if (size <= pl.racePosList.size())
                {
                    size = pl.racePosList.size();
                }
                if (sizeLap <= pl.lapPosList.size())
                {
                    sizeLap = pl.lapPosList.size();
                }
                if (sizePoints <= pl.racePointList.size())
                {
                    sizePoints = pl.racePointList.size();
                }
            }
        }
        if(overallCounter < size) overallCounter = size;
        if(overallCounter < sizeLap) overallCounter = sizeLap;
        if(overallCounter < sizePoints) overallCounter = sizePoints;


        for(Player pl : playerList)
        {
            int counterLap = overallCounter - pl.lapPosList.size();
            int counterRaceRes = overallCounter - pl.racePosList.size();
            int counterRacePoi = overallCounter - pl.racePointList.size();

            for(int i = 0; i < counterLap; i++)
            {
                pl.lapPosList.add(0);
            }
            for(int i = 0; i < counterRaceRes; i++)
            {
                pl.racePosList.add(0);
            }
            for(int i = 0; i < counterRacePoi; i++)
            {
                pl.racePointList.add(0);
            }
            /**int counter = size - pl.racePosList.size();
            int counter2 = sizeLap - pl.lapPosList.size();
            int counter3 =  sizePoints - pl.racePointList.size();
            for(int i = 0; i < counter; i++)
            {
                pl.racePosList.add(0);
            }
            for(int i = 0; i < counter2; i++)
            {
                pl.lapPosList.add(0);
            }
            for(int i = 0; i < counter3; i++)
            {
                pl.racePointList.add(0);
            }**/
        }
    }
    public static void updateTeam(SuperDTO.Playerstats playerstats, SuperDTO superDTO, Player player)
    {
        boolean foundTeam = false;

        if(teamList != null)
        {
            for (Team team : teamList)
            {
                if (player.clan.equals(team.TAG))
                {
                    foundTeam = true;

                    List<Integer> racePositions = playerstats.getRank();
                    List<Integer> lapPositions = playerstats.getLapRank();

                    if(StaticSystemController.UseCustomPoints) team.allPoints.addAll(playerstats.getPoints(superDTO));
                    else team.allPoints.addAll(playerstats.getTeamPoints(superDTO));
                }
            }
            if(!foundTeam)
            {
                Team teami = new Team();
                teami.TAG = playerstats.getClan();
                if(StaticSystemController.UseCustomPoints) teami.allPoints.addAll(playerstats.getPoints(superDTO));
                else teami.allPoints.addAll(playerstats.getTeamPoints(superDTO));
                teamList.add(teami);
            }
        }
        else
        {
            Team teami = new Team();
            teami.TAG = playerstats.getClan();
            if(StaticSystemController.UseCustomPoints) teami.allPoints.addAll(playerstats.getPoints(superDTO));
            else teami.allPoints.addAll(playerstats.getTeamPoints(superDTO));
            teamList.add(teami);
        }
    }

    public static Player updatePlayer(SuperDTO.Playerstats playerstats, SuperDTO superDTO)
    {
        Player player = new Player();
        player.id = playerstats.getID();
        player.name = playerstats.getName();
        player.clan = playerstats.getClan();
        player.raceResults += giveAsString(playerstats.getRank());
        player.lapResults += giveAsString(playerstats.getLapRank());
        if(!StaticSystemController.UseCustomPoints) player.teamPointList.addAll(playerstats.getTeamPoints(superDTO));
        else player.teamPointList.addAll(playerstats.getPoints(superDTO));
        for(int io = 0; io < index; io++)
        {
            if(player.lapPosList.size() < index)
            {
                player.lapPosList.add(0);
            }
            if(player.racePosList.size() < index)
            {
                player.racePosList.add(0);
            }
            if(player.racePointList.size() < index)
            {
                player.racePointList.add(0);
            }
        }
        player.racePointList.addAll(playerstats.getPoints(superDTO));
        player.lapPosList.addAll(playerstats.getLapRank());
        player.racePosList.addAll(playerstats.getRank());
        player.bestLaps += countBestLaps(playerstats.getRank(),playerstats.getLapRank());
        player.playedRaces += countPlayedRaces(playerstats.getRank());
        player.averagePosition = getAsAvr(player.racePosList);
        player.averageLapPosition = getAsAvr(player.lapPosList);

        List<Integer> points = playerstats.getPoints(superDTO);
        List<Integer> teamPoints = playerstats.getTeamPoints(superDTO);
        List<Integer> racePositions = playerstats.getRank();
        List<Integer> lapPositions = playerstats.getLapRank();

        for(Integer p: points)
        {
            player.totalPoints+=p;
            player.racePoints+=p + ", ";
        }
        for(Integer p: teamPoints)
        {
            player.totalTeamPoints+=p;
        }

        player.eraseUn();

        playerList.add(player);
        return player;
    }

    public static String giveAsString(List<Integer> list)
    {
        String str = "";
        if(list != null)
        {
            for(int i = 0; i < list.size(); i++)
            {
                str += "" + list.get(i);

                if(i != list.size() - 1)
                {
                    str+=", ";
                }
            }
        }
        return str;
    }

    public static int giveAsIntegerValue(List<Integer> list)
    {
        int value = 0;

        if(list != null)
        {
            for(int i = 0; i < list.size(); i++)
            {
                value += list.get(i);
            }
        }
        return value;
    }

    public static int countBestLaps(List<Integer> list1,List<Integer> list2)
    {
        int rr = 0;

        if(list1 != null)
        {
            for (int i = 0; i < list1.size(); i++)
            {
                if(list1.get(i)==1)
                {
                    rr++;
                }
            }
        }
        if(list2 != null)
        {
            for (int i = 0; i < list2.size(); i++)
            {
                if(list2.get(i)==1)
                {
                    rr++;
                }
            }
        }
        return rr;
    }
    public static int countPoints(List<Integer> list1,List<Integer> list2)
    {
        int rr = 0;

        if(list1 != null)
        {
            for (int i = 0; i < list1.size(); i++)
            {
                if(list1.get(i)==1)
                {
                    rr++;
                }
            }
        }
        if(list2 != null)
        {
            for (int i = 0; i < list2.size(); i++)
            {
                if(list2.get(i)==1)
                {
                    rr++;
                }
            }
        }
        return rr;
    }


    public static int countPlayedRaces(List<Integer> list)
    {
        int rr = 0;
        if(list != null)
        {
            for (int i = 0; i < list.size(); i++)
            {
                if(list.get(i) > 0)
                {
                    rr++;
                }
            }
        }
        return rr;
    }

    public static float getAsAvr(List<Integer> list)
    {
        int sum = 0;

        List<Integer> reals = new ArrayList<>();
        if(list != null)
        {
            for (Integer inte : list)
            {
                if(inte != 0)reals.add(inte);
            }
        }
        if(reals != null)
        {
            for (Integer inte : reals)
            {
                sum+=inte;
            }
        }
        Float floati = 0f;
        if(reals.size() != 0)
        {
            Integer inte = (sum * 100) / reals.size();
            floati = Float.valueOf((float) inte / 100);
        }
        //BigDecimal big = BigDecimal.valueOf(temp / reals.size());

        //int num = 0;
        //if(reals.size() > 0) {num = (100 * sum / reals.size());}

        return floati;
    }
}
