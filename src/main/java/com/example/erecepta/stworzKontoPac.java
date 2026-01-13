package com.example.erecepta;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class stworzKontoPac {

    // ===== Dane osobowe =====
    private Label labelTitle = new Label("Tworzenie konta");
    private Label labelDane = new Label("Dane osobowe");

    private Label labelImie = new Label("Imię");
    private Label labelNazwisko = new Label("Nazwisko");
    private Label labelPesel = new Label("PESEL");
    private Label labelData = new Label("Data urodzenia");
    private Label labelAdres = new Label("Adres zamieszkania");

    // ===== Kontakt =====
    private Label labelKontakt = new Label("Kontakt");
    private Label labelTelefon = new Label("Numer telefonu");
    private Label labelEmail = new Label("Adres e-mail");

    // ===== Pola =====
    public TextField textImie = new TextField();
    public TextField textNazwisko = new TextField();
    public TextField textPesel = new TextField();
    public DatePicker dateUrodzenia = new DatePicker();
    public TextField textAdres = new TextField();
    public TextField textTelefon = new TextField();
    public TextField textEmail = new TextField();

    private Button buttonStworzKonto = new Button("Stwórz Konto");
    public Button buttonWyloguj = new Button("Wyjdź");

    public void start(Stage primaryStage) {

        // Placeholdery
        textImie.setPromptText("Np. Jan");
        textNazwisko.setPromptText("Np. Kowalski");
        textPesel.setPromptText("11 cyfr");
        textAdres.setPromptText("Ulica, numer domu, miasto, kod pocztowy");
        textTelefon.setPromptText("000 000 000");
        textEmail.setPromptText("pacjent@przyklad.pl");

        // Nagłówki
        labelTitle.setFont(Font.font("Manrope", 32));
        labelDane.setFont(Font.font("Manrope", 26));
        labelKontakt.setFont(Font.font("Manrope", 26));

        // ===== Grid dane osobowe =====
        VBox VDaneOsobowe = new VBox();
        GridPane daneGrid = new GridPane();
        VDaneOsobowe.getStyleClass().add("daneGrid");
        daneGrid.setHgap(30);
        daneGrid.setVgap(20);

        daneGrid.add(labelImie, 0, 0);
        daneGrid.add(textImie, 0, 1);

        daneGrid.add(labelNazwisko, 1, 0);
        daneGrid.add(textNazwisko, 1, 1);

        daneGrid.add(labelPesel, 0, 2);
        daneGrid.add(textPesel, 0, 3);

        daneGrid.add(labelData, 1, 2);
        daneGrid.add(dateUrodzenia, 1, 3);

        daneGrid.add(labelAdres, 0, 4, 2, 1);
        daneGrid.add(textAdres, 0, 5, 2, 1);

        GridPane.setHgrow(textImie, Priority.ALWAYS);
        GridPane.setHgrow(textNazwisko, Priority.ALWAYS);
        dateUrodzenia.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(dateUrodzenia, Priority.ALWAYS);


        // ===== Grid kontakt =====
        VBox Vkontakt = new VBox();
        Vkontakt.getStyleClass().add("Vkontakt");
        Vkontakt.setAlignment(Pos.TOP_CENTER);
        GridPane kontaktGrid = new GridPane();
        kontaktGrid.setAlignment(Pos.CENTER);
        kontaktGrid.setHgap(30);
        kontaktGrid.setVgap(20);

        kontaktGrid.add(labelTelefon, 0, 0);
        kontaktGrid.add(textTelefon, 0, 1);

        kontaktGrid.add(labelEmail, 1, 0);
        kontaktGrid.add(textEmail, 1, 1);


        buttonStworzKonto.setMaxWidth(Double.MAX_VALUE);
        buttonWyloguj.setMaxWidth(Double.MAX_VALUE);
        kontaktGrid.add(buttonStworzKonto, 0, 2);
        kontaktGrid.add(buttonWyloguj, 1, 2);
        buttonWyloguj.getStyleClass().add("exit-btn");
        buttonStworzKonto.setMaxWidth(Double.MAX_VALUE);
        buttonWyloguj.setMaxWidth(Double.MAX_VALUE);

        GridPane.setHgrow(textTelefon, Priority.ALWAYS);
        GridPane.setHgrow(textEmail, Priority.ALWAYS);


        VDaneOsobowe.getChildren().addAll(labelDane, daneGrid);

        Vkontakt.getChildren().addAll(
                labelKontakt,
                kontaktGrid
        );

        // ===== Root =====
        VBox root = new VBox(30);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.TOP_LEFT);

        root.getChildren().addAll(
                labelTitle,
                VDaneOsobowe, new Separator(),
                Vkontakt
        );

        Scene scene = new Scene(root, 1300, 780); // jak w Twoim kodzie

        scene.getStylesheets().add(getClass().getResource("/css/tworzenieKont/noweKontoPac.css").toExternalForm());

        primaryStage.setTitle("E-Recepta");
        primaryStage.setScene(scene);
        primaryStage.show();

        buttonWyloguj.setOnAction(e -> {
            logika mainPanel = new logika();
            mainPanel.start(primaryStage);
        });
    }

    // gettery jak u Ciebie
    public String getImie() {
        return textImie.getText();
    }

    public String getNazwisko() {
        return textNazwisko.getText();
    }

    public String getPesel() {
        return textPesel.getText();
    }

    public String getAdres() {
        return textAdres.getText();
    }

    public String getTelefon() {
        return textTelefon.getText();
    }

    public String getEmail() {
        return textEmail.getText();
    }
    public Button getClearButton() {
        return buttonStworzKonto;
    }

    public Button getSubmitButton() {
        return buttonWyloguj;
    }
}