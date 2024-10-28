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

public class VisitorsController implements Initializable {
    public TableView mainTable;

    private ObservableList<Visitors> visitorsList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        TableColumn<Visitors, Integer> idVisitorColumn = new TableColumn<>("Номер посетителя");
        idVisitorColumn.setCellValueFactory(new PropertyValueFactory<>("idVisitor"));

        TableColumn<Visitors, String> surnameColumn = new TableColumn<>("Фамилия");
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));

        TableColumn<Visitors, String> nameColumn = new TableColumn<>("Имя");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Visitors, String> middleNameColumn = new TableColumn<>("Отчество");
        middleNameColumn.setCellValueFactory(new PropertyValueFactory<>("middleName"));

        TableColumn<Visitors, String> phoneNumberColumn = new TableColumn<>("Номер телефона");
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        TableColumn<Visitors, Integer> ticketsNumberColumn = new TableColumn<>("Номер билета");
        ticketsNumberColumn.setCellValueFactory(new PropertyValueFactory<>("ticketsNumber"));


        // подцепляем столбцы к таблице
        mainTable.getColumns().addAll(idVisitorColumn, surnameColumn, middleNameColumn, phoneNumberColumn, ticketsNumberColumn);

        visitorsList = FXCollections.observableArrayList();
        loadData();

        mainTable.setItems(visitorsList);
    }

    private void loadData() {
        try {
            // Connect to the database
            Connection conn = ConnectionUtil.connectdb();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM visitors");

            // Iterate through the ResultSet and create Employee objects for each row
            while (rs.next()) {
                int idVisitor = rs.getInt("Visitor_s_number");
                String surname = rs.getString("Surname");
                String name = rs.getString("Name");
                String middleName = rs.getString("Middle_name");
                String phoneNumber = rs.getString("Phone_number");
                int ticketsNumber = rs.getInt("Ticket_number");
                visitorsList.add(new Visitors(idVisitor, surname, name, middleName, phoneNumber, ticketsNumber));
            }

            // Close the connection
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onAddClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("VisitorsAddForm.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(this.mainTable.getScene().getWindow());

        stage.showAndWait();

        VisitorsFormController controller = loader.getController();
        if (controller.getModalResult()) {
            Visitors newVisitors = controller.getVisitors();
            this.visitorsList.add(newVisitors);
        }

    }

    public void onDeleteClick(ActionEvent actionEvent) {
            try
            {
                Visitors selectedrow = (Visitors) mainTable.getSelectionModel().getSelectedItem();

                Connection conn = ConnectionUtil.connectdb();
                PreparedStatement ps = conn.prepareStatement("DELETE FROM visitors WHERE Visitor_s_number = ?");

                ps.setInt(1, selectedrow.getIdVisitor());

                ps.execute();
                conn.close();
            }
            catch (SQLException e)
            {
                System.out.println(e);
            }
        }
    }

