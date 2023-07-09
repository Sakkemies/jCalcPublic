module org.jcalc {
    requires javafx.controls;
    requires com.google.gson;
    opens org.jcalc.dto to com.google.gson;
    opens org.jcalc.scenebuilders.components to javafx.base;

    exports org.jcalc;
}