package com.example.oop_project.controllers;

import com.example.oop_project.HelloApplication;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import com.example.oop_project.models.Order;
import com.example.oop_project.models.roadDeliver;
import com.example.oop_project.models.airDeliver;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.Locale;
import java.util.ResourceBundle;

public class OrderListController implements Initializable {
    public static ObservableList list;

    @FXML
    private Button addBtn;

    @FXML
    private Button btn_refresh;

    @FXML
    private TableColumn<Order, Double> cost;

    @FXML
    private TableColumn<Order, Double> distance;

    @FXML
    private TableColumn<Order, String> receivedAddress;

    @FXML
    private TableColumn<Order, String> receiverName;

    @FXML
    private TableColumn<Order, String> senderName;
//***************
    @FXML
    private TableView<Order> table;

    @FXML
    private TableColumn<Order, String> type;

    @FXML
    private TableColumn<Order, String> item;

    @FXML
    private TableColumn<Order, Double> weight;

    @FXML
    private AnchorPane mainPain;
    Alert alert = new Alert(Alert.AlertType.NONE);
    Order order;

    @FXML
    private Button AddButton;

    @FXML
    private TextField distance1;

    @FXML
    private TextField item1;

    @FXML
    private TextField receivedAddress1;

    @FXML
    private TextField receiverName1;

    @FXML
    private TextField senderName1;

    @FXML
    private ComboBox<String> type1;

    @FXML
    private TextField weight1;

    @FXML
    private ComboBox<String> cb_searchbyName;

    @FXML
    private Button btn_search;



    @FXML
    private TextField search_field;
    @FXML
    void openAddDialog(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AddOrderView.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        table.setEditable(true);

        //init select combobox
        type1.getItems().add("Đường Bộ");
        type1.getItems().add("Hàng Không");
        type1.getSelectionModel().selectFirst();
        cb_searchbyName.getItems().add("Người gửi");
        cb_searchbyName.getItems().add("Địa chỉ");
        cb_searchbyName.getItems().add("Chi phí vận chuyển");
        cb_searchbyName.getSelectionModel().selectFirst();

        list = FXCollections.observableArrayList(
            new roadDeliver("chau", "dat", "ha noi", 10, "hang", 6),

            new airDeliver("chau", "tung", "da nang", 20, "bim bim", 7),
            new roadDeliver("trang", "dat", "ha noi", 10, "hang", 6)


        );

        cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        distance.setCellValueFactory(new PropertyValueFactory<>("distance"));
        receivedAddress.setCellValueFactory(new PropertyValueFactory<>("receivedAddress"));
        receiverName.setCellValueFactory(new PropertyValueFactory<>("receiverName"));
        senderName.setCellValueFactory(new PropertyValueFactory<>("senderName"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        item.setCellValueFactory(new PropertyValueFactory<>("item"));
        weight.setCellValueFactory(new PropertyValueFactory<>("weight"));

        table.setItems(list);





//        FilteredList<Order> filteredList = new FilteredList<>(list,b->true);
//        search_field.textProperty().addListener((observable,oldValue,newValue)->{
//            filteredList.setPredicate(roadDeliver -> {
//                if(newValue.isEmpty()||newValue.isBlank()||newValue==null){
//                    return true;
//                }
//                String searchKeyword= newValue.toLowerCase();
//                if(roadDeliver.getSenderName().toLowerCase().indexOf(searchKeyword)>-1)
//                    return true;
//                else return false;
//            });
//        });
//        SortedList<Order> sortedList= new SortedList<>(filteredList);
//        sortedList.comparatorProperty().bind((ObservableValue<? extends Comparator<? super Order>>) table.comparatorProperty());
//        table.setItems(sortedList);
//      https://edencoding.com/search-bar-dynamic-filtering/




    }

    public void addOrder(ActionEvent actionEvent) {
        if(senderName1.getText() == ""
                || receiverName1.getText() == ""
                || receivedAddress1.getText() == ""
                || distance1.getText() == ""
                || type1.getValue() == ""
                || item.getText() == ""
                || weight1.getText() == "") {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Bạn phải điền đầy đủ thông tin!");
            alert.show();
            return;
        }
        switch (type1.getValue()){
            case "Đường Bộ":
                order = new roadDeliver(senderName1.getText(), receiverName1.getText(), receivedAddress1.getText(), Double.parseDouble(distance1.getText()), item1.getText(), Double.parseDouble(weight1.getText()));
                break;
            case "Hàng Không":
                order = new airDeliver(senderName1.getText(), receiverName1.getText(), receivedAddress1.getText(), Double.parseDouble(distance1.getText()), item1.getText(), Double.parseDouble(weight1.getText()));
                break;
            default: break;
        }
        list.add(order);
  //***********************
        table.setItems(list);


    }
    // phan cua Dat
    // Nút tìm
    @FXML
    void searchAction(ActionEvent event) {

        ObservableList<Order> searchlist =FXCollections.observableArrayList();
        //
        switch (cb_searchbyName.getValue()) {
            case "Người gửi":
                for (Object or : list) {
                    Order or1 = (Order) or; // duyệt ép kiểu
                    if (or1.getSenderName().contains(search_field.getText()))     searchlist.add(or1);

                }
                break;
            case "Địa chỉ":
                for (Object or : list) {
                    Order or1 = (Order) or;
                    if (or1.getReceivedAddress().contains(search_field.getText()))     searchlist.add(or1);

                }
                break;
            case  "Chi phí vận chuyển":
                try {
                    double cost = Double.parseDouble(search_field.getText());
                    
                }catch (NumberFormatException e) {
                    System.out.println("dddddddddd");
                }
                for (Object or : list) {
                    Order or1 = (Order) or;

                    if (or1.getCost()>Double.parseDouble(search_field.getText()))     searchlist.add(or1);

                }
                break;
        }
        table.setItems(searchlist); // hiện lên trên bảng list tìm
    }

    @FXML
    void refreshAction(ActionEvent event) {
        table.setItems(list);
    }   // set lại list
}
