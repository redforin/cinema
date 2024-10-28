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

public class FilmsHasHallsController implements Initializable {
    public TableView mainTable;

    private ObservableList<FilmsHasHalls> filmsHasHallsList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        TableColumn<FilmsHasHalls, Integer> kodColumn = new TableColumn<>("Номер фильма");
        kodColumn.setCellValueFactory(new PropertyValueFactory<>("kod"));

        TableColumn<FilmsHasHalls, Integer> numberColumn = new TableColumn<>("Номер зала");
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));

        mainTable.getColumns().addAll(kodColumn, numberColumn);

        filmsHasHallsList = FXCollections.observableArrayList();
        loadData();

        mainTable.setItems(filmsHasHallsList);
    }

    private void loadData() {
        try {
            // Connect to the database
            Connection conn = ConnectionUtil.connectdb();;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM films_has_halls");

            // Iterate through the ResultSet and create Employee objects for each row
            while (rs.next()) {
                int kod = rs.getInt("Film_number");
                int number = rs.getInt("Hall_number");
                filmsHasHallsList.add(new FilmsHasHalls(kod, number));
            }

            // Close the connection
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onAddClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FilmsHasHallsAddForm.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(this.mainTable.getScene().getWindow());

        stage.showAndWait();

        FilmsHasHallsFormController controller = loader.getController();
        if (controller.getModalResult()) {
            FilmsHasHalls newFilmsHasHalls = controller.getFilmsHasHalls();
            this.filmsHasHallsList.add(newFilmsHasHalls);
        }

    }

    public void onDeleteClick(ActionEvent actionEvent) {
            try
            {
                FilmsHasHalls selectedrow = (FilmsHasHalls) mainTable.getSelectionModel().getSelectedItem();

                Connection conn = ConnectionUtil.connectdb();
                PreparedStatement ps = conn.prepareStatement("DELETE FROM films_has_halls WHERE Film_number = ?");

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

