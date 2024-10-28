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

public class TicketsController implements Initializable {
    public TableView mainTable;

    private ObservableList<Tickets> ticketsList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        TableColumn<Tickets, Integer> numberColumn = new TableColumn<>("Номер билета");
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));

        TableColumn<Tickets, Integer> priceColumn = new TableColumn<>("Цена");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        mainTable.getColumns().addAll(numberColumn, priceColumn);

        ticketsList = FXCollections.observableArrayList();
        loadData();

        mainTable.setItems(ticketsList);
    }

        private void loadData() {
            try {
                // Connect to the database
                Connection conn = ConnectionUtil.connectdb();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM tickets");

                // Iterate through the ResultSet and create Employee objects for each row
                while (rs.next()) {
                    int number = rs.getInt("Ticket_number");
                    int price = rs.getInt("Price");
                    ticketsList.add(new Tickets(number, price));
                }

                // Close the connection
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    public void onAddClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("TicketsAddForm.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(this.mainTable.getScene().getWindow());

        stage.showAndWait();

        TicketsFormController controller = loader.getController();
        if (controller.getModalResult()) {
            Tickets newTickets = controller.getTickets();
            this.ticketsList.add(newTickets);
        }

    }

    public void onDeleteClick(ActionEvent actionEvent) {
            try
            {
                Tickets selectedrow = (Tickets) mainTable.getSelectionModel().getSelectedItem();

                Connection conn = ConnectionUtil.connectdb();
                PreparedStatement ps = conn.prepareStatement("DELETE FROM tickets WHERE Ticket_number = ?");

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


