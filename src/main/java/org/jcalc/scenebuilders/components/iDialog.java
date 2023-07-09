package org.jcalc.scenebuilders.components;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.jcalc.dto.SuperDTO;
import org.jcalc.handlers.StaticSystemController;
import org.jcalc.scenebuilders.StaticSceneBuilder;

import java.util.ArrayList;
import java.util.List;

public class iDialog extends Dialog
{
    //public int[] points = StaticSystemController.scoreArray;
    public iDialog(String title, String contentText, String TAG)
    {
        switch (TAG)
        {
            case "CONFIRM":
                confirmDialog(title,contentText);
                break;
            case "ERROR":
                errorDialog(title,contentText);
                break;
            case "SETTINGS":
                settingsDialog(title,contentText);
                break;
            case "ORDER":
                orderDialog(title);
                break;
            case "CHANGE_PROJECT_NAME":
                projectNameDialog(title);
                break;
            case "CHANGE_NAME":
                nameDialog(title);
                break;
            default:
                break;
        }
    }

    public void projectNameDialog(String title)
    {
        setTitle(title);
        Label titlebox = UIUtils.getEmptyLabel(StaticSceneBuilder.FontStyleColorWhite,title,StaticSceneBuilder.DialogBackground);
        String name = StaticSystemController.projectName;

        iTextField textField = new iTextField(name);
        textField.setText(name);
        textField.setEditable(true);
        textField.setPrefWidth(200);

        Button buttonOKi = new iButton().EmptyButton("OK");
        buttonOKi.setText("OK");

        Button buttonCancel = new iButton().EmptyButton("Cancel");
        buttonCancel.setText("Cancel");

        VBox box = UIUtils.getEmptyVBox(UIUtils.Spacing_4, UIUtils.Insets_4,Pos.BASELINE_CENTER, StaticSceneBuilder.DialogBackground);
        HBox hBox = UIUtils.getEmptyHBox(UIUtils.Spacing_4,UIUtils.Insets_10,Pos.BASELINE_CENTER, StaticSceneBuilder.DialogBackground);
        hBox.getChildren().addAll(buttonOKi,buttonCancel);

        box.getChildren().addAll(titlebox,textField);

        BorderPane pane = UIUtils.getEmptyBorderPane(UIUtils.Insets_4);
        pane.setTop(box);
        pane.setCenter(hBox);

        this.getDialogPane().setBackground(StaticSceneBuilder.DialogBackground);
        this.getDialogPane().setStyle(StaticSceneBuilder.FontStyleColorWhiteSize14);
        this.getDialogPane().setContent(pane);

        buttonOKi.setOnMouseReleased(mouseEvent -> {
            ButtonType buttonOK = new ButtonType("OK");
            this.getDialogPane().getButtonTypes().addAll(buttonOK);

            if(textField.getText() != "") {
                StaticSystemController.projectName = textField.getText();
            }
            StaticSystemController.updateViews(false,true);

            this.close();
        });

        buttonCancel.setOnMouseReleased(mouseEvent -> {
            ButtonType buttonOK = new ButtonType("OK");
            this.getDialogPane().getButtonTypes().addAll(buttonOK);

            this.close();
        });

        //ButtonType buttonOK = new ButtonType("OK");

        //this.getDialogPane().getButtonTypes().addAll(buttonOK);
        //

        this.setOnCloseRequest(e -> {});
    }
    public void nameDialog(String title)
    {
        setTitle(title);
        Label titlebox = UIUtils.getEmptyLabel(StaticSceneBuilder.FontStyleColorWhite,title,StaticSceneBuilder.DialogBackground);
        String name = "";

        if(StaticSystemController.selectedId != null) {
            for (SuperDTO sup : StaticSystemController.SuperDTOlist) {
                if (sup.getProgId() == StaticSystemController.selectedId) {
                    name = sup.getSuperDTOname();
                }
            }
        }
        iTextField textField = new iTextField(name);
        textField.setText(name);
        textField.setEditable(true);
        textField.setPrefWidth(200);

        Button buttonOKi = new iButton().EmptyButton("OK");
        buttonOKi.setText("OK");

        Button buttonCancel = new iButton().EmptyButton("Cancel");
        buttonCancel.setText("Cancel");

        VBox box = UIUtils.getEmptyVBox(UIUtils.Spacing_4, UIUtils.Insets_4,Pos.BASELINE_CENTER, StaticSceneBuilder.DialogBackground);
        HBox hBox = UIUtils.getEmptyHBox(UIUtils.Spacing_4,UIUtils.Insets_10,Pos.BASELINE_CENTER, StaticSceneBuilder.DialogBackground);

        hBox.getChildren().addAll(buttonOKi,buttonCancel);

        box.getChildren().addAll(titlebox,textField);

        BorderPane pane = UIUtils.getEmptyBorderPane(UIUtils.Insets_4);
        pane.setTop(box);
        pane.setCenter(hBox);
        pane.setBorder(StaticSceneBuilder.BorderForPane);

        this.getDialogPane().setBackground(StaticSceneBuilder.DialogBackground);
        this.getDialogPane().setStyle(StaticSceneBuilder.FontStyleColorWhiteSize14);
        this.getDialogPane().setContent(pane);

        buttonOKi.setOnMouseReleased(mouseEvent -> {
            ButtonType buttonOK = new ButtonType("OK");
            this.getDialogPane().getButtonTypes().addAll(buttonOK);

            for(SuperDTO sup : StaticSystemController.SuperDTOlist)
            {
                if(sup.getProgId() == StaticSystemController.selectedId)
                {
                    sup.setSuperDTOname(textField.getText());
                    StaticSystemController.updateViews(false,false);
                }
            }

            this.close();
        });

        buttonCancel.setOnMouseReleased(mouseEvent -> {
            ButtonType buttonOK = new ButtonType("OK");
            this.getDialogPane().getButtonTypes().addAll(buttonOK);

            this.close();
        });

        //ButtonType buttonOK = new ButtonType("OK");

        //this.getDialogPane().getButtonTypes().addAll(buttonOK);
        //

        this.setOnCloseRequest(e -> {});
    }
    public void orderDialog(String title)
    {

        Label titlebox = UIUtils.getEmptyLabel(StaticSceneBuilder.FontStyleColorWhite,title,StaticSceneBuilder.DialogBackground);

        GridPane grid = UIUtils.getGridPane(UIUtils.Insets_2,UIUtils.gap_5,UIUtils.gap_5);

        int i = 0;
        int e = 0;
        StaticSystemController.eventButtonList = new ArrayList<>();

        for(SuperDTO superDTO: StaticSystemController.SuperDTOlist)
        {
            iButton but = new iButton().getOrderEventButton(i + (30 * e), superDTO.getProgId(), superDTO.getSuperDTOname(), this);StaticSystemController.eventButtonList.add(but);grid.add(but, e, i);
            //else {iButton but = new iButton().getOrderEventButton((1 * (1+e)) - 1, superDTO.getProgId(), superDTO.getSuperDTOname(), this);StaticSystemController.eventButtonList.add(but);grid.add(but, e, i);}

            i++;
            if(i > 29)
            {
                i = 0;
                e++;
            }
        }

        HBox hBox = UIUtils.getEmptyHBox(UIUtils.Spacing_4);

        Button confirm = new iButton().getConfirmForModifyOrderButton("Confirm",this);
        Button cancel = new iButton().getCancelButton("Cancel",this);

        hBox.getChildren().addAll(confirm,cancel);

        ScrollPane scrollPane = UIUtils.getEmptyScrollPane();
        scrollPane.setContent(grid);
        UIUtils.setContentStyle(scrollPane);

        BorderPane borderPane = UIUtils.getEmptyBorderPane();
        borderPane.setCenter(scrollPane);
        borderPane.setBottom(hBox);
        borderPane.setTop(titlebox);

        this.getDialogPane().setBackground(StaticSceneBuilder.DialogBackground);
        this.getDialogPane().setStyle(StaticSceneBuilder.FontStyleColorWhiteSize14);
        this.setResizable(true);

        this.getDialogPane().setContent(borderPane);
    }

    public void confirmDialog(String title, String contentText)
    {
        this.setTitle(title);

        VBox box = UIUtils.getEmptyVBox(UIUtils.Spacing_10,UIUtils.Insets_2,Pos.CENTER, StaticSceneBuilder.DialogBackground);
        Label titlebox =UIUtils.getEmptyLabel(StaticSceneBuilder.FontStyleColorWhite,title,StaticSceneBuilder.DialogBackground);
        Label contentbox = UIUtils.getEmptyLabel(StaticSceneBuilder.FontStyleColorWhite,contentText,StaticSceneBuilder.DialogBackground);

        box.getChildren().addAll(titlebox,contentbox);
        this.getDialogPane().setBackground(StaticSceneBuilder.DialogBackground);
        this.getDialogPane().setStyle(StaticSceneBuilder.FontStyleColorWhiteSize14);
        this.getDialogPane().setContent(box);

        ButtonType buttonYes = new ButtonType("Yes");
        ButtonType buttonNo = new ButtonType("No");

        this.getDialogPane().getButtonTypes().addAll(buttonYes, buttonNo);
        //

        this.setOnCloseRequest(e -> {});
    }

    public void errorDialog(String title, String contentText)
    {
        this.setTitle(title);

        VBox box = UIUtils.getEmptyVBox(UIUtils.Spacing_10,UIUtils.Insets_2,Pos.CENTER, StaticSceneBuilder.DialogBackground);
        Label titlebox =UIUtils.getEmptyLabel(StaticSceneBuilder.FontStyleColorWhite,title,StaticSceneBuilder.DialogBackground);
        Label contentbox = UIUtils.getEmptyLabel(StaticSceneBuilder.FontStyleColorWhite,contentText,StaticSceneBuilder.DialogBackground);

        Button buttonOKi = new iButton().EmptyButton("OK");
        buttonOKi.setText("OK");

        box.getChildren().addAll(titlebox,contentbox,buttonOKi);
        this.getDialogPane().setBackground(StaticSceneBuilder.DialogBackground);
        this.getDialogPane().setStyle(StaticSceneBuilder.FontStyleColorWhiteSize14);
        this.getDialogPane().setContent(box);

        buttonOKi.setOnMouseReleased(mouseEvent -> {
            ButtonType buttonOK = new ButtonType("OK");
            this.getDialogPane().getButtonTypes().addAll(buttonOK);

            this.close();
        });

        //ButtonType buttonOK = new ButtonType("OK");

        //this.getDialogPane().getButtonTypes().addAll(buttonOK);
        //

        this.setOnCloseRequest(e -> {});
    }
    public static void setMinSize(iTextField textField)
    {
        textField.setMinSize(minWidthTField,minHeightTField);
    }

    public static int minWidthTField = 60;
    public static int minHeightTField = 30;
    public GridPane getScoreOptions(GridPane scoreGrid)
    {
        for(int i = 0; i < 22; i++)
        {
            if(i > 0)
            {
                if (i != 21) {
                    iTextField textField = new iTextField("Placement: " + i);
                    textField.setSettingTAGTextField();
                    scoreGrid.add(textField, 0, i);
                    setMinSize(textField);

                    iTextField textFieldtext = new iTextField(StaticSystemController.scoreArray[i] + "");
                    textFieldtext.setSettingTextField(i, "SCORE");
                    setMinSize(textFieldtext);
                    scoreGrid.add(textFieldtext, 1, i);
                    StaticSystemController.OptionScorefields.add(textFieldtext);
                }
                else
                {

                    iTextField textField = new iTextField("Fastest lap");
                    textField.setSettingTAGTextField();
                    scoreGrid.add(textField, 0, i);
                    setMinSize(textField);

                    iTextField textFieldtext = new iTextField(StaticSystemController.scoreArray[31] + "");
                    textFieldtext.setSettingTextField(i, "SCORE");
                    setMinSize(textFieldtext);
                    scoreGrid.add(textFieldtext, 1, i);
                    StaticSystemController.OptionScorefields.add(textFieldtext);
                }
            }
            else
            {
                Label scoreOptionLabel = UIUtils.getEmptyLabel(StaticSceneBuilder.FontStyleColorWhite,"Score options 1");
                scoreGrid.add(scoreOptionLabel,0,i);
            }
        }
        for(int i = 0; i < 22; i++)
        {
            if(i > 0)
            {
                if (i != 21) {
                    iTextField textField = new iTextField("Placement: " + i);
                    textField.setSettingTAGTextField();
                    scoreGrid.add(textField, 3, i);
                    setMinSize(textField);

                    iTextField textFieldtext = new iTextField(StaticSystemController.scoreArray2[i] + "");
                    textFieldtext.setSettingTextField(i, "SCORE2");
                    setMinSize(textFieldtext);
                    scoreGrid.add(textFieldtext, 4, i);
                    StaticSystemController.OptionScorefields.add(textFieldtext);
                }
                else
                {

                    iTextField textField = new iTextField("Fastest lap");
                    textField.setSettingTAGTextField();
                    scoreGrid.add(textField, 3, i);
                    setMinSize(textField);

                    iTextField textFieldtext = new iTextField(StaticSystemController.scoreArray2[31] + "");
                    setMinSize(textFieldtext);
                    textFieldtext.setSettingTextField(i, "SCORE2");
                    scoreGrid.add(textFieldtext, 4, i);
                    StaticSystemController.OptionScorefields.add(textFieldtext);
                }
            }
            else
            {
                Label scoreOptionLabel = UIUtils.getEmptyLabel(StaticSceneBuilder.FontStyleColorWhite,"Score options 2");
                scoreGrid.add(scoreOptionLabel,3,i);
            }
        }

        return scoreGrid;
    }

    public void setStyle()
    {
    }
    public void settingsDialog(String title, String contentText)
    {
        StaticSystemController.OptionScorefields = new ArrayList<>();

        this.setTitle(title);
        this.setResizable(true);

        iVBox box = new iVBox(StaticSceneBuilder.InvisibleBackground);
        Label titlebox = UIUtils.getEmptyLabel(StaticSceneBuilder.FontStyleColorWhite,StaticSceneBuilder.ButtonFont,title, Pos.BASELINE_CENTER, StaticSceneBuilder.RealInvisibleBackground);
        //Label contentbox = UIUtils.getEmptyLabel(StaticSceneBuilder.FontStyleColorWhite,StaticSceneBuilder.ButtonFont,contentText, Pos.BASELINE_CENTER, StaticSceneBuilder.InvisibleBackground);

        GridPane scoreGrid = getScoreOptions(new GridPane());
        UIUtils.setGridPaneStyle(scoreGrid, UIUtils.Insets_10, UIUtils.gap_3, UIUtils.gap_3, StaticSceneBuilder.BorderForPane, StaticSceneBuilder.BackgroundVeryDarkPaneBackground);
        GridPane commonGrid = UIUtils.getGridPane(UIUtils.Insets_10, UIUtils.gap_3, UIUtils.gap_3, StaticSceneBuilder.BorderForPane,StaticSceneBuilder.InvisibleBackground);
        GridPane jsonGrid = UIUtils.getGridPane(UIUtils.Insets_10, UIUtils.gap_3, UIUtils.gap_3, StaticSceneBuilder.BorderForPane,StaticSceneBuilder.InvisibleBackground);
        GridPane centerGrid = UIUtils.getGridPane(UIUtils.Insets_10,UIUtils.gap_3,UIUtils.gap_3,StaticSceneBuilder.BorderForPane,StaticSceneBuilder.InvisibleBackground);


        /** LEFT OPTIONS **/
        Label commonOptionLabel = UIUtils.getEmptyLabel(StaticSceneBuilder.FontStyleColorWhite,"Event editing options");
        commonGrid.add(commonOptionLabel,0,0);

        iTextField customScores = new iTextField("Custom scores");
        Tooltip scoretoolTip = new Tooltip("This option overruns all event scores with manually set ones in the table view");
        customScores.setTooltip(scoretoolTip);
        customScores.setPrefWidth(textFieldWidth);
        iCheckBox customScoresBox = new iCheckBox();
        if(StaticSystemController.UseCustomPoints) {customScoresBox.setSelected(true);}else customScoresBox.setSelected(false);
        customScoresBox.setOnAction(actionEvent -> {if(customScoresBox.isSelected())StaticSystemController.UseCustomPoints=true;else StaticSystemController.UseCustomPoints=false;});

        iTextField teamsByClan = new iTextField("Team scores by clan");
        Tooltip teamsByClantoolTip = new Tooltip("This option overruns original team scores");
        teamsByClan.setTooltip(teamsByClantoolTip);
        teamsByClan.setPrefWidth(textFieldWidth);
        iCheckBox teamsByClanBox = new iCheckBox();
        if(StaticSystemController.TeamsByClan) {teamsByClanBox.setSelected(true);}else teamsByClanBox.setSelected(false);
        teamsByClanBox.setOnAction(actionEvent -> {if(teamsByClanBox.isSelected())StaticSystemController.TeamsByClan=true;else StaticSystemController.TeamsByClan=false;});

        iTextField IDediting = new iTextField("Player ID editing");
        Tooltip idtoolTip = new Tooltip("Enables player ID editing");
        IDediting.setTooltip(idtoolTip);
        IDediting.setPrefWidth(textFieldWidth);
        iCheckBox IDeditingBox = new iCheckBox();
        if(StaticSystemController.EnablePlayerIDEditing) {IDeditingBox.setSelected(true);}else IDeditingBox.setSelected(false);
        IDeditingBox.setOnAction(actionEvent -> {if(IDeditingBox.isSelected())StaticSystemController.EnablePlayerIDEditing=true;else StaticSystemController.EnablePlayerIDEditing=false;});

        iTextField autoCorrect = new iTextField("Auto correct events");
        Tooltip autotoolTip = new Tooltip("Adds automatically events to the not modified players \nif they have different amount of events");
        autoCorrect.setTooltip(autotoolTip);
        autoCorrect.setPrefWidth(textFieldWidth);
        iCheckBox autoCorrectBox = new iCheckBox();
        if(StaticSystemController.autoCorrectEventSize) {autoCorrectBox.setSelected(true);}else autoCorrectBox.setSelected(false);
        autoCorrectBox.setOnAction(actionEvent -> {if(autoCorrectBox.isSelected())StaticSystemController.autoCorrectEventSize=true;else StaticSystemController.autoCorrectEventSize=false;});

        iTextField autoSplit = new iTextField("Auto split events");
        Tooltip autoSplittoolTip = new Tooltip("Split automatically loaded sessions");
        autoSplit.setTooltip(autoSplittoolTip);
        autoSplit.setPrefWidth(textFieldWidth);
        iCheckBox autoSplitBox = new iCheckBox();
        if(StaticSystemController.AutoSplitEvents) {autoSplitBox.setSelected(true);}else autoSplitBox.setSelected(false);
        autoSplitBox.setOnAction(actionEvent -> {if(autoSplitBox.isSelected())StaticSystemController.AutoSplitEvents=true;else StaticSystemController.AutoSplitEvents=false;});

        commonGrid.add(customScores,0,1);commonGrid.add(customScoresBox,1,1);
        commonGrid.add(teamsByClan,0,2);commonGrid.add(teamsByClanBox,1,2);
        commonGrid.add(autoCorrect,0,3);commonGrid.add(autoCorrectBox,1,3);
        commonGrid.add(IDediting,0,4);commonGrid.add(IDeditingBox,1,4);
        commonGrid.add(autoSplit,0,5);commonGrid.add(autoSplitBox,1,5);

        /** JSON OPTIONS **/
        iTextField formatTextField = new iTextField("Format ID: ");
        Tooltip formattoolTip = new Tooltip("Format ID. Default: 2856");
        formatTextField.setTooltip(formattoolTip);
        formatTextField.setPrefWidth(textFieldWidth);
        iTextField sFormatField = new iTextField(StaticSystemController.FormatID + "");
        sFormatField.setSettingMaxEventsTextField(999999, "FORMAT_ID");
        StaticSystemController.OptionScorefields.add(sFormatField);

        iTextField formatVersionTextField = new iTextField("Format version: ");
        Tooltip formatVersiontoolTip = new Tooltip("Format version. Default: 3");
        formatVersionTextField.setTooltip(formatVersiontoolTip);
        formatVersionTextField.setPrefWidth(textFieldWidth);
        iTextField sFormatVersionField = new iTextField(StaticSystemController.FormatVersion + "");
        sFormatVersionField.setSettingMaxEventsTextField(999999, "FORMAT_VERSION");
        StaticSystemController.OptionScorefields.add(sFormatVersionField);

        iTextField maxEventsToRememberTextField = new iTextField("Max events to remember: ");
        Tooltip maxEventsToRemembertoolTip = new Tooltip("Max events to remember. Default: 100");
        maxEventsToRememberTextField.setTooltip(maxEventsToRemembertoolTip);
        maxEventsToRememberTextField.setPrefWidth(textFieldWidth);
        iTextField smaxEventsToRememberField = new iTextField(StaticSystemController.MaxEventsToRemember + "");
        smaxEventsToRememberField.setSettingMaxEventsTextField(999999, "MAX_EVENTS_TO_REMEMBER");
        StaticSystemController.OptionScorefields.add(smaxEventsToRememberField);

        iTextField maxPlayerCountTextField = new iTextField("Max player count: ");
        Tooltip maxPlayerCounttoolTip = new Tooltip("Max player count. Default: 100");
        maxPlayerCountTextField.setTooltip(maxPlayerCounttoolTip);
        maxPlayerCountTextField.setPrefWidth(textFieldWidth);
        iTextField smaxPlayerCountField = new iTextField(StaticSystemController.MaxPlayerCount + "");
        smaxPlayerCountField.setSettingMaxEventsTextField(999999, "MAX_PLAYERS");
        StaticSystemController.OptionScorefields.add(smaxPlayerCountField);

        Label jsonOptionLabel = UIUtils.getEmptyLabel(StaticSceneBuilder.FontStyleColorWhite,"JSON -options");
        jsonGrid.add(jsonOptionLabel,0,0);

        jsonGrid.add(formatTextField,0,1);jsonGrid.add(sFormatField,1,1);
        jsonGrid.add(formatVersionTextField,0,2);jsonGrid.add(sFormatVersionField,1,2);
        jsonGrid.add(maxEventsToRememberTextField,0,3);jsonGrid.add(smaxEventsToRememberField,1,3);
        jsonGrid.add(maxPlayerCountTextField,0,4);jsonGrid.add(smaxPlayerCountField,1,4);

        /** BUTTONS **/
        iHBox hbox = new iHBox();
        hbox.getButtonsForSettingsButtonBbox(this);
        hbox.setBorder(StaticSceneBuilder.BorderForEventButtons);
        hbox.setPadding(new Insets(5,5,5,5));
        hbox.setSpacing(5);

        /** CENTER OPTIONS **/

        //iTextField stextField = new iTextField("Showed events: ");
        //Tooltip eventtoolTip = new Tooltip("How many event you see on left panel in event view. \nIf you have small resolution, it's recommended to use smaller number");
        //stextField.setTooltip(eventtoolTip);
        //stextField.setPrefWidth(200);
        //iTextField sOptionField = new iTextField(StaticSystemController.MaxScrollEventButton + "");
        //sOptionField.setSettingMaxEventsTextField(999999, "MAX_EVENTS");
        //StaticSystemController.OptionScorefields.add(sOptionField);

        iTextField screenStretcher = new iTextField("Screen stretcher");
        Tooltip screenStretcherTip = new Tooltip("If enabled, it tries to force window updating sizes of components");
        screenStretcher.setTooltip(screenStretcherTip);
        screenStretcher.setPrefWidth(textFieldWidth);
        iCheckBox screenStretcherBox = new iCheckBox();
        if(StaticSystemController.screenStretcher) {screenStretcherBox.setSelected(true);}else screenStretcherBox.setSelected(false);
        screenStretcherBox.setOnAction(actionEvent -> {if(screenStretcherBox.isSelected())StaticSystemController.screenStretcher=true;else StaticSystemController.screenStretcher=false;});

        iTextField warnings = new iTextField("Show additional warnings");
        Tooltip warningtoolTip = new Tooltip("If enabled, you see additional warnings when you make big changes");
        warnings.setTooltip(warningtoolTip);
        warnings.setPrefWidth(textFieldWidth);
        iCheckBox warningsBox = new iCheckBox();
        if(StaticSystemController.ShowWarnings) {warningsBox.setSelected(true);}else warningsBox.setSelected(false);
        warningsBox.setOnAction(actionEvent -> {if(warningsBox.isSelected())StaticSystemController.ShowWarnings=true;else StaticSystemController.ShowWarnings=false;});


        iTextField tableview = new iTextField("Table view");
        Tooltip tabletoolTip = new Tooltip("If enabled, you see table in table view. If disabled, you see grid");
        tableview.setTooltip(tabletoolTip);
        tableview.setPrefWidth(textFieldWidth);
        iCheckBox tableviewBox = new iCheckBox();
        if(StaticSystemController.TableViewEnabled) {tableviewBox.setSelected(true);}else tableviewBox.setSelected(false);
        tableviewBox.setOnAction(actionEvent -> {if(tableviewBox.isSelected())StaticSystemController.TableViewEnabled=true;else StaticSystemController.TableViewEnabled=false;});

        Label centerOptionLabel = UIUtils.getEmptyLabel(StaticSceneBuilder.FontStyleColorWhite,"More options");

        centerGrid.add(centerOptionLabel,0,0);
        centerGrid.add(warnings,0,1);centerGrid.add(warningsBox,1,1);
        centerGrid.add(screenStretcher,0,2);centerGrid.add(screenStretcherBox,1,2);

        /** ACTUAL PANEL **/
        BorderPane settingPane = UIUtils.getEmptyBorderPane(StaticSceneBuilder.BorderForPane, UIUtils.Insets_15);
        settingPane.setTop(titlebox);
        settingPane.setBottom(hbox);

        GridPane gridPane = UIUtils.getGridPane(UIUtils.Insets_4,UIUtils.gap_3,UIUtils.gap_3,StaticSceneBuilder.BorderForPane,StaticSceneBuilder.BackgroundVeryDarkPaneBackground);
        gridPane.add(commonGrid, 0,0);
        gridPane.add(jsonGrid,0,1);
        gridPane.add(centerGrid,0,2);

        ScrollPane scrollPane = UIUtils.getEmptyScrollPane();
        scrollPane.setContent(scoreGrid);
        UIUtils.setContentStyle(scrollPane);

        SplitPane split1 = iSplitPane.getSplitPaneForGridPanes(gridPane, scrollPane);

        settingPane.setCenter(split1);
        this.getDialogPane().setBackground(StaticSceneBuilder.DialogBackground);
        this.getDialogPane().setStyle(StaticSceneBuilder.FontStyleColorWhiteSize14);
        this.getDialogPane().setContent(settingPane);

        settingPane.setMaxSize(StaticSceneBuilder.dialogMaxWidth, StaticSceneBuilder.dialogMaxWidthHeight);
        settingPane.setPrefSize(StaticSceneBuilder.dialogMaxWidth, StaticSceneBuilder.dialogMaxWidthHeight);

        this.setOnCloseRequest(e ->
        {
            ButtonType buttonCANCEL = new ButtonType("Close");
            this.getDialogPane().getButtonTypes().addAll(buttonCANCEL);
            StaticSystemController.updateViews(false,true);

            this.close();
            StaticSystemController.OptionScorefields = new ArrayList<>();
        });
    }

    public static final int textFieldWidth = 200;
}
