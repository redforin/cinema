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

public class VisitorsHasFilmsFormController {
    @FXML
    private Connection con;
    @FXML
    private Statement stmt;

    public TextField txtVHFKod;
    public TextField txtVHFNumber;

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

    public VisitorsHasFilms getVisitorsHasFilms() {
        try {
            con = ConnectionUtil.connectdb();
            stmt = con.createStatement();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Unable to connect to database " + e.getMessage());
        }


        try {
            String sql;
            sql = "insert into visitors_has_films(Visitor_s_number,Film_number) values ("
                    + txtVHFKod.getText() + ","
                    + txtVHFNumber.getText() + ")";

            int rs = stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Информация сохранёна");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Unable to insert record " + e.getMessage());
        }

        return null;
    }
}

