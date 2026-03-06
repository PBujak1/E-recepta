package com.example.erecepta;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class nowaWizyta {

    private FontAwesomeIconView searchIcon = new FontAwesomeIconView(FontAwesomeIcon.SEARCH);
    private FontAwesomeIconView iconUser = new FontAwesomeIconView(FontAwesomeIcon.USER_CIRCLE);

    private ToggleButton stacjonarnieBtn = new ToggleButton("Stacjonarnie");
    private ToggleButton Eporada = new ToggleButton("E-porada");

    private TextField searchField = new TextField();

    private Button testButton = new Button("Test");
    private Button exitButton = new Button("Exit");

    public void start(Stage primaryStage){
        VBox root = new VBox();
        root.setAlignment(Pos.TOP_CENTER);
        root.getStyleClass().add("main-panel");

        HBox topBox = new HBox(10);
        topBox.setAlignment(Pos.CENTER);
        topBox.getStyleClass().add("top-panel");
        topBox.getChildren().addAll(searchIcon, searchField);

        HBox.setHgrow(searchField, Priority.ALWAYS);

        iconUser.setGlyphSize(30);
        iconUser.setStyleClass("my-user-icon");
        searchIcon.setGlyphSize(30);
        searchIcon.setStyleClass("my-search-icon");

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
        selection.getChildren().addAll(iconUser, stacjonarnieBtn, Eporada);

        root.getChildren().addAll(
                topBox, new Separator(),
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
