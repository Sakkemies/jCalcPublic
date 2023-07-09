package org.jcalc.scenebuilders.components;

import javafx.scene.control.CheckBox;
import org.jcalc.dto.SuperDTO;
import org.jcalc.handlers.StaticSystemController;
import org.jcalc.scenebuilders.StaticSceneBuilder;

public class iCheckBox extends CheckBox
{
    public iCheckBox()
    {
        UIUtils.setICheckBoxDefaultStyle(this);
    }
    public void setActionSecondaryScores()
    {
        setDisable(true);
        for(SuperDTO superdto: StaticSystemController.SuperDTOlist)
        {
            if(superdto.getProgId()==StaticSystemController.selectedId)
            {
                setSelected(superdto.getSecondScoresOn());
                if(StaticSystemController.UseCustomPoints)
                {
                    setDisable(false);
                }
            }
        }

        setOnAction(actionEvent -> {
            for(SuperDTO superDTO: StaticSystemController.SuperDTOlist)
            {
                if(superDTO.getProgId()==StaticSystemController.selectedId)
                {
                    if (superDTO.getSecondScoresOn())
                    {
                        superDTO.setSecondScoresOn(false);
                    }
                    else
                    {
                        superDTO.setSecondScoresOn(true);
                    }

                    setSelected(superDTO.getSecondScoresOn());
                }
            }
        });
    }
}

