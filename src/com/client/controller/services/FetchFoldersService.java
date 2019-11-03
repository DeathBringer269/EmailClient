package com.client.controller.services;

import com.client.model.EmailTreeItem;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Store;

public class FetchFoldersService extends Service<Void> {

    private Store store;

    private EmailTreeItem<String> folderRoot;

    public FetchFoldersService(Store store, EmailTreeItem<String> folderRoot) {
        this.store = store;
        this.folderRoot = folderRoot;
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                fetchFolders();
                return null;
            }
        };
    }

    private void fetchFolders() throws MessagingException {
        Folder[] folders = store.getDefaultFolder().list();
        handleFolders(folders, folderRoot);
    }

    private void handleFolders(Folder[] folders, EmailTreeItem<String> folderRoot) throws MessagingException {
        for(Folder folder: folders) {
            EmailTreeItem<String> emailTreeItem = new EmailTreeItem<String>(folder.getName());
            folderRoot.getChildren().add(emailTreeItem);
            folderRoot.setExpanded(true);
            fetchMessagesFolder(folder, emailTreeItem);
            if(folder.getType() == Folder.HOLDS_FOLDERS) {
                Folder[] subFolders = folder.list();
                handleFolders(subFolders, emailTreeItem);
            }
        }
    }

    private void fetchMessagesFolder(Folder folder, EmailTreeItem<String> emailTreeItem) {
        Service fetchMessagesService = new Service() {

            @Override
            protected Task createTask() {
                return new Task() {

                    @Override
                    protected Object call() throws Exception {
                        if(folder.getType() != Folder.HOLDS_FOLDERS) {
                            folder.open(Folder.READ_WRITE);
                            int folderSize = folder.getMessageCount();
                            for(int i = folderSize; i> 0;i--) {
//                                System.out.println(folder.getMessage(i).getSubject());
                                emailTreeItem.addEmail(folder.getMessage(i));
                            }
                        }
                        return null;
                    }
                };
            }
        };
        fetchMessagesService.start();
    }
}
