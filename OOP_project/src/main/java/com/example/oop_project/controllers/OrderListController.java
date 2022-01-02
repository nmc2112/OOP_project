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
import java.time.Year;
import java.util.*;

public class OrderListController implements Initializable {
    public static ObservableList<Order> list;
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
    private Label lbl_sumOfCost;

    Alert alert = new Alert(Alert.AlertType.NONE);
    Order order;

    //hàm khởi tạo ban đầu của giao diện
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

        //init table
        list = FXCollections.observableArrayList(
            new roadDeliver("Nguyễn Minh Châu", "Đàm Tiến Đạt", "15 ngõ 142 Nguyễn Ngọc Nại, Hà Nội", 10, "Kẹo", 6, LocalDate.of(2021, 7, 25)),
            new roadDeliver("Nguyễn Minh Ngọc", "Nguyễn Võ Trí", "1805B tòa nhà Rivera Park, Hà Nội", 10, "Nước ngọt", 6, LocalDate.of(2022, 1, 25)),
            new roadDeliver("Lại Dương Phương Linh", "Trần Ngọc Bách", "2501 tòa nhà Hinode City, Hà Nội", 10, "Rượu", 6, LocalDate.of(2022, 1, 11)),

            new airDeliver("Nguyễn Ngọc Khánh", "Nguyễn Sơn Tùng", "17 ngõ 92 Mai Động, Hà Nội", 20, "Bim Bim", 7, LocalDate.of(2021, 8, 25)

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

    //Phần của Nguyễn Minh Châu 20198208
    //hàm thêm order
    public void addOrder(ActionEvent actionEvent) {
        //check rỗng
        if (checkNull()) return;

        //thêm order dựa theo type là đường bộ hay hàng không
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
        alert.setAlertType(Alert.AlertType.INFORMATION);
        alert.setContentText("Bạn đã thêm thành công!!");
        alert.show();
        resetFields();
    }

    //hàm update order
    @FXML
    void updateOrder(ActionEvent event) {
        //lấy ra order được chọn từ bảng
        Order getSelectedOrder = table.getSelectionModel().getSelectedItem();
        if (getSelectedOrder == null) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Bạn hãy click chọn vào đối tượng cần cập nhật ở bảng bên!");
            alert.show();
        } else {
            //check rỗng
            if (checkNull()) return;

            //xóa đối tượng cũ từ bảng và thêm đối tượng mới với các trường đã được sửa từ đối tượng cũ bị xóa
            Order newOrder = null;
            switch (type.getValue()) {
                case "Đường Bộ":
                    newOrder = new roadDeliver(
                            senderName.getText(),
                            receiverName.getText(),
                            receivedAddress.getText(),
                            Double.parseDouble(distance.getText()),
                            item.getText(),
                            Double.parseDouble(weight.getText()),
                            date.getValue()
                    );
                    break;
                case "Hàng Không":
                    newOrder = new airDeliver(
                            senderName.getText(),
                            receiverName.getText(),
                            receivedAddress.getText(),
                            Double.parseDouble(distance.getText()),
                            item.getText(),
                            Double.parseDouble(weight.getText()),
                            date.getValue()
                    );
                    break;
            }
            list.remove(getSelectedOrder);
            list.add(newOrder);
            table.refresh();

            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("Cập nhật thành công!!");
            alert.show();
            resetFields();

        }
    }

    //check các trường bị null
    private boolean checkNull() {
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
            return true;
        }
        return false;
    }

    // Phần của Nguyễn Sơn Tùng 20198271
    // hàm xóa order
    @FXML
    void deleteOrder(ActionEvent event) {
        //chọn order từ bảng
        Order getSelectedOrder = table.getSelectionModel().getSelectedItem();
        if (getSelectedOrder != null) {
            //check xem có chắc chắn muốn xóa không
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "Bạn có muốn xóa không?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert1.showAndWait();

            //yes thì xóa
            if (alert1.getResult() == ButtonType.YES) {
                list.remove(getSelectedOrder);
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("Xóa thành công!!");
                alert.show();
                resetFields();

            }
        } else {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Bạn hãy click chọn vào đối tượng cần xóa ở bảng bên!");
            alert.show();
            resetFields();
        }
    }


    // hàm thông kê doanh thu theo tháng hiện tại
    @FXML
    void listAction(ActionEvent event) {
        ObservableList<Order> searchlist = FXCollections.observableArrayList();
        double sumOfCost = 0;

        // lấy tháng và năm hiện tại
        LocalDate currentdate = LocalDate.now();
        Month currentMonth = currentdate.getMonth();
        int currentYear = currentdate.getYear();
        //duyệt các order từ list xem nếu như tháng năm của order trùng với tháng năm hiện tại
        for (Order ord : list) {
            warning.setText("");
            if (ord.getDate().getMonth().equals(currentMonth) && ord.getDate().getYear() == currentYear) {
                searchlist.add(ord);
                //cộng tổng các cost của order
                sumOfCost += ord.getCost();

            }
        }
        //đưa các order thỏa mãn ra table
        table.setItems(searchlist);
        resetFields();
        lbl_sumOfCost.setText("Tổng doanh thu:  " + sumOfCost + " đồng.");
    }

    // hàm lấy dữ liệu từ dòng đã chọn đưa ra các text field bên trái
    public void selectOrderMouseClicked(MouseEvent mouseEvent) {
        Order getSelectedOrder = table.getSelectionModel().getSelectedItem();
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

    // Phần của Đàm Tiến Đạt 20198212
    // hàm tìm order
    @FXML
    void findOrder(ActionEvent event) {
        ObservableList<Order> searchlist = FXCollections.observableArrayList();
        //check lựa chọn của combobox
        if (cb_search.getValue() == null) warning.setText("Hãy điền trường cần tìm");
        else {
            switch (cb_search.getValue()) {
                //thêm order trùng với tên người gửi vào list
                case "Người gửi":
                    for (Order or : list) {
                        warning.setText("");
                        if (or.getSenderName().toLowerCase().contains(findTextField.getText().toLowerCase())) searchlist.add(or);
                    }
                    break;
                //thêm order trùng với địa chỉ vào list
                case "Địa chỉ":
                    for (Order or : list) {
                        warning.setText("");
                        if (or.getReceivedAddress().toLowerCase().contains(findTextField.getText().toLowerCase())) searchlist.add(or);
                    }
                    break;
                //thêm order có chi phí vận chuyển lớn hơn số nhập vào
                case "Chi phí vận chuyển":
                    if(findTextField.getText() != null)
                        try {
                            double cost = Double.parseDouble(findTextField.getText());

                            for (Order or : list) {
                                warning.setText("");
                                if (or.getCost() > cost) searchlist.add(or);

                            }
                        } catch (NumberFormatException e) {
                            warning.setText("Giá trị không hợp lệ! Mời nhập lại.");
                        }
                    break;

            }
        }
        // hiện các order thỏa mãn lên bảng
        table.setItems(searchlist);
    }

    //phần code dùng chung
    @FXML
    void refreshAction(ActionEvent event) {
        warning.setText("");
        table.setItems(list);
        resetFields();
    }

    // reset các fields
    public void resetFields(){
        distance.setText("");
        item.setText("");
        receivedAddress.setText("");
        receiverName.setText("");
        senderName.setText("");
        type.setValue(null);
        date.setValue(null);
        weight.setText("");
        findTextField.setText("");
        lbl_sumOfCost.setText("");
    }
}
