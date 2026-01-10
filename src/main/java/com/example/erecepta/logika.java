package com.example.erecepta;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class logika extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private logFX logFX1 = new logFX();
    private Button submit = logFX1.getSubmitButton();
    private Button clear = logFX1.getClearButton();
    private TextField loginField = logFX1.getLoginTextField();
    private TextField passwordField = logFX1.getPasswordField();
    private String getImie = "getImie";
    private String getNazwisko = "getNazwisko";
    private String imie;
    private String nazwisko;
    private String nazwaPacjenta;

    @Override
    public void start(Stage primaryStage) {
        logFX1.logPanel(primaryStage);
        /*
        Tworzenie teraz przycisków akcji czyli co się ma dziać po naciśnięciu guziczków
        */
        submit.setOnAction(e -> {
            if (loginField.getText().isEmpty() && passwordField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Błąd danych");
                alert.setHeaderText(null);
                alert.setContentText("Proszę uzupełnić login i hasło!");
                alert.showAndWait();
            } else if (loginField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Błąd danych");
                alert.setHeaderText(null);
                alert.setContentText("Pole login nie może być puste!");
                alert.showAndWait();
            } else if (passwordField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Błąd danych");
                alert.setHeaderText(null);
                alert.setContentText("Hasło nie może być puste!");
                alert.showAndWait();
            } else {
                String PESEL = logFX1.getLogin();
                String password = logFX1.getPassword();
                int mode = logFX1.getChosenMode();

                ServerConnection serverConnection = new ServerConnection(PESEL, password);


                switch (mode) {
                    case 1:
                        try {
                            String result = serverConnection.getPacjent("loginPacjent", PESEL);
                            if ("BRAK_DANYCH".equals(result)) {
                                new Alert(Alert.AlertType.WARNING, "Błędny login lub hasło").showAndWait();
                                return;
                            } else {
                            imie = serverConnection.getPacjent(getImie, PESEL);
                            nazwisko = serverConnection.getPacjent(getNazwisko, PESEL);
                            nazwaPacjenta = imie + " " + nazwisko;
                            mainPacPanel mainPanelPac = new mainPacPanel(imie, nazwisko, nazwaPacjenta);
                            mainPanelPac.start(primaryStage);
                            }
                        } catch (IOException ex) {
                            new Alert(Alert.AlertType.WARNING, "Nie udało się pobrać danych pacjenta!").showAndWait();
                        }
                        break;
                    case 2:
                        try {
                            imie = serverConnection.getPacjent(getImie, PESEL);
                            nazwisko = serverConnection.getPacjent(getNazwisko, PESEL);
                            nazwaPacjenta = imie + nazwisko;
                            mainLekPanel mainPanelLek = new mainLekPanel(imie, nazwisko, PESEL, password);
                            mainPanelLek.start(primaryStage);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    case 3:
                        stworzKontoPac stworzKontoPac = new stworzKontoPac();
                        stworzKontoPac.start(primaryStage);
                        break;
                    case 4:
                        stworzKontoLek stworzKontoLek = new stworzKontoLek();
                        stworzKontoLek.start(primaryStage);
                        break;
                    default:
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setContentText("Błąd wczytywania danych!");
                }
            }
        });

        clear.setOnAction(e -> {
            loginField.clear();
            passwordField.clear();
        });
    }
}



