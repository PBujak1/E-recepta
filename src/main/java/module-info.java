module com.example.erecepta {
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;
    requires org.controlsfx.controls;

    opens com.example.erecepta to javafx.graphics;
        exports com.example.erecepta;
        }
