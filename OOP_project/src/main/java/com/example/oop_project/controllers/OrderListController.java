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
        type.getItems().add("???????ng B???");
        type.getItems().add("H??ng Kh??ng");
        type.getSelectionModel().selectFirst();

        cb_search.getItems().add("Ng?????i g???i");
        cb_search.getItems().add("?????a ch???");
        cb_search.getItems().add("Chi ph?? v???n chuy???n");
//        cb_search.getSelectionModel().selectFirst();

        list = FXCollections.observableArrayList(
                new roadDeliver("Nguy???n Minh Ch??u", "????m Ti???n ?????t", "H?? N???i", 10, "H??ng", 6, LocalDate.of(2021, 7, 25)),
                new roadDeliver("Nguy???n Minh Ng???c", "????m Ti???n ?????t", "H?? N???i", 10, "H??ng", 6, LocalDate.of(2021, 12, 25)),
                new roadDeliver("L????ng Ph????ng Linh", "????m Ti???n ?????t", "Nha Trang", 10, "H??ng", 6, LocalDate.of(2021, 12, 11)),

                new airDeliver("Nguy???n Ng???c Kh??nh", "Nguy???n S??n T??ng", "???? N???ng", 20, "Bim Bim", 7, LocalDate.of(2021, 8, 25)

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
            alert.setContentText("B???n ph???i ??i???n ?????y ????? th??ng tin!");
            alert.show();
            return;
        }
        switch (type.getValue()) {
            case "???????ng B???":
                order = new roadDeliver(senderName.getText(), receiverName.getText(), receivedAddress.getText(), Double.parseDouble(distance.getText()), item.getText(), Double.parseDouble(weight.getText()), date.getValue());
                break;
            case "H??ng Kh??ng":
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
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "B???n c?? mu???n x??a kh??ng?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert1.showAndWait();

            if (alert1.getResult() == ButtonType.YES) {
                list.remove(getSelectedOrder);

                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("X??a th??nh c??ng!!");

                alert.show();
            }

        } else {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("B???n h??y click ch???n v??o ?????i t?????ng c???n x??a ??? b???ng b??n!");
            alert.show();
        }

    }

    @FXML
    void updateOrder(ActionEvent event) {
        Order getSelectedOrder = table.getSelectionModel().getSelectedItem();
        if (getSelectedOrder == null) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("B???n h??y click ch???n v??o ?????i t?????ng c???n c???p nh???t ??? b???ng b??n!");
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
                case "???????ng B???":
                    getSelectedOrder.setCost(getSelectedOrder.calculateCost("???????ng B???")); break;
                case "H??ng Kh??ng":
                    getSelectedOrder.setCost(getSelectedOrder.calculateCost("H??ng Kh??ng")); break;
            }
            getSelectedOrder.setDate(date.getValue());
            table.refresh();

            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("C???p nh???t th??nh c??ng!!");
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

    // Ph???n c???a ?????t N??T T??M
    @FXML
    void findOrder(ActionEvent event) {
        ObservableList<Order> searchlist =FXCollections.observableArrayList();
        if (cb_search.getValue() == null) warning.setText("H??y ??i???n tr?????ng c???n t??m");
        else {
            switch (cb_search.getValue()) {
                case "Ng?????i g???i":
                    for (Object or : list) {
                        warning.setText("");
                        Order or1 = (Order) or; // duy???t ??p ki???u
                        if (or1.getSenderName().toLowerCase().contains(findTextField.getText().toLowerCase())) {
                            searchlist.add(or1);
                        }
//                        if(or1.getDate().getMonth().toString().equals("JULY")) System.out.println("dat");

                    }
                    break;
                case "?????a ch???":
                    for (Object or : list) {
                        warning.setText("");
                        Order or1 = (Order) or;
                        if (or1.getReceivedAddress().contains(findTextField.getText())) searchlist.add(or1);

                    }
                    break;
                case "Chi ph?? v???n chuy???n":
                    try {
                        double cost = Double.parseDouble(findTextField.getText());

                        for (Object or : list) {
                            warning.setText("");
                            Order or1 = (Order) or;

                            if (or1.getCost() > Double.parseDouble(findTextField.getText())) searchlist.add(or1);

                        }
                    } catch (NumberFormatException e) {
                        warning.setText("Gi?? tr??? kh??ng h???p l???! M???i nh???p l???i.");
                    }
                    break;

            }
        }

        table.setItems(searchlist); // hi???n l??n tr??n b???ng list t??m

    }

    // PH??N C???A ?????T
    @FXML
    void refreshAction(ActionEvent event) {
        warning.setText("");
        table.setItems(list);
    }

// N??T TH???NG K?? DOANH THU
    @FXML
    void listAction(ActionEvent event) {
        ObservableList<Order> searchlist =FXCollections.observableArrayList();
        LocalDate currentdate = LocalDate.now();
        Month currentMonth = currentdate.getMonth();
        double sumOfCost=0;
        for (Object or : list) {
            warning.setText("");
            Order ord = (Order) or; // duy???t ??p ki???u
            if(ord.getDate().getMonth().toString().equals(currentMonth.toString())) {
                searchlist.add(ord);
                sumOfCost+=ord.getCost();

            }
//

        }
        table.setItems(searchlist);
        lbl_sumOfCost.setText("T???ng doanh thu:  "+sumOfCost);


    }

}
