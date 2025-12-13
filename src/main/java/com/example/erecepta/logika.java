package com.example.erecepta;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class logika extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    logFX logFX1 = new logFX();
    Button submit = logFX1.getSubmitButton();
    Button clear = logFX1.getClearButton();
    TextField loginField = logFX1.getLoginTextField();
    TextField passwordField = logFX1.getPasswordField();

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
                String login = logFX1.getLogin();
                String password = logFX1.getPassword();
                int mode = logFX1.getChosenMode();

                logowanie log1 = new logowanie(login, password);

                String imie = log1.getImie(login);
                String nazwisko = log1.getNazwisko(login);

                mainLekPanel mainPanel = new mainLekPanel(imie, nazwisko, login, password); //Na razie przeniesione tu puki nie ma innych paneli

                switch (mode) {
                    case 1:
                        mainPanel.start(primaryStage);
                        break;
                    case 2:
                        mainPanel.start(primaryStage);
                        break;
                    case 3:
                        mainPanel.start(primaryStage);
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



