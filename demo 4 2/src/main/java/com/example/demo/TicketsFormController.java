package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TicketsFormController {
    @FXML
    private Connection con;
    @FXML
    private Statement stmt;

        public TextField txtTicketsNumber;
        public TextField txtTicketsPrice;

        // добавляем новое поле
        private Boolean modalResult = false;

        // добавляем новое поле
        public void onSaveClick(ActionEvent actionEvent) {
            this.modalResult = true; // ставим результат модального окна на true
            // закрываем окно к которому привязана кнопка
            ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
        }

        public void onCancelClick(ActionEvent actionEvent) {
            this.modalResult = false; // ставим результат модального окна на false
            // закрываем окно к которому привязана кнопка
            ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
        }

        // геттер для результата модального окна
        public Boolean getModalResult() {
            return modalResult;
        }



        public Tickets getTickets() {
            try {
                con = ConnectionUtil.connectdb();
                stmt = con.createStatement();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Unable to connect to database " + e.getMessage());
            }


            try {
                String sql;
                sql = "insert into tickets(Ticket_number,Price) values ("
                        + txtTicketsNumber.getText() + ","
                        + txtTicketsPrice.getText() + ")";

                int rs = stmt.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Информация сохранёна");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Unable to insert record " + e.getMessage());
            }

            return null;
        }
    }

