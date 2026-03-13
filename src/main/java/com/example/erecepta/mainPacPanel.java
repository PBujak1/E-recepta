package com.example.erecepta;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class mainPacPanel {

    String nazwisko;
    String imie;
    String login;
    String password;
    String nazwa;

    private final FontAwesomeIconView profileIcon = new FontAwesomeIconView(FontAwesomeIcon.USER_CIRCLE);
    private final FontAwesomeIconView belloIcon = new FontAwesomeIconView(FontAwesomeIcon.BELL);

    private Label warningTestLabel1 = new Label("Recepta 1 kończy się za 2 dni!");
    private Label warningTestLabel2 = new Label("Recepta 2 kończy się za 4 dni!");
    private Label notificationLabel1 = new Label("Otrzymano nową ereceptę!");
    private Label notificationLabel2 = new Label("Otrzymano nową ereceptę!");

    //Guziki z panelem z guziczkami
    private final Button wizyta = new Button("Prośba o E-receptę");
    private final Button historia = new Button("Historia wizyt");
    private final Button nadchodzaceWizyty = new Button("Nadchodzace wizyty");
    private final Button dawkowanie = new Button("Dawkowanie");

    private final Button wyloguj = new Button("Wyloguj") ;

    mainPacPanel(String login, String password, String nazwa) {
        this.login = login;
        this.password = password;
        this.nazwa = nazwa;
    }

    public void start(Stage primaryStage) throws IOException {

        VBox boczek = new VBox();
        HBox.setHgrow(boczek, Priority.ALWAYS);
        HBox root = new HBox(1);
        root.getStyleClass().add("main-panel");

        ServerConnection serverConnection = new ServerConnection(login, password);
        String coś = serverConnection.getPacjent("getWizytaPacjenta", password);
        System.out.println(coś);
        Label[] warningLabel = new Label[]{
                warningTestLabel1,
                warningTestLabel2,
                warningTestLabel1,
                new Label(coś)
        };

        Label[] notificationLabel = new Label[]{
                notificationLabel1,
                notificationLabel2,
        };

        /*
        Panel górny który zawiera ikonki, powiadomienia oraz nazwę profilu użytkownika
         */
        Region spacer = new Region();
        HBox nameBox = new HBox(20);
        HBox.setHgrow(spacer, Priority.ALWAYS);

        if (notificationLabel.length > 0) {
            Label imieNazwisko = new Label(nazwa);

            nameBox.setAlignment(Pos.CENTER_LEFT);
            nameBox.getStyleClass().add("nameBox");
            nameBox.getChildren().addAll(
                    profileIcon,
                    imieNazwisko,
                    spacer,
                    belloIcon
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
        for (int i = 1; i < warningLabel.length ; i++) {
            HBox warningBox = new HBox(10);
            warningBox.setAlignment(Pos.CENTER_LEFT);
            warningBox.getStyleClass().add("warningBox");
            FontAwesomeIconView warningIcon = new FontAwesomeIconView(FontAwesomeIcon.WARNING);
            warningIcon.setFill(Color.ORANGE);
            warningIcon.getStyleClass().add("warningIcon");
            warningBox.getChildren().addAll(warningIcon, warningLabel[i]);
            warningsBox.getChildren().add(warningBox);
        }

        //Dodanie ładnego przycisksu nowej recepty

        VBox nowaReceptaBox = new VBox(10);
        VBox.setVgrow(nowaReceptaBox, Priority.ALWAYS);
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
        receptyBox.setAlignment(Pos.CENTER);
        VBox.setVgrow(receptyBox, Priority.ALWAYS);
        receptyBox.getStyleClass().add("receptyBox");
        receptyBox.getChildren().addAll(
                warningsBox,new Separator(),
                nowaReceptaBox
        );

        ScrollPane receptyScrollPane = new ScrollPane(receptyBox);
        VBox.setVgrow(receptyScrollPane, Priority.ALWAYS);
        receptyScrollPane.setFitToWidth(true);
        receptyScrollPane.setFitToHeight(true);
        receptyScrollPane.getStyleClass().add("receptyScrollPane");


        /*
            Dodanie wykresu do środkowego panelu
         */
        VBox mainButtonBox = new VBox();
        VBox.setVgrow(mainButtonBox, Priority.ALWAYS);
        HBox charts = new HBox(10);
        Region spacer2 = new Region();
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Ilość wizyt");
        xAxis.setLabel("Rok");
        BarChart barChart = new BarChart(xAxis, yAxis);
        XYChart.Series dataSeries = new XYChart.Series();

        dataSeries.getData().add(new XYChart.Data("13-12-2023", 1));
        dataSeries.getData().add(new XYChart.Data("13-12-2024", 3));
        dataSeries.getData().add(new XYChart.Data("13-12-2025", 5));
        dataSeries.getData().add(new XYChart.Data("13-12-2026", 3));

        barChart.getData().add(dataSeries);
        barChart.setLegendVisible(false);

        PieChart pieChart = new PieChart();
        PieChart.Data slice1 = new PieChart.Data("Kamil Klęk", 67);
        PieChart.Data slice2 = new PieChart.Data("Druid", 69);
        PieChart.Data slice3 = new PieChart.Data("Zielarz", 112);
        pieChart.setLegendVisible(false);

        pieChart.getData().add(slice1);
        pieChart.getData().add(slice2);
        pieChart.getData().add(slice3);

        barChart.getStyleClass().add("bar-chart");
        pieChart.getStyleClass().add("pie-chart");
        mainButtonBox.getStyleClass().add("mainButtonBox");
        mainButtonBox.setAlignment(Pos.TOP_CENTER);
        charts.getChildren().addAll(
                barChart,
                pieChart
        );

        /*
            Dodanie ikonek do głównych 4 przycisków
         */
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

        mainButtonBox.getChildren().addAll(
                charts,
                spacer2,
                mainButtonPanel
        );

        HBox wylogujBox = new HBox();
        wylogujBox.setAlignment(Pos.BOTTOM_LEFT);
        wylogujBox.getStyleClass().add("wylogujBox");
        wylogujBox.getChildren().add(wyloguj);
        /*
           SCENA
        */
        boczek.setAlignment(Pos.TOP_CENTER);
        boczek.getChildren().addAll(
                nameBox, new Separator(),
                mainButtonBox, new Separator(),
                wylogujBox
        );
        root.getChildren().addAll(
                boczek, new Separator(),
                receptyScrollPane
        );
        Scene scene = new Scene(root, 1300, 780); //1300, 780
        scene.getStylesheets().add(
                getClass().getResource("/css/mainPanels/stylePac.css").toExternalForm()
        );

        primaryStage.setTitle("E-Recepta");
        primaryStage.setScene(scene);
        primaryStage.show();

        profileIcon.getStyleClass().add("profileIcon");
        profileIcon.setGlyphSize(60);
        belloIcon.getStyleClass().add("belloIcon");
        belloIcon.setGlyphSize(60);

        wyloguj.setOnAction(e -> {
            logika mainPane = new logika();
            mainPane.start(primaryStage);
        });
    }

    public Button getWizytaButton() {
        return wizyta;
    }

    public Button getNadchodzaceWizyty() {
        return nadchodzaceWizyty;
    }

    public Button getHistoriaButton() {
        return historia;
    }

    public Button getDawkowanieButton() {
        return dawkowanie;
    }
}
