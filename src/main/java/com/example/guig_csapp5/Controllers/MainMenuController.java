//package com.example.guig_csapp5.Controllers;
//
//import com.example.guig_csapp5.Core.CheckNumber;
//import com.example.guig_csapp5.Core.Connectable;
//import com.example.guig_csapp5.Core.GlobalVar;
//import com.example.guig_csapp5.Protocol.GuigProtocolClient;
//import com.example.guig_csapp5.Protocol.GuigProtocolServer;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//
//public class MainMenuController {
//
//    Connectable peer;
//
//    @FXML
//    private Button clientConnectButton;
//
//    @FXML
//    private Label label1;
//
//    @FXML
//    private Button quitButton;
//
//    @FXML
//    private Button serverConnectButton;
//
//    @FXML
//    private TextField textField;
//
//    @FXML
//    private TextField textField5;
//
//    @FXML
//    private Button getMsgButton;
//
//    @FXML
//    private Button checkNumber;
//
//    @FXML
//    void clientConnectButtonClick(ActionEvent event) throws IOException {
//        GuigProtocolClient guigProtocolClient=new GuigProtocolClient("127.0.0.1",8000);
//        guigProtocolClient.connect();
//        this.peer=guigProtocolClient;
//        label1.setText("Я - Клиент");
//        redirectToSettingsMenu(event);
//    }
//
//
//    void redirectToSettingsMenu(ActionEvent event) throws IOException {
//        final Node source = (Node) event.getSource();
//        final Stage stage2 = (Stage) source.getScene().getWindow();
//        stage2.close();
//
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SettingsMenu.fxml"));
//        Parent root1 = null;
//        root1 = (Parent) fxmlLoader.load();
//        Stage stage = new Stage();
//        stage.setTitle("Settings Menu");
//        stage.setScene(new Scene(root1));
//        stage.show();
//    }
//
//
//    @FXML
//    void quitButtonClick(ActionEvent event) {
//        System.exit(0);
//    }
//
//    @FXML
//    private Button sendMsgButton;
//
//
//    @FXML
//    void serverConnectButtonClick(ActionEvent event) throws IOException {
//        GuigProtocolServer guigProtocolServer=new GuigProtocolServer();
//        guigProtocolServer.connect();
//        this.peer=guigProtocolServer;
//        label1.setText("Я - Сервер");
//        redirectToSettingsMenu(event);
//    }
//
//    @FXML
//    void sendMsgButtonClick(ActionEvent event) {
//        send(peer,textField.getText());
//       // sendReceive(this.peer,"prot",textField.getText());
//    }
//
//    @FXML
//    void getMsgButton(ActionEvent event) {
//        receive(peer);
//      //  receiveSend(this.peer,textField.getText());
//    }
//
//    @FXML
//    void checkNumberClick(ActionEvent event) {
//        System.out.println(GlobalVar.NUMBER_RECEIVED);
//    }
//
//
//    @FXML
//    void checkAnswerButtonClick(ActionEvent event) {
//        if(!CheckNumber.check(textField5.getText())){
//            System.out.println("Не верно");
//        }
//        else {
//            System.out.println("Верно");
//        }
//    }
//
//
//    private static void send(Connectable peer,String msgSent){
//        peer.send(msgSent);
//    }
//
//    private static void receive(Connectable peer){
//        String remoteAnswer=peer.receive();
//        GlobalVar.NUMBER_RECEIVED=Integer.parseInt(remoteAnswer);
//    }
//


    //
//    private static String sendReceive(Connectable peer, String prot, String localCommand) {
//        String localResponse, remoteResponse;
//        peer.send(localCommand);
//        localResponse = "localResponse1";
//        remoteResponse = peer.receive();
//
////        if (!localResponse.equals(remoteResponse)) {
////            throw new IllegalStateException(
////                    String.format("Command %s processing returned %s from the local protocol and %s from the remote one",
////                            localCommand, localResponse, remoteResponse));
////        }
//        return remoteResponse;
//    }

    //Получить-отдать
//    private static String receiveSend(Connectable peer, String prot) {
//        String remoteAnswer = peer.receive();
//        GlobalVar.NUMBER_RECEIVED=Integer.parseInt(remoteAnswer);
//
//        String localResponse = prot;
//        peer.send(localResponse);
//        return localResponse;
////    }
//
//
//
//
//
//
//
//}