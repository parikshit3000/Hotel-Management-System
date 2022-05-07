package com.example.javafxproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class login implements Initializable {

    @FXML
    private Label l1;

    @FXML
    private Button login;

    @FXML
    private Button login1;

    @FXML
    private PasswordField upass;

    @FXML
    private TextField usern;

    @FXML
    void handleButtonActionLogin(ActionEvent event) {
        HelloApplication.setUser(usern.getText());
        String uname = usern.getText();
        String passwo = upass.getText();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root", "");

            PreparedStatement statement = con.prepareStatement("select * from registration where username='" + uname + "';");
            PreparedStatement statement1 = con.prepareStatement("select * from registration where pass='" + passwo + "';");
            ResultSet rs = statement.executeQuery();
            ResultSet rs1 = statement1.executeQuery();

            if (rs.next()) {
                if (rs1.next()) {
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("functionalities.fxml"));
                    Scene scene = null;
                    try {
                        scene = new Scene(fxmlLoader.load());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } else {
                    l1.setText("Password do NOT Match");
                }
            } else {
                l1.setText("Username do NOT Exist");
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @FXML
    void handleButtonActionRegister(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("register.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        l1.setText("Welcome to Apna Hotels");
    }
}
