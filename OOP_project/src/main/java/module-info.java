module com.example.oop_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.example.oop_project to javafx.fxml;
    exports com.example.oop_project;
    exports com.example.oop_project.models;
    exports com.example.oop_project.controllers;
    opens com.example.oop_project.controllers to javafx.fxml;
}