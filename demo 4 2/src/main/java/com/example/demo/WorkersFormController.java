package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class WorkersFormController implements Initializable {
    public TableView mainTable;

    private ObservableList<Workers> workersList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        TableColumn<Workers, Integer> kodColumn = new TableColumn<>("Код работника");
        kodColumn.setCellValueFactory(new PropertyValueFactory<>("kod"));

        TableColumn<Workers, String> surnameColumn = new TableColumn<>("Фамилия");
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));

        TableColumn<Workers, String> nameColumn = new TableColumn<>("Имя");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Workers, String> middleNameColumn = new TableColumn<>("Отчество");
        middleNameColumn.setCellValueFactory(new PropertyValueFactory<>("middleName"));

        TableColumn<Workers, String> postColumn = new TableColumn<>("Должность");
        postColumn.setCellValueFactory(new PropertyValueFactory<>("post"));

        TableColumn<Workers, String> phoneNumberColumn = new TableColumn<>("Номер телефона");
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        TableColumn<Workers, String> adressColumn = new TableColumn<>("Адрес");
        adressColumn.setCellValueFactory(new PropertyValueFactory<>("adress"));

        TableColumn<Workers, Integer> passportSeriesColumn = new TableColumn<>("Серия паспорта");
        passportSeriesColumn.setCellValueFactory(new PropertyValueFactory<>("passportSeries"));

        TableColumn<Workers, Integer> passportNumberColumn = new TableColumn<>("Номер паспорта");
        passportNumberColumn.setCellValueFactory(new PropertyValueFactory<>("passportNumber"));

        // подцепляем столбцы к таблице
        mainTable.getColumns().addAll(kodColumn, nameColumn, surnameColumn, middleNameColumn, postColumn, phoneNumberColumn, adressColumn, passportSeriesColumn, passportNumberColumn);

        workersList = FXCollections.observableArrayList();
        loadData();

        mainTable.setItems(workersList);
    }

    private void loadData() {
        try {
            // Connect to the database
            Connection conn = ConnectionUtil.connectdb();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM workers");

            // Iterate through the ResultSet and create Employee objects for each row
            while (rs.next()) {
                int kod = rs.getInt("Kod_workers");
                String surname = rs.getString("Surname");
                String name = rs.getString("Name");
                String middleName = rs.getString("Middle_name");
                String post = rs.getString("Post");
                String phoneNumber = rs.getString("Phone_number");
                String adress = rs.getString("Adress");
                int passportSeries = rs.getInt("Passport_series");
                int passportNumber = rs.getInt("Passport_number");
                workersList.add(new Workers(kod, surname, name, middleName, post, phoneNumber, adress, passportSeries, passportNumber));
            }

            // Close the connection
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onAddClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("WorkersAddForm.fxml"));
        Parent root = loader.load();

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
        WorkersForm controller = loader.getController();
        // проверяем что наали кнопку save
        if (controller.getModalResult()) {
            // собираем еду с формы
            Workers newWorkers = controller.getWorkers();
            // добавляем в список
            this.workersList.add(newWorkers);
        }

    }

    public void onDeleteClick(ActionEvent actionEvent) {
        try
        {
            Workers selectedrow = (Workers) mainTable.getSelectionModel().getSelectedItem();

            Connection conn = ConnectionUtil.connectdb();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM workers WHERE Kod_workers = ?");

            ps.setInt(1, selectedrow.getKod());

            ps.execute();
            conn.close();
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }
}

