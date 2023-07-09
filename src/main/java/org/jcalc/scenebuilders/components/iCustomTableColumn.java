package org.jcalc.scenebuilders.components;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.TableColumn;

public class iCustomTableColumn extends TableColumn
{
    private SimpleDoubleProperty percentWidth = new SimpleDoubleProperty();
    public iCustomTableColumn(String columnName)
    {
        super(columnName);
    }
    public SimpleDoubleProperty percentWidth()
    {
        return percentWidth;
    }
    public double getPercentWidth()
    {
        return percentWidth.get();
    }
    public void setPercentWidth(double percentWidth)
    {
        this.percentWidth.set(percentWidth);
    }
}
