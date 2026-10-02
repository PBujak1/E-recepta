package com.example.erecepta.pacjent;

import com.example.erecepta.backend.dto.DawkowanieResponse;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;

public class dawkowanie {

    private BorderPane root = new BorderPane();
    private List<Label> nazwaLeku = new ArrayList<>();
    private List<Integer> liczbaOpakowan = new ArrayList<>();
    private List<Integer> dawkowanie = new ArrayList<>();
    private Label titleLabel = new Label("Dawkowanie leków");
    private Button wyjdz = new Button("Wyjdź");

    public dawkowanie(List<DawkowanieResponse> response) {

        for (int i = 0; i < response.size(); i++) {
            nazwaLeku.add(new Label(response.get(i).getNazwaLeku()));
            liczbaOpakowan.add(response.get(i).getLiczbaOpakowan());
            dawkowanie.add(response.get(i).getDawkowanie());
        }

        root.setPadding(new Insets(50 , 50 , 50 , 50));

        VBox titleBox = new VBox(10);
        Region spacer = new Region();
        titleBox.setAlignment(Pos.TOP_CENTER);
        titleBox.getChildren().addAll(titleLabel, spacer);

        GridPane receptyPane = new GridPane();
        receptyPane.setPadding(new Insets(30));
        receptyPane.gridLinesVisibleProperty().set(true);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(25);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(25);
        receptyPane.getColumnConstraints().addAll(col1, col2, col3);

        for (int i = 0; i < response.size(); i++) {

            receptyPane.add(nazwaLeku.get(i), 0, i);
            GridPane.setMargin(nazwaLeku.get(i), new Insets(30));

            Label opakowania = new Label(liczbaOpakowan.get(i).toString());
            receptyPane.add(opakowania, 1, i);
            GridPane.setMargin(opakowania, new Insets(30));

            Label dawkowanieLabel = new Label(dawkowanie.get(i).toString());
            receptyPane.add(dawkowanieLabel, 2, i);
            GridPane.setMargin(dawkowanieLabel, new Insets(30));
        }

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

        receptyPane.getStyleClass().add("historiaPac-gridPane");
        contentPane.getStyleClass().add("historiaPac-main-panel-content");
        titleLabel.getStyleClass().add("historiaPac-titleLabel");
        wyjdz.getStyleClass().add("historiaPac-exit-btn");
    }

    public Button getWyjdz() {return wyjdz;}

    public Parent getView() { return root; }
}
