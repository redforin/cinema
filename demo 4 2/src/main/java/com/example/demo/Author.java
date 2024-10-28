package com.example.demo;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Author implements Initializable {

    @FXML
    private TextField idLog;

    @FXML
    private PasswordField idPass;

    Stage dialogStage = new Stage();
    Scene scene;

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public Author() {
        connection = ConnectionUtil.connectdb();
    }

    public void loginAction(ActionEvent event){
        String name = idLog.getText().toString();
        String pass = idPass.getText().toString();

        String sql = "SELECT * FROM users WHERE name = ? and pass = ?";

        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, pass);
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()){
                infoBox("Пожалуйста, введите правильный логин и пароль", null, "Failed");
            }else{
                infoBox("Вход в систему завершен успешно",null,"Success" );
                Node node = (Node)event.getSource();
                dialogStage = (Stage) node.getScene().getWindow();
                dialogStage.close();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("hello-view.fxml"));
                Parent root = loader.load();

                // ну а тут создаем новое окно
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                // указываем что оно модальное
                stage.initModality(Modality.WINDOW_MODAL);
                // указываем что оно должно блокировать главное окно

                // открываем окно и ждем пока его закроют
                stage.showAndWait();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    public static void infoBox(String infoMessage, String headerText, String title){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {


    }

}