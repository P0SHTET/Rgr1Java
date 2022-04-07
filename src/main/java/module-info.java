module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires static lombok;
    requires slf4j.api;
    requires org.kordamp.bootstrapfx.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.datatype.jsr310;

    opens com.example to javafx.fxml;
    exports com.example;
    exports com.example.controllers;

    opens com.example.model to com.google.gson, javafx.fxml, com.fasterxml.jackson.databind;
    opens com.example.controllers to javafx.fxml;
}