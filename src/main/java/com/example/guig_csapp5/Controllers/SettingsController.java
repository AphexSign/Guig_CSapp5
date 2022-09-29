package com.example.guig_csapp5.Controllers;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.guig_csapp5.Core.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class SettingsController {

    private Difficulty difficulty;
    private Range range;


    @FXML
    private Button getSettingsButton;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button connectButton;

    @FXML
    private ToggleGroup diff;

    @FXML
    private Label labelStatus;

    @FXML
    private ToggleGroup rng;

    @FXML
    private RadioButton radioButton1;

    @FXML
    private RadioButton radioButton2;

    @FXML
    private RadioButton radioButton3;

    @FXML
    private RadioButton radioButton4;

    @FXML
    private RadioButton radioButton5;

    @FXML
    private RadioButton radioButton6;

    @FXML
    private RadioButton radioButton7;

    @FXML
    private RadioButton radioButton8;

    @FXML
    private TextField textField1;

    @FXML
    private Button sendSettingsButton;

    @FXML
    private Button getStatusButton;

    @FXML
    private Button startButton;

    @FXML
    void startButtonClick(ActionEvent event) throws Exception {

        final Node source = (Node) event.getSource();
        final Stage stage2 = (Stage) source.getScene().getWindow();
        stage2.close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GameMenu.fxml"));
        Parent root1 = null;
        try {
            root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Game menu");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }




    @FXML
    void getDifficulty(ActionEvent event) {
        if (radioButton1.isSelected()) {
            this.difficulty = Difficulty.EASY;
        }
        if (radioButton2.isSelected()) {
            this.difficulty = Difficulty.MEDIUM;
        }
        if (radioButton3.isSelected()) {
            this.difficulty = Difficulty.HARD;
        }
        if (radioButton4.isSelected()) {
            this.difficulty = Difficulty.IMPOSSIBLE;
        }
    }

    @FXML
    void getRange(ActionEvent event) {
        if (radioButton5.isSelected()) {
            this.range = Range.TEN;
        }
        if (radioButton6.isSelected()) {
            this.range = Range.HUNDRED;
        }
        if (radioButton7.isSelected()) {
            this.range = Range.THOUSAND;
        }
        if (radioButton8.isSelected()) {
            this.range = Range.TENTHOUSAND;
        }
    }

    //Послать настройки
    @FXML
    void sendSettingsButtonClick(ActionEvent event) throws IOException {
    //Послать настройки другому игроку
        //Это право есть только у Сервера


        if(GlobalVar.peer.getClass().getSimpleName().equals("GuigProtocolServer")){
            GlobalVar.peer.sendObj(GlobalVar.serverMode);
            getSettingsButton.setDisable(false);
        }

        //Если это клиент, то модифицируем
        if(GlobalVar.peer.getClass().getSimpleName().equals("GuigProtocolClient")){
            GlobalVar.NUMBER_RECEIVED=GlobalVar.clientMode.getTaskNumber();
            GlobalVar.serverMode=GlobalVar.clientMode;
            GlobalVar.serverMode.setTaskNumber(Integer.parseInt(textField1.getText()));


            GlobalVar.peer.sendObj(GlobalVar.serverMode);
            System.out.println("Посылаю обьект Серверу: "+GlobalVar.serverMode);
           GlobalVar.clientMode.setTaskNumber(GlobalVar.NUMBER_RECEIVED);


        }



    }
    //Получить настройки
    @FXML
    void getSettingsButtonClick(ActionEvent event) throws IOException, ClassNotFoundException {
        //Получаем обьект, преобразуем его в МОДУ

        //Если настройки получает клиент,
        if(GlobalVar.peer.getClass().getSimpleName().equals("GuigProtocolClient")){
            Object object=GlobalVar.peer.getObj();
            GlobalVar.clientMode=(Mode) object;
            System.out.println("Мои настройки:"+GlobalVar.clientMode);
            //Получаем доступ к отправки настроек
            sendSettingsButton.setDisable(false);
        }

        if(GlobalVar.peer.getClass().getSimpleName().equals("GuigProtocolServer")){

            Object object=GlobalVar.peer.getObj();
            System.out.println("До изменения МодСервер: "+GlobalVar.serverMode);
            GlobalVar.serverMode=(Mode) object;

            var tmp= (Mode)GlobalVar.peer.getObj();
            System.out.println("После прихода Мода Сервера:"+tmp);


//            String str=GlobalVar.peer.receive();
//            System.out.println("Получил строку сервер: "+str);
//            System.out.println(GlobalVar.serverMode);
            //Получаем доступ к отправки настроек
        }
    }


    @FXML
    void getStatusButtonClick(ActionEvent event) {
        if(GlobalVar.peer.getClass().getSimpleName().equals("GuigProtocolClient")){
            System.out.println("КлиентМод: "+GlobalVar.clientMode);
        }
        if(GlobalVar.peer.getClass().getSimpleName().equals("GuigProtocolServer")){
            System.out.println("СерверМод: "+GlobalVar.serverMode);
        }

    }

    @FXML
    void connectButtonClick(ActionEvent event) {
        //Проверка, что выбрано и загадано число
        //Только потом можно будет перейти к коннекту
        if (this.range!=null&&this.difficulty!=null&&textField1!=null){

            if(GlobalVar.peer.getClass().getSimpleName().equals("GuigProtocolServer")) {
                GlobalVar.serverMode = new Mode(this.range, this.difficulty);
            }

            System.out.println(GlobalVar.mode);
            //Проверка на подходимость числа
            if(DefinerNumber.checkNumberBoundaries(textField1.getText(),GlobalVar.serverMode)){
                //Заносим в глобальную переменную
                // GlobalVar.NumberForPartner=Integer.parseInt(textField1.getText());
                GlobalVar.serverMode.setTaskNumber(Integer.parseInt(textField1.getText()));
            } else {
                System.out.println("Не могу внести значение");
            }
        }
        else {
            System.out.println("Введите нужные значения");
        }

        try {
            GlobalVar.peer.connect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



//    void redirectToGameMenu(ActionEvent event) throws IOException {
//        final Node source = (Node) event.getSource();
//        final Stage stage2 = (Stage) source.getScene().getWindow();
//        stage2.close();
//
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameMenu.fxml"));
//        Parent root1 = null;
//        root1 = (Parent) fxmlLoader.load();
//        Stage stage = new Stage();
//        stage.setTitle("Game Menu");
//        stage.setScene(new Scene(root1));
//        stage.show();
//    }


    @FXML
    void initialize() {
        String myStatus;

        if(GlobalVar.peer.getClass().getSimpleName().equals("GuigProtocolServer")){
            labelStatus.setText("Мой статус: Сервер");
            getSettingsButton.setDisable(true);

        }
        else {
            labelStatus.setText("Мой статус: Клиент");
            //Отключение всех радиобаттанов
            sendSettingsButton.setDisable(true);
            radioButton1.setDisable(true);
            radioButton2.setDisable(true);
            radioButton3.setDisable(true);
            radioButton4.setDisable(true);
            radioButton5.setDisable(true);
            radioButton6.setDisable(true);
            radioButton7.setDisable(true);
            radioButton8.setDisable(true);

        }



        assert connectButton != null : "fx:id=\"connectButton\" was not injected: check your FXML file 'SettingsMenu.fxml'.";
        assert difficulty != null : "fx:id=\"difficulty\" was not injected: check your FXML file 'SettingsMenu.fxml'.";
        assert labelStatus != null : "fx:id=\"labelStatus\" was not injected: check your FXML file 'SettingsMenu.fxml'.";
        assert range != null : "fx:id=\"range\" was not injected: check your FXML file 'SettingsMenu.fxml'.";

    }

}
