package com.example.erecepta;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.lang.*;


public class logFX{

    VBox root = new VBox(5);
    /*
    Tworzenie zmiennych
    */
    private Label text1 = new Label("Nazwisko:");
    private Label text2 = new Label("PESEL:");
    private Label welcome = new Label("Witaj ponownie!");
    private Label subtitle = new Label("Zaloguj się, aby uzyskać dostęp do e-recept");
    private Label title = new Label("E-recepta");
    private Label mode = new Label("Zaloguj jako Pacjent");
    private Label resetHasla = new Label("Zrestartuj swoje hasło");

    private Button button1 = new Button("Wyczyść");
    private Button button2 = new Button("Zaloguj");
    private Button wyjdz = new Button("Wyjdz");
    private Button zalogujPacjent = new Button("Zaloguj jako pacjent");
    private Button zalogujLekarz = new Button("Zaloguj jako lekarz");
    private Button stworzKontoPacjenta = new Button("stworz konto Pacjenta");
    private Button stwórzKontoLekarza = new Button("Stwórz konto Lekarza");

    public TextField textField1 = new TextField();
    public PasswordField passwordField1 = new PasswordField();

    int chosenMode = 1;

    public logFX() {

        //Główny panel do którego dodajemy inne mniejsze
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        //Panel ostatni z przyciskiem do zamknięcia aplikacji oraz resetem hasła
        HBox dolnyPanel = new HBox(600);
        dolnyPanel.setAlignment(Pos.CENTER);
        //Przycisk
        HBox wyjdzHbox = new HBox();
        wyjdzHbox.setAlignment(Pos.CENTER);
        wyjdzHbox.getChildren().addAll(wyjdz);
        //napis do resetowania
        HBox resetujHbox = new HBox();
        resetujHbox.setAlignment(Pos.CENTER);
        resetujHbox.getChildren().add(resetHasla);
        dolnyPanel.getChildren().addAll(resetujHbox, wyjdzHbox);

        //Panel który zawiera napis e-recepta
        HBox header = new HBox();
        header.setPadding(new Insets(16, 16, 8, 16));
        header.setAlignment(Pos.CENTER);
        title.setFont(Font.font("Manrope", 20));
        header.getChildren().add(title);

        // Panel który zawiera napis e-recepta i to z zalogowaniem
        VBox headline = new VBox();
        headline.setPadding(new Insets(20, 16, 8, 16));
        headline.setAlignment(Pos.CENTER);
        welcome.setFont(Font.font("Manrope", 35));
        headline.getChildren().addAll(welcome, subtitle);

        //panel który zawiera przyciski do wyboru jako co chcemy się zalogować
        HBox leftPanel = new HBox(5);
        leftPanel.setAlignment(Pos.CENTER);
        zalogujPacjent.setMaxWidth(Double.MAX_VALUE);
        zalogujLekarz.setMaxWidth(Double.MAX_VALUE);
        stworzKontoPacjenta.setMaxWidth(Double.MAX_VALUE);
        stwórzKontoLekarza.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(zalogujPacjent, Priority.ALWAYS);
        HBox.setHgrow(zalogujLekarz, Priority.ALWAYS);
        HBox.setHgrow(stworzKontoPacjenta, Priority.ALWAYS);
        HBox.setHgrow(stwórzKontoLekarza, Priority.ALWAYS);
        leftPanel.getChildren().addAll(
                zalogujPacjent,
                zalogujLekarz,
                stworzKontoPacjenta,
                stwórzKontoLekarza
        );

        // Panel który zawiera napis e-recepta i to z zalogowaniem
        VBox headLog = new VBox(40);
        VBox.setVgrow(headLog, Priority.ALWAYS);

        //Panel który zawiera napis e-recepta
        HBox headMode = new HBox();
        headMode.setAlignment(Pos.TOP_CENTER);
        headMode.getChildren().add(mode);
        mode.setFont(Font.font("Manrope", 30));

        //Panel do którego dodajemy wprowadzenie loginu i hasła
        GridPane gridPane = new GridPane();
        //Konfiguracja Kolumn
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHalignment(HPos.RIGHT); // tekst wyrównany do prawej
        col1.setPercentWidth(20); // Pierwsza kolumna zajmie 20% szerokości
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHalignment(HPos.RIGHT);
        col2.setHgrow(Priority.ALWAYS);
        col2.setFillWidth(true);
        gridPane.getColumnConstraints().addAll(col1, col2);
        //Rozciąganie pól tekstowych
        textField1.setMaxWidth(Double.MAX_VALUE);
        passwordField1.setMaxWidth(Double.MAX_VALUE);
        button1.setMaxWidth(Double.MAX_VALUE);
        button2.setMaxWidth(Double.MAX_VALUE);
        gridPane.add(text1, 0, 0);
        gridPane.add(textField1, 1, 0);
        gridPane.add(text2, 0, 1);
        gridPane.add(passwordField1, 1, 1);
        gridPane.add(button1, 0, 2);
        gridPane.add(button2, 1, 2);
        gridPane.setGridLinesVisible(false);
        button1.setMaxWidth(Double.MAX_VALUE);
        button2.setMaxWidth(Double.MAX_VALUE);

        headLog.getChildren().addAll(headMode, gridPane);

        root.getChildren().addAll(
                header,
                headline, new Separator(),
                leftPanel, new Separator(),
                headLog, new Separator(),
                dolnyPanel
        );

        zalogujPacjent.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("LOGOWANIE");
            alert.setHeaderText(null);
            alert.setContentText("Logujesz się jako Pacjent");
            alert.showAndWait();
            mode.setText("Zaloguj jako Pacjent");
            chosenMode = 1;
        });

        zalogujLekarz.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("LOGOWANIE");
            alert.setHeaderText(null);
            alert.setContentText("Logujesz się jako Lekarz");
            alert.showAndWait();
            mode.setText("Zaloguj jako Lekarz");
            chosenMode = 2;
        });

        root.getStyleClass().add("logFX-root-panel");
        dolnyPanel.getStyleClass().add("logFX-dolnyPanel");
        wyjdz.getStyleClass().add("logFX-exit-btn");
        resetHasla.getStyleClass().add("logFX-reset-btn");
        leftPanel.getStyleClass().add("logFX-left-panels");
        headLog.getStyleClass().add("logFX-mode-pane");
        headMode.getStyleClass().add("logFX-mode");
        text1.getStyleClass().add("logFX-text1");
        text2.getStyleClass().add("logFX-text2");
        text1.getStyleClass().add("logFX-login-label");
        text2.getStyleClass().add("logFX-haslo-label");
        passwordField1.getStyleClass().add("logFX-password-field");
        textField1.getStyleClass().add("logFX-textfield");
        gridPane.getStyleClass().add("logFX-grid-pane");
    }

    public String getLogin() {
        return textField1.getText();
    }

    public String getPassword() {
        return passwordField1.getText();
    }

    public Button getClearButton() {
        return button1;
    }

    public Button getSubmitButton() {
        return button2;
    }

    public TextField getLoginTextField() {
        return textField1;
    }

    public PasswordField getPasswordField() {
        return passwordField1;
    }

    public int getChosenMode() {
        return chosenMode;
    }

    public Parent getView() { return root; }

    public Button getWyjdzBtn() { return wyjdz; }

    public Button getStworzKontoPacjenta() { return stworzKontoPacjenta; }

    public Button getStwórzKontoLekarza() { return stwórzKontoLekarza; }
}

