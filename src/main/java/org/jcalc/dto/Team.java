package org.jcalc.dto;

import java.util.ArrayList;
import java.util.List;

public class Team
{
    public List<Integer> positions = new ArrayList<>();
    public List<Integer> allPoints = new ArrayList<>();
    public String TAG = null;
    public Team()
    {
        positions.add(0);
        allPoints.add(0);
    }

    public int givePoints()
    {
        int points = 0;

        if(allPoints != null && TAG != null)
        {
            if(!TAG.equals(""))
            {
                for (Integer pos : allPoints)
                {
                    points += pos;
                }
            }
        }

        return points;
    }

    public String givePointsAsString()
    {
        String points = "";

        if(allPoints != null && TAG != null)
        {
            if(!TAG.equals(""))
            {
                for (Integer pos : allPoints)
                {
                    if(pos != 0)
                    {
                        points += pos + ", ";
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder(points);
        try{sb.deleteCharAt(points.length()-2);sb.deleteCharAt(points.length()-1);}catch (StringIndexOutOfBoundsException e){}
        String ss = sb.toString();

        return ss;
    }
}

