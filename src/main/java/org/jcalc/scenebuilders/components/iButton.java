package org.jcalc.scenebuilders.components;


import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.jcalc.dto.SuperDTO;
import org.jcalc.handlers.StaticFileSaver;
import org.jcalc.handlers.StaticSystemController;
import org.jcalc.scenebuilders.SceneController;
import org.jcalc.scenebuilders.StaticActionHandler;
import org.jcalc.scenebuilders.StaticSceneBuilder;

import java.util.ArrayList;
import java.util.List;

public class iButton extends Button
{
    public int id = 9999;
    public int eventOrderIndex = 0;
    public Button EmptyButton(String text)
    {
        UIUtils.setDefaultButtonStyle(this, StaticSceneBuilder.ButtonFont, StaticSceneBuilder.ButtonBackground, text,true);

        return this;
    }
    public void setOneBackground()
    {
        this.setOnAction(actionEvent -> {
            this.setBackground(StaticSceneBuilder.ButtonAddClickedBackground);
        });

        this.setOnMouseEntered(actionEvent -> {
            this.setBackground(StaticSceneBuilder.ButtonAddClickedBackground);
        });

        this.setOnMouseExited(actionEvent -> {
            this.setBackground(StaticSceneBuilder.ButtonAddClickedBackground);
        });
        this.setOnMousePressed(actionEvent -> {
            this.setBackground(StaticSceneBuilder.ButtonAddClickedBackground);
        });
    }

    public iButton getOrderEventButton(int index, int id, String realName, iDialog dialog)
    {
        this.eventOrderIndex = index;
        this.id = id;

        if(realName.equals("Event")) {
            CreateEmptyNonSelectedIdEventButton(realName + " " + id);
            realName = realName + " " + id;
        }
        else
        {
            CreateEmptyEventButton(realName);
        }

        this.setOnMousePressed(actionEvent ->
        {
            if(StaticSystemController.SelectedIdIndex1 == null)
            {
                StaticSystemController.SelectedIdIndex1 = index;
                setOneBackground();
            }
            else if(StaticSystemController.SelectedIdIndex2 == null)
            {
                StaticSystemController.SelectedIdIndex2 = index;
                setOneBackground();
            }

            if(StaticSystemController.SelectedIdIndex2!=null && StaticSystemController.SelectedIdIndex1!=null)
            {
                List<SuperDTO> listOfSupers = new ArrayList<>();
                //StaticSystemController.eventButtonList.set()
                iButton but1 = StaticSystemController.eventButtonList.get(StaticSystemController.SelectedIdIndex1);
                iButton but2 = StaticSystemController.eventButtonList.get(StaticSystemController.SelectedIdIndex2);

                StaticSystemController.eventButtonList.set(StaticSystemController.SelectedIdIndex2,but1);
                StaticSystemController.eventButtonList.set(StaticSystemController.SelectedIdIndex1,but2);

                for(iButton inte: StaticSystemController.eventButtonList)
                {
                    for(SuperDTO superDTO: StaticSystemController.SuperDTOlist)
                    {
                        if(superDTO.getProgId() == inte.id)
                        {
                            listOfSupers.add(superDTO);
                        }
                    }
                }
                StaticSystemController.SuperDTOlist = listOfSupers;

                StaticSystemController.SelectedIdIndex2 = null;
                StaticSystemController.SelectedIdIndex1 = null;

                ButtonType buttonCANCEL = new ButtonType("Cancel");
                iDialog dialog1 = new iDialog("Modify order", "","ORDER");
                dialog1.show();

                dialog.getDialogPane().getButtonTypes().addAll(buttonCANCEL);
                dialog.close();

                StaticSystemController.updateViews(false,true);
            }
        });

        UIUtils.setDefaultButtonSize(this,true);
        this.setBackground(StaticSceneBuilder.ButtonBackground);

        Tooltip tooltip = new Tooltip("Event: " + realName);
        Tooltip.install(this, tooltip);

        return this;
    }

    public iButton getEventButton(int id, String realName)
    {

        if(realName.equals("Event")) {
            CreateEmptyEventButton(realName + " " + id);
            realName = realName + " " + id;
        }
        else
        {
            CreateEmptyEventButton(realName);
        }
        UIUtils.setButtonSize(this, 28,140,28,300,28,300);
        this.getAction(id);
        this.id = id;

        if (StaticSystemController.selectedId != null)
        {
            if (StaticSystemController.selectedId == id)
            {
                this.setBackground(StaticSceneBuilder.ButtonSelectedEventBackground);
            }
            else
            {
                this.setBackground(StaticSceneBuilder.ButtonBackground);
            }
        }
        else
        {
            this.setBackground(StaticSceneBuilder.ButtonBackground);
        }

        Tooltip tooltip = new Tooltip("Event: " + realName);
        Tooltip.install(this, tooltip);

        return this;
    }
    public int getEventId()
    {
        return this.id;
    }

    public void update(String text)
    {
        this.setText(text);
    }

    public void getAction(int ids)
    {
        this.setOnMouseReleased(actionEvent ->
        {
            if(!StaticSystemController.mergeActive) {
                StaticSystemController.selectedId = this.id;
                StaticSystemController.updateViews(false, true);
            }
            else
            {
                StaticSystemController.selectedMergeID = this.id;

                StaticActionHandler.MergeEvents();

                StaticSystemController.selectedMergeID = null;
                StaticSystemController.mergeActive = false;
                StaticSystemController.updateViews(false,false);
            }
        });
    }

    public Button getViewButton(String text)
    {
        CreateEmptyButton("View");

        this.setOnMouseReleased(actionEvent ->
        {
            if(StaticSystemController.SceneControl == 2 && StaticSystemController.ShowWarnings) {
                iDialog dialog = new iDialog("Change view", "Do you want to save the changes?","CONFIRM");
                dialog.showAndWait();

                if (dialog.getResult().toString().contains("Yes")) {
                    SceneController.SwitchScene(1,false);
                }
            }
            else{
            //else StaticSystemController.updateViews(false, true);
            SceneController.SwitchScene(1,false);}
        });

        Tooltip tooltip = new Tooltip("Change the view");
        Tooltip.install(this, tooltip);

        return this;
    }

    public Button getCancelButton(String text, iDialog dialog)
    {
        CreateEmptyButton("Cancel");

        Tooltip tooltip = new Tooltip("Close the window.");
        Tooltip.install(this, tooltip);
        this.setOnMouseReleased(actionEvent ->
        {
            ButtonType buttonCANCEL = new ButtonType("Cancel");
            dialog.getDialogPane().getButtonTypes().addAll(buttonCANCEL);

            dialog.close();
        });

        return this;
    }

    public Button getMergeButton(String text)
    {
        CreateEmptyButton("Merge");
        if(StaticSystemController.SceneControl == 0 || StaticSystemController.SceneControl == 1)
        {
            this.setDisable(true);
        }

        if(StaticSystemController.mergeActive)
        {
            this.setOnMouseExited(actionEvent ->
            {
                this.setBackground(StaticSceneBuilder.ButtonRoundActiveBackground);
            });
            this.setBackground(StaticSceneBuilder.ButtonRoundActiveBackground);
        }

        this.setOnMouseReleased(actionEvent ->
        {
            if(!StaticSystemController.mergeActive)
            {
                StaticSystemController.mergeActive = true;
                StaticSystemController.updateViews(false,false);
            }
            else
            {
                StaticSystemController.mergeActive = false;
                StaticSystemController.updateViews(false,false);
            }
        });

        Tooltip tooltip = new Tooltip("Merge two events");
        Tooltip.install(this, tooltip);

        return this;
    }

    public Button getResetDefaultsButton(String text, iDialog dialog)
    {
        CreateEmptyButton("Reset");

        Tooltip tooltip = new Tooltip("Reset to default settings");
        Tooltip.install(this, tooltip);

        return this;
    }

    public Button getConfirmForModifyOrderButton(String text, iDialog dialog)
    {
        CreateEmptyButton("Confirm");

        Tooltip tooltip = new Tooltip("Accept changes");
        Tooltip.install(this, tooltip);

        this.setOnMouseReleased(actionEvent ->
        {
            ButtonType buttonCANCEL = new ButtonType("Cancel");
            dialog.getDialogPane().getButtonTypes().addAll(buttonCANCEL);

            dialog.close();
        });

        return this;
    }
    public Button getApplyButton(String text, iDialog dialog)
    {
        CreateEmptyButton("Apply");

        this.setOnMouseReleased(actionEvent ->
        {
            int i = 0;
            for(iTextField field: StaticSystemController.OptionScorefields)
            {
                if(field.getOptionTAG().equals("SCORE"))
                {
                    if (i < 20) {
                        try{StaticSystemController.scoreArray[i + 1] = Integer.parseInt(field.getText());}
                        catch (NumberFormatException e){new iDialog("Settings", "Number is not in valid format!", "ERROR").showAndWait();}
                    }
                    if (i == 20) {
                        try{StaticSystemController.scoreArray[31] = Integer.parseInt(field.getText());}
                        catch (NumberFormatException e){new iDialog("Settings", "Number is not in valid format!", "ERROR").showAndWait();}
                    }
                }
                else if(field.getOptionTAG().equals("MAX_EVENTS"))
                {
                    try{StaticSystemController.MaxScrollEventButton = Integer.parseInt(field.getText()); StaticSystemController.updateViews(false,true);}
                    catch (NumberFormatException e){new iDialog("Settings", "Number is not in valid format!", "ERROR").showAndWait();}
                }
                else if(field.getOptionTAG().equals("FORMAT_ID"))
                {
                    try{StaticSystemController.FormatID = Integer.parseInt(field.getText()); StaticSystemController.updateViews(false,true);}
                    catch (NumberFormatException e){new iDialog("Settings", "Number is not in valid format!", "ERROR").showAndWait();}
                }
                else if(field.getOptionTAG().equals("FORMAT_VERSION"))
                {
                    try{StaticSystemController.FormatVersion = Integer.parseInt(field.getText()); StaticSystemController.updateViews(false,true);}
                    catch (NumberFormatException e){new iDialog("Settings", "Number is not in valid format!", "ERROR").showAndWait();}
                }
                else if(field.getOptionTAG().equals("MAX_EVENTS_TO_REMEMBER"))
                {
                    try{StaticSystemController.MaxEventsToRemember = Integer.parseInt(field.getText()); StaticSystemController.updateViews(false,true);}
                    catch (NumberFormatException e){new iDialog("Settings", "Number is not in valid format!", "ERROR").showAndWait();}
                }
                else if(field.getOptionTAG().equals("MAX_PLAYERS"))
                {
                    try{StaticSystemController.MaxPlayerCount = Integer.parseInt(field.getText()); StaticSystemController.updateViews(false,true);}
                    catch (NumberFormatException e){new iDialog("Settings", "Number is not in valid format!", "ERROR").showAndWait();}
                }
                i++;
            }

            i=0;
            for(iTextField field: StaticSystemController.OptionScorefields)
            {
                if(field.getOptionTAG().equals("SCORE2"))
                {
                    if (i < 41)
                    {
                        try
                        {
                            StaticSystemController.scoreArray2[i-21 + 1] = Integer.parseInt(field.getText());
                        } catch (NumberFormatException e) {
                            new iDialog("Settings", "Number is not in valid format!", "ERROR").showAndWait();
                        }
                    }
                    if (i == 41)
                    {
                        try
                        {
                            StaticSystemController.scoreArray2[31] = Integer.parseInt(field.getText());
                        } catch (NumberFormatException e) {
                            new iDialog("Settings", "Number is not in valid format!", "ERROR").showAndWait();
                        }
                    }
                }
                i++;
            }
            StaticFileSaver.saveSettings();
        });

        Tooltip tooltip = new Tooltip("Apply the changes");
        Tooltip.install(this, tooltip);

        return this;
    }

    public Button getSettingsButton(String text)
    {
        CreateEmptyButton("Settings");

        this.setOnMouseReleased(actionEvent ->
        {
            iDialog dialog = new iDialog("Settings", "","SETTINGS");
            dialog.showAndWait();
        });

        Tooltip tooltip = new Tooltip("Settings");
        Tooltip.install(this, tooltip);

        return this;
    }

    public Button getModifyOrderButton(String text)
    {
        CreateEmptyButton("Modify order");

        this.setOnMouseReleased(actionEvent ->
        {
            iDialog dialog = new iDialog("Modify order", "","ORDER");
            dialog.showAndWait();
        });

        Tooltip tooltip = new Tooltip("Modify the order of the events");
        Tooltip.install(this, tooltip);

        return this;
    }
    public Button getRefreshButton(String text)
    {
        CreateEmptyButton("Refresh");

        this.setOnMouseReleased(actionEvent -> {
            //StaticSceneBuilder.refreshGridList();
            StaticSystemController.updateViews(false,true);
        });

        Tooltip tooltip = new Tooltip("Refresh and save the tables");
        Tooltip.install(this, tooltip);

        return this;
    }

    public Button getSplitButton(String text)
    {
        CreateEmptyButton("Split event");
        if(StaticSystemController.SceneControl == 0 || StaticSystemController.SceneControl == 1) {
            this.setDisable(true);
            //this.setVisible(false);
        }

        this.setOnMouseReleased(actionEvent -> {
            StaticActionHandler.SplitEvent();
        });

        Tooltip tooltip = new Tooltip("Split the selected event into two");
        Tooltip.install(this, tooltip);

        return this;
    }

    public Button getCopyButton(String text)
    {
        CreateEmptyButton("Copy event");
        if(StaticSystemController.SceneControl == 0 || StaticSystemController.SceneControl == 1) {
            this.setDisable(true);
            //this.setVisible(false);
        }

        this.setOnMouseReleased(actionEvent -> {
            StaticActionHandler.CopyEvent();
        });

        Tooltip tooltip = new Tooltip("Copy the selected event into two");
        Tooltip.install(this, tooltip);

        return this;
    }

    public Button getRemovePlayerButton(int progID, Long playerID)
    {

        CreateEmptyRemoveButton("-");
        this.setStyle("-fx-text-fill: white;");
        if(StaticSystemController.SceneControl == 0 || StaticSystemController.SceneControl == 1) {
            this.setDisable(true);
            //this.setVisible(false);
        }

        this.setOnMouseReleased(actionEvent -> {
            for(SuperDTO superDTO: StaticSystemController.SuperDTOlist)
            {
                if(superDTO.getProgId() == StaticSystemController.selectedId) {
                    if (StaticSystemController.ShowWarnings) {
                        iDialog dialog = new iDialog("Delete player", "Are you sure?", "CONFIRM");
                        dialog.showAndWait();

                        if (dialog.getResult().toString().contains("Yes")) {
                            superDTO.deletePlayer(playerID);
                            StaticSystemController.updateViews(false, false);
                        }
                    } else {
                        superDTO.deletePlayer(playerID);
                        StaticSystemController.updateViews(false, false);
                    }
                }
            }
        });

        Tooltip tooltip = new Tooltip("Remove player");
        Tooltip.install(this, tooltip);

        return this;
    }

    public void CreateEmptyRemoveButton(String text)
    {
        this.setBackground(StaticSceneBuilder.ButtonRemoveBackground);
        this.setHeight(25);
        this.setWidth(200);
        this.setPrefSize(100,25);
        this.setFont(StaticSceneBuilder.ButtonFont);
        this.setText(text);

        this.setOnAction(actionEvent -> {this.setBackground(StaticSceneBuilder.ButtonRemoveOnBackground);});
        this.setOnMouseEntered(actionEvent -> {this.setBackground(StaticSceneBuilder.ButtonRemoveOnBackground);});
        this.setOnMouseExited(actionEvent -> {this.setBackground(StaticSceneBuilder.ButtonRemoveBackground);});
        this.setOnMousePressed(actionEvent -> {this.setBackground(StaticSceneBuilder.ButtonRemoveClickedBackground);});
    }

    public void CreateEmptyAddButton(String text)
    {
        this.setBackground(StaticSceneBuilder.ButtonAddBackground);
        this.setHeight(25);
        this.setWidth(200);
        this.setPrefSize(100,25);
        this.setFont(StaticSceneBuilder.ButtonFont);
        this.setText(text);

        this.setOnAction(actionEvent -> {this.setBackground(StaticSceneBuilder.ButtonAddOnBackground);});
        this.setOnMouseEntered(actionEvent -> {this.setBackground(StaticSceneBuilder.ButtonAddOnBackground);});
        this.setOnMouseExited(actionEvent -> {this.setBackground(StaticSceneBuilder.ButtonAddBackground);});
        this.setOnMousePressed(actionEvent -> {this.setBackground(StaticSceneBuilder.ButtonAddClickedBackground);});
    }

    public void CreateEmptyRoundButton(String text)
    {
        this.setBackground(StaticSceneBuilder.ButtonRoundPassiveBackground);
        this.setPrefSize(15,15);
        this.setMinSize(15,2);this.setMaxSize(15,15);
        this.setFont(Font.font("Arial", FontWeight.BOLD,14));
        this.setText(text);

        this.setOnAction(actionEvent -> {this.setBackground(StaticSceneBuilder.ButtonRoundPassiveBackground);});
        this.setOnMouseEntered(actionEvent -> {this.setBackground(StaticSceneBuilder.ButtonRoundPassiveOnBackground);});
        this.setOnMouseExited(actionEvent -> {this.setBackground(StaticSceneBuilder.ButtonRoundPassiveBackground);});
        this.setOnMousePressed(actionEvent -> {this.setBackground(StaticSceneBuilder.ButtonRoundPassiveClickedBackground);});
    }

    public Button getRoundButtonEvent(String text,int index)
    {
        CreateEmptyRoundButton("");
        this.setStyle("-fx-text-fill: #caffb3;");

        if(StaticSystemController.ScrollEventButtonIndex == index)
        {
            this.setDisable(true);
            this.setBackground(StaticSceneBuilder.ButtonRoundActiveSelectedBackground);
        }

        if(StaticSystemController.SuperDTOlist.size() <= index)
        {
            this.setDisable(true);
        }

        this.setOnMouseReleased(actionEvent -> {
            StaticSystemController.ScrollEventButtonIndex = index;
            System.out.println(StaticSystemController.ScrollEventButtonIndex);
            StaticSystemController.updateViews(false,true);
        });

        Tooltip tooltip = new Tooltip("Show events from " + index + " to " + (index+StaticSystemController.MaxScrollEventButton));
        Tooltip.install(this, tooltip);

        return this;
    }

    public Button getSaveEventButton(String text)
    {
        CreateEmptyButton("Save event");
        if(StaticSystemController.SceneControl == 0 || StaticSystemController.SceneControl == 1) {
            this.setDisable(true);
            //this.setVisible(false);
        }

        this.setOnMouseReleased(actionEvent -> {
            String filename = "";

            if(StaticSystemController.selectedId != null)
            {
                for(SuperDTO sup: StaticSystemController.SuperDTOlist)
                {
                    if(sup.getProgId() == StaticSystemController.selectedId)
                    {
                        filename = sup.getSuperDTOname();
                    }
                }
                StaticFileSaver.SaveSingleEvent(filename);
            }
        });

        Tooltip tooltip = new Tooltip("Save selected event");
        Tooltip.install(this, tooltip);

        return this;
    }

    public Button getAddNewPlayer(String text)
    {
        CreateEmptyAddButton("+");
        this.setStyle("-fx-text-fill: #caffb3;");

        if(StaticSystemController.SceneControl == 0 || StaticSystemController.SceneControl == 1) {
            this.setDisable(true);
            //this.setVisible(false);
        }

        this.setOnMouseReleased(actionEvent -> {
            for(SuperDTO superdto: StaticSystemController.SuperDTOlist)
            {
                if(superdto.getProgId() == StaticSystemController.selectedId)
                {
                    superdto.addPlayer(StaticSystemController.freePlayerId);
                    StaticSystemController.freePlayerId -= 1;
                    StaticSystemController.updateViews(false,true);
                }
            }
        });

        Tooltip tooltip = new Tooltip("Add a new player");
        Tooltip.install(this, tooltip);

        return this;
    }

    public Button getAddNewEvent(String text)
    {
        CreateEmptyAddButton("+");
        this.setStyle("-fx-text-fill: #caffb3;");

        this.setOnMouseReleased(actionEvent -> {
            int id = 0;
            if(StaticSystemController.SuperDTOlist != null) {
                for (SuperDTO superDTO : StaticSystemController.SuperDTOlist) {
                    if (id <= superDTO.getProgId()) {
                        id = superDTO.getProgId() + 1;
                        StaticSystemController.selectedId = id;
                    }
                }
            }

            StaticSystemController.FreeID = id + 1;

            SuperDTO sup = new SuperDTO();
            sup.setNullEvent();
            sup.setProgId(id);
            StaticSystemController.SuperDTOlist.add(sup);
            StaticSystemController.updateViews(false,false);
        });

        this.setMinSize(10,10);
        this.setMaxWidth(30);

        Tooltip tooltip = new Tooltip("Add a new event");
        Tooltip.install(this, tooltip);

        return this;
    }

    public Button getDeleteEventButton(String text)
    {
        CreateEmptyButton("Delete event");

        if(StaticSystemController.SceneControl == 0 || StaticSystemController.SceneControl == 1) {
            this.setDisable(true);
            //this.setVisible(false);
        }

        this.setOnMouseReleased(actionEvent ->
        {
            if(StaticSystemController.ShowWarnings){
                iDialog dialog = new iDialog("Delete event", "Are you sure?","CONFIRM");
                dialog.showAndWait();

                if(dialog.getResult().toString().contains("Yes"))
                {
                    int idss = 0;
                    boolean found = false;
                    if(StaticSystemController.selectedId != null)
                    {
                        for (SuperDTO supe : StaticSystemController.SuperDTOlist)
                        {
                            if (StaticSystemController.selectedId == supe.getProgId())
                            {
                                found = true;
                                break;
                            }
                            idss++;
                        }

                        if (found)
                        {
                            StaticSystemController.SuperDTOlist.remove(idss);
                            StaticSystemController.updateViews(false, true);
                        }
                    }}}else{
                int idss = 0;
                boolean found = false;
                if(StaticSystemController.selectedId != null)
                {
                    for (SuperDTO supe : StaticSystemController.SuperDTOlist)
                    {
                        if (StaticSystemController.selectedId == supe.getProgId())
                        {
                            found = true;
                            break;
                        }
                        idss++;
                    }

                    if (found)
                    {
                        StaticSystemController.SuperDTOlist.remove(idss);
                        StaticSystemController.updateViews(false, true);
                    }}
            }
        });

        Tooltip tooltip = new Tooltip("Delete selected event");
        Tooltip.install(this, tooltip);

        return this;
    }

    public Button getDeleteEventByIndexButton(String text,int ids)
    {

        CreateEmptyRemoveButton("-");
        UIUtils.setCustomButton(this, "-", "-fx-text-fill: #caffb3;",110,50,50);
        this.id = ids;

        this.setOnMouseReleased(actionEvent ->
        {
            if(StaticSystemController.ShowWarnings){
                iDialog dialog = new iDialog("Delete event", "Are you sure?","CONFIRM");
                dialog.showAndWait();

                if(dialog.getResult().toString().contains("Yes"))
                {
                    int idss = 0;
                    boolean found = false;
                    if(StaticSystemController.SuperDTOlist != null)
                    {
                        for (SuperDTO supe : StaticSystemController.SuperDTOlist)
                        {
                            if (id == supe.getProgId())
                            {
                                found = true;
                                break;
                            }
                            idss++;
                        }

                        if (found)
                        {
                            StaticSystemController.SuperDTOlist.remove(idss);
                            StaticSystemController.updateViews(false, true);
                        }
                    }}}else{
                int idss = 0;
                boolean found = false;
                if(StaticSystemController.SuperDTOlist != null)
                {
                    for (SuperDTO supe : StaticSystemController.SuperDTOlist)
                    {
                        if (id == supe.getProgId())
                        {
                            found = true;
                            break;
                        }
                        idss++;
                    }

                    if (found)
                    {
                        StaticSystemController.SuperDTOlist.remove(idss);
                        StaticSystemController.updateViews(false, true);
                    }}
            }
        });

        Tooltip tooltip = new Tooltip("Delete event");
        Tooltip.install(this, tooltip);

        return this;
    }
    public void CreateEmptyButton(String text)
    {
        UIUtils.setDefaultButtonStyle(this, StaticSceneBuilder.ButtonFont,StaticSceneBuilder.ButtonBackground, text,true);

        this.setOnAction(actionEvent -> {this.setBackground(StaticSceneBuilder.ButtonOnBackground);});
        this.setOnMouseEntered(actionEvent -> {this.setBackground(StaticSceneBuilder.ButtonOnBackground);});
        this.setOnMouseExited(actionEvent -> {this.setBackground(StaticSceneBuilder.ButtonBackground);});
        this.setOnMousePressed(actionEvent -> {this.setBackground(StaticSceneBuilder.ButtonClickedBackground);});
    }

    public void CreateEmptyEventButton(String text)
    {
        UIUtils.setDefaultButtonStyle(this, StaticSceneBuilder.ButtonFont,StaticSceneBuilder.ButtonBackground, text,true);

        this.setOnAction(actionEvent -> {
            if(StaticSystemController.selectedId == null)
            {
                this.setBackground(StaticSceneBuilder.ButtonOnBackground);
            }
            else if(id != StaticSystemController.selectedId)
            {
                this.setBackground(StaticSceneBuilder.ButtonOnBackground);
            }
            else if(id == StaticSystemController.selectedId)
            {
                this.setBackground(StaticSceneBuilder.ButtonSelectedEventBackground);
            }
        });

        this.setOnMouseEntered(actionEvent -> {
            if(StaticSystemController.selectedId == null)
            {
                this.setBackground(StaticSceneBuilder.ButtonOnBackground);
            }
            else if(id != StaticSystemController.selectedId)
            {
                this.setBackground(StaticSceneBuilder.ButtonOnBackground);
            }
            else if(id == StaticSystemController.selectedId)
            {
                this.setBackground(StaticSceneBuilder.ButtonSelectedEventBackground);
            }
        });

        this.setOnMouseExited(actionEvent -> {
            if(StaticSystemController.selectedId == null)
            {
                this.setBackground(StaticSceneBuilder.ButtonBackground);
            }
            else if(id != StaticSystemController.selectedId)
            {
                this.setBackground(StaticSceneBuilder.ButtonBackground);
            }
            else if(id == StaticSystemController.selectedId)
            {
                this.setBackground(StaticSceneBuilder.ButtonSelectedEventBackground);
            }
        });
        this.setOnMousePressed(actionEvent -> {
            if(StaticSystemController.selectedId == null)
            {
                this.setBackground(StaticSceneBuilder.ButtonClickedBackground);
            }
            else if(id != StaticSystemController.selectedId)
            {
                this.setBackground(StaticSceneBuilder.ButtonClickedBackground);
            }
            else if(id == StaticSystemController.selectedId)
            {
                this.setBackground(StaticSceneBuilder.ButtonAddClickedBackground);
            }
        });
    }
    public void CreateEmptyNonSelectedIdEventButton(String text)
    {
        UIUtils.setDefaultButtonStyle(this, StaticSceneBuilder.ButtonFont,StaticSceneBuilder.ButtonBackground, text,true);

        this.setOnAction(actionEvent -> {
            if(StaticSystemController.selectedId == null)
            {
                this.setBackground(StaticSceneBuilder.ButtonOnBackground);
            }
            else if(id != StaticSystemController.selectedId)
            {
                this.setBackground(StaticSceneBuilder.ButtonOnBackground);
            }
        });

        this.setOnMouseEntered(actionEvent -> {
            if(StaticSystemController.selectedId == null)
            {
                this.setBackground(StaticSceneBuilder.ButtonOnBackground);
            }
            else if(id != StaticSystemController.selectedId)
            {
                this.setBackground(StaticSceneBuilder.ButtonOnBackground);
            }
        });

        this.setOnMouseExited(actionEvent -> {
            if(StaticSystemController.selectedId == null)
            {
                this.setBackground(StaticSceneBuilder.ButtonBackground);
            }
            else if(id != StaticSystemController.selectedId)
            {
                this.setBackground(StaticSceneBuilder.ButtonBackground);
            }
        });
        this.setOnMousePressed(actionEvent -> {
            if(StaticSystemController.selectedId == null)
            {
                this.setBackground(StaticSceneBuilder.ButtonClickedBackground);
            }
            else if(id != StaticSystemController.selectedId)
            {
                this.setBackground(StaticSceneBuilder.ButtonClickedBackground);
            }
        });
    }

}

