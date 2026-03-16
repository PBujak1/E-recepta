package com.example.erecepta;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class nowaWizyta {
    private String PESEL;
    private String imie;

    //Tytuł
    private final Label titleLabel = new Label("Formularz E-wyzyty");

    //Top panel
    private final FontAwesomeIconView searchIcon = new FontAwesomeIconView(FontAwesomeIcon.SEARCH);
    private final TextField searchField = new TextField();
    private final Button acceptBtn = new Button("Wybierz");

    //Pod top panel
    private final FontAwesomeIconView iconUser = new FontAwesomeIconView(FontAwesomeIcon.USER_CIRCLE);
    private final FontAwesomeIconView calendarIcon = new FontAwesomeIconView(FontAwesomeIcon.CALENDAR);
    private final RadioButton stacjonarnieBtn = new RadioButton("Stacjonarnie");
    private final RadioButton Eporada = new RadioButton("E-porada");
    private final Label rodzajString = new Label("Rodzaj wizyty");

    //szuaknie terminu
    private final Calendar calendar = Calendar.getInstance();

    //Guziczki
    private final Button exitButton = new Button("Wyjście");

    nowaWizyta(String PESEL, String imie) {
        this.PESEL = PESEL;
        this.imie = imie;
    }

    public void start(Stage primaryStage) throws IOException {
        VBox root = new VBox();
        root.setAlignment(Pos.TOP_CENTER);
        root.getStyleClass().add("main-panel");

        ServerConnection connection = new ServerConnection(imie, PESEL);
        String daneLekarze = connection.getPacjent("getLekarze", PESEL);
        List<String> lekarze = new ArrayList<>(Arrays.asList(daneLekarze.split("\n")));

        /*
            Górny panel
         */
        HBox topBox = new HBox(10);
        topBox.setAlignment(Pos.CENTER);
        acceptBtn.getStyleClass().add("acceptBtn");
        topBox.getStyleClass().add("top-panel");
        topBox.getChildren().addAll(searchIcon, searchField, acceptBtn);
        HBox.setHgrow(searchField, Priority.ALWAYS);
        searchField.setPromptText("Wyszukaj lekarza");
        searchField.getStyleClass().add("search-field");

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

        /*
            Panel do wybrania formy e-wizyty
         */
        VBox selectionBox = new VBox(15);
        selectionBox.getStyleClass().add("selection");
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
            Sprawdzenie dostępnych lekarzy
         */
        HBox termin = new HBox(15);
        termin.setAlignment(Pos.CENTER);

        /*
            Utworzenie panelu lekarza po jego wcześniejszym wyszukaniu
         */
        HBox lekarz = new HBox(10);
        lekarz.setAlignment(Pos.CENTER_LEFT);
        VBox daneLekarza = new VBox(10);
        daneLekarza.setAlignment(Pos.CENTER_LEFT);
        lekarz.getChildren().addAll(
                iconUser,
                daneLekarza
        );
        lekarz.getStyleClass().add("lekarz");

        root.getChildren().addAll(
                titleLabel,
                topBox, new Separator(),
                selectionBox, new Separator(),
                lekarz, new Separator(),
                calendarIcon,
                exitButton
        );

        Scene scene = new Scene(root, 1300, 780);
        scene.getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/css/mainPacPanels/nowaWizyta.css")).toExternalForm()
        );

        primaryStage.setTitle("E-Recepta");
        primaryStage.setScene(scene);
        primaryStage.show();

        /*
            Akcje
         */
        acceptBtn.setOnAction(event -> {
            String imieNazwisko = searchField.getText().trim();
            String[] czesci = imieNazwisko.split("\\s+");

            if (czesci.length >= 2) {
                String imie = czesci[0];
                String nazwisko = czesci[1];
                try {
                    ServerConnection serverConnection = new ServerConnection(imie, nazwisko);
                    iconUser.setVisible(true);
                    String nrPZW = serverConnection.getLekarz("getPZWLekarza", imie, nazwisko);
                    String email = serverConnection.getLekarz("getEmailLekarza", imie, nazwisko);
                    String telefon = serverConnection.getLekarz("getTelefonLekarza", imie, nazwisko);
                    daneLekarza.getChildren().addAll(
                            new Label(imieNazwisko),
                            new Label(nrPZW),
                            new Label(email),
                            new Label(telefon)
                    );
                } catch (IOException e){
                    throw new RuntimeException(e);
                }
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Wprowadzono błędne dane").showAndWait();
            }

        });

        /*
            Ustawienie wyglądu ikon
         */
        iconUser.setGlyphSize(60);
        iconUser.setVisible(false);
        iconUser.setStyleClass("my-user-icon");
        searchIcon.setGlyphSize(30);
        searchIcon.setStyleClass("my-search-icon");
        exitButton.getStyleClass().add("exit-btn");
        titleLabel.getStyleClass().add("title-label");
        daneLekarza.getStyleClass().add("dane-lekarza");
    }

    public Button getWyjdzButton() {
        return exitButton;
    }

    public Button getAcceptBtn() {
        return acceptBtn;
    }
}
