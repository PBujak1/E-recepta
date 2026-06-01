package com.example.erecepta.pacjent;

import com.example.erecepta.ServerConnection;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class nowaWizyta {
    private VBox root = new VBox(10);
    private String PESEL;
    private String imie;

    //Tytuł
    private final Label titleLabel = new Label("Formularz E-wyzyty");

    //Top panel
    private final FontAwesomeIconView searchIcon1 = new FontAwesomeIconView(FontAwesomeIcon.SEARCH);
    private final FontAwesomeIconView searchIcon2 = new FontAwesomeIconView(FontAwesomeIcon.SEARCH);
    private final TextField searchField1 = new TextField(); 
    private final TextField searchField2 = new TextField();

    private final Button accpetDateBtn = new Button("Akceptuj");
    private final Button acceptBtn1 = new Button("Wybierz");
    private final Button acceptBtn2 = new Button("Wybierz");

    //Pod top panel
    private final FontAwesomeIconView iconUser = new FontAwesomeIconView(FontAwesomeIcon.USER_CIRCLE);
    private final FontAwesomeIconView calendarIcon = new FontAwesomeIconView(FontAwesomeIcon.CALENDAR);
    private final RadioButton stacjonarnieBtn = new RadioButton("Stacjonarnie");
    private final RadioButton Eporada = new RadioButton("E-porada");
    private final Label rodzajString = new Label("Rodzaj wizyty");
    private final Label wyborDaty = new Label("Wybierz datę wizyty");
    private final Label specjalnoscLabel = new Label("Specjalność");

    //szuaknie terminu
    private final Calendar calendar = Calendar.getInstance();

    //Guziczki
    private final Button exitButton = new Button("Wyjście");

    public nowaWizyta(String PESEL, String imie) throws IOException {
        this.PESEL = PESEL;
        this.imie = imie;

        root.setAlignment(Pos.TOP_CENTER);


        ServerConnection connection = new ServerConnection(imie, PESEL);
        String daneLekarze = connection.getPacjent("getLekarze", PESEL);
        List<String> lekarze = new ArrayList<>(Arrays.asList(daneLekarze.split("\n")));

        /*
            Wyszukiwania
         */
        HBox searchBox1 = new HBox(10);
        searchBox1.setAlignment(Pos.CENTER);
        searchBox1.getChildren().addAll(searchIcon1, searchField1, acceptBtn1);
        HBox.setHgrow(searchField1, Priority.ALWAYS);

        suggestrions(lekarze, searchField1);

        /*
            Panel do wybrania formy e-wizyty
         */
        VBox selectionBox = new VBox(15);
        HBox selection = new HBox(20);
        selection.setAlignment(Pos.CENTER);

        ToggleGroup toggleGroup = new ToggleGroup();
        stacjonarnieBtn.setToggleGroup(toggleGroup);
        Eporada.setToggleGroup(toggleGroup);
        stacjonarnieBtn.setMaxWidth(Double.MAX_VALUE);
        Eporada.setMaxWidth(Double.MAX_VALUE);
        stacjonarnieBtn.setAlignment(Pos.CENTER); // Wyśrodkowanie tekstu wewnątrz
        Eporada.setAlignment(Pos.CENTER);
        HBox.setHgrow(stacjonarnieBtn, Priority.ALWAYS);
        HBox.setHgrow(Eporada, Priority.ALWAYS);
        selection.getChildren().addAll(stacjonarnieBtn, Eporada);
        selectionBox.getChildren().addAll(rodzajString, selection);

        /*
            Panel wybierający datę oraz specjalizację
         */
        VBox innerBoxDate = new VBox(10);
        innerBoxDate.setAlignment(Pos.TOP_CENTER);

        //Panel do wybrania daty
        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("Data wizyty");
        datePicker.setPrefWidth(300);
        datePicker.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(datePicker, Priority.ALWAYS);
        accpetDateBtn.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(accpetDateBtn, Priority.ALWAYS);
        HBox date = new HBox(10);
        date.setAlignment(Pos.TOP_CENTER);
        date.getChildren().addAll(calendarIcon, datePicker );

        //Panel do wybrania specjalizacji
        HBox searchBox2 = new HBox(10);
        searchBox2.setAlignment(Pos.CENTER_LEFT);
        searchBox2.getChildren().addAll(searchIcon2, searchField2);
        searchField2.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(searchField2, Priority.ALWAYS);
        acceptBtn2.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(acceptBtn2, Priority.ALWAYS);

        suggestrions(lekarze, searchField2);

        innerBoxDate.getChildren().addAll(
                wyborDaty,
                date,
                accpetDateBtn, new Separator(),
                specjalnoscLabel,
                searchBox2,
                acceptBtn2
        );

         /*
            Panel wybierający lekarza w konkretnym terminie i specjalizacji
         */
        VBox innerBox = new VBox(10);
        innerBox.setAlignment(Pos.TOP_CENTER);

        // Tworzymy tabelę danych lekarza
        GridPane tabelaLekarza = new GridPane();
        VBox.setVgrow(tabelaLekarza, Priority.ALWAYS);

        // Definicja nagłówków
        Label hProfil = new Label("Profil");
        Label hDane = new Label("dane");
        Label hOpis = new Label("Opis");


        // Ustawienie szerokości kolumn (procentowo)
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(25);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(25);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(50);
        tabelaLekarza.getColumnConstraints().addAll(col1, col2, col3);

        // Zawartość kolumny 1: Ikona i napis "Profil"
        VBox profilBox = new VBox(10, iconUser);
        profilBox.setAlignment(Pos.CENTER);

        // Zawartość kolumny 2: Dane tekstowe
        VBox daneBox = new VBox(5);
        daneBox.setAlignment(Pos.CENTER_LEFT);

        // Zawartość kolumny 3: Opis
        Label opisLabel = new Label("Lekarz kardiologii z 20 letnim doświadczeniem...");
        opisLabel.setWrapText(true);
        VBox opisBox = new VBox(opisLabel);

        //ustawienie, aby kolumny rozciągały się na całą wysokość
        profilBox.setMaxHeight(Double.MAX_VALUE);
        daneBox.setMaxHeight(Double.MAX_VALUE);
        opisBox.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(profilBox, Priority.ALWAYS);
        GridPane.setVgrow(daneBox, Priority.ALWAYS);
        GridPane.setVgrow(opisBox, Priority.ALWAYS);
        profilBox.setFillWidth(true);
        daneBox.setFillWidth(true);
        opisBox.setFillWidth(true);

        // Dodanie do Grida
        tabelaLekarza.add(hProfil, 0, 0);
        tabelaLekarza.add(hDane, 1, 0);
        tabelaLekarza.add(hOpis, 2, 0);
        tabelaLekarza.add(profilBox, 0, 1);
        tabelaLekarza.add(daneBox, 1, 1);
        tabelaLekarza.add(opisBox, 2, 1);

        innerBox.getChildren().addAll(
                searchBox1,
                new Separator(),
                tabelaLekarza
        );


        /*
            Utworzenie panelu wybierania daty po jego wcześniejszym wyszukaniu
         */
        HBox lekarz = new HBox(10);
        VBox.setVgrow(lekarz, Priority.ALWAYS);
        lekarz.setAlignment(Pos.CENTER_LEFT);
        lekarz.getChildren().addAll(
                innerBoxDate,
                innerBox
        );

        innerBoxDate.setMaxWidth(500);
        innerBox.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(innerBox, Priority.ALWAYS);
        HBox.setHgrow(innerBoxDate, Priority.ALWAYS);

        root.getChildren().addAll(
                titleLabel,
                selectionBox, new Separator(),
                lekarz, new Separator(),
                exitButton
        );

        /*
            Akcje
         */
        acceptBtn1.setOnAction(event -> {
            String imieNazwisko = searchField1.getText().trim();
            String[] czesci = imieNazwisko.split("\\s+");

            if (czesci.length >= 2) {
                String imie1 = czesci[0];
                String nazwisko = czesci[1];
                try {
                    ServerConnection serverConnection = new ServerConnection(imie1, nazwisko);
                    iconUser.setVisible(true);
                    String nrPZW = serverConnection.getLekarz("getPZWLekarza", imie1, nazwisko);
                    String email = serverConnection.getLekarz("getEmailLekarza", imie1, nazwisko);
                    String telefon = serverConnection.getLekarz("getTelefonLekarza", imie1, nazwisko);
                    daneBox.getChildren().addAll(
                            new Label(imieNazwisko),
                            new Label(nrPZW),
                            new Label(email),
                            new Label(telefon)
                    );
                    Image img = serverConnection.getImage("imageGetter", 1);

                    ImageView imgView = new ImageView(img);
                    imgView.setFitHeight(150);
                    imgView.setFitWidth(150);
                    if (img != null) {
                        profilBox.getChildren().clear();
                    }
                    profilBox.getChildren().add(imgView);
                } catch (IOException e){
                    throw new RuntimeException(e);
                }
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Wprowadzono błędne dane").showAndWait();
            }

        });

        acceptBtn2.setOnAction(event -> {
            ServerConnection serverConnection = new ServerConnection(imie, PESEL);
            FileChooser chooser = new FileChooser();


            // filtrowanie plików (opcjonalnie)
            chooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Obrazy", "*.png", "*.jpg", "*.jpeg")
            );

            File selectedFile = chooser.showOpenDialog(null);
            if (selectedFile != null) {
                try {
                    serverConnection.setImage("imageSetter", selectedFile, 1);
                    new Alert(Alert.AlertType.INFORMATION, "Plik został wysłany").showAndWait();
                } catch (IOException e) {
                    new Alert(Alert.AlertType.ERROR, "Nie udało się wysłać pliku").showAndWait();
                }
            }
        });

        /*
            Ustawienie wyglądu ikon
         */
        root.getStyleClass().add("nowaWizyta-main-panel");
        searchField1.setPromptText("nowaWizyta-Wyszukaj lekarza");
        searchField1.getStyleClass().add("nowaWizyta-search-field1");
        searchField2.setPromptText("nowaWizyta-Wyszukaj specjalizację");
        searchField2.getStyleClass().add("nowaWizyta-search-field2");

        acceptBtn1.getStyleClass().add("nowaWizyta-acceptBtn1");
        searchBox1.getStyleClass().add("nowaWizyta-top-panel");
        acceptBtn2.getStyleClass().add("nowaWizyta-acceptBtn2");
        accpetDateBtn.getStyleClass().add("nowaWizyta-accpetDateBtn");

        date.getStyleClass().add("nowaWizyta-date");
        searchBox2.getStyleClass().add("nowaWizyta-search-panel2");
        specjalnoscLabel.getStyleClass().add("nowaWizyta-specjalnoscLabel");
        wyborDaty.getStyleClass().add("nowaWizyta-wyborDatyLabel");

        iconUser.setGlyphSize(60);
        iconUser.setVisible(true);
        iconUser.setStyleClass("nowaWizyta-my-user-icon");
        searchIcon1.setGlyphSize(30);
        searchIcon1.setStyleClass("nowaWizyta-my-search-icon");
        searchIcon2.setGlyphSize(30);
        searchIcon2.setStyleClass("nowaWizyta-my-search-icon");

        exitButton.getStyleClass().add("nowaWizyta-exit-btn");
        titleLabel.getStyleClass().add("nowaWizyta-title-label");

        daneBox.getStyleClass().add("nowaWizyta-dane-lekarza");
        datePicker.getStyleClass().add("nowaWizyta-date-picker");
        calendarIcon.getStyleClass().add("nowaWizyta-calendar-icon");
        calendarIcon.setGlyphSize(30);

        tabelaLekarza.getStyleClass().add("nowaWizyta-tabela-lekarza");
        selectionBox.getStyleClass().add("nowaWizyta-selection");

        hProfil.getStyleClass().add("nowaWizyta-header-label");
        hDane.getStyleClass().add("nowaWizyta-header-label");
        hOpis.getStyleClass().add("nowaWizyta-header-label");
        profilBox.getStyleClass().add("cell-border");
        daneBox.getStyleClass().addAll("nowaWizyta-cell-border", "nowaWizyta-padding-left");
        opisBox.getStyleClass().addAll("nowaWizyta-cell-border", "nowaWizyta-padding-left");
        lekarz.getStyleClass().add("nowaWizyta-lekarz");
        innerBoxDate.getStyleClass().add("nowaWizyta-inner-box-date");
        innerBox.getStyleClass().add("nowaWizyta-inner-box-lekarz");
    }

    public static void suggestrions(List<String> lekarze, TextField searchField) {
        ContextMenu suggestionsMenu = new ContextMenu();
        searchField.textProperty().addListener((obs, oldText, newText) -> {
            if (newText.isEmpty()) {
                suggestionsMenu.hide();
            } else {
                List<String> filtered = lekarze.stream().filter(name -> name.toLowerCase().contains(newText.toLowerCase()))
                        .collect(Collectors.toList());
                if (!filtered.isEmpty()) {
                    // Tworzymy MenuItemy dla podpowiedzi
                    suggestionsMenu.getItems().clear();
                    for (String match : filtered) {
                        MenuItem item = new MenuItem(match);
                        item.setOnAction(e -> {
                            searchField.setText(match);
                            suggestionsMenu.hide();
                        });
                        suggestionsMenu.getItems().add(item);
                    }
                    if (!suggestionsMenu.isShowing()) {
                        suggestionsMenu.show(searchField, javafx.geometry.Side.BOTTOM, 0, 0);
                    }
                } else {
                    suggestionsMenu.hide();
                }
            }
        });
    }

    public Button getWyjdzButton() {
        return exitButton;
    }

    public Button getAcceptBtn() {
        return acceptBtn1;
    }

    public Parent getView() { return root; }
}
