package org.jcalc.scenebuilders.components;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.jcalc.dto.SuperDTO;
import org.jcalc.handlers.StaticStringHandler;
import org.jcalc.handlers.StaticSystemController;
import org.jcalc.scenebuilders.StaticSceneBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.jcalc.scenebuilders.StaticSceneBuilder.*;

public class iGridPane extends GridPane
{
    public iGridPane()
    {
        this.setBackground(InvisibleBackground);
        //this.setBorder(StaticSceneBuilder.BorderForPane);
        this.setPadding(new Insets(10,10,10,10));
        this.setHgap(3);
        this.setVgap(3);
    }

    public void setEventVBox(Integer width, Integer height)
    {
        List<iButton> listOfButtons = new ArrayList<>();
        this.setPrefHeight(200);
        this.setMaxWidth(500);
        this.setMinHeight(50);
        this.setPadding(new Insets(30,10,30,10));
        this.setBackground(StaticSceneBuilder.RealInvisibleBackground);
        this.setBorder(StaticSceneBuilder.BorderForEventButtons2);

        for(SuperDTO superd: StaticSystemController.SuperDTOlist)
        {
            iButton button = new iButton().getEventButton(superd.getProgId(),superd.getSuperDTOname());
            button.setMinSize(10,10);
            listOfButtons.add(button);
        }

        int i = 0;
        int index = 0;

        for(iButton button: listOfButtons)
        {
            if(i >= StaticSystemController.ScrollEventButtonIndex && i < StaticSystemController.ScrollEventButtonIndex+StaticSystemController.MaxScrollEventButton) {

                this.add(button, 0, index);

                iButton deletebutton = new iButton();
                deletebutton.getDeleteEventByIndexButton("-", button.getEventId());
                //deletebutton.setMinSize(27,25);
                //deletebutton.setPrefSize(27,25);
                this.add(deletebutton,1,index);
                index++;
            }
            i++;
        }
        Button addButton = new iButton().getAddNewEvent("+");
        addButton.setMinSize(27,26);
        addButton.setPrefSize(27,26);
        this.add(addButton,1,index);
    }

    public void setEventItems()
    {
        this.setTagFields();
        this.setEventFields();
    }

    public void setEventFields()
    {
        SuperDTO sDTO = new SuperDTO();boolean found = false;
        if(StaticSystemController.selectedId != null)
        {
            for (SuperDTO superDTO : StaticSystemController.SuperDTOlist)
            {
                if (superDTO.getProgId() == StaticSystemController.selectedId)
                {
                    sDTO = superDTO;
                    found = true;
                }
            }
        }

        if(found)
        {
            try
            {
                int magicNumber = 2;

                for (int i = magicNumber; i < (sDTO.findPlayers().size() + magicNumber); i++)
                {
                    iTextField itf = new iTextField(sDTO.findPlayers().get(i - magicNumber).getName(), sDTO.getProgId(), i - magicNumber, "PLAYER_NAME", sDTO.findPlayers().get(i - magicNumber).getID());
                    this.add(itf, 0, i);

                    iTextField itf2 = new iTextField(Long.toString(sDTO.findPlayers().get(i - magicNumber).getID()), sDTO.getProgId(), i - magicNumber, "PLAYER_ID", sDTO.findPlayers().get(i - magicNumber).getID());
                    this.add(itf2, 1, i);
                    if (StaticSystemController.EnablePlayerIDEditing)
                    {
                        itf2.setEditable(true);
                    }
                    else
                    {
                        itf2.setEditable(false);
                        itf2.setBackground(TextTagBackground);
                    }

                    iTextField itf3 = new iTextField(sDTO.findPlayers().get(i - magicNumber).getClan(), sDTO.getProgId(), i - magicNumber, "CLAN", sDTO.findPlayers().get(i - magicNumber).getID());
                    this.add(itf3, 2, i);itf3.setMinHeight(1);

                    iTextField itf4 = new iTextField(StaticStringHandler.ParseListStringToString(sDTO.findPlayers().get(i - magicNumber).getLapRank().toString()), sDTO.getProgId(), i - magicNumber, "LAP_RANK", sDTO.findPlayers().get(i - magicNumber).getID());
                    this.add(itf4, 3, i);itf4.setMinHeight(1);

                    iTextField itf5 = new iTextField(StaticStringHandler.ParseListStringToString(sDTO.findPlayers().get(i - magicNumber).getRank().toString()), sDTO.getProgId(), i - magicNumber, "RACE_RANK", sDTO.findPlayers().get(i - magicNumber).getID());
                    this.add(itf5, 4, i);itf5.setMinHeight(1);

                    //iTextField itf6 = new iTextField(StaticStringHandler.ParseListStringToString(sDTO.findPlayers().get(i - 3).getLapRank().toString()), sDTO.getProgId(), i - 3, "LAP_RANK", sDTO.findPlayers().get(i - 3).getID());
                    iTextField itf6 = new iTextField("N/A");
                    this.add(itf6, 5, i);itf6.setMinHeight(1);

                    //iTextField itf7 = new iTextField(StaticStringHandler.ParseListStringToString(sDTO.findPlayers().get(i - 3).getRank().toString()), sDTO.getProgId(), i - 3, "BEST_LAP", sDTO.findPlayers().get(i - 3).getID());
                    iTextField itf7 = new iTextField("N/A");
                    this.add(itf7, 6, i);itf7.setMinHeight(1);

                    iTextField itf8 = new iTextField(StaticStringHandler.ParseListStringToString(sDTO.findPlayers().get(i - magicNumber).getPoints().toString()), sDTO.getProgId(), i - magicNumber, "POINTS", sDTO.findPlayers().get(i - magicNumber).getID());
                    this.add(itf8, 7, i);itf8.setMinHeight(1);

                    iTextField itf9 = new iTextField(StaticStringHandler.ParseListStringToString(sDTO.findPlayers().get(i - magicNumber).getTeamPoints().toString()), sDTO.getProgId(), i - magicNumber, "TEAM_POINTS", sDTO.findPlayers().get(i - magicNumber).getID());
                    this.add(itf9, 8, i);itf9.setMinHeight(1);

                    iButton temp = new iButton();
                    temp.getRemovePlayerButton(sDTO.getProgId(), sDTO.findPlayers().get(i - magicNumber).getID());
                    this.add(temp, 9, i);temp.setMinHeight(1);

                    StaticSystemController.TextFieldListInGridPane.add(itf);
                    StaticSystemController.TextFieldListInGridPane.add(itf2);
                    StaticSystemController.TextFieldListInGridPane.add(itf3);
                    StaticSystemController.TextFieldListInGridPane.add(itf4);
                    StaticSystemController.TextFieldListInGridPane.add(itf5);

                    //StaticSystemController.TextFieldListInGridPane.add(itf6);
                    //StaticSystemController.TextFieldListInGridPane.add(itf7);

                    StaticSystemController.TextFieldListInGridPane.add(itf8);
                    StaticSystemController.TextFieldListInGridPane.add(itf9);

                    if (i == sDTO.findPlayers().size() + magicNumber-1) {
                        iButton temp2 = new iButton();
                        temp2.getAddNewPlayer("+");
                        temp2.setMinHeight(28);
                        this.add(temp2, 9, i + 1);
                    }

                }
            }
            catch (NullPointerException e)
            {

            }
        }
    }

    public void setTagFields()
    {
        //SuperDTO sDTO = new SuperDTO();

        boolean found = false;
        if(StaticSystemController.selectedId != null)
        {
            for (SuperDTO superDTO : StaticSystemController.SuperDTOlist)
            {
                if (superDTO.getProgId() == StaticSystemController.selectedId)
                {
                    //sDTO = superDTO;
                    found = true;
                    //System.out.println(sDTO);
                }
            }
        }

        if(found) {

            iTextField playerField = new iTextField("Player");
            this.add(playerField, 0, 1);

            iTextField playerIDField = new iTextField("Player ID");
            this.add(playerIDField, 1, 1);

            iTextField clanField = new iTextField("Clan");
            this.add(clanField, 2, 1);

            iTextField lapResultField = new iTextField("Lap results");
            this.add(lapResultField, 3, 1);

            iTextField raceResultsField = new iTextField("Race results");
            this.add(raceResultsField, 4, 1);

            iTextField bestLapField = new iTextField("Best laps");
            this.add(bestLapField, 5, 1);

            iTextField totalTimeField = new iTextField("Total times");
            this.add(totalTimeField, 6, 1);

            iTextField racePointField = new iTextField("Race points");
            this.add(racePointField, 7, 1);

            iTextField teamPointField = new iTextField("Team points");
            this.add(teamPointField, 8, 1);
        }
    }

}
