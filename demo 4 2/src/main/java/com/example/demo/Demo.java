package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Demo {
    public void onFilmClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FilmsForm.fxml"));
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

    public void onWorkersClick(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("WorkersForm.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);

        stage.showAndWait();

    }

    public void onTicketsClick(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("TicketsForm.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);

        stage.showAndWait();
    }

    public void onVisitorsClick(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("VisitorsForm.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);

        stage.showAndWait();
    }

    public void onWorkersHasHallsClick(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("WorkersHasHallsForm.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        // указываем что оно модальное
        stage.initModality(Modality.WINDOW_MODAL);
        // указываем что оно должно блокировать главное окно

        // открываем окно и ждем пока его закроют
        stage.showAndWait();
    }

    public void onFilmsHasHallsClick(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FilmsHasHallsForm.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);

        stage.showAndWait();
    }


    public void onVisitorsHasFilmsClick(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("VisitorsHasFilmsForm.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);

        stage.showAndWait();
    }

    public void onHallsClick(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("HallsForm.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);

        stage.showAndWait();
    }
}
