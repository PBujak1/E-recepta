package com.example.erecepta.pacjent;

import com.example.erecepta.backend.dto.WizytyResponse;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;

public class nadchodzaceWizyty {

    private BorderPane root = new BorderPane();


    private List<Label> dataWizyty  = new ArrayList<>();
    private List<Label> nazwaLekarza =  new ArrayList<>();
    private List<Label> nazwaPacjenta  = new ArrayList<>();
    private List<Label> nrRecepty = new ArrayList<>();

    private Label titleLabel = new Label("Nadchodzące Wizyty");
    private Button wyjdz = new Button("Wyjdź");

    public nadchodzaceWizyty(List<WizytyResponse> response) {

        for (int i = 0; i < response.size(); i++) {
            dataWizyty.add(new Label(response.get(i).getDataWizyty()));
            nazwaLekarza.add(new Label(response.get(i).getNazwaLekarza()));
            nazwaPacjenta.add(new Label(response.get(i).getNazwaPacjenta()));
            nrRecepty.add(new Label(response.get(i).getNrRecepty()));
        }

        root.setPadding(new Insets(50 , 50 , 50 , 50));

        VBox titleBox = new VBox(10);
        Region spacer = new Region();
        titleBox.setAlignment(Pos.TOP_CENTER);
        titleBox.getChildren().addAll(titleLabel,spacer);

        GridPane receptyPane = new GridPane();
        //receptyPane.setPadding(new Insets(30));
        receptyPane.gridLinesVisibleProperty().set(true);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(30);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(30);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(30);
        ColumnConstraints col4 = new ColumnConstraints();
        col4.setPercentWidth(10);
        receptyPane.getColumnConstraints().addAll(col1, col2, col3, col4);

        for (int i = 0; i < response.size(); i++) {

            receptyPane.add(dataWizyty.get(i), 0, i);
            GridPane.setMargin(dataWizyty.get(i), new Insets(30));

            receptyPane.add(nazwaLekarza.get(i), 1, i);
            GridPane.setMargin(nazwaLekarza.get(i), new Insets(30));

            receptyPane.add(nazwaPacjenta.get(i), 2, i);
            GridPane.setMargin(nazwaPacjenta.get(i), new Insets(30));

            receptyPane.add(nrRecepty.get(i), 3, i);
            GridPane.setMargin(nrRecepty.get(i), new Insets(30));
        }

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
