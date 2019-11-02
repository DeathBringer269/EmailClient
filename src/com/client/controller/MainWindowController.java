package com.client.controller;

import com.client.EmailManager;
import com.client.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeView;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController extends BaseController implements Initializable {

    @FXML
    private TreeView<String> emailTreeView;

    @FXML
    private TableView<?> emailTableView;

    @FXML
    private WebView emailWebView;

    public MainWindowController(EmailManager emailManager, ViewFactory viewFactory, String fxmlName) {
        super(emailManager, viewFactory, fxmlName);
    }

    @FXML
    void optionsActions( ) {
        viewFactory.showOptionsWindow();
    }

    @FXML
    void onAddAcountAction( ) {
        viewFactory.showLoginWindow();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUpEmailTreeView();
    }

    private void setUpEmailTreeView() {
        emailTreeView.setRoot(emailManager.getFolderRoot());
        emailTreeView.setShowRoot(false);
    }
}
