package ui.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.model.ShopService;

import java.io.IOException;

public abstract class RequestHandler {

    private ShopService service;

    public abstract void handleRequest (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;

    public void setService(ShopService service) {
        this.service = service;
    }

    public ShopService getService() {
        return service;
    }


}
