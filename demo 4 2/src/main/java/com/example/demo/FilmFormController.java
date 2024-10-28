package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.*;
import java.sql.*;

public class FilmFormController {

    @FXML
    private Connection con;

    @FXML
    private Statement stmt;

    public TextField txtFilmsId;
    public TextField txtFilmsTitle;
    public TextField txtFilmsTimeStart;
    public TextField txtFilmsTimeEnd;


    // добавляем новое поле
    private Boolean modalResult = false;


    // добавляем новое поле
    public void onSaveClick(ActionEvent actionEvent) {
        this.modalResult = true; // ставим результат модального окна на true
        // закрываем окно к которому привязана кнопка
        ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
    }

    public void onCancelClick(ActionEvent actionEvent) {
        this.modalResult = false; // ставим результат модального окна на false
        // закрываем окно к которому привязана кнопка
        ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
    }

    // геттер для результата модального окна
    public Boolean getModalResult() {
        return modalResult;
    }


    public Films getFilms() {
        try {
            con = ConnectionUtil.connectdb();
            stmt = con.createStatement();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Unable to connect to database " + e.getMessage());
        }


        try {
            String sql;
            sql = "insert into films(Film_number,Name,Session_start,End_of_session) values ("
                    + txtFilmsId.getText() + ","
                    + "'" + txtFilmsTitle.getText() + "',"
                    + "'" + txtFilmsTimeStart.getText() + "',"
                    + "'" + txtFilmsTimeEnd.getText() + "')";

            int rs = stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Информация сохранёна");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Unable to insert record " + e.getMessage());
        }

        return null;
    }
}

