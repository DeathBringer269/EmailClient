package com.client.controller;

import com.client.EmailManager;
import com.client.view.ViewFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginWindowController extends BaseController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorField;

    public LoginWindowController(EmailManager emailManager, ViewFactory viewFactory, String fxmlName) {
        super(emailManager, viewFactory, fxmlName);
    }

    @FXML
    void onLoginButtonClicked(ActionEvent event) {
        viewFactory.showMainWindow();
        Stage stage = (Stage) errorField.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

}
