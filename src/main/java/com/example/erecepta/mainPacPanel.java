package com.example.erecepta;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class mainPacPanel {

    String nazwisko;
    String imie;
    String login;
    String password;

    mainPacPanel(String login, String password, String imie, String nazwisko) {
        this.login = login;
        this.password = password;
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    public void start(Stage primaryStage) {

        /*
           LEWA KOLUMNA - MENU
        */
        VBox leftPanel = new VBox(15);
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        leftPanel.getStyleClass().add("left-panel");
        leftPanel.setPrefWidth(300);

        Label doctorInfo = new Label("Lekarz: " + imie + " " + nazwisko + " \nPWZ: 1234567");
        doctorInfo.getStyleClass().add("doctor-info");

        Button nowaRecepta = new Button("NOWA E-RECEPTA");
        Button historia = new Button("Historia Pacjentów");
        Button mojeRecepty = new Button("Moje Recepty");
        Button ustawienia = new Button("Ustawienia");
        Button pomoc = new Button("Pomoc");

        Button wyloguj = new Button("Wyloguj");
        wyloguj.getStyleClass().add("logout-btn");

        nowaRecepta.setMaxWidth(Double.MAX_VALUE);
        nowaRecepta.setPadding(new Insets(20, 0, 20, 0));
        historia.setMaxWidth(Double.MAX_VALUE);
        historia.setPadding(new Insets(20, 0, 20, 0));
        mojeRecepty.setMaxWidth(Double.MAX_VALUE);
        mojeRecepty.setPadding(new Insets(20, 0, 20, 0));
        ustawienia.setMaxWidth(Double.MAX_VALUE);
        ustawienia.setPadding(new Insets(20, 0, 20, 0));
        pomoc.setMaxWidth(Double.MAX_VALUE);
        pomoc.setPadding(new Insets(20, 0, 20, 0));
        wyloguj.setMaxWidth(Double.MAX_VALUE);
        wyloguj.setPadding(new Insets(20, 0, 20, 0));

        leftPanel.getChildren().addAll(
                doctorInfo,
                new Separator(),
                nowaRecepta,
                historia,
                mojeRecepty,
                ustawienia,
                pomoc,
                new Separator(),
                spacer,
                wyloguj
        );


        /*
           ŚRODKOWA KOLUMNA - FORMULARZ)
        */

        VBox centerPanel = new VBox(20);
        centerPanel.getStyleClass().add("center-panel");

        Label title = new Label("Wystawianie E-Recepty");
        title.getStyleClass().add("center-title");

        TextField searchField = new TextField();
        searchField.setPromptText("Wyszukaj Pacjenta (PESEL / Imię i Nazwisko)");

        Button wczytajBtn = new Button("Wczytaj");
        wczytajBtn.getStyleClass().add("blue-btn");

        Button nowyPacjentBtn = new Button("Nowy Pacjent");

        HBox searchBox = new HBox(10, searchField, wczytajBtn, nowyPacjentBtn);

        /* Dane pacjenta */
        GridPane danePacjenta = new GridPane();
        danePacjenta.setVgap(10);
        danePacjenta.setHgap(10);

        danePacjenta.add(new Label("Imię i Nazwisko:"), 0, 0);
        danePacjenta.add(new Label("Iwona Nowak"), 1, 0);

        danePacjenta.add(new Label("PESEL:"), 0, 1);
        danePacjenta.add(new Label("01234567890"), 1, 1);

        danePacjenta.add(new Label("Adres:"), 0, 2);
        danePacjenta.add(new Label("ul. Kwiatowa 5, Warszawa"), 1, 2);

        Label typRecepty = new Label("Typ Recepty");
        typRecepty.getStyleClass().add("subtitle");

        TextField lekField = new TextField();
        lekField.setPromptText("Wyszukaj lek...");

        Button skalujBtn = new Button("Skaluj!");
        Button dodajLekBtn = new Button("Dodaj Lek");
        dodajLekBtn.getStyleClass().add("blue-btn");

        HBox lekBox = new HBox(10, lekField, skalujBtn, dodajLekBtn);

        centerPanel.getChildren().addAll(
                title,
                searchBox,
                new Separator(),
                danePacjenta,
                new Separator(),
                typRecepty,
                lekBox
        );


        /*
           PRAWA KOLUMNA - INFORMACJE
        */

        VBox rightPanel = new VBox(20);
        rightPanel.getStyleClass().add("right-panel");
        rightPanel.setPrefWidth(300);

        Label alergieTitle = new Label("ALERGIE PACJENTA:");
        alergieTitle.getStyleClass().add("subtitle");

        Label alergia = new Label("Uczulenie na penicylinę!");
        alergia.getStyleClass().add("alert-box");

        Label historiaTitle = new Label("Historia Recept Pacjenta:");

        Button zobaczWszystko = new Button("Zobacz Wszystko");

        rightPanel.getChildren().addAll(
                alergieTitle,
                alergia,
                new Separator(),
                historiaTitle,
                zobaczWszystko
        );


        /*
           BORDERPANE — UŁOŻENIE 3 PANELI
        */

        BorderPane root = new BorderPane();
        root.setLeft(leftPanel);
        root.setCenter(centerPanel);
        root.setRight(rightPanel);

        /*
           SCENA I CSS
        */

        Scene scene = new Scene(root, 1300, 780); //1300, 780

        scene.getStylesheets().add(
                getClass().getResource("/css/mainPanels/styleLek.css").toExternalForm()
        );

        primaryStage.setTitle("E-Recepta");
        primaryStage.setScene(scene);
        primaryStage.show();

        wyloguj.setOnAction(e -> {
            logika mainPane = new logika();
            mainPane.start(primaryStage);
        });
    }
}
