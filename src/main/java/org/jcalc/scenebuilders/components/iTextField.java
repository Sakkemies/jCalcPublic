package org.jcalc.scenebuilders.components;

import javafx.scene.control.TextField;
import org.jcalc.dto.SuperDTO;
import org.jcalc.handlers.StaticStringHandler;
import org.jcalc.handlers.StaticSystemController;
import org.jcalc.scenebuilders.StaticSceneBuilder;


import java.util.ArrayList;
import java.util.List;

import static org.jcalc.scenebuilders.StaticSceneBuilder.*;

public class iTextField extends TextField
{
    public int myX = 0;
    public int myY = 0;
    private int id = 9999;
    private int listPos = 9999;
    private String tag = "";
    private String optionTAG = "";
    private long playerID;

    public iTextField(String name)
    {
        this.setText(name);
        this.setBackground(TextTagBackground);
        this.setOpacity(1);
        this.setFont(ButtonFont);
        this.setEditable(false);
        this.setPrefSize(500,30);
        this.setMaxSize(500,30);
        this.setMinSize(50,25);
        this.setStyle(StaticSceneBuilder.FontStyleColorWhite);

    }
    public String getOptionTAG()
    {
        return optionTAG;
    }

    public void setSettingTAGTextField()
    {
        this.setPrefSize(150,30);
        this.setMaxSize(150,30);
        this.setMinSize(10,10);
        this.setBackground(TextTagBackground);
        this.setOpacity(1);
        this.setFont(ButtonFont);
        this.setEditable(false);
        this.setStyle(StaticSceneBuilder.FontStyleColorWhite);
    }

    public void setSettingTextField(int id, String TAG)
    {
        this.optionTAG = TAG;
        this.setPrefSize(50,30);
        this.setMaxSize(50,30);
        this.setMinSize(10,10);
        this.setBackground(TextFieldBackground);
        this.setOpacity(1);
        this.setFont(ButtonFont);
        this.setEditable(true);
        this.setStyle(StaticSceneBuilder.FontStyleColorWhite);
    }

    public void setSettingMaxEventsTextField(int id, String TAG)
    {
        this.optionTAG = TAG;
        this.setPrefSize(50,30);
        this.setMaxSize(50,30);
        this.setMinSize(10,10);
        this.setBackground(TextFieldBackground);
        this.setOpacity(1);
        this.setFont(ButtonFont);
        this.setEditable(true);
        this.setStyle(StaticSceneBuilder.FontStyleColorWhite);
    }

    public iTextField(String text, int id, int listPos, String TAG, long playerID)
    {
        this.playerID = playerID;
        this.tag = TAG;
        this.listPos = listPos;
        this.id = id;
        this.setText(text);
        this.setBackground(TextFieldBackground);
        this.setOpacity(1);
        this.setFont(ButtonFont);
        this.setEditable(true);
        this.setPrefSize(100,30);
        //this.setMaxSize(500,30);
        this.setMinSize(50,25);
        this.setStyle(StaticSceneBuilder.FontStyleColorWhite);

        this.setOnAction(actionEvent -> {if(this.isEditable()) this.setBackground(StaticSceneBuilder.ButtonAddOnBackground);});
        this.setOnMouseEntered(actionEvent -> {if(this.isEditable()) this.setBackground(StaticSceneBuilder.ButtonAddOnBackground);});
        this.setOnMouseExited(actionEvent -> {if(this.isEditable()) this.setBackground(StaticSceneBuilder.TextFieldBackground);});
        this.setOnMousePressed(actionEvent -> {if(this.isEditable()) this.setBackground(StaticSceneBuilder.ButtonAddClickedBackground);});

        if(TAG.equals("EVENT_NAME"))
        {
            this.setDarkThemedBackround();
            //this.setStyle("-fx-text-fill: white");
        }
    }
    public long getPlayerID()
    {
        return this.playerID;
    }

    public void setDarkThemedBackround()
    {
        this.setBackground(InvisibleBackground);
        this.setOnAction(actionEvent -> {if(this.isEditable()) this.setBackground(StaticSceneBuilder.ButtonAddOnBackground);});
        this.setOnMouseEntered(actionEvent -> {if(this.isEditable()) this.setBackground(StaticSceneBuilder.ButtonAddOnBackground);});
        this.setOnMouseExited(actionEvent -> {if(this.isEditable()) this.setBackground(InvisibleBackground);});
        this.setOnMousePressed(actionEvent -> {if(this.isEditable()) this.setBackground(StaticSceneBuilder.ButtonAddClickedBackground);});
    }

    public boolean checkIfModified()
    {
        for (SuperDTO dto : StaticSystemController.SuperDTOlist)
        {
            for (SuperDTO.Playerstats pl : dto.m_playerStats)
            {
                for(Integer p: pl.getTeamPoints())
                {
                    if(p == 10000)
                    {
                        return true;
                    }
                }

                for(Integer p: pl.getPoints())
                {
                    if(p == 10000)
                    {
                        return true;
                    }
                }

                for(Integer p: pl.getRank())
                {
                    if(p == 10000)
                    {
                        return true;
                    }
                }

                for(Integer p: pl.getLapRank())
                {
                    if(p == 10000)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void getNewData()
    {
        if (tag.equals("EVENT_NAME")) {
            for (SuperDTO dto : StaticSystemController.SuperDTOlist) {
                if (dto.getProgId() == id) {
                    this.setText(dto.getSuperDTOname());
                }
            }
        }
        if (tag.equals("PLAYER_NAME")) {
            for (SuperDTO dto : StaticSystemController.SuperDTOlist) {
                if (dto.getProgId() == id) {
                    for (SuperDTO.Playerstats pl : dto.m_playerStats) {
                        if (playerID == pl.getID()) {
                            this.setText(pl.getName());
                        }
                    }
                }
            }
        }

        if (tag.equals("PLAYER_ID")) {
            for (SuperDTO dto : StaticSystemController.SuperDTOlist) {
                if (dto.getProgId() == id) {
                    for (SuperDTO.Playerstats pl : dto.m_playerStats) {
                        if (playerID == pl.getID()) {
                            this.setText(Long.toString(pl.getID()));
                        }
                    }
                }
            }
        }

        if (tag.equals("CLAN")) {
            for (SuperDTO dto : StaticSystemController.SuperDTOlist) {
                if (dto.getProgId() == id) {
                    for (SuperDTO.Playerstats pl : dto.m_playerStats) {
                        if (playerID == pl.getID()) {
                            this.setText(pl.getClan());

                        }
                    }
                }
            }
        }

        if (tag.equals("LAP_RANK")) {
            for (SuperDTO dto : StaticSystemController.SuperDTOlist) {
                if (dto.getProgId() == id) {
                    for (SuperDTO.Playerstats pl : dto.m_playerStats) {
                        if (playerID == pl.getID()) {
                            this.setText(StaticStringHandler.ParseListStringToString(pl.getLapRank().toString()));
                        }
                    }
                }
            }
        }

        if (tag.equals("RACE_RANK")) {
            for (SuperDTO dto : StaticSystemController.SuperDTOlist) {
                if (dto.getProgId() == id) {
                    for (SuperDTO.Playerstats pl : dto.m_playerStats) {
                        if (playerID == pl.getID()) {
                            this.setText(StaticStringHandler.ParseListStringToString(pl.getRank().toString()));
                        }
                    }
                }
            }
        }

        if (tag.equals("POINTS")) {
            for (SuperDTO dto : StaticSystemController.SuperDTOlist) {
                if (dto.getProgId() == id) {
                    for (SuperDTO.Playerstats pl : dto.m_playerStats) {
                        if (playerID == pl.getID()) {
                            this.setText(StaticStringHandler.ParseListStringToString(pl.getPoints().toString()));
                        }
                    }
                }
            }
        }
        if (tag.equals("TEAM_POINTS")) {
            for (SuperDTO dto : StaticSystemController.SuperDTOlist) {
                if (dto.getProgId() == id) {
                    for (SuperDTO.Playerstats pl : dto.m_playerStats) {
                        if (playerID == pl.getID()) {
                            this.setText(StaticStringHandler.ParseListStringToString(pl.getTeamPoints().toString()));
                        }
                    }
                }
            }
        }
    }
    public void update()
    {
        if(this.isFocused())
        {
            this.setBackground(TextFieldActiveBackground);
        }
        else
        {
            this.setBackground(TextFieldBackground);
        }

        if (tag.equals("EVENT_NAME")) {
            for (SuperDTO dto : StaticSystemController.SuperDTOlist) {
                if (dto.getProgId() == id) {
                    dto.setSuperDTOname(this.getText());
                }
            }
        }

        if (tag.equals("PLAYER_NAME")) {
            for (SuperDTO dto : StaticSystemController.SuperDTOlist) {
                if (dto.getProgId() == id) {
                    for (SuperDTO.Playerstats pl : dto.m_playerStats) {
                        if (playerID == pl.getID()) {
                            pl.setName(playerID, this.getText());

                        }
                    }
                }
            }
        }

        if (tag.equals("CLAN")) {
            for (SuperDTO dto : StaticSystemController.SuperDTOlist) {
                if (dto.getProgId() == id) {
                    for (SuperDTO.Playerstats pl : dto.m_playerStats) {
                        if (playerID == pl.getID()) {
                            pl.setClan(playerID, this.getText());

                        }
                    }
                }
            }
        }

        if (tag.equals("LAP_RANK")) {
            for (SuperDTO dto : StaticSystemController.SuperDTOlist) {
                if (dto.getProgId() == id) {
                    for (SuperDTO.Playerstats pl : dto.m_playerStats) {
                        if (playerID == pl.getID()) {
                            pl.setData(playerID, this.getText(),tag);
                        }
                    }
                }
            }
        }

        if (tag.equals("RACE_RANK")) {
            for (SuperDTO dto : StaticSystemController.SuperDTOlist) {
                if (dto.getProgId() == id) {
                    for (SuperDTO.Playerstats pl : dto.m_playerStats) {
                        if (playerID == pl.getID()) {
                            pl.setData(playerID, this.getText(),tag);
                        }
                    }
                }
            }
        }

        if (tag.equals("POINTS")) {
            for (SuperDTO dto : StaticSystemController.SuperDTOlist) {
                if (dto.getProgId() == id) {
                    for (SuperDTO.Playerstats pl : dto.m_playerStats) {
                        if (playerID == pl.getID()) {
                            pl.setData(playerID, this.getText(),tag);
                        }
                    }
                }
            }
        }
        if (tag.equals("TEAM_POINTS")) {
            for (SuperDTO dto : StaticSystemController.SuperDTOlist) {
                if (dto.getProgId() == id) {
                    for (SuperDTO.Playerstats pl : dto.m_playerStats) {
                        if (playerID == pl.getID()) {
                            pl.setData(playerID, this.getText(),tag);
                        }
                    }
                }
            }
        }

        if (tag.equals("PLAYER_ID")) {
            for (SuperDTO dto : StaticSystemController.SuperDTOlist)
            {
                if (dto.getProgId() == id)
                {
                    List<Long> idlist = new ArrayList<>();
                    for (SuperDTO.Playerstats pl : dto.m_playerStats)
                    {
                        idlist.add(pl.getID());
                    }
                    for (SuperDTO.Playerstats pl : dto.m_playerStats)
                    {
                        if (playerID == pl.getID())
                        {
                            pl.setPlayerID(playerID, Long.parseLong(this.getText()),idlist);
                        }
                    }
                }
            }
        }
    }
}
