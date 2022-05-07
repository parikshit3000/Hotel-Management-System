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
import java.sql.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Viewhotels implements Initializable {
    String user;
    public static String hot_id;
    String hotel_id;

    @FXML
    private TextField bookid;

    @FXML
    private TableColumn<HotelModel, String> col_id;

    @FXML
    private TableView<HotelModel> hoteltable;

    @FXML
    private TableColumn<HotelModel, Integer> col_rooms;

    @FXML
    private TableColumn<HotelModel, String> col_facility;

    @FXML
    private TableColumn<HotelModel, String> col_name;

    @FXML
    private TableColumn<HotelModel, String> col_price;

    @FXML
    private TableColumn<HotelModel, Integer> col_rating;

    @FXML
    private Label loginname;

    @FXML
    void backviewhot(ActionEvent event) {
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
    void handleButtonActionContinueBooking(ActionEvent event) {
    hotel_id = bookid.getText();
    System.out.println(hotel_id);
    setHid(hotel_id);
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root", "");

            PreparedStatement statement = con.prepareStatement("select * from hotels where id='"+hotel_id+"';");
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("bookHotel.fxml"));
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
        }catch(Exception e){System.out.println(e);}
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user = HelloApplication.getUser();
        loginname.setText(user);

        col_id.setCellValueFactory(new PropertyValueFactory<HotelModel, String>("hid"));
        col_name.setCellValueFactory(new PropertyValueFactory<HotelModel, String>("name"));
        col_rooms.setCellValueFactory(new PropertyValueFactory<HotelModel, Integer>("rooms"));
        col_rating.setCellValueFactory(new PropertyValueFactory<HotelModel, Integer>("rating"));
        col_price.setCellValueFactory(new PropertyValueFactory<HotelModel, String>("price"));
        col_facility.setCellValueFactory(new PropertyValueFactory<HotelModel, String>("fac"));

        ObservableList<HotelModel> items = FXCollections.observableArrayList();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root", "");

            PreparedStatement statement = con.prepareStatement("select * from hotels;");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String s0 = rs.getString(1);
                String s1 = rs.getString(2);
                int s2 = rs.getInt(3);
                int s3 = rs.getInt(4);
                String s4 = rs.getString(5);
                String s5 = rs.getString(6);

                items.add(new HotelModel(s0, s1, s2, s3, s4, s5));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        hoteltable.setItems(items);
    }
    public void setHid(String str){
        hot_id=str;
    }
    public static String getHid(){
        return hot_id;
    }
}
