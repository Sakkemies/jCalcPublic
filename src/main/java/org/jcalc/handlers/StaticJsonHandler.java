package org.jcalc.handlers;


import org.jcalc.dto.PlayerDTO;
import org.jcalc.dto.SuperDTO;

import java.util.ArrayList;
import java.util.List;

public class StaticJsonHandler
{
    public static List<SuperDTO> getSuperDTOlist()
    {
        return StaticJsonHandler.getSuperDTOlist();
    }

    public static List<List<SuperDTO.Playerstats>> getSuperDTOplayerStats()
    {
        List<List<SuperDTO.Playerstats>> listOfPlayerStats = new ArrayList<>();

        for(SuperDTO superd: getSuperDTOlist())
        {
            listOfPlayerStats.add(superd.findPlayers());
        }

        return listOfPlayerStats;
    }

    public static void CreateEventList()
    {

    }
    public static void InitializeLists()
    {
        List<PlayerDTO> listOfPlayers = new ArrayList<>();
        List<SuperDTO> listOfSuperDto = new ArrayList<>();
        StaticSystemController.PlayerDTOlist = listOfPlayers;
        StaticSystemController.SuperDTOlist = listOfSuperDto;
    }

    public static boolean LoadRawJsonFile(String filename, boolean updateScene)
    {
        boolean value = StaticFileLoader.AddNewJSONtoSuperDTOlist(filename);

        updatePlayerList();

        //updateTable();
        if (updateScene)
        {
            StaticSystemController.updateViews(false,true);
        }

        return value;
    }

    public static boolean LoadOldProjectJsonFile(String filename, boolean updateScene)
    {
        boolean value = StaticFileLoader.LoadOldProjectJsonFile(filename);

        updatePlayerList();

        if (updateScene)
        {
            StaticSystemController.updateViews(false,true);
        }

        return value;
    }

    public static boolean LoadMergeSuperJsonFile(String filename, boolean updateScene)
    {

        boolean value =  StaticFileLoader.MergeOldProjectJsonFile(filename);

        updatePlayerList();

        if (updateScene)
        {
            StaticSystemController.updateViews(false,true);
        }

        return value;
    }


    public static List<PlayerDTO> getPlayerList()
    {
        List<List<SuperDTO.Playerstats>> listOfPlayers = new ArrayList<>();

        for(SuperDTO dto: StaticSystemController.SuperDTOlist)
        {
            listOfPlayers.add(dto.returnPlayers());
        }

        List<SuperDTO.Playerstats> pp = new ArrayList<>();

        List<PlayerDTO> listOfDTOS = new ArrayList<>();

        for(List<SuperDTO.Playerstats> l: listOfPlayers)
        {
            for(SuperDTO.Playerstats player: l)
            {
                boolean found = false;
                String name = player.getName();
                String clan = player.getClan();
                List<Integer> lapRank = player.getLapRank();
                List<Integer> rank = player.getRank();
                Long id = player.getID();
                List<Integer> points = player.getPoints();
                List<Integer> teamPoints = player.getTeamPoints();
                boolean secondScores = player.getSecondsScoresOn();

                for(PlayerDTO pl: listOfDTOS)
                {
                    if(pl.getID() == player.getID())
                    {
                        pl.update(points, rank, lapRank, teamPoints);
                        pl.updateTags(name, clan);
                        found = true;
                    }
                }

                if (!found)
                {
                    listOfDTOS.add(new PlayerDTO(id, name, points, rank, clan, lapRank, teamPoints, secondScores));
                }
            }
        }


        return listOfDTOS;
    }
    public static void updatePlayerList()
    {
        List<List<SuperDTO.Playerstats>> list = new ArrayList<>();
        for(SuperDTO dto: StaticSystemController.SuperDTOlist)
        {
            list.add(dto.returnPlayers());
            //System.out.println(dto.returnPlayers());
        }

        StaticSystemController.PlayerDTOlist = new ArrayList<>();

        for(List<SuperDTO.Playerstats> l: list)
        {
            for(SuperDTO.Playerstats player: l)
            {
                boolean found = false;
                String name = player.getName();
                String clan = player.getClan();
                List<Integer> lapRank = player.getLapRank();
                List<Integer> rank = player.getRank();
                Long id = player.getID();
                List<Integer> points = player.getPoints();
                List<Integer> teamPoints = player.getTeamPoints();
                boolean secondScores = player.getSecondsScoresOn();

                for(PlayerDTO pl: StaticSystemController.PlayerDTOlist)
                {
                    if(pl.getID() == player.getID())
                    {
                        pl.update(points, rank, lapRank, teamPoints);
                        pl.updateTags(name, clan);
                        found = true;
                    }
                }

                if (!found)
                {
                    StaticSystemController.PlayerDTOlist.add(new PlayerDTO(id, name, points, rank, clan, lapRank, teamPoints, secondScores));
                }
            }
        }
    }
}
