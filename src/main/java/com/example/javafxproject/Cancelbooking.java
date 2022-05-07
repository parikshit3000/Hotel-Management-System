package com.example.javafxproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Cancelbooking implements Initializable {
    String user;
    String hotel_id;

    @FXML
    private Label loginname;

    @FXML
    private Label canbookfeedback;

    @FXML
    private TableColumn<viewbookingmodel, String> vcindate;

    @FXML
    private TextField canhid;

    @FXML
    private TableColumn<viewbookingmodel, String> vcoutdate;

    @FXML
    private TableColumn<viewbookingmodel, String> vgname;

    @FXML
    private TableColumn<viewbookingmodel, String> vhid;

    @FXML
    private TableColumn<viewbookingmodel, String> vhname;

    @FXML
    private TableView<viewbookingmodel> vhtable;

    @FXML
    private TableColumn<viewbookingmodel, Integer> vrooms;

    @FXML
    void handleButtonActionCancelBooking(ActionEvent event) {
        hotel_id=canhid.getText();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root", "");

            Statement s = con.createStatement();

            String s1 = "delete from booking where huser='"+user+"' and hid='"+hotel_id+"';";
            s.executeUpdate(s1);
            System.out.println("Success");
            con.close();
            canbookfeedback.setText("Booking Successfully Cancelled!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void backviewbook(ActionEvent event) {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user=HelloApplication.getUser();
        loginname.setText(user);
        vhid.setCellValueFactory(new PropertyValueFactory<viewbookingmodel, String>("hid"));
        vhname.setCellValueFactory(new PropertyValueFactory<viewbookingmodel, String>("hname"));
        vcindate.setCellValueFactory(new PropertyValueFactory<viewbookingmodel, String>("cindate"));
        vcoutdate.setCellValueFactory(new PropertyValueFactory<viewbookingmodel, String>("coutdate"));
        vrooms.setCellValueFactory(new PropertyValueFactory<viewbookingmodel, Integer>("nrooms"));
        vgname.setCellValueFactory(new PropertyValueFactory<viewbookingmodel, String>("huser"));

        ObservableList<viewbookingmodel> bookings = FXCollections.observableArrayList();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root", "");

            PreparedStatement statement = con.prepareStatement("select * from booking where huser='"+user+"';");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String s0 = rs.getString(1);
                String s1 = rs.getString(2);
                String s2 = rs.getString(3);
                String s3 = rs.getString(4);
                int s4 = rs.getInt(5);
                String s5 = rs.getString(6);

                bookings.add(new viewbookingmodel(s0, s1, s2, s3, s4, s5));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        vhtable.setItems(bookings);
    }
}
