package org.jcalc.scenebuilders.components;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.jcalc.scenebuilders.StaticSceneBuilder;


public class iSplitPane extends SplitPane
{
    private boolean mouseDragOnDivider = false;
    public iSplitPane(BorderPane componentLeft, Pane componentRight)
    {
        /**
        this.setBorder(StaticSceneBuilder.BorderForPane);
        this.setBackground(StaticSceneBuilder.GlassPaneBackground);
        this.setPadding(new Insets(10, 10, 10, 10));
        this.getItems().addAll(componentLeft, componentRight);

        this.getDividers().get(0).positionProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                StaticSceneBuilder.SplitPanePosition = t1;
            }
        });
        this.getDividers().get(0).setPosition((Double) StaticSceneBuilder.SplitPanePosition);**/
    }

    public static SplitPane getVerticalSplitPane()
    {
        SplitPane splitPane = UIUtils.getDefaultVerticalSplitPane();
        return splitPane;
    }

    public static SplitPane getSplitPaneForGridPanes(GridPane left, GridPane Right)
    {
        SplitPane pane = UIUtils.getDefaultSplitPane();
        pane.getItems().addAll(left, Right);

        return pane;
    }
    public static SplitPane getSplitPaneForGridPanes(GridPane left, ScrollPane Right)
    {
        SplitPane pane = UIUtils.getDefaultSplitPane();
        pane.getItems().addAll(left, Right);

        return pane;
    }
}

