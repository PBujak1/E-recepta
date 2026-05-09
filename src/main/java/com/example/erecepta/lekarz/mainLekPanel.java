package com.example.erecepta.lekarz;

import com.example.erecepta.ServerConnection;
import com.example.erecepta.logika;
import com.example.erecepta.pacjent.nowaWizyta;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    private Label plecLabel = new Label("Płeć: ");
    private Label plec = new Label("");
    private Label wiekLabel = new Label("Wiek: ");
    private Label wiek = new Label("");
    private Label PESELLabel = new Label("PESEL: ");
    private Label PESELPacjenta = new Label("");
    private Label adresLabel = new Label("Adres: ");
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

    private VBox allergyList = new VBox(12);
    ScrollPane contentPane = new ScrollPane();
    List<String> MedList = new ArrayList<>();

    //Zmienne prawej kolumny
    BorderPane root = new BorderPane();
    private Label alergieTitle = new Label("ALERGIE PACJENTA:");
    private final Label historiaTitle = new Label("Najbliższe E-wizyty:");
    private final Button zobaczWszystko = new Button("Zobacz Wszystko");
    VBox nowyLek = new VBox(10);

    public mainLekPanel(String login, String password, String imie, String nazwisko) {
        this.login = login;
        this.password = password;
        this.imie = imie;
        this.nazwisko = nazwisko;

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
                Objects.requireNonNull(getClass().getResourceAsStream("/icons/search.png"))
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
        danePacjenta.setVgap(15);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHalignment(HPos.RIGHT);
        col1.setPercentWidth(30);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHalignment(HPos.LEFT);
        col2.setPercentWidth(5);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setHalignment(HPos.RIGHT);
        col3.setPercentWidth(30);
        ColumnConstraints col4 = new ColumnConstraints();
        col4.setHalignment(HPos.LEFT);
        col4.setHgrow(Priority.ALWAYS);
        col4.setFillWidth(true);

        danePacjenta.getColumnConstraints().addAll(col1, col2, col3, col4);
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

        /*
            DODANIE LEKÓW DO NOWEJ RECEPTY
            PO WYSZUKANIU PACJENTA MOŻNA SZYBKO DODAĆ PARĘ LEKÓW
            LEKI SĄ ZAWSZE DO 1 OPAKOWANIA
        */

        //Środkowy panel wyszukiwanie leku
        HBox searchBox2 = new HBox(8);
        lekField.setPromptText("Wyszukaj lek...");
        searchBox2.getChildren().addAll(searchIcon2, lekField);

        try {
            ServerConnection connection = new ServerConnection(imie, password);
            String tablicaLekow = connection.getPacjent("getLeki", password);
            List<String> leki = new ArrayList<>(Arrays.asList(tablicaLekow.split("\n")));
            nowaWizyta.suggestrions(leki, lekField);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        karta.getStyleClass().add("karta");
        imieINazwiskoPacjenta.getStyleClass().add("imie-nazwisko");
        searchBox2.getStyleClass().add("search-box2");
        searchBox2.setAlignment(Pos.CENTER_LEFT);

        HBox.setHgrow(lekField, Priority.ALWAYS);
        lekField.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(searchBox2, Priority.ALWAYS);

        VBox botBox = new VBox(10);
        botBox.setAlignment(Pos.CENTER);
        VBox.setVgrow(botBox, Priority.ALWAYS);

        Region spacer1 = new Region();

        HBox searchMed = new HBox(10);
        searchMed.setAlignment(Pos.CENTER_LEFT);
        searchMed.getChildren().addAll(
                searchBox2,
                dodajLekBtn
        );

        ScrollPane lekiScrollPane = new ScrollPane(nowyLek);
        lekiScrollPane.setFitToWidth(true);
        lekiScrollPane.setPrefHeight(300);
        lekiScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        lekiScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        HBox bottomBtn = new HBox();
        bottomBtn.setAlignment(Pos.CENTER);
        VBox.setVgrow(bottomBtn, Priority.ALWAYS);
        bottomBtn.getChildren().addAll(wypiszBtn);
        wypiszBtn.getStyleClass().add("wypisz-recepte");

        botBox.getChildren().addAll(
                typReceptyLabel,
                searchMed, new Separator(),
                lekiScrollPane,
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

        allergyList.getStyleClass().add("allergy-container");

        ScrollPane contentPane2 = new ScrollPane(allergyList);
        contentPane2.setFitToWidth(true);
        contentPane2.setMaxHeight(Double.MAX_VALUE);
        contentPane2.getStyleClass().add("main-panel-content");

        VBox historyBox = new VBox(12);
        historyBox.setAlignment(Pos.CENTER);
        historyBox.getChildren().addAll(historiaTitle, zobaczWszystko);
        historiaTitle.getStyleClass().add("historia-title");
        zobaczWszystko.setMaxWidth(Double.MAX_VALUE);
        zobaczWszystko.getStyleClass().add("zobacz-wszystko");

        VBox emptyPane = new VBox(new Label());
        emptyPane.setAlignment(Pos.TOP_CENTER);

        contentPane.setFitToWidth(true);
        VBox.setVgrow(contentPane, Priority.ALWAYS);
        contentPane.setMaxHeight(Double.MAX_VALUE);
        contentPane.getStyleClass().add("main-panel-content");
        contentPane.setContent(emptyPane);

        VBox rightPanel = new VBox(20);
        rightPanel.getChildren().addAll(
                titleBoxP,
                contentPane2, new Separator(),
                historyBox,
                contentPane
        );
        rightPanel.getStyleClass().add("right-panel");
        alergieTitle.getStyleClass().add("subtitle");


        /*
           BORDERPANE — UŁOŻENIE 3 PANELI
        */

        root.setLeft(leftPanel);
        root.setCenter(centerPanel);
        root.setRight(rightPanel);

        /*
           SCENA
        */
        titleMiddle.getStyleClass().add("title-middle");
        centerPanel.getStyleClass().add("center-panel");
        title.getStyleClass().add("center-title");
        wczytajBtn.getStyleClass().add("blue-btn");
        typReceptyLabel.getStyleClass().add("subtitle");
        danePacjenta.getStyleClass().add("dane-pacjenta");
        dodajLekBtn.getStyleClass().add("blue-btn");
        searchField.getStyleClass().add("search-field");
        lekField.getStyleClass().add("search-field2");
        lekiScrollPane.getStyleClass().add("lek-scrollpane");

        nowaRecepta.setOnAction(a -> {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Nie wybrano pacjenta!");
            alert.showAndWait();
        });

        zobaczWszystko.setOnAction(a -> {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Nie wybrano pacjenta!");
            alert.showAndWait();
        });

        dodajLekBtn.setOnAction(a -> {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Nie wybrano pacjenta!");
            alert.showAndWait();
        });

        wypiszBtn.setOnAction(a -> {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Nie wybrano pacjenta!");
            alert.showAndWait();
        });
    }

    public Button getHistoryBtn() {return historia;}
    public Button getMojeReceptaBtn() { return mojeRecepty; }
    public Button getUstawieniaLek() { return ustawienia; }
    public Button getPomoc() { return pomoc; }
    public Button getZobaczWszystko() { return zobaczWszystko; }
    public Button getWyloguj() { return wyloguj; }
    public Button getWczytajBtn() { return wczytajBtn; }
    public Button getNowaRecepta() { return nowaRecepta; }

    public TextField getSearchField() { return searchField; }

    public Label getImieINazwiskoPacjenta() { return imieINazwiskoPacjenta;}
    public Label getPESELPacjenta() { return PESELPacjenta; }
    public Label getAdresPacjenta() { return adresPacjenta; }
    public Label getPlec() { return plec; }
    public Label getWiek() { return wiek; }
    public Label getTelefon() { return telefon; }
    public Label getEmail() { return email; }

    public VBox getAllergyList() { return allergyList; }

    public Parent getView() { return root; }

    public Button getDodajLekBtn() { return dodajLekBtn; }
    public TextField getLekField() { return lekField; }

    public Button getWypiszBtn() { return wypiszBtn; }
    public ScrollPane getContentPane() { return contentPane; }
    public List<String> getMedList() { return MedList; }
    public VBox getNowyLek() { return nowyLek; }
}
