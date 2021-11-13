package com.example.oop_project.controllers;

import com.example.oop_project.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OrderListController implements Initializable {
    public static ObservableList list;
    @FXML
    private Button addBtn;

    @FXML
    private Button DeleteButton;

    @FXML
    private Button UpdateButton1;

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

    @FXML
    private TableView<?> table;

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

        list = FXCollections.observableArrayList(
            new roadDeliver("chau", "dat", "ha noi", 10, "hang", 6),

            new airDeliver("chau", "tung", "da nang", 20, "bim bim", 7)
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

    }
    @FXML
    void deleteOrder(ActionEvent event) {
        Order getSelectedOrder = (Order) table.getSelectionModel().getSelectedItem();
        if(getSelectedOrder != null){
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete ?" , ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert1.showAndWait();

            if (alert1.getResult() == ButtonType.YES) {
                list.remove(getSelectedOrder);

//                table.setItems(list);

                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("Xóa thành công!!");

                alert.show();
            }

        }else {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("bạn hãy click chọn vào đối tượng cần xóa ở bảng bên!");
            alert.show();
        }

    }
    @FXML
    void updateOrder(ActionEvent event) {
        Order getSelectedOrder = (Order) table.getSelectionModel().getSelectedItem();
        if(getSelectedOrder == null) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("bạn hãy click chọn vào đối tượng cần cập nhật ở bảng bên!");
            alert.show();
        }else {
            getSelectedOrder.setSenderName(senderName1.getText());
            getSelectedOrder.setReceiverName(receiverName1.getText());
            getSelectedOrder.setReceivedAddress(receivedAddress1.getText());
            getSelectedOrder.setType(type1.getValue());
            getSelectedOrder.setDistance(Double.parseDouble(distance1.getText()));
            getSelectedOrder.setItem(item1.getText());
            getSelectedOrder.setWeight(Double.parseDouble(weight1.getText()));


            table.refresh();

            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("Cập nhật thành công!!");
            alert.show();


        }



    }

    public void selectOrderMouseClicked(MouseEvent mouseEvent) {
        Order getSelectedOrder = (Order) table.getSelectionModel().getSelectedItem();
        if(getSelectedOrder != null){
            senderName1.setText(getSelectedOrder.getSenderName());
            receiverName1.setText(getSelectedOrder.getReceiverName());
            receivedAddress1.setText(getSelectedOrder.getReceivedAddress());
            distance1.setText(String.valueOf((getSelectedOrder.getDistance())));
            type1.setValue(getSelectedOrder.getType());
            item1.setText(getSelectedOrder.getItem());
            weight1.setText(String.valueOf(getSelectedOrder.getWeight()));

        }


    }
}
