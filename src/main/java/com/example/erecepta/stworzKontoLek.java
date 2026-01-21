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

public class stworzKontoLek {

    private Label imie;
    private Label nazwisko;
    private Label PZW;
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
    private Label starePZW = new Label("Stare PZW:");
    private Label staryPeselLabel = new Label("Stary Pesel:");
    private Label staryAdresLabel = new Label("Stary Adres:");
    private Label staryTelefonLabel = new Label("Stary Telefon:");
    private Label staryEmailLabel = new Label("Stary Email:");
    private Label staryWiekLabel = new Label("Stary Wiek:");
    private Label staraPlecLabel = new Label("Stara Płeć:");

    private Label noweImieLabel = new Label("nowe Imie:");
    private Label noweNazwiskoLabel = new Label("nowe Nazwisko:");
    private Label NrPZW = new Label("NrPZW:");
    private Label nowyPeselLabel = new Label("nowy Pesel:");
    private Label nowyAdresLabel = new Label("nowy Adres:");
    private Label nowyTelefonLabel = new Label("nowy Telefon:");
    private Label nowyEmailLabel = new Label("nowy Email:");
    private Label nowyWiekLabel = new Label("nowy Wiek:");
    private Label nowaPlecLabel = new Label("nowa Płeć:");

    private TextField imieField = new TextField();
    private TextField nazwiskoField = new TextField();
    private TextField PZWField = new TextField();
    private TextField peselField = new TextField();
    private TextField adresField = new TextField();
    private TextField telefonField = new TextField();
    private TextField emailField = new TextField();
    private TextField wiekField = new TextField();
    private TextField plecField = new TextField();

    private String noweImie;
    private String noweNazwisko;
    private String nowyPZW;
    private String nowyPesel;
    private String nowyAdres;
    private String nowyTelefon;
    private String nowyEmail;
    private String nowyWiek;
    private String nowaPlec;

    private Button wyjdz = new Button("Wyjdz");
    private Button akceptuj = new Button("Akceptuj");

    private Label title = new Label("STWÓRZ KONTO LEKARZA");

    stworzKontoLek(String imie, String nazwisko, String PZW, String PESEL, String adres, String telefon, String email, String wiek, String plec) {
        this.imie = new Label(imie);
        this.nazwisko = new Label(nazwisko);
        this.PZW = new Label(PZW);
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

        ColumnConstraints col3 = new ColumnConstraints();
        col3.setHalignment(HPos.RIGHT);
        col3.setPercentWidth(20);
        ColumnConstraints col4 = new ColumnConstraints();
        col4.setHalignment(HPos.RIGHT);
        col4.setHgrow(Priority.ALWAYS);
        col4.setFillWidth(true);
        gridPane.getColumnConstraints().addAll(col3, col4);

        gridPane.add(noweImieLabel, 0, 0);
        gridPane.add(noweNazwiskoLabel, 0, 1);
        gridPane.add(NrPZW, 0, 2);
        gridPane.add(nowyPeselLabel, 0, 3);
        gridPane.add(nowyAdresLabel, 0, 4);
        gridPane.add(nowyTelefonLabel, 0, 5);
        gridPane.add(nowyEmailLabel, 0, 6);
        gridPane.add(nowyWiekLabel, 0, 7);
        gridPane.add(nowaPlecLabel, 0, 8);

        gridPane.add(imieField, 1, 0);
        gridPane.add(nazwiskoField, 1, 1);
        gridPane.add(PZWField, 1, 2);
        gridPane.add(peselField, 1, 3);
        gridPane.add(adresField, 1, 4);
        gridPane.add(telefonField, 1, 5);
        gridPane.add(emailField, 1, 6);
        gridPane.add(wiekField, 1, 7);
        gridPane.add(plecField, 1, 8);

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
            nowyPZW = PZWField.getText();
            nowyPesel = peselField.getText();
            nowyAdres = adresField.getText();
            nowyTelefon = telefonField.getText();
            nowyEmail = emailField.getText();
            nowyWiek = wiekField.getText();
            nowaPlec = plecField.getText();

            ServerConnection serverConnection = new ServerConnection(PESELString, nazwiskoString);
            try {
                serverConnection.getNowuLek("nowyLek", noweImie, noweNazwisko, nowyPZW
                        ,nowyPesel, nowyAdres, nowyTelefon, nowyEmail, nowyWiek, nowaPlec);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("UWAGA!");
                alert.setHeaderText(null);
                alert.setContentText("Poczekaj na sprawdzenie poprawności danych oraz licencji przez admina!");
                alert.showAndWait();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public Button getWyjdzBtn() {return wyjdz;}
}
