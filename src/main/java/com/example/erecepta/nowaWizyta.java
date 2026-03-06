package com.example.erecepta;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class nowaWizyta {

    private ToggleButton stacjonarnieBtn = new ToggleButton("Stacjonarnie");
    private ToggleButton Eporada = new ToggleButton("E-porada");

    private Button testButton = new Button("Test");
    private Button exitButton = new Button("Exit");

    public void start(Stage primaryStage){
        VBox root = new VBox();
        root.setAlignment(Pos.TOP_CENTER);
        root.getStyleClass().add("main-panel");

        HBox selection = new HBox();
        selection.getStyleClass().add("selection");
        selection.setAlignment(Pos.CENTER);

        ToggleGroup toggleGroup = new ToggleGroup();
        stacjonarnieBtn.setToggleGroup(toggleGroup);
        Eporada.setToggleGroup(toggleGroup);
        stacjonarnieBtn.setMaxWidth(Double.MAX_VALUE);
        Eporada.setMaxWidth(Double.MAX_VALUE);
        stacjonarnieBtn.setSelected(true);
        HBox.setHgrow(stacjonarnieBtn, Priority.ALWAYS);
        HBox.setHgrow(Eporada, Priority.ALWAYS);
        selection.getChildren().addAll(stacjonarnieBtn, Eporada);

        root.getChildren().addAll(
                selection, new Separator(),
                testButton,
                exitButton
        );

        Scene scene = new Scene(root, 1300, 780);
        scene.getStylesheets().add(
                getClass().getResource("/css/mainPacPanels/nowaWizyta.css").toExternalForm()
        );

        primaryStage.setTitle("E-Recepta");
        primaryStage.setScene(scene);
        primaryStage.show();
    };

    public Button getWyjdzButton() {
        return exitButton;
    }
}
