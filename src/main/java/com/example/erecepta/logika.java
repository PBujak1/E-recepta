package com.example.erecepta;
import com.example.erecepta.lekarz.*;
import com.example.erecepta.pacjent.mainPacPanel;
import com.example.erecepta.pacjent.nowaWizyta;
import javafx.application.Application;
import javafx.scene.Scene;
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
        Scene scene = new Scene(logFX1.getView());

        scene.getStylesheets().addAll(
                getClass().getResource("/css/mainPacPanels/nowaWizyta.css").toExternalForm(),
                getClass().getResource("/css/logPanels/styleStart.css").toExternalForm(),
                getClass().getResource("/css/logPanels/styleLog.css").toExternalForm(),
                getClass().getResource("/css/mainPanels/stylePac.css").toExternalForm(),
                getClass().getResource("/css/tworzenieKont/noweKontoPac.css").toExternalForm()
        );

        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();

        logFX1.getWyjdzBtn().setOnAction(e -> {
            primaryStage.close();
        });

        logFX1.getStworzKontoPacjenta().setOnAction(e -> {
            stworzKontoPac stworzKontoPac = new stworzKontoPac();
            scene.setRoot(stworzKontoPac.getView());

            stworzKontoPac.getExitButton().setOnAction(actionEvent -> {
                scene.setRoot(logFX1.getView());
                logFX1.getLoginTextField().clear();
                logFX1.getPasswordField().clear();
            });
        });

        logFX1.getStwórzKontoLekarza().setOnAction(e -> {
            stworzKontoLek stworzKontoLek = new stworzKontoLek(
                    "Tworzenie konta",
                    "Tworzenie konta",
                    "Tworzenie konta",
                    "Tworzenie konta",
                    "Tworzenie konta",
                    "Tworzenie konta",
                    "Tworzenie konta",
                    "Tworzenie konta",
                    "Tworzenie konta"
            );
            stworzKontoLek.start(primaryStage);

            stworzKontoLek.getWyjdzBtn().setOnAction(actionEvent -> {
                primaryStage.setScene(scene);
                primaryStage.show();
            });
        });
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
                String password = logFX1.getLogin();
                String PESEL = logFX1.getPassword();
                int mode = logFX1.getChosenMode();
                ServerConnection serverConnection = new ServerConnection(PESEL, password);

                switch (mode) {
                    case 1:
                        try {
                            String result = serverConnection.getPacjent("loginPacjent", PESEL);
                            if ("Brak danych".equals(result)) {
                                new Alert(Alert.AlertType.INFORMATION, "Brak połączenia").showAndWait();
                                return;
                            } else {
                            imie = serverConnection.getPacjent("getImiePacjent", PESEL);
                            nazwisko = serverConnection.getPacjent("getNazwiskoPacjent", PESEL);
                            nazwaPacjenta = imie + " " + nazwisko;
                            mainPacPanel mainPanelPac = new mainPacPanel(imie, PESEL, nazwaPacjenta);
                            scene.setRoot(mainPanelPac.getView());

                            mainPanelPac.getDawkowanieButton().setOnAction(event -> {

                            });

                            mainPanelPac.getHistoriaButton().setOnAction(event -> {

                            });

                            mainPanelPac.getWizytaButton().setOnAction(event -> {
                                System.out.println("działa");
                                try {
                                    nowaWizyta nowaWizyta = new nowaWizyta(PESEL, imie);
                                    scene.setRoot(new nowaWizyta(PESEL, imie).getView());

                                    nowaWizyta.getWyjdzButton().setOnAction(e1 -> {
                                        try {
                                            scene.setRoot(new mainPacPanel(imie, PESEL, nazwaPacjenta).getView());
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                    });
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                            });

                            mainPanelPac.getNadchodzaceWizyty().setOnAction(event -> {

                            });

                            }
                        } catch (IOException ex) {
                            new Alert(Alert.AlertType.WARNING, "Nie udało się pobrać danych pacjenta!").showAndWait();
                        }
                        break;
                    case 2:
                        try {
                            String result = serverConnection.getPacjent("loginLekarz", PESEL);
                            if ("Brak danych".equals(result)) {
                                new Alert(Alert.AlertType.INFORMATION, "Brak połączenia").showAndWait();
                                return;
                            } else {
                                imie = serverConnection.getPacjent("getImieLekarz", PESEL);
                                nazwisko = serverConnection.getPacjent("getNazwiskoLekarz", PESEL);
                                String IDLekarza = serverConnection.getPacjent("getIDLekarza", PESEL);
                                String recepta = serverConnection.getPacjent("getRecepta", IDLekarza);
                                String historia = serverConnection.getPacjent("getHistoria", IDLekarza);
                                String adres1 = serverConnection.getPacjent("getAdresLekarz", PESEL);
                                String telefon1 = serverConnection.getPacjent("getTelefonLekarz", PESEL);
                                String email1 = serverConnection.getPacjent("getEmailLekarz", PESEL);
                                String wiek1 = serverConnection.getPacjent("getWiekLekarz", PESEL);
                                String plec1 = serverConnection.getPacjent("getPlecLekarz", PESEL);
                                nazwaPacjenta = imie + nazwisko;
                                mainLekPanel mainPanelLek = new mainLekPanel(PESEL, password, imie, nazwisko);
                                mainPanelLek.start(primaryStage);

                                mainPanelLek.getPomoc().setOnAction(e1 -> {
                                    pomoc pomocPanel = new pomoc();
                                    pomocPanel.start(primaryStage);

                                    pomocPanel.getWyjdz().setOnAction(a -> {
                                        mainPanelLek.start(primaryStage);
                                    });
                                });

                                mainPanelLek.getUstawieniaLek().setOnAction(a -> {
                                    ustawieniaLek ustawieniaLekarz = new ustawieniaLek(
                                            imie,
                                            nazwisko,
                                            PESEL,
                                            adres1,
                                            telefon1,
                                            email1,
                                            wiek1,
                                            plec1
                                    );
                                    ustawieniaLekarz.start(primaryStage);

                                    ustawieniaLekarz.getWyjdzBtn().setOnAction(actionEvent -> {
                                        mainPanelLek.start(primaryStage);
                                    });
                                });

                                mainPanelLek.getMojeReceptaBtn().setOnAction(actionEvent -> {
                                    mojeRecepty mr = new mojeRecepty(recepta);
                                    mr.start(primaryStage);

                                    mr.getWyjdz().setOnAction(a -> {
                                        mainPanelLek.start(primaryStage);
                                    });
                                });

                                mainPanelLek.getHistoryBtn().setOnAction(actionEvent -> {
                                    historiaPac hp = new historiaPac(historia);
                                    hp.start(primaryStage);

                                    hp.getWyjdz().setOnAction(a -> {
                                        mainPanelLek.start(primaryStage);
                                    });
                                });

                                nowaRecepta.getWyjdzBtn().setOnAction(a1 -> {
                                    mainPanelLek.start(primaryStage);
                                });
                            }
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
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



