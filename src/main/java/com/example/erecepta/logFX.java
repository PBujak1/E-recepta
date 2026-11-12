package com.example.erecepta;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class logFX {

    private final StackPane root = new StackPane();

    private final Label title = new Label("E-Recepta");
    private final Label welcome = new Label("Witaj ponownie!");
    private final Label subtitle = new Label("Zaloguj się, aby uzyskać dostęp do systemu");
    private final Label mode = new Label("Logowanie jako Pacjent");
    private final Label resetHasla = new Label("Nie pamiętasz hasła? Zrestartuj");

    private final Label text1 = new Label("Nazwisko:");
    private final Label text2 = new Label("PESEL:");

    private final Button button1 = new Button("Wyczyść");
    private final Button button2 = new Button("Zaloguj się");
    private final Button wyjdz = new Button("Zamknij aplikację");
    private final Button zalogujPacjent = new Button("Pacjent");
    private final Button zalogujLekarz = new Button("Lekarz");
    private final Button stworzKontoPacjenta = new Button("Rejestracja Pacjenta");
    private final Button stwórzKontoLekarza = new Button("Rejestracja Lekarza");

    public TextField textField1 = new TextField();
    public PasswordField passwordField1 = new PasswordField();

    private int chosenMode = 1;

    public logFX() {
        root.setAlignment(Pos.CENTER);
        root.getStyleClass().add("logFX-root-panel");

        // Główna karta logowania
        VBox card = new VBox(20);
        VBox.setMargin(card, new Insets(150));
        card.setAlignment(Pos.CENTER);
        card.getStyleClass().add("logFX-card");

        // 1. Logo i nagłówek
        FontAwesomeIconView brandIcon = new FontAwesomeIconView(FontAwesomeIcon.PLUS_SQUARE);
        brandIcon.setGlyphSize(42);
        brandIcon.getStyleClass().add("logFX-brand-icon");

        HBox brandBox = new HBox(10);
        brandBox.setAlignment(Pos.CENTER);
        brandBox.getChildren().addAll(brandIcon, title);
        title.getStyleClass().add("logFX-title");

        VBox headerBox = new VBox(6);
        headerBox.setAlignment(Pos.CENTER);
        welcome.getStyleClass().add("logFX-welcome");
        subtitle.getStyleClass().add("logFX-subtitle");
        headerBox.getChildren().addAll(brandBox, welcome, subtitle);

        // 2. Przełącznik ról
        HBox roleSwitcher = new HBox(10);
        roleSwitcher.setAlignment(Pos.CENTER);
        roleSwitcher.getStyleClass().add("logFX-role-switcher");

        zalogujPacjent.setMaxWidth(Double.MAX_VALUE);
        zalogujLekarz.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(zalogujPacjent, Priority.ALWAYS);
        HBox.setHgrow(zalogujLekarz, Priority.ALWAYS);

        roleSwitcher.getChildren().addAll(zalogujPacjent, zalogujLekarz);
        updateRoleButtons();

        zalogujPacjent.setOnAction(e -> {
            chosenMode = 1;
            mode.setText("Logowanie jako Pacjent");
            updateRoleButtons();
        });

        zalogujLekarz.setOnAction(e -> {
            chosenMode = 2;
            mode.setText("Logowanie jako Lekarz");
            updateRoleButtons();
        });

        // 3. Pola formularza
        VBox formBox = new VBox(14);
        formBox.setAlignment(Pos.CENTER_LEFT);

        text1.getStyleClass().add("logFX-input-label");
        textField1.setPromptText("Wprowadź nazwisko...");
        textField1.getStyleClass().add("logFX-textfield");
        textField1.setMaxWidth(Double.MAX_VALUE);

        text2.getStyleClass().add("logFX-input-label");
        passwordField1.setPromptText("Wprowadź PESEL...");
        passwordField1.getStyleClass().add("logFX-password-field");
        passwordField1.setMaxWidth(Double.MAX_VALUE);

        VBox input1Box = new VBox(6, text1, textField1);
        VBox input2Box = new VBox(6, text2, passwordField1);

        formBox.getChildren().addAll(input1Box, input2Box);

        // 4. Przyciski akcji (Zaloguj, Wyczyść)
        button2.setMaxWidth(Double.MAX_VALUE);
        button2.getStyleClass().add("logFX-submit-btn");

        button1.setMaxWidth(Double.MAX_VALUE);
        button1.getStyleClass().add("logFX-clear-btn");

        VBox actionsBox = new VBox(10);
        actionsBox.setAlignment(Pos.CENTER);
        actionsBox.getChildren().addAll(button2, button1);

        // 5. Rejestracja nowych kont
        Label noAccountLabel = new Label("Nie masz jeszcze konta?");
        noAccountLabel.getStyleClass().add("logFX-small-label");

        HBox registerBox = new HBox(12);
        registerBox.setAlignment(Pos.CENTER);
        stworzKontoPacjenta.getStyleClass().add("logFX-secondary-link-btn");
        stwórzKontoLekarza.getStyleClass().add("logFX-secondary-link-btn");
        stworzKontoPacjenta.setMaxWidth(Double.MAX_VALUE);
        stwórzKontoLekarza.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(stworzKontoPacjenta, Priority.ALWAYS);
        HBox.setHgrow(stwórzKontoLekarza, Priority.ALWAYS);
        registerBox.getChildren().addAll(stworzKontoPacjenta, stwórzKontoLekarza);

        VBox registrationSection = new VBox(8);
        registrationSection.setAlignment(Pos.CENTER);
        registrationSection.getChildren().addAll(noAccountLabel, registerBox);

        // 6. Stopka (Reset hasła + Wyjdź)
        resetHasla.getStyleClass().add("logFX-reset-btn");
        wyjdz.getStyleClass().add("logFX-exit-btn");

        HBox footerBox = new HBox(20);
        footerBox.setAlignment(Pos.CENTER);
        Region footerSpacer = new Region();
        HBox.setHgrow(footerSpacer, Priority.ALWAYS);
        footerBox.getChildren().addAll(resetHasla, footerSpacer, wyjdz);

        // Złożenie karty
        card.getChildren().addAll(
                headerBox,
                new Separator(),
                roleSwitcher,
                formBox,
                actionsBox,
                new Separator(),
                registrationSection,
                new Separator(),
                footerBox
        );

        root.getChildren().add(card);
    }

    private void updateRoleButtons() {
        zalogujPacjent.getStyleClass().removeAll("logFX-role-btn-active", "logFX-role-btn-inactive");
        zalogujLekarz.getStyleClass().removeAll("logFX-role-btn-active", "logFX-role-btn-inactive");

        if (chosenMode == 1) {
            zalogujPacjent.getStyleClass().add("logFX-role-btn-active");
            zalogujLekarz.getStyleClass().add("logFX-role-btn-inactive");
        } else {
            zalogujPacjent.getStyleClass().add("logFX-role-btn-inactive");
            zalogujLekarz.getStyleClass().add("logFX-role-btn-active");
        }
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

    public Parent getView() {
        return root;
    }

    public Button getWyjdzBtn() {
        return wyjdz;
    }

    public Button getStworzKontoPacjenta() {
        return stworzKontoPacjenta;
    }

    public Button getStwórzKontoLekarza() {
        return stwórzKontoLekarza;
    }
}
