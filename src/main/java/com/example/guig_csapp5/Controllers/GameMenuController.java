package com.example.guig_csapp5.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.guig_csapp5.Core.GlobalVar;
import com.example.guig_csapp5.Core.Mode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GameMenuController {

    private int tmpNumber;
    private int controlNumber;
    private Mode tmpMode;
    private int totalTries;
    private int triesLeft;
    private int myTries = 0;

    private int totalScores = 0;
    public static String msg1 = "";
    public static String msg2 = "";
    public static String msg3 = "";
    public static String msg9 = "";

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label labelHint;

    @FXML
    private Label labelTry;

    @FXML
    private Label labelResult2;

    @FXML
    private Button quitButton;

    @FXML
    private Button tryButton;

    @FXML
    private TextField textField1;

    @FXML
    void quitButtonClick(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void tryButtonClick(ActionEvent event) {

//        tmpNumber = Integer.parseInt(textField1.getText());
//
//        if (tmpNumber == controlNumber){
//            labelHint.setText("Верно");
//        }else {
//            labelHint.setText("Не верно");
//            triesLeft--;
//            labelTry.setText(Integer.toString(triesLeft));
//        }


        try {
            tmpNumber = Integer.parseInt(textField1.getText());

            if ((tmpNumber == controlNumber) && triesLeft > 0) {
                this.myTries++;
                this.totalScores = (int) (((this.totalTries - this.myTries) * tmpMode.getDifficult().getValue() * tmpMode.getBound() + tmpMode.getBound()) / totalTries);

                 minusTry(event);
                System.out.println(this.totalScores);
                labelHint.setText("Верно");
                labelResult2.setText("Молодец!");
                msg1 = "ВЫ ВЫИГРАЛИ!";
                msg2 = "Ваш игрок набрал " + this.totalScores + " очков!";
                msg3 = "Затратив при этом " + myTries + "/" + totalTries + " попыток.";

                System.out.println(msg1+msg2+msg3);
                // resetGame();

                final Node source = (Node) event.getSource();
                final Stage stage2 = (Stage) source.getScene().getWindow();
                stage2.close();

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AnotherPartyMenu.fxml"));
                Parent root1 = null;
                root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Вы выиграли!");
                stage.setScene(new Scene(root1));
                stage.show();

            } else {
                labelHint.setText("Не верно");
                if (controlNumber > tmpNumber) {
                    labelResult2.setText("Загаданное число больше, чем введенное!");

                } else {
                    labelResult2.setText("Загаданное число меньше, чем введенное!");
                }
                this.myTries++;
                minusTry(event);
            }

        } catch (Exception e) {
            labelHint.setText("----");
            labelResult2.setText("Введите числовое значение!");
        }

    }

    private void minusTry(ActionEvent event) {

        int inputNumber = Integer.parseInt(textField1.getText());
        if ((myTries>totalTries-1)&&(inputNumber != controlNumber)) {

            //Вызвать, что проиграл
            msg1 = "ВЫ ПРОИГРАЛИ!";
            msg2 = "Ваш игрок набрал " + this.totalScores + " очков!";
            msg3 = "Затратив при этом " + totalTries + "/" + totalTries + " попыток.";
            msg9 = "Число было: " + tmpMode.getTaskNumber();

            //Записать все в party
            //Передать все в список к игроку


            final Node source = (Node) event.getSource();
            final Stage stage2 = (Stage) source.getScene().getWindow();
            stage2.close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AnotherPartyMenu.fxml"));
            Parent root1 = null;

            try {
                root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Вы выиграли!");
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        triesLeft--;
        this.labelTry.setText(Integer.toString(triesLeft));
    }



    @FXML
    void initialize() {

        if (GlobalVar.peer.getClass().getSimpleName().equals("GuigProtocolServer")) {
            tmpMode = GlobalVar.serverMode;
        } else {
             tmpMode = GlobalVar.clientMode;
        }
        controlNumber=tmpMode.getTaskNumber();
        //Всего попыток
        totalTries=tmpMode.getTryCount();
        triesLeft=tmpMode.getTryCount();
        //Инициализация попыток
        labelTry.setText(Integer.toString(triesLeft));


        assert labelHint != null : "fx:id=\"labelHint\" was not injected: check your FXML file 'GameMenu.fxml'.";
        assert labelTry != null : "fx:id=\"labelTry\" was not injected: check your FXML file 'GameMenu.fxml'.";
        assert quitButton != null : "fx:id=\"quitButton\" was not injected: check your FXML file 'GameMenu.fxml'.";
        assert tryButton != null : "fx:id=\"tryButton\" was not injected: check your FXML file 'GameMenu.fxml'.";

    }

}

