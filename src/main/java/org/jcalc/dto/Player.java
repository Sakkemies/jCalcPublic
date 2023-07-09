package org.jcalc.dto;

import javafx.util.Pair;
import org.jcalc.handlers.StaticSystemController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Player
{
    public void eraseUn()
    {
    }

    public String givePointsAsString()
    {
        String points = racePoints;

        StringBuilder sb = new StringBuilder(points);
        try{sb.deleteCharAt(points.length()-2);sb.deleteCharAt(points.length()-1);}catch (StringIndexOutOfBoundsException e){}
        String ss = sb.toString();

        return ss;
    }

    public long id = 0l;
    public String name = "";
    public String clan = "";

    public List<Integer> racePosList = new ArrayList<>();
    public List<Pair<String, Integer>> racePositionsByEvent = new ArrayList<>();
    public List<Integer> lapPosList = new ArrayList<>();
    public List<Integer> racePointList = new ArrayList<>();
    public List<Integer> teamPointList = new ArrayList<>();
    public String lapResults = "";
    public String raceResults = "";
    public int bestLaps = 0;
    public int playedRaces = 0;
    public String racePoints = "";
    public String teamRacePoints = "";
    public String totalTime = "";
    public float averagePosition = 0f;
    public float averageLapPosition = 0f;
    public String timeDifference = "";
    public String racePerformance = "";
    public String lapPerformance = "";
    public int totalPoints = 0;
    public int totalTeamPoints = 0;
    public int position = 0;
    public int teamPosition = 0;

}

