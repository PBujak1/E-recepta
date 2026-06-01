package com.example.erecepta;
import com.example.erecepta.lekarz.*;
import com.example.erecepta.pacjent.mainPacPanel;
import com.example.erecepta.pacjent.nowaWizyta;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

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
                Objects.requireNonNull(getClass().getResource("/css/logPanels/styleStart.css")).toExternalForm(),
                Objects.requireNonNull(getClass().getResource("/css/logPanels/styleLog.css")).toExternalForm(),
                Objects.requireNonNull(getClass().getResource("/css/mainPacPanels/stylePac.css")).toExternalForm(),
                Objects.requireNonNull(getClass().getResource("/css/tworzenieKont/noweKontoPac.css")).toExternalForm(),
                Objects.requireNonNull(getClass().getResource("/css/mainPanels/ustawieniaLek.css")).toExternalForm(),
                Objects.requireNonNull(getClass().getResource("/css/mainPanels/styleLek.css")).toExternalForm(),
                Objects.requireNonNull(getClass().getResource("/css/mainPanels/ustawieniaLek.css")).toExternalForm(),
                Objects.requireNonNull(getClass().getResource("/css/mainPanels/pomoc.css")).toExternalForm(),
                Objects.requireNonNull(getClass().getResource("/css/mainPanels/mojaHistoria.css")).toExternalForm(),
                Objects.requireNonNull(getClass().getResource("/css/mainPanels/mojeRecepty.css")).toExternalForm(),
                Objects.requireNonNull(getClass().getResource("/css/mainPanels/nowaRecepta.css")).toExternalForm(),
                Objects.requireNonNull(getClass().getResource("/css/mainPacPanels/nowaWizyta.css")).toExternalForm()
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
            scene.setRoot(stworzKontoLek.getView());

            stworzKontoLek.getWyjdzBtn().setOnAction(actionEvent -> {
                scene.setRoot(logFX1.getView());
                logFX1.getLoginTextField().clear();
                logFX1.getPasswordField().clear();
            });
        });
        /*
        Tworzenie teraz przycisków akcji czyli co się ma dziać po naciśnięciu guziczków
        */
        submit.setOnAction(e -> {
            if (loginField.getText().isEmpty() && passwordField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(primaryStage);
                alert.setTitle("Błąd danych");
                alert.setHeaderText(null);
                alert.setContentText("Proszę uzupełnić login i hasło!");
                alert.showAndWait();
            } else if (loginField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(primaryStage);
                alert.setTitle("Błąd danych");
                alert.setHeaderText(null);
                alert.setContentText("Pole login nie może być puste!");
                alert.showAndWait();
            } else if (passwordField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(primaryStage);
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
                                new Alert(Alert.AlertType.INFORMATION, "Błędny Login lub Hasło!").showAndWait();
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
                                try {
                                    nowaWizyta nowaWizyta = new nowaWizyta(PESEL, imie);
                                    scene.setRoot(nowaWizyta.getView());

                                    nowaWizyta.getWyjdzButton().setOnAction(e1 -> {
                                        scene.setRoot(mainPanelPac.getView());
                                    });
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                            });

                            mainPanelPac.getNadchodzaceWizyty().setOnAction(event -> {

                            });

                            mainPanelPac.getWylogujButton().setOnAction(event -> {
                                scene.setRoot(logFX1.getView());
                                logFX1.getLoginTextField().clear();
                                logFX1.getPasswordField().clear();
                            });
                            }
                        } catch (IOException ex) {
                            new Alert(Alert.AlertType.WARNING, "Brak połączenia!").showAndWait();
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
                                scene.setRoot(mainPanelLek.getView());

                                mainPanelLek.getWyloguj().setOnAction(event -> {
                                    logFX1.getLoginTextField().clear();
                                    logFX1.getPasswordField().clear();
                                    scene.setRoot(logFX1.getView());
                                });

                                mainPanelLek.getWczytajBtn().setOnAction(event -> {
                                    try {
                                        String PESELp = mainPanelLek.getSearchField().getText();
                                        String IDLekarzap = serverConnection.getPacjent("getIDLekarza", PESEL);
                                        String e_wizyty = serverConnection.getPacjent("getWizyta", IDLekarzap);
                                        String imie1 = serverConnection.getPacjent("getImiePacjent", PESELp);
                                        String nazwisko1 = serverConnection.getPacjent("getNazwiskoPacjent", PESELp);
                                        String adres1p = serverConnection.getPacjent("getAdres", PESELp);
                                        String telefon1p = serverConnection.getPacjent("getTelefon", PESELp);
                                        String email1p = serverConnection.getPacjent("getEmail", PESELp);
                                        String wiek1p = serverConnection.getPacjent("getWiek", PESELp);
                                        String plec1p = serverConnection.getPacjent("getPlec", PESELp);
                                        String historiaPacString = serverConnection.getPacjent("getHistoriaPac", PESELp);
                                        String alergia1 = serverConnection.getPacjent("getAlergia", PESELp);

                                        mainPanelLek.getImieINazwiskoPacjenta().setText(imie1 + " " + nazwisko1);
                                        mainPanelLek.getPESELPacjenta().setText(PESELp);
                                        mainPanelLek.getAdresPacjenta().setText(adres1p);
                                        mainPanelLek.getPlec().setText(plec1p);
                                        mainPanelLek.getWiek().setText(wiek1p);
                                        mainPanelLek.getTelefon().setText(telefon1p);
                                        mainPanelLek.getEmail().setText(email1p);

                                        String[] alergieTablica = alergia1.split("\\r?\\n");
                                        mainPanelLek.getAllergyList().getChildren().clear();

                                        for (int i = 0; i < alergieTablica.length; i++) {
                                            HBox allergyChip1 = new HBox(10);
                                            Label alergieLabel = new Label(alergieTablica[i]);
                                            allergyChip1.getChildren().addAll(alergieLabel);
                                            allergyChip1.getStyleClass().add("allergy-chip1");
                                            allergyChip1.setAlignment(Pos.CENTER);
                                            mainPanelLek.getAllergyList().getChildren().add(allergyChip1);
                                        }

                                        mainPanelLek.getNowaRecepta().setOnAction(a -> {
                                            nowaRecepta Recepta = new nowaRecepta(imie1, nazwisko1, PESELp, PESEL);
                                            scene.setRoot(Recepta.getView());

                                            Recepta.getDodaj().setOnAction(actionEvent -> {
                                                String lek = Recepta.getNazwaLeku().getText();
                                                String opakowania = Recepta.getLiczbaOpakowan().getText();
                                                String odplatnosci = Recepta.getOdplatnosc().getText();

                                                try {
                                                    serverConnection.getUpdateRec("updateWszystkoRec", PESELp, lek, opakowania, odplatnosci, PESEL);
                                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                                    alert.initOwner(primaryStage);
                                                    alert.setTitle("UWAGA!");
                                                    alert.setHeaderText(null);
                                                    alert.setContentText("Zmieniono dane!");
                                                    alert.showAndWait();
                                                } catch (IOException e2) {
                                                    throw new RuntimeException(e2);
                                                }
                                            });
                                        });

                                        mainPanelLek.getZobaczWszystko().setOnAction(a -> {
                                            VBox historiaPane = new VBox(new Label(e_wizyty));
                                            System.out.print(e_wizyty);
                                            historiaPane.getStyleClass().add("historia");
                                            historiaPane.setAlignment(Pos.TOP_CENTER);

                                            mainPanelLek.getContentPane().setContent(historiaPane);
                                        });

                                        mainPanelLek.getDodajLekBtn().setOnAction(e1 -> {
                                            String tekst = mainPanelLek.getLekField().getText();
                                            if (!mainPanelLek.getLekField().getText().isEmpty()) {
                                                mainPanelLek.getMedList().add(tekst);
                                                mainPanelLek.getNowyLek().getChildren().add(new Label(mainPanelLek.getMedList().size() + ". " + tekst + " (1 Op.)"));
                                                mainPanelLek.getLekField().clear();
                                            } else {
                                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                                alert.initOwner(primaryStage);
                                                alert.setTitle("Błąd danych");
                                                alert.setHeaderText(null);
                                                alert.setContentText("Pole leku nie może być puste!");
                                                alert.showAndWait();
                                            }
                                        });

                                        mainPanelLek.getWypiszBtn().setOnAction(e2 -> {
                                            for (int i = 0; i < mainPanelLek.getMedList().size(); i++) {
                                                try {
                                                    serverConnection.getUpdateRec("updateWszystkoRec", PESELp, mainPanelLek.getMedList().get(i), "1", "0", PESEL);
                                                } catch (IOException ex) {
                                                    throw new RuntimeException(ex);
                                                }
                                            }
                                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                            alert.setTitle("UWAGA!");
                                            alert.setHeaderText(null);
                                            alert.setContentText("Dodano Lek!");
                                            alert.showAndWait();
                                            mainPanelLek.getSearchField().setText("");
                                        });
                                    } catch (IOException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                });

                                mainPanelLek.getPomoc().setOnAction(e1 -> {
                                    pomoc pomocPanel = new pomoc();
                                    scene.setRoot(pomocPanel.getView());

                                    pomocPanel.getWyjdz().setOnAction(a -> {
                                        scene.setRoot(mainPanelLek.getView());
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
                                    scene.setRoot(ustawieniaLekarz.getView());

                                    ustawieniaLekarz.getWyjdzBtn().setOnAction(actionEvent -> {
                                        scene.setRoot(mainPanelLek.getView());
                                    });
                                });

                                mainPanelLek.getMojeReceptaBtn().setOnAction(actionEvent -> {
                                    mojeRecepty mr = new mojeRecepty(recepta);
                                    scene.setRoot(mr.getView());

                                    mr.getWyjdz().setOnAction(a -> {
                                        scene.setRoot(mainPanelLek.getView());
                                    });
                                });

                                mainPanelLek.getHistoryBtn().setOnAction(actionEvent -> {
                                    historiaPac hp = new historiaPac(historia);
                                    scene.setRoot(hp.getView());

                                    hp.getWyjdz().setOnAction(a -> {
                                        scene.setRoot(mainPanelLek.getView());
                                    });
                                });

                                nowaRecepta.getWyjdzBtn().setOnAction(a1 -> {
                                    scene.setRoot(mainPanelLek.getView());
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