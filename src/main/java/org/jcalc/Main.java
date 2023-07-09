package org.jcalc;

import javafx.application.Application;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.jcalc.handlers.StaticFileLoader;
import org.jcalc.handlers.StaticJsonHandler;
import org.jcalc.handlers.StaticSystemController;
import org.jcalc.scenebuilders.StaticSceneBuilder;

public class Main extends Application
{

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        int height = (int) Screen.getScreens().get(0).getBounds().getHeight();
        int width = (int) Screen.getScreens().get(0).getBounds().getWidth();
        StaticSceneBuilder.SceneWidth = (int) (width / 1.2);
        StaticSceneBuilder.SceneHeight = (int) (height / 1.2);

        StaticSceneBuilder.dialogMaxWidth = (int) (width / 1.5);
        StaticSceneBuilder.dialogMaxWidthHeight = (int) (height / 1.5);

        StaticFileLoader.LoadSettings();
        StaticJsonHandler.InitializeLists();
        //StaticJsonHandler.LoadRawJsonFile(null,false);
        StaticSystemController.SceneControl = 0;

        StaticSystemController.MainStage = stage;
        stage.setTitle(StaticSystemController.Name + " " + StaticSystemController.Version);
        stage.setScene(StaticSystemController.MainScene);
        stage.show();

        StaticSystemController.loadOtherComponents();
    }
}