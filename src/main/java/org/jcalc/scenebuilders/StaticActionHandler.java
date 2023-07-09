package org.jcalc.scenebuilders;


//import com.google.gson.Gson.internal.LinkedTreeMap;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jcalc.dto.SuperDTO;
import org.jcalc.handlers.StaticSystemController;
import org.jcalc.scenebuilders.components.iDialog;

import java.lang.reflect.Type;
import java.util.*;

//import static org.jcalc.handlers.StaticSystemController.*;

public class StaticActionHandler
{
    private static int freeId = 0;
    public static void MergeEvents()
    {
        SuperDTO mergeSuperDTO = null;
        SuperDTO selectedSuperDTO = null;

        if(StaticSystemController.selectedId == StaticSystemController.selectedMergeID)
        {
            iDialog dialog = new iDialog("Merge events", "Both events are same.", "ERROR");
            dialog.showAndWait();
        }
        else
        {
            for (SuperDTO superdto : StaticSystemController.SuperDTOlist)
            {
                if (superdto.getProgId() == StaticSystemController.selectedMergeID)
                {
                    mergeSuperDTO = superdto;
                }
                if (superdto.getProgId() == StaticSystemController.selectedId)
                {
                    selectedSuperDTO = superdto;
                }
            }
        }

        if(selectedSuperDTO != null && mergeSuperDTO != null)
        {
            for (SuperDTO.Playerstats stats : selectedSuperDTO.m_playerStats)
            {
                mergeSuperDTO.addEvent(stats);
            }
        }

        StaticSystemController.selectedId = mergeSuperDTO.getProgId();
        if(StaticSystemController.autoCorrectEventSize)StaticSystemController.autoCorrect();
        StaticSystemController.SuperDTOlist.remove(StaticSystemController.SuperDTOlist.indexOf(selectedSuperDTO));
    }

    public static void MergeEvents(SuperDTO mergeSuperDTO, SuperDTO selectedSuperDTO)
    {
        if(selectedSuperDTO != null && mergeSuperDTO != null)
        {
            for (SuperDTO.Playerstats stats : selectedSuperDTO.m_playerStats)
            {
                mergeSuperDTO.addEvent(stats);
            }
        }

        StaticSystemController.selectedId = mergeSuperDTO.getProgId();
        if(StaticSystemController.autoCorrectEventSize)StaticSystemController.autoCorrect();
        StaticSystemController.SuperDTOlist.remove(StaticSystemController.SuperDTOlist.indexOf(selectedSuperDTO));
    }

    public static void MergeAllEvents()
    {
        int size = StaticSystemController.SuperDTOlist.size();
        for(int i = 0; i < size; i++)
        {
            if(StaticSystemController.SuperDTOlist.size() > 1)
            {
                MergeEvents(StaticSystemController.SuperDTOlist.get(0), StaticSystemController.SuperDTOlist.get(1));
                StaticSystemController.updateViews(false,true);
            }
        }
    }

    public static void SplitAllEvents()
    {
        List<SuperDTO> superDTOS = new ArrayList<>();

        freeId = getFreeProgId();

        for(SuperDTO sup : StaticSystemController.SuperDTOlist)
        {
            superDTOS.addAll(SplitEvent(sup,false));
        }

        StaticSystemController.SuperDTOlist = superDTOS;
    }
    public static void CopyEvent()
    {
        /**/
        try
        {
            SceneController.switchToLoadingScene();

            int freeId = getFreeProgId();
            int oldId = getFreeProgId()+1;
            int count = 0;
            int cc = 0;

            String mapString = "";
            SuperDTO dtoC = new SuperDTO();

            if (StaticSystemController.selectedId != null) {
                for (SuperDTO dto : StaticSystemController.SuperDTOlist)
                {
                    if (dto.getProgId() == StaticSystemController.selectedId)
                    {
                        if(!dto.getSuperDTOname().contains("") && !dto.getSuperDTOname().contains(""))
                        {
                            dto.setSuperDTOname(dto.getSuperDTOname() + "");
                        }
                        else
                        {
                            dto.setSuperDTOname(dto.getSuperDTOname());
                            dto.setSuperDTOname(dto.getSuperDTOname().replace("", ""));
                        }
                        mapString = new Gson().toJson(dto);

                        cc = 0;
                        for(int i = 0; i < dto.m_playerStats.length; i++)
                        {
                            if (cc < dto.m_playerStats[i].getEventStatsLength())
                            {
                                cc = dto.m_playerStats[i].getEventStatsLength();
                                count = (int) cc/2;
                            }
                        }
                        dtoC = dto;
                    }
                }
            }

            if (mapString != null || mapString != "")
            {
                oldId = dtoC.getProgId();
                StaticSystemController.SuperDTOlist.remove(StaticSystemController.SuperDTOlist.indexOf(dtoC));
                StaticSystemController.updateViews(false,true);

                //StaticSystemController.SuperDTOlist.remove(StaticSystemController.SuperDTOlist.indexOf(dtoC));
                Type type = new TypeToken<SuperDTO>() {}.getType();
                SuperDTO superDTO = new Gson().fromJson(mapString, type);
                superDTO.setProgId(oldId);
                //superDTO.setSuperDTOname(superDTO.getSuperDTOname().replace("1", "2"));

                SuperDTO superDTO2 = new Gson().fromJson(mapString, type);
                superDTO2.setProgId(freeId);
                superDTO2.setSuperDTOname(superDTO2.getSuperDTOname().replace("", ""));

                StaticSystemController.SuperDTOlist.add(superDTO2);StaticSystemController.SuperDTOlist.add(superDTO);
            }
        }
        catch (NullPointerException e){System.out.println(e);}
        SceneController.SwitchScene(StaticSystemController.SceneControl, true);
        StaticSystemController.updateViews(false, true);/**/
    }
    public static void SplitEvent()
    {
        /**/
        try
        {
            SceneController.switchToLoadingScene();

            int freeId = getFreeProgId();
            int oldId = getFreeProgId()+1;
            int count = 0;
            int cc = 0;

            String mapString = "";
            SuperDTO dtoC = new SuperDTO();

            if (StaticSystemController.selectedId != null) {
                for (SuperDTO dto : StaticSystemController.SuperDTOlist)
                {
                    if (dto.getProgId() == StaticSystemController.selectedId)
                    {
                        if(!dto.getSuperDTOname().contains("") && !dto.getSuperDTOname().contains(""))
                        {
                            dto.setSuperDTOname(dto.getSuperDTOname() + "");
                        }
                        else
                        {
                            dto.setSuperDTOname(dto.getSuperDTOname());
                            dto.setSuperDTOname(dto.getSuperDTOname().replace("", ""));
                        }
                        mapString = new Gson().toJson(dto);

                        cc = 0;
                        for(int i = 0; i < dto.m_playerStats.length; i++)
                        {
                            if (cc < dto.m_playerStats[i].getEventStatsLength())
                            {
                                cc = dto.m_playerStats[i].getEventStatsLength();
                                count = (int) cc/2;
                            }
                        }
                        dtoC = dto;
                    }
                }
            }

            if(cc > 1) {
                if (mapString != null || mapString != "") {
                    oldId = dtoC.getProgId();
                    StaticSystemController.SuperDTOlist.remove(StaticSystemController.SuperDTOlist.indexOf(dtoC));
                    StaticSystemController.updateViews(false, true);

                    //StaticSystemController.SuperDTOlist.remove(StaticSystemController.SuperDTOlist.indexOf(dtoC));
                    Type type = new TypeToken<SuperDTO>() {
                    }.getType();
                    SuperDTO superDTO = new Gson().fromJson(mapString, type);
                    superDTO.setProgId(oldId);
                    //superDTO.setSuperDTOname(superDTO.getSuperDTOname().replace("1", "2"));

                    SuperDTO superDTO2 = new Gson().fromJson(mapString, type);
                    superDTO2.setProgId(freeId);
                    superDTO2.setSuperDTOname(superDTO2.getSuperDTOname().replace("", ""));

                    StaticSystemController.SuperDTOlist.add(superDTO2);
                    StaticSystemController.SuperDTOlist.add(superDTO);


                    for (int i = cc - 1; i >= count; i--) {
                        superDTO2.deleteAllEventsFromSpecificIndex(i);
                    }

                    for (int i = count - 1; i >= 0; i--) {
                        superDTO.deleteAllEventsFromSpecificIndex(i);
                    }
                    //StaticSystemController.SuperDTOlist.remove(StaticSystemController.SuperDTOlist.indexOf(dtoC));
                }
            }
        }
        catch (NullPointerException e){System.out.println(e);}
        SceneController.SwitchScene(StaticSystemController.SceneControl, true);
        StaticSystemController.updateViews(false, true);/**/
    }

    public static List<SuperDTO> SplitEvent(SuperDTO dto, boolean update)
    {
        /**/
        List<SuperDTO> superDTOS = new ArrayList<>();

        try
        {
            SceneController.switchToLoadingScene();

            //int freeId = getFreeProgId();
            int count = 0;
            int cc = 0;

            String mapString = "";

            mapString = new Gson().toJson(dto);

            cc = 0;
            for(int i = 0; i < dto.m_playerStats.length; i++)
            {
                if (cc < dto.m_playerStats[i].getEventStatsLength())
                {
                    cc = dto.m_playerStats[i].getEventStatsLength();
                    if(cc > count) count = cc;;
                }
            }

            if (mapString != null || mapString != "")
            {
                StaticSystemController.updateViews(false,true);

                Type type = new TypeToken<SuperDTO>() {}.getType();

                List<SuperDTO> supList = new ArrayList<>();
                for(int i = 0; i < count; i++)
                {
                    SuperDTO superDTO = new Gson().fromJson(mapString, type);
                    superDTO.setProgId(freeId);
                    freeId++;
                    supList.add(superDTO);
                }

                int index = count-1;
                for(SuperDTO sup: supList)
                {
                    for (int i = count - 1; i >= 0; i--)
                    {
                        if(i != index) sup.deleteAllEventsFromSpecificIndex(i);
                    }
                    index--;
                }

                for(int i = supList.size()-1; i >= 0; i--)
                {
                    superDTOS.add(supList.get(i));
                }
            }
        }
        catch (NullPointerException e){System.out.println(e);}
        if(update)
        {
            SceneController.SwitchScene(StaticSystemController.SceneControl, true);
            StaticSystemController.updateViews(false, true);
        }
        return superDTOS;
    }

    public static void SplitAll(SuperDTO dto, boolean update)
    {
        /**/
        try
        {
            SceneController.switchToLoadingScene();

            int freeId = getFreeProgId();
            int count = 0;
            int cc = 0;

            String mapString = "";

            mapString = new Gson().toJson(dto);

            cc = 0;
            for(int i = 0; i < dto.m_playerStats.length; i++)
            {
                if (cc < dto.m_playerStats[i].getEventStatsLength())
                {
                    cc = dto.m_playerStats[i].getEventStatsLength();
                    if(cc > count) count = cc;;
                }
            }

            if (mapString != null || mapString != "")
            {
                StaticSystemController.updateViews(false,true);

                Type type = new TypeToken<SuperDTO>() {}.getType();

                List<SuperDTO> supList = new ArrayList<>();
                for(int i = 0; i < count; i++)
                {
                    SuperDTO superDTO = new Gson().fromJson(mapString, type);
                    superDTO.setProgId(freeId);
                    freeId++;
                    supList.add(superDTO);
                }

                int index = count-1;
                for(SuperDTO sup: supList)
                {
                    for (int i = count - 1; i >= 0; i--)
                    {
                        if(i != index) sup.deleteAllEventsFromSpecificIndex(i);
                    }
                    index--;
                }

                for(int i = supList.size()-1; i >= 0; i--)
                {
                    StaticSystemController.SuperDTOlist.add(supList.get(i));
                }
            }
        }
        catch (NullPointerException e){System.out.println(e);}
        if(update)
        {
            SceneController.SwitchScene(StaticSystemController.SceneControl, true);
            StaticSystemController.updateViews(false, true);
        }
    }

    static int getFreeProgId()
    {
        int i = 0;

        for(SuperDTO superDto: StaticSystemController.SuperDTOlist)
        {
            if(i <= superDto.getProgId())
            {
                i = superDto.getProgId() + 1;
            }
        }

        return i;
         /**/
    }
}
