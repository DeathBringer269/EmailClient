package com.client.controller;

import com.client.EmailManager;
import com.client.controller.services.LoginService;
import com.client.model.EmailAccount;
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
        if(fieldsAreValid()) {
            EmailAccount emailAccount = new EmailAccount(usernameField.getText(),passwordField.getText());
            LoginService loginService = new LoginService(emailAccount,emailManager);
            EmailLoginResult emailLoginResult = loginService.login();
            switch(emailLoginResult) {
                case SUCCESS:
                    System.out.print("Logged in using " + usernameField.getText());
                    return;
            }
        }
    }

    private boolean fieldsAreValid() {
        if(usernameField.getText().isEmpty()) {
            errorField.setText("Username cannot be empty");
            return false;
        } else if(passwordField.getText().isEmpty()) {
            errorField.setText("Password cannot be empty");
            return false;
        }else {
            errorField.setText("");
            return true;
        }
    }

}
