package org.jcalc.scenebuilders;


import javafx.stage.Screen;
import org.jcalc.handlers.StaticSystemController;

public class SceneController
{
    public static double correction = 2;

    public void switchToMainScene()
    {
        //double h = StaticSystemController.MainStage.getHeight();
        //double w = StaticSystemController.MainStage.getWidth();

        //StaticSystemController.MainScene = StaticSceneBuilder.CreateMainScene();
        //StaticSystemController.MainStage.setScene(StaticSystemController.MainScene);
        StaticSystemController.MainStage.getProperties().clear();
        StaticSystemController.MainStage.setScene(StaticSceneBuilder.CreateMainScene());

        StaticSystemController.MainScene = StaticSystemController.MainStage.getScene();
        //StaticSystemController.MainStage.setHeight(h);
        //StaticSystemController.MainStage.setWidth(w);
        StaticSystemController.MainStage.show();
       // StaticSystemController.updateViews(false,true);
        setCorrection();
    }
    public void switchToTableScene()
    {
        //double h = StaticSystemController.MainStage.getHeight();
        //double w = StaticSystemController.MainStage.getWidth();

        //StaticSystemController.MainStage.setScene(StaticSystemController.TableScene);
        StaticSystemController.MainStage.getProperties().clear();
        StaticSystemController.MainStage.setScene(StaticSceneBuilder.CreateTableScene());

        StaticSystemController.MainScene = StaticSystemController.MainStage.getScene();
        //StaticSystemController.MainStage.setHeight(h);
        //StaticSystemController.MainStage.setWidth(w);
        StaticSystemController.MainStage.show();
       // StaticSystemController.updateViews(false,true);
        setCorrection();
    }

    public static void switchToLoadingScene()
    {
        //double h = StaticSystemController.MainStage.getHeight();
        //double w = StaticSystemController.MainStage.getWidth();

        StaticSystemController.MainStage.getProperties().clear();
        StaticSystemController.MainStage.setScene(StaticSystemController.LoadingScene);

        StaticSystemController.MainScene = StaticSystemController.MainStage.getScene();
        //StaticSystemController.MainStage.setHeight(h);
        //StaticSystemController.MainStage.setWidth(w);
        StaticSystemController.MainStage.show();
        //StaticSystemController.updateViews(false,true);
        new SceneController().setCorrection();
    }
    public void switchToObjectScene()
    {
        //double h = StaticSystemController.MainStage.getHeight();
        //double w = StaticSystemController.MainStage.getWidth();

        //StaticSystemController.ObjectScene = StaticSceneBuilder.CreateObjectScene();
        //StaticSystemController.MainStage.setScene(StaticSystemController.ObjectScene);
        StaticSystemController.MainStage.getProperties().clear();
        StaticSystemController.MainStage.setScene(StaticSceneBuilder.CreateObjectScene());


        StaticSystemController.MainScene = StaticSystemController.MainStage.getScene();
        //StaticSystemController.MainStage.setHeight(h);
        //StaticSystemController.MainStage.setWidth(w);
        StaticSystemController.MainStage.show();

        //StaticSystemController.updateViews(false,true);
        //StaticSystemController.MainStage.setFullScreen(true);
        setCorrection();
    }

    public static void SwitchScene(int num)
    {
        StaticSystemController.SceneControl += num;

        if (StaticSystemController.SceneControl < 0)
        {
            StaticSystemController.SceneControl = StaticSystemController.MaxScenes;
        }

        if (StaticSystemController.SceneControl > StaticSystemController.MaxScenes)
        {
            StaticSystemController.SceneControl = 0;
        }

        SceneController.SwitchScene();
    }
    public static void SwitchScene(int num, boolean isExact)
    {
        if(isExact)
        {
            StaticSystemController.SceneControl = num;
            SceneController.SwitchScene();
        }
        else
        {
            SceneController.SwitchScene(num);
        }

    }

    public static void setCorrection()
    {
        //StaticSystemController.MainStage.setWidth(StaticSystemController.MainStage.getWidth() + correction/2);
        //StaticSystemController.MainStage.setHeight(StaticSystemController.MainStage.getHeight() + correction);

        if(StaticSystemController.screenStretcher) {
            if (!StaticSystemController.MainStage.isMaximized()) {
                StaticSystemController.MainStage.setWidth(StaticSystemController.MainStage.getWidth() - correction / 2);
                StaticSystemController.MainStage.setHeight(StaticSystemController.MainStage.getHeight() - correction);
            } else {
                StaticSystemController.MainStage.setMaximized(false);
                int height = (int) Screen.getScreens().get(0).getBounds().getHeight() - 50;
                int width = (int) Screen.getScreens().get(0).getBounds().getWidth();

                StaticSystemController.MainStage.setWidth(width - correction / 2);
                StaticSystemController.MainStage.setHeight(height - correction);
                StaticSystemController.MainStage.centerOnScreen();
            }

            correction *= -1;
        }
    }
    public static void SwitchScene()
    {
        switch(StaticSystemController.SceneControl)
        {
            case 0:
                System.out.println("Scene project");
                new SceneController().switchToMainScene();
                break;
            case 1:
                System.out.println("Scene Table");
                new SceneController().switchToTableScene();
                break;
            case 2:
                System.out.println("Scene Event");
                new SceneController().switchToObjectScene();
                break;
            default:
                break;
        }
    }
}
