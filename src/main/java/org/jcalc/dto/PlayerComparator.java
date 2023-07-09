package org.jcalc.dto;

import org.jcalc.handlers.StaticSystemController;
import org.jcalc.handlers.StaticYGridPaneController;

import java.util.Comparator;

public class PlayerComparator implements Comparator<Player>
{
    @Override
    public int compare(Player o1, Player o2)
    {
        if(StaticSystemController.compareTo == StaticSystemController.compareToPoints)
        {
            if (o1 == null && o2 == null)
                return 0;
            if (o1 == null)
                return 1;
            if (o2 == null)
                return -1;

            int value = o2.totalPoints - o1.totalPoints;
            if (value != 0)
                return value;

            value = o1.name.compareTo(o2.name);
            return value;
        }
        else if(StaticSystemController.compareTo == StaticSystemController.compareToTeamPoints)
        {
            if (o1 == null && o2 == null)
                return 0;
            if (o1 == null)
                return 1;
            if (o2 == null)
                return -1;

            int value = o2.totalTeamPoints - o1.totalTeamPoints;
            if (value != 0)
                return value;

            value = o1.name.compareTo(o2.name);
            return value;
        }
        else if(StaticSystemController.compareTo == StaticSystemController.compareToPosition)
        {
            if (o1 == null && o2 == null)
                return 0;
            if (o1 == null || o1.position == 0f)
                return 1;
            if (o2 == null || o2.position == 0f)
                return -1;

            float value = o2.position - o1.position;
            if(value < 0)
            {
                return 1;
            }
            if(value > 0)
            {
                return -1;
            }

            int valueString = o1.name.compareTo(o2.name);
            return valueString;
        }
        else if(StaticSystemController.compareTo == StaticSystemController.compareToTeamPosition)
        {
            if (o1 == null && o2 == null)
                return 0;
            if (o1 == null || o1.teamPosition == 0f)
                return 1;
            if (o2 == null || o2.teamPosition == 0f)
                return -1;

            float value = o2.teamPosition - o1.teamPosition;
            if(value < 0)
            {
                return 1;
            }
            if(value > 0)
            {
                return -1;
            }

            int valueString = o1.name.compareTo(o2.name);
            return valueString;
        }
        else if(StaticSystemController.compareTo == StaticSystemController.compareToAvgPosition)
        {
            if (o1 == null && o2 == null)
                return 0;
            if (o1 == null || o1.averagePosition == 0f)
                return 1;
            if (o2 == null || o2.averagePosition == 0f)
                return -1;

            float value = o2.averagePosition - o1.averagePosition;
            if(value < 0)
            {
                return 1;
            }
            if(value > 0)
            {
                return -1;
            }

            int valueString = o1.name.compareTo(o2.name);
            return valueString;
        }
        else if(StaticSystemController.compareTo == StaticSystemController.compareToAvgLapPosition)
        {
            if (o1 == null && o2 == null)
                return 0;
            if (o1 == null || o1.averageLapPosition == 0f)
                return 1;
            if (o2 == null || o2.averageLapPosition == 0f)
                return -1;

            float value = o2.averageLapPosition - o1.averageLapPosition;
            if(value < 0)
            {
                return 1;
            }
            if(value > 0)
            {
                return -1;
            }

            int valueString = o1.name.compareTo(o2.name);
            return valueString;
        }
        else if(StaticSystemController.compareTo == StaticSystemController.compareToBestLaps)
        {
            if (o1 == null && o2 == null)
                return 0;
            if (o1 == null)
                return 1;
            if (o2 == null)
                return -1;

            int value = o2.bestLaps - o1.bestLaps;
            if (value != 0)
                return value;

            value = o1.name.compareTo(o2.name);
            return value;
        }
        else if(StaticSystemController.compareTo == StaticSystemController.compareToPlayedRaces)
        {
            if (o1 == null && o2 == null)
                return 0;
            if (o1 == null)
                return 1;
            if (o2 == null)
                return -1;

            int value = o2.playedRaces - o1.playedRaces;
            if (value != 0)
                return value;

            value = o1.name.compareTo(o2.name);
            return value;
        }
        else if(StaticSystemController.compareTo == StaticSystemController.compareToID)
        {
            if (o1 == null && o2 == null)
                return 0;
            if (o1 == null)
                return 1;
            if (o2 == null)
                return -1;

            long value = o2.id - o1.id;
            if (value != 0)
                return Long.valueOf(value).intValue();//value;

            value = o1.name.compareTo(o2.name);
            return Long.valueOf(value).intValue();//value;
        }
        else if(StaticSystemController.compareTo == StaticSystemController.compareToName)
        {
            return o1.name.compareTo(o2.name);
        }
        else if(StaticSystemController.compareTo == StaticSystemController.compareToClan)
        {
            if(o1.clan.equals(""))
            {
                return 1;
            }
            if(o2.clan.equals(""))
            {
                return -1;
            }
            return o1.clan.compareTo(o2.clan);
        }
        else
        {
            if (o1 == null && o2 == null)
                return 0;
            if (o1 == null)
                return 1;
            if (o2 == null)
                return -1;

            int value = o2.totalPoints - o1.totalPoints;
            if (value != 0)
                return value;

            value = o1.name.compareTo(o2.name);
            return value;
        }
    }
}
