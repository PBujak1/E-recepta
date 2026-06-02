package com.example.erecepta.lekarz;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class pomoc {

    BorderPane root = new BorderPane();
    private Label title = new Label("Wsparcie techniczne");
    private Label subtitle = new Label("System E-recepta");
    private Label callText = new Label("ZADZWOŃ DO NAS");
    private Label phone = new Label("+48 123 456 789");
    private Label mailText = new Label("NAPISZ E-MAIL");
    private Label email = new Label("wsparcie@e-recepta.pl");
    private Label hours = new Label("Pn – Pt: 08:00 – 18:00");

    private Button wyjdz = new Button("Wyjdz");

    public pomoc() {
        wyjdz.getStyleClass().add("pomoc-exit-btn");
        Region spacer = new Region();

        VBox center = new VBox(20);
        center.getStyleClass().add("pomoc-center");
        center.setAlignment(Pos.CENTER);
        center.setPadding(new Insets(40));

        title.setFont(Font.font("System", FontWeight.BOLD, 28));
        subtitle.setFont(Font.font(16));
        callText.setFont(Font.font(String.valueOf(FontWeight.BOLD), 14));
        phone.setFont(Font.font(String.valueOf(FontWeight.BOLD), 20));
        mailText.setFont(Font.font(String.valueOf(FontWeight.BOLD), 14));
        email.setFont(Font.font(18));
        hours.setFont(Font.font(14));

        center.getChildren().addAll(
                title,
                subtitle,
                callText,
                phone,
                mailText,
                email,
                hours,
                spacer,
                wyjdz
        );

        root.setCenter(center);
    }

    public Button getWyjdz() {
        return wyjdz;
    }

    public Parent getView() { return root; }
}
