package org.jcalc.scenebuilders.components;

import javafx.scene.control.ScrollPane;
import org.jcalc.scenebuilders.StaticSceneBuilder;

public class iScrollPane extends ScrollPane
{
    public iScrollPane()
    {
        this.setBorder(StaticSceneBuilder.BorderForPane);
    }
}

