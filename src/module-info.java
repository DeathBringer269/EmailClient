module EmailClient {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.web;

    opens com.client;
    opens com.client.view;
    opens com.client.controller;
    
}