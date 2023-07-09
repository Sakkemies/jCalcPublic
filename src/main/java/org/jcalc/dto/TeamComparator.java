package org.jcalc.dto;

import org.jcalc.handlers.StaticSystemController;

import java.util.Comparator;

public class TeamComparator implements Comparator<Team>
{
    @Override
    public int compare(Team o1, Team o2)
    {
        if(StaticSystemController.compareTo == StaticSystemController.compareToTeamPoints)
        {
            if (o1 == null && o2 == null)
                return 0;
            if (o1 == null)
                return 1;
            if (o2 == null)
                return -1;

            int value = o2.givePoints() - o1.givePoints();
            if (value != 0)
                return value;

            String o1TAG = o1.TAG;
            String o2TAG = o2.TAG;

            if(o1.TAG == null)
            {
                o1TAG = "";
            }
            if(o2.TAG == null)
            {
                o2TAG = "";
            }
            value = o1TAG.compareTo(o2TAG);
            return value;
        }
        else return 0;
    }
}
