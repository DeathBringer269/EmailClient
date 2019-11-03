package com.client.controller;

import com.client.EmailManager;
import com.client.controller.services.LoginService;
import com.client.model.EmailAccount;
import com.client.view.ViewFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginWindowController extends BaseController implements Initializable {

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
    void onLoginButtonClicked( ) {
        if(fieldsAreValid()){
            EmailAccount emailAccount = new EmailAccount(usernameField.getText(), passwordField.getText());
            LoginService loginService = new LoginService(emailAccount, emailManager);
                loginService.start();
                loginService.setOnSucceeded(event -> {
                    EmailLoginResult emailLoginResult = loginService.getValue();
                    switch (emailLoginResult) {
                        case SUCCESS:
                            System.out.println("login succesfull!!!" + emailAccount);
                            if(!viewFactory.isMainViewInitialized()) {
                                viewFactory.showMainWindow();
                            }
                            Stage stage = (Stage) errorField.getScene().getWindow();
                            viewFactory.closeStage(stage);
                            return;
                        case FAILED_BY_CREDENTIALS:
                            errorField.setText("Invalid Credentials");
                            return;
                        case FAILED_BY_NETWORK:
                            errorField.setText("Unexpected error");
                            return;
                        default:
                            return;
                    }
                });
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameField.setText("");
        passwordField.setText("");
    }
}
