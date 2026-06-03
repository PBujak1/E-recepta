package com.example.erecepta.pacjent;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class nadchodzaceWizyty {

    private BorderPane root = new BorderPane();
    private String historiaString;
    private Label titleLabel = new Label("Nadchodzące Wizyty");
    private Label informationLabel = new Label("Ze względu na rodo wszystkie dane zapisane są za pomocą ID");
    private Label daneLabel = new Label("Dane pokazują poszczególno IDWizyty, Datę wizyty, IDLekarza, IDPacjenta, IDRecepty");
    private Button wyjdz = new Button("Wyjdź");

    public nadchodzaceWizyty(String historia) {

        this.historiaString = historia;

        root.setPadding(new Insets(50 , 50 , 50 , 50));

        VBox titleBox = new VBox(10);
        Region spacer = new Region();
        titleBox.setAlignment(Pos.TOP_CENTER);
        titleBox.getChildren().addAll(titleLabel, informationLabel,spacer, daneLabel);

        VBox receptyPane = new VBox(new Label(historiaString));
        receptyPane.setAlignment(Pos.TOP_CENTER);

        ScrollPane contentPane = new ScrollPane();
        contentPane.setFitToWidth(true);
        VBox.setVgrow(contentPane, Priority.ALWAYS);
        contentPane.setMaxHeight(Double.MAX_VALUE);

        contentPane.setContent(receptyPane);
        VBox bottomPane = new VBox();
        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.getChildren().addAll(wyjdz);

        root.setCenter(contentPane);
        root.setTop(titleBox);
        root.setBottom(bottomPane);

        contentPane.getStyleClass().add("historiaPac-main-panel-content");
        titleLabel.getStyleClass().add("historiaPac-titleLabel");
        informationLabel.getStyleClass().add("historiaPac-informationLabel");
        daneLabel.getStyleClass().add("historiaPac-daneLabel");
        wyjdz.getStyleClass().add("historiaPac-exit-btn");
    }

    public Button getWyjdz() {return wyjdz;}

    public Parent getView() { return root; }
}
