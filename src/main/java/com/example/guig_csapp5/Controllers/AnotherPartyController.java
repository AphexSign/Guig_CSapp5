package com.example.guig_csapp5.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class AnotherPartyController {

    @FXML
    private Button encoreButton;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label label3;

    @FXML
    private Label label5;

    @FXML
    private Label label9;

    @FXML
    private Button noButton;

    @FXML
    void encoreButtonClick(ActionEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage2 = (Stage) source.getScene().getWindow();
        stage2.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/startScreen.fxml"));
        Parent root1 = null;
        try {
            root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Угадайка v0.01");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void noButtonClick(ActionEvent event) {
        System.exit(0);
    }


    @FXML
    void initialize() {
        if (GameMenuController.msg1.equals("ВЫ ПРОИГРАЛИ!")) {
            label1.setTextFill(Color.RED);
        } else {
            label1.setTextFill(Color.GREEN);
        }

        label1.setText(GameMenuController.msg1);
        label2.setText(GameMenuController.msg2);
        label3.setText(GameMenuController.msg3);
        label9.setText(GameMenuController.msg9);

        assert encoreButton != null : "fx:id=\"encoreButton\" was not injected: check your FXML file 'AnotherParty.fxml'.";
        assert noButton != null : "fx:id=\"noButton\" was not injected: check your FXML file 'AnotherParty.fxml'.";

    }



}