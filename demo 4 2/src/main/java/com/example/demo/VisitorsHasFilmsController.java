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

public class VisitorsHasFilmsController implements Initializable {
    public TableView mainTable;

    private ObservableList<VisitorsHasFilms> visitorsHasFilmsList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        TableColumn<VisitorsHasFilms, Integer> kodColumn = new TableColumn<>("Номер посетителя");
        kodColumn.setCellValueFactory(new PropertyValueFactory<>("kod"));

        TableColumn<VisitorsHasFilms, Integer> numberColumn = new TableColumn<>("Номер фильма");
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));

        mainTable.getColumns().addAll(kodColumn, numberColumn);

        visitorsHasFilmsList = FXCollections.observableArrayList();
        loadData();

        mainTable.setItems(visitorsHasFilmsList);
    }

    private void loadData() {
        try {
            // Connect to the database
            Connection conn = ConnectionUtil.connectdb();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM visitors_has_films");

            // Iterate through the ResultSet and create Employee objects for each row
            while (rs.next()) {
                int kod = rs.getInt("Visitor_s_number");
                int number = rs.getInt("Film_number");
                visitorsHasFilmsList.add(new VisitorsHasFilms(kod, number));
            }

            // Close the connection
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onAddClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("VisitorsHasFilmsAddForm.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(this.mainTable.getScene().getWindow());

        stage.showAndWait();

        VisitorsHasFilmsFormController controller = loader.getController();
        if (controller.getModalResult()) {
            VisitorsHasFilms newVisitorsHasFilms = controller.getVisitorsHasFilms();
            this.visitorsHasFilmsList.add(newVisitorsHasFilms);
        }

    }

    public void onDeleteClick(ActionEvent actionEvent) {
        try
        {
            VisitorsHasFilms selectedrow = (VisitorsHasFilms) mainTable.getSelectionModel().getSelectedItem();

            Connection conn = ConnectionUtil.connectdb();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM visitors_has_films WHERE Visitor_s_number = ?");

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
