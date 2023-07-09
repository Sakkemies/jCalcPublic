package org.jcalc.scenebuilders.components;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.jcalc.dto.Player;
import org.jcalc.handlers.StaticDTOHandler;
import org.jcalc.handlers.StaticSystemController;
import org.jcalc.scenebuilders.StaticSceneBuilder;

import java.util.ArrayList;
import java.util.List;

public class iPlayerPane extends BorderPane
{
    public static final String pointString = "  Points: ";
    public static final String avgPositionString = "  Average race position: ";
    public static final String avgBestLapPositionString = "  Average best lap position: ";

    public iPlayerPane()
    {
        setBorder(StaticSceneBuilder.BorderForPane);
        setBackground(StaticSceneBuilder.RealInvisibleBackground);

        GridPane InfoGrid = new GridPane();
        Player player = getPlayer();

        List<iTextField> infoList = new ArrayList<>();

        if(player != null)
        {
            infoList.add(getTextField(player, player.name, null, StaticSceneBuilder.FontBig, 600, 65));
            if(StaticSystemController.ShowIDinPlayerView) infoList.add(getTextField(player, "  ID: " + player.id, null, StaticSceneBuilder.FontMedium, 600, 30));
            if(!player.clan.equals("") && !player.clan.equals("null") )infoList.add(getTextField(player, "  Clan: " + player.clan, null, StaticSceneBuilder.FontMedium, 600, 30));
            infoList.add(getTextField(player, pointString + StaticDTOHandler.giveAsIntegerValue(player.racePointList), null, StaticSceneBuilder.FontMedium, 600, 30));
            infoList.add(getTextField(player, avgPositionString + player.averagePosition, null, StaticSceneBuilder.FontMedium, 600, 30));
            infoList.add(getTextField(player, avgBestLapPositionString + player.averageLapPosition, null, StaticSceneBuilder.FontMedium, 600, 30));
        }

        if(infoList != null)
        {
            for(int i = 0; i < infoList.size(); i++)
            {
                InfoGrid.add(infoList.get(i), 0, i);
            }
        }
        if(player != null)setCenter(getLineChartWithKnownData(player,"Race positions", "Positions", POSITIONS));
        setLeft(InfoGrid);
    }

    public iTextField getTextField(Player player, String text1, Number text2, Font font, int width, int height)
    {
        iTextField textField = new iTextField(text1);
        if(text1 != null)
        {
            textField.setAlignment(Pos.BASELINE_LEFT);
            textField.setText(text1);
            textField.setBackground(StaticSceneBuilder.RealInvisibleBackground);
            textField.setMaxHeight(height);
            textField.setPrefHeight(height);
            textField.setFont(font);
            textField.setStyle("-fx-text-fill: white");
        }
        return textField;
    }

    public Player getPlayer()
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

    public VBox getLineChartWithKnownData(Player player, String name, String definition, byte tag)
    {
        List<String> possibilities = new ArrayList<>();
        possibilities.add("Race positions");
        possibilities.add("Lap positions");
        possibilities.add("Points");
        possibilities.add("Lap & race positions");

        List<Integer> integerList = new ArrayList<>();

        XYChart.Series series = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();

        ComboBox selections = new ComboBox(FXCollections
                .observableArrayList(possibilities));

        if(StaticSystemController.SelectedChartType == 0)
        {
            selections.setValue("Race positions");
            series.setName("Race positions");
            integerList.addAll(player.racePosList);
        }
        else if (StaticSystemController.SelectedChartType == 1)
        {
            selections.setValue("Lap positions");
            series.setName("Lap positions");
            integerList.addAll(player.lapPosList);
        }
        else if (StaticSystemController.SelectedChartType == 2)
        {
            selections.setValue("Points");
            series.setName("Points");
            integerList.addAll(player.racePointList);
        }
        else if (StaticSystemController.SelectedChartType == 3)
        {
            selections.setValue("Lap & race positions");
            series.setName("Race positions");
            series2.setName("Lap positions");
            integerList.addAll(player.racePosList);
        }

        EventHandler<ActionEvent> event =
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e)
                    {
                        //System.out.println(players.getValue());

                        if(selections.getValue() == "Race positions")
                        {
                            StaticSystemController.SelectedChartType = 0;
                            series.setName("Positions");
                        }
                        else if(selections.getValue() == "Lap positions")
                        {
                            StaticSystemController.SelectedChartType = 1;
                            series.setName("Lap positions");
                        }
                        else if(selections.getValue() == "Points")
                        {
                            StaticSystemController.SelectedChartType = 2;
                            series.setName("Points");
                        }
                        else if(selections.getValue() == "Lap & race positions")
                        {
                            StaticSystemController.SelectedChartType = 3;
                            series.setName("Race positions");
                            series2.setName("Lap positions");
                        }
                        StaticSystemController.updateViews(false,false);
                    }
                };

        selections.setOnAction(event);


        iVBox vBox = new iVBox();
        vBox.setPadding(new Insets(10,2,2,2));

        NumberAxis xAxis = new NumberAxis();

        NumberAxis yAxis = new NumberAxis();

        if(StaticSystemController.SelectedChartType == 3)
        {
            List<Integer> secondList = player.lapPosList;
            if(secondList != null)
            {
                for (int i = 0; i < secondList.size(); i++)
                {
                    if (secondList.get(i) != 0) series2.getData().add(new XYChart.Data(i, secondList.get(i) * -1));
                }
            }
        }

        LineChart<Number, Number> chart = new LineChart<Number,Number>(xAxis,yAxis);

        xAxis.setTickLabelFill(Color.WHITE);
        yAxis.setTickLabelFill(Color.WHITE);
        xAxis.setTickLabelFont(StaticSceneBuilder.FontSmall);
        yAxis.setTickLabelFont(StaticSceneBuilder.FontSmall);
        yAxis.setTickUnit(2);       //??
        xAxis.setTickUnit(2);
        //chart.setBackground(StaticSceneBuilder.GlassPaneBackground);
        chart.setStyle("-fx-background: #303030; -fx-opacity: 0.7;");

        if(integerList != null)
        {
            if(integerList.size() > 0)
            {
                for (int i = 0; i < integerList.size(); i++)
                {
                    if(StaticSystemController.SelectedChartType == 1 || StaticSystemController.SelectedChartType == 0 || StaticSystemController.SelectedChartType == 3)
                    {
                        if (integerList.get(i) != 0) series.getData().add(new XYChart.Data(i, integerList.get(i) * -1));
                    }
                    else
                    {
                        series.getData().add(new XYChart.Data(i, integerList.get(i)));
                    }
                }
            }
        }

        if(StaticSystemController.SelectedChartType == 1 || StaticSystemController.SelectedChartType == 0 || StaticSystemController.SelectedChartType == 3)
        {
            yAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(yAxis)
            {
                @Override
                public String toString(Number value)
                {
                    return String.format("%7.1f", -value.doubleValue());
                }
            });
        }

        chart.getData().add(series);
        if(StaticSystemController.SelectedChartType == 3) chart.getData().add(series2);
        chart.setPrefHeight(2000);
        //xAxis.setTickUnit(1);
        //chart.setStyle("-fx-text-fill: white; -fx-foreground: white;");

        vBox.getChildren().addAll(selections, chart);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setPrefHeight(2000);
        return vBox;
    }

    final static byte POINTS = 3;
    final static byte POSITIONS = 1;
    final static byte LAPS = 2;
}
