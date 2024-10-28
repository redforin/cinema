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

public class WorkersForm {
    @FXML
    private Connection con;
    @FXML
    private Statement stmt;

    public TextField txtWorkersKod;
    public TextField txtWorkersSurname;
    public TextField txtWorkersName;
    public TextField txtWorkersMiddleName;
    public TextField txtWorkersPost;
    public TextField txtWorkersPhoneNumber;
    public TextField txtWorkersAdress;
    public TextField txtWorkersPassportSeries;
    public TextField txtWorkersPassportNumber;

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



    public Workers getWorkers() {
        try {
            con = ConnectionUtil.connectdb();
            stmt = con.createStatement();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Unable to connect to database " + e.getMessage());
        }


        try {
            String sql;
            sql = "insert into workers(Kod_workers,Surname,Name,Middle_name,Post,Phone_number,Adress,Passport_series,Passport_number) values ("
                    + txtWorkersKod.getText() + ","
                    + "'" + txtWorkersSurname.getText() + "',"
                    + "'" + txtWorkersName.getText() + "',"
                    + "'" + txtWorkersMiddleName.getText() + "',"
                    + "'" + txtWorkersPost.getText() + "',"
                    + "'" + txtWorkersPhoneNumber.getText() + "',"
                    + "'" + txtWorkersAdress.getText() + "',"
                    + txtWorkersPassportSeries.getText() + ","
                    + txtWorkersPassportNumber.getText() + ")";

            int rs = stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Информация сохранёна");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Unable to insert record " + e.getMessage());
        }

        return null;
    }
}

