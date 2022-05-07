package com.example.javafxproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class BookHotel implements Initializable {
    String hote_id;
    String hname, u;
    @FXML
    private Label bhid;

    @FXML
    private DatePicker cindate;

    @FXML
    private DatePicker coutdate;

    @FXML
    private Label hotelname;

    @FXML
    private ChoiceBox<Integer> nrooms;

    @FXML
    private Label bookconf;

    @FXML
    void backbookHot(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("functionalities.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void handleButtonActionConfirmBooking(ActionEvent event) {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject?autoReconnect=true&useSSL=false", "root", "");
            Statement s = con.createStatement();
            String s1 = "insert into booking values ('" + hote_id + "','" + hname + "','" + cindate.getValue() + "','" + coutdate.getValue() + "','"+nrooms.getValue()+"','"+HelloApplication.getUser()+"');";

            s.executeUpdate(s1);
            System.out.println("success");
            bookconf.setText("Booking Confirmed");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hote_id = Viewhotels.getHid();
        ObservableList hrooms = FXCollections.observableArrayList();
        hrooms.addAll(1,2,3,4,5);
        nrooms.setItems(hrooms);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root", "");

            PreparedStatement statement = con.prepareStatement("select * from hotels where id='"+hote_id+"';");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                bhid.setText(rs.getString(1));
                hotelname.setText(rs.getString(2));
                hname = rs.getString(2);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
