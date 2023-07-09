package org.jcalc.dto;

import javafx.beans.property.SimpleStringProperty;
import org.jcalc.handlers.StaticSystemController;

import java.util.List;

public class PlayerDTO
{
    private long id;
    public SimpleStringProperty idString;
    private String name;
    public SimpleStringProperty nameString;
    private String clan;
    public SimpleStringProperty clanString;
    private List<Integer> rank;
    public SimpleStringProperty rankString;
    private List<Integer> points;
    public SimpleStringProperty pointString;
    private List<Integer> lapRank;
    public SimpleStringProperty lapString;
    private List<Integer> teamPoints;
    public SimpleStringProperty teamPointsString;
    public boolean secondScoresOn = false;


    public PlayerDTO(long id, String name, List<Integer> points, List<Integer> rank,String clan, List<Integer> laprank, List<Integer> teamPoints, boolean secondScores)
    {
        this.id = id;
        this.name = name;
        this.points = points;
        this.clan = clan;
        this.rank = rank;
        this.lapRank = laprank;
        this.teamPoints = teamPoints;
        this.secondScoresOn = secondScores;

        this.idString = new SimpleStringProperty(Double.toString(this.id));
        this.nameString = new SimpleStringProperty(this.name);
        this.clanString = new SimpleStringProperty(this.clan);
        this.rankString = new SimpleStringProperty(this.rank.toString());
        this.pointString = new SimpleStringProperty(this.points.toString());
        this.lapString = new SimpleStringProperty(this.lapRank.toString());
        this.teamPointsString = new SimpleStringProperty(this.teamPoints.toString());
    }
    public int getTotalPoints()
    {
        int p = 0;
        for(Integer i: points)
        {
            p+=i;
        }
        return  p;
    }

    public int getTotalTeamPoints()
    {
        int p = 0;
        for(Integer i: teamPoints)
        {
            p+=i;
        }
        return  p;
    }

    public int getCustomTotalPoints()
    {
        int p = 0;
        for(Integer integer: rank)
        {
            if(integer > 0 && integer <= 20)p+= StaticSystemController.scoreArray[integer];
        }
        for(Integer integer: lapRank)
        {
            if(integer == 1)
            {
                p+=StaticSystemController.scoreArray[31];
            }
        }
        return p;
    }

    /**This causes exception: Try to do it with for(int i = 0) loop**/
    public void update(List<Integer> more, List<Integer> ranks, List<Integer> lapranks, List<Integer> teamPointsv)
    {
        for(Integer inte: teamPointsv)
        {
            teamPoints.add(inte);
        }

        for(Integer inte: more)
        {
            points.add(inte);
        }

        for(Integer r: ranks)
        {
            rank.add(r);
        }

        for(Integer r: lapranks)
        {
            lapRank.add(r);
        }

        this.idString = new SimpleStringProperty(Double.toString(this.id));
        this.nameString = new SimpleStringProperty(this.name);
        this.clanString = new SimpleStringProperty(this.clan);
        this.rankString = new SimpleStringProperty(this.rank.toString());
        this.pointString = new SimpleStringProperty(this.points.toString());
        this.lapString = new SimpleStringProperty(this.lapRank.toString());
    }

    public List<Integer> getRank()
    {
        return rank;
    }

    public List<Integer> getLapRank()
    {
        return lapRank;
    }

    public List<Integer> giveListOfPoints()
    {
        return points;
    }
    public void updateTags(String name, String clan)
    {
        this.name = name;
        this.clan = clan;
    }

    public long getID()
    {
        return this.id;
    }


    @Override
    public String toString()
    {
        return this.id + " / " + this.name + " (" + this.clan + ") / " + this.points;
    }

    public String getName()
    {
        return this.name;
    }

    public String getClan()
    {
        return this.clan;
    }

    public List<Integer> getPoints()
    {
        return this.points;
    }
    public List<Integer> getTeamPoints()
    {
        return this.teamPoints;
    }
}

