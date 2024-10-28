package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class FilmController implements Initializable {

        public TableView mainTable;

        private ObservableList<Films> filmsList;

        @Override
        public void initialize(URL location, ResourceBundle resources) {

            TableColumn<Films, Integer> idColumn = new TableColumn<>("Номер");
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

            TableColumn<Films, String> titleColumn = new TableColumn<>("Название");
            titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

            TableColumn<Films, String> timeStartColumn = new TableColumn<>("Начало сеанса");
            timeStartColumn.setCellValueFactory(new PropertyValueFactory<>("timeStart"));

            TableColumn<Films, String> timeEndColumn = new TableColumn<>("Конец сеанса");
            timeEndColumn.setCellValueFactory(new PropertyValueFactory<>("timeEnd"));

            // подцепляем столбцы к таблице
            mainTable.getColumns().addAll(idColumn, titleColumn, timeStartColumn, timeEndColumn);

            filmsList = FXCollections.observableArrayList();
            loadData();

            mainTable.setItems(filmsList);
        }

    private void loadData() {
        try {
            // Connect to the database
            Connection conn = ConnectionUtil.connectdb();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM films");

            // Iterate through the ResultSet and create Employee objects for each row
            while (rs.next()) {
                int id = rs.getInt("Film_number");
                String title = rs.getString("Name");
                String timeStart = rs.getString("Session_start");
                String timeEnd = rs.getString("End_of_session");
                filmsList.add(new Films(id, title,  timeStart, timeEnd));
            }

            // Close the connection
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        // добавляем инфу что наш код может выбросить ошибку IOException
        public void onAddClick(ActionEvent actionEvent) throws IOException {
            // эти три строчки создюат форму из fxml файлика
            // в принципе можно было бы обойтись
            // Parent root = FXMLLoader.load(getClass().getResource("FoodForm.fxml"));
            // но дальше вот это разбиение на три строки упростит нам жизнь
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("FilmAddForm.fxml"));
            Parent root = loader.load();

            // ну а тут создаем новое окно
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            // указываем что оно модальное
            stage.initModality(Modality.WINDOW_MODAL);
            // указываем что оно должно блокировать главное окно
            // ну если точнее, то окно, на котором мы нажали на кнопку
            stage.initOwner(this.mainTable.getScene().getWindow());

            // открываем окно и ждем пока его закроют
            stage.showAndWait();

            // вытаскиваем контроллер который привязан к форме
            // вытаскиваем контроллер который привязан к форме
            FilmFormController controller = loader.getController();
            // проверяем что наали кнопку save
            if (controller.getModalResult()) {
                // собираем еду с формы
                Films newFilms = controller.getFilms();
                // добавляем в список
                this.filmsList.add(newFilms);
            }
        }


        public void onDeleteClick(ActionEvent actionEvent) {
                try
                {
                    Films selectedrow = (Films) mainTable.getSelectionModel().getSelectedItem();

                    Connection conn = ConnectionUtil.connectdb();
                    PreparedStatement ps = conn.prepareStatement("DELETE FROM films WHERE Film_number = ?");

                    ps.setInt(1, selectedrow.getId());

                    ps.execute();
                    conn.close();
                }
                catch (SQLException e)
                {
                    System.out.println(e);
                }
        }
    }
