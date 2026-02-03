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

import java.io.IOException;

public class mainPacPanel {

    String nazwisko;
    String imie;
    String login;
    String password;
    String nazwa;

    private Label warningTestLabel1 = new Label("Recepta 1 kończy się za 2 dni!");
    private Label warningTestLabel2 = new Label("Recepta 2 kończy się za 4 dni!");
    private Label notificationLabel1 = new Label("Otrzymano nową ereceptę!");
    private Label notificationLabel2 = new Label("Otrzymano nową ereceptę!");

    //Guziki z panelem z guziczkami
    private Button wizyta = new Button("Prośba o E-receptę");
    private Button historia = new Button("Historia wizyt");
    private Button nadchodzaceWizyty = new Button("Nadchodzace wizyty");
    private Button dawkowanie = new Button("Dawkowanie");

    private final Button wyloguj = new Button("Wyloguj");

    mainPacPanel(String login, String password, String nazwa) {
        this.login = login;
        this.password = password;
        this.nazwa = nazwa;
    }

    public void start(Stage primaryStage) throws IOException {

        VBox root = new VBox(1);
        root.getStyleClass().add("main-panel");

        Label[] warningLabel = new Label[]{
                warningTestLabel1,
                warningTestLabel2,
                warningTestLabel1
        };

        Label[] notificationLabel = new Label[]{
                notificationLabel1,
                notificationLabel2,
        };

        /*
        Panel górny który zawiera ikonki, powiadomienia oraz nazwę profilu użytkownika
         */
        Region spacer = new Region();
        Image profileImage = new Image(
                getClass().getResourceAsStream("/icons/profile.png")
        );

        ImageView profileIcon = new ImageView(profileImage);
        profileIcon.setFitWidth(50);
        profileIcon.setFitHeight(50);
        profileIcon.setPreserveRatio(true);

        HBox nameBox = new HBox(20);
        HBox.setHgrow(spacer, Priority.ALWAYS);

        if (notificationLabel.length > 0) {
            Image notificationImage = new Image(
                    getClass().getResourceAsStream("/icons/notification-bell-active.png")
            );

            ImageView notificationIcon = new ImageView(notificationImage);
            notificationIcon.setFitWidth(50);
            notificationIcon.setFitHeight(50);
            notificationIcon.setPreserveRatio(true);

            Label imieNazwisko = new Label(nazwa);

            nameBox.setAlignment(Pos.CENTER_LEFT);
            nameBox.getStyleClass().add("nameBox");
            nameBox.getChildren().addAll(
                    profileIcon,
                    imieNazwisko,
                    spacer,
                    notificationIcon
            );
        } else {
            Image notificationImage = new Image(
                    getClass().getResourceAsStream("/icons/notification-bell.png")
            );

            ImageView notificationIcon = new ImageView(notificationImage);
            notificationIcon.setFitWidth(50);
            notificationIcon.setFitHeight(50);
            notificationIcon.setPreserveRatio(true);

            Label imieNazwisko = new Label(nazwa);

            nameBox.setAlignment(Pos.CENTER_LEFT);
            nameBox.getStyleClass().add("nameBox");
            nameBox.getChildren().addAll(
                    profileIcon,
                    imieNazwisko,
                    spacer,
                    notificationIcon
            );
        }

        /*
        Kolejny panel górny który zawiera powiadomienia oraz przycisk do wypisywania e-recepty
         */
        VBox warningsBox = new VBox(10);
        for (int i = 0; i < warningLabel.length; i++) {
            HBox warningBox = new HBox(10);
            warningBox.setAlignment(Pos.CENTER_LEFT);
            warningBox.getStyleClass().add("warningBox");
            Image alertImage = new Image(
                    getClass().getResourceAsStream("/icons/alert.png")
            );

            ImageView alertIcon = new ImageView(alertImage);
            alertIcon.setFitWidth(15);
            alertIcon.setFitHeight(15);
            alertIcon.setPreserveRatio(true);
            warningBox.getChildren().addAll(alertIcon, warningLabel[i]);
            warningsBox.getChildren().add(warningBox);
        }

        //Dodanie ładnego przycisksu nowej recepty

        VBox nowaReceptaBox = new VBox(10);
        for (int i = 0; i < notificationLabel.length; i++) {
            HBox nowaReceptaView = new HBox(10);
            nowaReceptaView.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(nowaReceptaView, Priority.ALWAYS);
            nowaReceptaView.setAlignment(Pos.CENTER_LEFT);
            nowaReceptaView.getStyleClass().add("nowaReceptaBox");
            Image newDocumentImage = new Image(
                    getClass().getResourceAsStream("/icons/new-document.png")
            );

            ImageView newDocumentIcon = new ImageView(newDocumentImage);
            newDocumentIcon.setFitWidth(15);
            newDocumentIcon.setFitHeight(15);
            newDocumentIcon.setPreserveRatio(true);
            nowaReceptaView.getChildren().addAll(newDocumentIcon, notificationLabel[i]);
            nowaReceptaBox.getChildren().add(nowaReceptaView);
        }

        VBox receptyBox = new VBox(20);
        receptyBox.setAlignment(Pos.CENTER_LEFT);
        receptyBox.getStyleClass().add("receptyBox");
        receptyBox.getChildren().addAll(
                warningsBox,new Separator(),
                nowaReceptaBox
        );

        ScrollPane receptyScrollPane = new ScrollPane(receptyBox);
        receptyScrollPane.setFitToWidth(true);
        receptyScrollPane.setPrefHeight(300);
        receptyScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        receptyScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        //Dodanie środkowego panelu z guziczkami
        VBox mainButtonBox = new VBox();
        mainButtonBox.getStyleClass().add("mainButtonBox");
        mainButtonBox.setAlignment(Pos.TOP_CENTER);

        Image calendarImage = new Image(
                getClass().getResourceAsStream("/icons/calendar.png")
        );

        ImageView calendarIcon = new ImageView(calendarImage);
        calendarIcon.setFitWidth(40);
        calendarIcon.setFitHeight(40);
        calendarIcon.setPreserveRatio(true);

        Image scheduleImage = new Image(
                getClass().getResourceAsStream("/icons/schedule.png")
        );

        ImageView scheduleIcon = new ImageView(scheduleImage);
        scheduleIcon.setFitWidth(40);
        scheduleIcon.setFitHeight(40);
        scheduleIcon.setPreserveRatio(true);

        Image medicineImage = new Image(
                getClass().getResourceAsStream("/icons/medicine.png")
        );

        ImageView medicineIcon = new ImageView(medicineImage);
        medicineIcon.setFitWidth(40);
        medicineIcon.setFitHeight(40);
        medicineIcon.setPreserveRatio(true);

        Image historyImage = new Image(
                getClass().getResourceAsStream("/icons/history.png")
        );

        ImageView historyIcon = new ImageView(historyImage);
        historyIcon.setFitWidth(40);
        historyIcon.setFitHeight(40);
        historyIcon.setPreserveRatio(true);

        GridPane mainButtonPanel = new GridPane();
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHalignment(HPos.RIGHT);
        col1.setPercentWidth(50);
        col1.setHalignment(HPos.RIGHT);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.ALWAYS);
        col2.setFillWidth(true);
        mainButtonPanel.getColumnConstraints().addAll(col1, col2);

        mainButtonPanel.setHgap(16);
        mainButtonPanel.setVgap(16);
        mainButtonPanel.setPadding(new Insets(20));

        wizyta.setGraphic(calendarIcon);
        nadchodzaceWizyty.setGraphic(scheduleIcon);
        historia.setGraphic(historyIcon);
        dawkowanie.setGraphic(medicineIcon);
        wizyta.setGraphicTextGap(10);
        nadchodzaceWizyty.setGraphicTextGap(10);
        historia.setGraphicTextGap(10);
        dawkowanie.setGraphicTextGap(10);

        mainButtonPanel.setAlignment(Pos.TOP_CENTER);
        mainButtonPanel.add(wizyta, 0, 0);
        mainButtonPanel.add(nadchodzaceWizyty, 1, 0);
        mainButtonPanel.add(historia, 0, 1);
        mainButtonPanel.add(dawkowanie, 1, 1);

        wizyta.setMaxWidth(Double.MAX_VALUE);
        nadchodzaceWizyty.setMaxWidth(Double.MAX_VALUE);
        historia.setMaxWidth(Double.MAX_VALUE);
        dawkowanie.setMaxWidth(Double.MAX_VALUE);
        wizyta.setMaxHeight(150);
        nadchodzaceWizyty.setMaxHeight(150);
        historia.setMaxHeight(150);
        dawkowanie.setMaxHeight(150);
        GridPane.setVgrow(wizyta, Priority.NEVER);
        GridPane.setVgrow(nadchodzaceWizyty, Priority.NEVER);
        GridPane.setVgrow(historia, Priority.NEVER);
        GridPane.setVgrow(dawkowanie, Priority.NEVER);
        GridPane.setHgrow(wizyta, Priority.ALWAYS);
        GridPane.setHgrow(nadchodzaceWizyty, Priority.ALWAYS);
        GridPane.setHgrow(historia, Priority.ALWAYS);
        GridPane.setHgrow(dawkowanie, Priority.ALWAYS);

        mainButtonBox.setAlignment(Pos.TOP_CENTER);
        mainButtonBox.getChildren().add(
                mainButtonPanel
        );

        HBox wylogujBox = new HBox();
        wylogujBox.setAlignment(Pos.CENTER_RIGHT);
        wylogujBox.getStyleClass().add("wylogujBox");
        wylogujBox.getChildren().add(wyloguj);
        /*
           SCENA
        */
        root.setAlignment(Pos.TOP_CENTER);
        root.getChildren().addAll(
                nameBox, new Separator(),
                receptyScrollPane, new Separator(),
                mainButtonBox, new Separator(),
                wylogujBox
        );
        Scene scene = new Scene(root, 1300, 780); //1300, 780
        scene.getStylesheets().add(
                getClass().getResource("/css/mainPanels/stylePac.css").toExternalForm()
        );

        primaryStage.setTitle("E-Recepta");
        primaryStage.setScene(scene);
        primaryStage.show();

        wyloguj.setOnAction(e -> {
            logika mainPane = new logika();
            mainPane.start(primaryStage);
        });

    }
}
