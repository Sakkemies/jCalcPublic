package org.jcalc.scenebuilders.components;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.jcalc.dto.PlayerDTO;
import org.jcalc.handlers.StaticJsonHandler;
import org.jcalc.handlers.StaticSystemController;
import org.jcalc.scenebuilders.StaticSceneBuilder;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class iTableForResults
{
    public List<DTO> dtoList = new ArrayList<>();
    public List<PlayerDTO> playerDTOS = new ArrayList<>();
    private int cellwidth = 200;

    public iTableForResults()
    {
        //createList();
    }

    public TableView<DTO> getTableForResults()
    {
        TableView<DTO> table = new TableView<>();
        getStyle(table);

        table = getResults();

        return table;
    }
    public static void getStyle(TableView<?> table)
    {
        table.setBorder(StaticSceneBuilder.BorderForPane);
        table.setOpacity(0.5);

        table.setMaxSize(500,500);
    }

    public TableView<DTO> getResults()
    {
        TableView<DTO> table = updateTable();

        return table;
    }

    public TableView<DTO> updateTable()
    {
        /**for(project.DTO dto: dtoList)
         {
         //listOfDTOs.add(dto);
         System.out.println(dto.getClanString());
         System.out.println(dto.getNameString());
         System.out.println(dto.getIdString());
         System.out.println(dto.getLapString());
         System.out.println(dto.getPointString());
         System.out.println(dto.getRankString());
         }**/

        TableView<DTO> table = new TableView<>();
        //table.isEditable();

        iCustomTableColumn playerId = new iCustomTableColumn("Player ID");
        playerId.setCellValueFactory(new PropertyValueFactory<DTO,String>("idString"));
        playerId.setPrefWidth(cellwidth );

        iCustomTableColumn name = new iCustomTableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<DTO,String>("nameString"));
        name.setPrefWidth(cellwidth );

        iCustomTableColumn clan = new iCustomTableColumn("Clan");
        clan.setCellValueFactory(new PropertyValueFactory<DTO,String>("clanString"));
        clan.setPrefWidth(cellwidth );

        iCustomTableColumn lapResults = new iCustomTableColumn("Lap results");
        lapResults.setCellValueFactory(new PropertyValueFactory<DTO,String>("lapString"));
        lapResults.setPrefWidth(cellwidth );

        iCustomTableColumn raceResults = new iCustomTableColumn("Race results");
        raceResults.setCellValueFactory(new PropertyValueFactory<DTO,String>("rankString"));
        raceResults.setPrefWidth(cellwidth );

        iCustomTableColumn racePoints = new iCustomTableColumn("Race points");
        racePoints.setCellValueFactory(new PropertyValueFactory<DTO,String>("pointString"));
        racePoints.setPrefWidth(cellwidth );

        iCustomTableColumn totalPoints = new iCustomTableColumn("Total points");
        totalPoints.setCellValueFactory(new PropertyValueFactory<DTO,Integer>("totalPoints"));
        totalPoints.setPrefWidth(cellwidth );

        iCustomTableColumn teamPoints = new iCustomTableColumn("Team points");
        teamPoints.setCellValueFactory(new PropertyValueFactory<DTO,Integer>("teamPoints"));
        teamPoints.setPrefWidth(cellwidth );

        iCustomTableColumn teamTotalPoints = new iCustomTableColumn("Total team points");
        teamTotalPoints.setCellValueFactory(new PropertyValueFactory<DTO,Integer>("teamTotalPoints"));
        teamTotalPoints.setPrefWidth(cellwidth );

        ObservableList<DTO> data = FXCollections.observableArrayList(dtoList);
        //System.out.println(data);
        table.getColumns().addAll(playerId,name,clan,lapResults,raceResults,racePoints,teamPoints,totalPoints,teamTotalPoints);
        table.setItems(data);

        FXCollections.emptyObservableList();

        return table;
    }
    public void createList()
    {
        playerDTOS = StaticJsonHandler.getPlayerList();

        dtoList.clear();

        try {
            if (playerDTOS != null) {
                if (!StaticSystemController.UseCustomPoints) {
                    for (PlayerDTO dto : playerDTOS) {
                        dtoList.add(new DTO(new SimpleStringProperty(Long.toString(dto.getID())), new SimpleStringProperty(dto.getName()),
                                new SimpleStringProperty(dto.getClan()), new SimpleStringProperty(dto.getRank().toString()),
                                new SimpleStringProperty(dto.getPoints().toString()), new SimpleStringProperty(dto.getLapRank().toString()), new SimpleIntegerProperty(dto.getTotalPoints()), new SimpleStringProperty(dto.getTeamPoints().toString()), new SimpleIntegerProperty(dto.getTotalTeamPoints())));
                    }

                }
                else
                {
                    for (PlayerDTO dto : playerDTOS)
                    {
                        dtoList.add(new DTO(new SimpleStringProperty(Long.toString(dto.getID())), new SimpleStringProperty(dto.getName()),
                                new SimpleStringProperty(dto.getClan()), new SimpleStringProperty(dto.getRank().toString()),
                                new SimpleStringProperty(dto.getPoints().toString()), new SimpleStringProperty(dto.getLapRank().toString()), new SimpleIntegerProperty(dto.getCustomTotalPoints()), new SimpleStringProperty(dto.getTeamPoints().toString()), new SimpleIntegerProperty(dto.getTotalTeamPoints())));
                    }
                }
            } else {
                System.out.println("Cannot convert event to table");
            }
        }
        catch (ConcurrentModificationException e)
        {
            System.out.println("Something went wrong");
        }

    }
    public static class DTO
    {
        private SimpleStringProperty idString;
        private SimpleStringProperty nameString;
        private SimpleStringProperty clanString;
        private SimpleStringProperty rankString;
        private SimpleStringProperty pointString;
        private SimpleStringProperty lapString;
        private SimpleIntegerProperty totalPoints;
        private SimpleStringProperty teamPoints;
        private SimpleIntegerProperty teamTotalPoints;

        public DTO(SimpleStringProperty id, SimpleStringProperty name, SimpleStringProperty clan, SimpleStringProperty rank, SimpleStringProperty point, SimpleStringProperty lap, SimpleIntegerProperty total, SimpleStringProperty teamPoints,SimpleIntegerProperty totalTeamPoints)
        {
            this.idString = id;
            this.nameString = name;
            this.clanString = clan;
            this.rankString = rank;
            this.pointString = point;
            this.lapString = lap;
            this.totalPoints = total;
            this.teamPoints = teamPoints;
            this.teamTotalPoints = totalTeamPoints;
        }
        public int getTotalPoints()
        {
            return totalPoints.get();
        }
        public void setTotalPoints(int fTotalPoints)
        {
            totalPoints.set(fTotalPoints);
        }
        public int getTeamTotalPoints()
        {
            return teamTotalPoints.get();
        }
        public void setTeamTotalPoints(int fTotalPoints)
        {
            teamTotalPoints.set(fTotalPoints);
        }

        public String getTeamPoints()
        {
            return teamPoints.get();
        }
        public void setTeamPoints(String fTeamPoints)
        {
            teamPoints.set(fTeamPoints);
        }
        public String getIdString()
        {
            return idString.get();
        }
        public void setIdString(String fIdString)
        {
            idString.set(fIdString);
        }
        public String getNameString()
        {
            return nameString.get();
        }
        public void setNameString(String fNameString)
        {
            nameString.set(fNameString);
        }
        public String getClanString()
        {
            return clanString.get();
        }
        public void setClanString(String fClanString)
        {
            clanString.set(fClanString);
        }
        public String getRankString()
        {
            return rankString.get();
        }
        public void setRankString(String fRankString)
        {
            rankString.set(fRankString);
        }
        public String getPointString()
        {
            return pointString.get();
        }
        public void setPointString(String fPointString)
        {
            pointString.set(fPointString);
        }
        public String getLapString()
        {
            return lapString.get();
        }
        public void setLapString(String fLapString)
        {
            lapString.set(fLapString);
        }

    }
}

