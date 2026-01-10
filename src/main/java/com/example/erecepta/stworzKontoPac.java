package com.example.erecepta;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class stworzKontoPac {

    public TextField textImie = new TextField();
    public TextField textNazwisko = new TextField();
    public TextField textPesel = new TextField();
    public DatePicker dateUrodzenia = new DatePicker();
    public TextField textAdres = new TextField();
    public TextField textTelefon = new TextField();
    public TextField textEmail = new TextField();
    public PasswordField textHaslo = new PasswordField();

    public void start(Stage primaryStage) {

        // ===== Nagłówek =====
        Label appTitle = new Label("E-recepta");
        appTitle.setFont(Font.font("Arial", 22));

        Label welcome = new Label("Stwórz konto Pacjenta");
        welcome.setFont(Font.font("Arial", 36));

        VBox header = new VBox(10, appTitle, welcome);
        header.setAlignment(Pos.CENTER);

        // ===== KARTA FORMULARZA =====
        VBox card = new VBox(20);
        card.setPadding(new Insets(30));
        card.setMaxWidth(1000);
        card.setAlignment(Pos.CENTER);

        card.getStyleClass().add("login-card"); // pod CSS

        Label cardTitle = new Label("Dane pacjenta");
        cardTitle.setFont(Font.font("Arial", 24));

        // Placeholdery
        textImie.setPromptText("Imię");
        textNazwisko.setPromptText("Nazwisko");
        textPesel.setPromptText("PESEL");
        textAdres.setPromptText("Adres zamieszkania");
        textTelefon.setPromptText("Numer telefonu");
        textEmail.setPromptText("Adres e-mail");
        textHaslo.setPromptText("Hasło");

        // ===== Grid formularza =====
        GridPane grid = new GridPane();
        grid.setHgap(25);
        grid.setVgap(20);

        grid.add(new Label("Imię:"), 0, 0);
        grid.add(textImie, 1, 0);

        grid.add(new Label("Nazwisko:"), 2, 0);
        grid.add(textNazwisko, 3, 0);

        grid.add(new Label("PESEL:"), 0, 1);
        grid.add(textPesel, 1, 1);

        grid.add(new Label("Data urodzenia:"), 2, 1);
        grid.add(dateUrodzenia, 3, 1);

        grid.add(new Label("Adres:"), 0, 2);
        grid.add(textAdres, 1, 2, 3, 1);

        grid.add(new Label("Telefon:"), 0, 3);
        grid.add(textTelefon, 1, 3);

        grid.add(new Label("E-mail:"), 2, 3);
        grid.add(textEmail, 3, 3);

        grid.add(new Label("Hasło:"), 0, 4);
        grid.add(textHaslo, 1, 4);

        // ===== Przyciski =====
        Button btnWyczysc = new Button("Wyczyść");
        Button btnZarejestruj = new Button("Utwórz konto");

        btnWyczysc.setPrefHeight(45);
        btnZarejestruj.setPrefHeight(45);

        btnWyczysc.setPrefWidth(200);
        btnZarejestruj.setPrefWidth(400);

        HBox buttons = new HBox(20, btnWyczysc, btnZarejestruj);
        buttons.setAlignment(Pos.CENTER);

        card.getChildren().addAll(cardTitle, grid, buttons);

        // ===== ROOT =====
        VBox root = new VBox(40, header, card);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.TOP_CENTER);

        Scene scene = new Scene(root, 1300, 780);

        // Twój CSS
        scene.getStylesheets().add(
                getClass().getResource("/css/mainPanels/styleLek.css").toExternalForm()
        );

        primaryStage.setTitle("E-Recepta");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
