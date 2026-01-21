package com.example.erecepta;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class historiaPac {

    private String historiaString;
    private Label titleLabel = new Label("Moja Historia Wizyt");
    private Label informationLabel = new Label("Ze względu na rodo wszystkie dane zapisane są za pomocą ID");
    private Label daneLabel = new Label("Dane pokazują poszczególno IDWizyty, Datę wizyty, IDLekarza, IDPacjenta, IDRecepty");
    private Button wyjdz = new Button("Wyjdź");

    historiaPac(String historia) {
        this.historiaString = historia;
    }

    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
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
        contentPane.getStyleClass().add("main-panel-content");
        contentPane.setContent(receptyPane);

        VBox bottomPane = new VBox();
        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.getChildren().addAll(wyjdz);

        root.setCenter(contentPane);
        root.setTop(titleBox);
        root.setBottom(bottomPane);

        titleLabel.getStyleClass().add("titleLabel");
        informationLabel.getStyleClass().add("informationLabel");
        daneLabel.getStyleClass().add("daneLabel");
        wyjdz.getStyleClass().add("exit-btn");

        Scene scene = new Scene(root, 1300, 780);
        scene.getStylesheets().add(
                getClass().getResource("/css/mainPanels/mojaHistoria.css").toExternalForm()
        );

        primaryStage.setTitle("E-Recepta");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Button getWyjdz() {return wyjdz;}
}
