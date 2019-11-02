package com.client;

import com.client.controller.services.FetchFoldersService;
import com.client.model.EmailAccount;
import com.client.model.EmailTreeItem;
import javafx.scene.control.TreeItem;

public class EmailManager {

    private EmailTreeItem<String> folderRoot = new EmailTreeItem<String>("");

    public void setFolderRoot(EmailTreeItem<String> folderRoot) {
        this.folderRoot = folderRoot;
    }

    public EmailTreeItem<String> getFolderRoot() {
        return folderRoot;
    }

    public void addEmailAccount(EmailAccount emailAccount) {
        EmailTreeItem<String> treeItem = new EmailTreeItem<String>(emailAccount.getAddress());
        FetchFoldersService fetchFoldersService = new FetchFoldersService(emailAccount.getStore(),treeItem);
        fetchFoldersService.start();
        folderRoot.getChildren().add(treeItem);
    }
}
