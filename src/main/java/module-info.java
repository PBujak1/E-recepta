module com.example.erecepta {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires de.jensd.fx.glyphs.fontawesome;
    requires org.controlsfx.controls;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;

    // Pozwalamy JavaFX (w tym FXML) na dostęp do Twoich klas
    opens com.example.erecepta to javafx.fxml, javafx.graphics;
    opens com.example.erecepta.pacjent to javafx.fxml, javafx.graphics;
    opens com.example.erecepta.lekarz to javafx.fxml, javafx.graphics;

    // Eksportujemy pakiety, żeby były widoczne w projekcie
    exports com.example.erecepta;
    exports com.example.erecepta.pacjent;
    exports com.example.erecepta.lekarz;
    exports com.example.erecepta.backend;
    opens com.example.erecepta.backend to javafx.fxml, javafx.graphics;
}