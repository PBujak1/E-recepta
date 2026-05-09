package com.example.erecepta.lekarz;

import com.example.erecepta.ServerConnection;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class nowaRecepta {

    private VBox root = new VBox(15);
    private String imie;
    private String nazwisko;
    private String PESEL;
    private String PESELLek;

    private Label pacjentLabel = new Label("Pacjent");

    private Label lekLabel = new Label("LEK");
    private TextField nazwaLeku = new TextField();
    private Label liczbaOpakowanLabel = new Label("LICZBA OPAKOWAŃ");
    private TextField liczbaOpakowan = new TextField();
    private Label odplatnoscLabel = new Label("ODPŁATNOŚĆ");
    private TextField odplatnosc = new TextField();



    private static Button wyjdz = new Button("Wyjdz");
    private Button dodaj = new Button("Dodaj");

    public nowaRecepta(String imie, String nazwisko, String PESEL, String PESELLek) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.PESEL = PESEL;
        this.PESELLek = PESELLek;

        root.setPadding(new Insets(20, 10, 20, 10));

        VBox titlePane = new VBox(10);
        titlePane.getStyleClass().add("title-pane");
        Label nazwaPacjenta = new Label(imie + " " + nazwisko + " (" + PESEL + ") ");
        nazwaPacjenta.getStyleClass().add("nazwa-pacjenta");
        titlePane.getChildren().addAll(
                pacjentLabel, new Separator(),
                nazwaPacjenta
        );

        HBox dolnyPanel = new HBox(600);
        dolnyPanel.setAlignment(Pos.CENTER);
        dolnyPanel.getStyleClass().add("dolnyPanel");
        //Przycisk
        HBox wyjdzHbox = new HBox();
        wyjdzHbox.setAlignment(Pos.CENTER);
        wyjdz.getStyleClass().add("exit-btn");
        wyjdzHbox.getChildren().addAll(wyjdz);
        //napis do resetowania
        HBox resetujHbox = new HBox();
        resetujHbox.setAlignment(Pos.CENTER);
        dodaj.getStyleClass().add("dalej-btn");
        resetujHbox.getChildren().add(dodaj);
        dolnyPanel.getChildren().addAll(resetujHbox, wyjdzHbox);

        Region spacer = new Region();

        VBox mainPane = new VBox(15);
        mainPane.setPadding(new Insets(10, 5, 10, 5));
        mainPane.getStyleClass().add("main-pane");
        VBox.setVgrow(liczbaOpakowan, Priority.ALWAYS);
        VBox.setVgrow(liczbaOpakowan, Priority.ALWAYS);
        mainPane.getChildren().addAll(
                lekLabel,
                nazwaLeku,
                liczbaOpakowanLabel,
                liczbaOpakowan,
                odplatnoscLabel,
                odplatnosc, spacer,
                dolnyPanel
        );

        root.getChildren().addAll(
                titlePane,
                mainPane
        );
    }

    public static Button getWyjdzBtn() {
        return wyjdz;
    }

    public Parent getView() { return root; }
    public Button getDodaj() { return dodaj; }

    public TextField getNazwaLeku() { return nazwaLeku; }
    public TextField getLiczbaOpakowan() { return liczbaOpakowan; }
    public TextField getOdplatnosc() { return odplatnosc; }
}