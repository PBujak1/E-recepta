package com.example.erecepta.lekarz;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class mojeRecepty {

    private BorderPane root = new BorderPane();
    private String receptyString;
    private Label titleLabel = new Label("Moje Recepty");
    private Label informationLabel = new Label("Ze względu na rodo wszystkie dane zapisane są za pomocą ID");
    private Label daneLabel = new Label("Dane pokazują poszczególno IDRecepty, Nazwa Leku, IDPacjenta, Ilość opakowań danego leku");
    private Button wyjdz = new Button("Wyjdź");

    public mojeRecepty(String recepty) {
        this.receptyString = recepty;

        root.setPadding(new Insets(50 , 50 , 50 , 50));

        VBox titleBox = new VBox(10);
        Region spacer = new Region();
        titleBox.setAlignment(Pos.TOP_CENTER);
        titleBox.getChildren().addAll(titleLabel, informationLabel,spacer, daneLabel);

        Label receptyLabel = new Label(receptyString);
        receptyLabel.setMaxWidth(Double.MAX_VALUE);

        VBox receptyPane = new VBox(receptyLabel);
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
    }

    public Button getWyjdz() {return wyjdz;}

    public Parent getView() { return root; }
}