package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class WorkersHasHallsFormController {

    @FXML
    private Connection con;
    @FXML
    private Statement stmt;
    public TextField txtWHHKod;
    public TextField txtWHHNumber;

    // добавляем новое поле
    private Boolean modalResult = false;

    public void onSaveClick(ActionEvent actionEvent) {
        this.modalResult = true;
        ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
    }

    public void onCancelClick(ActionEvent actionEvent) {
        this.modalResult = false;
        ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
    }

    public Boolean getModalResult() {
        return modalResult;
    }

    public WorkersHasHalls getWorkersHasHalls() {
        try {
            con = ConnectionUtil.connectdb();
            stmt = con.createStatement();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Unable to connect to database " + e.getMessage());
        }


        try {
            String sql;
            sql = "insert into workers_has_halls(Kod_workers,Hall_number) values ("
                    + txtWHHKod.getText() + ","
                    + txtWHHNumber.getText() + ")";

            int rs = stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Информация сохранёна");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Невозможно вставить запись " + e.getMessage());
        }

        return null;
    }
}

