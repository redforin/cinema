package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class HallsController implements Initializable {
    public TableView mainTable;

    private ObservableList<Halls> hallsList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        TableColumn<Halls, Integer> numberColumn = new TableColumn<>("Номер зала");
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));

        TableColumn<Halls, Integer> seatsColumn = new TableColumn<>("Количество мест");
        seatsColumn.setCellValueFactory(new PropertyValueFactory<>("seats"));

        TableColumn<Halls, String> formatColumn = new TableColumn<>("Формат");
        formatColumn.setCellValueFactory(new PropertyValueFactory<>("format"));

        mainTable.getColumns().addAll(numberColumn, seatsColumn, formatColumn);

        hallsList = FXCollections.observableArrayList();
        loadData();

        mainTable.setItems(hallsList);
    }

    private void loadData() {
        try {
            // Connect to the database
            Connection conn = ConnectionUtil.connectdb();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM halls");

            // Iterate through the ResultSet and create Employee objects for each row
            while (rs.next()) {
                int number = rs.getInt("Hall_number");
                int seats = rs.getInt("Number_of_seats");
                String format = rs.getString("Screen_format");
                hallsList.add(new Halls(number, seats, format));
            }

            // Close the connection
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onAddClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("HallsAddForm.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(this.mainTable.getScene().getWindow());

        stage.showAndWait();

        HallsFormController controller = loader.getController();
        if (controller.getModalResult()) {
            Halls newHalls = controller.getHalls();
            this.hallsList.add(newHalls);
        }

    }

    public void onDeleteClick(ActionEvent actionEvent) {
            try
            {
                Halls selectedrow = (Halls) mainTable.getSelectionModel().getSelectedItem();

                Connection conn = ConnectionUtil.connectdb();
                PreparedStatement ps = conn.prepareStatement("DELETE FROM halls WHERE Hall_number = ?");

                ps.setInt(1, selectedrow.getNumber());

                ps.execute();
                conn.close();
            }
            catch (SQLException e)
            {
                System.out.println(e);
            }
        }
    }
