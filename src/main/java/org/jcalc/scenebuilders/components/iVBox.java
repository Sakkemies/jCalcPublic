package org.jcalc.scenebuilders.components;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import org.jcalc.dto.Player;
import org.jcalc.dto.SuperDTO;
import org.jcalc.handlers.StaticDTOHandler;
import org.jcalc.handlers.StaticSystemController;
import org.jcalc.scenebuilders.StaticActionHandler;
import org.jcalc.scenebuilders.StaticSceneBuilder;

import java.util.ArrayList;
import java.util.List;

public class iVBox extends VBox
{
    public iVBox(Background background) {setBackground(background);}
    public iVBox() {}

    public Player getPlayer() //TODO
    {
        if(StaticSystemController.selectedPlayerID != null)
        {
            if (StaticDTOHandler.playerList != null)
            {
                for(Player player: StaticDTOHandler.playerList)
                {
                    if(player.id == StaticSystemController.selectedPlayerID)
                    {
                        return player;
                    }
                }
            }
        }
        return null;
    }
    public VBox getVboxForItems()
    {
        setVBoxStyle(StaticSceneBuilder.BackgroundPaneBackground, UIUtils.Insets_2);

        this.setPrefWidth(StaticSceneBuilder.getWidth());
        this.setPrefWidth(100);
        this.setMaxHeight(700);
        this.setLayoutX(5);

        return this;
    }

    public void setVBoxStyle(Background background, Insets customInsets)
    {
        UIUtils.setEmptyVBox(this, background,customInsets, Pos.BASELINE_LEFT,StaticSceneBuilder.BorderForPane);
        this.setVisible(true);
    }

}

