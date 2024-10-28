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

public class WorkersHasHallsController implements Initializable {
    public TableView mainTable;

    private ObservableList<WorkersHasHalls> workHasHallList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        TableColumn<WorkersHasHalls, Integer> kodColumn = new TableColumn<>("Номер работника");
        kodColumn.setCellValueFactory(new PropertyValueFactory<>("kod"));

        TableColumn<WorkersHasHalls, Integer> numberColumn = new TableColumn<>("Номер зала");
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));

        mainTable.getColumns().addAll(kodColumn, numberColumn);

        workHasHallList = FXCollections.observableArrayList();
        loadData();

        mainTable.setItems(workHasHallList);
    }

    private void loadData() {
        try {
            // Connect to the database
            Connection conn = ConnectionUtil.connectdb();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM workers_has_halls");

            // Iterate through the ResultSet and create Employee objects for each row
            while (rs.next()) {
                int kod = rs.getInt("Kod_workers");
                int number = rs.getInt("Hall_number");
                workHasHallList.add(new WorkersHasHalls(kod, number));
            }

            // Close the connection
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onAddClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("WorkersHasHallsAddForm.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(this.mainTable.getScene().getWindow());

        stage.showAndWait();

        WorkersHasHallsFormController controller = loader.getController();
        if (controller.getModalResult()) {
            WorkersHasHalls newWorkersHasHalls = controller.getWorkersHasHalls();
            this.workHasHallList.add(newWorkersHasHalls);
        }

    }

    public void onDeleteClick(ActionEvent actionEvent) {
        try
        {
            WorkersHasHalls selectedrow = (WorkersHasHalls) mainTable.getSelectionModel().getSelectedItem();

            Connection conn = ConnectionUtil.connectdb();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM workers_has_halls WHERE Kod_workers = ?");

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

