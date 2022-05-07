package com.example.javafxproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class register {

    @FXML
    private Label error;

    @FXML
    private Label regconfirmation;

    @FXML
    private Button loginbutton1;

    @FXML
    private PasswordField rcpassword;

    @FXML
    private Button registerbutton;

    @FXML
    private PasswordField rpassword;

    @FXML
    private TextField rregno;

    @FXML
    private TextField ruser;

    @FXML
    void handleButtonActionRegisterUser(ActionEvent event) {
        String rruser = ruser.getText();
        String regno = rregno.getText();
        String pass = rpassword.getText();
        String cpass = rcpassword.getText();
        System.out.println(rruser);

        if (rruser=="") {
            error.setText("Enter Valid Username");
        } else {
            if (regno.matches("[0-9]+[a-zA-Z]+[0-9]+") == false) {
                error.setText("Enter Valid Registration Number");
            } else {
                if (pass.matches("[a-zA-Z]{5}+[0-9]+") == false) {
                    error.setText("Password should have Min 5 Characters and numbers");
                } else {
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root", "");

                        PreparedStatement statement = con.prepareStatement("select * from registration where username='" + rruser + "';");
                        ResultSet rs = statement.executeQuery();
                        if (rs.next()) {
                            error.setText("Username already exists!");
                        } else {
                            System.out.println(pass);
                            System.out.println(pass.compareTo(cpass));

                            if (pass.compareTo(cpass) == 0) {
                                Statement s = con.createStatement();
                                String s1 = "insert into registration values ('" + ruser.getText() + "','" + rregno.getText() + "','" + rpassword.getText() + "','" + rcpassword.getText() + "');";
                                s.executeUpdate(s1);
                                System.out.println("success");
                                regconfirmation.setText("Registration Successfull");
                                error.setText("");
                            } else {
                                error.setText("Passwords do NOT match!");
                            }
                        }
                        con.close();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        }
    }

    @FXML
    void handleButtonActionBack(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
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
}
