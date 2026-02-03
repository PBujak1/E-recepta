package com.example.erecepta;

import javafx.geometry.HPos;
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
    private Label karta = new Label("Karta Pacjenta");
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
    private String historiaPacString;

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
        mojeRecepty.setMaxWidth(Double.MAX_VALUE);
        ;
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
        VBox centerPanel = new VBox(10);

        HBox titleBox = new HBox(10);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.getChildren().addAll(
                title
        );

        Image searchImage = new Image(
                getClass().getResourceAsStream("/icons/search.png")
        );

        ImageView searchIcon = new ImageView(searchImage);
        searchIcon.setFitWidth(20);
        searchIcon.setFitHeight(20);
        searchIcon.setPreserveRatio(true);

        ImageView searchIcon2 = new ImageView(searchImage);
        searchIcon2.setFitWidth(20);
        searchIcon2.setFitHeight(20);
        searchIcon2.setPreserveRatio(true);

        HBox searchBox1 = new HBox(8);
        searchField.setPromptText("Wyszukaj Pacjenta (PESEL):");
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
        VBox titleMiddle = new VBox(10);
        titleMiddle.setAlignment(Pos.CENTER);

        GridPane danePacjenta = new GridPane();
        danePacjenta.setVgap(10);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHalignment(HPos.RIGHT);
        col1.setPercentWidth(15);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHalignment(HPos.LEFT);
        col2.setPercentWidth(30);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setHalignment(HPos.RIGHT);
        col3.setPercentWidth(15);
        ColumnConstraints col4 = new ColumnConstraints();
        col3.setHalignment(HPos.RIGHT);
        col4.setHgrow(Priority.ALWAYS);
        col4.setFillWidth(true);

        danePacjenta.getColumnConstraints().addAll(col1, col2);
        danePacjenta.add(PESELLabel, 0, 1);
        danePacjenta.add(PESELPacjenta, 1, 1);
        danePacjenta.add(plecLabel, 2, 1);
        danePacjenta.add(plec, 3, 1);
        danePacjenta.add(wiekLabel, 0, 2);
        danePacjenta.add(wiek, 1, 2);
        danePacjenta.add(adresLabel, 2, 2);
        danePacjenta.add(adresPacjenta, 3, 2);

        titleMiddle.getChildren().addAll(
                karta,
                imieINazwiskoPacjenta,
                danePacjenta
        );

        //Środkowy panel wyszukiwanie leku
        HBox searchBox2 = new HBox(8);
        lekField.setPromptText("Wyszukaj lek...");
        searchBox2.getChildren().addAll(searchIcon2, lekField);
        karta.getStyleClass().add("karta");
        imieINazwiskoPacjenta.getStyleClass().add("imie-nazwisko");
        searchBox2.getStyleClass().add("search-box2");
        searchBox2.setAlignment(Pos.CENTER_LEFT);

        HBox.setHgrow(lekField, Priority.ALWAYS);
        lekField.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(searchBox2, Priority.ALWAYS);

        VBox botBox = new VBox(30);
        botBox.setAlignment(Pos.CENTER);
        VBox.setVgrow(botBox, Priority.ALWAYS);

        Region spacer1 = new Region();

        HBox searchMed = new HBox(10);
        searchMed.setAlignment(Pos.CENTER_LEFT);
        searchMed.getChildren().addAll(
                searchBox2,
                dodajLekBtn
        );

        titleMiddle.getStyleClass().add("title-middle");
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

        botBox.getChildren().addAll(
                typReceptyLabel,
                searchMed, new Separator(),
                bottomBtn
        );
        botBox.getStyleClass().add("bottom-btn");

        centerPanel.getChildren().addAll(
                titleBox,
                topBox, new Separator(),
                titleMiddle, new Separator(),
                spacer1,
                botBox
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

        VBox emptyPane = new VBox(new Label());
        emptyPane.setAlignment(Pos.TOP_CENTER);

        ScrollPane contentPane = new ScrollPane();
        contentPane.setFitToWidth(true);
        VBox.setVgrow(contentPane, Priority.ALWAYS);
        contentPane.setMaxHeight(Double.MAX_VALUE);
        contentPane.getStyleClass().add("main-panel-content");
        contentPane.setContent(emptyPane);

        VBox rightPanel = new VBox(20);
        rightPanel.getChildren().addAll(
                titleBoxP,
                allergyList, new Separator(),
                historyBox,
                contentPane
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
                String imie1 = serverConnection.getPacjent("getImiePacjent", PESEL);
                String nazwisko1 = serverConnection.getPacjent("getNazwiskoPacjent", PESEL);
                String adres1 = serverConnection.getPacjent("getAdres", PESEL);
                String telefon1 = serverConnection.getPacjent("getTelefon", PESEL);
                String email1 = serverConnection.getPacjent("getEmail", PESEL);
                String wiek1 = serverConnection.getPacjent("getWiek", PESEL);
                String plec1 = serverConnection.getPacjent("getPlec", PESEL);
                historiaPacString = serverConnection.getPacjent("getHistoriaPac", PESEL);

                imieINazwiskoPacjenta.setText(imie1 + " " + nazwisko1);
                PESELPacjenta.setText(PESEL);
                adresPacjenta.setText(adres1);
                plec.setText(plec1);
                wiek.setText(wiek1);
                telefon.setText(telefon1);
                email.setText(email1);

                nowaRecepta.setOnAction(a -> {
                    nowaRecepta Recepta = new nowaRecepta(imie1, nazwisko1, PESEL, login);
                    Recepta.start(primaryStage);
                });

                zobaczWszystko.setOnAction(a -> {
                    System.out.println(historiaPacString);
                    VBox historiaPane = new VBox(new Label(historiaPacString));
                    historiaPane.getStyleClass().add("historia");
                    historiaPane.setAlignment(Pos.TOP_CENTER);

                    contentPane.setContent(historiaPane);
                });

                dodajLekBtn.setOnAction(e1 -> {
                    try {
                        String lek = searchField.getText();

                        serverConnection.getUpdateRec("updateWszystkoRec", PESEL, lek, "1", "Nie", login);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("UWAGA!");
                        alert.setHeaderText(null);
                        alert.setContentText("Dodano Lek!");
                        alert.showAndWait();

                        searchField.setText("");
                    } catch (IOException e11) {
                        throw new RuntimeException(e11);
                    }
                });
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

    }
    public Button getHistoryBtn() {return historia;}

    public Button getMojeReceptaBtn() { return mojeRecepty; }

    public Button getUstawieniaLek() { return ustawienia; }

    public Button getPomoc() { return pomoc; }
}
