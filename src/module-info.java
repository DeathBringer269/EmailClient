module EmailClient {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.web;
    requires java.mail;
    requires activation;

    opens com.client;
    opens com.client.view;
    opens com.client.controller;
    opens com.client.model;
}