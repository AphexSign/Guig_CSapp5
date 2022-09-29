package com.example.guig_csapp5;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.guig_csapp5.Core.GlobalVar;
import com.example.guig_csapp5.Protocol.GuigProtocolClient;
import com.example.guig_csapp5.Protocol.GuigProtocolServer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ClientButton;

    @FXML
    private Button quitButton;

    @FXML
    private Button serverButton;

    @FXML
    void ClientButtonClick(ActionEvent event) throws IOException {
        GuigProtocolClient guigProtocolClient=new GuigProtocolClient("127.0.0.1",8000);
        GlobalVar.peer=guigProtocolClient;
        redirectToSettingsMenu(event);
        //Мы клиент, а значит у нас другие права. И нам будут не доступны настройки числа
        //Максимум нам могут выдать правила генерации числа от 0-100. И сложность.
    }

    @FXML
    void quitButtonClick(ActionEvent event) {
    System.exit(0);
    }

    @FXML
    void serverButtonClick(ActionEvent event) throws IOException {
        GuigProtocolServer guigProtocolServer=new GuigProtocolServer();
        GlobalVar.peer=guigProtocolServer;
        redirectToSettingsMenu(event);
        //Связь пока не устанавливаем
        //Но зато понимаем, что мы сервер - нам устанавливать правила игры
    }

    void redirectToSettingsMenu(ActionEvent event) throws IOException {
        final Node source = (Node) event.getSource();
        final Stage stage2 = (Stage) source.getScene().getWindow();
        stage2.close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SettingsMenu.fxml"));
        Parent root1 = null;
        root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Settings Menu");
        stage.setScene(new Scene(root1));
        stage.show();
    }




    @FXML
    void initialize() {
        assert ClientButton != null : "fx:id=\"ClientButton\" was not injected: check your FXML file 'startScreen.fxml'.";
        assert quitButton != null : "fx:id=\"quitButton\" was not injected: check your FXML file 'startScreen.fxml'.";
        assert serverButton != null : "fx:id=\"serverButton\" was not injected: check your FXML file 'startScreen.fxml'.";

    }

}
