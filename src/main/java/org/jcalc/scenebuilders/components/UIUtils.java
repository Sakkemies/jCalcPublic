package org.jcalc.scenebuilders.components;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import org.jcalc.dto.Player;
import org.jcalc.handlers.StaticDTOHandler;
import org.jcalc.handlers.StaticFileLoader;
import org.jcalc.handlers.StaticSystemController;
import org.jcalc.scenebuilders.StaticSceneBuilder;

import java.util.ArrayList;
import java.util.List;

public class UIUtils
{
    public static final Insets Insets_15 = new Insets(15);
    public static final Insets Insets_10 = new Insets(10);
    public static final Insets Insets_4 = new Insets(4);
    public static final Insets Insets_2 = new Insets(2);
    public static final int Spacing_20 = 20;
    public static final int Spacing_10 = 10;
    public static final int Spacing_4 = 4;
    public static final int gap_3 = 3;
    public static final int gap_5 = 5;
    public static final String BasicStyleString = "-fx-background-color: #242424; -fx-border-color: #363636;";

    public static final int buttonPrefWidth = 80;
    public static final int buttonPrefHeight = 25;
    public static final int buttonWidth = 80;
    public static final int buttonHeight = 25;
    public static ScrollPane getEmptyScrollPane()
    {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setBackground(StaticSceneBuilder.RealInvisibleBackground);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setBorder(StaticSceneBuilder.BorderForPane);

        return scrollPane;
    }

    public static ScrollPane getEmptyScrollPane(GridPane content) //TODO NOT WORKING CORRECTLY
    {
        ScrollPane scrollPane = getEmptyScrollPane();
        scrollPane.setContent(content);

        ScrollBar vScrollBar = new ScrollBar();
        vScrollBar.setOrientation(Orientation.VERTICAL);
        vScrollBar.minProperty().bind(scrollPane.vminProperty());
        vScrollBar.maxProperty().bind(scrollPane.vmaxProperty());
        vScrollBar.visibleAmountProperty().bind(scrollPane.heightProperty().divide(content.heightProperty()));
        scrollPane.vvalueProperty().bindBidirectional(vScrollBar.valueProperty());

        return scrollPane;
    }
    public static void setContentStyle(ScrollPane scrollPane)
    {
        scrollPane.getContent().setStyle(BasicStyleString);
    }

    public static void setButtonSize(Button button, int height, int width, int prefHeight, int prefWidth)
    {
        button.setPrefSize(prefWidth,prefHeight);
        button.setMaxSize(width,height);
    }
    public static void setCustomButton(Button button, String text,String style, int maxWidth, int maxHeight, int prefWidth, int prefHeight, int minWidth, int minHeight)
    {
        setButtonSize(button,maxHeight,maxWidth,prefHeight,prefWidth);
        button.setMinSize(minWidth,minHeight);
        button.setStyle(style);
        button.setText(text);
    }
    public static void setCustomButton(Button button, String text,String style, int maxWidth, int prefWidth, int minWidth)
    {
        button.setMinWidth(minWidth);
        button.setPrefWidth(prefWidth);
        button.setMaxWidth(maxWidth);
        button.setStyle(style);
        button.setText(text);
    }
    public static void setButtonSize(Button button, int minHeight, int minWidth, int height, int width, int prefHeight, int prefWidth)
    {
        setButtonSize(button, height, width, prefHeight, prefWidth);
        button.setMinSize(minWidth,minHeight);
    }

    public static void setDefaultButtonStyle(Button button, Font font, Background background, String text,boolean defaultSize)
    {
        if(defaultSize)setDefaultButtonSize(button, true);
        button.setFont(font);
        button.setBackground(background);
        button.setText(text);

        button.setOnAction(actionEvent -> {
            button.setBackground(StaticSceneBuilder.ButtonOnBackground);
        });

        button.setOnMouseEntered(actionEvent -> {
            button.setBackground(StaticSceneBuilder.ButtonOnBackground);
        });

        button.setOnMouseExited(actionEvent -> {
            button.setBackground(StaticSceneBuilder.ButtonBackground);
        });
        button.setOnMousePressed(actionEvent -> {
            button.setBackground(StaticSceneBuilder.ButtonClickedBackground);
        });
    }

    public static void setDefaultButtonSize(Button button,boolean defaultSize)
    {
        if(defaultSize) {
            button.setPrefSize(buttonPrefWidth,buttonPrefHeight);
            button.setMaxSize(buttonPrefWidth,buttonPrefHeight);
            button.setMinSize(10,buttonPrefHeight);
        }
    }
    public static VBox getEmptyVBox(int spacing, Insets insets, Pos pos)
    {
        VBox vBox = new VBox();
        vBox.setBackground(StaticSceneBuilder.RealInvisibleBackground);
        vBox.setPadding(insets);
        vBox.setSpacing(spacing);
        vBox.setAlignment(pos);

        return vBox;
    }

    public static void setEmptyVBox(VBox vBox, Background background, Insets insets, Pos pos, Border border)
    {
        vBox.setBackground(background);
        vBox.setPadding(insets);
        //vBox.setSpacing(spacing);
        vBox.setBorder(border);
        vBox.setAlignment(pos);
    }
    public static VBox getEmptyVBox(int spacing, Insets insets, Pos pos, Background background)
    {
        VBox vBox = new VBox();
        vBox.setBackground(background);
        vBox.setPadding(insets);
        vBox.setSpacing(spacing);
        vBox.setAlignment(pos);

        return vBox;
    }

    public static HBox getEmptyHBox(int spacing, Insets insets, Pos pos, Background background, Border border)
    {
        HBox hBox = getEmptyHBox(spacing);
        hBox.setBackground(background);
        hBox.setPadding(insets);
        if(pos != null) hBox.setAlignment(pos);
        hBox.setBorder(border);

        return hBox;
    }
    public static HBox getEmptyHBox(int spacing, Insets insets, Pos pos, Background background)
    {
        HBox hBox = getEmptyHBox(spacing);
        hBox.setBackground(background);
        hBox.setPadding(insets);
        if(pos != null) hBox.setAlignment(pos);

        return hBox;
    }
    public static HBox getEmptyHBox(int spacing)
    {
        HBox hBox = new HBox();
        hBox.setSpacing(spacing);
        return hBox;
    }

    public static Label getEmptyLabel(String fontStyle, Font font, Background background, String text)
    {
        Label label = new Label(text);
        label.setStyle(fontStyle);
        label.setFont(font);
        label.setAlignment(Pos.BASELINE_CENTER);

        return label;
    }
    public static Label getEmptyLabel(String fontStyle, Font font, String text, Pos pos)
    {
        Label label = new Label(text);
        label.setStyle(fontStyle);
        label.setFont(font);
        label.setAlignment(pos);

        return label;
    }

    public static Label getEmptyLabel(String fontStyle, Font font, String text, Pos pos,Background background)
    {
        Label label = getEmptyLabel(fontStyle, font, text, pos);
        label.setBackground(background);

        return label;
    }

    public static Label getEmptyLabel(String fontStyle,String text)
    {
        Label label = getEmptyLabel(fontStyle, StaticSceneBuilder.FontSmall, text, Pos.CENTER_LEFT);

        return label;
    }

    public static Label getEmptyLabel(String fontStyle,String text, Background background)
    {
        Label label = getEmptyLabel(fontStyle, text);
        label.setBackground(background);

        return label;
    }

    public static Label getImageLabel(Background background, int width, int height)
    {
        Label label = new Label();
        label.setBackground(background);
        label.setPrefSize(width,height);
        label.setMaxSize(width,height);

        return label;
    }

    public static BorderPane getDefaultRootPane()
    {
        BorderPane rootPane = new BorderPane();
        try{
            rootPane.setBackground(StaticFileLoader.LoadImageToBackground(StaticSystemController.defaultProjectBackgroundPath));}
        catch (RuntimeException e){rootPane.setBackground(StaticSceneBuilder.EmptyBackground);}
        System.out.println();

        return rootPane;
    }

    public static BorderPane getEmptyBorderPane()
    {
        BorderPane border = new BorderPane();
        border.setBackground(StaticSceneBuilder.RealInvisibleBackground);

        return border;
    }
    public static BorderPane getEmptyBorderPane(Insets insets)
    {
        BorderPane border = getEmptyBorderPane();
        border.setPadding(insets);

        return border;
    }
    public static BorderPane getEmptyBorderPane(Border borders, Insets insets)
    {
        BorderPane border = getEmptyBorderPane(insets);
        border.setBorder(borders);

        return border;
    }

    public static BorderPane getEmptyBorderPane(Border borders, Insets insets, Background background)
    {
        BorderPane border = getEmptyBorderPane(borders, insets);
        border.setBackground(background);

        return border;
    }
    public static BorderPane getEmptyBorderPane(Insets insets, Background background)
    {
        BorderPane border = getEmptyBorderPane(insets);
        border.setBackground(background);

        return border;
    }

    public static void setHboxDefaultStyle(HBox hBox)
    {
        hBox.setPrefHeight(30);
        hBox.setVisible(true);
        hBox.setBackground(StaticSceneBuilder.BackgroundVeryDarkPaneBackground);
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.BASELINE_CENTER);
        hBox.setBorder(StaticSceneBuilder.BorderForPane);
        hBox.setLayoutY(5);
    }
    public static void setHBoxStyle(HBox hbox, Insets insets, Background background, Pos pos, Border border, int prefHeight, int spacing, int layout_y)
    {

    }

    public static Menu getDefaultMenu(String text)
    {
        Menu menu = new Menu(text);

        return menu;
    }
    public static CheckMenuItem getDefaultCheckMenuItem(String text)
    {
        CheckMenuItem checkMenuItem = new CheckMenuItem(text);

        return checkMenuItem;
    }

    public static void setGridPaneStyle(GridPane grid, Insets insets, int hgap, int vgap, Border border, Background background)
    {
        grid.setBackground(background);
        grid.setPadding(insets);
        grid.setHgap(hgap);
        grid.setVgap(vgap);
        grid.setBorder(border);
    }

    public static GridPane getGridPane(Insets insets, int hgap, int vgap, Border border, Background background)
    {
        GridPane grid = getGridPane(insets,hgap,vgap);
        grid.setBackground(background);
        grid.setBorder(border);

        return grid;
    }

    public static GridPane getGridPane(Insets insets, int hgap, int vgap)
    {
        GridPane grid = new GridPane();
        grid.setPadding(insets);
        grid.setHgap(hgap);
        grid.setVgap(vgap);

        return grid;
    }
    public static TextField getTextField(String text, Background background, String fontStyle)
    {
        TextField textField = new TextField();
        textField.setText(text);
        textField.setBackground(background);
        textField.setStyle(fontStyle);

        return textField;
    }
    public static void setICheckBoxDefaultStyle(iCheckBox checkBox)
    {
        checkBox.setBackground(StaticSceneBuilder.GlassPaneBackground);
        checkBox.setBorder(StaticSceneBuilder.BorderForPane);
    }
    public static void setCheckBoxDefaultStyle(CheckBox checkBox)
    {
        checkBox.setBackground(StaticSceneBuilder.GlassPaneBackground);
        checkBox.setBorder(StaticSceneBuilder.BorderForPane);
    }

    public static MenuItem getDefaultMenuItem(String text)
    {
        MenuItem menuItem = new MenuItem(text);

        return menuItem;
    }

    public static SeparatorMenuItem getDefaultSeparatorMenuItem()
    {
        SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();
        return separatorMenuItem;
    }

    public static ComboBox getDefaultCombobox()
    {
        ComboBox comboBox = new ComboBox<>();
        setComboboxStyle(comboBox);
        return comboBox;
    }

    public static void setComboboxStyle(ComboBox comboBox)
    {

    }

    public static CheckBox getDefaultCheckBox()
    {
        CheckBox checkBox = new CheckBox();
        checkBox.setBackground(StaticSceneBuilder.GlassPaneBackground);
        checkBox.setBorder(StaticSceneBuilder.BorderForPane);

        return checkBox;
    }

    public static ComboBox getOBSPlayerCombobox()
    {
        List<String> playerList = new ArrayList<>();
        if(StaticDTOHandler.playerList != null)
        {
            for (Player pl : StaticDTOHandler.playerList)
            {
                playerList.add(pl.name + ", ID: " + pl.id);
            }
        }

        ComboBox players = new ComboBox(FXCollections
                .observableArrayList(playerList));
        UIUtils.setComboboxStyle(players);

        if(StaticDTOHandler.playerList != null && StaticSystemController.selectedPlayerID != null)
        {
            for(Player pl: StaticDTOHandler.playerList)
            {
                if(pl.id == StaticSystemController.selectedPlayerID)
                {
                    players.setValue(pl.name + ", ID: " + pl.id);
                }
            }
        }

        EventHandler<ActionEvent> event =
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e)
                    {
                        String np = (String) players.getValue();
                        String[] npa = np.split(":");
                        long id = Long.valueOf(npa[npa.length-1].replaceAll(" ",""));
                        StaticSystemController.selectedPlayerID = id;
                        StaticSystemController.updateViews(false,false);
                    }
                };

        players.setOnAction(event);

        return players;
    }

    public static SplitPane getDefaultSplitPane()
    {
        SplitPane pane = new SplitPane();
        pane.setBorder(StaticSceneBuilder.BorderForPane);
        pane.setBackground(StaticSceneBuilder.GlassPaneBackground);
        pane.setPadding(Insets_10);

        return pane;
    }

    public static SplitPane getDefaultVerticalSplitPane()
    {
        SplitPane pane = getDefaultSplitPane();
        pane.setOrientation(Orientation.VERTICAL);

        return pane;
    }
}
