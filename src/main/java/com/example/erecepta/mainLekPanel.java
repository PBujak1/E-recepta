package com.example.erecepta;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class mainLekPanel {

    String nazwisko;
    String imie;
    String login;
    String password;

    //Zmienne lewego panelu
    private final Button nowaRecepta = new Button("NOWA E-RECEPTA");
    private final Button historia = new Button("Historia Pacjentów");
    private final Button mojeRecepty = new Button("Moje Recepty");
    private final Button ustawienia = new Button("Ustawienia");
    private final Button pomoc = new Button("Pomoc");
    private final Button wyloguj = new Button("Wyloguj");

    //Zmienne środkowego panelu
    private Label title = new Label("Wystawianie E-Recepty");
    private Label imieINazwiskoLabel = new Label("Imię i nazwisko:");
    private Label imieINazwiskoPacjenta = new Label("");
    private Label plecLabel = new Label("Płeć:");
    private Label plec = new Label("");
    private Label wiekLabel = new Label("Wiek:");
    private Label wiek = new Label("");
    private Label PESELLabel = new Label("PESEL:");
    private Label PESELPacjenta = new Label("");
    private Label adresLabel = new Label("Adres:");
    private Label adresPacjenta = new Label("");
    private Label telefonLabel = new Label("Nr. Telefonu:");
    private Label telefon = new Label("");
    private Label emailLabel = new Label("Adres e-mail:");
    private Label email = new Label("");
    private Label typReceptyLabel = new Label("Leki do recepty");
    private Button wczytajBtn = new Button("Wczytaj");
    private Button wypiszBtn = new Button("Wypisz Receptę");
    private Button dodajLekBtn = new Button("Dodaj Lek");
    private TextField searchField = new TextField();
    private TextField lekField = new TextField();

    //Zmienne prawej kolumny
    private Label alergieTitle = new Label("ALERGIE PACJENTA:");
    private Label alergia1 = new Label("penicylina");
    private Label alergia2 = new Label("orzechy");
    private Label alergia3 = new Label("metylomeksatolina");
    private Label historiaTitle = new Label("Historia Recept Pacjenta:");
    private Button zobaczWszystko = new Button("Zobacz Wszystko");

    mainLekPanel(String login, String password, String imie, String nazwisko) {
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
        Label doctorInfo = new Label("Lekarz: " + imie + " " + nazwisko + " \nPWZ: 1234567");

        VBox leftPanelButtons = new VBox(15);

        nowaRecepta.setMaxWidth(Double.MAX_VALUE);
        historia.setMaxWidth(Double.MAX_VALUE);
        mojeRecepty.setMaxWidth(Double.MAX_VALUE);;
        ustawienia.setMaxWidth(Double.MAX_VALUE);
        pomoc.setMaxWidth(Double.MAX_VALUE);
        wyloguj.setMaxWidth(Double.MAX_VALUE);

        doctorInfo.getStyleClass().add("doctor-info");
        wyloguj.getStyleClass().add("logout-btn");
        leftPanel.getStyleClass().add("left-panel");
        leftPanelButtons.getStyleClass().add("left-btn-panel");

        leftPanelButtons.getChildren().addAll(
                nowaRecepta,
                historia,
                mojeRecepty,
                ustawienia,
                pomoc
        );

        leftPanel.getChildren().addAll(
                doctorInfo, new Separator(),
                leftPanelButtons, new Separator(),
                spacer,
                wyloguj
        );


        /*
           ŚRODKOWA KOLUMNA
           Podzielona jest na trzy panele
           1.Górny zawierający wyszukiwanie pacjenta
           2.Wypisujące dane pacjęnta
           3.Wyszukujący dany lek i dodanie go do recepty
        */
        VBox centerPanel = new VBox(20);

        HBox titleBox = new HBox(10);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.getChildren().addAll(
                title
        );

        Label searchIcon = new Label("\uD83D\uDD0D"); // 🔍
        searchIcon.getStyleClass().add("search-icon");
        Label searchIcon2 = new Label("\uD83D\uDD0D"); // 🔍
        searchIcon2.getStyleClass().add("search-icon2");

        HBox searchBox1 = new HBox(8);
        searchField.setPromptText("Wyszukaj Pacjenta (PESEL / Imię i Nazwisko)");
        searchBox1.getChildren().addAll(searchIcon, searchField);
        searchBox1.getStyleClass().add("search-box");
        searchBox1.setAlignment(Pos.CENTER_LEFT);

        HBox.setHgrow(searchField, Priority.ALWAYS);
        searchField.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(searchBox1, Priority.ALWAYS);

        HBox topBox = new HBox(10);
        topBox.setAlignment(Pos.CENTER);
        topBox.getChildren().addAll(
                searchBox1,
                wczytajBtn
        );

        /* Dane pacjenta */
        GridPane danePacjenta = new GridPane();
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHalignment(HPos.RIGHT);
        col1.setPercentWidth(25);
        col1.setHalignment(HPos.RIGHT);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.ALWAYS);
        col2.setFillWidth(true);

        danePacjenta.getColumnConstraints().addAll(col1, col2);
        danePacjenta.add(imieINazwiskoLabel, 0, 0);
        danePacjenta.add(imieINazwiskoPacjenta, 1, 0);
        danePacjenta.add(PESELLabel, 0, 1);
        danePacjenta.add(PESELPacjenta, 1, 1);
        danePacjenta.add(plecLabel, 0, 2);
        danePacjenta.add(plec, 1, 2);
        danePacjenta.add(wiekLabel, 0, 3);
        danePacjenta.add(wiek, 1, 3);
        danePacjenta.add(adresLabel, 0, 4);
        danePacjenta.add(adresPacjenta, 1, 4);
        danePacjenta.add(telefonLabel, 0, 5);
        danePacjenta.add(telefon, 1, 5);
        danePacjenta.add(emailLabel, 0, 6);
        danePacjenta.add(email, 1, 6);

        HBox searchBox2 = new HBox(8);
        lekField.setPromptText("Wyszukaj lek...");
        searchBox2.getChildren().addAll(searchIcon2, lekField);
        searchBox2.getStyleClass().add("search-box2");
        searchBox2.setAlignment(Pos.CENTER_LEFT);

        HBox.setHgrow(lekField, Priority.ALWAYS);
        lekField.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(searchBox2, Priority.ALWAYS);

        HBox botoomBox = new HBox(10);
        botoomBox.setAlignment(Pos.CENTER_LEFT);
        botoomBox.getChildren().addAll(
                searchBox2,
                dodajLekBtn
        );

        centerPanel.getStyleClass().add("center-panel");
        title.getStyleClass().add("center-title");
        wczytajBtn.getStyleClass().add("blue-btn");
        typReceptyLabel.getStyleClass().add("subtitle");
        danePacjenta.getStyleClass().add("dane-pacjenta");
        dodajLekBtn.getStyleClass().add("blue-btn");
        searchField.getStyleClass().add("search-field");
        lekField.getStyleClass().add("search-field2");

        HBox bottomBtn = new HBox();
        bottomBtn.setAlignment(Pos.CENTER);
        VBox.setVgrow(bottomBtn, Priority.ALWAYS);
        bottomBtn.getChildren().addAll(wypiszBtn);
        wypiszBtn.getStyleClass().add("wypisz-recepte");

        centerPanel.getChildren().addAll(
                titleBox,
                topBox, new Separator(),
                danePacjenta, new Separator(),
                typReceptyLabel,
                botoomBox, new Separator(),
                bottomBtn
        );


        /*
           PRAWA KOLUMNA - INFORMACJE
        */
        Label removeIcon1 = new Label("✕");
        removeIcon1.getStyleClass().add("allergy-remove");

        Label removeIcon2 = new Label("✕");
        removeIcon2.getStyleClass().add("allergy-remove");

        Label removeIcon3 = new Label("✕");
        removeIcon3.getStyleClass().add("allergy-remove");

        Image warningImage = new Image(
                getClass().getResourceAsStream("/icons/exclamation.png")
        );

        ImageView warningIcon = new ImageView(warningImage);
        warningIcon.setFitWidth(50);
        warningIcon.setFitHeight(50);
        warningIcon.setPreserveRatio(true);
        warningIcon.getStyleClass().add("allergy-alert-icon");

        Label allergyTitle = new Label("Alergie");
        allergyTitle.getStyleClass().add("allergy-title");

        HBox titleBoxP = new HBox(10);
        titleBoxP.setAlignment(Pos.CENTER);
        titleBoxP.getChildren().addAll(warningIcon, allergyTitle);

        HBox allergyChip1 = new HBox(10);
        allergyChip1.getChildren().addAll(alergia1, removeIcon1);
        allergyChip1.getStyleClass().add("allergy-chip1");
        allergyChip1.setAlignment(Pos.CENTER);

        HBox allergyChip2 = new HBox(10);
        allergyChip2.getChildren().addAll(alergia2, removeIcon2);
        allergyChip2.getStyleClass().add("allergy-chip2");
        allergyChip2.setAlignment(Pos.CENTER);

        HBox allergyChip3 = new HBox(10);
        allergyChip3.getChildren().addAll(alergia3, removeIcon3);
        allergyChip3.getStyleClass().add("allergy-chip3");
        allergyChip3.setAlignment(Pos.CENTER);

        VBox allergyList = new VBox(12);
        allergyList.getChildren().addAll(
                allergyChip1,
                allergyChip2,
                allergyChip3
        );
        allergyList.getStyleClass().add("allergy-container");

        alergia1.getStyleClass().add("allergy-text1");
        alergia2.getStyleClass().add("allergy-text2");
        alergia3.getStyleClass().add("allergy-text3");

        VBox historyBox = new VBox(12);
        historyBox.setAlignment(Pos.CENTER);
        historyBox.getChildren().addAll(historiaTitle, zobaczWszystko);
        historiaTitle.getStyleClass().add("historia-title");
        zobaczWszystko.setMaxWidth(Double.MAX_VALUE);
        zobaczWszystko.getStyleClass().add("zobacz-wszystko");

        VBox rightPanel = new VBox(20);
        rightPanel.getChildren().addAll(
                titleBoxP,
                allergyList, new Separator(),
                historyBox
        );
        rightPanel.getStyleClass().add("right-panel");
        alergieTitle.getStyleClass().add("subtitle");


        /*
           BORDERPANE — UŁOŻENIE 3 PANELI
        */
        BorderPane root = new BorderPane();
        root.setLeft(leftPanel);
        root.setCenter(centerPanel);
        root.setRight(rightPanel);

        /*
           SCENA
        */
        Scene scene = new Scene(root, 1300, 780); //1300, 780
        scene.getStylesheets().add(
                getClass().getResource("/css/mainPanels/styleLek.css").toExternalForm()
        );

        primaryStage.setTitle("E-Recepta");
        primaryStage.setScene(scene);
        primaryStage.show();

        ServerConnection serverConnection = new ServerConnection(login, password);

        wyloguj.setOnAction(e -> {
            logika mainPane = new logika();
            mainPane.start(primaryStage);
        });

        wczytajBtn.setOnAction(e -> {
            try {
                String PESEL = searchField.getText();
                String imie = serverConnection.getPacjent("getImie", PESEL);
                String nazwisko = serverConnection.getPacjent("getNazwisko", PESEL);

                imieINazwiskoPacjenta.setText(imie + " " + nazwisko);
                PESELPacjenta.setText(PESEL);
                adresPacjenta.setText(imie + " " + nazwisko);
                plec.setText(imie + " " + nazwisko);
                wiek.setText(imie + " " + nazwisko);
                adresPacjenta.setText(imie + " " + nazwisko);
                telefon.setText(imie + " " + nazwisko);
                email.setText(imie + " " + nazwisko);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public Button getPokazPac() { return wczytajBtn; }
}
