package com.example.erecepta;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class mainPacPanel {

    String nazwisko;
    String imie;
    String login;
    String password;

    private Label warningTestLabel1 = new Label("Recepta 1 kończy się za 2 dni!");
    private Label warningTestLabel2 = new Label("Recepta 2 kończy się za 4 dni!");
    private Button nowaRecepta = new Button("Nowa e-recepta");

    //Guziki z panelem z guziczkami
    private Label szybkiDostepLabel = new Label("Szybki dostęp");
    private Button wizyta = new Button("Umów wizytę");
    private Button historia = new Button("Historia wizyt");
    private Button nadchodzaceWizyty = new Button("Nadchodzace wizyty");
    private Button dawkowanie = new Button("Dawkowanie");

    private Label aktywneReceptyLabel = new Label("Aktywne e-recepty");


    mainPacPanel(String login, String password, String imie, String nazwisko) {
        this.login = login;
        this.password = password;
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    public void start(Stage primaryStage) {

        VBox root = new VBox();

        Label imieNazwisko = new Label(imie + " " + nazwisko);

        HBox nameBox = new HBox();
        nameBox.setAlignment(Pos.CENTER_LEFT);
        nameBox.getChildren().addAll(
            imieNazwisko
        );

        Label[] warningLabel = new Label[]{
                warningTestLabel1,
                warningTestLabel2
        };

        VBox receptyBox = new VBox();
        receptyBox.setAlignment(Pos.CENTER_LEFT);
        receptyBox.getChildren().addAll(
                warningLabel
        );
        receptyBox.getChildren().add(nowaRecepta);

        VBox szybkiDostepBox = new VBox();
        szybkiDostepBox.setAlignment(Pos.CENTER_LEFT);
        szybkiDostepBox.getChildren().add(szybkiDostepLabel);

        GridPane buttonPanel = new GridPane();
        buttonPanel.setAlignment(Pos.CENTER);
        buttonPanel.add(wizyta, 0, 0);
        buttonPanel.add(nadchodzaceWizyty, 1, 0);
        buttonPanel.add(historia, 0, 1);
        buttonPanel.add(dawkowanie, 1, 1);

        VBox aktywneReceptyBox = new VBox();
        szybkiDostepBox.setAlignment(Pos.CENTER_LEFT);
        szybkiDostepBox.getChildren().add(aktywneReceptyLabel);

        /*
           SCENA
        */
        root.getChildren().addAll(
                nameBox,
                receptyBox,
                szybkiDostepBox,
                buttonPanel,
                aktywneReceptyBox
        );
        Scene scene = new Scene(root, 1300, 780); //1300, 780
        scene.getStylesheets().add(
                getClass().getResource("/css/mainPanels/stylePac.css").toExternalForm()
        );

        primaryStage.setTitle("E-Recepta");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
