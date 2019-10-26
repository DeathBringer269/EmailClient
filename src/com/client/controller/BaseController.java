package com.client.controller;

import com.client.EmailManager;
import com.client.view.ViewFactory;

public abstract class BaseController {

    protected EmailManager emailManager;

    protected ViewFactory viewFactory;

    private String fxmlName;

    public BaseController(EmailManager emailManager, ViewFactory viewFactory, String fxmlName) {
        this.emailManager = emailManager;
        this.viewFactory = viewFactory;
        this.fxmlName = fxmlName;
    }

    public String getFxmlName() {
        return fxmlName;
    }

}
