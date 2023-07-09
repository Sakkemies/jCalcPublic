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
import javafx.scene.layout.HBox;
import org.jcalc.dto.SuperDTO;
import org.jcalc.handlers.StaticSystemController;
import org.jcalc.scenebuilders.StaticActionHandler;
import org.jcalc.scenebuilders.StaticSceneBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.jcalc.scenebuilders.StaticSceneBuilder.ButtonFont;

public class iHBox extends HBox
{
    public void setNewEventBox()
    {

        TextField nameField = new TextField();
        nameField.setText("");
        nameField.setMaxWidth(400);
        nameField.setPrefWidth(400);
        nameField.setEditable(false);
        nameField.setBackground(StaticSceneBuilder.RealInvisibleBackground);
        nameField.setFont(StaticSceneBuilder.FontMedium);
        nameField.setStyle(StaticSceneBuilder.FontStyleColorWhite);
        nameField.setOnMousePressed(actionEvent -> {
            iDialog dialog = new iDialog("Change event name", "", "CHANGE_NAME");
            dialog.show();
        });

        List<String> superDTOS = new ArrayList<>();
        setBackground(StaticSceneBuilder.InvisibleBackground);
        setSpacing(UIUtils.Spacing_10);
        setPadding(UIUtils.Insets_4);

        if(StaticSystemController.SuperDTOlist != null)
        {
            for (SuperDTO sup: StaticSystemController.SuperDTOlist)
            {
                superDTOS.add(sup.getSuperDTOname() + ", ID: " + sup.getProgId());
            }
        }

        ComboBox events = new ComboBox(FXCollections
                .observableArrayList(superDTOS));

        if(StaticSystemController.SuperDTOlist != null && StaticSystemController.selectedId != null)
        {
            for(SuperDTO sup: StaticSystemController.SuperDTOlist)
            {
                if(sup.getProgId() == StaticSystemController.selectedId)
                {
                    events.setValue(sup.getSuperDTOname() + ", ID: " + sup.getProgId());
                    nameField.setText(sup.getSuperDTOname());
                }
            }
        }

        EventHandler<ActionEvent> event =
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e)
                    {
                        //System.out.println(players.getValue());

                        String pp = (String) events.getValue();
                        String[] pp2 = pp.split(":");
                        try
                        {
                            int parseTOProgID = Integer.parseInt(pp2[pp2.length-1].replaceAll(" ",""));
                            if(StaticSystemController.mergeActive)
                            {
                                StaticSystemController.selectedMergeID = parseTOProgID;

                                StaticActionHandler.MergeEvents();

                                StaticSystemController.selectedMergeID = null;
                                StaticSystemController.mergeActive = false;
                            }
                            else {StaticSystemController.selectedId = parseTOProgID;}}
                        catch (NumberFormatException ex){}

                        StaticSystemController.updateViews(false,false);
                    }
                };

        events.setOnAction(event);
        events.setMaxWidth(200);
        //events.setMinHeight(1);

        getChildren().addAll(events,nameField);
        if(StaticSystemController.selectedId != null) {
            for (SuperDTO sup : StaticSystemController.SuperDTOlist) {
                if (sup.getProgId() == StaticSystemController.selectedId) {
                    iTextField label = new iTextField("Score option 2:");
                    label.setText("Score option 2:");
                    label.setPrefWidth(107);
                    label.setFont(StaticSceneBuilder.FontSmall);
                    label.setStyle(StaticSceneBuilder.FontStyleColorWhite);
                    label.setBackground(StaticSceneBuilder.InvisibleBackground);

                    iCheckBox checkBox = new iCheckBox();
                    checkBox.setPadding(new Insets(5,0,0,0));
                    checkBox.setActionSecondaryScores();
                    getChildren().addAll(label, checkBox);
                }
            }
        }
    }
    public void getButtonsForButtonHbox()
    {
        Button nextScene = new iButton().getViewButton("View");
        Button settings = new iButton().getSettingsButton("Settings");
        Button modifyOrder = new iButton().getModifyOrderButton("Modify order");

        this.getChildren().addAll(nextScene,settings,modifyOrder);
    }

    public void getButtonsForSettingsButtonBbox(iDialog dialog)
    {
        Button applyChanges = new iButton().getApplyButton("Apply", dialog);
        Button cancel = new iButton().getCancelButton("Close", dialog);
        cancel.setText("Close");
        Button resetDefaults = new iButton().getResetDefaultsButton("Reset defaults", dialog); resetDefaults.setDisable(true);

        this.getChildren().addAll(applyChanges,resetDefaults,cancel);
    }

    public void getButtonsForEventHbox()
    {
        Button deleteEvent = new iButton().getDeleteEventButton("Delete event");
        Button merge = new iButton().getMergeButton("Merge");
        Button refresh = new iButton().getRefreshButton("Refresh");
        Button copy = new iButton().getCopyButton("Copy event");
        Button split = new iButton().getSplitButton("Split event");
        Button save = new iButton().getSaveEventButton("Save event");
        Button addEvent = new iButton().getAddNewEvent("New event");

        this.getChildren().addAll(addEvent,refresh,merge,deleteEvent,copy,split,save);
    }
    public HBox getHboxForButtons()
    {
        UIUtils.setHboxDefaultStyle(this);

        getButtonsForButtonHbox();

        return this;
    }

    public HBox getHboxForEventButtons()
    {
        UIUtils.setHboxDefaultStyle(this);

        getButtonsForEventHbox();

        return this;
    }
}
