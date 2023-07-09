package org.jcalc.dto;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jcalc.handlers.StaticStringHandler;
import org.jcalc.handlers.StaticSystemController;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SuperDTO
{
    public int[] scoreArray = StaticSystemController.scoreArray;
    public boolean secondScoresOn = false;
    public int progid;
    public int m_formatId;
    public int m_formatVersion;
    public int m_totalEventCount;
    public int m_maxEventsToRemember;
    public int m_rememberedEventsCount;
    public int m_finishedEventsCount;
    public int m_maxPlayerCount;
    public Playerstats[] m_playerStats;
    public TeamStats[] m_teamStats;

    String eventName = "Event";
    public boolean getSecondScoresOn()
    {
        return secondScoresOn;
    }
    public void setSecondScoresOn(boolean scScoresOn)
    {
        this.secondScoresOn = scScoresOn;

        if(scScoresOn)
        {
            scoreArray = StaticSystemController.scoreArray2;
        }
        else
        {
            scoreArray = StaticSystemController.scoreArray;
        }

        for(Playerstats m: m_playerStats)
        {
            m.setSecondScoresOn(scScoresOn);
        }
    }

    public void setEventCounts()
    {
        int count = 0;
        for(int i = 0; i < m_playerStats.length; i++)
        {
            if(count <= m_playerStats[i].m_eventStats.length)
            {
                count = m_playerStats[i].m_eventStats.length;
            }
        }

        this.m_maxEventsToRemember = StaticSystemController.MaxEventsToRemember;
        this.m_maxPlayerCount = StaticSystemController.MaxPlayerCount;
        this.m_formatVersion = StaticSystemController.FormatVersion;
        this.m_formatId = StaticSystemController.FormatID;

        this.m_totalEventCount = count;
        this.m_rememberedEventsCount = count;
        this.m_finishedEventsCount = count;
    }

    public SuperDTO(List<String> listOfStatsPlayers, List<String> listOfStatsEvent, int ids, String eventString, String name, int divider)
    {
        this.progid = ids;
        this.eventName = name.replaceAll("1", "2");

        this.m_playerStats = new Playerstats[listOfStatsPlayers.size()];

        this.m_teamStats = new TeamStats[0]; //TODO

        Type eventType = new TypeToken<Playerstats[]>() {}.getType();
        m_playerStats = new Gson().fromJson(eventString, eventType);

        Type type = new TypeToken<Playerstats.Player>() {}.getType();
        Type type2 = new TypeToken<Playerstats.Eventstats[]>() {}.getType();

        for (int i = 0; i < m_playerStats.length; i++)
        {
            Playerstats.Player player = new Gson().fromJson(listOfStatsPlayers.get(i), type);
            System.out.println(listOfStatsPlayers.get(i));
            m_playerStats[i].setPlayer(player);
        }

        for (int i = 0; i < m_playerStats.length; i++)
        {
            System.out.println(listOfStatsEvent.get(i));
            Playerstats.Eventstats[] eventstats = new Gson().fromJson(listOfStatsEvent.get(i), type2);
            m_playerStats[i].m_eventStats = new Playerstats.Eventstats[eventstats.length];
            m_playerStats[i].m_eventStats = eventstats;
        }

        for(int i = 0; i < m_playerStats.length; i++)
        {
            for(int e = divider; e < m_playerStats[i].m_eventStats.length; e++)
            {
                m_playerStats[i].deleteItem(e);
            }
        }
        setEventCounts();
    }

    public void deleteAllEventsFromSpecificIndex(int index)
    {
        for(int i = 0; i < m_playerStats.length; i++)
        {
            m_playerStats[i].deleteItem(index);
        }
        setEventCounts();
    }
    public String getSuperDTOname()
    {
        return eventName;
    }

    public void setSuperDTOname(String name)
    {
        this.eventName = name;
    }

    public SuperDTO()
    {
        //System.out.println("Event loaded successfully");
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void addEvent(Playerstats playerstats)
    {
        boolean found = false;
        int index = 0;
        for(int i = 0; i < m_playerStats.length; i++)
        {
            if(m_playerStats[i] != null)
            {
                if (m_playerStats[i].getID() == playerstats.getID())
                {
                    found = true;
                    index = i;
                }
            }
        }

        if(!found)
        {
            Playerstats[] players = new Playerstats[m_playerStats.length+1];

            for(int e = 0; e < m_playerStats.length; e++)
            {
                players[e] = m_playerStats[e];
            }

            players[players.length-1] = playerstats;
            m_playerStats = players;
        }
        else
        {
            m_playerStats[index].addEvent(playerstats);
        }
    }

    public void setNullEvent()
    {
        this.m_formatId = 1234;
        this.m_totalEventCount = 1;
        this.m_formatVersion = 1234;
        this.m_maxPlayerCount = 1;
        this.m_maxEventsToRemember = 1;
        this.m_rememberedEventsCount = 1;
        this.m_finishedEventsCount = 1;
        this.m_playerStats = new Playerstats[1];

        long freeID = 99999999999999999l;

        if(m_playerStats != null)
        {
            for(int i = 0; i < m_playerStats.length; i++)
            {
                if(m_playerStats[i] != null)
                {
                    if (freeID >= m_playerStats[i].getID())
                    {
                        freeID = m_playerStats[i].getID() - 1;
                    }

                }
            }
        }
        StaticSystemController.freePlayerId = freeID-1;
        m_playerStats[0] = new Playerstats(freeID);
        setEventCounts();
    }

    public void addPlayer(Long ids)
    {
        Long temp = ids;
        for(Playerstats pp: m_playerStats)
        {
            if (pp.getID() < ids && pp.getID() > 90000000000000000l)
            {
                ids = pp.getID() - 1;
            }
        }
        StaticSystemController.freePlayerId = ids - 1;

        int index = m_playerStats.length;
        Playerstats[] on = new Playerstats[index+1];

        int indexx = 0;
        for (Playerstats i: m_playerStats)
        {
            on[indexx] = m_playerStats[indexx];
            indexx++;
        }

        on[index] = new Playerstats(ids);

        m_playerStats = on;

        int calc = 0;

        for(Playerstats i: m_playerStats)
        {
            if(i == null)
            {
                calc += 1;
                System.out.println("Null players inside the list (addPlayer)");
            }
        }
        setEventCounts();
    }

    public int getProgId()
    {
        return progid;
    }

    public void setProgId(int progid)
    {
        this.progid = progid;
    }
    public List<Playerstats> findPlayers()
    {
        List<Playerstats> playerStatList = new ArrayList<Playerstats>();

        for(Playerstats playerstat: m_playerStats)
        {
            playerStatList.add(playerstat);
        }
        return playerStatList;
    }

    public void setPlayerAttr(String attr,String newName,Long id)
    {
        for(Playerstats playerstat: m_playerStats)
        {
            if(attr.equals("PLAYER_NAME"))
            {
                playerstat.setName(id, newName);
            }
        }
    }
    public List<Playerstats.Player> getPlayerPlayer()
    {
        List<Playerstats.Player> list = new ArrayList<>();
        for (int i = 0; i < m_playerStats.length; i++)
        {
            list.add(m_playerStats[i].m_statsPlayer);
        }
        return list;
    }
    public List<Playerstats.Eventstats> getPlayerEvents()
    {
        List<Playerstats.Eventstats> list = new ArrayList<>();
        for (int i = 0; i < m_playerStats.length; i++)
        {
            for (int e = 0; e < m_playerStats[i].m_eventStats.length; e++)
            {
                list.add(m_playerStats[i].m_eventStats[e].getEvent());
            }
        }
        return list;
    }

    public int getID()
    {
        return this.m_formatId;
    }

    public List<SuperDTO.Playerstats> returnPlayers()
    {
        List<SuperDTO.Playerstats> list = new ArrayList<SuperDTO.Playerstats>();
        for(Playerstats player: m_playerStats)
        {
            list.add(player);
        }
        return list;
    }

    public void deleteEvent(int index, Long playerID)
    {
        for(int i = 0; i < m_playerStats.length; i++)
        {
            if(m_playerStats[i].getPlayerStats().getID() == playerID)
            {
                m_playerStats[i].deleteItem(index);
            }
        }
        setEventCounts();
    }

    public void deletePlayer(Long id)
    {
        try
        {
            Playerstats[] copy;

            if(m_playerStats.length > 1) {
                copy = new Playerstats[m_playerStats.length - 1];
            }
            else
            {
                copy = new Playerstats[1];
                copy[0] = new Playerstats(StaticSystemController.freePlayerId);
                StaticSystemController.freePlayerId -= 1;
            }

            int calc = 0;
            int i = 0;
            for (Playerstats player : m_playerStats)
            {
                if(player!= null)
                {
                    if (player.getID() != id)
                    {
                        copy[calc] = m_playerStats[i];
                        calc++;
                    }
                }
                i++;
            }

            m_playerStats = copy;

        }
        catch (ArrayIndexOutOfBoundsException e){System.out.println("Error: Index out of bounds!");}

        int calc = 0;
        for(Playerstats i: m_playerStats)
        {
            if(i == null)
            {
                calc += 1;
                System.out.println("Null players inside the list (deletePlayer)");
            }
        }
        setEventCounts();
    }

    //CLASS
    public class TeamStats
    {

    }
    public class Playerstats
    {
        boolean secondScoresOn = false;
        Player m_statsPlayer;
        int m_totalPoints;
        int m_rank;
        int m_previousRank;
        int m_lastPointsForgot;
        Eventstats[] m_eventStats;
        public void setSecondScoresOn(boolean m)
        {
            secondScoresOn = m;
        }
        public boolean getSecondsScoresOn()
        {
            return secondScoresOn;
        }

        public Playerstats getPlayerStats()
        {
            return this;
        }
        public int getEventStatsLength()
        {
            return m_eventStats.length;
        }
        public void setPlayer(Player player)
        {
            this.m_statsPlayer = player;
        }

        public Player getPlayer()
        {
            return m_statsPlayer;
        }

        public Playerstats(Long id)
        {
            m_statsPlayer = new Player(id);
            m_totalPoints = 0;
            m_rank = 0;
            m_previousRank = 0;
            m_lastPointsForgot = 0;
            m_eventStats = new Eventstats[1];
            m_eventStats[0] = new Eventstats(0,0,0,0,0);
        }

        public void addEvent(Playerstats playerstats)
        {
            m_statsPlayer.setName(playerstats.getName());
            m_statsPlayer.setClan(playerstats.getClan());

            Eventstats[] ev = new Eventstats[m_eventStats.length + playerstats.m_eventStats.length];
            for(int i = 0; i < m_eventStats.length; i++)
            {
                ev[i] = m_eventStats[i];
            }
            //int e = m_eventStats.length;

            //for(int i = 0; i < playerstats.m_eventStats.length; i++)
            //{
            //    ev[e] = playerstats.m_eventStats[i];
            //}
            for(int e = m_eventStats.length; e < ev.length; e++)
            {
                ev[e] = playerstats.m_eventStats[e-m_eventStats.length];
            }

            m_eventStats = ev;
        }

        public void setID(Long id)
        {
        }
        public String getName()
        {
            return m_statsPlayer.name;
        }

        public void deleteItem(int id)
        {
            if(id != 10000 && m_eventStats.length-1 >= id)
            {
                Eventstats[] arr = new Eventstats[m_eventStats.length - 1];

                int calc = 0;
                for (int i = 0; i < m_eventStats.length; i++) {
                    if (i != id) {
                        arr[calc] = m_eventStats[i];
                        calc += 1;
                    }
                }

                if(arr.length == 0)
                {
                    arr = new Eventstats[1];
                    arr[0] = new Eventstats(0,0,0,0,0);
                }
                m_eventStats = arr;
            }
        }
        public void setData(Long id,String nParsed,String TAG)
        {
            boolean forceStop = false;

            if(!forceStop) {
                String nParesd = nParsed.replace(",/delete", "");

                switch (TAG) {
                    case "LAP_RANK":
                        setLapRank(id, nParsed);
                        break;
                    case "RACE_RANK":
                        setRank(id, nParsed);
                        break;
                    case "POINTS":
                        setPoints(id, nParsed);
                        break;
                    case "TEAM_POINTS":
                        setTeamPoints(id, nParsed);
                        break;
                    default:
                        break;

                }
            }
        }
        public void setLapRank(Long id,String lapRank)
        {
            try {
                List<Integer> laps = StaticStringHandler.ParseStringToList(lapRank);
                Eventstats[] arr = new Eventstats[laps.size()];

                if (getID() == id && this.m_eventStats != null)
                {
                    for (int i = 0; i < arr.length; i++)
                    {
                        if(m_eventStats.length > i && m_eventStats[i]!=null)
                        {
                            m_eventStats[i].setLapRank(laps.get(i));
                        }
                        else
                        {
                            for (int e = 0; e < m_eventStats.length; e++)
                            {
                                arr[e] = m_eventStats[e];
                            }

                            arr[i] = new Eventstats(0,laps.get(i),0,0,0);
                            m_eventStats = arr;
                        }
                    }
                }
            }
            catch (NumberFormatException e)
            {

            }
        }
        public void setRank(Long id,String rank)
        {
            try {
                List<Integer> laps = StaticStringHandler.ParseStringToList(rank);
                if (getID() == id)
                {
                    for (int i = 0; i < laps.size(); i++)
                    {
                        if(m_eventStats.length > i && m_eventStats[i]!=null) {
                            m_eventStats[i].setRank(laps.get(i));
                        }
                        else
                        {
                            Eventstats[] arr = new Eventstats[laps.size()];
                            for (int e = 0; e < m_eventStats.length; e++)
                            {
                                arr[e] = m_eventStats[e];
                            }

                            arr[i] = new Eventstats(laps.get(i),0,0,0,0);
                            m_eventStats = arr;
                        }
                    }
                }
            }
            catch (NumberFormatException e)
            {

            }
        }
        public void setPoints(Long id,String points)
        {
            try {
                List<Integer> laps = StaticStringHandler.ParseStringToList(points);

                if (getID() == id)
                {
                    for (int i = 0; i < laps.size(); i++)
                    {
                        if(m_eventStats.length > i && m_eventStats[i]!=null) {
                            m_eventStats[i].setPoints(laps.get(i));
                        }
                        else
                        {
                            Eventstats[] arr = new Eventstats[laps.size()];
                            for (int e = 0; e < m_eventStats.length; e++)
                            {
                                arr[e] = m_eventStats[e];
                            }

                            arr[i] = new Eventstats(0,0,laps.get(i),0,0);
                            m_eventStats = arr;
                        }
                    }
                }
            }
            catch (NumberFormatException e)
            {

            }
        }

        public void setTeamPoints(Long id,String points)
        {
            try {
                List<Integer> laps = StaticStringHandler.ParseStringToList(points); //Get list of things

                if (getID() == id) //If id == id
                {
                    for (int i = 0; i < laps.size(); i++) //käy läpi jokainen muutos
                    {
                        if(m_eventStats.length > i && m_eventStats[i]!=null) //Jos eventstats.length on isompi kuin i(laps.length -1)
                        {
                            m_eventStats[i].setTeamPoints(laps.get(i));
                        }
                        else
                        {
                            Eventstats[] arr = new Eventstats[laps.size()];
                            for (int e = 0; e < m_eventStats.length; e++)
                            {
                                arr[e] = m_eventStats[e];
                            }

                            arr[i] = new Eventstats(0,0,0,0,laps.get(i));
                            m_eventStats = arr;
                        }
                    }
                }
            }
            catch (NumberFormatException e)
            {

            }
        }
        public void setName(Long id,String newName)
        {
            if(getID() == id)
            {
                m_statsPlayer.setName(newName);
            }
        }
        public void setPlayerID(Long oldID, Long ids, List<Long> list)
        {
            boolean found = false;
            if(getID() == oldID)
            {
                if(!list.contains(ids))
                {
                    m_statsPlayer.setID(ids);
                }
            }
        }


        public void setClan(Long id,String newName)
        {
            if(getID() == id)
            {
                m_statsPlayer.setClan(newName);
            }
        }
        public long getID()
        {
            return m_statsPlayer.id;
        }
        public List<Integer> getPoints()
        {
            List<Integer> list = new ArrayList<>();

            for (Eventstats ev : m_eventStats)
            {
                if(ev != null) {
                    list.add(ev.m_individualPoints);
                }
            }

            if(list==null)
            {
                list.add(0);
            }

            return list;
        }
        public List<Integer> getPoints(SuperDTO superDTO)
        {
            List<Integer> list = new ArrayList<>();

            if(!StaticSystemController.UseCustomPoints)
            {
                for (Eventstats ev : m_eventStats)
                {
                    if(ev != null) {
                    list.add(ev.m_individualPoints);}
                }
            }
            else
            {
                for(Eventstats ev: m_eventStats)
                {
                    Integer score = null;

                    if(ev.m_rank != 0 && ev.m_rank <= 20);
                    {
                        if(ev != null)
                        {
                            if (ev.m_rank <= 20)
                            {
                                if(score == null)
                                {
                                    score = 0;
                                }
                                score += superDTO.scoreArray[ev.m_rank];
                            }
                        }
                    }
                    if(ev.getLapRank() == 1)
                    {
                        if(ev != null)
                        {
                            if(score == null)
                            {
                                score = 0;
                            }
                            score += superDTO.scoreArray[31];
                        }
                    }
                    if(score != null) list.add(score);
                }
            }

            if(list==null)
            {
                //list.add(0);
                return null;
            }

            return list;
        }
        public List<Integer> getTeamPoints()
        {
            List<Integer> list = new ArrayList<>();

            for(Eventstats ev: m_eventStats)
            {
                if(ev != null) {
                list.add(ev.m_teamPoints);}
            }
            return list;
        }

        public List<Integer> getTeamPoints(SuperDTO superDTO)
        {
            List<Integer> list = new ArrayList<>();

            if(!StaticSystemController.UseCustomPoints)
            {
                for (Eventstats ev : m_eventStats)
                {
                    if(ev != null) {
                    list.add(ev.m_teamPoints);}
                }
            }
            else
            {
                for(Eventstats ev: m_eventStats)
                {
                    Integer score = null;
                    if(ev != null)
                    {
                        if(ev.m_rank != 0 && ev.m_rank <= 20);
                        {
                            if(score == null)
                            {
                                score = 0;
                            }
                            if (ev.m_rank <= 20)score += superDTO.scoreArray[ev.m_rank];
                        }
                        if(ev.getLapRank() == 1)
                        {
                            if(score == null)
                            {
                                score = 0;
                            }
                            score += superDTO.scoreArray[31];
                        }
                    }
                    if(score != null) list.add(score);
                }
            }

            if(list==null)
            {
                list.add(0);
            }

            return list;
        }
        public List<Integer> getRank()
        {
            List<Integer> list = new ArrayList<>();

            for(Eventstats ev: m_eventStats)
            {
                list.add(ev.m_rank);
            }
            return list;
        }

        public List<Integer> getLapRank()
        {
            List<Integer> list = new ArrayList<>();

            for(Eventstats ev: m_eventStats)
            {
                list.add(ev.m_lapRank);
            }
            return list;
        }

        public String getClan()
        {
            return m_statsPlayer.clan;
        }

        public String getMyStats()
        {
            String text = "";
            text += m_statsPlayer.getId() + ": " + m_statsPlayer.getName() + "(" + m_statsPlayer.getClan() + ")" + "\n";

            for(Eventstats player: m_eventStats)
            {
                text += player.m_individualPoints + " /Rank: " + player.getRank();
            }
            return text;
        }

        //CLASS
        class Eventstats
        {
            int m_rank;
            int m_lapRank;
            int m_individualPoints;
            int m_team;
            int m_teamPoints;

            public Eventstats(int rank, int lapRank, int individualPoints, int team, int teamPoints)
            {
                this.m_rank = rank;
                this.m_lapRank = lapRank;
                this.m_individualPoints = individualPoints;
                this.m_team = team;
                this.m_teamPoints = teamPoints;
            }

            public int getPoints()
            {
                return m_individualPoints;
            }
            public void setPoints(int n)
            {
                this.m_individualPoints = n;
            }
            public void setTeamPoints(int n)
            {
                this.m_teamPoints = n;
            }
            public int getTeamPoints()
            {
                return this.m_teamPoints;
            }

            public int getRank()
            {
                return m_rank;
            }
            public void setRank(int n)
            {
                this.m_rank = n;
            }

            public int getLapRank()
            {
                return m_lapRank;
            }
            public void setLapRank(int n)
            {
                this.m_lapRank = n;
            }

            public Eventstats getEvent()
            {
                return this;
            }
        }


        //CLASS
        class Player
        {
            String name;
            String clan;
            Long id;
            int index;
            int flag;
            Color color;

            public Player(Long ids)
            {
                name = "Player";
                clan = "";
                id = Long.valueOf(ids);
                index = 0;
                flag = 0;
                color = new Color();

            }
            public void setID(Long ids)
            {
                this.id = ids;
            }

            public Long getID()
            {
                return this.id;
            }

            public String getName()
            {
                return name;
            }
            public void setName(String name)
            {
                this.name = name;
            }

            public String getClan()
            {
                return clan;
            }
            public void setClan(String newClan)
            {
                this.clan = newClan;
            }

            public long getId()
            {
                return id;
            }

            public Player getPlayer()
            {
                return this;
            }

            //CLASS
            class Color
            {
                public Color()
                {
                    data1 = 0;
                    data2 = 0;
                    data3 = 0;
                }
                int data1;
                int data2;
                int data3;
            }

        }
    }

}

