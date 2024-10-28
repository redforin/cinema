package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.sql.*;

public class HallsFormController {

    @FXML
    private Connection con;

    @FXML
    private Statement stmt;

    public TextField txtHallsNum;
    public TextField txtHallsSeats;
    public TextField txtHallsFormat;

    // добавляем новое поле
    private Boolean modalResult = false;

    // добавляем новое поле
    public void onSaveClick(ActionEvent actionEvent) {
        this.modalResult = true;
        ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
    }

    public void onCancelClick(ActionEvent actionEvent) {
        this.modalResult = false;
        ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
    }

    // геттер для результата модального окна
    public Boolean getModalResult() {
        return modalResult;
    }

    public Halls getHalls() {
        try {
            con = ConnectionUtil.connectdb();
            stmt = con.createStatement();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Unable to connect to database " + e.getMessage());
        }


        try {
            String sql;
            sql = "insert into halls(Hall_number,Number_of_seats,Screen_format) values ("
                    + txtHallsNum.getText() + ","
                    + txtHallsSeats.getText() + ","
                    + "'" + txtHallsFormat.getText() + "')";
            ;

            int rs = stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Информация сохранёна");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Unable to insert record " + e.getMessage());
        }

        return null;
    }
}
