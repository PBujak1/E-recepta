package com.example.erecepta;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ustawieniaLek {

    private Label imie;
    private Label nazwisko;
    private Label PESEL;
    private Label adres;
    private Label telefon;
    private Label email;
    private Label wiek;
    private Label plec;

    private TextField imieField = new TextField();
    private TextField nazwiskoField = new TextField();
    private TextField peselField = new TextField();
    private TextField adresField = new TextField();
    private TextField telefonField = new TextField();
    private TextField emailField = new TextField();
    private TextField wiekField = new TextField();
    private TextField plecField = new TextField();

    ustawieniaLek(String imie, String nazwisko, String PESEL, String adres, String telefon, String email, String wiek, String plec) {
        this.imie = new Label(imie);
        this.nazwisko = new Label(nazwisko);
        this.PESEL = new Label(PESEL);
        this.adres = new Label(adres);
        this.telefon = new Label(telefon);
        this.email = new Label(email);
        this.wiek = new Label(wiek);
        this.plec = new Label(plec);
    }

    public void start(Stage primaryStage) {
        VBox root = new VBox();

        GridPane mainPane = new GridPane();
        mainPane.getStyleClass().add("main-pane");
        mainPane.add(imie, 0, 0);
        mainPane.add(nazwisko, 0, 1);
        mainPane.add(PESEL, 0, 2);
        mainPane.add(adres, 0, 3);
        mainPane.add(telefon, 0, 4);
        mainPane.add(email, 0, 5);
        mainPane.add(wiek, 0, 6);
        mainPane.add(plec, 0, 7);
        mainPane.add(imieField, 1, 0);
        mainPane.add(nazwiskoField, 1, 1);
        mainPane.add(peselField, 1, 2);
        mainPane.add(adresField, 1, 3);
        mainPane.add(telefonField, 1, 4);
        mainPane.add(emailField, 1, 5);
        mainPane.add(wiekField, 1, 6);
        mainPane.add(plecField, 1, 7);

        root.getChildren().addAll(
                mainPane
        );
        Scene scene = new Scene(root, 1300, 780);
        scene.getStylesheets().add(
                getClass().getResource("/css/mainPanels/ustawieniaLek.css").toExternalForm()
        );

        primaryStage.setTitle("E-Recepta");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
