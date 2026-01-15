package com.example.erecepta;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ustawieniaLek {

    private Label imie;
    private Label nazwisko;
    private Label PESEL;
    private String PESELString;
    private String nazwiskoString;
    private Label adres;
    private Label telefon;
    private Label email;
    private Label wiek;
    private Label plec;
    private Label stareImieLabel = new Label("Stare Imie:");
    private Label stareNazwiskoLabel = new Label("Stare Nazwisko:");
    private Label staryPeselLabel = new Label("Stary Pesel:");
    private Label staryAdresLabel = new Label("Stary Adres:");
    private Label staryTelefonLabel = new Label("Stary Telefon:");
    private Label staryEmailLabel = new Label("Stary Email:");
    private Label staryWiekLabel = new Label("Stary Wiek:");
    private Label staraPlecLabel = new Label("Stara Płeć:");

    private Label noweImieLabel = new Label("nowe Imie:");
    private Label noweNazwiskoLabel = new Label("nowe Nazwisko:");
    private Label nowyPeselLabel = new Label("nowy Pesel:");
    private Label nowyAdresLabel = new Label("nowy Adres:");
    private Label nowyTelefonLabel = new Label("nowy Telefon:");
    private Label nowyEmailLabel = new Label("nowy Email:");
    private Label nowyWiekLabel = new Label("nowy Wiek:");
    private Label nowaPlecLabel = new Label("nowa Płeć:");

    private TextField imieField = new TextField();
    private TextField nazwiskoField = new TextField();
    private TextField peselField = new TextField();
    private TextField adresField = new TextField();
    private TextField telefonField = new TextField();
    private TextField emailField = new TextField();
    private TextField wiekField = new TextField();
    private TextField plecField = new TextField();

    private String noweImie;
    private String noweNazwisko;
    private String nowyPesel;
    private String nowyAdres;
    private String nowyTelefon;
    private String nowyEmail;
    private String nowyWiek;
    private String nowaPlec;

    private Button wyjdz = new Button("Wyjdz");
    private Button akceptuj = new Button("Akceptuj");

    private Label title = new Label("Ustawienia");

    ustawieniaLek(String imie, String nazwisko, String PESEL, String adres, String telefon, String email, String wiek, String plec) {
        this.imie = new Label(imie);
        this.nazwisko = new Label(nazwisko);
        this.PESEL = new Label(PESEL);
        this.PESELString = PESEL;
        this.nazwiskoString = nazwisko;
        this.adres = new Label(adres);
        this.telefon = new Label(telefon);
        this.email = new Label(email);
        this.wiek = new Label(wiek);
        this.plec = new Label(plec);
    }

    public void start(Stage primaryStage) {
        VBox root = new VBox();
        root.setPadding(new Insets(10, 10, 10, 10));

        VBox titlePane = new VBox();
        titlePane.getStyleClass().add("title-pane");
        titlePane.setAlignment(Pos.TOP_CENTER);
        titlePane.getChildren().add(title);

        VBox mainPane = new VBox(50);
        mainPane.getStyleClass().add("main-pane");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(15);
        gridPane.setVgap(15);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHalignment(HPos.RIGHT);
        col1.setPercentWidth(15);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHalignment(HPos.LEFT);
        col2.setPercentWidth(20);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setHalignment(HPos.RIGHT);
        col3.setPercentWidth(20);
        ColumnConstraints col4 = new ColumnConstraints();
        col4.setHalignment(HPos.RIGHT);
        col4.setHgrow(Priority.ALWAYS);
        col4.setFillWidth(true);
        gridPane.getColumnConstraints().addAll(col1, col2, col3, col4);

        gridPane.add(stareImieLabel, 0, 0);
        gridPane.add(stareNazwiskoLabel, 0, 1);
        gridPane.add(staryPeselLabel, 0, 2);
        gridPane.add(staryAdresLabel, 0, 3);
        gridPane.add(staryTelefonLabel, 0, 4);
        gridPane.add(staryEmailLabel, 0, 5);
        gridPane.add(staryWiekLabel, 0, 6);
        gridPane.add(staraPlecLabel, 0, 7);

        gridPane.add(imie, 1, 0);
        gridPane.add(nazwisko, 1, 1);
        gridPane.add(PESEL, 1, 2);
        gridPane.add(adres, 1, 3);
        gridPane.add(telefon, 1, 4);
        gridPane.add(email, 1, 5);
        gridPane.add(wiek, 1, 6);
        gridPane.add(plec, 1, 7);

        gridPane.add(noweImieLabel, 2, 0);
        gridPane.add(noweNazwiskoLabel, 2, 1);
        gridPane.add(nowyPeselLabel, 2, 2);
        gridPane.add(nowyAdresLabel, 2, 3);
        gridPane.add(nowyTelefonLabel, 2, 4);
        gridPane.add(nowyEmailLabel, 2, 5);
        gridPane.add(nowyWiekLabel, 2, 6);
        gridPane.add(nowaPlecLabel, 2, 7);

        gridPane.add(imieField, 3, 0);
        gridPane.add(nazwiskoField, 3, 1);
        gridPane.add(peselField, 3, 2);
        gridPane.add(adresField, 3, 3);
        gridPane.add(telefonField, 3, 4);
        gridPane.add(emailField, 3, 5);
        gridPane.add(wiekField, 3, 6);
        gridPane.add(plecField, 3, 7);

        HBox dolnyPanel = new HBox(600);
        dolnyPanel.setAlignment(Pos.CENTER);
        dolnyPanel.getStyleClass().add("dolnyPanel");
        HBox wyjdzHbox = new HBox();
        wyjdzHbox.setAlignment(Pos.CENTER);
        wyjdz.getStyleClass().add("exit-btn");
        wyjdzHbox.getChildren().addAll(wyjdz);
        HBox resetujHbox = new HBox();
        resetujHbox.setAlignment(Pos.CENTER);
        akceptuj.getStyleClass().add("dalej-btn");
        resetujHbox.getChildren().add(akceptuj);
        dolnyPanel.getChildren().addAll(resetujHbox, wyjdzHbox);

        mainPane.getChildren().addAll(gridPane, dolnyPanel);

        root.getChildren().addAll(
                titlePane,
                mainPane
        );
        Scene scene = new Scene(root, 1300, 780);
        scene.getStylesheets().add(
                getClass().getResource("/css/mainPanels/ustawieniaLek.css").toExternalForm()
        );

        primaryStage.setTitle("E-Recepta");
        primaryStage.setScene(scene);
        primaryStage.show();

        akceptuj.setOnAction(event -> {
            noweImie = imieField.getText();
            noweNazwisko = nazwiskoField.getText();
            nowyPesel = peselField.getText();
            nowyAdres = adresField.getText();
            nowyTelefon = telefonField.getText();
            nowyEmail = emailField.getText();
            nowyWiek = wiekField.getText();
            nowaPlec = plecField.getText();

            ServerConnection serverConnection = new ServerConnection(PESELString, nazwiskoString);
            try {
                serverConnection.getUpdateLek("updateWszystkoLek", PESELString, noweImie, noweNazwisko
                ,nowyPesel, nowyAdres, nowyTelefon, nowyEmail, nowyWiek, nowaPlec);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("UWAGA!");
                alert.setHeaderText(null);
                alert.setContentText("Zmieniono dane!");
                alert.showAndWait();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public Button getWyjdzBtn() {return wyjdz;}
}
