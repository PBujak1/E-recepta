module com.example.erecepta {
    requires javafx.fxml;
    requires java.desktop;
    requires de.jensd.fx.glyphs.fontawesome;
    requires javafx.controls;

    opens com.example.erecepta to javafx.graphics;
        exports com.example.erecepta;
        }
