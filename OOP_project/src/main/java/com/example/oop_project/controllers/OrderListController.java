package com.example.oop_project.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import com.example.oop_project.models.Order;
import com.example.oop_project.models.roadDeliver;
import com.example.oop_project.models.airDeliver;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class OrderListController implements Initializable {
    public static ObservableList<Order> list;
    @FXML
    private Button addBtn;

    @FXML
    private Button DeleteButton;

    @FXML
    private Button UpdateButton1;

    @FXML
    private Button btn_search;

    @FXML
    private TextField findTextField;


    @FXML
    private TableColumn<Order, Double> colCost;

    @FXML
    private TableColumn<Order, Double> colDistance;

    @FXML
    private TableColumn<Order, String> colReceivedAddress;

    @FXML
    private TableColumn<Order, String> colReceiverName;

    @FXML
    private TableColumn<Order, String> colSenderName;

    @FXML
    private TableView<Order> table;

    @FXML
    private TableColumn<Order, String> colType;

    @FXML
    private TableColumn<Order, String> colItem;

    @FXML
    private TableColumn<Order, Double> colWeight;


    @FXML
    private TableColumn<Order, LocalDate> colDate;

    @FXML
    private AnchorPane mainPain;
    Alert alert = new Alert(Alert.AlertType.NONE);
    Order order;

    @FXML
    private Button AddButton;

    @FXML
    private TextField distance;

    @FXML
    private TextField item;

    @FXML
    private TextField receivedAddress;

    @FXML
    private TextField receiverName;

    @FXML
    private TextField senderName;

    @FXML
    private ComboBox<String> type;

    @FXML
    private DatePicker date;

    @FXML
    private TextField weight;

    @FXML
    private ComboBox<String> cb_search;

    @FXML
    private Label warning;

    @FXML
    private Button btn_refresh;

    @FXML
    private Button btn_list;

    @FXML
    private Label lbl_sumOfCost;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        table.setEditable(true);

        //init select combobox
        type.getItems().add("Đường Bộ");
        type.getItems().add("Hàng Không");
        type.getSelectionModel().selectFirst();

        cb_search.getItems().add("Người gửi");
        cb_search.getItems().add("Địa chỉ");
        cb_search.getItems().add("Chi phí vận chuyển");
//        cb_search.getSelectionModel().selectFirst();

        list = FXCollections.observableArrayList(
                new roadDeliver("Nguyễn Minh Châu", "Đàm Tiến Đạt", "Hà Nội", 10, "Hàng", 6, LocalDate.of(2021, 7, 25)),
                new roadDeliver("Nguyễn Minh Ngọc", "Đàm Tiến Đạt", "Hà Nội", 10, "Hàng", 6, LocalDate.of(2021, 12, 25)),
                new roadDeliver("Lương Phương Linh", "Đàm Tiến Đạt", "Nha Trang", 10, "Hàng", 6, LocalDate.of(2021, 12, 11)),

                new airDeliver("Nguyễn Ngọc Khánh", "Nguyễn Sơn Tùng", "Đà Nẵng", 20, "Bim Bim", 7, LocalDate.of(2021, 8, 25)

        ));


        colCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        colDistance.setCellValueFactory(new PropertyValueFactory<>("distance"));
        colReceivedAddress.setCellValueFactory(new PropertyValueFactory<>("receivedAddress"));
        colReceiverName.setCellValueFactory(new PropertyValueFactory<>("receiverName"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colSenderName.setCellValueFactory(new PropertyValueFactory<>("senderName"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colItem.setCellValueFactory(new PropertyValueFactory<>("item"));
        colWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        table.setItems(list);

    }

    public void addOrder(ActionEvent actionEvent) {
        if (senderName.getText() == ""
            || receiverName.getText() == ""
            || receivedAddress.getText() == ""
            || distance.getText() == ""
            || type.getValue() == ""
            || item.getText() == ""
            || weight.getText() == ""
            || date.getValue() == null) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Bạn phải điền đầy đủ thông tin!");
            alert.show();
            return;
        }
        switch (type.getValue()) {
            case "Đường Bộ":
                order = new roadDeliver(senderName.getText(), receiverName.getText(), receivedAddress.getText(), Double.parseDouble(distance.getText()), item.getText(), Double.parseDouble(weight.getText()), date.getValue());
                break;
            case "Hàng Không":
                order = new airDeliver(senderName.getText(), receiverName.getText(), receivedAddress.getText(), Double.parseDouble(distance.getText()), item.getText(), Double.parseDouble(weight.getText()), date.getValue());
                break;
            default:
                break;
        }
        list.add(order);

    }

    @FXML
    void deleteOrder(ActionEvent event) {
        Order getSelectedOrder = table.getSelectionModel().getSelectedItem();
        if (getSelectedOrder != null) {
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "Bạn có muốn xóa không?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert1.showAndWait();

            if (alert1.getResult() == ButtonType.YES) {
                list.remove(getSelectedOrder);

                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("Xóa thành công!!");

                alert.show();
            }

        } else {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Bạn hãy click chọn vào đối tượng cần xóa ở bảng bên!");
            alert.show();
        }

    }

    @FXML
    void updateOrder(ActionEvent event) {
        Order getSelectedOrder = table.getSelectionModel().getSelectedItem();
        if (getSelectedOrder == null) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Bạn hãy click chọn vào đối tượng cần cập nhật ở bảng bên!");
            alert.show();
        } else {

            getSelectedOrder.setSenderName(senderName.getText());
            getSelectedOrder.setReceiverName(receiverName.getText());
            getSelectedOrder.setReceivedAddress(receivedAddress.getText());
            getSelectedOrder.setType(type.getValue());
            getSelectedOrder.setDistance(Double.parseDouble(distance.getText()));
            getSelectedOrder.setItem(item.getText());
            getSelectedOrder.setWeight(Double.parseDouble(weight.getText()));
            switch (type.getValue()){
                case "Đường Bộ":
                    getSelectedOrder.setCost(getSelectedOrder.calculateCost("Đường Bộ")); break;
                case "Hàng Không":
                    getSelectedOrder.setCost(getSelectedOrder.calculateCost("Hàng Không")); break;
            }
            getSelectedOrder.setDate(date.getValue());
            table.refresh();

            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("Cập nhật thành công!!");
            alert.show();


        }


    }

    public void selectOrderMouseClicked(MouseEvent mouseEvent) {
        Order getSelectedOrder = (Order) table.getSelectionModel().getSelectedItem();
        if (getSelectedOrder != null) {
            senderName.setText(getSelectedOrder.getSenderName());
            receiverName.setText(getSelectedOrder.getReceiverName());
            receivedAddress.setText(getSelectedOrder.getReceivedAddress());
            distance.setText(String.valueOf((getSelectedOrder.getDistance())));
            type.setValue(getSelectedOrder.getType());
            item.setText(getSelectedOrder.getItem());
            weight.setText(String.valueOf(getSelectedOrder.getWeight()));
            date.setValue(getSelectedOrder.getDate());

        }


    }

    // Phần của Đạt NÚT TÌM
    @FXML
    void findOrder(ActionEvent event) {
        ObservableList<Order> searchlist =FXCollections.observableArrayList();
        if (cb_search.getValue() == null) warning.setText("Hãy điền trường cần tìm");
        else {
            switch (cb_search.getValue()) {
                case "Người gửi":
                    for (Object or : list) {
                        warning.setText("");
                        Order or1 = (Order) or; // duyệt ép kiểu
                        if (or1.getSenderName().toLowerCase().contains(findTextField.getText().toLowerCase())) {
                            searchlist.add(or1);
                        }
//                        if(or1.getDate().getMonth().toString().equals("JULY")) System.out.println("dat");

                    }
                    break;
                case "Địa chỉ":
                    for (Object or : list) {
                        warning.setText("");
                        Order or1 = (Order) or;
                        if (or1.getReceivedAddress().contains(findTextField.getText())) searchlist.add(or1);

                    }
                    break;
                case "Chi phí vận chuyển":
                    try {
                        double cost = Double.parseDouble(findTextField.getText());

                        for (Object or : list) {
                            warning.setText("");
                            Order or1 = (Order) or;

                            if (or1.getCost() > Double.parseDouble(findTextField.getText())) searchlist.add(or1);

                        }
                    } catch (NumberFormatException e) {
                        warning.setText("Giá trị không hợp lệ! Mời nhập lại.");
                    }
                    break;

            }
        }

        table.setItems(searchlist); // hiện lên trên bảng list tìm

    }

    // PHÀN CỦA ĐẠT
    @FXML
    void refreshAction(ActionEvent event) {
        warning.setText("");
        table.setItems(list);
    }

// NÚT THỐNG KÊ DOANH THU
    @FXML
    void listAction(ActionEvent event) {
        ObservableList<Order> searchlist =FXCollections.observableArrayList();
        LocalDate currentdate = LocalDate.now();
        Month currentMonth = currentdate.getMonth();
        double sumOfCost=0;
        for (Object or : list) {
            warning.setText("");
            Order ord = (Order) or; // duyệt ép kiểu
            if(ord.getDate().getMonth().toString().equals(currentMonth.toString())) {
                searchlist.add(ord);
                sumOfCost+=ord.getCost();

            }
//

        }
        table.setItems(searchlist);
        lbl_sumOfCost.setText("Tổng doanh thu:  "+sumOfCost);


    }

}
