package org.jcalc.scenebuilders;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.jcalc.dto.Player;
import org.jcalc.dto.SuperDTO;
import org.jcalc.handlers.StaticDTOHandler;
import org.jcalc.handlers.StaticFileLoader;
import org.jcalc.handlers.StaticStringHandler;
import org.jcalc.handlers.StaticSystemController;
import org.jcalc.scenebuilders.components.*;

import java.util.ArrayList;
import java.util.List;

public class StaticSceneBuilder
{
    public static int SceneWidth = 1360;
    public static int SceneHeight = 768;

    public static int dialogMaxWidth = 800;
    public static int dialogMaxWidthHeight = 600;
    public static Number SplitPanePosition = 0.17440119760479042;

    /**COLORS**/
    public static CornerRadii CornerRadiiForComponents = new CornerRadii(8);
    public static CornerRadii CornerRadiiForTextFields = new CornerRadii(4);
    public static CornerRadii CornerRadiiForEventButtons = new CornerRadii(10);
    public static CornerRadii CornerRadiiForClickedButton = new CornerRadii(12);
    public static Color PaneColor = new Color(0.30, 0.3, 0.3,0.5);
    public static Color GlassPaneColor = new Color(0.30, 0.3, 0.3,0.2);
    public static Color BackgroundPaneColor = new Color(0.25, 0.25, 0.25,0.4);
    public static Color BackgroundDarkPaneColor = new Color(0.25, 0.25, 0.25,0.9);
    public static Color BackgroundVeryDarkPaneColor = new Color(0.15, 0.15, 0.15,0.8);
    public static Color InvisibleBackgroundColor = new Color(0.25, 0.25, 0.25,0.9);
    public static Color InvisibleBackgroundColor2 = new Color(0.15, 0.15, 0.15,0.4);
    public static Color RealInvisibleBackgroundColor = new Color(0.25, 0.25, 0.25,0);
    public static Color BorderColor = new Color(0.2, 0.2, 0.2,0.5);
    public static Color DialogColor = new Color(0.2, 0.2, 0.2,1);

    public static Color ButtonColor = new Color(0.50, 0.5, 0.52,1);
    public static Color ButtonOnColor = new Color(0.54, 0.54, 0.56,1);
    public static Color ButtonClickColor = new Color(0.70, 0.7, 0.72,1);
    public static Color ButtonSelectedColor = new Color(0.80, 0.4, 0.4,1);

    public static Color ButtonRemoveColor = new Color(0.8, 0.4, 0.4,1);
    public static Color ButtonRemoveOnColor = new Color(0.9, 0.5, 0.5,1);
    public static Color ButtonRemoveClickColor = new Color(0.7, 0.3, 0.3,1);

    public static Color ButtonRoundPassiveColor = new Color(0.60, 0.9, 0.60,1);
    public static Color ButtonRoundPassiveOnColor = new Color(0.70, 1, 0.7,1);
    public static Color ButtonRoundPassiveClickColor = new Color(0.50, 0.8, 0.5,1);
    public static Color ButtonRoundActiveColor = new Color(0.60, 0.80, 0.90,1);
    public static Color ButtonRoundActiveOnColor = new Color(0.70, 0.70, 1,1);
    public static Color ButtonRoundActiveClickColor = new Color(0.50, 0.50, 0.80,1);
    public static Color ButtonRoundActiveSelectedColor = new Color(0.1, 1, 1,0.5);
    public static Color YGridPaneHeaderBackroundColor = new Color(0.1, 1, 1,0.06);
    public static Color YGridPaneBackroundColor = new Color(0.01, 0.01, 0.01,0.2);
    public static Color YGridPane2BackroundColor = new Color(0.1, 0.1, 0.1,0.2);
    public static Color YGridPaneRedBackroundColor = new Color(0.2, 0.005, 0.005,0.2);
    public static Color YGridPaneRed2BackroundColor = new Color(0.4, 0.05, 0.05,0.2);
    public static Background ButtonRoundPassiveBackground = new Background(new BackgroundFill(ButtonRoundPassiveColor, new CornerRadii(12), Insets.EMPTY));
    public static Background ButtonRoundPassiveOnBackground = new Background(new BackgroundFill(ButtonRoundPassiveOnColor, new CornerRadii(12), Insets.EMPTY));
    public static Background ButtonRoundPassiveClickedBackground = new Background(new BackgroundFill(ButtonRoundPassiveClickColor, new CornerRadii(12), Insets.EMPTY));
    public static Background ButtonRoundActiveBackground = new Background(new BackgroundFill(ButtonRoundActiveColor, new CornerRadii(12), Insets.EMPTY));
    public static Background ButtonRoundActiveOnBackground = new Background(new BackgroundFill(ButtonRoundActiveOnColor, new CornerRadii(12), Insets.EMPTY));
    public static Background ButtonRoundActiveClickedBackground = new Background(new BackgroundFill(ButtonRoundActiveClickColor, new CornerRadii(12), Insets.EMPTY));
    public static Background ButtonRoundActiveSelectedBackground = new Background(new BackgroundFill(ButtonRoundActiveSelectedColor, new CornerRadii(12), Insets.EMPTY));
    public static Background YGridPaneHeaderBackground = new Background(new BackgroundFill(YGridPaneHeaderBackroundColor, new CornerRadii(2), Insets.EMPTY));
    public static Background YGridPaneBackground = new Background(new BackgroundFill(YGridPaneBackroundColor, new CornerRadii(2), Insets.EMPTY));
    public static Background YGridPane2Background = new Background(new BackgroundFill(YGridPane2BackroundColor, new CornerRadii(2), Insets.EMPTY));
    public static Background YGridPaneRedBackground = new Background(new BackgroundFill(YGridPaneRedBackroundColor, new CornerRadii(2), Insets.EMPTY));
    public static Background YGridPane2RedBackground = new Background(new BackgroundFill(YGridPaneRed2BackroundColor, new CornerRadii(2), Insets.EMPTY));
    public static Color ButtonAddColor = new Color(0.4, 0.8, 0.4,1);
    public static Color ButtonAddOnColor = new Color(0.5, 0.9, 0.5,1);
    public static Color ButtonAddClickColor = new Color(0.3, 0.7, 0.3,1);

    public static Color TextTagColor = new Color(0.30, 0.3, 0.32,0.7);
    public static Color TextFieldColor = new Color(0.44, 0.44, 0.46,0.7);
    public static Color TextFieldActiveColor = new Color(0.94, 0.74, 0.74,0.9);
    public static Color EmptyBackgroundColor = new Color(0.02, 0.02, 0.02,1);
    public static Background EmptyBackground = new Background(new BackgroundFill(EmptyBackgroundColor, CornerRadii.EMPTY, Insets.EMPTY));
    public static Background ButtonRemoveBackground = new Background(new BackgroundFill(ButtonRemoveColor, CornerRadiiForComponents, Insets.EMPTY));
    public static Background ButtonRemoveOnBackground = new Background(new BackgroundFill(ButtonRemoveOnColor, CornerRadiiForComponents, Insets.EMPTY));
    public static Background ButtonRemoveClickedBackground = new Background(new BackgroundFill(ButtonRemoveClickColor, CornerRadiiForClickedButton, Insets.EMPTY));

    public static Background ButtonAddBackground = new Background(new BackgroundFill(ButtonAddColor, CornerRadiiForComponents, Insets.EMPTY));
    public static Background ButtonSelectedEventBackground = new Background(new BackgroundFill(ButtonAddColor, CornerRadiiForClickedButton, Insets.EMPTY));
    public static Background ButtonAddOnBackground = new Background(new BackgroundFill(ButtonAddOnColor, CornerRadiiForComponents, Insets.EMPTY));
    public static Background ButtonAddClickedBackground = new Background(new BackgroundFill(ButtonAddClickColor, CornerRadiiForClickedButton, Insets.EMPTY));

    public static Border BorderForPane = new Border(new BorderStroke(BorderColor, BorderStrokeStyle.SOLID, CornerRadiiForComponents, new BorderWidths(2)));
    public static Border BorderForEventButtons = new Border(new BorderStroke(BorderColor, BorderStrokeStyle.SOLID, CornerRadiiForEventButtons, new BorderWidths(2)));
    public static Border BorderForEventButtons2 = new Border(new BorderStroke(RealInvisibleBackgroundColor, BorderStrokeStyle.SOLID, new CornerRadii(1), new BorderWidths(8)));
    public static Background InvisibleBackground = new Background(new BackgroundFill(InvisibleBackgroundColor, CornerRadiiForComponents, Insets.EMPTY));
    public static Background RealInvisibleBackground = new Background(new BackgroundFill(RealInvisibleBackgroundColor, CornerRadiiForComponents, Insets.EMPTY));

    public static Background PaneBackground = new Background(new BackgroundFill( PaneColor, CornerRadiiForComponents, Insets.EMPTY));
    public static Background GlassPaneBackground = new Background(new BackgroundFill(GlassPaneColor, CornerRadiiForComponents, Insets.EMPTY));
    public static Background DialogPaneBackground = new Background(new BackgroundFill(BackgroundDarkPaneColor, new CornerRadii(0), Insets.EMPTY));
    public static Background BackgroundPaneBackground = new Background(new BackgroundFill(BackgroundPaneColor, CornerRadiiForComponents, Insets.EMPTY));
    public static Background BackgroundDarkPaneBackground = new Background(new BackgroundFill(BackgroundDarkPaneColor, CornerRadiiForComponents, Insets.EMPTY));
    public static Background BackgroundVeryDarkPaneBackground = new Background(new BackgroundFill(BackgroundVeryDarkPaneColor, new CornerRadii(0), Insets.EMPTY));
    public static Background ButtonBackground = new Background(new BackgroundFill(ButtonColor, CornerRadiiForComponents, Insets.EMPTY));
    public static Background ButtonOnBackground = new Background(new BackgroundFill(ButtonOnColor, CornerRadiiForComponents, Insets.EMPTY));
    public static Background ButtonClickedBackground = new Background(new BackgroundFill(ButtonClickColor, CornerRadiiForClickedButton, Insets.EMPTY));
    public static Background ButtonSelectedBackground = new Background(new BackgroundFill(ButtonSelectedColor, CornerRadiiForClickedButton, Insets.EMPTY));
    public static Background TextTagBackground = new Background(new BackgroundFill(TextTagColor, CornerRadiiForTextFields, Insets.EMPTY));
    public static Background TextFieldBackground = new Background(new BackgroundFill(TextFieldColor, CornerRadiiForTextFields, Insets.EMPTY));
    public static Background TextFieldActiveBackground = new Background(new BackgroundFill(TextFieldActiveColor, CornerRadiiForTextFields, Insets.EMPTY));
    public static Background DialogBackground = new Background(new BackgroundFill(DialogColor, new CornerRadii(0), Insets.EMPTY));
    public static Font ButtonFont = Font.font("Arial", FontWeight.BOLD,14);
    public static Font FontBig = Font.font("Arial", FontWeight.BOLD,28);
    public static Font FontVeryBig = Font.font("Arial", FontWeight.BOLD,100);
    public static Font FontMedium = Font.font("Arial", FontWeight.BOLD,16);
    public static Font FontSmall = Font.font("Arial", FontWeight.BOLD,12);
    public static String FontStyleColorWhite = "-fx-text-fill: white";
    public static String FontStyleColorWhiteSize14 = "-fx-font-size: 14px; -fx-text-fill: white";

    /**COMPONENTS**/
    //public static TableView<iTableForResults.project.DTO> FullTable;
    public static Scene CreateMainScene()
    {
        BorderPane rootPane = setBasicComponents();
        rootPane.setTop(new iMenuBar().getPlayerMenuBar());

        if(StaticSystemController.SuperDTOlist != null) StaticDTOHandler.updatePlayerList();

        ComboBox players = UIUtils.getOBSPlayerCombobox();

        HBox hBox = UIUtils.getEmptyHBox(UIUtils.Spacing_4,UIUtils.Insets_4,null,BackgroundDarkPaneBackground,BorderForPane);
        hBox.getChildren().add(players);

        BorderPane centerPane = new BorderPane();
        centerPane.setCenter(new iPlayerPane());
        centerPane.setTop(hBox);

        BorderPane border = UIUtils.getEmptyBorderPane();
        border.setCenter(centerPane);
        border.setPadding(new Insets(10));

        rootPane.setCenter(border);
        return new Scene(rootPane, getWidth(), getHeight());
        //return new Scene(rootPane);
    }

    public static Scene CreateLoadingScene()
    {
        Label label = UIUtils.getEmptyLabel(FontStyleColorWhite,FontVeryBig,"Waiting...",Pos.CENTER);

        BorderPane rootPane = setBasicComponents();
        rootPane.setTop(new iMenuBar().getMenuBar());
        rootPane.setCenter(label);

        rootPane.setBottom(new iVBox().getVboxForItems());

        return new Scene(rootPane, getWidth(), getHeight());
        //return new Scene(rootPane);
    }
    public static Scene CreateObjectScene()
    {
        iGridPane itemBox = new iGridPane();
        itemBox.setEventVBox(166, 10);

        iHBox hBox = new iHBox();
        hBox.setNewEventBox();
        hBox.setMaxHeight(42);

        iGridPane gridPane = new iGridPane();gridPane.setEventItems();
        gridPane.setBackground(BackgroundVeryDarkPaneBackground);

        ScrollPane scrollPane = UIUtils.getEmptyScrollPane(gridPane);
        //scrollPane.setContent(gridPane);
        UIUtils.setContentStyle(scrollPane);

        SplitPane splitPane = iSplitPane.getVerticalSplitPane();
        splitPane.getItems().addAll(hBox,scrollPane);

        BorderPane rootPane = setBasicComponents();
        rootPane.setTop(new iMenuBar().getEventMenuBar());
        rootPane.setCenter(splitPane);

        return new Scene(rootPane, getWidth(), getHeight());
        //return new Scene(rootPane);
    }
    public static Scene CreateTableScene()
    {
        BorderPane rootPane = setBasicComponents();
        VBox vBox = UIUtils.getEmptyVBox(UIUtils.Spacing_20, UIUtils.Insets_10, Pos.TOP_CENTER, RealInvisibleBackground);

        Label label = UIUtils.getEmptyLabel(StaticSceneBuilder.FontStyleColorWhite,StaticSceneBuilder.FontBig,StaticSystemController.projectName,Pos.BASELINE_CENTER);
        if(StaticSystemController.projectBackground != null)
        {
            Label imageLabel = UIUtils.getImageLabel(StaticSystemController.projectBackground, 300,300);
            vBox.getChildren().add(imageLabel);

            imageLabel.setOnMouseReleased(e -> {
                SceneController.switchToLoadingScene();
                StaticFileLoader.loadProjectImage(StaticSystemController.defaultProjectImagePath, true);
                StaticSystemController.updateViews(true, false);
                SceneController.SwitchScene(StaticSystemController.SceneControl, true);
            });
        }


        vBox.getChildren().add(label);
        label.setOnMouseReleased(e -> {
            iDialog dialog = new iDialog("Project name", "","CHANGE_PROJECT_NAME");
            dialog.show();
        });

        if(StaticSystemController.TableViewEnabled)
        {
            iTableForResults iTable = new iTableForResults();
            iTable.createList();
            rootPane.setCenter(iTable.getTableForResults());
        }
        else
        {
            yGridPane grid = new yGridPane();
            grid.createTable();
            grid.setBackground(StaticSceneBuilder.BackgroundVeryDarkPaneBackground);

            ScrollPane scrollPane = UIUtils.getEmptyScrollPane();
            scrollPane.setContent(grid);
            UIUtils.setContentStyle(scrollPane);

            rootPane.setCenter(scrollPane);
        }

        rootPane.setTop(new iMenuBar().getTableMenuBar());
        rootPane.setLeft(vBox);

        return new Scene(rootPane, getWidth(), getHeight());
        //return new Scene(rootPane);
    }

    public static BorderPane setBasicComponents()
    {
        BorderPane rootPane = UIUtils.getDefaultRootPane();

        BorderPane rootBottomPane = UIUtils.getEmptyBorderPane(UIUtils.Insets_10, StaticSceneBuilder.RealInvisibleBackground);
        //rootBottomPane.setBorder(BorderForPane);
        //rootBottomPane.setBackground(InvisibleBackground);

        rootBottomPane.setLeft(new iHBox().getHboxForButtons());
        rootBottomPane.setRight(new iHBox().getHboxForEventButtons());
        ScrollPane scrollPane =  UIUtils.getEmptyScrollPane();
        scrollPane.setContent(rootBottomPane);
        UIUtils.setContentStyle(scrollPane);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        rootPane.setBottom(scrollPane);

        return rootPane;
    }

    public static double getHeight()
    {
        try
        {
            double height = StaticSystemController.MainStage.getScene().getHeight();
            return height;
        }
        catch (NullPointerException e)
        {
            double height = SceneHeight;
            return height;
        }
    }
    public static double getWidth()
    {
        try
        {
            double width = StaticSystemController.MainStage.getScene().getWidth();
            return width;
        }
        catch (NullPointerException e)
        {
            double width = SceneWidth;
            return width;
        }
    }

    public static void refreshGridList(boolean updateScene)
    {
        if (StaticSystemController.TextFieldListInGridPane.size() != 0)
        {
            boolean update = true;

            int index = 10000;
            Long playerID = Long.valueOf(10000);
            for(iTextField field: StaticSystemController.TextFieldListInGridPane)
            {
                index = StaticStringHandler.GetDeletedItem(field.getText());
                if (index != 10000)
                {
                    playerID = field.getPlayerID();
                    //update = false;
                    for (SuperDTO dto : StaticSystemController.SuperDTOlist) {
                        if (dto.getProgId() == StaticSystemController.selectedId) {
                            dto.deleteEvent(index, playerID);
                            update = false;
                        }
                    }
                }
            }

            if(update)
            {
                for (iTextField field : StaticSystemController.TextFieldListInGridPane)
                {
                    field.update();
                }
                if(updateScene)StaticSystemController.updateViews(false,true);
            }
            else
            {
                for (iTextField field : StaticSystemController.TextFieldListInGridPane)
                {
                    field.getNewData();
                }
            }
        }
    }

}

